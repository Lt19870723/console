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
import com.cxdai.console.fix.service.FixBorrowService;
import com.cxdai.console.fix.vo.FixBorrowCnd;
import com.cxdai.console.fix.vo.FixBorrowVo;
import com.mysql.jdbc.StringUtils;
/**
 * 
 * <p>
 * Description:用户收益查询业务<br />
 * </p>
 * @title FixBorrowController.java
 * @package com.cxdai.console.fix.controller 
 * @author 于斌
 * @version 0.1 2015年7月14日
 */
@Controller
@RequestMapping(value = "/fix/queryInterestForUser")
public class InterestForUserController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(InterestForUserController.class);
    
	@Autowired
	private  FixBorrowService  fixBorrowService;
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
		return new ModelAndView("/fix/queryInterestForUser/main");
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
	public ModelAndView searchPageFixBorrowList(@ModelAttribute FixBorrowCnd fixBorrowCnd, @PathVariable Integer pageNo) {
	Page page = null;
	FixBorrowVo fixBorrowVo=null;
	try {
	    
		fixBorrowCnd.setOrderSql(" ");
		if(StringUtils.isNullOrEmpty(fixBorrowCnd.getBeginTime())){
			fixBorrowCnd.setBeginTime(null);
		}
		if(StringUtils.isNullOrEmpty(fixBorrowCnd.getEndTime())){
			fixBorrowCnd.setEndTime(null);
		}
		page = fixBorrowService.queryPageInterestUserByCnd(fixBorrowCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		//如果编号不为空，则统计定期宝的统计信息
		 
		if(!"".equals(fixBorrowCnd.getContractNo())){
		fixBorrowVo = fixBorrowService.queryAccountByContactNo(fixBorrowCnd);
		 
		}
	} catch (Exception e) {
		logger.error("用户页面查询出错", e);
	}
	  return new ModelAndView("/fix/queryInterestForUser/list").addObject("page", page).addObject("fixBorrowVo", fixBorrowVo);
   }
}
