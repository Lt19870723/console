package com.cxdai.console.borrow.approve.mapper;

import com.cxdai.console.base.mapper.BaseBorrowerMapper;
import com.cxdai.console.borrow.approve.vo.BorrowerVo;

/**
 * <p>
 * Description:借款者信息<br />
 * </p>
 * 
 * @title BorrowerMapper.java
 * @package com.cxdai.portal.borrow.mapper
 * @author justin.xu
 * @version 0.1 2014年9月13日
 */
public interface BorrowerMapper extends BaseBorrowerMapper {

	/**
	 * <p>
	 * Description: 根据借款标id查询借款者信息<br />
	 * </p>
	 * 
	 * @author chenlu
	 * @version 0.1 2014年8月22日
	 * @param map
	 * @return
	 * @throws Exception
	 *             BorrowerVo
	 */
	public BorrowerVo queryBorrowerByBorrowId(Integer borrowId) throws Exception;

	/**
	 * <p>
	 * Description:查询用户名和生日信息<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年9月16日
	 * @param userId
	 * @return
	 * @throws Exception
	 *             BorrowerVo
	 */
	public BorrowerVo queryBorrowerBaseInfoByBorrowId(Integer userId) throws Exception;

}
