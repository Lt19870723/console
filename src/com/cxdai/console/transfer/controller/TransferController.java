package com.cxdai.console.transfer.controller;

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

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.transfer.service.TransferService;
import com.cxdai.console.transfer.vo.TransferApprovedVo;
import com.cxdai.console.transfer.vo.TransferCnd;
import com.cxdai.console.transfer.vo.TransferVo;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.common.Constants;

/**
 * <p>
 * Description:债权转让 - 初审<br />
 * </p>
 * 
 * @title TransferController.java
 * @package com.cxdai.console.transfer.controller
 * @author tongjuxing
 * @version 0.1 2015年6月24日
 */
@Controller
@RequestMapping(value = "/transfer/transfer")
public class TransferController extends BaseController {
	private static final Logger logger = Logger.getLogger(TransferController.class);

	@Autowired
	private TransferService transferService;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/transfer/transfer/main");
	}

	
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute TransferCnd transferCnd, @PathVariable("pageNo") Integer pageNo) {
		transferCnd.setStatus(Constants.TRANSFER_STATU_WAIT.toString());
		Page page = transferService.queryPageListByCnd(transferCnd, pageNo, pageSize);
		return forword("/transfer/transfer/list").addObject("page", page);
	}
	
	@RequestMapping(value = "/status")
	public ModelAndView status(@RequestParam(value = "id", required = true) Integer id) {
		
		TransferVo transfer = new TransferVo();
		transfer.setId(id);
		
		return forword("/transfer/transfer/status").addObject("transfer", transfer);
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public MessageBox save(@ModelAttribute TransferVo transfer,@ModelAttribute TransferApprovedVo transferApprovedVo) {
		
		try {
			
			Integer status = transferApprovedVo.getStatus();
			if (status == null) {
				return new MessageBox("1", "审核状态错误");
			}

			if (!(status.intValue() == Constants.TRANSFER_FIRST_STATU_PASS || status.intValue() == Constants.TRANSFER_FIRST_STATU_NOPASS)) {
				return new MessageBox("1", "审核状态错误");
			}

			// 获取ip
			String ip = HttpTookit.getRealIpAddr(currentRequest());

			Integer userId = currentUser().getUserId();
			transferApprovedVo.setVerifyIp(ip);
			transferApprovedVo.setVerifyUser(userId);
			transferApprovedVo.setVerifyTime(new Date());
			transferApprovedVo.setTransferId(transfer.getId());

			if (status.intValue() == Constants.TRANSFER_FIRST_STATU_PASS) {
				transfer.setStatus(Constants.TRANSFER_STATU_ING);// 投标中
			}

			if (status.intValue() == Constants.TRANSFER_FIRST_STATU_NOPASS) {
				transfer.setStatus(Constants.TRANSFER_STATU_CHECK_FAIL);// 审核失败
			}

			transferService.updateStute(transferApprovedVo, transfer);
			return MessageBox.build("0", "初审成功");
			
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "初审失败");
		}
	}
	
}