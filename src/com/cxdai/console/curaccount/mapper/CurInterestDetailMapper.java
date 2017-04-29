package com.cxdai.console.curaccount.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.curaccount.entity.CurInterestDetail;
import com.cxdai.console.curaccount.vo.CurAccountCnd;
import com.cxdai.console.curaccount.vo.CurInterestDetailVo;

public interface CurInterestDetailMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(CurInterestDetail record);

	int insertSelective(CurInterestDetail record);

	CurInterestDetail selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CurInterestDetail record);

	int updateByPrimaryKey(CurInterestDetail record);

	/**
	 * <p>
	 * Description:根据userId 查询收益明细count<br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年5月6日
	 * @param curAccountCnd
	 * @return Integer
	 */
	public Integer queryInterestDetailCount(CurAccountCnd curAccountCnd);

	/**
	 * <p>
	 * Description:根据userId 查询收益明细List<br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年5月6日
	 * @param curAccountCnd
	 * @param page
	 * @return List<CurInterestDetailVo>
	 */
	public List<CurInterestDetailVo> queryInterestDetailList(CurAccountCnd curAccountCnd, Page page);

}