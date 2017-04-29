package com.cxdai.console.curaccount.mapper;

import java.util.List;

import com.cxdai.console.curaccount.vo.CurAccountRepportVo;
import com.cxdai.console.curaccount.vo.CurInterestDetailErrorVo;
import com.cxdai.console.curaccount.vo.CurInterestDetailReportCnd;
import com.cxdai.console.curaccount.vo.CurInterestDetailReportVo;

/**
 * 
 * <p>
 * Description:活期宝报表数据访问接口<br />
 * </p>
 * 
 * @title CurAccountReportMapper.java
 * @package com.cxdai.console.curaccount.mapper
 * @author YangShiJin
 * @version 0.1 2015年5月27日
 */
public interface CurAccountReportMapper {

	/**
	 * 
	 * <p>
	 * Description:查询某天收益对账统计信息<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年5月27日
	 * @param curInterestDetailReportCnd
	 * @return
	 * @throws Exception
	 *             CurInterestDetailReportVo
	 */
	public CurInterestDetailReportVo queryCurInterestReportByDate(CurInterestDetailReportCnd curInterestDetailReportCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询某天内未生成收益明细记录的用户<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年5月27日
	 * @param curInterestDetailReportCnd
	 * @return
	 * @throws Exception
	 *             List<CurInterestDetailErrrorVo>
	 */
	public List<CurInterestDetailErrorVo> queryNotDetailForUserNameByDate(CurInterestDetailReportCnd curInterestDetailReportCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询某天内未生成活期生息（收益入账）资金明细记录的用户<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年5月27日
	 * @param curInterestDetailReportCnd
	 * @return
	 * @throws Exception
	 *             List<CurInterestDetailErrrorVo>
	 */
	public List<CurInterestDetailErrorVo> queryNotAccountLogForUserNameByDate(CurInterestDetailReportCnd curInterestDetailReportCnd)
			throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询某天内重复生成收益明细记录的用户<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年5月27日
	 * @param curInterestDetailReportCnd
	 * @return
	 * @throws Exception
	 *             List<CurInterestDetailErrrorVo>
	 */
	public List<CurInterestDetailErrorVo> queryRepeatDetailForUserNameByDate(CurInterestDetailReportCnd curInterestDetailReportCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询某天内重复生成活期生息资金明细记录的用户<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年5月27日
	 * @param curInterestDetailReportCnd
	 * @return
	 * @throws Exception
	 *             List<CurInterestDetailErrrorVo>
	 */
	public List<CurInterestDetailErrorVo> queryRepeatAccountLogForUserNameByDate(CurInterestDetailReportCnd curInterestDetailReportCnd)
			throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询某天内收益入账有误的用户<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年5月27日
	 * @param curInterestDetailReportCnd
	 * @return
	 * @throws Exception
	 *             List<CurInterestDetailErrrorVo>
	 */
	public List<CurInterestDetailErrorVo> queryEnterMoneyErrorForUserNameByDate(CurInterestDetailReportCnd curInterestDetailReportCnd)
			throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询活期宝总金额账户对账统计信息<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年5月27日
	 * @return
	 * @throws Exception
	 *             CurAccountRepportVo
	 */
	public CurAccountRepportVo queryCurAccountTotalReport() throws Exception;
}
