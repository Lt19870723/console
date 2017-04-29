package com.cxdai.console.transfer.controller;

import org.apache.commons.lang3.StringUtils;
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
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.transfer.service.TransferService;
import com.cxdai.console.transfer.vo.TransferListCnd;
import com.cxdai.console.transfer.vo.TransferVo;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.common.Constants;

/**
 * <p>
 * Description:债权转让 - 转让中债权<br />
 * </p>
 * 
 * @title TransferingController.java
 * @package com.cxdai.console.transfer.controller
 * @author tongjuxing
 * @version 0.1 2015年6月24日
 */
@Controller
@RequestMapping(value = "/transfer/transfering")
public class TransferingController extends BaseController {
	private static final Logger logger = Logger.getLogger(TransferingController.class);

	@Autowired
	private TransferService transferService;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/transfer/transfering/main");
	}

	
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute TransferListCnd transferListCnd, @PathVariable("pageNo") Integer pageNo) {
        transferListCnd.setTransferType(Constants.TRANSFER_STATU_ING);
		Page page = transferService.queryPageTransferListByCnd(transferListCnd, pageNo, pageSize);
		return forword("/transfer/transfering/list").addObject("page", page);
	}
	
	@RequestMapping(value = "/cancel")
	public ModelAndView cancel(@RequestParam(value = "id", required = true) Integer id) {
		
		TransferVo transfer = new TransferVo();
		transfer.setId(id);
		
		return forword("/transfer/transfering/cancel").addObject("transfer", transfer);
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public MessageBox save(@ModelAttribute TransferVo transfer) {
		if (StringUtils.isEmpty(transfer.getCancelRemark())) {
			return new MessageBox("1", "取消备注不能为空");
		}
		
		try {

			// 获取ip
			String ip = HttpTookit.getRealIpAddr(currentRequest());

			Integer userId = currentUser().getUserId();
			transfer.setCancelUser(userId);
			transfer.setCancelIp(ip);
			transfer.setStatus(Constants.TRANSFER_CANCEL);
			transferService.updateStuteForCancelTransfer(transfer);

			if ("0001".equals(transfer.getRemark())) {
				return MessageBox.build("0", "取消转让成功");
			} else {
				logger.error("后台取消转让：userid=" + userId + ",transferid=" + transfer.getId());
				return MessageBox.build("1", "取消转让失败");
			}
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "取消转让失败");
		}
	}
	
}