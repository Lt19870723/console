package com.cxdai.console.curaccount.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.curaccount.entity.CurAccount;
import com.cxdai.console.curaccount.entity.CurAccountlog;
import com.cxdai.console.curaccount.mapper.CurAccountlogMapper;
import com.cxdai.console.curaccount.vo.CurAccountLogCnd;
import com.cxdai.console.curaccount.vo.CurInOutAccountCnd;
import com.cxdai.console.curaccount.vo.CurInOutAccountVo;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.system.mapper.ConfigurationMapper;

/***
 * 
 * <p>
 * Description:活期宝账户操作日志记录 BLL <br />
 * </p>
 * 
 * @title CurAccountLogServiceImpl.java
 * @package com.cxdai.portal.curAccount.service.impl
 * @author HuangJun
 * @version 0.1 2015年4月27日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CurAccountLogService implements Serializable {

	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;

	Logger logger = LoggerFactory.getLogger(CurAccountLogService.class);

	@Autowired
	private CurAccountlogMapper curAccountlogMapper;

	@Autowired
	private ConfigurationMapper configurationMapper;

	/**
	 * 活期宝转入转出 - 分页 (non-Javadoc)
	 * 
	 * @see com.cxdai.portal.curAccount.service.CurAccountLogService#queryCurAccountLogByPage(java.util.Map,
	 *      com.cxdai.common.page.Page)
	 */
	public Page queryCurAccountLogByPage(CurInOutAccountCnd curAccountLogCnd, int pageNo, int pageSize) throws Exception {
		List<CurInOutAccountVo> retLst = null;
		Page page = new Page(pageNo, pageSize);
		try {

			int curCount = curAccountlogMapper.queryCurAccountLogCount(curAccountLogCnd);
			if (curCount > 0) {
				page.setTotalCount(curCount);
			}

			retLst = curAccountlogMapper.queryCurAccountLogList(curAccountLogCnd, page);

			if (retLst != null && retLst.size() > 0) {
				page.setResult(retLst);
			}

		} catch (Exception e) {
			logger.error("活期宝转入转出 - 分页异常",e);
		}

		return page;

	}

	/**
	 * <p>
	 * Description: 转入累计， 转出累计,查询所有符合条件的累计-不分页 <br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年5月18日
	 * @param curAccountLogCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 *             Map<String,Object>
	 */
	
	public Map<String, Object> getSumMoneyInOut(CurInOutAccountCnd curAccountLogCnd) throws Exception {
 
		Map<String, Object> sumMap = new HashMap<String, Object>();
		try {
 			// 转入合计			
			BigDecimal sumIn = curAccountlogMapper.queryCurAccountSumForIn(curAccountLogCnd);
			// 转出合计			
			BigDecimal sumOut = curAccountlogMapper.queryCurAccountSumForOut(curAccountLogCnd);
		
			sumMap.put("sumIn", sumIn);
			sumMap.put("sumOut", sumOut);

		} catch (Exception e) {
			logger.error("转入累计， 转出累计异常",e);
		}
		return sumMap;
	}

	
	public void saveCurAccountLogByParams(CurAccount curAccount, Integer userId, BigDecimal money, String remark, String addIp, Integer type,
			Integer adduser) throws Exception {
		CurAccountlog curAccountlog = new CurAccountlog();
		curAccountlog.setUserId(userId);
		curAccountlog.setType(type);
		curAccountlog.setMoney(money);
		curAccountlog.setTotal(curAccount.getTotal());
		curAccountlog.setUseMoney(curAccount.getUseMoney());
		curAccountlog.setNoUseMoney(curAccount.getNoUseMoney());
		curAccountlog.setInterestTotal(curAccount.getInterestTotal());
		curAccountlog.setInterestYesterday(curAccount.getInterestYesterday());
		curAccountlog.setAdduser(adduser);
		curAccountlog.setAddip(addIp);
		curAccountlog.setAddtime(new Date());
		curAccountlog.setRemark(remark);
		curAccountlogMapper.insert(curAccountlog);
	}

	/**
	 * 根据 type 获取 数据字典 集合 (non-Javadoc)
	 * 
	 * @see com.cxdai.console.curAccount.service.CurAccountLogService#selectConfigurationByConn()
	 */
	public List<Map<String, Object>> selectConfigurationByConn(Configuration configuration) throws Exception {
		List<Map<String, Object>> retlst = new ArrayList<Map<String, Object>>();
		try {

			retlst = configurationMapper.selectConfigurationByConn(configuration);

		} catch (Exception e) {
			logger.error("活期宝-读取数据词典异常: " + e);
		}
		return retlst;

	}

	/**
	 * 统计 - 转入累计金额 (non-Javadoc)
	 * 
	 * @see com.cxdai.console.curAccount.service.CurAccountLogService#querySumMoneyIn(com.cxdai.console.curAccount.vo.CurAccountLogCnd)
	 */
	public BigDecimal querySumMoneyIn(CurAccountLogCnd curAccountLogCnd) throws Exception {
		return curAccountlogMapper.querySumMoneyIn(curAccountLogCnd);
	}

	/**
	 * 统计 - 转出累计金额 (non-Javadoc)
	 * 
	 * @see com.cxdai.console.curAccount.service.CurAccountLogService#querySumMoneyOut(com.cxdai.console.curAccount.vo.CurAccountLogCnd)
	 */
	public BigDecimal querySumMoneyOut(CurAccountLogCnd curAccountLogCnd) throws Exception {
		return curAccountlogMapper.querySumMoneyOut(curAccountLogCnd);
	}

}
