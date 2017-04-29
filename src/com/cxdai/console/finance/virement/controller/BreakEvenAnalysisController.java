package com.cxdai.console.finance.virement.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.entity.BreakEvenAnalysis;
import com.cxdai.console.finance.virement.service.BreakEvenAnalysisService;
import com.cxdai.console.finance.virement.vo.BreakEvenCnd;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.ViewExcel;
//import com.cxdai.console.finance.virement.service.BreakEvenAnalysisService;
//import com.cxdai.console.finance.virement.vo.BreakEvenCnd;
import com.cxdai.console.util.WordAndExcelGenerator;

/**
 * 
 * <p>
 * Description:资金管理 - 收支管理 - 收支数据分析<br />
 * </p>
 * 
 * @title BreakEvenAnalysisController.java
 * @package com.cxdai.console.finance.virement.controller
 * @author Administrator
 * @version 0.1 2016年4月19日
 */
@Controller
@RequestMapping(value = "/finance/breakEvenAnalysis")
public class BreakEvenAnalysisController extends BaseController {
	private final static Logger logger = Logger
			.getLogger(BreakEvenAnalysisController.class);

	@Autowired
	private BreakEvenAnalysisService breakEvenAnalysis;

	@RequestMapping("/main")
	public ModelAndView CashRecordMain() {
		return new ModelAndView("/finance/breakEvenAnalysis/main");
	}

