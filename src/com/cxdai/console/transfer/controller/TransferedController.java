package com.cxdai.console.transfer.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.transfer.service.TransferService;
import com.cxdai.console.transfer.vo.SubscribeVo;
import com.cxdai.console.transfer.vo.TransferListCnd;
import com.cxdai.console.common.Constants;

/**
 * <p>
 * Description:债权转让 - 已转让债权<br />
 * </p>
 * 
 * @title TransferingController.java
 * @package com.cxdai.console.transfer.controller
 * @author tongjuxing
 * @version 0.1 2015年6月25日
 */
@Controller
@RequestMapping(value = "/transfer/transfered")
public class TransferedController extends BaseController {
	private static final Logger logger = Logger.getLogger(TransferedController.class);

	@Autowired
	private TransferService transferService;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/transfer/transfered/main");
	}

	
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute TransferListCnd transferListCnd, @PathVariable("pageNo") Integer pageNo) {
        transferListCnd.setTransferType(Constants.TRANSFER_SUCCESS);
		Page page = transferService.queryPageTransferListByCnd(transferListCnd, pageNo, pageSize);
		return forword("/transfer/transfered/list").addObject("page", page);
	}
	
	/**
	 * <p>
	 * Description:查询认购债权人列表<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年6月26日
	 */
	@RequestMapping(value = "/detail")
	public ModelAndView searchPageSubscribeMemberTransferedList(@RequestParam(value = "id", required = true) Integer id) {
		List<SubscribeVo> subscribeMemberList = new ArrayList<SubscribeVo>();
		try {
			subscribeMemberList = transferService.querySubscribeMemberByTransferId(id);
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/transfer/transfered/detail").addObject("subscribelist", subscribeMemberList);
	}
	
}