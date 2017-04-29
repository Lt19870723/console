package com.cxdai.console.customer.authenticate.controller;

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
import com.cxdai.console.customer.information.service.BankApproService;
import com.cxdai.console.customer.information.vo.BankApproCnd;
import com.cxdai.console.customer.information.vo.BankApproVo;
import com.cxdai.console.system.vo.UserVo;

@Controller
@RequestMapping(value = "/authenticte/bankappro")
public class BankApproController extends BaseController {
	private static final Logger logger = Logger.getLogger(BankApproController.class);
	@Autowired
	BankApproService bankApproService;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/customer/authenticate/bankappromain");
	}

	/**
	 * <p>
	 * Description:查询实名认证列表<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年5月4日 void
	 */
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView searchPageBankApproList(@ModelAttribute BankApproCnd bankApproCnd, @PathVariable("pageNo") Integer pageNo) {
		try {
			Page page = bankApproService.queryPageListByCnd(bankApproCnd, pageNo, 10);
			return forword("/customer/authenticate/bankapprolist").addObject("page", page);
		} catch (Exception e) {

		}
		return null;
	}

	@RequestMapping("/approve")
	public @ResponseBody
	MessageBox approve(@ModelAttribute BankApproVo bankApproVo, @RequestParam("check") String check) {
		String resultMsg = "success";
		try {
			UserVo user = new UserVo();
			user.setId(currentUser().getUserId());
			user.setUserName(currentUser().getUserName());
			if ("pass".equals(check)) {
				resultMsg = bankApproService.saveApprovePass(bankApproVo, user);
			} else {
				resultMsg = bankApproService.saveApproveReject(bankApproVo, user);
			}
		} catch (Exception e) {
			logger.error("approve", e);
			return new MessageBox("0", e.getMessage());
		}

		return new MessageBox("1", resultMsg);

	}

}
