package com.cxdai.console.maintain.cms.entity;

import java.io.Serializable;

import com.cxdai.console.common.page.BaseCnd;

public class CmsChannelCnd extends BaseCnd implements Serializable {

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }



}