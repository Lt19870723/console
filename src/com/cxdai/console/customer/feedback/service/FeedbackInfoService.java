package com.cxdai.console.customer.feedback.service;

import java.util.Date;
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
import com.cxdai.console.system.vo.UserVo;

@Service
@Transactional(rollbackFor = Throwable.class)
public class FeedbackInfoService {
	@Autowired
	private FeedbackInfoMapper feedbackInfoMapper;
	@Autowired
	private BaseFeedbackInfoMapper baseFeedbackInfoMapper;

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
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

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public FeedbackInfoVo queryById(int id) throws Exception {
		return feedbackInfoMapper.queryById(id);
	}

	public String saveApprovePass(FeedbackInfoVo feedbackInfoVo, UserVo userVo) throws Exception {
		String reuslt = "success";
		FeedbackInfoVo feedbackInfoVoOld = feedbackInfoMapper.queryByIdForUpdate(feedbackInfoVo.getId());
		if (feedbackInfoVoOld.getStatus() != 0) {
			return "该记录已处理，无法再设置已解决！";
		}
		feedbackInfoVoOld.setStatus(1); // 已解决
		feedbackInfoVoOld.setStaffId(userVo.getId());
		feedbackInfoVoOld.setContactContent(feedbackInfoVo.getContactContent());
		feedbackInfoVoOld.setContactTime(new Date());
		feedbackInfoVoOld.setVersion(feedbackInfoVoOld.getVersion() + 1);
		FeedbackInfo feedbackInfo = new FeedbackInfo();
		BeanUtils.copyProperties(feedbackInfoVoOld, feedbackInfo);
		baseFeedbackInfoMapper.updateEntity(feedbackInfo);
		return reuslt;
	}

	public String saveApproveReject(FeedbackInfoVo feedbackInfoVo, UserVo userVo) throws Exception {
		String reuslt = "success";
		FeedbackInfoVo feedbackInfoVoOld = feedbackInfoMapper.queryByIdForUpdate(feedbackInfoVo.getId());
		if (feedbackInfoVoOld.getStatus() != 0) {
			return "该记录已处理，无法再设置已解决！";
		}
		feedbackInfoVoOld.setStatus(2); // 不予解决
		feedbackInfoVoOld.setStaffId(userVo.getId());
		feedbackInfoVoOld.setContactContent(feedbackInfoVo.getContactContent());
		feedbackInfoVoOld.setContactTime(new Date());
		feedbackInfoVoOld.setVersion(feedbackInfoVoOld.getVersion() + 1);
		FeedbackInfo feedbackInfo = new FeedbackInfo();
		BeanUtils.copyProperties(feedbackInfoVoOld, feedbackInfo);
		baseFeedbackInfoMapper.updateEntity(feedbackInfo);
		return reuslt;
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
