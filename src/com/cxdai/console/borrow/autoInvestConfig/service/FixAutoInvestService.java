package com.cxdai.console.borrow.autoInvestConfig.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.borrow.autoInvestConfig.entity.FixAutoInvest;
import com.cxdai.console.borrow.autoInvestConfig.entity.FixAutoInvestRecord;
import com.cxdai.console.borrow.autoInvestConfig.mapper.FixAutoInvestMapper;
import com.cxdai.console.borrow.autoInvestConfig.mapper.FixAutoInvestRecordMapper;
import com.cxdai.console.borrow.autoInvestConfig.vo.FixAutoInvestCnd;
import com.cxdai.console.common.page.Page;
/**
 * 
 * <p>
 * Description:自动投宝业务处理方法<br />
 * </p>
 * @title AutoInvestService.java
 * @package com.cxdai.console.borrow.autotender.service 
 * @author Administrator
 * @version 0.1 2015年6月25日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class FixAutoInvestService {
	public Logger logger = Logger.getLogger(FixAutoInvestService.class);

	@Autowired
	private FixAutoInvestMapper fixAutoInvestMapper;
	@Autowired
	private FixAutoInvestRecordMapper fixAutoInvestRecordMapper;
	/**
	 * 
	 * <p>
	 * Description:自动投宝规则列表分页<br />
	 * </p>
	 * 
	 * @author liutao
	 * @date 2015年11月20日
	 * @param fixAutoInvestCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception Page
	 */
	public Page queryListForPage(FixAutoInvestCnd fixAutoInvestCnd, int curPage, int pageSize) throws Exception{
		Page page = new Page(curPage, pageSize);
		Integer totalCount = fixAutoInvestMapper.queryfixAutoInvestCount(fixAutoInvestCnd);
		page.setTotalCount(totalCount);
		List<FixAutoInvest> list = fixAutoInvestMapper.queryfixAutoInvestList(fixAutoInvestCnd,page);
		page.setResult(list);
		return page;
	}
	/**
	 * 
	 * <p>
	 * Description:自动投宝记录列表分页<br />
	 * </p>
	 * 
	 * @author liutao
	 * @date 2015年11月20日
	 * @param fixAutoInvestCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception Page
	 */
	public Page queryRecordListForPage(FixAutoInvestCnd fixAutoInvestCnd, int curPage, int pageSize) throws Exception{
		Page page = new Page(curPage, pageSize);
		Integer totalCount = fixAutoInvestRecordMapper.queryfixAutoInvestRecordCount(fixAutoInvestCnd);
		page.setTotalCount(totalCount);
		List<FixAutoInvestRecord> list = fixAutoInvestRecordMapper.queryfixAutoInvestRecordList(fixAutoInvestCnd,page);
		page.setResult(list);
		return page;
	}
	/**
	 * 
	 * <p>
	 * Description:根据id查询自动投标规则记录 <br />
	 * </p>
	 * 
     * @author liutao
	 * @date 2015年11月20日
	 * @param id
	 * @return
	 */
	public FixAutoInvestRecord queryById(int id) throws Exception{
		return fixAutoInvestRecordMapper.queryById(id);
	}

}
