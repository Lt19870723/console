package com.cxdai.console.borrow.manage.service;

/**
 * Created by Administrator on 2016/6/15.
 */

import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.borrow.approve.entity.TenderRecord;
import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.emerg.mapper.TenderRecordMapper;
import com.cxdai.console.borrow.manage.mapper.BRepaymentRecordMapper;
import com.cxdai.console.borrow.manage.mapper.CGRepayMentMapper;
import com.cxdai.console.borrow.manage.mapper.EFundRepayMentMapper;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.borrow.manage.vo.EFundRepayMent;
import com.cxdai.console.borrow.manage.vo.EFundRepayMentCnd;
import com.cxdai.console.borrow.manage.vo.OrderRangeVo;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.fix.mapper.BCollectionrecordMapper;
import com.cxdai.console.fix.vo.BCollectionAmountVo;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提前结清
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CGRepaySettlementService {
    private Logger logger=Logger.getLogger(CGRepaySettlementService.class);

    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private BRepaymentRecordService bRepaymentRecordService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BCollectionrecordMapper bCollectionrecordMapper;
    @Autowired
    private BRepaymentRecordMapper bRepaymentRecordMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountLogService accountLogService;
    @Autowired
    private CGRepayMentMapper cgRepayMentMapper;
    @Autowired
    private TenderRecordMapper tenderRecordMapper;
    @Autowired
    private EFundRepayMentMapper eFundRepayMentMapper;


    /*
    * 存管提前结清
    */
    @Transactional(rollbackFor = Throwable.class)
    public String saveCustodyRepayBorrowbackup(Integer repaymentid, Integer userId) throws Exception {
        String result = BusinessConstants.SUCCESS;
        BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordService.queryRepaymentByIdForUpdate(repaymentid);

        // 验证还款的数据正确性
        result = validateRepayBorrowData(userId, bRepaymentRecordVo);
        if (!result.equals(BusinessConstants.SUCCESS)) {
            return result;
        }
        // 验证是否能进行提前结清处理
        result = checkRepayType(bRepaymentRecordVo);
        if (!result.equals(BusinessConstants.SUCCESS)) {
            return result;
        }

        Integer borrowId=bRepaymentRecordVo.getBorrowId();
        BorrowVo borrowVo =  borrowMapper.selectByPrimaryKey(borrowId);
        OrderRangeVo orderRange =  bRepaymentRecordService.getCurrentOrder(borrowId);

        if(orderRange == null ){
            return "借款标已逾期，无法提前结清";
        }


        orderRange = setOrderRangeVo(orderRange,bRepaymentRecordVo);

        logger.info(orderRange);
        BigDecimal deducttotal_interest_total= BigDecimal.ZERO; // 所有投资者累计应扣利息总额
        BigDecimal collection_current_interest_total= BigDecimal.ZERO;  // 所有投资者当期累计应收利息总额
        Borrow borrow = borrowMapper.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());

        //~~~~~~~~~~~~~~~~~~~投资人提前结清回款业务~~~~~~~~~~~~~~~~~~~
        // 1:查询当期尚未还款的待收记录
        List<BCollectionRecordVo> collectionList =
                bCollectionrecordMapper.queryCurrentCollectionRecord(borrowId,orderRange.getOrder());
        for (BCollectionRecordVo collection: collectionList) {
            BigDecimal deduct_early_interest=BigDecimal.ZERO;//投资者要扣除的提前还款利息
            BigDecimal collection_current_interest=BigDecimal.ZERO;//投资者当期应收利息
            BigDecimal collection_next_interest_total= BigDecimal.ZERO;//投资者当期之后应收利息总额
            BigDecimal collectiontotal_account= BigDecimal.ZERO;//投资者累计待收总额额
            BigDecimal collectiontotal_interest= BigDecimal.ZERO;//投资者累计待收利息总额
            BigDecimal collection_deduct_interest_total= BigDecimal.ZERO; //投资者提前结清倒扣利息总额
            BigDecimal collection_interest= collection.getInterest();

            /* ------计算需要扣除的利息 和 当期待收利息 开始------*/
            if(orderRange.getDiffStartDay() > 0){
                deduct_early_interest=collection_interest;
                collection_current_interest=BigDecimal.ZERO;
            }else{
                int earlyday=0;// 当期提前还款天数
                if(orderRange.getDiffEndDay() > 3){
                    earlyday=orderRange.getDiffEndDay()-2;
                }

                //-- 计算该用户该笔投标当期每一天的利息
                BigDecimal everyday_interest = collection_interest.divide(new BigDecimal(orderRange.getInterval())).setScale(
                        8,BigDecimal.ROUND_HALF_DOWN);

                //-- 当期该投资者该笔投标需扣除的利息
                deduct_early_interest = everyday_interest.multiply(new BigDecimal(earlyday)).setScale(2,BigDecimal.ROUND_HALF_UP);
                //-- 该投资者该笔投标当期应收利息总额
                collection_current_interest = collection_interest.subtract(deduct_early_interest);
            }
            /* ------计算需要扣除的利息 和 当期待收利息 结束------*/

            // 投资者该笔投标对应的当期之后应收利息总额
            Map cnitMap =new HashMap();
            cnitMap.put("order",orderRange.getOrder());
            cnitMap.put("userId",collection.getUserId());
            cnitMap.put("borrowId",collection.getBorrowId());
            cnitMap.put("tendId",collection.getTenderId());
            cnitMap.put("lastOrder",orderRange.getLastOrder());
            collection_next_interest_total = bCollectionrecordMapper.queryAmountAfterCurrent(cnitMap);

            //该投资者该笔投标累计待收总额，累计待收本金总额，累计待收利息总额
            BCollectionAmountVo  collectionAmount = bCollectionrecordMapper.queryAmountAll(cnitMap);

            // 该投资者该笔投标提前结清倒扣利息总额 = 该笔投标当期应扣利息 + 该笔投标当期之后应收利息总额
            collection_deduct_interest_total=deduct_early_interest.add(collection_next_interest_total);
            // 所有投资者累计倒扣利息总额
            deducttotal_interest_total=deducttotal_interest_total.add(collection_deduct_interest_total);
            // 所有投资者当期累计应收利息总额
            collection_current_interest_total=collection_current_interest_total.add(collection_current_interest);



            TenderRecord tenderVo = tenderRecordMapper.selectById(collection.getTenderId());

            //先判断本息还款表是否存在该还款记录，如果存在则不重复插入数据
            EFundRepayMentCnd efundCnd =new EFundRepayMentCnd();
            efundCnd.setCollectionId(collection.getId());
            efundCnd.setReapaymentId(bRepaymentRecordVo.getId());
            efundCnd.setType(Constants.REPAYMENT_TYPE_EARLY);
            int efundCount = eFundRepayMentMapper.queryRepayMentCount(efundCnd);
            if(efundCount>0){continue;}

            //生成本息还款数据
            EFundRepayMent eFundRepayMent=new EFundRepayMent();

            eFundRepayMent.setType(Constants.REPAYMENT_TYPE_EARLY);
            eFundRepayMent.setBorrowId(borrowVo.getId());
            eFundRepayMent.seteProjectId(borrowVo.geteProjectId());
            eFundRepayMent.setReapaymentId(bRepaymentRecordVo.getId());
            eFundRepayMent.setCollectionId(collection.getId());
            eFundRepayMent.setOrder(collection.getOrder());
            eFundRepayMent.setRepayName(borrowVo.getRepayName());
            eFundRepayMent.setInnerFlag(1);
            eFundRepayMent.setBizNo(UUIDGenerator.generate(Constants.CUSTODY_SERIAL_FRR2));//-----------
            eFundRepayMent.seteInvestNo(tenderVo.geteInvestNo());//-----------
            eFundRepayMent.setReturnType(0);
            //eFundRepayMent.setRepaymentAmount(repayMoney.multiply(new BigDecimal(100)).intValue());
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


            // 更新当期的待收记录，状态已还款，实收金额 = 当前应收利息 + 当期待收本金，实收时间 = 当前时间
            Map cMap =new HashMap();
            cMap.put("id",collection.getId());
            cMap.put("repayment_money",collection_current_interest.add(collection.getCapital()));
            bCollectionrecordMapper.updateCollectionById(cMap);


        }


        return BusinessConstants.SUCCESS;
    }
    /**
     * 生成存管提前结清上报数据
     * @param repaymentid
     * @param addip
     * @param userId
     * @param staff
     * @param borrowUserName
     * @return
     * @throws Exception
     */

    public void repayEarlySettlement(Integer repaymentid, String addip, Integer userId, ShiroUser staff, String borrowUserName) throws Exception {



    }



    /**
     * <p>
     * Description:验证提前结清数据的正确性<br />
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
        Borrow borrow = borrowMapper.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());
        Integer borrowId= borrow.getId();
        Integer borrowStatus =borrow.getStatus();

        Integer webpay =  bRepaymentRecordService.queryWebpayCount(borrowId);
//        if (webpay>0){
//            return "存在垫付记录，不能提前结清";
//        }
        if (borrowStatus.intValue() != 4 ){
            return "借款标状态不为还款中，不能提前结清";
        }

        BigDecimal collectionTotal=  bCollectionrecordMapper.queryCollectionTotalByBorrowId(borrowId);
        BigDecimal repayTotal =  bRepaymentRecordService.queryRepayTotalByBorrowId(borrowId);

//        if(collectionTotal.compareTo(repayTotal) != 0){
//            return "还款总额与待收总额不一致，不能提前结清";
//        }

        AccountVo accoutVo = accountService.queryAccountByUserIdForUpdate(userId);
        if(repayTotal.compareTo(accoutVo.geteUseMoney()) == 1 ){
            return "借款者账户金额不足，无法提前结清";
        }
        return result;
    }

    /**
     * 如果当前期数的应还款时间 = 当前时间，则将下一期作为当前期数
     * @param orderRange
     * @param bRepaymentRecordVo
     * @return
     */

    public OrderRangeVo setOrderRangeVo(OrderRangeVo orderRange, BRepaymentRecordVo bRepaymentRecordVo){

        OrderRangeVo resultOrder =new OrderRangeVo();

        Map map =new HashMap();
        map.put("borrowId",bRepaymentRecordVo.getBorrowId());
        map.put("order",resultOrder.getOrder());
        BRepaymentRecordVo  repaymentRecordVo=null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String now = sdf.format(new Date());
        String etime = sdf.format(orderRange.getEndTime());
        if(now.equals(etime)){
            try {
                repaymentRecordVo = bRepaymentRecordMapper.selectNextRepayMent(map);
                if(repaymentRecordVo != null){
                    resultOrder.setLastOrder(orderRange.getOrder());
                    resultOrder.setStartTime(orderRange.getEndTime());
                    resultOrder.setOrder(repaymentRecordVo.getOrder());
                    resultOrder.setEndTime(repaymentRecordVo.getRepaymentTimeDate());
                    return resultOrder;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return orderRange;
    }

    /**
     * 判断否能进行提前结清处理
     * @param bRepaymentRecordVo
     */
    public String checkRepayType(BRepaymentRecordVo bRepaymentRecordVo){

        // 还款时间和当前时间相差的天数
        Date repaymentTimeDate = new Date(Long.parseLong(bRepaymentRecordVo.getRepaymentTime()) * 1000);
        repaymentTimeDate = DateUtils.parse(DateUtils.format(repaymentTimeDate, DateUtils.YMD_DASH), DateUtils.YMD_DASH);
        Date now = DateUtils.parse(DateUtils.format(new Date(), DateUtils.YMD_DASH), DateUtils.YMD_DASH);

        // 提前天数
        int earlyDays = DateUtils.dayDiff(repaymentTimeDate, now);
        if(earlyDays <= 3){
            return "该笔待还不符合提前结清要求";
        }
        Borrow borrow = null;
        try {
            borrow = borrowMapper.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 只有抵押标到期还本付息和按月付息到期还本两种类型，才启用提前还款，比应还款日提前3天以上（不包含3天）
        // 只有信用标等额本息类型，才启用提前还款，比应还款日提前3天以上（不包含3天）
//        if (((borrow.getBorrowtype() == Constants.BORROW_TYPE_PLEDGE && (borrow.getStyle() == Constants.BORROW_STYLE_DUE_PAY_ALL || borrow.getStyle() == Constants.BORROW_STYLE_MONTH_PAY_INTEREST)) || (borrow
//                .getBorrowtype() == Constants.BORROW_TYPE_RECOMMEND && borrow.getStyle() == Constants.BORROW_STYLE_MONTH_INSTALMENTS)) && earlyDays > 3) {
//            return  BusinessConstants.SUCCESS;
//        } else {
//            return "该笔待还不符合提前结清要求";
//        }
        return  BusinessConstants.SUCCESS;
    }



    /*
* 存管提前结清
*/
    @Transactional(rollbackFor = Throwable.class)
    public String saveCustodyRepayBorrow(Integer repaymentid, Integer userId) throws Exception {
        String result = BusinessConstants.SUCCESS;
        BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordService.queryRepaymentByIdForUpdate(repaymentid);

        // 验证还款的数据正确性
        result = validateRepayBorrowData(userId, bRepaymentRecordVo);
        if (!result.equals(BusinessConstants.SUCCESS)) {
            return result;
        }
        // 验证是否能进行提前结清处理
        result = checkRepayType(bRepaymentRecordVo);
        if (!result.equals(BusinessConstants.SUCCESS)) {
            return result;
        }

        Integer borrowId=bRepaymentRecordVo.getBorrowId();
        OrderRangeVo orderRange =  bRepaymentRecordService.getCurrentOrder(borrowId);

        if(orderRange == null ){
            return "借款标已逾期，无法提前结清";
        }


        orderRange = setOrderRangeVo(orderRange,bRepaymentRecordVo);

        logger.info(orderRange);
        BigDecimal deducttotal_interest_total= BigDecimal.ZERO; // 所有投资者累计应扣利息总额
        BigDecimal collection_current_interest_total= BigDecimal.ZERO;  // 所有投资者当期累计应收利息总额
        Borrow borrow = borrowMapper.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());

        //~~~~~~~~~~~~~~~~~~~投资人提前结清回款业务~~~~~~~~~~~~~~~~~~~
        // 1:查询当期尚未还款的待收记录
        List<BCollectionRecordVo> collectionList =
                bCollectionrecordMapper.queryCurrentCollectionRecord(borrowId,orderRange.getOrder());
        for (BCollectionRecordVo collection: collectionList) {
            BigDecimal deduct_early_interest=BigDecimal.ZERO;//投资者要扣除的提前还款利息
            BigDecimal collection_current_interest=BigDecimal.ZERO;//投资者当期应收利息
            BigDecimal collection_next_interest_total= BigDecimal.ZERO;//投资者当期之后应收利息总额
            BigDecimal collectiontotal_account= BigDecimal.ZERO;//投资者累计待收总额额
            BigDecimal collectiontotal_interest= BigDecimal.ZERO;//投资者累计待收利息总额
            BigDecimal collection_deduct_interest_total= BigDecimal.ZERO; //投资者提前结清倒扣利息总额
            BigDecimal collection_interest= collection.getInterest();

            /* ------计算需要扣除的利息 和 当期待收利息 开始------*/
            if(orderRange.getDiffStartDay() > 0){
                deduct_early_interest=collection_interest;
                collection_current_interest=BigDecimal.ZERO;
            }else{
                int earlyday=0;// 当期提前还款天数
                if(orderRange.getDiffEndDay() > 3){
                    earlyday=orderRange.getDiffEndDay()-2;
                }

                //-- 计算该用户该笔投标当期每一天的利息
                BigDecimal everyday_interest = collection_interest.divide(new BigDecimal(orderRange.getInterval())).setScale(
                        8,BigDecimal.ROUND_HALF_DOWN);

                //-- 当期该投资者该笔投标需扣除的利息
                deduct_early_interest = everyday_interest.multiply(new BigDecimal(earlyday)).setScale(2,BigDecimal.ROUND_HALF_UP);
                //-- 该投资者该笔投标当期应收利息总额
                collection_current_interest = collection_interest.subtract(deduct_early_interest);
            }
            /* ------计算需要扣除的利息 和 当期待收利息 结束------*/

            // 投资者该笔投标对应的当期之后应收利息总额
            Map cnitMap =new HashMap();
            cnitMap.put("order",orderRange.getOrder());
            cnitMap.put("userId",collection.getUserId());
            cnitMap.put("borrowId",collection.getBorrowId());
            cnitMap.put("tendId",collection.getTenderId());
            cnitMap.put("lastOrder",orderRange.getLastOrder());
            collection_next_interest_total = bCollectionrecordMapper.queryAmountAfterCurrent(cnitMap);

            //该投资者该笔投标累计待收总额，累计待收本金总额，累计待收利息总额
            BCollectionAmountVo  collectionAmount = bCollectionrecordMapper.queryAmountAll(cnitMap);
            collectiontotal_account=collectionAmount.getTotal_repay_account();
            collectiontotal_interest=collectionAmount.getTotal_interest();

            // 该投资者该笔投标提前结清倒扣利息总额 = 该笔投标当期应扣利息 + 该笔投标当期之后应收利息总额
            collection_deduct_interest_total=deduct_early_interest.add(collection_next_interest_total);
            // 所有投资者累计倒扣利息总额
            deducttotal_interest_total=deducttotal_interest_total.add(collection_deduct_interest_total);
            // 所有投资者当期累计应收利息总额
            collection_current_interest_total=collection_current_interest_total.add(collection_current_interest);

            //更新账户提前还款入账 --提前结清，还款入账
            AccountVo investAccountVo = accountService.queryAccountByUserIdForUpdate(collection.getUserId());
            investAccountVo.seteUseMoney(investAccountVo.geteUseMoney().add(collectiontotal_account));
            investAccountVo.seteCollection(investAccountVo.geteCollection().subtract(collectiontotal_account));

            Account investAccount = new Account();
            BeanUtils.copyProperties(investAccountVo, investAccount);
            accountMapper.updateEntity(investAccount);

            String investAccountRemark="存管提前结清，还款入账";
            String investType="cg_settlement_repayment_back";
            accountLogService.saveEAccountLogByParams(investAccountVo,collection.getUserId(),collectiontotal_account,investAccountRemark,
                    "0.0.0.1",investType,null,null,null);

            //更新账户提前还款入账 --扣除提前结清总利息

            if(collection_deduct_interest_total.compareTo(BigDecimal.ZERO) > 0){
                AccountVo interestAccountVo = accountService.queryAccountByUserIdForUpdate(collection.getUserId());
                investAccountVo.setTotal(investAccountVo.getTotal().subtract(collection_deduct_interest_total));
                investAccountVo.seteUseMoney(investAccountVo.geteUseMoney().subtract(collection_deduct_interest_total));

                Account interestAccount = new Account();
                BeanUtils.copyProperties(interestAccountVo, interestAccount);
                accountMapper.updateEntity(interestAccount);

                //新增投资人账户日志
                String interestAccountRemark="存管扣除提前还款利息";
                String interestType="cg_settlement_early_interest";
                accountLogService.saveEAccountLogByParams(interestAccountVo,collection.getUserId(),collection_deduct_interest_total,interestAccountRemark,
                        "0.0.0.1",interestType,null,null,null);
            }

            // 如果上一期期数存在，则更新上一期为本金和利息全额回款

            if(orderRange.getLastOrder() !=null && orderRange.getLastOrder().intValue()>0){
                bCollectionrecordMapper.updateCollectionByMap(cnitMap);
            }

            // 更新当期的待收记录，状态已还款，实收金额 = 当前应收利息 + 当期待收本金，实收时间 = 当前时间
            Map cMap =new HashMap();
            cMap.put("id",collection.getId());
            cMap.put("repayment_money",collection_current_interest.add(collection.getCapital()));
            bCollectionrecordMapper.updateCollectionById(cMap);

            // 更新该笔投标当期之后的待收记录
            bCollectionrecordMapper.updateCollectionAfterorderByMap(cnitMap);

            //  更新投资人该笔投标记录
            Map tendMap =new HashMap();
            tendMap.put("tenderrecordId",collection.getTenderId());
            tendMap.put("repayment_money",collectiontotal_account.subtract(collection_deduct_interest_total));
            tendMap.put("status",2);
            tendMap.put("collection_interest",collectiontotal_interest.subtract(collection_deduct_interest_total));
            cgRepayMentMapper.updateTenderrecord(tendMap);
            cgRepayMentMapper.updateTenderrecordStatusById(tendMap);

        }

        //~~~~~~~~~~~~~~~~~~~借款者提前结清业务开始~~~~~~~~~~~~~~~~~~~

        //借款者扣款-更新借款人账户
        // 累计待还总额
        BigDecimal repayTotal =  cgRepayMentMapper.queryRepayTotalByBorrowId(borrowId);
        Integer repayment_userid= bRepaymentRecordVo.getUserId();
        AccountVo repaymentAccount = accountService.queryAccountByUserIdForUpdate(repayment_userid);

        repaymentAccount.setTotal(repaymentAccount.getTotal().subtract(repayTotal));
        repaymentAccount.seteUseMoney(repaymentAccount.geteUseMoney().subtract(repayTotal));
        Account accountNewRepatMent = new Account();
        BeanUtils.copyProperties(repaymentAccount, accountNewRepatMent);
        accountMapper.updateEntity(accountNewRepatMent);

        String accountNewRepatMentRemark="存管提前结清，还款扣除";
        String accountNewRepatMentype="cg_settlement_repayment_deduct";
        accountLogService.saveEAccountLogByParams(repaymentAccount,repayment_userid,repayTotal,
                accountNewRepatMentRemark, "0.0.0.1",accountNewRepatMentype,null,null,null);

        //-- 如果所有投资者累计应扣利息总额大于0
        if(deducttotal_interest_total.compareTo(BigDecimal.ZERO)>0){
            AccountVo interestTotalAccount = accountService.queryAccountByUserIdForUpdate(repayment_userid);

            interestTotalAccount.setTotal(interestTotalAccount.getTotal().subtract(deducttotal_interest_total));
            interestTotalAccount.seteUseMoney(interestTotalAccount.geteUseMoney().subtract(deducttotal_interest_total));
            Account interestTotalAccountNewRepatMent = new Account();
            BeanUtils.copyProperties(interestTotalAccount, interestTotalAccountNewRepatMent);
            accountMapper.updateEntity(interestTotalAccountNewRepatMent);

            String interestTotalAccountRemark="存管增加提前结清利息";
            String interestTotalAccounttype="cg_settlement_add_interest";
            accountLogService.saveEAccountLogByParams(repaymentAccount,repayment_userid,repayTotal,
                    interestTotalAccountRemark, "0.0.0.1",interestTotalAccounttype,null,null,null);
        }

        //如果上一期期数存在，则更新上一期为本金和利息全额回款
        Map repayMap =new HashMap();
        repayMap.put("borrowId",borrowId);
        repayMap.put("userId",borrow.getUserId());
        repayMap.put("lastOrder",orderRange.getLastOrder());
        repayMap.put("order",orderRange.getOrder());
        if(orderRange.getLastOrder() !=null && orderRange.getLastOrder().intValue()>0){
            cgRepayMentMapper.updatePrevRepaymentByMap(repayMap);
        }
        //更新当期的待还记录
        repayMap.put("collection_current_interest_total",collection_current_interest_total);
        cgRepayMentMapper.updateCurrentRepaymentByMap(repayMap);

        //更新当期之后的待还记录
        cgRepayMentMapper.updateCurrentRepaymentByMap(repayMap);
        //~~~~~~~~~~~~~~~~~~~借款者提前结清业务结束~~~~~~~~~~~~~~~~~~~

        Map borrowStatusMap=new HashMap();
        borrowStatusMap.put("status",5);
        borrowStatusMap.put("borrowId",borrow.getId());
        cgRepayMentMapper.updateBorrowStatus(borrowStatusMap);

        return BusinessConstants.SUCCESS;
    }

}
