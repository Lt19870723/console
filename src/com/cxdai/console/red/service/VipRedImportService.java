package com.cxdai.console.red.service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.reward.mapper.BaseMemberAccumulatePointsMapper;
import com.cxdai.console.base.mapper.BaseMemberMapper;
import com.cxdai.console.base.mapper.BaseMobileApproMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.red.entity.RedAccount;
import com.cxdai.console.red.entity.RedAccountLog;
import com.cxdai.console.red.entity.VipRedImport;
import com.cxdai.console.red.entity.VipRedView;
import com.cxdai.console.red.mapper.RedAccountLogMapper;
import com.cxdai.console.red.mapper.RedAccountMapper;
import com.cxdai.console.red.mapper.VipRedImportMapper;
import com.cxdai.console.red.util.ExcelUtil;
import com.cxdai.console.red.vo.VipRedImportCnd;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.sms.service.SmsSendService;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.ShiroUtils;

/**
 * 红包管理-贵族特权红包发放
 * 
 * @author liutao
 * @Date 2015-11-11
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class VipRedImportService {
	private final static Logger logger = Logger.getLogger(RedRecordLogService.class);
	@Autowired
	private BaseMemberAccumulatePointsMapper baseMemberAccumulatePointsMapper;
	@Autowired
	private BaseMemberMapper baseMemberMapper;
	@Autowired
	private VipRedImportMapper vipRedImportMapper;
	@Autowired
	private MemberService memberService;
	@Autowired
	private VipRedViewService vipRedViewService;
	@Autowired
	private RedAccountMapper redAccountMapper;
	@Autowired
	private RedAccountLogMapper redAccountLogMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private SmsSendService sendService;
	@Autowired
	private BaseMobileApproMapper baseMobileApproMapper;

	/**
	 * 红包管理-查询贵族特权红包发放集合
	 * 
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@Transactional(rollbackFor = Throwable.class, readOnly = true)
	public Page queryVipRedImportVoList(VipRedImportCnd vipRedImportCnd, int pageSize, int curPage) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = vipRedImportMapper.queryVipRedImportVoCount(vipRedImportCnd);
		page.setTotalCount(totalCount);
		List<VipRedImport> list = vipRedImportMapper.queryVipRedImportVoList(vipRedImportCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 红包管理-新增贵族特权红包发放(批量备注信息增加)
	 * 
	 * @author liutao
	 * @Date 2015-11-11
	 */
	public int add(VipRedImport vipRedImport, String info) throws Exception {
		ShiroUser shiroUser = ShiroUtils.currentUser();
		vipRedImport.setOptName(shiroUser.getUserName());
//		vipRedImport.setOptTime(new Date());
		vipRedImport.setAddName(shiroUser.getUserName());
		vipRedImport.setAddTime(new Date());
		vipRedImport.setRemarkInfo(info);
		return vipRedImportMapper.add(vipRedImport);
	}

	/**
	 * 红包管理-新增贵族特权红包发放
	 * 
	 * @author liutao
	 * @Date 2015-11-11
	 */
	public int add(VipRedImport vipRedImport) throws Exception {
		ShiroUser shiroUser = ShiroUtils.currentUser();
		vipRedImport.setOptName(shiroUser.getUserName());
//		vipRedImport.setOptTime(new Date());
		vipRedImport.setAddName(shiroUser.getUserName());
		vipRedImport.setAddTime(new Date());
		return vipRedImportMapper.add(vipRedImport);
	}

	/**
	 * 红包管理-更新贵族特权红包状态
	 * 
	 * @author liutao
	 * @Date 2015-11-11
	 */
	public void updateRedStatus(String id) throws Exception {
		vipRedImportMapper.updateRedStatus(id);
	}

	/**
	 * 红包管理-贵族特权红包发放导入
	 * 
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@Transactional(rollbackFor = Throwable.class)
	public List<VipRedImport> importRed(InputStream in, String info, String redType) throws Exception {
		List<VipRedImport> vipRedImports = readRedInfoSorXls(in);
		if (null != vipRedImports && vipRedImports.size() > 0) {
			MemberVo memberVo = new MemberVo();
			// 校验用户名是否存在
			for (VipRedImport vipRed : vipRedImports) {
				if (null != vipRed && StringUtils.isEmpty(vipRed.getUseName())) {
					vipRed.setStrAlertMsg("用户名不能为空！");
					return vipRedImports;
				}
				/*
				 * if (null != vipRed && StringUtils.isEmpty(vipRed.getRedType())) { vipRed.setStrAlertMsg("用户名：" + vipRed.getUseName() + "的红包类型不能为空！"); return vipRedImports; }
				 */
				if (null != vipRed && StringUtils.isEmpty(vipRed.getRedCount())) {
					vipRed.setStrAlertMsg("用户名：" + vipRed.getUseName() + "的红包数量不能为空！");
					return vipRedImports;
				}

				/*
				 * if (!(vipRed.getRedType().equals("50亿活动红包") || vipRed.getRedType().equals("贵宾特权红包") || vipRed.getRedType().equals("新人活动红包") || vipRed .getRedType().equals("活动红包"))) {
				 * vipRed.setStrAlertMsg("用户名：" + vipRed.getUseName() + "的红包类型只能为50亿活动红包或者贵宾特权红包或者新人活动红包或者活动红包！"); return vipRedImports; }
				 */

				if (null != vipRed && StringUtils.isEmpty(vipRed.getRedMoney())) {
					vipRed.setStrAlertMsg("用户名：" + vipRed.getUseName() + "的红包金额不能为空！");
					return vipRedImports;
				}

				/*
				 * if (vipRed.getRedType().equals("贵宾特权红包")) { if (!vipRed.getRedMoney().equals("50")) { vipRed.setStrAlertMsg("用户名：" + vipRed.getUseName() + "的红包金额只能为50！"); return vipRedImports; } }
				 * else if (vipRed.getRedType().equals("50亿活动红包")) { if (!(vipRed.getRedMoney().equals("10") || vipRed.getRedMoney().equals("50") || vipRed.getRedMoney().equals("200"))) {
				 * vipRed.setStrAlertMsg("用户名：" + vipRed.getUseName() + "的红包金额只能为10或者50或者200！"); return vipRedImports; } } else if (vipRed.getRedType().equals("新人活动红包")) { if
				 * (!(vipRed.getRedMoney().equals("20") || vipRed.getRedMoney().equals("30") || vipRed.getRedMoney().equals("50"))) { vipRed.setStrAlertMsg("用户名：" + vipRed.getUseName() +
				 * "的红包金额只能为20或者30或者50！"); return vipRedImports; } }
				 */

				memberVo = memberMapper.queryMemberVoByUsername(vipRed.getUseName());
				if (null == memberVo) {
					vipRed.setStrAlertMsg("用户名：" + vipRed.getUseName() + "，在系统中不存在");
					return vipRedImports;
				} else {
					vipRed.setUserId(memberVo.getId());
				}

				if (null != vipRed && StringUtils.isEmpty(vipRed.getRemark())) {
					// vipRed.setStrAlertMsg("用户名：" + vipRed.getUseName() + "的备注不能为空！");
					// return vipRedImports;
				} else {
					if (vipRed.getRemark().length() > 190) {
						vipRed.setRemark(vipRed.getRemark().substring(0, 190));
					}
				}
			}
			// 先存入贵宾特权红包日志表
			VipRedImport vipRedImport = new VipRedImport();
			vipRedImport.setNumber(vipRedImports.size());
			vipRedImport.setStatus(0);
			if (vipRedImports.size() == 1) {
				vipRedImport.setRemark(vipRedImports.get(0).getRemark());
			} else {
				vipRedImport.setRemark("批量发送红包");
			}
			add(vipRedImport, info);
			// 插入贵宾特权红包表
			logger.info("importRed  Begin:" + DateUtils.format(new Date(), DateUtils.YMD_HMS) + ".....");
			for (VipRedImport vipRed : vipRedImports) {
				for (int i = 0; i < Integer.valueOf(vipRed.getRedCount()); i++) {
					VipRedView vipRedView = new VipRedView();
					vipRedView.setUserName(vipRed.getUseName());
					vipRedView.setRemark(vipRed.getRemark());
					vipRedView.setOptId(vipRedImport.getId());
					vipRedView.setUserId(vipRed.getUserId());

					vipRedView.setRedType(redType);
					/*
					 * if(vipRed.getRedType().equals("贵宾特权红包")){ vipRedView.setRedType("1930"); }else if(vipRed.getRedType().equals("50亿活动红包")){ vipRedView.setRedType("1990"); }else
					 * if(vipRed.getRedType().equals("新人活动红包")){ vipRedView.setRedType("1970"); }else if(vipRed.getRedType().equals("活动红包")){ vipRedView.setRedType("2010"); }
					 */
					vipRedView.setRedMoney(vipRed.getRedMoney());
					vipRedViewService.add(vipRedView);
				}
			}
			logger.info("importRed End:" + DateUtils.format(new Date(), DateUtils.YMD_HMS) + ".....");
		} else {
			List<VipRedImport> reds = new ArrayList<VipRedImport>();
			VipRedImport red = new VipRedImport();
			red.setStrAlertMsg("读取excel表格信息失败");
			reds.add(red);
			return reds;
		}
		return vipRedImports;
	}

	/**
	 * 红包管理-读取贵族特权红包发放EXCEL数据
	 * 
	 * @author liutao
	 * @Date 2015-11-11
	 */
	private List<VipRedImport> readRedInfoSorXls(InputStream is) throws IOException, ParseException {
		List<VipRedImport> vipRedImports = new java.util.ArrayList<VipRedImport>();
		VipRedImport vipRedImport = null;
		int totalRows = 0;
		int totalCells = 0;
		int beginRow = 0;
		int endRow = 0;
		try {
			HSSFWorkbook book = new HSSFWorkbook(is);
			// 得到第一个shell
			HSSFSheet sheet = book.getSheetAt(0);
			// 得到Excel的行数
			totalRows = sheet.getPhysicalNumberOfRows();
			// 得到Excel的列数
			if (totalRows >= 1 && sheet.getRow(0) != null) {
				totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
			}
			// 得到起始行 getPhysicalNumberOfCells无法读取中间空行时的总行数
			beginRow = sheet.getFirstRowNum();
			endRow = sheet.getLastRowNum();
			// 循环Excel行数,从第二行开始。标题不入库
			for (int r = beginRow + 1; r <= endRow; r++) {
				HSSFRow row = sheet.getRow(r);
				if (null == sheet.getRow(r))
					continue;
				vipRedImport = new VipRedImport();
				// 循环Excel的列
				for (int c = 0; c < totalCells; c++) {
					HSSFCell cell = row.getCell(c);
					if (null != cell) {
						// 第一列
						if (c == 0) {
							String usename = ExcelUtil.getValue(row.getCell(0));
							vipRedImport.setUseName(usename);
						}
						/*
						 * // 获得第二列<红包类型> else if (c == 1) { if (row.getCell(1) != null) { String redType = ExcelUtil.getValue(row.getCell(1)); vipRedImport.setRedType(redType); } }
						 */
						// 获得第三列<红包金额>
						else if (c == 1) {
							String redMoney = ExcelUtil.getValue(row.getCell(1));
							if (StringUtils.isNotEmpty(redMoney)) {
								vipRedImport.setRedMoney(String.valueOf(Integer.valueOf((int) Math.floor(Double.valueOf(redMoney.trim())))));
							}
						}
						// 获得第三列<红包数量>
						else if (c == 2) {
							String redCount = ExcelUtil.getValue(row.getCell(2));
							if (StringUtils.isNotEmpty(redCount)) {
								vipRedImport.setRedCount(String.valueOf(Integer.valueOf((int) Math.floor(Double.valueOf(redCount.trim())))));
							}
						}
						// 获得第四列<备注>
						else if (c == 3) {
							String remark = ExcelUtil.getValue(row.getCell(3));
							vipRedImport.setRemark(remark);
						}
					}
				}
				if (null != vipRedImport && StringUtils.isNotEmpty(vipRedImport.getUseName()) /* && StringUtils.isNotEmpty(vipRedImport.getRemark()) */
						/* && StringUtils.isNotEmpty(vipRedImport.getRedType()) */&& StringUtils.isNotEmpty(vipRedImport.getRedMoney())) {
					vipRedImports.add(vipRedImport);
				}
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		return vipRedImports;
	}

	/**
	 * 红包管理-发放贵族特权红包
	 * 
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void insertredAccountAndredAccountLog(String id, String ip) {
		logger.info("insertredAccountAndredAccountLog  Begin:" + DateUtils.format(new Date(), DateUtils.YMD_HMS) + ".....");
		try {
			List<VipRedView> vipRedViews = vipRedViewService.queryRedByRedId(id);
			if (null != vipRedViews && vipRedViews.size() > 0) {
				for (VipRedView vipRedView : vipRedViews) {
					RedAccount redAccount = new RedAccount();
					redAccount.setUserId(vipRedView.getUserId());
					redAccount.setMoney(new BigDecimal(vipRedView.getRedMoney()));
					redAccount.setAddTime(new Date());
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, 30);
					Date endTime = cal.getTime();
					redAccount.setEndTime(endTime);
					redAccount.setFlag(0);
					redAccount.setRemark(vipRedView.getRemark());
					redAccount.setLastUpdateTime(new Date());
					redAccount.setInviterId(vipRedView.getId());
					redAccount.setRedType(Integer.valueOf(vipRedView.getRedType()));
					if (null != vipRedView && vipRedView.getRedType().equals("1930")) {
						redAccount.setStatus(1);
					} else {
						redAccount.setStatus(2);
					}
					redAccountMapper.add(redAccount);
					RedAccountLog redAccountLog = new RedAccountLog();
					redAccountLog.setMoney(new BigDecimal(vipRedView.getRedMoney()));
					redAccountLog.setUserId(vipRedView.getUserId());
					redAccountLog.setRedId(redAccount.getId());
					redAccountLog.setBizId(0);
					redAccountLog.setOpttime(new Date());
					if (null != vipRedView && vipRedView.getRedType().equals("1930")) {
						redAccountLog.setStatus(1);
					} else {
						redAccountLog.setStatus(2);
					}
					redAccountLog.setRemark(vipRedView.getRemark());
					redAccountLog.setAddip(ip);
					redAccountLog.setOptuser(-1);
					redAccountLogMapper.add(redAccountLog);
					// 50亿活动红包发送短信，活动已结束
//					SendMobileCnd sendMobileCnd = new SendMobileCnd();
//					if (null != vipRedView.getUserId()) {
//						MobileAppro mobileAppro = baseMobileApproMapper.queryByUserId(vipRedView.getUserId());
//						if (null != mobileAppro && null != mobileAppro.getMobileNum()) {
//							sendMobileCnd.setMobile(mobileAppro.getMobileNum());
//						}
//						sendMobileCnd.setIp(ip);
//						sendMobileCnd.setPlatform(1);
//
//					}
//					if (null != vipRedView && vipRedView.getRedType().equals("1930")) {
//						Map<String, Object> paramMap = new HashMap<String, Object>();
//						paramMap.put("endTime", DateUtils.format(DateUtils.dateFormatReturnDate(new Date(), 30), DateUtils.YMD_NYRSH));
//						sendMobileCnd.setParamMap(paramMap);
//						sendMobileCnd.setSmsTemplateType(507);
//						sendService.saveTemplateMessage(sendMobileCnd);
//					} else if (null != vipRedView && vipRedView.getRedType().equals("1990")) {
//						if (String.valueOf(Integer.valueOf((int) Math.floor(Double.valueOf(vipRedView.getRedMoney().trim())))).equals("10")) {
//							sendMobileCnd.setSmsTemplateType(603);
//						} else if (String.valueOf(Integer.valueOf((int) Math.floor(Double.valueOf(vipRedView.getRedMoney().trim())))).equals("50")) {
//							sendMobileCnd.setSmsTemplateType(605);
//						} else if (String.valueOf(Integer.valueOf((int) Math.floor(Double.valueOf(vipRedView.getRedMoney().trim())))).equals("200")) {
//							sendMobileCnd.setSmsTemplateType(604);
//						}
//						sendService.saveTemplateMessage(sendMobileCnd);
//					}
				}
			}
			logger.info("insertredAccountAndredAccountLog  End:" + DateUtils.format(new Date(), DateUtils.YMD_HMS) + ".....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional(rollbackFor = Throwable.class)
	public void grantRed(String id, String ip) {
		try {
			// 更新日志表状态和时间
			updateRedStatus(id);
			// 操作红包表和红包日志表
			insertredAccountAndredAccountLog(id, ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional(rollbackFor = Throwable.class)
	public void forVipRedSubmit(String useName, String remark, Integer id, String redType, String redMoney) {
		try {
			VipRedImport vipRedImport = new VipRedImport();
			vipRedImport.setUseName(useName);
			vipRedImport.setRemarkInfo(remark);
			vipRedImport.setNumber(1);
			vipRedImport.setStatus(0);
			add(vipRedImport);
			VipRedView vipRedView = new VipRedView();
			vipRedView.setUserName(useName);
			vipRedView.setRemark(remark);
			vipRedView.setOptId(vipRedImport.getId());
			vipRedView.setUserId(id);
			vipRedView.setRedType(redType);
			vipRedView.setRedMoney(redMoney);
			vipRedViewService.add(vipRedView);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional(rollbackFor = Throwable.class)
	public void deleteImport(Integer id) {
		try {
			vipRedImportMapper.updateInviterLogStatus(id.toString());
			vipRedImportMapper.updateInviterStatus(id.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional(rollbackFor = Throwable.class)
	public Map<String, List<VipRedImport>> selectRedMoney() {
		Map<String, List<VipRedImport>> map = new HashMap<String, List<VipRedImport>>();
		try {
			List<VipRedImport> vipReds = vipRedImportMapper.selectRedMoney("1930");
			List<VipRedImport> activeReds = vipRedImportMapper.selectRedMoney("1970");
			List<VipRedImport> interReds = vipRedImportMapper.selectRedMoney("1980");
			List<VipRedImport> billReds = vipRedImportMapper.selectRedMoney("1990");
			List<VipRedImport> rewardReds = vipRedImportMapper.selectRedMoney("2000");
			List<VipRedImport> huodongReds = vipRedImportMapper.selectRedMoney("2010");
			map.put("vipReds", vipReds);
			map.put("activeReds", activeReds);
			map.put("interReds", interReds);
			map.put("billReds", billReds);
			map.put("rewardReds", rewardReds);
			map.put("huodongReds", huodongReds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
