package com.cxdai.console.statistics.customer.controller;

import java.io.BufferedOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.statistics.reconciliation.service.AccountCheckService;
import com.cxdai.console.statistics.reconciliation.vo.RealtimeUserAccountVO;
import com.cxdai.console.util.DateUtils;

/**
 * 用户资金情况
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/customer/realtimeusercount")
public class RealtimeUserController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(RealtimeUserController.class);
	
	@Autowired
	private AccountCheckService accountCheckService;
	
	@RequestMapping("/main")
	public ModelAndView forRealtimeUseAccountMain(){
		return new ModelAndView("/statistics/customer/realtimeuser/main");
	}
	
	/**
	 * 用户资金情况统计结果
	 * @return
	 */
	@RequestMapping("/realtimedetail")
	public ModelAndView queryRealtimeUserAccount(){
		RealtimeUserAccountVO realtimeUserAccountVO = new RealtimeUserAccountVO();
		try {
			realtimeUserAccountVO = accountCheckService.queryRealtimeUserAccount();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户资金情况错误信息："+e);
		}
		return new ModelAndView("/statistics/customer/realtimeuser/list").addObject("realtimeUserAccountVO", realtimeUserAccountVO);
	}
	
	/**
	 * <p>
	 * Description:导出实时用户资金情况TXT<br />
	 * </p>
	 */
	@RequestMapping("/export")
	public void exportRealtimeUserAccountTxt(HttpServletResponse response) {
		String fileName = "用户资金情况" + DateUtils.format(new Date(), DateUtils.YMDHMS) + ".csv";
		try {
			fileName = new String(fileName.getBytes("GBK"), "ISO8859_1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setContentType("text/plain");// 一下两行关键的设置
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);// filename指定默认的名字
		BufferedOutputStream buff = null;
		StringBuffer write = new StringBuffer();
		String tab = ",";
		String enter = "\r\n";
		write.append("用户类型,用户名,总资产,待还净值,实际资产,可用余额,冻结金额,待收总额,可提现金额,受限金额\r\n");
		ServletOutputStream outSTr = null;
		try {
			// 获取待打款的记录
			List<RealtimeUserAccountVO> list = accountCheckService.queryRealtimeUserAccountList();
			outSTr = response.getOutputStream();// 建立
			buff = new BufferedOutputStream(outSTr);
			if (list.size() > 0) {
				int i = 1;
				for (RealtimeUserAccountVO realtimeUserAccountVO : list) {
					if (realtimeUserAccountVO.getUserType() != null && !realtimeUserAccountVO.getUserType().equals("")) {
						write.append(realtimeUserAccountVO.getUserType() + tab);
					} else {
						write.append(tab);
					}
					if (realtimeUserAccountVO.getUserName() != null && !realtimeUserAccountVO.getUserName().equals("")) {
						write.append(realtimeUserAccountVO.getUserName() + tab);
					} else {
						write.append(tab);
					}
					if (realtimeUserAccountVO.getTotal() != null) {
						write.append(realtimeUserAccountVO.getTotal() + tab);
					} else {
						write.append(tab);
					}
					if (realtimeUserAccountVO.getRepayNetMoney() != null) {
						write.append(realtimeUserAccountVO.getRepayNetMoney() + tab);
					} else {
						write.append(tab);
					}
					if (realtimeUserAccountVO.getActualMoney() != null) {
						write.append(realtimeUserAccountVO.getActualMoney() + tab);
					} else {
						write.append(tab);
					}
					if (realtimeUserAccountVO.getUserMoney() != null) {
						write.append(realtimeUserAccountVO.getUserMoney() + tab);
					} else {
						write.append(tab);
					}
					if (realtimeUserAccountVO.getNoUserMoney() != null) {
						write.append(realtimeUserAccountVO.getNoUserMoney() + tab);
					} else {
						write.append(tab);
					}
					if (realtimeUserAccountVO.getCollection() != null) {
						write.append(realtimeUserAccountVO.getCollection() + tab);
					} else {
						write.append(tab);
					}
					if (realtimeUserAccountVO.getDrawMoney() != null) {
						write.append(realtimeUserAccountVO.getDrawMoney() + tab);
					} else {
						write.append(tab);
					}
					if (realtimeUserAccountVO.getNoDrawMoney() != null) {
						write.append(realtimeUserAccountVO.getNoDrawMoney() + tab);
					} else {
						write.append(tab);
					}
					write.append("\r\n");
					i++;
				}
			}
			buff.write(write.toString().getBytes("GBK"));
			buff.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
