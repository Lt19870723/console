
package com.cxdai.console.common.custody.xml;

import java.math.BigDecimal;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**   
 * <p>
 * Description:项目登记报文<br />
 * </p>
 * @title PIReq.java
 * @package com.cxdai.common.custody.xml 
 * @author tanghaitao
 * @version 0.1 2016年5月17日
 */

public class PIReqTwo extends BasePIReq{

	@XStreamAsAttribute  
    String id = "PIReq"; 
	
	private String ProjectId="";//项目ID
	
	private String ProjectName="";//项目名称
	
	private String ProjectBeginTime="";//募集开始日期 格式：yyyyMMdd
	
	private String ProjectEndDate="";//募集结束日期 格式：yyyyMMdd
	
	private String InterestBeginDate="";//项目起息日期 格式：yyyyMMdd
	
	private String InterestEndDate="";//项目到期日期 格式：yyyyMMdd
	
	private String RepayDate="";//还款日  格式：yyyyMMdd
	
	private Integer InvestDuration=0;//融资期限 单位：天
	
	private BigDecimal Rate=new BigDecimal(0);//融资利率 
	
	private String ProjectStatus="";//项目状态
	
	private Integer Amount=0;//融资本金 单位：分
	
	private Integer RepayType=0;//融资人利息
	
	private BigDecimal RepayRate;//投资收益率
	
	private Integer PlatformPoundage=0;//平台服务费
	
	private Integer BankPoundage=0;//银行服务费
	
	private String RepayName="";//还款账户户名
	
	private String RepayAcct="";//还款账户账号
	
	private String Person="";//是否个人
	
	private String Flag="";//是否他行
	
	private String BankBranchNo="";//开户行人行联行号
	
	private String ContractNo="";//合同编号
	
	private String Remarks="";//备注
	
	private BigDecimal WitnessRate=new BigDecimal(0.00);//见证费费率
	
	private String WitnessAccount="";//见证费入账账号
	
	private String ProjectType="";//项目类型
	
	private String AdvanceRepayment="";//是否允许提前还款  0否1是
	
	private String OverdueRepayment="";//是否允许逾期还款 0否1是
	
	private Integer AdvanceDay=0;//最大提前天数
	
	private Integer OverdueDay=0;//最大逾期天数
	
	private String IsTransfer="";//是否允许转让  0不允许 1允许
	
	private String InvestFee="";//是否收取投资人费用 0否1是（目前不起作用）
	
	private String InvestFeePoint="";//投资人收费时点 0划款1还款，（目前不起作用）
	
	private String InvestFeeType="";//投资人收费方式 0按笔1按比率，目前该字段不起作用
	
	private BigDecimal InvestFeeContent=new BigDecimal(0.00);//投资人收费内容  每笔多少或比率%，目前该字段不起作用
	
	private String GatherFee="";//是否收取融资人费用  0否1是
	
	private String GatherFeePoint="";//融资人收费时点   0划款1还款
	
	private String GatherFeeType="";//融资人收费方式  0按笔1按比率
	
	private BigDecimal GatherFeeContent=new BigDecimal(0.00);//融资人收费内容  每笔多少或比率%
	
	private String AdvancePay="";//是否允许垫资  默认不垫资，1垫资
	
	private String extension="";//消息扩展

	public String getProjectId() {
		return ProjectId;
	}

	public void setProjectId(String projectId) {
		ProjectId = projectId;
	}

