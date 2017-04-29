package com.cxdai.console.firstborrow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.firstborrow.vo.FirstTenderDetailCnd;
import com.cxdai.console.firstborrow.vo.FirstTenderDetailVo;
import com.cxdai.console.statistics.account.vo.AccountVo;

/**
 * <p>
 * Description:优先投标计划开通明细数据访问类<br />
 * </p>
 * 
 * @title FirstTenderDetailMapper.java
 * @package com.cxdai.console.first.mapper
 * @author justin.xu
 * @version 0.1 2014年7月22日
 */
public interface FirstTenderDetailMapper {

	/**
	 * <p>
	 * Description:根据直通车id更新指定状态<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月18日
	 * @param firstBorrowId
	 * @param status
	 * @return
	 * @throws Exception Integer
	 */
	public Integer updateStatusByFirstBorrowId(@Param("firstBorrowId") Integer firstBorrowId, @Param("status") Integer status) throws Exception;

	/**
	 * <p>
	 * Description:根据条件询集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月15日
	 * @param firstTenderDetailCnd
	 * @param page
	 * @return
	 * @throws Exception List<FirstTenderDetailVo>
	 */
	public List<FirstTenderDetailVo> queryFirstTenderDetailList(FirstTenderDetailCnd firstTenderDetailCnd) throws Exception;

	/**
	 * <p>
	 * Description:根据直通车id找到投标明细中的用户帐号<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月17日
	 * @param firstBorrowId
	 * @return List<AccountVo>
	 */
	public List<AccountVo> queryAccountListForUpdateByFirstId(Integer firstBorrowId);

	public Integer updateRealIdByFirstBorrowId(Integer firstBorrowId);
}
