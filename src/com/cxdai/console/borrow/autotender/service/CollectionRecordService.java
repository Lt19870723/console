package com.cxdai.console.borrow.autotender.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.borrow.approve.vo.CollectionRepayInfoVo;
import com.cxdai.console.borrow.approve.vo.CollectionStatisticCnd;
import com.cxdai.console.borrow.autotender.mapper.CollectionStatisticMapper;
/**
 * 
 * <p>
 * Description: 待收记录查询服务<br />
 * </p>
 * @title CollectionRecordService.java
 * @package com.cxdai.console.borrow.autotender.service 
 * @author Administrator
 * @version 0.1 2015年6月25日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CollectionRecordService {
	public Logger logger = Logger.getLogger(CollectionRecordService.class);
	@Autowired
	private CollectionStatisticMapper collectionStatisticMapper;

	 
	public CollectionRepayInfoVo queryRepayTotalByCnd(CollectionStatisticCnd collectionStatisticCnd) throws Exception {
		return collectionStatisticMapper.queryRepayTotalByCnd(collectionStatisticCnd);
	}
}