	/**
	 * 
	 * <p>
	 * Description:查询收支分析记录<br />
	 * </p>
	 * 
	 * @author fanhaijun
	 * @version 0.1 2016年4月19日
	 * @param breakEvenCnd
	 * @param pageNo
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageBreakEvenList(
			@ModelAttribute BreakEvenCnd breakEvenCnd,
			@PathVariable("pageNo") Integer pageNo) {
		Page page = null;
		try {
			page = breakEvenAnalysis.queryPageListByCnd(breakEvenCnd, pageNo,
					Constants.CONSOLE_PAGE_SIZE4);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("收支数据查询异常，原因：" + e);
		}
		return new ModelAndView("/finance/breakEvenAnalysis/list").addObject(
				"page", page);

	}

	/**
	 * 
	 * <p>
	 * Description:获取导出的数量<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2015年2月10日 void
	 */
	@RequestMapping("/count")
	@ResponseBody
	public MessageBox getExportCount(@ModelAttribute BreakEvenCnd breakEvenCnd,
			HttpServletRequest request, HttpServletResponse response) {
		List<BreakEvenAnalysis> list = null;
		try {
			list = breakEvenAnalysis.exportToExcel(breakEvenCnd);
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
	 * 
	 * <p>
	 * Description:导出Excel<br />
	 * </p>
	 * 
	 * @author yangshijin
	 * @version 0.1 2014年7月8日 void
	 * @throws ParseException
	 */
	@RequestMapping("/export_")
	public void exportToExcel(@ModelAttribute BreakEvenCnd breakEvenCnd,
			HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ParseException {
		req.setCharacterEncoding("utf-8");
		List<BreakEvenAnalysis> list = breakEvenAnalysis
				.exportToExcel(breakEvenCnd);
		Map<String, Object> map = new HashMap<String, Object>();
		// 生成Excel时，office会提示格式错误，动态设re置模板中ss:ExpandedRowCount属性，即可解决
		// http://blog.csdn.net/zhanwentao2/article/details/7298341
		map.put("rowSize", list == null ? 2 : list.size() + 2);
		map.put("table", list);
		// 提示：在调用工具类生成Word文档之前应当检查所有字段是否完整
		// 否则Freemarker的模板引擎在处理时可能会因为找不到值而报错 这里暂时忽略这个步骤了
		File file = null;
		InputStream fin = null;
		ServletOutputStream out = null;
		try {

			// 调用工具类WordGenerator的createDoc方法生成Word文档
			file = WordAndExcelGenerator.createDocOrExcel("收支数据分析", map,
					"breakEvenAnalysis");
			fin = new FileInputStream(file);

			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/octet-stream");
			// 设置浏览器以下载的方式处理该文件默认名为resume.doc

			String fileName = new String("收支数据分析.xls".getBytes("GBK"),
					"iso8859-1");
			resp.addHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			out = resp.getOutputStream();
			byte[] buffer = new byte[512]; // 缓冲区
			int bytesToRead = -1;
			// 通过循环将读入的Word文件的内容输出到浏览器中
			while ((bytesToRead = fin.read(buffer)) != -1) {
				out.write(buffer, 0, bytesToRead);
			}
		} finally {
			if (fin != null)
				fin.close();
			if (out != null)
				out.close();
			if (file != null)
				file.delete(); // 删除临时文件
		}
		return;
	}

	/****
	 * 
	 * <p>
	 * Description:生成图片<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月20日
	 * @param breakEvenCnd
	 * @param picType
	 *            1：收入支出图2：净收益图
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 *             void
	 */
	@RequestMapping("/generatePics")
	public ModelAndView generatePics(@ModelAttribute BreakEvenCnd breakEvenCnd,
			HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ModelAndView mv = new ModelAndView(
				"/finance/breakEvenAnalysis/incomePay2Netbenefit");

		if (breakEvenCnd.getBeginTime() != null
				&& !"".equals(breakEvenCnd.getBeginTime())) {
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			try {
				breakEvenCnd.setBegin(formatDate.parse(breakEvenCnd
						.getBeginTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String incomePayResult = breakEvenAnalysis
				.getIncomePayJson(breakEvenCnd);
		String netBenefitResult = breakEvenAnalysis
				.getNetBenefitJson(breakEvenCnd);

		logger.info("收入支出图所需json: " + incomePayResult);
		logger.info("净收益图所需json: " + netBenefitResult);
		mv.addObject("incomepayjson", incomePayResult).addObject(
				"netBenefitResult", netBenefitResult);

		return mv;
	}

	/***
	 * 预期输入页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}/edit")
	public ModelAndView eidt(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView("/finance/breakEvenAnalysis/edit");
		if (id != null) {
			BreakEvenAnalysis ba = breakEvenAnalysis.selectByPrimaryKey(id);
			ba.getAllPrincipal();
			mv.addObject("breakEvenAnalysis", ba);
		}
		return mv;
	}

	/**
	 * 更新预期
	 */
	@RequestMapping(value = "/updateExpectRate")
	@ResponseBody
	public MessageBox updateExpectRate(
			@ModelAttribute BreakEvenAnalysis requestCnd) {
		try {

			int count = breakEvenAnalysis.updateByPrimaryKey(requestCnd);
			if (count == 1) {
				return new MessageBox("1", "更新成功!");
			} else {
				return new MessageBox("0", "更新失败!");
			}
		} catch (Exception e) {
			logger.error("预期提现保存结果出错" + e.getMessage());
			e.printStackTrace();
			return new MessageBox("0", "更新异常!");
		}
	}

	@RequestMapping(value = "export")
	public ModelAndView exportCheckWithList(
			@ModelAttribute BreakEvenCnd breakEvenCnd, ModelMap model,
			HttpServletRequest req) throws ServletException, IOException,
			ParseException {
		String[] headData = new String[] { "日期", "收入", "支出", "净收益", "充值", "提现",
				"净流量", "收入增长率", "支出增长率", "充值增长率", "提现增长率", "到期本息(包含直通车)",
				"到期本息（直通车）", "预期提现率", "预期提现金额", "实际提现率" };
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern("##,###.00");
		List<String[]> dataList = new ArrayList<String[]>();
		List<BreakEvenAnalysis> list = breakEvenAnalysis
				.exportToExcel(breakEvenCnd);
		String[] endData = null;
		SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
		if (list != null && list.size() > 0) {
			for (BreakEvenAnalysis che : list) {
				String[] data = new String[headData.length];
				if (che.getType() == 1) {
					String time = sf.format(che.getTime());
					String s = time.substring(0, time.lastIndexOf('-'));
					data[0] = s + "月平均";
				} else {
					data[0] = sf.format(che.getTime());
				}
				if (che.getIncome() == null) {
					data[1] = "";
				} else {
					data[1] = String.valueOf(che.getIncome());
				}
				if (che.getPay() == null) {
					data[2] = "";
				} else {
					data[2] = String.valueOf(che.getPay());
				}
				if(che.getNetProceeds()==null){
					data[3] ="";
				}else{
					data[3] = String.valueOf(che.getNetProceeds());
				}
				if(che.getRecharge()==null){
					data[4] ="";
				}else{
					data[4] = String.valueOf(che.getRecharge());
				}
				if(che.getAdvance()==null){
					data[5] ="";
				}else{
					data[5] = String.valueOf(che.getAdvance());
				}
				if(che.getNetFlow()==null){
					data[6] = "";
				}else{
					data[6] = String.valueOf(che.getNetFlow());
				}
				if(che.getIncomeRate()==null){
					data[7] ="";
				}else{
					data[7] = String.valueOf(che.getIncomeRate());
				}
				if(che.getPayRate()==null){
					data[8] ="";
				}else{
					data[8] = String.valueOf(che.getPayRate());
				}
				if(che.getRechargeRate()==null){
					data[9] = "";
				}else{
					data[9] = String.valueOf(che.getRechargeRate());
				}
				if(che.getRecordeRate()==null){
					data[10] = "";
				}else{
					data[10] = String.valueOf(che.getRecordeRate());
				}
				if(che.getAllPrincipal()==null){
					data[11] ="";
				}else{
					data[11] = String.valueOf(che.getAllPrincipal());
				}
				if(che.getThroughPrincipal()==null){
					data[12] ="";
				}else{
					data[12] = String.valueOf(che.getThroughPrincipal());
				}
				if(che.getExpectRate()==null){
					data[13] ="";
				}else{
					data[13] = String.valueOf(che.getExpectRate());
				}
				if(che.getExpectCash()==null){
					data[14] ="";
				}else{
					data[14] = String.valueOf(che.getExpectCash());
				}
				if(che.getActualRate()==null){
					data[15] ="";
				}else{
					data[15] = String.valueOf(che.getActualRate());
				}
				dataList.add(data);
			}
		} else {
			String[] data = new String[1];
			data[0] = "暂无数据";
			dataList.add(data);
		}
		return new ModelAndView(new ViewExcel("收支数据分析列表"
				+ DateUtils.formatDateYmd(new Date()), headData, dataList,
				endData), model);
	}
}
