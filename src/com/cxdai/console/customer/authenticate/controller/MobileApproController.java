package com.cxdai.console.customer.authenticate.controller;

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
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.customer.information.entity.ApproModifyLog;
import com.cxdai.console.customer.information.service.MobileApproService;
import com.cxdai.console.customer.information.vo.MobileApproVo;
import com.cxdai.console.customer.information.vo.RealNameApproCnd;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;

@Controller
@RequestMapping(value = "/authenticte/mobileAppro")
public class MobileApproController extends BaseController {
	private static final Logger logger = Logger.getLogger(MobileApproController.class);

	@Autowired
	MobileApproService mobileApproService;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/customer/authenticate/mobileappromain");
	}

	/**
	 * <p>
	 * Description:查询实名认证列表<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年5月4日 void
	 */
	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView searchPageRealNameApproList(@ModelAttribute RealNameApproCnd realNameApproCnd, @PathVariable("pageNo") Integer pageNo) {
		try {
			Page page = mobileApproService.getNoPhoneCheck(realNameApproCnd, pageNo, 10);
			return forword("/customer/authenticate/mobileapprolist").addObject("page", page);
		} catch (Exception e) {

		}
		return null;
	}

	@RequestMapping("/approve")
	public @ResponseBody
	MessageBox approve(@RequestParam("userId") Integer userId, @RequestParam("check") String check) {
		String msg = "success";
		try {
			MobileApproVo m = new MobileApproVo();
			m.setUserId(userId);
			if ("pass".equals(check)) {
				m.setPassed(1);
				m.setApproTime(DateUtils.getCurrentTimeStamp());
				String result = mobileApproService.saveNoMobileCheck(m);
				if (!result.equals(BusinessConstants.SUCCESS)) {
					msg = result;
					return new MessageBox("0", msg);
				} else {
					return new MessageBox("1", msg);
				}

			} else {
				m.setPassed(-1);
				m.setMobileNum("99999999999");
				m.setRandCode("888888");
				m.setApproTime(DateUtils.getCurrentTimeStamp());
				m.setAddTime(DateUtils.getCurrentTimeStamp());
				m.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
				mobileApproService.nopassMobileCheck(m);
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
	 * Description:修改手机<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年10月18日 void
	 */
	@RequestMapping("/updateMobileCheck")
	public @ResponseBody
	MessageBox updateMobileCheck(@RequestParam("userId") Integer userId, @RequestParam("number") String number) {
		String msg;
		try {
			if (number == null && "".equals(number.trim())) {
				msg = "参数错误，请确认";
				return new MessageBox("0", msg);
			}
			MobileApproVo m = new MobileApproVo();
			m.setMobileNum(number.trim());
			m.setUserId(userId);
			ApproModifyLog apprModifyLog = new ApproModifyLog();
			apprModifyLog.setUserId(userId);
			apprModifyLog.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
			apprModifyLog.setAddtime(new Date());
			apprModifyLog.setStaffId(currentUser().getUserId());
			apprModifyLog.setAddMac("@@@@");
			apprModifyLog.setType(BusinessConstants.APPRO_MODIFY_LOG_TYPE_MOBILE);
			apprModifyLog.setNewContent(number.trim());
			String result = mobileApproService.updateMobileCheck(m, apprModifyLog);
			if (!result.equals(BusinessConstants.SUCCESS)) {
				return new MessageBox("0", result);
			} else {
				return new MessageBox("1", "修改手机成功");
			}
		} catch (Exception e) {
			msg = "修改手机出错";
			logger.error("修改邮箱出错", e);
			return new MessageBox("0", msg);
		}

	}

}
