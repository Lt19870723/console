package com.cxdai.console.maintain.portal.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.portal.entity.SensitiveCnd;
import com.cxdai.console.maintain.portal.entity.SensitiveVo;

/**
 * <p>
 * Description:禁用的用户名列表需要实时维护更新<br />
 * </p>
 * 
 * @title MobileApproMapper.java
 * @package com.cxdai.console.member.mapper
 * @author justin.xu
 * @version 0.1 2014年4月25日
 */
public interface SensitiveMapper {
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
	public List<SensitiveVo> querySensitiveList(SensitiveCnd SensitiveCnd, Page page) throws Exception;

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
	public List<SensitiveVo> querySensitiveTypeList() throws Exception;

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
	public int querySensitiveCount(SensitiveCnd SensitiveCnd) throws Exception;

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
	public void deleteSensitive(SensitiveCnd SensitiveCnd) throws Exception;

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
	public void saveSensitive(SensitiveVo sensitiveVo) throws Exception;

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
	public SensitiveVo querySensitiveByid(int ID) throws Exception;

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
	public void updateSensitive(SensitiveVo sensitiveVo) throws Exception;

	/**
	 * <p>
	 * Description:返回是否重复结果集<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param mobileApproCnd
	 * @return
	 * @throws Exception List<VIPApproVo>
	 */
	public int querySensitiveForSave(SensitiveVo sensitiveVo) throws Exception;

}
