package com.cxdai.console.customer.information.mapper;

import java.util.List;

import com.cxdai.console.customer.information.entity.Integral;
import com.cxdai.console.customer.information.vo.IntegralCnd;
import com.cxdai.console.customer.information.vo.IntegralVo;



/**
 * <p>
 * Description:积分等级数据访问类<br />
 * </p>
 * 
 * @title IntegralMapper.java
 * @package com.cxdai.console.member.mapper
 * @author justin.xu
 * @version 0.1 2014年5月6日
 */
public interface IntegralMapper {
	/**
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param integral
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(Integral integral) throws Exception;

	/**
	 * <p>
	 * Description:根据id查询<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param id
	 * @return
	 * @throws Exception Integral
	 */
	public Integral queryById(Integer id) throws Exception;

	/**
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param integral
	 * @return
	 * @throws Exception int
	 */
	public int updateEntity(Integral integral) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询对象集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param mobileApproCnd
	 * @return
	 * @throws Exception List<IntegralVo>
	 */
	public List<IntegralVo> queryIntegralList(IntegralCnd integralCnd)
			throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询对象集合数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param IntegralCnd
	 *            integralCnd
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer queryIntegralCount(IntegralCnd integralCnd) throws Exception;
}
