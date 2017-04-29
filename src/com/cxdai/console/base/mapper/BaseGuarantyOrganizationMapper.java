package com.cxdai.console.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.base.entity.GuarantyOrganization;

/**
 * 担保机构
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BaseGuarantyOrganizationMapper.java
 * @package com.cxdai.base.mapper
 * @author huangpin
 * @version 0.1 2014年9月11日
 */
public interface BaseGuarantyOrganizationMapper {

	/**
	 * 添加
	 * <p>
	 * Description:<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年9月4日
	 * @param organization
	 * @return Integer
	 */
	public Integer insertEntity(GuarantyOrganization organization);

	/**
	 * 查询所有
	 * <p>
	 * Description:<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年9月4日
	 * @return List<GuarantyOrganization>
	 */
	public List<GuarantyOrganization> getAll();

	/**
	 * 根据字段查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年9月6日
	 * @param proName
	 * @param proValue
	 * @return List<GuarantyOrganization>
	 */
	public List<GuarantyOrganization> getByProperty(@Param(value = "proName") String proName, @Param(value = "proValue") String proValue);

	/**
	 * 更新
	 * <p>
	 * Description:<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年9月4日
	 * @param organization void
	 */
	public Integer updateEntity(GuarantyOrganization organization);

	/**
	 * 删除ById
	 * <p>
	 * Description:被rocky_borrow使用的不能删除<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年9月4日
	 * @param id void
	 */
	public Integer deleteEntity(Integer id);

}
