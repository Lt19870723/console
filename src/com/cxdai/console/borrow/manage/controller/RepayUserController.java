package com.cxdai.console.borrow.manage.controller;

import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.cxdai.console.borrow.manage.service.*;
import com.cxdai.console.common.page.PageListModel;
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
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.borrow.manage.vo.BorrowUserRechangeVo;
import com.cxdai.console.borrow.manage.vo.EFundRepayMent;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.common.statics.CGBusinessConstants;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.security.custody.KeyReader;
import com.cxdai.console.security.custody.SignManagerImpl;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.util.CacheService;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.PropertiesUtil;
import com.cxdai.console.util.ShiroUtils;
import com.cxdai.console.util.custody.XML;
import com.cxdai.console.util.custody.https.HttpClientMessageSender;
import com.cxdai.console.util.serialnumberutil.UUIDGenerator;

/**
 * 
 * <p>
 * Description:借款者还款业务<br />
 * </p>
 * 
 * @title RepayUserController.java
 * @package com.cxdai.console.borrow.manage.controller
 * @author yubin
 * @version 0.1 2015年7月3日
 */
@Controller
@RequestMapping(value = "/borrow/manage/repayUser")
public class RepayUserController extends BaseController {

	private final static Logger logger = Logger.getLogger(RepayUserController.class);
	@Autowired
	private BRepaymentRecordService bRepaymentRecordService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BorrowManageService borrowManageService;
	@Autowired
	private BorrowUserRechangeAndReplayService borrowUserRechangeAndReplayService;
	@Autowired
	private BorrowMapper borrowMapper;
	@Autowired
	private EFundRepayMentService eFundRepayMentService;
	@Autowired
	private CGBorrowManageService gGBorrowManageService;


	private String path = PropertiesUtil.getValue("portal_path");

