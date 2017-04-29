package com.cxdai.console.account.risk.mapper;


import com.cxdai.console.account.risk.entity.RiskMargin;

/**
 * 
 * <p>
 * Description:风险备用金数据访问类<br />
 * </p>
 * @title RishMarginMapper.java
 * @package com.cxdai.base.mapper 
 */
public interface BaseRiskMarginMapper {

	/**
	 * 
	 * <p>
	 * Description:根据id查询风险备用金<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年4月23日
	 * @param id
	 * @return
	 * @throws Exception
	 * Member
	 */
	public RiskMargin queryById(Integer id) throws Exception;
	
	/**
	 * 
	 * <p>
	 * Description:查询风险备用金<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年7月3日
	 * @return
	 * @throws Exception
	 * RiskMargin
	 */
	public RiskMargin queryRiskMargin() throws Exception;
	

	/**
	 * 
	 * <p>
	 * Description:更新风险备用金<br />
	 * </p>
	 * @author yangshijin
	 * @version 0.1 2014年7月3日
	 * @param riskMargin
	 * @return
	 * @throws Exception
	 * int
	 */
	public int updateEntity(RiskMargin riskMargin) throws Exception;

}
