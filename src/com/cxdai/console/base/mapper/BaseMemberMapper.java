package com.cxdai.console.base.mapper;

import com.cxdai.console.base.entity.Member;

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
public interface BaseMemberMapper {
	/**
	 * <p>
	 * Description:插入记录到会员表,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param member
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int insertEntity(Member member) throws Exception;

	/**
	 * <p>
	 * Description:根据id查询会员<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param id
	 * @return
	 * @throws Exception
	 *             Member
	 */
	public Member queryById(Integer id) throws Exception;

	/**
	 * <p>
	 * Description:更新会员<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param member
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int updateEntity(Member member) throws Exception;

}
