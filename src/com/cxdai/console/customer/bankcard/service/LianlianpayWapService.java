package com.cxdai.console.customer.bankcard.service;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.Constants;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.customer.bankcard.entity.BankInfoCnd;
import com.cxdai.console.customer.bankcard.entity.LlWapBankcardUnbindRequest;
import com.cxdai.console.customer.bankcard.mapper.BankInfoMapper;
import com.cxdai.console.customer.bankcard.vo.BankInfoVo;
import com.cxdai.console.customer.bankcard.vo.LlWapBankcardUnbindResponse;
import com.cxdai.console.util.JsonUtils;
import com.cxdai.console.util.LLPayUtil;
import com.cxdai.console.util.SignAndVerify;
import com.cxdai.console.util.file.FileUtil;
import com.cxdai.console.util.httputil.HttpURLUtil;



/**
 * <p>
 * Description:连连手机支付service<br />
 * </p>
 * 
 * @title LianlianpayWapServiceImpl.java
 * @package com.cxdai.console.lianlian.service.impl
 * @author justin.xu
 * @version 0.1 2015年4月8日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LianlianpayWapService{

	@Autowired
	private BankInfoMapper bankInfoMapper;

	
	public String saveBankcardUnbind(LlWapBankcardUnbindRequest llWapBankcardUnbindRequest) throws Exception {
		if (null == llWapBankcardUnbindRequest || null == llWapBankcardUnbindRequest.getUser_id() || "".equals(llWapBankcardUnbindRequest.getUser_id().trim())) {
			return "未找到此用户";
		}
		BankInfoCnd bankInfoCnd = new BankInfoCnd();
		bankInfoCnd.setUserId(Integer.parseInt(llWapBankcardUnbindRequest.getUser_id()));
		bankInfoCnd.setVerifyStatus(Constants.BANK_INFO_VERIFY_STATUS_YES);
		List<BankInfoVo> bankInfoList = bankInfoMapper.queryBankInfoList(bankInfoCnd);
		if (null == bankInfoList || bankInfoList.size() == 0 || bankInfoList.size() > 1) {
			return "未找到用户绑定的银行卡";
		}
		BankInfoVo bankInfoVo = bankInfoList.get(0);
		if (null == bankInfoVo.getNoAgree() || "".equals(bankInfoVo.getNoAgree().trim())) {
			return "用户未签约银行卡协议号,请确认";
		}

		llWapBankcardUnbindRequest.setOid_partner(BusinessConstants.ONLINE_PAY_LIANLIANPAY_OID_PARTNER);
		llWapBankcardUnbindRequest.setPay_type("D");// 手机认证支付
		llWapBankcardUnbindRequest.setSign_type(BusinessConstants.ONLINE_PAY_LIANLIANPAY_SIGNTYPE);
		llWapBankcardUnbindRequest.setNo_agree(bankInfoVo.getNoAgree());

		// 设置签名密钥
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.decodeBase64(FileUtil.readFile(
				LianlianpayWapService.class.getResource("/").getPath() + BusinessConstants.ONLINE_PAY_LIANLIANPAY_RPIVATE_KEY).toString()));
		KeyFactory fact = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = fact.generatePrivate(spec);
		String signContent = LLPayUtil.generateToSignContent(llWapBankcardUnbindRequest);
		String signMsg = SignAndVerify.sign("MD5withRSA", signContent, privateKey);
		llWapBankcardUnbindRequest.setSign(signMsg);

		// 得到请求后的json字符串
		String arguments = JsonUtils.bean2Json(llWapBankcardUnbindRequest);
		String resultJson = HttpURLUtil.doPost(BusinessConstants.ONLINE_PAY_LLWAP_CARD_UNBIND_URL, arguments, "UTF-8");
		LlWapBankcardUnbindResponse llWapBankcardUnbindResponse = JsonUtils.json2Bean(resultJson, LlWapBankcardUnbindResponse.class);
		if (null == llWapBankcardUnbindResponse || null == llWapBankcardUnbindResponse.getRet_code() || !"0000".equals(llWapBankcardUnbindResponse.getRet_code())) {
			return "银行卡信息有误,请确认！";
		}
		// 验签是否成功
		if (!LLPayUtil.validateSignMsg(llWapBankcardUnbindResponse)) {
			return "解绑银行卡安全验证出错，请联系客服！";
		}
		// 去除用户绑定的协议号
		bankInfoMapper.updateNoAgreeByUserId(Integer.parseInt(llWapBankcardUnbindRequest.getUser_id()), null);
		return BusinessConstants.SUCCESS;
	}

}
