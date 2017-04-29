package com.cxdai.console.customer.information.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.vo.MemberAuditVo;
import com.cxdai.console.customer.information.vo.MemberCnd;



/**
 * <p>
 * Description:会员数据访问类<br />
 * </p>
 * 
 * @title MemberMapper.java
 * @package com.base.mapper
 * @author justin.xu
 * @version 0.1 2014年4月16日
 */
public interface MemberAuditMapper {

	/**
	 * <p>
	 * Description:查询用户列表<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2014年11月14日
	 * @param MemberCnd
	 * @return
	 * @throws Exception
	 *             Member
	 */
	public List<MemberAuditVo> queryMemberList(MemberCnd memberCnd,Page page) throws Exception;
	/**
	 * <p>
	 * Description:查询用户列表<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2014年11月14日
	 * @param MemberCnd
	 * @return
	 * @throws Exception
	 *             Member
	 */
	public Integer countMemberList(MemberCnd memberCnd) throws Exception;


}
