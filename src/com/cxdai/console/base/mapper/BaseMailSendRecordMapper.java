package com.cxdai.console.base.mapper;

import com.cxdai.console.base.entity.MailSendRecord;

/**
 * 
 * <p>
 * Description:邮件发送记录数据访问类<br />
 * </p>
 * 
 * @title BaseMailSendRecordMapper.java
 * @package com.cxdai.base.mapper
 * @author yangshijin
 * @version 0.1 2014年9月15日
 */
public interface BaseMailSendRecordMapper {
	/**
	 * 
	 * <p>
	 * Description:插入记录到关联人表,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月15日
	 * @param mailSendRecord
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(MailSendRecord mailSendRecord) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据id查询记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月15日
	 * @param id
	 * @return
	 * @throws Exception MailSendRecord
	 */
	public MailSendRecord queryById(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:更新记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月15日
	 * @param mailSendRecord
	 * @return
	 * @throws Exception int
	 */
	public int updateEntity(MailSendRecord mailSendRecord) throws Exception;

}
