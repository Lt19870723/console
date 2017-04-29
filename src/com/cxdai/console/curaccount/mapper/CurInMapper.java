package com.cxdai.console.curaccount.mapper;

import java.util.List;
import java.util.Map;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.curaccount.vo.CurInCnd;
import com.cxdai.console.curaccount.vo.CurInVo;

public interface CurInMapper {

	CurInVo selectByPrimaryKey(Integer id);

	/**
	 * 
	 * <p>
	 * Description:查询开始产生收益日期错误的转入记录<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年5月27日
	 * @return
	 * @throws Exception
	 *             CurIn
	 */
	public List<CurInVo> queryCurInListForCalInterestDayError(CurInCnd curInCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询开始产生收益日期错误的转入记录数量<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年5月27日
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer queryCurInListForCalInterestDayErrorCount(CurInCnd curInCnd) throws Exception;
	
	/**
	 * 调用活期宝转入存储过程
	 * @author WangQianJin
	 * @title @param params
	 * @date 2015年8月27日
	 */
	public void saveCurrentInForTenderBack(Map<String, Object> params) throws Exception;
	
}