	public String getProjectName() {
		return ProjectName;
	}

	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}

	public String getProjectBeginTime() {
		return ProjectBeginTime;
	}

	public void setProjectBeginTime(String projectBeginTime) {
		ProjectBeginTime = projectBeginTime;
	}

	public String getProjectEndDate() {
		return ProjectEndDate;
	}

	public void setProjectEndDate(String projectEndDate) {
		ProjectEndDate = projectEndDate;
	}

	public String getInterestBeginDate() {
		return InterestBeginDate;
	}

	public void setInterestBeginDate(String interestBeginDate) {
		InterestBeginDate = interestBeginDate;
	}

	public String getInterestEndDate() {
		return InterestEndDate;
	}

	public void setInterestEndDate(String interestEndDate) {
		InterestEndDate = interestEndDate;
	}

	public String getRepayDate() {
		return RepayDate;
	}

	public void setRepayDate(String repayDate) {
		RepayDate = repayDate;
	}

	public Integer getInvestDuration() {
		return InvestDuration;
	}

	public void setInvestDuration(Integer investDuration) {
		InvestDuration = investDuration;
	}

	public BigDecimal getRate() {
		return Rate;
	}

	public void setRate(BigDecimal rate) {
		Rate = rate;
	}

	public String getProjectStatus() {
		return ProjectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		ProjectStatus = projectStatus;
	}

	public Integer getAmount() {
		return Amount;
	}

	public void setAmount(Integer amount) {
		Amount = amount;
	}

	public Integer getRepayType() {
		return RepayType;
	}

	public void setRepayType(Integer repayType) {
		RepayType = repayType;
	}

	public BigDecimal getRepayRate() {
		return RepayRate;
	}

	public void setRepayRate(BigDecimal repayRate) {
		RepayRate = repayRate;
	}

	public Integer getPlatformPoundage() {
		return PlatformPoundage;
	}

	public void setPlatformPoundage(Integer platformPoundage) {
		PlatformPoundage = platformPoundage;
	}

	public Integer getBankPoundage() {
		return BankPoundage;
	}

	public void setBankPoundage(Integer bankPoundage) {
		BankPoundage = bankPoundage;
	}

	public String getRepayName() {
		return RepayName;
	}

	public void setRepayName(String repayName) {
		RepayName = repayName;
	}

	public String getRepayAcct() {
		return RepayAcct;
	}

	public void setRepayAcct(String repayAcct) {
		RepayAcct = repayAcct;
	}

	public String getPerson() {
		return Person;
	}

	public void setPerson(String person) {
		Person = person;
	}

	public String getFlag() {
		return Flag;
	}

	public void setFlag(String flag) {
		Flag = flag;
	}

	public String getBankBranchNo() {
		return BankBranchNo;
	}

	public void setBankBranchNo(String bankBranchNo) {
		BankBranchNo = bankBranchNo;
	}

	public String getContractNo() {
		return ContractNo;
	}

	public void setContractNo(String contractNo) {
		ContractNo = contractNo;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public BigDecimal getWitnessRate() {
		return WitnessRate;
	}

	public void setWitnessRate(BigDecimal witnessRate) {
		WitnessRate = witnessRate;
	}

	public String getWitnessAccount() {
		return WitnessAccount;
	}

	public void setWitnessAccount(String witnessAccount) {
		WitnessAccount = witnessAccount;
	}

	public String getProjectType() {
		return ProjectType;
	}

	public void setProjectType(String projectType) {
		ProjectType = projectType;
	}

	public String getAdvanceRepayment() {
		return AdvanceRepayment;
	}

	public void setAdvanceRepayment(String advanceRepayment) {
		AdvanceRepayment = advanceRepayment;
	}

	public String getOverdueRepayment() {
		return OverdueRepayment;
	}

	public void setOverdueRepayment(String overdueRepayment) {
		OverdueRepayment = overdueRepayment;
	}

	public Integer getAdvanceDay() {
		return AdvanceDay;
	}

	public void setAdvanceDay(Integer advanceDay) {
		AdvanceDay = advanceDay;
	}

	public Integer getOverdueDay() {
		return OverdueDay;
	}

	public void setOverdueDay(Integer overdueDay) {
		OverdueDay = overdueDay;
	}

	public String getIsTransfer() {
		return IsTransfer;
	}

	public void setIsTransfer(String isTransfer) {
		IsTransfer = isTransfer;
	}

	public String getInvestFee() {
		return InvestFee;
	}

	public void setInvestFee(String investFee) {
		InvestFee = investFee;
	}

	public String getInvestFeePoint() {
		return InvestFeePoint;
	}

	public void setInvestFeePoint(String investFeePoint) {
		InvestFeePoint = investFeePoint;
	}

	public String getInvestFeeType() {
		return InvestFeeType;
	}

	public void setInvestFeeType(String investFeeType) {
		InvestFeeType = investFeeType;
	}

	public BigDecimal getInvestFeeContent() {
		return InvestFeeContent;
	}

	public void setInvestFeeContent(BigDecimal investFeeContent) {
		InvestFeeContent = investFeeContent;
	}

	public String getGatherFee() {
		return GatherFee;
	}

	public void setGatherFee(String gatherFee) {
		GatherFee = gatherFee;
	}

	public String getGatherFeePoint() {
		return GatherFeePoint;
	}

	public void setGatherFeePoint(String gatherFeePoint) {
		GatherFeePoint = gatherFeePoint;
	}

	public String getGatherFeeType() {
		return GatherFeeType;
	}

	public void setGatherFeeType(String gatherFeeType) {
		GatherFeeType = gatherFeeType;
	}

	public BigDecimal getGatherFeeContent() {
		return GatherFeeContent;
	}

	public void setGatherFeeContent(BigDecimal gatherFeeContent) {
		GatherFeeContent = gatherFeeContent;
	}

	public String getAdvancePay() {
		return AdvancePay;
	}

	public void setAdvancePay(String advancePay) {
		AdvancePay = advancePay;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
	
	
}
