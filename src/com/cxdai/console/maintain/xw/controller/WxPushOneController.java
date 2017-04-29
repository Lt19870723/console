package com.cxdai.console.maintain.xw.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.constant.CmsConstant;
import com.cxdai.console.maintain.cms.util.FileLoadUtil;
import com.cxdai.console.maintain.xw.entity.SearchNews;
import com.cxdai.console.maintain.xw.entity.ShowList;
import com.cxdai.console.maintain.xw.entity.WxArticles;
import com.cxdai.console.maintain.xw.service.WxPushService;
import com.cxdai.console.util.JsonUtils;
import com.cxdai.console.util.PropertiesUtil;

/**
 * <p>
 * Description:微信维护 - 单图文消息推送<br />
 * </p>
 * 
 * @title WxPushOneController.java
 * @package com.cxdai.console.first.controller
 * @author tongjuxing
 * @version 0.1 2015年7月6日
 */
@Controller
@RequestMapping(value = "/maintain/xw/wxpushone")
public class WxPushOneController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(WxPushOneController.class);

	@Autowired
	private WxPushService wxPushService;
	

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/maintain/xw/wxpushone/main");
	}

	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute SearchNews searchNews,
			@PathVariable("pageNo") Integer pageNo) {
		Page page = new Page(pageNo, pageSize);
		try {
			searchNews.setPushType(1);
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
		
		return forword("/maintain/xw/wxpushone/list").addObject("page", page).addObject("consoleWxImagePath", FileLoadUtil.ROOTUPLOADPATH);
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
		return forword("/maintain/xw/wxpushone/add").addObject("consoleWxImagePath", FileLoadUtil.ROOTUPLOADPATH);

	}
	
	/**
	 * 
	 * <p>
	 * Description:获得描述<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年7月8日 ModelAndView
	 */
	@RequestMapping(value = "/detailone/{id}")
	public ModelAndView detailOne(@PathVariable("id") Integer id) {
		WxArticles wxArticles = wxPushService.queryById(id);
		return forword("/maintain/xw/wxpushone/detailone","wxArticles",wxArticles);
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
		WxArticles wxArticles = wxPushService.queryById(id);
		return forword("/maintain/xw/wxpushone/add","wxArticles",wxArticles).addObject("consoleWxImagePath", FileLoadUtil.ROOTUPLOADPATH);
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:上传图片<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年4月9日
	 * @param files
	 * @param picType
	 * @return String
	 */
	@RequestMapping(value = "/uploadpics", produces = { "text/plain;charset=UTF-8" })
	@ResponseBody
	@RequiresAuthentication
	public String uploadPics(@RequestParam("files") MultipartFile[] files) {
		MessageBox msg = null;
		try {
			String cmsUploadPath =  currentRequest().getSession().getServletContext().getRealPath(PropertiesUtil.getValue("cms_upload_path"));
			msg = FileLoadUtil.upload(files[0], cmsUploadPath, CmsConstant.WXMSGFILEDIC, CmsConstant.fileSizeLimit);
			
		} catch (Exception e) {
			logger.error("资料上传异常", e);
			msg = MessageBox.build("1", "资料上传异常");
		}
		return JsonUtils.bean2Json(msg);
	}
	
	/**
	 * <p>
	 * Description:保存优先投标<br />
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
	public MessageBox save(@ModelAttribute WxArticles wxArticles) {

		try {
			if (wxArticles.getId() != null && wxArticles.getId() > 0) {
				wxPushService.updateNews(wxArticles, null, 0, 1);
			} else {
				if (wxPushService.insertNews(wxArticles, null, 0, 1) == -1)
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
		if (wxPushService.deleteNews(id, 1) < 1) {
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
			Map<String, Object> map = wxPushService.saveAndPushNews(id, 1);
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
		
		wxPushService.saveByCopy(id, 1);
		
		return MessageBox.build("0", "复制完成！");
		
	}

}