package com.cxdai.console.borrow.manage.service;

import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.manage.mapper.BRepaymentRecordMapper;
import com.cxdai.console.borrow.manage.mapper.EFundRepayMentMapper;
import com.cxdai.console.borrow.manage.vo.*;
import com.cxdai.console.cg.entity.MessageRecord;
import com.cxdai.console.cg.mapper.MessageRecordMapper;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.custody.*;
import com.cxdai.console.common.custody.xml.XmlUtil;
import com.cxdai.console.common.statics.CGBusinessConstants;
import com.cxdai.console.fix.mapper.BCollectionrecordMapper;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.PropertiesUtil;
import com.cxdai.console.util.serialnumberutil.UUIDGenerator;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(rollbackFor = Throwable.class)
public class EFundRepayMentService {
	@Autowired
	private EFundRepayMentMapper eFundRepayMentMapper;
	@Autowired
	private MessageRecordMapper messageRecordMapper;
	@Autowired
	private BCollectionrecordMapper bCollectionrecordMapper;
	@Autowired
	private BRepaymentRecordMapper bRepaymentRecordMapper;	
	
	
	/**
	 * 查询需要上报的本息还款数据
	 * @param reapaymentId
	 * @param borrowId
	 * @param type
	 * @return
	 */
	public List<EFundRepayMent> selectByRepayId(Integer reapaymentId,Integer borrowId,Integer type) {
		//type 1:正常还款 2：提前还款 3： 垫付 4： 垫付导出 5： 补罚息
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("reapaymentId", reapaymentId);
		parameter.put("borrowId", borrowId);
		parameter.put("type", type);
		return eFundRepayMentMapper.selectByRepayId(parameter);
	}
	
	public String createRepayXML( List<EFundRepayMent> efundList,BorrowVo  borrowVo){
		
		
		 XStream xstream = new XStream();
		 xstream = new XStream(new DomDriver());
		 
		 Finance finance =new Finance();	 
		 Message message =new Message();
		 message.setId(UUIDGenerator.getProjectId(CGBusinessConstants.MSGID, borrowVo.getId()));
		 
		 RepayBiz custodyBiz =new RepayBiz();
		 custodyBiz.setVersion(PropertiesUtil.getValue("custody_version"));
		 custodyBiz.setInstId(PropertiesUtil.getValue("custody_instId"));
		 custodyBiz.setCertId(PropertiesUtil.getValue("custody_certId"));
		 custodyBiz.setDate(DateUtils.formatDateForCustody());
		 custodyBiz.setProjectId(borrowVo.geteProjectId());
		 custodyBiz.setProjectName(borrowVo.getName());
		 custodyBiz.setRealname(borrowVo.getRepayName() == null ? "" : borrowVo.getRepayName());
		 custodyBiz.setCount(efundList.size());
		 custodyBiz.setComplete("0");
		 custodyBiz.setWitnessFee(0);
		 custodyBiz.setFlag("0");
		 custodyBiz.setExtension("本息还款："+DateUtils.getCurrentTimeStamp());
		 
		 CustodyList custodyList =new CustodyList();
		 
		 List<Record> recordList=new ArrayList<Record>();
        for(EFundRepayMent em : efundList){
			RepayDetail repayDetail =new RepayDetail();
			repayDetail.setP2PserialNo(em.getBizNo());
			repayDetail.setInvestmentSerialNo(em.geteInvestNo());
			repayDetail.setType(em.getReturnType());
			repayDetail.setFlag(em.getFlag()==null ? "0" : em.getFlag()+"");
			repayDetail.setBankBranchNo(em.getBankBranchNo() == null ? "" : em.getBankBranchNo()+"");
			repayDetail.setAccountBankNumber(em.getAccountBankNumber() == null ? "" : em.getAccountBankNumber()+"");
			repayDetail.setAccountName(em.getAccountName()  == null ? "" : em.getAccountName()+"" );
			repayDetail.setRepaymentAmount(em.getRepaymentAmount());
			repayDetail.setFee(em.getFee() == null ? 0 : em.getFee()) ;
			repayDetail.setExtraInterest(0);
			repayDetail.setCurrency(em.getCurrency()+"");
			repayDetail.setPayType(em.getPayType()+"");
			recordList.add(repayDetail);
        }
        
       
        custodyList.setRecordList(recordList);
        custodyBiz.setList(custodyList);
        message.setMode(custodyBiz);
        finance.setMessage(message);
        
        xstream.autodetectAnnotations(true);
        xstream.aliasField("FRReq", Message.class, "Mode");
        xstream.addDefaultImplementation(RepayBiz.class,CustodyBiz.class);
       
		return xstream.toXML(finance);
		
	}
	
