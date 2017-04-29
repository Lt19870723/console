package com.cxdai.console.firstborrow.controller;

import org.apache.log4j.Logger;
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
import com.cxdai.console.firstborrow.service.FirstTenderRealService;
import com.cxdai.console.firstborrow.vo.FirstTenderRealCnd;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.HttpTookit;

/**
 * <p>
 * Description:直通车管理 - 提前解锁审核<br />
 * </p>
 * 
 * @title FirstUnLockController.java
 * @package com.cxdai.console.first.controller
 * @author tongjuxing
 * @version 0.1 2015年7月1日
 */
@Controller
@RequestMapping(value = "/firstborrow/firstunlock")
public class FirstUnLockController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(FirstUnLockController.class);
	
	@Autowired
	private FirstTenderRealService firstTenderRealService;

	private final int pageSize = 10;

	/** 认购记录id */
	private String id;
	/** 用户id */
	private String userId;
	
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/firstborrow/firstunlock/main");
	}

	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute FirstTenderRealCnd firstTenderRealCnd,
			@PathVariable("pageNo") Integer pageNo) {
		Page page = new Page();
		try {
			// 排序方法
			firstTenderRealCnd.setOrderSql(" ORDER BY tr.ID DESC");
			// 解锁中状态
			firstTenderRealCnd.setStatus(String
					.valueOf(Constants.TENDER_REAL_STATUS_UNLOCKING));
			page = firstTenderRealService.queryPageListByCnd(
					firstTenderRealCnd, pageNo, pageSize);
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/firstborrow/firstunlock/list").addObject("page", page);
	}
	
	/**
	 * <p>
	 * Description:跳转到审核窗口<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年7月1日
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/detail/{id}/{userId}")
	public ModelAndView toAddModify(@PathVariable("id") String id,
			@PathVariable("userId") String userId) {
		
		this.id = id;
		this.userId = userId;
		return forword("/firstborrow/firstunlock/detail");
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
	public MessageBox approvedPass(@ModelAttribute FirstBorrowAppr firstBorrowAppr) {
		try {
			ShiroUser user = currentUser();
			// 执行解锁通过方法
			String resultMsg = firstTenderRealService.saveApprovedPass(String.valueOf(firstBorrowAppr.getId()),
					firstBorrowAppr.getRemark(), firstBorrowAppr.getUserId(), user,
					HttpTookit.getRealIpAddr(currentRequest()));
			
			if ("success".equals(resultMsg)){
				return MessageBox.build("0", "直通车解锁审核通过成功！");
			}else{
				return MessageBox.build("1", resultMsg);
			}
			
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
	public MessageBox approvedReject(@ModelAttribute FirstBorrowAppr firstBorrowAppr) {
		try {
			ShiroUser user = currentUser();
			// 执行解锁拒绝方法
			String resultMsg = firstTenderRealService.saveApprovedReject(String.valueOf(firstBorrowAppr.getId()),
					firstBorrowAppr.getRemark(),firstBorrowAppr.getUserId(), user);
			
			if ("success".equals(resultMsg)){
				return MessageBox.build("0", "直通车解锁审核不通过成功！");
			}else{
				return MessageBox.build("1", resultMsg);
			}
				
		} catch (Exception e) {
			logger.error("解锁审核拒绝失败", e);
			return MessageBox.build("1", "系统异常，请稍候重试或联系管理员！");
		}
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}