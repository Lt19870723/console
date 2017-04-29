package com.cxdai.console.borrow.manage.vo;

import com.cxdai.console.util.DateUtils;

import java.util.Date;

/**
 * Created by Administrator on 2016/6/15.
 */
public class OrderRangeVo {

    public Date startTime;
    public Date endTime;
    public Integer order;
    public Integer lastOrder;

    public int diffStartDay;
    public int diffEndDay;
    public int interval;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }


    public Integer getLastOrder() {
        return lastOrder;
    }

    public void setLastOrder(Integer lastOrder) {
        this.lastOrder = lastOrder;
    }


    public int getDiffStartDay() {
        return DateUtils.dayDiff(getStartTime(),new Date());
    }

    public void setDiffStartDay(int diffStartDay) {
        this.diffStartDay = diffStartDay;
    }

    public int getDiffEndDay() {
        return DateUtils.dayDiff(getEndTime(),new Date());
    }

    public void setDiffEndDay(int diffEndDay) {
        this.diffEndDay = diffEndDay;
    }


    public int getInterval() {
        Date stime = DateUtils.parse(DateUtils.format(getStartTime(), DateUtils.YMD_DASH), DateUtils.YMD_DASH);
        Date etime = DateUtils.parse(DateUtils.format(getEndTime(), DateUtils.YMD_DASH), DateUtils.YMD_DASH);
        return DateUtils.dayDiff(etime,stime);
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }



    @Override
    public String toString() {
        return "OrderRangeVo{" +
                " \n startTime=" + DateUtils.format(getStartTime(),DateUtils.YMD_DASH) +
                ",\n endTime=" + DateUtils.format(getEndTime(),DateUtils.YMD_DASH) +
                ",\n order=" + order +
                ",\n lastOrder=" + lastOrder +
                ",\n diffStartDay=" + getDiffStartDay() +
                ",\n diffEndDay=" +  getDiffEndDay() +
                ",\n Interval=" +  getInterval() +
                '}';
    }


}
