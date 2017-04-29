package com.cxdai.console.curaccount.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.curaccount.entity.CurAccount;
import com.cxdai.console.curaccount.vo.CurAccountCnd;
import com.cxdai.console.curaccount.vo.CurAccountVo;

/**
 * <p>
 * Description: 活期宝账户数据访问类 <br />
 * </p>
 * 
 * @title CurAccountMapper.java
 * @package com.cxdai.console.curaccount.mapper
 * @author HuangJun
 * @version 0.1 2015年5月12日
 */
public interface CurAccountMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(CurAccount record);

	int insertSelective(CurAccount record);

	CurAccountVo selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CurAccount record);

	int updateByPrimaryKey(CurAccount record);


	/**
	 * <p>
	 * Description:根据用户ID查询活期宝账户信息<br />
	 * </p>
	 * 
	 * @author 朱泳霖
	 * @version 0.1 2015年5月5日
	 * @param userId
	 * @return CurAccount
	 */
	public CurAccountVo selectByUserId(Integer userId);

	/**
	 * 
	 * <p>
	 * Description:根据用户ID查询活期宝账户信息，并锁定<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年5月5日
	 * @param userId
	 * @return CurAccount
	 */
	public CurAccountVo selectByUserIdForUpdate(Integer userId);

	/**
	 * <p>
	 * Description: 用户收益count - 分页 <br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年5月13日
	 * @param curAccountCnd
	 * @return
	 * @throws Exception
	 *             Integer
	 */
	public Integer queryCurAccountCount(CurAccountCnd curAccountCnd) throws Exception;

	/**
	 * <p>
	 * Description: 用户收益list - 分页<br />
	 * </p>
	 * 
	 * @author HuangJun
	 * @version 0.1 2015年5月13日
	 * @param curAccountCnd
	 * @param page
	 * @return
	 * @throws Exception
	 *             List<CurAccountVo>
	 */
	public List<CurAccountVo> queryCurAccountList(CurAccountCnd curAccountCnd, Page page) throws Exception;
	
	
	/**
	 * <p>
	 * Description:  用户收益 - 求和   <br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年5月14日
	 * @param curAccountCnd
	 * @return
	 * @throws Exception
	 * CurAccountVo
	 */
	public CurAccountVo querySumByConn(CurAccountCnd curAccountCnd) throws Exception;
	
	
	
	
	/**
	 * <p>
	 * Description: 查看详情-根据id查询我的账户   <br />
	 * </p>
	 * @author HuangJun
	 * @version 0.1 2015年5月14日
	 * @param id
	 * @return
	 * CurAccountVo
	 */
	public CurAccountVo queryCurAccountVoById(Integer id);
	
	

}