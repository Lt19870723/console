package com.cxdai.console.system.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.base.entity.IPWhileList;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.Dictionary;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.system.service.IPWhileService;
import com.cxdai.console.system.vo.IPWhileListCnd;

/**
 * 
 * <p>
 * Description:IP白名单<br />
 * </p>
 * @title IPWhileController.java
 * @package com.cxdai.console.system.controller 
 * @author yubin
 * @version 0.1 2015年7月5日
 */
@Controller
@RequestMapping(value = "/system/ipWhile")
public class IPWhileController extends BaseController {
	
    private final static Logger logger=Logger.getLogger(IPWhileController.class);
    @Autowired
	private IPWhileService iPWhileListService; 
	/**
	 * 
	 * <p>
	 * Description:进入IP白名单列表<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
	
		return  new ModelAndView("/system/ipWhile/main").addObject("thirdPlatformList",thirdPlatformList());
	}
	/**
	 * 
	 * <p>
	 * Description:添加页面<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年7月5日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/add")
	public ModelAndView add(){
		 
		return  new ModelAndView("/system/ipWhile/add").addObject("thirdPlatformList",thirdPlatformList());
	}
	/**
	 * 
	 * <p>
	 * Description:查询黑白名单记录<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年7月3日
	 * @param memberCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
	 @RequestMapping("/list/{pageNo}")
		public ModelAndView searchIPWhileList(@ModelAttribute IPWhileListCnd IPWhileListCnd, @PathVariable Integer pageNo) {
	    	 
	    	Page page=null;
	    	try {
				page = iPWhileListService.queryListForPage(IPWhileListCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
			} catch (Exception e) {
				stackTraceError(logger, e);
			}
		   	return new ModelAndView("/system/ipWhile/list").addObject("page", page);
	   }
	/**
	 * 
	 * <p>
	 * Description:进入白名单修改页面<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年7月3日
	 * @param userId
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/initUpdateIPWhile")
	public ModelAndView initUpdateIPWhileList(@RequestParam(value = "id", required = false) Integer userId){
		 
		IPWhileList iPWhileList=null;
		try {
			if (userId != null && userId.intValue() > 0) {
				iPWhileList = iPWhileListService.queryById(userId);
			}
		} catch (Exception e) {
			stackTraceError(logger, e);
		}
		return  new ModelAndView("/system/ipWhile/update").addObject("iPWhileList",iPWhileList )
				                                          .addObject("thirdPlatformList",thirdPlatformList());
	}
   /**
    * 
    * <p>
    * Description:新增或修改白名单<br />
    * </p>
    * @author yubin
    * @version 0.1 2015年6月29日
    * @param borrowId
    * @return
    * MessageBox
    */
   @RequestMapping("/saveOrUpdateIPWhile")
   @ResponseBody
   public  MessageBox saveOrUpdateIPWhile(@ModelAttribute IPWhileList iPWhileList) {
   	String message=null;
   	try {
        message= iPWhileListService.insertOrUpdateIpWhileList(iPWhileList);
        if(message.equals("success")){
   		return  MessageBox.build("0", "操作成功");
        }
        return  MessageBox.build("1", message);
   	} catch (Exception e) {
   		stackTraceError(logger, e);
   		message = "还款失败，请刷新页面后重试！";
   		return  MessageBox.build("1", message);
   	}
   }   
   /**
    * 
    * <p>
    * Description:新增或修改白名单<br />
    * </p>
    * @author yubin
    * @version 0.1 2015年6月29日
    * @param borrowId
    * @return
    * MessageBox
    */
   @RequestMapping("/delIPWhile")
   @ResponseBody
   public  MessageBox delIPWhile(@RequestParam(value = "id", required = false) Integer userId) {
   	String message=null;
   	try {
        message= iPWhileListService.delIpWhileList(userId);
        if(message.equals("删除成功")){
        	return MessageBox.build("0", message);
        }
   		return  MessageBox.build("1", message);
   	} catch (Exception e) {
   		stackTraceError(logger, e);
   		message = "还款失败，请刷新页面后重试！";
   		return  MessageBox.build("1", message);
   	}
   }
   /**
    * 
    * <p>
    * Description:初始化配置元素<br />
    * </p>
    * @author yubin
    * @version 0.1 2015年7月5日
    * @return
    * List<SelectItem>
    */
   public  List<SelectItem> thirdPlatformList(){
	   List<SelectItem> thirdPlatformList = new ArrayList<SelectItem>();
	   thirdPlatformList = new ArrayList<SelectItem>();
	   Collection<Configuration> thirdPlatformCofig = Dictionary.getValues(1400);
	   for (Configuration vo : thirdPlatformCofig) {
		   thirdPlatformList.add(new SelectItem(vo.getValue(), vo.getName()));
	   }
	   return thirdPlatformList;
   }
}