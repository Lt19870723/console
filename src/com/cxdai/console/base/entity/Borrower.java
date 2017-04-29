package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.util.Date;

public class Borrower implements Serializable{

	private static final long serialVersionUID = 5809304495343612620L;
	
	private Integer id;				//自增主键
	private Integer borrowId;		//rocky_borrow借款人表ID
	private Integer maritalStatus;	//婚姻状况 1- 已婚    2-未婚    3-离异    4-丧偶  
	private Integer education;		//学历
	private String industry;		//公司行业
	private Integer scale;			//公司规模
	private String jobTitle;		//岗位职位
	private String workCity;		//工作城市
	private Integer workYears;		//工作时间
	private Date addtime;			//添加时间
	private String addip;			//添加IP
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
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
	public Integer getScale() {
		return scale;
	}
	public void setScale(Integer scale) {
		this.scale = scale;
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
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public String getAddip() {
		return addip;
	}
	public void setAddip(String addip) {
		this.addip = addip;
	}

	
}
