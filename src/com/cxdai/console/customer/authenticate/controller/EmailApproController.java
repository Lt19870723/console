package com.cxdai.console.customer.authenticate.controller;

import java.util.Date;
import java.util.UUID;

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
import com.cxdai.console.customer.information.service.MobileApproService;
import com.cxdai.console.customer.information.vo.EmailApproVo;
import com.cxdai.console.customer.information.vo.RealNameApproCnd;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;

@Controller
@RequestMapping(value = "/authenticte/emailAppro")
public class EmailApproController extends BaseController {
	private static final Logger logger = Logger.getLogger(EmailApproController.class);

	@Autowired
	MobileApproService mobileApproService;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/customer/authenticate/emailappromain");
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
	public ModelAndView searchPageEmailApproList(@ModelAttribute RealNameApproCnd realNameApproCnd, @PathVariable("pageNo") Integer pageNo) {
		try {
			Page page = mobileApproService.getNoEmailCheck(realNameApproCnd, pageNo, 10);
			return forword("/customer/authenticate/emailapprolist").addObject("page", page);
		} catch (Exception e) {

		}
		return null;
	}

	@RequestMapping("/approve")
	public @ResponseBody
	MessageBox approve(@RequestParam("userId") Integer userId, @RequestParam("check") String check) {
		String msg = "success";
		try {
			EmailApproVo m = new EmailApproVo();
			m.setUserId(userId);
			if ("pass".equals(check)) {
				m.setUserId(userId);
				m.setRANDUUID(UUID.randomUUID().toString());
				m.setCHECKED("1");
				m.setAPPRTIME(DateUtils.getCurrentTimeStamp());
				m.setAPPRIP(HttpTookit.getRealIpAddr(currentRequest()));
				String result = mobileApproService.saveNoEmailCheck(m);
				if (!result.equals(BusinessConstants.SUCCESS)) {
					msg = result;
					return new MessageBox("0", msg);
				} else {
					msg = "邮箱审核通过成功";
					return new MessageBox("1", msg);
				}
			} else {
				m.setUserId(userId);
				m.setRANDUUID(UUID.randomUUID().toString());
				m.setCHECKED("-1");
				m.setAPPRTIME(DateUtils.getCurrentTimeStamp());
				m.setAPPRIP(HttpTookit.getRealIpAddr(currentRequest()));
				mobileApproService.noPassEmailCheck(m);
				msg = "审核成功";
				return new MessageBox("1", msg);
			}
		} catch (Exception e) {
			logger.error("approve", e);
			return new MessageBox("0", e.getMessage());
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
			if (number == null && "".equals(number.trim())) {
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
			String result = mobileApproService.updateEmailCheck(m, apprModifyLog);
			if (!result.equals(BusinessConstants.SUCCESS)) {
				msg = result;
				return new MessageBox("0", msg);
			}
			msg = "修改邮箱成功";
			return new MessageBox("1", msg);
		} catch (Exception e) {
			msg = "修改邮箱出错";
			logger.error("修改邮箱出错", e);
			return new MessageBox("0", msg);
		}

	}

}
