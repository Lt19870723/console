package com.cxdai.console.borrow.manage.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.borrow.emerg.mapper.TenderRecordMapper;
import com.cxdai.console.borrow.emerg.vo.TenderRecordCheck;
import com.cxdai.console.borrow.emerg.vo.TenderRecordCnd;
import com.cxdai.console.borrow.emerg.vo.TenderRecordVo;
import com.cxdai.console.borrow.emerg.vo.UserTenderRecordCnd;
import com.cxdai.console.common.page.Page;
/**
 * 
 * <p>
 * Description投标记录业务逻辑实现类<br />
 * </p>
 * @title TenderRecordService.java
 * @package com.cxdai.console.borrow.manage.service 
 * @author yubin
 * @version 0.1 2015年6月30日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class TenderRecordService {
	@Autowired
	private TenderRecordMapper tenderRecordMapper;

	/**
	 * 
	 * <p>
	 * Description:根据borrowId查询投标记录（导出使用<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月30日
	 * @param borrowId
	 * @return
	 * @throws Exception
	 * List<TenderRecordCheck>
	 */
	public List<TenderRecordCheck> queryTenderRecordForExcelByBorrowId(int borrowId) throws Exception {
		return tenderRecordMapper.queryTenderRecordForExcelByBorrowId(borrowId);
	}

	/**
	 * 
	 * <p>
	 * Description:根据borrowId查询投标记录<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月30日
	 * @param borrowId
	 * @return
	 * @throws Exception
	 * List<TenderRecordVo>
	 */
	public List<TenderRecordVo> queryListByBorrowId(Integer borrowId) throws Exception {
		return tenderRecordMapper.queryListByBorrowId(borrowId);
	}

	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月30日
	 * @param userTenderRecordCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * Page
	 */
	public Page searchUserTenderRecord(UserTenderRecordCnd userTenderRecordCnd, Integer curPage, Integer pageSize) {
		Page page = new Page(curPage, pageSize);
		page.setResult(tenderRecordMapper.searchUserTenderRecord(userTenderRecordCnd, page));
		page.setTotalCount(tenderRecordMapper.searchCountUserTenderRecord(userTenderRecordCnd));
		return page;
	}

	/**
	 * 
	 * <p>
	 * Description:根据投标条件查询投标记录<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月30日
	 * @param tenderRecordCnd
	 * @return
	 * @throws Exception
	 * List<TenderRecordVo>
	 */
	public List<TenderRecordVo> queryListByTenderRecordCnd(TenderRecordCnd tenderRecordCnd) throws Exception {
		return tenderRecordMapper.queryListByTenderRecordCnd(tenderRecordCnd);
	}

	
	public BigDecimal queryTotalRepaymentAccount(TenderRecordCnd tenderRecordCnd) {
		return tenderRecordMapper.queryTotalRepaymentAccount(tenderRecordCnd);
	}
}
