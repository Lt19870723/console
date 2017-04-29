package com.cxdai.console.statistics.customer.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.customer.service.ShareholderRankService;
import com.cxdai.console.statistics.customer.vo.ShareholderRankCnd;
import com.cxdai.console.statistics.customer.vo.ShareholderRankVo;

/**
 * 股东加权排名
 * @return
 */
@Controller
@RequestMapping(value="/customer/shareholdercount")
public class ShareholderController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(ShareholderController.class);
	@Autowired
	private ShareholderRankService shareholderRankService;
	
	@RequestMapping("/main")
	public ModelAndView forShareholderMain(){
		return new ModelAndView("/statistics/customer/shareholder/main");
	}
	
	/**
	 * 股东加权排名列表
	 * @param shareholderRankCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchShareholder(@ModelAttribute ShareholderRankCnd shareholderRankCnd,@PathVariable Integer pageNo){
		Page page = null;
		try {
			page = shareholderRankService.queryPagesList(shareholderRankCnd, pageNo, Constants.CONSOLE_PAGE_SIZE2);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("股东加权排名错误信息"+e);
		}
		return new ModelAndView("/statistics/customer/shareholder/list").addObject("page", page);
	}
	
	/**
	 * 查询股东加权排名页面
	 * @return
	 */
	@RequestMapping("/findShareholder/{id}")
	public ModelAndView findShareholder(@PathVariable Integer id){
		ShareholderRankVo shareholderRankVo = new ShareholderRankVo();
		try {
			shareholderRankVo = shareholderRankService.queryById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/statistics/customer/shareholder/layer").addObject("shareholderRankVo", shareholderRankVo);
	}

}
