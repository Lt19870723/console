package com.cxdai.console.curaccount.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.curaccount.mapper.CurInterestDetailMapper;
import com.cxdai.console.curaccount.vo.CurAccountCnd;
import com.cxdai.console.curaccount.vo.CurInterestDetailVo;

/****
 * 
 * 
 * <p>
 * Description: 收益发放日志 BLL <br />
 * </p>
 * 
 * @title CurInterestDetailServiceImpl.java
 * @package com.cxdai.portal.curAccount.service.impl
 * @author HuangJun
 * @version 0.1 2015年4月27日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CurInterestDetailService {

	Logger logger = LoggerFactory.getLogger(CurInterestDetailService.class);

	@Autowired
	private CurInterestDetailMapper curInterestDetailMapper;

	/**
	 * 根据userId 查询收益明细 - 分页 (non-Javadoc)
	 * 
	 * @see com.cxdai.portal.curAccount.service.CurInterestDetailService#queryCurInterestDetailByPage(java.util.Map,
	 *      com.cxdai.common.page.Page)
	 */
	public Page queryCurInterestDetailByPage(CurAccountCnd curAccountCnd, int curPage, int pageSize) throws Exception {

		Page page = new Page(curPage,pageSize);
		int totalCount = 0;
		List<CurInterestDetailVo> retLst = null;
		try {
			totalCount = curInterestDetailMapper.queryInterestDetailCount(curAccountCnd);
			if (totalCount > 0) {
				page.setTotalCount(totalCount);
			}

			retLst = curInterestDetailMapper.queryInterestDetailList(curAccountCnd, page);
			if (retLst != null && retLst.size() > 0) {
				page.setResult(retLst);
			}

		} catch (Exception e) {
			logger.error("根据userId 查询收益明细异常",e);
		}
		return page;
	}

}
