package com.cxdai.console.curaccount.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.curaccount.entity.CurAccountlog;
import com.cxdai.console.curaccount.vo.CurAccountLogCnd;
import com.cxdai.console.curaccount.vo.CurAccountLogVo;
import com.cxdai.console.curaccount.vo.CurInOutAccountCnd;
import com.cxdai.console.curaccount.vo.CurInOutAccountVo;

public interface CurAccountlogMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(CurAccountlog record);

	int insertSelective(CurAccountlog record);

	CurAccountlog selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CurAccountlog record);

	int updateByPrimaryKey(CurAccountlog record);

	/**
	 * <p>
	 * Description:活期宝转入转出 count <br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年5月6日
	 * @param curAccountLogCnd
	 * @return Integer
	 */
	public Integer queryCurAccountLogCount(CurInOutAccountCnd curAccountLogCnd);

	/**
	 * <p>
	 * Description: 活期宝转入转出 List<br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年5月6日
	 * @param curAccountLogCnd
	 * @param page
	 * @return List<CurAccountLogVo>
	 */
	public List<CurInOutAccountVo> queryCurAccountLogList(CurInOutAccountCnd curAccountLogCnd, Page page);

	/**
	 * <p>
	 * Description: 不分页 - 转入，转入累计金额<br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年5月20日
	 * @param curAccountLogCnd
	 * @return List<CurAccountLogVo>
	 */
	public List<CurAccountLogVo> queryCurAccountLogSum(CurAccountLogCnd curAccountLogCnd);
	
	/**
	 * 累计转入金额
	 * @author WangQianJin
	 * @title @return
	 * @date 2015年12月24日
	 */
	public BigDecimal queryCurAccountLogSumForIn();
	/**
	 * 累计转出金额
	 * @author WangQianJin
	 * @title @return
	 * @date 2015年12月24日
	 */
	public BigDecimal queryCurAccountLogSumForOut();

	/**
	 * <p>
	 * Description: 统计 - 转入累计金额 <br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年5月29日
	 * @param curAccountLogCnd
	 * @return BigDecimal
	 */
	public BigDecimal querySumMoneyIn(CurAccountLogCnd curAccountLogCnd);

	/**
	 * <p>
	 * Description: 统计 - 转出累计金额 <br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年5月29日
	 * @param curAccountLogCnd
	 * @return BigDecimal
	 */
	public BigDecimal querySumMoneyOut(CurAccountLogCnd curAccountLogCnd);
	/**
	 * 累计转入金额
	 * @author WangQianJin
	 * @title @return
	 * @date 2015年12月24日
	 */
	public BigDecimal queryCurAccountSumForIn(CurInOutAccountCnd curAccountLogCnd);
	/**
	 * 累计转出金额
	 * @author WangQianJin
	 * @title @return
	 * @date 2015年12月24日
	 */
	public BigDecimal queryCurAccountSumForOut(CurInOutAccountCnd curAccountLogCnd);

}