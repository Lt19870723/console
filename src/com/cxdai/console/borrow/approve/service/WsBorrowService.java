package com.cxdai.console.borrow.approve.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cxdai.console.account.recharge.entity.Account;
import com.cxdai.console.account.recharge.mapper.AutoInvestConfigMapper;
import com.cxdai.console.account.recharge.mapper.BaseAccountMapper;
import com.cxdai.console.account.recharge.service.AutoInvestConfigRecordService;
import com.cxdai.console.account.recharge.vo.AutoInvestConfigVo;
import com.cxdai.console.base.entity.AutoInvestConfig;
import com.cxdai.console.base.entity.AutoInvestConfigRecord;
import com.cxdai.console.base.entity.BTenderRecord;
import com.cxdai.console.base.entity.BaseEBankInfo;
import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.base.entity.BorrowApproved;
import com.cxdai.console.base.mapper.BaseAccountLogMapper;
import com.cxdai.console.base.mapper.BaseBorrowApprovedMapper;
import com.cxdai.console.base.mapper.BaseEBankInfoMapper;
import com.cxdai.console.borrow.approve.entity.TenderBorrowCnd;
import com.cxdai.console.borrow.approve.entity.TenderRecord;
import com.cxdai.console.borrow.approve.mapper.BTransferMapper;
import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.vo.BorrowApprovedVo;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.approve.vo.CollectionRepayInfoVo;
import com.cxdai.console.borrow.approve.vo.CollectionStatisticCnd;
import com.cxdai.console.borrow.autotender.service.AutoInvestService;
import com.cxdai.console.borrow.autotender.service.CollectionRecordService;
import com.cxdai.console.borrow.emerg.mapper.TenderRecordMapper;
import com.cxdai.console.borrow.manage.mapper.BRepaymentRecordMapper;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecord;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.cg.entity.MessageRecord;
import com.cxdai.console.cg.mapper.MessageRecordMapper;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.custody.xml.FBRes;
import com.cxdai.console.common.custody.xml.Finance;
import com.cxdai.console.common.custody.xml.Message;
import com.cxdai.console.common.custody.xml.PIReq;
import com.cxdai.console.common.custody.xml.UFBReq;
import com.cxdai.console.common.custody.xml.XmlUtil;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.common.statics.CGBusinessConstants;
import com.cxdai.console.customer.information.mapper.BorrowBusinessMapper;
import com.cxdai.console.customer.information.vo.BorrowBusinessVo;
import com.cxdai.console.customer.svip.mapper.BaseAutoInvestConfigMapper;
import com.cxdai.console.customer.svip.mapper.BaseAutoInvestConfigRecordMapper;
import com.cxdai.console.customer.svip.mapper.BaseVIPApproMapper;
import com.cxdai.console.firstborrow.service.FirstBorrowService;
import com.cxdai.console.fix.mapper.FixBorrowTransferMapper;
import com.cxdai.console.fix.service.FixBorrowService;
import com.cxdai.console.fix.service.FixBorrowTransferService;
import com.cxdai.console.fix.vo.FixBorrowTransferVo;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.statistics.account.entity.AccountLog;
import com.cxdai.console.statistics.account.entity.UserLevelRatio;
import com.cxdai.console.statistics.account.mapper.AccountMapper;
import com.cxdai.console.statistics.account.mapper.AccountNetValueMapper;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.system.mapper.ConfigurationMapper;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.WebServiceMD5;
import com.cxdai.console.util.exception.AppException;
import com.cxdai.console.util.serialnumberutil.UUIDGenerator;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Service
@Transactional(rollbackFor = Throwable.class)
public class WsBorrowService {

	@Autowired
	private BorrowMapper borrowMapper;
	@Autowired
	private BorrowApprovedService borrowApprovedService;
	@Autowired
	private BaseBorrowApprovedMapper baseBorrowApprovedMapper;
	@Autowired
	private BRepaymentRecordMapper bRepaymentRecordMapper;
	/****** private method ******************/
	@Autowired
	private FirstBorrowService firstBorrowService;
	@Autowired
	private AutoInvestService autoInvestService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BTransferMapper bTransferMapper;
	@Autowired
	private CollectionRecordService collectionRecordService;
	@Autowired
	private BorrowBusinessMapper borrowBusinessMapper;
	@Autowired
	private BaseAccountMapper baseAccountMapper;
	@Autowired
	private AccountLogService accountLogService;
	@Autowired
	private AccountNetValueMapper accountNetValueMapper;
	@Autowired
	private TenderRecordMapper tenderRecordMapper;
	@Autowired
	private FixBorrowService fixBorrowService;
	@Autowired
	private FixBorrowTransferMapper fixBorrowTransferMapper;
	@Autowired
	private FixBorrowTransferService fixBorrowTransferService;
	@Autowired
	ConfigurationMapper configurationMapper;
	@Autowired
	private MessageRecordMapper messageRecordMapper;
	@Autowired
	private AutoInvestConfigMapper autoInvestConfigMapper;
	@Autowired
	private BaseEBankInfoMapper baseEBankInfoMapper;
	@Autowired
	private BaseVIPApproMapper baseVIPApproMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private BaseAccountLogMapper baseAccountLogMapper;
	@Autowired
	private CGUtilService cGUtilService;
	@Autowired
	private AutoInvestConfigRecordService autoInvestConfigRecordService;
	@Autowired
	private BaseAutoInvestConfigRecordMapper baseAutoInvestConfigRecordMapper;
	@Autowired
	private BaseAutoInvestConfigMapper baseAutoInvestConfigMapper;
	
	/**
	 * 
	 * <p>
	 * Description:初审借款标<br />
	 * </p>
	 * 
	 * @author Administrator
	 * @version 0.1 2015年6月25日
	 * @param borrowId
	 * @param flag
	 * @param userId
	 * @param remark
	 * @param serviceKey
	 * @return
	 * @throws Exception
	 *             String
	 */
	public String saveApproveBorrowAntiFraud(int borrowId, int flag, int userId, String remark, String serviceKey) throws Exception {
		// 验证数据是否正确
		Map<String, Object> validateKeyMap = new HashMap<>();
		validateKeyMap.put("borrowId", borrowId);
		validateKeyMap.put("flag", flag);
		validateKeyMap.put("userId", userId);
		validateKeyMap.put("remark", remark);
		if (!WebServiceMD5.validateServiceKey(validateKeyMap, serviceKey)) {
			return "数据验签出错";
		}

		BorrowApprovedVo borrowApprovedVo = borrowApprovedService.selectByBorrowIdForUpdate(borrowId);
		BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowId);
		// 如果标的状态不是初审通过
		if (borrowApprovedVo.getStatus() != BusinessConstants.BORROW_APPSTATUS_FIRST_PASS_CODE
				|| borrowVo.getApprstatus() != BusinessConstants.BORROW_APPSTATUS_FIRST_PASS_CODE) {
			return "标状态非初审通过,请确认数据";
		}
		// 审核通过
		if (flag == BusinessConstants.APPROVE_BORROW_PASS) {
			borrowApprovedVo.setStatus(BusinessConstants.BORROW_APPSTATUS_ANTIFRAUD_PASS_CODE);
			borrowVo.setApprstatus(BusinessConstants.BORROW_APPSTATUS_ANTIFRAUD_PASS_CODE);
			// 审核不通过
		} else {
			borrowApprovedVo.setStatus(BusinessConstants.BORROW_APPSTATUS_DRAFT_CODE);
			borrowVo.setStatus(BusinessConstants.BORROW_STATUS_NOPASS_CODE);
			borrowVo.setApprstatus(BusinessConstants.BORROW_APPSTATUS_DRAFT_CODE);
		}
		borrowApprovedVo.setVerifyUser2(userId);
		borrowApprovedVo.setVerifyRemark2(remark);
		borrowApprovedVo.setVerifyTime2(DateUtils.getTime());
		// 更新借款审核记录
		BorrowApproved borrowApproved = new BorrowApproved();
		BeanUtils.copyProperties(borrowApprovedVo, borrowApproved);
		baseBorrowApprovedMapper.updateEntity(borrowApproved);
		Borrow borrow = new Borrow();
		BeanUtils.copyProperties(borrowVo, borrow);
		borrowMapper.updateBorrowStatusById(borrow);

