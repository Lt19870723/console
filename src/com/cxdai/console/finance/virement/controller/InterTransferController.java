package com.cxdai.console.finance.virement.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.entity.AttachMent;
import com.cxdai.console.finance.virement.entity.AuditCnd;
import com.cxdai.console.finance.virement.entity.BankAccountInfo;
import com.cxdai.console.finance.virement.entity.MoneyOperation;
import com.cxdai.console.finance.virement.service.AttachmentService;
import com.cxdai.console.finance.virement.service.BankAccountService;
import com.cxdai.console.finance.virement.service.InterTransferService;
import com.cxdai.console.finance.virement.util.FileLoad;
import com.cxdai.console.finance.virement.vo.InterTransferCnd;
import com.cxdai.console.finance.virement.vo.MoneyOperationVo;
import com.cxdai.console.maintain.cms.constant.CmsConstant;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.JsonUtils;
import com.cxdai.console.util.PropertiesUtil;
import com.cxdai.console.util.WordAndExcelGenerator;

/****
 * 内部转账Controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/finance/interTransfer")
public class InterTransferController extends BaseController {
	private final static Logger logger = Logger.getLogger(InterTransferController.class);

	@Autowired
	private InterTransferService interTransferService;
	@Autowired
	private AccountLogService accountLogService;
	@Autowired
	private BankAccountService bankAccountService;
	@Autowired
	private AttachmentService attachmentService;
	

	/***
	 * 内部转账主界面
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView interTransferMain() {
		return new ModelAndView("/finance/virement/main");
	}

	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageInterTransferList(@ModelAttribute InterTransferCnd interTransferCnd,
			@PathVariable Integer pageNo) {
		Page page = null;
		interTransferCnd.setAdduser(currentUser().getUserId());
		try {
			page = interTransferService.queryListForPage(interTransferCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return new ModelAndView("/finance/virement/list").addObject("page", page);
	}

	/***
	 * 内部转账查询
	 * 
	 * @return
	 */
	@RequestMapping("/searchMain")
	public ModelAndView searchMain() {
		return new ModelAndView("/finance/virement/searchmain");
	}

	@RequestMapping("/searchList/{pageNo}")
	public ModelAndView searchList(@ModelAttribute InterTransferCnd interTransferCnd, @PathVariable Integer pageNo) {
		Page page = null;

		try {
			page = interTransferService.searchListForPage(interTransferCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return new ModelAndView("/finance/virement/searchlist").addObject("page", page);
	}

	/**
	 * 
	 * <p>
	 * Description:进入申请页面<br />
	 * </p>
	 * 
	 * @author tanghaitao
	 * @version 0.1 2016年3月31日
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/toapplyFor")
	public ModelAndView toapplyFor(Integer id) {
		List<Configuration> list = null;
		List<BankAccountInfo> accountList = null;
		ModelAndView mv = new ModelAndView("finance/virement/add");
		try {
			list = accountLogService.queryConfigurationListByType(80706);
			accountList = bankAccountService.selectAll();
			if (id != null) {
				MoneyOperation moneyOperation = interTransferService.selectByPrimaryKey(id);
				mv.addObject("moneyOperation", moneyOperation);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户资金记录获取交易类型列表错误：" + e);
		}
		return mv.addObject("bankList", list).addObject("accountList", accountList);

	}

	/**
	 * 
	 * <p>
	 * Description:内部转账申请<br />
	 * </p>
	 * 
	 * @author tanghaitao
	 * @version 0.1 2016年3月31日
	 * @param moneyOperation
	 * @param type
	 * @return MessageBox
	 */
	@RequestMapping(value = "/applyFor/{type}")
	@ResponseBody
	public MessageBox applyFor(@ModelAttribute MoneyOperation moneyOperation, @PathVariable Integer type) {
		try {
			if (moneyOperation.getId() == null) {
				moneyOperation.setAddip(HttpTookit.getRealIpAddr(currentRequest()));
				moneyOperation.setAdduser(currentUser().getUserId());
				moneyOperation.setOperationType(MoneyOperation.INTER_TRANSFER_TYPE);
				interTransferService.insertMoneyOperation(moneyOperation, type);
			} else {
				moneyOperation.setUpdateip(HttpTookit.getRealIpAddr(currentRequest()));
				moneyOperation.setUpdateuser(currentUser().getUserId());
				moneyOperation.setOperationType(MoneyOperation.INTER_TRANSFER_TYPE);
				interTransferService.updateMoneyOperation(moneyOperation, type);
			}

		} catch (Exception e) {
			logger.error(e);
			return new MessageBox("0", "操作失败!");
		}
		return new MessageBox("1", "操作成功!");
	}

	/**
	 * 
	 * <p>
	 * Description:进入审核页面<br />
	 * </p>
	 * 
	 * @author tanghaitao
	 * @version 0.1 2016年3月31日
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/toAudit")
	public ModelAndView toAudit() {
		return new ModelAndView("finance/approve/main");

	}

	/**
	 * 
	 * <p>
	 * Description:查询待审核记录<br />
	 * </p>
	 * 
	 * @author tanghaitao
	 * @version 0.1 2016年3月31日
	 * @param interTransferCnd
	 * @param pageNo
	 * @return ModelAndView
	 */
	@RequestMapping("/findAuditList/{pageNo}")
	public ModelAndView findAuditList(@ModelAttribute InterTransferCnd interTransferCnd, @PathVariable Integer pageNo) {
		Page page = null;

		try {
			interTransferCnd.setStatus(2);// 待审核
			page = interTransferService.queryListForPage(interTransferCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return new ModelAndView("finance/approve/list").addObject("page", page);
	}

	/**
	 * 
	 * <p>
	 * Description:进入审核详细信息页面<br />
	 * </p>
	 * 
	 * @author tanghaitao
	 * @version 0.1 2016年3月31日
	 * @param id
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/findApproveInfo/{id}")
	public ModelAndView findApproveInfo(@PathVariable Integer id) {
		MoneyOperationVo moneyOperation = null;
		try {
			moneyOperation = interTransferService.findMoneyOperation(id);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		return new ModelAndView("finance/approve/virementapprove").addObject("moneyOperation", moneyOperation);

	}

	/**
	 * 
	 * <p>
	 * Description:审核<br />
	 * </p>
	 * 
	 * @author tanghaitao
	 * @version 0.1 2016年3月31日
	 * @param auditCnd
	 * @return MessageBox
	 */
	@RequestMapping(value = "/audit/{type}")
	@ResponseBody
	public MessageBox Audit(@ModelAttribute AuditCnd auditCnd, @PathVariable Integer type) {
		try {
			auditCnd.setIp(HttpTookit.getRealIpAddr(currentRequest()));
			auditCnd.setUserId(currentUser().getUserId());
			interTransferService.moneyOperationAudit(auditCnd, type);
		} catch (Exception e) {
			logger.error(e);
			return new MessageBox("0", "操作失败!");
		}

		return new MessageBox("1", "操作成功!");

	}

	@RequestMapping(value = "/upload", produces = { "text/plain;charset=UTF-8" })
	@ResponseBody
	public String upload(@RequestParam("files") MultipartFile[] files) throws Exception {
		MessageBox msg = null;
		String slideshowfiledic = "transfer";
		//String cmsUploadPath = currentRequest().getSession().getServletContext().getRealPath(PropertiesUtil.getValue("ss_upload_path"));
		String cmsUploadPath = PropertiesUtil.getValue("cms_upload_path");
			try {
				msg = FileLoad.upload(files[0], cmsUploadPath, slideshowfiledic, CmsConstant.fileSizeLimit);
				String fileName= files[0].getOriginalFilename();
				msg.setMessage(msg.getMessage()+","+fileName);
			} catch (Exception e) {
				logger.error(e);
				msg = MessageBox.build("1", "图片上传错误!!");
			}

		return JsonUtils.bean2Json(msg);
	}

	/****
	 * 删除转账信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteByPrimaryKey/{id}")
	@ResponseBody
	public MessageBox deleteByPrimaryKey(@PathVariable Integer id) {
		try {

			interTransferService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			logger.error(e);
			return new MessageBox("0", "删除失败!");
		}
		return new MessageBox("1", "删除成功!");
	}

	/**
	 * 
	 * <p>
	 * Description:详情<br />
	 * </p>
	 * 
	 * @author tanghaitao
	 * @version 0.1 2016年3月31日
	 * @param id
	 * @return ModelAndView
	 */

	@RequestMapping(value = "/detail/{id}")
	public ModelAndView detail(@PathVariable Integer id) {
		MoneyOperation moneyOperation = null;
		List<BankAccountInfo> accountList = null;
		List<AttachMent> fileList = null;
		try {
			accountList = bankAccountService.selectAll();
			moneyOperation = interTransferService.findMoneyOperationDetail(id);
			AttachMent attachMent = new AttachMent();
			attachMent.setBusinessId(id);
			attachMent.setBusinessType(1);
			attachMent.setType(1);
			fileList = attachmentService.selectFielByTagleId(attachMent);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

		return new ModelAndView("finance/virement/detail").addObject("moneyOperation", moneyOperation)
				.addObject("bankList", accountList).addObject("fileList", fileList);

	}

	/**
	 * 根据转出人获取转出人信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * ContractLendVo
	 */
	@RequestMapping(value = "/getInfoById/{id}")
	@ResponseBody
	public BankAccountInfo getPayee(@PathVariable("id") Integer id) {
		BankAccountInfo backinfo = bankAccountService.selectById(id);
		return backinfo;
	}

	@RequestMapping(value = "{id}/downloadInterTransferExcel")
	public void beforeLoanApprove(@PathVariable("id") Integer id, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		Map<String, Object> map = interTransferService.findMoneyOperationMap(id);

		// 提示：在调用工具类生成Word文档之前应当检查所有字段是否完整
		// 否则Freemarker的模板引擎在处理时可能会因为找不到值而报错 这里暂时忽略这个步骤了
		File file = null;
		InputStream fin = null;
		ServletOutputStream out = null;
		try {
			// 调用工具类WordGenerator的createDoc方法生成Word文档
			file = WordAndExcelGenerator.createDocOrExcel("内部转账导出表单", map, "finance");
			fin = new FileInputStream(file);

			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/octet-stream");
			// 设置浏览器以下载的方式处理该文件默认名为resume.doc
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

			resp.addHeader("Content-Disposition", "attachment;filename=" + format.format(new Date()) + ".xls");
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
