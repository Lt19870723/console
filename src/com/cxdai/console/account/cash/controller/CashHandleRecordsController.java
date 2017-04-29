package com.cxdai.console.account.cash.controller;

import java.io.BufferedOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.cxdai.console.account.cash.mapper.CashRecordMapper;
import com.cxdai.console.account.cash.service.CashRecordService;
import com.cxdai.console.account.cash.vo.CashPayVo;
import com.cxdai.console.account.cash.vo.CashRecordCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.DateUtils;

/**
 * 提现处理
 * 
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/account/cashhandlerecord")
public class CashHandleRecordsController extends BaseController {

	private static final Logger logger = Logger.getLogger(CashHandleRecordsController.class);

	@Autowired
	private CashRecordService cashRecordService;
	@Autowired
	private CashRecordMapper cashRecordMapper;

	/**
	 * 进入提现处理初始页面
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView forCashHanlderMain() {
		return new ModelAndView("/account/cash/query/handle/main");
	}

	/**
	 * Description:查询提现处理待打款的记录<br />
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageCashRecordPayList(@ModelAttribute CashRecordCnd cashRecordCnd, @PathVariable Integer pageNo, HttpServletRequest request) {
		Map<String, BigDecimal> resultMap = null;
		Page page = null;
		try {
			cashRecordCnd.setCashColumn(2);
			cashRecordCnd.setStatus("1");
            cashRecordCnd.setIsCustody(0);
			cashRecordCnd.setBeginTime(request.getParameter("beginTime"));
			cashRecordCnd.setEndTime(request.getParameter("endTime"));
			page = cashRecordService.queryPageListByCnd(cashRecordCnd, pageNo, Constants.CONSOLE_PAGE_SIZE4);
			resultMap = cashRecordService.queryCashRecorData(cashRecordCnd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询提现处理待打款记录错误信息：" + e.getMessage());
		}
		return new ModelAndView("/account/cash/query/handle/list").addObject("page", page).addObject("resultMap", resultMap);
	}

	/**
	 * Description:立即打款
	 */
	@RequestMapping("/paycash/{id}")
	@ResponseBody
	public MessageBox payCash(@PathVariable Integer id, HttpServletRequest request) {
		UserVo userVo = getCurrentUserVo();
		String result = null;
		try {
			result = cashRecordService.savePayCash(id, userVo, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MessageBox.build("1", "系统错误，请稍后再试!!");
		}
		return new MessageBox("0", result);
	}

	/**
	 * 取消提现
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/cancelcash/{id}")
	@ResponseBody
	public MessageBox cancelCash(@PathVariable Integer id, HttpServletRequest request) {
		UserVo userVo = getCurrentUserVo();
		String result = "";
		try {
			result = cashRecordService.cancelCash(id, userVo, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MessageBox.build("1", "系统错误，取消提现失败，请稍后再试!!");
		}
		return new MessageBox("0", result);
	}

	/**
	 * 进入打款失败页面
	 */
	@RequestMapping("/detailfailcash/{id}")
	public ModelAndView detailFailCash(@PathVariable Integer id) {
		return new ModelAndView("/account/cash/query/handle/layer").addObject("id", id);
	}

	/**
	 * 打款失败
	 */
	@RequestMapping("/failcash/{id}")
	@ResponseBody
	public MessageBox failCash(@PathVariable Integer id, HttpServletRequest request) {
		UserVo userVo = getCurrentUserVo();
		String result = null;
		String remark = request.getParameter("remark");
		try {
			result = cashRecordService.saveFailCash(id, userVo, remark, request);
		} catch (Exception e) {
			e.printStackTrace();
			return MessageBox.build("1", "系统错误，打款失败，请稍后再试!!");
		}
		return new MessageBox("0", result);
	}

	/**
	 * 批量导出待打款记录的EXCEL(用于民生银行打款)记录数
	 * 
	 * @author liutao
	 * @Date 2016-03-16
	 */
	@RequestMapping("/count/{ids}")
	@ResponseBody
	public MessageBox count(@PathVariable String ids) {
		int count = 0;
		try {
			List<CashPayVo> list = new ArrayList<CashPayVo>();
			String[] idss = ids.split(",");
			if (idss.length > 0) {
				list = cashRecordMapper.exportForPayToExcelToMS(idss, "0");
			}
			if (null == list || list.size() == 0) {
				return MessageBox.build("1", "没有未导出数据");
			}
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "网络异常，刷新后重试！");
		}
		return new MessageBox("0", String.valueOf(count));
	}

	/**
	 * <p>
	 * Description:批量导出待打款记录的EXCEL(用于民生银行打款)<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月22日 void
	 */
	@RequestMapping("/MSExportExcel/{ids}")
	public void exprtCashPayExcelToMS(@PathVariable String ids, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获取待打款的记录
			UserVo userVo = getCurrentUserVo();
			List<CashPayVo> list = cashRecordService.exportForPayToExcelToMS(ids, userVo.getId());
			Map dto = new HashMap();
			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/fcashPayReportExcelToMS.jasper");

			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response,
					"批量打款" + DateUtils.format(new Date(), DateUtils.YMD) + "（用于民生银行打款）");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * Description:批量导出待打款记录的TXT(用于民生银行打款)<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月22日 void
	 */
	@RequestMapping("/MSExportTxt/{ids}")
	public void exportCashPayTxtToMS(@PathVariable String ids, HttpServletResponse response) {
		String fileName = "批量打款" + DateUtils.format(new Date(), DateUtils.YMD) + "（用于民生银行打款）.txt";
		try {
			fileName = new String(fileName.getBytes("GBK"), "ISO8859_1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setContentType("text/plain");// 一下两行关键的设置
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);// filename指定默认的名字
		BufferedOutputStream buff = null;
		StringBuffer write = new StringBuffer();
		String tab = ",";
		String enter = "\r\n";
		ServletOutputStream outSTr = null;
		try {
			// 获取待打款的记录
			UserVo userVo = getCurrentUserVo();
			List<CashPayVo> list = cashRecordService.exportForPayToTxtToMs(ids, userVo.getId());
			outSTr = response.getOutputStream();// 建立
			buff = new BufferedOutputStream(outSTr);
			if (list.size() > 0) {
				for (CashPayVo cashPayVo : list) {
					if (cashPayVo.getAccount() != null && !cashPayVo.getAccount().equals("")) {
						write.append(cashPayVo.getAccount() + tab);
					} else {
						write.append(tab);
					}
					if (cashPayVo.getBank() != null && !cashPayVo.getBank().equals("")) {
						write.append(cashPayVo.getBank() + tab);
					} else {
						write.append(tab);
					}
					if (cashPayVo.getRealName() != null && !cashPayVo.getRealName().equals("")) {
						write.append(cashPayVo.getRealName() + tab);
					} else {
						write.append(tab);
					}
					if (cashPayVo.getCredited() != null && !cashPayVo.getCredited().equals("")) {
						write.append(cashPayVo.getCredited() + tab);
					} else {
						write.append(tab);
					}
					if (cashPayVo.getCashUse() != null && !cashPayVo.getCashUse().equals("")) {
						write.append(cashPayVo.getCashUse() + tab);
					} else {
						write.append(tab);
					}
					if (cashPayVo.getHuilu() != null && !cashPayVo.getHuilu().equals("")) {
						write.append(cashPayVo.getHuilu() + tab);
					} else {
						write.append(tab);
					}
					if (cashPayVo.getCnapscode() != null && !cashPayVo.getCnapscode().equals("")) {
						write.append(cashPayVo.getCnapscode() + tab);
					} else {
						write.append(tab);
					}
					if (cashPayVo.getMobile() != null && !cashPayVo.getMobile().equals("")) {
						write.append(cashPayVo.getMobile() + tab);
					} else {
						write.append(tab);
					}
					if (cashPayVo.getStstem() != null && !cashPayVo.getStstem().equals("")) {
						write.append(cashPayVo.getStstem() + enter);
					} else {
						write.append(enter);
					}
				}
			}
			buff.write(write.toString().getBytes("GBK"));
			buff.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 批量导出待打款记录的EXCEL(用户网银在线打款)记录数
	 * 
	 * @author liutao
	 * @Date 2016-03-16
	 */
	@RequestMapping("/count1/{ids}")
	@ResponseBody
	public MessageBox count1(@PathVariable String ids) {
		int count = 0;
		try {
			List<CashPayVo> list = new ArrayList<CashPayVo>();
			String[] idss = ids.split(",");
			if (idss.length > 0) {
				list = cashRecordMapper.exportForPayToExcelToWYZX(idss, "0");
			}
			if (null == list || list.size() == 0) {
				return MessageBox.build("1", "没有未导出数据");
			}
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "网络异常，刷新后重试！");
		}
		return new MessageBox("0", String.valueOf(count));
	}

	/**
	 * <p>
	 * Description:批量导出待打款记录的EXCEL(用户网银在线打款)<br />
	 * </p>
	 */
	@RequestMapping("/WYExportExcel/{ids}")
	public void exprtCashPayExcelToWYZX(@PathVariable String ids, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获取待打款的记录
			UserVo userVo = getCurrentUserVo();
			List<CashPayVo> list = cashRecordService.exportForPayToExcelToWYZX(ids, userVo.getId());
			Map dto = new HashMap();
			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/fcashPayReportExcelToWYZX.jasper");

			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response,
					"批量打款" + DateUtils.format(new Date(), DateUtils.YMD) + "（用于网银在线打款）");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * Description:批量导出待打款记录的To(用户网银在线打款)<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月22日 void
	 */
	@RequestMapping("/WYExportTxt/{ids}")
	public void exportCashPayTxtForWYZX(@PathVariable String ids, HttpServletRequest request, HttpServletResponse response) {
		String fileName = "批量打款" + DateUtils.format(new Date(), DateUtils.YMD) + "（用于网银在线打款）.txt";
		try {
			fileName = new String(fileName.getBytes("GBK"), "ISO8859_1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setContentType("text/plain");// 一下两行关键的设置
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);// filename指定默认的名字
		BufferedOutputStream buff = null;
		StringBuffer write = new StringBuffer();
		String tab = ",";
		String enter = "\r\n";
		write.append("单笔序号,收款方银行账号,银行类型,真实姓名,付款金额(元),账户属性,账户类型,开户地区,开户城市,支行名称,联行号,付款说明,收款人手机号,所属机构\r\n");
		ServletOutputStream outSTr = null;
		try {
			// 获取待打款的记录
			UserVo userVo = getCurrentUserVo();
			List<CashPayVo> list = cashRecordService.exportForPayToTxtToWXZX(ids, userVo.getId());
			outSTr = response.getOutputStream();// 建立
			buff = new BufferedOutputStream(outSTr);
			if (list.size() > 0) {
				int i = 1;
				for (CashPayVo cashPayVo : list) {
					write.append(i + tab);
					if (cashPayVo.getAccount() != null && !cashPayVo.getAccount().equals("")) {
						write.append(cashPayVo.getAccount() + tab);
					} else {
						write.append(tab);
					}
					if (cashPayVo.getBank() != null && !cashPayVo.getBank().equals("")) {
						write.append(cashPayVo.getBank() + tab);
					} else {
						write.append(tab);
					}
					if (cashPayVo.getRealName() != null && !cashPayVo.getRealName().equals("")) {
						write.append(cashPayVo.getRealName() + tab);
					} else {
						write.append(tab);
					}
					if (cashPayVo.getCredited() != null && !cashPayVo.getCredited().equals("")) {
						write.append(cashPayVo.getCredited() + tab);
					} else {
						write.append(tab);
					}
					if (cashPayVo.getAccountAttr() != null && !cashPayVo.getAccountAttr().equals("")) {
						write.append(cashPayVo.getAccountAttr() + tab);
					} else {
						write.append("对私" + tab);
					}
					if (cashPayVo.getAccountType() != null && !cashPayVo.getAccountType().equals("")) {
						write.append(cashPayVo.getAccountType() + tab);
					} else {
						write.append("借记卡" + tab);
					}
					write.append(",,,,国诚,,");
					write.append(enter);
					i++;
				}
			}
			buff.write(write.toString().getBytes("GBK"));
			buff.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * <p>
	 * Description:批量已导出(设置已导出记录)<br />
	 * </p>
	 */
	public void batchExported(@PathVariable String ids) {
		try {
			UserVo userVo = getCurrentUserVo();
			String result = cashRecordService.batchExported(ids, userVo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 批量打款成功
	 * 
	 * @param ids
	 */
	@RequestMapping("/batchpaycash/{ids}")
	@ResponseBody
	public MessageBox batchPayCash(@PathVariable String ids) {
		String result = null;
		try {
			UserVo userVo = getCurrentUserVo();
			result = cashRecordService.batchPayCash(ids, userVo.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return MessageBox.build("1", "系统错误，批量打款失败，请稍后再试!!");
		}
		return new MessageBox("0", result);
	}

}
