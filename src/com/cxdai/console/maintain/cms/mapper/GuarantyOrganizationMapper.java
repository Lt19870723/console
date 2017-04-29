package com.cxdai.console.maintain.cms.mapper;

import java.util.List;

import com.cxdai.console.base.mapper.BaseGuarantyOrganizationMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.entity.GuarantyOrganizationCnd;
import com.cxdai.console.maintain.cms.entity.GuarantyOrganizationVo;

public interface GuarantyOrganizationMapper extends BaseGuarantyOrganizationMapper {

	public List<GuarantyOrganizationVo> pageQuery(GuarantyOrganizationCnd organizationCnd, Page page);

	public int pageQueryCount(GuarantyOrganizationCnd organizationCnd);

}
