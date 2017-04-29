package com.cxdai.console.customer.borrow.controller;

import java.util.Date;

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

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.customer.information.entity.ApproModifyLog;
import com.cxdai.console.customer.information.service.RealNameApproService;
import com.cxdai.console.customer.information.vo.EmailApproVo;
import com.cxdai.console.customer.information.vo.MemberAuditVo;
import com.cxdai.console.customer.information.vo.MemberCnd;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.HttpTookit;

/**
 * <p>
 * Description:客户管理 - 借款用户审核<br />
 * </p>
 * 
 * @title BorrowAuditController.java
 * @package com.cxdai.console.customer.borrow.controller
 * @author hujianpan
 * @version 0.1 2015年3月16日
 */
@Controller
@RequestMapping(value = "/borrow/borrowaudit")
public class BorrowAuditController extends BaseController {
	private static final Logger logger = Logger.getLogger(BorrowAuditController.class);

	@Autowired
	RealNameApproService realNameApproService;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/customer/contact/borrowmembermain");
	}

	/**
	 * <p>
	 * Description:查询邮箱列表<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年5月4日 void
	 */
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView searchPageEmailApproList(@ModelAttribute MemberCnd memberCnd, @PathVariable("pageNo") Integer pageNo) {
		try {
			Page page = realNameApproService.queryMemberPageListByCnd(memberCnd, pageNo, 10);
			return forword("/customer/contact/borrowmemberlist").addObject("page", page);
		} catch (Exception e) {

		}
		return null;
	}

	@RequestMapping("/approve")
	public @ResponseBody
	MessageBox approve(@RequestParam("ids") String ids, @RequestParam("check") String check) {
		String resultMsg = "success";

		MemberAuditVo memberAuditVo = new MemberAuditVo();
		resultMsg = "success";
		String[] idArry = null;
		if (!StringUtils.isEmpty(ids)) {
			idArry = ids.split(",");
		}
		if (null == idArry) {
			resultMsg = "请选择要审核的用户";
			return new MessageBox("0", resultMsg);
		}

		try {
			UserVo userVo = new UserVo();
			userVo.setId(currentUser().getUserId());
			userVo.setUserName(currentUser().getUserName());
			for (String id : idArry) {

				if ("pass".equals(check)) {
					memberAuditVo.setId(Integer.valueOf(id));
					resultMsg = realNameApproService.saveMemberAuditPass(memberAuditVo, userVo, currentRequest());
				} else {
					memberAuditVo.setId(Integer.valueOf(id));
					resultMsg = realNameApproService.saveMemberAuditUnPass(memberAuditVo, userVo, currentRequest());
				}

			}

			return new MessageBox("1", "审核成功");
		} catch (NumberFormatException e) {

			return new MessageBox("0", "审核失败");
		} catch (Exception e) {
			return new MessageBox("0", "审核失败");
		}

	}

	/**
	 * <p>
	 * Description:修改Email<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年10月18日 void
	 */
	@RequestMapping("/updateEmailCheck")
	public @ResponseBody
	MessageBox updateEmailCheck(@RequestParam("userId") Integer userId, @RequestParam("number") String number) {
		String msg;
		try {
			if (number == null || "".equals(number.trim())) {
				msg = "参数错误，请确认";
				return new MessageBox("0", msg);
			}
			EmailApproVo m = new EmailApproVo();
			m.setEMAIL(number.trim());
			m.setUserId(userId);
			ApproModifyLog apprModifyLog = new ApproModifyLog();
			apprModifyLog.setUserId(userId);
			apprModifyLog.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
			apprModifyLog.setAddtime(new Date());
			apprModifyLog.setStaffId(currentUser().getUserId());
			apprModifyLog.setAddMac("@@@@");
			apprModifyLog.setType(BusinessConstants.APPRO_MODIFY_LOG_TYPE_EMAIL);
			apprModifyLog.setNewContent(number.trim());
			msg = "修改邮箱成功";
			return new MessageBox("1", msg);
		} catch (Exception e) {
			msg = "修改邮箱出错";
			logger.error("修改邮箱出错", e);
			return new MessageBox("0", msg);
		}

	}

}
