package com.cxdai.console.curaccount.entity;

import java.util.Date;

public class CurNoworkDay {
    private Integer id;

    private Date startDate;

    private Date endDate;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CurNoworkDay [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", name=" + name + "]";
	}
    
    
    
    
}