package com.cxdai.console.fix.controller;

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
import com.cxdai.console.fix.service.FixTenderDetailService;
import com.cxdai.console.fix.vo.FixTenderDetailCnd;
import com.cxdai.console.fix.vo.FixTenderDetailVo;
/**
 * 
 * <p>
 * Description:定期宝用户加入统计<br />
 * </p>
 * @title FixBorrowController.java
 * @package com.cxdai.console.fix.controller 
 * @author 于斌
 * @version 0.1 2015年7月14日
 */
@Controller
@RequestMapping(value = "/fix/joinFixBorrow")
public class JoinFixBorrowController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(JoinFixBorrowController.class);
    
	@Autowired
	private FixTenderDetailService fixTenderDetailService;
	/**
	 * 
	 * <p>
	 * Description:进入定期宝业务<br />
	 * </p>
	 * @author 于斌
	 * @version 0.1 2015年7月14日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		return new ModelAndView("/fix/joinFixBorrow/main");
	}
	/**
	 * 
	 * <p>
	 * Description:定期宝业务查询 <br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
    @RequestMapping("/list/{pageNo}")
	public ModelAndView searchTotalForJoinFixBorrowList(@ModelAttribute FixTenderDetailCnd fixTenderDetailCnd, @PathVariable Integer pageNo) {
	Page page = null; 
	FixTenderDetailVo fixTenderDetailVo=null; 
	try {
		 
		page = fixTenderDetailService.queryTotalForJoinFixBorrow(fixTenderDetailCnd, pageNo,Constants.CONSOLE_PAGE_SIZE);
		//判断是否合同号有输入，如果有输入，则统计定期宝相关信息，反之不统计
		if(null!=fixTenderDetailCnd.getContractNo() && !fixTenderDetailCnd.getContractNo().equals("")){
			fixTenderDetailVo = fixTenderDetailService.queryTotalForJoinFixBorrowSingle(fixTenderDetailCnd.getContractNo().trim());
		}else{
			fixTenderDetailVo = new FixTenderDetailVo();
		}	 
	} catch (Exception e) {
		
	   		logger.error(e);
	}
	  return new ModelAndView("/fix/joinFixBorrow/list").addObject("page", page).addObject("fixTenderDetailVo", fixTenderDetailVo);
   }
}
