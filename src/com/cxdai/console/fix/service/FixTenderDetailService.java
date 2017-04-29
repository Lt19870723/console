package com.cxdai.console.fix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.mapper.FixTenderDetailMapper;
import com.cxdai.console.fix.vo.FixTenderDetailCnd;
import com.cxdai.console.fix.vo.FixTenderDetailVo;
import com.cxdai.console.util.DateUtils;

/**
 * <p>
 * Description:定期宝认购明接口类<br />
 * </p>
 * 
 * @title FixTenderDetailService.java
 * @package com.cxdai.portal.fix1.service
 * @author caodefeng
 * @version 0.1 2015年5月14日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class FixTenderDetailService {
	
	@Autowired
	private FixTenderDetailMapper fixTenderDetailMapper;

	/**
	 * 分页查询定期宝用户加入列表
	 * @param fixBorrowId
	 * @param page
	 * @return
	 */
	public Page queryTotalForJoinFixBorrow(
			FixTenderDetailCnd fixTenderDetailCnd, int pageNo,int pageSize) throws Exception {
		Page page = new Page(pageNo, pageSize);
		if(fixTenderDetailCnd.getBeginTimeStr()!=""){
			fixTenderDetailCnd.setBeginTime(DateUtils.timeStampToDate(DateUtils.date2TimeStamp(fixTenderDetailCnd.getBeginTimeStr())));
		}
		if(fixTenderDetailCnd.getEndTimeStr()!=""){
			fixTenderDetailCnd.setEndTime(DateUtils.timeStampToDate(DateUtils.date2TimeStamp(fixTenderDetailCnd.getEndTimeStr())));
		}
		Integer totalCount = fixTenderDetailMapper.queryTotalForJoinFixBorrowCounts(fixTenderDetailCnd);
		page.setTotalCount(totalCount);
		List<FixTenderDetailVo> list = fixTenderDetailMapper.queryTotalForJoinFixBorrow(fixTenderDetailCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 根据定期宝编号查询某个定期宝加入情况
	 * @param contractNo
	 * @return
	 */
	public FixTenderDetailVo queryTotalForJoinFixBorrowSingle(String contractNo) throws Exception {
		return fixTenderDetailMapper.queryTotalForJoinFixBorrowSingle(contractNo);
	}
}
