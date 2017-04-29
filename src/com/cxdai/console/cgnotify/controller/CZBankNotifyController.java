package com.cxdai.console.cgnotify.controller;

import com.cxdai.console.base.entity.MailSendRecord;
import com.cxdai.console.base.mapper.BaseMailSendRecordMapper;
import com.cxdai.console.borrow.check.service.BorrowRecheckService;
import com.cxdai.console.borrow.manage.service.CGRepayMentService;
import com.cxdai.console.cg.entity.MessageRecord;
import com.cxdai.console.cg.mapper.MessageRecordMapper;
import com.cxdai.console.cgnotify.entity.EReconHeader;
import com.cxdai.console.cgnotify.service.CZBankAccountService;
import com.cxdai.console.cgnotify.service.CZBankReconNotifyService;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.custody.FinanceFactory;
import com.cxdai.console.common.custody.QcReq;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.google.common.io.Files;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

import static com.cxdai.console.common.custody.xml.XmlUtil.getDocument;
import static com.cxdai.console.common.custody.xml.XmlUtil.getFirstElementValue;

/**
 * <p>
 * Description:浙商银行通知Controller<br />
 * </p>
 *
 * @author zhaowei
 * @version 0.1 2016年5月18日
 * @title CZBankNotifyController.java
 * @package com.cxdai.portal.account.controller
 */
@Controller
@RequestMapping(value = "/cgnotify/czbank")
public class CZBankNotifyController extends BaseController {

    public Logger logger = Logger.getLogger(CZBankNotifyController.class);

    @Autowired
    protected CZBankAccountService czBankAccountService;
    @Autowired
    protected MessageRecordMapper messageRecordMapper;
    @Autowired
    protected CZBankReconNotifyService czBankReconNotifyService;
    @Autowired
    protected BorrowRecheckService borrowRecheckService;
    @Autowired
    protected CGRepayMentService cgRepayMentService;
    @Autowired
    private BaseMailSendRecordMapper baseMailSendRecordMapper;
    @Autowired
    private MemberMapper memberMapper;

    /**
     * <p>
     * Description:浙商银行开户回调接口<br />
     * </p>
     *
     * @return
     * @throws Exception String
     * @author zhaowei
     * @version 0.1 2016年5月19日
     */
    @RequestMapping("/receive")
    @ResponseBody
    public String onReceive(@RequestBody String message) {
        if (message.contains("OCNotify")) {
            // 开户异步通知
            return this.ocNotify(message);
        } else if (message.contains("BTFNotify")) {
            // 批量扣款通知
            return this.btfNotify(message);
        } else if (message.contains("BRFNotify")) {
            // 批量还款通知
            return this.brfNotify(message);
        } else if (message.contains("BIOTFNotify")) {
            // 出入账通知
            return this.biotfNotify(message);
        } else if (message.contains("CMANotify")) {
            // 修改存管主账户他行主账户通知
            return this.cmaNotify(message);
        }

        try {
            Document reqDoc = getDocument(message);
            String filename = getFirstElementValue(reqDoc, "fileName");
            this.addMessageRecord(2, "14", message, null, null, getFilename(filename), "未知对账文件通知");
        } catch (Exception e) {
            logger.error("对账通知异常", e);
            this.addMessageRecord(2, "14", message, null, null, "未解析到文件名", "未知对账文件通知");
        }
        return "FAILURE";
    }

