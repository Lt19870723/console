package com.cxdai.console.account.recharge.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.cxdai.console.account.recharge.vo.RechargeRecordCnd;
import com.cxdai.console.account.recharge.vo.RechargeRecordVo;
import com.cxdai.console.common.page.Page;

/**
 * <p>
 * Description:充值记录查询和审核数据访问类<br />
 * </p>
 * 
 * @title RechargeRecordMapper.java
 * @package com.cxdai.console.account.mapper
 * @author justin.xu
 * @version 0.1 2014年6月12日
 */
public interface RechargeRecordMapper {
	/**
	 * <p>
	 * Description:根据条件分页查询集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param rechargeRecordCnd
	 * @return List<RechargeRecordVo>
	 */
	public List<RechargeRecordVo> queryRechargeRecordList(RechargeRecordCnd rechargeRecordCnd, Page page) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param rechargeRecordCnd
	 * @return List<RechargeRecordVo>
	 */
	public List<RechargeRecordVo> queryRechargeRecordList(RechargeRecordCnd rechargeRecordCnd) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询对象集合数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param rechargeRecordCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryRechargeRecordCount(RechargeRecordCnd rechargeRecordCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询充值记录并分页<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年7月10日
	 * @param rechargeRecordCnd
	 * @return
	 * @throws Exception List<RechargeRecordVo>
	 */
	public List<RechargeRecordVo> queryRechargeRecordListForPages(RechargeRecordCnd rechargeRecordCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据条件统计充值总额<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年9月23日
	 * @param rechargeRecordCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryRechargeTotalByCnd(RechargeRecordCnd rechargeRecordCnd) throws Exception;

	/**
	 * <p>
	 * Description:根据条件统计充值到账总额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2015年3月4日
	 * @param rechargeRecordCnd
	 * @return
	 * @throws Exception BigDecimal
	 */
	public BigDecimal queryRealRechargeTotalByCnd(RechargeRecordCnd rechargeRecordCnd) throws Exception;
}
