
package com.cxdai.console.borrow.approve.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cxdai.console.account.recharge.mapper.BaseAccountMapper;
import com.cxdai.console.base.entity.AccountError;
import com.cxdai.console.base.entity.BaseEBankInfo;
import com.cxdai.console.base.entity.MailSendRecord;
import com.cxdai.console.base.mapper.AccountErrorMapper;
import com.cxdai.console.base.mapper.BaseAccountLogMapper;
import com.cxdai.console.base.mapper.BaseEBankInfoMapper;
import com.cxdai.console.base.mapper.BaseMailSendRecordMapper;
import com.cxdai.console.borrow.approve.entity.InvestBorrow;
import com.cxdai.console.borrow.approve.entity.TenderBorrowCnd;
import com.cxdai.console.borrow.approve.entity.TenderRecord;
import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.check.entity.BorrowerDealError;
import com.cxdai.console.borrow.check.mapper.BorrowerDealErrorMapper;
import com.cxdai.console.borrow.emerg.mapper.TenderRecordMapper;
import com.cxdai.console.cg.entity.MessageRecord;
import com.cxdai.console.cg.mapper.MessageRecordMapper;
import com.cxdai.console.common.custody.xml.AQReq;
import com.cxdai.console.common.custody.xml.FBReq;
import com.cxdai.console.common.custody.xml.FBRes;
import com.cxdai.console.common.custody.xml.Finance;
import com.cxdai.console.common.custody.xml.Message;
import com.cxdai.console.common.custody.xml.PIReq;
import com.cxdai.console.common.custody.xml.PIReqTwo;
import com.cxdai.console.common.custody.xml.PTRReq;
import com.cxdai.console.common.custody.xml.PTRRes;
import com.cxdai.console.common.custody.xml.PTRResList;
import com.cxdai.console.common.custody.xml.Record;
import com.cxdai.console.common.custody.xml.RecordList;
import com.cxdai.console.common.custody.xml.UFBRes;
import com.cxdai.console.common.custody.xml.XmlUtil;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.common.statics.CGBusinessConstants;
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.statistics.account.entity.Account;
import com.cxdai.console.statistics.account.entity.AccountLog;
import com.cxdai.console.statistics.account.mapper.AccountMapper;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.exception.AppException;
import com.cxdai.console.util.serialnumberutil.UUIDGenerator;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CGUtilServiceImpl.java
 * @package com.cxdai.portal.account.service.impl 
 * @author tanghaitao
 * @version 0.1 2016年5月19日
 */

@Service
@Transactional
public class CGUtilService{

	public Logger logger = Logger.getLogger(CGUtilService.class);
	
