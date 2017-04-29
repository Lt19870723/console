package com.cxdai.console.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.util.PropertiesUtil;
/**
 * 
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PortalDictionaryController.java
 * @package com.cxdai.console.system.controller 
 * @author yubin
 * @version 0.1 2015年7月5日
 */
@Controller
@RequestMapping(value = "/system/dictionary")
public class PortalDictionaryController extends BaseController {
	
	private String path=PropertiesUtil.getValue("portal_path");
	/**
	 * 
	 * <p>
	 * Description:官网数据字典维护页面<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年7月5日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
	
		return  new ModelAndView("/system/dictionary/main").addObject("portal_path",path);
	}
}
