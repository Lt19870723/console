package com.cxdai.console.sycee.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.sms.service.SmsSendService;
import com.cxdai.console.sycee.entity.SyceeAddress;
import com.cxdai.console.sycee.entity.SyceeExchange;
import com.cxdai.console.sycee.service.SyceeExchangeService;
import com.cxdai.console.sycee.vo.SyceeExchangeCnd;
import com.cxdai.console.sycee.vo.SyceeExchangeVo;
import com.cxdai.console.util.DateUtils;

/**
 * 客户管理 -元宝明细-元宝兑换统计
 * <p>
 * Description: <br />
 * </p>
 * 
 * @title SyceeExchangeController.java
 * @package com.cxdai.console.sycee.controller
 * @author yubin
 * @version 0.1 2015年10月22日
 */
@Controller
@RequestMapping(value = "/account/syceeExchange")
public class SyceeExchangeController extends BaseController {

	private static final Logger logger = Logger.getLogger(SyceeExchangeController.class);

	@Autowired
	private SyceeExchangeService syceeExchangeService;

	@Autowired
	SmsSendService smsSendService;

	/**
	 * 元宝兑换统计主界面
	 * <p>
	 * Description:元宝兑换统计主界面<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年10月22日
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView forSyceeChangeMain() {
		return new ModelAndView("/sycee/syceeExchange/main");
	}

	/**
	 * 元宝兑换统计列表
	 * <p>
	 * Description:元宝兑换统计列表<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年10月22日
	 * @param cnd
	 * @param pageNo
	 * @return ModelAndView
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView forSyceeChangeList(@ModelAttribute SyceeExchangeCnd cnd, @PathVariable Integer pageNo) {
		Page page = new Page(pageNo, Constants.CONSOLE_PAGE_SIZE2);
		try {
			page = syceeExchangeService.pageQuery(cnd, page);
		} catch (Exception e) {
			logger.error("查询元宝兑换列表异常：" + e);
		}
		return new ModelAndView("/sycee/syceeExchange/list").addObject("page", page);
	}

	/**
	 * 导出Excel数据统计功能
	 * <p>
	 * Description:导出Excel数据统计功能<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年10月22日
	 * @param borrowCnd
	 * @return MessageBox
	 */
	@RequestMapping("/count")
	@ResponseBody
	public MessageBox count(@ModelAttribute SyceeExchangeCnd cnd) {
		List<SyceeExchange> list = null;
		try {

			list = syceeExchangeService.findAll(cnd);
		} catch (Exception e) {
			logger.error(e);
			return MessageBox.build("1", "网络异常，刷新后重试！");
		}
		if (null == list || list.size() == 0) {
			return MessageBox.build("1", "没有数据");
		}
		if (list.size() > 50000) {
			return MessageBox.build("1", "本次导出的数据量大于50000条，无法导出，如须导出请与技术人员联系！");
		}
		return new MessageBox("0", String.valueOf(list.size()));
	}

	/**
	 * 导出Excel数据功能
	 * <p>
	 * Description:导出Excel数据功能<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年10月22日
	 * @param cnd
	 * @param request
	 * @param response
	 *            void
	 */
	@RequestMapping("/exportToExcel")
	public void exportToExcel(@ModelAttribute SyceeExchangeCnd cnd, HttpServletResponse response) {
		try {

			List<SyceeExchange> list = syceeExchangeService.findAll(cnd);
			if (null == list || list.size() == 0) {
				return;
			}
			if (list.size() > 50000) {
				return;
			}
			Map<Object, Object> dto = new HashMap<Object, Object>();
			ReportData reportData = new ReportData();
			reportData.setParametersDto(dto);
			reportData.setFieldsList(list);
			reportData.setReportFilePath("/report/exportExcel/syceeExchange.jasper");

			java.io.InputStream is = currentSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
			// 这里可以传入pdf,excel,word,html,print导出各种类型文件
			JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, currentRequest(), response,
					"元宝兑换记录" + DateUtils.format(new Date(), DateUtils.YMD));
		} catch (Exception e) {
			logger.error("元宝兑换记录", e);
		}
	}

	/**
	 * 商品兑换处理页面
	 * <p>
	 * Description:商品兑换-兑换处理<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年10月23日
	 * @param id
	 * @return ModelAndView
	 */
	@RequestMapping("/deal")
	public ModelAndView deal(String id, String opt) {
		ModelAndView mv = new ModelAndView("/sycee/syceeExchange/deal");
		try {
			SyceeExchangeVo vo = syceeExchangeService.getSyceeExchangeById(Integer.valueOf(id));
			logger.error("当前用户的vouserID为：---------------"+vo.getUserId());
			SyceeAddress address = syceeExchangeService.getAddresssById(vo.getUserId());
			mv.addObject("vo", vo);
			mv.addObject("address", address);
			logger.error("当前用户的联系方式为：---------------"+address);
		} catch (Exception e) {
			logger.error("兑换处理异常:", e);
		}
		return mv.addObject("id", id).addObject("opt", opt);
	}

	/**
	 * 商品兑换-处理
	 * <p>
	 * Description:商品兑换-处理<br />
	 * </p>
	 * 
	 * @author yubin
	 * @version 0.1 2015年10月23日
	 * @param syc
	 * @return MessageBox
	 */
	@RequestMapping("/dealResult")
	@ResponseBody
	public MessageBox dealResult(@ModelAttribute SyceeExchange syc) {
		if (syc.getId() == null) {
			return new MessageBox("0", "无数据操作");
		}
		try {
			int i = syceeExchangeService.updateByPrimaryKeySelective(syc);
			if (i > 0) {
				return new MessageBox("1", "成功处理");
			}
		} catch (Exception e) {
			logger.error("元宝兑换审核失败", e);
		}
		return new MessageBox("0", "处理失败");
	}

	@RequestMapping("/sendMsg")
	@ResponseBody
	public MessageBox sendMsg(int exchangeId) {
		try {
			SyceeExchangeVo vo = syceeExchangeService.getSyceeExchangeById(exchangeId);
			HashMap smsMap = new HashMap<String, Object>();
			smsMap.put("goodsName", vo.getName());
			smsMap.put("goodsRemark", vo.getGoodsRemark());
			smsSendService.saveSms("0.0.0.1", 602, smsMap, vo.getMobilenum());
		} catch (Exception e) {
			logger.error("兑换短信发送失败", e);
			return new MessageBox("0", "短信发送失败，请联系管理员");
		}
		return new MessageBox("1", "短信已发送");
	}

}
