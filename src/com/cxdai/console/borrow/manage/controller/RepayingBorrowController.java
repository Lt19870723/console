package com.cxdai.console.borrow.manage.controller;
import java.math.BigDecimal;

import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.borrow.manage.service.BRepaymentRecordService;
import com.cxdai.console.borrow.manage.service.BorrowManageService;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.PropertiesUtil;
/**
 * 
 * <p>
 * Description:还款中的借款标业务<br />
 * </p>
 * @title TenderBorrowController.java
 * @package com.cxdai.console.borrow.manage.controller 
 * @author yubin
 * @version 0.1 2015年6月28日
 */
@Controller
@RequestMapping(value = "/manage/forRepayingBorrow")
public class RepayingBorrowController extends BaseController {
	
	private final static Logger logger=Logger.getLogger(RepayingBorrowController.class);
	@Autowired
	private BorrowManageService borrowManageService;
	@Autowired
	private BRepaymentRecordService bRepaymentRecordService;
	private String path=PropertiesUtil.getValue("portal_path");
	/**
	 * 
	 * <p>
	 * Description:进入还款中的借款标列表<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		return  new ModelAndView("/borrow/manage/repayingBorrow/main");
	}
	/**
	 * 
	 * <p>
	 * Description:分页查询还款中的借款标集合<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
    @RequestMapping("/list/{pageNo}")
	public ModelAndView searchRepayingBorrowList(@ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
	Page page = null; 
	BigDecimal sumRepayAccount = null;
	try {
		    borrowCnd.setIsCustody("0");  //借款标管理-还款中借款标 只处理非存管标
		    borrowCnd.setStatus("0");// 还款中
		    borrowCnd.setCgWebstatus("");//不包含存管垫付
	   		page =bRepaymentRecordService.selectRepayingBorrow(borrowCnd, pageNo,  Constants.CONSOLE_PAGE_SIZE);
	   		sumRepayAccount=bRepaymentRecordService.sumWaitRepayMoney(borrowCnd);
	} catch (Exception e) {
	   		e.printStackTrace();
	   		logger.error(e);
	}
	  return new ModelAndView("/borrow/manage/repayingBorrow/list").addObject("page", page)
			  .addObject("sumRepayAccount", sumRepayAccount)
			  .addObject("portal_path", path);
   }
    /**
     * 
     * <p>
     * Description:网站垫付<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年6月29日
     * @param borrowId
     * @return
     * MessageBox
     */
    @RequestMapping("/webpay")
	@ResponseBody
	public  MessageBox webpay(@RequestParam(value = "id", required = false) Integer borrowId) {
		String msg=null;
		BRepaymentRecordVo bRepaymentRecordVo = null;
		try {
			bRepaymentRecordVo = bRepaymentRecordService.queryRepaymentByIdForUpdate(borrowId);
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("2", "系统繁忙,请稍后重试！");
		}
		if (null == bRepaymentRecordVo) {
			return MessageBox.build("0", "待还记录不存在");
		}

		//存管还款
		if(bRepaymentRecordVo.getIsCustody() != null && bRepaymentRecordVo.getIsCustody().intValue() ==1 ){
			return MessageBox.build("2", "请在《存管还款中借款标》菜单进行存管标还款处理 ");
		}else{
			try {
				msg= doSelfWebpay(borrowId);
			} catch (Exception e) {
				stackTraceError(logger, e);
				return MessageBox.build("2", "系统繁忙,请稍后重试！");
			}
		}

		if(msg.equals("success")){
			   return MessageBox.build("0", "垫付成功");
		   }
		   return MessageBox.build("1", msg);
	}


   public String doSelfWebpay(Integer repaymentId ) throws Exception{
	   String msg=null;
	   msg =borrowManageService.saveWebpayBorrow(repaymentId, HttpTookit.getRealIpAddr(currentRequest()));
	   return  msg;

//	   try {
//		   msg =borrowManageService.saveWebpayBorrow(repaymentId, HttpTookit.getRealIpAddr(currentRequest()));
//		   if(msg.equals("success")){
//			   return MessageBox.build("0", "垫付成功");
//		   }
//		   return MessageBox.build("1", msg);
//	   } catch (Exception e) {
//		   stackTraceError(logger, e);
//		   return MessageBox.build("2", "系统繁忙,请稍后重试！");
//	   }
   }

}
