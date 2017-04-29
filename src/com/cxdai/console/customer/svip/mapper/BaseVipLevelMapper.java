package com.cxdai.console.customer.svip.mapper;

import com.cxdai.console.customer.svip.entity.VipLevel;

/**
 * 
 * <p>
 * Description:终身顶级会员类数据访问类<br />
 * </p>
 * 
 * @title BaseVipLevelMapper.java
 * @package com.cxdai.base.mapper
 * @author yangshijin
 * @version 0.1 2015年1月13日
 */
public interface BaseVipLevelMapper {

	/**
	 * 
	 * <p>
	 * Description:新增对象<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月13日
	 * @param vipLevel
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(VipLevel vipLevel) throws Exception;

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
	 * @throws Exception VipLevel
	 */
	public VipLevel queryById(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月13日
	 * @param vipLevel
	 * @return
	 * @throws Exception int
	 */
	public int updateEntity(VipLevel vipLevel) throws Exception;

}
