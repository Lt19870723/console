package com.cxdai.console.fix.controller;

import java.math.BigDecimal;

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
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.MD5;
import com.cxdai.console.util.ShiroUtils;
import com.cxdai.console.util.exception.AppException;
/**
 * 
 * <p>
 * Description:定期宝审核业务<br />
 * </p>
 * @title FixBorrowController.java
 * @package com.cxdai.console.fix.controller 
 * @author 于斌
 * @version 0.1 2015年7月14日
 */
@Controller
@RequestMapping(value = "/fix/fixBorrowAppr")
public class FixBorrowApprController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(FixBorrowApprController.class);
    
	@Autowired
	private FixBorrowService fixBorrowService;
	@Autowired
	private FixBorrowMapper fixBorrowMapper;
	
	/**
	 * 
	 * <p>
	 * Description:定期宝审核业务<br />
	 * </p>
	 * @author 于斌
	 * @version 0.1 2015年7月14日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		return new ModelAndView("/fix/fixBorrowAppr/main");
	}
	/**
	 * 
	 * <p>
	 * Description:定期宝审核查询 <br />
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
		fixBorrowCnd.setStatus(String.valueOf(Constants.FIX_BORROW_STATUS_TO_APPROVE));
    	page = fixBorrowService.queryAppFixBorrowList(fixBorrowCnd, pageNo, Constants.CONSOLE_PAGE_SIZE); 
	} catch (Exception e) {
		
	   		logger.error(e);
	}
	  return new ModelAndView("/fix/fixBorrowAppr/list").addObject("page", page);
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
			return new ModelAndView("/fix/fixBorrowAppr/approve").addObject("fixBorrowVo",fixBorrowVo);				                                       
		}
	/**
	 * 定期宝审核通过
	 * <p>
	 * Description:<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * void
	 */
	@RequestMapping("/approvedPass")
	@ResponseBody
	public MessageBox approvedPass(@ModelAttribute FixBorrowVo fixBorrowVo) {
		if (fixBorrowVo.getId()!=null) {
			FixBorrowVo vo=fixBorrowMapper.getFixBorrowByCnd(fixBorrowVo.getId());
			if(vo.getStatus().intValue()!=1){
				return new MessageBox("0", "此定期宝已处理，请刷新页面！");
			}	
		}
		String resultMsg = null;
		try {
			ShiroUser staff=ShiroUtils.currentUser();
			//如果定期宝id不为空且员工对象不为空
			if (fixBorrowVo.getId()!=null  && null != staff) {
				fixBorrowVo.setPlanAccount(fixBorrowVo.getPlanAccount().multiply(new BigDecimal(10000)));
				fixBorrowVo.setLowestAccount(new BigDecimal(100));
				fixBorrowVo.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
				fixBorrowVo.setMostAccount(fixBorrowVo.getMostAccount().multiply(new BigDecimal(10000)));
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
				// 执行更新方法
				resultMsg = fixBorrowService.saveApprovedPass(fixBorrowVo);
				if(resultMsg.equals("success")){
					return new MessageBox("1","操作成功");
				}
				 
			} else {
				resultMsg = "审核通过失败，请稍候重试或联系管理员！";
			}
		} catch (AppException ae) {
			logger.error("定期宝审核报错", ae);
			resultMsg = ae.getMessage();
		} catch (Exception e) {
			logger.error("定期宝审核报错", e);
			e.printStackTrace();
			resultMsg = "系统异常，请稍候重试或联系管理员！";
		}
		return new MessageBox("0",resultMsg);
	}

	/**
	 * 定期宝审核拒绝
	 * <p>
	 * Description:<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * void
	 */
	@RequestMapping("/approvedReject")
	@ResponseBody
	public MessageBox approvedReject(@ModelAttribute FixBorrowVo fixBorrowVo) {
		if (fixBorrowVo.getId()!=null) {
			FixBorrowVo vo=fixBorrowMapper.getFixBorrowByCnd(fixBorrowVo.getId());
			if(vo.getStatus().intValue()!=1){
				return new MessageBox("0", "此定期宝已处理，请刷新页面！");
			}		
		}
		String resultMsg = null;
		try {
			ShiroUser staff=ShiroUtils.currentUser();
			//如果定期宝id不为空且员工对象不为空
			if (fixBorrowVo.getId()!=null  && null != staff) {
				fixBorrowVo.setPlanAccount(fixBorrowVo.getPlanAccount().multiply(new BigDecimal(10000)));
				fixBorrowVo.setLowestAccount(new BigDecimal(100));
				fixBorrowVo.setMostAccount(fixBorrowVo.getMostAccount().multiply(new BigDecimal(10000)));
				fixBorrowVo.setAddIp(HttpTookit.getRealIpAddr(currentRequest()));
				 
				// 执行更新方法
				resultMsg = fixBorrowService.saveApprovedReject(fixBorrowVo);
				if(resultMsg.equals("success")){
					return new MessageBox("1","操作成功");
				}
			} else {
				resultMsg = "审核通过失败，请稍候重试或联系管理员！";
			}
		} catch (AppException ae) {
			logger.error("定期宝取消审核报错", ae);
			resultMsg = ae.getMessage();
		} catch (Exception e) {
			logger.error("定期宝取消审核报错", e);
			resultMsg = "系统异常，请稍候重试或联系管理员！";
		}
		return new MessageBox("0",resultMsg);
	}
}
