package com.cxdai.console.base.mapper;

import com.cxdai.console.sms.vo.SmsRecord;


/**
 * <p>
 * Description:发送短信数据访问类<br />
 * </p>
 * @title BaseSmsRecordMapper.java
 * @package com.cxdai.base.mapper
 * @author justin.xu
 * @version 0.1 2014年4月30日
 */
public interface BaseSmsRecordMapper {
	/**
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param smsRecord
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(SmsRecord smsRecord) throws Exception;

	/**
	 * 查询一个手机号一天发送的短信次数
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2015年8月11日
	 * @param mobile
	 * @return int
	 */
	public int getSentNum(String mobile);

}
