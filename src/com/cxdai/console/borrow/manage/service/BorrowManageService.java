package com.cxdai.console.borrow.manage.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cxdai.console.borrow.approve.entity.TenderRecord;
import com.cxdai.console.borrow.emerg.vo.TenderRecordVo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.approve.vo.CollectionRepayInfoVo;
import com.cxdai.console.borrow.approve.vo.CollectionStatisticCnd;
import com.cxdai.console.borrow.autotender.service.CollectionRecordService;
import com.cxdai.console.borrow.emerg.mapper.TenderRecordMapper;
import com.cxdai.console.borrow.manage.mapper.BRepaymentRecordLogMapper;
import com.cxdai.console.borrow.manage.mapper.EFundRepayMentMapper;
import com.cxdai.console.borrow.manage.mapper.QueryBorrowRocordMapper;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.borrow.manage.vo.EFundRepayMent;
import com.cxdai.console.borrow.manage.vo.EFundRepayMentCnd;
import com.cxdai.console.borrow.manage.vo.RepaymentrecordlogVo;
import com.cxdai.console.cg.entity.MessageRecord;
import com.cxdai.console.cg.mapper.MessageRecordMapper;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.custody.xml.Finance;
import com.cxdai.console.common.custody.xml.Message;
import com.cxdai.console.common.custody.xml.PTRReq;
import com.cxdai.console.common.custody.xml.PTRRes;
import com.cxdai.console.common.custody.xml.XmlUtil;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.common.statics.CGBusinessConstants;
import com.cxdai.console.fix.mapper.BCollectionrecordMapper;
import com.cxdai.console.fix.mapper.FixBorrowTransferMapper;
import com.cxdai.console.fix.service.FixBorrowService;
import com.cxdai.console.fix.service.FixBorrowTransferService;
import com.cxdai.console.fix.vo.BCollectionRecordCnd;
import com.cxdai.console.fix.vo.BCollectionRecordVo;
import com.cxdai.console.fix.vo.FixBorrowTransferVo;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.transfer.mapper.TransferMapper;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.ShiroUtils;
import com.cxdai.console.util.exception.AppException;
import com.cxdai.console.util.serialnumberutil.UUIDGenerator;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Service
@Transactional(rollbackFor = Throwable.class)
public class BorrowManageService {
	
	private Logger logger=Logger.getLogger(BorrowManageService.class);
	
	@Autowired
	private BorrowMapper borrowMapper;
	@Autowired
	private QueryBorrowRocordMapper queryBorrowRocordMapper;
	@Autowired
	private BRepaymentRecordService bRepaymentRecordService;
	@Autowired
	private TransferMapper transferMapper;
	@Autowired
	private BRepaymentRecordLogMapper bRepaymentRecordLogMapper;
	@Autowired
	private AccountService accountService;
	@Autowired
	private CollectionRecordService collectionRecordService;
	@Autowired
	private FixBorrowService fixBorrowService;
	@Autowired
	private FixBorrowTransferMapper fixBorrowTransferMapper;
	@Autowired
	private FixBorrowTransferService fixBorrowTransferService;
	@Autowired
	private BCollectionrecordMapper bCollectionrecordMapper;
	@Autowired
	private TenderRecordMapper tenderRecordMapper;
	@Autowired
	private EFundRepayMentMapper eFundRepayMentMapper;
	@Autowired
	private MessageRecordMapper messageRecordMapper;

	@Autowired
	private CGRepayMentService gGRepayMentService;

	/**
	 * <p>
	 * Description:投标中的借款标<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception Page
	 */
	public Page queryTenderBorrowList(BorrowCnd borrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page p = new Page();
		p.setPageNo(curPage);
		p.setPageSize(pageSize);
		int totalCount = borrowMapper.querTenderBorrowCount(borrowCnd);
		p.setTotalCount(totalCount);
		List<BorrowVo> list = borrowMapper.querTenderBorrowList(borrowCnd, p);
		p.setResult(list);
		return p;
	}

