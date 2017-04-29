package com.cxdai.console.customer.bankcard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.bankcard.entity.BankcardTimes;
import com.cxdai.console.customer.bankcard.entity.BankcardTimesCnd;

public interface BankcardTimesMapper {
	int pageQueryCount(BankcardTimesCnd changeCnd) throws Exception;

	List<BankcardTimes> pageQuery(BankcardTimesCnd changeCnd, Page page) throws Exception;

	void addTimes(@Param("userId") int userId, @Param("changeTimes") int changeTimes, @Param("applyTimes") int applyTimes, @Param("clickTimes") int clickTimes) throws Exception;

}
