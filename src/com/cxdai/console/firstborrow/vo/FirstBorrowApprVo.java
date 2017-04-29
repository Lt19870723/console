package com.cxdai.console.firstborrow.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.Dictionary;
import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:优先投标计划审核<br />
 * </p>
 * 
 * @title FirstBorrowApprVo.java
 * @package com.cxdai.console.first.vo
 * @author justin.xu
 * @version 0.1 2014年7月3日
 */
public class FirstBorrowApprVo implements Serializable {
	private static final long serialVersionUID = -8485687809062135153L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 审核人ID
	 */
	private Integer userId;
	/**
	 * 审核时间
	 */
	private Date apprTime;
	/**
	 * 审核状态 0 ：审核拒绝 1 审核通过
	 */
	private Integer status;
	/**
	 * 审核备注
	 */
	private String remark;
	/**
	 * 审核类型，0 ：发布审核 1：满标审核
	 */
	private Integer style;
	/**
	 * 优选计划ID
	 */
	private Integer firstBorrowId;
	/**
	 * 直通车最终记录id
	 */
	private Integer firstTenderRealId;
	/**
	 * 直通车明细记录id
	 */
	private Integer firstTenderDetailId;

	/** 页面显示用到的转换字符 begin */
	private String apprTimeStr;
	private String username; // 审核人
	private String statusStr;
	private String styleStr;

	/** 页面显示用到的转换字符 end */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getApprTime() {
		return apprTime;
	}

	public void setApprTime(Date apprTime) {
		this.apprTime = apprTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public Integer getFirstBorrowId() {
		return firstBorrowId;
	}

	public void setFirstBorrowId(Integer firstBorrowId) {
		this.firstBorrowId = firstBorrowId;
	}

	public String getApprTimeStr() {
		if (null != apprTime) {
			return DateUtils.format(apprTime, DateUtils.YMD_HMS);
		}
		return apprTimeStr;
	}

	public void setApprTimeStr(String apprTimeStr) {
		this.apprTimeStr = apprTimeStr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatusStr() {
		return Dictionary.getValue(805, String.valueOf(status));
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getStyleStr() {
		return Dictionary.getValue(806, String.valueOf(style));
	}

	public void setStyleStr(String styleStr) {
		this.styleStr = styleStr;
	}

	public Integer getFirstTenderRealId() {
		return firstTenderRealId;
	}

	public void setFirstTenderRealId(Integer firstTenderRealId) {
		this.firstTenderRealId = firstTenderRealId;
	}

	public Integer getFirstTenderDetailId() {
		return firstTenderDetailId;
	}

	public void setFirstTenderDetailId(Integer firstTenderDetailId) {
		this.firstTenderDetailId = firstTenderDetailId;
	}
}