	/**
	 * <p>
	 * Description:借款标分页查询<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param repaymentBorrowCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception Page
	 */
	public Page searchPageBorrowList(BorrowCnd repaymentBorrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = queryBorrowRocordMapper.queryBorrowRecordCount(repaymentBorrowCnd);
		page.setTotalCount(totalCount);
		List<BorrowVo> list = queryBorrowRocordMapper.queryBorrowRecordList(repaymentBorrowCnd, page);
		page.setResult(list);
		return page;
	}
	public BorrowVo queryBorrowTotal(BorrowCnd repaymentBorrowCnd) throws Exception {
		BorrowVo borrowVo = queryBorrowRocordMapper.queryBorrowTotal(
				repaymentBorrowCnd);
		return borrowVo;
	}
	/**
	 * <p>
	 * Description:借款标查询，不分页<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param repaymentBorrowCnd
	 * @return
	 * @throws Exception List<BorrowVo>
	 */
	public List<BorrowVo> searchListBorrowList(BorrowCnd repaymentBorrowCnd) throws Exception {
		List<BorrowVo> list = queryBorrowRocordMapper.queryBorrowRecordList(repaymentBorrowCnd);
		return list;
	}

	public BorrowVo findBorrow(Integer borrowId) throws Exception{
		return borrowMapper.selectByPrimaryKey(borrowId);
		
	}
	
