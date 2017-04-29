package com.cxdai.console.sms.service.impl;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.console.base.mapper.BaseSmsRecordMapper;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.sms.mapper.SmsRecordMapper;
import com.cxdai.console.sms.service.SmsSendService;
import com.cxdai.console.sms.service.SmsTemplateService;
import com.cxdai.console.sms.vo.SendMobileCnd;
import com.cxdai.console.sms.vo.SmsRecord;
import com.cxdai.console.sms.vo.SmsTemplateCnd;
import com.cxdai.console.sms.vo.SmsTemplateVo;
import com.cxdai.console.util.SmsUtil;
import com.cxdai.console.util.ZucpSmsUtil;
import com.cxdai.console.util.service.PhoneService;

/**
 * <p>
 * Description:短信发送Service实现类<br />
 * </p>
 * 
 * @title SmsSendServiceImpl.java
 * @package com.cxdai.portal.sms.service.impl
 * @author justin.xu
 * @version 0.1 2014年4月30日
 */
@Service
public class SmsSendServiceImpl implements SmsSendService {
	@Autowired
	private BaseSmsRecordMapper baseSmsRecordMapper;

	@Autowired
	private SmsTemplateService smsTemplateService;
	@Autowired
	private PhoneService phoneService;
	@Autowired
	private SmsRecordMapper smsRecordMapper;

	/**
	 * <p>
	 * Description:根据模板发送短信<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年11月17日
	 * @param sendMobileCnd
	 * @throws Exception
	 *             void
	 */
	@Override
	public String saveTemplateMessage(SendMobileCnd sendMobileCnd) throws Exception {
		// 验证参数是否正确，如果需要验证码，必须指定模块名
		if (null == sendMobileCnd || null == sendMobileCnd.getSmsTemplateType() || null == sendMobileCnd.getMobile()
				|| (sendMobileCnd.getNeedRandcode() && null == sendMobileCnd.getModelName())) {
			return "参数错误,请确认";
		}
		// 获取短信模板
		SmsTemplateVo smsTempale = smsTemplateService.querySmsTemplateByType(sendMobileCnd.getSmsTemplateType());
		SmsRecord smsRecord = new SmsRecord();
		if (sendMobileCnd.getNeedRandcode()) {
			// 获得验证码并存入缓存
			String randCode = phoneService.querySmsValidate(sendMobileCnd.getMobile(), sendMobileCnd.getModelName());
			sendMobileCnd.getParamMap().put("randCode", randCode);
		}
		// 封装模板参数
		String content = SmsUtil.generateParamContent(smsTempale.getContent(), sendMobileCnd.getParamMap());
		smsRecord.setAddip(sendMobileCnd.getIp());
		smsRecord.setAddtime(new Date());
		// 发送短信
		smsRecord.setContent(content);
		smsRecord.setMobile(sendMobileCnd.getMobile());
		smsRecord.setSmsType(sendMobileCnd.getSmsTemplateType());
		smsRecord.setSendStatus(BusinessConstants.SMS_SEND_STATUS_WAIT);
		smsRecord.setLastmodifytime(new Date());
		smsRecord.setSmsTemplateType(sendMobileCnd.getSmsTemplateType());
		smsRecord.setPlatform(sendMobileCnd.getPlatform()); // 设置平台来源
		return this.saveSmsByZucp(smsRecord);
	}

	public String saveSmsByZucp(SmsRecord smsRecord) throws Exception {

		int sentNum = baseSmsRecordMapper.getSentNum(smsRecord.getMobile());
		// System.out.println("sentNum-----------------" + sentNum);

		String result = "success";
		String taskid = "";
		String invoking_status = "";

		if (sentNum > 20) {
			invoking_status = "发送失败！该手机号当日短信发送次数已达：" + sentNum;
			result = "发送失败！您当日发送次数已超系统上限。";
		} else {
			// 调用发送短信接口
			taskid = ZucpSmsUtil.sendSms(URLEncoder.encode(smsRecord.getContent(), "UTF-8"), smsRecord.getMobile());
			if (taskid != null && (taskid.startsWith("-") || taskid.equals("")))// 发送短信，如果是以负号开头就是发送失败。
			{
				invoking_status = "发送失败！返回值为：" + taskid;
			}
			// 输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
			else {
				invoking_status = "发送成功，返回值为：" + taskid;
			}
		}

		smsRecord.setVendorType(Constants.SMS_RECORD_VENDOR_TYPE_ZUCP);
		smsRecord.setInvokingStatus(invoking_status);
		smsRecord.setTaskid(taskid);
		// 设置客户端类型,只设置指定类型短信模板的平台来源
		List<Integer> smsTemplateIdContainer = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 12, 13, 15, 16, 17, 18, 19, 20, 400);
		if (smsRecord.getSmsTemplateType() != null && smsTemplateIdContainer.contains(smsRecord.getSmsTemplateType())) {
			ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			if (shiroUser != null) {
				smsRecord.setPlatform(shiroUser.getPlatform());
			}
		}
		baseSmsRecordMapper.insertEntity(smsRecord);
		return result;
	}

	/**
	 * 发送短信
	 * <p>
	 * Description:<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2014年8月26日
	 * @param addip
	 *            发送IP
	 * @param templateId
	 *            短信模板ID
	 * @param map
	 *            短信模板参数
	 * @param mobile
	 *            手机号码
	 * @return
	 * @throws Exception
	 *             String
	 */
	public String saveSms(String addip, int templateId, Map<String, Object> map, String mobile) throws Exception {
		SmsTemplateVo smsTempale = smsTemplateService.querySmsTemplateByType(templateId);

		SmsRecord smsRecord = new SmsRecord();
		smsRecord.setAddip(addip);
		smsRecord.setAddtime(new Date());

		String content = SmsUtil.generateParamContent(smsTempale.getContent(), map);

		smsRecord.setContent(content);
		smsRecord.setMobile(mobile);
		smsRecord.setSmsType(templateId);
		smsRecord.setSendStatus(BusinessConstants.SMS_SEND_STATUS_WAIT);
		smsRecord.setLastmodifytime(new Date());
		smsRecord.setSmsTemplateType(templateId);
		return saveSmsByZucp(smsRecord);
	}

	@Override
	public Page searchSmsRecordVo(SmsTemplateCnd smsTemplateCnd, Integer pageSize, Integer currPage) throws Exception {
		Page page = new Page(currPage, pageSize);
		Integer count = smsRecordMapper.querySmsRecordCount(smsTemplateCnd);
		page.setTotalCount(count);
		List<SmsRecord> list = smsRecordMapper.querySmsRecordList(smsTemplateCnd, page);
		page.setResult(list);
		return page;
	}
}
