package com.cxdai.console.curaccount.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.curaccount.entity.CurIn;
import com.cxdai.console.curaccount.mapper.CurInMapper;
import com.cxdai.console.curaccount.vo.CurInCnd;
import com.cxdai.console.curaccount.vo.CurInLaunchVo;
import com.cxdai.console.curaccount.vo.CurInVo;


/**
 * 
 * <p>
 * Description:活期宝转入记录业务逻辑方法<br />
 * </p>
 * 
 * @title CurInServiceImpl.java
 * @package com.cxdai.console.curAccount.service.impl
 * @author YangShiJin
 * @version 0.1 2015年5月27日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CurInService {
	@Autowired
	private CurInMapper curInMapper;

	
	public CurIn selectByPrimaryKey(Integer id) {
		return curInMapper.selectByPrimaryKey(id);
	}

	
	public Page queryCurInListForCalInterestDayErrorList(CurInCnd curInCnd, int pageSize, int curPage) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = curInMapper.queryCurInListForCalInterestDayErrorCount(curInCnd);
		page.setTotalCount(totalCount);
		List<CurInVo> list = curInMapper.queryCurInListForCalInterestDayError(curInCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 投标退回将转出的活期宝再次转入活期宝
	 * @author WangQianJin
	 * @title @param curInLaunchVo
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月27日
	 */
	public String saveCurrentInForTenderBack(CurInLaunchVo curInLaunchVo) throws Exception {
		String result = BusinessConstants.SUCCESS;
		try {
			// 保存活期宝转入信息
			Map<String, Object> params = new HashMap<String, Object>();
			// 用户ID
			params.put("userId", curInLaunchVo.getTenderUserId());
			// 加入金额
			params.put("account", curInLaunchVo.getCurOutAccount());
			// 转入方式(1:可用余额转入,2:投标资金退回活期宝, 3:购买债权资金退回活期宝)
			params.put("tenderType", curInLaunchVo.getTenderBackType());
			// 操作类型 (101 未产生收益转可产生收益  102 可用余额转入  103 投标资金退回  104 购买债权资金退回  105  活期生息 201 转出到可用余额   202 投标转出  203 开通直通车转出  204 购买债权转出   205 购买直通车转让转出)
			params.put("curLogType", curInLaunchVo.getCurLogType());
			// 日志类型 (201:可用余额转出到活期宝)
			params.put("accountLogType", curInLaunchVo.getAccountLogType());
			// 转出ID
			params.put("outId", curInLaunchVo.getCurOutId());
			// IP地址
			params.put("addip", curInLaunchVo.getAddIp());
			// 平台来源
			params.put("platform", curInLaunchVo.getPlatform());
			
			// 存储过程返回参数
			curInMapper.saveCurrentInForTenderBack(params);
			String msg = params.get("msg").toString();
			if (!"00000".equals(msg)) {
				return msg;
			}
		} catch (Exception e) {
			throw new Exception("保存活期宝转入信息出错！");
		}
		return result;
	}
	
}
