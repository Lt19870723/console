package com.cxdai.console.borrow.manage.service;


import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.base.entity.MailSendRecord;
import com.cxdai.console.base.mapper.BaseMailSendRecordMapper;
import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.manage.mapper.BRepaymentRecordMapper;
import com.cxdai.console.borrow.manage.mapper.CGRepayMentMapper;
import com.cxdai.console.borrow.manage.mapper.EFundRepayMentMapper;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.borrow.manage.vo.EFundRepayMent;
import com.cxdai.console.cgnotify.entity.EReconDetail;
import com.cxdai.console.cgnotify.entity.EReconHeader;
import com.cxdai.console.cgnotify.mapper.EReconDetailMapper;
import com.cxdai.console.cgnotify.mapper.EReconHeaderMapper;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.fix.mapper.BCollectionrecordMapper;
import com.cxdai.console.fix.vo.BCollectionRecordVo;
import com.cxdai.console.statistics.account.entity.Account;
import com.cxdai.console.statistics.account.mapper.AccountMapper;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.util.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存管本息还款服务类
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CGRepayMentService {
    private final static Logger logger = Logger.getLogger(CGRepayMentService.class);
    @Autowired
    private BorrowManageService borrowManageService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private AccountLogService accountLogService;
    @Autowired
    private BRepaymentRecordMapper bRepaymentRecordMapper;
    @Autowired
    private BCollectionrecordMapper bCollectionrecordMapper;
    @Autowired
    private CGRepayMentMapper cgRepayMentMapper;
    @Autowired
    private EFundRepayMentMapper eFundRepayMentMapper;
    @Autowired
    private EReconHeaderMapper eReconHeaderMapper;
    @Autowired
    private EReconDetailMapper eReconDetailMapper;
    @Autowired
    private BaseMailSendRecordMapper baseMailSendRecordMapper;
    @Autowired
    private BRepaymentRecordService bRepaymentRecordService;


    @Transactional(rollbackFor = Throwable.class)
    public void RepayRecon(String eProjectId, String fileName) throws Exception {
       // EFundRepayMent eFendLast = eFundRepayMentMapper.queryEfendRepayByProject(eProjectId);
        EReconDetail eReconDetail =  eReconDetailMapper.queryOneDetail(2,eProjectId,fileName);

        if(eReconDetail == null || eReconDetail.getP2pNo() ==null || eReconDetail.getP2pNo().trim().length() ==0 ){
            StringBuilder sb = new StringBuilder();
            sb.append("<div>尊敬的各位领导，大家好</div>");
            sb.append("<div style='margin-left:20px;'>存管标存管项目编号："+eProjectId+";对账文件名："+fileName+"；</div>");
            sb.append("<div style='margin-left:20px;'>未找到合适的本息还款匹配类型。</div>");
            sb.append("<div style='margin-left:20px;'>请及时处理</div>");
            Borrow borrow = borrowMapper.selectByProjectId(eProjectId);
            sendMail(borrow.getId(),20,0,sb.toString());
            return;
        }


        String p2pNo =  eReconDetail.getP2pNo();
        String bizTypeStr= p2pNo.substring(0,3);

        logger.info("=====开始进行对账后还款处理，项目登记名称"+eProjectId+";还款类型："+bizTypeStr+"========"+DateUtils.format(new Date(), DateUtils.YMD_HMS));
        long startTime = System.currentTimeMillis();
        //正常还款流程
        if (bizTypeStr.equals(Constants.CUSTODY_SERIAL_FRR1)) {
            doNormalRepayRecon( eProjectId,  fileName);
        }

        //提前还款流程
        if (bizTypeStr.equals(Constants.CUSTODY_SERIAL_FRR2)) {
            doEarlyRepayRecon( eProjectId,  fileName);
        }


        long endTime = System.currentTimeMillis();
        float seconds = (endTime - startTime) / 1000F;
        logger.info("=====进行对账后还款处理结束，还款类型："+bizTypeStr+"========；耗时："+seconds);
    }


    @Transactional(rollbackFor = Throwable.class)
    public void doNormalRepayRecon(String eProjectId, String fileName) throws Exception {

        EReconHeader eReconHeader = eReconHeaderMapper.queryRecon(BusinessConstants.CUSTODY_RECON_REPAY, eProjectId, fileName);
        Borrow borrow = borrowMapper.selectByProjectId(eProjectId);
        StringBuilder sb = new StringBuilder();

        if (eReconHeader == null) {
            //新增邮件待发记录
            sb.append("<div>尊敬的各位领导，大家好</div>");
            sb.append("<div style='margin-left:20px;'>存管标存管项目编号："+eProjectId+";正常还款未找到银行对账头文件："+fileName+"记录</div>");
            sb.append("<div style='margin-left:20px;'>请及时处理</div>");
            sendMail(borrow.getId(),20,0,sb.toString());
            return;
        }

        Integer failNum = eReconHeader.getNoNum();
        Integer repayment_userid = borrow.getUserId();
        BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordMapper.queryRepaymentByProjectId(eProjectId);

        //全部成功的情况
        if (failNum == null || failNum.intValue() == 0) {
            logger.info("存管正常还款全部成功对账后业务处理");
            doSuccessRecord(repayment_userid,  eReconHeader, eProjectId, fileName, bRepaymentRecordVo);

        } else {
            logger.info("存管正常还款部分成功对账后业务处理");
            doSuccessRecord(repayment_userid, eReconHeader, eProjectId, fileName, bRepaymentRecordVo);

            sb.append("<div>尊敬的各位领导，大家好</div>");
            sb.append("<div style='margin-left:20px;'>存管标存管项目编号："+eProjectId+"，正常还款部分还款失败</div>");
            sb.append("<div style='margin-left:20px;'>失败笔数："+ failNum.intValue()+";失败金额："+eReconHeader.getNoMoney()+"</div>");
            sb.append("<div style='margin-left:20px;'>请及时处理</div>");
            sendMail(borrow.getId(),20,0,sb.toString());
        }

        updateStatusLastOrder(bRepaymentRecordVo,borrow);


    }


    private void doSuccessRecord(Integer repayment_userid, EReconHeader eReconHeader,
                                 String eProjectId, String fileName,
                                 BRepaymentRecordVo bRepaymentRecordVo) throws Exception {


        Borrow borrow = borrowMapper.selectByProjectId(eProjectId);

        BigDecimal repayment_money = eReconHeader.getYesMoney(); //成功还款的总额
        //~~~~~~~~~~~~借款者扣款~~~~~~~~~~~
        //查询本息还款总待收金额
        AccountVo repaymentAccount = accountService.queryAccountByUserIdForUpdate(repayment_userid);

        //借款者扣款-更新借款人账户
        repaymentAccount.setTotal(repaymentAccount.getTotal().subtract(repayment_money)); //总额减少
        repaymentAccount.seteUseMoney(repaymentAccount.geteUseMoney().subtract(repayment_money)); //可用减少
        Account account = new Account();
        BeanUtils.copyProperties(repaymentAccount, account);
        accountMapper.updateEntity(account);

        //借款者扣款-新增账户操作日志
        String repayAccountRemark = "存管还款扣除";
        String repayType = "cg_repayment_deduct";
        accountLogService.saveEAccountLogByParams(repaymentAccount, repayment_userid, repayment_money, repayAccountRemark,
                "0.0.0.1", repayType, 0, borrow.getId(), borrow.getName());


        //~~~~~~~~~~~~更新待还记录~~~~~~~~~~~
        //更新待还记录-查找待还并锁定
        // 获得待还记录并锁定
        Map repayMap = new HashMap();
        repayMap.put("repayment_money", repayment_money);
        repayMap.put("reapaymentId", bRepaymentRecordVo.getId());
        bRepaymentRecordMapper.updateRepayRecord(repayMap);

        //~~~~~~~~~~~~查询待收循环处理~~~~~~~~~~~
        List<String> p2pNoList = eReconDetailMapper.queryDetailP2PNoList(2, eProjectId, fileName, 4);
        if (p2pNoList != null && p2pNoList.size() > 0) {
            List<BCollectionRecordVo> collectionList = bCollectionrecordMapper.queryCollectionListByBizNo(p2pNoList);
            for (BCollectionRecordVo collection : collectionList) {

                Integer collectionId = collection.getId();
                // BigDecimal repaymentMoney =collection.getRepayAccount();
                BigDecimal repaymentMoney = eReconDetailMapper.queryDetailMoney(collection.getBizRepayMentNo());

                //更新待收 银行还款中--》还款完成
                Map conllectionMap = new HashMap();
                conllectionMap.put("collectionId", collectionId);
                conllectionMap.put("newStatus", 1);
                conllectionMap.put("oldStatus", 4);
                bCollectionrecordMapper.updateStatusAfterRecon(conllectionMap);

                //更新投标记录
                Map tenderrecordMap = new HashMap();
                tenderrecordMap.put("repayment_money", repaymentMoney);
                tenderrecordMap.put("tenderrecordId", collection.getTenderId());
                tenderrecordMap.put("collection_interest", collection.getInterest());
                cgRepayMentMapper.updateTenderrecord(tenderrecordMap);

                //更新投资人账户
                AccountVo investAccountVo = accountService.queryAccountByUserIdForUpdate(collection.getUserId());
                investAccountVo.seteUseMoney(investAccountVo.geteUseMoney().add(repaymentMoney)); //可用增加
                investAccountVo.seteCollection(investAccountVo.geteCollection().subtract(repaymentMoney)); //待收减少

                Account investAccount = new Account();
                BeanUtils.copyProperties(investAccountVo, investAccount);
                accountMapper.updateEntity(investAccount);

                //新增投资人账户日志
                String investAccountRemark = "存管还款入账";
                String investType = "cg_repayment_back";
                accountLogService.saveEAccountLogByParams(investAccountVo, collection.getUserId(), repaymentMoney, investAccountRemark,
                        "0.0.0.1", investType, 0, borrow.getId(), borrow.getName());

            }

        }

    }

    @Transactional(rollbackFor = Throwable.class)
    public void doEarlyRepayRecon(String eProjectId, String fileName) throws Exception {

        EReconHeader eReconHeader = eReconHeaderMapper.queryRecon(BusinessConstants.CUSTODY_REPAY_TYPE_EARLY, eProjectId, fileName);
        Borrow borrow = borrowMapper.selectByProjectId(eProjectId);
        StringBuilder sb = new StringBuilder();

        if (eReconHeader == null) {
            //新增邮件待发记录
            sb.append("<div>尊敬的各位领导，大家好</div>");
            sb.append("<div style='margin-left:20px;'>存管标存管项目编号："+eProjectId+";提前还款未找到银行对账头文件："+fileName+"记录</div>");
            sb.append("<div style='margin-left:20px;'>请及时处理</div>");

            sendMail(borrow.getId(),20,0,sb.toString());
            return;
        }

        Integer failNum = eReconHeader.getNoNum();
        BigDecimal repayment_money = eReconHeader.getYesMoney();


        Integer repayment_userid = borrow.getUserId();
        BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordMapper.queryRepaymentByProjectId(eProjectId);



        //全部成功的情况
        if (failNum == null || failNum.intValue() == 0) {
            logger.info("存管提前还款全部成功对账后业务处理");
            doSuccessEarlyRecord(repayment_userid, eReconHeader, eProjectId, fileName, bRepaymentRecordVo);

        } else {
            logger.info("存管提前还款部分成功对账后业务处理");
            doSuccessEarlyRecord(repayment_userid, eReconHeader, eProjectId, fileName, bRepaymentRecordVo);

            sb.append("<div>尊敬的各位领导，大家好</div>");
            sb.append("<div style='margin-left:20px;'>存管标存管项目编号："+eProjectId+"，提前还款部分还款失败</div>");
            sb.append("<div style='margin-left:20px;'>失败笔数："+ failNum.intValue()+";失败金额："+eReconHeader.getNoMoney()+"</div>");
            sb.append("<div style='margin-left:20px;'>请及时处理</div>");
            sendMail(borrow.getId(),20,0,sb.toString());

        }

        updateStatusLastOrder(bRepaymentRecordVo,borrow);


    }



    private void doSuccessEarlyRecord(Integer repayment_userid, EReconHeader eReconHeader,
                                 String eProjectId, String fileName,
                                 BRepaymentRecordVo bRepaymentRecordVo) throws Exception {

        BigDecimal repayment_money = eReconHeader.getYesMoney(); //成功还款的总额
        //~~~~~~~~~~~~借款者扣款~~~~~~~~~~~
        AccountVo repaymentAccount = accountService.queryAccountByUserIdForUpdate(repayment_userid);
        Borrow borrow = borrowMapper.selectByProjectId(eProjectId);

        //借款者扣款-更新借款人账户
        repaymentAccount.setTotal(repaymentAccount.getTotal().subtract(repayment_money));
        repaymentAccount.seteUseMoney(repaymentAccount.geteUseMoney().subtract(repayment_money));
        Account account = new Account();
        BeanUtils.copyProperties(repaymentAccount, account);
        accountMapper.updateEntity(account);

        //借款者扣款-新增账户操作日志
        String repayAccountRemark = "存管还款扣除";
        String repayType = "cg_repayment_deduct";
        accountLogService.saveEAccountLogByParams(repaymentAccount, repayment_userid, repayment_money, repayAccountRemark,
                "0.0.0.1", repayType,0, borrow.getId(), borrow.getName());


        //~~~~~~~~~~~~更新待还记录~~~~~~~~~~~
        //更新待还记录-查找待还并锁定
        Map repayMap = new HashMap();
        repayMap.put("repayment_money", repayment_money);
        repayMap.put("reapaymentId", bRepaymentRecordVo.getId());
        bRepaymentRecordMapper.updateRepayRecord(repayMap);

        //~~~~~~~~~~~~查询待收循环处理~~~~~~~~~~~
        List<String> p2pNoList = eReconDetailMapper.queryDetailP2PNoList(2, eProjectId, fileName, 4);
        if (p2pNoList != null && p2pNoList.size() > 0) {
            List<BCollectionRecordVo> collectionList = bCollectionrecordMapper.queryCollectionListByBizNo(p2pNoList);
            for (BCollectionRecordVo collection : collectionList) {

                EReconDetail eReconDetail = eReconDetailMapper.queryDetail(2, eProjectId, fileName, 4,collection.getBizRepayMentNo());
                Integer collectionId = collection.getId();
                BigDecimal repaymentMoney = eReconDetail.getMoney();
                BigDecimal yqRepaymentMoney=collection.getRepayAccount();


                BigDecimal early_interest= yqRepaymentMoney.subtract(repaymentMoney);//提前还款利息


                //更新待收 银行还款中--》还款完成
                Map conllectionMap = new HashMap();
                conllectionMap.put("collectionId", collectionId);
                conllectionMap.put("newStatus", 1);
                conllectionMap.put("oldStatus", 4);
                bCollectionrecordMapper.updateStatusAfterRecon(conllectionMap);

                //更新投标记录
                Map tenderrecordMap = new HashMap();
                BigDecimal collection_interest = collection.getInterest().subtract(early_interest);
                tenderrecordMap.put("repayment_money", repaymentMoney);
                tenderrecordMap.put("tenderrecordId", collection.getTenderId());
                tenderrecordMap.put("collection_interest", collection_interest);
                cgRepayMentMapper.updateTenderrecord(tenderrecordMap);

                //更新投资人账户-存管还款入账
                AccountVo investAccountVo = accountService.queryAccountByUserIdForUpdate(collection.getUserId());
                investAccountVo.seteUseMoney(investAccountVo.geteUseMoney().add(yqRepaymentMoney));
                investAccountVo.seteCollection(investAccountVo.geteCollection().subtract(yqRepaymentMoney));

                Account investAccount = new Account();
                BeanUtils.copyProperties(investAccountVo, investAccount);
                accountMapper.updateEntity(investAccount);

                //新增投资人账户日志
                String investAccountRemark = "存管提前还款入账";
                String investType = "cg_repayment_back";
                accountLogService.saveEAccountLogByParams(investAccountVo, collection.getUserId(), yqRepaymentMoney, investAccountRemark,
                        "0.0.0.1", investType,0, borrow.getId(), borrow.getName());

                //更新投资人账户-扣除提前还款利息

                /*Map eFundRepayMap = new HashMap();
                eFundRepayMap.put("collectionId", collectionId);
                eFundRepayMap.put("status", 20);
                EFundRepayMent eFundRepayMent = eFundRepayMentMapper.selectCollectionId(eFundRepayMap);
                Integer attachAmount = eFundRepayMent.getAttachAmount();

                BigDecimal interest = new BigDecimal(attachAmount / 100);*/

                AccountVo interestAccountVo = accountService.queryAccountByUserIdForUpdate(collection.getUserId());
                investAccountVo.setTotal(investAccountVo.getTotal().subtract(early_interest));
                investAccountVo.seteUseMoney(investAccountVo.geteUseMoney().subtract(early_interest));

                Account interestAccount = new Account();
                BeanUtils.copyProperties(interestAccountVo, interestAccount);
                accountMapper.updateEntity(interestAccount);

                //新增投资人账户日志
                String interestAccountRemark = "存管扣除提前还款利息";
                String interestType = "cg_subtract_early_interest";
                accountLogService.saveEAccountLogByParams(interestAccountVo, collection.getUserId(), early_interest, interestAccountRemark,
                        "0.0.0.1", interestType, 0, borrow.getId(), borrow.getName());

            }

        }
    }





    /**
     * 判断是否跨期
     *
     * @param borrowId 借款标ID
     * @param order    期数
     * @return
     * @throws Exception
     */
    public boolean isCross(Integer borrowId, Integer order) throws Exception {

        Boolean b = Boolean.FALSE;
        //第一期不存在跨期
        if (order.intValue() == 1) {
            return b;
        }

        //查询上一期还款记录
        Map map = new HashMap();
        map.put("borrowId", borrowId);
        map.put("order", order);
        BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordMapper.selectPrevRepayMent(map);
        if (bRepaymentRecordVo == null) {
            return b;
        }

        Date repaymentTimeDate = new Date(Long.parseLong(bRepaymentRecordVo.getRepaymentTime()) * 1000);
        repaymentTimeDate = DateUtils.parse(DateUtils.format(repaymentTimeDate, DateUtils.YMD_DASH), DateUtils.YMD_DASH);
        Date now = DateUtils.parse(DateUtils.format(new Date(), DateUtils.YMD_DASH), DateUtils.YMD_DASH);
        int lateDays = DateUtils.dayDiff(repaymentTimeDate, now);

        if (lateDays > 0) {
            return Boolean.TRUE;
        }
        return b;

    }


    /**
     * 计算应还时相差天数的开始时间 第一期为添加时间，第二期为上一期的应还时间
     *
     * @return
     */
    public String startTime(Integer borrowId, Integer order, Integer repaymentId) throws Exception {
        String startTime = "";
        BRepaymentRecordVo bRepaymentRecordVo = null;
        if (order != null && order.intValue() == 1) {
//            bRepaymentRecordVo = bRepaymentRecordMapper.queryBRepaymentRecordById(repaymentId);
//            startTime = bRepaymentRecordVo.getAddtime();

            BorrowVo borrow =borrowMapper.selectByPrimaryKey(borrowId);
            startTime = borrow.getSuccessTime();
            return startTime;
        } else {
            Map map = new HashMap();
            map.put("borrowId", borrowId);
            map.put("order", order);
            bRepaymentRecordVo = bRepaymentRecordMapper.selectPrevRepayMent(map);
            startTime = bRepaymentRecordVo.getRepaymentTime();
            return startTime;
        }
    }


    /**
     * 计算提前还款天数
     *
     * @return
     */
    public int earlyday(Integer repaymentId) throws Exception {
        BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordMapper.queryBRepaymentRecordById(repaymentId);
        Date repaymentTimeDate = new Date(Long.parseLong(bRepaymentRecordVo.getRepaymentTime()) * 1000);
        repaymentTimeDate = DateUtils.parse(DateUtils.format(repaymentTimeDate, DateUtils.YMD_DASH), DateUtils.YMD_DASH);
        Date now = DateUtils.parse(DateUtils.format(new Date(), DateUtils.YMD_DASH), DateUtils.YMD_DASH);
        int lateDays = DateUtils.dayDiff(repaymentTimeDate, now);
        return lateDays - 2;
    }


    /**
     * 计算逾期天数
     *
     * @return
     */
    public int lateday(Integer repaymentId) throws Exception {
        BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordMapper.queryBRepaymentRecordById(repaymentId);
        Date repaymentTimeDate = new Date(Long.parseLong(bRepaymentRecordVo.getRepaymentTime()) * 1000);
        repaymentTimeDate = DateUtils.parse(DateUtils.format(repaymentTimeDate, DateUtils.YMD_DASH), DateUtils.YMD_DASH);
        Date now = DateUtils.parse(DateUtils.format(new Date(), DateUtils.YMD_DASH), DateUtils.YMD_DASH);
        int lateDays = DateUtils.dayDiff(now, repaymentTimeDate);
        return lateDays;
    }

    /**
     * 计算提前还款利息计算
     *
     * @return
     */
    public BigDecimal earlyInterest(boolean f, BigDecimal collectionInterest,
                                    String repayment_repaymenttime, String compare_repaymenttime,
                                    int earlyday) throws Exception {

        BigDecimal early_interest = BigDecimal.ZERO;
        if (f) {
            early_interest = collectionInterest;
            return early_interest;
        }

        //~~~~~~计算每一天的利息:投资者此次总利息除以该借款标真实的天数
        Date repaymentTimeDate = new Date(Long.parseLong(repayment_repaymenttime) * 1000);
        repaymentTimeDate = DateUtils.parse(DateUtils.format(repaymentTimeDate, DateUtils.YMD_DASH), DateUtils.YMD_DASH);

        Date compareTimeDate = new Date(Long.parseLong(compare_repaymenttime) * 1000);
        compareTimeDate = DateUtils.parse(DateUtils.format(compareTimeDate, DateUtils.YMD_DASH), DateUtils.YMD_DASH);

        int lateDays = DateUtils.dayDiff(repaymentTimeDate, compareTimeDate);
        BigDecimal everyday_interest = collectionInterest.divide(new BigDecimal(lateDays), 8, BigDecimal.ROUND_DOWN);

        //提前还款利息
        early_interest = everyday_interest.multiply(new BigDecimal(earlyday)).setScale(2, BigDecimal.ROUND_HALF_UP);
        return early_interest;
    }

    @Transactional(propagation= Propagation.NOT_SUPPORTED)
    public void sendMail(Integer typeId,Integer type,Integer status,String remark) throws Exception{
        MailSendRecord mailSendRecord=new MailSendRecord();
        mailSendRecord.setTypeId(typeId);
        mailSendRecord.setType(20);//对账复审，借款标异常
        mailSendRecord.setStatus(0);//0：未发送
        mailSendRecord.setAddtime(new Date());
        mailSendRecord.setRemark(remark);
        baseMailSendRecordMapper.insertEntity(mailSendRecord);

    }


    public void updateStatusLastOrder(BRepaymentRecordVo bRepaymentRecordVo,Borrow borrow) throws Exception{

        //~~~~~~~~~~更新相关表状态~~~~~~~~~~
        Integer dfwh = bRepaymentRecordMapper.selectDFWHCount(borrow.getId());

        //判断是否为最后一期
        if (borrow.getOrder() == bRepaymentRecordVo.getOrder()) {

            //更新投标记录状态为2：所投标完成结束
            Map tendStatusMap = new HashMap();
            tendStatusMap.put("status", 2);
            tendStatusMap.put("borrowId", borrow.getId());
            cgRepayMentMapper.updateTenderrecordStatus(tendStatusMap);

            //更新借款标状态
            int borrowStatus = dfwh.intValue() > 0 ? 42 : 5;
            Map borrowStatusMap = new HashMap();
            borrowStatusMap.put("status", borrowStatus);
            borrowStatusMap.put("borrowId", borrow.getId());
            cgRepayMentMapper.updateBorrowStatus(borrowStatusMap);

        } else {

            //更新借款标状态
            int borrowStatus = dfwh.intValue() > 0 ? 42 : 4;
            Map borrowStatusMap2 = new HashMap();
            borrowStatusMap2.put("status", borrowStatus);
            borrowStatusMap2.put("borrowId", borrow.getId());
            cgRepayMentMapper.updateBorrowStatus(borrowStatusMap2);
        }
    }
}
