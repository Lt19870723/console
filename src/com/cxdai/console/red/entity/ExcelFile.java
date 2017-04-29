package com.cxdai.console.red.entity;


public class ExcelFile {

	private String id;// 主键（32位随机数）
	private String name;// 
	private String username;// 真实文件名
	private String remark;// 后缀名
	private Long bytes;// 文件字节数
	private String path;// 文件全路径
	private String memo;// 备注
//	private Integer addUserId;
//	private Date addTime;
//	private Integer removeTag;// 删除标志 1未删除 2已删除
	private Integer rows;// 行数
//	private Integer status;// 状态：0，待发，1，已发
//	private Date sendTime;// 发送时间
//	private String type;// 类型：代收付BJDSF,LX，金账户,BJJZH
//	private Integer sendUserId;
	private Integer yesNum;// 发送成功数
	private Integer noNum;// 发送失败数
	private String addUserName;// 上传用户姓名
//	private String sendUserName;// 发短信用户

	// 属性
//	private String statusStr;

	public ExcelFile() {
		super();
	}

//	public ExcelFile(String id, Integer sendUserId, Integer yesNum, Integer noNum, String sendUserName,Integer status) {
//		super();
//		this.id = id;
//		this.sendUserId = sendUserId;
//		this.yesNum = yesNum;
//		this.noNum = noNum;
//		this.sendUserName = sendUserName;
//		this.status = status;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		int extDot = name.lastIndexOf('.');
//		if (extDot > 0) {
//			mime = name.substring(extDot + 1);
//		}
	}

//	public String getMime() {
//		return mime;
//	}

//	public void setMime(String mime) {
//		this.mime = mime;
//	}

	public Long getBytes() {
		return bytes;
	}

	public void setBytes(Long bytes) {
		this.bytes = bytes;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
//
//	public Integer getAddUserId() {
//		return addUserId;
//	}
//
//	public void setAddUserId(Integer addUserId) {
//		this.addUserId = addUserId;
//	}
//
//	public Date getAddTime() {
//		return addTime;
//	}
//
//	public void setAddTime(Date addTime) {
//		this.addTime = addTime;
//	}
//
//	public Integer getRemoveTag() {
//		return removeTag;
//	}
//
//	public void setRemoveTag(Integer removeTag) {
//		this.removeTag = removeTag;
//	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

//	public Integer getStatus() {
//		return status;
//	}
//
//	public void setStatus(Integer status) {
//		this.status = status;
//	}
//
//	public Date getSendTime() {
//		return sendTime;
//	}

//	public void setSendTime(Date sendTime) {
//		this.sendTime = sendTime;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public String getStatusStr() {
//		if (status != null) {
//			if (status == 0)
//				statusStr = "未发送";
//			if (status == 1)
//				statusStr = "已发送";
//		}
//		return statusStr;
//	}
//
//	public void setStatusStr(String statusStr) {
//		this.statusStr = statusStr;
//	}
//
//	public Integer getSendUserId() {
//		return sendUserId;
//	}
//
//	public void setSendUserId(Integer sendUserId) {
//		this.sendUserId = sendUserId;
//	}

	public Integer getYesNum() {
		return yesNum;
	}

	public void setYesNum(Integer yesNum) {
		this.yesNum = yesNum;
	}

	public Integer getNoNum() {
		return noNum;
	}

	public void setNoNum(Integer noNum) {
		this.noNum = noNum;
	}

	public String getAddUserName() {
		return addUserName;
	}

	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

//	public String getSendUserName() {
//		return sendUserName;
//	}
//
//	public void setSendUserName(String sendUserName) {
//		this.sendUserName = sendUserName;
//	}
}
