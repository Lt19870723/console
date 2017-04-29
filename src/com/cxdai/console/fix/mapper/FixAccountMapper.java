package com.cxdai.console.fix.mapper;

import java.math.BigDecimal;

import com.cxdai.console.fix.entity.FixAccount;
import com.cxdai.console.fix.vo.FixAccountVo;
import com.cxdai.console.fix.vo.FixBorrowCnd;


/**   
 * <p>定期宝数据库访问类
 * Description:这里写描述<br />
 * </p>
 * @title FixBorrowMapper.java
 * @package com.cxdai.console.fix.mapper 
 * @author 陈建国
 * @version 0.1 2015年5月28日
 */
 

public interface FixAccountMapper {
	
	 
	
	/**
	 * <p>新增账户表记录 有关定期宝
	 * Description:这里写描述<br />
	 * </p>
	 * @author 陈建国
	 * @version 0.1 2015年5月28日
	 * @param fixAccountVo
	 * @return
	 * @throws Exception
	 * int
	 */
	public int insertFixAccount(FixAccountVo fixAccountVo) throws Exception;
	
 /**
  * 查询时间段内的利息收入
  * <p>
  * Description:这里写描述<br />
  * </p>
  * @author 陈建国
  * @version 0.1 2015年6月4日
  * @param fixBorrowCnd
  * @return
  * @throws Exception
  * BigDecimal
  */
	public BigDecimal queryProfitByBetweenDate(FixBorrowCnd fixBorrowCnd) throws Exception;

	
	
	 /**
	  * 查询时间段内的支出利息
	  * <p>
	  * Description:这里写描述<br />
	  * </p>
	  * @author 陈建国
	  * @version 0.1 2015年6月4日
	  * @param fixBorrowCnd
	  * @return
	  * @throws Exception
	  * BigDecimal
	  */
		public BigDecimal queryPayMentByBetweenDate(FixBorrowCnd fixBorrowCnd) throws Exception;
	
		
	 /**
	  * 查询时间段内的定期寶的信息
	  * <p>
	  * Description:这里写描述<br />
	  * </p>
	  * @author 陈建国
	  * @version 0.1 2015年6月4日
	  * @param fixBorrowCnd
	  * @return
	  * @throws Exception
	  * BigDecimal
	  */
		public FixAccountVo queryProfitBycontractNo(FixBorrowCnd fixBorrowCnd) throws Exception;
		
	
		 /**
		  * 查询定期宝的可用余额
		  * <p>
		  * Description:这里写描述<br />
		  * </p>
		  * @author 陈建国
		  * @version 0.1 2015年6月4日
		  * @param fixBorrowCnd
		  * @return
		  * @throws Exception
		  * BigDecimal
		  */
		public BigDecimal queryFixUseMoneyTotal() throws Exception;
		/**
		 * <p>
		 * Description:根据定期宝ID查询定期宝账户,并锁定<br />
		 * </p>
		 * 
		 * @author HuangJun
		 * @version 0.1 2015年6月25日
		 * @param fixAccount
		 * @return
		 * @throws Exception
		 *             FixBorrow
		 */
		public FixAccountVo queryFixAccountByIdForUpdate(FixAccount fixAccount) throws Exception;

		/**
		 * <p>
		 * Description: 根据 定期宝ID 更新定期宝账户金额 <br />
		 * </p>
		 * 
		 * @author HuangJun
		 * @version 0.1 2015年6月25日
		 * @param fixAccount
		 * @return
		 * @throws Exception
		 *             int
		 */
		int updateFixAccountById(FixAccount fixAccount) throws Exception;

		/**
		 * <p>
		 * Description:判断定期宝是否有债权<br />
		 * </p>
		 * 
		 * @author HuangJun
		 * @version 0.1 2015年7月6日
		 * @param fixAccount
		 * @return
		 * @throws Exception
		 *             int
		 */
		public int queryCountByFixBorrowId(FixAccount fixAccount) throws Exception;

		/**
		 * <p>
		 * Description:根据定期宝ID查询定期宝账户信息<br />
		 * </p>
		 * 
		 * @author 朱泳霖
		 * @version 0.1 2015年7月27日
		 * @param fixBorrowId
		 * @return
		 * @throws Exception
		 *             FixAccountVo
		 */
		public FixAccountVo queryFixAccountByFixBorrowId(Integer fixBorrowId) throws Exception;

		/**
		 * 
		 * <p>
		 * Description:更新账户信息<br />
		 * </p>
		 * 
		 * @author 朱泳霖
		 * @version 0.1 2015年6月29日
		 * @param fixAccount
		 *            void
		 */
		public void updateFixAccount(FixAccount fixAccount);
		
		/**
		 * <p>
		 * Description:根据定期宝ID查询定期宝账户信息<br />
		 * </p>
		 * 
		 * @author WangQianJin
		 * @version 0.1 2015年12月6日
		 * @param fixBorrowId
		 * @return
		 * @throws Exception
		 *             FixAccountVo
		 */
		public FixAccountVo searchFixAccountByFixBorrowId(Integer fixBorrowId) throws Exception;
}
