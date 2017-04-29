package com.cxdai.console.lottery.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;
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

import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.report.JasperExportUtils;
import com.cxdai.console.common.report.ReportData;
import com.cxdai.console.lottery.service.ChanceRuleInfoService;
import com.cxdai.console.lottery.service.UseRecordService;
import com.cxdai.console.lottery.vo.ChanceRuleInfo;
import com.cxdai.console.lottery.vo.UseRecord;
import com.cxdai.console.lottery.vo.UseRecordCnd;
import com.cxdai.console.lottery.vo.UseRecordStatic;
import com.cxdai.console.util.DateUtils;
/**
 * 
 * <p>
 * Description:抽奖机会使用记录控制类<br />
 * </p>
 * @title GoodController.java
 * @package com.cxdai.console.lottery.controller 
 * @author yubin
 * @version 0.1 2015年7月6日
 */
@Controller
@RequestMapping(value = "/lottery/useRecord")
public class UserRecordController extends BaseController {
	
	private final static Logger logger=Logger.getLogger(UserRecordController.class);
	@Autowired
	private UseRecordService  useRecordService;
	@Autowired
	private ChanceRuleInfoService chanceRuleInfoService;
	
	  /**
	    * 
	    * <p>
	    * Description:抽奖奖品管理主界面<br />
	    * </p>
	    * @author yubin
	    * @version 0.1 2015年6月23日
	    * @return
	    * ModelAndView
	    */
	    @RequestMapping("/main")
		public ModelAndView main() {
	    	 
	    	List<SelectItem> chanceRuleInfosInsert =new ArrayList<SelectItem>();
	    	List<SelectItem> goodSelectItems = new ArrayList<SelectItem>();
	    	 
	    	try {
				for (UseRecord useRecord : useRecordService.queryListUseRecordForGoodsName()) {
					goodSelectItems.add(new SelectItem(useRecord.getLotteryGoodsName(), useRecord.getLotteryGoodsName()));
				}
				List<ChanceRuleInfo> list = chanceRuleInfoService.queryAllChanceRuleInfos();
				for (ChanceRuleInfo bankVo : list) {
					chanceRuleInfosInsert.add(new SelectItem(bankVo.getId(), bankVo.getName()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ModelAndView("/lottery/useRecord/main")
			                  .addObject("chanceRuleInfosInsert",chanceRuleInfosInsert)
			                  .addObject("goodSelectItems",goodSelectItems);
		}
	   /**
	    *  
	    * <p>
	    * Description:抽奖奖品管理列表<br />
	    * </p>
	    * @author Administrator
	    * @version 0.1 2015年6月24日
	    * @param rechargeRecordCnd
	    * @param pageNo
	    * @return
	    * ModelAndVie
	    */
	   @RequestMapping("/list/{pageNo}")
	   public ModelAndView searchUseRecordList(@ModelAttribute UseRecordCnd useRecordCnd, @PathVariable Integer pageNo) {
	   	Page page = null;
	   	List<SelectItem> goodSelectItems = null;
	   	UseRecordStatic recordStatic =null;
	    
	   	List<UseRecord> list;
		try {
			list = useRecordService.queryListUseRecordForGoodsName();
			goodSelectItems = new ArrayList<SelectItem>();
			for (UseRecord useRecord : list) {
				goodSelectItems.add(new SelectItem(useRecord.getLotteryGoodsName(), useRecord.getLotteryGoodsName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		// 获取统计数据
		recordStatic = useRecordService.queryUseRecordStatic(useRecordCnd);
		// 获取列表
		page = useRecordService.queryPageUseRecordByCnd(useRecordCnd, pageNo,  Constants.CONSOLE_PAGE_SIZE);
	   	return new ModelAndView("/lottery/useRecord/list")
	   	                              .addObject("page", page)
	   	                              .addObject("recordStatic",recordStatic);
	   }
	   
	   /**
		 * <p>
		 * Description:统计导出数据的数量<br />
		 * </p>
		 * 
		 * @author hujianpan
		 * @version 0.1 2015年3月17日
		 * @param rechargeRecordCnd
		 * @return MessageBox
		 */
		@RequestMapping("/count")
		@ResponseBody
		public MessageBox count(@ModelAttribute UseRecordCnd useRecordCnd) {
			List<UseRecord> list = null;
			try {
				// 获取待打款的记录
				 list = useRecordService.queryListUseRecordForExcelByCnd(useRecordCnd);
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
		 * <p>
		 * Description:导出excel<br />
		 * </p>
		 * 
		 * @author hujianpan
		 * @version 0.1 2015年3月17日
		 * @param rechargeRecordCnd
		 * @param request
		 * @param response void
		 */
		@RequestMapping("/export")
		public void exportToExcel(@ModelAttribute UseRecordCnd useRecordCnd, HttpServletRequest request, HttpServletResponse response) {
			try {
				 
				// 获取待打款的记录
				List<UseRecord> list = useRecordService.queryListUseRecordForExcelByCnd(useRecordCnd);
				Map dto = new HashMap();
				ReportData reportData = new ReportData();
				reportData.setParametersDto(dto);
				reportData.setFieldsList(list);
				reportData.setReportFilePath("/report/exportExcel/fuserecordReportExcel.jasper");
				java.io.InputStream is = request.getSession().getServletContext().getResourceAsStream(reportData.getReportFilePath());
				// 这里可以传入pdf,excel,word,html,print导出各种类型文件
				JasperExportUtils.export(reportData.getFieldsList(), reportData.getParametersDto(), "excel", is, request, response,
						"抽奖记录" + DateUtils.format(new Date(), DateUtils.YMD));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 

}
