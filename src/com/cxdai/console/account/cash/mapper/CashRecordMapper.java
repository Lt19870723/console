package com.cxdai.console.account.cash.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.account.cash.entity.CashRecord;
import com.cxdai.console.account.cash.vo.CashPayVo;
import com.cxdai.console.account.cash.vo.CashRecordCnd;
import com.cxdai.console.account.cash.vo.CashRecordVo;
import com.cxdai.console.common.page.Page;

/**
 * <p>
 * Description:提现记录数据访问类<br />
 * </p>
 * 
 * @title CashRecordMapper.java
 * @package com.cxdai.console.account.mapper
 * @author justin.xu
 * @version 0.1 2014年6月25日
 */
public interface CashRecordMapper {
	/**
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param cashRecord
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(CashRecord cashRecord) throws Exception;

	/**
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param accountLog
	 * @return
	 * @throws Exception int
	 */
	public int updateEntity(CashRecord cashRecord) throws Exception;

	/**
	 * <p>
	 * Description:根据条件分页查询集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年6月25日
	 * @param cashRecordCnd
	 * @param page
	 * @return
	 * @throws Exception List<CashRecordVo>
	 */
	public List<CashRecordVo> queryCashRecordList(CashRecordCnd cashRecordCnd, Page page) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年6月25日
	 * @param cashRecordCnd
	 * @return
	 * @throws Exception List<CashRecordVo>
	 */
	public List<CashRecordVo> queryCashRecordList(CashRecordCnd cashRecordCnd) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询对象集合数量<<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年6月25日
	 * @param cashRecordCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryCashRecordCount(CashRecordCnd cashRecordCnd) throws Exception;

	/**
	 * <p>
	 * Description:查询某用户最近两次的提现到账记录<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年6月26日
	 * @param memberId
	 * @return
	 * @throws Exception List<CashRecordVo>
	 */
	public List<CashRecordVo> queryCashTwoSuccessListByMemberId(Integer memberId) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询单个充值对象并锁定<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年6月13日
	 * @param cashRecordCnd
	 * @return
	 * @throws Exception cashRecordCnd
	 */
	public CashRecordVo queryCashRecordByIdForUpdate(Integer id) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询单个充值对象<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年6月13日
	 * @param cashRecordCnd
	 * @return
	 * @throws Exception cashRecordCnd
	 */
	public CashRecordVo queryCashRecordById(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:批量导出待打款的记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月25日
	 * @param ids
	 * @return List<CashPayVo>
	 */
	public List<CashPayVo> exportForPayToExcel(@Param("ids") String[] ids, @Param("isExport") String isExport);

	/**
	 * 
	 * <p>
	 * Description:根据条件统计提现金额总计、到账金额总计、手续费总计<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月8日
	 * @param cashRecordCnd
	 * @return
	 * @throws Exception Map<String,Object>
	 */
	public Map<String, Object> queryCashRecorData(CashRecordCnd cashRecordCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据userId查询提现冻结总额<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月27日
	 * @param userId
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryTotalForFreeze(Integer userId) throws Exception;
	
	/**
	 * <p>
	 * Description:批量导出待打款的记录（用于民生银行打款）<br />
	 * </p>
	 * @param ids
	 * @return List<CashPayVo>
	 */
	public List<CashPayVo> exportForPayToExcelToMS(@Param("ids") String[] ids, @Param("isExport") String isExport);
	
	/**
	 * <p>
	 * Description:批量导出待打款的记录（用于网银在线打款）<br />
	 * </p>
	 * @param ids
	 * @return List<CashPayVo>
	 */
	public List<CashPayVo> exportForPayToExcelToWYZX(@Param("ids") String[] ids, @Param("isExport") String isExport);

}