    @RequestMapping("/queryOa")
    public ModelAndView queryOa(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("/custody/single/oaResult");
        try {
            String username = WebUtils.findParameterValue(req, "username");
            if (!StringUtils.hasLength(username)) {
                mv.addObject("isOaOk", false);
                mv.addObject("msg", "用户名不能为空");
                return mv;
            }
            MemberVo memberVo = memberMapper.queryMemberVoByUsername(username);
            if (memberVo == null) {
                mv.addObject("isOaOk", false);
                mv.addObject("msg", "用户名不存在");
                return mv;
            }
            Integer userId = memberVo.getId();
            // 调用浙商银行接口验证是否已开通
            QcReq qcReq = new QcReq();
            qcReq.setCstno(userId.toString());
            qcReq.setDate(new Date());
            // 创建请求实例
            FinanceFactory.FinanceProxy financeProxy = FinanceFactory.getInstance()
                    .create()
                    .setModel(qcReq)
                    .aliasMode("QCReq");

            // 首先调用接口前插入一条通讯报文记录
            String relateNo = getRelateNo();
            this.addMessageRecord(1, "15", financeProxy.toXML(),
                    relateNo, userId, "开户信息查询请求");
            // 请求浙商银行接口
            FinanceFactory.FinanceResponse res = financeProxy.send();
            // 接口返回报文记录
            this.addMessageRecord(2, "15", res.getResponse(),
                    relateNo, userId, "开户信息查询响应");

            Document resDoc = res.getResDocument();
            if (resDoc != null && !res.isHasError()) {
                mv.addObject("isOaOk", true);
                mv.addObject("accountName", getFirstElementValue(resDoc, "accountName"));
                mv.addObject("certType", getFirstElementValue(resDoc, "certType"));
                mv.addObject("certNo", getFirstElementValue(resDoc, "certNo"));
                mv.addObject("mobile", getFirstElementValue(resDoc, "mobile"));
                mv.addObject("stt", getFirstElementValue(resDoc, "stt"));
                mv.addObject("bindSerialNo", getFirstElementValue(resDoc, "bindSerialNo"));
                mv.addObject("eCardNo", getFirstElementValue(resDoc, "EcardNo"));
                mv.addObject("otherAccno", getFirstElementValue(resDoc, "otherAccno"));
                mv.addObject("bankName", getFirstElementValue(resDoc, "bankName"));
                mv.addObject("cstNo", getFirstElementValue(resDoc, "Cstno"));
                mv.addObject("extension", getFirstElementValue(resDoc, "extension"));
            } else if (res.isHasError()) {
                mv.addObject("isOaOk", false);
                mv.addObject("msg", res.getErrorMsg());
            } else {
                mv.addObject("isOaOk", false);
                mv.addObject("msg", "返回结果异常");
            }
        } catch (Exception e) {
            mv.addObject("isOaOk", false);
            mv.addObject("msg", "请求超时");
        }
        return mv;
    }

    private String getRelateNo() {
        return uuid();
    }

    private String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * <p>
     * Description:浙商银行开户回调接口<br />
     * </p>
     *
     * @return
     * @throws Exception String
     * @author zhaowei
     * @version 0.1 2016年5月19日
     */
    protected String ocNotify(String message) {
        Assert.hasLength(message, "message must be have length");

        try {
            this.addMessageRecord(2, "1", message, null, null, "开立存管账户通知");
            boolean isValid = FinanceFactory.getInstance().checkSign(message);
            // 验签通过
            if (isValid) {
                // 更新返回结果
                czBankAccountService.saveEBankInfo(message);
            }
        } catch (Exception e) {
            logger.error("开户通知处理异常", e);
            return "FAILURE";
        }

        return "SUCCESS";
    }

    /**
     * 批量扣款对账文件通知接口
     *
     * @param message 报文
     * @return 处理结果
     */
    protected String btfNotify(String message) {
        String result = BusinessConstants.SUCCESS;
        Assert.hasLength(message, "message must be have length");

        try {
            Document reqDoc = getDocument(message);
            String digest = getFirstElementValue(reqDoc, "digest");
            String filename = getFirstElementValue(reqDoc, "fileName");
            String projectId = getFirstElementValue(reqDoc, "ProjectId");
            this.addMessageRecord(2, "14", message, null, null, getFilename(filename), "批量扣款对账文件通知");
            // 处理通知
            this.processNotify(1, message, filename, projectId, digest);
        } catch (Exception ex) {
            logger.error("BTFNotify process failed", ex);
        }

        return result;
    }


    /**
     * 批量还款对账文件通知接口
     *
     * @param message 报文
     * @return 处理结果
     */
    protected String brfNotify(@RequestBody String message) {
        String result = BusinessConstants.SUCCESS;
        Assert.hasLength(message, "message must be have length");

        try {
            Document reqDoc = getDocument(message);
            String digest = getFirstElementValue(reqDoc, "digest");
            String filename = getFirstElementValue(reqDoc, "fileName");
            String projectId = getFirstElementValue(reqDoc, "ProjectId");
            this.addMessageRecord(2, "14", message, null, null, getFilename(filename), "批量还款对账文件通知");
            // 处理通知
            this.processNotify(2, message, filename, projectId, digest);
        } catch (Exception ex) {
            logger.error("BRFNotify process failed", ex);
        }
        return result;
    }

    /**
     * 出入账通知对账接口
     *
     * @param message 报文
     * @return 处理结果
     */
    protected String biotfNotify(@RequestBody String message) {
        String result = BusinessConstants.SUCCESS;
        Assert.hasLength(message, "message must be have length");

        try {
            Document reqDoc = getDocument(message);
            String digest = getFirstElementValue(reqDoc, "digest");
            String filename = getFirstElementValue(reqDoc, "fileName");
            this.addMessageRecord(2, "14", message, null, null, getFilename(filename), "出入账对账文件通知");
            // 处理通知
            this.processNotify(4, message, filename, null, digest);
        } catch (Exception ex) {
            logger.error("BIOTFNotify process failed", ex);
        }

        return result;
    }

