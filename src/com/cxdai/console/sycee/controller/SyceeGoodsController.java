package com.cxdai.console.sycee.controller;

import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
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
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.Dictionary;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.maintain.cms.constant.CmsConstant;
import com.cxdai.console.maintain.cms.util.FileLoadUtil;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.sycee.entity.SyceeGoods;
import com.cxdai.console.sycee.entity.SyceeGoodsDiscount;
import com.cxdai.console.sycee.service.SyceeGoodService;
import com.cxdai.console.sycee.vo.SyceeGoodCnd;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.JsonUtils;
import com.cxdai.console.util.PropertiesUtil;
import com.cxdai.console.util.ShiroUtils;

/**
 * 内容维护-元宝商城-商品
 * <p>
 * Description: <br />
 * </p>
 * 
 * @title SyceeGoodsController.java
 * @package com.cxdai.console.sycee.controller
 * @author yubin
 * @version 0.1 2015年10月22日
 */
@Controller
@RequestMapping(value = "/account/syceeGoods")
public class SyceeGoodsController extends BaseController {

	private static final Logger logger = Logger.getLogger(SyceeGoodsController.class);

	@Autowired
	private SyceeGoodService syceeGoodService;

	/**
	 * 商品列表（全部）-主界面
	 * <p>
	 * Description:<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年10月22日
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main() {
		SyceeGoodsDiscount dis = syceeGoodService.getDiscount();
		return new ModelAndView("/sycee/syceeGoods/main").addObject("opt", "all").addObject("d", dis);
	}

	/**
	 * 商品审核列表（待审核）-主界面
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年10月30日
	 * @return ModelAndView
	 */
	@RequestMapping("/main2")
	public ModelAndView approveMain() {
		return new ModelAndView("/sycee/syceeGoods/main2").addObject("opt", "approve");
	}

	/**
	 * 商品列表
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年10月30日
	 * @param cnd
	 * @param pageNo
	 * @param opt
	 * @return ModelAndView
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView syceeGoodsList(@ModelAttribute SyceeGoodCnd cnd, @PathVariable Integer pageNo, String opt) {
		Page page = new Page(pageNo, Constants.CONSOLE_PAGE_SIZE2);
		try {
			page = syceeGoodService.pageQuery(cnd, page);
		} catch (Exception e) {
			logger.error("查询用户元宝明细异常：" + e);
		}
		return new ModelAndView("/sycee/syceeGoods/list").addObject("page", page).addObject("opt", opt);
	}

	/**
	 * 商品新增&编辑-初始化
	 * <p>
	 * Description:增加页面<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年10月23日
	 * @param id
	 * @return ModelAndView
	 */
	@RequestMapping("/add")
	public ModelAndView initAdd(Integer id) {
		ModelAndView mv = new ModelAndView("/sycee/syceeGoods/add");
		try {
			if (id != 0)
				mv.addObject("syceeGoods", syceeGoodService.selectByPrimaryKey(id));

			Collection<Configuration> e1 = Dictionary.getValues(1950);// 红包分类
			Collection<Configuration> e2 = Dictionary.getValues(20001);// 一级分类
			Collection<Configuration> e3 = Dictionary.getValues(20002);// 二级分类
			Collection<Configuration> e4 = Dictionary.getValues(20003);// 三级分类
			mv.addObject("slideImagePath", FileLoadUtil.ROOTUPLOADPATH).addObject("e1", e1).addObject("e2", e2).addObject("e3", e3).addObject("e4", e4);
		} catch (Exception e) {
			logger.error("初始化添加商品异常", e);
		}
		return mv;
	}

	/**
	 * 商品新增&编辑-提交
	 * <p>
	 * Description:添加商品<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年10月23日
	 * @param good
	 * @return MessageBox
	 */
	@RequestMapping("/save")
	@ResponseBody
	public MessageBox subAdd(@ModelAttribute SyceeGoods good, String subWay) {
		try {
			int i;
			// 设置有效期
			if (good.getValidDay() == null) {
				good.setValidDay(0);
			}
			// 提交审核
			if ("subAppr".equals(subWay)) {
				good.setApproveStatus(0);
			}
			if (good.getId() != null) {
				i = syceeGoodService.updateByPrimaryKeySelective(good);
			} else {
				i = syceeGoodService.insertSelective(good);
			}
			if (i > 0) {
				return new MessageBox("1", "操作成功");
			}
		} catch (Exception e) {
			logger.error("元宝商品新增&编辑-提交异常", e);
		}
		return new MessageBox("0", "操作失败");
	}

