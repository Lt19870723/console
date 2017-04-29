package com.cxdai.console.fix.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.cxdai.console.fix.entity.FixTenderSubscribe;
import com.cxdai.console.fix.vo.FixTenderSubscribeVo;
import com.cxdai.console.fix.vo.FixTenderTransferCnd;

public interface FixTenderSubscribeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FixTenderSubscribe record);

    int insertSelective(FixTenderSubscribe record);

    FixTenderSubscribe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FixTenderSubscribe record);

    int updateByPrimaryKey(FixTenderSubscribe record);
    
    /**
	 * 
	 * <p>
	 * Description:根据定期宝转让ID查询定期宝转让认购总和<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年7月14日
	 * @param fixBorrowTransferId
	 * @return BigDecimal
	 */
	public BigDecimal queryTenderSubscribeSum(Integer fixBorrowTransferId);
	
	/**
	 * 
	 * <p>
	 * Description:根据条件查询定期宝转让认购信息<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年7月14日
	 * @param fixTenderTransferCnd
	 * @return List<FixTenderSubscribeVo>
	 */
	public List<FixTenderSubscribeVo> queryTenderSubscribeListByTransferId(FixTenderTransferCnd fixTenderTransferCnd);
	
	/**
	 * 
	 * <p>
	 * Description:修改定期宝转让认购信息状态<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年6月30日
	 * @param fixBorrowTransferId
	 *            void
	 */
	public void updateTenderSubscribeStatus(Integer fixBorrowTransferId);
	
	/**
	 * 
	 * <p>
	 * Description:分组查询定期宝认购信息<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年7月2日
	 * @param fixBorrowTransferId
	 * @return List<FixTenderSubscribeVo>
	 */
	public List<FixTenderSubscribeVo> queryTenderSubscribeListGroupByTransferId(Integer fixBorrowTransferId);
	
	/**
	 * 
	 * <p>
	 * Description:定期宝中间表存储过程<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年7月2日
	 * @param params
	 *            void
	 */
	void saveFixTenderCore(Map<String, Object> params);
	
	/**
	 * 
	 * <p>
	 * Description:调用定期宝投资人投标待收记录存储过程<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年7月2日
	 * @param params
	 *            void
	 */
	void saveFixUserTendercollectionCore(Map<String, Object> params);
	
	/**
	 * 
	 * <p>
	 * Description:根据定期宝ID修改定期宝投标中间表的状态<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年7月2日
	 * @param fixBorrowId
	 *            void
	 */
	void updateFixTenderUserStatus(Integer fixBorrowId);
	
	Integer querySubscribeCountByTransferId(Integer fixBorrowTransferId);
	
	/**
	 * 根据ID修改认购状态为认购失败
	 * @author WangQianJin
	 * @title @param id
	 * @date 2015年9月15日
	 */
	public void updateStatusForSubFailureById(FixTenderSubscribeVo fixTenderSubscribeVo);
}