	@Autowired
    private BaseEBankInfoMapper baseEBankInfoMapper;
	@Autowired
	private MessageRecordMapper messageRecordMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private BaseAccountMapper baseAccountMapper;
	@Autowired
	private BaseAccountLogMapper baseAccountLogMapper;
	@Autowired
	private AccountErrorMapper accountErrorMapper;
	@Autowired
	private BorrowService borrowService;
	@Autowired
	private BorrowMapper borrowMapper;
	@Autowired
	private BaseMailSendRecordMapper baseMailSendRecordMapper;
	@Autowired
	private BorrowerDealErrorMapper borrowerDealErrorMapper;
	@Autowired
	private TenderRecordMapper tenderRecordMapper;
	/**
	 * 
	 * <p>
	 * Description:查询存管账户余额<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月19日
	 * @param userId
	 * @return
	 * Account
	 */
	public String saveCGAccount(ShiroUser shiroUser,String relateNo,String remark,Integer borrowId) throws Exception{
		BaseEBankInfo baseEBankInfo=baseEBankInfoMapper.selectByUserId(shiroUser.getUserId());
		
		String reqMessage=this.createPIReqXml(baseEBankInfo);
		System.out.println(reqMessage);
		String reqXml= XmlUtil.sign(reqMessage, CGBusinessConstants.AQReq);
		//余额接口调用
		String rep= XmlUtil.send(reqXml);
		//记录项目登记日志
		MessageRecord messageRecord=new MessageRecord();
		messageRecord.setMode("5");//场景 5:余额查询
		messageRecord.setType(1);//1:主动，2:被动
		messageRecord.setMsg(reqXml);//报文体
		messageRecord.setOrderNo(borrowId.toString());
		messageRecord.setOptUserid(shiroUser.getUserId());
		messageRecord.setPlatform(shiroUser.getPlatform());
		messageRecord.setRemark(remark);
		messageRecord.setRelateNo(relateNo);//调用关联号
		messageRecord.setBindNo(baseEBankInfo.getBindNo());
		messageRecordMapper.insert(messageRecord);
		return rep;
		
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:调用资金冻结接口<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月23日
	 * @param shiroUser
	 * @param relateNo
	 * @param remark
	 * @return
	 * @throws Exception
	 * String
	 */
	public String saveFBReq(ShiroUser shiroUser,String relateNo,String remark,TenderBorrowCnd tenderBorrowCnd,String mode,String p2pDJ) throws Exception{
		// 查询借款标并锁定
		BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(tenderBorrowCnd.getBorrowid());
		BigDecimal syAccount=borrowVo.getAccount().subtract(borrowVo.getAdvance());
		if(tenderBorrowCnd.getTenderMoney().compareTo(syAccount)==1){
			throw new AppException("剩余投标金额不足");
		}
		BorrowVo borrow=new BorrowVo();
		borrow.setId(borrowVo.getId());
		borrow.setAdvance(borrowVo.getAdvance().add(tenderBorrowCnd.getTenderMoney()));
		borrowMapper.updateBorrow(borrow);
		BaseEBankInfo baseEBankInfo=baseEBankInfoMapper.selectByUserId(shiroUser.getUserId());
		String reqMessage=this.createFBReq(baseEBankInfo, borrowVo, tenderBorrowCnd,p2pDJ);
		System.out.println(reqMessage);
		String reqXml= XmlUtil.sign(reqMessage, CGBusinessConstants.FBReq);
		//资金冻结接口调用
		String rep= XmlUtil.send(reqXml);
		this.insertMsg(reqXml, mode, 1, shiroUser, remark, relateNo,tenderBorrowCnd.getBorrowid());
		
		return rep;
		
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:解析余额报文<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月19日
	 * @return
	 * Account
	 */
	public Account parseAQReqXml(String repXml,ShiroUser shiroUser,String remark,String relateNo,Integer borrowId) throws Exception{
		//记录响应报文
		this.insertMsg(repXml, "5", 2, shiroUser, remark, relateNo,borrowId);
		//判断响应报文
				boolean isError=XmlUtil.checkXml(repXml);
				if(isError){
					logger.error("响应ERROR报文:"+repXml);
				}		
				//验签
				boolean istrue= XmlUtil.checkYq(repXml);
				if(!istrue){
					logger.error("验签失败");
				}
		if(!isError && istrue){
			Account account=new Account();
			Map<String, Object> map= XmlUtil.toBiz(repXml, CGBusinessConstants.AQRes);	
			account.seteUseMoney(new BigDecimal((String)map.get("totalAmount")).divide(new BigDecimal(100)));
			account.seteFreezeMoney(new BigDecimal((String)map.get("freezeAmout")).divide(new BigDecimal(100)));
			account.setZsWithdrawamount(new BigDecimal((String)map.get("withdrawAmount")).divide(new BigDecimal(100)));
			return account;
		}else{
			return null;
		}
		
		
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:解析投资资金冻结响应报文<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月23日
	 * @param repXml
	 * @param shiroUser
	 * @param remark
	 * @param relateNo
	 * @param mode
	 * @return
	 * @throws Exception
	 * FBRes
	 */
	public FBRes parseFBResXml(String repXml,ShiroUser shiroUser,String remark,String relateNo,String mode,TenderBorrowCnd tenderBorrowCnd) throws Exception{
		try {
			String msg= this.saveResXml(repXml, mode, shiroUser, remark, relateNo,tenderBorrowCnd.getBorrowid());
			if(!msg.equals(BusinessConstants.SUCCESS)){
				BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(tenderBorrowCnd.getBorrowid());
				BorrowVo borrow=new BorrowVo();
				borrow.setId(borrowVo.getId());
				borrow.setAdvance(borrowVo.getAdvance().subtract(tenderBorrowCnd.getTenderMoney()));
				borrowMapper.updateBorrowAdvance(borrow);
				return null;
			}
			FBRes fBRes = new FBRes();
			Map<String, Object> map= XmlUtil.toBiz(repXml, CGBusinessConstants.FBRes);	
			fBRes.setSerialNo((String)map.get("serialNo"));
			fBRes.setBlockStatus((String)map.get("BlockStatus"));
			fBRes.setInstSettleDate((String)map.get("instSettleDate"));
			return fBRes;
		} catch (Exception e) {
			logger.error(repXml);
			logger.error(e);
			// 查询借款标并锁定
			BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(tenderBorrowCnd.getBorrowid());
			BorrowVo borrow=new BorrowVo();
			borrow.setId(borrowVo.getId());
			borrow.setAdvance(borrowVo.getAdvance().subtract(tenderBorrowCnd.getTenderMoney()));
			borrowMapper.updateBorrowAdvance(borrow);
			return null;
		}
		
		
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:投资冻结解冻<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年6月7日
	 * @param repXml
	 * @param shiroUser
	 * @param remark
	 * @param relateNo
	 * @param mode
	 * @param tenderBorrowCnd
	 * @param oriSerialNo
	 * @return
	 * @throws Exception
	 * UFBRes
	 */
	public String parseUFBResXml(String repXml,ShiroUser shiroUser,String remark,String relateNo,String mode,TenderBorrowCnd tenderBorrowCnd,String oriSerialNo) throws Exception{
		
			String msg= this.saveResXml(repXml, mode, shiroUser, remark, relateNo,tenderBorrowCnd.getBorrowid());
			if(!msg.equals(BusinessConstants.SUCCESS)){
				//添加异常记录，并邮件和短信通知相关人员，后台处理
				BorrowerDealError borrowerDealError=new BorrowerDealError();
				borrowerDealError.setStatus(2);//投标冻结解冻
				borrowerDealError.setBorrowId(tenderBorrowCnd.getBorrowid());
				//borrowerDealError.setSerialno(uFBRes.getSerialNo());
				borrowerDealError.setAccount(tenderBorrowCnd.getTenderMoney());
				borrowerDealError.setAdduser(-2);
				borrowerDealError.setDisposeStatus(0);//未处理
				borrowerDealError.setUserId(shiroUser.getUserId());
				borrowerDealError.setOriSerialNo(oriSerialNo);
				borrowerDealError.setRemark("投标冻结");
				borrowerDealErrorMapper.insert(borrowerDealError);
				
				//新增邮件待发记录 
				MailSendRecord mailSendRecord=new MailSendRecord();
				mailSendRecord.setTypeId(tenderBorrowCnd.getBorrowid());
				mailSendRecord.setType(10);//投标冻结解冻失败
				mailSendRecord.setStatus(0);
				mailSendRecord.setAddtime(new Date());
				baseMailSendRecordMapper.insertEntity(mailSendRecord);
				
				return null;
			}
			try {
				
				UFBRes uFBRes = new UFBRes();
				Map<String, Object> map= XmlUtil.toBiz(repXml, CGBusinessConstants.UFBRes);	
				uFBRes.setUnfreezeStatus((String)map.get("unfreezeStatus"));
				uFBRes.setSerialNo((String)map.get("serialNo"));
				//解冻成功
				if(uFBRes.getUnfreezeStatus().equals("20")){
					try {
						// 投资失败，冻结总金额减去投资金额
						BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(tenderBorrowCnd.getBorrowid());
						BorrowVo borrow=new BorrowVo();
						borrow.setId(borrowVo.getId());
						borrow.setAdvance(borrowVo.getAdvance().subtract(tenderBorrowCnd.getTenderMoney()));
						borrowMapper.updateBorrowAdvance(borrow);
					} catch (Exception e) {
						logger.error("投资失败，冻结总金额减去投资金额操作异常",e);
						return "冻结总金额减去投资金额操作异常";
					}
					
				}else{
					//记录解冻失败记录。后台手动解冻
					//添加异常记录，并邮件和短信通知相关人员，后台处理
					BorrowerDealError borrowerDealError=new BorrowerDealError();
					borrowerDealError.setStatus(2);//投标冻结解冻
					borrowerDealError.setBorrowId(tenderBorrowCnd.getBorrowid());
					borrowerDealError.setSerialno(uFBRes.getSerialNo());
					borrowerDealError.setAccount(tenderBorrowCnd.getTenderMoney());
					borrowerDealError.setAdduser(-2);
					borrowerDealError.setDisposeStatus(0);//未处理
					borrowerDealError.setUserId(shiroUser.getUserId());
					borrowerDealError.setOriSerialNo(oriSerialNo);
					borrowerDealError.setRemark("投标冻结");
					borrowerDealErrorMapper.insert(borrowerDealError);
					
					//新增邮件待发记录 
					MailSendRecord mailSendRecord=new MailSendRecord();
					mailSendRecord.setTypeId(tenderBorrowCnd.getBorrowid());
					mailSendRecord.setType(10);//投标冻结解冻失败
					mailSendRecord.setStatus(0);
					mailSendRecord.setAddtime(new Date());
					baseMailSendRecordMapper.insertEntity(mailSendRecord);
				}
				
			} catch (Exception e) {
				logger.error("投资冻结解冻操作异常",e);
				return "投资冻结解冻操作异常";
			}
			
			return BusinessConstants.SUCCESS;
		
		
	}
	
	
	
	
	/**
	 * 
	 * <p>
	 * Description:对比用户在平台与存管账户<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月19日
	 * @param account
	 * void
	 */
	public String savecheckAccount(Account account,String addip,Integer platform,String scene) throws Exception{
		String msg="success";
		
		AccountVo accountVo = accountMapper.findAccountByUserIdForUpdate(account.getUserId());
		
		MemberVo memberVo=memberMapper.queryPasswordInfoById(account.getUserId());
		//E户
		if(memberVo.geteType()==1){
			if(accountVo.geteUseMoney().compareTo(account.geteUseMoney())!=0 || accountVo.geteFreezeMoney().compareTo(account.geteFreezeMoney())!=0){
				List<AccountError> list= accountErrorMapper.findAccountErrorByUserId(account.getUserId());
				if(list.size()>0){
					AccountError ae=list.get(0);
					//判断账户异常未处理的记录与存管金额对比
					if(ae.getZsEUseMoney().compareTo(account.geteUseMoney())!=0 || ae.getZsEFreezeMoney().compareTo(account.geteFreezeMoney())!=0 || ae.getZsWithdrawamount().compareTo(account.getZsWithdrawamount())!=0){
						//邮件提醒相关人员(骆彬彬，马导，李刚，黄拼，王前进)
						//新增邮件待发记录 
						/*MailSendRecord mailSendRecord=new MailSendRecord();
						mailSendRecord.setTypeId(account.getUserId());
						mailSendRecord.setType(9);//平台与存管账户资金不等
						mailSendRecord.setStatus(0);
						mailSendRecord.setAddtime(new Date());
						baseMailSendRecordMapper.insertEntity(mailSendRecord);*/
						
						//记录账户异常信息日志
						accountErrorMapper.insert(this.beanAccountError(accountVo, account, addip, platform, scene));
					}
				}else{
					//新增邮件待发记录 
					/*MailSendRecord mailSendRecord=new MailSendRecord();
					mailSendRecord.setTypeId(account.getUserId());
					mailSendRecord.setType(9);//平台与存管账户资金不等
					mailSendRecord.setStatus(0);
					mailSendRecord.setAddtime(new Date());
					baseMailSendRecordMapper.insertEntity(mailSendRecord);*/
					//记录账户异常信息日志
					accountErrorMapper.insert(this.beanAccountError(accountVo, account, addip, platform, scene));
				}
				//return "账户异常，请联系客服!";
			}
				
			}else if(memberVo.geteType()==2){//商卡
				//以存管账户为主，更新平台账户
				if(accountVo.geteUseMoney().compareTo(account.geteUseMoney())!=0 ){
					BigDecimal money=account.geteUseMoney().subtract(accountVo.geteUseMoney());
					Account at=new Account();
					at.setId(accountVo.getId());
					at.seteUseMoney(account.geteUseMoney());
					at.setTotal(accountVo.getTotal().add(money));
					baseAccountMapper.updateAccount(at);
					//记录账户日志
					AccountLog accountLog=new AccountLog();
					accountLog.setUserId(account.getUserId());
					accountLog.setType(CGBusinessConstants.SK_SYN);
					accountLog.setMoney(new BigDecimal(Math.abs(money.doubleValue())));
					accountLog.setToUser(-2);//-2:存管系统
					accountLog.setIdType(20);//存管系统
					accountLog.setRemark("与存管系统商卡可用同步");
					accountLog.setAddip(addip);
					accountLog.setIsCustody(memberVo.getIsCustody());
					baseAccountLogMapper.insertAccountLog(accountLog);
					
				}
				
				if(accountVo.geteFreezeMoney().compareTo(account.geteFreezeMoney())!=0){
					BigDecimal money=account.geteFreezeMoney().subtract(accountVo.geteFreezeMoney());
					Account at=new Account();
					at.setId(accountVo.getId());
					at.seteFreezeMoney(account.geteFreezeMoney());
					at.setTotal(accountVo.getTotal().add(money));
					baseAccountMapper.updateAccount(at);
					//记录账户日志
					AccountLog accountLog=new AccountLog();
					accountLog.setUserId(account.getUserId());
					accountLog.setType(CGBusinessConstants.SK_SYN);
					accountLog.setMoney(new BigDecimal(Math.abs(money.doubleValue())));
					accountLog.setToUser(-2);//-2:存管系统
					accountLog.setIdType(21);//存管系统
					accountLog.setRemark("与存管系统商卡冻结同步");
					accountLog.setAddip(addip);
					accountLog.setIsCustody(memberVo.getIsCustody());
					baseAccountLogMapper.insertAccountLog(accountLog);
				}
				
			}
		return msg;
		
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:组建余额查询报文<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月19日
	 * @param baseEBankInfo
	 * @return
	 * String
	 */
	public String createPIReqXml(BaseEBankInfo baseEBankInfo){
		
		 XStream xstream = new XStream();
		 xstream = new XStream(new DomDriver());
		 Finance finance =new Finance();
		 Message message =new Message();
		 message.setId(UUIDGenerator.generate(CGBusinessConstants.MSGID));
		 AQReq aQReq =new AQReq();
		 aQReq.setDate(DateUtils.format(new Date(), DateUtils.YMDH_M_S));
		 aQReq.setBindSerialNo(baseEBankInfo.getBindNo());
		 aQReq.setAccNo(baseEBankInfo.getEcardNo());	
			xstream.autodetectAnnotations(true);
	        xstream.aliasField(CGBusinessConstants.AQReq, Message.class, "Mode");
	        xstream.aliasSystemAttribute(null, "class"); // 去掉 class 属性[/color]  
	        message.setMode(aQReq);
	        finance.setMessage(message);
		
		return xstream.toXML(finance);
		
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:组建资金冻结报文<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月23日
	 * @param baseEBankInfo
	 * @return
	 * String
	 */
	public String createFBReq(BaseEBankInfo baseEBankInfo,BorrowVo borrowVo,TenderBorrowCnd tenderBorrowCnd,String p2pDJ){
		 XStream xstream = new XStream();
		 xstream = new XStream(new DomDriver());
		 Finance finance =new Finance();
		 Message message =new Message();
		 message.setId(UUIDGenerator.generate(CGBusinessConstants.MSGID));
		 FBReq fBReq =new FBReq();
		 fBReq.setDate(DateUtils.format(new Date(), DateUtils.YMDH_M_S));
		 fBReq.setProjectId(borrowVo.geteProjectId());
		 fBReq.setProjectName(borrowVo.getName());
		 fBReq.setRealname(baseEBankInfo.getRealname());
		 fBReq.setCertType(baseEBankInfo.getCerttype().toString());
		 fBReq.setCertNo(baseEBankInfo.getCertNo());
		 fBReq.setBindSerialNo(baseEBankInfo.getBindNo());
		 fBReq.setP2PserialNo(p2pDJ);
		 fBReq.setInvestmentAmount(tenderBorrowCnd.getTenderMoney().multiply(new BigDecimal(100)).intValue());
		 fBReq.setCurrency("156");//156(人民币元)
			xstream.autodetectAnnotations(true);
	        xstream.aliasField(CGBusinessConstants.FBReq, Message.class, "Mode");
	        xstream.aliasSystemAttribute(null, "class"); // 去掉 class 属性[/color]  
	        message.setMode(fBReq);
	        finance.setMessage(message);
		
		return xstream.toXML(finance);
		
	}
	
	
	
	public AccountError beanAccountError(AccountVo accountVo,Account account,String addip,Integer platform,String scene){
		AccountError accountError=new AccountError();
		accountError.setUserId(account.getUserId());
		accountError.setTotal(accountVo.getTotal());
		accountError.setP2pNetvalue(accountVo.getNetvalue());
		accountError.setP2pUseMoney(accountVo.getUseMoney());
		accountError.setP2pNoUseMoney(accountVo.getNoUseMoney());
		accountError.setP2pCollection(accountVo.getCollection());
		accountError.setP2pFirstBorrowUseMoney(accountVo.getFirstBorrowUseMoney());
		accountError.setP2pVersion(accountVo.getVersion());
		accountError.setP2pDrawMoney(accountVo.getDrawMoney());
		accountError.setP2pNoDrawMoney(accountVo.getNoDrawMoney());
		accountError.setP2peUseMoney(accountVo.geteUseMoney());
		accountError.setP2peNoUseMoney(accountVo.geteFreezeMoney());
		accountError.setP2peCollection(accountVo.geteCollection());
		accountError.setZsEUseMoney(account.geteUseMoney());
		accountError.setZsEFreezeMoney(account.geteFreezeMoney());
		accountError.setZsWithdrawamount(accountVo.geteCollection());
		accountError.setZsWithdrawamount(account.getZsWithdrawamount());
		accountError.setStatus(0);//未处理
		accountError.setAddip(addip);
		accountError.setPlatform(platform);
		accountError.setScene(scene);
		return accountError;
	}
	
	
	
	public BaseEBankInfo eUserInfo(Integer userId) throws Exception{
		BaseEBankInfo baseEBankInfo=baseEBankInfoMapper.selectByUserId(userId);
		return baseEBankInfo;
	} 
	
	/**
	 * 
	 * <p>
	 * Description:查询该用户是否异常<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月21日
	 * @param userId
	 * @return
	 * @throws Exception
	 * Integer
	 */
	public Integer findAccountErrorByUserIdCount(Integer userId) throws Exception{
		return accountErrorMapper.findAccountErrorByUserIdCount(userId);
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:短信接口调用<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月21日
	 * @param shiroUser
	 * @param remark
	 * @param relateNo
	 * @return
	 * @throws Exception
	 * String
	 *//*
	public String saveMobileCode(ShiroUser shiroUser,String remark,String relateNo,Integer borrowId) throws Exception{
		BaseEBankInfo baseEBankInfo=baseEBankInfoMapper.selectByUserId(shiroUser.getUserId());
		//组建报文
		String reqMessage=this.createSSReqXml(baseEBankInfo);
		System.out.println(reqMessage);
		String reqXml=XmlUtil.sign(reqMessage, CGBusinessConstants.SSReq);
		//短信接口调用
		String rep= XmlUtil.send(reqXml);
		//记录接口调用记录
		this.insertMsg(reqXml, "13", 1, shiroUser, remark, relateNo,borrowId);
		return rep;
		
		
	}
	
	
	*//**
	 * 
	 * <p>
	 * Description:组建短信接口报文<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月21日
	 * @param baseEBankInfo
	 * @return
	 * String
	 *//*
	public String createSSReqXml(BaseEBankInfo baseEBankInfo){
	
		XStream xstream = new XStream();
		 xstream = new XStream(new DomDriver());
		 Finance finance =new Finance();
		 Message message =new Message();
		 message.setId(UUIDGenerator.generate(CGBusinessConstants.MSGID));
		 SSReq sSReq =new SSReq();
		 sSReq.setDate(DateUtils.format(new Date(), DateUtils.YMDH_M_S));
		 sSReq.setMobile(baseEBankInfo.getMobile());
		 sSReq.setSmsType("2");//2:资金冻结
			xstream.autodetectAnnotations(true);
	        xstream.aliasField(CGBusinessConstants.SSReq, Message.class, "Mode");
	        xstream.aliasSystemAttribute(null, "class"); // 去掉 class 属性[/color]  
	        message.setMode(sSReq);
	        finance.setMessage(message);
		
		return xstream.toXML(finance);
		
	}*/
	
	
	/**
	 * 
	 * <p>
	 * Description:记录报文<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月21日
	 * @param reqXml
	 * @param mode
	 * @param type
	 * @param shiroUser
	 * @param remark
	 * @param relateNo
	 * void
	 */
	public void insertMsg(String reqXml,String mode,int type,ShiroUser shiroUser,String remark,String relateNo,Integer borrowId,Integer msgType){
				
				BaseEBankInfo baseEBankInfo=baseEBankInfoMapper.selectByUserId(shiroUser.getUserId());
				//记录项目登记日志
				MessageRecord messageRecord=new MessageRecord();
				messageRecord.setMode(mode);//场景 
				messageRecord.setType(type);//1:主动，2:被动
				messageRecord.setMsg(reqXml);//报文体
				messageRecord.setMsgType(msgType);
				messageRecord.setOrderNo(borrowId.toString());
				messageRecord.setOptUserid(shiroUser.getUserId());
				messageRecord.setPlatform(shiroUser.getPlatform());
				messageRecord.setRemark(remark);
				messageRecord.setRelateNo(relateNo);//调用关联号
				if(baseEBankInfo!=null){
					messageRecord.setBindNo(baseEBankInfo.getBindNo());
				}
				messageRecordMapper.insert(messageRecord);
	}
	
	
	public void insertMsg(String reqXml,String mode,int type,ShiroUser shiroUser,String remark,String relateNo,Integer borrowId){
		
		BaseEBankInfo baseEBankInfo=baseEBankInfoMapper.selectByUserId(shiroUser.getUserId());
		//记录项目登记日志
		MessageRecord messageRecord=new MessageRecord();
		messageRecord.setMode(mode);//场景 
		messageRecord.setType(type);//1:主动，2:被动
		messageRecord.setMsg(reqXml);//报文体
		messageRecord.setOrderNo(borrowId.toString());
		messageRecord.setOptUserid(shiroUser.getUserId());
		messageRecord.setPlatform(shiroUser.getPlatform());
		messageRecord.setRemark(remark);
		messageRecord.setRelateNo(relateNo);//调用关联号
		if(baseEBankInfo!=null){
			messageRecord.setBindNo(baseEBankInfo.getBindNo());
		}
		messageRecordMapper.insert(messageRecord);
}
	
	/**
	 * 
	 * <p>
	 * Description:记录响应报文<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月21日
	 * @param repXml
	 * @param mode
	 * @param shiroUser
	 * @param remark
	 * @param relateNo
	 * @throws Exception
	 * void
	 */
	public String saveResXml(String repXml,String mode,ShiroUser shiroUser,String remark,String relateNo,Integer borrowId) throws Exception{
		String msg=BusinessConstants.SUCCESS;
		boolean isError=XmlUtil.checkXml(repXml);	
		boolean istrue= XmlUtil.checkYq(repXml);
		
			//判断响应报文
						if(isError){
							logger.info("响应ERROR报文:"+repXml);
							msg="响应ERROR报文";
						}		
						//验签
						if(!istrue){
							logger.info("验签失败"+repXml);
							msg= "验签失败";
						}
						if(msg.equals(BusinessConstants.SUCCESS)){
							//记录响应报文
							this.insertMsg(repXml, mode, 2, shiroUser, remark, relateNo,borrowId);					
						}else{
							//记录响应报文
							this.insertMsg(repXml, mode, 2, shiroUser, remark, relateNo,borrowId,1);					
						}
						
						
						return msg;
						
						
	}

	
	/*public List<MessageRecord> findMessageRecord(MessageRecord messageRecord){
		return messageRecordMapper.findMessageRecord(messageRecord);
	}
*/

	
	/**
	 * 
	 * <p>
	 * Description:调用项目登记接口<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月25日
	 * @param borrowVo
	 * @param shiroUser
	 * @param mode
	 * @param remark
	 * @param relateNo
	 * @return
	 * @throws Exception
	 * String
	 */
	public String savePIReq(BorrowVo borrowVo,ShiroUser shiroUser,String mode,String remark,String relateNo) throws Exception{
		String reqMessage= createPIReqXml(borrowVo);
		System.out.println(reqMessage);
		String reqXml= XmlUtil.sign(reqMessage, CGBusinessConstants.PIREQ);
		//调用项目登记接口
		String rep= XmlUtil.send(reqXml);
		this.insertMsg(reqXml, mode, 1, shiroUser, remark, relateNo,borrowVo.getId());
		return rep;
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
		 PIReqTwo pIReq =new PIReqTwo();
			pIReq.setDate(DateUtils.format(new Date(), DateUtils.YMDH_M_S));
			pIReq.setProjectId(borrowVo.geteProjectId());
		    pIReq.setProjectName(borrowVo.getName());
			pIReq.setProjectBeginTime(DateUtils.timeStampToDate(borrowVo.getPublishTime(), DateUtils.YMD));
			pIReq.setInvestDuration(DateUtils.dayDiff( DateUtils.monthOffset(DateUtils.timeStampToDate(borrowVo.getPublishTime()), borrowVo.getTimeLimit()),DateUtils.timeStampToDate(borrowVo.getPublishTime())));//融资期限
			pIReq.setAmount(borrowVo.getAccount().multiply(new BigDecimal(100)).intValue());
			pIReq.setAdvanceRepayment("1");
			pIReq.setOverdueRepayment("1");
			pIReq.setIsTransfer("1");
			
			pIReq.setProjectEndDate(DateUtils.timeStampToDate(borrowVo.getSuccessTime(), DateUtils.YMD));
			pIReq.setInterestBeginDate(DateUtils.timeStampToDate(((Integer)(Integer.parseInt(borrowVo.getSuccessTime())+24*60*60)).toString(),DateUtils.YMD));
			pIReq.setInterestEndDate(DateUtils.timeStampToDate(borrowVo.getEndTime(),DateUtils.YMD));
			pIReq.setRepayDate(DateUtils.timeStampToDate(borrowVo.getEndTime(),DateUtils.YMD));
			pIReq.setRepayRate(borrowVo.getApr());
			pIReq.setRepayName(borrowVo.getRepayName());
			pIReq.setRepayAcct(borrowVo.getRepayAcct());
			pIReq.setAdvancePay("1");//默认不垫资，1垫资
			
			xstream.autodetectAnnotations(true);
	        xstream.aliasField(CGBusinessConstants.PIREQ, Message.class, "Mode");
	        xstream.aliasSystemAttribute(null, "class"); // 去掉 class 属性[/color]  
	        message.setMode(pIReq);
	        finance.setMessage(message);
		
		return xstream.toXML(finance);
		
	}
	
	
	
	/**
	 * 获取总页数
	 */
	public Integer findTenderrecordInfoCount(Integer borrowId,Integer pageSize) throws Exception{
		Integer count= borrowMapper.findTenderrecordInfoCount(borrowId);
		Page page=new Page();
		page.setPageSize(pageSize);
		page.setTotalCount(count);
		return page.getTotalPage();
	}
	
	
	public Page findTenderrecordInfo(Integer borrowId, Page page) throws Exception{
		
		List<InvestBorrow> list= borrowMapper.findTenderrecordInfo(borrowId, page);
		Integer count =borrowMapper.findTenderrecordInfoCount(borrowId);
		page.setResult(list);
		page.setTotalCount(count);
		return page;
	}
	
	/**
	 * 项目投资信息登记接口调用
	 */
	public String savePTRReq(BorrowVo borrowVo,List<InvestBorrow> list,ShiroUser shiroUser,String remark,String mode,String relateNo) throws Exception{
		//生成项目投资报文
		String reqMessage= this.createPTRReqXml(borrowVo, list);
		System.out.println(reqMessage);
		String reqXml=XmlUtil.sign(reqMessage, CGBusinessConstants.PTRReq);
		//调用项目登记接口
		String rep= XmlUtil.send(reqXml);
		this.insertMsg(reqXml, mode, 1, shiroUser, remark, relateNo,borrowVo.getId());
		return rep;
		
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:组建项目投资信息登记报文<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月25日
	 * @param borrowVo
	 * @param list
	 * @return
	 * String
	 */
	public String createPTRReqXml(BorrowVo borrowVo,List<InvestBorrow> list){
		 XStream xstream = new XStream();
		 xstream = new XStream(new DomDriver());
		 Finance finance =new Finance();
		 Message message =new Message();
		 message.setId(UUIDGenerator.generate(CGBusinessConstants.MSGID));
		 PTRReq pTRReq =new PTRReq();
		 pTRReq.setDate(DateUtils.format(new Date(), DateUtils.YMDH_M_S));
		 pTRReq.setProjectId(borrowVo.geteProjectId());
		 pTRReq.setProjectName(borrowVo.getName());
		 pTRReq.setProjectStatus("1");//满标
		 pTRReq.setRepayName(borrowVo.getRepayName());
		 pTRReq.setRepayAcct(borrowVo.getRepayAcct());
		 pTRReq.setCount(list.size());
		
		 Integer amout = 0;
		 List<Record> record=new ArrayList<Record>();
		 for (InvestBorrow investBorrow : list) {
			 Record r=new Record();
			// r.setP2PserialNo(UUIDGenerator.getProjectId(CGBusinessConstants.P2P_SERIAL_NO, investBorrow.getInvestBorrowId()));
			 r.setP2PserialNo(investBorrow.getP2pSerialNo());
			 r.setRealname(investBorrow.getRealname());
			 r.setCertType(investBorrow.getCertType());
			 r.setCertNo(investBorrow.getCertNo());
			 r.setBindSerialNo(investBorrow.getBindSerialNo());
			 r.setPayNum(investBorrow.getPayNum());
			 r.setInvestmentAmount((investBorrow.getInvestmentAmount().multiply(new BigDecimal(100))).intValue());
			 r.setInterestCapital(investBorrow.getInterestCapital().multiply(new BigDecimal(100)).intValue());
			 amout=amout+r.getInvestmentAmount();
			 record.add(r);
		}
		 pTRReq.setAmout(amout);
		 RecordList recordList = new RecordList();
		 recordList.setRecordList(record);
		 pTRReq.setList(recordList);
		 
			xstream.autodetectAnnotations(true);
	        xstream.aliasField(CGBusinessConstants.PTRReq, Message.class, "Mode");
	        xstream.aliasSystemAttribute(null, "class"); // 去掉 class 属性[/color]  
	        message.setMode(pTRReq);
	        finance.setMessage(message);
		
		return xstream.toXML(finance);
		 
	
	}
	
	/**
	 * 
	 * <p>
	 * Description:解析项目投资登记报文<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月25日
	 * @param repXml
	 * @param mode
	 * @param shiroUser
	 * @param remark
	 * @param relateNo
	 * @return
	 * @throws Exception
	 * PTRRes
	 */
	public PTRRes parsePTRResXml(String repXml,String mode,ShiroUser shiroUser,String remark,String relateNo,Integer borrowId) throws Exception{
		String msg= this.saveResXml(repXml, mode, shiroUser, remark, relateNo,borrowId);
		if(!msg.equals(BusinessConstants.SUCCESS)){
			return null;
		}
		PTRRes pTRRes=new PTRRes();
		List<PTRResList> pTRResList = new ArrayList<PTRResList>();
		Map<String, Object> map= XmlUtil.toBiz(repXml, CGBusinessConstants.PTRRes);
		pTRRes.setSuccount(Integer.parseInt((String)map.get("succount")) );
		pTRRes.setSucsum(Integer.parseInt((String)map.get("sucsum")));
		pTRRes.setFailcount(Integer.parseInt((String)map.get("failcount")));
		pTRRes.setFailsum(Integer.parseInt((String)map.get("failsum")));
		
		List<Map> mapList= XmlUtil.toDetail(repXml, CGBusinessConstants.PTRRes);	
		for (Map map2 : mapList) {
			PTRResList pl=new PTRResList();
			pl.setP2PserialNo((String)map2.get("P2PserialNo"));
			pl.setInvestmentStatus((String)map2.get("InvestmentStatus"));
			pl.setInvestmentSerialNo((String)map2.get("InvestmentSerialNo"));
			pl.setInstSettleDate((String)map2.get("instSettleDate"));
			pTRResList.add(pl);
		}
		pTRRes.setpTRResList(pTRResList);
		
		return pTRRes;
	}
	
	
	
	/**
	 * 
	 * <p>
	 * Description:更新投标记录信息<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月25日
	 * @param borrowVo
	 * @param list
	 * @throws Exception
	 * void
	 */
	public void updateTenderrecord(BorrowVo borrowVo,PTRRes pTRRes,List<InvestBorrow> InvestBorrowList) throws Exception{
		//并锁定借款标
		BorrowVo bv = borrowMapper.selectByPrimaryKeyForUpdate(borrowVo.getId());
		//更新投资信息（投资成功笔数.....）
		BorrowVo bvo=new BorrowVo();
		bvo.setId(bv.getId());
		bvo.setSuccount(pTRRes.getSuccount());
		bvo.setSucsum(new BigDecimal(pTRRes.getSucsum()/100));
		bvo.setFailcount(pTRRes.getFailcount());
		bvo.setFailsum(new BigDecimal(pTRRes.getFailsum()/100)  );
		borrowMapper.updateCGBorrowZS(bvo);
		
		
		List<PTRResList> list=pTRRes.getpTRResList();
		//查询投标记录并锁定
		List<TenderRecord> tv= tenderRecordMapper.findTenderrecordForUpdate(borrowVo.getId());
		for (TenderRecord tr : tv) {
			for (PTRResList ptrResList : list) {
				if(tr.getBizInvestNo().equals(ptrResList.getP2PserialNo())){
					//更新投标记录
					TenderRecord tenderRecord=new TenderRecord();
					//tenderRecord.seteInvestDate(ptrResList.getInstSettleDate());
					tenderRecord.seteInvestNo(ptrResList.getInvestmentSerialNo());
					tenderRecord.seteInvestStatus(Integer.parseInt(ptrResList.getInvestmentStatus()));
					tenderRecord.setId(tr.getId());
					tenderRecordMapper.updateTenderrecordZS(tenderRecord);
				}
				
			}
			
		}
		
		
		try {
			BigDecimal account = BigDecimal.ZERO;
			for (InvestBorrow investBorrow : InvestBorrowList) {
				account=account.add(investBorrow.getInvestmentAmount());
			}
			if(pTRRes.getFailsum()>0 || pTRRes.getFailcount()>0 || pTRRes.getSucsum()!=account.multiply(new BigDecimal(100)).intValue() || pTRRes.getSuccount()!=InvestBorrowList.size()){
				//新增邮件待发记录 
				MailSendRecord mailSendRecord=new MailSendRecord();
				mailSendRecord.setTypeId(borrowVo.getId());
				mailSendRecord.setType(8);//项目投资信息上报未全部成功
				mailSendRecord.setStatus(0);
				mailSendRecord.setAddtime(new Date());
				baseMailSendRecordMapper.insertEntity(mailSendRecord);
				
			}
		} catch (Exception e) {
			logger.error("新增邮件待发记录 ",e);
		}
		
		
		
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:更新标的状态<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月25日
	 * @param borrowVo
	 * @param pTRRes
	 * void
	 */
	public void updateBorrowStatus(BorrowVo borrowVo) throws Exception{
			//将标的状态更新为银行复审中
			BorrowVo bv=new BorrowVo();
			bv.setId(borrowVo.getId());
			bv.setStatus(6);//6（银行复审中）
			borrowMapper.updateBorrow(bv);
			
	}
	
}
