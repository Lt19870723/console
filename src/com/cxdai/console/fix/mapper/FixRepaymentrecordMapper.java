package com.cxdai.console.fix.mapper;

import org.apache.poi.hssf.record.FontRecord;

import com.cxdai.console.fix.entity.FixRepaymentrecord;


public interface FixRepaymentrecordMapper {

	/**
	 * <p>
	 * Description:获取还款总额，还款可用余额，还款实际收益<br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年6月25日
	 * @param fixBorrowId
	 * @return FixRepaymentrecord
	 */
	public FixRepaymentrecord queryFixRepayByConn(FixRepaymentrecord fixRepaymentrecord) throws Exception;

	/**
	 * <p>
	 * Description:根据定期宝id更新定期宝待还=1(已还)<br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年6月25日
	 * @param fixRepaymentrecord
	 * @return
	 * @throws Exception
	 *             int
	 */
	int updateRepaymentById(FixRepaymentrecord fixRepaymentrecord) throws Exception;
	
	
	/**
	 * <p>
	 * Description:判断:还款日期<=now()<br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年7月6日
	 * @param fixRepaymentrecord
	 * @return
	 * @throws Exception
	 * int
	 */
	public int queryRepaymentByFixBorrowId(FixRepaymentrecord fixRepaymentrecord) throws Exception;
	

}