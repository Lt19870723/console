package com.cxdai.console.account.reward.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.reward.entity.MemberAccumulatePoints;
import com.cxdai.console.account.reward.mapper.BaseMemberAccumulatePointsMapper;
import com.cxdai.console.base.entity.Member;
import com.cxdai.console.base.mapper.BaseMemberMapper;

/**
 * <p>
 * Description:积分奖励业务类<br />
 * </p>
 * @title PointsAwardService.java
 * @package com.cxdai.console.account.service
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class PointsAwardService {

	@Autowired
	private BaseMemberAccumulatePointsMapper baseMemberAccumulatePointsMapper;
	@Autowired
	private BaseMemberMapper baseMemberMapper;

	/**
	 * <p>
	 * Description:积分奖励<br />
	 * </p>
	 * @param userId
	 * @param points
	 * @param remark
	 * @return
	 * @throws Exception String
	 */
	public String pointsAward(Integer userId, int points, String remark,String detail) throws Exception {
		Member member = baseMemberMapper.queryById(userId);
		if (member != null) {
			int newPoint = member.getAccumulatepoints() + points;
			if (newPoint < 0)
				newPoint = 0;
			int newHonor = member.getHonor() + points;
			if (newHonor < 0)
				newHonor = 0;
			member.setGainaccumulatepoints(0);// 元宝系统不记录原可用积分字段
			member.setAccumulatepoints(newPoint);
			member.setHonor(newHonor);
			if (baseMemberMapper.updateEntity(member) > 0) { // 奖励积分
				// 保存奖励积分日志记录
				MemberAccumulatePoints memberAccumulatePoints = new MemberAccumulatePoints();
				memberAccumulatePoints.setMemberId(member.getId());
				memberAccumulatePoints.setAccumulatePoints(points);
				memberAccumulatePoints.setGainAccumulatePoints(member.getGainaccumulatepoints());
				memberAccumulatePoints.setGainDate(new Date());
				memberAccumulatePoints.setType(11); // 网站奖励
				memberAccumulatePoints.setPointSmagnification(1);
				memberAccumulatePoints.setDetail(detail);
				memberAccumulatePoints.setRemark(remark);
				memberAccumulatePoints.setHonor(member.getHonor());
				memberAccumulatePoints.setSycee(member.getAccumulatepoints());
				baseMemberAccumulatePointsMapper.insertEntity(memberAccumulatePoints);
				return "元宝奖励成功";
			} else {
				return "元宝奖励失败";
			}
		} else {
			return "该用户不存在";
		}
	}
}