	public MessageBox sendXML(String msgXML,String relateNo,Integer userId,Integer repaymentid ){
		String rep;		
		try {
			 rep= XmlUtil.send(msgXML, "FRReq");
		} catch (ConnectTimeoutException  e) {
			return MessageBox.build("0", "请求超时");
		} catch (Exception e) {
			  e.printStackTrace();
			return MessageBox.build("0", "请求异常");
		}	
		
		//记录请求日志
		saveMsg(msgXML, relateNo, userId, repaymentid.toString(),"本息还款-请求","11",0);
		return MessageBox.build("1", rep);
		
	}
	
	public void saveMsg(String msgXML,String relateNo,Integer userId,String orderNo,String remark,String mode,int type){
		MessageRecord messageRecord=new MessageRecord();
		messageRecord.setMode(mode);//场景 
		messageRecord.setType(type);//0:主动，1:被动
		messageRecord.setMsg(msgXML);//报文体
		messageRecord.setOrderNo(orderNo);
		messageRecord.setOptUserid(userId);
		messageRecord.setPlatform(3);
		messageRecord.setRemark(remark);
		messageRecord.setRelateNo(relateNo);//调用关联号
		messageRecordMapper.insert(messageRecord);
		
	}
	
	
	
	@Transactional(rollbackFor = Throwable.class)
	public MessageBox doRepayResponse(String msgXML,String relateNo,Integer userId,
									  Integer repaymentId,Integer bizType) throws Exception {
		//记录响应日志
		saveMsg(msgXML, relateNo, userId, repaymentId.toString().toString(),"本息还款-响应","11",1);
		
		//验签
		boolean istrue= XmlUtil.checkYq(msgXML);
		if(!istrue){
			return MessageBox.build("0", "验签失败");
		}	
		//判断响应报文
		boolean isError=XmlUtil.checkXml(msgXML);
		if(isError){
			Map<String, Object> maperr = XmlUtil.toError(msgXML);
			String errorCode=(String)maperr.get("errorCode");
			String errorDetail=(String)maperr.get("errorDetail");
			String err="银行响应异常：["+errorCode+"]"+errorDetail;
			return MessageBox.build("0", err);
		}
		
		//解析响应
		Map<String, Object> mapBiz = XmlUtil.toBiz(msgXML, "FRRes");
		List<Map> detailResList =   XmlUtil.toDetail(msgXML, "FRRes");
		List<RepayResDetail>  resDetailList =new ArrayList<RepayResDetail>();
		RepayResBiz repayResBiz=new RepayResBiz();

		try {

			repayResBiz.setSuccount(Integer.parseInt(mapBiz.get("succount").toString()));
			repayResBiz.setSucsum(new BigDecimal(mapBiz.get("sucsum").toString()).divide(new BigDecimal(100)));
			repayResBiz.setFailcount(Integer.parseInt(mapBiz.get("failcount").toString()));
			repayResBiz.setFailsum(new BigDecimal(mapBiz.get("failsum").toString()).divide(new BigDecimal(100)));

			repayResBiz.setReapaymentId(repaymentId);

			for(Map dMap : detailResList){
				RepayResDetail repayResDetail =new RepayResDetail();
				repayResDetail.setRepaymentStatus((String)dMap.get("RepaymentStatus"));
				repayResDetail.setRepaymentSerialNo((String)dMap.get("RepaymentSerialNo"));
				repayResDetail.setP2PserialNo((String)dMap.get("P2PserialNo"));
				repayResDetail.setInstSettleDate((String)dMap.get("instSettleDate"));
				resDetailList.add(repayResDetail);
			}
		}catch(Exception e){
			e.printStackTrace();
			return MessageBox.build("0", "响应本息还款响应解析异常");
		}

		
		//解析后更新相关业务表
		aferRepayResponse(repayResBiz,resDetailList, repaymentId,bizType);
		return MessageBox.build("1", "响应处理成功");
	}
	
	public void aferRepayResponse(RepayResBiz repayResBiz,List<RepayResDetail> rrDetailList,Integer repaymentid,Integer bizType){
		
		//更新e_fund_repayment表	,待收表
		for(RepayResDetail rd : rrDetailList){
			EFundRepayMentCnd efund =new EFundRepayMentCnd();
			efund.setBizNo(rd.getP2PserialNo());
			efund.seteRepaymentNo(rd.getRepaymentSerialNo());
			efund.setResult(Integer.valueOf(rd.getRepaymentStatus()));
			efund.setType(bizType);//
			efund.setReapaymentId(repaymentid);
			eFundRepayMentMapper.updateByResponse(efund);
			bCollectionrecordMapper.updateByResponse(efund);
		}
		
		//更新待还记录表
		repayResBiz.setReapaymentId(repaymentid);
		bRepaymentRecordMapper.updateByResponse(repayResBiz);
		
		
	}

	public void updateByPrimaryKeySelective(EFundRepayMent eFundRepayMent){
		eFundRepayMent.setUpdatetime(new Date());
		eFundRepayMentMapper.updateByPrimaryKeySelective(eFundRepayMent);
	}

}
