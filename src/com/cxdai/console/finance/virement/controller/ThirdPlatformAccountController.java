package com.cxdai.console.finance.virement.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.entity.Journal;
import com.cxdai.console.finance.virement.service.JournalService;
import com.cxdai.console.finance.virement.vo.JournalCnd;
import com.cxdai.console.finance.virement.vo.JournalVo;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.WordAndExcelGenerator;

/****
 * 第三方账户明细账Controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/finance/thirdPlatformAccount")
public class ThirdPlatformAccountController extends BaseController {

	private final static Logger logger = Logger.getLogger(ThirdPlatformAccountController.class);

	@Autowired
	private JournalService journalService;

	/***
	 * 第三方平台明细账主界面
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView journalMain() {
		return new ModelAndView("/finance/thirdPlatform/main");
	}

	/****
	 * 第三方平台明细账列表
	 * 
	 * @param journalCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageJournalList(@ModelAttribute JournalCnd journalCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			journalCnd.setAdduser(currentUser().getUserId());
			journalCnd.setStatus(Journal.STATUS_NOMAL);
			journalCnd.setType(Journal.TYPE_OTHER_PLATFORM);
			page = journalService.queryListForPage(journalCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return new ModelAndView("/finance/thirdPlatform/list").addObject("page", page);
	}

	/***
	 * 添加或编辑第三方平台明细账页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/addOrEditJournal")
	public ModelAndView addOrEditJournal(Integer id) {
		ModelAndView mv = new ModelAndView("/finance/thirdPlatform/addOrEdit");
		if (id != null) {
			Journal journal = journalService.selectByPrimaryKey(id);
			mv.addObject("journal", journal);
		}
		return mv;
	}

	/***
	 * 保存或修改第三方平台明细账
	 * 
	 * @param journalVo
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateJournal")
	@ResponseBody
	public MessageBox saveOrUpdateJournal(@ModelAttribute JournalVo journalVo) {
		try {
			journalVo.setType(Journal.TYPE_OTHER_PLATFORM);
			String result = journalService.judgeJournal(journalVo);
			if ("ok".equals(result)) {
				if (journalVo.getId() == null) {// 新增
					journalVo.setAddip(HttpTookit.getRealIpAddr(currentRequest()));
					journalVo.setAdduser(currentUser().getUserId());
				}
				journalVo.setUpdateip(HttpTookit.getRealIpAddr(currentRequest()));
				journalVo.setUpdateuser(currentUser().getUserId());

				journalService.saveOrUpdateJournal(journalVo);
			} else {
				return new MessageBox("0", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return new MessageBox("0", "操作失败!");
		}
		return new MessageBox("1", "操作成功!");
	}

	/****
	 * 详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail/{id}")
	public ModelAndView detail(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("/finance/thirdPlatform/detailJournal");
		if (id != null) {
			Journal journal = journalService.selectByPrimaryKey(id);
			mv.addObject("journal", journal);
		}
		return mv;

	}

	/****
	 * 删除现金日记账信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteJournal/{id}")
	@ResponseBody
	public MessageBox deleteJournal(@PathVariable Integer id) {
		try {

			journalService.deleteJournal(id, HttpTookit.getRealIpAddr(currentRequest()), currentUser().getUserId());
		} catch (Exception e) {
			logger.error(e);
			return new MessageBox("0", "删除失败!");
		}
		return new MessageBox("1", "删除成功!");
	}

	/****
	 * 导出日记账信息
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "downloadExcel")
	public void downloadJournalExcel(@ModelAttribute JournalCnd journalCnd, HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {

		journalCnd.setType(Journal.TYPE_OTHER_PLATFORM);
		List<Map<String, Object>> list = journalService.findJournalList(journalCnd);
		Map<String, Object> map = new HashMap<String, Object>();
		// 生成Excel时，office会提示格式错误，动态设置模板中ss:ExpandedRowCount属性，即可解决
		// http://blog.csdn.net/zhanwentao2/article/details/7298341
		map.put("rowSize", list == null ? 2 : list.size() + 2);
		map.put("table", list);
		// 提示：在调用工具类生成Word文档之前应当检查所有字段是否完整
		// 否则Freemarker的模板引擎在处理时可能会因为找不到值而报错 这里暂时忽略这个步骤了

		req.setCharacterEncoding("utf-8");
		File file = null;
		InputStream fin = null;
		ServletOutputStream out = null;
		try {

			// 调用工具类WordGenerator的createDoc方法生成Word文档
			file = WordAndExcelGenerator.createDocOrExcel("第三方平台明细账", map, "journal");
			fin = new FileInputStream(file);

			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/octet-stream");
			// 设置浏览器以下载的方式处理该文件默认名为resume.doc

			String fileName = new String("第三方平台明细账.xls".getBytes("GBK"), "iso8859-1");
			resp.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			out = resp.getOutputStream();
			byte[] buffer = new byte[512]; // 缓冲区
			int bytesToRead = -1;
			// 通过循环将读入的Word文件的内容输出到浏览器中
			while ((bytesToRead = fin.read(buffer)) != -1) {
				out.write(buffer, 0, bytesToRead);
			}
		} finally {
			if (fin != null)
				fin.close();
			if (out != null)
				out.close();
			if (file != null)
				file.delete(); // 删除临时文件
		}
		return;
	}

	/***
	 * 内部转账主界面
	 * 
	 * @return
	 */
	@RequestMapping("/searchJournalMain")
	public ModelAndView searchJournalMain() {
		return new ModelAndView("/finance/thirdPlatform/searchJournalMain");
	}

	@RequestMapping("/searchJournalList/{pageNo}")
	public ModelAndView searchJournalList(@ModelAttribute JournalCnd journalCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			journalCnd.setStatus(Journal.STATUS_NOMAL);
			journalCnd.setType(Journal.TYPE_OTHER_PLATFORM);
			page = journalService.queryListForPage(journalCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return new ModelAndView("/finance/thirdPlatform/searchJournalList").addObject("page", page);
	}
}
