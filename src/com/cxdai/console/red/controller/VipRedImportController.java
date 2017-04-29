package com.cxdai.console.red.controller;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.Dictionary;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.red.entity.VipRedImport;
import com.cxdai.console.red.mapper.VipRedImportMapper;
import com.cxdai.console.red.service.VipRedImportService;
import com.cxdai.console.red.service.VipRedViewService;
import com.cxdai.console.red.util.WDWUtil;
import com.cxdai.console.red.vo.VipRedImportCnd;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.HttpTookit;

/**
 * 红包管理-贵族特权红包发放
 * 
 * @author liutao
 * @Date 2015-11-11
 */
@Controller
@RequestMapping(value = "/vipredImport")
public class VipRedImportController extends BaseController {
	private static final Logger logger = Logger.getLogger(VipRedImportController.class);
	@Autowired
	private MemberService memberService;
	@Autowired
	private VipRedImportService vipRedImportService;
	@Autowired
	private VipRedViewService vipRedViewService;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private VipRedImportMapper vipRedImportMapper;

	@RequestMapping("/main")
	public ModelAndView forVipRedImportMain() {
		return new ModelAndView("/red/vipredimport/main");
	}

	@RequestMapping("/layer")
	public ModelAndView forVipRedlayer() {
		Map<String, List<VipRedImport>> map = vipRedImportService.selectRedMoney();

		return new ModelAndView("/red/vipredimport/layer").addObject("redInfos", map);
	}

	@RequestMapping("/import")
	public ModelAndView forVipRedImport() {
		Collection<Configuration> redtypes = Dictionary.getValues(1900);
		return new ModelAndView("/red/vipredimport/import").addObject("redtypes", redtypes);
	}

	@RequestMapping("/viewmain")
	public ModelAndView forVipRedImportViewMain(@ModelAttribute VipRedImportCnd vipRedImportCnd) {
		return new ModelAndView("/red/vipredimport/viewmain").addObject("id", vipRedImportCnd.getId());
	}

