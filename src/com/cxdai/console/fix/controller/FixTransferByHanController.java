package com.cxdai.console.fix.controller;

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
import com.cxdai.console.fix.vo.FixBorrowCnd;
import com.cxdai.console.util.HttpTookit;
/**
 * 
 * <p>
 * Description:定期宝手动转让<br />
 * </p>
 * @title FixTransferByHanController.java
 * @package com.cxdai.console.fix.controller 
 * @author yubin
 * @version 0.1 2015年8月13日
 */
@Controller
@RequestMapping(value = "/fix/fixTransferByHand")
public class FixTransferByHanController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(FixTransferByHanController.class);
    
	@Autowired
	private  FixBorrowService  fixBorrowService;
	/**
	 * 
	 * <p>
	 * Description:进入定期宝手动转让业务<br />
	 * </p>
	 * @author 于斌
	 * @version 0.1 2015年7月14日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		return new ModelAndView("/fix/fixTransferByHand/main");
	}
	/**
	 * 
	 * <p>
	 * Description:定期宝手动转让查询 <br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
    @RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageFixBorrowList(@ModelAttribute FixBorrowCnd fixBorrowCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			
			page = fixBorrowService.getFixBorrowByTransferConn(fixBorrowCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {
			logger.error("定期宝手动转让查询出错", e);
		}
		  return new ModelAndView("/fix/fixTransferByHand/list").addObject("page", page);
	}
    /**
     * 
     * <p>
     * Description:手动转让<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年8月13日
     * @param id
     * @return
     * MessageBox
     */
    @RequestMapping("/toTransferCancel")
	@ResponseBody
	public MessageBox toTransferCancel(@RequestParam(value = "id", required = false) Integer id ) {
		String msg="success";
		try {
			if (null != id) {
				msg = fixBorrowService.saveTransfer(id, HttpTookit.getRealIpAddr(currentRequest()));
			}
			if(msg.equals("success")){
				return MessageBox.build("1", "手动转让成功");
			}
		    return MessageBox.build("0", msg);
		} catch (Exception e) {
			logger.error("手动转让出错", e);
			return MessageBox.build("0", "系统异常，请稍候重试或联系管理员"); 
		}
	}
}
