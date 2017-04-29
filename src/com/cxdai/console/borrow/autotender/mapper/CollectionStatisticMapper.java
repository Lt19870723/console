package com.cxdai.console.borrow.autotender.mapper;

import com.cxdai.console.borrow.approve.vo.CollectionRepayInfoVo;
import com.cxdai.console.borrow.approve.vo.CollectionStatisticCnd;
 

/**
 * 
 * @author hujianpan 待收记录信息统计汇总
 */
public interface CollectionStatisticMapper {

	/**
	 * <p>
	 * Description:根据查询条件有效应收总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年12月22日
	 * @param collectionStatisticCnd
	 * @return
	 * @throws Exception CollectionRepayInfoVo
	 */
	public CollectionRepayInfoVo queryRepayTotalByCnd(CollectionStatisticCnd collectionStatisticCnd) throws Exception;
}
