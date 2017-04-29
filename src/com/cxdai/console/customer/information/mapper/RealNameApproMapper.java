package com.cxdai.console.customer.information.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.entity.RealNameAppro;
import com.cxdai.console.customer.information.vo.RealNameApproCnd;
import com.cxdai.console.customer.information.vo.RealNameApproVo;



/**
 * <p>
 * Description:实名认证数据访问类<br />
 * </p>
 * 
 * @title RealNameApproMapper.java
 * @package com.cxdai.portal.member.mapper
 * @author justin.xu
 * @version 0.1 2014年4月24日
 */
public interface RealNameApproMapper {

	/**
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param realNameAppro
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(RealNameAppro realNameAppro) throws Exception;

	/**
	 * <p>
	 * Description:根据id查询<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param id
	 * @return
	 * @throws Exception RealNameAppro
	 */
	public RealNameAppro queryById(Integer id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据user_id查询<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2014年8月18日
	 * @param id
	 * @return
	 * @throws Exception RealNameAppro
	 */
	public RealNameAppro queryByUserId(Integer id) throws Exception;

	/**
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param realNameAppro
	 * @return
	 * @throws Exception int
	 */
	public int updateEntity(RealNameAppro realNameAppro) throws Exception;

	/**
	 * <p>
	 * Description:根据条件分页查询集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param realNameApproCnd
	 * @return List<EmailApproVo>
	 */
	public List<RealNameApproVo> queryRealNameApproList(RealNameApproCnd realNameApproCnd, Page page) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param realNameApproCnd
	 * @return List<EmailApproVo>
	 */
	public List<RealNameApproVo> queryRealNameApproList(RealNameApproCnd realNameApproCnd) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询对象集合数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param realNameApproCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryRealNameApproCount(RealNameApproCnd realNameApproCnd) throws Exception;

	public RealNameApproVo getRealNameApproByUserName(String userName);

	/**
	 * <p>
	 * Description:验证重复数据<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param memberRegisterCnd
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryRepeatRealNameApproCount(RealNameApproCnd realNameApproCnd) throws Exception;

}