	/**
	 * 红包管理-点击总记录数链接查询贵族特权红包发放
	 * 
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@RequestMapping("/viewList/{pageNo}")
	public ModelAndView searchVipRedViewList(@ModelAttribute VipRedImportCnd vipRedImportCnd, @PathVariable Integer pageNo) {
		Page page = null;
		Collection<Configuration> configurations = null;
		try {
			page = vipRedViewService.queryVipRedViewVoList(vipRedImportCnd, Constants.CONSOLE_PAGE_SIZE, pageNo);
			configurations = Dictionary.getValues(1900);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询红包记录错误信息：" + e.getMessage());
		}
		return new ModelAndView("/red/vipredimport/viewlist").addObject("page", page).addObject("redTypes", configurations);
	}

	/**
	 * 红包管理-查询贵族特权红包发放
	 * 
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchVipRedImport(@ModelAttribute VipRedImportCnd vipRedImportCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			page = vipRedImportService.queryVipRedImportVoList(vipRedImportCnd, Constants.CONSOLE_PAGE_SIZE, pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询红包记录错误信息：" + e.getMessage());
		}
		return new ModelAndView("/red/vipredimport/list").addObject("page", page);
	}

	/**
	 * 红包管理-发送贵族特权红包
	 * 
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@RequestMapping("/grant")
	@ResponseBody
	public MessageBox RedGrant(@ModelAttribute VipRedImport vo) {
		String result = "发送成功";
		if (vo.getId() == null) {
			return new MessageBox("1", "ID不能为空");
		}
		VipRedImport red = vipRedImportMapper.queryVipRedImportById(vo.getId());
		if (red == null || red.getStatus().intValue() == 1) {
			return new MessageBox("1", "此记录已变更，请刷新页面！");
		}
		try {
			// 发送红包
			vipRedImportService.grantRed(vo.getId().toString(), HttpTookit.getRealIpAddr(currentRequest()));
		} catch (Exception e) {
			e.printStackTrace();
			return MessageBox.build("1", "系统出错，请稍后重试");
		}
		return new MessageBox("0", result);
	}

	/**
	 * 红包管理-单个新增贵族特权红包
	 * 
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@RequestMapping("/submit")
	@ResponseBody
	public MessageBox forVipRedSubmit(HttpServletRequest request) throws Exception {
		String result = "新增成功";
		if (null == request.getParameter("useName")) {
			return MessageBox.build("1", "用户名不能为空");
		}
		if (null == request.getParameter("remark")) {
			return MessageBox.build("1", "备注不能为空");
		}
		if (null == request.getParameter("redType")) {
			return MessageBox.build("1", "红包类型不能为空");
		}
		if (request.getParameter("redType").equals("2") && null == request.getParameter("redMoney")) {
			return MessageBox.build("1", "红包金额不能为空");
		}
		String useName = request.getParameter("useName").trim();
		String remark = request.getParameter("remark").trim();
		String redType = request.getParameter("redType").trim();
		String redMoney = "";
		if (redType.equals("1970")) {
			redMoney = request.getParameter("newRedMoney").trim();
		} else if (redType.equals("1990")) {
			redMoney = request.getParameter("redMoney").trim();
		} else if (redType.equals("1930")) {
			redMoney = request.getParameter("vipRedMoney").trim();
		} else if (redType.equals("1980")) {
			redMoney = request.getParameter("interRedMoney").trim();
		} else if (redType.equals("2000")) {
			redMoney = request.getParameter("rewardRedMoney").trim();
		} else if (redType.equals("2010")) {
			redMoney = request.getParameter("huodongRedMoney").trim();
		}
		MemberVo memberVo = memberMapper.queryMemberVoByUsername(useName.trim());
		if (null == memberVo) {
			return MessageBox.build("1", "您输入的用户名在系统中不存在，请重新输入");
		}
		vipRedImportService.forVipRedSubmit(useName, remark, memberVo.getId(), redType, redMoney);
		return new MessageBox("0", result);
	}

	/**
	 * 红包管理-贵族特权红包发放导入
	 * 
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@RequestMapping(value = "/importRed", method = RequestMethod.POST)
	public ModelAndView importRed(@RequestParam("filename") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strAlertMsg = "";
		ModelAndView mv = new ModelAndView("/red/vipredimport/import");
		String info = request.getParameter("info");
		String redType = request.getParameter("redType");
		// 判断文件是否为空
		if (file == null) {
			strAlertMsg = "文件为空";
			return mv.addObject("msg", strAlertMsg);
		}
		String fileName = file.getOriginalFilename();
		// 判断文件大小、即名称
		long size = file.getSize();
		if (fileName == null || ("").equals(fileName) && size == 0) {
			strAlertMsg = "文件名或大小为空";
			return mv.addObject("msg", strAlertMsg);
		}
		if (size > (5 * 1024 * 1024)) {
			strAlertMsg = "文件大小不能大于5M";
			return mv.addObject("msg", strAlertMsg);
		}

		// 验证文件名是否合格
		if (!validateExcel(fileName)) {
			strAlertMsg = "文件名不是excel2003版本格式";
			return mv.addObject("msg", strAlertMsg);
		}
		InputStream in = file.getInputStream();
		List<VipRedImport> importReds = vipRedImportService.importRed(in, info, redType);
		for (VipRedImport vipRedImport : importReds) {
			if (null != vipRedImport && StringUtils.isNotEmpty(vipRedImport.getStrAlertMsg())) {
				return mv.addObject("msg", vipRedImport.getStrAlertMsg());
			}
		}
		int count = 0;
		if (null != importReds && importReds.size() > 0) {
			count = importReds.size();
		}
		if (count != 0) {
			strAlertMsg = "成功导入" + count + "条！";
		} else {
			strAlertMsg = "导入失败！";
		}
		return mv.addObject("msg", strAlertMsg);
	}

	/**
	 * 红包管理-删除
	 * 
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@RequestMapping("/deleteImport")
	@ResponseBody
	public MessageBox deleteImport(@ModelAttribute VipRedImport vo) {
		String result = "删除成功";
		if (vo.getId() == null) {
			return new MessageBox("1", "ID不能为空");
		}
		try {
			// 发送红包
			vipRedImportService.deleteImport(vo.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return MessageBox.build("1", "系统出错，请稍后重试");
		}
		return new MessageBox("0", result);
	}

	/**
	 * 红包管理-验证EXCEL文件
	 * 
	 * @author liutao
	 * @Date 2015-11-11
	 */
	public boolean validateExcel(String filePath) {
		if (filePath == null || !(WDWUtil.isExcel2003(filePath))) {
			return false;
		}
		return true;
	}
}
