package com.cxdai.console.red.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.red.entity.VipRedView;
import com.cxdai.console.red.vo.VipRedImportCnd;



public interface VipRedViewMapper {

	/**
	 * 红包管理-查询贵族特权红包查看总数
	 * @author liutao
	 * @Date 2015-11-11
	 */
	public Integer queryVipRedViewVoCount(VipRedImportCnd vipRedImportCnd) throws Exception;
	/**
	 * 红包管理-查询贵族特权红包查看集合
	 * @author liutao
	 * @Date 2015-11-11
	 */
	public List<VipRedView> queryVipRedViewVoList(VipRedImportCnd vipRedImportCnd, Page page) throws Exception;

	public int add(VipRedView vipRedView) throws Exception;
	public List<VipRedView> queryRedByRedId(String id) throws Exception;
}
