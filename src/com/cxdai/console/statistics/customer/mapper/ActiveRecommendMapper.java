package com.cxdai.console.statistics.customer.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.customer.vo.InvetedUserVo;
import com.cxdai.console.statistics.customer.vo.RecommendAwardCnd;
import com.cxdai.console.statistics.customer.vo.RecommendUserCountCnd;

public interface ActiveRecommendMapper {

	int queryCountRecommendUserCountList(@Param("recommendUserCountCnd") RecommendUserCountCnd recommendAwardCnd);

	List<InvetedUserVo> queryRecommendUserCountList(@Param("recommendUserCountCnd") RecommendUserCountCnd recommendAwardCnd, Page page);

	int queryCountRecommendAwardList(@Param("recommendAwardCnd") RecommendAwardCnd recommendAwardCnd);

	List<InvetedUserVo> queryRecommendAwardList(@Param("recommendAwardCnd") RecommendAwardCnd recommendAwardCnd, Page page);

	List<InvetedUserVo> queryInviteUserListByUser(@Param("recommendAwardCnd") RecommendAwardCnd recommendAwardCnd, Page page);

	int queryCountInviteUserListByUser(@Param("recommendAwardCnd") RecommendAwardCnd recommendAwardCnd);

	List<InvetedUserVo> queryUsercollectionListByInviteUser(@Param("recommendAwardCnd") RecommendAwardCnd recommendAwardCnd, Page page);

	int queryCountUsercollectionListByInviteUser(@Param("recommendAwardCnd") RecommendAwardCnd recommendAwardCnd);

	InvetedUserVo getRecharge();
	
	InvetedUserVo queryDetailInviteUserById(int id);

}
