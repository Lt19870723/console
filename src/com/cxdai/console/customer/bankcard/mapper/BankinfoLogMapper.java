package com.cxdai.console.customer.bankcard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.bankcard.entity.BankinfoLog;
import com.cxdai.console.customer.bankcard.vo.BankCardLockCnd;
import com.cxdai.console.customer.bankcard.vo.BankCardLockVo;
import com.cxdai.console.customer.bankcard.vo.UserBindBankCnd;



/**
 * 
 * @author zoulixiang、hujianpan
 * @desc 用户银行卡操作日志DAO 
 */
public interface BankinfoLogMapper {

    int deleteByPrimaryKey(Integer id);

	int insert(BankinfoLog record);
	
	int insertBankInfoLog(BankinfoLog record);

    BankinfoLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(BankinfoLog record);
    /**
     * 
     * <p>
     * Description:生成批量日志<br />
     * </p>
     * @author 胡建盼
     * @version 0.1 2014年12月10日
     * @param bankinfoLog
     * @return
     * @throws Exception
     * Integer
     */
    public Integer batchInsert(BankinfoLog bankinfoLog) throws Exception;

    /**
	 * 
	 * <p>
	 * Description:查询银行卡锁定信息列表<br />
	 * </p>
	 * 
	 * @author 陈鹏
	 * @version 0.1 2014年12月18日
	 */
	List<BankCardLockVo> queryPageAllBankCardLockListByCnd(@Param("bankCardLockCnd") BankCardLockCnd bankCardLockCnd, Page page);
	int queryCountPageAllBankCardLockListByCnd(@Param("bankCardLockCnd") BankCardLockCnd bankCardLockCnd);
	
	/**
	 * 
	 * <p>
	 * Description:查询微信绑定信息列表<br />
	 * </p>
	 * 
	 * @author 陈鹏
	 * @version 0.1 2014年12月18日
	 */
	//List<BankCardLockVo> queryWxBindListByCnd(BankCardLockCnd bankCardLockCnd);
	List<BankCardLockVo> queryPageAllWxBindListByCnd(@Param("bankCardLockCnd") BankCardLockCnd bankCardLockCnd, Page page);
	int queryCountPageAllWxBindListByCnd(@Param("bankCardLockCnd") BankCardLockCnd bankCardLockCnd);

	/**
	 * 
	 * <p>
	 * Description:查询总共锁定银行卡的用户总数<br />
	 * </p>
	 * 
	 * @author 陈鹏
	 * @version 0.1 2014年12月18日
	 */
	public int queryBankCardLockAcount();
	
	/**
	 * 
	 * <p>
	 * Description:查询已绑定微信的用户总数<br /> 
	 * </p>
	 * 
	 * @author 陈鹏
	 * @version 0.1 2014年12月18日
	 */
	public int queryWxBindAcount();
	/**
	 * 
	 * <p>
	 * Description:查询已绑定微信的用户总数<br /> 
	 * </p>
	 * 
	 * @author 陈鹏
	 * @version 0.1 2014年12月18日
	 */
	public int queryWxBindAcountByTime(BankCardLockCnd bankCardLockCnd);

	/**
	 * 
	 * <p>
	 * Description:导出excel-银行卡锁定<br /> 
	 * </p>
	 * 
	 * @author 陈鹏
	 * @version 0.1 2014年12月19日
	 */
	List<BankCardLockVo> queryBankCardLockListByCnd(BankCardLockCnd bankCardLockCnd);

	/**
	 * 
	 * <p>
	 * Description:导出excel-微信绑定<br /> 
	 * </p>
	 * 
	 * @author 陈鹏
	 * @version 0.1 2014年12月19日
	 */
	List<BankCardLockVo>  queryPageAllWxBindListByCnd(@Param("bankCardLockCnd") BankCardLockCnd bankCardLockCnd);



    /**
     * 
     * <p>
     * Description:查询用户银行卡操作日志流水<br />
     * </p>
     * @author 胡建盼
     * @version 0.1 2014年12月10日
     * @param userBindBankCnd
     * @return
     * List<BankinfoLog>
     */
    public List<BankinfoLog>queryUserBanksCardOperateLog (UserBindBankCnd userBindBankCnd,Page page);
    /**
     * 
     * <p>
     * Description:统计符合条件的用户银行卡操作日志流水数量<br />
     * </p>
     * @author 胡建盼
     * @version 0.1 2014年12月10日
     * @param userBindBankCnd
     * @return
     * Integer
     */
    public Integer countUserBanksCardOperateLog (UserBindBankCnd userBindBankCnd);
}