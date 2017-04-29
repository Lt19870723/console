package com.cxdai.console.customer.feedback.mapper;

import com.cxdai.console.customer.feedback.entity.FeedbackInfo;

/**
 * 
 * <p>
 * Description:反馈信息数据访问类<br />
 * </p>
 * 
 * @title BaseFeedbackInfoMapper.java
 * @package com.cxdai.base.mapper
 * @author yangshijin
 * @version 0.1 2014年9月20日
 */
public interface BaseFeedbackInfoMapper {
	/**
	 * 
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月20日
	 * @param feedbackInfo
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(FeedbackInfo feedbackInfo) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据id查询<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月20日
	 * @param id
	 * @return
	 * @throws Exception FeedbackInfo
	 */
	public FeedbackInfo queryById(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月20日
	 * @param feedbackInfo
	 * @return
	 * @throws Exception int
	 */
	public int updateEntity(FeedbackInfo feedbackInfo) throws Exception;

}
