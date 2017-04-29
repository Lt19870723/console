package com.cxdai.console.finance.virement.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
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
import com.cxdai.console.finance.virement.service.WithdrawalAnalysisService;
import com.cxdai.console.finance.virement.vo.WithdrawalAnalysisCnd;
import com.cxdai.console.finance.virement.vo.WithdrawalAnalysisVo;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.WordAndExcelGenerator;

/****
 * 充值数据分析
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/finance/withdrawalAnalysis")
public class WithdrawalAnalysisController extends BaseController {
	private final static Logger logger = Logger.getLogger(WithdrawalAnalysisController.class);

	@Autowired
	private WithdrawalAnalysisService withdrawalAnalysisService;

	/****
	 * 
	 * <p>
	 * Description:充值数据主页面<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月17日
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView index() {
		return new ModelAndView("/finance/withdrawalAnalysis/main");
	}

	/****
	 * 
	 * <p>
	 * Description:充值数据列表<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月17日
	 * @param withdrawalAnalysisCnd
	 * @param pageNo
	 * @return ModelAndView
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView serachList(@ModelAttribute WithdrawalAnalysisCnd withdrawalAnalysisCnd,
			@PathVariable("pageNo") Integer pageNo) {
		ModelAndView mv = new ModelAndView("/finance/withdrawalAnalysis/list");
		Page page = null;
		Map<String, BigDecimal> resultMap = null;
		try {
			page = withdrawalAnalysisService.queryWithdrawalAnalysisListForPages(withdrawalAnalysisCnd, pageNo,
					Constants.CONSOLE_PAGE_SIZE);
			resultMap = withdrawalAnalysisService.getTotalDataMap(withdrawalAnalysisCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("提现数据查询异常，原因：" + e);
		}
		return mv.addObject("page", page).addObject("resultMap", resultMap);
	}

	/***
	 * 
	 * <p>
	 * Description:根据条件总提现总数<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月18日
	 * @param withdrawalAnalysisCnd
	 * @return MessageBox
	 */
	@RequestMapping("/count")
	@ResponseBody
	public MessageBox getExportCount(@ModelAttribute WithdrawalAnalysisCnd withdrawalAnalysisCnd) {
		List<WithdrawalAnalysisVo> list = null;
		try {
			list = withdrawalAnalysisService.exportToExcel(withdrawalAnalysisCnd);
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "网络异常，刷新后重试！");
		}
		if (null == list || list.size() == 0) {
			return MessageBox.build("1", "没有数据");
		}
		if (list.size() > 50000) {
			return MessageBox.build("1", "本次导出的数据量大于50000条，无法导出，如须导出请与技术人员联系！");
		}
		return new MessageBox("0", String.valueOf(list.size()));
	}

	/***
	 * 
	 * <p>
	 * Description:根据条件导出提现记录<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月18日
	 * @param withdrawalAnalysisCnd
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 *             void
	 */
	@RequestMapping(value = "export")
	public void downloadJournalExcel(@ModelAttribute WithdrawalAnalysisCnd withdrawalAnalysisCnd,
			HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		List<WithdrawalAnalysisVo> list = withdrawalAnalysisService.exportToExcel(withdrawalAnalysisCnd);
		Map<String, Object> map = new HashMap<String, Object>();
		// 生成Excel时，office会提示格式错误，动态设置模板中ss:ExpandedRowCount属性，即可解决
		// http://blog.csdn.net/zhanwentao2/article/details/7298341
		map.put("rowSize", list == null ? 2 : list.size() + 2);
		map.put("table", list);
		// 提示：在调用工具类生成Word文档之前应当检查所有字段是否完整
		// 否则Freemarker的模板引擎在处理时可能会因为找不到值而报错 这里暂时忽略这个步骤了
		File file = null;
		InputStream fin = null;
		ServletOutputStream out = null;
		try {
			String fileName = "提现数据分析" + DateUtils.formatDateYmd(new Date()) + ".xls";
			fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
			// 调用工具类WordGenerator的createDoc方法生成Word文档
			file = WordAndExcelGenerator.createDocOrExcel("提现数据分析", map, "withdrawalAnalysis");
			fin = new FileInputStream(file);

			resp.reset();// 清空输出流
			resp.setCharacterEncoding("utf-8");
			resp.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setDateHeader("Expires", 0);
			resp.setContentType("application/octet-stream; charset=utf-8");

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
}
