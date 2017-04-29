package com.cxdai.console.transfer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.transfer.vo.TransferApprovedVo;
import com.cxdai.console.transfer.vo.TransferCnd;
import com.cxdai.console.transfer.vo.TransferVo;

public interface TransferApprovedMapper {

	List<TransferVo> queryPageListByCnd(@Param("transferCnd") TransferCnd transferCnd, Page page);

	int queryCountPageListByCnd(@Param("transferCnd") TransferCnd transferCnd);

	void updateStute(@Param("transferApprovedVo") TransferApprovedVo transferApprovedVo);

}
