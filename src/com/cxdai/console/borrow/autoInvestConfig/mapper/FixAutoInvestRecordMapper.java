package com.cxdai.console.borrow.autoInvestConfig.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Update;

import com.cxdai.console.borrow.autoInvestConfig.entity.FixAutoInvest;
import com.cxdai.console.borrow.autoInvestConfig.entity.FixAutoInvestRecord;
import com.cxdai.console.borrow.autoInvestConfig.vo.FixAutoInvestCnd;
import com.cxdai.console.common.page.Page;
 

public interface FixAutoInvestRecordMapper {

	/**
	 * <p>
	 * Description:根据条件查询集合<br />
	 * </p>
	 * @author liutao
	 * @date 2015年11月20日
	 * @param fixAutoInvestCnd
	 * @return List<FixAutoInvestEntity>
	 */
	public List<FixAutoInvestRecord> queryfixAutoInvestRecordList(FixAutoInvestCnd fixAutoInvestCnd,Page page) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询对象集合数量<br />
	 * </p>
	 * 
	 * @author liutao
	 * @date 2015年11月20日
	 * @param fixAutoInvestCnd
	 * @return Integer
	 * @throws Exception Integer
	 */
	public Integer queryfixAutoInvestRecordCount(FixAutoInvestCnd fixAutoInvestCnd) throws Exception;
	/**
	 * <p>
	 * Description:根据条件查询对象集合数量<br />
	 * </p>
	 * 
	 * @author liutao
	 * @date 2015年11月20日
	 * @param fixAutoInvestCnd
	 * @return Integer
	 * @throws Exception Integer
	 */
	public FixAutoInvestRecord queryById(int id) throws Exception;
	/**
	 * <p>
	 * Description:根据定期宝ID查询排序时间<br />
	 * </p>
	 * @author liutao
	 * @date 2015年11月20日
	 * @param fixAutoInvestCnd
	 * @return Integer
	 * @throws Exception Integer
	 */
	public  List<FixAutoInvestRecord> queryByfixId(@Param("fixId") int fixId) throws Exception;
	/**
	 * <p>
	 * Description:根据自动投宝ID更新排序时间<br />
	 * </p>
	 * @author liutao
	 * @date 2015年11月20日
	 * @param fixAutoInvestCnd
	 * @return Integer
	 * @throws Exception Integer
	 */
	@Update("update t_fix_auto_invest set uptime=#{preUptime} where id=#{autoInvestId}")
	@ResultType(Integer.class)
	int updateUptime(@Param("autoInvestId") int autoInvestId, @Param("preUptime") String preUptime) throws Exception;
	public int insertInvestRecord(FixAutoInvestRecord fixAutoInvestRecordEntity);

	
}
