package com.cxdai.console.borrow.check.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.recharge.mapper.AutoInvestConfigMapper;
import com.cxdai.console.account.recharge.mapper.AutoInvestConfigRecordMapper;
import com.cxdai.console.account.recharge.vo.AutoInvestConfigRecordVo;
import com.cxdai.console.account.recharge.vo.AutoInvestConfigVo;
import com.cxdai.console.base.entity.BaseEBankInfo;
import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.base.entity.BorrowAdvanceLog;
import com.cxdai.console.base.entity.BorrowApproved;
import com.cxdai.console.base.entity.MailSendRecord;
import com.cxdai.console.base.mapper.BaseBorrowApprovedMapper;
import com.cxdai.console.base.mapper.BaseEBankInfoMapper;
import com.cxdai.console.base.mapper.BaseMailSendRecordMapper;
import com.cxdai.console.borrow.approve.entity.TenderRecord;
import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.check.entity.BorrowerDealError;
import com.cxdai.console.borrow.check.mapper.BorrowerDealErrorMapper;
import com.cxdai.console.borrow.check.vo.BorrowCheckVo;
import com.cxdai.console.borrow.emerg.mapper.TenderRecordMapper;
import com.cxdai.console.borrow.manage.mapper.BRepaymentRecordMapper;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.cgnotify.entity.EReconDetail;
import com.cxdai.console.cgnotify.entity.EReconHeader;
import com.cxdai.console.cgnotify.mapper.EReconDetailMapper;
import com.cxdai.console.cgnotify.mapper.EReconHeaderMapper;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.common.statics.CGBusinessConstants;
import com.cxdai.console.fix.mapper.BCollectionrecordMapper;
import com.cxdai.console.fix.vo.BCollectionRecordVo;
import com.cxdai.console.statistics.account.entity.Account;
import com.cxdai.console.statistics.account.entity.AccountLog;
import com.cxdai.console.statistics.account.mapper.AccountLogMapper;
import com.cxdai.console.statistics.account.mapper.AccountMapper;
import com.cxdai.console.statistics.account.vo.AccountVo;

/**   
 * <p>
 * Description:满标流标服务<br />
 * </p>
 * @title BorrowRecheckService.java
 * @package com.cxdai.console.borrow.check.service 
 * @author tanghaitao
 * @version 0.1 2016年5月30日
 */

@Service
@Transactional(rollbackFor = Throwable.class)
public class BorrowRecheckService {

	public Logger logger = Logger.getLogger(BorrowRecheckService.class);
	
	@Autowired
	private BorrowMapper borrowMapper;
	@Autowired
	private EReconDetailMapper eReconDetailMapper;
	@Autowired
	private EReconHeaderMapper eReconHeaderMapper;
	@Autowired
	private TenderRecordMapper tenderRecordMapper;
	@Autowired
	private BaseBorrowApprovedMapper baseBorrowApprovedMapper;
	@Autowired
	private BCollectionrecordMapper bCollectionrecordMapper;
	@Autowired
	private BaseEBankInfoMapper baseEBankInfoMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private AccountLogMapper accountLogMapper;
	@Autowired
	private BRepaymentRecordMapper bRepaymentRecordMapper;
	@Autowired
	private BaseMailSendRecordMapper baseMailSendRecordMapper;
	@Autowired
	private BorrowerDealErrorMapper borrowerDealErrorMapper;
	@Autowired
	private AutoInvestConfigRecordMapper autoInvestConfigRecordMapper;
	@Autowired
	private AutoInvestConfigMapper autoInvestConfigMapper;
	
