package com.cxdai.console.base.mapper;

import com.cxdai.console.customer.information.vo.EmailAppro;

/**
 * <p>
 * Description:邮箱认证数据访问类<br />
 * </p>
 * 
 * @title EmailApproMapper.java
 * @package com.base.mapper
 * @author justin.xu
 * @version 0.1 2014年4月16日
 */
public interface BaseEmailApproMapper {
	/**
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param emailAppro
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int insertEntity(EmailAppro emailAppro) throws Exception;

	/**
	 * <p>
	 * Description:根据id查询<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param id
	 * @return
	 * @throws Exception
	 *             EmailAppro
	 */
	public EmailAppro queryById(Integer id) throws Exception;

	/**
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月15日
	 * @param emailAppro
	 * @return
	 * @throws Exception
	 *             int
	 */
	public int updateEntity(EmailAppro emailAppro) throws Exception;

}
