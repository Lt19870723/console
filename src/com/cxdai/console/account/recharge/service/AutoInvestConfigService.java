package com.cxdai.console.account.recharge.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.recharge.mapper.AutoInvestConfigMapper;
import com.cxdai.console.account.recharge.vo.AutoInvestConfigCnd;
import com.cxdai.console.account.recharge.vo.AutoInvestConfigVo;
import com.cxdai.console.common.page.Page;
/**
 * 
 * <p>
 * Description:自动投标规则业务接口<br />
 * </p>
 * 
 * @title AutoInvestConfigService.java
 * @package com.cxdai.console.account.service
 * @author yangshijin
 * @version 0.1 2014年11月28日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class AutoInvestConfigService {
	@Autowired
	private AutoInvestConfigMapper autoInvestConfigMapper;
	/**
	 * 
	 * <p>
	 * Description:自动投标规则列表分页<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年7月2日
	 * @param iPWhileListCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception Page
	 */
	public Page queryListForPage(AutoInvestConfigCnd autoInvestConfigCnd, int curPage, int pageSize) throws Exception{
		Page page = new Page(curPage, pageSize);
		Integer totalCount = autoInvestConfigMapper.queryListCountByCnd(autoInvestConfigCnd);
		page.setTotalCount(totalCount);
		List<AutoInvestConfigVo> list = autoInvestConfigMapper.queryListByCnd(autoInvestConfigCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 
	 * <p>
	 * Description:根据id查询自动投标规则记录 <br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年11月28日
	 * @param id
	 * @return
	 * @throws Exception AutoInvestConfigVo
	 */
	public AutoInvestConfigVo queryById(int id) throws Exception{
		return autoInvestConfigMapper.queryById(id);
	}
}
