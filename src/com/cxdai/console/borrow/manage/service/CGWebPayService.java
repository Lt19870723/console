package com.cxdai.console.borrow.manage.service;

import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.borrow.approve.entity.TenderRecord;
import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.vo.CollectionRepayInfoVo;
import com.cxdai.console.borrow.approve.vo.CollectionStatisticCnd;
import com.cxdai.console.borrow.autotender.service.CollectionRecordService;
import com.cxdai.console.borrow.emerg.mapper.TenderRecordMapper;
import com.cxdai.console.borrow.manage.mapper.CGRepayMentMapper;
import com.cxdai.console.borrow.manage.mapper.EFundRepayMentMapper;
import com.cxdai.console.borrow.manage.mapper.RepaymentCGRecordMapper;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.borrow.manage.vo.EFundRepayMent;
import com.cxdai.console.borrow.manage.vo.EFundRepayMentCnd;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.fix.mapper.BCollectionrecordMapper;
import com.cxdai.console.fix.vo.BCollectionRecordCnd;
import com.cxdai.console.fix.vo.BCollectionRecordVo;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.statistics.account.entity.Account;
import com.cxdai.console.statistics.account.mapper.AccountMapper;
import com.cxdai.console.statistics.account.service.AccountLogService;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.serialnumberutil.UUIDGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/22.
 * 平台垫付
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CGWebPayService {


    private Logger logger=Logger.getLogger(CGWebPayService.class);

    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private BRepaymentRecordService bRepaymentRecordService;
    @Autowired
    private CollectionRecordService collectionRecordService;
    @Autowired
    private BorrowManageService borrowManageService;
    @Autowired
    private BCollectionrecordMapper bCollectionrecordMapper;
    @Autowired
    private CGRepayMentService cgRepayMentService;
    @Autowired
    private CGRepayMentMapper cgRepayMentMapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountLogService accountLogService;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private RepaymentCGRecordMapper repaymentCGRecordMapper;
    @Autowired
    private TenderRecordMapper tenderRecordMapper;
    @Autowired
    private EFundRepayMentMapper eFundRepayMentMapper;
    @Autowired
    private EFundRepayMentService eFundRepayMentService;


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
    public String saveWebpayBorrow(ShiroUser shiroUser, Integer repaymentId, String addip) throws Exception {


        //~~~~~~~~~~~垫付时候合法性校验 开始~~~~~~~~~~~~~~~
        BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordService.queryRepaymentByIdForUpdate(repaymentId);
        if (null == bRepaymentRecordVo) {
            return "待还记录不存在";
        }
        if (!(bRepaymentRecordVo.getStatus() == Constants.REPAYMENTRECORD_STATUS_NO_PAY &&
                bRepaymentRecordVo.getWebstatus() == Constants.REPAYMENTRECORD_WEBSTATUS_NO_PAY)) {
            return "待还记录状态已变更,请确认";
        }

        //存管还款
        if(bRepaymentRecordVo.getIsCustody() != null && bRepaymentRecordVo.getIsCustody().intValue() !=1 ){
            return "非存管标不支持存款垫付功能";
        }

        String now = DateUtils.date2TimeStamp(DateUtils.format(new Date(), DateUtils.YMD_DASH)); // 当天的时间戳
        String rpaymentTime = DateUtils.date2TimeStamp(DateUtils.TimeStamp2Date(bRepaymentRecordVo.getRepaymentTime())); // 当天的时间戳
        if (Integer.parseInt(now) < Integer.parseInt(rpaymentTime)) {
            return "不能提前执行垫付！";
        }
        if (bRepaymentRecordService.selectAdvaceRepair(bRepaymentRecordVo.getOrder(), bRepaymentRecordVo.getBorrowId()) > 0) {
            return "您前面还有未还款的期数，不能跨期垫付！";
        }

        CollectionStatisticCnd collectionStatisticCnd = new CollectionStatisticCnd();
        collectionStatisticCnd.setRepaymentId(bRepaymentRecordVo.getId());
        collectionStatisticCnd.setBorrowId(bRepaymentRecordVo.getBorrowId());
        collectionStatisticCnd.setLockedRecordYn(BusinessConstants.LOCKED_RECORD_YES);
        collectionStatisticCnd.setStatus(Constants.COLLECTION_RECORD_STATUS_UNPAY);
        CollectionRepayInfoVo collectionRepayInfoVo = collectionRecordService.queryRepayTotalByCnd(collectionStatisticCnd);
        BigDecimal repayAccount =  bRepaymentRecordVo.getRepaymentAccount();


        if(collectionRepayInfoVo.getRepayAccountTotal().compareTo(repayAccount) != 0){
            return "还款总额与待收总额不一致，不能提前结清";
        }

        //~~~~~~~~~~~垫付时候合法性校验 END~~~~~~~~~~~~~~~

        // 借款标
        Borrow borrow = borrowMapper.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());

        int borrow_borrowtype = borrow.getBorrowtype().intValue(); //借款标类型
        Integer borrowId =borrow.getId();
        String  eProjectId= borrow.geteProjectId();

        int lateday = cgRepayMentService.lateday(repaymentId); //逾期天数
        BigDecimal ratio1=new BigDecimal(0.001);
        BigDecimal ratio2=new BigDecimal(0.002);
        BigDecimal repayment_advanceYesaccount = BigDecimal.ZERO;

                //获取当期的待收记录并锁定
        BCollectionRecordCnd bCollectionRecordCnd=new BCollectionRecordCnd();
        bCollectionRecordCnd.setOrder(bRepaymentRecordVo.getOrder());
        bCollectionRecordCnd.setBorrowId(bRepaymentRecordVo.getBorrowId());
        List<BCollectionRecordVo> collectionList =  bCollectionrecordMapper.queryCollectionForRepay(bCollectionRecordCnd);

        //~~~~~~~~~~~垫付更新平台记录状态 START~~~~~~~~~~~~~~~
        //根据待收生成本息还款数据
        logger.info("===垫付更新平台相关记录状态===，待收记录条数："+collectionList.size());
        for(BCollectionRecordVo collection: collectionList ){
            BigDecimal late_interest =BigDecimal.ZERO; //逾期利息
            BigDecimal collection_interest =BigDecimal.ZERO; //预计收款利息
            BigDecimal collection_capital =BigDecimal.ZERO; //预计收款本金
            BigDecimal collection_repayaccount =BigDecimal.ZERO; //预计收款金额
            BigDecimal collection_advanceYesaccount=BigDecimal.ZERO;

            Integer collectionId= collection.getId();

            collection_interest=collection.getInterest();
            collection_capital = collection.getCapital();
            collection_repayaccount=collection.getRepayAccount();
            Integer tenderId = collection.getTenderId();

            // 更新投标记录
            Map tendMap =new HashMap();
            tendMap.put("tenderrecordId",tenderId);
            tendMap.put("repayment_money",collection_repayaccount);
            tendMap.put("collection_interest",collection_interest);
            cgRepayMentMapper.updateTenderrecord(tendMap);
            //cgRepayMentMapper.updateTenderrecordStatusById(tendMap);
            /**净值标**/
            if(borrow_borrowtype == 3){
                late_interest = collection_repayaccount.multiply(ratio2)
                        .multiply(new BigDecimal(lateday))
                        .setScale(2,BigDecimal.ROUND_HALF_UP);

            }else{
                late_interest = collection_repayaccount.multiply(ratio1)
                        .multiply(new BigDecimal(lateday))
                        .setScale(2,BigDecimal.ROUND_HALF_UP);
            }

            collection_advanceYesaccount=collection_interest.add(collection_capital);
            repayment_advanceYesaccount=repayment_advanceYesaccount.add(collection_advanceYesaccount);

            //更新平台垫付待收记录
            Map collectionMap =new HashMap();
            collectionMap.put("repay_yesaccount",collection_advanceYesaccount);
            collectionMap.put("status",5);
            collectionMap.put("advance_yesaccount",collection_advanceYesaccount);
            collectionMap.put("lateday",lateday);
            collectionMap.put("late_interest",late_interest);
            collectionMap.put("collection_id",collectionId);
            cgRepayMentMapper.updateCollectionForWebpay(collectionMap);

            //更新账户 --垫付入账
//            AccountVo investAccountVo = accountService.queryAccountByUserIdForUpdate(collection.getUserId());
//            investAccountVo.seteUseMoney(investAccountVo.geteUseMoney().add(collection_repayaccount));
//            investAccountVo.seteCollection(investAccountVo.geteCollection().subtract(collection_repayaccount));
//
//            Account investAccount = new Account();
//            BeanUtils.copyProperties(investAccountVo, investAccount);
//            accountMapper.updateEntity(investAccount);
//
//            String investAccountRemark="存管垫付还款入账";
//            String investType="cg_webpay_repayment_back";
//            accountLogService.saveEAccountLogByParams(investAccountVo,collection.getUserId(),collection_repayaccount,investAccountRemark,
//                    "0.0.0.1",investType,null,null,null);
        }

        //更新待还记录表-银行垫付中
        BigDecimal late_repay_interest = bRepaymentRecordVo.getRepaymentAccount()
                                        .multiply(ratio2)
                                        .multiply(new BigDecimal(lateday))
                                        .setScale(2,BigDecimal.ROUND_HALF_UP);
        Map repayMentMap =new HashMap();
        repayMentMap.put("webStatus",5);
        repayMentMap.put("advance_yesaccount",bRepaymentRecordVo.getRepaymentAccount());
        repayMentMap.put("lateday",lateday);
        repayMentMap.put("late_interest",late_repay_interest);
        repayMentMap.put("repaymentId",bRepaymentRecordVo.getId());
        repayMentMap.put("advanceTime",new Date());
        cgRepayMentMapper.updateRepayMentForWebpay(repayMentMap);

        //更新标的状态为已垫付
        Map borrowMap =new HashMap();
        borrowMap.put("status",42);
        borrowMap.put("borrowId",borrowId);
        cgRepayMentMapper.updateBorrowStatus(borrowMap);

        //~~~~~~~~~~~垫付更新平台记录状态 END~~~~~~~~~~~~~~~

        //~~~~~~~~~~~开始生成垫付还款上报数据 START~~~~~~~~~~~~~~~
        logger.info("===开始生成正常本息还款上报数据===，待收记录条数："+collectionList.size());
        for(BCollectionRecordVo collection: collectionList ){
            TenderRecord tenderVo = tenderRecordMapper.selectById(collection.getTenderId());

            //先判断本息还款表是否存在该还款记录，如果存在则不重复插入数据
            EFundRepayMentCnd efundCnd =new EFundRepayMentCnd();
            efundCnd.setCollectionId(collection.getId());
            efundCnd.setReapaymentId(bRepaymentRecordVo.getId());
            efundCnd.setType(Constants.REPAYMENT_TYPE_NORMAL);
            int efundCount = eFundRepayMentMapper.queryRepayMentCount(efundCnd);
            if(efundCount>0){continue;}
            //生成本息还款数据
            EFundRepayMent eFundRepayMent=new EFundRepayMent();

            eFundRepayMent.setType(Constants.REPAYMENT_TYPE_WEBPAY);
            eFundRepayMent.setBorrowId(borrowId);
            eFundRepayMent.seteProjectId(eProjectId);
            eFundRepayMent.setReapaymentId(bRepaymentRecordVo.getId());
            eFundRepayMent.setCollectionId(collection.getId());
            eFundRepayMent.setOrder(collection.getOrder());
            eFundRepayMent.setRepayName(borrow.getRepayName());
            eFundRepayMent.setInnerFlag(1);
            eFundRepayMent.setBizNo(UUIDGenerator.generate(Constants.CUSTODY_SERIAL_FRR3));//-----------
            eFundRepayMent.seteInvestNo(tenderVo.geteInvestNo());//-----------
            eFundRepayMent.setReturnType(0);
            eFundRepayMent.setRepaymentAmount(collection.getRepayAccount().multiply(new BigDecimal(100)).intValue());
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
        }

        //~~~~~~~~~~~开始生成垫付还款上报数据 END~~~~~~~~~~~~~~~
        // int a=1000/0;

        return BusinessConstants.SUCCESS;
    }

    /**
     * 银行垫付后确认
     * @param shiroUser
     * @param repaymentId
     */
    public String saveWebpaywebpayConfirm(ShiroUser shiroUser,Integer repaymentId,String addip ) throws Exception {

        //~~~~~~~~~~~垫付时候合法性校验 开始~~~~~~~~~~~~~~~
        BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordService.queryRepaymentByIdForUpdate(repaymentId);
        if (null == bRepaymentRecordVo) {
            return "待还记录不存在";
        }
        if (!(bRepaymentRecordVo.getStatus() == Constants.REPAYMENTRECORD_STATUS_NO_PAY &&
                bRepaymentRecordVo.getWebstatus() == Constants.REPAYMENTRECORD_WEBSTATUS_BANK_PAY)) {
            return "待还记录状态已变更,请确认";
        }
        //存管还款
        if(bRepaymentRecordVo.getIsCustody() != null && bRepaymentRecordVo.getIsCustody().intValue() !=1 ){
            return "非存管标不支持存款垫付功能";
        }

        //1:更新待还记录状态
        List<EFundRepayMent> efundList = eFundRepayMentService.selectByRepayId(repaymentId, bRepaymentRecordVo.getBorrowId(),3);
        for (EFundRepayMent efend: efundList ) {
            //更新垫付还款数据上送表
            efend.setIsSend(1);//上送成功
            eFundRepayMentService.updateByPrimaryKeySelective(efend);
            //更新待收表状态
            cgRepayMentMapper.updateCollectionStatusByID(2,efend.getCollectionId());

            BCollectionRecordVo collection = bCollectionrecordMapper.selectByPrimaryKey(efend.getCollectionId());
            // 更新账户 --垫付入账
            AccountVo investAccountVo = accountService.queryAccountByUserIdForUpdate(collection.getUserId());
            investAccountVo.seteUseMoney(investAccountVo.geteUseMoney().add(collection.getRepayAccount()));
            investAccountVo.seteCollection(investAccountVo.geteCollection().subtract(collection.getRepayAccount()));

            Account investAccount = new Account();
            BeanUtils.copyProperties(investAccountVo, investAccount);
            accountMapper.updateEntity(investAccount);

            String investAccountRemark="存管垫付还款入账";
            String investType="cg_webpay_repayment_back";
            accountLogService.saveEAccountLogByParams(investAccountVo,collection.getUserId(),collection.getRepayAccount(),investAccountRemark,
                    "0.0.0.1",investType,null,null,null);
        }

        //2:批量更新待收记录状态
        Map repayMentMap =new HashMap();
        repayMentMap.put("webStatus",1);
        repayMentMap.put("repaymentId",bRepaymentRecordVo.getId());
        cgRepayMentMapper.updateRepayMentForWebpay(repayMentMap);

        return BusinessConstants.SUCCESS;

    }




    /**
     *
     * <p>
     * Description:分页查询还款中的借款标集合<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年6月28日
     * @param borrowCnd
     * @param curPage
     * @param pageSize
     * @return
     * @throws Exception
     * Page
     */
    public Page selectRepayingBorrow(BorrowCnd borrowCnd, Integer curPage, Integer pageSize) throws Exception {
        Page page = new Page(curPage, pageSize);


        if (borrowCnd.getRepaymentTimeBeginStr() != null && !"".equals(borrowCnd.getRepaymentTimeBeginStr())) {
            borrowCnd.setRepaymentTimeBeginStr(DateUtils.dateTime2TimeStamp( borrowCnd.getRepaymentTimeBeginStr() + " 00:00:00"));
        } else {
            borrowCnd.setRepaymentTimeBeginStr(null);
        }
        if (borrowCnd.getRepaymentTimeEndStr() != null && !"".equals(borrowCnd.getRepaymentTimeEndStr())) {
            borrowCnd.setRepaymentTimeEndStr(DateUtils.dateTime2TimeStamp(borrowCnd.getRepaymentTimeEndStr() + " 23:59:59"));
        } else {
            borrowCnd.setRepaymentTimeEndStr(null);
        }
        String statusStr = borrowCnd.getStatusStr();
        if(statusStr != null && !statusStr.equals("")){
            borrowCnd.setStatus(statusStr);
            if(statusStr.equals("0")){
                borrowCnd.setRepaymentStatus("1");
            }
            if(statusStr.equals("1")){
                borrowCnd.setRepaymentStatus("2");
            }

        }
        int totalCount = repaymentCGRecordMapper.selectRepayingBorrowCount(borrowCnd);
        page.setTotalCount(totalCount);
        List<BRepaymentRecordVo> list = repaymentCGRecordMapper.selectRepayingBorrow(borrowCnd, page);
        page.setResult(list);
        return page;
    }

    public BigDecimal sumWaitRepayMoney(BorrowCnd borrowCnd) {
        return repaymentCGRecordMapper.sumWaitRepayMoney(borrowCnd);
    }
}


