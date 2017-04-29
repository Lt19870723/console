package com.cxdai.console.customer.information.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.entity.Member;
import com.cxdai.console.customer.information.vo.MemberCnd;
import com.cxdai.console.customer.information.vo.MemberRepeatCnd;
import com.cxdai.console.customer.information.vo.MemberVo;

/**
 * 
 * <p>
 * Description:会员记录数据访问类<br />
 * </p>
 * 
 * @title MemberMapper.java
 * @package com.cxdai.console.account.mapper
 * @author yangshijin
 * @version 0.1 2014年7月2日
 */
public interface MemberMapper {

	/**
	 * <p>
	 * Description:插入记录到会员表,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param member
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(Member member) throws Exception;

    public MemberVo queryMemberByIdForUpdate(Integer userId) throws Exception;

	/**
	 * <p>
	 * Description:根据id查询会员<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param id
	 * @return
	 * @throws Exception Member
	 */
	public Member queryById(Integer id) throws Exception;


    public MemberVo queryUserBaseInfo(Integer userId);

    public int updateMemberForCustody(MemberVo memberVo);

	/**
	 * <p>
	 * Description:更新会员<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param member
	 * @return
	 * @throws Exception int
	 */
	public int updateEntity(Member member) throws Exception;
	/**
	 * 
	 * <p>
	 * Description:根据条件分页查询集合<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年7月2日
	 * @param memberCnd
	 * @param page
	 * @return
	 * @throws Exception List<MemberVo>
	 */
	public List<MemberVo> queryMemberVoList(MemberCnd memberCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据条件查询对象集合数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年7月2日
	 * @param memberCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryMemberVoCount(MemberCnd memberCnd) throws Exception;
	
	/**
	 * 后台-客户信息查询数目
	 * @author WangQianJin
	 * @title @param memberCnd
	 * @title @return
	 * @title @throws Exception
	 * @date 2016年1月26日
	 */
	public Integer queryMemberVoListForCount(MemberCnd memberCnd) throws Exception;	

	/**
	 * 
	 * <p>
	 * Description:后台-客户信息查询(分页)<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年7月17日
	 * @param memberCnd
	 * @return
	 * @throws Exception List<MemberVo>
	 */
	public List<MemberVo> queryMemberVoListForPages(MemberCnd memberCnd) throws Exception;

	/**
	 * <p>
	 * Description:客户信息查询，用于数据导出<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年12月9日
	 * @param memberCnd
	 * @param pageSize
	 * @param curPage
	 * @return List<MemberVo>
	 * @throws Exception
	 */
	public List<MemberVo> queryMemberVoListForExport(MemberCnd memberCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据Id查询会员记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年7月2日
	 * @param id
	 * @return MemberVo
	 */
	public MemberVo queryMemberById(int id) throws Exception;

	/**
	 * <p>
	 * Description:实名认证通过更新论坛角色<br />
	 * </p>
	 * 
	 * @author qiongbiao.zhang
	 * @version 0.1 2014年8月26日
	 * @param userId
	 * @return int
	 */
	public int updateBbsUserGroupForRegistered(Integer userId);

	/**
	 * <p>
	 * Description:实名认证通过更新论坛权限<br />
	 * </p>
	 * 
	 * @author qiongbiao.zhang
	 * @version 0.1 2014年8月26日
	 * @param userId
	 * @return int
	 */
	public int insertBbsUserPermissionForRegistered(Integer userId);

	public Integer getMemberIdByUserName(String userName);

	/**
	 * <p>
	 * Description:验证重复数据<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param memberRepeatCnd
	 * @return Integer
	 * @throws Exception
	 */
	public Integer queryRepeatMemberCount(MemberRepeatCnd memberRepeatCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据用户名查询记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年1月27日
	 * @param username
	 * @return
	 * @throws Exception MemberVo
	 */
	public MemberVo queryMemberVoByUsername(String username) throws Exception;

	/**
	 * <p>
	 * Description:更新所有借款用户密码<br />
	 * </p>
	 * 
	 * @author hujianpan
	 * @version 0.1 2015年2月27日
	 * @throws Exception void
	 */
	public void updateBorrowMemberPassword(@Param("password") String password) throws Exception;

	public void updateLoginPwd(@Param("username") String username, @Param("logpwd") String logpwd);

	public void updateDealPwd(@Param("username") String username, @Param("dealpwd") String dealpwd);
	/**
	 * <p>
	 * Description:新注册用户初始化论坛角色<br />
	 * </p>
	 * 
	 * @author qiongbiao.zhang
	 * @version 0.1 2014年8月26日
	 * @param userId
	 * @return int
	 */
	public int insertBbsUserGroupForUncertified(Integer userId);
	
	/**
	 * 分页查询客户信息
	 * @author WangQianJin
	 * @title @param memberCnd
	 * @title @param page
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月17日
	 */
	public List<MemberVo> getPageMemberVoList(MemberCnd memberCnd, Page page) throws Exception;

	/**
	 * 分页查询客户数量
	 * @author WangQianJin
	 * @title @param memberCnd
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月17日
	 */
	public Integer getPageMemberVoCount(MemberCnd memberCnd) throws Exception;
	@Select("SELECT USERNAME FROM rocky_member WHERE ID=#{userId}")
	@ResultType(java.lang.String.class)
	public String selectUserNameById(@Param("userId") Integer userId);
	
	/****
	 * 更加客户id查询客户信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-10
	 * @param username
	 * @return
	 * @throws Exception
	 * MemberVo
	 */
	public MemberVo queryMemberVoById(int id) throws Exception;
	
	
	public MemberVo queryPasswordInfoById(int userId);
	

}
