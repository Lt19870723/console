package com.cxdai.console.customer.feedback.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.feedback.vo.FeedbackInfoCnd;
import com.cxdai.console.customer.feedback.vo.FeedbackInfoVo;

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
public interface FeedbackInfoMapper {

	/**
	 * 
	 * <p>
	 * Description:根据条件查询反馈信息记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月20日
	 * @param id
	 * @return
	 * @throws Exception FeedbackInfo
	 */
	public List<FeedbackInfoVo> queryListByFeedbackInfoCnd(FeedbackInfoCnd feedbackInfoCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据条件查询反馈信息记录(分页)<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月20日
	 * @param id
	 * @return
	 * @throws Exception FeedbackInfo
	 */
	public List<FeedbackInfoVo> queryListByFeedbackInfoCnd(FeedbackInfoCnd feedbackInfoCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据条件查询反馈信息记录数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月20日
	 * @param feedbackInfoCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryCountByFeedbackInfoCnd(FeedbackInfoCnd feedbackInfoCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据id查询反馈信息记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月20日
	 * @param feedbackInfoCnd
	 * @return
	 * @throws Exception Integer
	 */
	public FeedbackInfoVo queryById(int id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据id查询反馈信息记录,并锁记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月20日
	 * @param feedbackInfoCnd
	 * @return
	 * @throws Exception Integer
	 */
	public FeedbackInfoVo queryByIdForUpdate(int id) throws Exception;
}
