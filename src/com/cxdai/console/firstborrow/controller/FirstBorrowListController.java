package com.cxdai.console.firstborrow.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.firstborrow.service.FirstBorrowApprService;
import com.cxdai.console.firstborrow.service.FirstBorrowService;
import com.cxdai.console.firstborrow.vo.FirstBorrowApprCnd;
import com.cxdai.console.firstborrow.vo.FirstBorrowApprVo;
import com.cxdai.console.firstborrow.vo.FirstBorrowCnd;
import com.cxdai.console.firstborrow.vo.FirstBorrowVo;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.exception.AppException;

/**
 * <p>
 * Description:直通车管理 - 直通车列表<br />
 * </p>
 * 
 * @title FirstBorrowController.java
 * @package com.cxdai.console.first.controller
 * @author tongjuxing
 * @version 0.1 2015年6月25日
 */
@Controller
@RequestMapping(value = "/firstborrow/firstborrowlist")
public class FirstBorrowListController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(FirstBorrowListController.class);

	@Autowired
	private FirstBorrowService firstBorrowService;
	
	@Autowired
	private FirstBorrowApprService firstBorrowApprService;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/firstborrow/firstborrowlist/main");
	}

	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute FirstBorrowCnd firstBorrowCnd,
			@PathVariable("pageNo") Integer pageNo) {
		Page page = new Page();
		try {
			firstBorrowCnd.setOrderSql(" ORDER BY f.ID DESC");
			page = firstBorrowService.queryPageListByCnd(firstBorrowCnd,
					pageNo, pageSize);
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/firstborrow/firstborrowlist/list").addObject("page", page);
	}

	/**
	 * <p>
	 * Description:验证添加方法<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年6月29日
	 * @param cardFile
	 * @param request
	 * @return AjaxJson
	 */
	@RequestMapping(value = "/validateadd")
	@ResponseBody
	public MessageBox validateAdd() {
		try {

			boolean result = firstBorrowService.validateAdd();
			// 如果有计划在处理中
			if (result) {
				return MessageBox.build("1", "当前已有一个投标直通车，请先处理！");
			} else {
				return MessageBox.build("0", "");
			}
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "系统异常，请稍候重试或联系管理员");
		}
	}

	/**
	 * <p>
	 * Description:显示添加<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年6月29日
	 * @param cardFile
	 * @param request
	 * @return AjaxJson
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add() {
		Date lastEndTime = null;
		FirstBorrowVo lastfirstBorrowVo = firstBorrowService.getLatest();
		if (lastfirstBorrowVo != null && lastfirstBorrowVo.getEndTime() != null) {
			lastEndTime = lastfirstBorrowVo.getEndTime();
			if (Integer.parseInt(DateUtils.format(
					lastfirstBorrowVo.getEndTime(), "mm")) != 0) {
				lastEndTime = DateUtils.hoursOffset(
						lastfirstBorrowVo.getEndTime(), 1);
			}
			if (lastEndTime.compareTo(new Date()) == -1) {
				lastEndTime = DateUtils.hoursOffset(new Date(), 1);
			}
		}

		FirstBorrowVo firstBorrowVo = new FirstBorrowVo();
		firstBorrowVo.setLowestAccount(1000);

		return forword("/firstborrow/firstborrowlist/add").addObject("firstBorrowVo",
				firstBorrowVo).addObject("lastEndTime", lastEndTime);

	}

	/**
	 * <p>
	 * Description:保存优先投标<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年6月30日
	 * @param request
	 * @param id
	 * @return MessageBox
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public MessageBox save(@ModelAttribute FirstBorrowVo firstBorrowVo) {
		try {
			if (null != firstBorrowVo.getId()) {
				// 执行更新方法
				firstBorrowVo.setPlanAccount(firstBorrowVo.getPlanAccount() * 10000);
				firstBorrowVo.setLowestAccount(1000);
				firstBorrowVo.setMostAccount(firstBorrowVo.getMostAccount() * 10000);
				// 清除密码
				if (null == firstBorrowVo.getPasswordSource() || "".equals(firstBorrowVo.getPasswordSource())) {
					firstBorrowVo.setCleanPassword("true");
				}
				String resultMsg = firstBorrowService.updateFirstBorrow(firstBorrowVo);
				
				if ("success".equals(resultMsg)){
					return MessageBox.build("0", "修改成功！");
				}else{
					return MessageBox.build("1", resultMsg);
				}
			} else {
				firstBorrowVo.setUserId(currentUser().getUserId());
				firstBorrowVo.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
				// 将万元转换成具体的金额
				firstBorrowVo.setPlanAccount(firstBorrowVo.getPlanAccount() * 10000);
				firstBorrowVo.setLowestAccount(1000);
				firstBorrowVo.setMostAccount(firstBorrowVo.getMostAccount() * 10000);
				String resultMsg = firstBorrowService.insertFirstBorrow(firstBorrowVo);
				if ("success".equals(resultMsg)){
					return MessageBox.build("0", "保存成功！");
				}else{
					return MessageBox.build("1", resultMsg);
				}
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
	 * Description:提交审核优先投标<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年7月1日
	 * @param request
	 * @param id
	 * @return MessageBox
	 */
	@RequestMapping(value = "/submitapprove")
	@ResponseBody
	public MessageBox submitApprove(@ModelAttribute FirstBorrowVo firstBorrowVo) {
		try {
			ShiroUser staff = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			if (null != firstBorrowVo && null != firstBorrowVo.getId() && null != staff) {
				FirstBorrowVo firstBorrow = new FirstBorrowVo();
				firstBorrow.setId(firstBorrowVo.getId());
				firstBorrow.setVersion(firstBorrowVo.getVersion());
				// 待审核状态
				firstBorrow.setStatus(Constants.FIRST_BORROW_STATUS_TO_APPROVE);
				// 执行更新方法
				String resultMsg = firstBorrowService.updateFirstBorrowStatus(firstBorrow);
				if ("success".equals(resultMsg)){
					return MessageBox.build("0", "提交审核成功！");
				}else{
					return MessageBox.build("1", resultMsg);
				}
			} else {
				return MessageBox.build("1", "提交失败，请稍候重试或联系管理员！");
			}
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "系统异常，请稍候重试或联系管理员！");
		}
	}
	
	/**
	 * <p>
	 * Description:删除优先投标<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年6月30日
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception Map<String,Object>
	 */
	@RequestMapping(value = "/delete/{id}/{version}")
	@ResponseBody
	public MessageBox delete(@PathVariable("id") Integer id,
			@PathVariable("version") String version) throws Exception {
		try {
			FirstBorrowVo firstBorrowVo = new FirstBorrowVo();
			firstBorrowVo.setId(id);
			firstBorrowVo.setVersion(version);
			firstBorrowVo.setStatus(Constants.FIRST_BORROW_STATUS_DELETED);
			String resultMsg = firstBorrowService.updateFirstBorrowStatus(firstBorrowVo);
			if ("success".equals(resultMsg)){
				return MessageBox.build("0", "删除成功！");
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
	 * Description:跳转到新增、修改、查看窗口<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年6月30日
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/detail/{id}/{operation}")
	public ModelAndView toAddModify(@PathVariable("id") Integer id,
			@PathVariable("operation") String operation) {
		try {
				FirstBorrowCnd firstBorrowCnd = new FirstBorrowCnd();
				firstBorrowCnd.setId(String.valueOf(id));
				FirstBorrowVo firstBorrowVo = firstBorrowService.queryFirstBorrowByCnd(firstBorrowCnd);
				if (firstBorrowVo.getValidTime() != null) {
					int validTime = firstBorrowVo.getValidTime().intValue();
					int a = 24 * 60;
					int b = 60;
					if ((validTime % a) == 0) {
						firstBorrowVo.setValidTimeStyle(1); // 按天
						firstBorrowVo.setValidTime(validTime / a);
					} else {
						if ((validTime % b) == 0) {
							firstBorrowVo.setValidTimeStyle(2); // 按小时
							firstBorrowVo.setValidTime(validTime / b);
						} else {
							firstBorrowVo.setValidTimeStyle(3); // 按分钟
						}
					}
				}
				FirstBorrowApprCnd firstBorrowApprCnd = new FirstBorrowApprCnd();
				firstBorrowApprCnd.setFirstBorrowId(String.valueOf(id));
				List<FirstBorrowApprVo> firstBorrowApprList = firstBorrowApprService.queryfirstBorrowApprList(firstBorrowApprCnd);

				Date lastEndTime = null;
				FirstBorrowVo lastfirstBorrowVo = firstBorrowService.getNewLatestById(id);
				if (lastfirstBorrowVo != null && lastfirstBorrowVo.getEndTime() != null) {
					lastEndTime = lastfirstBorrowVo.getEndTime();
					if (Integer.parseInt(DateUtils.format(lastfirstBorrowVo.getEndTime(), "mm")) != 0) {
						lastEndTime = DateUtils.hoursOffset(lastfirstBorrowVo.getEndTime(), 1);
					}
					if (lastEndTime.compareTo(new Date()) == -1) {
						lastEndTime = DateUtils.hoursOffset(new Date(), 1);
					}
				}
				
				return forword("/firstborrow/firstborrowlist/detail")
						.addObject("firstBorrowVo",firstBorrowVo)
						.addObject("firstBorrowApprList", firstBorrowApprList)
						.addObject("lastEndTime", lastEndTime)
						.addObject("operation", operation);

		} catch (Exception e) {
			logger.error(e);
		}
		
		return forword("/firstborrow/firstborrowlist/detail");
	}
	
	/**
	 * 跳转到审核通过后再次修改界面
	 * @author WangQianJin
	 * @title @param id
	 * @title @param operation
	 * @title @return
	 * @date 2015年11月2日
	 */
	@RequestMapping(value = "/update/{id}/{operation}")
	public ModelAndView toUpdate(@PathVariable("id") Integer id,
			@PathVariable("operation") String operation) {
		try {
				FirstBorrowCnd firstBorrowCnd = new FirstBorrowCnd();
				firstBorrowCnd.setId(String.valueOf(id));
				FirstBorrowVo firstBorrowVo = firstBorrowService.queryFirstBorrowByCnd(firstBorrowCnd);
				if (firstBorrowVo.getValidTime() != null) {
					int validTime = firstBorrowVo.getValidTime().intValue();
					int a = 24 * 60;
					int b = 60;
					if ((validTime % a) == 0) {
						firstBorrowVo.setValidTimeStyle(1); // 按天
						firstBorrowVo.setValidTime(validTime / a);
					} else {
						if ((validTime % b) == 0) {
							firstBorrowVo.setValidTimeStyle(2); // 按小时
							firstBorrowVo.setValidTime(validTime / b);
						} else {
							firstBorrowVo.setValidTimeStyle(3); // 按分钟
						}
					}
				}
				FirstBorrowApprCnd firstBorrowApprCnd = new FirstBorrowApprCnd();
				firstBorrowApprCnd.setFirstBorrowId(String.valueOf(id));
				List<FirstBorrowApprVo> firstBorrowApprList = firstBorrowApprService.queryfirstBorrowApprList(firstBorrowApprCnd);

				Date lastEndTime = null;
				FirstBorrowVo lastfirstBorrowVo = firstBorrowService.getNewLatestById(id);
				if (lastfirstBorrowVo != null && lastfirstBorrowVo.getEndTime() != null) {
					lastEndTime = lastfirstBorrowVo.getEndTime();
					if (Integer.parseInt(DateUtils.format(lastfirstBorrowVo.getEndTime(), "mm")) != 0) {
						lastEndTime = DateUtils.hoursOffset(lastfirstBorrowVo.getEndTime(), 1);
					}
					if (lastEndTime.compareTo(new Date()) == -1) {
						lastEndTime = DateUtils.hoursOffset(new Date(), 1);
					}
				}
				
				return forword("/firstborrow/firstborrowlist/update")
						.addObject("firstBorrowVo",firstBorrowVo)
						.addObject("firstBorrowApprList", firstBorrowApprList)
						.addObject("lastEndTime", lastEndTime)
						.addObject("operation", operation);

		} catch (Exception e) {
			logger.error(e);
		}
		
		return forword("/firstborrow/firstborrowlist/update");
	}

}