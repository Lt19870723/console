package com.cxdai.console.customer.blacklist.controller;

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
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.customer.blacklist.service.BlackListService;
import com.cxdai.console.customer.blacklist.vo.BlackListCnd;
import com.cxdai.console.customer.blacklist.vo.BlackListVo;
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.HttpTookit;

/**
 * <p>
 * Description:客户管理 - 黑名单设置<br />
 * </p>
 * 
 * @title BlackListController.java
 * @package com.cxdai.console.first.controller
 * @author tongjuxing
 * @version 0.1 2015年7月12日
 */
@Controller
@RequestMapping(value = "/customer/blacklist")
public class BlackListController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(BlackListController.class);

	@Autowired
	private BlackListService blackListSevice;
	@Autowired
	private MemberMapper memberMapper;
	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/customer/blacklist/main");
	}

	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute BlackListCnd blackListCnd,
			@PathVariable("pageNo") Integer pageNo) {
		Page page = new Page();
		try {
			page = blackListSevice.queryPageListByCnd(blackListCnd, pageNo, pageSize);
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/customer/blacklist/list").addObject("page", page);
	}

	/**
	 * <p>
	 * Description:显示添加<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年6月29日
	 * @param cardFile
	 * @param request
	 * @return AjaxJson
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add() {

		return forword("/customer/blacklist/add");

	}

	/**
	 * <p>s
	 * Description:添加黑名单<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年6月30日
	 * @param request
	 * @param id
	 * @return MessageBox
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public MessageBox save(@ModelAttribute BlackListVo blackListVo) {
		
		ShiroUser user = currentUser();
		String ip = HttpTookit.getRealIpAddr(currentRequest());
		
		try {

			String result = blackListSevice.saveBlackList(blackListVo, user, ip);
			if (result.equals("success") && blackListVo.getType() == BusinessConstants.BLACK_TYPE_FIRST_DEBUS) {
				result = blackListSevice.handleFirstTenderReal(blackListVo.getUserId(), blackListVo.getType(), user, ip);
			}
			if (!result.equals("success") && blackListVo.getType() == BusinessConstants.BLACK_TYPE_FIRST_DEBUS) {
				result = blackListSevice.updateBlackList(blackListVo, user, ip, -1);
				if (result.equals("success")) {
					result = "直通车下车失败！";
				}
			} else if (blackListVo.getType() == BusinessConstants.BLACK_TYPE_FIRST_DEBUS) {
				result = blackListSevice.updateBlackList(blackListVo, user, ip, 2);
			}

			if ("success".equals(result)){
				
				return MessageBox.build("0", "保存成功！");
			}else{
				return MessageBox.build("1", result);
			}
				
		} catch (Exception e) {
			try {
				blackListSevice.updateBlackList(blackListVo, user, ip, -1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			logger.error(e);
			return MessageBox.build("1", "系统异常，请稍候重试或联系管理员！");
		}
	}
	
	/**
	 * <p>
	 * Description:显示取消<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年6月29日
	 * @param cardFile
	 * @param request
	 * @return AjaxJson
	 */
	@RequestMapping(value = "/cancle/{id}/{userId}/{type}")
	public ModelAndView cancle(@PathVariable("id") Integer id,
			@PathVariable("userId") Integer userId,
			@PathVariable("type") String type) {

		return forword("/customer/blacklist/cancle")
				.addObject("id", id)
				.addObject("username", memberMapper.selectUserNameById(userId))
				.addObject("type", type);

	}
	
	/**
	 * <p>
	 * Description:取消黑名单<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年7月12日
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception Map<String,Object>
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public MessageBox update(@ModelAttribute BlackListVo blackListVo) {
		
		ShiroUser user = currentUser();
		String ip = HttpTookit.getRealIpAddr(currentRequest());
		
		try {
			
			String result = blackListSevice.updateBlackList(blackListVo, user, ip, -1);

			if ("success".equals(result)){
				
				return MessageBox.build("0", "取消成功！");
			}else{
				return MessageBox.build("1", result);
			}
				
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "系统异常，请稍候重试或联系管理员！");
		}
	}
	

}