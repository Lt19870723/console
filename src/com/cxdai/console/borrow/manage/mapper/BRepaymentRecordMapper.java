package com.cxdai.console.borrow.manage.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.cxdai.console.borrow.manage.vo.*;
import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;

 	

/**
 * <p>
 * Description:待还记录访问类<br />
 * </p>
 * 
 * @title BRepaymentRecordMapper.java
 * @package com.cxdai.console.borrow.mapper
 * @author justin.xu
 * @version 0.1 2014年8月13日
 */
public interface BRepaymentRecordMapper {
	/**
	 * <p>
	 * Description: 查询本期还款期数之前未还记录数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月27日
	 * @param borrowId
	 * @param order
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer queryBeforeOrderUnPayCount(@Param("borrowId") Integer borrowId, @Param("order") Integer order) throws Exception;

	/**
	 * <p>
	 * Description:根据借款标id获得待还记录集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月3日
	 * @param borrowId
	 * @return
	 * @throws Exception
	 *             List<BRepaymentRecordVo>
	 */
	public List<BRepaymentRecordVo> queryBRepaymentRecordByBorrowId(Integer borrowId) throws Exception;

	/**
	 * <p>
	 * Description:根据借款标id查询待还集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月13日
	 * @param borrowId
	 * @param page
	 * @return
	 * @throws Exception
	 *             List<BRepaymentRecordVo>
	 */
	public List<BRepaymentRecordVo> queryRepaymentByBorrowId(@Param("borrowId") Integer borrowId, Page page) throws Exception;

	/**
	 * <p>
	 * Description:根据借款标id查询待还集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月13日
	 * @param borrowId
	 * @param page
	 * @return
	 * @throws Exception
	 *             List<BRepaymentRecordVo>
	 */
	public List<BRepaymentRecordVo> queryRepaymentByBorrowId(Integer borrowId) throws Exception;

	/**
	 * <p>
	 * Description:根据借款标id查询待还数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月13日
	 * @return Integer
	 */
	Integer queryRepaymentByBorrowIdCount(Integer borrowId) throws Exception;

	/**
	 * <p>
	 * Description:根据id查找待还记录并锁定 <br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月18日
	 * @param id
	 * @return
	 * @throws Exception
	 *             BRepaymentRecordVo
	 */
	public BRepaymentRecordVo queryRepaymentByIdForUpdate(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:后台操作还款的借款标记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月12日
	 * @param page
	 * @return
	 * @throws Exception
	 *             List<BRepaymentRecordVo>
	 */
	List<BRepaymentRecordVo> selectRepayingBorrow(BorrowCnd borrowCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:后台操作还款的借款标记录数量<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年5月22日
	 * @param borrowCnd
	 * @param page
	 * @return
	 * @throws Exception
	 *             List<BRepaymentRecordVo>
	 */
	List<BRepaymentRecordVo> selectRepayedBorrow(BorrowCnd borrowCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询还款中的借款标数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月12日
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	Integer selectRepayingBorrowCount(BorrowCnd borrowCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:统计符合条件的还款中的借款标的【待还总额】<br />
	 * </p>
	 * 
	 * @author 胡建盼
	 * @version 0.1 2014年12月26日
	 * @param borrowCnd
	 * @return Integer
	 */
	BigDecimal sumWaitRepayMoney(BorrowCnd borrowCnd);

	/**
	 * 
	 * <p>
	 * Description:查询需要补罚息的记录集合<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月12日
	 * @param page
	 * @return
	 * @throws Exception
	 *             List<BRepaymentRecordVo>
	 */
	List<BRepaymentRecordVo> selectRepairInterestList(BorrowCnd borrowCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询需要补罚息的记录数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月12日
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	Integer selectRepairInterestCount(BorrowCnd borrowCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询官方标还款记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月16日
	 * @param bRepaymentRecordCnd
	 * @param page
	 * @return
	 * @throws Exception
	 *             List<BRepaymentRecordVo>
	 */
	public List<BRepaymentRecordVo> selectRepaymentList(BRepaymentRecordCnd bRepaymentRecordCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询官方标还款记录数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月16日
	 * @param bRepaymentRecordCnd
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer selectRepaymentListCount(BRepaymentRecordCnd bRepaymentRecordCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询官方标还款记录导出Excel<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月16日
	 * @param bRepaymentRecordCnd
	 * @return
	 * @throws Exception
	 *             List<BRepaymentRecordCheck>
	 */
	public List<BRepaymentRecordCheck> selectRepaymentListToExcel(BRepaymentRecordCnd bRepaymentRecordCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询某标某期之前未还款的记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年12月20日
	 * @param order
	 * @param borrowId
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer selectAdvaceRepair(@Param("order") Integer order, @Param("borrowId") Integer borrowId) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据userId查询待还总额<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年12月24日
	 * @param userId
	 * @return
	 * @throws Exception
	 *             BigDecimal
	 */
	public BigDecimal queryNoRepayTotal(Integer userId) throws Exception;

	/**
	 * <p>
	 * Description:根据id获得待还记录并锁定<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月3日
	 * @param id
	 * @return
	 * @throws Exception
	 *             BRepaymentRecordVo
	 */
	public BRepaymentRecordVo queryBRepaymentRecordByIdForupdate(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:更新还款记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月12日
	 * @param bRepaymentRecord
	 * @return int
	 */
	public int updateBReaymentRecordById(BRepaymentRecord bRepaymentRecord) throws Exception;

	public int selectCountRepayedBorrow(BorrowCnd borrowCnd);
	
	public void updateByResponse(RepayResBiz repayResBiz);

	/**
	 * 根据借款标项目ID查找银行还款中的待还记录并锁定
	 * @param eProjectId
	 * @return
	 * @throws Exception
     */
	public BRepaymentRecordVo queryRepaymentByProjectId(String eProjectId) throws Exception;


	public void updateRepayRecord(Map map) throws Exception;

	/**
	 * 查询垫付未还款记录数
	 * @param borrowId
	 * @return
	 * @throws Exception
	 */
	public Integer selectDFWHCount(@Param("borrowId") Integer borrowId)throws Exception;


	/**
	 * 查询上一期还款记录
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public BRepaymentRecordVo selectPrevRepayMent(Map map) throws Exception;

	/**
	 * 根据id获得待还
	 * @param id
	 * @return
	 * @throws Exception
     */
	public BRepaymentRecordVo queryBRepaymentRecordById(Integer id) throws Exception;
	
	public Integer insertRepaymentrecord(BRepaymentRecordVo bRepaymentRecordVo);


	public Integer selectRepayFailCount(BorrowCnd borrowCnd) throws Exception;

	public List<BRepaymentRecordVo> selectRepayFail(BorrowCnd borrowCnd, Page page) throws Exception;


	public BRepaymentRecordVo queryRepaymentById(Integer id) throws Exception;


	/**
	 * <p>
	 * Description:查询已垫付未还款记录总数<br />
	 * </p>
	 *
	 * @author justin.xu
	 * @version 0.1 2014年8月13日
	 * @return Integer
	 */
	public Integer queryWebpayCount(Integer borrowId) throws Exception;

	/**
	 * 查询应还总额
	 * @param borrowId
	 * @return
	 */
	public BigDecimal queryRepayTotalByBorrowId(@Param("borrowId") Integer borrowId);

	/**
	 * 查询当前所在期数
	 * @param borrowId
	 * @return
     */
	public OrderRangeVo getCurrentOrder(@Param("borrowId") Integer borrowId);


	/**
	 * 查询上一期还款记录
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public BRepaymentRecordVo selectNextRepayMent(Map map) throws Exception;


}
