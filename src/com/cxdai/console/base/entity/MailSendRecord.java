package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * <p>
 * Description:邮件发送记录<br />
 * </p>
 * 
 * @title MailSendRecord.java
 * @package com.cxdai.base.entity
 * @author yangshijin
 * @version 0.1 2014年9月15日
 */
public class MailSendRecord implements Serializable {

	private static final long serialVersionUID = -5058556479257147513L;

	/** 主键Id */
	private Integer id;
	/** 类型Id */
	private Integer typeId;
	/** 类型（1：满标发邮件(borrowId)，2：注册实名认证(userId)，3：还款提醒(repaymentId)） */
	private Integer type;
	/** 状态(0：未发送，1：已发送，2：发送失败) */
	private Integer status;
	/** 发送数量 */
	private Integer sendNum;
	/** 发送时间 */
	private Date sendTime;
	/** 添加时间 */
	private Date addtime;

    private String email;

    private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSendNum() {
		return sendNum;
	}

	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
