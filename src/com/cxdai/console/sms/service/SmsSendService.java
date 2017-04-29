package com.cxdai.console.sms.service;

import java.util.Map;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.sms.vo.SendMobileCnd;
import com.cxdai.console.sms.vo.SmsRecord;
import com.cxdai.console.sms.vo.SmsTemplateCnd;


/**
 * <p>
 * Description:短信发送Service<br />
 * </p>
 * 
 * @title SmsSendService.java
 * @package com.cxdai.portal.sms.service
 * @author justin.xu
 * @version 0.1 2014年4月30日
 */
public interface SmsSendService {
	/**
	 * <p>
	 * Description:根据模板发送短信<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年11月17日
	 * @param sendMobileCnd
	 * @param smsTemplateMap 短信模板key和内容map
	 * @throws Exception void
	 */
	public String saveTemplateMessage(SendMobileCnd sendMobileCnd) throws Exception;

	/**
	 * <p>
	 * Description:调用漫道接口进行短信发送<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月30日
	 * @param smsRecord
	 * @return
	 * @throws Exception String
	 */
	public String saveSmsByZucp(SmsRecord smsRecord) throws Exception;

	/**
	 * 发送短信
	 * <p>
	 * Description:<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2014年8月26日
	 * @param addip 发送IP
	 * @param templateId 短信模板ID
	 * @param map 短信模板参数
	 * @param mobile 手机号码
	 * @return
	 * @throws Exception String
	 */
	public String saveSms(String addip, int templateId, Map<String, Object> map, String mobile) throws Exception;

	public Page searchSmsRecordVo(SmsTemplateCnd smsTemplateCnd, Integer pageSize,
			Integer currPage) throws Exception;
}
