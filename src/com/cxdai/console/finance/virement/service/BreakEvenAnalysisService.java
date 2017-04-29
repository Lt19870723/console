package com.cxdai.console.finance.virement.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.entity.BreakEvenAnalysis;
import com.cxdai.console.finance.virement.mapper.BreakEvenAnalysisMapper;
import com.cxdai.console.finance.virement.vo.BreakEvenCnd;
import com.cxdai.console.util.JsonUtils;

@Service
public class BreakEvenAnalysisService {
	@Autowired
	private BreakEvenAnalysisMapper breakEvenAnalysisMapper;

	public Page queryPageListByCnd(BreakEvenCnd breakEvenCnd, Integer curPage,
			Integer pageSize) throws ParseException {
		Page page = new Page(curPage, pageSize);
//		Integer totalCount = breakEvenAnalysisMapper
//				.queryListCountByCnd(breakEvenCnd);
		List<BreakEvenAnalysis> list = breakEvenAnalysisMapper.queryListByCnd(
				breakEvenCnd, page);
		List<BreakEvenAnalysis> ba = this.findByMonth(breakEvenCnd);
		if (ba.size() != 0 && ba != null) {
			list.add(0, ba.get(0));
		}
		page.setTotalCount(list.size());
		page.setResult(list);
		return page;
	}

	/**
	 * 根据查询时间获取上个月的平均数据
	 */
	public List<BreakEvenAnalysis> findByMonth(BreakEvenCnd breakEvenCnd)
			throws ParseException {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy");
		// 上个月的一天
		Date date = sf.parse(breakEvenCnd.getBeginTime());
		String s = sf.format(date);
		String s2 = s.substring(s.indexOf('-'), s.lastIndexOf('-'))
				.replace('-', ' ').trim();
		int i = Integer.parseInt(s2);
		int lastMonth = 0;
		String year = sf2.format(date);
		if (i == 1) {
			lastMonth = 12;
			year = Integer.parseInt(year) - 1 + "".trim();
		} else {
			lastMonth = i - 1;
		}

		String stime;
		if (lastMonth < 10) {
			stime = year + "-0" + lastMonth + "-" + "01";
		} else {
			stime = year + "-" + lastMonth + "-" + "01";
		}
		breakEvenCnd.setBeginTime(stime);
		breakEvenCnd.setEndTime(stime);

		List<BreakEvenAnalysis> ba = breakEvenAnalysisMapper
				.findByMonth(breakEvenCnd);
		return ba;
	}

	public List<BreakEvenAnalysis> exportToExcel(BreakEvenCnd breakEvenCnd)
			throws ParseException {
		List<BreakEvenAnalysis> list = breakEvenAnalysisMapper
				.queryBreakEvenAnalysisList(breakEvenCnd);
		List<BreakEvenAnalysis> ba = this.findByMonth(breakEvenCnd);
		if(list!=null&&list.size()>0){
			if (ba != null && ba.size() >= 0) {
				list.add(0, ba.get(0));
			}
			for(BreakEvenAnalysis b:list){
				if(b.getType()==1){
					b.setAllPrincipal(null);
					b.setThroughPrincipal(null);
				}
			}
			return list;
		}else{
			return ba;
		}
		
	
	}

	public List<Map<String, Object>> queryIncomePayList(
			BreakEvenCnd breakEvenCnd) {
		// TODO Auto-generated method stub
		return null;
	}

	public BigDecimal[] queryIncomeList(BreakEvenCnd breakEvenCnd) {
		List<BigDecimal> list = breakEvenAnalysisMapper
				.queryIncomeList(breakEvenCnd);
		if (list != null) {
			return list.toArray(new BigDecimal[list.size()]);
		}

		return new BigDecimal[] { new BigDecimal(400), new BigDecimal(440),
				new BigDecimal(500), new BigDecimal(300), new BigDecimal(460) };
	}

	public BigDecimal[] queryPayList(BreakEvenCnd breakEvenCnd) {
		List<BigDecimal> list = breakEvenAnalysisMapper
				.queryPayList(breakEvenCnd);
		if (list != null) {
			return list.toArray(new BigDecimal[list.size()]);
		}
		return new BigDecimal[] { new BigDecimal(300), new BigDecimal(340),
				new BigDecimal(400), new BigDecimal(600), new BigDecimal(560) };
	}

