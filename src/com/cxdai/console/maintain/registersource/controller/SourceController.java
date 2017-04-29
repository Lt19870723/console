package com.cxdai.console.maintain.registersource.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.registersource.entity.SourceCnd;
import com.cxdai.console.maintain.registersource.entity.SourceListVo;
import com.cxdai.console.maintain.registersource.entity.SourceVo;
import com.cxdai.console.maintain.registersource.service.SourceService;
import com.cxdai.console.system.entity.Configuration;
import com.mysql.jdbc.StringUtils;

/**
 * 注册来源source 相关控制类
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title SourceAction.java
 * @package com.cxdai.console.opration.action
 * @author ZHUCHEN
 * @version 0.1 2015年1月17日
 */
@Controller
@RequestMapping(value = "/source")
public class SourceController extends BaseController {

	private static final long serialVersionUID = -7408999295127275486L;

	private final static Logger logger = Logger.getLogger(SourceController.class);

	@Autowired
	private SourceService sourceService;
	
	
	
	@RequestMapping(value="/toSourceMain")
	@ResponseBody
	public ModelAndView toSourceMain(){
		return forword("/maintain/registersource/sourceMain");
	}
	

	/**
	 * source查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author ZHUCHEN
	 * @version 0.1 2015年1月17日 void
	 */
	@RequestMapping(value="/searchAll")
	@ResponseBody
	public ModelAndView searchAll(@ModelAttribute  SourceCnd sourceCnd) {
		try{
			List<SourceListVo> sourceList = sourceService.searchAll(sourceCnd);
			Page page = new Page();
			page.setResult(sourceList);
			return forword("/maintain/registersource/sourceList").addObject("page",page);
		}catch (Exception e){
			logger.error("查询注册来源失败",e);
			return forword("/common/500");
		}
	}
	
	@RequestMapping(value="/getSourceChannel")
	@ResponseBody
	public StringBuffer getSourceChannel(){
		StringBuffer selectList = new StringBuffer();
		List<Configuration> list = sourceService.querySourceList();
		for (int i = 0; i < list.size(); i++) {
			selectList.append(list.get(i).getName()+","+list.get(i).getValue()+"|");
		}
		return selectList;
		
	}
	
	@RequestMapping(value="/toSourceChannelEdit/{sourceId}")
	@ResponseBody
	public ModelAndView toSourceChannelEdit(@PathVariable("sourceId") String sourceId){
		SourceVo sourceVo =null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (!StringUtils.isNullOrEmpty(sourceId)) {
				sourceVo = sourceService.searchOne(sourceId);
				if(sourceVo.getStartTime()!=null && !"".equals(sourceVo.getStartTime())){
					sourceVo.setStartTimeView(format.format(sourceVo.getStartTime()));
				}
				if(sourceVo.getEndTime()!=null && !"".equals(sourceVo.getEndTime())){
					sourceVo.setEndTimeView(format.format(sourceVo.getEndTime()));
				}
			} 
		} catch (Exception e) {
			logger.error("初始化设置失败，请联系管理员",e);
		}
		return forword("/maintain/registersource/sourceEdit").addObject("sourceVo", sourceVo);
	}
	
	

	@RequestMapping(value="/saveSouces")
	@ResponseBody
	public MessageBox saveSouces(@ModelAttribute SourceVo sourceVo){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(sourceVo.getStartTimeView()!=null && !"".equals(sourceVo.getStartTimeView())){
				sourceVo.setStartTime(format.parse(sourceVo.getStartTimeView()));
			}
			if(sourceVo.getEndTimeView()!=null && !"".equals(sourceVo.getEndTimeView())){
				sourceVo.setEndTime(format.parse(sourceVo.getEndTimeView()));
			}
			Integer userid = currentUser().getUserId();
			sourceVo.setCreateTime(new Date());
			sourceVo.setCreateUserId(userid);
			String returnMsg = sourceService.saveSource(sourceVo);
			if("保存成功".equals(returnMsg)){
				return MessageBox.build("1", returnMsg);
			}
			return MessageBox.build("0", returnMsg);
		} catch (Exception e) {
			logger.error("注册来源保存失败",e);
		}
		return MessageBox.build("0", "保存失败");
	}
}
