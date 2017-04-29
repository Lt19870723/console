package com.cxdai.console.maintain.xw.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.cxdai.console.account.recharge.vo.RechargeRecordCnd;
import com.cxdai.console.account.recharge.vo.RechargeRecordVo;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.WxContants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.customer.bankcard.service.UserBindBankService;
import com.cxdai.console.customer.bankcard.vo.BankCardLockCnd;
import com.cxdai.console.customer.bankcard.vo.BankCardLockVo;
import com.cxdai.console.util.DateUtils;

@Controller
@RequestMapping(value = "/contentSystem/wx")
public class WxBindController extends BaseController {
	
	private final static Logger logger = Logger.getLogger(WxBindController.class);

	@Autowired
	private UserBindBankService userBindBankService;
	
	/**
	 * <p>
	 * Description:跳转<br />
	 * </p>
	 * 
	 * @author 张鹏
	 * @version 0.1 2015年6月24日
	 */
	@RequestMapping(value="/toWxBindList")
	public ModelAndView toWxBindList() {
		return forword("/maintain/xw/main");
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询微信绑定信息列表<br />
	 * </p>
	 * 
	 * @author 张鹏
	 * @version 0.1 2015年6月24日
	 */

	@RequestMapping(value="/wxList/{pageNo}")
	@ResponseBody
	public ModelAndView searchWxBindList(@ModelAttribute BankCardLockCnd bankCardLockCnd, @PathVariable("pageNo") Integer pageNo) {
		
		try {
			Page page = userBindBankService.queryWxBindListByCnd(bankCardLockCnd, pageNo, WxContants.WX_PAGE_SIZE);
			return forword("/maintain/xw/wxBindList").addObject("page", page);
		} catch (Exception e) {
			logger.error("获取微信用户列表失败",e);
			return forword("/common/500");
		}
	}

	/**
	 * 
	 * <p>
	 * Description:查询已绑定微信的用户数<br />
	 * </p>
	 * 
	 * @author 张鹏
	 * @version 0.1 2015年6月24日
	 */
	@RequestMapping(value="/getWxBindNum")
	@ResponseBody
	public int getWxBindNum(){
		return userBindBankService.queryWxBindAcount();
	}
	/**
	 * 
	 * <p>
	 * Description:查询指定时间已绑定微信的用户数<br />
	 * </p>
	 * 
	 * @author 张鹏
	 * @version 0.1 2015年6月24日
	 */
	@RequestMapping(value="/getWxBindNumByTime")
	@ResponseBody
	public int getWxBindNumByTime(@ModelAttribute BankCardLockCnd bankCardLockCnd){
		return userBindBankService.queryWxBindAcountByTime(bankCardLockCnd);
	}
	
	
	
	/**
	 * <p>
	 * Description:统计导出数据的数量<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月17日
	 * @param rechargeRecordCnd
	 * @return MessageBox
	 */
	@RequestMapping("/count")
	@ResponseBody
	public MessageBox count(@ModelAttribute BankCardLockCnd bankCardLockCnd,HttpServletRequest request, HttpServletResponse response) {
		List<BankCardLockVo> list =null;
		try {
			list = userBindBankService.wxExportToExcel(bankCardLockCnd, request, response);
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
	
	
	/**
	 * <p>
	 * Description:导出excel<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年3月17日
	 * @param rechargeRecordCnd
	 * @param request
	 * @param response void
	 */
	@RequestMapping("/export")
	public void exportToExcel(@ModelAttribute BankCardLockCnd bankCardLockCnd, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<BankCardLockVo> list = userBindBankService.wxExportToExcel(bankCardLockCnd, request, response);
			if (null == list || list.size() == 0) {
				return;
			}
			if (list.size() > 50000) {
				return;
			}
			Map<Object, Object> dto = new HashMap<Object, Object>();
			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/fWxBindReportExcel.jasper");

			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response, "微信绑定信息报表" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
