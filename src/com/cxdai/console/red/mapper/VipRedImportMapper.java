package com.cxdai.console.red.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.red.entity.VipRedImport;
import com.cxdai.console.red.vo.VipRedImportCnd;



public interface VipRedImportMapper {

	/**
	 * 红包管理-查询贵族特权红包发放数
	 * @author liutao
	 * @Date 2015-11-11
	 */
	public Integer queryVipRedImportVoCount(VipRedImportCnd vipRedImportCnd) throws Exception;
	/**
	 * 红包管理-查询贵族特权红包发放集合
	 * @author liutao
	 * @Date 2015-11-11
	 */
	public List<VipRedImport> queryVipRedImportVoList(VipRedImportCnd vipRedImportCnd, Page page) throws Exception;

	public int add(VipRedImport vipRedImport) throws Exception;
	public void updateRedStatus(String id) throws Exception;
	
	@Select("update t_red_envelop_inviter_log set ACTIVE=0 where id=#{id}")
	public void updateInviterLogStatus(@Param("id") String id) throws Exception;
	@Select("update t_red_envelop_inviter set ACTIVE=0 where OPTID=#{id}")
	public void updateInviterStatus(@Param("id") String id) throws Exception;
	
	@Select("select DISTINCT RED_MONEY as redMoney from  t_red_rule where RED_TYPE=#{type} order by (RED_MONEY+0)")
	@ResultType(com.cxdai.console.red.entity.VipRedImport.class)
	public List<VipRedImport> selectRedMoney(@Param("type") String type) throws Exception;
	
	/**
	 * 根据ID获取导入记录
	 * @author WangQianJin
	 * @title @param id
	 * @title @return
	 * @date 2016年6月15日
	 */
	public VipRedImport queryVipRedImportById(Integer id);
}
