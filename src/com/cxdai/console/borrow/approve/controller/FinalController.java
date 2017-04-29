package com.cxdai.console.borrow.approve.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.account.recharge.vo.AutoInvestConfigVo;
import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.borrow.approve.entity.InvestBorrow;
import com.cxdai.console.borrow.approve.entity.TenderBorrowCnd;
import com.cxdai.console.borrow.approve.service.BorrowService;
import com.cxdai.console.borrow.approve.service.CGUtilService;
import com.cxdai.console.borrow.approve.service.WsBorrowService;
import com.cxdai.console.borrow.approve.vo.BorrowApprovedVo;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.custody.xml.FBRes;
import com.cxdai.console.common.custody.xml.PTRRes;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.common.statics.CGBusinessConstants;
import com.cxdai.console.customer.information.entity.BorrowerUser;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.statistics.account.entity.Account;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.PropertiesUtil;
import com.cxdai.console.util.ShiroUtils;
import com.cxdai.console.util.WebServiceMD5;
import com.cxdai.console.util.serialnumberutil.UUIDGenerator;
/**
 * 
 * <p>
 * Description:借款标终审业务<br />
 * </p>
 * @title FinalController.java
 * @package com.cxdai.console.borrow.approve.controller 
 * @author yubin
 * @version 0.1 2015年6月26日
 */
@Controller
@RequestMapping(value = "/approve/final")
public class FinalController extends BaseController {
   
   private final static Logger logger=Logger.getLogger(FinalController.class);
   
   @Autowired
   private BorrowService borrowService;
   @Autowired
   private WsBorrowService wsBorrowService;
   @Autowired
   private CGUtilService cGUtilService;
   private String path=PropertiesUtil.getValue("portal_path");
 
