package com.cxdai.console.cgnotify.vo;

import com.cxdai.console.common.page.BaseCnd;

import java.util.Date;

/**
 * <p>
 * Description: <br />
 * </p>
 *
 * @author zhaowei
 * @version 0.1 2016/6/6
 * @title cxdai_interface
 * @package com.cxdai.console.cgnotify.vo
 */
public class ReconResultCnd extends BaseCnd {

    private Date beginTime;

    private Date endTime;

    private String beginTimeStr;
    private String endTimeStr;

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getBeginTimeStr() {
        return beginTimeStr;
    }

    public void setBeginTimeStr(String beginTimeStr) {
        this.beginTimeStr = beginTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }
}
