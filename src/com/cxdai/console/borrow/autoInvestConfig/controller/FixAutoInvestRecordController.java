package com.cxdai.console.borrow.autoInvestConfig.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.borrow.autoInvestConfig.entity.FixAutoInvestRecord;
import com.cxdai.console.borrow.autoInvestConfig.service.FixAutoInvestService;
import com.cxdai.console.borrow.autoInvestConfig.vo.FixAutoInvestCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.util.PropertiesUtil;
/**
 * 
 * <p>
 * Description:自动投宝规则日志记录<br />
 * </p>
 * @title FixAutoInvestRecordController.java
 * @package com.cxdai.console.borrow.autoInvestConfig.controller 
 * @author liutao
 * @date 2015年11月20日
 */
@Controller
@RequestMapping(value = "/borrow/autoInvestConfig/fixAutoInvestRecord")
public class FixAutoInvestRecordController extends BaseController{ 
	
	   private final static Logger logger=Logger.getLogger(FixAutoInvestRecordController.class);
	   
	   @Autowired
	   private FixAutoInvestService fixAutoInvestService;
	   
	   private String path = PropertiesUtil.getValue("portal_path");
	   /**
	    * 
	    * <p>
	    * Description:自动投宝记录主界面<br />
	    * </p>
	    * @author liutao
	    * @date 2015年11月20日
	    * @return
	    * ModelAndView
	    */
	   @RequestMapping("/main")
		public ModelAndView finalFixRecordsMain() {
			return new ModelAndView("/borrow/autoInvestConfig/fixAutoInvestRecord/main");
		}
	   /**
	    * 
	    * <p>
	    * Description:查询自动投宝记录<br />
	    * </p>
	    * @author liutao
	    * @date 2015年11月20日
	    * @param FixAutoInvestCnd
	    * @param pageNo
	    * @return
	    * ModelAndVie
	    */
	   @RequestMapping("/list/{pageNo}")
	   public ModelAndView searchPagefinalFixList(@ModelAttribute FixAutoInvestCnd fixAutoInvestCnd, @PathVariable Integer pageNo) {
	   	Page page = null;
	   	try {
	   		page = fixAutoInvestService.queryRecordListForPage(fixAutoInvestCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
	   	} catch (Exception e) {
	   		logger.error("查询自动投宝日志错误信息："+e);
	   	}
	   	return new ModelAndView("/borrow/autoInvestConfig/fixAutoInvestRecord/list").addObject("page", page).addObject("portal_path", path);
	   }
	   /**
	    * 
	    * <p>
	    * Description:查询记录详情<br />
	    * </p>
	    * @author liutao
	    * @date 2015年11月20日
	    * @param borrowId
	    * @return
	    * ModelAndView
	    */
	   @RequestMapping("/findAutoInvestConfigRecordDetail")
		public  ModelAndView findAutoInvestFixConfigRecordDetail(@ModelAttribute FixAutoInvestCnd  fixAutoInvestCnd) {
		     
			ModelAndView mv = new ModelAndView("/borrow/autoInvestConfig/fixAutoInvestRecord/layer");
			FixAutoInvestRecord fixAutoInvestRecordEntity=new FixAutoInvestRecord();
			try {
				if (fixAutoInvestCnd != null && fixAutoInvestCnd.getId()> 0) {
					 fixAutoInvestRecordEntity = fixAutoInvestService.queryById(fixAutoInvestCnd.getId());
				}
				mv.addObject("fixAutoInvestRecordEntity",fixAutoInvestRecordEntity);
				 
			} catch (Exception e) {
				logger.error("查询自动投宝日志详情错误信息："+e);
			}
		    return mv;
		}
}
