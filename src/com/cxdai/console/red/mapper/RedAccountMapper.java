package com.cxdai.console.red.mapper;


import java.math.BigDecimal;
import java.util.List;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.red.entity.RedAccount;
import com.cxdai.console.red.vo.RedRecordCnd;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Update;

public interface RedAccountMapper {
	/**
	 * 红包管理-红包记录数
	 * @author liutao
	 * @Date 2015-11-11
	 */
	public Integer queryRedAccountVoCount(RedRecordCnd redRecordCnd) throws Exception;
	/**
	 * 红包管理-红包记录集合
	 * @author liutao
	 * @Date 2015-11-11
	 */
	public List<RedAccount> queryRedAccountVoList(RedRecordCnd redRecordCnd, Page page) throws Exception;
	/**
	 * 红包管理-红包记录导出
	 * @author liutao
	 * @Date 2015-11-11
	 */
	public List<RedAccount> queryRedRecordVoListForExport(RedRecordCnd redRecordCnd) throws Exception;

	int add(RedAccount redAccount) throws Exception;

	RedAccount getById(int id);

	RedAccount getByIdForUpdate(int id);

	/**
	 * 获取某个业务使用的红包
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2015年11月4日
	 * @param usebizId
	 * @param usebizType 1-认购定期宝，2-认购直通车，3-认购标的
	 * @param status 3已冻结；4已使用
	 * @return List<RedAccount>
	 */
	List<RedAccount> getBizReds(@Param("usebizId") int usebizId, @Param("usebizType") int usebizType, @Param("status") int status);

	/**
	 * 返还红包
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2015年11月4日
	 * @param redId
	 * @return
	 * @throws Exception int
	 */
	@Update("update t_red_envelop_account set USEBIZ_ID=null,USEBIZ_TYPE=null,status=2,LAST_UPDATE_TIME=now(),end_time=date_add(END_TIME, interval timediff(now(), FREEZE_TIME) day_second),freeze_time=null where id=#{redId}")
	@ResultType(Integer.class)
	int returnRed(@Param("redId") int redId) throws Exception;
	
	
	/**
	 * 红包管理-查询红包金额合计
	 * @author liutao
	 * @Date 2016-01-05
	 */
	public BigDecimal queryRedMoneyTotal(RedRecordCnd redRecordCnd) throws Exception;
}
