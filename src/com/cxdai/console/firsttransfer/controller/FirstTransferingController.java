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

//import com.cxdai.base.entity.Staff;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.firstborrow.service.FirstTransferService;
import com.cxdai.console.firstborrow.vo.FirstTransferCnd;
import com.cxdai.console.firstborrow.vo.FirstTransferVo;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.transfer.vo.TransferVo;
import com.cxdai.console.util.HttpTookit;

/**
 * <p>
 * Description:直通车转让 - 直通车转让中<br />
 * </p>
 * 
 * @title FirstTransferingController.java
 * @package com.cxdai.console.firsttransfer.controller
 * @author tongjuxing
 * @version 0.1 2015年6月24日
 */
@Controller
@RequestMapping(value = "/firsttransfer/transfering")
public class FirstTransferingController extends BaseController {
	private static final Logger logger = Logger.getLogger(FirstTransferingController.class);

	
	@Autowired
	private FirstTransferService firstTransferService;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/firsttransfer/transfering/main");
	}

	
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute FirstTransferCnd firstTransferCnd, @PathVariable("pageNo") Integer pageNo) {
		firstTransferCnd.setTransferStatus(Constants.TRANSFER_STATU_ING);
		Page page = firstTransferService.queryFirstTransferListByCnd(firstTransferCnd, pageNo, pageSize);
		
		return forword("/firsttransfer/transfering/list").addObject("page", page);
	}
	
	@RequestMapping(value = "/cancel")
	public ModelAndView cancel(@RequestParam(value = "id", required = true) Integer id) {
		
		TransferVo transfer = new TransferVo();
		transfer.setId(id);
		
		return forword("/firsttransfer/transfering/cancel").addObject("transfer", transfer);
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public MessageBox save(@ModelAttribute FirstTransferVo transfer) {

		try {
			

			// 获取ip
			String ip = HttpTookit.getRealIpAddr(currentRequest());

			ShiroUser user = currentUser();
			String result = firstTransferService.saveCancelFirstTransfer(transfer, user, ip);
			
			if (BusinessConstants.SUCCESS.equals(result)) {
				return MessageBox.build("0", "取消转让成功");
			} else {
				logger.error("后台取消转让：userid=" + user.getUserId() + ",transferid=" + transfer.getId());
				return MessageBox.build("1", "取消转让失败");
			}

		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "取消转让失败");
		}
	}
	
}