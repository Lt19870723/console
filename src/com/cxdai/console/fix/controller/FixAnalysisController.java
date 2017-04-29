package com.cxdai.console.fix.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.fix.mapper.FixBorrowMapper;
import com.cxdai.console.fix.service.FixBorrowService;
import com.cxdai.console.fix.vo.FixBorrowCnd;
import com.cxdai.console.fix.vo.FixStaticVo;
import com.cxdai.console.util.DateUtils;

/**
 * @author WangQianJin
 * @title 定期宝统计分析
 * @date 2016年3月15日
 */
@Controller
@RequestMapping(value = "/fix/fixAnalysis")
public class FixAnalysisController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(FixAnalysisController.class);
    
	@Autowired
	private FixBorrowService fixBorrowService;
	@Autowired
	private FixBorrowMapper fixBorrowMapper;
	
	/**
	 * 统计分析主页
	 * @author WangQianJin
	 * @title @return
	 * @date 2016年3月15日
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		return new ModelAndView("/fix/fixAnalysis/main");
	}
	
	/**
	 * 统计分析内容页
	 * @author WangQianJin
	 * @title @param fixBorrowCnd
	 * @title @param pageNo
	 * @title @return
	 * @date 2016年3月15日
	 */
    @RequestMapping("/list")
	public ModelAndView list(@ModelAttribute FixBorrowCnd fixBorrowCnd,HttpServletRequest request) {
    	FixStaticVo fixStaticVo=new FixStaticVo();		 
		try {			
			if(!StringUtils.isEmpty(fixBorrowCnd.getBeginTime())){
				fixBorrowCnd.setBeginDate(DateUtils.parse(fixBorrowCnd.getBeginTime() + " 00:00:00", DateUtils.YMD_HMS));
			}
			if(!StringUtils.isEmpty(fixBorrowCnd.getEndTime())){
				fixBorrowCnd.setEndDate(DateUtils.parse(fixBorrowCnd.getEndTime()+ " 23:59:59", DateUtils.YMD_HMS));
			}
			if(null!=fixBorrowCnd&&null!=fixBorrowCnd.getLockLimit()&&fixBorrowCnd.getLockLimit()==100000000){
				fixStaticVo = fixBorrowMapper.queryNewFixStaticAnalysis(fixBorrowCnd);
			}else{
				fixStaticVo = fixBorrowMapper.queryFixStaticAnalysis(fixBorrowCnd);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return new ModelAndView("/fix/fixAnalysis/list").addObject("fixStaticVo", fixStaticVo);
   }
}
