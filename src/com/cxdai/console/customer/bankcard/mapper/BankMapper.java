package com.cxdai.console.customer.bankcard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.bankcard.entity.Bank;
import com.cxdai.console.customer.bankcard.entity.BankCnd;
import com.cxdai.console.customer.bankcard.entity.BankVo;

/**
 * 
 * <p>
 * Description:银行卡分支行信息数据访问类<br />
 * </p>
 * 
 * @title BankMapper.java
 * @package com.cxdai.console.account.mapper
 * @author yangshijin
 * @version 0.1 2014年10月10日
 */
public interface BankMapper {

	/**
	 * 
	 * <p>
	 * Description:插入记录,返回新增的主键ID<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月10日
	 * @param bank
	 * @return
	 * @throws Exception int
	 */
	public int insertEntity(Bank bank) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:更新对象<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月10日
	 * @param bank
	 * @return
	 * @throws Exception int
	 */
	public int updateEntity(Bank bank) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据主键Id查询记录<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月10日
	 * @param id
	 * @return
	 * @throws Exception BankVo
	 */
	public BankVo queryBankVoById(int id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据主键Id查询记录，并锁定记录 <br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月10日
	 * @param id
	 * @return
	 * @throws Exception BankVo
	 */
	public BankVo queryBankVoByIdForUpdate(int id) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据条件查询记录集合,并分页<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月10日
	 * @param bankCnd
	 * @param page
	 * @return
	 * @throws Exception List<BankVo>
	 */
	public List<BankVo> queryBankVoList(BankCnd bankCnd, Page page) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据条件查询记录数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月10日
	 * @param bankCnd
	 * @return
	 * @throws Exception int
	 */
	public int queryBankVoCount(BankCnd bankCnd) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:查询省<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月10日
	 * @return
	 * @throws Exception List<BankVo>
	 */
	public List<BankVo> queryProvinceList() throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据省查询市<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月10日
	 * @param province
	 * @return
	 * @throws Exception List<BankVo>
	 */
	public List<BankVo> queryCityList(String province) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据市查询区/县<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月10日
	 * @param province
	 * @return
	 * @throws Exception List<BankVo>
	 */
	public List<BankVo> queryDistrictList(String city) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据区/县查询银行名称及银行编码<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月10日
	 * @param province
	 * @return
	 * @throws Exception List<BankVo>
	 */
	public List<BankVo> queryBankList(String district) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据银行名称查询银行编码<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月11日
	 * @param bankName
	 * @return
	 * @throws Exception List<BankVo>
	 */
	public List<BankVo> queryBankCode(String bankName) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据区/县、银行名称查询支行名称及联行号<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月10日
	 * @param province
	 * @return
	 * @throws Exception List<BankVo>
	 */
	public List<BankVo> queryBranchList(@Param("district") String district, @Param("bankName") String bankName) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据银行名称、银行编码及支行名称查询支行名称及联行号<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月10日
	 * @param province
	 * @return
	 * @throws Exception List<BankVo>
	 */
	public List<BankVo> queryBranchListLike(@Param("district") String district, @Param("branch") String branch) throws Exception;

	/**
	 * 
	 * <p>
	 * Description:根据银行名称及支行名称查询数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年10月17日
	 * @param bankName
	 * @param branch
	 * @return
	 * @throws Exception Integer
	 */
	public Integer queryBankByBankNameAndBranch(@Param("bankName") String bankName, @Param("branch") String branch) throws Exception;

	public List<BankVo> queryBankListByDistrict(@Param("district") String district);

}
