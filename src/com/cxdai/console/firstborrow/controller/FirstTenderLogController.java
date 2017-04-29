package com.cxdai.console.firstborrow.controller;

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
import com.cxdai.console.firstborrow.service.FirstTenderLogService;
import com.cxdai.console.firstborrow.vo.FirstTenderLogCnd;
import com.cxdai.console.firstborrow.vo.FirstTenderLogVo;
import com.cxdai.console.util.PropertiesUtil;

/**
 * <p>
 * Description:直通车管理 - 日志记录<br />
 * </p>
 * 
 * @title FirstTenderLogController.java
 * @package com.cxdai.console.firstborrow.controller
 * @author tongjuxing
 * @version 0.1 2015年7月1日
 */
@Controller
@RequestMapping(value = "/firstborrow/firsttenderlog")
public class FirstTenderLogController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(FirstTenderLogController.class);
	
	private String portal_path = PropertiesUtil.getValue("portal_path");
	
	@Autowired
	private FirstTenderLogService firstTenderLogService;

	private final int pageSize = 10;
	
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/firstborrow/firsttenderlog/main");
	}

	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute FirstTenderLogCnd firstTenderLogCnd,
			@PathVariable("pageNo") Integer pageNo) {
		Page page = new Page();
		try {
			firstTenderLogCnd.setOrderSql(" order by tl.id desc ");
			page = firstTenderLogService.queryPageListByCnd(firstTenderLogCnd, new Page(pageNo, pageSize));
			
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/firstborrow/firsttenderlog/list").addObject("page", page).addObject("portal_path",portal_path);
	}
	
	/**
	 * <p>
	 * Description:跳转到详细窗口<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年7月1日
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/detail/{id}")
	public ModelAndView toAddModify(@PathVariable("id") Integer id) {
		
		FirstTenderLogVo firstTenderLogVo = new FirstTenderLogVo();
		try {
			firstTenderLogVo = firstTenderLogService.queryById(id);
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/firstborrow/firsttenderlog/detail").addObject("firstTenderLogVo", firstTenderLogVo);
	}
	
	/**
	 * 
	 * <p>
	 * Description:清除7天前所有的失败记录<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年7月1日 void
	 */
	@RequestMapping(value = "/delfirsttenderlog")
	@ResponseBody
	public MessageBox delFirstTenderLog() {
		try {
			String resultMsg = firstTenderLogService.delFirstTenderLog();
			
			if ("success".equals(resultMsg)){
				return MessageBox.build("0", "清除成功！");
			}else{
				return MessageBox.build("1", resultMsg);
			}
			
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "清除失败！");
		}
	}

}