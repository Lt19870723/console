package com.cxdai.console.customer.bankcard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.bankcard.entity.BankcardTimesCnd;
import com.cxdai.console.customer.bankcard.entity.BankcardVerification;




/**
 * <p>
 * Description:银行信息数据访问类<br />
 * </p>
 * @title BankInfoMapper.java
 * @package com.cxdai.portal.member.mapper
 * @author justin.xu
 * @version 0.1 2014年5月13日
 */
public interface BankVerificationMapper {
	
	int pageQueryVerifyInfoCount(BankcardTimesCnd changeCnd) throws Exception;

	List<BankcardVerification> pageQueryVerifyInfo(BankcardTimesCnd changeCnd, Page page) throws Exception;
	
	
	@Update("update t_bankcard_verify set delete_flag=-1,remark=#{remark} WHERE ID=#{id}")
	@ResultType(java.lang.Integer.class)
	public Integer delVerify(@Param("id") Integer id,@Param("remark") String remark);
	
	/**
	 * 新增记录
	 * @author WangQianJin
	 * @title @param record
	 * @title @return
	 * @date 2016年6月14日
	 */
	int insert(BankcardVerification record);
	
	/**
	 * 根据用户ID查询今天请求次数
	 * @author WangQianJin
	 * @title @param userId
	 * @title @return
	 * @date 2016年5月19日
	 */
	public Integer queryTodayRequestNumByUid(Integer userId);
	
	/**
	 * 根据用户ID查询所有请求次数
	 * @author WangQianJin
	 * @title @param userId
	 * @title @return
	 * @date 2016年5月19日
	 */
	public Integer queryAllRequestNumByUid(Integer userId);
}