	/**
	 * 商品-详情
	 * <p>
	 * Description:<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年10月23日
	 * @param id
	 * @return ModelAndView
	 */
	@RequestMapping("/view")
	public ModelAndView view(int id, String opt) {
		ModelAndView mv = new ModelAndView("/sycee/syceeGoods/view");
		try {
			if (id != 0) {
				mv.addObject("syceeGoods", syceeGoodService.selectByPrimaryKey(id));
			}
		} catch (Exception e) {
			logger.error("查看商品详情异常", e);
		}

		return mv.addObject("opt", opt);
	}

	/**
	 * 商品-审核
	 * <p>
	 * Description:<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年10月23日
	 * @param good
	 * @return MessageBox
	 */
	@RequestMapping("/approve")
	@ResponseBody
	public MessageBox approve(@ModelAttribute SyceeGoods good) {
		try {
			ShiroUser user = ShiroUtils.currentUser();
			good.setApproveTime(new Date());
			good.setApproveUser(user.getUserId());
			good.setApproveUsername(user.getUserName());
			int i = syceeGoodService.updateByPrimaryKeySelective(good);
			if (i > 0) {
				return new MessageBox("1", "审核成功");
			}
		} catch (Exception e) {
			logger.error("审核操作", e);
		}
		return new MessageBox("0", "审核失败");
	}

	/**
	 * 商品-上下架
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * 
	 * @author huangpin
	 * @version 0.1 2015年10月30日
	 * @param good
	 * @return MessageBox
	 */
	@RequestMapping("/grounding")
	@ResponseBody
	public MessageBox grounding(@ModelAttribute SyceeGoods good) {
		int i;
		String message = "";
		try {
			good = syceeGoodService.selectByPrimaryKey(good.getId());
			if (good.getShowFlag() == 1) {
				good.setShowFlag(2);
				message = "已下架";
			} else {
				good.setShowFlag(1);
				message = "已上架";
			}
			i = syceeGoodService.updateByPrimaryKeySelective(good);
			if (i > 0) {
				return new MessageBox("1", message);
			}
		} catch (Exception e) {
			logger.error("异常。。。", e);
		}
		return new MessageBox("0", "操作失败");
	}

	/**
	 * 商品-图片上传
	 * <p>
	 * Description:图片上传<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年10月23日
	 * @param files
	 * @param sTypeNum
	 * @return
	 * @throws Exception
	 *             String
	 */
	@RequestMapping(value = "/upload", produces = { "text/plain;charset=UTF-8" })
	@ResponseBody
	public String upload(@RequestParam("files") MultipartFile[] files) throws Exception {
		MessageBox msg = null;
		String cmsUploadPath = PropertiesUtil.getValue("cms_upload_path");

		try {
			msg = FileLoadUtil.upload(files[0], cmsUploadPath, CmsConstant.SLIDESHOWFILEDIC2, CmsConstant.fileSizeLimit);
		} catch (Exception e) {
			logger.error(e);
			msg = MessageBox.build("1", "图片上传错误!!");
		}

		return JsonUtils.bean2Json(msg);
	}

	@RequestMapping("/discountDate")
	public ModelAndView discountDate() {
		SyceeGoodsDiscount dis = syceeGoodService.getDiscount();
		return new ModelAndView("/sycee/syceeGoods/discountDate").addObject("d", dis);
	}

	@RequestMapping("/discountDateSub")
	@ResponseBody
	public MessageBox discountDateSub(String beginDate, String endDate) {
		if (beginDate == null || beginDate.length() < 10 || endDate == null || endDate.length() < 10) {
			return MessageBox.build("0", "数据提交错误");
		}
		try {
			syceeGoodService.setDiscountDate(beginDate, endDate);
			return new MessageBox("1", "设置成功");
		} catch (Exception e) {
			logger.error("设置元宝商城折扣活动日期异常：" + e);
			return new MessageBox("0", "操作失败");
		}
	}
}
