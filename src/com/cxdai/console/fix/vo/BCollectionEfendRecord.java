package com.cxdai.console.fix.vo;

import com.cxdai.console.util.DateUtils;

/**
 * Created by Administrator on 2016/5/31.
 */
public class  BCollectionEfendRecord extends BCollectionRecordVo {

    public String name;
    public Integer borrowtype;
    public Integer style;
    private String time_limitStr;
    private String repay_timeStr;
    private Integer timeLimit;
    private Integer borrowOrder;
    private String eRepayMentStatusStr;
    private String username;

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public Integer getBorrowtype() {
        return borrowtype;
    }

    public void setBorrowtype(Integer borrowtype) {
        this.borrowtype = borrowtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTime_limitStr() {
        if (getBorrowtype() == 4) {
            return "秒还";
        } else if (getBorrowtype() != 4 && getStyle() != 4) {
            return timeLimit + "个月";
        } else if (getBorrowtype() != 4 && getStyle() == 4) {
            return timeLimit + "天";
        }
        return "数据异常";
    }

    public void setTime_limitStr(String time_limitStr) {
        this.time_limitStr = time_limitStr;
    }

    public String getRepay_timeStr() {
        if (null != this.getRepayTime()) {
            return DateUtils.TimeStamp2Date(this.getRepayTime());
        }
        return repay_timeStr;
    }

    public void setRepay_timeStr(String repay_timeStr) {
        this.repay_timeStr = repay_timeStr;
    }


    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getBorrowOrder() {
        return borrowOrder;
    }

    public void setBorrowOrder(Integer borrowOrder) {
        this.borrowOrder = borrowOrder;
    }

    public String geteRepayMentStatusStr() {
        Integer stt =geteRepayMentStatus();
        if(stt ==null ){
            return eRepayMentStatusStr;
        }
        if(stt.intValue() ==0 ){
            return "不存在或未处理";
        }else if(stt.intValue() == 10){
            return "处理中";
        }else if(stt.intValue() ==20){
            return "成功";
        } else if(stt.intValue() ==30){
            return "失败";
        }else {
            return "未知状态";

        }
    }

    public void seteRepayMentStatusStr(String eRepayMentStatusStr) {
        this.eRepayMentStatusStr = eRepayMentStatusStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
