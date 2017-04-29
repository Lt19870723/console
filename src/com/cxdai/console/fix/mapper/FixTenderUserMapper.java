package com.cxdai.console.fix.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.vo.FixTenderUserCnd;
import com.cxdai.console.fix.vo.FixTenderUserVo;

/**
 * <p>
 * Description:定期宝投标日志数据库接口类<br />
 * </p>
 * 
 * @title FixTenderUserMapper.java
 * @package com.cxdai.base.account
 * @author caodefeng
 * @version 0.1 2015年5月23日
 */
public interface FixTenderUserMapper {
	/**
	 * 查询定期宝投标列表
	 * @param fixTenderUserCnd
	 * @param page
	 * @return
	 */
	public List<FixTenderUserVo>	queryFixTenerUserVoList(FixTenderUserCnd fixTenderUserCnd,Page page) throws Exception;

	/**
	 * 查询定期宝投标记录数
	 * @param fixTenderUserCnd
	 * @return
	 */
	public Integer queryFixTenerUserVoCounts(FixTenderUserCnd fixTenderUserCnd) throws Exception;
	/**
	 * 
	 * <p>
	 * Description:根据条件查询定期宝投标列表<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年8月9日
	 * @param fixTenderUserCnd
	 * @return List<FixTenderUserVo>
	 */
	public List<FixTenderUserVo> queryFixTenerUserByCnd(FixTenderUserCnd fixTenderUserCnd);
}
