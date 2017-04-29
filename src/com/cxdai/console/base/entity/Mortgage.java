package com.cxdai.console.base.entity;

import java.io.Serializable;
import java.util.Date;

public class Mortgage implements Serializable{
	
	private static final long serialVersionUID = 1867449258541065347L;

	private Integer id;					//自增主键
	private Integer borrowId;			//rocky_borrow借款人表ID
	private Integer hasHouse;			//是否拥有房产 1- 是  0- 否 
	private Integer hasHouseMortgage;	//是否拥有房贷：	1- 是   0-否
	private String houseLocation;		//房产位置
	private String houseArea;			//房产面积
	private Integer hasCar;				//是否拥有车产：	1- 是   0-否 
	private Integer hasCarMortgage;		//是否拥有车贷：1-是  0- 否
	private String carNo;				//车辆型号
	private String carValue;			//车辆价值
	private Date addtime;				//添加时间
	private String addip;				//添加IP 
	
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
	public Integer getHasHouse() {
		return hasHouse;
	}
	public void setHasHouse(Integer hasHouse) {
		this.hasHouse = hasHouse;
	}
	public Integer getHasHouseMortgage() {
		return hasHouseMortgage;
	}
	public void setHasHouseMortgage(Integer hasHouseMortgage) {
		this.hasHouseMortgage = hasHouseMortgage;
	}
	public String getHouseLocation() {
		return houseLocation;
	}
	public void setHouseLocation(String houseLocation) {
		this.houseLocation = houseLocation;
	}
	public String getHouseArea() {
		return houseArea;
	}
	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}
	public Integer getHasCar() {
		return hasCar;
	}
	public void setHasCar(Integer hasCar) {
		this.hasCar = hasCar;
	}
	public Integer getHasCarMortgage() {
		return hasCarMortgage;
	}
	public void setHasCarMortgage(Integer hasCarMortgage) {
		this.hasCarMortgage = hasCarMortgage;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getCarValue() {
		return carValue;
	}
	public void setCarValue(String carValue) {
		this.carValue = carValue;
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