	/**
	 * 
	 * <p>
	 * Description:进入借款标罚息列表<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main() {

		return new ModelAndView("/borrow/manage/repayUser/main");
	}

	/**
	 * 
	 * <p>
	 * Description:分页查询还款中的借款标集合<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param pageNo
	 * @return ModelAndView
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView selectRepayingBorrow(
			@ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
		BigDecimal useMoney = null, sumRepayAccount = null;
		Page page = null;
		try {
			Integer memberId = memberService.getMemberIdByUserName(borrowCnd
					.getUserName().trim());
			if (memberId != null) {
				AccountVo accountVo = accountService
						.queryAccountByUserId(memberId);
				useMoney = accountVo.getUseMoney();
			} else {
				useMoney = null;
			}
			borrowCnd.setIsOfficial("1");
			borrowCnd.setStatus("");
			borrowCnd.setCgWebstatus("");//不包含存管垫付
			page = bRepaymentRecordService.selectRepayingBorrow(borrowCnd,
					pageNo, Constants.CONSOLE_PAGE_SIZE);

			List<BRepaymentRecordVo> bRepaymentRecordVos = (List<BRepaymentRecordVo>) page
					.getResult();

			if (!bRepaymentRecordVos.isEmpty()) {
				// 获取借款者当期的还款总和
				// repaymentAccount =
				// bRepaymentRecordVos.get(0).getRepaymentAccount();
				for (Iterator<BRepaymentRecordVo> iterator = bRepaymentRecordVos
						.iterator(); iterator.hasNext();) {
					BRepaymentRecordVo bRepaymentRecordVo = iterator.next();
					Date repaymentTimeDate = new Date(
							Long.parseLong(bRepaymentRecordVo
									.getRepaymentTime()) * 1000);
					repaymentTimeDate = DateUtils.parse(DateUtils.format(
							repaymentTimeDate, DateUtils.YMD_DASH),
							DateUtils.YMD_DASH);
					Date now = DateUtils.parse(
							DateUtils.format(new Date(), DateUtils.YMD_DASH),
							DateUtils.YMD_DASH);

					int earlyDays = DateUtils.dayDiff(repaymentTimeDate, now);
					bRepaymentRecordVo.setEaryDay(earlyDays);
					// 逾期天数
					int lateDays = DateUtils.dayDiff(now, repaymentTimeDate);
					// 罚息金额
					BigDecimal lateDayInterest = BigDecimal.ZERO;
					if (lateDays > 0) {
						lateDayInterest = bRepaymentRecordVo
								.getRepaymentAccount()
								.multiply(
										new BigDecimal(
												BusinessConstants.OUT_OF_DAYE_RATE))
								.multiply(new BigDecimal(lateDays))
								.setScale(2, RoundingMode.UP);
					}
					// 应还总金额 = 还款金额 + 罚息金额
					BigDecimal totalPayMoney = bRepaymentRecordVo
							.getRepaymentAccount().setScale(2, RoundingMode.UP)
							.add(lateDayInterest);
					// 借款者帐号
					AccountVo accoutVo = accountService.queryAccountByUserId(bRepaymentRecordVo.getUserId());

					if(bRepaymentRecordVo.getIsCustody() !=null && bRepaymentRecordVo.getIsCustody().equals("1")){
						if (totalPayMoney.compareTo(accoutVo.geteUseMoney()) == 1) {
							bRepaymentRecordVo.setNeedRechargeAccount(totalPayMoney.subtract(accoutVo.geteUseMoney())
									.setScale(2,RoundingMode.UP));
						} else {
							bRepaymentRecordVo.setNeedRechargeAccount(BigDecimal.ZERO);
						}

					}else{
						if (totalPayMoney.compareTo(accoutVo.getUseMoney()) == 1) {
							bRepaymentRecordVo.setNeedRechargeAccount(totalPayMoney.subtract(accoutVo.getUseMoney())
									.setScale(2,RoundingMode.UP));
						} else {
							bRepaymentRecordVo.setNeedRechargeAccount(BigDecimal.ZERO);
						}
					}

				}

			}
			sumRepayAccount = bRepaymentRecordService.sumWaitRepayMoney(borrowCnd);
		} catch (Exception e) {
			logger.error(e);
		}
		return new ModelAndView("/borrow/manage/repayUser/list")
				.addObject("page", page).addObject("portal_path", path)
				.addObject("sumRepayAccount", sumRepayAccount)
				.addObject("useMoney", useMoney);
	}

	/**
	 * 
	 * <p>
	 * Description:借款者还款<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年6月29日
	 * @return MessageBox
	 */
	@RequestMapping("/doReplay")
	@ResponseBody
	public MessageBox doReplay(@RequestParam(value = "name", required = false) String borrowUserName,
			@RequestParam(value = "id", required = false) Integer repaymentid) {
		String message = null;
		try {

			BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordService.queryRepaymentByIdForUpdate(repaymentid);
			BorrowVo borrow = borrowMapper.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());

			// 如果是存管标，走银行存管本息还款流程
			if (borrow.getIsCustody() != null && borrow.getIsCustody().intValue() == 1
					&& bRepaymentRecordVo.getIsCustody() !=null && bRepaymentRecordVo.getIsCustody().intValue() == 1) {
			   // return MessageBox.build("0","存管标还款功能处于关闭状态");
				return doCustodyReplay(borrowUserName, repaymentid);
			} else {
				return doSelfReplay(borrowUserName, repaymentid);
			}
		}catch (Exception e) {
			message = "还款失败，请刷新页面后重试！";
			return MessageBox.build("0", message);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:借款者充值接口<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年7月3日
	 * @return MessageBox
	 */
	@RequestMapping("/doBorrowUserRechange")
	@ResponseBody
	public MessageBox doReplay(
			@ModelAttribute BorrowUserRechangeVo borrowUserRechangeVo) {
		String message = null;
		try {
			String userName = borrowUserRechangeVo.getUserName();

			if (StringUtils.isEmpty(userName)) {
				message = "用户名不存在";

			}
			Integer memberId = memberService.getMemberIdByUserName(userName);
			if (memberId == null) {
				message = "用户名不存在";

			}
			MemberVo memberVo = memberService.queryMemberById(memberId);

			if (memberVo == null) {
				message = "用户名不存在";

			}
			if (!memberVo.getUsername().equals(userName)) {
				message = "用户名不存在";

			}
			if (memberVo.getIsFinancialUser() == 1) {
				message = "【" + userName + "】是理财用户，无法充值！";

			}

			String accountType="0";

//			String accountType =  borrowUserRechangeVo.getAccountType();
//
//			if (accountType == null || accountType.equals("")) {
//				message = "账户类型不能为空";
//			}
//
//			if(accountType != null && accountType.equals("1")){
//					if (memberVo.getIsCustody() == null ||
//							memberVo.getIsCustody().intValue() != 1) {
//						message = "【" + userName + "】未开通存管账户，无法进行存管充值！";
//					}
//			}

			BigDecimal reChangeMoney = borrowUserRechangeVo.getReChangeMoney();
			if (reChangeMoney == null) {
				message = "请输入充值金额";

			}
			if (reChangeMoney.compareTo(new BigDecimal(0)) == 0) {
				message = "输入金额有误";

			}
			if (message != null) {
				return MessageBox.build("0", message);
			}
			// 获取ip
			ShiroUser shiroUser = ShiroUtils.currentUser();
			String ip = HttpTookit.getRealIpAddr(currentRequest());
			message = borrowUserRechangeAndReplayService.savepay(memberId,
					borrowUserRechangeVo, ip, shiroUser,accountType);
			if (message.equals("success")) {
				return MessageBox.build("1", "充值成功");
			}
			return MessageBox.build("0", message);
		} catch (Exception e) {
			message = "充值失败！";
			return MessageBox.build("0", message);
		}
	}

	// 银行本息还款
	public MessageBox doCustodyReplay(String borrowUserName, Integer repaymentid) {
		String message = null;
		int bizType=0;
		MessageBox messageBox=MessageBox.build("0","");
			Integer memberId = memberService.getMemberIdByUserName(borrowUserName);
			if (memberId == null) {message = "请输入用户名不存在";
				return MessageBox.build("0", message);
			}
			ShiroUser shiroUser = ShiroUtils.currentUser();
			String ip = HttpTookit.getRealIpAddr(currentRequest());
			
			String repayKey = "RPKEY" + String.valueOf(repaymentid);
			String repay = String.valueOf(repaymentid);
			boolean repaybool = CacheService.getInstance().existKey(repayKey);

			//~~~~~~~~~~~~~~~生成本息还款数据~~~~~~~~~~~~~~~
			if (!repaybool) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("uname", shiroUser.getUserName());
				map.put("repay", repay);
				CacheService.getInstance().put(repayKey, map);
				CacheService.getInstance().expire(repayKey, 1800);
				try {
					 messageBox = gGBorrowManageService.saveCustodyRepayBorrow(repaymentid, ip, memberId, shiroUser, borrowUserName);
				} catch (Exception e) {
					CacheService.getInstance().remove(repayKey);
					logger.error(e.getMessage() , e);
					return MessageBox.build("0", "生成本息还款数据失败，请刷新页面后重试！");
				}
			} else {
				Map<String, String> repayMap = CacheService.getInstance().getMap(repayKey);
				message = "生成本息还款数据已经在进行中，操作人：" + repayMap.get("uname");
				return MessageBox.build("0", message);
			}

		    CacheService.getInstance().remove(repayKey);
			if(!messageBox.getCode().equals("1")){
				return messageBox;
			}



		//~~~~~~~~~~~~~~~上报本息还款数据~~~~~~~~~~~~~~~
		try {
			if ( messageBox.getCode().equals("1")) {
				bizType=Integer.parseInt(messageBox.getMessage());
				BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordService.queryRepaymentByIdForUpdate(repaymentid);
				List<EFundRepayMent> efundList = eFundRepayMentService.selectByRepayId(repaymentid, bRepaymentRecordVo.getBorrowId(),bizType);
				if(efundList == null || efundList.size() == 0 ){
					return MessageBox.build("1", "暂无本息还款上报数据");
				}

				PageListModel plist = new PageListModel(efundList, 200);
				int totalPage = plist.getTotalPages();
				logger.info("本次总共:"+totalPage+"批，本息还款上报记录");
				for (int i = 1; i <= totalPage; i++) {
					List<EFundRepayMent> efundPageList =plist.getObjects(i);
					MessageBox mb = doReport(efundPageList,bRepaymentRecordVo,repaymentid, shiroUser.getUserId(),bizType);
					if(! mb.getCode().equals("1")){
						return mb;
					}
				}
				return MessageBox.build("1", "上报本息还款数据成功");
			}
			return MessageBox.build("0", message);
		} catch (Exception e) {
			message="上报本息还款数据异常，请刷新页面后重试！";
			logger.error(message , e);
			return MessageBox.build("0", message);
		}
	}

