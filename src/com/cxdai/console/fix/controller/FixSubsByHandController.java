package com.cxdai.console.fix.controller;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.service.FixBorrowService;
import com.cxdai.console.fix.service.FixBorrowTransferService;
import com.cxdai.console.fix.vo.FixBorrowCnd;
import com.cxdai.console.fix.vo.FixBorrowTransferCnd;
import com.cxdai.console.fix.vo.FixBorrowTransferVo;
import com.cxdai.console.util.HttpTookit;
/**
 * 
 * <p>
 * Description:定期宝手动认购业务<br />
 * </p>
 * @title FixBorrowController.java
 * @package com.cxdai.console.fix.controller 
 * @author 于斌
 * @version 0.1 2015年7月14日
 */
@Controller
@RequestMapping(value = "/fix/fixSubsByHand")
public class FixSubsByHandController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(FixSubsByHandController.class);
    
	@Autowired
	private  FixBorrowService  fixBorrowService;
	@Autowired
	private FixBorrowTransferService fixBorrowTransferService;
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
		return new ModelAndView("/fix/fixSubsByHand/main");
	}
	/**
	 * 
	 * <p>
	 * Description:定期宝业务查询 <br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
    @RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageFixBorrowList(@ModelAttribute FixBorrowTransferCnd fixBorrowTransferCnd,
			@ModelAttribute FixBorrowCnd fixBorrowCnd, @PathVariable Integer pageNo) {
	Page page = null;
	BigDecimal sumUseMoney=null;
	BigDecimal sumUseMoneyYes=null;
	try {
	    
		// 可认购列表
		page = fixBorrowTransferService.getFixBorrowBySubsConn(fixBorrowTransferCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		// 定期宝可用金额
		sumUseMoney = fixBorrowService.getFixBorrowSumUseMoney(fixBorrowCnd);
		// 定期宝可投金额
		sumUseMoneyYes = fixBorrowService.getFixBorrowSumUseMoneyYes(fixBorrowCnd);
	} catch (Exception e) {
		logger.error("定期宝认购出错", e);
	}
	  return new ModelAndView("/fix/fixSubsByHand/list").addObject("page", page)
			                                            .addObject("sumUseMoney", sumUseMoney)
			                                            .addObject("sumUseMoneyYes", sumUseMoneyYes);
   }
    /**
     * 
     * <p>
     * Description:认购<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年8月13日
     * @param Id
     * @return
     * MessageBox
     */
    @RequestMapping("/toSubsByHand")
	@ResponseBody
	public MessageBox toSubsByHand(@RequestParam(value = "id", required = false) Integer id ) {
		String msg="success";
		try {
			// 定期宝可投金额
			BigDecimal sumUseMoneyYes = fixBorrowService.getFixBorrowSumUseMoneyYes(new FixBorrowCnd());
			if(sumUseMoneyYes.compareTo(BigDecimal.ZERO)<=0){
				return MessageBox.build("0", "可投金额不足，无法认购！"); 
			}
			if (null != id) {
				FixBorrowTransferVo fixBorrowTransferVo = fixBorrowTransferService.queryTransferingFixBorrowList(id);
				if (fixBorrowTransferVo != null) {
					// 定期宝优先认购
					msg = fixBorrowTransferService.saveManualSubscribe(fixBorrowTransferVo, "0", HttpTookit.getRealIpAddr(currentRequest()));
					// 自动复审
					fixBorrowTransferService.saveApproveTransferRecheck(fixBorrowTransferVo.getId(), HttpTookit.getRealIpAddr(currentRequest()));
				}
				// 定期宝非优先认购
				// 查询转让中定期宝信息
				FixBorrowTransferVo fixBorrowTransferVo1 = fixBorrowTransferService.queryTransferingFixBorrowList(id);
				if (fixBorrowTransferVo1 != null) {
					// 定期宝非优先认购
					fixBorrowTransferService.saveManualSubscribe(fixBorrowTransferVo1, "1", HttpTookit.getRealIpAddr(currentRequest()));
					// 自动复审
					fixBorrowTransferService.saveApproveTransferRecheck(fixBorrowTransferVo1.getId(), HttpTookit.getRealIpAddr(currentRequest()));
				}
			}
			if(msg.equals("success")){
		      return MessageBox.build("1", "认购成功");
			}
			return MessageBox.build("0", msg); 
		} catch (Exception e) {
			logger.error("手动认购出错", e);
			return MessageBox.build("0", "系统异常，请稍候重试或联系管理员"); 
		}
	} 
    @RequestMapping("/toTransferCancel")
	@ResponseBody
	public MessageBox toTransferCancel(@RequestParam(value = "id", required = false) Integer id ) {
		String msg="success";
		try {
			if (null != id) {
				msg = fixBorrowTransferService.saveTransferCancel(id, HttpTookit.getRealIpAddr(currentRequest()));
			}
			if(msg.equals("success")){
			      return MessageBox.build("1", "取消成功");
			}
		    return MessageBox.build("0", msg);
		} catch (Exception e) {
			logger.error("手动认购出错", e);
			return MessageBox.build("0", "系统异常，请稍候重试或联系管理员"); 
		}
	} 
}
