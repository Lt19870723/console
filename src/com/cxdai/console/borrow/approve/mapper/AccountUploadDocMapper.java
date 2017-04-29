package com.cxdai.console.borrow.approve.mapper;

import java.util.List;

import com.cxdai.console.base.entity.AccountUploadDoc;
import com.cxdai.console.borrow.emerg.vo.AccountUploadDocCnd;
import com.cxdai.console.borrow.emerg.vo.AccountUploadDocVo;

/**
 * <p>
 * Description:上传资料数据访问类<br />
 * </p>
 * 
 * @title AccountUploadDocMapper.java
 * @package com.cxdai.portal.account.mapper
 * @author justin.xu
 * @version 0.1 2014年5月14日
 */
public interface AccountUploadDocMapper {
	/**
	 * <p>
	 * Description:根据条件查询对象集合<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param accountUploadDocCnd
	 * @return
	 * @throws Exception
	 *             List<AccountUploadDocVo>
	 */
	public List<AccountUploadDocVo> queryAccountUploadDocList(AccountUploadDocCnd accountUploadDocCnd) throws Exception;

	/**
	 * <p>
	 * Description:根据条件查询对象集合数量<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年4月23日
	 * @param AccountUploadDocCnd
	 *            accountUploadDocCnd
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer queryAccountUploadDocCount(AccountUploadDocCnd accountUploadDocCnd) throws Exception;

	/**
	 * 添加上传资料
	 * <p>
	 * Description:添加上传资料<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2014年8月18日
	 * @param doc
	 * @return Integer
	 */
	public Integer insertEntity(AccountUploadDoc doc);

	/**
	 * 删除上传资料
	 * <p>
	 * Description:删除上传资料<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2014年8月18日
	 * @param docId
	 *            void
	 */
	public void deleteDoc(Integer docId);

	/**
	 * 删除by标ID
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2014年10月17日
	 * @param borrowId
	 *            void
	 */
	public void deleteDocByBorrowId(Integer borrowId);

	/**
	 * <p>
	 * Description:根据借款标id查询认证类型集合和时间<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年9月14日
	 * @param borrowId
	 * @return
	 * @throws Exception
	 *             List<AccountUploadDocVo>
	 */
	public List<AccountUploadDocVo> queryUniqueStylesByBorrowId(Integer borrowId) throws Exception;

}
