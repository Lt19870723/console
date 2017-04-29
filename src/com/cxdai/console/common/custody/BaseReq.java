package com.cxdai.console.common.custody;

import com.cxdai.console.util.PropertiesUtil;
import com.cxdai.console.util.PropertiesUtil;

import java.util.Date;

/**
 * <p>
 * Description:  基础的请求<br />
 * </p>
 *
 * @author zhaowei
 * @version 0.1 2016/5/18
 * @title cxdai_interface
 * @package com.cxdai.common.custody
 */
public class BaseReq extends CustodyBiz {

    /**
     * 版本号
     */
    private String version = PropertiesUtil.getValue("custody_version");
    /**
     * 机构号
     */
    private String instId = PropertiesUtil.getValue("custody_instId");
    /**
     * 数字证书标识
     */
    private String certId = PropertiesUtil.getValue("custody_certId");
    /**
     * 发送时间
     */
    private Date date = new Date();
    /**
     * 消息扩展
     */
    private String extension;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
