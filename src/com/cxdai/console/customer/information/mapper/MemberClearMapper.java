package com.cxdai.console.customer.information.mapper;

import java.util.List;

import com.cxdai.console.customer.information.vo.MemberClearVo;
import com.cxdai.console.customer.information.vo.MemberCnd;
import com.cxdai.console.customer.information.vo.MemberVo;

/**
 * 
 * <p>
 * Description:客户注销<br />
 * </p>
 * 
 * @title MemberMapper.java
 * @package com.cxdai.console.account.mapper
 * @author yangshijin
 * @version 0.1 2014年7月2日
 */
public interface MemberClearMapper {
	/**
	 * 
	 * <p>
	 * Description:会员表中状态置为账户注销（rocky_member status=-1）<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年7月2日
	 * @param memberCnd
	 * @param page
	 * @return
	 * @throws Exception List<MemberVo>
	 */
	public void updateMemberStatus(MemberClearVo memberClearVo) throws Exception;

	/**
	 * 手机验证表中是否通过验证设置为未通过（rocky_mobile_appro PASSED=-1）
	 */
	public void updateMemberMobileStatus(MemberClearVo memberClearVo) throws Exception;

	/**
	 * 邮箱认证表中是否通过验证设置为未通过（rocky_email_appro CHECKED=-1）
	 */
	public void updateMemberEmailStatus(MemberClearVo memberClearVo) throws Exception;

	/**
	 * 实名认证表中是否通过实名认证审核不通过（rocky_realname_appro ISPASSED=-1)
	 */
	public void updateMemberRealnameStatus(MemberClearVo memberClearVo) throws Exception;

	/**
	 * 、新增一条注销日志记录（增一张注销日志表，主键、注销用户ID,添加人、添加时间、添加IP，添加的mac地址，备注）
	 */
	public void insertMemberClearLog(MemberClearVo memberClearVo) throws Exception;

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
}
