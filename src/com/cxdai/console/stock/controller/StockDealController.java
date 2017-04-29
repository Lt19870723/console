package com.cxdai.console.stock.controller;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.stock.entity.StockDeal;
import com.cxdai.console.stock.entity.StockEntrust;
import com.cxdai.console.stock.service.StockDealService;
import com.cxdai.console.stock.service.StockEntrustService;
import com.cxdai.console.stock.vo.StockDealRequest;
import com.cxdai.console.util.StringUtils;

/***
 * 股权成交记录Controller
 * 
 * @author xiaofei.li
 *
 */
@Controller
@RequestMapping("/stock/stockDeal")
public class StockDealController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(StockDealController.class);

	
	@Autowired
	private StockDealService stockDealService;
	
	@Autowired
	private StockEntrustService stockEntrustService;
	

	/***
	 * 股权成交记录界面
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView interTransferMain() {
		return new ModelAndView("/stock/stockDeal/main");
	}

	/**
	 * 分页列表查询
	 * 
	 * @param requestCnd
	 * @return
	 */	
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageInterTransferList(@ModelAttribute StockDealRequest request,
			@PathVariable Integer pageNo) {
		ModelAndView mv = new ModelAndView("/stock/stockDeal/list");
		try {
			Page page = stockDealService.queryList(request, pageNo, Constants.CONSOLE_PAGE_SIZE);
			mv.addObject("page", page);
		} catch (Exception e) {
			logger.error("分页查询内转成交记录列表错误" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据委托单检索委托详情及记录<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-5-10
	 * @param id
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/{id}/info")
	public ModelAndView eidt(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("/stock/stockDeal/info");
		try{
			if (StringUtils.isNotEmpty(id)) {
				StockDeal record = stockDealService.queryDealById(id);
				mv.addObject("record", record);
				StockEntrust seller = stockEntrustService.queryById(record.getSellerEntrustId()+"");
				mv.addObject("seller", seller);
				StockEntrust buyer = stockEntrustService.queryById(record.getBuyerEntrustId()+"");
				mv.addObject("buyer", buyer);
			}
		}catch(Exception e){
			logger.error("根据日主键获取成交记录实体" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:进入股权统计汇总页面<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-5-25
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/summary")
	public ModelAndView summary() {
		return new ModelAndView("/stock/stockSummary/main");
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:统计汇总估计统计<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-5-25
	 * @param request
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value="/sumStock")
	public ModelAndView sumStock(@ModelAttribute StockDealRequest request) {
		ModelAndView mv = new ModelAndView("/stock/stockSummary/list");
		try{
				Map<String,Object> record = stockDealService.querySummaryByDate(request);
				mv.addObject("record", record);
		}catch(Exception e){
			logger.error("获取内转成交汇总错误" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}
	
}
