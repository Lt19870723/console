package com.cxdai.console.sms.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.sms.vo.SmsRecord;
import com.cxdai.console.sms.vo.SmsTemplateCnd;


/**
 * <p>
 * Description:短信发送数据访问类<br />
 * </p>
 * 
 * @title SmsTemplateMapper.java
 * @package com.cxdai.portal.sms.mapper
 * @author justin.xu
 * @version 0.1 2014年4月29日
 */
public interface SmsRecordMapper {
	/**
	 * <p>
	 * Description:根据条件查询对象集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月29日
	 * @param smsTemplateCnd
	 * @return
	 * @throws Exception
	 *             List<SmsTemplateVo>
	 */
	public List<SmsRecord> querySmsRecordList(
			SmsTemplateCnd smsTemplateCnd,Page page) throws Exception;
	
	public Integer querySmsRecordCount(
			SmsTemplateCnd smsTemplateCnd) throws Exception;
}