   /**
    * 
    * <p>
    * Description:借款标终审主界面<br />
    * </p>
    * @author yubin
    * @version 0.1 2015年6月23日
    * @return
    * ModelAndView
    */
   @RequestMapping("/main")
	public ModelAndView finalRecordsMain() {
	 
		return new ModelAndView("/borrow/approve/final/main");
	}
   /**
    * 
    * <p>
    * Description:查询终审列表<br />
    * </p>
    * @author Administrator
    * @version 0.1 2015年6月24日
    * @param rechargeRecordCnd
    * @param pageNo
    * @return
    * ModelAndVie
    */
   @RequestMapping("/list/{pageNo}")
   public ModelAndView searchPagefinalList(@ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
   	Page page = null;
    
   	try {
   		page = borrowService.selectFinalCheckBorrow(borrowCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
   	} catch (Exception e) {
   		e.printStackTrace();
   		logger.error(e);
   	}
   	return new ModelAndView("/borrow/approve/final/list").addObject("page", page).addObject("portal_path", path);
   }
   /**
    * 
    * <p>
    * Description:借款标初审页面<br />
    * </p>
    * @author Administrator
    * @version 0.1 2015年6月25日
    * @param menuId
    * @return
    * ModelAndView
    */
    @RequestMapping(value = "/finalCheck")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) {
		ModelAndView mv = new ModelAndView("/borrow/approve/final/layer");
		BorrowVo borrowVo = null;
		List<BorrowerUser> borrowerUserList = null;
		if(id!=null){
			try {
				borrowVo = borrowService.selectByPrimaryKey(id);
				//查询权证人员账户号
				BorrowerUser borrowerUser=new BorrowerUser();
				borrowerUser.setStatus(1);//1：已启用
				borrowerUserList= borrowService.selectBorrowUser(borrowerUser);
			} catch (Exception e) {
			
				e.printStackTrace();
			} 
			
			mv.addObject("borrowId", id).addObject("borrowVo", borrowVo).addObject("borrowerUserList", borrowerUserList);
		}
		return mv;
	}
    /**
	 * 借款标终审审核结果
	 * @return
	 */
	@RequestMapping("/pass")
	@ResponseBody
	public MessageBox approvePass(@ModelAttribute BorrowApprovedVo approvedVo,@ModelAttribute BorrowVo vo,HttpServletRequest request){
		String msg = null;
		try {
			ShiroUser shiroUser = ShiroUtils.currentUser();
		
			String result = borrowService.updateBorrowAreaType(approvedVo.getBorrowId(),vo.getAreaType(),vo.getAreaChangeTime());
		    
			if (result.equals("success")) {
				// 验证数据是否正确
				Map<String, Object> validateKeyMap = new HashMap<>();
				validateKeyMap.put("borrowId", approvedVo.getBorrowId());
				validateKeyMap.put("flag", approvedVo.getFlag());
				validateKeyMap.put("userId",shiroUser.getUserId());
				validateKeyMap.put("remark", approvedVo.getRemark());
				validateKeyMap.put("addip", HttpTookit.getRealIpAddr(super.currentRequest()));
				
				if(vo.getIsCustody()==0 || vo.getIsCustody()==null){
					msg = wsBorrowService.saveApproveBorrowFinal(approvedVo.getBorrowId(), approvedVo.getFlag(), shiroUser.getUserId(),approvedVo.getRemark(),HttpTookit.getRealIpAddr(super.currentRequest()), WebServiceMD5.encodeParams(validateKeyMap));
				}
				//存管标
				else if(vo.getIsCustody()==1){
					String relateNo=UUIDGenerator.generate(CGBusinessConstants.RELATENO);
					String projectId= UUIDGenerator.getProjectId(CGBusinessConstants.BORROW_PIReq_ID,approvedVo.getBorrowId());
					MessageBox mb = wsBorrowService.saveBorrowPIReq(approvedVo.getBorrowId(), approvedVo.getFlag(), shiroUser,approvedVo.getRemark(),HttpTookit.getRealIpAddr(super.currentRequest()), WebServiceMD5.encodeParams(validateKeyMap),relateNo,projectId);
					//登记接口调用成功
					if(mb!=null){
						if(mb.getCode().equals("0")){
							vo.seteProjectId(projectId);
							msg =wsBorrowService.saveApproveCGBorrowFinal(approvedVo.getBorrowId(), shiroUser, approvedVo.getRemark(), HttpTookit.getRealIpAddr(super.currentRequest()),relateNo,mb.getMessage(),vo);
							BorrowVo borrowVo = borrowService.selectByPrimaryKey(approvedVo.getBorrowId());
							if (borrowVo.getAreaType() != null && borrowVo.getAreaType() == 0) { // 普通专区可以进行自动投标
								// 开始自动投标
								this.tenderCGBorrow(request, borrowVo);
							}
							
						}else{
							msg=mb.getMessage();
						}
					}else{msg="success";}
				
				}
				
			} else {
				msg=result;
			} 
			
			if(msg.equals(BusinessConstants.SUCCESS)) {
				return MessageBox.build("0", "操作成功");
			}
		 
			return MessageBox.build("-1", msg);
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "系统繁忙,请稍后重试或联系系统管理员！");
		}
	}
	/**
	 * 
	 * <p>
	 * Description:初始化定时发标<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年8月16日 void
	 */
	@RequestMapping("/initTimingBorrow")
	public  ModelAndView initTimingBorrow(@RequestParam(value = "id", required = false) Integer borrowId) {
		ModelAndView mv = new ModelAndView("/borrow/approve/final/set_timing_borrow");
		try {
		   BorrowVo	borrow = borrowService.selectByPrimaryKey(borrowId);
		   
			if (borrow.getTimingBorrowTime() != null && !borrow.getTimingBorrowTime().equals("")) {
				mv.addObject("timingBorrowTime", DateUtils.parse(DateUtils.timeStampToDate(borrow.getTimingBorrowTime(), DateUtils.YMD_HMS), DateUtils.YMD_HMS));
				}
			if (borrow.getAreaType() != null) {
				mv.addObject("areaType", borrow.getAreaType());
				mv.addObject("areaChangeTime", borrow.getAreaChangeTime()); 
			}
			
			
			    //查询权证人员账户号
				BorrowerUser borrowerUser=new BorrowerUser();
				borrowerUser.setStatus(1);//1：已启用
				List<BorrowerUser> borrowerUserList= borrowService.selectBorrowUser(borrowerUser);
			    mv.addObject("borrowVo",borrow).addObject("borrowerUserList", borrowerUserList);
			
			
			mv.addObject("borrowId",borrowId);
			 
		} catch (Exception e) {
			stackTraceError(logger, e);
		}
	    return mv;
	}
     /**
      * 
      * <p>
      * Description:初始化修改标<br />
      * </p>
      * @author 于斌
      * @version 0.1 2015年7月16日
      * @param borrowId
      * @return
      * ModelAndView
      */
	@RequestMapping("/initUpdateBorrowInfo")
	public  ModelAndView initUpdateBorrowInfo(@RequestParam(value = "id", required = false) Integer borrowId) {
		ModelAndView mv = new ModelAndView("/borrow/approve/final/edit");
		try {
		   BorrowVo	borrowVo  = borrowService.selectByPrimaryKey(borrowId);
		    //查询权证人员账户号
			BorrowerUser borrowerUser=new BorrowerUser();
			borrowerUser.setStatus(1);//1：已启用
			List<BorrowerUser> borrowerUserList= borrowService.selectBorrowUser(borrowerUser);
		    mv.addObject("borrowVo",borrowVo).addObject("borrowerUserList", borrowerUserList);
			 
		} catch (Exception e) {
			stackTraceError(logger, e);
		}
	    return mv;
	}
    /**
     * 
     * <p>
     * Description:更新表<br />
     * </p>
     * @author 于斌
     * @version 0.1 2015年7月16日
     * @param borrowVo
     * @return
     * MessageBox
     */
	@RequestMapping("/updateBorrowInfo")
	@ResponseBody
	public MessageBox updateBorrowInfo(@ModelAttribute BorrowVo borrowVo ) {
		String msg="";
		 
		try {
		 msg = borrowService.updateBorrowInfo(borrowVo);
		 if(msg.equals("success")){
			 return MessageBox.build("1", "修改标成功");
		 }
		 return MessageBox.build("0", msg);
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("0", "修改标失败！"); 
		}
	}
	/**
	 * 
	 * <p>
	 * Description:设定定时发标<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年8月16日 void
	 */
	@RequestMapping("/setTimingBorrow")
	@ResponseBody
	public MessageBox setTimingBorrow(@ModelAttribute BorrowVo borrowVo ) {
		String msg="";
		 
		try {
		 msg = borrowService.setTimingBorrow(DateUtils.parse(borrowVo.getTimingBorrowTime(),DateUtils.YMD_HMS)
					 , borrowVo.getId(), borrowVo.getAreaType(),borrowVo.getAreaChangeTime(),borrowVo);
		 if(msg.equals("success")){
			 return MessageBox.build("0", "定时发标成功");
		 }
		 return MessageBox.build("1", msg);
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "定时发标设置失败！"); 
		}
	}

	/**
	 * 
	 * <p>
	 * Description:删除定时发标<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年8月16日 void
	 */
	@RequestMapping("/delTimingBorrow")
	@ResponseBody
	public  MessageBox delTimingBorrow(@RequestParam(value = "id", required = false) Integer borrowId) {
		try {
			String msg = borrowService.delTimingBorrow(borrowId);
			return MessageBox.build("0", msg);  
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "删除定时发标失败！");  
		}
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:自动投存管标<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月23日
	 * @param request
	 * @param tenderBorrowCnd
	 * @return
	 * MessageBox
	 */
	public MessageBox tenderCGBorrow(HttpServletRequest request,BorrowVo bv) {
		MessageBox msg = null;
		try {
			ShiroUser shiroUser = currentUser();
			//筛选符合条件的用户
			List<AutoInvestConfigVo> autoInvestConfigVoList = wsBorrowService.findAutoInvestConfig(bv);
			for (AutoInvestConfigVo autoInvestConfigVo : autoInvestConfigVoList) {
				Integer index=1;
				shiroUser.setUserId(autoInvestConfigVo.getUser_id());
				//投资前校验
				
				
				String relateNo=UUIDGenerator.generate(CGBusinessConstants.RELATENO);
				String remark="自动投标余额查询";
				//调用存管余额查询接口
				String rep= cGUtilService.saveCGAccount(shiroUser, relateNo,remark,bv.getId());
				//解析报文
				Account account= cGUtilService.parseAQReqXml(rep, shiroUser, remark, relateNo,bv.getId());
				if(account==null){
					logger.error("返回ERROR报文或验签失败");
					continue;
				}
				//平台与存管资金校验
				String scene="自动投标";//业务发生场景
				account.setUserId(shiroUser.getUserId());
				cGUtilService.savecheckAccount(account, HttpTookit.getRealIpAddr(request), shiroUser.getPlatform(), scene);
			
				
				//查询该借款标的已投总额
				BigDecimal accountYes= wsBorrowService.queryTenderTotalByBorrowId(bv.getId());
				//计算投标金额
				BigDecimal investMoney=BigDecimal.ZERO;
				//按设置金额投标
				if(autoInvestConfigVo.getTender_type()==1){
					if((bv.getAccount().subtract(accountYes)).compareTo(autoInvestConfigVo.getTender_account_auto())!=-1 && autoInvestConfigVo.getTender_account_auto().compareTo(account.geteUseMoney())!=1){
						investMoney=autoInvestConfigVo.getTender_account_auto();
					}else{
						if(bv.getAccount().subtract(accountYes).compareTo(autoInvestConfigVo.getMin_tender_account())!=-1 && autoInvestConfigVo.getMin_tender_account().compareTo(account.geteUseMoney())!=1){
							investMoney=autoInvestConfigVo.getMin_tender_account();
						}else{
							if(autoInvestConfigVo.getBalance_not_enough()==1){
								investMoney=account.geteUseMoney();
							}
						}
					}
				//按账户余额
				}else if(autoInvestConfigVo.getTender_type()==3){
					if(bv.getAccount().subtract(accountYes).compareTo(account.geteUseMoney())!=-1){
						investMoney=account.geteUseMoney();
					}else{
						if(bv.getAccount().subtract(accountYes).compareTo(autoInvestConfigVo.getMin_tender_account())!=-1 && account.geteUseMoney().compareTo(autoInvestConfigVo.getMin_tender_account())!=-1){
							investMoney=bv.getAccount().subtract(accountYes);
						}else{
							if(autoInvestConfigVo.getBalance_not_enough()==1){
								investMoney=account.geteUseMoney();
							}
						}
					}
					
				}
				//确认最终投标金额
				BigDecimal tenderAccount=wsBorrowService.isEffectiveMoney(bv.getId(), shiroUser.getUserId(), investMoney,account.geteUseMoney());
				
				if(autoInvestConfigVo.getTender_type()==1 && tenderAccount.compareTo(autoInvestConfigVo.getTender_account_auto())==-1){
					tenderAccount=BigDecimal.ZERO;
				}
				
				if(tenderAccount.compareTo(new BigDecimal(0.01))!=-1 && tenderAccount.compareTo(autoInvestConfigVo.getMin_tender_account())!=-1){
				
				TenderBorrowCnd tenderBorrowCnd=new TenderBorrowCnd();
				tenderBorrowCnd.setBorrowid(bv.getId());
				tenderBorrowCnd.setTenderMoney(tenderAccount);
				
				//调用银行资金冻结接口
				String mode="8";//场景  资金冻结
				String relateNoFBReq=UUIDGenerator.generate(CGBusinessConstants.RELATENO);
				String remarkFBReq="自动投标资金冻结";
				String p2pDJ= UUIDGenerator.generate(CGBusinessConstants.P2P_SERIAL_DJ);
				String resXml= cGUtilService.saveFBReq(shiroUser, relateNoFBReq, remarkFBReq, tenderBorrowCnd, mode, p2pDJ);
				//解析报文
				FBRes fBRes = cGUtilService.parseFBResXml(resXml, shiroUser, remarkFBReq, relateNoFBReq, mode, tenderBorrowCnd);
				if(fBRes==null){
					logger.error("返回ERROE报文或验签失败");
					continue;
				}
				
				
				//添加投标记录
				String ms = null;
				try {
					ms= wsBorrowService.tenderCGBorrow(fBRes, tenderBorrowCnd, shiroUser, HttpTookit.getRealIpAddr(request),p2pDJ,investMoney,autoInvestConfigVo,index);
				} catch (Exception e) {
					logger.error(e);
					//调用资金解冻接口
					BorrowVo borrowVo = borrowService.selectByPrimaryKey(tenderBorrowCnd.getBorrowid());
					String modeUFBReq="10";//场景  资金解冻
					String relateNoUFBReq=UUIDGenerator.generate(CGBusinessConstants.RELATENO);
					String remarkUFBReq="投标资金冻结解冻";
					Integer investmentAmount=tenderBorrowCnd.getTenderMoney().multiply(new BigDecimal(100)).intValue();
					String repXml= wsBorrowService.saveUFBReq(borrowVo, fBRes.getSerialNo(), investmentAmount, shiroUser, remarkUFBReq, modeUFBReq, relateNoUFBReq);
					String uFBResMsg= cGUtilService.parseUFBResXml(repXml, shiroUser, remarkUFBReq, relateNoUFBReq, modeUFBReq, tenderBorrowCnd, fBRes.getSerialNo());
					if(!uFBResMsg.equals(BusinessConstants.SUCCESS)){
						logger.error("投标资金冻结解冻异常");
						continue;
					}
					continue;
					
				}
				
				//投标成功
				if(BusinessConstants.SUCCESS.equals(ms)){
					logger.error("用户："+shiroUser.getUserId()+",自动投标成功");
					//return MessageBox.build("1", "投标成功");
				//满标复审中	
				}else if(ms.equals("checkBorrow")){
					//满标复审校验
					BorrowVo borrowVo = borrowService.selectByPrimaryKey(tenderBorrowCnd.getBorrowid());
					if (!borrowVo.getStatus().equals(Constants.BORROW_STATUS_SUCCESS_CODE) || borrowVo.getAccount().compareTo(borrowVo.getAccountYes()) != 0) {
						logger.error("借款标异常,借款标状态不是满标复审中");
						return	MessageBox.build("0", "借款标异常,借款标状态不是满标复审中");
					}
					// 查询投标记录已投总金额
					BigDecimal tenderTotal = wsBorrowService.queryTenderTotalByBorrowId(bv.getId());
					if (null == tenderTotal || tenderTotal.compareTo(borrowVo.getAccount()) != 0) {
						logger.error("借款标异常,借款标金额与投标金额不相等");
						return	MessageBox.build("0", "借款标异常,借款标金额与投标金额不相等");
					}
					
					//调用银行项目登记接口，完善项目登记信息
					String modePIReq="6.2";//场景  6(项目基本信息登记)
					String relateNoPIReq=UUIDGenerator.generate(CGBusinessConstants.RELATENO);
					String remarkPIReq="项目第二次登记完善";
					String resXmlPIReq= cGUtilService.savePIReq(borrowVo, shiroUser, modePIReq, remarkPIReq, relateNoPIReq);
					//记录银行项目登记接口响应日志
					String m= cGUtilService.saveResXml(resXmlPIReq, modePIReq, shiroUser, remarkPIReq, relateNoPIReq,borrowVo.getId());
					if(!m.equals(BusinessConstants.SUCCESS)){
						logger.error(m);
						return MessageBox.build("0", "系统繁忙,请刷新页面或稍后重试！");
					}
					/**调用银行项目投资信息登记服务**/
					Integer pagetotal= cGUtilService.findTenderrecordInfoCount(tenderBorrowCnd.getBorrowid(), 200);
					for(int i=1;i<=pagetotal;i++){
						//根据借款标ID查询投标记录及投资人实名信息（分页查询，pageSize=200）
						Page page= cGUtilService.findTenderrecordInfo(tenderBorrowCnd.getBorrowid(), new Page(i, 200));
						//调用项目投资信息登记接口
						String modePTRReq="9";//场景  9项目投资信息登记
						String relateNoPTRReq=UUIDGenerator.generate(CGBusinessConstants.RELATENO);
						String remarkPTRReq="项目投资信息登记";
						List<InvestBorrow> list=(List<InvestBorrow>) page.getResult();
						String repXml= cGUtilService.savePTRReq(borrowVo, list, shiroUser, remarkPTRReq, modePTRReq, relateNoPTRReq);
						//记录项目投资信息登记响应日志,并解析
						PTRRes pTRRes=cGUtilService.parsePTRResXml(repXml, modePTRReq, shiroUser, remarkPTRReq, relateNoPTRReq,borrowVo.getId());
						if(pTRRes==null){
							logger.error("项目投资信息登记失败");
							return MessageBox.build("0", "系统繁忙,请刷新页面或稍后重试！");
						}
						
						cGUtilService.updateTenderrecord(borrowVo, pTRRes,list);
					}
						//将标的状态更新为银行复审中
						cGUtilService.updateBorrowStatus(borrowVo);
					logger.error("投标成功");
					break;
				
				//投标失败	
				}else{
					logger.error(ms);
					continue;
				}
				
		}
				index=index+1;
	}
			wsBorrowService.updateBorrowStatusById(bv.getId());
		
	} catch (Exception e) {
		try {
			wsBorrowService.updateBorrowStatusById(bv.getId());
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		logger.error("投标失败", e);
		return MessageBox.build("0", "系统繁忙,请刷新页面或稍后重试！");
	}
				
		
		return msg;
		
	} 
	
	
}
