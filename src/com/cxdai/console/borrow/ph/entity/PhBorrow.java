/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PhBorrow.java
 * @package com.cxdai.console.borrow.ph.entity 
 * @author tanghaitao
 * @version 0.1 2016年6月24日
 */
package com.cxdai.console.borrow.ph.entity;

import java.math.BigDecimal;

/**   
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title PhBorrow.java
 * @package com.cxdai.console.borrow.ph.entity 
 * @author tanghaitao
 * @version 0.1 2016年6月24日
 */

public class PhBorrow {

	private String name;//借款标题
	
	private Integer order;//排序(借款期限)
	
	private Integer borrowtype;//借款标种类1：信用标
	
	private Integer timeLimit;//借款期限
	
	private Integer style;//还款方式
	
	private BigDecimal account;//借贷总金额
	
	private BigDecimal apr;//年利率
	
	/** 最低投标金额 */
	private BigDecimal lowestAccount;
	
	/** 有效时间 天数 */
	private Integer validTime;
	
	/** 借款详细说明 */
	private String content;
	
	/** 合同编号 */
	private String contractNo;
	
	/** 备注 */
	private String remark;
	
	/** 抵押标类型（1：新增，2：续贷，3：资产处理） */
	private Integer pledgeType;
	
	/**借款用途 */
	private String borrowUse;
	
	/** 借贷类型：1-诚薪贷 2-诚商贷 3-净值贷 */
	private Integer productType;
	
	/**存管方式   0：非存管，1：浙商存管 **/
	private Integer isCustody;
	
	
	private String borrowName;//借款人姓名
	
	private String registerName;//借款人注册名
	
	private BigDecimal userIncome;//个人月收入(元)
	
	private String mobile;//手机号
	
	private String idCard;//身份证号
	
	private Integer maritalStatus;//婚姻状况 1- 已婚    2-未婚    3-离异    4-丧偶',
	
	private Integer education;		//学历
	
	private String industry;		//公司行业
	
	private String jobTitle;		//岗位职位
	
	private String workCity;		//工作城市
	
	private Integer workYears;		//工作时间
	
	private Integer hasHouse;			//是否拥有房产 1- 是  0- 否 
	
	private Integer hasCar;				//是否拥有车产：	1- 是   0-否 
	
	private String addip;				//添加IP 
	
	private String biddingStatus;//发标状态
	
	private Integer biddingFrom;//发标来源（1、贷款系统）
	
	
	/** 是否机构担保 0：否，1：是 */
	private Integer isGuaranty;
	
	/** 是否抵押 0：否，1：是 */
	private Integer isMortgage;
	
	
	private Integer biddingId;//发标id（贷款提供，发标主键id） 
	private Integer contractId;//合同id
	
	private String respCode;
	private String respMsg;
	
	
	public Integer getBiddingId() {
		return biddingId;
	}

	public void setBiddingId(Integer biddingId) {
		this.biddingId = biddingId;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public Integer getIsGuaranty() {
		return isGuaranty;
	}

	public void setIsGuaranty(Integer isGuaranty) {
		this.isGuaranty = isGuaranty;
	}

	public Integer getIsMortgage() {
		return isMortgage;
	}

	public void setIsMortgage(Integer isMortgage) {
		this.isMortgage = isMortgage;
	}

	

	public Integer getBiddingFrom() {
		return biddingFrom;
	}

	public void setBiddingFrom(Integer biddingFrom) {
		this.biddingFrom = biddingFrom;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getBorrowtype() {
		return borrowtype;
	}

	public void setBorrowtype(Integer borrowtype) {
		this.borrowtype = borrowtype;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public BigDecimal getLowestAccount() {
		return lowestAccount;
	}

	public void setLowestAccount(BigDecimal lowestAccount) {
		this.lowestAccount = lowestAccount;
	}

	public Integer getValidTime() {
		return validTime;
	}

	public void setValidTime(Integer validTime) {
		this.validTime = validTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPledgeType() {
		return pledgeType;
	}

	public void setPledgeType(Integer pledgeType) {
		this.pledgeType = pledgeType;
	}

	public String getBorrowUse() {
		return borrowUse;
	}

	public void setBorrowUse(String borrowUse) {
		this.borrowUse = borrowUse;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getIsCustody() {
		return isCustody;
	}

	public void setIsCustody(Integer isCustody) {
		this.isCustody = isCustody;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public String getRegisterName() {
		return registerName;
	}

	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}

	public BigDecimal getUserIncome() {
		return userIncome;
	}

	public void setUserIncome(BigDecimal userIncome) {
		this.userIncome = userIncome;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getWorkCity() {
		return workCity;
	}

	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}

	public Integer getWorkYears() {
		return workYears;
	}

	public void setWorkYears(Integer workYears) {
		this.workYears = workYears;
	}

	public Integer getHasHouse() {
		return hasHouse;
	}

	public void setHasHouse(Integer hasHouse) {
		this.hasHouse = hasHouse;
	}

	public Integer getHasCar() {
		return hasCar;
	}

	public void setHasCar(Integer hasCar) {
		this.hasCar = hasCar;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}

	public String getBiddingStatus() {
		return biddingStatus;
	}

	public void setBiddingStatus(String biddingStatus) {
		this.biddingStatus = biddingStatus;
	}

	
	
	
	
}
