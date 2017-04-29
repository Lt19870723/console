package com.cxdai.console.borrow.approve.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.base.entity.BorrowAdvanceLog;
import com.cxdai.console.borrow.approve.entity.InvestBorrow;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.approve.vo.CheckBorrowVo;
import com.cxdai.console.borrow.check.vo.BorrowCheckVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.page.Page;

 


/**
 * <p>
 * Description:借款标数据访问类<br />
 * </p>
 * 
 * @title BorrowMapper.java
 * @package com.cxdai.console.borrow.mapper
 * @author justin.xu
 * @version 0.1 2014年8月13日
 */
public interface BorrowMapper {
	/**
	 * <p>
	 * Description:还款引发债权转让撤销<br />
	 * </p>
	 * 
	 * @author chenpeng
	 * @version 0.1 2014年12月22日
	 * @param map
	 * @return
	 */
	public void transferCancelByRepay(Map<String, Object> mapTrans);

	/**
	 * <p>
	 * Description:调用垫付后还款存储过程<br />
	 * </p>
	 * 
	 * @author zhanghao
	 * @version 0.1 2014年6月18日
	 * @param map void
	 */
	public void afterWebpayBorrow(Map map);

	/**
	 * <p>
	 * Description:调用还款存储过程<br />
	 * </p>
	 * 
	 * @author zhanghao
	 * @version 0.1 2014年6月4日
	 * @param map void
	 */
	public void repayBorrow(Map map);

	/**
	 * <p>
	 * Description: 提前还款<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月3日
	 * @param map void
	 */
	public void repayEarlyBorrow(Map map);

	/**
	 * <p>
	 * Description:根据借款标id查询投标总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年9月15日
	 * @param borrowId
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryTenderTotalByBorrowId(Integer borrowId) throws Exception;

	/**
	 * <p>
	 * Description:根据id更新借款标状态和审核状态<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月30日
	 * @param borrowVo
	 * @return
	 * @throws Exception Integer
	 */
	public Integer updateBorrowStatusById(Borrow borrow) throws Exception;

