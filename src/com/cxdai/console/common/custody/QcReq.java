package com.cxdai.console.common.custody;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * <p>
 * Description: 开户信息查询<br />
 * </p>
 *
 * @author zhaowei
 * @version 0.1 2016/5/18
 * @title cxdai_interface
 * @package com.cxdai.common.custody
 */
@XStreamAlias("QCReq")
public class QcReq extends BaseReq {

    @XStreamAsAttribute
    private String id = "QCReq";

    /**
     * P2P平台客户号
     */
    @XStreamAlias("Cstno")
    private String cstno;

    public String getCstno() {
        return cstno;
    }

    public void setCstno(String cstno) {
        this.cstno = cstno;
    }
}