	// 平台本息还款
	public MessageBox doSelfReplay(String borrowUserName, Integer repaymentid) {
		String message = null;
		try {

			Integer memberId = memberService.getMemberIdByUserName(borrowUserName);
			if (memberId == null) {
				message = "请输入用户名不存在";
				return MessageBox.build("0", message);
			}
			ShiroUser shiroUser = ShiroUtils.currentUser();
			String ip = HttpTookit.getRealIpAddr(currentRequest());
			String repayKey = "RPKEY" + String.valueOf(repaymentid);
			String repay = String.valueOf(repaymentid);
			boolean repaybool = CacheService.getInstance().existKey(repayKey);

			if (!repaybool) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("uname", shiroUser.getUserName());
				map.put("repay", repay);
				CacheService.getInstance().put(repayKey, map);
				CacheService.getInstance().expire(repayKey, 1800);
				try {
					message = borrowManageService.saveRepayBorrow(repaymentid,
							ip, memberId, shiroUser, borrowUserName);
				} catch (Exception e) {
					CacheService.getInstance().remove(
							"RPKEY" + String.valueOf(repaymentid));
					message = "执行还款流程失败，请刷新页面后重试！";
					return MessageBox.build("0", message);
				}
			} else {
				Map<String, String> repayMap = CacheService.getInstance().getMap(repayKey);
				message = "还款操作已经在进行中，操作人：" + repayMap.get("uname");
				return MessageBox.build("0", message);
			}

			CacheService.getInstance().remove(repayKey);

			if (message.equals(BusinessConstants.SUCCESS)) {
				return MessageBox.build("1", "还款成功");
			}
			return MessageBox.build("0", message);
		} catch (Exception e) {
			message = "还款失败，请刷新页面后重试！";
			return MessageBox.build("0", message);
		}
	}


	public MessageBox doReport(List<EFundRepayMent> efundList,BRepaymentRecordVo bRepaymentRecordVo,
								  Integer repaymentid,Integer userId,Integer bizType) throws Exception {
		BorrowVo borrowVo = borrowMapper.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());
		//生成还款XML
		String reqMessage = eFundRepayMentService.createRepayXML(efundList, borrowVo);
		//调用银行本息还款服务
		String relateNo = UUIDGenerator.generate(CGBusinessConstants.RELATENO);
		MessageBox mbox = eFundRepayMentService.sendXML(reqMessage, relateNo, userId, repaymentid);
		//银行本息请求处理
		if (!mbox.getCode().equals("1")) {return mbox;}
		MessageBox resbox = eFundRepayMentService.doRepayResponse(mbox.getMessage(),relateNo, userId, repaymentid,bizType);
		//银行本息响应处理
		if (!resbox.getCode().equals( "1")) {return resbox;}
		return MessageBox.build("1", "上报本息还款数据成功");
	}


}
