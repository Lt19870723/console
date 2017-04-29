package com.cxdai.console.cgnotify.vo;

import java.math.BigDecimal;

/**
 * <p>
 * Description: <br />
 * </p>
 *
 * @author zhaowei
 * @version 0.1 2016/5/27
 * @title cxdai_interface
 * @package com.cxdai.console.cgnotify
 */
public class EReconHeaderVo {

    /**
     * 状态
     */
    private Integer status;
    /**
     * 成功笔数
     */
    private Integer successCount;
    /**
     * 成功总金额
     */
    private BigDecimal successTotal;
    /**
     * 失败笔数
     */
    private Integer failCount;
    /**
     * 失败总金额
     */
    private BigDecimal failTotal;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public BigDecimal getSuccessTotal() {
        return successTotal;
    }

    public void setSuccessTotal(BigDecimal successTotal) {
        this.successTotal = successTotal;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public BigDecimal getFailTotal() {
        return failTotal;
    }

    public void setFailTotal(BigDecimal failTotal) {
        this.failTotal = failTotal;
    }
}
