package com.cxdai.console.fix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.fix.mapper.FixTenderUserMapper;
import com.cxdai.console.fix.vo.FixTenderUserCnd;
import com.cxdai.console.fix.vo.FixTenderUserVo;

/**
 * <p>
 * Description:定期宝投标日志接口类<br />
 * </p>
 * 
 * @title FixTenderUserService.java
 * @package com.cxdai.console.fix.service
 * @author caodefeng
 * @version 0.1 2015年5月23日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class FixTenderUserService {
	
	@Autowired
	private FixTenderUserMapper  fixTenderUserMapper;
	/**
	 * 分页查询定期宝投标日志
	 * @param fixTenderUserCnd
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page queryFixTenerUserVoList(FixTenderUserCnd fixTenderUserCnd,
			Integer pageNo, Integer pageSize) throws Exception {
		Page page = new Page(pageNo,pageSize);
		Integer totalCounts = fixTenderUserMapper.queryFixTenerUserVoCounts(fixTenderUserCnd);
		page.setTotalCount(totalCounts);
		List<FixTenderUserVo>  list = fixTenderUserMapper.queryFixTenerUserVoList(fixTenderUserCnd, page);
		page.setResult(list);
		return page;
	}
}
