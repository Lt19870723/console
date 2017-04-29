package com.cxdai.console.account.recharge.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.account.recharge.vo.AutoInvestConfigCnd;
import com.cxdai.console.account.recharge.vo.AutoInvestConfigVo;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.common.page.Page;



/**
 * 
 * <p>
 * Description:自动投标规则类数据访问类<br />
 * </p>
 * 
 * @title BaseAutoInvestConfigMapper.java
 * @package com.cxdai.base.mapper
 * @author yangshijin
 * @version 0.1 2014年5月19日
 */
public interface AutoInvestConfigMapper {

	/**
	 * 
	 * <p>
	 * Description:查询自动投标规则<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年5月19日
	 * @param user_id
	 * @return
	 * @throws Exception List<AutoInvestConfigVo>
	 */
	public List<AutoInvestConfigVo> queryListByCnd(AutoInvestConfigCnd autoIntInvestConfigCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询自动投标规则数量.<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年5月20日
	 * @param user_id
	 * @return
	 * @throws Exception int
	 */
	public int queryListCountByCnd(AutoInvestConfigCnd autoIntInvestConfigCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据ID查询自投标规则（包含排队号）<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年5月20日
	 * @param id
	 * @return
	 * @throws Exception AutoInvestConfigVo
	 */
	public AutoInvestConfigVo queryById(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据borrowId查询最后一笔自动投标的排队时间<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年11月28日
	 * @param borrowId
	 * @return
	 * @throws Exception String
	 */
	public String queryUptimeForLastAutoTender(int borrowId) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:调用自动投标存储过程<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年11月28日
	 * @param map void
	 */
	public void autoTender(Map map);
	
	/**
	 * 
	 * <p>
	 * Description:筛选符合条件的用户<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年6月28日
	 * @param borrowVo
	 * @return
	 * AutoInvestConfigVo
	 */
	public List<AutoInvestConfigVo> findAutoInvestConfig(BorrowVo borrowVo);
	
	
	public AutoInvestConfigVo findInvestConfig(Integer id);
	
	public Integer updateAutoInvestConfig(@Param("uptime") String uptime,@Param("id") Integer id);
}
