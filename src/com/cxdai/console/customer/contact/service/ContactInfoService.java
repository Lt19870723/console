package com.cxdai.console.customer.contact.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.feedback.entity.FeedbackInfo;
import com.cxdai.console.customer.feedback.mapper.BaseFeedbackInfoMapper;
import com.cxdai.console.customer.feedback.mapper.FeedbackInfoMapper;
import com.cxdai.console.customer.feedback.vo.FeedbackInfoCnd;
import com.cxdai.console.customer.feedback.vo.FeedbackInfoVo;
import com.cxdai.console.system.entity.User;

/**
 * <p>
 * Description:我要联系<br />
 * </p>
 * 
 * @title ContactInfoService.java
 * @package com.cxdai.console.customer.contact.service
 * @author hujianpan
 * @version 0.1 2015年3月16日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class ContactInfoService {

	@Autowired
	private FeedbackInfoMapper feedbackInfoMapper;
	@Autowired
	private BaseFeedbackInfoMapper baseFeedbackInfoMapper;

	@Transactional(rollbackFor = Throwable.class, readOnly = true)
	public Page queryListForPage(FeedbackInfoCnd feedbackInfoCnd, int pageSize, int pageNo) throws Exception {
		if (feedbackInfoCnd.getStatus() != null && feedbackInfoCnd.getStatus() == -1) {
			feedbackInfoCnd.setStatus(null);
		}
		Page page = new Page(pageNo, pageSize);
		int totalCount = feedbackInfoMapper.queryCountByFeedbackInfoCnd(feedbackInfoCnd);
		page.setTotalCount(totalCount);
		List<FeedbackInfoVo> list = feedbackInfoMapper.queryListByFeedbackInfoCnd(feedbackInfoCnd, page);
		page.setResult(list);
		return page;
	}

	public String updateAcceptFeedback(FeedbackInfoVo feedbackInfoVo, User user) throws Exception {
		String reuslt = "success";
		FeedbackInfoVo feedbackInfoVoOld = feedbackInfoMapper.queryByIdForUpdate(feedbackInfoVo.getId());
		if (feedbackInfoVoOld.getStatus() != 0) {
			return "该记录已处理，无需再联系！";
		}
		if (feedbackInfoVo.getStaffId() != null) {
			return "该记录已被其他人联系！";
		}
		feedbackInfoVoOld.setStaffId(user.getId());
		feedbackInfoVoOld.setVersion(feedbackInfoVoOld.getVersion() + 1);
		FeedbackInfo feedbackInfo = new FeedbackInfo();
		BeanUtils.copyProperties(feedbackInfoVoOld, feedbackInfo);
		baseFeedbackInfoMapper.updateEntity(feedbackInfo);
		return reuslt;
	}

}
