package com.cxdai.console.curaccount.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CurInOutAccountVo implements Serializable {
	
	private static final long serialVersionUID = -6903226903535940857L;

	//用户名
	private String username;
	
	private BigDecimal total;
	
	private BigDecimal account;
	
	private Date addtime;
	
	private int type;
	
	private Integer flag;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
}