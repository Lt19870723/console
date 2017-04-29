package com.cxdai.console.firstborrow.mapper;

import java.util.List;

import com.cxdai.console.firstborrow.vo.FirstBorrowApprCnd;
import com.cxdai.console.firstborrow.vo.FirstBorrowApprVo;

/**s
 * <p>
 * Description:优先投标计划审核数据访问类<br />
 * </p>
 * 
 * @title FirstBorrowApprMapper.java
 * @package com.cxdai.console.first.mapper
 * @author justin.xu
 * @version 0.1 2014年7月3日
 */
public interface FirstBorrowApprMapper {

	/**
	 * <p>
	 * Description:根据条件查询集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年7月2日
	 * @param firstBorrowApprCnd
	 * @return
	 * @throws Exception
	 *             List<FirstBorrowVo>
	 */
	public List<FirstBorrowApprVo> queryfirstBorrowApprList(
			FirstBorrowApprCnd firstBorrowApprCnd) throws Exception;

}
