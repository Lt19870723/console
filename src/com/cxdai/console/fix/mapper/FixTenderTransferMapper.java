package com.cxdai.console.fix.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.cxdai.console.fix.entity.FixTenderTransfer;
import com.cxdai.console.fix.vo.FixTenderTransferCnd;
import com.cxdai.console.fix.vo.FixTenderTransferVo;

public interface FixTenderTransferMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FixTenderTransfer record);

    int insertSelective(FixTenderTransfer record);

    FixTenderTransfer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FixTenderTransfer record);

    int updateByPrimaryKey(FixTenderTransfer record);
    /**
	 * 
	 * <p>
	 * Description:根据定期宝转让ID查询定期宝投标转让表信息<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年6月29日
	 * @param fixTenderTransferCnd
	 * @return List<FixTenderTransferVo>
	 */
	public List<FixTenderTransferVo> queryFixTenderTransferList(FixTenderTransferCnd fixTenderTransferCnd);
	
	/**
	 * 
	 * <p>
	 * Description:根据定期宝转让ID查询待收总额<br />
	 * </p>
	 * @author 朱泳霖
	 * @version 0.1 2015年7月2日
	 * @param fixBorrowTransferId
	 * @return
	 * BigDecimal
	 */
	public BigDecimal queryRepaymentAccountSumByTransferId(Integer fixBorrowTransferId);
	
	/**
	 * 
	 * <p>
	 * Description:根据转让ID修改标转让记录的状态<br />
	 * </p>
	 * @author 朱泳霖
	 * @version 0.1 2015年8月3日
	 * @param record
	 * @return
	 * int
	 */
	int updateByTransferId(FixTenderTransfer record);
}