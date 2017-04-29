package com.cxdai.console.customer.bankcard.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.cxdai.console.util.DateUtils;

/**
 * 银行卡更换申请记录表
 * <p>
 * Description:这里写描述<br />
 * </p>
 * 
 * @title BankcardChange.java
 * @package com.cxdai.console.bank.vo
 * @author huangpin
 * @version 0.1 2015年3月24日
 */
public class BankcardChange implements Serializable {

	private static final long serialVersionUID = 6565551871379113262L;

	private Integer id; // 自增ID
	private String realName; // 真实姓名
	private String idCard; // 证件号码，与rocky_realname_appro应一致
	private String idCardType; // 证件类型
	private String phone; // 手机号
	
	private String oldbank; // 原银行卡号
	private String oldBankcard; // 原银行卡号
	private String newBank; // 新卡银行
	private String newBankCode;
	private String newBranch; // 新卡分行
	private String newBranchNo; // 新卡分行行号
	private String newBankcard; // 新卡卡号

	private String updateReason;// 换卡原因：丢失，损坏，卡号错误
	private String remark; // 备注
	private Date lastAddTime; // 上次申请时间
	private Date addTime; // 创建时间（申请时间）
	private String addIp; // 添加IP
	private Integer userId; // 申请人

	private String userName; // 申请人用户名
	private Integer approveStatus; // 审核状态：0，待审核，1，审核通过，-1，审核不通过，-2，草稿
	private Date approveTime; // 审核时间
	private Integer approveUserId; // 审核人员ID
	private String approveUserName; // 审批人姓名
	private String firstAuditUserName;// 初审人姓名

	private String approveIp;// 审批IP

	private Date recheckTime; // 复审时间
	private String recheckTimeStr;// 复审时间Str
	private Integer recheckUserId; // 复审人员ID
	private String recheckUserName; // 复审人姓名
	private String recheckIp;// 复审IP

	private String recheckRemark; // 复审备注
	private String verifyVedioPath;// 认证视频路径

	public String getVerifyVedioPath() {
		return verifyVedioPath;
	}

	public void setVerifyVedioPath(String verifyVedioPath) {
		this.verifyVedioPath = verifyVedioPath;
	}

	private String approveRemark; // 审核备注
	private Integer platform; // 平台来源

	// 显示属性
	private String approveStatusStr;
	private String lastAddTimeStr;
	private String addTimeStr;
	private String approveTimeStr;
	private String firstAuditTimeStr;

	// 关联查询
	private Integer changeTimes; // 换卡次数
	private Integer applyTimes; // 申请次数
	private Integer clickTimes; // 点击申请次数

	public Integer getId() {
		return id;
	}