    /**
     * 修改存管e户他行主账户通知
     *
     * @param message 报文
     * @return 处理结果
     */
    private String cmaNotify(String message) {
        String result = BusinessConstants.SUCCESS;
        Assert.hasLength(message, "message must be have length");

        try {
            this.addMessageRecord(2, "22", message, null, null, "修改存管e户他行主账户通知");
            boolean isValid = FinanceFactory.getInstance().checkSign(message);
            // 验签通过
            if (isValid) {
                // 更新返回结果
                czBankAccountService.updateEBankInfo(message);
            }
        } catch (Exception ex) {
            logger.error("CMANotify process failed", ex);
            return "FAILURE";
        }

        return result;
    }

    /**
     * 获取文件名不包含扩展名
     *
     * @param filePath 文件路径
     * @return 文件名称
     */
    private String getFilename(String filePath) {
        return Files.getNameWithoutExtension(filePath);
    }

    /**
     * 处理通知
     *
     * @param type      通知类型
     * @param message   报文
     * @param filename  文件名称
     * @param projectId 项目ID
     * @param digest    摘要
     * @throws Exception
     */
    protected void processNotify(int type, String message, String filename,
                                 String projectId, String digest) throws Exception {
        boolean isValid = FinanceFactory.getInstance().checkSign(message);
        // 验签通过
        if (isValid) {
            // 下载文件
            String tmpFilePath = czBankReconNotifyService.downloadFileFromFtp(type, filename);
            // 验证摘要
            isValid = czBankReconNotifyService.checkDigest(type, tmpFilePath, digest);
            // 摘要验证通过
            if (isValid) {
                // 解密并解压文件
                String csvFile = czBankReconNotifyService.unzipFile(type, tmpFilePath);
                // 读取CSV文件并保存到数据库
                EReconHeader header = czBankReconNotifyService.saveCSVData(type, csvFile, projectId);

                if (type == 4) {
                    // 出入账不进行系统自动对账，在后台管理中增加查询对账功能
                    return;
                }
                // 数据对账
                boolean successful = czBankReconNotifyService.saveReconResult(type, projectId,
                        header.getStatus(), header.getId(), filename);

                // 对账成功
                if (successful) {
                    Integer status = header.getStatus();
                    String ip = currentRequest().getRemoteHost();
                    if (type == 1 && status == 0) {
                        // 流标
                        borrowRecheckService.flowBorrow(projectId, ip, -2);
                    } else if (type == 1 && status == 1) {
                        // 满标
                        borrowRecheckService.recheckBorrow(projectId, -2,
                                "系统复审存管标", ip);
                    } else if (type == 2) {
                        // 正常还款
                        cgRepayMentService.doNormalRepayRecon(projectId, filename);
                    }
                } else {
                    // 发送邮件
                    //新增邮件待发记录
                    MailSendRecord mailSendRecord = new MailSendRecord();
                    mailSendRecord.setTypeId(header.getId());
                    mailSendRecord.setType(14);
                    mailSendRecord.setStatus(0);
                    mailSendRecord.setAddtime(new Date());
                    mailSendRecord.setRemark(filename);
                    baseMailSendRecordMapper.insertEntity(mailSendRecord);

                    if (type == 1) {
                        Integer status = header.getStatus();
                        borrowRecheckService.saveErrorBorrow(status, -2, projectId, "对账失败");
                    }
                }
            }
        }
    }

    /**
     * @param type     1: 主动；2：被动
     * @param relateNo 关联号
     */
    protected void addMessageRecord(Integer type, String model, String msg, String relateNo,
                                    Integer userId, String bindNo, String remark) {
        // 接口返回报文记录
        MessageRecord messageRecord = new MessageRecord();
        // 开户查询
        messageRecord.setMode(model);
        // 主动
        messageRecord.setType(type);
        messageRecord.setMsg(msg);
        messageRecord.setOptUserid(userId);
        messageRecord.setPlatform(1);
        messageRecord.setRelateNo(relateNo);
        messageRecord.setBindNo(bindNo);
        messageRecord.setRemark(remark);
        messageRecordMapper.insert(messageRecord);
    }

    /**
     * @param type     1: 主动；2：被动
     * @param relateNo 关联号
     */
    protected void addMessageRecord(Integer type, String model, String msg, String relateNo,
                                    Integer userId, String remark) {
        // 接口返回报文记录
        MessageRecord messageRecord = new MessageRecord();
        // 开户查询
        messageRecord.setMode(model);
        // 主动
        messageRecord.setType(type);
        messageRecord.setMsg(msg);
        messageRecord.setOptUserid(userId);
        messageRecord.setPlatform(1);
        messageRecord.setRelateNo(relateNo);
        messageRecord.setRemark(remark);
        messageRecordMapper.insert(messageRecord);
    }

}
