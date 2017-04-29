package com.cxdai.console.sms.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.sms.mapper.SmsTemplateMapper;
import com.cxdai.console.sms.service.SmsTemplateService;
import com.cxdai.console.sms.vo.SmsTemplateCnd;
import com.cxdai.console.sms.vo.SmsTemplateVo;


@Service
public class SmsTemplateServiceImpl implements SmsTemplateService {
	public Logger logger = Logger.getLogger(SmsTemplateServiceImpl.class);

	@Autowired
	private SmsTemplateMapper smsTemplateMapper;

	@Override
	public SmsTemplateVo querySmsTemplateByType(Integer type) throws Exception {
		SmsTemplateCnd smsTemplateCnd = new SmsTemplateCnd();
		smsTemplateCnd.setType(type);
		smsTemplateCnd.setFlag(Constants.SMS_TEMPLATE_FLAG_YES);
		List<SmsTemplateVo> list = smsTemplateMapper
				.querySmsTemplateList(smsTemplateCnd);
		if (null != list && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}
	@Override
	public Page querySmsTemplateList(SmsTemplateCnd smsTemplateCnd,Integer pageSize,Integer currPage) throws Exception {
		Page page=new Page(currPage,pageSize);
		smsTemplateCnd.setFlag(Constants.SMS_TEMPLATE_FLAG_YES);
		int  count = smsTemplateMapper.querySmsTemplateCount(smsTemplateCnd);
		page.setTotalCount(count);
		List<SmsTemplateVo> list = smsTemplateMapper.querySmsTemplateList(smsTemplateCnd,page);
		page.setResult(list);
		return page;
	}
}
