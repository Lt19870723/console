package com.cxdai.console.borrow.emerg.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.Dictionary;

/**
 * <p>
 * Description:上传资料<br />
 * </p>
 * 
 * @title AccountUploadDocVo.java
 * @package com.cxdai.portal.account.vo
 * @author justin.xu
 * @version 0.1 2014年5月14日
 */
public class AccountUploadDocVo implements Serializable {

	private static final long serialVersionUID = -7479326477862062111L;
	private Integer id;
	/** 上传人ID */
	private Integer userId;
	/** 附件名称 */
	private String docName;
	/** 文件路径 */
	private String docPath;
	/** 上传时间 */
	private Date uploadTime;
	/** 上传IP */
	private String uploadIp;
	/**
	 * 类型 (1:身份证 2:企业营业执照 3:组织结构代码 4:其他 5:房产登记簿 6:房产登记收据 7:户口本 8:房产证 9:汇款凭证
	 * 10:借条 11:收条 12:房地产登记证明)
	 */
	private Integer style;
	/** 借款标id */
	private Integer borrowId;

	private String styleLabel;
	
	/**图片路径是否存在，0不存在；1存在*/
	private String flagExist;	

	public String getFlagExist() {
		return flagExist;
	}

	public void setFlagExist(String flagExist) {
		this.flagExist = flagExist;
	}

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

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadIp() {
		return uploadIp;
	}

	public void setUploadIp(String uploadIp) {
		this.uploadIp = uploadIp;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public String getStyleLabel() {
		if (null != getStyle())
			styleLabel = Dictionary.getValue(817, getStyle() + "");
		return styleLabel;
	}

	public void setStyleLabel(String styleLabel) {
		this.styleLabel = styleLabel;
	}

}
