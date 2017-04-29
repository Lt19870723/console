package com.cxdai.console.firstborrow.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.firstborrow.service.FirstTenderRealService;
import com.cxdai.console.firstborrow.vo.FirstTenderRealCnd;
import com.cxdai.console.firstborrow.vo.FirstTenderRealVo;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;

/**
 * <p>
 * Description:直通车管理 - 直通车列表<br />
 * </p>
 * 
 * @title FirstTenderRealController.java
 * @package com.cxdai.console.first.controller
 * @author tongjuxing
 * @version 0.1 2015年6月25日
 */
@Controller
@RequestMapping(value = "/firstborrow/firsttenderreal")
public class FirstTenderRealController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(FirstTenderRealController.class);

	
	@Autowired
	private FirstTenderRealService firstTenderRealService;

	private final int pageSize = 10;

	@RequestMapping(value = "/main")
	public ModelAndView main() {
		return forword("/firstborrow/firsttenderreal/main");
	}
	
	@RequestMapping(value = "/sum")
	@ResponseBody
	public Map<String,Object> getCount(@ModelAttribute FirstTenderRealCnd firstTenderRealCnd) {
		Map<String,Object> result = new HashMap<String,Object>();
		
		try {
			
			BigDecimal firstTenderRealAccount = firstTenderRealService.queryFirstTenderRealAccount(firstTenderRealCnd);
			DecimalFormat df1 = new DecimalFormat("#,##0");
			result.put("firstTenderRealAccount",df1.format(firstTenderRealAccount));
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	@RequestMapping(value = "/list/{pageNo}")
	public ModelAndView list(@ModelAttribute FirstTenderRealCnd firstTenderRealCnd,
			@PathVariable("pageNo") Integer pageNo) {
		Page page = new Page();
		try {
			firstTenderRealCnd.setOrderSql(" ORDER BY tr.ID DESC");
			page = firstTenderRealService.queryPageListByCnd(firstTenderRealCnd, pageNo, pageSize);
			
		} catch (Exception e) {
			logger.error(e);
		}
		return forword("/firstborrow/firsttenderreal/list").addObject("page", page);
	}

	/**
	 * <p>
	 * Description:验证添加方法<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年6月29日
	 * @param cardFile
	 * @param request
	 * @return AjaxJson
	 */
	@RequestMapping(value = "/validateexport")
	@ResponseBody
	public MessageBox validateExport(@ModelAttribute FirstTenderRealCnd firstTenderRealCnd) {
		try {
			// 排序方法
			firstTenderRealCnd.setOrderSql(" ORDER BY tr.ID asc");
			List<FirstTenderRealVo> list = firstTenderRealService.findFirstTenderRealVoToExcel(firstTenderRealCnd);
			if (list.size() > 50000) {
				return MessageBox.build("1", "本次导出的数据量大于50000条，无法导出，如须导出请与技术人员联系！");
			}
			return MessageBox.build("0", "");
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "系统异常，请稍候重试或联系管理员！");
		}
		
	}

	@RequestMapping("/exportexcel")
	public void exportExcel(@ModelAttribute FirstTenderRealCnd firstTenderRealCnd,HttpServletRequest request,HttpServletResponse response) {
		try {
			// 排序方法
			firstTenderRealCnd.setOrderSql(" ORDER BY tr.ID asc");
			List<FirstTenderRealVo> list = firstTenderRealService.findFirstTenderRealVoToExcel(firstTenderRealCnd);

			ReportData reportData = new ReportData();
			reportData.setParametersDto(new HashMap<Object, Object>());
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/firstTenderRealReportExcel.jasper");

			java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response,
					"直通车报表" + DateUtils.format(new Date(), DateUtils.YMD));
			
		} catch (Exception e) {
			logger.error(e);
		}
	}

	
	/**
	 * <p>
	 * Description:解除直通车<br />
	 * </p>
	 * 
	 * @author tongjuxing
	 * @version 0.1 2015年7月6日
	 * @param request
	 * @param id
	 * @return MessageBox
	 * @throws Exception 
	 */
	@RequestMapping(value = "/unlock/{id}/{userId}")
	@ResponseBody
	public MessageBox unlock(@PathVariable("id") Integer id,
			@PathVariable("userId") Integer userId) throws Exception {
		try {
			// 申请解锁
			String resultMsg = firstTenderRealService.saveUnlockManual(id, userId);
			if ("success".equals(resultMsg)) {
				// 解锁自动审核
				resultMsg = firstTenderRealService.saveApprovedPass(String.valueOf(id), "后台自动审核", userId, currentUser(), HttpTookit.getRealIpAddr(currentRequest()));
			}

			if ("success".equals(resultMsg)){
				return MessageBox.build("0", "解除直通车锁定成功！");
			}else{
				return MessageBox.build("1", resultMsg);
			}
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "系统异常，请稍候重试或联系管理员！");
		}
	}

}