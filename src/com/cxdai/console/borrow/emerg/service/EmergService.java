package com.cxdai.console.borrow.emerg.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.recharge.mapper.AutoInvestConfigMapper;
import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.approve.vo.CheckBorrowVo;
import com.cxdai.console.borrow.emerg.mapper.TenderRecordMapper;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.firstborrow.mapper.FirstBorrowMapper;

 
/**
 * 
 * <p>
 * Description:应急措施业务<br 
 * </p>
 * @title EmergServiceImpl.java
 * @package com.cxdai.console.borrow.emerg.service 
 * @author Administrator
 * @version 0.1 2015年6月24日
 */
@Service@Transactional(rollbackFor = Throwable.class)
public class EmergService {
	
	@Autowired
	private BorrowMapper borrowMapper;
	
	@Autowired
	private FirstBorrowMapper firstBorrowMapper;
	
	@Autowired
	private TenderRecordMapper tenderRecordMapper;
	
	@Autowired
	private AutoInvestConfigMapper autoInvestConfigMapper;
	/**
	 * 
	 * <p>
	 * Description:直通车再投<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2015年6月24日
	 * @param borrowCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * Page
	 */
	public Page queryListForHandFirstTender(BorrowCnd borrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page p = new Page();
		p.setPageNo(curPage);
		p.setPageSize(pageSize);
		int totalCount = borrowMapper.queryCountForHandFirstTender(borrowCnd);
		p.setTotalCount(totalCount);
		List<BorrowVo> list = borrowMapper.queryListForHandFirstTender(borrowCnd, p);
		p.setResult(list);
		return p;
	}
	/**
	 * 
	 * <p>
	 * Description:投标出错复审<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2015年6月25日
	 * @param borrowCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * Page
	 */
	public Page selectReCheckBorrow(BorrowCnd borrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = borrowMapper.selectReCheckBorrowCount(borrowCnd);
		page.setTotalCount(totalCount);
		List<CheckBorrowVo> list = borrowMapper.selectReCheckBorrow(borrowCnd, page);
		page.setResult(list);
		return page;
	}
	/**
	 * 
	 * <p>
	 * Description:继续自动投标业务<br />
	 * </p>
	 * @author Administrator
	 * @version 0.1 2015年6月25日
	 * @param borrowCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * Page
	 */
	public Page queryBorrowListForRestartAutoTender(BorrowCnd borrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page p = new Page();
		p.setPageNo(curPage);
		p.setPageSize(pageSize);
		int totalCount = borrowMapper.queryBorrowCountForRestartAutoTender(borrowCnd);
		p.setTotalCount(totalCount);
		List<BorrowVo> list = borrowMapper.queryBorrowListForRestartAutoTender(borrowCnd, p);
		p.setResult(list);
		return p;
	}
	/**
	 * 
	 * <p>
	 * Description:开始手动直通投标<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月27日
	 * @param borrowId
	 * @return
	 * @throws Exception
	 * String
	 */
	@Transactional(rollbackFor = Throwable.class)
	public String handleFirstTender(int borrowId) throws Exception {
		String result = "手动直通车投标成功！";
		try {
			// 查询借款标，并锁记录
			BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowId);
			//判断是否是非存管标
			if(borrowVo.getIsCustody()==null || borrowVo.getIsCustody()==0){
			}else{
				return "该借款标是存管标，无法投标！";
			}
			if (borrowVo == null || borrowVo.getStatus() != 2) {
				return "该借款标状态已变更，请刷新后重试！";
			}
			if (borrowVo.getBorrowtype() != 1 && borrowVo.getBorrowtype() != 2 && borrowVo.getBorrowtype() != 5) {
				return "只能对信用标、抵押标、担保标这三类标种进行直通车投标！";
			}
			if (borrowVo != null && borrowVo.getStatus() == 2) {
				Map<String, Object> map = tenderRecordMapper.queryTenderRecordByBorrowId(borrowVo.getId());
				if (map.get("total_account") != null && !map.get("total_account").toString().equals("")) {
					if (borrowVo.getAccountYes().compareTo(new BigDecimal(map.get("total_account").toString())) != 0) {
						return "投标记录总额与标已投金额不一致，无法手动直通车投标，请与技术人员联系！";
					}
				} else {
					if (borrowVo.getAccountYes().compareTo(BigDecimal.ZERO) != 0) {
						return "投标记录总额与标已投金额不一致，无法手动直通车投标，请与技术人员联系！";
					}
				}
				// 开始手动直通车投标
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("borrowid", borrowId);
				firstBorrowMapper.firstTender(dataMap);
				if (dataMap.get("msg") != null) {
					if (!dataMap.get("msg").toString().equals("0001")) {
						result = "手动直通车投标失败！";
					}
				} else {
					result = "手动直通车投标失败！";
				}
			}
			borrowVo = borrowMapper.selectByPrimaryKey(borrowId);
			if (borrowVo.getStatus() == 3) {
				// 抵押标自动复审通过
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("borrowid", borrowVo.getId());
				params.put("checkUserId", "-1");
				params.put("checkRemark", "自动复审通过");
				params.put("addip", "");
				borrowMapper.approveBorrowReCheck(params);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "手动直通车投标出错！";
		}

		return result;
	}
	/**
	 * 
	 * <p>
	 * Description:继续自动投标<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月27日
	 * @param borrowId
	 * @return
	 * @throws Exception
	 * String
	 */
	@Transactional(rollbackFor = Throwable.class)
	public String restartAutoTender(int borrowId) throws Exception {
		String result = "继续自动投标成功！";
		try {
			BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowId);
			if (borrowVo == null || borrowVo.getStatus() != 2) {
				return "该借款标状态已变更，请刷新后重试！";
			}
			if (borrowVo != null && borrowVo.getStatus() == 2 && borrowVo.getIsAutotender() == 1) {
				Map<String, Object> map = tenderRecordMapper.queryTenderRecordByBorrowId(borrowVo.getId());
				if (map.get("total_account") != null && !map.get("total_account").toString().equals("")) {
					borrowVo.setAccountYes(new BigDecimal(map.get("total_account").toString()));
				}
				borrowVo.setTenderTimes(Integer.parseInt(map.get("total_count").toString()));
				Borrow borrow = new Borrow();
				BeanUtils.copyProperties(borrowVo, borrow);
				borrowMapper.updateByPrimaryKey(borrow);
				// 查询最后一笔自动投标的排队时间
				String uptimeLast = autoInvestConfigMapper.queryUptimeForLastAutoTender(borrowId);
				if (borrow.getBidPassword() == null || borrow.getBidPassword().equals("")) {
					// 开始继续自动投标
					Map<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("borrowid", borrowId);
					if (uptimeLast != null && !uptimeLast.equals("")) {
						dataMap.put("uptime", uptimeLast);
					} else {
						dataMap.put("uptime", 1);
					}
					autoInvestConfigMapper.autoTender(dataMap);
					if (dataMap.get("msg") != null) {
						if (!dataMap.get("msg").toString().equals("0001")) {
							result = "继续自动投标失败！";
						} else {
							borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowId);
							BeanUtils.copyProperties(borrowVo, borrow);
							borrow.setIsAutotender(0); // 开始手动投标
							borrowMapper.updateByPrimaryKey(borrow);
						}
					} else {
						result = "继续自动投标失败！";
					}
				}
			}
			borrowVo = borrowMapper.selectByPrimaryKey(borrowId);
			if (borrowVo.getStatus() == 3) {
				// 抵押标自动复审通过
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("borrowid", borrowVo.getId());
				params.put("checkUserId", "-1");
				params.put("checkRemark", "自动复审通过");
				params.put("addip", "");
				borrowMapper.approveBorrowReCheck(params);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "继续自动投标出错！";
		}

		return result;
	}
	/**
	 * 
	 * <p>
	 * Description:将借款标由新手专区转为普通专区<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月30日
	 * @param borrowId
	 * @return
	 * @throws Exception
	 * String
	 */
	@Transactional(rollbackFor = Throwable.class)
	public String changeAreaBorrow(int borrowId) throws Exception {
		BorrowVo borrowVo = borrowMapper.selectByPrimaryKeyForUpdate(borrowId);
		borrowVo.setAreaType(0);
		borrowVo.setAreaChangeTime(new Date());
		borrowVo.setLowestAccount(new BigDecimal(50));
		borrowVo.setMostAccount(BigDecimal.ZERO);
		Borrow borrow = new Borrow();
		BeanUtils.copyProperties(borrowVo, borrow);
		if (borrowMapper.updateByPrimaryKey(borrow) > 0) {
			return "转普通专区成功！";
		}
		return "转普通专区失败！";
	}
}
