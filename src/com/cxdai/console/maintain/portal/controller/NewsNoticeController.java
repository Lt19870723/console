package com.cxdai.console.maintain.portal.controller;

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
import com.cxdai.console.maintain.portal.entity.NewsNotice;
import com.cxdai.console.maintain.portal.entity.NewsNoticeCnd;
import com.cxdai.console.maintain.portal.service.NewsNoticeService;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;

@Controller
@RequestMapping(value = "/notice")
public class NewsNoticeController extends BaseController{
	private static final long serialVersionUID = -673628584022127524L;

	public Logger logger = Logger.getLogger(NewsNoticeController.class);
	
	
	@Autowired
	private NewsNoticeService newsNoticeService;

	
	@RequestMapping(value="/toNoticeMain")
	@ResponseBody
	public ModelAndView toNoticeMain(){
		return forword("/maintain/portal/noticeMain");
	}
	
	/**
	 * 
	 * <p>
	 * Description:查询新闻公告列表<br />
	 * </p>
	 * 
	 * @author zhangpeng
	 * @version 0.1 2014年4月24日 void
	 */
	@RequestMapping(value="/searchNewsNotices/{pageNo}")
	@ResponseBody
	public ModelAndView searchNewsNotices(@ModelAttribute NewsNoticeCnd newsNoticeCnd,@PathVariable("pageNo") Integer pageNo) {
		Page page = new Page();
		try {
			newsNoticeCnd.setStatus(0);
			page = newsNoticeService.queryListByNewsNoticeCnd(newsNoticeCnd, pageNo, WxContants.WX_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forword("/maintain/portal/noticeList").addObject("page", page);
	}
	

	@RequestMapping(value="/delNotice")
	@ResponseBody
	public MessageBox delNotice(Integer noticeId){
		try {
			NewsNotice newsNotice = newsNoticeService.queryById(noticeId);
			newsNotice.setStatus(1);
			int i = newsNoticeService.updateNewsNotice(newsNotice);
			if(i>0){
				return MessageBox.build("1", "删除成功");
			}
		} catch (Exception e) {
			logger.error("删除公告失败",e);
		}
		return MessageBox.build("0", "删除失败");
				
	}
	

	@RequestMapping(value="/addNotice")
	@ResponseBody
	public MessageBox addNotice(@ModelAttribute NewsNotice newsNotice,HttpServletRequest reuqest){
		try {
			String ip = HttpTookit.getRealIpAddr(reuqest);
			newsNotice.setHits(0);
			newsNotice.setAddtime(DateUtils.getTime());
			newsNotice.setAddip(ip);
			newsNotice.setUpdatetime(DateUtils.getTime());
			newsNotice.setUpip(ip);
			int i = newsNoticeService.insertNewsNotice(newsNotice);
			if(i>0){
				return MessageBox.build("1", "添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageBox.build("0", "添加失败");
	}

	@RequestMapping(value="/toNoticeAdd")
	@ResponseBody
	public ModelAndView toNoticeAdd(){
		return forword("/maintain/portal/noticeAdd");
	}
	
	
	@RequestMapping(value="/toNoticeEdit/{noticeId}")
	@ResponseBody
	public ModelAndView toNoticeEdit(@PathVariable("noticeId") Integer noticeId){
		NewsNotice newsNotice =null;
		try {
			if(noticeId!=null){
				newsNotice = newsNoticeService.queryById(noticeId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forword("/maintain/portal/noticeEdit").addObject("newsNotice", newsNotice);
	}

	@RequestMapping(value="/editNotice")
	@ResponseBody
	public MessageBox editNotice(@ModelAttribute NewsNotice newsNotice,HttpServletRequest request){
		try {
			newsNotice.setUpdatetime(DateUtils.getTime());
			String ip = HttpTookit.getRealIpAddr(request);
			newsNotice.setUpip(ip);
			int i = newsNoticeService.updateNewsNotice(newsNotice);
			if(i>0){
				return MessageBox.build("1", "修改成功");
			}
		} catch (Exception e) {
			logger.error("公告修改失败",e);
		}
		return MessageBox.build("0", "修改失败");
	}

}
