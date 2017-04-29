package com.cxdai.console.firstborrow.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.base.entity.FirstBorrow;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.firstborrow.vo.FirstBorrowCnd;
import com.cxdai.console.firstborrow.vo.FirstBorrowVo;

/**
 * <p>
 * Description:直通车数据访问类<br />
 * </p>
 * 
 * @title FirstBorrowMapper.java
 * @package com.cxdai.console.first.mapper
 * @author justin.xu
 * @version 0.1 2014年7月2日
 */
public interface FirstBorrowMapper {
	/**
	 * <p>
	 * Description:根据直通车id，在解锁时更新直通车实际总金额<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月28日
	 * @param unlockaccount 要解锁的金额
	 * @param id
	 * @return
	 * @throws Exception Integer
	 */
	public Integer updateRealAccountByUnlock(@Param("unlockaccount") Integer unlockaccount, @Param("id") Integer id) throws Exception;

	/**
	 * <p>
	 * Description:根据条件分页查询集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月2日
	 * @param firstBorrowCnd
	 * @param page
	 * @return
	 * @throws Exception List<FirstBorrowVo>
	 */
	public List<FirstBorrowVo> queryFirstBorrowList(FirstBorrowCnd firstBorrowCnd, Page page) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月2日
	 * @param firstBorrowCnd
	 * @return
	 * @throws Exception List<FirstBorrowVo>
	 */
	public List<FirstBorrowVo> queryFirstBorrowList(FirstBorrowCnd firstBorrowCnd) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询对象集合数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月2日
	 * @param firstBorrowCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryFirstBorrowCount(FirstBorrowCnd firstBorrowCnd) throws Exception;

	/**
	 * <p>
	 * Description:验证条件添加记录<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月4日
	 * @param firstBorrow
	 * @return
	 * @throws Exception Integer
	 */
	public Integer insertFirstBorrowWidthCondition(FirstBorrow firstBorrow) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询最大期数<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月7日
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryMaxPeriods() throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询最大合同号<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月7日
	 * @return
	 * @throws Exception String
	 */
	public String queryMaxContractNo() throws Exception;

	/**
	 * 
	 * <p>
	 * Description:调用直通车投标存储过程<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年8月12日
	 * @param map void
	 */
	public void firstTender(Map map);

	/**
	 * 
	 * <p>
	 * Description:获取最新直通车信息<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年3月31日
	 * @return FirstBorrowVo
	 */
	public FirstBorrowVo getLatest();

	/**
	 * 
	 * <p>
	 * Description:获取最新直通车信息(用于直通车修改、审核，排除本id的直通车)<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年3月31日
	 * @return FirstBorrowVo
	 */
	public FirstBorrowVo getNewLatestById(int id);
}
