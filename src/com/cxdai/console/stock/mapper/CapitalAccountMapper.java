package com.cxdai.console.stock.mapper;

import com.cxdai.console.curaccount.entity.CurAccountlog;
import com.cxdai.console.statistics.account.entity.AccountLog;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.stock.vo.AccountLogRequest;
import com.cxdai.console.stock.vo.CapitalAccountCnd;


public interface CapitalAccountMapper {

	Integer saveCurAccountlog(CurAccountlog curLog);
	
	Integer saveAccountlog(AccountLog accountlog);
    
    
    /**
     * 
     * <p>
     * Description:变更资金账户信息<br />
     * </p>
     * @author xiaofei.li
     * @version 0.1 2016-5-19
     * @param account
     * @return
     * Integer
     */
    Integer updateAccount(AccountVo account);
    
    
    /**
     * 
     * <p>
     * Description:添加资金账户变更日志<br />
     * </p>
     * @author xiaofei.li
     * @version 0.1 2016-5-20
     * @param accountLogCnd
     * @return
     * Integer
     */
    Integer saveAccountlog(AccountLogRequest accountLogCnd);
    
    /**
     * 
     * <p>
     * Description:获取资金账户<br />
     * </p>
     * @author xiaofei.li
     * @version 0.1 2016-5-23
     * @param accountCnd
     * @return
     * @throws Exception
     * AccountVo
     */
    AccountVo queryCapitalAccount(CapitalAccountCnd accountCnd) throws Exception;

}