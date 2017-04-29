package com.cxdai.console.sycee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.sycee.entity.SyceeGoods;
import com.cxdai.console.sycee.entity.SyceeGoodsDiscount;
import com.cxdai.console.sycee.vo.SyceeGoodCnd;

public interface SyceeGoodsMapper {

	int insertSelective(SyceeGoods record);

	int updateByPrimaryKeySelective(SyceeGoods record);

	int countSyceeGoodList(SyceeGoodCnd cnd);

	List<SyceeGoods> selectSyceeGoodList(SyceeGoodCnd cnd, Page page);

	SyceeGoods selectByPrimaryKey(Integer id);

	@Select("SELECT `NAME`<=CURDATE() AND CURDATE()<`VALUE` as discountFlag,name as beginDate,value as endDate from rocky_configuration where TYPE=1398 and `STATUS`=0")
	@ResultType(SyceeGoodsDiscount.class)
	SyceeGoodsDiscount getDiscount();

}
