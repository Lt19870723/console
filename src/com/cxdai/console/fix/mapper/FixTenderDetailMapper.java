package com.cxdai.console.fix.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.entity.FixTenderDetail;
import com.cxdai.console.fix.vo.FixTenderDetailCnd;
import com.cxdai.console.fix.vo.FixTenderDetailVo;


/**
 * <p>
 * Description:定期宝认购明细数据库接口类<br />
 * </p>
 * 
 * @title FixTenderDetailMapper.java
 * @package com.cxdai.base.account
 * @author caodefeng
 * @version 0.1 2015年5月23日
 */
public interface FixTenderDetailMapper {
	/**
	 * 查询定期宝加入用户列表
	 * @param fixTenderDetailCnd
	 * @param page
	 * @return
	 */
	public  List<FixTenderDetailVo> queryTotalForJoinFixBorrow(FixTenderDetailCnd fixTenderDetailCnd,Page page) throws Exception;
	
	/**
	 * 查询定期宝加入用户记录数
	 * @param fixTenderDetailCnd
	 * @return
	 */
	public Integer queryTotalForJoinFixBorrowCounts(FixTenderDetailCnd fixTenderDetailCnd) throws Exception;
	
	/**
	 * 根据定期宝编号查询某个定期宝加入情况
	 * @param contractNo
	 * @return
	 */
	public FixTenderDetailVo queryTotalForJoinFixBorrowSingle(String contractNo) throws Exception;
	/**
	 * <p>
	 * Description: 根据定期宝ID，更新定期宝认购明细status<br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年6月29日
	 * @param fixTenderDetail
	 * @return
	 * @throws Exception
	 * int
	 */
	int updateFixFenderDetailByConn(FixTenderDetail fixTenderDetail) throws Exception;
	
	/**
	 * 根据定期宝ID获取认购明细
	 * @author WangQianJin
	 * @title @param fixBorrowId
	 * @title @return
	 * @date 2015年9月8日
	 */
	public List<FixTenderDetailVo> queryFixFenderDetailListByFixBorrowId(Integer fixBorrowId) throws Exception;
}
