package com.cxdai.console.base.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.base.entity.Stock;

/**
 * 股权排名
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title BaseStockMapper.java
 * @package com.cxdai.base.mapper
 * @author huangpin
 * @version 0.1 2014年9月11日
 */
public interface BaseStockMapper {

	/**
	 * 查询所有
	 * <p>
	 * Description:<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年9月4日
	 * @return List<GuarantyOrganization>
	 */
	public List<Stock> getAll();

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
	public List<Stock> getByProperty(@Param(value = "proName") String proName, @Param(value = "proValue") String proValue);

	/**
	 * 根据ID查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年9月12日
	 * @param id
	 * @return Stock
	 */
	public Stock getById(@Param(value = "id") Integer id);

	/**
	 * 更新账户总额，可提，可用
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年9月11日
	 * @param money
	 * @param userId
	 * @return int
	 */
	public int updateAccount(@Param(value = "money") BigDecimal money, @Param(value = "userId") Integer userId);

}
