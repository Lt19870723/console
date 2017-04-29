package com.cxdai.console.borrow.manage.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.borrow.approve.service.WsBorrowService;
import com.cxdai.console.borrow.manage.service.BRepaymentRecordService;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.PropertiesUtil;
import com.cxdai.console.util.WebServiceMD5;
/**
 * 
 * <p>
 * Description:借款标罚息业务<br />
 * </p>
 * @title InterestPenaltyController.java
 * @package com.cxdai.console.borrow.manage.controller 
 * @author yubin
 * @version 0.1 2015年6月28日
 */
@Controller
@RequestMapping(value = "/manage/forInterestPenalty")
public class InterestPenaltyController extends BaseController {
	
	private final static Logger logger=Logger.getLogger(InterestPenaltyController.class);
	@Autowired
	private BRepaymentRecordService bRepaymentRecordService;
	@Autowired
	private WsBorrowService wsBorrowService;
	private String path=PropertiesUtil.getValue("portal_path");
	/**
	 * 
	 * <p>
	 * Description:进入借款标罚息列表<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		
		return  new ModelAndView("/borrow/manage/interestPenalty/main");
	}
	/**
	 * 
	 * <p>
	 * Description:分页查询需要补罚息的集合<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
    @RequestMapping("/list/{pageNo}")
	public ModelAndView searchInterestPenaltyList(@ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
	  Page page = null;   
	try {
	   		page =bRepaymentRecordService.selectRepairInterestPage(borrowCnd, pageNo,  Constants.CONSOLE_PAGE_SIZE);
	} catch (Exception e) {
	   		 
	   		logger.error(e);
	}
	   	return new ModelAndView("/borrow/manage/interestPenalty/list").addObject("page", page).addObject("portal_path", path);
   }
    /**
     * 
     * <p>
     * Description:补罚息<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年6月29日
     * @param borrowId
     * @return
     * MessageBox
     */
	@RequestMapping("/operatingpenalty")
	@ResponseBody
	public  MessageBox cancelBorrow(@RequestParam(value = "id", required = false) Integer borrowId) {
		String msg=null;
		try {
			// 验证数据是否正确
			Map<String, Object> validateKeyMap = new HashMap<>();
			validateKeyMap.put("repaymentid",borrowId);
			validateKeyMap.put("addip", HttpTookit.getRealIpAddr(currentRequest()));
			msg = wsBorrowService.saveOperatingPenalty(borrowId, HttpTookit.getRealIpAddr(currentRequest()), WebServiceMD5.encodeParams(validateKeyMap));
            if(msg.equals(BusinessConstants.SUCCESS)){
            	return  MessageBox.build("0", "操作成功");
            }
			return MessageBox.build("1", msg);  
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "删除定时发标失败！");  
		}
	}
}
