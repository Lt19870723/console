
package com.cxdai.console.borrow.check.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.check.entity.BorrowerDealError;
import com.cxdai.console.borrow.check.service.BorrowRecheckService;
import com.cxdai.console.borrow.check.service.CGBorrowService;
import com.cxdai.console.borrow.check.vo.BorrowErrorCnd;
import com.cxdai.console.borrow.manage.service.BorrowManageService;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.HttpTookit;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BorrowerErrorController.java
 * @package com.cxdai.console.borrow.check.controller 
 * @author tanghaitao
 * @version 0.1 2016年6月3日
 */

@Controller
@RequestMapping(value="/borrowError")
public class BorrowerErrorController extends BaseController{
	
	 private final static Logger logger=Logger.getLogger(BorrowerErrorController.class);
	
	@Autowired
	private CGBorrowService cGBorrowService;
	@Autowired
	private BorrowRecheckService borrowRecheckService;
	@Autowired
	private BorrowManageService borrowManageService;
	
	
	@RequestMapping(value="/toMain")
	public ModelAndView toMain(){
		return new ModelAndView("borrow/errorBorrow/main");
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:存管标异常列表<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年6月4日
	 * @param borrowErrorCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value="/toBorrowErrorList/{pageNo}")
	public ModelAndView toBorrowErrorList(@ModelAttribute BorrowErrorCnd borrowErrorCnd,@PathVariable Integer pageNo){
		Page page = null;
		try {
			page=cGBorrowService.findBorrowerError(new Page(pageNo, 10), borrowErrorCnd);
			
			
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
	   		
		}
		return new ModelAndView("borrow/errorBorrow/list").addObject("page", page);
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:进入审核页面<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年6月4日
	 * @param id
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value="/toReCheckpage")
	public ModelAndView toReCheckpage(@RequestParam(value = "id", required = false) Integer id){
		return new ModelAndView("borrow/errorBorrow/layer").addObject("id", id);
		
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:存管标手动复审<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年6月4日
	 * @param remark
	 * @return
	 * MessageBox
	 */
	@RequestMapping(value="/manualRecheckBorrow")
	@ResponseBody
	public MessageBox manualRecheckBorrow(String remark,Integer borrowErrorId,HttpServletRequest request){
		String msg = null;
		try {
			ShiroUser shiroUser=currentUser();
			BorrowerDealError borrowerDealError=cGBorrowService.selectByPrimaryKey(borrowErrorId);
			BorrowVo borrowVo =borrowManageService.findBorrow(borrowerDealError.getBorrowId());
			//满标复审
			if(borrowerDealError.getStatus()==1){
				String approvedRemark="后台手动复审";
				msg= borrowRecheckService.recheckBorrow(borrowVo.geteProjectId(), shiroUser.getUserId(), approvedRemark,HttpTookit.getRealIpAddr(request));
				if(msg.equals(BusinessConstants.SUCCESS)){
					cGBorrowService.updateBorrowError(borrowErrorId, remark);
					return new MessageBox("0", "复审成功");
				}
				
			//流标复审
			}else if(borrowerDealError.getStatus()==0){
				msg= borrowRecheckService.flowBorrow(borrowVo.geteProjectId(), HttpTookit.getRealIpAddr(request), shiroUser.getUserId());
				if(msg.equals(BusinessConstants.SUCCESS)){
					cGBorrowService.updateBorrowError(borrowErrorId, remark);
					return new MessageBox("0", "复审成功");
				}
			}
			
			
		} catch (Exception e) {
			logger.error(e);
			return new MessageBox("1", "网络异常，请稍后再试!");
		}
		
		return new MessageBox("1", msg);
		
	}
	 
}
