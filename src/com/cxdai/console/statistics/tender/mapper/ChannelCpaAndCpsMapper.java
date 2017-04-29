package com.cxdai.console.statistics.tender.mapper;

import java.util.List;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.tender.entity.OperationCnd;
import com.cxdai.console.statistics.tender.entity.CpaAndCpsVo;

/**
 * @author WangQianJin
 * @title 各渠道CPA与CPS分析
 * @date 2016年3月16日
 */
public interface ChannelCpaAndCpsMapper {
	
	/**
	 * 投之家CPA数量
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年3月16日
	 */
	public int queryTzjCpaCount(OperationCnd operationCnd);

	/**
	 * 投之家CPA列表
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年3月16日
	 */
	public List<CpaAndCpsVo> queryTzjCpaList(OperationCnd operationCnd, Page page);
	
	/**
	 * 投之家CPA列表
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年3月16日
	 */
	public List<CpaAndCpsVo> queryTzjCpaList(OperationCnd operationCnd);
	
	/**
	 * 投之家CPS数量
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年3月16日
	 */
	public int queryTzjCpsCount(OperationCnd operationCnd);

	/**
	 * 投之家CPS列表
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年3月16日
	 */
	public List<CpaAndCpsVo> queryTzjCpsList(OperationCnd operationCnd, Page page);
	
	/**
	 * 投之家CPS列表
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年3月16日
	 */
	public List<CpaAndCpsVo> queryTzjCpsList(OperationCnd operationCnd);
	
	/**
	 * 网贷天眼CPA数量
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年3月16日
	 */
	public int queryWdtyCpaCount(OperationCnd operationCnd);

	/**
	 * 网贷天眼CPA列表
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年3月16日
	 */
	public List<CpaAndCpsVo> queryWdtyCpaList(OperationCnd operationCnd, Page page);
	
	/**
	 * 网贷天眼CPA列表
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年3月16日
	 */
	public List<CpaAndCpsVo> queryWdtyCpaList(OperationCnd operationCnd);
	
	/**
	 * 网贷天眼CPS数量
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年3月16日
	 */
	public int queryWdtyCpsCount(OperationCnd operationCnd);

	/**
	 * 网贷天眼CPS列表
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年3月16日
	 */
	public List<CpaAndCpsVo> queryWdtyCpsList(OperationCnd operationCnd, Page page);
	
	/**
	 * 网贷天眼CPS列表
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年3月16日
	 */
	public List<CpaAndCpsVo> queryWdtyCpsList(OperationCnd operationCnd);
	
	
	/**
	 * 多麦网数量
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年4月20日
	 */
	public int queryDmwCount(OperationCnd operationCnd);

	/**
	 * 多麦网分页列表
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年4月20日
	 */
	public List<CpaAndCpsVo> queryDmwList(OperationCnd operationCnd, Page page);
	
	/**
	 * 多麦网列表
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年4月20日
	 */
	public List<CpaAndCpsVo> queryDmwList(OperationCnd operationCnd);
	
	
	/**
	 * 富爸爸数量
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年4月20日
	 */
	public int queryFbbCount(OperationCnd operationCnd);

	/**
	 * 富爸爸分页列表
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年4月20日
	 */
	public List<CpaAndCpsVo> queryFbbList(OperationCnd operationCnd, Page page);
	
	/**
	 * 富爸爸列表
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年4月20日
	 */
	public List<CpaAndCpsVo> queryFbbList(OperationCnd operationCnd);
	
	
	/**
	 * 领克特数量
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年4月20日
	 */
	public int queryLktCount(OperationCnd operationCnd);

	/**
	 * 领克特分页列表
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年4月20日
	 */
	public List<CpaAndCpsVo> queryLktList(OperationCnd operationCnd, Page page);
	
	/**
	 * 领克特列表
	 * @author WangQianJin
	 * @title @param operationCnd
	 * @title @return
	 * @date 2016年4月20日
	 */
	public List<CpaAndCpsVo> queryLktList(OperationCnd operationCnd);

}
