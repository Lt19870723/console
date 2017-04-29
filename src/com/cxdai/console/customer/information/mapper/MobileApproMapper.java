package com.cxdai.console.customer.information.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.vo.EmailApproVo;
import com.cxdai.console.customer.information.vo.MobileApproCnd;
import com.cxdai.console.customer.information.vo.MobileApproVo;
import com.cxdai.console.customer.information.vo.RealNameApproCnd;



/**
 * <p>
 * Description:手机认证数据访问类<br />
 * </p>
 * 
 * @title MobileApproMapper.java
 * @package com.cxdai.console.member.mapper
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public interface MobileApproMapper {
	/**
	 * <p>
	 * Description:根据条件查询对象集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param mobileApproCnd
	 * @return
	 * @throws Exception List<VIPApproVo>
	 */
	public List<MobileApproVo> queryMobileApproList(MobileApproCnd mobileApproCnd) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询对象集合数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param MobileApproCnd mobileApproCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryMobileApproCount(MobileApproCnd mobileApproCnd) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询未审核的数据<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param MobileApproCnd mobileApproCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer getNoMobileCheckCount(RealNameApproCnd realNameApproCnd) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询未审核的数据<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param MobileApproCnd mobileApproCnd
	 * @return
	 * @throws Exception Integer
	 */
	public List<MobileApproVo> getNoMobileCheckList(RealNameApproCnd realNameApproCnd, Page page) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询未审核的数据<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param MobileApproCnd mobileApproCnd
	 * @return
	 * @throws Exception Integer
	 */
	public void saveNoMobileCheck(MobileApproVo mobileApproVo) throws Exception;

	// 判断是理财者还是投资者
	public int getMobileCheck(MobileApproVo mobileApproVo) throws Exception;

	// /理财者更新member的type 和 app标的passed
	public void updateMobileMember(MobileApproVo mobileApproVo) throws Exception;

	public void updateMobileApper(MobileApproVo mobileApproVo) throws Exception;

	public void updateMobileAppernopass(MobileApproVo mobileApproVo) throws Exception;

	public void updateMobileMembernopass(MobileApproVo mobileApproVo) throws Exception;

	public void updateMobilenew(MobileApproVo mobileApproVo) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询未审核的邮箱数据<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param MobileApproCnd mobileApproCnd
	 * @return
	 * @throws Exception Integer
	 */
	public List<EmailApproVo> getNoEmailCheckList(RealNameApproCnd realNameApproCnd, Page page) throws Exception;

	// 得到分页数据
	public Integer getNoEmailCheckCount(RealNameApproCnd realNameApproCnd) throws Exception;

	// 判断是理财者还是投资者
	public int getEmailCheck(EmailApproVo emailApproVo) throws Exception;

	// 保存数据<br />
	// 投资者数据保存
	public void saveNoEmailCheck(EmailApproVo emailApproVo) throws Exception;

	public void updateEmail(EmailApproVo emailApproVo) throws Exception;

	// /理财者更新member的type 和 app标的checked
	public void updateEmailMember(EmailApproVo emailApproVo) throws Exception;

	public void updateEmailApper(EmailApproVo emailApproVo) throws Exception;

	public void updateEmailApperNoPass(EmailApproVo emailApproVo) throws Exception;

	public void updateEmailMemberNoPass(EmailApproVo emailApproVo) throws Exception;

	public void updateEmailnew(EmailApproVo emailApproVo) throws Exception;

	/**
	 * <p>
	 * Description:验证手机认证数据是否重复<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月30日
	 * @param mobileApproCnd
	 * @return Integer
	 */
	public Integer queryRepeatMobileApproCount(MobileApproCnd mobileApproCnd) throws Exception;

}
