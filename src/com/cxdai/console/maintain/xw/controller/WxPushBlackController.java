package com.cxdai.console.maintain.xw.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.cxdai.console.common.WxContants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.xw.entity.WxBlackListCnd;
import com.cxdai.console.maintain.xw.entity.WxBlackListVo;
import com.cxdai.console.maintain.xw.service.WxBlackListService;
import com.cxdai.console.util.HttpTookit;

@Controller
@RequestMapping(value = "/wxBlack/wx")
public class WxPushBlackController extends BaseController {

	private final static Logger logger = Logger.getLogger(WxPushBlackController.class);
	
	@Autowired
	private WxBlackListService wxBlackListService;

	
	/**
	 * <p>
	 * Description:跳转<br />
	 * </p>
	 * 
	 * @author 张鹏
	 * @version 0.1 2015年6月26日
	 */
	@RequestMapping(value="/toWxBlackMain")
	@ResponseBody
	public ModelAndView toWxBlockMain(){
		return forword("/maintain/xw/wxBlackMain");
	}
	/**
	 * <p>
	 * Description:获取微信推送黑名单列表<br />
	 * </p>
	 * 
	 * @author 张鹏
	 * @version 0.1 2015年6月26日
	 */
	@RequestMapping(value="/wxBlackList/{pageNo}")
	@ResponseBody
	public ModelAndView wxBlackList(@ModelAttribute WxBlackListCnd blackListCnd,@PathVariable("pageNo") Integer pageNo){
		try {

			Page page = wxBlackListService.queryPageListByCnd(blackListCnd, pageNo, WxContants.WX_PAGE_SIZE);
			return forword("/maintain/xw/wxBlackList").addObject("page",page);
		} catch (Exception e) {
			logger.error("微信黑名单查询失败",e);
		}
		return forword("common/500");
	}

	
	/**
	 * 
	 * <p>
	 * Description:取消黑名单记录<br />
	 * </p>
	 * 
	 * @author 张鹏
	 * @version 0.1 2015年6月26日 void
	 */

	@RequestMapping(value="/cancelBlackList")
	@ResponseBody
	public MessageBox cancelBlackList(@ModelAttribute WxBlackListVo blackListVo,HttpServletRequest request) {
		try {
			String ip = HttpTookit.getRealIpAddr(request);
			String result = wxBlackListService.updateBlackList(blackListVo, ip);
			if(!"success".equals(result)){
				return MessageBox.build("0", result);
			}
			return MessageBox.build("1", "取消成功");
		} catch (Exception e) {
			logger.error("微信取消黑名单失败",e);
		}
		return MessageBox.build("0", "取消失败");
	}
}