	/****
	 * 
	 * <p>
	 * Description:收入支出图片Json字符串<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月20日
	 * @param breakEvenCnd
	 * @return String
	 */
	public String getIncomePayJson(BreakEvenCnd breakEvenCnd) {

		List<Map<String, Object>> list = breakEvenAnalysisMapper
				.getIncomePayResultMap(breakEvenCnd);
		Map<String, BigDecimal> maxPayOrIncome = breakEvenAnalysisMapper
				.getMaxPayOrIncome(breakEvenCnd);
		BigDecimal maxIncome = maxPayOrIncome == null ? BigDecimal.ZERO
				: maxPayOrIncome.get("maxIncome");// 最大收入
		BigDecimal maxPay = maxPayOrIncome == null ? BigDecimal.ZERO
				: maxPayOrIncome.get("maxPay");// 最大支出
		if (list != null && !list.isEmpty()) {
			Map<String, Object> result = new HashMap<String, Object>();

			List<Object> incomeList = new ArrayList<Object>();// 收入
			List<Object> payList = new ArrayList<Object>();// 支出
			List<Object> categories = new ArrayList<Object>();// 时间
			for (Map<String, Object> map : list) {
				for (String key : map.keySet()) {
					Object o = map.get(key);
					if ("income".equals(key)) {
						incomeList.add(o);
					} else if ("pay".equals(key)) {
						payList.add(o);
					} else if ("dataDate".equals(key)) {
						categories.add(o);
					}
				}
			}

			result.put("income", incomeList.toArray(new Object[incomeList.size()]));// 收入
			result.put("pay", payList.toArray(new Object[payList.size()]));// 支出
			result.put("categories", categories);// X轴
			result.put("yMax", maxIncome.compareTo(maxPay) >= 0 ? maxIncome
					: maxPay);// 纵轴最大值
			return JsonUtils.bean2Json(result);
		}

		return null;
	}

	/****
	 * 
	 * <p>
	 * Description:根据条件统计收益，并组装成json字符串<br />
	 * </p>
	 * 
	 * @author Kimmyzhao
	 * @version 0.1 2016年4月22日
	 * @param breakEvenCnd
	 * @return String
	 */
	public String getNetBenefitJson(BreakEvenCnd breakEvenCnd) {
		List<Map<String, Object>> list = breakEvenAnalysisMapper
				.getNetBenefit(breakEvenCnd);
		// 统计最大净收益与最小净收益;用于控制Y轴范围
		Map<String, BigDecimal> minOrMaxNetBenefitMap = breakEvenAnalysisMapper
				.getMinOrMaxNetBenefit(breakEvenCnd);

		if (list != null && !list.isEmpty()) {
			BigDecimal avgPremonth = new BigDecimal(list.get(0)
					.get("netBenefit").toString());// 上月净收益平均值
			Map<String, Object> result = new HashMap<String, Object>();

			List<Object> netBenefitList = new ArrayList<Object>();// 收入
			List<Object> categories = new ArrayList<Object>();// 时间
			for (Map<String, Object> map : list) {

				for (String key : map.keySet()) {
					if ("netBenefit".equalsIgnoreCase(key)) {
						netBenefitList.add(map.get(key));
					} else if ("dateTime".equalsIgnoreCase(key)) {
						categories.add(map.get(key));
					}
				}
			}

			result.put("netBenefit", netBenefitList.toArray(new Object[netBenefitList.size()]));// 数据列
			result.put("categories", categories);// X轴
			result.put("yMax", avgPremonth.compareTo(minOrMaxNetBenefitMap
					.get("maxNetBenefit")) >= 0 ? avgPremonth
					: minOrMaxNetBenefitMap.get("maxNetBenefit"));// Y轴最大值
			result.put(
					"yMin",
					avgPremonth.compareTo(minOrMaxNetBenefitMap
							.get("minNetBenefit")) >= 0 ? minOrMaxNetBenefitMap
							.get("minNetBenefit") : avgPremonth);// Y轴最小值
			return JsonUtils.bean2Json(result);
		}
		return null;
	}

	public BreakEvenAnalysis selectByPrimaryKey(Integer id) {
		BreakEvenAnalysis ba = breakEvenAnalysisMapper.selectByPrimaryKey(id);
		if(ba!=null&&ba.getExpectRate()!=null){
			ba.setExpectRate(ba.getExpectRate().replace("%", "").trim());
		}
		return ba;
	}

	public int updateByPrimaryKey(BreakEvenAnalysis ba) {
		if (ba != null && ba.getExpectRate() != null) {
			BigDecimal rate=new BigDecimal(ba.getExpectRate()).divide(new BigDecimal("100"),4,BigDecimal.ROUND_HALF_EVEN);
			BigDecimal expectCash = ba.getAllPrincipal().multiply(rate);
			ba.setExpectRate(ba.getExpectRate()+"%");
			ba.setExpectCash(expectCash);
		}
		int num = breakEvenAnalysisMapper.updateByPrimaryKey(ba);
		return num;

	}
}
