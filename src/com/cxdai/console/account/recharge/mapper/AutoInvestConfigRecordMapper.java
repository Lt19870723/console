package com.cxdai.console.account.recharge.mapper;

import java.util.List;

import com.cxdai.console.account.recharge.vo.AutoInvestConfigRecordCnd;
import com.cxdai.console.account.recharge.vo.AutoInvestConfigRecordVo;
import com.cxdai.console.common.page.Page;

/**
 * 
 * <p>
 * Description:自动投标规则日志类数据访问类<br />
 * </p>
 * 
 * @title AutoInvestConfigRecordMapper.java
 * @package com.cxdai.console.account.mapper
 * @author yangshijin
 * @version 0.1 2014年11月28日
 */
public interface AutoInvestConfigRecordMapper {

	/**
	 * 
	 * <p>
	 * Description:根据条件查询自动投标规则日志记录集合（分页）<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年5月20日
	 * @param autoInvestConfigRecordCnd
	 * @param page
	 * @return
	 * @throws Exception List<AutoInvestConfigRecordVo>
	 */
	public List<AutoInvestConfigRecordVo> queryAutoInvestConfigRecordListForPage(AutoInvestConfigRecordCnd autoInvestConfigRecordCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据条件查询自动投标规则日志数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年5月20日
	 * @param autoInvestConfigRecordCnd
	 * @return
	 * @throws Exception int
	 */
	public int queryAutoInvestConfigRecordCount(AutoInvestConfigRecordCnd autoInvestConfigRecordCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据ID查询日志信息<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年5月28日
	 * @param id
	 * @return
	 * @throws Exception AutoInvestConfigRecordVo
	 */
	public AutoInvestConfigRecordVo queryById(Integer id) throws Exception;
	
	
	public AutoInvestConfigRecordVo findAutoInvestRecord(Integer recordId);
	
	public Integer updateInvestConfigRecord(Integer id);
}
