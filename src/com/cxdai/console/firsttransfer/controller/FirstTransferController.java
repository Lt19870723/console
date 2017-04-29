package com.cxdai.console.firsttransfer.controller;

import java.util.Date;

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
import com.cxdai.console.firstborrow.service.FirstTransferService;
import com.cxdai.console.firstborrow.vo.FirstTransferCnd;
import com.cxdai.console.util.HttpTookit;

/**
 * <p>
 * Description:直通车转让 - 初审<br />
 * </p>
 * 
 * @title FirstTransferController.java
 * @package com.cxdai.console.transfer.controller
 * @author tongjuxing
 * @version 0.1 2015年6月24日
 */
@Controller
@RequestMapping(value = "/firsttransfer/transfer")
public class FirstTransferController extends BaseController {
	private static final Logger logger = Logger.getLogger(FirstTransferController.class);

	@Autowired
	private FirstTransferService firstTransferService;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/firsttransfer/transfer/main");
	}

	
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute FirstTransferCnd firstTransferCnd, @PathVariable("pageNo") Integer pageNo) {
		firstTransferCnd.setApprovedStatus(Constants.TRANSFER_STATU_NEW_WAIT);
		Page page = firstTransferService.queryPageListByCnd(firstTransferCnd, pageNo, pageSize);
		return forword("/firsttransfer/transfer/list").addObject("page", page);
	}
	
	@RequestMapping(value = "/status")
	public ModelAndView status(@RequestParam(value = "id", required = true) Integer id) {
		
		FirstTransferApproved firstTransferApproved = new FirstTransferApproved();
		firstTransferApproved.setFirstTransferId(id);
		
		return forword("/firsttransfer/transfer/status").addObject("firstTransferApproved", firstTransferApproved);
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public MessageBox save(@ModelAttribute FirstTransferApproved firstTransferApproved) {
		
		try {
			
			Integer status = firstTransferApproved.getStatus();
			if (status == null) {
				return new MessageBox("1", "审核状态错误");
			}

			if (!(status == Constants.TRANSFER_FIRST_STATU_PASS || status == Constants.TRANSFER_FIRST_STATU_NOPASS)) {
				return new MessageBox("1", "审核状态错误");
			}
			
			// 获取ip
			String ip = HttpTookit.getRealIpAddr(currentRequest());
			Integer userId = currentUser().getUserId();
			
			firstTransferApproved.setVerifyIp(ip);
			firstTransferApproved.setVerifyUser(userId);
			firstTransferApproved.setVerifyTime(new Date());
			firstTransferService.updateTransferApproved(firstTransferApproved);
						
			return MessageBox.build("0", "直通车初审成功");
			
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "直通车初审失败");
		}
	}
	
}