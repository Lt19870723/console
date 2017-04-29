package com.cxdai.console.firsttransfer.controller;

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
import com.cxdai.console.firstborrow.service.FirstTransferService;
import com.cxdai.console.firstborrow.vo.FirstTransferCnd;

/**
 * <p>
 * Description:直通车转让 - 直通车已撤销<br />
 * </p>
 * 
 * @title FirstTransferingController.java
 * @package com.cxdai.console.transfer.controller
 * @author tongjuxing
 * @version 0.1 2015年6月26日
 */
@Controller
@RequestMapping(value = "/firsttransfer/transfercancel")
public class FirstTransfercancelController extends BaseController {
	private static final Logger logger = Logger.getLogger(FirstTransfercancelController.class);

	@Autowired
	private FirstTransferService firstTransferService;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/firsttransfer/transfercancel/main");
	}
	
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute FirstTransferCnd firstTransferCnd, @PathVariable("pageNo") Integer pageNo) {
		firstTransferCnd.setTransferStatus(Constants.TRANSFER_CANCEL);
		Page page = firstTransferService.queryFirstTransferListByCnd(firstTransferCnd, pageNo, pageSize);
		
		return forword("/firsttransfer/transfercancel/list").addObject("page", page);
	}
	
}