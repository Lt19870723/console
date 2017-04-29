package com.cxdai.console.account.cash.mapper;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Description:借款统计数据访问类<br />
 * </p>
 * 
 * @title BorrowReportMapper.java
 * @package com.cxdai.console.account.mapper
 * @author justin.xu
 * @version 0.1 2014年6月26日
 */
public interface BorrowReportMapper {
	/**
	 * <p>
	 * Description:查询某个用户ID下某种标还款中的待还金额总计<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年6月26日
	 * @param memberId
	 * @param borrowType
	 * @return
	 * @throws Exception
	 *             BigDecimal
	 */
	public BigDecimal queryRepaymentAccountTotalByMemberIdAndBorrowType(
			@Param("memberId") Integer memberId,
			@Param("borrowType") Integer borrowType) throws Exception;
}
