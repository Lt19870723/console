package com.cxdai.console.maintain.portal.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
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
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.constant.CmsConstant;
import com.cxdai.console.maintain.cms.util.FileLoadUtil;
import com.cxdai.console.maintain.portal.entity.Slideshow;
import com.cxdai.console.maintain.portal.entity.SlideshowCnd;
import com.cxdai.console.maintain.portal.entity.SlideshowOperateLog;
import com.cxdai.console.maintain.portal.service.SlideshowService;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.JsonUtils;
import com.cxdai.console.util.PropertiesUtil;

/**
 * 幻灯片管理
 * 
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/maintain/slideshow")
public class SlideshowController extends BaseController {

	private static final Logger logger = Logger.getLogger(SlideshowController.class);

	@Autowired
	private SlideshowService slideshowService;

	/**
	 * 进入幻灯片管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView toSlideshowList() {
		return new ModelAndView("/maintain/portal/slideshow/main");
	}

	/**
	 * 幻灯片管理列表
	 * 
	 * @param slideshowCnd
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/searchslideshowList/{pageNo}")
	public ModelAndView searchslideshowList(@ModelAttribute SlideshowCnd slideshowCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			page = slideshowService.queryPageSlideshowListByCnd(slideshowCnd, pageNo, Constants.CONSOLE_PAGE_SIZE2);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询幻灯片管理列表错误信息：" + e);
		}
		return new ModelAndView("/maintain/portal/slideshow/list").addObject("page", page);
	}

	/**
	 * 删除-逻辑删除，修改状态
	 * 
	 * @param record
	 * @param slideshowId
	 * @return
	 */
	@RequestMapping("/deleteSlideshow/{slideshowId}")
	@ResponseBody
	public MessageBox deleteSlideshow(@ModelAttribute Slideshow record, @PathVariable Integer slideshowId, HttpServletRequest request) {
		UserVo userVo = getCurrentUserVo();
		Integer addBy = userVo.getId();
		String ip = HttpTookit.getRealIpAddr(request);
		String mac = "####"; // HttpTookit.getRealMac(ip);
		String remark = "";
		String mesg = null;

		record.setId(slideshowId);
		record.setStatus(1);
		record.setOrder(0);

		try {
			slideshowService.updateByPrimaryKeySelective(record);
			slideshowService
					.save(new SlideshowOperateLog(slideshowId, CmsConstant.PORTALTYPE, CmsConstant.SLIDESHOWDELETE, addBy, new Date(), ip, mac, remark));
			mesg = "删除成功!!";
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageBox("1", "系统错误，删除失败，请稍后再试!!");
		}
		return new MessageBox("0", mesg);
	}

	/**
	 * 添加，修改--页面加载
	 * 
	 * @param slideshowId
	 * @return
	 */
	@RequestMapping("/toSaveSlideshow/{slideshowId}/{type}")
	public ModelAndView toSaveSlideshow(@PathVariable Integer slideshowId, @PathVariable String type) {
		Slideshow slideshow;
		// 添加，修改判断
		if (slideshowId != 0) {
			slideshow = slideshowService.selectByPrimaryKey(slideshowId);
		} else {
			slideshow = new Slideshow();
		}
		return new ModelAndView("/maintain/portal/slideshow/add").addObject("slideshow", slideshow).addObject("type", type)
				.addObject("slideshowId", slideshowId).addObject("slideImagePath", FileLoadUtil.ROOTUPLOADPATH);
	}

	/**
	 * 添加，修改
	 * 
	 * @param slideshow
	 * @param slideshowId
	 * @return
	 */
	@RequestMapping(value = "/saveSlideshow/{slideshowId}")
	@ResponseBody
	public MessageBox saveSlideshow(@ModelAttribute Slideshow slideshow, @PathVariable Integer slideshowId, HttpServletRequest request,
			@RequestParam("sType") Integer sType, @RequestParam("sImage") String sImage) {
		UserVo userVo = getCurrentUserVo();
		Integer addBy = userVo.getId();
		String msg = null;
		// 获取ip
		String ip = HttpTookit.getRealIpAddr(request);
		String mac = "####";
		String remark = "";
		slideshow.setsImage(sImage);
		slideshow.setsType(sType);
		// 标题重复 | 修改时，标题相同不是重复； | 数据库查询标题相同的数据
		SlideshowCnd slideshowCnd1 = new SlideshowCnd();
		slideshowCnd1.setTitle(slideshow.getTitle().toString().trim());
		Slideshow slideshowUpdate1 = slideshowService.queryByParam(slideshowCnd1);
		if (slideshowUpdate1 != null) {
			if (slideshowId.intValue() != 0) { // 修改
				if (slideshowId != slideshowUpdate1.getId()) { // 判断是否同一条记录；
					msg = "标题已存在！";
					return MessageBox.build("1", msg);
				}
			} else {// 添加
				msg = "标题已存在！";
				return MessageBox.build("1", msg);
			}
		}

		// 图片为空
		if (StringUtils.isEmpty(slideshow.getsImage())) {
			msg = "图片不能为空！";
			return MessageBox.build("1", msg);
		}

		// 发布开始时间为空，则为服务器当前时间；
		if (slideshow.getStartTime() == null) {
			slideshow.setStartTime(new Date());
		} else {
			if (slideshowId.intValue() == 0) { // 添加时判断
				if (slideshow.getStartTime().getTime() < new Date().getTime()) {
					msg = "发布开始时间不能小于当前时间！";
					return MessageBox.build("1", msg);
				}
			}
		}

		// 发布结束时间为空，则+10年
		if (StringUtils.isEmpty(slideshow.getEndTime())) {
			Date time1 = new Date();
			slideshow.setEndTime(DateUtils.addYears(time1, 10));
		} else {
			if (slideshowId.intValue() == 0) { // 添加时判断
				if (slideshow.getEndTime().getTime() < new Date().getTime()) {
					msg = "发布结束时间不能小于当前时间！";
					return MessageBox.build("1", msg);
				}
			}
		}

		try {
			// 修改
			if (slideshowId.intValue() != 0) {
				if (slideshow.getOrder() == 0) {
					msg = "排序数字不能为0！";
					return MessageBox.build("1", msg);
				}
				// 排序数字查询，oldOrder未改变
				Integer oldOrder = slideshowService.selectByPrimaryKey(slideshowId).getOrder();
				if (!oldOrder.equals(slideshow.getOrder())) {
					SlideshowCnd slideshowCndUpdate = new SlideshowCnd();
					slideshowCndUpdate.setsOrder(slideshow.getOrder());
					slideshowCndUpdate.setsType(slideshow.getsType());
					Slideshow slideshowUpdate = slideshowService.queryByParam(slideshowCndUpdate);
					if (slideshowUpdate != null) {
						msg = "排序数字已存在，请设置为其它数字！";
						return MessageBox.build("1", msg);
					}
				}

				slideshowService.updateByPrimaryKeySelective(slideshow);
				slideshowService.save(new SlideshowOperateLog(slideshowId, CmsConstant.PORTALTYPE, CmsConstant.SLIDESHOWUPDATE, addBy, new Date(), ip, mac,
						remark));
			} else {
				// 添加
				slideshow.setStatus(0);
				slideshow.setCreateBy(addBy);
				slideshow.setCreateTime(new Date());

				// 获得最小排序数字；
				int type1 = slideshow.getsType();
				int orderNume = slideshowService.getMaxOrder(type1);
				slideshow.setOrder(orderNume - 1);

				slideshowService.saveSlideshow(slideshow);
				slideshowService
						.save(new SlideshowOperateLog(slideshowId, CmsConstant.PORTALTYPE, CmsConstant.SLIDESHOWADD, addBy, new Date(), ip, mac, remark));
			}

			msg = "保存成功!";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			msg = "保存失败!";
			return MessageBox.build("1", msg);
		}
		return new MessageBox("0", msg);
	}

	/**
	 * 图片上传
	 * 
	 * @param slideshow
	 * @param event
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload", produces = { "text/plain;charset=UTF-8" })
	@ResponseBody
	public String upload(@RequestParam("files") MultipartFile[] files, @RequestParam("sType") Integer sTypeNum) throws Exception {
		MessageBox msg = null;
		String slideshowfiledic = null;
		// String cmsUploadPath = currentRequest().getSession().getServletContext().getRealPath(PropertiesUtil.getValue("www_ss_upload"));
		String cmsUploadPath = PropertiesUtil.getValue("cms_upload_path");
		if (StringUtils.isEmpty(sTypeNum)) {
			msg = MessageBox.build("1", "请先选择类型!!");
		} else {
			if (sTypeNum == 1 || sTypeNum == 7) {
				slideshowfiledic = CmsConstant.SLIDESHOWFILEDIC1;
			}
			if (sTypeNum == 2) {
				slideshowfiledic = CmsConstant.SLIDESHOWFILEDIC2;
			}
			if (sTypeNum == 3 || sTypeNum == 4) {
				slideshowfiledic = CmsConstant.SLIDESHOWFILEDIC3;
			}
			if (sTypeNum == 5 || sTypeNum == 6) {
				slideshowfiledic = CmsConstant.SLIDESHOWFILEDIC4;
			}
			try {
				msg = FileLoadUtil.upload(files[0], cmsUploadPath, slideshowfiledic, CmsConstant.fileSizeLimit);
			} catch (Exception e) {
				logger.error(e);
				msg = MessageBox.build("1", "图片上传错误!!");
			}
		}
		return JsonUtils.bean2Json(msg);
	}

}
