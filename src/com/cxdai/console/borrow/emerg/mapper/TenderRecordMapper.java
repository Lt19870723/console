package com.cxdai.console.borrow.emerg.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.base.entity.BTenderRecord;
import com.cxdai.console.borrow.approve.entity.TenderRecord;
import com.cxdai.console.borrow.emerg.vo.TenderRecordCheck;
import com.cxdai.console.borrow.emerg.vo.TenderRecordCnd;
import com.cxdai.console.borrow.emerg.vo.TenderRecordVo;
import com.cxdai.console.borrow.emerg.vo.UserTenderRecordCnd;
import com.cxdai.console.borrow.emerg.vo.UserTenderRecordVo;
import com.cxdai.console.common.page.Page;

public interface TenderRecordMapper {

	/**
	 * 
	 * <p>
	 * Description:根据borrowId查询统计投标总金额及投标次数<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年8月26日
	 * @param borrowId
	 * @return
	 * @throws Exception Map<String,Object>
	 */
	public Map<String, Object> queryTenderRecordByBorrowId(int borrowId) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据borrowId查询投标记录（导出使用）<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月15日
	 * @param borrowId
	 * @return
	 * @throws Exception List<TenderRecordCheck>
	 */
	public List<TenderRecordCheck> queryTenderRecordForExcelByBorrowId(int borrowId) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据borrowId查询投标记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年11月21日
	 * @param borrowId
	 * @return
	 * @throws Exception List<TenderRecordVo>
	 */
	public List<TenderRecordVo> queryListByBorrowId(Integer borrowId) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询投标记录<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2015年2月5日
	 * @param borrowId
	 * @return
	 * @throws Exception List<TenderRecordVo>
	 */
	public List<TenderRecordVo> queryListByTenderRecordCnd(TenderRecordCnd tenderRecordCnd) throws Exception;

	public BigDecimal queryTotalRepaymentAccount(TenderRecordCnd tenderRecordCnd);

	public List<UserTenderRecordVo> searchUserTenderRecord(UserTenderRecordCnd userTenderRecordCnd, Page page);

	public Integer searchCountUserTenderRecord(UserTenderRecordCnd userTenderRecordCnd);
	
	public Integer insert(BTenderRecord bTenderRecord) throws Exception;
	
	/**
	 * <p>
	 * Description:根据定期宝ID查询投标记录表信息<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年6月28日
	 * @param tenderRecordCnd
	 * @return
	 * @throws Exception
	 *             List<TenderRecordVo>
	 */
	public List<TenderRecordVo> queryFixTenderRecordList(TenderRecordCnd tenderRecordCnd) throws Exception;
	
	TenderRecordVo selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(BTenderRecord record);
	
	
	/**
	 * 
	 * <p>
	 * Description:根据P2P平台流水号查询投标记录(投标冻结流水号)<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月30日
	 * @param bizNo
	 * @return
	 * TenderRecord
	 */
	public TenderRecord findtenderrecordByBizNo(String bizNo);
	
	/**
	 * 
	 * <p>
	 * Description:根据P2P平台流水号查询投标记录(投标流水号)<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年6月16日
	 * @param bizNo
	 * @return
	 * TenderRecord
	 */
	public TenderRecord findtenderrecordByInvestNo(String investNo);
	
	public Integer updateFailureTenderrecord(Integer id);
	
	/**
	 * 
	 * <p>
	 * Description:根据借款标ID查询投标成功的记录并锁定<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月30日
	 * @param borrowId
	 * @return
	 * TenderRecord
	 */
	public List<TenderRecord> findSuccessTenderrecord(Integer borrowId);
	
	/**
	 * 
	 * <p>
	 * Description:根据借款标ID更新投标成功记录的状态<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月30日
	 * @param borrowId
	 * @return
	 * Integer
	 */
	public Integer updateTenderrecordStatus(Integer borrowId);
	
	
	/**
	 * 
	 * <p>
	 * Description:查询投标失败的记录<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月31日
	 * @param borrowId
	 * @return
	 * List<TenderRecord>
	 */
	public List<TenderRecord> findFailureTenderrecord(Integer borrowId);


	public TenderRecord selectById(Integer id);
	
	public Integer insertEmailRecord(Integer borrowid);
	
	/**
	 * 
	 * <p>
	 * Description:等额本息待收总利息<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月24日
	 * @param map
	 * @return
	 * BigDecimal
	 */
	BigDecimal getFqInsTotal(Map<String, Object> map);
	
	
	/**
	 * 
	 * <p>
	 * Description:获取用户等级<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月24日
	 * @param map
	 * @return
	 * Map<String,Object>
	 */
	Map<String, Object> getUserLevelRatio(Map<String, Object> map);
	
	
	/**
	 * 
	 * <p>
	 * Description:添加投标记录<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月24日
	 * @param tenderRecord
	 * @return
	 * int
	 */
	int insertTenderrecord(TenderRecord tenderRecord);
	
	/**
	 * 
	 * <p>
	 * Description:根据标ID查询投标记录并锁定<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月25日
	 * @param borrowId
	 * @return
	 * TenderRecordVo
	 */
	List<TenderRecord> findTenderrecordForUpdate(Integer borrowId);
	
	
	/**
	 * 
	 * <p>
	 * Description:根据银行响应结果更新投标记录<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月25日
	 * @param tenderRecord
	 * @return
	 * int
	 */
	int updateTenderrecordZS(TenderRecord tenderRecord);
	
	
	TenderRecord findlastTenderrecord(@Param("borrowId") Integer borrowId,@Param("userId") Integer userId);
	
	BigDecimal isEffectiveMoney(Map<String, Object> map);
}