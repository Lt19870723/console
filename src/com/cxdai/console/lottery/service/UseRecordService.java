package com.cxdai.console.lottery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.lottery.mapper.UseRecordMapper;
import com.cxdai.console.lottery.vo.UseRecord;
import com.cxdai.console.lottery.vo.UseRecordCnd;
import com.cxdai.console.lottery.vo.UseRecordStatic;
import com.cxdai.console.util.DateUtils;

@Service
@Transactional(rollbackFor = Throwable.class)
public class   UseRecordService {

	@Autowired
	UseRecordMapper recordMapper;


	public Page queryPageUseRecordByCnd(UseRecordCnd useRecordCnd, int pageNo, int pageSize) {
		Page  page=new  Page(pageNo, pageSize);
		if(useRecordCnd.getBeginDateStr()!=null){
			useRecordCnd.setBeginDate(DateUtils.parse(useRecordCnd.getBeginDateStr(), DateUtils.YMD_DASH));
		}
		if(useRecordCnd.getEndDateStr()!=null){
			useRecordCnd.setEndDate(DateUtils.parse(useRecordCnd.getEndDateStr()+" 23:59:59" , DateUtils.YMD_DASH));
		}
		page.setResult(recordMapper.queryListUseRecordByCnd(useRecordCnd, page));
		page.setTotalCount(recordMapper.queryCountUseRecordByCnd(useRecordCnd));
		return page;

	}

	
	public UseRecordStatic queryUseRecordStatic(UseRecordCnd useRecordCnd) {
		if(useRecordCnd.getBeginDateStr()!=null){
			useRecordCnd.setBeginDate(DateUtils.parse(useRecordCnd.getBeginDateStr(), DateUtils.YMD_DASH));
		}
		if(useRecordCnd.getEndDateStr()!=null){
			useRecordCnd.setEndDate(DateUtils.parse(useRecordCnd.getEndDateStr()+" 23:59:59" , DateUtils.YMD_DASH));
		}
		return recordMapper.queryUseRecordStatic(useRecordCnd);
	}

	
	public void updateUseRecordByEntry(UseRecord UseRecord) {
		recordMapper.updateByPrimaryKeySelective(UseRecord);
	}

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
	public List<UseRecord> queryListUseRecordForExcelByCnd(UseRecordCnd useRecordCnd) throws Exception {
		if(useRecordCnd.getBeginDateStr()!=null){
			useRecordCnd.setBeginDate(DateUtils.parse(useRecordCnd.getBeginDateStr(), DateUtils.YMD_DASH));
		}
		if(useRecordCnd.getEndDateStr()!=null){
			useRecordCnd.setEndDate(DateUtils.parse(useRecordCnd.getEndDateStr()+" 23:59:59" , DateUtils.YMD_DASH));
		}
		return recordMapper.queryListUseRecordForExcelByCnd(useRecordCnd);
	}

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
	public List<UseRecord> queryListUseRecordForGoodsName() throws Exception {
		return recordMapper.queryListUseRecordForGoodsName();
	}

}
