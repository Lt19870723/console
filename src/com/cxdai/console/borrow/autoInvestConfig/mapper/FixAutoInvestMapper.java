package com.cxdai.console.borrow.autoInvestConfig.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Update;

import com.cxdai.console.borrow.autoInvestConfig.entity.FixAutoInvest;
import com.cxdai.console.borrow.autoInvestConfig.entity.FixAutoInvestRecord;
import com.cxdai.console.borrow.autoInvestConfig.vo.FixAutoInvestCnd;
import com.cxdai.console.common.page.Page;
 

public interface FixAutoInvestMapper {

	/**
	 * <p>
	 * Description:根据条件查询集合<br />
	 * </p>
	 * @author liutao
	 * @date 2015年11月20日
	 * @param fixAutoInvestCnd
	 * @return List<FixAutoInvestEntity>
	 */
	public List<FixAutoInvest> queryfixAutoInvestList(FixAutoInvestCnd fixAutoInvestCnd,Page page) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询对象集合数量<br />
	 * </p>
	 * @author liutao
	 * @date 2015年11月20日
	 * @param fixAutoInvestCnd
	 * @return Integer
	 * @throws Exception Integer
	 */
	public Integer queryfixAutoInvestCount(FixAutoInvestCnd fixAutoInvestCnd) throws Exception;
	/**
	 * <p>
	 * Description:根据ID查询定期宝设置记录<br />
	 * </p>
	 * @author liutao
	 * @date 2015年11月20日
	 * @param fixAutoInvestCnd
	 * @return Integer
	 * @throws Exception Integer
	 */
	public  FixAutoInvest queryInvestById(@Param("id") int id) throws Exception;

	
}
