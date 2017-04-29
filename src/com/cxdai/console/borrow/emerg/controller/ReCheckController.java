package com.cxdai.console.borrow.emerg.controller;

 

import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.cxdai.console.borrow.check.vo.BorrowReCheckCnd;
import com.cxdai.console.borrow.emerg.service.EmergService;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.HttpClientUtils;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.PropertiesUtil;
import com.cxdai.console.util.ShiroUtils;
import com.cxdai.console.util.WebServiceMD5;
 
/**
 * 
 * <p>
 * Description:投标出错复审业务<br /> 
 * </p>
 * @title ReCheckController.java
 * @package com.cxdai.console.borrow.emerg.controller 
 * @author Administrator
 * @version 0.1 2015年6月25日
 */
@Controller
@RequestMapping(value = "/borrow/recheck")
public class ReCheckController extends BaseController{
	  private final static Logger logger=Logger.getLogger(HandleFirstController.class);
	   
	   @Autowired
	   private EmergService emergService;
	   @Autowired
	   private WsBorrowService wsBorrowService;
	   @Autowired
	   private BorrowService borrowService;
	   
	   private String path=PropertiesUtil.getValue("portal_path");
	   
	   
	   /**
	    * 
	    * <p>
	    * Description:手动直通车主页面<br />
	    * </p>
	    * @author Administrator
	    * @version 0.1 2015年6月24日
	    * @return
	    * ModelAndView
	    */
	   @RequestMapping("/main")
		public ModelAndView reCheckRecordsMain() {
		 
			return new ModelAndView("/borrow/emerg/recheck/main");
		}
       /**
        * 
        * <p>
        * Description:分页查询复审标集合<br />
        * </p>
        * @author Administrator
        * @version 0.1 2015年6月25日
        * @param borrowCnd
        * @param pageNo
        * @return
        * ModelAndView
        */
	   @RequestMapping("/list/{pageNo}")
	   public ModelAndView searchReCheckList(@ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
	   	Page page = null;
	   	 
	   	try {
	   		page = emergService.selectReCheckBorrow(borrowCnd, pageNo,Constants.CONSOLE_PAGE_SIZE);
			 
	   	} catch (Exception e) {
	   		logger.error(e);
	   	}
	   	return new ModelAndView("/borrow/emerg/recheck/list").addObject("page", page).addObject("portal_path",path);
	   }
	    /**
	     * 
	     * <p>
	     * Description:借款标复审<br />
	     * </p>
	     * @author yubin
	     * @version 0.1 2015年6月27日
	     * @param borrowApprovedVo
	     * @param borrowVo
	     * @return
	     * MessageBox
	     */
		@RequestMapping("/checkBorrowReCheck")
		@ResponseBody
		public MessageBox checkBorrowReCheck(@ModelAttribute BorrowApprovedVo borrowApprovedVo ){
			String msg = null;
			try {
				    ShiroUser shiroUser = ShiroUtils.currentUser();
					// 验证数据是否正确
					Map<String, Object> validateKeyMap = new HashMap<>();
					validateKeyMap.put("borrowid", Integer.parseInt(String.valueOf(borrowApprovedVo.getBorrowId())));
					validateKeyMap.put("checkUserId", shiroUser.getUserId());
					validateKeyMap.put("checkRemark", borrowApprovedVo.getRemark());
					validateKeyMap.put("addip", HttpTookit.getRealIpAddr(currentRequest()));
					BorrowVo borrowVo = borrowService.selectByPrimaryKey(borrowApprovedVo.getBorrowId());
					if(borrowVo.getIsCustody()==0 || borrowVo.getIsCustody()==null){
					msg = wsBorrowService.saveApproveBorrowReCheck( borrowApprovedVo.getBorrowId(), shiroUser.getUserId(), borrowApprovedVo.getRemark(),
							HttpTookit.getRealIpAddr(currentRequest()), WebServiceMD5.encodeParams(validateKeyMap));
					}else if(borrowVo.getIsCustody()==1){
						msg = wsBorrowService.cGsaveApproveBorrowReCheck( borrowApprovedVo.getBorrowId(), shiroUser.getUserId(), borrowApprovedVo.getRemark(),
								HttpTookit.getRealIpAddr(currentRequest()), WebServiceMD5.encodeParams(validateKeyMap));
						if(msg.equals(BusinessConstants.SUCCESS)){
							msg=this.CGcheckBorrowReCheck(borrowVo.getId(), shiroUser);
						}
						
					}
				if(msg.equals(BusinessConstants.SUCCESS)) {
					return MessageBox.build("0", "审核通过");
				}
				return MessageBox.build("-1", msg);
			} catch (Exception e) {
				stackTraceError(logger, e);
				return MessageBox.build("1", "系统繁忙,请稍后重试或联系系统管理员！");
			}
		}
		/**
		 *   
		 * <p>
		 * Description:跳转投错标出错复审窗体页面<br />
		 * </p>
		 * @author yubin
		 * @version 0.1 2015年6月28日
		 * @param id
		 * @return
		 * ModelAndView
		 */
		@RequestMapping(value = "/reCheckpage")
	    public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) {
				ModelAndView mv = new ModelAndView("/borrow/emerg/recheck/layer");
				if(id!=null){
					mv.addObject("id", id);
				}
				return mv;
	    }
		
		
		/**
		 * 调用官网复审接口
		 * <p>
		 * Description:这里写描述<br />
		 * </p>
		 * @author tanghaitao
		 * @version 0.1 2016年6月14日
		 * @param borrowId
		 * @param shiroUser
		 * @return
		 * String
		 */
		public String CGcheckBorrowReCheck(Integer borrowId,ShiroUser shiroUser){
			LinkedHashMap<String, ?> map = null;
			try {
				BorrowReCheckCnd vo=new BorrowReCheckCnd();
				vo.setBorrowId(borrowId);
				vo.setUserId(shiroUser.getUserId());
				vo.setPlatform(shiroUser.getPlatform());
				 map =HttpClientUtils.getRomoteObject(getClient(), path+"/CGBorrow/CGcheckBorrowReCheck.html", vo);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			return (String) map.get("code");
		}
}
