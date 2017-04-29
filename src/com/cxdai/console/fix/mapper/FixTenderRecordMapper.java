package com.cxdai.console.fix.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.vo.TenderRecordCnd;
import com.cxdai.console.fix.vo.TenderRecordVo;

/**
 * <p>
 * Description:定期宝投标日志数据库接口类<br />
 * </p>
 * 
 * @title FixTenderRecordMapper.java
 * @package com.cxdai.console.fix.mapper
 * @author caodefeng
 * @version 0.1 2015年6月8日
 */
public interface FixTenderRecordMapper {
	/**
	 * 查询定期宝投标列表
	 * @param tenderRecordCnd
	 * @param page
	 * @return
	 */
	public List<TenderRecordVo>	queryTenderRecordVoList(TenderRecordCnd tenderRecordCnd,Page page) throws Exception;

	/**
	 * 查询定期宝投标记录数
	 * @param fixTenderUserCnd
	 * @return
	 */
	public Integer queryTenderRecordVoCounts(TenderRecordCnd tenderRecordCnd) throws Exception;
}