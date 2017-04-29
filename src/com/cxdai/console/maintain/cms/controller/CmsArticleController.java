package com.cxdai.console.maintain.cms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.WxContants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.constant.CmsConstant;
import com.cxdai.console.maintain.cms.entity.CmsArticle;
import com.cxdai.console.maintain.cms.entity.CmsArticleCnd;
import com.cxdai.console.maintain.cms.entity.CmsChannel;
import com.cxdai.console.maintain.cms.entity.CmsOperateLog;
import com.cxdai.console.maintain.cms.service.CmsArticleService;
import com.cxdai.console.maintain.cms.service.CmsChannelService;
import com.cxdai.console.maintain.cms.service.CmsOperateLogService;
import com.cxdai.console.maintain.cms.util.FileLoadUtil;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.JsonUtils;
import com.cxdai.console.util.PropertiesUtil;

/**
 * <p>
 * Description:文章操作类<br />
 * </p>
 * @title CmsArticleAction.java
 * @package com.cxdai.console.cms.action
 * @author 邹理享
 * @version 0.1 2015年1月28日
 */
@Controller
@RequestMapping(value = "/cmsArticle")
public class CmsArticleController extends BaseController {

	Logger logger = LoggerFactory.getLogger(CmsArticleController.class);
	/**
	 * @fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;

	@Autowired
	private CmsArticleService cmsArticleService;

	@Autowired
	private CmsChannelService cmsChannelService;

	@Autowired
	private CmsOperateLogService cmsOperateLogService;

	@RequestMapping(value = "/toCmsSarticleMain")
	@ResponseBody
	public ModelAndView toCmsChannelMain() {
		return forword("/maintain/cms/cmsSarticleMain");
	}

	@RequestMapping(value = "/toCmsSarticleAdd")
	@ResponseBody
	public ModelAndView toCmsChannelAdd() {
		return forword("/maintain/cms/cmsSarticleAdd").addObject("cmsChannelPath", FileLoadUtil.ROOTUPLOADPATH);
	}

	@RequestMapping(value = "/searchCmsSarticleList/{pageNo}")
	@ResponseBody
	public ModelAndView searchCmsSarticleList(@ModelAttribute CmsArticleCnd cmsArticleCnd, @PathVariable("pageNo") Integer pageNo) {
		Page page = new Page();
		try {
			page = cmsArticleService.searchCmsArticlePageByCnd(cmsArticleCnd, pageNo, WxContants.WX_PAGE_SIZE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forword("/maintain/cms/cmsSarticleList").addObject("page", page);
	}

	@RequestMapping(value = "/initCmsChannel")
	@ResponseBody
	public StringBuffer initCmsChannel() {
		StringBuffer selectList = new StringBuffer();
		try {
			List<CmsChannel> list = cmsChannelService.queryCmsChannelList();
			for (int i = 0; i < list.size(); i++) {
				selectList.append(list.get(i).getId() + "," + list.get(i).getName() + "|");
			}
		} catch (Exception e) {
			logger.error("获取栏目列表失败", e);
		}
		return selectList;
	}

	@RequestMapping(value = "/removeCmsArticle")
	@ResponseBody
	public MessageBox removeCmsArticle(String cmsArticleIdsStr, HttpServletRequest request) {
		try {
			String[] cmsArticleIds = StringUtils.splitByWholeSeparator(cmsArticleIdsStr, ",");
			cmsArticleService.deleteCmsArticleByIds(cmsArticleIds);
			Integer addBy = currentUser().getUserId();
			String ip = HttpTookit.getRealIpAddr(request);
			String mac = HttpTookit.getRealMac(ip);
			String remark = "";
			for (int i = 0; i < cmsArticleIds.length; i++) {
				cmsOperateLogService.save(new CmsOperateLog(Integer.parseInt(cmsArticleIds[i]), CmsConstant.ARTICLESOURCETYPE, CmsConstant.CMSDELETE, addBy, new Date(), ip, mac, remark));
			}
			return MessageBox.build("1", "删除文章成功");
		} catch (Exception e) {
			logger.error("删除文章出错", e);
		}
		return MessageBox.build("0", "删除文章失败");
	}

	@RequestMapping(value = "/saveCmsArticle")
	@ResponseBody
	public MessageBox saveCmsArticle(@ModelAttribute CmsArticle cmsArticle, HttpServletRequest request) {
		try {
			Integer addBy = currentUser().getUserId();
			cmsArticle.setCreateBy(addBy);
			cmsArticle.setUpdateBy(addBy);
			// 获取ip
			String ip = HttpTookit.getRealIpAddr(request);
			String mac = HttpTookit.getRealMac(ip);
			String remark = "";
			if (cmsArticle.getId().intValue() != 0) {
				cmsArticleService.updateCmsArticle(cmsArticle);
				cmsOperateLogService.save(new CmsOperateLog(cmsArticle.getId(), CmsConstant.ARTICLESOURCETYPE, CmsConstant.CMSUPDATE, addBy, new Date(), ip, mac, remark));
			} else {
				cmsArticleService.saveCmsArticle(cmsArticle);
				cmsOperateLogService.save(new CmsOperateLog(cmsArticle.getId(), CmsConstant.ARTICLESOURCETYPE, CmsConstant.CMSADD, addBy, new Date(), ip, mac, remark));
			}
			return MessageBox.build("1", "保存成功");
		} catch (Exception e) {
			logger.error("保存文章出错", e);
		}
		return MessageBox.build("0", "保存失败");
	}

	@RequestMapping(value = "/toCmsSarticleEdit/{cmsArticleId}")
	@ResponseBody
	public ModelAndView toCmsSarticleEdit(@PathVariable("cmsArticleId") Integer cmsArticleId) {
		CmsArticle cmsArticle = cmsArticleService.getCmsArticleById(cmsArticleId);
		return forword("/maintain/cms/cmsSarticleEdit").addObject("CmsArticle", cmsArticle).addObject("cmsChannelPath", FileLoadUtil.ROOTUPLOADPATH);
	}

	@RequestMapping(value = "/updateTop")
	@ResponseBody
	public MessageBox updateTop(Integer cmsArticleId, Integer topStaus, HttpServletRequest request) {
		try {
			cmsArticleService.updateTopById(cmsArticleId, topStaus);
			Integer addBy = currentUser().getUserId();
			String ip = HttpTookit.getRealIpAddr(request);
			String mac = HttpTookit.getRealMac(ip);
			String remark = topStaus == 0 ? "取消置顶" : "置顶";
			cmsOperateLogService.save(new CmsOperateLog(cmsArticleId, CmsConstant.ARTICLESOURCETYPE, CmsConstant.CMSTOP, addBy, new Date(), ip, mac, remark));
			return MessageBox.build("1", "成功");
		} catch (Exception e) {
			logger.error("修改文章置顶失败", e);
		}
		return MessageBox.build("0", "失败");
	}

	@RequestMapping(value = "/uploadpic", produces = { "text/plain;charset=UTF-8" })
	@ResponseBody
	public String upload(@RequestParam("files") MultipartFile[] files) throws Exception {
		MessageBox msg = null;
		try {
			// String cmsUploadPath = currentRequest().getSession().getServletContext().getRealPath(PropertiesUtil.getValue("www_ss_upload"));
			String cmsUploadPath = PropertiesUtil.getValue("cms_upload_path");
			msg = FileLoadUtil.upload(files[0], cmsUploadPath, CmsConstant.ARTICLEFILEDIC, CmsConstant.fileSizeLimit);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("文章图片导入错误信息：" + e);
			msg = MessageBox.build("1", "图片上传失败!!");
		}
		return JsonUtils.bean2Json(msg);
	}
}
