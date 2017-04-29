package com.cxdai.console.stock.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.stock.entity.ApplyInfo;
import com.cxdai.console.stock.vo.ApplyInfoCnd;

public interface ApplyInfoMapper {
	
	/***
	 * 查询提交审核信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-9
	 * @param apply
	 * @param page
	 * @return
	 * @throws Exception
	 * List<ApplyInfo>
	 */
	public List<ApplyInfo> queryApplyForPage(ApplyInfoCnd applyCnd, Page page) throws Exception;

	/****
	 * 统计提交审核信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-9
	 * @param apply
	 * @return
	 * @throws Exception
	 * Integer
	 */
	public Integer queryApplyForCounts(ApplyInfoCnd applyCnd) throws Exception;
	
	
	/***
	 * 查询散标待收
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-10
	 * @param userId
	 * @return
	 * BigDecimal
	 */
	public BigDecimal queryCollectionrecord(int userId);
	/****
	 * 查询定期宝待收
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-10
	 * @param userId
	 * @return
	 * BigDecimal
	 */
	public BigDecimal queryFixCollectionrecord(int userId);
	
	/***
	 * 存管待收
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-6-20
	 * @param userId
	 * @return
	 * BigDecimal
	 */
	public BigDecimal queryCunGuanCollectionrecord(int userId);
	
	
	public void updateStatus(@Param("status") int status, @Param("id") int id, @Param("updateuser") int updateuser,
			@Param("updateIp") String updateIp);

	
	
    int deleteByPrimaryKey(Integer id);

    int insert(ApplyInfo record);

    int insertSelective(ApplyInfo record);

    ApplyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplyInfo record);

    int updateByPrimaryKey(ApplyInfo record);
    
    /**
     * 
     * <p>
     * Description:作废退出股东申请<br />
     * </p>
     * @author xiaofei.li
     * @version 0.1 2016-6-1
     * void
     */
    void toVoidApply();
}