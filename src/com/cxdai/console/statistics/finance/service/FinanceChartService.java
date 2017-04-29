package com.cxdai.console.statistics.finance.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.borrow.manage.vo.BRepaymentRecordCnd;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.finance.mapper.FinanceChartMapper;
import com.cxdai.console.util.DateUtils;

@Service
@Transactional(rollbackFor = Throwable.class)
public class FinanceChartService {
	@Autowired
	private FinanceChartMapper financeChartMapper;
    
	
	public Page findRepaymentChartForWeekPages(BRepaymentRecordCnd repaymentRecordCnd, int curPage, int pageSize) throws Exception {
		getBRepaymentRecordCnd(repaymentRecordCnd);
		Page page = new Page(curPage, pageSize);
		int totalcount = financeChartMapper.findRepaymentChartForWeekCount(repaymentRecordCnd);
		page.setTotalCount(totalcount);
		List<BRepaymentRecordVo> list = financeChartMapper.findRepaymentChartForWeekPages(repaymentRecordCnd, page);
		page.setResult(list);
		return page;
	}

	public Page findRepaymentChartForOverduePages(BRepaymentRecordCnd repaymentRecordCnd, int curPage, int pageSize) throws Exception {
		getBRepaymentRecordCnd(repaymentRecordCnd); 
		Page page = new Page(curPage, pageSize);
		int totalcount = financeChartMapper.findRepaymentChartForOverdueCount(repaymentRecordCnd);
		page.setTotalCount(totalcount);
		List<BRepaymentRecordVo> list = financeChartMapper.findRepaymentChartForOverduePages(repaymentRecordCnd, page);
		page.setResult(list);
		return page;
	}

	public Page findRepaymentChartForFinishPages(BRepaymentRecordCnd repaymentRecordCnd, int curPage, int pageSize) throws Exception {
		
		Page page = new Page(curPage, pageSize);
		if (repaymentRecordCnd.getRepaymentTimeBegin() != null) {
			repaymentRecordCnd.setBeignTime(DateUtils.dateTime2TimeStamp(DateUtils.format(repaymentRecordCnd.getRepaymentTimeBegin(), DateUtils.YMD_HMS)));
		} else {
			repaymentRecordCnd.setBeignTime(null);
		}
		if (repaymentRecordCnd.getRepaymentTimeEnd() != null) {
			repaymentRecordCnd.setEndTime(DateUtils.dateTime2TimeStamp(DateUtils.format(repaymentRecordCnd.getRepaymentTimeEnd(), DateUtils.YMD_DASH) + " 23:59:59"));
		} else {
			repaymentRecordCnd.setEndTime(null);
		}
		int totalcount = financeChartMapper.findRepaymentChartForFinishCount(repaymentRecordCnd);
		page.setTotalCount(totalcount);
		List<BRepaymentRecordVo> list = financeChartMapper.findRepaymentChartForFinishPages(repaymentRecordCnd, page);
		page.setResult(list);
		return page;
	}

	public Map<String, BigDecimal> queryRepaymentChartForWeek(BRepaymentRecordCnd repaymentRecordCnd) throws Exception {
		 
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		List<Map<String, Object>> list = financeChartMapper.queryRepaymentChartForWeek(repaymentRecordCnd);
		if (list.size() > 0 && list.get(0) != null) {
			if (list.get(0).get("noRepaymentAccount") != null) {
				map.put("noRepaymentAccount", new BigDecimal(list.get(0).get("noRepaymentAccount").toString()));
			} else {
				map.put("noRepaymentAccount", new BigDecimal(0));
			}
			if (list.get(0).get("yesRepaymentAccount") != null) {
				map.put("yesRepaymentAccount", new BigDecimal(list.get(0).get("yesRepaymentAccount").toString()));
			} else {
				map.put("yesRepaymentAccount", new BigDecimal(0));
			}
			if (list.get(0).get("repaymentAccount") != null) {
				map.put("repaymentAccount", new BigDecimal(list.get(0).get("repaymentAccount").toString()));
			} else {
				map.put("repaymentAccount", new BigDecimal(0));
			}
		} else {
			map.put("noRepaymentAccount", BigDecimal.ZERO);
			map.put("yesRepaymentAccount", BigDecimal.ZERO);
			map.put("repaymentAccount", BigDecimal.ZERO);
		}
		return map;
	}

	public Map<String, BigDecimal> queryFirstRepaymentChartForWeek(BRepaymentRecordCnd repaymentRecordCnd) throws Exception {
		getBRepaymentRecordCnd(repaymentRecordCnd);
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map = financeChartMapper.queryFirstRepaymentChartForWeek(repaymentRecordCnd);
		return map;
	}

	public List<BRepaymentRecordVo> queryRepaymentChartForWeekList(BRepaymentRecordCnd repaymentRecordCnd) throws Exception {
		getBRepaymentRecordCnd(repaymentRecordCnd);
		return financeChartMapper.findRepaymentChartForWeekPages(repaymentRecordCnd);
	}

	public List<BRepaymentRecordVo> queryRepaymentChartForOvedueList(BRepaymentRecordCnd repaymentRecordCnd) throws Exception {
		getBRepaymentRecordCnd(repaymentRecordCnd);
		return financeChartMapper.findRepaymentChartForOverduePages(repaymentRecordCnd);
	}

