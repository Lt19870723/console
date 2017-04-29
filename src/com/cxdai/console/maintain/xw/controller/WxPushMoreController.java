package com.cxdai.console.maintain.xw.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.util.FileLoadUtil;
import com.cxdai.console.maintain.xw.entity.SearchNews;
import com.cxdai.console.maintain.xw.entity.ShowList;
import com.cxdai.console.maintain.xw.entity.WxArticles;
import com.cxdai.console.maintain.xw.service.WxPushService;

/**
 * <p>
 * Description:微信维护 - 多图文消息推送<br />
 * </p>
 * 
 * @title WxPushMoreController.java
 * @package com.cxdai.console.first.controller
 * @author tongjuxing
 * @version 0.1 2015年7月6日
 */
@Controller
@RequestMapping(value = "/maintain/xw/wxpushmore")
public class WxPushMoreController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(WxPushMoreController.class);

	@Autowired
	private WxPushService wxPushService;

	private WxArticles wxArticles;
	
	List<WxArticles> newsList;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/maintain/xw/wxpushmore/main");
	}

	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute SearchNews searchNews,
			@PathVariable("pageNo") Integer pageNo) {
		Page page = new Page(pageNo, pageSize);
		try {
			searchNews.setPushType(2);
			Integer count = wxPushService.searchCount(searchNews);
			page.setTotalCount(count);
			if (page.getTotalPage() < page.getPageNo()) {
				page.setPageNo(page.getTotalPage());
			}
			searchNews.setBegin(page.getOffset());
			searchNews.setPageSize(page.getPageSize());
			List<ShowList> list = wxPushService.searchAll(searchNews);
			if (list != null && list.size() > 0) {
				for (ShowList sList : list) {
					if (sList.getStatus() != 1) {
						sList.setPushNum(null);
						sList.setPushTime(null);
						sList.setPushNumTotal(null);
					}
				}
			}
			page.setResult(list);
			
		} catch (Exception e) {
			logger.error(e);
		}
		
		return forword("/maintain/xw/wxpushmore/list").addObject("page", page).addObject("consoleWxImagePath", FileLoadUtil.ROOTUPLOADPATH);
	}

	/**
	 * <p>
	 * Description:显示添加<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年7月7日
	 * @param cardFile
	 * @param request
	 * @return AjaxJson
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add() {
		int newsCount = 2;
		WxArticles wxArticles = new WxArticles();
		wxArticles.setParentId(null);
		wxArticles.setDescription(null);
		wxArticles.setTitle(null);
		wxArticles.setUrl(null);
		wxArticles.setSort(0);
		wxArticles.setId(null);
		List<WxArticles> newsList = new ArrayList<WxArticles>();
		for (int i = 0; i < 9; i++) {
			WxArticles articles2 = new WxArticles();
			articles2.setSort(i + 1);
			newsList.add(articles2);
		}
		
		return forword("/maintain/xw/wxpushmore/add")
				.addObject("consoleWxImagePath", FileLoadUtil.ROOTUPLOADPATH)
				.addObject("newsCount", newsCount)
				.addObject("wxArticles", wxArticles)
				.addObject("newsList", newsList);

	}
	
	/**
	 * 
	 * <p>
	 * Description:获得多图文详情<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年7月8日 ModelAndView
	 */
	@RequestMapping(value = "/detail/{id}")
	public ModelAndView detail(@PathVariable("id") Integer id) {
		List<WxArticles> newsList = wxPushService.queryNewsById(id);
		WxArticles wxArticles = wxPushService.queryById(id);
		return forword("/maintain/xw/wxpushmore/detail")
				.addObject("consoleWxImagePath", FileLoadUtil.ROOTUPLOADPATH)
				.addObject("wxArticles", wxArticles)
				.addObject("newsList", newsList);
	}
	
	/**
	 * 
	 * <p>
	 * Description:显示修改<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年7月8日 ModelAndView
	 */
	@RequestMapping(value = "/modify/{id}")
	public ModelAndView modify(@PathVariable("id") Integer id) {
		List<WxArticles> newsList = wxPushService.queryNewsById(id);
		int newsCount = newsList.size();
		for (int i = newsCount; i < 9; i++) {
			WxArticles articles = new WxArticles();
			articles.setSort(i + 1);
			newsList.add(articles);
		}
		
		WxArticles wxArticles = wxPushService.queryById(id);
		
		return forword("/maintain/xw/wxpushmore/add")
				.addObject("consoleWxImagePath", FileLoadUtil.ROOTUPLOADPATH)
				.addObject("newsCount",Math.max(newsCount, 2))
				.addObject("wxArticles", wxArticles)
				.addObject("newsList", newsList);
	}
	
	
	/**
	 * <p>
	 * Description:保存<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年7月8日
	 * @param request
	 * @param id
	 * @return MessageBox
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public MessageBox save(@ModelAttribute WxPushMoreController form) {

		try {
			if (form.newsList.size() == 0) {
				return MessageBox.build("1", "保存失败,请至少填写2条图文消息！");
			}
			
			if (form.wxArticles.getId() != null && form.wxArticles.getId() > 0) {
				wxPushService.updateNews(form.wxArticles, form.newsList, form.newsList.size(), 2);
			} else {
				if (wxPushService.insertNews(form.wxArticles, form.newsList, form.newsList.size(), 2) == -1)
					return MessageBox.build("1", "保存失败！");
			}
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", e.getMessage());
		}
		
		return MessageBox.build("0", "保存成功！");

	}
	
	/**
	 * <p>
	 * Description:删除<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年7月8日
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception Map<String,Object>
	 */
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public MessageBox delete(@PathVariable("id") Integer id) throws Exception {
		if (wxPushService.deleteNews(id, 2) < 1) {
			return MessageBox.build("1", "该图文消息无法删除");
		}
		
		return MessageBox.build("0", "删除成功！");
		
	}
	
	/**
	 * <p>
	 * Description:推送<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年7月8日
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception Map<String,Object>
	 */
	@RequestMapping(value = "/push/{id}")
	@ResponseBody
	public MessageBox push(@PathVariable("id") Integer id) throws Exception {
		WxArticles articles = wxPushService.queryById(id);

		try {
			if (wxPushService.queryPushIng() > 0) {
				return MessageBox.build("1", "当前有正在推送的任务,请等待推送完成后再进行推送.");
			}

			articles.setStatus(3);
			wxPushService.updateNewsStatus(articles);
			Map<String, Object> map = wxPushService.saveAndPushNews(id, 2);
			String resultMsg = (String) map.get("message");
			if (StringUtils.isEmpty(resultMsg)){
				resultMsg = "推送完成！";
			}
			
			return MessageBox.build("0", resultMsg);
		} catch (Exception e) {
			logger.error(e);
			articles.setStatus(2);
			wxPushService.updateNewsStatus(articles);
			
			return MessageBox.build("1", "推送失败");
		}
		
	}
	
	/**
	 * <p>
	 * Description:复制<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年7月8日
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception MessageBox
	 */
	@RequestMapping(value = "/pushagain/{id}")
	@ResponseBody
	public MessageBox pushAgain(@PathVariable("id") Integer id) throws Exception {
		
		wxPushService.saveByCopy(id, 2);
		
		return MessageBox.build("0", "复制完成！");
		
	}
	
	public WxArticles getWxArticles() {
		return wxArticles;
	}

	public void setWxArticles(WxArticles wxArticles) {
		this.wxArticles = wxArticles;
	}

	public List<WxArticles> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<WxArticles> newsList) {
		this.newsList = newsList;
	}

}