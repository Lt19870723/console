package com.cxdai.console.customer.svip.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.svip.entity.VipLevelCnd;
import com.cxdai.console.customer.svip.entity.VipLevelVo;

/**
 * 
 * <p>
 * Description:VIP会员等级类数据访问类<br />
 * </p>
 * 
 * @title VipLevelMapper.java
 * @package com.cxdai.base.mapper
 * @author yangshijin
 * @version 0.1 2015年1月13日
 */
public interface VipLevelMapper {

	/**
	 * 
	 * <p>
	 * Description:根据id查询对象<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月13日
	 * @param id
	 * @return
	 * @throws Exception VipLevelVo
	 */
	public VipLevelVo queryById(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据id查询对象，并锁定<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月13日
	 * @param id
	 * @return
	 * @throws Exception VipLevelVo
	 */
	public VipLevelVo queryByIdForUpdate(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询终身顶级会员列表（分页）<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月13日
	 * @param vipLevelCnd
	 * @param page
	 * @return
	 * @throws Exception List<VipLevelVo>
	 */
	public List<VipLevelVo> querySvipList(VipLevelCnd vipLevelCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询终身顶级会员列表数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月13日
	 * @param vipLevelCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer querySvipCount(VipLevelCnd vipLevelCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据用户id及type查询VIP<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月13日
	 * @param userId
	 * @return
	 * @throws Exception VipLevelVo
	 */
	public VipLevelVo queryByUserIdAndType(@Param("userId") Integer userId, @Param("type") Integer type) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据type查询正常记录中最大的排序号<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月14日
	 * @param type
	 * @return
	 * @throws Exception Integer
	 */
	public Integer getMaxOrderByType(Integer type) throws Exception;
}
