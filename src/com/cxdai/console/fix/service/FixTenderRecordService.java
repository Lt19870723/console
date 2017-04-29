package com.cxdai.console.fix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.mapper.FixTenderRecordMapper;
import com.cxdai.console.fix.vo.TenderRecordCnd;
import com.cxdai.console.fix.vo.TenderRecordVo;

/**
 * <p>
 * Description:定期宝投标日志接口类<br />
 * </p>
 * 
 * @title TenderRecordService.java
 * @package com.cxdai.console.fix.service
 * @author caodefeng
 * @version 0.1 2015年5月23日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class FixTenderRecordService {
	
	@Autowired
	private FixTenderRecordMapper	fixTenderRecordMapper;
	/**
	 * 分页查询定期宝投标日志
	 * @param tenderRecordCnd
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	
 
	public Page queryTenderRecordVoList(TenderRecordCnd tenderRecordCnd,
			Integer pageNo, Integer pageSize) throws Exception {
		Page page = new Page(pageNo,pageSize);
		Integer totalCounts = fixTenderRecordMapper.queryTenderRecordVoCounts(tenderRecordCnd);
		page.setTotalCount(totalCounts);
		List<TenderRecordVo> list = fixTenderRecordMapper.queryTenderRecordVoList(tenderRecordCnd, page);
		page.setResult(list);
		return page;
	}
}