	public String getFirstAuditUserName() {
		return approveUserName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOldBankcard() {
		return oldBankcard;
	}

	public void setOldBankcard(String oldBankcard) {
		this.oldBankcard = oldBankcard;
	}

	public String getNewBank() {
		return newBank;
	}

	public void setNewBank(String newBank) {
		this.newBank = newBank;
	}

	public String getNewBranch() {
		return newBranch;
	}

	public void setNewBranch(String newBranch) {
		this.newBranch = newBranch;
	}

	public String getNewBankcard() {
		return newBankcard;
	}

	public void setNewBankcard(String newBankcard) {
		this.newBankcard = newBankcard;
	}

	public String getUpdateReason() {
		return updateReason;
	}

	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Integer getApproveUserId() {
		return approveUserId;
	}

	public void setApproveUserId(Integer approveUserId) {
		this.approveUserId = approveUserId;
	}

	public String getApproveRemark() {
		return approveRemark;
	}

	public void setApproveRemark(String approveRemark) {
		this.approveRemark = approveRemark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIdCardType() {
		return idCardType;
	}

	public void setIdCardType(String idCardType) {
		this.idCardType = idCardType;
	}

	public String getNewBranchNo() {
		return newBranchNo;
	}

	public void setNewBranchNo(String newBranchNo) {
		this.newBranchNo = newBranchNo;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public String getApproveUserName() {
		if (null == approveStatus) {
			return StringUtils.EMPTY;
		}
		/* 初审下状态 */
		if (1 == approveStatus || -1 == approveStatus) {
			return approveUserName;

		} else if (2 == approveStatus || -2 == approveStatus) {// 复审下状态
			return recheckUserName;
		}

		return StringUtils.EMPTY;
	}

	public void setApproveUserName(String approveUserName) {
		this.approveUserName = approveUserName;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public String getApproveStatusStr() {
		if (approveStatus != null) {
			switch (approveStatus) {
			case 0:
				approveStatusStr = "待审核";
				break;
			case 1:
				approveStatusStr = "初审通过";
				break;
			case -1:
				approveStatusStr = "初审不通过";
				break;
			case 2:
				approveStatusStr = "复审通过";
				break;
			case -2:
				approveStatusStr = "复审不通过";
				break;
			default:
				break;
			}
		}
		return approveStatusStr;
	}

	public void setApproveStatusStr(String approveStatusStr) {
		this.approveStatusStr = approveStatusStr;
	}

	public String getAddTimeStr() {
		if (addTime != null) {
			addTimeStr = DateUtils.format(addTime, DateUtils.YMD_HMS);
		}
		return addTimeStr;
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}

	public String getLastAddTimeStr() {
		if (lastAddTime != null) {
			lastAddTimeStr = DateUtils.format(lastAddTime, DateUtils.YMD_HMS);
		}
		return lastAddTimeStr;
	}

	public void setLastAddTimeStr(String lastAddTimeStr) {
		this.lastAddTimeStr = lastAddTimeStr;
	}

	public String getRecheckTimeStr() {
		if (null == recheckTime) {
			return StringUtils.EMPTY;
		}
		return DateUtils.format(recheckTime, DateUtils.YMD_HMS);
	}

	public String getApproveTimeStr() {
		if (null == approveStatus || 0 == approveStatus) {
			return StringUtils.EMPTY;
		}
		/* 初审下状态 */
		if (1 == approveStatus || -1 == approveStatus) {
			if (approveTime != null) {
				approveTimeStr = DateUtils.format(approveTime, DateUtils.YMD_HMS);
			}

		} else if (2 == approveStatus || -2 == approveStatus) {// 复审下状态
			if (recheckTime != null) {
				approveTimeStr = DateUtils.format(recheckTime, DateUtils.YMD_HMS);
			}
		}

		return approveTimeStr;
	}

	public String getFirstAuditTimeStr() {
		if (approveTime != null) {
			return DateUtils.format(approveTime, DateUtils.YMD_HMS);
		}
		return StringUtils.EMPTY;
	}

	public void setApproveTimeStr(String approveTimeStr) {
		this.approveTimeStr = approveTimeStr;
	}

	public String getApproveIp() {
		return approveIp;
	}

	public void setApproveIp(String approveIp) {
		this.approveIp = approveIp;
	}

	public Date getLastAddTime() {
		return lastAddTime;
	}

	public void setLastAddTime(Date lastAddTime) {
		this.lastAddTime = lastAddTime;
	}

	public Integer getChangeTimes() {
		return changeTimes;
	}

	public void setChangeTimes(Integer changeTimes) {
		this.changeTimes = changeTimes;
	}

	public Integer getApplyTimes() {
		return applyTimes;
	}

	public void setApplyTimes(Integer applyTimes) {
		this.applyTimes = applyTimes;
	}

	public Integer getClickTimes() {
		return clickTimes;
	}

	public void setClickTimes(Integer clickTimes) {
		this.clickTimes = clickTimes;
	}

	public String getNewBankCode() {
		return newBankCode;
	}

	public void setNewBankCode(String newBankCode) {
		this.newBankCode = newBankCode;
	}

	public Date getRecheckTime() {
		return recheckTime;
	}

	public void setRecheckTime(Date recheckTime) {
		this.recheckTime = recheckTime;
	}

	public Integer getRecheckUserId() {
		return recheckUserId;
	}

	public void setRecheckUserId(Integer recheckUserId) {
		this.recheckUserId = recheckUserId;
	}

	public String getRecheckUserName() {
		return recheckUserName;
	}

	public void setRecheckUserName(String recheckUserName) {
		this.recheckUserName = recheckUserName;
	}

	public String getRecheckIp() {
		return recheckIp;
	}

	public void setRecheckIp(String recheckIp) {
		this.recheckIp = recheckIp;
	}

	public String getRecheckRemark() {
		return recheckRemark;
	}

	public void setRecheckRemark(String recheckRemark) {
		this.recheckRemark = recheckRemark;
	}

	public String getOldbank() {
		return oldbank;
	}

	public void setOldbank(String oldbank) {
		this.oldbank = oldbank;
	}
}
