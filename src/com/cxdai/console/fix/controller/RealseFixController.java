package com.cxdai.console.fix.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.mapper.FixBorrowMapper;
import com.cxdai.console.fix.service.FixBorrowService;
import com.cxdai.console.fix.vo.FixBorrowCnd;
import com.cxdai.console.fix.vo.FixBorrowVo;
import com.cxdai.console.fix.vo.FixOperationLogVo;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.MD5;
import com.cxdai.console.util.ShiroUtils;
import com.cxdai.console.util.exception.AppException;
/**
 * 
 * <p>
 * Description:发布定期宝
业务<br />
 * </p>
 * @title FixBorrowController.java
 * @package com.cxdai.console.fix.controller 
 * @author 于斌
 * @version 0.1 2015年7月14日
 */
@Controller
@RequestMapping(value = "/fix/realseFix")
public class RealseFixController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(RealseFixController.class);
	@Autowired
	private FixBorrowService fixBorrowService;
	@Autowired
	private FixBorrowMapper fixBorrowMapper;
	
	/**
	 * 
	 * <p>
	 * Description:进入发布定期宝业务<br />
	 * </p>
	 * @author 于斌
	 * @version 0.1 2015年7月14日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		return new ModelAndView("/fix/realseFix/main");
	}
	/**
	 * 
	 * <p>
	 * Description:跳转增加页面<br />
	 * </p>
	 * @author 于斌
	 * @version 0.1 2015年7月14日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/add")
	public ModelAndView add(){
		 Date lastEndTime=DateUtils.hoursOffset(new Date(), 1);
		return new ModelAndView("/fix/realseFix/layer").addObject("lastEndTime", lastEndTime);
	}
	/**
	    * 
	    * <p>
	    * Description:撤销定期宝<br />
	    * </p>
	    * @author 于斌
	    * @version 0.1 2015年7月14日
	    * @param borrowVo
	    * @return
	    * MessageBox
	    */
    @RequestMapping("/doDelete")
	@ResponseBody
	public MessageBox doDelete(@RequestParam(value = "id", required = false) Integer Id ) {
		String msg="";
		if (null != Id) {
			FixBorrowVo vo=fixBorrowMapper.getFixBorrowByCnd(Id);
			if(vo.getStatus().intValue()!=0){
				return new MessageBox("1", "此定期宝已处理，请刷新页面！");
			}			
		}
		try {
			FixBorrowVo fixBorrowVo = new FixBorrowVo();
			fixBorrowVo.setId(Id);
			fixBorrowVo.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
			fixBorrowVo.setStatus(Constants.FIX_BORROW_STATUS_DELETED);
			msg= fixBorrowService.updateFixBorrowStatus(fixBorrowVo);
		 if(msg.equals("success")){
			 return MessageBox.build("0", "删除成功");
		 }
		 return MessageBox.build("1", msg);
		} catch (Exception e) {
			stackTraceError(logger, e);
			e.printStackTrace();
			return MessageBox.build("1", "系统异常，请稍候重试或联系管理员"); 
		}
	}
    /**
	 * 
	 * <p>
	 * Description:查看详细页面<br />
	 * </p>
	 * @author 于斌
	 * @version 0.1 2015年7月14日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/detail")
	public ModelAndView detail(@RequestParam(value = "id", required = false) Integer Id ){
		FixBorrowVo fixBorrowVo=null;
		List<FixOperationLogVo>	fixBorrowApprList=null;
		   try {
				if (null != Id ) {
					FixBorrowCnd fixBorrowCnd = new FixBorrowCnd();
					fixBorrowCnd.setId(Id);
				    fixBorrowVo = fixBorrowService.queryFixBorrowByCnd(fixBorrowCnd);
					//通过后台存的有效时间，反推有效期限方式和数字
					if (fixBorrowVo.getValidTime() != null) {
						int validTime = fixBorrowVo.getValidTime().intValue();
						int a = 24 * 60;
						int b = 60;
						if ((validTime % a) == 0) {
							fixBorrowVo.setValidTimeStyle(1); // 按天
							fixBorrowVo.setValidTime(validTime / a);
						} else {
							if ((validTime % b) == 0) {
								fixBorrowVo.setValidTimeStyle(2); // 按小时
								fixBorrowVo.setValidTime(validTime / b);
							} else {
								fixBorrowVo.setValidTimeStyle(3); // 按分钟
							}
						}
					}
					fixBorrowApprList = fixBorrowService.queryfixBorrowApprList(Id);
				}
			} catch (Exception e) {
				logger.error("定期宝弹出详细界面查询报错", e);
			}
			return new ModelAndView("/fix/realseFix/show").addObject("fixBorrowVo",fixBorrowVo).addObject("fixBorrowApprList", fixBorrowApprList);				                                       
		}
	/**
	 * 
	 * <p>
	 * Description:进入修改页面<br />
	 * </p>
	 * @author 于斌
	 * @version 0.1 2015年7月14日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer Id ){
		FixBorrowVo fixBorrowVo=null;
		 Date lastEndTime=DateUtils.hoursOffset(new Date(), 1);
		   try {
				if (null != Id ) {
					FixBorrowCnd fixBorrowCnd = new FixBorrowCnd();
					fixBorrowCnd.setId(Id);
				    fixBorrowVo = fixBorrowService.queryFixBorrowByCnd(fixBorrowCnd);
					//通过后台存的有效时间，反推有效期限方式和数字
					if (fixBorrowVo.getValidTime() != null) {
						int validTime = fixBorrowVo.getValidTime().intValue();
						int a = 24 * 60;
						int b = 60;
						if ((validTime % a) == 0) {
							fixBorrowVo.setValidTimeStyle(1); // 按天
							fixBorrowVo.setValidTime(validTime / a);
						} else {
							if ((validTime % b) == 0) {
								fixBorrowVo.setValidTimeStyle(2); // 按小时
								fixBorrowVo.setValidTime(validTime / b);
							} else {
								fixBorrowVo.setValidTimeStyle(3); // 按分钟
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error("定期宝弹出详细界面查询报错", e);
			}
			return new ModelAndView("/fix/realseFix/edit").addObject("fixBorrowVo",fixBorrowVo).addObject("lastEndTime", lastEndTime);
		}
	/**
	 * 
	 * <p>
	 * Description:进入提交审核页面<br />
	 * </p>
	 * @author 于斌
	 * @version 0.1 2015年7月15日
	 * @param Id
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/approve")
	public ModelAndView approve(@RequestParam(value = "id", required = false) Integer Id ){
		FixBorrowVo fixBorrowVo=null;
		   try {
				if (null != Id ) {
					FixBorrowCnd fixBorrowCnd = new FixBorrowCnd();
					fixBorrowCnd.setId(Id);
				    fixBorrowVo = fixBorrowService.queryFixBorrowByCnd(fixBorrowCnd);
					//通过后台存的有效时间，反推有效期限方式和数字
					if (fixBorrowVo.getValidTime() != null) {
						int validTime = fixBorrowVo.getValidTime().intValue();
						int a = 24 * 60;
						int b = 60;
						if ((validTime % a) == 0) {
							fixBorrowVo.setValidTimeStyle(1); // 按天
							fixBorrowVo.setValidTime(validTime / a);
						} else {
							if ((validTime % b) == 0) {
								fixBorrowVo.setValidTimeStyle(2); // 按小时
								fixBorrowVo.setValidTime(validTime / b);
							} else {
								fixBorrowVo.setValidTimeStyle(3); // 按分钟
							}
						}
					}
				 	 
				}
			} catch (Exception e) {
				logger.error("定期宝弹出详细界面查询报错", e);
			}
			return new ModelAndView("/fix/realseFix/approve").addObject("fixBorrowVo",fixBorrowVo);				                                       
		}
 	/**
	 * 
	 * <p>
	 * Description:定期宝业务查询 <br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
    @RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageFixBorrowList(@ModelAttribute FixBorrowCnd fixBorrowCnd, @PathVariable Integer pageNo) {
    	Page page = null; 
     
    	try {
    		fixBorrowCnd.setOrderSql(" ORDER BY fixBorrow.ID DESC");
    		page = fixBorrowService.queryPageListByCnd(fixBorrowCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
    	} catch (Exception e) {
    		logger.error(e);
    	}
    	  return new ModelAndView("/fix/realseFix/list").addObject("page", page);
   }
   /**
    * 
    * <p>
    * Description:确认提交审核功能<br />
    * </p>
    * @author 于斌
    * @version 0.1 2015年7月15日
    * void
    */
    @RequestMapping("/submitApprove")
    @ResponseBody
    public MessageBox submitApprove(@ModelAttribute FixBorrowVo fixBorrowVo) {
		String resultMsg = null;
		try {
			ShiroUser shiroUser=ShiroUtils.currentUser();
			//如果定期宝对象不为空并且定期宝id不为空
			if (null != fixBorrowVo && null != fixBorrowVo.getId() && null != shiroUser) {
				FixBorrowVo vo=fixBorrowMapper.getFixBorrowByCnd(fixBorrowVo.getId());
				if(vo!=null && vo.getStatus()!=null && vo.getStatus().intValue()>=1){
					return new MessageBox("0", "此定期宝已提交审核！");
				}
				if(vo!=null && vo.getStatus()!=null && vo.getStatus().intValue()<0){
					return new MessageBox("0", "此定期宝已无效！");
				}
				FixBorrowVo fixBorrow = new FixBorrowVo();				
				fixBorrow.setId(fixBorrowVo.getId());
				// 待审核状态
				fixBorrow.setStatus(Constants.FIX_BORROW_STATUS_TO_APPROVE);
				fixBorrow.setBidPassword(vo.getBidPassword());
				fixBorrow.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
				// 执行更新方法
				resultMsg = fixBorrowService.updateFixBorrowStatus(fixBorrow);
			} else {
				resultMsg = "提交失败，请稍候重试或联系管理员！";
			}
			if(resultMsg.equals("success")){
				return new MessageBox("1", "操作成功");
			}
		} catch (Exception e) {
			logger.error("定期宝提交审核报错", e);
			resultMsg = "系统异常，请稍候重试或联系管理员！";
		}
		return new MessageBox("0", resultMsg);
	}
    /**
	 * 保存定期宝信息
	 * <p>
	 * Description:<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * void
	 */
    @RequestMapping("/saveFixBorrow")
    @ResponseBody
	public MessageBox saveFixBorrow(@ModelAttribute FixBorrowVo fixBorrowVo) {    
    	if (null != fixBorrowVo && null != fixBorrowVo.getId()) {
			FixBorrowVo vo=fixBorrowMapper.getFixBorrowByCnd(fixBorrowVo.getId());
			if(vo.getStatus().intValue()<0 || vo.getStatus().intValue()>3){				
				return new MessageBox("0","此定期宝已处理，请刷新页面！");
			}			
		}
		String resultMsg = null;		
		try {
			ShiroUser shiroUser=ShiroUtils.currentUser();
			//执行新增方法，反之则调用更新方法
			if (null != fixBorrowVo && null != fixBorrowVo.getId() && null != shiroUser) {
				// 执行更新方法
				fixBorrowVo.setPlanAccount(fixBorrowVo.getPlanAccount().multiply(new BigDecimal(10000)));
				fixBorrowVo.setLowestAccount(new BigDecimal(100));
				fixBorrowVo.setMostAccount(fixBorrowVo.getMostAccount().multiply(new BigDecimal(10000)));
				fixBorrowVo.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
				// 密码加密
				if (StringUtils.isNotEmpty(fixBorrowVo.getBidPassword())) {
					if (StringUtils.isNotEmpty(fixBorrowVo.getBidPasswordTemp())) {
						 if(fixBorrowVo.getBidPasswordTemp().equals(MD5.toMD5(fixBorrowVo.getBidPassword()))||fixBorrowVo.getBidPasswordTemp().equals(fixBorrowVo.getBidPassword())){
							 fixBorrowVo.setBidPassword(fixBorrowVo.getBidPasswordTemp());
						 }else{
							 fixBorrowVo.setBidPassword(MD5.toMD5(fixBorrowVo.getBidPassword()));
						 }
					}else{
						 fixBorrowVo.setBidPassword(MD5.toMD5(fixBorrowVo.getBidPassword()));
					}
				}else{
					fixBorrowVo.setCleanPassword("清空密码");
				}
				resultMsg = fixBorrowService.updateFixBorrow(fixBorrowVo);
			} else if (null != fixBorrowVo && null != shiroUser) {
				fixBorrowVo.setUserId(shiroUser.getUserId());
				// 将万元转换成具体的金额
				fixBorrowVo.setPlanAccount(fixBorrowVo.getPlanAccount().multiply(new BigDecimal(10000)));
				fixBorrowVo.setLowestAccount(new BigDecimal(100));
				fixBorrowVo.setMostAccount(fixBorrowVo.getMostAccount().multiply(new BigDecimal(10000)));
				fixBorrowVo.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
				// 密码加密
				if (fixBorrowVo.getBidPassword() != null && !"".equals(fixBorrowVo.getBidPassword())) {
					fixBorrowVo.setBidPassword(MD5.toMD5(fixBorrowVo.getBidPassword()));
				}else{
					fixBorrowVo.setCleanPassword("清空密码");
				}
				resultMsg = fixBorrowService.insertFixBorrow(fixBorrowVo);
			} else {
				resultMsg = "保存失败，请稍候重试或联系管理员！";
			}
			if(resultMsg.equals("success")){
				return new MessageBox("1", "操作成功");
			}
			
		} catch (AppException ae) {
			logger.error("定期宝保存报错", ae);
			resultMsg = ae.getMessage();
		} catch (Exception e) {
			logger.error("定期宝保存报错", e);
			resultMsg = "系统异常，请稍候重试或联系管理员！";
		}
		return new MessageBox("0",resultMsg);
	} 
    
    /**
     * 审核通过后再次修改
     * @author WangQianJin
     * @title @param Id
     * @title @return
     * @date 2015年11月3日
     */
	@RequestMapping("/toEdit")
	public ModelAndView toEdit(@RequestParam(value = "id", required = false) Integer Id ){
		FixBorrowVo fixBorrowVo=null;
		 Date lastEndTime=DateUtils.hoursOffset(new Date(), 1);
		   try {
				if (null != Id ) {
					FixBorrowCnd fixBorrowCnd = new FixBorrowCnd();
					fixBorrowCnd.setId(Id);
				    fixBorrowVo = fixBorrowService.queryFixBorrowByCnd(fixBorrowCnd);
					//通过后台存的有效时间，反推有效期限方式和数字
					if (fixBorrowVo.getValidTime() != null) {
						int validTime = fixBorrowVo.getValidTime().intValue();
						int a = 24 * 60;
						int b = 60;
						if ((validTime % a) == 0) {
							fixBorrowVo.setValidTimeStyle(1); // 按天
							fixBorrowVo.setValidTime(validTime / a);
						} else {
							if ((validTime % b) == 0) {
								fixBorrowVo.setValidTimeStyle(2); // 按小时
								fixBorrowVo.setValidTime(validTime / b);
							} else {
								fixBorrowVo.setValidTimeStyle(3); // 按分钟
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error("定期宝弹出详细界面查询报错", e);
			}
			return new ModelAndView("/fix/realseFix/toEdit").addObject("fixBorrowVo",fixBorrowVo).addObject("lastEndTime", lastEndTime);
	}
}
