package com.cxdai.console.stock.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.stock.entity.StockDeal;
import com.cxdai.console.stock.entity.StockEntrust;
import com.cxdai.console.stock.service.StockDealService;
import com.cxdai.console.stock.service.StockEntrustService;
import com.cxdai.console.stock.vo.StockEntrustCnd;
import com.cxdai.console.stock.vo.StockEntrustRequest;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.StringUtils;

/***
 * 充值核对Controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/stock/stockEntrust")
public class StockEntrustController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(StockEntrustController.class);

	@Autowired
	private StockEntrustService stockEntrustService;
	
	@Autowired
	private StockDealService stockDealService;
	

	/***
	 * 提现核对主界面
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView interTransferMain() {
		return new ModelAndView("/stock/stockEntrust/main");
	}

	/**
	 * 分页列表查询
	 * 
	 * @param requestCnd
	 * @return
	 */	
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageInterTransferList(@ModelAttribute StockEntrustRequest request,
			@PathVariable Integer pageNo) {
		ModelAndView mv = new ModelAndView("/stock/stockEntrust/list");
		try {
			Page page = stockEntrustService.queryList(request, pageNo, Constants.CONSOLE_PAGE_SIZE);
			mv.addObject("page", page);
		} catch (Exception e) {
			logger.error("分页查询内转委托挂单列表错误" + e.getMessage());
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
		ModelAndView mv = new ModelAndView("/stock/stockEntrust/info");
		try{
			if (StringUtils.isNotEmpty(id)) {
				StockEntrust record = stockEntrustService.queryById(id);
				mv.addObject("record", record);
				List<StockDeal> list = stockDealService.queryDealList(id);
				mv.addObject("list", list);
			}
		}catch(Exception e){
			logger.error("根据日主键获取委托单实体及成交记录" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:撤销委托单<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-6-2
	 * @param entrustCnd
	 * @param req
	 * @return
	 * MessageBox
	 */
	@RequestMapping(value="/revokeEntrust")
	@ResponseBody
	public MessageBox revokeEntrust(StockEntrustCnd entrustCnd, HttpServletRequest req){
		MessageBox result = null;
		try{
			//参数校验
			if(entrustCnd.getId() == null || entrustCnd.getUserId() == null){
				return  new MessageBox("30000", "非法参数提交！");
			}
			entrustCnd.setUpdateIp(HttpTookit.getRealIpAddr(currentRequest()));
			result = stockEntrustService.saveRevokeEntrust(entrustCnd,currentUser().getUserId(),currentUser().getUserName());
		}catch(Exception e){
			logger.error("撤单失败"+e.getMessage());
			result = new MessageBox("40000", "撤单失败！"+e.getMessage());
		}
		return result;
		
	}
}