		return BusinessConstants.SUCCESS;

	}

	public String saveApproveBorrowFirst(int borrowId, int flag, int userId, String remark, String creditRating, String serviceKey) throws Exception {
		// 验证数据是否正确
		Map<String, Object> validateKeyMap = new HashMap<>();
		validateKeyMap.put("borrowId", borrowId);
		validateKeyMap.put("flag", flag);
		validateKeyMap.put("userId", userId);
		validateKeyMap.put("remark", remark);
		validateKeyMap.put("creditRating", creditRating);
		if (!WebServiceMD5.validateServiceKey(validateKeyMap, serviceKey)) {
			return "数据验签出错";
		}

		BorrowApprovedVo borrowApprovedVo = borrowApprovedService.selectByBorrowIdForUpdate(borrowId);
		BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowId);

		// 如果标的状态不是新标，审核中或标的审核状态不是待审核
		if (borrowApprovedVo.getStatus() != BusinessConstants.BORROW_APPSTATUS_WAIT_CODE || borrowVo.getStatus() != BusinessConstants.BORROW_STATUS_NEW_CODE) {
			return "标状态非待审核,请确认数据";
		}
		borrowVo.setCreditRating(creditRating);
		// 审核通过
		if (flag == BusinessConstants.APPROVE_BORROW_PASS) {
			borrowApprovedVo.setStatus(BusinessConstants.BORROW_APPSTATUS_FIRST_PASS_CODE);
			borrowVo.setApprstatus(BusinessConstants.BORROW_APPSTATUS_FIRST_PASS_CODE);
			// 审核不通过
		} else {
			borrowApprovedVo.setStatus(BusinessConstants.BORROW_APPSTATUS_DRAFT_CODE);
			borrowVo.setStatus(BusinessConstants.BORROW_STATUS_NOPASS_CODE);
			borrowVo.setApprstatus(BusinessConstants.BORROW_APPSTATUS_DRAFT_CODE);
		}
		borrowApprovedVo.setVerifyUser(userId);
		borrowApprovedVo.setVerifyRemark(remark);
		borrowApprovedVo.setVerifyTime(DateUtils.getTime());
		// 更新借款审核记录
		BorrowApproved borrowApproved = new BorrowApproved();
		BeanUtils.copyProperties(borrowApprovedVo, borrowApproved);
		baseBorrowApprovedMapper.updateEntity(borrowApproved);
		// 更新借款标状态
		Borrow borrow = new Borrow();
		BeanUtils.copyProperties(borrowVo, borrow);
		borrowMapper.updateBorrowStatusById(borrow);

		return BusinessConstants.SUCCESS;

	}

	/**
	 * 借款标终审
	 * 
	 * @param borrowId
	 * @param flag
	 * @param userId
	 * @param remark
	 * @param addip
	 * @param serviceKey
	 * @return
	 * @throws AppException
	 * @throws Exception
	 */
	public String saveApproveBorrowFinal(Integer borrowId, Integer flag, Integer userId, String remark, String addip, String serviceKey) throws AppException,
			Exception {
		// 验证数据是否正确
		Map<String, Object> validateKeyMap = new HashMap<>();
		validateKeyMap.put("borrowId", borrowId);
		validateKeyMap.put("flag", flag);
		validateKeyMap.put("userId", userId);
		validateKeyMap.put("remark", remark);
		validateKeyMap.put("addip", addip);
		if (!WebServiceMD5.validateServiceKey(validateKeyMap, serviceKey)) {
			return "数据验签出错";
		}

		BorrowApprovedVo borrowApprovedVo = borrowApprovedService.selectByBorrowIdForUpdate(borrowId);
		BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowId);
		// 如果标的状态不是反欺诈通过通过
		if (borrowApprovedVo.getStatus() != BusinessConstants.BORROW_APPSTATUS_ANTIFRAUD_PASS_CODE
				|| borrowVo.getApprstatus() != BusinessConstants.BORROW_APPSTATUS_ANTIFRAUD_PASS_CODE) {
			return "标状态非反欺诈通过,请确认数据";
		}
		// 查询借款标与业务员关系
		BorrowBusinessVo borrowBusinessVo = borrowBusinessMapper.selectBorBusByBorrowIdForUpdate(borrowId);
		// 查询并锁定用户账户
		Account account = baseAccountMapper.queryByUserIdForUpdate(borrowBusinessVo.getUserId());
		/* 是否审核通过 */
		if (flag == BusinessConstants.APPROVE_BORROW_PASS) {
			if (borrowBusinessVo == null || borrowBusinessVo.getUserId() == null) {
				return "权证人员不存在，请修改标选择业务员！";
			}
			// 判断业务员是否为其他
			if (borrowBusinessVo.getUserId().intValue() != 0) {
				if (account.getUseMoney() == null || account.getUseMoney().compareTo(BusinessConstants.BUSINESS_TENDER_MONEY) < 0) {
					return "权证人员账户可用余额小于" + BusinessConstants.BUSINESS_TENDER_MONEY;
				}
			}
			borrowApprovedVo.setStatus(BusinessConstants.BORROW_APPSTATUS_FINAL_PASS_CODE);
			borrowVo.setApprstatus(BusinessConstants.BORROW_APPSTATUS_FINAL_PASS_CODE);
			borrowVo.setStatus(BusinessConstants.BORROW_STATUS_TEND_CODE);
			borrowVo.setPublishTime(String.valueOf(System.currentTimeMillis() / 1000));
			if (borrowVo.getAreaType() != null && borrowVo.getAreaType().intValue() == 0) { // 普通专区
				borrowVo.setIsAutotender(1); // 开始直通车投标和自动投标
			}
		} else {
			borrowApprovedVo.setStatus(BusinessConstants.BORROW_APPSTATUS_DRAFT_CODE);
			borrowVo.setStatus(BusinessConstants.BORROW_STATUS_NOPASS_CODE);
			borrowVo.setApprstatus(BusinessConstants.BORROW_APPSTATUS_DRAFT_CODE);
		}
		borrowVo.setIsCustodyStr("0");//非存管
		borrowApprovedVo.setVerifyUser3(userId);
		borrowApprovedVo.setVerifyRemark3(remark);
		borrowApprovedVo.setVerifyTime3(DateUtils.getTime());
		// 更新借款审核记录
		BorrowApproved borrowApproved = new BorrowApproved();
		BeanUtils.copyProperties(borrowApprovedVo, borrowApproved);
		baseBorrowApprovedMapper.updateEntity(borrowApproved);
		Borrow borrow = new Borrow();
		BeanUtils.copyProperties(borrowVo, borrow);
		borrowMapper.updateBorrowStatusById(borrow);

		// 判断是否投标中并且业务员不是其他
		if (borrow.getStatus() == BusinessConstants.BORROW_STATUS_TEND_CODE && borrowBusinessVo.getUserId().intValue() != 0) {
			// 业务员自动投标
			tenderBorrowForBusinessStaff(borrow, borrowBusinessVo.getUserId(), addip, account);
		}

		if (borrow.getAreaType() != null && borrow.getAreaType() == 0) { // 普通专区可以进行直通车投标和自动投标
			// 开始直通车投标和自动投标
			this.saveFirstOrAutoTender(borrowId);
		}
		// 自动复审
		this.saveApproveBorrowReCheck(borrowId, userId, "系统自动复审", addip);
		return BusinessConstants.SUCCESS;

	}
	
	
	
	
	
	/**
	 * 
	 * <p>
	 * Description:存管标发布<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月17日
	 * @param borrowId
	 * @param flag
	 * @param userId
	 * @param remark
	 * @param addip
	 * @param serviceKey
	 * @return
	 * @throws AppException
	 * @throws Exception
	 * String
	 */
	public String saveApproveCGBorrowFinal(Integer borrowId, ShiroUser shiroUser, String remark, String addip,String relateNo,String rep,BorrowVo vo) throws AppException,Exception {

		//记录项目登记接口响应日志
		MessageRecord messageRecord=new MessageRecord();
		messageRecord.setMode("6");//场景 6(项目基本信息登记)
		messageRecord.setType(2);//1:主动，2:被动
		messageRecord.setMsg(rep);//报文体
		messageRecord.setOrderNo(borrowId.toString());
		messageRecord.setOptUserid(shiroUser.getUserId());
		messageRecord.setPlatform(shiroUser.getPlatform());
		messageRecord.setRemark("项目第一次登记接口调用响应");
		messageRecord.setRelateNo(relateNo);//调用关联号
		messageRecordMapper.insert(messageRecord);
		
		
		//判断响应报文
		boolean isError=XmlUtil.checkXml(rep);
		if(isError){
			return "项目登记失败";
		}
		
		//验签
		boolean istrue= XmlUtil.checkYq(rep);
		if(!istrue){
			return "验签失败";
		}
		
		//如果成功响应
				BorrowApprovedVo borrowApprovedVo = borrowApprovedService.selectByBorrowIdForUpdate(borrowId);
				BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowId);
				/*审核通过 */
					borrowApprovedVo.setStatus(BusinessConstants.BORROW_APPSTATUS_FINAL_PASS_CODE);
					borrowVo.setApprstatus(BusinessConstants.BORROW_APPSTATUS_FINAL_PASS_CODE);
					borrowVo.setStatus(BusinessConstants.BORROW_STATUS_TEND_CODE);
					borrowVo.setPublishTime(String.valueOf(System.currentTimeMillis() / 1000));
					borrowVo.setIsCheck(1);//是否登记   0：未登记，1：已登记
					borrowVo.seteProjectId(vo.geteProjectId());//银行登记项目ID
					borrowVo.setIsCustodyStr(vo.getIsCustody().toString());
					borrowVo.setRepayAcct(vo.getRepayAcct());
					borrowVo.setRepayName(vo.getRepayName());
					if (borrowVo.getAreaType() != null && borrowVo.getAreaType().intValue() == 0) { // 普通专区
						borrowVo.setIsAutotender(1); // 开始直通车投标和自动投标
					}
				borrowApprovedVo.setVerifyUser3(shiroUser.getUserId());
				borrowApprovedVo.setVerifyRemark3(remark);
				borrowApprovedVo.setVerifyTime3(DateUtils.getTime());
				// 更新借款审核记录
				BorrowApproved borrowApproved = new BorrowApproved();
				BeanUtils.copyProperties(borrowApprovedVo, borrowApproved);
				baseBorrowApprovedMapper.updateEntity(borrowApproved);
				Borrow borrow = new Borrow();
				BeanUtils.copyProperties(borrowVo, borrow);
				borrowMapper.updateBorrowStatusById(borrow);
				
				
				/*// 判断是否投标中并且业务员不是其他
				if (borrow.getStatus() == BusinessConstants.BORROW_STATUS_TEND_CODE && borrowBusinessVo.getUserId().intValue() != 0) {
					// 业务员自动投标
					tenderBorrowForBusinessStaff(borrow, borrowBusinessVo.getUserId(), addip, account);
				}

				if (borrow.getAreaType() != null && borrow.getAreaType() == 0) { // 普通专区可以进行直通车投标和自动投标
					// 开始直通车投标和自动投标
					this.saveFirstOrAutoTender(borrowId);
				}
				// 自动复审
				this.saveApproveBorrowReCheck(borrowId, userId, "系统自动复审", addip);*/
				return BusinessConstants.SUCCESS;
		

}
		
	
	/**
	 * 
	 * <p>
	 * Description:项目登记接口调用<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月17日
	 * @return
	 * @throws AppException
	 * @throws Exception
	 * String
	 */
	public MessageBox saveBorrowPIReq(Integer borrowId, Integer flag, ShiroUser shiroUser, String remark, String addip, String serviceKey,String relateNo,String projectId) throws AppException,Exception {
		// 验证数据是否正确
		Map<String, Object> validateKeyMap = new HashMap<>();
		validateKeyMap.put("borrowId", borrowId);
		validateKeyMap.put("flag", flag);
		validateKeyMap.put("userId", shiroUser.getUserId());
		validateKeyMap.put("remark", remark);
		validateKeyMap.put("addip", addip);
		if (!WebServiceMD5.validateServiceKey(validateKeyMap, serviceKey)) {
			return new MessageBox("1", "数据验签出错");
		}

		BorrowApprovedVo borrowApprovedVo = borrowApprovedService.selectByBorrowIdForUpdate(borrowId);
		BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowId);
	/*	if(borrowVo.getIsCheck()==1){
			return new MessageBox("1", "项目已完成第一次登记");
		}*/
		
		// 如果标的状态不是反欺诈通过通过
		if (borrowApprovedVo.getStatus() != BusinessConstants.BORROW_APPSTATUS_ANTIFRAUD_PASS_CODE
				|| borrowVo.getApprstatus() != BusinessConstants.BORROW_APPSTATUS_ANTIFRAUD_PASS_CODE) {
			return new MessageBox("1", "标状态非反欺诈通过,请确认数据");
		}
		// 查询借款标与业务员关系
		BorrowBusinessVo borrowBusinessVo = borrowBusinessMapper.selectBorBusByBorrowIdForUpdate(borrowId);
		// 查询并锁定用户账户
		Account account = baseAccountMapper.queryByUserIdForUpdate(borrowBusinessVo.getUserId());
		
		if (flag == BusinessConstants.APPROVE_BORROW_PASS) {
			if (borrowBusinessVo == null || borrowBusinessVo.getUserId() == null) {
				return new MessageBox("1", "权证人员不存在，请修改标选择业务员！");
			}
			// 判断业务员是否为其他
			if (borrowBusinessVo.getUserId().intValue() != 0) {
				if (account.getUseMoney() == null || account.getUseMoney().compareTo(BusinessConstants.BUSINESS_TENDER_MONEY) < 0) {
					return new MessageBox("1", "权证人员账户可用余额小于" + BusinessConstants.BUSINESS_TENDER_MONEY);
				}
			}
			borrowVo.setPublishTime(String.valueOf(System.currentTimeMillis() / 1000));
			borrowVo.seteProjectId(projectId);
			//调用银行项目登记接口
			String reqMessage=this.createPIReqXml(borrowVo);
			System.out.println(reqMessage);
			String reqXml= XmlUtil.sign(reqMessage, CGBusinessConstants.PIREQ);
			String rep= XmlUtil.send(reqXml);
			
			//记录项目登记日志
			MessageRecord messageRecord=new MessageRecord();
			messageRecord.setMode("6");//场景 6(项目基本信息登记)
			messageRecord.setType(1);//1:主动，2:被动
			messageRecord.setMsg(reqXml);//报文体
			messageRecord.setOrderNo(borrowId.toString());
			messageRecord.setOptUserid(shiroUser.getUserId());
			messageRecord.setPlatform(shiroUser.getPlatform());
			messageRecord.setRemark("项目第一次登记接口调用");
			messageRecord.setRelateNo(relateNo);//调用关联号
			messageRecordMapper.insert(messageRecord);
			
			return new MessageBox("0",rep);
			
		} else {
			borrowApprovedVo.setStatus(BusinessConstants.BORROW_APPSTATUS_DRAFT_CODE);
			borrowVo.setStatus(BusinessConstants.BORROW_STATUS_NOPASS_CODE);
			borrowVo.setApprstatus(BusinessConstants.BORROW_APPSTATUS_DRAFT_CODE);

			borrowApprovedVo.setVerifyUser3(shiroUser.getUserId());
			borrowApprovedVo.setVerifyRemark3(remark);
			borrowApprovedVo.setVerifyTime3(DateUtils.getTime());
			// 更新借款审核记录
			BorrowApproved borrowApproved = new BorrowApproved();
			BeanUtils.copyProperties(borrowApprovedVo, borrowApproved);
			baseBorrowApprovedMapper.updateEntity(borrowApproved);
			Borrow borrow = new Borrow();
			BeanUtils.copyProperties(borrowVo, borrow);
			borrowMapper.updateBorrowStatusById(borrow);
			return null;
		}
		
		
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:生成项目登记报文<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月18日
	 * @param borrowVo
	 * @return
	 * String
	 */
	public String createPIReqXml(BorrowVo borrowVo){
		
		 XStream xstream = new XStream();
		 xstream = new XStream(new DomDriver());
		 Finance finance =new Finance();
		 Message message =new Message();
		 message.setId(UUIDGenerator.generate(CGBusinessConstants.MSGID));
		 PIReq pIReq =new PIReq();
			pIReq.setDate(DateUtils.format(new Date(), DateUtils.YMDH_M_S));
			pIReq.setProjectId(borrowVo.geteProjectId());
		    pIReq.setProjectName(borrowVo.getName());
			pIReq.setProjectBeginTime(DateUtils.timeStampToDate(borrowVo.getPublishTime(), DateUtils.YMD));
			pIReq.setInvestDuration(DateUtils.dayDiff( DateUtils.monthOffset(DateUtils.timeStampToDate(borrowVo.getPublishTime()), borrowVo.getTimeLimit()),DateUtils.timeStampToDate(borrowVo.getPublishTime())));//融资期限
			pIReq.setAmount(borrowVo.getAccount().multiply(new BigDecimal(100)).intValue());
			pIReq.setAdvanceRepayment("1");
			pIReq.setOverdueRepayment("1");
			pIReq.setIsTransfer("1");
			
			xstream.autodetectAnnotations(true);
	        xstream.aliasField(CGBusinessConstants.PIREQ, Message.class, "Mode");
	        xstream.aliasSystemAttribute(null, "class"); // 去掉 class 属性[/color]  
	        message.setMode(pIReq);
	        finance.setMessage(message);
		
		return xstream.toXML(finance);
		
	}
	
	

	/**
	 * 业务员自动投标
	 * 
	 * @author WangQianJin
	 * @title @param borrow
	 * @title @param userId
	 * @title @param addip
	 * @title @throws Exception
	 * @date 2015年8月14日
	 */
	public void tenderBorrowForBusinessStaff(Borrow borrow, Integer userId, String addip, Account account) throws Exception {
		// 业务员投标金额
		BigDecimal tenderMoney = BusinessConstants.BUSINESS_TENDER_MONEY;
		BigDecimal drawMoney = BigDecimal.ZERO;
		BigDecimal noDrawMoney = BigDecimal.ZERO;

		// 判断受限金额是否大于投标金额
		if (account.getNoDrawMoney().compareTo(tenderMoney) >= 0) {
			noDrawMoney = tenderMoney;
		} else {
			noDrawMoney = account.getNoDrawMoney();
			drawMoney = tenderMoney.subtract(noDrawMoney);
		}
		account.setUseMoney(account.getUseMoney().subtract(tenderMoney));
		account.setNoUseMoney(account.getNoUseMoney().add(tenderMoney));
		account.setDrawMoney(account.getDrawMoney().subtract(drawMoney));
		account.setNoDrawMoney(account.getNoDrawMoney().subtract(noDrawMoney));
		// 1、更新业务员账户
		baseAccountMapper.updateEntity(account);
		AccountVo accountVo = new AccountVo();
		BeanUtils.copyProperties(account, accountVo);
		// 2、添加账户日志
		accountLogService.saveAccountLogByParams(accountVo, userId, tenderMoney, "按权证人员投标方式投标，资金冻结成功。", addip, "tender_cold", Integer.valueOf(0),
				borrow.getId(), borrow.getName());
		borrow.setAccountYes(borrow.getAccountYes().add(tenderMoney));
		borrow.setTenderTimes(Integer.valueOf(borrow.getTenderTimes().intValue() + 1));
		if (borrow.getAccount().compareTo(borrow.getAccountYes()) == 0) {
			borrow.setStatus(BusinessConstants.BORROW_STATUS_SUCCESS_CODE);
			borrow.setSuccessTime(DateUtils.getTime());
			if (borrow.getStyle().intValue() == 4) {
				borrow.setEndTime(String.valueOf(DateUtils.getTimeStampsByDate(DateUtils.dayOffset(new Date(), borrow.getTimeLimit().intValue()))));
			} else {
				borrow.setEndTime(String.valueOf(DateUtils.getTimeStampsByDate(DateUtils.monthOffset(new Date(), borrow.getTimeLimit().intValue()))));
			}
		}
		// 3、更新借款标信息
		this.borrowMapper.updateByPrimaryKey(borrow);
		// 4、新增投标明细
		pagekageTenderRecord(borrow, userId, tenderMoney, drawMoney, noDrawMoney, addip);
	}

	/**
	 * 新增投标明细
	 * 
	 * @author WangQianJin
	 * @title @param borrow
	 * @title @param userId
	 * @title @param tenderMoney
	 * @title @param drawMoney
	 * @title @param noDrawMoney
	 * @title @param addip
	 * @title @throws Exception
	 * @date 2015年8月14日
	 */
	public void pagekageTenderRecord(Borrow borrow, Integer userId, BigDecimal tenderMoney, BigDecimal drawMoney, BigDecimal noDrawMoney, String addip)
			throws Exception {
		// 投标记录
		BTenderRecord bTenderRecord = new BTenderRecord();
		bTenderRecord.setUserId(userId);
		bTenderRecord.setStatus(Integer.valueOf(0));
		bTenderRecord.setTenderType(Integer.valueOf(4));
		bTenderRecord.setAccount(tenderMoney);
		bTenderRecord.setBorrowId(borrow.getId());
		BigDecimal interest = BigDecimal.ZERO;
		Integer style = borrow.getStyle();
		int styleValue = 0;
		if (style != null) {
			styleValue = style.intValue();
		}
		/**
		 * 根据还款方式计算利息
		 */
		if (styleValue == 1) {
			/* 等额本息 */
			BigDecimal sumCapital = BigDecimal.ZERO;
			BigDecimal capital = BigDecimal.ZERO;
			BigDecimal interestTotal = BigDecimal.ZERO;
			BigDecimal monthRate = borrow.getApr().divide(new BigDecimal(100), 12, 4).divide(new BigDecimal(12), 12, 4);
			BigDecimal valueA = monthRate.add(new BigDecimal(1)).pow(borrow.getTimeLimit().intValue()).setScale(12, 4);
			BigDecimal valueB = valueA.subtract(new BigDecimal(1));
			BigDecimal instalmentAccount = tenderMoney.multiply(monthRate).multiply(valueA).divide(valueB, 2, 4);
			for (int i = 1; i <= borrow.getTimeLimit().intValue(); i++) {
				if (i == borrow.getTimeLimit().intValue()) {
					capital = tenderMoney.subtract(sumCapital);
					interest = instalmentAccount.subtract(capital);
					interestTotal = interestTotal.add(interest);
				} else {
					BigDecimal interestTime = BigDecimal.ZERO;
					BigDecimal sumCost = BigDecimal.ZERO;
					BigDecimal costTime = BigDecimal.ZERO;
					for (int j = 1; j <= borrow.getTimeLimit().intValue(); j++) {
						interestTime = tenderMoney.subtract(sumCost).multiply(monthRate);
						costTime = instalmentAccount.subtract(interestTime);
						sumCost = sumCost.add(costTime);
					}
					interest = interestTime;
					sumCapital = sumCapital.add(instalmentAccount).subtract(interest);
					interestTotal = interestTotal.add(interest);
				}
			}
			interest = interestTotal.setScale(2, 4);
		} else if (styleValue == 2) {
			/* 按月付息到期还本 */
			interest = tenderMoney.multiply(borrow.getApr()).divide(new BigDecimal(100), 12, 4).divide(new BigDecimal(12), 12, 4)
					.multiply(new BigDecimal(String.valueOf(borrow.getTimeLimit()))).setScale(2, 4);
		} else if (styleValue == 3) {
			/* 到期还本付息 */
			BigDecimal monthRate = borrow.getApr().divide(new BigDecimal(100), 12, 4).divide(new BigDecimal(12), 12, 4);
			interest = tenderMoney.multiply(monthRate).multiply(new BigDecimal(String.valueOf(borrow.getTimeLimit()))).setScale(2, 4);
		} else if (styleValue == 4) {
			/* 按天还款 */
			BigDecimal dayRate = borrow.getApr().divide(new BigDecimal(100), 12, 4).divide(new BigDecimal(360), 12, 4);
			interest = tenderMoney.multiply(dayRate).multiply(new BigDecimal(String.valueOf(borrow.getTimeLimit()))).setScale(2, 4);
		}
		UserLevelRatio userLevelRatio = new UserLevelRatio();
		userLevelRatio.setUserid(userId);
		// 获得用户会员等级和比率
		accountNetValueMapper.callGetUserLevelRatio(userLevelRatio);
		bTenderRecord.setInterest(interest);
		bTenderRecord.setRepaymentAccount(interest.add(tenderMoney));
		bTenderRecord.setRepaymentYesaccount(BigDecimal.ZERO);
		bTenderRecord.setRepaymentYesinterest(BigDecimal.ZERO);
		bTenderRecord.setAddip(addip);
		bTenderRecord.setDrawMoney(drawMoney);
		bTenderRecord.setNoDrawMoney(noDrawMoney);
		bTenderRecord.setIsVip(Integer.valueOf(1));
		bTenderRecord.setRatio(userLevelRatio.getO_ratio());
		bTenderRecord.setUserLevel(userLevelRatio.getO_userLevel());
		bTenderRecord.setPlatform(Integer.valueOf(1));
		// 新增投标记录
		tenderRecordMapper.insert(bTenderRecord);
	}

	/**
	 * 
	 * <p>
	 * Description:自动复审<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年6月27日
	 * @param borrowid
	 * @param checkUserId
	 * @param checkRemark
	 * @param addip
	 * @return
	 * @throws Exception
	 *             String
	 */
	public String saveApproveBorrowReCheck(Integer borrowid, Integer checkUserId, String checkRemark, String addip) throws Exception {
		BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowid);
		System.out.println(borrowVo.getStatus());
		if (!borrowVo.getStatus().equals(BusinessConstants.BORROW_STATUS_SUCCESS_CODE) || borrowVo.getAccount().compareTo(borrowVo.getAccountYes()) != 0) {
			return "借款标状态不是满标复审中";
		}
		// 查询已投标总额 (logic for leader)
		BigDecimal tenderTotal = borrowMapper.queryTenderTotalByBorrowId(borrowid);
		System.out.println(tenderTotal);
		System.out.println("22222222222222222");
		if (null == tenderTotal || tenderTotal.compareTo(borrowVo.getAccount()) != 0) {
			return "借款标金额与投标金额不相等";
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("borrowid", borrowid);
		params.put("checkUserId", checkUserId);
		params.put("checkRemark", checkRemark);
		params.put("addip", addip);
		borrowMapper.approveBorrowReCheck(params);
		String msg = params.get("msg").toString();
		System.out.println("1111111111111111111111");
		System.out.println(msg);
		if (!"00001".equals(msg)) {
			throw new AppException("借款标复审失败");
		}
		// 如果是秒标
		if (borrowVo.getBorrowtype() == BusinessConstants.BORROW_TYPE_SEC) {
			List<BRepaymentRecordVo> repaymentRecordVos = bRepaymentRecordMapper.queryBRepaymentRecordByBorrowId(borrowid);
			BRepaymentRecordVo repaymentRecord = repaymentRecordVos.get(0);
			String result = this.saveRepayBorrow(repaymentRecord.getId(), addip, borrowVo.getUserId());
			if (!"00001".equals(result)) {
				throw new AppException("秒标自动还款失败");
			}
		}

		// 发邮件
		return BusinessConstants.SUCCESS;
	}
	
	
	public String cGsaveApproveBorrowReCheck(Integer borrowid, Integer checkUserId, String checkRemark, String addip) throws Exception {
		BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowid);
		System.out.println(borrowVo.getStatus());
		if (!borrowVo.getStatus().equals(BusinessConstants.BORROW_STATUS_SUCCESS_CODE) || borrowVo.getAccount().compareTo(borrowVo.getAccountYes()) != 0) {
			return "借款标状态不是满标复审中";
		}
		// 查询已投标总额 (logic for leader)
		BigDecimal tenderTotal = borrowMapper.queryTenderTotalByBorrowId(borrowid);
		System.out.println(tenderTotal);
		System.out.println("22222222222222222");
		if (null == tenderTotal || tenderTotal.compareTo(borrowVo.getAccount()) != 0) {
			return "借款标金额与投标金额不相等";
		}

		return BusinessConstants.SUCCESS;
	}

	/**
	 * 定期宝，直通车，自动投标
	 * <p>
	 * Description:开始直通车投标和自动投标<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月31日
	 * @param borrowId
	 * @return String
	 */
	private String saveFirstOrAutoTender(Integer borrowId) throws Exception {
		BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowId);
		if (borrowVo.getStatus() == 2) {
			// 开始自动投标
			if (borrowVo.getAccount().compareTo(new BigDecimal(200000)) >= 0
					&& (borrowVo.getBorrowtype() == BusinessConstants.BORROW_TYPE_RECOMMEND || borrowVo.getBorrowtype() == BusinessConstants.BORROW_TYPE_PLEDGE || borrowVo
							.getBorrowtype() == BusinessConstants.BORROW_TYPE_GUARANTEED)) { // 借款金额大于20万，且为抵押标才开启直通车投标

				// 开始定期宝自动投标,判断是否要自动投标
				Configuration cf = configurationMapper.selectByType(1399);
				if (cf != null && cf.getName().equals("1") && cf.getValue() != null) {
					Date now = new Date();
					DateFormat dfm = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
					Date limitD = dfm.parse(cf.getValue() + "23:59:59");
					if (now.compareTo(limitD) > 0) {
						fixBorrowService.saveFixBorrow(borrowVo.getId());
					}
				} else {
					fixBorrowService.saveFixBorrow(borrowVo.getId());
				}

				// 开始直通车投标
				borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowId);
				firstBorrowService.saveFirstBorrow(borrowVo.getId());
				
				borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowId);
				// 开始自动投标
				if (borrowVo.getStatus() == BusinessConstants.BORROW_STATUS_TEND_CODE
						&& (borrowVo.getBidPassword() == null || borrowVo.getBidPassword().equals(""))) {
					autoInvestService.saveAutoTenderBorrow(borrowVo.getId());
				}
			} else {
				// 开始自动投标
				if (borrowVo.getStatus() == BusinessConstants.BORROW_STATUS_TEND_CODE
						&& (borrowVo.getBidPassword() == null || borrowVo.getBidPassword().equals(""))) {
					autoInvestService.saveAutoTenderBorrow(borrowVo.getId());
				}
			}
			Borrow borrow = new Borrow();
			borrow.setIsAutotender(0); // 开始手动投标
			borrow.setId(borrowVo.getId());
			borrowMapper.updateBorrowStatusById(borrow);
		}
		return BusinessConstants.SUCCESS;
	}

	/**
	 * <p>
	 * Description:还款<br />
	 * </p>
	 * 
	 * @author zhanghao
	 * @version 0.1 2014年5月27日
	 * @param borrowid
	 * @throws Exception
	 */
	private String saveRepayBorrow(Integer repaymentid, String addip, Integer userId) throws Exception {
		String result = BusinessConstants.SUCCESS;
		// 获得待还记录并锁定
		BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordMapper.queryBRepaymentRecordByIdForupdate(repaymentid);
		// 验证还款的数据正确性
		result = validateRepayBorrowData(userId, bRepaymentRecordVo);
		if (!result.equals(BusinessConstants.SUCCESS)) {
			return result;
		}

		// 还款时间和当前时间相差的天数
		Date repaymentTimeDate = new Date(Long.parseLong(bRepaymentRecordVo.getRepaymentTime()) * 1000);
		repaymentTimeDate = DateUtils.parse(DateUtils.format(repaymentTimeDate, DateUtils.YMD_DASH), DateUtils.YMD_DASH);
		Date now = DateUtils.parse(DateUtils.format(new Date(), DateUtils.YMD_DASH), DateUtils.YMD_DASH);
		// 验证是否有足够的余额还款
		result = validateHaveEnoughMoney(userId, bRepaymentRecordVo, repaymentTimeDate, now);
		if (!result.equals(BusinessConstants.SUCCESS)) {
			return result;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("borrowid", bRepaymentRecordVo.getBorrowId());
		map.put("repaymentid", repaymentid);
		map.put("addip", addip);
		map.put("platform", 1);
		// 借款标
		Borrow borrow = borrowMapper.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());
		// 提前天数
		int earlyDays = DateUtils.dayDiff(repaymentTimeDate, now);

		// 还款引发债权转让撤销
		cancelTransfer(bRepaymentRecordVo.getBorrowId());

		// 根据借款标获取转让中的定期宝
		List<FixBorrowTransferVo> bfList = fixBorrowTransferMapper.queryFixBorrowTransferByBorrowId(bRepaymentRecordVo.getBorrowId());
		boolean flag = false;

		// 只有 1：按月分期还款，2：按月付息到期还本 , 3：到期还本付息，才启用提前还款，比应还款日提前3天以上（不包含3天）
		if (earlyDays > 3) {
			if(borrow.getStyle() != BusinessConstants.BORROW_STYLE_DUE_PAY_ALL 
					&& borrow.getStyle() != BusinessConstants.BORROW_STYLE_MONTH_PAY_INTEREST
					&& borrow.getStyle() != BusinessConstants.BORROW_STYLE_MONTH_INSTALMENTS){
				throw new AppException("还款方式有误");
			}
			// 定期宝自动取消转让
			cancelOrTransferFixBorrow(bfList, "cancel");
			flag = true;
			// 提前还款
			borrowMapper.repayEarlyBorrow(map);
		} else {
			// 已垫付
			if (bRepaymentRecordVo.getWebstatus() == BusinessConstants.REPAYMENTRECORD_WEBSTATUS_HAVE_PAY) {
				// 垫付后还款
				borrowMapper.afterWebpayBorrow(map);
			} else {
				// 定期宝自动取消转让
				cancelOrTransferFixBorrow(bfList, "cancel");
				flag = true;
				// 正常还款
				borrowMapper.repayBorrow(map);
			}
		}
		String msg = map.get("msg").toString();
		if (!"00001".equals(msg)) {
			throw new AppException("还款失败");
		}

		if (flag) {
			// 定期宝自动发起转让
			cancelOrTransferFixBorrow(bfList, "transfer");
		}

		return BusinessConstants.SUCCESS;
	}

	/**
	 * <p>
	 * Description:验证是否有足够的余额还款<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月27日
	 * @param userId
	 * @param bRepaymentRecordVo
	 * @param repaymentTimeDate
	 * @param now
	 * @return
	 * @throws Exception
	 *             String
	 */
	private String validateHaveEnoughMoney(Integer userId, BRepaymentRecordVo bRepaymentRecordVo, Date repaymentTimeDate, Date now) throws Exception {
		String result = BusinessConstants.SUCCESS;
		// 逾期天数
		int lateDays = DateUtils.dayDiff(now, repaymentTimeDate);
		// 罚息金额
		BigDecimal lateDayInterest = BigDecimal.ZERO;
		if (lateDays > 0) {
			lateDayInterest = bRepaymentRecordVo.getRepaymentAccount().multiply(new BigDecimal(BusinessConstants.OUT_OF_DAYE_RATE))
					.multiply(new BigDecimal(lateDays)).setScale(2, RoundingMode.UP);
		}
		// 应还总金额 = 还款金额 + 罚息金额
		BigDecimal totalPayMoney = bRepaymentRecordVo.getRepaymentAccount().setScale(2, RoundingMode.UP).add(lateDayInterest);
		// 借款者帐号
		AccountVo accoutVo = accountService.queryAccountByUserIdForUpdate(userId);
		if (totalPayMoney.compareTo(accoutVo.getUseMoney()) == 1) {
			StringBuffer noenoughMoneymsg = new StringBuffer();
			noenoughMoneymsg.append("您的账户余额不足！应还金额为：");
			noenoughMoneymsg.append(bRepaymentRecordVo.getRepaymentAccount().setScale(2, RoundingMode.UP));
			if (lateDays > 0) {
				noenoughMoneymsg.append(",缴纳罚息为：");
				noenoughMoneymsg.append(lateDayInterest);
			}
			return noenoughMoneymsg.toString();
		}
		return result;
	}

	/**
	 * <p>
	 * Description:还款引发债权转让撤销<br />
	 * </p>
	 * 
	 * @author chenpeng
	 * @version 0.1 2014年12月24日
	 * @param borrowid
	 * @throws Exception
	 */
	private void cancelTransfer(Integer borrowId) throws Exception {
		// 还款，判断是否有债权转让存在，有的，撤销债权转让；
		// 借款表id查询对应的多个债权转让id列表
		List<Integer> cancelTransfers = bTransferMapper.queryCancelTransfers(borrowId);

		if (cancelTransfers.size() > 0) {
			for (Integer tid : cancelTransfers) {
				Map<String, Object> mapTrans = new HashMap<String, Object>();
				mapTrans.put("id", tid);
				mapTrans.put("cancelIP", "0.0.0.0");
				mapTrans.put("cancelUser", 0);
				mapTrans.put("cancelRemark", "还款时撤销");
				mapTrans.put("transferStatus", 6);
				mapTrans.put("msg", "");

				borrowMapper.transferCancelByRepay(mapTrans);
				if ("0001".equals(mapTrans.get("msg".toString()))) {

				} else {
					throw new AppException("还款时撤销出错");
				}
			}
		}

	}

	/**
	 * <p>
	 * Description:验证还款数据的正确性<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月27日
	 * @param borrowid
	 * @param userId
	 * @param bRepaymentRecordVo
	 * @return
	 * @throws Exception
	 *             String
	 */
	private String validateRepayBorrowData(Integer userId, BRepaymentRecordVo bRepaymentRecordVo) throws Exception {
		String result = BusinessConstants.SUCCESS;
		if (null == bRepaymentRecordVo) {
			return "待还数据不存在，请确认！";
		}
		if (!bRepaymentRecordVo.getUserId().equals(userId)) {
			return "非法还款数据,请确认！";
		}
		if (bRepaymentRecordVo.getStatus() == BusinessConstants.REPAYMENTRECORD_STATUS_HAVE_PAY) {
			return "该笔借款已还清！请勿重复操作！";
		}
		if (bRepaymentRecordVo.getStatus() != BusinessConstants.REPAYMENTRECORD_STATUS_NO_PAY) {
			return "待还数据非待还中,请确认！";
		}

		// 判断在这之前是否还有未还资金
		Integer lessOrderUnpayCount = bRepaymentRecordMapper.queryBeforeOrderUnPayCount(bRepaymentRecordVo.getBorrowId(), bRepaymentRecordVo.getOrder());
		if (null != lessOrderUnpayCount && lessOrderUnpayCount > 0) {
			return "该笔还款之前尚有未结清的还款,请核实！";
		}

		// 还款时判断待收总额(待收表）是否与待还总额（待还表)差异的绝对值
		CollectionStatisticCnd collectionStatisticCnd = new CollectionStatisticCnd();
		collectionStatisticCnd.setRepaymentId(bRepaymentRecordVo.getId());
		collectionStatisticCnd.setBorrowId(bRepaymentRecordVo.getBorrowId());
		// 已垫付
		if (bRepaymentRecordVo.getWebstatus() == BusinessConstants.REPAYMENTRECORD_WEBSTATUS_HAVE_PAY) {
			collectionStatisticCnd.setStatus(BusinessConstants.COLLECTION_RECORD_STATUS_WEBPAY);
		} else {
			collectionStatisticCnd.setStatus(BusinessConstants.COLLECTION_RECORD_STATUS_UNPAY);
		}
		collectionStatisticCnd.setLockedRecordYn(BusinessConstants.LOCKED_RECORD_YES);
		CollectionRepayInfoVo collectionRepayInfoVo = collectionRecordService.queryRepayTotalByCnd(collectionStatisticCnd);
		// 投标人数小于等于一千，差值为5块钱，投标人数小于等于两千但大于一千，差值可以为10块,大于两千小于等于三千，差值为15，大于三千20 判断
		BigDecimal compareMoney = new BigDecimal("5");
		if (collectionRepayInfoVo.getCollectionPersons() <= 1000) {
			compareMoney = new BigDecimal("5");
		} else if (collectionRepayInfoVo.getCollectionPersons() > 1000 && collectionRepayInfoVo.getCollectionPersons() <= 2000) {
			compareMoney = new BigDecimal("10");
		} else if (collectionRepayInfoVo.getCollectionPersons() > 2000 && collectionRepayInfoVo.getCollectionPersons() <= 3000) {
			compareMoney = new BigDecimal("15");
		} else {
			compareMoney = new BigDecimal("20");
		}
		BigDecimal diff = bRepaymentRecordVo.getRepaymentAccount().subtract(collectionRepayInfoVo.getRepayAccountTotal());
		if (diff.abs().compareTo(compareMoney) == 1) {
			return "应还款金额与待收金额不匹配,请联系客服";
		}

		return result;
	}

	/**
	 * 
	 * <p>
	 * Description:借款标复审<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年6月27日
	 * @param borrowid
	 * @param checkUserId
	 * @param checkRemark
	 * @param addip
	 * @param serviceKey
	 * @return
	 * @throws Exception
	 *             String
	 */
	public String saveApproveBorrowReCheck(Integer borrowid, Integer checkUserId, String checkRemark, String addip, String serviceKey) throws Exception {
		// 验证数据是否正确
		Map<String, Object> validateKeyMap = new HashMap<>();
		validateKeyMap.put("borrowid", borrowid);
		validateKeyMap.put("checkUserId", checkUserId);
		validateKeyMap.put("checkRemark", checkRemark);
		validateKeyMap.put("addip", addip);
		if (!WebServiceMD5.validateServiceKey(validateKeyMap, serviceKey)) {
			return "数据验签出错";
		}
		return this.saveApproveBorrowReCheck(borrowid, checkUserId, checkRemark, addip);
	}
	
	public String cGsaveApproveBorrowReCheck(Integer borrowid, Integer checkUserId, String checkRemark, String addip, String serviceKey) throws Exception {
		// 验证数据是否正确
		Map<String, Object> validateKeyMap = new HashMap<>();
		validateKeyMap.put("borrowid", borrowid);
		validateKeyMap.put("checkUserId", checkUserId);
		validateKeyMap.put("checkRemark", checkRemark);
		validateKeyMap.put("addip", addip);
		if (!WebServiceMD5.validateServiceKey(validateKeyMap, serviceKey)) {
			return "数据验签出错";
		}
		return this.cGsaveApproveBorrowReCheck(borrowid, checkUserId, checkRemark, addip);
	}

	/**
	 * 
	 * <p>
	 * Description:操作罚息<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年6月29日
	 * @param repaymentid
	 * @param addip
	 * @param serviceKey
	 * @return
	 * @throws Exception
	 *             String
	 */
	public String saveOperatingPenalty(Integer repaymentid, String addip, String serviceKey) throws Exception {
		// 验证数据是否正确
		Map<String, Object> validateKeyMap = new HashMap<>();
		validateKeyMap.put("repaymentid", repaymentid);
		validateKeyMap.put("addip", addip);
		if (!WebServiceMD5.validateServiceKey(validateKeyMap, serviceKey)) {
			return "数据验签出错";
		}
		BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordMapper.queryBRepaymentRecordByIdForupdate(repaymentid);
		if (bRepaymentRecordVo.getIsRepairInterest() != null && bRepaymentRecordVo.getIsRepairInterest() == 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("repaymentid", repaymentid);
			map.put("addip", addip);
			borrowMapper.operatingPenalty(map);
			String msg = map.get("msg").toString();
			if (!"00001".equals(msg)) {
				throw new AppException("处理罚息失败");
			} else {
				bRepaymentRecordVo.setIsRepairInterest(1);
				bRepaymentRecordVo.setRepairInterestTime(new Date());
				BRepaymentRecord bRepaymentRecord = new BRepaymentRecord();
				BeanUtils.copyProperties(bRepaymentRecordVo, bRepaymentRecord);
				if (bRepaymentRecordMapper.updateBReaymentRecordById(bRepaymentRecord) <= 0) {
					throw new AppException("处理罚息失败");
				}
			}
		} else {
			BRepaymentRecord bRepaymentRecord = new BRepaymentRecord();
			BeanUtils.copyProperties(bRepaymentRecordVo, bRepaymentRecord);
			bRepaymentRecordMapper.updateBReaymentRecordById(bRepaymentRecord);
		}
		return BusinessConstants.SUCCESS;
	}

	/**
	 * 定期宝取消转让或发起转让
	 * 
	 * @author WangQianJin
	 * @throws Exception
	 * @title @param bfList
	 * @title @param type
	 * @date 2015年9月15日
	 */
	private void cancelOrTransferFixBorrow(List<FixBorrowTransferVo> bfList, String type) throws Exception {
		if (bfList != null && bfList.size() > 0) {
			for (FixBorrowTransferVo fixBorrowTransferVo : bfList) {
				if ("cancel".equals(type)) {
					// 定期宝自动取消转让
					fixBorrowTransferService.saveTransferCancel(fixBorrowTransferVo.getId(), "0.0.0.1");
				} else if ("transfer".equals(type)) {
					// 定期宝自动发起转让
					fixBorrowService.saveTransfer(fixBorrowTransferVo.getFixBorrowId(), "0.0.0.1");
				}
			}
		}
	}
	
	
	public List<AutoInvestConfigVo> findAutoInvestConfig(BorrowVo borrowVo){
		return autoInvestConfigMapper.findAutoInvestConfig(borrowVo);
		
	}
	
	public BigDecimal queryTenderTotalByBorrowId(Integer borrowId) throws Exception{
		return borrowMapper.queryTenderTotalByBorrowId(borrowId);
	}
	
	
	public String tenderCGBorrow(FBRes fBRes,TenderBorrowCnd tenderBorrowCnd,ShiroUser shiroUser,String addip,String p2pDJ,BigDecimal investMoney,AutoInvestConfigVo autoInvestConfigVo,Integer index) throws Exception{
		//查询借款标信息，并锁定
		BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(tenderBorrowCnd.getBorrowid());
		if(fBRes.getBlockStatus().equals("20")){
			//查询用户账户并锁定
			AccountVo accountvo = accountService.queryAccountByUserIdForUpdate(shiroUser.getUserId());
			BaseEBankInfo baseEBankInfo=baseEBankInfoMapper.selectByUserId(shiroUser.getUserId());
			BigDecimal 	interest = null;    
			if(borrowVo.getStyle()==3){
				/**到期还本付息**/
				interest=tenderBorrowCnd.getTenderMoney().multiply(borrowVo.getApr()).divide(new BigDecimal(100*12),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(borrowVo.getTimeLimit())).setScale(2, BigDecimal.ROUND_HALF_UP);
			}else if(borrowVo.getStyle()==4){
				 /**按天还款**/
				interest=tenderBorrowCnd.getTenderMoney().multiply(borrowVo.getApr()).divide(new BigDecimal(100*360),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(borrowVo.getTimeLimit())).setScale(2, BigDecimal.ROUND_HALF_UP);
			}else if(borrowVo.getStyle()==1){
				/**等额本息**/
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("money", tenderBorrowCnd.getTenderMoney());
				map.put("apr", borrowVo.getApr());
				map.put("limit", borrowVo.getTimeLimit());
				interest=tenderRecordMapper.getFqInsTotal(map);
			}else if(borrowVo.getStyle()==2){
				 /**按月付息到期还本**/
				interest=tenderBorrowCnd.getTenderMoney().multiply(borrowVo.getApr()).divide(new BigDecimal(100*12),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(borrowVo.getTimeLimit())).setScale(2, BigDecimal.ROUND_HALF_UP);
			}	    
			
			Integer passed= baseVIPApproMapper.findPassed(shiroUser.getUserId());
			if(passed==null || passed==-1){
				passed=0;
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", shiroUser.getUserId());
			tenderRecordMapper.getUserLevelRatio(map);
			TenderRecord tenderRecord=new TenderRecord();
			tenderRecord.setUserId(shiroUser.getUserId());
			tenderRecord.setBorrowId(tenderBorrowCnd.getBorrowid());
			tenderRecord.setAccount(tenderBorrowCnd.getTenderMoney());
			tenderRecord.setInterest(interest);
			tenderRecord.setRepaymentAccount(interest.add(tenderBorrowCnd.getTenderMoney()));
			tenderRecord.setIsVip(passed);
			tenderRecord.setRatio((String)map.get("ratio"));
			tenderRecord.setUserLevel((String)map.get("userLevel"));
			tenderRecord.setPlatform(1);
			tenderRecord.setIsCustody(1);//浙商存管
			tenderRecord.seteFreezeNo(fBRes.getSerialNo());
			tenderRecord.seteBankInfoId(baseEBankInfo.getId());
			tenderRecord.seteInvestStatus(0);//未上报
			tenderRecord.seteFreezeDate(fBRes.getInstSettleDate());
			tenderRecord.setBizInvestNo(UUIDGenerator.generate(CGBusinessConstants.BORROW_PIReq_ID));
			tenderRecord.setBizFreezeNo(p2pDJ);
			tenderRecord.setAddip(addip);
			tenderRecordMapper.insertTenderrecord(tenderRecord);
			
			//更新借款标
			BorrowVo borrow=new BorrowVo();
			borrow.setId(borrowVo.getId());
			borrow.setAccountYes(borrowVo.getAccountYes().add(tenderBorrowCnd.getTenderMoney()));
			borrow.setTenderTimes(borrowVo.getTenderTimes()+1);
			borrowMapper.updateBorrow(borrow);
			//更新投资人账户资金
			AccountVo av=new AccountVo();
			av.setId(accountvo.getId());
			av.seteUseMoney(accountvo.geteUseMoney().subtract(tenderBorrowCnd.getTenderMoney()));
			av.seteFreezeMoney(accountvo.geteFreezeMoney().add(tenderBorrowCnd.getTenderMoney()));
			accountMapper.updateCGAccount(av);
			//添加账户日志
			AccountLog accountLog=new AccountLog();
			accountLog.setUserId(shiroUser.getUserId());
			accountLog.setType(CGBusinessConstants.CG_TB);
			accountLog.setMoney(tenderBorrowCnd.getTenderMoney());
			accountLog.setToUser(tenderRecord.getId());
			accountLog.setIdType(0);
			accountLog.setRemark("投标冻结");
			accountLog.setBorrowId(borrowVo.getId());
			accountLog.setBorrowName(borrowVo.getName());
			accountLog.setAddip(addip);
			accountLog.setIsCustody(1);//浙商存管
			baseAccountLogMapper.insertAccountLog(accountLog);
			
			
			//判断是否是最后一笔
			BigDecimal accountYes=borrowVo.getAccountYes().add(tenderBorrowCnd.getTenderMoney());
			
			//查询最后一笔投标记录
			TenderRecord tr = tenderRecordMapper.findlastTenderrecord(tenderBorrowCnd.getBorrowid(), shiroUser.getUserId());
			if(accountYes.compareTo(borrowVo.getAccount())==0 && investMoney.compareTo(tr.getAccount())==1){
				String autoRemark = "您本次的自动投标排名不变";
				//更新投标记录
				BTenderRecord record=new BTenderRecord();
				record.setId(tr.getId());
				record.setAutotenderOrderRemark(autoRemark);
				tenderRecordMapper.updateByPrimaryKeySelective(record);
				
				//新增自动投标规则日志
				AutoInvestConfig autoInvestConfig = new AutoInvestConfig();
				BeanUtils.copyProperties(autoInvestConfigVo, autoInvestConfig);
				AutoInvestConfigRecord autoInvestConfigRecord = autoInvestConfigRecordService.setAutoInvestConfigRecord(autoInvestConfigVo);
				autoInvestConfigRecord.setRownum(autoInvestConfigVo.getRownum());
				autoInvestConfigRecord.setAddtime(new Date());
				autoInvestConfigRecord.setRecord_type(2);
				baseAutoInvestConfigRecordMapper.insertEntity(autoInvestConfigRecord);
			
			}else{
				//新增自动投标规则日志
				AutoInvestConfig autoInvestConfig = new AutoInvestConfig();
				BeanUtils.copyProperties(autoInvestConfigVo, autoInvestConfig);
				AutoInvestConfigRecord autoInvestConfigRecord = autoInvestConfigRecordService.setAutoInvestConfigRecord(autoInvestConfigVo);
				autoInvestConfigRecord.setRownum(autoInvestConfigVo.getRownum());
				autoInvestConfigRecord.setAddtime(new Date());
				autoInvestConfigRecord.setRecord_type(2);
				baseAutoInvestConfigRecordMapper.insertEntity(autoInvestConfigRecord);
			
				//普通VIP须重新排队（终身顶级VIP无需重新排队）
				if(autoInvestConfigVo.getVipLevel()==0){
					String autoUptime = null;
					if(index>0 && index<10){
						autoUptime=DateUtils.getCurrentTimeStamp()+"000000"+index.toString();
					}else if(index>10 && index<100){
						autoUptime=DateUtils.getCurrentTimeStamp()+"00000"+index.toString();
					}else if(index>100 && index<1000){
						autoUptime=DateUtils.getCurrentTimeStamp()+"0000"+index.toString();
					}else if(index>1000 && index<10000){
						autoUptime=DateUtils.getCurrentTimeStamp()+"000"+index.toString();
					}else if(index>10000 && index<100000){
						autoUptime=DateUtils.getCurrentTimeStamp()+"00"+index.toString();
					}else if(index>100000 && index<1000000){
						autoUptime=DateUtils.getCurrentTimeStamp()+"0"+index.toString();
					}else if(index>1000000){
						autoUptime=DateUtils.getCurrentTimeStamp()+index.toString().substring(0, 7);
					}
					
					//更新排队时间
					AutoInvestConfig ac=new AutoInvestConfig();
					ac.setId(autoInvestConfigVo.getId());
					ac.setUptime(autoUptime);
					baseAutoInvestConfigMapper.updateUptime(ac);
				}
				
			}
			
			
			
			if(accountYes.compareTo(borrowVo.getAccount())==0){
				//更新借款标的状态我满标复审中
				BorrowVo bv=new BorrowVo();
				bv.setId(borrowVo.getId());
				bv.setTimeLimit(borrowVo.getTimeLimit());
				bv.setStatus(3);//满标复审中
				if(borrowVo.getStyle()==4){
					borrowMapper.updateBorrowStutusDay(bv);
				}else{
					borrowMapper.updateBorrowStutus(bv);
				}
				return "checkBorrow";
				
			}
			
		}else if(fBRes.getBlockStatus().equals("30")){
			// 冻结失败，预计已投资总金额减去投资金额
			BorrowVo borrow=new BorrowVo();
			borrow.setId(borrowVo.getId());
			borrow.setAdvance(borrowVo.getAdvance().subtract(tenderBorrowCnd.getTenderMoney()));
			borrowMapper.updateBorrowAdvance(borrow);
			return "投资失败";
		}else{
			// 冻结失败，预计已投资总金额减去投资金额
			BorrowVo borrow=new BorrowVo();
			borrow.setId(borrowVo.getId());
			borrow.setAdvance(borrowVo.getAdvance().subtract(tenderBorrowCnd.getTenderMoney()));
			borrowMapper.updateBorrowAdvance(borrow);
			return "银行处理中";
		}
		return BusinessConstants.SUCCESS;
		
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:投资资金冻结解冻接口<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月26日
	 * @param borrowVo
	 * @param oriSerialNo
	 * @param investmentAmount
	 * @param shiroUser
	 * @param remark
	 * @param mode
	 * @param relateNo
	 * @return
	 * @throws Exception
	 * String
	 */
	public String saveUFBReq(BorrowVo borrowVo,String oriSerialNo,Integer investmentAmount,ShiroUser shiroUser,String remark,String mode,String relateNo) throws Exception{
		//生成项目投资报文
		BaseEBankInfo baseEBankInfo=baseEBankInfoMapper.selectByUserId(shiroUser.getUserId());
		String reqMessage= this.createUFBReqXml(borrowVo, baseEBankInfo, oriSerialNo, investmentAmount);
		System.out.println(reqMessage);
		String reqXml=XmlUtil.sign(reqMessage, CGBusinessConstants.UFBReq);
		//调用项目登记接口
		String rep= XmlUtil.send(reqXml);
		cGUtilService.insertMsg(reqXml, mode, 1, shiroUser, remark, relateNo,borrowVo.getId());
		return rep;
		
	} 
	
	
	/**
	 * 
	 * <p>
	 * Description:投资资金冻结解冻报文<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月18日
	 * @param borrowVo
	 * @return
	 * String
	 */
	public String createUFBReqXml(BorrowVo borrowVo,BaseEBankInfo baseEBankInfo,String oriSerialNo,Integer investmentAmount){
		
		 XStream xstream = new XStream();
		 xstream = new XStream(new DomDriver());
		 Finance finance =new Finance();
		 Message message =new Message();
		 message.setId(UUIDGenerator.generate(CGBusinessConstants.MSGID));
		 UFBReq uFBReq =new UFBReq();
		 	uFBReq.setDate(DateUtils.format(new Date(), DateUtils.YMDH_M_S));
		 	uFBReq.setProjectId(borrowVo.geteProjectId());
		 	uFBReq.setBindSerialNo(baseEBankInfo.getBindNo());
		 	uFBReq.setOriSerialNo(oriSerialNo);
		 	uFBReq.setInvestmentAmount(investmentAmount);
		 	
			xstream.autodetectAnnotations(true);
	        xstream.aliasField(CGBusinessConstants.UFBReq, Message.class, "Mode");
	        xstream.aliasSystemAttribute(null, "class"); // 去掉 class 属性[/color]  
	        message.setMode(uFBReq);
	        finance.setMessage(message);
		
		return xstream.toXML(finance);
		
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:确定最终投标金额<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年6月29日
	 * @param borrowId
	 * @param userId
	 * @param investMoney
	 * @return
	 * BigDecimal
	 */
	public BigDecimal isEffectiveMoney(Integer borrowId,Integer userId,BigDecimal investMoney,BigDecimal eusemoney){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("borrowId", borrowId);
		map.put("userId", userId);
		map.put("investMoney", investMoney);
		map.put("eusemoney", eusemoney);
		BigDecimal tenderAccount= tenderRecordMapper.isEffectiveMoney(map);
		return tenderAccount;
	}
	
	
	public void updateBorrowStatusById(Integer borrowId) throws Exception{
		Borrow borrow = new Borrow();
		borrow.setIsAutotender(0); // 开始手动投标
		borrow.setId(borrowId);
		borrowMapper.updateBorrowStatusById(borrow);
	}
	
}
