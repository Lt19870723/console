package com.cxdai.console.account.cash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.cash.mapper.DrawLogRecordMapper;
import com.cxdai.console.account.cash.vo.DrawableRecordCnd;
import com.cxdai.console.account.cash.vo.TurnDrawLogVO;
import com.cxdai.console.common.page.Page;

@Service
@Transactional(rollbackFor = Throwable.class)
public class DrawLogRecordService {

	@Autowired
	private DrawLogRecordMapper drawLogRecordMapper;

	public Page queryDrawPageListByCnd(DrawableRecordCnd drawableRecordCnd, Page page) {

		Integer totalCount = drawLogRecordMapper.countDrawLogRecord(drawableRecordCnd);
		page.setTotalCount(totalCount);
		List<TurnDrawLogVO> list = drawLogRecordMapper.queryDrawLogRecordList(drawableRecordCnd, page);
		page.setResult(list);

		return page;
	}

	public List<TurnDrawLogVO> queryDrawListByCnd(DrawableRecordCnd drawableRecordCnd) {

		List<TurnDrawLogVO> list = drawLogRecordMapper.queryOneMonthDrawLogRecordList(drawableRecordCnd);
		return list;
	}

}
