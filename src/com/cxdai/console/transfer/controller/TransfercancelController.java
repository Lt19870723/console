package com.cxdai.console.transfer.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.transfer.service.TransferService;
import com.cxdai.console.transfer.vo.TransferListCnd;
import com.cxdai.console.common.Constants;

/**
 * <p>
 * Description:债权转让 - 已撤销债权<br />
 * </p>
 * 
 * @title TransferingController.java
 * @package com.cxdai.console.transfer.controller
 * @author tongjuxing
 * @version 0.1 2015年6月26日
 */
@Controller
@RequestMapping(value = "/transfer/transfercancel")
public class TransfercancelController extends BaseController {
	private static final Logger logger = Logger.getLogger(TransfercancelController.class);

	@Autowired
	private TransferService transferService;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/transfer/transfercancel/main");
	}

	
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute TransferListCnd transferListCnd, @PathVariable("pageNo") Integer pageNo) {
        transferListCnd.setTransferType(Constants.TRANSFER_CANCEL);
		Page page = transferService.queryPageTransferListByCnd(transferListCnd, pageNo, pageSize);
		return forword("/transfer/transfercancel/list").addObject("page", page);
	}
	
}