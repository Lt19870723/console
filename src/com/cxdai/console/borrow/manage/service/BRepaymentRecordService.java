package com.cxdai.console.borrow.manage.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxdai.console.borrow.manage.vo.*;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.borrow.manage.mapper.BRepaymentRecordLogMapper;
import com.cxdai.console.borrow.manage.mapper.BRepaymentRecordMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.util.DateUtils;
/**
 * 
 * <p>
 * Description:待还记录业务实现类<br />
 * </p>
 * @title BRepaymentRecordService.java
 * @package com.cxdai.console.borrow.manage.service 
 * @author yubin
 * @version 0.1 2015年6月28日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class BRepaymentRecordService {
	@Autowired
	private BRepaymentRecordMapper bRepaymentRecordMapper;

	@Autowired
	private BRepaymentRecordLogMapper bRepaymentRecordLogMapper;

	/**
	 * 
	 * <p>
	 * Description:根据借款标id分页查询待还集合<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param curPage
	 * @param pageSize
	 * @param borrowId
	 * @return
	 * @throws Exception
	 * Page
	 */
	public Page queryRepaymentByBorrowId(Integer curPage, Integer pageSize, Integer borrowId) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = bRepaymentRecordMapper.queryRepaymentByBorrowIdCount(borrowId);
		page.setTotalCount(totalCount);
		List<BRepaymentRecordVo> list = bRepaymentRecordMapper.queryRepaymentByBorrowId(borrowId, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 
	 * <p>
	 * Description:根据借款标id查询待还集合<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowId
	 * @return
	 * @throws Exception
	 * List<BRepaymentRecordVo>
	 */
	public List<BRepaymentRecordVo> queryRepaymentByBorrowId(Integer borrowId) throws Exception {
		return bRepaymentRecordMapper.queryRepaymentByBorrowId(borrowId);
	}

	/**
	 * 
	 * <p>
	 * Description:根据id查找待还记录并锁定 <br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param id
	 * @return
	 * @throws Exception
	 * BRepaymentRecordVo
	 */
	public BRepaymentRecordVo queryRepaymentByIdForUpdate(Integer id) throws Exception {
		return bRepaymentRecordMapper.queryRepaymentByIdForUpdate(id);
	}

	/**
	 * 
	 * <p>
	 * Description:分页查询还款中的借款标集合<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * Page
	 */
	public Page selectRepayingBorrow(BorrowCnd borrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);


		if (borrowCnd.getRepaymentTimeBeginStr() != null && !"".equals(borrowCnd.getRepaymentTimeBeginStr())) {
			borrowCnd.setRepaymentTimeBeginStr(DateUtils.dateTime2TimeStamp( borrowCnd.getRepaymentTimeBeginStr() + " 00:00:00"));
		} else {
			borrowCnd.setRepaymentTimeBeginStr(null);
		}
		if (borrowCnd.getRepaymentTimeEndStr() != null && !"".equals(borrowCnd.getRepaymentTimeEndStr())) {
			borrowCnd.setRepaymentTimeEndStr(DateUtils.dateTime2TimeStamp(borrowCnd.getRepaymentTimeEndStr() + " 23:59:59"));
		} else {
			borrowCnd.setRepaymentTimeEndStr(null);
		}
		String statusStr = borrowCnd.getStatusStr();
		if(statusStr != null && !statusStr.equals("")){
			borrowCnd.setStatus(statusStr);
			if(statusStr.equals("0")){
				borrowCnd.setRepaymentStatus("1");
			}
			if(statusStr.equals("1")){
				borrowCnd.setRepaymentStatus("2");
			}

		}
		int totalCount = bRepaymentRecordMapper.selectRepayingBorrowCount(borrowCnd);
		page.setTotalCount(totalCount);
		List<BRepaymentRecordVo> list = bRepaymentRecordMapper.selectRepayingBorrow(borrowCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 
	 * <p>
	 * Description:统计符合条件的还款中的借款标的【待还总额】<br />
	 * </p>
	 * 
	 * @author 胡建盼
	 * @version 0.1 2014年12月26日
	 * @param borrowCnd
	 * @return Integer
	 */
	
	public BigDecimal sumWaitRepayMoney(BorrowCnd borrowCnd) {
		return bRepaymentRecordMapper.sumWaitRepayMoney(borrowCnd);
	}
 
	/**
	 * 
	 * <p>
	 * Description:查询需要补罚息的记录集合<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * Page
	 */
	public Page selectRepairInterestPage(BorrowCnd borrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = bRepaymentRecordMapper.selectRepairInterestCount(borrowCnd);
		page.setTotalCount(totalCount);
		List<BRepaymentRecordVo> list = bRepaymentRecordMapper.selectRepairInterestList(borrowCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 
	 * <p>
	 * Description:查询官方标还款记录(分页)<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param bRepaymentRecordCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * Page
	 */
	public Page selectRepaymentListForPage(BRepaymentRecordCnd bRepaymentRecordCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		if (bRepaymentRecordCnd.getStatus() == null || bRepaymentRecordCnd.getStatus() == -1) {
			bRepaymentRecordCnd.setStatus(null);
		}
		if (bRepaymentRecordCnd.getWebstatus() == null || bRepaymentRecordCnd.getWebstatus() == -1) {
			bRepaymentRecordCnd.setWebstatus(null);
		}
		if (bRepaymentRecordCnd.getRepaymentTimeBegin() != null) {
			bRepaymentRecordCnd.setRepaymentTimeBegin(DateUtils.convert2StartDate(bRepaymentRecordCnd.getRepaymentTimeBegin()));
			bRepaymentRecordCnd.setRepaymentTimeBeginStr(bRepaymentRecordCnd.getRepaymentTimeBegin().getTime() / 1000 + "");
		}
		if (bRepaymentRecordCnd.getRepaymentTimeEnd() != null) {
			bRepaymentRecordCnd.setRepaymentTimeEnd(DateUtils.convert2EndDate(bRepaymentRecordCnd.getRepaymentTimeEnd()));
			bRepaymentRecordCnd.setRepaymentTimeEndStr(bRepaymentRecordCnd.getRepaymentTimeEnd().getTime() / 1000 + "");
		}
		int totalCount = bRepaymentRecordMapper.selectRepaymentListCount(bRepaymentRecordCnd);
		page.setTotalCount(totalCount);
		List<BRepaymentRecordVo> list = bRepaymentRecordMapper.selectRepaymentList(bRepaymentRecordCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 
	 * <p>
	 * Description:查询官方标还款记录导出Excel<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param bRepaymentRecordCnd
	 * @return
	 * @throws Exception
	 * List<BRepaymentRecordCheck>
	 */
	public List<BRepaymentRecordCheck> selectRepaymentListToExcel(BRepaymentRecordCnd bRepaymentRecordCnd) throws Exception {
		System.out.println(bRepaymentRecordCnd.getStatus()+"=="+bRepaymentRecordCnd.getWebstatus());
		if (bRepaymentRecordCnd.getStatus() == -1) {
			bRepaymentRecordCnd.setStatus(null);
		}
		if (bRepaymentRecordCnd.getWebstatus() == -1) {
			bRepaymentRecordCnd.setWebstatus(null);
		}
		if (bRepaymentRecordCnd.getRepaymentTimeBegin() != null) {
			bRepaymentRecordCnd.setRepaymentTimeBegin(DateUtils.convert2StartDate(bRepaymentRecordCnd.getRepaymentTimeBegin()));
			bRepaymentRecordCnd.setRepaymentTimeBeginStr(bRepaymentRecordCnd.getRepaymentTimeBegin().getTime() / 1000 + "");
		}
		if (bRepaymentRecordCnd.getRepaymentTimeEnd() != null) {
			bRepaymentRecordCnd.setRepaymentTimeEnd(DateUtils.convert2EndDate(bRepaymentRecordCnd.getRepaymentTimeEnd()));
			bRepaymentRecordCnd.setRepaymentTimeEndStr(bRepaymentRecordCnd.getRepaymentTimeEnd().getTime() / 1000 + "");
		}
		return bRepaymentRecordMapper.selectRepaymentListToExcel(bRepaymentRecordCnd);
	}

	
	public Integer selectAdvaceRepair(Integer order, Integer borrowId) throws Exception {
		return bRepaymentRecordMapper.selectAdvaceRepair(order, borrowId);
	}

	
	public Integer queryBeforeOrderUnPayCount(Integer borrowId, Integer order) throws Exception {
		return bRepaymentRecordMapper.queryBeforeOrderUnPayCount(borrowId, order);
	}
    /**
     * 
     * <p>
     * Description:查询某标某期之前未还款的记录<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年6月28日
     * @param borrowCnd
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     * Page
     */
	public Page selectRepayingLog(BorrowCnd borrowCnd, Integer pageNo, Integer pageSize) throws Exception {
		Page page = new Page(pageNo, pageSize);
	  
		if (borrowCnd.getRepaymentTimeBeginStr() != null) {
			borrowCnd.setRepaymentTimeBeginStr(DateUtils.dateTime2TimeStamp(borrowCnd.getRepaymentTimeBeginStr()+ " 00:00:00"));
		} else {
			borrowCnd.setRepaymentTimeBeginStr(null);
		}
		if (borrowCnd.getRepaymentTimeEndStr() != null) {
			borrowCnd.setRepaymentTimeEndStr(DateUtils.dateTime2TimeStamp(borrowCnd.getRepaymentTimeEndStr()+ " 23:59:59"));
		} else {
			borrowCnd.setRepaymentTimeEndStr(null);
		}
	 	page.setTotalCount(bRepaymentRecordMapper.selectCountRepayedBorrow(borrowCnd));
		page.setResult(bRepaymentRecordMapper.selectRepayedBorrow(borrowCnd, page));
		return page;

	}



	public Page selectRepayFail(BorrowCnd borrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);


		if (borrowCnd.getRepaymentTimeBeginStr() != null && !"".equals(borrowCnd.getRepaymentTimeBeginStr())) {
			borrowCnd.setRepaymentTimeBeginStr(DateUtils.dateTime2TimeStamp( borrowCnd.getRepaymentTimeBeginStr() + " 00:00:00"));
		} else {
			borrowCnd.setRepaymentTimeBeginStr(null);
		}
		if (borrowCnd.getRepaymentTimeEndStr() != null && !"".equals(borrowCnd.getRepaymentTimeEndStr())) {
			borrowCnd.setRepaymentTimeEndStr(DateUtils.dateTime2TimeStamp(borrowCnd.getRepaymentTimeEndStr() + " 23:59:59"));
		} else {
			borrowCnd.setRepaymentTimeEndStr(null);
		}

		int totalCount = bRepaymentRecordMapper.selectRepayFailCount(borrowCnd);
		page.setTotalCount(totalCount);
		List<BRepaymentRecordVo> list = bRepaymentRecordMapper.selectRepayFail(borrowCnd, page);
		page.setResult(list);
		return page;
	}

	public BRepaymentRecordVo queryRepaymentById(Integer id) throws Exception {
		return bRepaymentRecordMapper.queryRepaymentById(id);
	}

	/**
	 * 查询已垫付未还款记录总数
	 * @param borrowId
	 * @return
	 * @throws Exception
     */
	public Integer queryWebpayCount(Integer borrowId) throws Exception {
		return bRepaymentRecordMapper.queryWebpayCount(borrowId);
	}


	/**
	 * 查询应还总额
	 * @param borrowId
	 * @return
	 * @throws Exception
	 */
	public BigDecimal queryRepayTotalByBorrowId(Integer borrowId) throws Exception {
		return bRepaymentRecordMapper.queryRepayTotalByBorrowId(borrowId);
	}



	/**
	 * 查询当前所在期数
	 * @param borrowId
	 * @return
	 * @throws Exception
	 */
	public OrderRangeVo getCurrentOrder(Integer borrowId) throws Exception {
		return bRepaymentRecordMapper.getCurrentOrder(borrowId);
	}


}
