package com.cxdai.console.finance.virement.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.entity.CheckRecharge;
import com.cxdai.console.finance.virement.entity.RechargeType;
import com.cxdai.console.finance.virement.entity.WithdrawalsStatus;
import com.cxdai.console.finance.virement.service.CheckRechargeService;
import com.cxdai.console.finance.virement.vo.CheckAccountRequestCnd;
import com.cxdai.console.finance.virement.vo.CheckAccountResponseCnd;
import com.cxdai.console.finance.virement.vo.QueryPageCheckWithCnd;
import com.cxdai.console.finance.virement.vo.RechargeViewExcel;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.JsonUtils;
import com.cxdai.console.util.StringUtils;

/***
 * 充值核对Controller
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/finance/checkRecharge")
public class CheckRechargeController extends BaseController {

	private final static Logger logger = LoggerFactory.getLogger(CheckRechargeController.class);

	@Autowired
	private CheckRechargeService checkRechargeService;
	

	/***
	 * 提现核对主界面
	 * 
	 * @return
	 */
	@RequestMapping("/main")
	public ModelAndView interTransferMain() {
		return new ModelAndView("/finance/checkRecharge/main");
	}

	/**
	 * 分页列表查询每日提现对账信息
	 * 
	 * @param requestCnd
	 * @return
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchPageInterTransferList(@ModelAttribute QueryPageCheckWithCnd requestCnd,
			@PathVariable Integer pageNo) {
		ModelAndView mv = new ModelAndView("/finance/checkRecharge/list");
		try {
			Page page = checkRechargeService.queryPageCheckRecharge(requestCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
			mv.addObject("page", page);
		} catch (Exception e) {
			logger.error("分页查询每日提现汇总列表出错" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}

	/***
	 * 提现核对输入页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/{date}/edit")
	public ModelAndView eidt(@PathVariable("date") String date) {
		ModelAndView mv = new ModelAndView("/finance/checkRecharge/edit");
		try{
			if (StringUtils.isNotEmpty(date)) {
				List<CheckRecharge> checkWithdrawals = checkRechargeService.queryByDate(date);
				mv.addObject("checkWithdrawals", checkWithdrawals);
			}
		}catch(Exception e){
			logger.error("根据日期获取充值对账信息" + e.getMessage());
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 
	 * <p>
	 * Description:充值对账<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-18
	 * @return
	 * Map<String,Object>
	 */
	@RequestMapping(value="/checkAccount")
	@ResponseBody
	public Map<String,Object> checkAccount(HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", false);
		 try {
			String strJsob = IOUtils.toString(req.getInputStream(),"UTF-8");
			List<CheckAccountRequestCnd> request = JsonUtils.jsonToList(strJsob, CheckAccountRequestCnd.class);
			List<CheckAccountResponseCnd> list =  checkRechargeService.checkAccount(request);
			map.put("status", true);
			map.put("msg", list);
		} catch (Exception e) {
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		 return map;
	} 
	
	
	/**
	 * 
	 * <p>
	 * Description:保存对账信息<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-21
	 * @param req
	 * @return
	 * Map<String,Object>
	 */
	@RequestMapping(value="/saveRecharge")
	@ResponseBody
	public Map<String,Object> saveRecharge(HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		 try {
			String strJsob = IOUtils.toString(req.getInputStream(),"UTF-8");
			List<CheckAccountRequestCnd> request = JsonUtils.jsonToList(strJsob, CheckAccountRequestCnd.class);
			int result =  checkRechargeService.saveRecharge(request,currentUser().getUserId(),HttpTookit.getRealIpAddr(currentRequest()));
			map.put("status", result);
		} catch (Exception e) {
			map.put("msg", "保存失败!"+e.getMessage());
			e.printStackTrace();
		}
		 return map;
	} 
	
	
	

	/**
	 * 导出提现对账信息
	 * 
	 * @param requestCnd
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/export")
	public ModelAndView exportRechargeList(@ModelAttribute QueryPageCheckWithCnd requestCnd, ModelMap model)
			throws Exception {
		String[] headData = new String[] { "日期", "充值端口", "充值总额(元)", "充值成功总额(元)", "实际充值总额(元)", "计算手续费(元)", "实际手续费(元)", "手续费差异(元)", "第三方到账总额(元)","借款者虚拟充值总额(元)","差异(元)","备注","状态" };
		List<String[]> dataList = new ArrayList<String[]>();
		List<CheckRecharge> list = checkRechargeService.exportRechargeList(requestCnd);
		int  listNum= list.size() / 5;
		String[] endData = null;
		int num = 0;
		if (list != null && list.size() > 0) {
			String date ="";
			String type = "合计(不含虚拟充值)";
			BigDecimal totalMoney = BigDecimal.ZERO;
			BigDecimal successMoney = BigDecimal.ZERO;
			BigDecimal checkSuccessMoney = BigDecimal.ZERO;
			BigDecimal calculationFee = BigDecimal.ZERO;
			BigDecimal checkFee = BigDecimal.ZERO;
			BigDecimal differenceFee = BigDecimal.ZERO;
			BigDecimal receiveMoney = BigDecimal.ZERO;
			BigDecimal fictitiousRecharge = BigDecimal.ZERO;
			BigDecimal differenceTotal = BigDecimal.ZERO;
			String isSuccess = "";
			String remarks = "";
			for(int i=0;i<list.size()+listNum;i++){
				num++;
				int forCount = (int)Math.floor(i/6);
				String[] data = new String[headData.length];
				if(num % 6== 0){
					data[0] = date;
					data[1] = type;
					data[2] = totalMoney+"";
					data[3] = successMoney+"";
					data[4] = checkSuccessMoney+"";
					data[5] = calculationFee+ "";
					data[6] = checkFee + "";
					data[7] = differenceFee + "";
					data[8] = receiveMoney + "";
					data[9] = fictitiousRecharge+ "";
					data[10] = differenceTotal+ "";
					data[11] = remarks;
					data[12] = isSuccess;
					date ="";
					totalMoney = BigDecimal.ZERO;
					successMoney = BigDecimal.ZERO;
					checkSuccessMoney = BigDecimal.ZERO;
					calculationFee = BigDecimal.ZERO;
					checkFee = BigDecimal.ZERO;
					differenceFee = BigDecimal.ZERO;
					receiveMoney = BigDecimal.ZERO;
					fictitiousRecharge = BigDecimal.ZERO;
					differenceTotal = BigDecimal.ZERO;
					isSuccess = "";
					remarks = "";
				}else{
					String dateStr = DateUtils.formatDateYmd(list.get(i-forCount).getDate());
					date = dateStr;
					data[0] = dateStr;
					data[1] = RechargeType.getByCode(list.get(i-forCount).getType()).getDesc();
					if(list.get(i-forCount).getTotalMoney() != null){
						if(RechargeType.ALIPAY.getCode() != list.get(i-forCount).getType())
						totalMoney = totalMoney.add(list.get(i-forCount).getTotalMoney());
						data[2] = list.get(i-forCount).getTotalMoney() + "";
					}else{
						data[2] =  "0";
					}
					if(list.get(i-forCount).getSuccessMoney() != null){
						if(RechargeType.ALIPAY.getCode() != list.get(i-forCount).getType())
						successMoney = successMoney.add(list.get(i-forCount).getSuccessMoney());
						data[3] = list.get(i-forCount).getSuccessMoney() + "";
					}else{
						data[3] = "0";
					}
					if(list.get(i-forCount).getCheckSuccessMoney() != null){
						if(RechargeType.ALIPAY.getCode() != list.get(i-forCount).getType())
						checkSuccessMoney = checkSuccessMoney.add(list.get(i-forCount).getCheckSuccessMoney());
						data[4] = list.get(i-forCount).getCheckSuccessMoney()+"";
					}else{
						data[4] = "";
					}
					if(list.get(i-forCount).getCalculationFee() != null){
						if(RechargeType.ALIPAY.getCode() != list.get(i-forCount).getType())
						calculationFee = calculationFee.add(list.get(i-forCount).getCalculationFee());
						data[5] = list.get(i-forCount).getCalculationFee()+"";
					}else{
						data[5] = "0";
					}
					if(list.get(i-forCount).getCheckFee() != null){
						if(RechargeType.ALIPAY.getCode() != list.get(i-forCount).getType())
						checkFee = checkFee.add(list.get(i-forCount).getCheckFee());
						data[6] = list.get(i-forCount).getCheckFee()+"";
					}else{
						data[6] = "";
					}
					if(list.get(i-forCount).getDifferenceFee() != null){
						if(RechargeType.ALIPAY.getCode() != list.get(i-forCount).getType())
						differenceFee = differenceFee.add(list.get(i-forCount).getDifferenceFee());
						data[7] = list.get(i-forCount).getDifferenceFee()+"";
					}else{
						data[7] = "";
					}
					
					if(list.get(i-forCount).getReceiveMoney() != null){
						if(RechargeType.ALIPAY.getCode() != list.get(i-forCount).getType())
						receiveMoney = receiveMoney.add(list.get(i-forCount).getReceiveMoney());
						data[8] = list.get(i-forCount).getReceiveMoney()+"";
					}else{
						data[8] = "";
					}
					
					if(list.get(i-forCount).getFictitiousRecharge() != null){
						if(RechargeType.ALIPAY.getCode() != list.get(i-forCount).getType())
						fictitiousRecharge = fictitiousRecharge.add(list.get(i-forCount).getFictitiousRecharge());
						data[9] = list.get(i-forCount).getFictitiousRecharge()+"";
					}else{
						data[9] = "";
					}
					if(list.get(i-forCount).getDifferenceTotal() != null){
						if(RechargeType.ALIPAY.getCode() != list.get(i-forCount).getType())
						differenceTotal = differenceTotal.add(list.get(i-forCount).getDifferenceTotal());
						data[10] = list.get(i-forCount).getDifferenceTotal()+"";
					}else{
						data[10] = "";
					}
					String status = WithdrawalsStatus.getByCode(list.get(i-forCount).getIsSuccess()).getDesc();
					remarks = list.get(i-forCount).getRemarks();
					data[11] = list.get(i-forCount).getRemarks();
					
					isSuccess = status;
					data[12] = status;
				}
				dataList.add(data);
			
			}
		} else {
			String[] data = new String[1];
			data[0] = "暂无数据";
			dataList.add(data);
		}
		return new ModelAndView(
				new RechargeViewExcel("充值对账列表" + DateUtils.formatDateYmd(new Date()), headData, dataList, endData), model);
	}
	
	
	
}
