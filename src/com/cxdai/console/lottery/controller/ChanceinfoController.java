package com.cxdai.console.lottery.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.lottery.entity.ChanceInfoSignSet;
import com.cxdai.console.lottery.service.ChanceInfoService;
/**
 * 
 * <p>
 * Description:抽奖机会信息控制类<br />
 * </p>
 * @title GoodController.java
 * @package com.cxdai.console.lottery.controller 
 * @author yubin
 * @version 0.1 2015年7月6日
 */
@Controller
@RequestMapping(value = "/lottery/chanceinfo")
public class ChanceinfoController extends BaseController {
	
	private final static Logger logger=Logger.getLogger(ChanceinfoController.class);
	@Autowired
	private ChanceInfoService chanceInfoService;
	
	  /**
	    * 
	    * <p>
	    * Description:抽奖奖品管理主界面<br />
	    * </p>
	    * @author yubin
	    * @version 0.1 2015年6月23日
	    * @return
	    * ModelAndView
	    */
	    @RequestMapping("/main")
		public ModelAndView main() {
	    	ChanceInfoSignSet dis = chanceInfoService.getDiscount();
			return new ModelAndView("/lottery/chanceinfo/main").addObject("d", dis);
		}
	   /**
	    *  
	    * <p>
	    * Description:抽奖奖品管理列表<br />
	    * </p>
	    * @author Administrator
	    * @version 0.1 2015年6月24日
	    * @param rechargeRecordCnd
	    * @param pageNo
	    * @return
	    * ModelAndVie
	    */
	   @RequestMapping("/list/{pageNo}")
	   public ModelAndView searchGoodList( @PathVariable Integer pageNo,@RequestParam(value = "userName", required = false) String userName) {
	   	Page page = null;
	    //@RequestParam(value = "id", required = false) String userName
	   	try {
	   		page   = chanceInfoService.queryPageChanceInfoList(userName,pageNo, Constants.CONSOLE_PAGE_SIZE);
	   	} catch (Exception e) {
	   	 
	   		logger.error(e);
	   	}
	   	return new ModelAndView("/lottery/chanceinfo/list").addObject("page", page);
	   }
		@RequestMapping("/SignDate")
		public ModelAndView SignDate() {
			ChanceInfoSignSet dis = chanceInfoService.getDiscount();
			return new ModelAndView("/lottery/chanceinfo/signDate").addObject("d", dis);
		}
		@RequestMapping("/signDateSub")
		@ResponseBody
		public MessageBox signDateSub(String beginDate, String endDate, Integer awardNum) {
			if (beginDate == null || beginDate.length() < 10 || endDate == null || endDate.length() < 10 || awardNum == null) {
				return MessageBox.build("0", "数据提交错误");
			}
			try {
				chanceInfoService.SetChanceInfoSign(beginDate, endDate,awardNum);
				return new MessageBox("1", "设置成功");
			} catch (Exception e) {
				logger.error("设置签到活动日期异常：" + e);
				return new MessageBox("0", "操作失败");
			}
		}
}
