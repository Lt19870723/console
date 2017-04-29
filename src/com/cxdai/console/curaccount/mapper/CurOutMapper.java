package com.cxdai.console.curaccount.mapper;

import com.cxdai.console.curaccount.vo.CurOutVo;
import java.util.*;

public interface CurOutMapper {
	
	/**
	 * 根据业务ID获取转出记录
	 * @author WangQianJin
	 * @title @param fixId
	 * @title @return
	 * @title @throws Exception
	 * @date 2015年8月27日
	 */
	public List<CurOutVo> getCurOutVoByFixId(Integer fixId) throws Exception;
}