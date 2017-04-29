package com.cxdai.console.firsttransfer.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.base.entity.FirstTransferApproved;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.firstborrow.service.FirstSubscribeService;
import com.cxdai.console.firstborrow.service.FirstTransferService;
import com.cxdai.console.firstborrow.vo.FirstTransferCnd;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.HttpTookit;

/**
 * <p>
 * Description:直通车转让 - 复审<br />
 * </p>
 * 
 * @title FirstTransfercheckController.java
 * @package com.cxdai.console.transfer.controller
 * @author tongjuxing
 * @version 0.1 2015年6月26日
 */
@Controller
@RequestMapping(value = "/firsttransfer/transfercheck")
public class FirstTransfercheckController extends BaseController {
	private static final Logger logger = Logger.getLogger(FirstTransfercheckController.class);

	@Autowired
	private FirstTransferService firstTransferService;
	
	@Autowired
	private FirstSubscribeService firstSubscribeService;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/firsttransfer/transfercheck/main");
	}

	
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute FirstTransferCnd firstTransferCnd, @PathVariable("pageNo") Integer pageNo) {
		firstTransferCnd.setApprovedStatus(Constants.TRANSFER_FIRST_STATU_PASS);
		Page page = firstTransferService.queryPageListByCnd(firstTransferCnd, pageNo, pageSize);
		
		return forword("/firsttransfer/transfercheck/list").addObject("page", page);
	}
	
	@RequestMapping(value = "/status")
	public ModelAndView status(@RequestParam(value = "id", required = true) Integer id) {
		
		FirstTransferApproved firstTransferApproved = new FirstTransferApproved();
		firstTransferApproved.setFirstTransferId(id);
		
		return forword("/firsttransfer/transfercheck/status").addObject("firstTransferApproved", firstTransferApproved);
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public MessageBox save(@ModelAttribute FirstTransferApproved firstTransferApproved) {
		
		try {
			// 获取ip
			String ip = HttpTookit.getRealIpAddr(currentRequest());
			ShiroUser user = currentUser();
			String result = firstSubscribeService.saveTransferRecheck(user, firstTransferApproved, ip);

			if (BusinessConstants.SUCCESS.equals(result)) {
				return MessageBox.build("0", "直通车转让复审成功！");
			} else {
				return new MessageBox("1", result);
			}
			
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "直通车转让复审失败！");
		}
	}
	
}