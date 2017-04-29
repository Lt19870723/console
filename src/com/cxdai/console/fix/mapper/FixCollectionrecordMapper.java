package com.cxdai.console.fix.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.cxdai.console.fix.entity.FixCollectionrecord;
import com.cxdai.console.fix.vo.FixCollectionrecordVo;

public interface FixCollectionrecordMapper {

	/**
	 * <p>
	 * Description:根据定期宝ID获得该用户和该用户投宝的待收金额,并锁定该表<br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年6月25日
	 * @param fixCollectionrecord
	 * @return
	 * @throws Exception
	 *             List<FixCollectionrecord>
	 */
	public List<FixCollectionrecordVo> queryCollectionByConn(FixCollectionrecord fixCollectionrecord) throws Exception;

	/**
	 * <p>
	 * Description:待收金额,本金，利息的和<br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年6月26日
	 * @param fixCollectionrecord
	 * @return
	 * @throws Exception
	 *             FixCollectionrecordVo
	 */
	public FixCollectionrecordVo querySumCollectionAccountByFixBorrowId(FixCollectionrecord fixCollectionrecord) throws Exception;

	/**
	 * <p>
	 * Description:根据【定期宝Id】【userId】更新定期宝投资人待收记录<br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年7月14日
	 * @param fixCollectionrecord
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int updateFixCollectionRecordByFixId(FixCollectionrecord fixCollectionrecord) throws Exception;

	@Select("SELECT IFNULL(SUM(REPAY_ACCOUNT),0) from t_fix_collectionrecord where `STATUS`=0 and user_id=#{userId}")
	@ResultType(BigDecimal.class)
	public BigDecimal getTotalByUserId(int userId);

}