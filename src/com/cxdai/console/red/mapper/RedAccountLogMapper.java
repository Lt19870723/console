package com.cxdai.console.red.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.red.entity.RedAccountLog;
import com.cxdai.console.red.vo.RedRecordCnd;



public interface RedAccountLogMapper {

	/**
	 * 红包管理-红包使用记录数
	 * @author liutao
	 * @Date 2015-11-11
	 */
	public Integer queryRedAccountLogVoCount(RedRecordCnd redRecordCnd) throws Exception;
	/**
	 * 红包管理-红包使用记录集合
	 * @author liutao
	 * @Date 2015-11-11
	 */
	public List<RedAccountLog> queryRedAccountLogVoList(RedRecordCnd redRecordCnd, Page page) throws Exception;
	int add(RedAccountLog redAccountLog) throws Exception;

}
