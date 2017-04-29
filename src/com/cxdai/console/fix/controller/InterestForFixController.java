package com.cxdai.console.fix.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxdai.console.account.cash.vo.CashRecordCnd;
import com.cxdai.console.account.cash.vo.CashRecordVo;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.fix.vo.FixBorrowVo;
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
import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.service.FixAccountService;
import com.cxdai.console.fix.service.FixBorrowService;
import com.cxdai.console.fix.vo.FixAccountVo;
import com.cxdai.console.fix.vo.FixBorrowCnd;
import com.cxdai.console.util.DateUtils;
import com.mysql.jdbc.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * <p>
 * Description:定期宝收益查询业务<br />
 * </p>
 * @title FixBorrowController.java
 * @package com.cxdai.console.fix.controller 
 * @author 于斌
 * @version 0.1 2015年7月14日
 */
@Controller
@RequestMapping(value = "/fix/interestForFix")
public class InterestForFixController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(InterestForFixController.class);
    @Autowired
    private FixBorrowService fixBorrowService;
    @Autowired
    private FixAccountService fixAccountService;
	/**
	 * 
	 * <p>
	 * Description:进入定期宝业务<br />
	 * </p>
	 * @author 于斌
	 * @version 0.1 2015年7月14日
	 * @return
	 * ModelAndView
	 */
    @RequestMapping("/main")
	public ModelAndView main(){
		return new ModelAndView("/fix/interestForFix/main");
	}
	/**
	 * 
	 * <p>
	 * Description:条件查询 定期宝收益业务查询 <br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
    @RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageInterestForFixList(@ModelAttribute FixBorrowCnd fixBorrowCnd, @PathVariable Integer pageNo) {
	Page page = null;
	FixAccountVo fixAccountVo=null;
	String operation="0";//操作类型
	/**利息金額 */
	BigDecimal profitMoney=null;
	/**支出金額 */
    BigDecimal payMentMoney=null;
	try {
	 
		fixBorrowCnd.setOrderSql(" ");
		page = fixBorrowService.queryPageInterestFixByCnd(fixBorrowCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		 
		//判断如果是有定期宝编号，没有日期，则统计出来定期宝的相关信息，反之则统计日期期间的统计信息
		if (!"".equals(fixBorrowCnd.getContractNo())) {
			fixAccountVo = fixAccountService.queryProfitBycontractNo(fixBorrowCnd);
			if(fixAccountVo==null){
				fixAccountVo = new FixAccountVo();
				fixAccountVo.setProfit(new BigDecimal(0));
				fixAccountVo.setRepaymentYesAccount(new BigDecimal(0));
				fixAccountVo.setUseMoney(new BigDecimal(0));
			}
            operation = "1";
		} else if ("".equals(fixBorrowCnd.getContractNo()) && (fixBorrowCnd.getBeginDate() != null || fixBorrowCnd.getEndDate() != null)) {
			profitMoney = fixAccountService.queryProfitByBetweenDate(fixBorrowCnd);
			payMentMoney = fixAccountService.queryPayMentByBetweenDate(fixBorrowCnd);
			operation = "2";
		}
	} catch (Exception e) {
		e.printStackTrace();
		logger.error("用户页面查询出错", e);
	}
	  return new ModelAndView("/fix/interestForFix/list").addObject("page", page).addObject("operation", operation)
			                                             .addObject("fixAccountVo",fixAccountVo)
			                                             .addObject("profitMoney", profitMoney)
			                                             .addObject("payMentMoney", payMentMoney);
   }

	/**
	 *
	 * <p>
	 * Description:获取导出的数量<br />
	 * </p>
	 *
	 * @author wangwanbin
	 * @version 0.1 2015年3月18日 void
	 */
	@RequestMapping("/count")
	@ResponseBody
	public MessageBox getExportCount(@ModelAttribute FixBorrowCnd fixBorrowCnd) {
		int count;
		try {
			fixBorrowCnd.setOrderSql(" ");
			count = fixBorrowService.countExport(fixBorrowCnd);
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "网络异常，刷新后重试！");
		}
		if (count == 0) {
			return MessageBox.build("1", "没有数据");
		}
		if (count > 50000) {
			return MessageBox.build("1", "本次导出的数据量大于50000条，无法导出，如须导出请与技术人员联系！");
		}
		return new MessageBox("0", String.valueOf(count));
	}

	/**
	 *
	 * <p>
	 * Description:导出Excel<br />
	 * </p>
	 *
	 * @author wangwanbin
	 * @version 0.1 2015年3月18日 void
	 */
	@RequestMapping("/export")
	public void exportToExcel(@ModelAttribute FixBorrowCnd fixBorrowCnd, HttpServletRequest request, HttpServletResponse response) {
		try {
			fixBorrowCnd.setOrderSql(" ");
			List<FixBorrowVo> list = fixBorrowService.listExport(fixBorrowCnd);
			Map<Object, Object> dto = new HashMap<Object, Object>();

			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/fixBorrowVoExcel.jasper");

			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response, "定期宝收益报表" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			// this.setMsg("fail");
			e.printStackTrace();
		}
	}
}
