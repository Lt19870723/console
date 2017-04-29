package com.cxdai.console.finance.virement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.finance.virement.entity.AttachMent;
import com.cxdai.console.finance.virement.mapper.AttachMentMapper;

/***
 * 附件数据Service
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class AttachmentService {

	@Autowired
	private AttachMentMapper attachMentMapper;
	

	/***
	 * 查询附件信息
	 * @param attachMent
	 * @return
	 */
	public List<AttachMent> selectFielByTagleId(AttachMent attachMent) {
		List<AttachMent> list = attachMentMapper.selectFielByTagleId(attachMent);
		return list;
	}

	
}
