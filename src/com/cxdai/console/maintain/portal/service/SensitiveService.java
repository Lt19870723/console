package com.cxdai.console.maintain.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.recharge.mapper.BaseAccountMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.portal.entity.SensitiveCnd;
import com.cxdai.console.maintain.portal.entity.SensitiveVo;
import com.cxdai.console.maintain.portal.mapper.SensitiveMapper;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.statistics.account.service.AccountService;

@Service
@Transactional(rollbackFor = Throwable.class)
public class SensitiveService{
	public Logger logger = Logger.getLogger(SensitiveService.class);

	@Autowired
	private SensitiveMapper sensitiveMapper;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BaseAccountMapper baseAccountMapper;
	@Autowired
	private AccountLogService accountLogService;

	// 得到所有的类型名称
	
	public List<SensitiveVo> querySensitiveTypeList() {
		List<SensitiveVo> a = new ArrayList<SensitiveVo>();

		try {
			a = sensitiveMapper.querySensitiveTypeList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return a;
	}

	// 得到关键字列表
	
	public Page querySensitiveList(SensitiveCnd sensitiveCnd, int curPage, int pageSize) {

		Page page = new Page(curPage, pageSize);
		List<SensitiveVo> a = new ArrayList<SensitiveVo>();
		try {

			int totalCount = sensitiveMapper.querySensitiveCount(sensitiveCnd);
			page.setTotalCount(totalCount);

			if (sensitiveCnd.getWord() == null || sensitiveCnd.getWord().equals("")) {
				sensitiveCnd.setWord("");
			}

			a = sensitiveMapper.querySensitiveList(sensitiveCnd, page);
			page.setResult(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	// 删除关键字
	@Transactional(rollbackFor = Throwable.class)
	public void deleteSensitive(SensitiveCnd sensitiveCnd) {
		try {
			sensitiveMapper.deleteSensitive(sensitiveCnd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 保存关键字
	@Transactional(rollbackFor = Throwable.class)
	
	public void saveSensitive(SensitiveVo sensitiveVo) {
		try {
			sensitiveMapper.saveSensitive(sensitiveVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 根据id查询
	
	public SensitiveVo querySensitiveByid(int ID) {
		SensitiveVo s = null;
		try {
			s = sensitiveMapper.querySensitiveByid(ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	// 更新数据
	@Transactional(rollbackFor = Throwable.class)
	public void updateSensitive(SensitiveVo sensitiveVo) {
		try {
			sensitiveMapper.updateSensitive(sensitiveVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 验证重复
	
	public int querySensitiveForSave(SensitiveVo sensitiveVo) {

		int cont = 0;
		try {
			cont = sensitiveMapper.querySensitiveForSave(sensitiveVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cont;
	};

}
