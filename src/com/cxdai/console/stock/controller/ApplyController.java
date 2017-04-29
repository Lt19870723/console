package com.cxdai.console.stock.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.Dictionary;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.stock.entity.ApplyInfo;
import com.cxdai.console.stock.entity.StockAccount;
import com.cxdai.console.stock.entity.StockApprove;
import com.cxdai.console.stock.service.ApplyService;
import com.cxdai.console.stock.vo.ApplyInfoCnd;

/***
 * 
 * <p>
 * Description:申请审核<br />
 * </p>
 * @title ApplyController.java
 * @package com.cxdai.console.stock.controller 
 * @author xinwugang
 * @version 0.1 2016-5-9
 */
@Controller
@RequestMapping(value="/apply")
public class ApplyController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(ApplyController.class);
	@Autowired
	private ApplyService applyService;
	
	/***
	 * 申请审核信息主页面
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-9
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main/{type}")
	public ModelAndView applyMain(@PathVariable("type") Integer type){
		return new ModelAndView("/stock/apply/main").addObject("type", type);
	}
	/****
	 * 申请审核信息列表页面
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-9
	 * @param pageNo
	 * @param applyCnd
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value = "/applyList/{pageNo}")
	public ModelAndView list(@PathVariable Integer pageNo, ApplyInfoCnd applyCnd) {
		
		Page page = null;
		try {
			page =  applyService.queryApplyForPage(applyCnd,Constants.CONSOLE_PAGE_SIZE, pageNo);
		} catch (Exception e) {
			logger.error(e);
			return new ModelAndView("/stock/apply/list").addObject("page", null);
		}
		return new ModelAndView("/stock/apply/list").addObject("page", page);
		
	}
	/****
	 * 审核页面
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-10
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value = "/toApprove/{id}/{userId}")
	public ModelAndView toApprove(@PathVariable Integer id,@PathVariable Integer userId){
		
		BigDecimal count = null;//散标待收
		BigDecimal fixcount= null;//定期宝待收
		BigDecimal cunguan= null;//存管待收
		ApplyInfo applyInfo = null;
		BigDecimal collectionTotal = null;
		StockAccount stockAccount = null;
		try {
			applyInfo = applyService.selectByPrimaryKey(id);
			if(applyInfo.getType()==1){
				count = applyService.queryCollectionrecord(userId);
				fixcount = applyService.queryFixCollectionrecord(userId);
				cunguan = applyService.queryCunGuanCollectionrecord(userId);
				collectionTotal = count.add(fixcount).add(cunguan);
				String stockApplyNum = Dictionary.getValue(1904, "collection_val");
				return new ModelAndView("/stock/apply/approve").addObject("applyInfo", applyInfo).addObject("collectionTotal", collectionTotal).addObject("stockApplyNum", stockApplyNum);
			}
			if(applyInfo.getType()==2){
				stockAccount = applyService.selectStockCountByUserId(userId);
				return new ModelAndView("/stock/apply/approve").addObject("applyInfo", applyInfo).addObject("stockAccount", stockAccount);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/***
	 * 系统审核
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-10
	 * @param applyInfo
	 * @return
	 * MessageBox
	 */
	@RequestMapping(value = "/approveSubmit")
	@ResponseBody
	public MessageBox approveSubmit(ApplyInfo applyInfo,HttpServletRequest request) {
		try {
			ApplyInfo app = applyService.selectByPrimaryKey(applyInfo.getId());
			if(app.getStatus()==2 || app.getStatus()==3){
				return MessageBox.build("1", "该信息已被其他人审核过，请重新刷新页面!");
			}
			
			
			BigDecimal count = null;//散标待收
			BigDecimal fixcount= null;//定期宝待收
			BigDecimal cunguan = null;//存管待收
			BigDecimal collectionTotal = null;//散标待收 +定期宝待收+存管待收
			String stockApplyNum = Dictionary.getValue(1904, "collection_val");
			BigDecimal collval = new BigDecimal(stockApplyNum);
			count = applyService.queryCollectionrecord(applyInfo.getUserId());
			fixcount = applyService.queryFixCollectionrecord(applyInfo.getUserId());
			cunguan = applyService.queryCunGuanCollectionrecord(applyInfo.getUserId());
			collectionTotal = count.add(fixcount).add(cunguan);
			if(applyInfo.getType()==1){
				if(applyInfo.getStatus()==2 && collectionTotal.compareTo(collval)==-1){
					return MessageBox.build("1", "待收小于20W,不能审核通过！");
				}
			}
			applyService.approveApply(applyInfo,request);
			return MessageBox.build("0", "审批成功");
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "审核失败");
		}
	}
	
	/***
	 * 查询详细审核信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-10
	 * @param id
	 * @param targetType
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value = "/toDetail/{id}/{targetType}")
	public ModelAndView toDetail(@PathVariable Integer id,@PathVariable Integer targetType){
		 ApplyInfo applyInfo = null;
		 List<StockApprove> stockApproveList = null;
		try {
			applyInfo = applyService.selectByPrimaryKey(id);
			StockApprove stockApprove = new StockApprove();
			stockApprove.setTargetId(id);
			stockApprove.setTargetType(targetType);
			stockApproveList = applyService.selectApproveList(stockApprove);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/stock/apply/detail").addObject("applyInfo", applyInfo).addObject("stockApproveList", stockApproveList);
	}

}
