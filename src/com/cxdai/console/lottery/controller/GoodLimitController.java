package com.cxdai.console.lottery.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.lottery.service.GoodLimitService;
import com.cxdai.console.lottery.service.GoodService;
import com.cxdai.console.lottery.vo.Good;
import com.cxdai.console.lottery.vo.GoodsLimit;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.ShiroUtils;

/**
 * 
 * <p>
 * Description:抽奖发放限制控制类<br />
 * </p>
 * 
 * @title GoodController.java
 * @package com.cxdai.console.lottery.controller
 * @author yubin
 * @version 0.1 2015年7月6日
 */
@Controller
@RequestMapping(value = "/lottery/goodlimit")
public class GoodLimitController extends BaseController {

	private final static Logger logger = Logger.getLogger(GoodLimitController.class);
	@Autowired
	private GoodLimitService goodLimitService;
	@Autowired
	private GoodService goodService;

	/**
	 * 
	 * <p>
	 * Description:抽奖奖品管理主界面<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年6月23日
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main() {

		return new ModelAndView("/lottery/goodlimit/main");
	}

	/**
	 * 
	 * <p>
	 * Description:抽奖奖品管理列表<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2015年6月24日
	 * @param rechargeRecordCnd
	 * @param pageNo
	 * @return ModelAndVie
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchGoodList(@PathVariable Integer pageNo) {
		Page page = null;

		try {
			page = goodLimitService.queryPageGoodLimitList(pageNo,
					Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {

			logger.error(e);
		}
		return new ModelAndView("/lottery/goodlimit/list").addObject("page",
				page);
	}
    /**
     * 
     * <p>
     * Description:进入保存商品页面<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年7月7日
     * @param goodsLimitId
     * @return
     * ModelAndView
     */
	@RequestMapping(value = "/toSaveGoodLimit")
	public ModelAndView toSaveGoodLimit(@RequestParam(value = "id", required = false) Integer goodsLimitId) {
		GoodsLimit goodsLimit=null;
		List<SelectItem> chanceRuleInfosInsert = new ArrayList<SelectItem>();
		try {
			List<Good> list = goodService.queryGoods();

			for (Good bankVo : list) {
				chanceRuleInfosInsert.add(new SelectItem(bankVo.getId(), bankVo
						.getRealGoodName()));
			}

			if (goodsLimitId != null && goodsLimitId != 0) {
				goodsLimit = goodLimitService.getGoodsLimitById(goodsLimitId);
			} else {
				goodsLimit = new GoodsLimit();
				goodsLimit.setId(0);
			}
		} catch (Exception e) {

		}
		return new ModelAndView("/lottery/goodlimit/layer").addObject("goodsLimit", goodsLimit)
				                                           .addObject("chanceRuleInfosInsert", chanceRuleInfosInsert);
	}
	/**
	 * 
	 * <p>
	 * Description:保存<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年7月7日
	 * @param goodsLimit
	 * @return
	 * MessageBox
	 */
	@RequestMapping(value = "saveGoodLimit")
	@ResponseBody
	public MessageBox saveGoodLimit(@ModelAttribute GoodsLimit goodsLimit){
		 
		String resultMsg=null;
		try {
			if (goodsLimit.getId() == 0) {
				goodsLimit.setId(null);
			}
			setCommonInfo(goodsLimit);
			String startTimeStr=goodsLimit.getStartTimeStr();
			if (startTimeStr == null) {
				resultMsg = "请选择开始时间";
				return new MessageBox("0", resultMsg); 
			}
		 
			Date startTime = DateUtils.strToDate(startTimeStr);
            
			String endTimeStr=goodsLimit.getEndTimeStr();	
			if (endTimeStr == null) {
				resultMsg = "请选择结束时间";
				return new MessageBox("0", resultMsg);  
			}
			Date endTime =  DateUtils.strToDate(endTimeStr);

			String monthOneDayStr = DateUtils.format(endTime, DateUtils.YMD_DASH) + " 23:59:59";
			Date endtime = DateUtils.parse(monthOneDayStr, DateUtils.YMD_HMS);
			goodsLimit.setEndTime(endtime);
			goodsLimit.setStartTime(startTime);
			endTime = goodsLimit.getEndTime();
            
			if (!startTime.before(endTime)) {
				resultMsg = "开始时间不能大于结束时间";
				return new MessageBox("0", resultMsg); 
			}
            
			goodLimitService.saveGoodLimit(goodsLimit);
			resultMsg = "操作成功";
			return new MessageBox("1", resultMsg); 
		} catch (NumberFormatException e) {
			resultMsg = "操作出错";
		} catch (RuntimeException e) {
			resultMsg = e.getMessage();
		} catch (Exception e) {
			resultMsg = "操作出错";
		}
		return new MessageBox("0", resultMsg); 
	}
	private void setCommonInfo(GoodsLimit good2) {
		String ip = HttpTookit.getRealIpAddr(currentRequest());
		ShiroUser shiroUser=ShiroUtils.currentUser();
		Integer userId = shiroUser.getUserId();
		if (good2.getId() == null) {
			good2.setAddIp(ip);
			good2.setAddUserId(userId);
			good2.setAddTime(new Date());
		}
	}
	/**
	 * 
	 * <p>
	 * Description:删除<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年7月7日
	 * @param goodsLimitId
	 * @return
	 * MessageBox
	 */
	@RequestMapping(value = "/deleteGoodLimit")
	@ResponseBody
	public MessageBox deleteGoodLimit(@RequestParam(value = "id", required = false) Integer goodsLimitId){
		try {
			
			goodLimitService.deleteGoodLimitById(goodsLimitId);
			return new MessageBox("1", "删除成功"); 
		} catch (Exception e) {
			return new MessageBox("0", "删除失败"); 
		}
	}
}
