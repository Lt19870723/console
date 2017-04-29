package com.cxdai.console.maintain.cms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.base.entity.GuarantyOrganization;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.entity.GuarantyOrganizationCnd;
import com.cxdai.console.maintain.cms.entity.GuarantyOrganizationVo;
import com.cxdai.console.maintain.cms.mapper.GuarantyOrganizationMapper;

@Service
@Transactional(rollbackFor = Throwable.class)
public class GuarantyOrganizationService{

	@Autowired
	private GuarantyOrganizationMapper organizationMapper;

	/**
	 * 列表
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年9月5日
	 * @return
	 * @throws Exception List<GuarantyOrganization>
	 */
	public Page pageQuery(GuarantyOrganizationCnd organizationCnd, Page page) throws Exception {
		int totalCount = organizationMapper.pageQueryCount(organizationCnd);
		page.setTotalCount(totalCount);
		List<GuarantyOrganizationVo> list = organizationMapper.pageQuery(organizationCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 删除
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年9月5日
	 * @param id
	 * @return
	 * @throws Exception String
	 */
	public String delete(int id) throws Exception {
		String msg = "";
		int n = organizationMapper.deleteEntity(id);
		if (n == 0) {
			msg = "删除失败：该机构已有担保标使用，不能删除。";
		}
		return msg;
	}

	/**
	 * 添加
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年9月5日
	 * @param organization
	 * @return
	 * @throws Exception String
	 */
	public String add(GuarantyOrganization organization) throws Exception {
		List<GuarantyOrganization> list = organizationMapper.getByProperty("name", organization.getName());
		if (list != null && list.size() > 0) {
			return "添加失败：担保机构名称已被使用！";
		}
		organization.setAddtime(new Date());
		int n = organizationMapper.insertEntity(organization);
		if (n == 0) {
			return "添加失败，请联系管理员。";
		}
		return "";
	}

	/**
	 * 初始化编辑
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年9月7日
	 * @param id
	 * @return
	 * @throws Exception GuarantyOrganization
	 */
	public GuarantyOrganization initEdit(int id) throws Exception {
		List<GuarantyOrganization> list = organizationMapper.getByProperty("id", id + "");
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 编辑
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年9月7日
	 * @param id
	 * @param organization
	 * @return
	 * @throws Exception String
	 */
	public String update(int id, GuarantyOrganization organization) throws Exception {
		List<GuarantyOrganization> list = organizationMapper.getByProperty("name", organization.getName());
		if (list != null && list.size() > 0 && id != list.get(0).getId()) {
			return "修改失败：担保机构名称已被使用！";
		}
		organizationMapper.updateEntity(organization);
		return "";
	}

}
