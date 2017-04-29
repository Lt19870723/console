package com.cxdai.console.firstborrow.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.base.entity.FirstBorrowAppr;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.firstborrow.service.FirstBorrowApprService;
import com.cxdai.console.firstborrow.service.FirstBorrowService;
import com.cxdai.console.firstborrow.vo.FirstBorrowCnd;
import com.cxdai.console.firstborrow.vo.FirstBorrowVo;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.exception.AppException;

/**
 * <p>
 * Description:直通车管理 - 直通车审核<br />
 * </p>
 * 
 * @title FirstBorrowController.java
 * @package com.cxdai.console.first.controller
 * @author tongjuxing
 * @version 0.1 2015年7月1日
 */
@Controller
@RequestMapping(value = "/firstborrow/firstborrowappr")
public class FirstBorrowApprController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(FirstBorrowApprController.class);

	@Autowired
	private FirstBorrowService firstBorrowService;
	
	@Autowired
	private FirstBorrowApprService firstBorrowApprService;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/firstborrow/firstborrowappr/main");
	}

	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute FirstBorrowCnd firstBorrowCnd,
			@PathVariable("pageNo") Integer pageNo) {
		Page page = new Page();
		try {
			// 排序方法
			firstBorrowCnd.setOrderSql(" ORDER BY f.ID DESC");
			// 待审核状态
			firstBorrowCnd.setStatus(String.valueOf(Constants.FIRST_BORROW_STATUS_TO_APPROVE));
			page = firstBorrowService.queryPageListByCnd(firstBorrowCnd, pageNo, pageSize);
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/firstborrow/firstborrowappr/list").addObject("page", page);
	}
	
	/**
	 * <p>
	 * Description:审核通过方法<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年3月3日
	 * @return
	 */
	@RequestMapping(value = "/approvedpass")
	@ResponseBody
	public MessageBox approvedPass(@ModelAttribute FirstBorrowVo firstBorrowVo,@ModelAttribute FirstBorrowAppr firstBorrowAppr) {
		try {
			ShiroUser staff = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			if (null != staff) {
				firstBorrowAppr.setFirstBorrowId(firstBorrowVo.getId());
				firstBorrowAppr.setUserId(staff.getUserId());

				firstBorrowVo.setPlanAccount(firstBorrowVo.getPlanAccount() * 10000);
				firstBorrowVo.setLowestAccount(1000);
				firstBorrowVo.setMostAccount(firstBorrowVo.getMostAccount() * 10000);
				// 执行更新方法
				String resultMsg = firstBorrowApprService.saveApprovedPass(firstBorrowVo, firstBorrowAppr, firstBorrowVo.getVersion());
				if ("success".equals(resultMsg)){
					return MessageBox.build("0", "直通车审核通过成功！");
				}else{
					return MessageBox.build("1", resultMsg);
				}
			} else {
				return MessageBox.build("1", "审核通过失败，请稍候重试或联系管理员！");
			}
		} catch (AppException ae) {
			return MessageBox.build("1", ae.getMessage());
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "系统异常，请稍候重试或联系管理员！");
		}
	}

	/**
	 * <p>
	 * Description:审核拒绝方法<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年3月3日
	 * @return
	 */
	@RequestMapping(value = "/approvedreject")
	@ResponseBody
	public MessageBox approvedReject(@ModelAttribute FirstBorrowVo firstBorrowVo,@ModelAttribute FirstBorrowAppr firstBorrowAppr) {
		try {
			ShiroUser staff = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			if (null != staff) {
				firstBorrowAppr.setFirstBorrowId(firstBorrowVo.getId());
				firstBorrowAppr.setUserId(staff.getUserId());

				firstBorrowVo.setPlanAccount(firstBorrowVo.getPlanAccount() * 10000);
				firstBorrowVo.setLowestAccount(1000);
				firstBorrowVo.setMostAccount(firstBorrowVo.getMostAccount() * 10000);
				// 执行更新方法
				String resultMsg = firstBorrowApprService.saveApprovedReject(firstBorrowVo, firstBorrowAppr, firstBorrowVo.getVersion());
				
				if ("success".equals(resultMsg)){
					return MessageBox.build("0", "直通车审核不通过成功！");
				}else{
					return MessageBox.build("1", resultMsg);
				}
			} else {
				return MessageBox.build("1", "审核通过失败，请稍候重试或联系管理员！");
			}
		} catch (AppException ae) {
			return MessageBox.build("1", ae.getMessage());
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "系统异常，请稍候重试或联系管理员！");
		}
	}

}