package com.cxdai.console.firstborrow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.base.entity.FirstTransfer;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.firstborrow.vo.FirstTransferCnd;
import com.cxdai.console.firstborrow.vo.FirstTransferVo;

public interface FirstTransferMapper {
	
	/**
	 * <p>
	 * Description:根据条件分页查询直通车初审转让信息<br />
	 * </p>
	 * @author 朱泳霖
	 * @version 0.1 2015年4月9日
	 * @param firstTransferCnd
	 * @param page
	 * @return
	 * List<FirstTransferVo>
	 */
	public List<FirstTransferVo> queryPageListByCnd(@Param("firstTransferCnd") FirstTransferCnd firstTransferCnd, Page page);

	/**
	 * <p>
	 * Description:根据条件查询直通车转让信息总件数<br />
	 * </p>
	 * @author 朱泳霖
	 * @version 0.1 2015年4月9日
	 * @param firstTransferCnd
	 * @return
	 * Integer
	 */
	public Integer queryCountPageListByCnd(@Param("firstTransferCnd") FirstTransferCnd firstTransferCnd);
	
	/**
	 * <p>
	 * Description:分页查询直通车转让信息<br />
	 * </p>
	 * @author 朱泳霖
	 * @version 0.1 2015年4月9日
	 * @param firstTransferCnd
	 * @param page
	 * @return
	 * List<FirstTransferVo>
	 */
	public List<FirstTransferVo> queryFirstTransferListByCnd(@Param("firstTransferCnd") FirstTransferCnd firstTransferCnd, Page page);
	
	/**
	 * <p>
	 * Description:查询直通车转让信息总件数<br />
	 * </p>
	 * @author 朱泳霖
	 * @version 0.1 2015年4月9日
	 * @param firstTransferCnd
	 * @return
	 * Integer
	 */
	public Integer queryCountFirstTransferListByCnd(@Param("firstTransferCnd") FirstTransferCnd firstTransferCnd);
	
	/**
	 * <p>
	 * Description:根据直通车转让ID查询直通车转让信息<br />
	 * </p>
	 * @author 朱泳霖
	 * @version 0.1 2015年4月12日
	 * @param firstTransferCnd
	 * @return
	 * FirstTransferVo
	 */
	public FirstTransferVo queryTransferById(@Param("firstTransferCnd") FirstTransferCnd firstTransferCnd);
	
	/**
	 * <p>
	 * Description:根据直通车转让ID更新直通车转让信息<br />
	 * </p>
	 * @author 朱泳霖
	 * @version 0.1 2015年4月12日
	 * @param firstTransfer
	 * @throws Exception
	 * void
	 */
	public void updateFirstTransferStatus(FirstTransfer firstTransfer) throws Exception;
	
}