	/**
	 * <p>
	 * Description:根据id查询借款标<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月13日
	 * @param id
	 * @return BorrowVo
	 */
	BorrowVo selectByPrimaryKey(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据id查询借款标<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年8月16日
	 * @param id
	 * @return
	 * @throws Exception BorrowVo
	 */
	BorrowVo selectByPrimaryKeyForUpdate(Integer id) throws Exception;

	/**
	 * <p>
	 * Description:分页查询初审标集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月13日
	 * @param page
	 * @return List<CheckBorrowVo>
	 */
	List<CheckBorrowVo> selectFirstcheckBorrow(BorrowCnd borrowCnd, Page page) throws Exception;

	/**
	 * <p>
	 * Description:查询初审标数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月13日
	 * @return Integer
	 */
	Integer selectFirstcheckBorrowCount(BorrowCnd borrowCnd) throws Exception;

	/**
	 * <p>
	 * Description:分页查询反欺诈标集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月13日
	 * @param page
	 * @return
	 * @throws Exception List<CheckBorrowVo>
	 */
	List<CheckBorrowVo> selectAntiFraudCheckBorrow(BorrowCnd borrowCnd, Page page) throws Exception;

	/**
	 * <p>
	 * Description:查询反欺诈标数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月13日
	 * @return Integer
	 */
	Integer selectAntiFraudCheckBorrowCount(BorrowCnd BorrowCnd) throws Exception;

	/**
	 * <p>
	 * Description:分页查询终审标集合<br />
	 * </p>
	 * s *
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月13日
	 * @param page
	 * @return
	 * @throws Exception List<CheckBorrowVo>
	 */
	List<CheckBorrowVo> selectFinalCheckBorrow(BorrowCnd BorrowCnd, Page page) throws Exception;

	/**
	 * <p>
	 * Description:查询终审标数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月13日
	 * @return Integer
	 */
	Integer selectFinalCheckBorrowCount(BorrowCnd BorrowCnd) throws Exception;

	/**
	 * <p>
	 * Description:分页查询复审标集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月13日
	 * @param page
	 * @return
	 * @throws Exception List<CheckBorrowVo>
	 */
	List<CheckBorrowVo> selectReCheckBorrow(BorrowCnd borrowCnd, Page page) throws Exception;

	/**
	 * <p>
	 * Description:查询复审标数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月13日
	 * @return Integer
	 */
	Integer selectReCheckBorrowCount(BorrowCnd borrowCnd) throws Exception;

	/**
	 * <p>
	 * Description:分页查询所有借款标<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月13日
	 * @param page
	 * @return
	 * @throws Exception List<BorrowVo>
	 */
	List<BorrowVo> selectAllBorrow(Page page) throws Exception;

	/**
	 * <p>
	 * Description:查询所有借款标数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年8月13日
	 * @return Integer
	 */
	Integer selectAllBorrowCount() throws Exception;

	/**
	 * 
	 * <p>
	 * Description:分页查询还款中和还款结束的借款标列表数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年6月13日
	 * @param borrowCnd
	 * @param page
	 * @return
	 * @throws Exception List<BorrowVo>
	 */
	public List<BorrowVo> queryBorrowListForRepay(BorrowCnd borrowCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:还款中和还款结束的借款标列表数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年6月13日
	 * @param borrowCnd
	 * @param page
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryBorrowCountForRepay(BorrowCnd borrowCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:更新Borrow<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年8月16日
	 * @param borrow
	 * @return
	 * @throws Exception int
	 */
	public int updateByPrimaryKey(Borrow borrow) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:调用垫付存储过程 <br />
	 * </p>
	 * 
	 * @author zhanghao
	 * @version 0.1 2014年6月18日
	 * @param map void
	 */
	public void webpayBorrow(Map<String, Object> params) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询需要继续自动投标的借款标列表<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年6月13日
	 * @param borrowCnd
	 * @param page
	 * @return
	 * @throws Exception List<BorrowVo>
	 */
	public List<BorrowVo> queryBorrowListForRestartAutoTender(BorrowCnd borrowCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询需要继续自动投标的借款标列表数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年6月13日
	 * @param borrowCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryBorrowCountForRestartAutoTender(BorrowCnd borrowCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:调用撤标存储过程<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年8月27日
	 * @param map void
	 */
	public void cancelBorrow(Map map);

	/**
	 * 
	 * <p>
	 * Description:查询投标中的借款标<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年8月27日
	 * @param borrowCnd
	 * @param page
	 * @return
	 * @throws Exception List<BorrowVo>
	 */
	public List<BorrowVo> querTenderBorrowList(BorrowCnd borrowCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询投标中的借款标数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年8月27日
	 * @param borrowCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer querTenderBorrowCount(BorrowCnd borrowCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:调用复审存储过程<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月3日
	 * @param params
	 * @throws Exception void
	 */
	public void approveBorrowReCheck(Map<String, Object> params) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询可以手动直通车投标的借款标列表<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年6月13日
	 * @param borrowCnd
	 * @param page
	 * @return
	 * @throws Exception List<BorrowVo>
	 */
	public List<BorrowVo> queryListForHandFirstTender(BorrowCnd borrowCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询可以手动直通车投标的借款标列表 数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年6月13日
	 * @param borrowCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryCountForHandFirstTender(BorrowCnd borrowCnd) throws Exception;

	/**
	 * <p>
	 * Description:操作罚息<br />
	 * </p>
	 * 
	 * @author zhanghao
	 * @version 0.1 2014年7月22日
	 * @param map void
	 */
	public void operatingPenalty(Map map);
	
	/**
	 * <p>
	 * Description:直通车转让撤销储存过程调用<br />
	 * </p>
	 * @author 朱泳霖
	 * @version 0.1 2015年4月2日
	 * @param params
	 * @throws Exception
	 * void
	 */
	public void cancelFirstTransfer(Map<String, Object> params) throws Exception;
	/**
	 * 定期宝查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 陈建国
	 * @version 0.1 2015年6月2日
	 * @param borrowCnd
	 * @param page
	 * @return
	 * @throws Exception
	 *             List<BorrowVo>
	 */
	public List<BorrowVo> queryListForHandFixTender(BorrowCnd borrowCnd, Page page) throws Exception;

	/**
	 * 借款标查询针对定期宝
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 陈建国
	 * @version 0.1 2015年6月2日
	 * @param borrowCnd
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer queryCountForHandFixTender(BorrowCnd borrowCnd) throws Exception;
	/**
	 * 
	 * <p>
	 * Description:根据定时发布时间查询记录数量<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年7月9日
	 * @param date
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public BorrowVo queryBorrowByPublishTime(Date date) throws Exception;
	/**
	 * 
	 * <p>
	 * Description:查询有效借款标数量<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年7月10日
	 * @param borrowCnd
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer selectEffectiveBorrowListCount(BorrowCnd borrowCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询有效借款 集合<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年7月10日
	 * @param borrowCnd
	 * @param page
	 * @return
	 * @throws Exception
	 *             List<BorrowVo>
	 */
	public List<BorrowVo> selectEffectiveBorrowList(BorrowCnd borrowCnd, Page page) throws Exception;
	/**
	 *
	 * <p>
	 * Description:根据合同编号查询<br />
	 * </p>
	 *
	 * @author YangShiJin
	 * @version 0.1 2015年6月3日
	 * @param contractNo
	 * @return Borrow
	 */
	public Borrow queryBorrowByContractNo(String contractNo);

	
	
	/**
	 * 
	 * <p>
	 * Description:根据项目ID查询借款标并锁定<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月30日
	 * @param eProjectId
	 * @return
	 * BorrowVo
	 */
	public BorrowVo selectByProjectIdForUpdate(String eProjectId);
	
	
	public BorrowCheckVo findTenderrecordSum(Integer borrowId);


	/**
	 *
	 * <p>
	 * Description:根据银行项目编号查询借款标信息<br />
	 * </p>
	 */
	public Borrow selectByProjectId(String eProjectId);

	
	
	public Integer updateBorrowAccount(Borrow borrow);
	
	
	/**
	 * 
	 * <p>
	 * Description:计算等额本息每月待收总额<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月31日
	 * @return
	 * BigDecimal
	 */
	public BigDecimal findCollectionAccount(@Param("tenderAccount") BigDecimal tenderAccount,@Param("apr")BigDecimal apr,@Param("timeLimit")Integer timeLimit);
	
	
	public BigDecimal getFqIns(Map<String, Object> map);
	
	public Integer findRepayTime(@Param("timeFlag")Integer timeFlag,@Param("successTime")String successTime);
	
	public Integer findDayRepayTime(@Param("timeFlag")Integer timeFlag,@Param("successTime")String successTime);



    public Borrow queryByProjectId(String projectId);
    
    public Integer updateBorrow(BorrowVo borrowVo);
    
    public Integer insertBorrowAdvanceLog(BorrowAdvanceLog borrowAdvanceLog);

    public Integer updateCGBorrow(BorrowVo borrowVo);
    
    public Integer  updateCGBorrowZS(BorrowVo borrowVo);
    
    public Integer updateBorrowStutusDay(BorrowVo borrowVo);
    
    public Integer updateBorrowStutus(BorrowVo borrowVo);
    
    
    /**
	 * 
	 * <p>
	 * Description:查询投标记录详情<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月25日
	 * @param borrowId
	 * @return
	 * InvestBorrow
	 */
	public List<InvestBorrow> findTenderrecordInfo(Integer borrowId,Page page);
	
	public int findTenderrecordInfoCount(Integer borrowId);
	
	public int updateBorrowAdvance(BorrowVo borrowVo);
}
