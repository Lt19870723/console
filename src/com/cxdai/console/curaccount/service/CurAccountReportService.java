package com.cxdai.console.curaccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.curaccount.mapper.CurAccountReportMapper;
import com.cxdai.console.curaccount.vo.CurAccountRepportVo;
import com.cxdai.console.curaccount.vo.CurInterestDetailReportCnd;
import com.cxdai.console.curaccount.vo.CurInterestDetailReportVo;

/**
 * 
 * <p>
 * Description:活期宝对账业务逻辑处理类<br />
 * </p>
 * 
 * @title CurAccountReportServiceImpl.java
 * @package com.cxdai.console.curAccount.service.impl
 * @author YangShiJin
 * @version 0.1 2015年5月27日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CurAccountReportService {

	@Autowired
	private CurAccountReportMapper curAccountReportMapper;

	
	public CurInterestDetailReportVo queryCurInterestReportByDate(CurInterestDetailReportCnd curInterestDetailReportCnd) throws Exception {
		return curAccountReportMapper.queryCurInterestReportByDate(curInterestDetailReportCnd);
	}

	
	public CurAccountRepportVo queryCurAccountTotalReport() throws Exception {
		return curAccountReportMapper.queryCurAccountTotalReport();
	}
}
