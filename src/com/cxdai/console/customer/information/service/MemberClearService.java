package com.cxdai.console.customer.information.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.bankcard.service.UserBindBankService;
import com.cxdai.console.customer.information.mapper.MemberClearMapper;
import com.cxdai.console.customer.information.vo.MemberClearVo;
import com.cxdai.console.customer.information.vo.MemberCnd;
import com.cxdai.console.customer.information.vo.MemberVo;

/**
 * 
 * <p>
 * Description:会员业务实现类<br />
 * </p>
 * 
 * @title MemberServiceImpl.java
 * @package com.cxdai.console.account.service.impl
 * @author yangshijin
 * @version 0.1 2014年7月2日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class MemberClearService {

	@Autowired
	private MemberClearMapper memberClearMapper;
	@Autowired
	UserBindBankService userBindBankService;

	public void clearMemberInfo(MemberClearVo memberClearVo) throws Exception {
		// 会员表中状态置为账户注销（
		memberClearMapper.updateMemberStatus(memberClearVo);
		// 邮箱认证表中是否通过验证设置为未通过
		memberClearMapper.updateMemberEmailStatus(memberClearVo);
		// 机验证表中是否通过验证设置为未通过
		memberClearMapper.updateMemberMobileStatus(memberClearVo);
		// 实名认证表中是否通过实名认证审核不通过
		memberClearMapper.updateMemberRealnameStatus(memberClearVo);
		// 新增一条注销日志记录（增一张注销日志表，主键、注销用户ID,添加人、添加时间、添加IP，添加的mac地址，备注）
		memberClearMapper.insertMemberClearLog(memberClearVo);
		// 注销用户银行卡
		userBindBankService.cancelUserBankCard(memberClearVo.getMEMBERID());

	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page queryMemberVoListForPages(MemberCnd memberCnd, int pageSize, int curPage) throws Exception {
		Page page = new Page(curPage, pageSize);
		if (curPage <= 1) {
			memberCnd.set_offset(0);
		} else {
			memberCnd.set_offset((curPage - 1) * pageSize);
		}
		memberCnd.set_limit(pageSize);
		int totalCount = memberClearMapper.queryMemberVoCount(memberCnd);
		page.setTotalCount(totalCount);
		List<MemberVo> list = memberClearMapper.queryMemberVoListForPages(memberCnd);
		page.setResult(list);
		return page;
	}
}
