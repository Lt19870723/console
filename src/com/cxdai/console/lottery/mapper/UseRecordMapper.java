package com.cxdai.console.lottery.mapper;

import java.util.List;

import com.cxdai.console.base.mapper.BaseUseRecordMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.lottery.vo.UseRecord;
import com.cxdai.console.lottery.vo.UseRecordCnd;
import com.cxdai.console.lottery.vo.UseRecordStatic;

public interface UseRecordMapper extends BaseUseRecordMapper {

	List<UseRecord> queryListUseRecordByCnd(UseRecordCnd useRecordCnd, Page page);

	int queryCountUseRecordByCnd(UseRecordCnd useRecordCnd);

	UseRecordStatic queryUseRecordStatic(UseRecordCnd useRecordCnd);

	/**
	 * 
	 * <p>
	 * Description:查询抽奖信息导出Excel<br />
	 * </p>
	 * @author YangShiJin
	 * @version 0.1 2015年4月28日
	 * @param useRecordCnd
	 * @return
	 * List<UseRecord>
	 */
	List<UseRecord> queryListUseRecordForExcelByCnd(UseRecordCnd useRecordCnd) throws Exception;
	
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author YangShiJin
	 * @version 0.1 2015年5月5日
	 * @return
	 * @throws Exception
	 * List<UseRecord>
	 */
	List<UseRecord> queryListUseRecordForGoodsName() throws Exception;
}