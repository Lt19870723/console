package com.cxdai.console.sycee.mapper;


import org.apache.ibatis.annotations.Param;

import com.cxdai.console.sycee.entity.SyceeAddress;


public interface SyceeAddressMapper {

	/**
	 * 根据userId查询用户是否已经登记过联系方式
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author yuanhaiyang
	 * @version 0.1 2016年4月6日
	 * @param userId
	 * @return SyceeAddress
	 */
	SyceeAddress getById(@Param("user_id") int user_id);
}
