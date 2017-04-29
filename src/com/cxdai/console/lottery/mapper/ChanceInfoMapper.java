package com.cxdai.console.lottery.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.base.mapper.BaseChanceInfoMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.lottery.entity.ChanceInfoSignSet;
import com.cxdai.console.lottery.vo.ChanceInfo;

public interface ChanceInfoMapper extends BaseChanceInfoMapper {

	/**
     * 
     * <p>
     * Description:根据机会来源类型和userId查询记录数量<br />
     * </p>
     * @author YangShiJin
     * @version 0.1 2015年4月7日
     * @param code
     * @param userId
     * @return
     * LotteryChanceInfoVo
     */
    public Integer selectCountByCodeAndUserId(@Param("code") String code, @Param("userId") Integer userId);

	public List<ChanceInfo> queryChanceInfoList(@Param("userName") String userName, Page page);

	public int queryCountChanceInfoList();
	
	@Select("SELECT t.ORDER as awardNum,t.NAME AS beginDate,t.VALUE AS endDate FROM rocky_configuration t WHERE t.TYPE = 1397 AND t.STATUS= 0")
	@ResultType(ChanceInfoSignSet.class)
	ChanceInfoSignSet getChanceInfoSign();
	
	/**
	 * 根据用户ID查询累积抽奖次数
	 * @author wushaoling
	 * @param userId
	 * @return 累积抽奖次数
	 */
	public Integer querySumLotteryNum(@Param("userId") Integer userId);

}