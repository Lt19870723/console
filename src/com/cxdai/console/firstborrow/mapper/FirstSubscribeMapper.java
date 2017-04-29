package com.cxdai.console.firstborrow.mapper;

import java.util.List;
import java.util.Map;

import com.cxdai.console.firstborrow.vo.FirstSubscribeVo;

/**
 * <p>
 * Description:直通车转让认购数据访问类<br />
 * </p>
 * 
 * @title FirstSubscribeMapper.java
 * @package com.cxdai.portal.first.mapper
 * @author 朱泳霖
 * @version 0.1 2015年3月19日
 */
public interface FirstSubscribeMapper {
	
	public List<FirstSubscribeVo> querySubscribeListByTransferId(Integer transferId);
	
	/**
	 * <p>
	 * Description:调用直通车自动复审存储过程<br />
	 * </p>
	 * @author 朱泳霖
	 * @version 0.1 2015年3月27日
	 * @param params
	 * void
	 */
	public void saveTransferRecheck(Map<String, Object> params) throws Exception;
	
	public List<FirstSubscribeVo> queryTransferRecheckSubscribe(Integer transferId);

}
