package com.cxdai.console.maintain.xw.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.WxBase;
import com.cxdai.console.common.WxContants;
import com.cxdai.console.maintain.xw.entity.AutoMessage;
import com.cxdai.console.maintain.xw.entity.SearchNews;
import com.cxdai.console.maintain.xw.entity.ShowList;
import com.cxdai.console.maintain.xw.entity.WxArticles;
import com.cxdai.console.maintain.xw.entity.WxNews;
import com.cxdai.console.maintain.xw.mapper.WxPushMapper;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.HttpClientUtils;
import com.cxdai.console.util.ShiroUtils;
import com.cxdai.console.util.WxUtils;
import com.mysql.jdbc.StringUtils;

@Service
@Transactional(rollbackFor = Throwable.class)
public class WxPushService{
	@Autowired
	private WxPushMapper wxPushMapper;

	
	public int searchCount(SearchNews searchNews) {
		return wxPushMapper.searchCount(searchNews);
	}

	
	public List<ShowList> searchAll(SearchNews searchNews) {
		return wxPushMapper.searchAll(searchNews);
	}

	
	public WxArticles queryById(Integer id) {
		return wxPushMapper.queryById(id);
	}

	
	public int insertNews(WxArticles articles, List<WxArticles> wxNewsList, int count, int type) throws Exception {
		Date d = new Date();
		String ip = WxUtils.getRealIpAddr();
		String mac = "";
		ShiroUser shiroUser = ShiroUtils.currentUser();
		int userid = shiroUser.getUserId();
		articles.setCreateTime(d);
		articles.setStatus(2);
		articles.setPushNum(0);
		articles.setCreateUser(userid);
		articles.setParentId(null);
		articles.setType(type);
		articles.setIp(ip);
		articles.setMac(mac);
		wxPushMapper.insterNews(articles);
		if (type == 2) {
			WxArticles wxArticles = null;
			for (int i = 0; i < count; i++) {
				if (wxNewsList.get(i).getSort() < 1) {
					throw new Exception("保存失败,存在错误的图文消息顺序");
				}
				wxArticles = new WxArticles();
				wxArticles.setDescription(wxNewsList.get(i).getDescription());
				wxArticles.setPicurl(wxNewsList.get(i).getPicurl());
				wxArticles.setUrl(wxNewsList.get(i).getUrl());
				wxArticles.setTitle(wxNewsList.get(i).getTitle());
				wxArticles.setParentId(articles.getId());
				wxArticles.setCreateTime(d);
				wxArticles.setStatus(2);
				wxArticles.setPushNum(0);
				wxArticles.setIp(ip);
				wxArticles.setMac(mac);
				wxArticles.setType(type);
				wxArticles.setSort(wxNewsList.get(i).getSort());
				wxArticles.setCreateUser(userid);
				if (wxPushMapper.insterNews(wxArticles) != 1)
					throw new Exception("保存失败,插入多图文失败");
			}
		}
		return 1;
	}

	
	public int updateNews(WxArticles wxArticles, List<WxArticles> wxNewsList, int newcount, int selectType) throws Exception {
		Date d = new Date();
		String ip = WxUtils.getRealIpAddr();
		String mac = "";
		ShiroUser shiroUser = ShiroUtils.currentUser();
		int userid = shiroUser.getUserId();
		List<Integer> list = new ArrayList<>();
		wxArticles.setIp(ip);
		wxArticles.setMac(mac);
		wxPushMapper.updateNews(wxArticles);
		if (selectType == 2) {
			WxArticles articles = new WxArticles();
			List<WxArticles> pid = wxPushMapper.queryNewsByPid(wxArticles.getId());
			if (pid != null && pid.size() != 0) {
				for (WxArticles es : pid) {
					list.add(es.getId());
				}
			}
			if (wxArticles != null) {
				for (int i = 0; i < newcount; i++) {
					if (wxNewsList.get(i).getSort() < 1) {
						throw new Exception("保存失败,存在错误的图文消息顺序");
					}
					articles = new WxArticles();
					articles.setDescription(wxNewsList.get(i).getDescription());
					articles.setPicurl(wxNewsList.get(i).getPicurl());
					articles.setUrl(wxNewsList.get(i).getUrl());
					articles.setTitle(wxNewsList.get(i).getTitle());
					articles.setParentId(wxArticles.getId());
					articles.setCreateTime(wxArticles.getCreateTime());
					articles.setStatus(2);
					articles.setCreateUser(wxArticles.getCreateUser());
					articles.setUpdateTime(d);
					articles.setUpdateUser(userid);
					articles.setIp(ip);
					articles.setMac(mac);
					articles.setSort(wxNewsList.get(i).getSort());
					if (wxPushMapper.insterNews(articles) != 1)
						throw new Exception("数据插入错误");
				}
				if (list.size() > 0) {
					String temp = list.toString();
					String ids = temp.substring(1, temp.length() - 1);
					wxPushMapper.deleteNewsByPid(ids);
				}
			}
		}
		return 1;
	}

	
	public List<WxArticles> queryNewsById(Integer id) {

		WxArticles wxArticles = wxPushMapper.queryById(id);
		List<WxArticles> articles = new ArrayList<WxArticles>();
		if (wxArticles != null) {
			articles = wxPushMapper.queryNewsByPid(wxArticles.getId());
		}
		return articles;
	}

	
	public int deleteNews(Integer selectId, int type) {
		return wxPushMapper.deleteNews(selectId, type);
	}

