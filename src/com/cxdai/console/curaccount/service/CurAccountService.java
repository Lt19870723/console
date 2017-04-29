package com.cxdai.console.curaccount.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.curaccount.mapper.CurAccountMapper;
import com.cxdai.console.curaccount.vo.CurAccountCnd;
import com.cxdai.console.curaccount.vo.CurAccountVo;

/****
 * 
 * 
 * <p>
 * Description: 活期宝账户 BLL <br />
 * </p>
 * 
 * @title CurAccountServiceImpl.java
 * @package com.cxdai.portal.curAccount.service.impl
 * @author HuangJun
 * @version 0.1 2015年4月27日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CurAccountService {

	Logger logger = LoggerFactory.getLogger(CurAccountService.class);

	@Autowired
	CurAccountMapper curAccountMapper;

	/**
	 *  查看详情 - 根据userId查询我的账户
	 *  
	 *  (non-Javadoc)
	 * @see com.cxdai.console.curAccount.service.CurAccountService#selectByUserId(java.lang.Integer)
	 */
	
	public CurAccountVo selectByUserId(Integer userId) {
		return curAccountMapper.selectByUserId(userId);
	}

	public CurAccountVo selectByUserIdForUpdate(Integer userId) {
		return curAccountMapper.selectByUserIdForUpdate(userId);
	}

	/**
	 * 查询我的账户-用户收益 - 分页 (non-Javadoc)
	 * 
	 * @see com.cxdai.console.curAccount.service.CurAccountService#queryCurAccountListPage(com.cxdai.console.curAccount.vo.CurAccountCnd,
	 *      int, int)
	 */
	public Page queryCurAccountListPage(CurAccountCnd curAccountCnd, int curPage, int pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		try {

			int totalCount = curAccountMapper.queryCurAccountCount(curAccountCnd);
			page.setTotalCount(totalCount);
			List<CurAccountVo> list = curAccountMapper.queryCurAccountList(curAccountCnd, page);
			if (list != null && list.size() > 0) {
				page.setResult(list);
			}

		} catch (Exception e) {
			logger.error("查询我的账户-用户收益异常", e);
		}
		return page;

	}

	/**
	 * 
	 * 用户收益 - 求和 (non-Javadoc)
	 * 
	 * @see com.cxdai.console.curAccount.service.CurAccountService#querySumByConn(com.cxdai.console.curAccount.vo.CurAccountCnd)
	 */
	
	public CurAccountVo querySumByConn(CurAccountCnd curAccountCnd) throws Exception {
		CurAccountVo retCurAccountVo = new CurAccountVo();
		try {
				retCurAccountVo = curAccountMapper.querySumByConn(curAccountCnd);
		} catch (Exception e) {
			logger.error("用户收益 - 求和异常", e);
		}
		return retCurAccountVo;
	}
	
	
	/**
	 * 查看详情 - 根据id查询我的账户
	 *  (non-Javadoc)
	 * @see com.cxdai.console.curAccount.service.CurAccountService#queryCurAccountVoById(java.lang.Integer)
	 */
	public CurAccountVo queryCurAccountVoById(Integer id) throws Exception{
		CurAccountVo retCurAccountVo = new CurAccountVo();
		try {
			retCurAccountVo = curAccountMapper.queryCurAccountVoById(id);
		} catch (Exception e) {
			logger.error("查看详情 - 根据id查询我的账户异常", e);
		}
		return retCurAccountVo;
	}

}
