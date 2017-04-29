package com.cxdai.console.firstborrow.mapper;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.base.entity.FirstTransferApproved;

public interface FirstTransferApprovedMapper {

	/**
	 * <p>
	 * Description:根据直通车转让ID更新直通车转让审核信息<br />
	 * </p>
	 * @author 朱泳霖
	 * @version 0.1 2015年4月13日
	 * @param firstTransferApproved
	 * void
	 */
	public void updateTransferApproved(@Param("firstTransferApproved") FirstTransferApproved firstTransferApproved) throws Exception;

}
