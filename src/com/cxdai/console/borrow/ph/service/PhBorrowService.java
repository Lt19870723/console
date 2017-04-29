/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PhBorrowService.java
 * @package com.cxdai.console.borrow.ph.service 
 * @author tanghaitao
 * @version 0.1 2016年7月5日
 */
package com.cxdai.console.borrow.ph.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.base.entity.BaseEBankInfo;
import com.cxdai.console.base.entity.Borrower;
import com.cxdai.console.base.entity.Mortgage;
import com.cxdai.console.borrow.approve.service.BorrowService;
import com.cxdai.console.borrow.emerg.vo.SalariatBorrowVo;
import com.cxdai.console.borrow.ph.entity.PhBorrow;
import com.cxdai.console.cg.entity.MessageRecord;
import com.cxdai.console.cg.mapper.MessageRecordMapper;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.MemberRepeatCnd;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.security.ShiroUser;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PhBorrowService.java
 * @package com.cxdai.console.borrow.ph.service 
 * @author tanghaitao
 * @version 0.1 2016年7月5日
 */

@Service
@Transactional
public class PhBorrowService {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BorrowService borrowService;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private MessageRecordMapper messageRecordMapper;
	
	
	public PhBorrow initBorrow(PhBorrow phBorrow) throws Exception{
		
		phBorrow.setRespCode("0000");
		phBorrow.setRespMsg("通知成功");
		
		
		//注册借款用户
		MemberVo memberVo=new MemberVo();
		memberVo.setUsername(phBorrow.getRegisterName());
		memberVo.setLogpassword("123456");
		memberVo.setRealName(phBorrow.getBorrowName());
		memberVo.setIdcard(phBorrow.getIdCard());
		memberVo.setMobileNum(phBorrow.getMobile());
		memberVo.setPhplatform("dk");
		
		// 验证用户名
		MemberRepeatCnd usernameCnd = new MemberRepeatCnd();
		usernameCnd.setUsername(memberVo.getUsername());
		Integer usernameCount = memberMapper.queryRepeatMemberCount(usernameCnd);
		String msg;
		if (null != usernameCount && usernameCount > 0) {
			msg=BusinessConstants.SUCCESS;
			memberVo=memberMapper.queryMemberVoByUsername(memberVo.getUsername());
		}else{
			msg=memberService.saveMember(memberVo);
			
			
		}
		
		if(msg.equals(BusinessConstants.SUCCESS)) {
			//发标
			SalariatBorrowVo salariatBorrowVo=new SalariatBorrowVo();
			Borrower borrower=new Borrower();
			borrower.setMaritalStatus(phBorrow.getMaritalStatus());
			borrower.setEducation(phBorrow.getEducation());
			borrower.setIndustry(phBorrow.getIndustry());
			borrower.setJobTitle(phBorrow.getJobTitle());
			borrower.setWorkCity(phBorrow.getWorkCity());
			borrower.setWorkYears(phBorrow.getWorkYears());
			Mortgage mortgage=new Mortgage();
			mortgage.setHasHouse(phBorrow.getHasHouse());
			mortgage.setHasCar(phBorrow.getHasCar());
			salariatBorrowVo.setIsCheck(0);//默认为未登记
			salariatBorrowVo.setPlatform("5");
			salariatBorrowVo.setIsMortgage(phBorrow.getIsMortgage());
			salariatBorrowVo.setIsGuaranty(phBorrow.getIsGuaranty());
			salariatBorrowVo.setIsJointGuaranty(0);
			salariatBorrowVo.setName(phBorrow.getBorrowName());
			salariatBorrowVo.setOrder(phBorrow.getOrder());
			salariatBorrowVo.setBorrowtype(phBorrow.getBorrowtype());
			salariatBorrowVo.setTimeLimit(phBorrow.getTimeLimit());
			salariatBorrowVo.setStyle(phBorrow.getStyle());
			salariatBorrowVo.setAccount(phBorrow.getAccount());
			salariatBorrowVo.setApr(phBorrow.getApr());
			salariatBorrowVo.setLowestAccount(phBorrow.getLowestAccount());
			salariatBorrowVo.setValidTime(phBorrow.getValidTime());
			salariatBorrowVo.setContent(phBorrow.getContent());
			salariatBorrowVo.setContractNo(phBorrow.getContractNo());
			salariatBorrowVo.setRemark(phBorrow.getRemark());
			salariatBorrowVo.setPledgeType(phBorrow.getPledgeType());
			salariatBorrowVo.setBorrowUse(phBorrow.getBorrowUse());
			salariatBorrowVo.setProductType(phBorrow.getProductType());
			salariatBorrowVo.setIsCustody(phBorrow.getIsCustody());
			salariatBorrowVo.setStatus(1);
			salariatBorrowVo.setBusinessUserId(0);
			salariatBorrowVo.setIsNotice(0);//未通知
			msg = borrowService.saveApplyBorrow(memberVo, salariatBorrowVo, borrower, mortgage, phBorrow.getAddip());
			if(!msg.equals(BusinessConstants.SUCCESS)){
				phBorrow.setRespCode("9999");
				phBorrow.setRespMsg("发标失败:"+msg);
			}
		}else{
			phBorrow.setRespCode("9999");
			phBorrow.setRespMsg("借款用户注册失败:"+msg);
		}
		return phBorrow;
		
		
	}
	
	
	public void insertMsg(String reqXml,String mode,int type,String remark,String relateNo,Integer platform,Integer userId){
		
		//记录项目登记日志
		MessageRecord messageRecord=new MessageRecord();
		messageRecord.setMode(mode);//场景 
		messageRecord.setType(type);//1:主动，2:被动
		messageRecord.setMsg(reqXml);//报文体
		messageRecord.setOrderNo(userId.toString());
		messageRecord.setOptUserid(userId);
		messageRecord.setPlatform(platform);
		messageRecord.setRemark(remark);
		messageRecord.setRelateNo(relateNo);//调用关联号
		messageRecordMapper.insert(messageRecord);
}
	
	
	
}
