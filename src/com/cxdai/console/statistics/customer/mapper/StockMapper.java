package com.cxdai.console.statistics.customer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.base.entity.Stock;
import com.cxdai.console.base.mapper.BaseStockMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.customer.vo.StockCnd;

public interface StockMapper extends BaseStockMapper {

	/**
	 * 分页查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2017年9月23日
	 * @param stockCnd
	 * @param page
	 * @return List<Stock>
	 */
	public List<Stock> pageQuery(StockCnd stockCnd, Page page);

	/**
	 * 分页查询记录数
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2017年9月23日
	 * @param stockCnd
	 * @return int
	 */
	public int pageQueryCount(StockCnd stockCnd);

	/**
	 * 修改ById-管理员现金行权
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2017年9月23日
	 * @param stock
	 * @return int
	 */
	public int updateStockMoney(Stock stock);

	public Stock getByIdForUpdate(@Param(value = "id") Integer id);

}
