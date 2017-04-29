package com.cxdai.console.customer.information.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.entity.OnlineCounter;
import com.cxdai.console.customer.information.vo.LoginRecordCnd;
import com.cxdai.console.customer.information.vo.OnlineCounterVo;

/**
 * <p>
 * Description:在线人数记录数据访问类<br />
 * </p>
 * 
 * @title OnlineCounterMapper.java
 * @package com.cxdai.console.member.mapper
 * @author justin.xu
 * @version 0.1 2014年11月14日
 */
public interface OnlineCounterMapper {

	public int insert(OnlineCounter onlineCounter) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:分页查询用户登入日志<br />
	 * </p>
	 * 
	 * @author 胡建盼
	 * @version 0.1 2014年12月25日
	 * @return Page
	 */
	public List<OnlineCounterVo> queryMemberLoginRecordWithPage(LoginRecordCnd loginRecordCnd, Page page);

	/**
	 * 
	 * <p>
	 * Description:统计符合条件的记录数量<br />
	 * </p>
	 * 
	 * @author 胡建盼
	 * @version 0.1 2014年12月25日
	 * @param loginRecordCnd
	 * @return Integer
	 */
	public Integer countMemberLoginRecord(LoginRecordCnd loginRecordCnd);
}
