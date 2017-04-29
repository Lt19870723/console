package com.cxdai.console.borrow.approve.controller;

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

import com.cxdai.console.borrow.approve.service.BorrowService;
import com.cxdai.console.borrow.approve.service.WsBorrowService;
import com.cxdai.console.borrow.approve.vo.BorrowApprovedVo;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.PropertiesUtil;
import com.cxdai.console.util.ShiroUtils;
import com.cxdai.console.util.WebServiceMD5;
/**
 * 
 * <p>
 * Description:终审业务逻辑<br />
 * </p>
 * @title FirstController.java
 * @package com.cxdai.console.borrow.approve.controller 
 * @author Administrator
 * @version 0.1 2015年6月24日
 */
@Controller
@RequestMapping(value ="/approve/first")
public class FirstController extends BaseController{
    
	private final static Logger logger=Logger.getLogger(FirstController.class);
	@Autowired
	private BorrowService borrowService;
	@Autowired
	private WsBorrowService wsBorrowService;
	private String path=PropertiesUtil.getValue("portal_path");
    @RequestMapping("/main")
	public ModelAndView firstRecordsMain() {
		 
		return new ModelAndView("/borrow/approve/first/main");
	 }
	   /**
	    * 
	    * <p>
	    * Description:查询终审列表<br />
	    * </p>
	    * @author Administrator
	    * @version 0.1 2015年6月24日
	    * @param rechargeRecordCnd
	    * @param pageNo
	    * @return
	    * ModelAndVie
	    */
	   @RequestMapping("/list/{pageNo}")
	   public ModelAndView searchPagefirstList(@ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
	   	Page page = null;
	    
	   	try {
	   		page = borrowService.selectFirstcheckBorrow(borrowCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
	   	} catch (Exception e) {
	   		e.printStackTrace();
	   		logger.error(e);
	   	}
	   	return new ModelAndView("/borrow/approve/first/list").addObject("page", page).addObject("portal_path", path);
	   }
	   /**
	    * 
	    * <p>
	    * Description:借款标初审页面<br />
	    * </p>
	    * @author Administrator
	    * @version 0.1 2015年6月25日
	    * @param menuId
	    * @return
	    * ModelAndView
	    */
	    @RequestMapping(value = "/firstCheck")
		public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) {
			ModelAndView mv = new ModelAndView("/borrow/approve/first/layer");
			if(id!=null){
				mv.addObject("id", id);
			}
			return mv;
		}
	    /**
		 * 借款标初审审核结果
		 * @return
		 */
		@RequestMapping("/pass")
		@ResponseBody
		public MessageBox approvePass(@ModelAttribute BorrowApprovedVo borrowApprovedVo,@ModelAttribute BorrowVo borrow){
		    String msg ="";
			try {
				ShiroUser shiroUser = ShiroUtils.currentUser();
				Map<String, Object> validateKeyMap = new HashMap<>();
				validateKeyMap.put("borrowId", borrowApprovedVo.getBorrowId());
				validateKeyMap.put("flag", borrowApprovedVo.getFlag());
				validateKeyMap.put("userId", shiroUser.getUserId());
				validateKeyMap.put("remark", borrowApprovedVo.getRemark());
				validateKeyMap.put("creditRating", borrow.getCreditRating());
				msg = wsBorrowService.saveApproveBorrowFirst(borrowApprovedVo.getBorrowId(), borrowApprovedVo.getFlag(),shiroUser.getUserId(), borrowApprovedVo.getRemark(), borrow.getCreditRating(),
						WebServiceMD5.encodeParams(validateKeyMap));
				if(msg.equals(BusinessConstants.SUCCESS)) {
					return MessageBox.build("0", "审核通过");
				}
				return MessageBox.build("-1", msg);
			} catch (Exception e) {
				stackTraceError(logger, e);
				return MessageBox.build("1", "操作失败");
			}
		}
}
