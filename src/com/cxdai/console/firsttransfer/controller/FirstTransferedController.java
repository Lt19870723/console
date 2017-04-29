package com.cxdai.console.firsttransfer.controller;

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
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.firstborrow.service.FirstSubscribeService;
import com.cxdai.console.firstborrow.service.FirstTransferService;
import com.cxdai.console.firstborrow.vo.FirstSubscribeVo;
import com.cxdai.console.firstborrow.vo.FirstTransferCnd;

/**
 * <p>
 * Description:直通车转让 - 直通车已转让<br />
 * </p>
 * 
 * @title FirstTransferingController.java
 * @package com.cxdai.console.transfer.controller
 * @author tongjuxing
 * @version 0.1 2015年6月25日
 */
@Controller
@RequestMapping(value = "/firsttransfer/transfered")
public class FirstTransferedController extends BaseController {
	private static final Logger logger = Logger.getLogger(FirstTransferedController.class);

	@Autowired
	private FirstTransferService firstTransferService;
	
	@Autowired
	private FirstSubscribeService firstSubscribeService;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/firsttransfer/transfered/main");
	}

	
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute FirstTransferCnd firstTransferCnd, @PathVariable("pageNo") Integer pageNo) {
		firstTransferCnd.setTransferStatus(Constants.TRANSFER_SUCCESS);
		Page page = firstTransferService.queryFirstTransferListByCnd(firstTransferCnd, pageNo, pageSize);
		return forword("/firsttransfer/transfered/list").addObject("page", page);
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
		List<FirstSubscribeVo> firstSubscribeList = new ArrayList<FirstSubscribeVo>();
		try {
			firstSubscribeList = firstSubscribeService.querySubscribeListByTransferId(id);
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/firsttransfer/transfered/detail").addObject("subscribelist", firstSubscribeList);
	}
	
}