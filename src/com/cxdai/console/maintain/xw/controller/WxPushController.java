package com.cxdai.console.maintain.xw.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.cxdai.console.maintain.xw.entity.SearchNews;
import com.cxdai.console.maintain.xw.entity.ShowList;
import com.cxdai.console.maintain.xw.entity.WxArticles;
import com.cxdai.console.maintain.xw.service.WxPushService;

@Controller
@RequestMapping(value = "/newSystem/wx")
public class WxPushController extends BaseController {

	private final static Logger logger = Logger.getLogger(WxPushController.class);
	
	@Autowired
	private WxPushService wxPushService;

	/**
	 * 跳转至微信文本推送页面
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 张鹏
	 * @version 0.1 2015年6月24日 void
	 */
	@RequestMapping(value="/toWxPushView")
	public ModelAndView toWxPushView(){
		return forword("/maintain/xw/wxPushMain");
	}

	/**
	 * 跳转至微信单图推送页面
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 张鹏
	 * @version 0.1 2015年6月24日 void
	 */
	@RequestMapping(value="/toWxPushImgView")
	public ModelAndView toWxPushImgView(){
		return forword("/maintain/xw/wxPushImgMain");
	}
	
	/**
	 * 查询文本消息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 张鹏
	 * @version 0.1 2015年6月24日 void
	 */
	@RequestMapping(value="/serachWxPush/{pageNo}")
	@ResponseBody
	public ModelAndView searchAll(@ModelAttribute SearchNews searchNews, @PathVariable("pageNo") Integer pageNo,HttpServletRequest request) {
		try{
			Page page = this.serachWxPushList(searchNews, pageNo, request,WxContants.WX_PUSH_TYPE_TEST);
			return forword("/maintain/xw/wxPushList").addObject("page", page);
		}catch(Exception e){
			logger.error("微信文本推送查询异常",e);
			return forword("/common/500");
		}
	}

	/**
	 * 查询单图文消息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author 张鹏
	 * @version 0.1 2015年6月24日 void
	 */
	@RequestMapping(value="/serachWxImgPush/{pageNo}")
	@ResponseBody
	public ModelAndView searchImgAll(@ModelAttribute SearchNews searchNews, @PathVariable("pageNo") Integer pageNo,HttpServletRequest request) {
		try{
			Page page = this.serachWxPushList(searchNews, pageNo, request,WxContants.WX_PUSH_TYPE_IMG);
			return forword("/maintain/xw/wxPushImgList").addObject("page", page);
		}catch(Exception e){
			logger.error("微信文本推送查询异常",e);
			return forword("/common/500");
		}
	}
	
	
	/**
	 * 修改
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author Wang Bo
	 * @version 0.1 2015年1月20日 void
	 */
	@RequestMapping(value="/editWxPushText")
	@ResponseBody
	public MessageBox editWxPushText(@ModelAttribute WxArticles wxArticles) {
		try{
			if (wxPushService.updateNews(wxArticles, null, 0, 3) == 1) {
				return MessageBox.build("1", "更新成功");
			}
		}catch (Exception e){
			logger.error("更新微信推送失败",e);
			return MessageBox.build("0", "更新失败，请重试");
		}
		return MessageBox.build("0", "更新失败，请重试");
	}
	
	

	/**
	 * 删除消息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author Wang Bo
	 * @version 0.1 2015年1月5日 void
	 */
	@RequestMapping(value="/delWxPushText")
	@ResponseBody
	public MessageBox delWxPushText(@ModelAttribute WxArticles wxArticles) {
		try{
			if (wxArticles == null) {
				return MessageBox.build("0", "删除失败");
			}
			if (wxPushService.deleteNews(wxArticles.getId(), wxArticles.getType()) >= 1){
				return MessageBox.build("1", "删除成功");
			}
		}catch (Exception e){
			logger.error("删除微信推送小时失败",e);
		}
		return MessageBox.build("0", "删除失败");
	}
	
	@RequestMapping(value="/addWxPushText")
	@ResponseBody
	public MessageBox addWxPushText(@ModelAttribute WxArticles wxArticles){
		try{
			List<WxArticles> list = new ArrayList<WxArticles>();
			if (wxPushService.insertNews(wxArticles, list, 0, wxArticles.getType()) >= 1){
				return MessageBox.build("1", "添加成功");
			}
		}catch (Exception e){
			logger.error("添加微信推送文本失败");
		}
		return MessageBox.build("0", "添加失败");
	}

	
	/**
	 * 再次推送
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author Wang Bo
	 * @version 0.1 2015年1月27日 void
	 */
	@RequestMapping(value="/pushAgain")
	@ResponseBody
	public MessageBox pushAgain(@ModelAttribute WxArticles wxArticles) {
		try {
			WxArticles articles = wxPushService.queryById(wxArticles.getId());
			Map<String, Object> map = null;
			if (wxArticles.getId() == null || wxArticles.getId() < 1) {
				return MessageBox.build("0", "推送失败");
			}
			if (wxPushService.queryPushIng() > 0) {
				return MessageBox.build("0", "推送失败,同一时间只能推送一个任务");
			}

			articles.setStatus(3);
			wxPushService.updateNewsStatus(articles);
			map = wxPushService.saveAndPushNews(wxArticles.getId(), wxArticles.getType());
			return MessageBox.build("1", "推送成功");
		} catch (Exception e) {
			wxArticles.setStatus(2);
			wxPushService.updateNewsStatus(wxArticles);
			logger.error("推送失败",e);
		}
		return MessageBox.build("0", "推送失败");
	}
	
	public Page serachWxPushList(SearchNews searchNews,Integer pageNo,HttpServletRequest request,Integer wxPushType){
		List<ShowList> list =null;
		Page page =null;
		try{
			searchNews.setPushType(wxPushType);
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("beginTime");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
			if(beginTime!=null && !"".equals(beginTime)){
				searchNews.setPushTime(format.parse(beginTime));
			}
			if(endTime!=null && !"".equals(endTime)){
				searchNews.setPushTime2(format.parse(endTime));
			}
			Integer count = wxPushService.searchCount(searchNews);
			page =new Page();
			page.setTotalCount(count);
			if (page.getTotalPage() < page.getPageNo()) {
				page.setPageNo(page.getTotalPage());
			}
			searchNews.setBegin((pageNo - 1) * page.getPageSize());
			searchNews.setPageSize(page.getPageSize());
			list = wxPushService.searchAll(searchNews);
			if (list != null && list.size() > 0) {
				for (ShowList sList : list) {
					if (sList.getStatus() != 1) {
						sList.setPushNum(null);
						sList.setPushTime(null);
						sList.setPushNumTotal(null);
					}
					if(sList.getText().length()>30){
						sList.setShowText(sList.getText().substring(0, 30)+"...");
					}else{
						sList.setShowText(sList.getText());
					}
				}
			}
			page.setResult(list);
		}catch (Exception e){
			logger.error("查询结果异常",e);
		}
		return page;
	}

	@RequestMapping(value="/addWxPushImg")
	@ResponseBody
	public ModelAndView addWxPushImg(){
		return forword("/maintain/xw/addWxPushImg");
	}
}
