package com.cxdai.console.sms.service;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.sms.vo.SmsTemplateCnd;
import com.cxdai.console.sms.vo.SmsTemplateVo;


/**
 * <p>
 * Description:短信模板业务类<br />
 * </p>
 * 
 * @title SmsTemplateService.java
 * @package com.cxdai.portal.sms.service
 * @author justin.xu
 * @version 0.1 2014年4月29日
 */
public interface SmsTemplateService {
	/**
	 * <p>
	 * Description:根据类型查询有效模板<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月29日
	 * @param type
	 * @return
	 * @throws Exception
	 *             SmsTemplate
	 */
	public SmsTemplateVo querySmsTemplateByType(Integer type) throws Exception;

	public Page querySmsTemplateList(SmsTemplateCnd smsTemplateCnd,Integer page, Integer currPage) throws Exception;
}
