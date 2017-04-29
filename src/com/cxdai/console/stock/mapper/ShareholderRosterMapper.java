package com.cxdai.console.stock.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.stock.entity.ShareholderRoster;
import com.cxdai.console.stock.vo.ShareholderCnd;

public interface ShareholderRosterMapper {
    
	/***
	 * 查询股东花名册信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-9
	 * @param apply
	 * @param page
	 * @return
	 * @throws Exception
	 * List<ApplyInfo>
	 */
	public List<ShareholderRoster> queryShareholderForPage(ShareholderCnd shareholderCnd, Page page) throws Exception;

	/****
	 * 统计股东花名册信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-9
	 * @param apply
	 * @return
	 * @throws Exception
	 * Integer
	 */
	public Integer queryShareholderForCounts(ShareholderCnd shareholderCnd) throws Exception;
	
	
	int deleteByPrimaryKey(Integer id);

    int insert(ShareholderRoster record);

    int insertSelective(ShareholderRoster record);

    ShareholderRoster selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShareholderRoster record);

    int updateByPrimaryKey(ShareholderRoster record);
    
    ShareholderRoster findShoreInfo(Integer id);
    
    /**
     * 
     * <p>
     * Description:获取最新股东花名册版本号<br />
     * </p>
     * @author xiaofei.li
     * @version 0.1 2016-6-1
     * @return
     * int
     */
    int queryVersion();
    
    /**
     * 
     * <p>
     * Description:作废股东花名册<br />
     * </p>
     * @author xiaofei.li
     * @version 0.1 2016-6-1
     * @param version
     * void
     */
    void toVoid(int id);
    
    
    List<ShareholderRoster> queryList(int version);
}