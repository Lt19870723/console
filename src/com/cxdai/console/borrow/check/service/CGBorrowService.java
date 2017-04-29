
package com.cxdai.console.borrow.check.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.base.entity.AccountError;
import com.cxdai.console.base.entity.BaseEBankInfo;
import com.cxdai.console.base.mapper.AccountErrorMapper;
import com.cxdai.console.base.mapper.BaseEBankInfoMapper;
import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.check.entity.BorrowerDealError;
import com.cxdai.console.borrow.check.mapper.BorrowerDealErrorMapper;
import com.cxdai.console.borrow.check.vo.AccountErrorCnd;
import com.cxdai.console.borrow.check.vo.BorrowErrorCnd;
import com.cxdai.console.borrow.check.vo.BorrowErrorVo;
import com.cxdai.console.cg.entity.MessageRecord;
import com.cxdai.console.cg.mapper.MessageRecordMapper;
import com.cxdai.console.common.custody.xml.AQReq;
import com.cxdai.console.common.custody.xml.Finance;
import com.cxdai.console.common.custody.xml.Message;
import com.cxdai.console.common.custody.xml.UFBReq;
import com.cxdai.console.common.custody.xml.UFBRes;
import com.cxdai.console.common.custody.xml.XmlUtil;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.common.statics.CGBusinessConstants;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.statistics.account.entity.Account;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.serialnumberutil.UUIDGenerator;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title CGBorrowService.java
 * @package com.cxdai.console.borrow.check.service 
 * @author tanghaitao
 * @version 0.1 2016年6月2日
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class CGBorrowService {

	public Logger logger = Logger.getLogger(CGBorrowService.class);
	
	@Autowired
	private BorrowerDealErrorMapper borrowerDealErrorMapper;
	@Autowired
	private AccountErrorMapper accountErrorMapper;
	@Autowired
	private BaseEBankInfoMapper baseEBankInfoMapper;
	@Autowired
	private MessageRecordMapper messageRecordMapper;
	@Autowired
	private BorrowMapper borrowMapper;
	
	/**
	 * 
	 * <p>
	 * Description:查询异常存管标记录<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年6月3日
	 * @param page
	 * @param borrowErrorCnd
	 * @return
	 * Page
	 */
	public Page findBorrowerError(Page page, BorrowErrorCnd borrowErrorCnd) throws Exception{
		List<BorrowErrorVo> list= borrowerDealErrorMapper.findBorrowerError(borrowErrorCnd, page);
		Integer count= borrowerDealErrorMapper.findBorrowerErrorCount(borrowErrorCnd);
		page.setTotalCount(count);
		page.setResult(list);
		return page;
		
	}
	
	public BorrowerDealError selectByPrimaryKey(Integer id) throws Exception{
		return borrowerDealErrorMapper.selectByPrimaryKey(id);
	}
	
	
	public Page findAccountError(Page page,AccountErrorCnd accountErrorCnd) throws Exception{
		List<AccountError> list=accountErrorMapper.findAccountError(accountErrorCnd, page);
		Integer count= accountErrorMapper.findAccountErrorCount(accountErrorCnd);
		page.setTotalCount(count);
		page.setResult(list);
		return page;
	}
	
	
	public void updateAccountError(AccountError accountError){
		accountErrorMapper.updateByPrimaryKeySelective(accountError);
	}
	
	public void updateBorrowError(Integer borrowErrorId,String checkRemark){
		//更新异常记录表
		BorrowerDealError bde=new BorrowerDealError();
		bde.setId(borrowErrorId);
		bde.setCheckRemark(checkRemark);
		bde.setDisposeStatus(1);//已处理
		borrowerDealErrorMapper.updateByPrimaryKeySelective(bde);
	}
	
	public Page findFBReqAccount(Page page,BorrowErrorCnd borrowErrorCnd) throws Exception{
		List<BorrowErrorVo> list= borrowerDealErrorMapper.findFBReqAccount(borrowErrorCnd, page);
		Integer count=borrowerDealErrorMapper.findFBReqAccountCount(borrowErrorCnd);
		page.setResult(list);
		page.setTotalCount(count);
		return page;
		
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
	public String parseUFBResXml(String repXml,ShiroUser shiroUser,String remark,String relateNo,String mode,BorrowerDealError borrowerDealError,String checkRemark) throws Exception{
		
			String msg= this.saveResXml(repXml, mode, shiroUser, remark, relateNo);
			if(!msg.equals(BusinessConstants.SUCCESS)){
				return "响应报文异常";
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
						BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowerDealError.getBorrowId());
						BorrowVo borrow=new BorrowVo();
						borrow.setId(borrowVo.getId());
						borrow.setAdvance(borrowVo.getAdvance().subtract(borrowerDealError.getAccount()));
						borrowMapper.updateBorrow(borrow);
						
						//更新异常记录表
						BorrowerDealError bde=new BorrowerDealError();
						bde.setId(borrowerDealError.getId());
						bde.setCheckRemark(checkRemark);
						bde.setDisposeStatus(1);//已处理
						borrowerDealErrorMapper.updateByPrimaryKeySelective(bde);
						
					} catch (Exception e) {
						logger.error("投资失败，冻结总金额减去投资金额操作异常",e);
						return "冻结总金额减去投资金额操作异常";
					}
					
				}else{
					return "银行解冻失败";
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
	public String saveUFBReq(BorrowVo borrowVo,String oriSerialNo,Integer investmentAmount,ShiroUser shiroUser,String remark,String mode,String relateNo,Integer userId) throws Exception{
		//生成项目投资报文
		BaseEBankInfo baseEBankInfo=baseEBankInfoMapper.selectByUserId(userId);
		String reqMessage= this.createUFBReqXml(borrowVo, baseEBankInfo, oriSerialNo, investmentAmount);
		System.out.println(reqMessage);
		String reqXml=XmlUtil.sign(reqMessage, CGBusinessConstants.UFBReq);
		//调用项目登记接口
		String rep= XmlUtil.send(reqXml);
		this.insertMsg(reqXml, mode, 0, shiroUser, remark, relateNo);
		return rep;
		
	} 
	
	
	
	public void insertMsg(String reqXml,String mode,int type,ShiroUser shiroUser,String remark,String relateNo){
		//记录项目登记日志
		MessageRecord messageRecord=new MessageRecord();
		messageRecord.setMode(mode);//场景 
		messageRecord.setType(type);//0:主动，1:被动
		messageRecord.setMsg(reqXml);//报文体
		messageRecord.setOrderNo(shiroUser.getUserId().toString());
		messageRecord.setOptUserid(shiroUser.getUserId());
		messageRecord.setPlatform(shiroUser.getPlatform());
		messageRecord.setRemark(remark);
		messageRecord.setRelateNo(relateNo);//调用关联号
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
	public String saveResXml(String repXml,String mode,ShiroUser shiroUser,String remark,String relateNo) throws Exception{
				
		//记录响应报文
		this.insertMsg(repXml, mode, 1, shiroUser, remark, relateNo);		
		
			//判断响应报文
						boolean isError=XmlUtil.checkXml(repXml);
						if(isError){
							logger.info("响应ERROR报文:"+repXml);
							return "响应ERROR报文";
						}		
						//验签
						boolean istrue= XmlUtil.checkYq(repXml);
						if(!istrue){
							logger.info("验签失败"+repXml);
							return "验签失败";
						}
						return BusinessConstants.SUCCESS;
						
						
	}

	
	
	/**
	 * 
	 * <p>
	 * Description:组建资金解冻报文<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年6月8日
	 * @param borrowVo
	 * @param baseEBankInfo
	 * @param oriSerialNo
	 * @param investmentAmount
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
	
	
	public String saveCGAccount(ShiroUser shiroUser,String relateNo,String remark,String bindNo) throws Exception{
		//BaseEBankInfo baseEBankInfo=baseEBankInfoMapper.selectByUserId(shiroUser.getUserId());
		
		String reqMessage=this.createPIReqXml(bindNo);
		System.out.println(reqMessage);
		String reqXml= XmlUtil.sign(reqMessage, CGBusinessConstants.AQReq);
		//余额接口调用
		String rep= XmlUtil.send(reqXml);
		//记录项目登记日志
		MessageRecord messageRecord=new MessageRecord();
		messageRecord.setMode("5");//场景 5:余额查询
		messageRecord.setType(1);//1:主动，2:被动
		messageRecord.setMsg(reqXml);//报文体
		messageRecord.setOrderNo(bindNo);
		messageRecord.setOptUserid(shiroUser.getUserId());
		messageRecord.setPlatform(shiroUser.getPlatform());
		messageRecord.setRemark(remark);
		messageRecord.setRelateNo(relateNo);//调用关联号
		messageRecord.setBindNo(bindNo);
		messageRecordMapper.insert(messageRecord);
		return rep;
		
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
	public String createPIReqXml(String bindNo){
		
		 XStream xstream = new XStream();
		 xstream = new XStream(new DomDriver());
		 Finance finance =new Finance();
		 Message message =new Message();
		 message.setId(UUIDGenerator.generate(CGBusinessConstants.MSGID));
		 AQReq aQReq =new AQReq();
		 aQReq.setDate(DateUtils.format(new Date(), DateUtils.YMDH_M_S));
		 aQReq.setBindSerialNo(bindNo);
		 //aQReq.setAccNo(baseEBankInfo.getEcardNo());	
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
	 * Description:解析余额报文<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月19日
	 * @return
	 * Account
	 */
	public Account parseAQReqXml(String repXml,ShiroUser shiroUser,String remark,String relateNo) throws Exception{
		//记录响应报文
		this.insertMsg(repXml, "5", 2, shiroUser, remark, relateNo);
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
	
	
	public String selectByUserName(String userName){
		return baseEBankInfoMapper.selectByUserName(userName);
		
	}
	
	
}
