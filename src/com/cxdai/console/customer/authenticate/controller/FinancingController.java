package com.cxdai.console.customer.authenticate.controller;

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
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.customer.authenticate.entity.QuickFinancing;
import com.cxdai.console.customer.authenticate.service.QuickFinancingService;
import com.cxdai.console.customer.authenticate.vo.QuickFinancingCnd;
/**
 * 
 * <p>
 * Description:我要融资业务<br />
 * </p>
 * @title FinancingController.java
 * @package com.cxdai.console.customer.authenticate.controller 
 * @author yubin
 * @version 0.1 2015年9月8日
 */
@Controller
@RequestMapping(value = "/authenticte/financing")
public class FinancingController extends BaseController {
	private static final Logger logger = Logger.getLogger(FinancingController.class);
	@Autowired
	QuickFinancingService quickFinancingService;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/customer/authenticate/financingmain");
	}

	/**
	 * 
	 * <p>
	 * Description:快速融资审核列表<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年9月8日
	 * @param realNameApproCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView searchPageRealNameApproList(@ModelAttribute QuickFinancingCnd cnd, @PathVariable("pageNo") Integer pageNo) {
		try {
			Page page = quickFinancingService.getFinancing(cnd,pageNo, 10);
			return forword("/customer/authenticate/financinglist").addObject("page", page);
		} catch (Exception e) {

		}
		return null;
	}

	@RequestMapping("/approve")
	public @ResponseBody
	MessageBox approve(@RequestParam("id") Integer id,
			           @RequestParam("status") Integer status,
			           @RequestParam("remark") String remark) {
		String resultMsg = "success";
		try {
			resultMsg=quickFinancingService.saveFinancingCheck(id, status, remark); 
			if(!resultMsg.equals(BusinessConstants.SUCCESS)){
				return new MessageBox("0", "审核不成功");
			}
		} catch (Exception e) {
			logger.error("approve", e);
			return new MessageBox("0", e.getMessage());
		}

		return new MessageBox("1", "审核成功");

	}
	@RequestMapping("/view")
	@ResponseBody
	public QuickFinancing view(@RequestParam("id") Integer id){
		QuickFinancing quickFinancing=null;
		quickFinancing=quickFinancingService.findById(id);
		return quickFinancing;
	}

}
