package com.cxdai.console.account.reward.mapper;

import java.util.List;

import com.cxdai.console.account.reward.entity.MemberAccumulatePoints;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.sycee.entity.MemberAccumulatePointsCnd;

/**
 * <p>
 * Description:用户积分记录数据访问类<br />
 * </p>
 * @title MemberAccumulatePointsMapper.java
 * @package com.cxdai.base.mapper
 * @author justin.xu
 * @version 0.1 2014年4月17日
 */
public interface BaseMemberAccumulatePointsMapper {
	/**
	 * <p>
	 * Description:插入记录返回新增的主键ID<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param member
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(MemberAccumulatePoints memberAccumulatePoints) throws Exception;

	/**
	 * <p>
	 * Description:根据id查询<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param id
	 * @return
	 * @throws Exception MemberAccumulatePoints
	 */
	public MemberAccumulatePoints queryById(Integer id) throws Exception;

	/**
	 * <p>
	 * Description:更新会员<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param memberAccumulatePoints
	 * @return
	 * @throws Exception int
	 */
	public int updateEntity(MemberAccumulatePoints memberAccumulatePoints) throws Exception;

	public List<MemberAccumulatePoints> pageQuery(MemberAccumulatePointsCnd cnd, Page page);

	public int pageQueryCount(MemberAccumulatePointsCnd cnd);
}
