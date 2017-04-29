package com.cxdai.console.account.cash.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cxdai.console.account.cash.vo.DrawableRecordCnd;
import com.cxdai.console.account.cash.vo.TurnDrawLogVO;
import com.cxdai.console.common.page.Page;

/**
 * <p>
 * Description:账户资金记录查询<br />
 * </p>
 * 
 * @title AccOperatingRecordMapper.java
 * @package com.cxdai.portal.account.mapper
 * @author 胡建盼
 * @version 0.1 2014年8月5日
 */
@Repository
public interface DrawLogRecordMapper {

	public Integer countDrawLogRecord(DrawableRecordCnd drawableRecordCnd);

	public List<TurnDrawLogVO> queryDrawLogRecordList(DrawableRecordCnd drawableRecordCnd, Page page);

	public List<TurnDrawLogVO> queryOneMonthDrawLogRecordList(DrawableRecordCnd drawableRecordCnd);
}
