package com.cxdai.console.borrow.manage.service;

import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.borrow.approve.entity.TenderRecord;
import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.approve.vo.CollectionRepayInfoVo;
import com.cxdai.console.borrow.approve.vo.CollectionStatisticCnd;
import com.cxdai.console.borrow.autotender.service.CollectionRecordService;
import com.cxdai.console.borrow.emerg.mapper.TenderRecordMapper;
import com.cxdai.console.borrow.manage.mapper.*;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.borrow.manage.vo.EFundRepayMent;
import com.cxdai.console.borrow.manage.vo.EFundRepayMentCnd;
import com.cxdai.console.borrow.manage.vo.RepaymentrecordlogVo;
import com.cxdai.console.cg.mapper.MessageRecordMapper;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.fix.mapper.BCollectionrecordMapper;
import com.cxdai.console.fix.mapper.FixBorrowTransferMapper;
import com.cxdai.console.fix.service.FixBorrowService;
import com.cxdai.console.fix.service.FixBorrowTransferService;
import com.cxdai.console.fix.vo.BCollectionRecordCnd;
import com.cxdai.console.fix.vo.BCollectionRecordVo;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.transfer.mapper.TransferMapper;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.StringUtils;
import com.cxdai.console.util.exception.AppException;
import com.cxdai.console.util.serialnumberutil.UUIDGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/8.
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CGBorrowManageService {

    private Logger logger=Logger.getLogger(CGBorrowManageService.class);

    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private BRepaymentRecordService bRepaymentRecordService;
    @Autowired
    private BRepaymentRecordLogMapper bRepaymentRecordLogMapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CollectionRecordService collectionRecordService;
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

    @Autowired
    private CGRepayMentMapper cgRepayMentMapper;
    @Autowired
    private BRepaymentRecordMapper bRepaymentRecordMapper;
    @Autowired
    private CGRepayMentService cgRepayMentService;


    /*
	 * 存管还款
	 */
    @Transactional(rollbackFor = Throwable.class)
    public MessageBox saveCustodyRepayBorrow(Integer repaymentid, String addip, Integer userId, ShiroUser staff, String borrowUserName) throws Exception {

        String result = BusinessConstants.SUCCESS;
        // 获得待还记录并锁定
        BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordService.queryRepaymentByIdForUpdate(repaymentid);

        // 验证还款的数据正确性
        result = validateRepayBorrowData(userId, bRepaymentRecordVo);
        if (!result.equals(BusinessConstants.SUCCESS)) {return MessageBox.build("0",result);}

        //还款时间和当前时间相差的天数
        Date repaymentTimeDate = new Date(Long.parseLong(bRepaymentRecordVo.getRepaymentTime()) * 1000);
        repaymentTimeDate = DateUtils.parse(DateUtils.format(repaymentTimeDate, DateUtils.YMD_DASH), DateUtils.YMD_DASH);
        Date now = DateUtils.parse(DateUtils.format(new Date(), DateUtils.YMD_DASH), DateUtils.YMD_DASH);
        // 验证是否有足够的余额还款
        result = validateCustodyHaveEnoughMoney(userId, bRepaymentRecordVo, repaymentTimeDate, now);
        if (!result.equals(BusinessConstants.SUCCESS)) {
            return MessageBox.build("0",result);
        }

        // 借款标
        Borrow borrow = borrowMapper.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());
        // 提前天数
        int earlyDays = DateUtils.dayDiff(repaymentTimeDate, now);
        String msg="";
        int bizType =0;

        // 判断不能再满标前提前还款
        BorrowVo borrowVo =  borrowMapper.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());
        Date borrowSuccessTime =  new Date(Long.parseLong(borrowVo.getSuccessTime()) * 1000);
        int  curday = DateUtils.dayDiff(now, borrowSuccessTime);
        if(curday < 0){
            return MessageBox.build("0","不能在满标前进行还款操作！");
        }

        EFundRepayMentCnd efundCnd =new EFundRepayMentCnd();
        efundCnd.setReapaymentId(bRepaymentRecordVo.getId());
        efundCnd.setIsSend(1);
        efundCnd.setAddtime(new Date());
       // efundCnd.setOrder(bRepaymentRecordVo.getOrder());
        int efundCount = eFundRepayMentMapper.queryRepayMentCount(efundCnd);
        if(efundCount >0){
            return MessageBox.build("0","当天当期存在已上报本息还款记录，不能再次上报");
        }

        // 只有抵押标到期还本付息和按月付息到期还本两种类型，才启用提前还款，比应还款日提前3天以上（不包含3天）
        // 只有信用标等额本息类型，才启用提前还款，比应还款日提前3天以上（不包含3天）
        try {
            if (earlyDays > 3)
            {
                if(borrow.getStyle() != BusinessConstants.BORROW_STYLE_DUE_PAY_ALL
                        && borrow.getStyle() != BusinessConstants.BORROW_STYLE_MONTH_PAY_INTEREST
                        && borrow.getStyle() != BusinessConstants.BORROW_STYLE_MONTH_INSTALMENTS){
                    return MessageBox.build("0","还款方式有误");
                }
                bizType=2;
                repayEarly(repaymentid,  addip,  userId,  staff,  borrowUserName);
            } else {
                // 已垫付
                if (bRepaymentRecordVo.getWebstatus() == Constants.REPAYMENTRECORD_WEBSTATUS_HAVE_PAY) {
                    return MessageBox.build("0","暂不开放垫付后还款功能");
                } else {
                    bizType=1;
                    repayNormal( repaymentid,  addip,  userId,  staff,  borrowUserName);
                }
            }
        } catch (Exception e) {
            logger.error("生成还款记录失败，还款类型："+bizType,e);
            throw new AppException("生成还款记录失败，还款类型："+bizType);
        }
        saveRepayMentLog( borrow,addip, staff, borrowUserName,repaymentid);
        return  MessageBox.build("1", String.valueOf(bizType));
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
            return "存管待还数据不存在，请确认！";
        }
        if (!bRepaymentRecordVo.getUserId().equals(userId)) {
            return "非法存管还款数据,请确认！";
        }
        if (bRepaymentRecordVo.getStatus() == Constants.REPAYMENTRECORD_STATUS_HAVE_PAY) {
            return "该笔存管借款已还清！请勿重复操作！";
        }
        if (bRepaymentRecordVo.getStatus() != Constants.REPAYMENTRECORD_STATUS_NO_PAY) {
            return "存管待还数据非待还中,请确认！";
        }

        if (bRepaymentRecordVo.getStatus() == Constants.REPAYMENTRECORD_STATUS_NO_PAY) {
            if(bRepaymentRecordVo.getIsCustody() ==1 && bRepaymentRecordVo.getWebstatus() ==1){
                return "存管待还数据垫付后还款请在'存管还款中借款标'菜单进行操作！";
            }

        }

        if (bRepaymentRecordVo.getStatus() == Constants.REPAYMENTRECORD_STATUS_BANK_PAY) {
            return "待还数据处于银行还款中状态，请勿重复操作！";
        }

        if (bRepaymentRecordVo.getWebstatus() == Constants.REPAYMENTRECORD_WEBSTATUS_BANK_PAY) {
            return "待还数据处于银行垫付还款中状态，请勿重复操作！";
        }

        // 判断在这之前是否还有未还资金
        Integer lessOrderUnpayCount = bRepaymentRecordService.queryBeforeOrderUnPayCount(bRepaymentRecordVo.getBorrowId(), bRepaymentRecordVo.getOrder());
        if (null != lessOrderUnpayCount && lessOrderUnpayCount > 0) {
            return "该笔还款之前尚有未结清的还款,请核实！";
        }

        // 判断待收待还是否匹配
        CollectionStatisticCnd collectionStatisticCnd = new CollectionStatisticCnd();
        collectionStatisticCnd.setRepaymentId(bRepaymentRecordVo.getId());
        collectionStatisticCnd.setBorrowId(bRepaymentRecordVo.getBorrowId());
        // 已垫付
        if (bRepaymentRecordVo.getWebstatus() == Constants.REPAYMENTRECORD_WEBSTATUS_HAVE_PAY) {

            collectionStatisticCnd.setStatus(Constants.COLLECTION_RECORD_STATUS_BANK_WEBPAY);
        } else {
            collectionStatisticCnd.setStatus(Constants.COLLECTION_RECORD_STATUS_UNPAY);
        }
        collectionStatisticCnd.setLockedRecordYn(BusinessConstants.LOCKED_RECORD_YES);
        CollectionRepayInfoVo collectionRepayInfoVo = collectionRecordService.queryRepayTotalByCnd(collectionStatisticCnd);
        if (collectionRepayInfoVo.getRepayAccountTotal().compareTo(bRepaymentRecordVo.getRepaymentAccount()) != 0) {
            return "应还款金额与待收金额不匹配,请联系客服";
        }
        return result;
    }


    private String validateCustodyHaveEnoughMoney(Integer userId, BRepaymentRecordVo bRepaymentRecordVo, Date repaymentTimeDate, Date now) throws Exception {
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
        if (totalPayMoney.compareTo(accoutVo.geteUseMoney()) == 1) {
            StringBuffer noenoughMoneymsg = new StringBuffer();
            noenoughMoneymsg.append("您的账户余额不足！应还金额为：");
            noenoughMoneymsg.append(bRepaymentRecordVo.getRepaymentAccount().setScale(2, RoundingMode.UP));
            if (lateDays > 0) {
                noenoughMoneymsg.append("，缴纳罚息为：");
                noenoughMoneymsg.append(lateDayInterest);
            }
            noenoughMoneymsg.append("，应充值金额：");
            noenoughMoneymsg.append(totalPayMoney.subtract(accoutVo.geteUseMoney()));
            return noenoughMoneymsg.toString();
        }
        return result;
    }


    /**
     * 生成存管正常还款上报数据
     * @param repaymentid
     * @param addip
     * @param userId
     * @param staff
     * @param borrowUserName
     * @return
     * @throws Exception
     */
    public void repayNormal(Integer repaymentid, String addip, Integer userId, ShiroUser staff, String borrowUserName) throws Exception {
        // 获得待还记录并锁定
        BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordService.queryRepaymentByIdForUpdate(repaymentid);

        //获取当期的待收记录并锁定
        BCollectionRecordCnd bCollectionRecordCnd=new BCollectionRecordCnd();
        bCollectionRecordCnd.setOrder(bRepaymentRecordVo.getOrder());
        bCollectionRecordCnd.setBorrowId(bRepaymentRecordVo.getBorrowId());
        List<BCollectionRecordVo> collectionList =  bCollectionrecordMapper.queryCollectionForRepay(bCollectionRecordCnd);

        //查询借款标记录
        BorrowVo borrowVo =  borrowMapper.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());
        String eProjectId=borrowVo.geteProjectId();


        BigDecimal repayMentAccount = BigDecimal.ZERO;
        int borrow_borrowtype = borrowVo.getBorrowtype().intValue(); //借款标类型
        int lateday = cgRepayMentService.lateday(repaymentid); //逾期天数
        BigDecimal ratio1=new BigDecimal(0.001);
        BigDecimal ratio2=new BigDecimal(0.002);
        //根据待收生成本息还款数据
        logger.info("===开始生成正常本息还款上报数据===，待收记录条数："+collectionList.size());
        for(BCollectionRecordVo collection: collectionList ){
            TenderRecord tenderVo = tenderRecordMapper.selectById(collection.getTenderId());
            BigDecimal late_interest =BigDecimal.ZERO; //逾期利息
            BigDecimal collection_repayaccount = collection.getRepayAccount();

            //先判断本息还款表是否存在该还款记录，如果存在则不重复插入数据
            EFundRepayMentCnd efundCnd =new EFundRepayMentCnd();
            efundCnd.setCollectionId(collection.getId());
            efundCnd.setReapaymentId(bRepaymentRecordVo.getId());
            efundCnd.setType(Constants.REPAYMENT_TYPE_NORMAL);
            int efundCount = eFundRepayMentMapper.queryRepayMentCount(efundCnd);
            if(efundCount>0){continue;}
            //生成本息还款数据
            EFundRepayMent eFundRepayMent=new EFundRepayMent();

            eFundRepayMent.setType(Constants.REPAYMENT_TYPE_NORMAL);
            eFundRepayMent.setBorrowId(borrowVo.getId());
            eFundRepayMent.seteProjectId(eProjectId);
            eFundRepayMent.setReapaymentId(bRepaymentRecordVo.getId());
            eFundRepayMent.setCollectionId(collection.getId());
            eFundRepayMent.setOrder(collection.getOrder());
            eFundRepayMent.setRepayName(borrowVo.getRepayName());
            eFundRepayMent.setInnerFlag(1);
            eFundRepayMent.setBizNo(UUIDGenerator.generate(Constants.CUSTODY_SERIAL_FRR1));//-----------
            eFundRepayMent.seteInvestNo(tenderVo.geteInvestNo());//-----------
            eFundRepayMent.setReturnType(0);
            BigDecimal collectRepayAccount= collection_repayaccount.multiply(new BigDecimal(100));
            eFundRepayMent.setRepaymentAmount(collectRepayAccount.intValue());
            eFundRepayMent.setCurrency(156);
            eFundRepayMent.setResult(0);
            eFundRepayMent.setIsSend(0);
            eFundRepayMent.setPayType(1);
            eFundRepayMent.setAddtime(new Date());
            //保存
            eFundRepayMentMapper.insert(eFundRepayMent);

            //更新待收记录，写入流水号
            BCollectionRecordCnd cr=new BCollectionRecordCnd();
            cr.setBizNo(eFundRepayMent.getBizNo());
            cr.setId(collection.getId());
            bCollectionrecordMapper.updateBizNoByID(cr);

            repayMentAccount = repayMentAccount.add(collectRepayAccount);


            if(borrow_borrowtype == 3){
                late_interest = collection_repayaccount.multiply(ratio2)
                        .multiply(new BigDecimal(lateday))
                        .setScale(2,BigDecimal.ROUND_HALF_UP);

            }else{
                late_interest = collection_repayaccount.multiply(ratio1)
                        .multiply(new BigDecimal(lateday))
                        .setScale(2,BigDecimal.ROUND_HALF_UP);
            }

            //更新待收
            Map conllectionMap = new HashMap();
            conllectionMap.put("collection_id", collection.getId());
//          conllectionMap.put("lateday", lateday);
//          conllectionMap.put("late_interest", late_interest);
            //存管逾期天数和利息按0进行处理
            conllectionMap.put("lateday", 0);
            conllectionMap.put("late_interest", 0);
            cgRepayMentMapper.updateCollectionForNormalRepay(conllectionMap);

        }

        //更新待还待还金额
        Map repayMap = new HashMap();
        repayMap.put("repay_yesaccount", bRepaymentRecordVo.getRepaymentAccount());
        repayMap.put("repaymentId", bRepaymentRecordVo.getId());
        cgRepayMentMapper.updateRepayMentAccount(repayMap);


    }


    /**
     * 生成存管提前还款上报数据
     * @param repaymentid
     * @param addip
     * @param userId
     * @param staff
     * @param borrowUserName
     * @return
     * @throws Exception
     */

    public void repayEarly(Integer repaymentid, String addip, Integer userId, ShiroUser staff, String borrowUserName) throws Exception {

        // 获得待还记录并锁定
        BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordService.queryRepaymentByIdForUpdate(repaymentid);

        //获取当期的待收记录并锁定
        BCollectionRecordCnd bCollectionRecordCnd=new BCollectionRecordCnd();
        bCollectionRecordCnd.setOrder(bRepaymentRecordVo.getOrder());
        bCollectionRecordCnd.setBorrowId(bRepaymentRecordVo.getBorrowId());
        List<BCollectionRecordVo> collectionList =  bCollectionrecordMapper.queryCollectionForRepay(bCollectionRecordCnd);
        //查询借款标记录
        BorrowVo borrowVo =  borrowMapper.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());
        String eProjectId=borrowVo.geteProjectId();


        //判断是否跨期
        boolean isCross = gGRepayMentService.isCross(borrowVo.getId(),bRepaymentRecordVo.getOrder());
        //计算应还时相差天数的开始时间
        String startTime = gGRepayMentService.startTime(borrowVo.getId(),bRepaymentRecordVo.getOrder(),repaymentid);
        //计算提前还款天数
        int earlyday = gGRepayMentService.earlyday(repaymentid);
        String repaymentTime = bRepaymentRecordVo.getRepaymentTime();

        logger.info("===开始生成提前还款上报数据===，待收记录条数："+collectionList.size());
        //根据待收生成本息还款数据
        for(BCollectionRecordVo collection: collectionList ){
            TenderRecord tenderVo = tenderRecordMapper.selectById(collection.getTenderId());

            //先判断本息还款表是否存在该还款记录，如果存在则不重复插入数据
            EFundRepayMentCnd efundCnd =new EFundRepayMentCnd();
            efundCnd.setCollectionId(collection.getId());
            efundCnd.setReapaymentId(bRepaymentRecordVo.getId());
            efundCnd.setType(Constants.REPAYMENT_TYPE_EARLY);
            int efundCount = eFundRepayMentMapper.queryRepayMentCount(efundCnd);
            if(efundCount>0){continue;}

            BigDecimal collectionInterest =collection.getInterest();

            //计算利息
            BigDecimal earlyInterest = gGRepayMentService.earlyInterest(isCross,collectionInterest,repaymentTime,startTime,earlyday);
            //最终金额
            BigDecimal repayMoney = collection.getRepayAccount().subtract(earlyInterest);
            //生成本息还款数据
            EFundRepayMent eFundRepayMent=new EFundRepayMent();

            eFundRepayMent.setType(Constants.REPAYMENT_TYPE_EARLY);
            eFundRepayMent.setBorrowId(borrowVo.getId());
            eFundRepayMent.seteProjectId(eProjectId);
            eFundRepayMent.setReapaymentId(bRepaymentRecordVo.getId());
            eFundRepayMent.setCollectionId(collection.getId());
            eFundRepayMent.setOrder(collection.getOrder());
            eFundRepayMent.setRepayName(borrowVo.getRepayName());
            eFundRepayMent.setInnerFlag(1);
            eFundRepayMent.setBizNo(UUIDGenerator.generate(Constants.CUSTODY_SERIAL_FRR2));//-----------
            eFundRepayMent.seteInvestNo(tenderVo.geteInvestNo());//-----------
            eFundRepayMent.setReturnType(0);
            eFundRepayMent.setRepaymentAmount(repayMoney.multiply(new BigDecimal(100)).intValue());
            eFundRepayMent.setCurrency(156);
            eFundRepayMent.setResult(0);
            eFundRepayMent.setIsSend(0);
            eFundRepayMent.setPayType(1);
            eFundRepayMent.setAttachAmount(earlyInterest.multiply(new BigDecimal(100)).intValue());
            eFundRepayMent.setAddtime(new Date());
            //保存
            eFundRepayMentMapper.insert(eFundRepayMent);
            //更新待收记录，写入流水号
            BCollectionRecordCnd cr=new BCollectionRecordCnd();
            cr.setBizNo(eFundRepayMent.getBizNo());
            cr.setId(collection.getId());
            bCollectionrecordMapper.updateBizNoByID(cr);

            //更新待收
            Map conllectionMap = new HashMap();
            conllectionMap.put("collection_id", collection.getId());
            conllectionMap.put("early_interest", earlyInterest);
            cgRepayMentMapper.updateCollectionForEarlyRepay(conllectionMap);

        }


        //更新待还待还金额
        Map repayMap = new HashMap();
        repayMap.put("repayment_money", bRepaymentRecordVo.getRepaymentAccount());
        repayMap.put("repaymentId", bRepaymentRecordVo.getId());
        cgRepayMentMapper.updateRepayMentAccount(repayMap);
    }



    /**
     * 生成存管提前还款上报数据
     * @param repaymentid
     * @param addip
     * @param userId
     * @param staff
     * @param borrowUserName
     * @return
     * @throws Exception
     */

    public void webPayDate(Integer repaymentid, String addip, Integer userId, ShiroUser staff, String borrowUserName) throws Exception {

    }



    /**
     * 保存待还记录操作日志,
     * @param borrow
     * @param addip
     * @param staff
     * @param borrowUserName
     * @param repaymentid
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    private void saveRepayMentLog( Borrow borrow,String addip, ShiroUser staff, String borrowUserName,Integer repaymentid){
        try {
            RepaymentrecordlogVo repaymentrecordlogVo = new RepaymentrecordlogVo();
            repaymentrecordlogVo.setBorrowName(borrow.getName());
            repaymentrecordlogVo.setBorrowUserName(borrowUserName);
            repaymentrecordlogVo.setRepaymentrecordid(repaymentid);
            repaymentrecordlogVo.setAddip(addip);
            repaymentrecordlogVo.setApr(borrow.getApr().doubleValue() + "");
            repaymentrecordlogVo.setBorrowId(borrow.getId());
            repaymentrecordlogVo.setDealUserId(staff.getUserId());
            repaymentrecordlogVo.setDealUserName(staff.getUserName());
            repaymentrecordlogVo.setAddtime(new Date());
            bRepaymentRecordLogMapper.insert(repaymentrecordlogVo);
        } catch (Exception e) {
            logger.error("保存待还记录操作日志失败！",e);
        }
    }
}