	public List<BRepaymentRecordVo> queryRepaymentChartForFinishList(BRepaymentRecordCnd repaymentRecordCnd) throws Exception {
		getBRepaymentRecordCnd(repaymentRecordCnd);
		return financeChartMapper.findRepaymentChartForFinishPages(repaymentRecordCnd);
	}

	public Map<String, Object> queryRepaymentChartMapForOverdue(BRepaymentRecordCnd repaymentRecordCnd) throws Exception {
		if (repaymentRecordCnd.getRepaymentTimeBegin() != null) {
			repaymentRecordCnd.setBeignTime(DateUtils.dateTime2TimeStamp(DateUtils.format(repaymentRecordCnd.getRepaymentTimeBegin(), DateUtils.YMD_HMS)));
		} else {
			repaymentRecordCnd.setBeignTime(null);
		}
		if (repaymentRecordCnd.getRepaymentTimeEnd() != null) {
			repaymentRecordCnd.setEndTime(DateUtils.dateTime2TimeStamp(DateUtils.format(repaymentRecordCnd.getRepaymentTimeEnd(), DateUtils.YMD_DASH) + " 23:59:59"));
		} else {
			repaymentRecordCnd.setEndTime(null);
		}
		Map<String, Object> map = financeChartMapper.queryRepaymentChartMapForOverdue(repaymentRecordCnd);
		if (map == null || map.get("totalCount") == null) {
			map.put("totalCount", BigDecimal.ZERO);
		}
		if (map == null || map.get("totalCapital") == null) {
			map.put("totalCapital", BigDecimal.ZERO);
		}
		if (map == null || map.get("totalFirstAcount") == null) {
			map.put("totalFirstAcount", BigDecimal.ZERO);
		}
		if (map == null || map.get("totalInterest") == null) {
			map.put("totalInterest", BigDecimal.ZERO);
		}
		if (map == null || map.get("totalLateInterest") == null) {
			map.put("totalLateInterest", BigDecimal.ZERO);
		}
		return map;
	}

	public Map<String, Object> queryRepaymentChartMapForFinish(BRepaymentRecordCnd repaymentRecordCnd) throws Exception {
		if (repaymentRecordCnd.getRepaymentTimeBegin() != null) {
			repaymentRecordCnd.setBeignTime(DateUtils.dateTime2TimeStamp(DateUtils.format(repaymentRecordCnd.getRepaymentTimeBegin(), DateUtils.YMD_HMS)));
		} else {
			repaymentRecordCnd.setBeignTime(null);
		}
		if (repaymentRecordCnd.getRepaymentTimeEnd() != null) {
			repaymentRecordCnd.setEndTime(DateUtils.dateTime2TimeStamp(DateUtils.format(repaymentRecordCnd.getRepaymentTimeEnd(), DateUtils.YMD_DASH) + " 23:59:59"));
		} else {
			repaymentRecordCnd.setEndTime(null);
		}
		Map<String, Object> map = financeChartMapper.queryRepaymentChartMapForFinish(repaymentRecordCnd);
		if (map == null || map.get("totalCount") == null) {
			map.put("totalCount", BigDecimal.ZERO);
		}
		if (map == null || map.get("totalCapital") == null) {
			map.put("totalCapital", BigDecimal.ZERO);
		}
		if (map == null || map.get("totalFirstAcount") == null) {
			map.put("totalFirstAcount", BigDecimal.ZERO);
		}
		if (map == null || map.get("totalInterest") == null) {
			map.put("totalInterest", BigDecimal.ZERO);
		}
		if (map == null || map.get("totalLateInterest") == null) {
			map.put("totalLateInterest", BigDecimal.ZERO);
		}
		return map;
	}
	public  static void getBRepaymentRecordCnd(BRepaymentRecordCnd repaymentRecordCnd){
		if (repaymentRecordCnd.getRepaymentTimeBegin() != null) {
			repaymentRecordCnd.setBeignTime(DateUtils.dateTime2TimeStamp(DateUtils.format(repaymentRecordCnd.getRepaymentTimeBegin(), DateUtils.YMD_HMS)));
		} else {
			repaymentRecordCnd.setBeignTime(null);
		}
		if (repaymentRecordCnd.getRepaymentTimeEnd() != null) {
			repaymentRecordCnd.setEndTime(DateUtils.dateTime2TimeStamp(DateUtils.format(repaymentRecordCnd.getRepaymentTimeEnd(), DateUtils.YMD_DASH) + " 23:59:59"));
		} else {
			repaymentRecordCnd.setEndTime(null);
		}
	}
	
	/**
	 * 统计某段时间内的官方标未还定期宝总金额、已还定期宝总金额、应还定期宝总金额
	 * @author WangQianJin
	 * @title @param repaymentRecordCnd
	 * @title @return
	 * @title @throws Exception
	 * @date 2016年3月7日
	 */
	public Map<String, BigDecimal> queryFixRepaymentChartForWeek(BRepaymentRecordCnd repaymentRecordCnd) throws Exception {
		getBRepaymentRecordCnd(repaymentRecordCnd);
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		map = financeChartMapper.queryFixRepaymentChartForWeek(repaymentRecordCnd);
		return map;
	}
}