	/**
	 * 
	 * <p>
	 * Description:满标复审<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年6月1日
	 * @param eProjectId
	 * @param addUserId
	 * @param approvedRemark
	 * @param addIp
	 * @return
	 * @throws Exception
	 * String
	 */
	public String recheckBorrow(String eProjectId,Integer addUserId,String approvedRemark,String addIp) throws Exception{
		
		//查询借款标并锁定
		BorrowVo borrowVo = borrowMapper.selectByProjectIdForUpdate(eProjectId);
		
		//满标操作
				EReconHeader eReconHeader =eReconHeaderMapper.findReconHeader(eProjectId, 1);
				if(eReconHeader.getStatus()==1){
					if(borrowVo.getStatus()==6){
						//查询批量扣款记录详情
						List<EReconDetail> eReconDetailList=eReconDetailMapper.findReconDetail(eProjectId, 1);
						
						String check= this.check(borrowVo, eReconDetailList, eProjectId,1,addUserId);
						if(!check.equals(BusinessConstants.SUCCESS)){
							return check;
						}
						
						//根据对账记录明细循环操作得出投资成功的记录
						this.screenOutSuccessTenderrecord(eReconDetailList, borrowVo);
						
						//查询投标记录并锁定(只查询划款成功的)
						List<TenderRecord> tenderRecordList= tenderRecordMapper.findSuccessTenderrecord(borrowVo.getId());
						
						//满标复审校验（已成功借到的金额和成功投标记录金额是否相等和状态是否是银行复审中）
						BorrowVo bv = borrowMapper.selectByPrimaryKey(borrowVo.getId());
						if(bv.getAccount().compareTo(bv.getAccountYes())!=0){
							//记录异常信息,发送邮件给相关人员
							String remark="借款标异常,已成功借到的金额和成功投标记录金额不等";
							this.saveErrorBorrow(1, addUserId, borrowVo.getId(), remark);
							//新增邮件待发记录 
							MailSendRecord mailSendRecord=new MailSendRecord();
							mailSendRecord.setTypeId(borrowVo.getId());
							mailSendRecord.setType(11);//对账复审，借款标异常
							mailSendRecord.setStatus(0);//0：未发送
							mailSendRecord.setAddtime(new Date());
							baseMailSendRecordMapper.insertEntity(mailSendRecord);
							
							return "借款标异常,已成功借到的金额和成功投标记录金额不等";
						}
						
						if(bv.getStatus()!=6){
							//记录异常信息,发送邮件给相关人员
							String remark="借款标异常,借款标状态不是银行复审中";
							this.saveErrorBorrow(1, addUserId, borrowVo.getId(), remark);
							//新增邮件待发记录 
							MailSendRecord mailSendRecord=new MailSendRecord();
							mailSendRecord.setTypeId(borrowVo.getId());
							mailSendRecord.setType(13);//满标复审，借款标异常
							mailSendRecord.setStatus(0);
							mailSendRecord.setAddtime(new Date());
							baseMailSendRecordMapper.insertEntity(mailSendRecord);
							
							return "借款标异常,借款标状态不是银行复审中";
						}
						
						//更新标的状态
						Borrow borrow=new Borrow();
						borrow.setId(borrowVo.getId());
						borrow.setStatus(4);//还款中
						borrow.setApprstatus(4);//复审通过
						borrowMapper.updateBorrowStatusById(borrow);
						//更新投标记录状态
						tenderRecordMapper.updateTenderrecordStatus(borrowVo.getId());
						//更新借款标审核记录
						BorrowApproved borrowApproved=new BorrowApproved();
						borrowApproved.setBorrowId(borrowVo.getId());
						borrowApproved.setVerifyUser4(addUserId);
						borrowApproved.setVerifyRemark4(approvedRemark);
						baseBorrowApprovedMapper.updateBorrowApprovedRecheck(borrowApproved);
						
						/***根据投标记录生成待收记录**/
						for (TenderRecord tenderRecord : tenderRecordList) {
							//锁定投资用户
							AccountVo accountVo = accountMapper.queryAccountByUserIdForUpdate(tenderRecord.getUserId());
							
							
							BaseEBankInfo baseEBankInfo = baseEBankInfoMapper.selectByUserId(tenderRecord.getUserId());
							//等额本息
							if(borrowVo.getStyle()==1){
							//前几个月的本金
							BigDecimal collectionCapitalTotal=BigDecimal.ZERO;
							//每月待收总额
							BigDecimal collectionAccount=borrowMapper.findCollectionAccount(tenderRecord.getAccount(), borrowVo.getApr(), borrowVo.getTimeLimit());
							//生成待收	
							for(int i=1;i<=borrowVo.getTimeLimit();i++){
								//待收本金
								BigDecimal collectionCapital;
								//待收利息
								BigDecimal collectionInterest;
								//如果是最后一笔
								/**最后一笔待收时： 本金=原有投标金额-前几个月的本金 ；利息=待收本息-本金*/
								if(i==borrowVo.getTimeLimit()){
									//待收本金
									collectionCapital=tenderRecord.getAccount().subtract(collectionCapitalTotal);
									//待收利息
									collectionInterest=collectionAccount.subtract(collectionCapital);
								}else{
									Map<String, Object> map=new HashMap<String, Object>();
									map.put("tenderAccount", tenderRecord.getAccount());
									map.put("apr", borrowVo.getApr());
									map.put("timeFlag", i);
									map.put("collectionAccount", collectionAccount);
									collectionInterest= borrowMapper.getFqIns(map);
									collectionCapital=collectionAccount.subtract(collectionInterest);
									collectionCapitalTotal=collectionCapitalTotal.add(collectionCapital);
								}
								//估计还款时间
								Integer repayTime =borrowMapper.findRepayTime(i,borrowVo.getSuccessTime());
								//新增待收记录
								BCollectionRecordVo bCollectionRecordVo=new BCollectionRecordVo();
								bCollectionRecordVo.setStatus(0);//尚未还款
								bCollectionRecordVo.setOrder(i);
								bCollectionRecordVo.setTenderId(tenderRecord.getId());
								bCollectionRecordVo.setRepayTime(repayTime.toString());
								bCollectionRecordVo.setRepayAccount(collectionAccount);
								bCollectionRecordVo.setInterest(collectionInterest);
								bCollectionRecordVo.setCapital(collectionCapital);
								bCollectionRecordVo.setAddip(addIp);
								bCollectionRecordVo.setIsFirstBorrow(2);
								bCollectionRecordVo.setUserId(tenderRecord.getUserId());
								bCollectionRecordVo.setBorrowId(tenderRecord.getBorrowId());
								bCollectionRecordVo.setIsCustody(1);
								bCollectionRecordVo.seteBankInfoNo(baseEBankInfo.getId());
								
								bCollectionrecordMapper.insertCollectionrecord(bCollectionRecordVo);
								
							}
							
							//按月付息到期还本	
							}else if(borrowVo.getStyle()==2){
								//前几期的利息之和
								BigDecimal collectionInterestSum = BigDecimal.ZERO;
								for(int i=1;i<=borrowVo.getTimeLimit();i++){
									//每月待收利息
									BigDecimal collectionInterest;
									//每月待收本金
									BigDecimal collectionCapital;
									
									if(i==borrowVo.getTimeLimit()){
										collectionCapital=tenderRecord.getAccount();
										collectionInterest=tenderRecord.getInterest().subtract(collectionInterestSum);
									}else{
										collectionCapital=BigDecimal.ZERO;
										collectionInterest=tenderRecord.getAccount().multiply(borrowVo.getApr()).divide(new BigDecimal(100*12),10,BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
										collectionInterestSum=collectionInterestSum.add(collectionInterest);
									}
									//估计还款时间
									Integer repayTime =borrowMapper.findRepayTime(i,borrowVo.getSuccessTime());
									//每月待收总额
									BigDecimal collectionAccount=collectionInterest.add(collectionCapital);
									//新增待收记录
									BCollectionRecordVo bCollectionRecordVo=new BCollectionRecordVo();
									bCollectionRecordVo.setStatus(0);//尚未还款
									bCollectionRecordVo.setOrder(i);
									bCollectionRecordVo.setTenderId(tenderRecord.getId());
									bCollectionRecordVo.setRepayTime(repayTime.toString());
									bCollectionRecordVo.setRepayAccount(collectionAccount);
									bCollectionRecordVo.setInterest(collectionInterest);
									bCollectionRecordVo.setCapital(collectionCapital);
									bCollectionRecordVo.setAddip(addIp);
									bCollectionRecordVo.setIsFirstBorrow(2);
									bCollectionRecordVo.setUserId(tenderRecord.getUserId());
									bCollectionRecordVo.setBorrowId(tenderRecord.getBorrowId());
									bCollectionRecordVo.setIsCustody(1);
									bCollectionRecordVo.seteBankInfoNo(baseEBankInfo.getId());
									bCollectionrecordMapper.insertCollectionrecord(bCollectionRecordVo);
									
								}
								
							}else{
								//估计还款时间
								Integer repayTime = null;
								//待收利息
								BigDecimal collectionInterest = null;
								//到期还本付息
								if(borrowVo.getStyle()==3){
									//待收利息
									collectionInterest=tenderRecord.getAccount().multiply(borrowVo.getApr()).divide(new BigDecimal(100*12),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(borrowVo.getTimeLimit())).setScale(2, BigDecimal.ROUND_HALF_UP);
									//估计还款时间
									repayTime =borrowMapper.findRepayTime(borrowVo.getTimeLimit(),borrowVo.getSuccessTime());
								//按天还款
								}else if(borrowVo.getStyle()==4){
									//待收利息
									collectionInterest=tenderRecord.getAccount().multiply(borrowVo.getApr()).divide(new BigDecimal(100*360),10,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(borrowVo.getTimeLimit())).setScale(2, BigDecimal.ROUND_HALF_UP);
									//估计还款时间
									repayTime =borrowMapper.findDayRepayTime(borrowVo.getTimeLimit(),borrowVo.getSuccessTime());
								}
								
								//新增待收记录
								BCollectionRecordVo bCollectionRecordVo=new BCollectionRecordVo();
								bCollectionRecordVo.setStatus(0);//尚未还款
								bCollectionRecordVo.setOrder(1);
								bCollectionRecordVo.setTenderId(tenderRecord.getId());
								bCollectionRecordVo.setRepayTime(repayTime.toString());
								bCollectionRecordVo.setRepayAccount(collectionInterest.add(tenderRecord.getAccount()));
								bCollectionRecordVo.setInterest(collectionInterest);
								bCollectionRecordVo.setCapital(tenderRecord.getAccount());
								bCollectionRecordVo.setAddip(addIp);
								bCollectionRecordVo.setIsFirstBorrow(2);
								bCollectionRecordVo.setUserId(tenderRecord.getUserId());
								bCollectionRecordVo.setBorrowId(tenderRecord.getBorrowId());
								bCollectionRecordVo.setIsCustody(1);
								bCollectionRecordVo.seteBankInfoNo(baseEBankInfo.getId());
								
								bCollectionrecordMapper.insertCollectionrecord(bCollectionRecordVo);
								
							}
							
							//更新投资人账户表
							//扣除账户存管投资冻结金额
							Account account=new Account();
							account.setId(accountVo.getId());
							account.setTotal(accountVo.getTotal().subtract(tenderRecord.getAccount()));
							account.seteFreezeMoney(accountVo.geteFreezeMoney().subtract(tenderRecord.getAccount()));
							accountMapper.updateEntity(account);
							//添加账户日志
							AccountLog accountLog=new AccountLog();
							accountLog.setUserId(accountVo.getUserId());
							accountLog.setType(CGBusinessConstants.TS);
							accountLog.setMoney(tenderRecord.getAccount());
							accountLog.setToUser(tenderRecord.getUserId());
							accountLog.setIdType(0);
							accountLog.setBorrowId(borrowVo.getId());
							accountLog.setBorrowName(borrowVo.getName());
							accountLog.setRemark("存管复审通过，扣除账户冻结金额");
							accountLog.setAddip(addIp);
							accountLog.setIsCustody(1);//浙商存管
							accountLogMapper.insertAccountLog(accountLog);
							
							/**待收金额增加**/
							//计算投资人的待收总额
							BCollectionRecordVo bCollectionRecordVo=new BCollectionRecordVo();
							bCollectionRecordVo.setUserId(accountVo.getUserId());
							bCollectionRecordVo.setBorrowId(borrowVo.getId());
							bCollectionRecordVo.setTenderId(tenderRecord.getId());
							BigDecimal repyAccount = bCollectionrecordMapper.findSumRepyAccount(bCollectionRecordVo);
							//增加存管待收总额
							Account account2=new Account();
							account2.setId(accountVo.getId());
							account2.setTotal(account.getTotal().add(repyAccount));
							account2.seteCollection(accountVo.geteCollection().add(repyAccount));
							accountMapper.updateEntity(account2);
							//添加账户日志
							AccountLog accountLog1=new AccountLog();
							accountLog1.setUserId(accountVo.getUserId());
							accountLog1.setType(CGBusinessConstants.CD);
							accountLog1.setMoney(repyAccount);
							accountLog1.setToUser(tenderRecord.getUserId());
							accountLog1.setIdType(0);
							accountLog.setBorrowId(borrowVo.getId());
							accountLog.setBorrowName(borrowVo.getName());
							accountLog1.setRemark("存管复审通过，待收金额增加");
							accountLog1.setAddip(addIp);
							accountLog1.setIsCustody(1);//浙商存管
							accountLog1.setBorrowId(borrowVo.getId());
							accountLog1.setBorrowName(borrowVo.getName());
							accountLogMapper.insertAccountLog(accountLog1);
							
							
						}
						
						/***** 生成待还记录**********/
						
						//锁定借款人账户表
						AccountVo accountVo = accountMapper.queryAccountByUserIdForUpdate(borrowVo.getUserId());
						if(borrowVo.getStyle()==1 || borrowVo.getStyle()==2){
							for(int i=1;i<=borrowVo.getTimeLimit();i++){
								//每月待还总额
								BorrowCheckVo borrowCheckVo=bCollectionrecordMapper.findCollectionAccount(borrowVo.getId(), i);
								//估计还款时间
								Integer repayTime;
								
								//估计还款时间
								repayTime =borrowMapper.findRepayTime(i,borrowVo.getSuccessTime());
								
								BRepaymentRecordVo bRepaymentRecordVo=new BRepaymentRecordVo();
								bRepaymentRecordVo.setStatus(0);
								bRepaymentRecordVo.setWebstatus(0);
								bRepaymentRecordVo.setOrder(i);
								bRepaymentRecordVo.setBorrowId(borrowVo.getId());
								bRepaymentRecordVo.setRepaymentTime(repayTime.toString());
								bRepaymentRecordVo.setRepaymentAccount(borrowCheckVo.getSumRepayAccount());
								bRepaymentRecordVo.setInterest(borrowCheckVo.getSumInterest());
								bRepaymentRecordVo.setCapital(borrowCheckVo.getSumCapital());
								bRepaymentRecordVo.setAddip(addIp);
								bRepaymentRecordVo.setUserId(borrowVo.getUserId());
								bRepaymentRecordVo.setIsRepairInterest(0);
								bRepaymentRecordVo.setIsCustody(1);
								bRepaymentRecordMapper.insertRepaymentrecord(bRepaymentRecordVo);
							}
						}else{
							//每月待还总额
							BorrowCheckVo borrowCheckVo=bCollectionrecordMapper.findCollectionAccount(borrowVo.getId(), 1);
							//估计还款时间
							Integer repayTime;
							//按天还款
							if(borrowVo.getStyle()==4){
								//估计还款时间
								repayTime =borrowMapper.findDayRepayTime(borrowVo.getTimeLimit(),borrowVo.getSuccessTime());
							}else{
							//估计还款时间
								repayTime =borrowMapper.findRepayTime(borrowVo.getTimeLimit(),borrowVo.getSuccessTime());
							}
							BRepaymentRecordVo bRepaymentRecordVo=new BRepaymentRecordVo();
							bRepaymentRecordVo.setStatus(0);
							bRepaymentRecordVo.setWebstatus(0);
							bRepaymentRecordVo.setOrder(1);
							bRepaymentRecordVo.setBorrowId(borrowVo.getId());
							bRepaymentRecordVo.setRepaymentTime(repayTime.toString());
							bRepaymentRecordVo.setRepaymentAccount(borrowCheckVo.getSumRepayAccount());
							bRepaymentRecordVo.setInterest(borrowCheckVo.getSumInterest());
							bRepaymentRecordVo.setCapital(borrowCheckVo.getSumCapital());
							bRepaymentRecordVo.setAddip(addIp);
							bRepaymentRecordVo.setUserId(borrowVo.getUserId());
							bRepaymentRecordVo.setIsRepairInterest(0);
							bRepaymentRecordVo.setIsCustody(1);
							bRepaymentRecordMapper.insertRepaymentrecord(bRepaymentRecordVo);
							
						}
							
							/**更新借款者账户**/
							Account account=new Account();
							account.setId(accountVo.getId());
							account.seteUseMoney(accountVo.geteUseMoney().add(borrowVo.getAccountYes()));
							account.setTotal(accountVo.getTotal().add(borrowVo.getAccountYes()));
							accountMapper.updateEntity(account);
							//添加账户日志
							AccountLog accountLog=new AccountLog();
							accountLog.setUserId(accountVo.getUserId());
							accountLog.setType(CGBusinessConstants.BS);
							accountLog.setMoney(borrowVo.getAccountYes());
							accountLog.setToUser(borrowVo.getId());
							accountLog.setBorrowId(borrowVo.getId());
							accountLog.setBorrowName(borrowVo.getName());
							accountLog.setIdType(0);
							accountLog.setRemark("存管复审通过，账户资金增加");
							accountLog.setAddip(addIp);
							accountLog.setIsCustody(1);//浙商存管
							accountLogMapper.insertAccountLog(accountLog);
						
						/********添加满标待发邮件***********/
						tenderRecordMapper.insertEmailRecord(borrowVo.getId());
							
							
						/******平台解冻划款失败的投资金额*********/
							//查询投资失败的投标记录
							List<TenderRecord> list=tenderRecordMapper.findFailureTenderrecord(borrowVo.getId());
							for (TenderRecord tenderRecord : list) {
								//查询用户账户，并锁定
								AccountVo av = accountMapper.queryAccountByUserIdForUpdate(tenderRecord.getUserId());
								//更新账户
								Account account1=new Account();
								account1.setId(av.getId());
								account1.seteUseMoney(av.geteUseMoney().add(tenderRecord.getAccount()));
								account1.seteFreezeMoney(av.geteFreezeMoney().subtract(tenderRecord.getAccount()));
								accountMapper.updateEntity(account1);
								//添加账户日志
								AccountLog accountLog1=new AccountLog();
								accountLog1.setUserId(av.getUserId());
								accountLog1.setType(CGBusinessConstants.TF);
								accountLog1.setMoney(tenderRecord.getAccount());
								accountLog1.setToUser(-2);//-2:存管系统
								accountLog1.setIdType(24);//存管复审通过，增加投资失败的金额
								accountLog1.setRemark("存管复审通过，增加投资失败的金额");
								accountLog1.setAddip(addIp);
								accountLog1.setIsCustody(1);//浙商存管
								accountLogMapper.insertAccountLog(accountLog1);
								
							}
						
					}else{
						//记录异常信息,发送邮件给相关人员
						logger.error("借款标满标状态异常,borrowId:"+borrowVo.getId());
						String remark="借款标满标状态异常,借款标的状态不是银行复审中";
						this.saveErrorBorrow(1, addUserId, borrowVo.getId(), remark);
						//新增邮件待发记录 
						MailSendRecord mailSendRecord=new MailSendRecord();
						mailSendRecord.setTypeId(borrowVo.getId());
						mailSendRecord.setType(13);//满标复审，借款标异常
						mailSendRecord.setStatus(0);
						mailSendRecord.setAddtime(new Date());
						baseMailSendRecordMapper.insertEntity(mailSendRecord);
						return "借款标异常,借款标状态不是银行复审中";
					}
				}else{
					//记录异常信息,发送邮件给相关人员
					logger.error("借款标满标状态异常,borrowId:"+borrowVo.getId());
					String remark="借款标满标状态异常,对账文件状态不是满标状态";
					this.saveErrorBorrow(1, addUserId, borrowVo.getId(), remark);
					//新增邮件待发记录 
					MailSendRecord mailSendRecord=new MailSendRecord();
					mailSendRecord.setTypeId(borrowVo.getId());
					mailSendRecord.setType(13);//满标复审，借款标异常
					mailSendRecord.setStatus(0);
					mailSendRecord.setAddtime(new Date());
					baseMailSendRecordMapper.insertEntity(mailSendRecord);
					return "借款标异常,操作状态不是满标";
				}
				return BusinessConstants.SUCCESS;
		
				
	}
	
	
	public void screenOutSuccessTenderrecord(List<EReconDetail> eReconDetailList,BorrowVo borrowVo){
		//根据对账记录明细循环操作得出投资成功的记录：
		for (EReconDetail eReconDetail : eReconDetailList) {
			//根据对账记录P2P平台流水号查询投标记录表,并锁定
			TenderRecord tenderRecord = tenderRecordMapper.findtenderrecordByInvestNo(eReconDetail.getP2pNo());
			//如果投资状态是失败
			if(eReconDetail.getStatus()==30){
				//更新投标记录表
				tenderRecordMapper.updateFailureTenderrecord(tenderRecord.getId());
				//更新借款标已投金额
				Borrow borrow=new Borrow();
				borrow.setId(borrowVo.getId());
				borrow.setAccount(borrowVo.getAccount().subtract(tenderRecord.getAccount()));
				borrow.setAccountYes(borrowVo.getAccountYes().subtract(tenderRecord.getAccount()));
				borrowMapper.updateBorrowAccount(borrow);
				
			}
			
		}
	}
	

	public String check(BorrowVo borrowVo,List<EReconDetail> eReconDetailList,String eProjectId,Integer borrowStatus,Integer userId) throws Exception{
		//校验已借到的金额和投标记录金额是否相等
		BorrowCheckVo bcv=borrowMapper.findTenderrecordSum(borrowVo.getId());
		if(borrowVo.getAccount().compareTo(bcv.getAccount())!=0){
			//记录异常信息,发送邮件给相关人员
			//添加异常记录，并邮件和短信通知相关人员，后台处理
			String remark="借款标异常,已借到的金额和投标记录金额不等";
			this.saveErrorBorrow(borrowStatus, userId, borrowVo.getId(), remark);
			//新增邮件待发记录 
			MailSendRecord mailSendRecord=new MailSendRecord();
			mailSendRecord.setTypeId(borrowVo.getId());
			mailSendRecord.setType(11);//对账复审，借款标异常
			mailSendRecord.setStatus(0);
			mailSendRecord.setAddtime(new Date());
			baseMailSendRecordMapper.insertEntity(mailSendRecord);
			return "借款标异常,已借到的金额和投标记录金额不等";
		}
		
		//校验投标记录数和对账明细记录数是否相等
		if(bcv.getCount()!=eReconDetailList.size()){
			//记录异常信息,发送邮件给相关人员
			String remark="借款标异常,标记录数和对账明细记录数不等";
			this.saveErrorBorrow(borrowStatus, userId, borrowVo.getId(), remark);
			//新增邮件待发记录 
			MailSendRecord mailSendRecord=new MailSendRecord();
			mailSendRecord.setTypeId(borrowVo.getId());
			mailSendRecord.setType(11);//对账复审，借款标异常
			mailSendRecord.setStatus(0);
			mailSendRecord.setAddtime(new Date());
			baseMailSendRecordMapper.insertEntity(mailSendRecord);
			return "借款标异常,标记录数和对账明细记录数不等";
		}
		
		//平台投标金额与对账文件投标金额校验
		BigDecimal reconDetailMoney=eReconDetailMapper.findReconDetailMoney(eProjectId, 1);
		if(reconDetailMoney.compareTo(bcv.getAccount())!=0){
			//记录异常信息,发送邮件给相关人员
			String remark="借款标异常,平台投标金额与对账文件投标金额不等";
			this.saveErrorBorrow(borrowStatus, userId, borrowVo.getId(), remark);
			//新增邮件待发记录 
			MailSendRecord mailSendRecord=new MailSendRecord();
			mailSendRecord.setTypeId(borrowVo.getId());
			mailSendRecord.setType(11);//对账复审，借款标异常
			mailSendRecord.setStatus(0);
			mailSendRecord.setAddtime(new Date());
			baseMailSendRecordMapper.insertEntity(mailSendRecord);
			return "借款标异常,平台投标金额与对账文件投标金额不等";
		}
		
		return BusinessConstants.SUCCESS;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:流标<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年6月1日
	 * @param eProjectId
	 * @param addIp
	 * @return
	 * @throws Exception
	 * String
	 */
	public String flowBorrow(String eProjectId,String addIp,Integer userId) throws Exception{
		
		//查询借款标并锁定
		BorrowVo borrowVo = borrowMapper.selectByProjectIdForUpdate(eProjectId);
		//流标操作
		EReconHeader eReconHeader =eReconHeaderMapper.findReconHeader(eProjectId, 1);
		if(eReconHeader.getStatus()==0){
			if(borrowVo.getStatus()==7){
				//查询批量扣款记录详情
				List<EReconDetail> eReconDetailList=eReconDetailMapper.findReconDetail(eProjectId, 1);
				//校验已借到的金额和投标记录金额是否相等
				BorrowCheckVo bcv=borrowMapper.findTenderrecordSum(borrowVo.getId());
				
				//校验投标记录数和对账明细记录数是否相等
				if(bcv.getCount()!=eReconDetailList.size()){
					//记录异常信息,发送邮件给相关人员
					String remark="借款标异常,标记录数和对账明细记录数不等";
					this.saveErrorBorrow(0, userId, borrowVo.getId(), remark);
					//新增邮件待发记录 
					MailSendRecord mailSendRecord=new MailSendRecord();
					mailSendRecord.setTypeId(borrowVo.getId());
					mailSendRecord.setType(11);//对账复审，借款标异常
					mailSendRecord.setStatus(0);
					mailSendRecord.setAddtime(new Date());
					baseMailSendRecordMapper.insertEntity(mailSendRecord);
					return "借款标异常,标记录数和对账明细记录数不等";
				}
				
				//平台投标金额与对账文件投标金额校验
				BigDecimal reconDetailMoney=eReconDetailMapper.findReconDetailMoney(eProjectId, 1);
				if(reconDetailMoney.compareTo(bcv.getAccount())!=0 || bcv.getAccount().compareTo(borrowVo.getAccountYes())!=0){
					//记录异常信息,发送邮件给相关人员
					String remark="借款标异常,平台投标金额与对账文件投标金额不等或平台投标金额与借款标已借到的金额不等";
					this.saveErrorBorrow(0, userId, borrowVo.getId(), remark);
					//新增邮件待发记录 
					MailSendRecord mailSendRecord=new MailSendRecord();
					mailSendRecord.setTypeId(borrowVo.getId());
					mailSendRecord.setType(11);//对账复审，借款标异常
					mailSendRecord.setStatus(0);
					mailSendRecord.setAddtime(new Date());
					baseMailSendRecordMapper.insertEntity(mailSendRecord);
					return "借款标异常,平台投标金额与对账文件投标金额不等或平台投标金额与借款标已借到的金额不等";
				}
				
				//更新借款标状态
				BorrowVo bv=new BorrowVo();
				bv.setId(borrowVo.getId());
				bv.setAccountYes(BigDecimal.ZERO);
				bv.setStatus(-2);//撤标
				bv.setCancelRemark("存管撤标");
				bv.setCancelUser(userId);
				borrowMapper.updateCGBorrow(bv);
				
				//插入撤标日志
				BorrowAdvanceLog borrowAdvanceLog=new BorrowAdvanceLog();
				borrowAdvanceLog.setBorrowId(borrowVo.getId());
				borrowAdvanceLog.setUserId(borrowVo.getUserId());
				borrowAdvanceLog.setAccount(borrowVo.getAccount());
				borrowAdvanceLog.setRealAccount(BigDecimal.ZERO);
				borrowAdvanceLog.setAddip(addIp);
				borrowAdvanceLog.setOperatorId(userId);
				borrowAdvanceLog.setPlatform(1);
				borrowAdvanceLog.setType(2);//2：撤标',
				borrowMapper.insertBorrowAdvanceLog(borrowAdvanceLog);
				
				
				//根据对账记录明细循环操作得出投资成功的记录：
				for (EReconDetail eReconDetail : eReconDetailList) {
					//根据对账记录P2P平台流水号查询投标记录表,并锁定
					TenderRecord tenderRecord = tenderRecordMapper.findtenderrecordByBizNo(eReconDetail.getP2pNo());
					//如果解冻成功
					if(eReconDetail.getStatus()==4){
						//更新投标记录
						tenderRecordMapper.updateFailureTenderrecord(tenderRecord.getId());
						//查询投资人账户，并锁定
						AccountVo accountVo = accountMapper.queryAccountByUserIdForUpdate(tenderRecord.getUserId());
						//更新账户资金
						Account account=new Account();
						account.setId(accountVo.getId());
						account.seteUseMoney(accountVo.geteUseMoney().add(eReconDetail.getMoney()));
						account.seteFreezeMoney(accountVo.geteFreezeMoney().subtract(eReconDetail.getMoney()));
						accountMapper.updateEntity(account);
						//添加账户日志
						AccountLog accountLog=new AccountLog();
						accountLog.setUserId(accountVo.getUserId());
						accountLog.setType(CGBusinessConstants.CTS);
						accountLog.setMoney(eReconDetail.getMoney());
						accountLog.setToUser(-2);//-2:存管系统
						accountLog.setIdType(25);//撤存管标
						accountLog.setRemark("撤存管标");
						accountLog.setAddip(addIp);
						accountLog.setIsCustody(1);//浙商存管
						accountLogMapper.insertAccountLog(accountLog);
						
					}else if(eReconDetail.getStatus()==5){
						//添加异常记录，并邮件和短信通知相关人员，后台处理
						BorrowerDealError borrowerDealError=new BorrowerDealError();
						borrowerDealError.setStatus(3);//流标解冻失败
						borrowerDealError.setBorrowId(borrowVo.getId());
						borrowerDealError.setP2pid(eReconDetail.getP2pNo());
						borrowerDealError.setSerialno(eReconDetail.getBankNo());
						borrowerDealError.setAccount(eReconDetail.getMoney());
						borrowerDealError.setTradetime(eReconDetail.getTradetime());
						borrowerDealError.setAdduser(userId);
						borrowerDealError.setDisposeStatus(0);//未处理
						borrowerDealError.setRemark("流标解冻失败，请联系银行线下沟通");
						borrowerDealErrorMapper.insert(borrowerDealError);
						
						//新增邮件待发记录 
						MailSendRecord mailSendRecord=new MailSendRecord();
						mailSendRecord.setTypeId(tenderRecord.getId());
						mailSendRecord.setType(12);//流标复审，用户解冻失败
						mailSendRecord.setStatus(0);
						mailSendRecord.setAddtime(new Date());
						baseMailSendRecordMapper.insertEntity(mailSendRecord);
						
					}
					
					if(tenderRecord.getTenderType()==1){
					//还原自动投标设置表排队时间
					AutoInvestConfigRecordVo autoInvestConfigRecordVo=autoInvestConfigRecordMapper.findAutoInvestRecord(tenderRecord.getId());
					if(autoInvestConfigRecordVo.getAuto_tender_id()!=null){
						AutoInvestConfigVo autoInvestConfigVo =autoInvestConfigMapper.findInvestConfig(autoInvestConfigRecordVo.getAuto_tender_id());
						//还原排队
						if(autoInvestConfigRecordVo.getAuto_tender_id()!=null && Integer.parseInt(autoInvestConfigVo.getUptime()) >Integer.parseInt(autoInvestConfigRecordVo.getUptime())){
							//更新自动投标日志记录
							autoInvestConfigMapper.updateAutoInvestConfig(autoInvestConfigRecordVo.getUptime(), autoInvestConfigRecordVo.getAuto_tender_id());
							autoInvestConfigRecordMapper.updateInvestConfigRecord(autoInvestConfigRecordVo.getId());
						}
					}
					}
					
					
				}
				
			}else{
				//记录异常信息,发送邮件给相关人员
				logger.error("借款标流标状态异常,borrowId:"+borrowVo.getId());
				
				String remark="借款标状态异常,不是银行流标处理中";
				this.saveErrorBorrow(0, userId, borrowVo.getId(), remark);
				//新增邮件待发记录 
				MailSendRecord mailSendRecord=new MailSendRecord();
				mailSendRecord.setTypeId(borrowVo.getId());
				mailSendRecord.setType(13);//流标复审，借款标异常
				mailSendRecord.setStatus(0);
				mailSendRecord.setAddtime(new Date());
				baseMailSendRecordMapper.insertEntity(mailSendRecord);
				return remark;
			}
		}else{
			//记录异常信息,发送邮件给相关人员
			logger.error("借款标流标状态异常,borrowId:"+borrowVo.getId());
			String remark="借款标状态异常,对账文件借款标不是流标状态";
			this.saveErrorBorrow(0, userId, borrowVo.getId(), remark);
			//新增邮件待发记录 
			MailSendRecord mailSendRecord=new MailSendRecord();
			mailSendRecord.setTypeId(borrowVo.getId());
			mailSendRecord.setType(13);//流标复审，借款标异常
			mailSendRecord.setStatus(0);
			mailSendRecord.setAddtime(new Date());
			baseMailSendRecordMapper.insertEntity(mailSendRecord);
			return remark;
			
		}	
		return BusinessConstants.SUCCESS;
	}
	
	
	public void saveErrorBorrow(Integer borrowStatus,Integer userId,Integer borrowId,String remark){
		Integer count= borrowerDealErrorMapper.findBorrowErrorCount(borrowId);
		if(count<=0){
		BorrowerDealError borrowerDealError=new BorrowerDealError();
		borrowerDealError.setStatus(borrowStatus);
		borrowerDealError.setBorrowId(borrowId);
		borrowerDealError.setAdduser(userId);
		borrowerDealError.setDisposeStatus(0);//未处理
		borrowerDealError.setRemark(remark);
		borrowerDealErrorMapper.insert(borrowerDealError);
		}
	}


    public void saveErrorBorrow(Integer borrowStatus,Integer userId,String projectId,String remark){
        //查询借款标并锁定
        Borrow borrowVo = borrowMapper.queryByProjectId(projectId);
        if (borrowVo != null) {
            this.saveErrorBorrow(borrowStatus, userId, borrowVo.getId(), remark);
        }
    }

}