	@SuppressWarnings("unchecked")
	
	public Map<String, Object> saveAndPushNews(Integer selectId, int selectType) throws Exception {
		Date d = new Date();
		String ip = WxUtils.getRealIpAddr();
		String mac = "";
		ShiroUser shiroUser = ShiroUtils.currentUser();
		int userid = shiroUser.getUserId();
		AutoMessage autoMessage = new AutoMessage();
		LinkedHashMap<String, Object> map = null;
		String uuid = UUID.randomUUID().toString();
		WxArticles articles = wxPushMapper.queryById(selectId);
		if (selectType == 3) {
			autoMessage = new AutoMessage();
			autoMessage.setMessage(articles.getDescription());
			autoMessage.setType(selectType);
			autoMessage.setSerialNumber(uuid);
			map = (LinkedHashMap<String, Object>) HttpClientUtils.getRomoteObject(new HttpClient(), WxContants.PUSH_URL, autoMessage);
			articles.setPushNum((int) map.get("num"));
			articles.setPushTime(d);
			articles.setPushNumTotal((int) map.get("total"));
			articles.setPushUser(userid);
			articles.setSerialNnumber(uuid);
			articles.setIp(ip);
			articles.setMac(mac);
			articles.setStatus(1);
			wxPushMapper.updateNewsStatus(articles);
		} else {
			List<WxArticles> list = new ArrayList<WxArticles>();

			list.add(articles);
			List<WxArticles> list2 = wxPushMapper.queryNewsByPid(selectId);
			list.addAll(list2);
			List<WxNews> wxNewsList = new ArrayList<>();
			for (WxArticles wx : list) {
				if (!StringUtils.isNullOrEmpty(wx.getPicurl()))
					wx.setPicurl(WxBase.IMAGE_URL + wx.getPicurl());
			}
			int count = list.size();
			if (count > 0) {
				for (WxArticles warticles : list) {
					WxNews wxNews = new WxNews();
					wxNews.setDescription(warticles.getDescription());
					wxNews.setTitle(warticles.getTitle());
					wxNews.setPicurl(warticles.getPicurl());
					wxNews.setUrl(warticles.getUrl());
					wxNewsList.add(wxNews);
				}
				String json = WxUtils.bean2Json(wxNewsList);
				autoMessage.setMessage(json);
				autoMessage.setType(1);
				autoMessage.setSerialNumber(uuid);
				map = (LinkedHashMap<String, Object>) HttpClientUtils.getRomoteObject(new HttpClient(), WxContants.PUSH_URL, autoMessage);
				articles.setId(selectId);
				articles.setPushTime(d);
				articles.setPushNum((int) map.get("num"));
				articles.setPushNumTotal((int) map.get("total"));
				articles.setPushUser(userid);
				articles.setSerialNnumber(uuid);
				articles.setIp(ip);
				articles.setMac(mac);
				articles.setStatus(1);
				wxPushMapper.updateNewsStatus(articles);
			}
		}
		return map;
	}

	
	public int saveByCopy(Integer selectId, int selectType) {
		WxArticles articles = new WxArticles();
		articles.setId(selectId);
		articles.setIp(WxUtils.getRealIpAddr());
		articles.setType(selectType);
		articles.setMac("");
		articles.setParentId(null);
		articles.setPushUser(0);
		if (selectType == 2) {
			wxPushMapper.insertByCopy(articles);
			int id = articles.getId();
			articles = new WxArticles();
			articles.setId(selectId);
			articles.setIp(WxUtils.getRealIpAddr());
			articles.setType(selectType);
			articles.setMac("");
			articles.setParentId(null);
			articles.setParentId(id);
			articles.setPushUser(0);
			return wxPushMapper.insertByCopy(articles);
		}
		return wxPushMapper.insertByCopy(articles);
	}

	public void updateNewsStatus(WxArticles articles) {
		wxPushMapper.updateNewsStatus(articles);

	}

	
	public int queryPushIng() {

		return wxPushMapper.queryPushIng();
	}
}
