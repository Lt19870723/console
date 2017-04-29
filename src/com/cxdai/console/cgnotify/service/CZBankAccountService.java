package com.cxdai.console.cgnotify.service;

import com.cxdai.console.base.entity.BaseEBankInfo;
import com.cxdai.console.base.entity.BaseEBankInfoLog;
import com.cxdai.console.base.mapper.BaseEBankInfoLogMapper;
import com.cxdai.console.base.mapper.BaseEBankInfoMapper;
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.customer.information.vo.MemberVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;

import static com.cxdai.console.common.custody.xml.XmlUtil.getDocument;
import static com.cxdai.console.common.custody.xml.XmlUtil.getFirstElementValue;

/**
 * <p>
 * Description: 保存银行绑定信息<br />
 * </p>
 *
 * @author zhaowei
 * @version 0.1 2016/5/19
 * @title cxdai_interface
 * @package com.cxdai.portal.account.service.impl
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CZBankAccountService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private BaseEBankInfoMapper baseEBankInfoMapper;
    @Autowired
    private BaseEBankInfoLogMapper baseEBankInfoLogMapper;

    private void saveEBankInfo(Document resDoc, Integer userId) throws Exception {
        Assert.notNull(resDoc, "resDoc cannot null");

        String accountName = getFirstElementValue(resDoc, "accountName");
        String certType = getFirstElementValue(resDoc, "certType");
        String certNo = getFirstElementValue(resDoc, "certNo");
        String mobile = getFirstElementValue(resDoc, "mobile");
        String stt = getFirstElementValue(resDoc, "stt");
        String bindSerialNo = getFirstElementValue(resDoc, "bindSerialNo");
        String eCardNo = getFirstElementValue(resDoc, "EcardNo");
        String otherAccno = getFirstElementValue(resDoc, "otherAccno");
        String bankName = getFirstElementValue(resDoc, "bankName");
        String cstNo = getFirstElementValue(resDoc, "Cstno");
        String extension = getFirstElementValue(resDoc, "extension");
        Assert.hasLength(certType, "certType mustbe have length");
        Assert.hasLength(extension, "extension mustbe have length");
        Assert.hasLength(bindSerialNo, "bindSerialNo mustbe have length");

        if (userId == null) {
            Assert.hasLength(cstNo, "cstNo must be have length");
            userId = Integer.parseInt(cstNo);
        }

        // 查询用户手机号及实名认证信息，用于组装报文
        MemberVo memberVo = memberMapper.queryUserBaseInfo(userId);
        mobile = StringUtils.hasLength(mobile) ? mobile : memberVo.getMobileNum();
        certNo = StringUtils.hasLength(certNo) ? certNo : memberVo.getIdCardNo();
        accountName = StringUtils.hasLength(accountName) ? accountName : memberVo.getRealName();
        if ("1".equals(stt)) {
            // 锁定用户并更新存管类型
            memberVo = memberMapper.queryMemberByIdForUpdate(userId);
            Assert.notNull(memberVo, "mmemberVo cannot null");
            memberVo.setIsCustody(1);
            memberVo.seteType(Integer.parseInt(extension) + 1);
            // 更新条件
            memberVo.setStatus(0);
            int i = memberMapper.updateMemberForCustody(memberVo);
            if (i > 0) {
                // 更新成功后处理
                BaseEBankInfo eBankInfo = baseEBankInfoMapper.selectByUserIdForUpdate(userId);
                boolean isUpdate = true;
                if (eBankInfo == null) {
                    isUpdate = false;
                    eBankInfo = new BaseEBankInfo();
                }
                eBankInfo.setUserId(userId);
                eBankInfo.setBankname("浙商银行");
                eBankInfo.setEcardNo(eCardNo);
                eBankInfo.setRealname(accountName);
                eBankInfo.setCerttype(Integer.parseInt(certType));
                eBankInfo.setCertNo(certNo);
                eBankInfo.setMobile(mobile);
                eBankInfo.setStatus(1);
                eBankInfo.setBindNo(bindSerialNo);
                eBankInfo.setCardtype(memberVo.geteType());
                eBankInfo.setCustodyBindName(bankName);
                eBankInfo.setCustodyBindNo(otherAccno);
                if (isUpdate) {
                    baseEBankInfoMapper.updateByPrimaryKeySelective(eBankInfo);
                } else {
                    baseEBankInfoMapper.insertSelective(eBankInfo);
                }
                // 记录变更日志
                BaseEBankInfoLog log = new BaseEBankInfoLog();
                log.setEbankinfoId(eBankInfo.getId());
                log.setUserId(userId);
                log.setBankname("浙商银行");
                log.setEcardNo(eCardNo);
                log.setRealname(accountName);
                log.setCerttype(Integer.parseInt(certType));
                log.setCertNo(certNo);
                log.setMobile(mobile);
                log.setStatus(1);
                log.setBindNo(bindSerialNo);
                log.setCardtype(memberVo.geteType());
                log.setCustodyBindName(bankName);
                log.setCustodyBindNo(otherAccno);
                baseEBankInfoLogMapper.insertSelective(log);
            }
        }
    }

    public void saveEBankInfo(String res) throws Exception {
        Assert.hasLength(res, "res must be have length");

        try {
            Document resDoc = getDocument(res);
            this.saveEBankInfo(resDoc, null);
        } catch (Exception e) {
            logger.error("saveEbankInfo error", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updateEBankInfo(String res) throws Exception {
        Assert.hasLength(res, "res must be have length");

        try {
            Document resDoc = getDocument(res);
            String otherAccno = getFirstElementValue(resDoc, "otherAccno");
            String bankName = getFirstElementValue(resDoc, "bankName");
            String cstNo = getFirstElementValue(resDoc, "Cstno");
            BaseEBankInfo record = new BaseEBankInfo();
            Assert.hasLength(cstNo, "cstNo must be have length");

            record.setUserId(Integer.valueOf(cstNo));
            record.setCustodyBindName(bankName);
            record.setCustodyBindNo(otherAccno);
            baseEBankInfoMapper.updateByUserId(record);
        } catch (Exception e) {
            logger.error("updateEBankInfo error", e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
