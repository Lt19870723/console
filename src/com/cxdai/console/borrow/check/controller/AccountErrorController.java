/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AccountErrorController.java
 * @package com.cxdai.console.borrow.check.controller 
 * @author tanghaitao
 * @version 0.1 2016年6月4日
 */
package com.cxdai.console.borrow.check.controller;

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

import com.cxdai.console.base.entity.AccountError;
import com.cxdai.console.borrow.approve.service.BorrowService;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.check.entity.BorrowerDealError;
import com.cxdai.console.borrow.check.service.CGBorrowService;
import com.cxdai.console.borrow.check.vo.AccountErrorCnd;
import com.cxdai.console.borrow.check.vo.BorrowErrorCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.common.statics.CGBusinessConstants;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.serialnumberutil.UUIDGenerator;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title AccountErrorController.java
 * @package com.cxdai.console.borrow.check.controller 
 * @author tanghaitao
 * @version 0.1 2016年6月4日
 */
@Controller
@RequestMapping(value="/accountError")
public class AccountErrorController extends BaseController{

	private final static Logger logger=Logger.getLogger(AccountErrorController.class);
	
	@Autowired
	private CGBorrowService cGBorrowService;
	@Autowired
	private BorrowService borrowService;
	
	@RequestMapping(value="/toMain")
	public ModelAndView toMain(){
		return new ModelAndView("customer/accountError/main");
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询异常账户记录<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年6月4日
	 * @param accountErrorCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value="/findAccountError/{pageNo}")
	public ModelAndView findAccountError( @ModelAttribute AccountErrorCnd accountErrorCnd,@PathVariable Integer pageNo){
		Page page=null;
		try {
			page=cGBorrowService.findAccountError(new Page(pageNo, 10), accountErrorCnd);
		
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
	   		
		}
		return new ModelAndView("customer/accountError/list").addObject("page", page);
		
	}
	
	
	@RequestMapping(value="/toDispose")
	public ModelAndView toDispose(@RequestParam(value = "id", required = false) Integer id){
		return new ModelAndView("customer/accountError/layer").addObject("accountErrorid", id);
	}
	
	
	@RequestMapping(value="/accountErrorDispose")
	@ResponseBody
	public MessageBox accountErrorDispose(String disposeRemark,Integer accountErrorid){
		try {
			ShiroUser shiroUser=currentUser();
			AccountError accountError=new AccountError();
			accountError.setId(accountErrorid);
			accountError.setDisposeRemark(disposeRemark);
			accountError.setDisposeUser(shiroUser.getUserId());
			accountError.setStatus(1);
			cGBorrowService.updateAccountError(accountError);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return new MessageBox("1","网络异常，请稍后再试");
		}
		
		 return new MessageBox("0","处理成功");
		
	}
	

	
	
	@RequestMapping(value="/toFBReqMain")
	public ModelAndView totoFBReqMain(){
		return new ModelAndView("customer/fBReqAccount/main");
	}
	
	@RequestMapping(value="/fBReqAccountList/{pageNo}")
	public ModelAndView fBReqAccountList(@ModelAttribute BorrowErrorCnd borrowErrorCnd,@PathVariable Integer pageNo){
		Page page = null;
		try {
			page=cGBorrowService.findFBReqAccount(new Page(pageNo, 10), borrowErrorCnd);
			
			
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
	   		
		}
		return new ModelAndView("customer/fBReqAccount/list").addObject("page", page);
	}
	
	@RequestMapping(value="/toFBReqAccount")
	public ModelAndView toFBReqAccount(@RequestParam(value = "id", required = false) Integer id){
		return new ModelAndView("customer/fBReqAccount/layer").addObject("fBReqAccountId", id);
	}
	
	
	@RequestMapping(value="/checkFBReqAccount")
	@ResponseBody
	public MessageBox checkFBReqAccount(Integer fBReqAccountId,String checkRemark){
		try {
			ShiroUser shiroUser=currentUser();
			
			BorrowerDealError borrowerDealError=cGBorrowService.selectByPrimaryKey(fBReqAccountId);
			BorrowVo borrowVo=borrowService.selectByPrimaryKey(borrowerDealError.getBorrowId());
			String modeUFBReq="10";//场景  资金解冻
			String relateNoUFBReq=UUIDGenerator.generate(CGBusinessConstants.RELATENO);
			String remarkUFBReq="投标资金冻结解冻,后台手动解冻";
			Integer investmentAmount=borrowerDealError.getAccount().multiply(new BigDecimal(100)).intValue();
			String repXml= cGBorrowService.saveUFBReq(borrowVo, borrowerDealError.getOriSerialNo(), investmentAmount, shiroUser, remarkUFBReq, modeUFBReq, relateNoUFBReq,borrowerDealError.getUserId());
			String uFBResMsg= cGBorrowService.parseUFBResXml(repXml, shiroUser, remarkUFBReq, relateNoUFBReq, modeUFBReq, borrowerDealError,checkRemark);
			if(!uFBResMsg.equals(BusinessConstants.SUCCESS)){
				return new MessageBox("1",uFBResMsg);
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return new MessageBox("1","网络异常，请稍后再试");
		}
		
		
		return new MessageBox("0","网络异常，解冻成功");
		
	}
}
