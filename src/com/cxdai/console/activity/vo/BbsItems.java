package com.cxdai.console.activity.vo;

import java.io.Serializable;
import java.util.Date;

import com.cxdai.console.common.page.BaseCnd;

/**
 * 帖子表
 * <p>
 * Description:主贴<br />
 * </p>
 * 
 * @title BbsItems.java
 * @package com.bbs.entity
 * @author qiongbiao.zhang
 * @version 0.1 2014年4月16日
 */
public class BbsItems extends BaseCnd implements Serializable {
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;

	private Integer id; // 主键

	private String title; // 标题

	private Integer columnId; // 栏目编号

	private Integer orderNum; // 排序

	private Date createTime;// 创建时间

	private Integer owner; // 拥有者编号

	private Integer type; // 帖子拥有者类型(0 member 1 staff)

	private Integer status; // 状态(0 正常，1 关闭, 2 草稿)

	private Integer clickNum; // 查看数量

	private Integer category; // 帖子的种类( 1 精华帖 )

	private Integer collectNum; // 收藏的数量

	private Integer upitemNum; // 顶贴次数

	private Integer lastNoteId; // 最后一次回复Id

	private Date lastNoteTime; // 最后一次回复时间

	private Integer isHot; // 是否热门

	private Integer isTop; // 是否置顶

	private Integer isDelete; // 是否删除

	private Integer isScreen; // 是否屏蔽

	private Date topTime; // 置顶时间

	private Integer isTimeingPost; // 是否定时发帖

	private Date timeingPostTime; // 定时发帖时间

	private Date deadLine; // 投票截止日期

	private Integer isAnswered; // 是否已答疑

	private Integer isVote; // 是否投票帖

	private Integer maxChoices; // 最大可选项数

	private Integer isVoteUserOvert; // 是否公开投票参与人

	private Integer integral;// 积分表

	/** 开始时间 */
	private String beginTime;

	/** 截止时间 */
	private String endTime;
	
	private String username;//作者
	
	private Integer notecount;//回帖数
	
	private String reason;//回帖理由

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getColumnId() {
		return columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getOwner() {
		return owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
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

	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public Integer getUpitemNum() {
		return upitemNum;
	}

	public void setUpitemNum(Integer upitemNum) {
		this.upitemNum = upitemNum;
	}

	public Integer getLastNoteId() {
		return lastNoteId;
	}

	public void setLastNoteId(Integer lastNoteId) {
		this.lastNoteId = lastNoteId;
	}

	public Date getLastNoteTime() {
		return lastNoteTime;
	}

	public void setLastNoteTime(Date lastNoteTime) {
		this.lastNoteTime = lastNoteTime;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsScreen() {
		return isScreen;
	}

	public void setIsScreen(Integer isScreen) {
		this.isScreen = isScreen;
	}

	public Date getTopTime() {
		return topTime;
	}

	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}

	public Integer getIsTimeingPost() {
		return isTimeingPost;
	}

	public void setIsTimeingPost(Integer isTimeingPost) {
		this.isTimeingPost = isTimeingPost;
	}

	public Date getTimeingPostTime() {
		return timeingPostTime;
	}

	public void setTimeingPostTime(Date timeingPostTime) {
		this.timeingPostTime = timeingPostTime;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public Integer getIsAnswered() {
		return isAnswered;
	}

	public void setIsAnswered(Integer isAnswered) {
		this.isAnswered = isAnswered;
	}

	public Integer getIsVote() {
		return isVote;
	}

	public void setIsVote(Integer isVote) {
		this.isVote = isVote;
	}

	public Integer getMaxChoices() {
		return maxChoices;
	}

	public void setMaxChoices(Integer maxChoices) {
		this.maxChoices = maxChoices;
	}

	public Integer getIsVoteUserOvert() {
		return isVoteUserOvert;
	}

	public void setIsVoteUserOvert(Integer isVoteUserOvert) {
		this.isVoteUserOvert = isVoteUserOvert;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUsername() {
		return username;
	}

	public Integer getNotecount() {
		return notecount;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setNotecount(Integer notecount) {
		this.notecount = notecount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}