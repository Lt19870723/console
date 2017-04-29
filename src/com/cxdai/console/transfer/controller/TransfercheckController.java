package com.cxdai.console.transfer.controller;

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
import com.cxdai.console.transfer.vo.TransferCnd;
import com.cxdai.console.transfer.vo.TransferVo;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.common.Constants;

/**
 * <p>
 * Description:债权转让 - 复审<br />
 * </p>
 * 
 * @title TransfercheckController.java
 * @package com.cxdai.console.transfer.controller
 * @author tongjuxing
 * @version 0.1 2015年6月26日
 */
@Controller
@RequestMapping(value = "/transfer/transfercheck")
public class TransfercheckController extends BaseController {
	private static final Logger logger = Logger.getLogger(TransfercheckController.class);

	@Autowired
	private TransferService transferService;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/transfer/transfercheck/main");
	}

	
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute TransferCnd transferCnd, @PathVariable("pageNo") Integer pageNo) {
		transferCnd.setBorrowStatus(Constants.TRANSFER_FULL_RECHECK.toString());
		Page page = transferService.queryPageTransferCheckListByCnd(transferCnd, pageNo, pageSize);
		return forword("/transfer/transfercheck/list").addObject("page", page);
	}
	
	@RequestMapping(value = "/status")
	public ModelAndView status(@RequestParam(value = "id", required = true) Integer id) {
		
		TransferVo transfer = new TransferVo();
		transfer.setId(id);
		
		return forword("/transfer/transfercheck/status").addObject("transfer", transfer);
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public MessageBox save(@ModelAttribute TransferVo transfer) {
		
		try {
			// 获取ip
			String ip = HttpTookit.getRealIpAddr(currentRequest());
			
			Integer userId = currentUser().getUserId();
			Integer id = transfer.getId();
			String remark = transfer.getRemark();
			String result = transferService.saveApproveTransferRecheck(id, userId, remark, ip, 1);

			if ("success".equals(result)) {
				return MessageBox.build("0", "债权转让复审成功！");
			} else {
				return new MessageBox("1", result);
			}
			
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "债权转让复审失败！");
		}
	}
	
}