	/**
	 * <p>
	 * Description:撤标<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月29日
	 * @param borrowId
	 * @return
	 * @throws Exception String
	 */
	@Transactional(rollbackFor = Throwable.class)
	public String cancelBorrow(int borrowId) throws Exception {
		BorrowVo borrowVo = borrowMapper.selectByPrimaryKey(borrowId);
		if (borrowVo == null) {
			return "该借款标不存在，无法撤标！";
		}
		if (borrowVo.getStatus() != 2) {
			return "该借款标状态已变更，请刷新后重试！";
		}
		if(borrowVo.getIsCustody()!=0){
			return "该借款标不是非存管标，无法撤标！";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("borrowid", borrowId);
		map.put("type", 1);
		map.put("addip", "127.0.0.1");
		map.put("platform", 1);
		map.put("msg", null);
		borrowMapper.cancelBorrow(map);
		if (map.get("msg") != null && map.get("msg").toString().equals("00001")) {
			return "撤标成功！";
		} else {
			throw new AppException("撤标失败！");
		}
	}
	
	
	
	
	public MessageBox cancelCGBorrow(int borrowId,ShiroUser shiroUser,String relateNo) throws Exception {
		BorrowVo borrowVo = borrowMapper.selectByPrimaryKey(borrowId);
		if (borrowVo == null) {
			return MessageBox.build("1", "该借款标不存在，无法撤标！");
		}
		if (borrowVo.getStatus() != 2) {
			return MessageBox.build("1", "该借款标状态已变更，请刷新后重试！");
		}
		if(borrowVo.getIsCustody()!=1){
			return MessageBox.build("1", "该借款标不是存管标，无法撤标！");
		}
		if(borrowVo.getIsCheck()!=1){
			return MessageBox.build("1", "该借款标没有登记，无法撤标！");
		}
		//创建流标报文
		Map<String, Object> map= tenderRecordMapper.queryTenderRecordByBorrowId(borrowId);
		Integer count=Integer.parseInt(map.get("total_count").toString());
		Integer amout=new BigDecimal(map.get("total_account").toString()).multiply(new BigDecimal(100)).intValue();
		String reqMessage=this.createPTRReqXml(borrowVo,count,amout);
		System.out.println(reqMessage);
		String rep= XmlUtil.send(reqMessage, CGBusinessConstants.PTRReq);
		//记录项目登记日志
		MessageRecord messageRecord=new MessageRecord();
		messageRecord.setMode("23");//场景 23项目投资信息登记
		messageRecord.setType(0);//0:主动，1:被动
		messageRecord.setMsg(reqMessage);//报文体
		messageRecord.setOrderNo(borrowVo.getId().toString());
		messageRecord.setOptUserid(shiroUser.getUserId());
		messageRecord.setPlatform(shiroUser.getPlatform());
		messageRecord.setRemark("流标接口调用");
		messageRecord.setRelateNo(relateNo);//调用关联号
		messageRecordMapper.insert(messageRecord);
		
		return MessageBox.build("0", rep);
		
	}
	
	public String savePTRRes(String rep,ShiroUser shiroUser,String relateNo,Integer borrowId) throws Exception{
			//记录项目登记接口响应日志
				MessageRecord messageRecord=new MessageRecord();
				messageRecord.setMode("23");//场景 6(项目基本信息登记)
				messageRecord.setType(1);//0:主动，1:被动
				messageRecord.setMsg(rep);//报文体
				messageRecord.setOrderNo(borrowId.toString());
				messageRecord.setOptUserid(shiroUser.getUserId());
				messageRecord.setPlatform(shiroUser.getPlatform());
				messageRecord.setRemark("流标接口调用响应");
				messageRecord.setRelateNo(relateNo);//调用关联号
				messageRecordMapper.insert(messageRecord);
				
				
				//判断响应报文
				boolean isError=XmlUtil.checkXml(rep);
				if(isError){
					return "接口响应REEOR报文,请刷新后重试！";
				}
				
				//验签
				boolean istrue= XmlUtil.checkYq(rep);
				if(!istrue){
					return "验签失败,请刷新后重试！";
				}
				
				//解析报文，更新借款标
				try {
					PTRRes pTRRes =this.parsePTRResXml(rep);
					//更新投资信息（投资成功笔数.....）
					BorrowVo bvo=new BorrowVo();
					bvo.setId(borrowId);
					bvo.setSuccount(pTRRes.getSuccount());
					bvo.setSucsum(new BigDecimal(pTRRes.getSucsum()/100));
					bvo.setFailcount(pTRRes.getFailcount());
					bvo.setFailsum(new BigDecimal(pTRRes.getFailsum()/100)  );
					borrowMapper.updateCGBorrowZS(bvo);
				} catch (Exception e) {
					logger.info(e);
					return "报文解析异常！,请速处理";
				}
				
				
				return BusinessConstants.SUCCESS;
	}
	
	
	public PTRRes parsePTRResXml(String repXml) throws Exception{
		PTRRes pTRRes=new PTRRes();
		Map<String, Object> map= XmlUtil.toBiz(repXml, CGBusinessConstants.PTRRes);
		pTRRes.setSuccount(Integer.parseInt((String)map.get("succount")) );
		pTRRes.setSucsum(Integer.parseInt((String)map.get("sucsum")));
		pTRRes.setFailcount(Integer.parseInt((String)map.get("failcount")));
		pTRRes.setFailsum(Integer.parseInt((String)map.get("failsum")));
		
		return pTRRes;
		
	}
	
	
	public Integer updateBorrowStatusById(Borrow borrow) throws Exception{
		return borrowMapper.updateBorrowStatusById(borrow);
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:组建流标报文<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年6月1日
	 * @param borrowVo
	 * @return
	 * String
	 */
	public String createPTRReqXml(BorrowVo borrowVo,Integer count,Integer amout){
		 XStream xstream = new XStream();
		 xstream = new XStream(new DomDriver());
		 Finance finance =new Finance();
		 Message message =new Message();
		 message.setId(UUIDGenerator.generate(CGBusinessConstants.MSGID));
		 PTRReq pTRReq =new PTRReq();
		 pTRReq.setDate(DateUtils.format(new Date(), DateUtils.YMDH_M_S));
		 pTRReq.setProjectId(borrowVo.geteProjectId());
		 pTRReq.setProjectName(borrowVo.getName());
		 pTRReq.setProjectStatus("0");//满标
		/* pTRReq.setRepayName(borrowVo.getRepayName());
		 pTRReq.setRepayAcct(borrowVo.getRepayAcct());*/
		 pTRReq.setCount(count);
		 pTRReq.setAmout(amout);
			xstream.autodetectAnnotations(true);
	        xstream.aliasField(CGBusinessConstants.PTRReq, Message.class, "Mode");
	        xstream.aliasSystemAttribute(null, "class"); // 去掉 class 属性[/color]  
	        message.setMode(pTRReq);
	        finance.setMessage(message);
		
		return xstream.toXML(finance);
		 
	
	}
	
	
	
	

	/**
	 * <p>
	 * Description:根据待还id执行垫付<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月29日
	 * @param repaymentId
	 * @param addip
	 * @return
	 * @throws Exception String
	 */
	public String saveWebpayBorrow(Integer repaymentId, String addip) throws Exception {
		BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordService.queryRepaymentByIdForUpdate(repaymentId);
		if (null == bRepaymentRecordVo) {
			return "待还记录不存在";
		}
		if (!(bRepaymentRecordVo.getStatus() == Constants.REPAYMENTRECORD_STATUS_NO_PAY && bRepaymentRecordVo.getWebstatus() == Constants.REPAYMENTRECORD_WEBSTATUS_NO_PAY)) {
			return "待还记录状态已变更,请确认";
		}
		String now = DateUtils.date2TimeStamp(DateUtils.format(new Date(), DateUtils.YMD_DASH)); // 当天的时间戳
		String rpaymentTime = DateUtils.date2TimeStamp(DateUtils.TimeStamp2Date(bRepaymentRecordVo.getRepaymentTime())); // 当天的时间戳
		if (Integer.parseInt(now) < Integer.parseInt(rpaymentTime)) {
			return "不能提前执行垫付！";
		}
		if (bRepaymentRecordService.selectAdvaceRepair(bRepaymentRecordVo.getOrder(), bRepaymentRecordVo.getBorrowId()) > 0) {
			return "您前面还有未还款的期数，不能跨期垫付！";
		}

		// 还款引发债权转让撤销
		cancelTransfer(bRepaymentRecordVo.getBorrowId());

		// 还款引发债权转让撤销
		cancelFirstTransfer(bRepaymentRecordVo.getBorrowId(), addip);

		// 根据借款标获取转让中的定期宝
		List<FixBorrowTransferVo> bfList = fixBorrowTransferMapper.queryFixBorrowTransferByBorrowId(bRepaymentRecordVo.getBorrowId());
		// 定期宝自动取消转让
		cancelOrTransferFixBorrow(bfList, "cancel");

		Map<String, Object> params = new HashMap<>();
		params.put("repaymentid", repaymentId);
		params.put("addip", addip);
		borrowMapper.webpayBorrow(params);
		String msg = params.get("msg").toString();
		if (!"00001".equals(msg)) {
			throw new AppException("垫付失败");
		}

		// 定期宝自动发起转让
		cancelOrTransferFixBorrow(bfList, "transfer");

		return BusinessConstants.SUCCESS;
	}

	private void cancelTransfer(Integer borrowId) throws Exception {
		// 还款，判断是否有债权转让存在，有的，撤销债权转让；
		// 借款表id查询对应的多个债权转让id列表
		List<Integer> cancelTransfers = transferMapper.queryCancelTransfers(borrowId);

		if (cancelTransfers.size() > 0) {
			for (Integer tid : cancelTransfers) {
				Map<String, Object> mapTrans = new HashMap<String, Object>();
				mapTrans.put("id", tid);
				mapTrans.put("cancelIP", "0.0.0.0");
				mapTrans.put("cancelUser", 0);
				mapTrans.put("cancelRemark", "还款时撤销-后台垫付");
				mapTrans.put("transferStatus", 6);
				mapTrans.put("msg", "");

				transferMapper.transferCancelByRepay(mapTrans);
				if ("0001".equals(mapTrans.get("msg".toString()))) {

				} else {
					throw new Exception("还款时撤销-后台垫付出错");
				}
			}
		}

	}

	/**
	 * <p>
	 * Description:普通车转让<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月29日
	 * @param borrowId
	 * @param addIp
	 * @throws Exception void
	 */
	private void cancelFirstTransfer(Integer borrowId, String addIp) throws Exception {
		ShiroUser shiroUser = ShiroUtils.currentUser();

		Map<String, Object> params = new HashMap<String, Object>();
		// 直通车转让Id
		params.put("borrowId", borrowId);
		// 用户ID
		params.put("userId", shiroUser.getUserId());
		// 用户名
		params.put("userName", shiroUser.getUserName());
		// IP地址
		params.put("addip", addIp);
		// 备注
		params.put("remark", "后台垫付时直通车转让撤销");
		// 平台来源
		params.put("platform", null);
		// 撤销原因;1:还款时撤销
		params.put("type", 1);
		// 状态
		params.put("status", 6);
		borrowMapper.cancelFirstTransfer(params);
		// 存储过程返回参数
		String msg = params.get("msg").toString();
		if (!"00000".equals(msg)) {
			throw new AppException("还款时直通车撤销出错");
		}
	}

	/**
	 * <p>
	 * Description:分页查询还款中和还款结束的借款标列表 集合<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月30日
	 * @param borrowCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception Page
	 */
	public Page queryBorrowListForRepay(BorrowCnd borrowCnd, Integer curPage, Integer pageSize) throws Exception {
		Page p = new Page();
		p.setPageNo(curPage);
		p.setPageSize(pageSize);
		if (borrowCnd.getSuccessTimeBegin() != null) {
			borrowCnd.setSuccessTimeBeginStr(DateUtils.dateTime2TimeStamp(DateUtils.format(borrowCnd.getSuccessTimeBegin(), DateUtils.YMD_DASH) + " 00:00:00"));
		}
		if (borrowCnd.getSuccessTimeEnd() != null) {
			borrowCnd.setSuccessTimeEndStr(DateUtils.dateTime2TimeStamp(DateUtils.format(borrowCnd.getSuccessTimeEnd(), DateUtils.YMD_DASH) + " 23:59:59"));
		}
		int totalCount = borrowMapper.queryBorrowCountForRepay(borrowCnd);
		p.setTotalCount(totalCount);
		List<BorrowVo> list = borrowMapper.queryBorrowListForRepay(borrowCnd, p);
		p.setResult(list);
		return p;
	}

	/*
	 * 还款
	 */
	@Transactional(rollbackFor = Throwable.class)
	public String saveRepayBorrow(Integer repaymentid, String addip, Integer userId, ShiroUser staff, String borrowUserName) throws Exception {

		String result = BusinessConstants.SUCCESS;
		// 获得待还记录并锁定
		BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordService.queryRepaymentByIdForUpdate(repaymentid);

		// 验证还款的数据正确性
		result = validateRepayBorrowData(userId, bRepaymentRecordVo);
		if (!result.equals(BusinessConstants.SUCCESS)) {
			return result;
		}

		BorrowVo borrowVo =  borrowMapper.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());

		// 还款时间和当前时间相差的天数
		Date repaymentTimeDate = new Date(Long.parseLong(bRepaymentRecordVo.getRepaymentTime()) * 1000);
		repaymentTimeDate = DateUtils.parse(DateUtils.format(repaymentTimeDate, DateUtils.YMD_DASH), DateUtils.YMD_DASH);
		Date now = DateUtils.parse(DateUtils.format(new Date(), DateUtils.YMD_DASH), DateUtils.YMD_DASH);
		Date borrowSuccessTime =  new Date(Long.parseLong(borrowVo.getSuccessTime()) * 1000);
		int earlyDays = DateUtils.dayDiff(repaymentTimeDate, now);

		// 验证是否有足够的余额还款
		result = validateHaveEnoughMoney(userId, bRepaymentRecordVo, repaymentTimeDate, now);
		if (!result.equals(BusinessConstants.SUCCESS)) {
			return result;
		}

		// 判断不能再满标前提前还款
		int  curday = DateUtils.dayDiff(now, borrowSuccessTime);
		if(curday < 0){
			return "不能在满标前进行还款操作！";
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("borrowid", bRepaymentRecordVo.getBorrowId());
		map.put("repaymentid", repaymentid);
		map.put("addip", addip);
		map.put("platform", 3); // 平台来源(1：网页 2、微信 ) 后台操作还款
		// 借款标
		Borrow borrow = borrowMapper.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());


		// 还款引发债权转让撤销
		cancelTransfer(bRepaymentRecordVo.getBorrowId());

		// 还款引发直通车转让撤销
		cancelFirstTransfer(bRepaymentRecordVo.getBorrowId(), addip);

		// 根据借款标获取转让中的定期宝
		List<FixBorrowTransferVo> bfList = fixBorrowTransferMapper.queryFixBorrowTransferByBorrowId(bRepaymentRecordVo.getBorrowId());
		boolean flag = false;

		// 只有 1：按月分期还款，2：按月付息到期还本 , 3：到期还本付息，才启用提前还款，比应还款日提前3天以上（不包含3天）
		if (earlyDays > 3) {
			if(borrow.getStyle() != BusinessConstants.BORROW_STYLE_DUE_PAY_ALL 
					&& borrow.getStyle() != BusinessConstants.BORROW_STYLE_MONTH_PAY_INTEREST
					&& borrow.getStyle() != BusinessConstants.BORROW_STYLE_MONTH_INSTALMENTS){
				throw new AppException("还款方式有误");
			}
			// 定期宝自动取消转让
			cancelOrTransferFixBorrow(bfList, "cancel");
			flag = true;
			// 提前还款
			borrowMapper.repayEarlyBorrow(map);
		} else {
			// 已垫付
			if (bRepaymentRecordVo.getWebstatus() == Constants.REPAYMENTRECORD_WEBSTATUS_HAVE_PAY) {
				// 垫付后还款
				borrowMapper.afterWebpayBorrow(map);
			} else {
				// 定期宝自动取消转让
				cancelOrTransferFixBorrow(bfList, "cancel");
				flag = true;
				// 正常还款
				borrowMapper.repayBorrow(map);
			}
		}
		String msg = map.get("msg").toString();
		if (!"00001".equals(msg)) {
			throw new AppException("还款失败");
		} else {
			try {
				RepaymentrecordlogVo repaymentrecordlogVo = new RepaymentrecordlogVo();
				repaymentrecordlogVo.setBorrowName(borrow.getName());
				repaymentrecordlogVo.setBorrowUserName(borrowUserName);
				repaymentrecordlogVo.setRepaymentrecordid(repaymentid);
				repaymentrecordlogVo.setAddip(addip);
				repaymentrecordlogVo.setApr(borrow.getApr().doubleValue() + "");
				repaymentrecordlogVo.setBorrowId(bRepaymentRecordVo.getBorrowId());
				repaymentrecordlogVo.setDealUserId(staff.getUserId());
				repaymentrecordlogVo.setDealUserName(staff.getUserName());
				repaymentrecordlogVo.setAddtime(new Date());
				bRepaymentRecordLogMapper.insert(repaymentrecordlogVo);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		if (flag) {
			// 定期宝自动取消转让
			cancelOrTransferFixBorrow(bfList, "transfer");
		}

		return BusinessConstants.SUCCESS;
	}
	/**
	 * <p>
	 * Description:验证还款数据的正确性<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年8月27日
	 * @param userId
	 * @param bRepaymentRecordVo
	 * @return
	 * @throws Exception String
	 */
	private String validateRepayBorrowData(Integer userId, BRepaymentRecordVo bRepaymentRecordVo) throws Exception {
		String result = BusinessConstants.SUCCESS;
		if (null == bRepaymentRecordVo) {
			return "待还数据不存在，请确认！";
		}
		if (!bRepaymentRecordVo.getUserId().equals(userId)) {
			return "非法还款数据,请确认！";
		}
		if (bRepaymentRecordVo.getStatus() == Constants.REPAYMENTRECORD_STATUS_HAVE_PAY) {
			return "该笔借款已还清！请勿重复操作！";
		}
		if (bRepaymentRecordVo.getStatus() != Constants.REPAYMENTRECORD_STATUS_NO_PAY) {
			return "待还数据非待还中,请确认！";
		}

		// 判断在这之前是否还有未还资金
		Integer lessOrderUnpayCount = bRepaymentRecordService.queryBeforeOrderUnPayCount(bRepaymentRecordVo.getBorrowId(), bRepaymentRecordVo.getOrder());
		if (null != lessOrderUnpayCount && lessOrderUnpayCount > 0) {
			return "该笔还款之前尚有未结清的还款,请核实！";
		}

		// 还款时判断待收总额(待收表）是否与待还总额（待还表)差异的绝对值
		CollectionStatisticCnd collectionStatisticCnd = new CollectionStatisticCnd();
		collectionStatisticCnd.setRepaymentId(bRepaymentRecordVo.getId());
		collectionStatisticCnd.setBorrowId(bRepaymentRecordVo.getBorrowId());
		// 已垫付
		if (bRepaymentRecordVo.getWebstatus() == Constants.REPAYMENTRECORD_WEBSTATUS_HAVE_PAY) {

			collectionStatisticCnd.setStatus(Constants.COLLECTION_RECORD_STATUS_WEBPAY);
		} else {
			collectionStatisticCnd.setStatus(Constants.COLLECTION_RECORD_STATUS_UNPAY);
		}
		collectionStatisticCnd.setLockedRecordYn(BusinessConstants.LOCKED_RECORD_YES);
		CollectionRepayInfoVo collectionRepayInfoVo = collectionRecordService.queryRepayTotalByCnd(collectionStatisticCnd);
		// 投标人数小于等于一千，差值为5块钱，投标人数小于等于两千但大于一千，差值可以为10块,大于两千小于等于三千，差值为15，大于三千20 判断
		BigDecimal compareMoney = new BigDecimal("5");
		if (collectionRepayInfoVo.getCollectionPersons() <= 1000) {
			compareMoney = new BigDecimal("5");
		} else if (collectionRepayInfoVo.getCollectionPersons() > 1000 && collectionRepayInfoVo.getCollectionPersons() <= 2000) {
			compareMoney = new BigDecimal("10");
		} else if (collectionRepayInfoVo.getCollectionPersons() > 2000 && collectionRepayInfoVo.getCollectionPersons() <= 3000) {
			compareMoney = new BigDecimal("15");
		} else {
			compareMoney = new BigDecimal("20");
		}
		BigDecimal diff = bRepaymentRecordVo.getRepaymentAccount().subtract(collectionRepayInfoVo.getRepayAccountTotal());
		if (diff.abs().compareTo(compareMoney) == 1) {
			return "应还款金额与待收金额不匹配,请联系客服";
		}

		return result;
	}

	/**
	 * <p>
	 * Description:验证是否有足够的余额还款<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年8月27日
	 * @param userId
	 * @param bRepaymentRecordVo
	 * @param repaymentTimeDate
	 * @param now
	 * @return
	 * @throws Exception String
	 */
	private String validateHaveEnoughMoney(Integer userId, BRepaymentRecordVo bRepaymentRecordVo, Date repaymentTimeDate, Date now) throws Exception {
		String result = BusinessConstants.SUCCESS;
		// 逾期天数
		int lateDays = DateUtils.dayDiff(now, repaymentTimeDate);
		// 罚息金额
		BigDecimal lateDayInterest = BigDecimal.ZERO;
		if (lateDays > 0) {
			lateDayInterest = bRepaymentRecordVo.getRepaymentAccount().multiply(new BigDecimal(BusinessConstants.OUT_OF_DAYE_RATE)).multiply(new BigDecimal(lateDays)).setScale(2, RoundingMode.UP);
		}
		// 应还总金额 = 还款金额 + 罚息金额
		BigDecimal totalPayMoney = bRepaymentRecordVo.getRepaymentAccount().setScale(2, RoundingMode.UP).add(lateDayInterest);
		// 借款者帐号
		AccountVo accoutVo = accountService.queryAccountByUserIdForUpdate(userId);
		if (totalPayMoney.compareTo(accoutVo.getUseMoney()) == 1) {
			StringBuffer noenoughMoneymsg = new StringBuffer();
			noenoughMoneymsg.append("您的账户余额不足！应还金额为：");
			noenoughMoneymsg.append(bRepaymentRecordVo.getRepaymentAccount().setScale(2, RoundingMode.UP));
			if (lateDays > 0) {
				noenoughMoneymsg.append("，缴纳罚息为：");
				noenoughMoneymsg.append(lateDayInterest);
			}
			noenoughMoneymsg.append("，应充值金额：");
			noenoughMoneymsg.append(totalPayMoney.subtract(accoutVo.getUseMoney()));
			return noenoughMoneymsg.toString();
		}
		return result;
	}


	/**
	 * 定期宝取消转让或发起转让
	 * @author WangQianJin
	 * @throws Exception
	 * @title @param bfList
	 * @title @param type
	 * @date 2015年9月15日
	 */
	private void cancelOrTransferFixBorrow(List<FixBorrowTransferVo> bfList, String type) throws Exception {
		if (bfList != null && bfList.size() > 0) {
			for (FixBorrowTransferVo fixBorrowTransferVo : bfList) {
				if ("cancel".equals(type)) {
					// 定期宝自动取消转让
					fixBorrowTransferService.saveTransferCancel(fixBorrowTransferVo.getId(), "0.0.0.1");
				} else if ("transfer".equals(type)) {
					// 定期宝自动发起转让
					fixBorrowService.saveTransfer(fixBorrowTransferVo.getFixBorrowId(), "0.0.0.1");
				}
			}
		}
	}
}
