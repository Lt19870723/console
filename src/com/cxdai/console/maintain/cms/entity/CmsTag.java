package com.cxdai.console.maintain.cms.entity;

public class CmsTag {
    private Integer id;

    private String name;

	private int status = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public int getStatus() {
        return status;
    }

	public CmsTag(String name) {
		super();
		this.name = name;
	}

	public CmsTag() {
		super();
	}

	public void setStatus(int status) {
        this.status = status;
    }
}