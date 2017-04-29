package com.cxdai.console.statistics.customer.mapper;

import java.util.List;

import com.cxdai.console.statistics.customer.vo.ShareholderRankCnd;
import com.cxdai.console.statistics.customer.vo.ShareholderRankVo;

/**
 * 
 * <p>
 * Description:股东加权数据访问类<br />
 * </p>
 * 
 * @title ShareholderRankMapper.java
 * @package com.cxdai.console.account.mapper
 * @author yangshijin
 * @version 0.1 2014年7月4日
 */
public interface ShareholderRankMapper {

	/**
	 * 
	 * <p>
	 * Description:根据条件分页查询集合<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年7月4日
	 * @param shareholderRankCnd
	 * @param page
	 * @return
	 * @throws Exception
	 *             List<ShareholderRankVo>
	 */
	public List<ShareholderRankVo> queryShareholderRankVoForPages(
			ShareholderRankCnd shareholderRankCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据条件查询对象集合数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年7月4日
	 * @param shareholderRankCnd
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer queryShareholderRankVoCount(
			ShareholderRankCnd shareholderRankCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据Id查询股东加权排名记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年7月4日
	 * @param id
	 * @return
	 * @throws Exception
	 *             ShareholderRankVo
	 */
	public ShareholderRankVo queryById(long id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:获取当天最新的插入日期<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年7月11日
	 * @return String
	 */
	public String findMaxAddtime() throws Exception;
}
