package com.cxdai.console.firstborrow.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.firstborrow.vo.FirstTenderRealCnd;
import com.cxdai.console.firstborrow.vo.FirstTenderRealVo;

/**
 * <p>
 * Description:优先投标计划最终认购记录数据访问类<br />
 * </p>
 * 
 * @title FirstTenderRealMapper.java
 * @package com.cxdai.console.first.mapper
 * @author justin.xu
 * @version 0.1 2014年10月28日
 */
public interface FirstTenderRealMapper {
	/**
	 * <p>
	 * Description:根据直通车id,用户id更新待收表中的优先投标计划为失效<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月21日
	 * @param firstBorrowId
	 * @param memberId
	 * @return
	 * @throws Exception Integer
	 */
	public Integer updateCollectionFirstToInvalid(@Param("firstTenderRealId") Integer firstTenderRealId, @Param("memberId") Integer memberId) throws Exception;

	/**
	 * <p>
	 * Description:根据条件分页查询集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月2日
	 * @param firstTenderRealCnd
	 * @param page
	 * @return
	 * @throws Exception List<FirstTenderRealVo>
	 */
	public List<FirstTenderRealVo> queryFirstTenderRealList(FirstTenderRealCnd firstTenderRealCnd, Page page) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月2日
	 * @param firstTenderRealCnd
	 * @return
	 * @throws Exception List<FirstTenderRealVo>
	 */
	public List<FirstTenderRealVo> queryFirstTenderRealList(FirstTenderRealCnd firstTenderRealCnd) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询对象集合数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月2日
	 * @param firstTenderRealCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryFirstTenderRealCount(FirstTenderRealCnd firstTenderRealCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询符合条件的最终记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年12月23日
	 * @param firstTenderRealCnd
	 * @return
	 * @throws Exception List<FirstTenderRealVo>
	 */
	public List<FirstTenderRealVo> findFirstTenderRealVoToExcel(FirstTenderRealCnd firstTenderRealCnd) throws Exception;

	/**
	 * <p>
	 * Description:直通车开通金额的总额计算<br />
	 * </p>
	 * 
	 * @author chenpeng
	 * @version 0.1 2014年12月25日
	 * @param firstTenderRealCnd
	 * @return BigDecimal
	 */
	public BigDecimal queryFirstTenderRealAccount(FirstTenderRealCnd firstTenderRealCnd);
	
	/**
	 * 
	 * <p>
	 * Description:查询直通车总余额<br />
	 * </p>
	 * @author YangShiJin
	 * @version 0.1 2015年4月22日
	 * @return
	 * BigDecimal
	 */
	public BigDecimal queryFirstUseMoneyTotal();
}
