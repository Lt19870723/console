package com.cxdai.console.borrow.manage.controller;

import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.service.BorrowService;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.manage.service.*;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.borrow.manage.vo.EFundRepayMent;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.page.PageListModel;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.common.statics.CGBusinessConstants;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.util.*;
import com.cxdai.console.util.serialnumberutil.UUIDGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by Administrator on 2016/6/16.
 * 提前结清
 */
@Controller
@RequestMapping(value = "/borrow/manage/repaySettlement")
public class RepaySettlementController extends BaseController {

    private final static Logger logger = Logger.getLogger(RepaySettlementController.class);
    @Autowired
    private BRepaymentRecordService bRepaymentRecordService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private CGRepaySettlementService cGRepaySettlementService;
    @Autowired
    private EFundRepayMentService eFundRepayMentService;
    @Autowired
    private BorrowMapper borrowMapper;

    private String path = PropertiesUtil.getValue("portal_path");

    /**
     *
     * <p>
     * Description:进入借款提前结清列表<br />
     * </p>
     */
    @RequestMapping("/main")
    public ModelAndView main() {
        return new ModelAndView("/borrow/manage/repaySettlement/main");
    }

    /**
     *
     * <p>
     * Description:分页查询还款中的借款标集合<br />
     * </p>
     */
    @RequestMapping("/list/{pageNo}")
    public ModelAndView selectRepayingBorrow(
            @ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
        BigDecimal useMoney = null, sumRepayAccount = null;
        Page page = null;
        try {
            Integer memberId = memberService.getMemberIdByUserName(borrowCnd.getUserName().trim());
            if (memberId != null) {
                AccountVo accountVo = accountService .queryAccountByUserId(memberId);
                useMoney = accountVo.getUseMoney();
            } else {
                useMoney = null;
            }
            borrowCnd.setIsOfficial("1");
            borrowCnd.setIsCustody("1");
            borrowCnd.setStatus("0");
            page = bRepaymentRecordService.selectRepayingBorrow(borrowCnd,
                    pageNo, Constants.CONSOLE_PAGE_SIZE);

            List<BRepaymentRecordVo> bRepaymentRecordVos = (List<BRepaymentRecordVo>) page
                    .getResult();

            if (!bRepaymentRecordVos.isEmpty()) {
                for (Iterator<BRepaymentRecordVo> iterator = bRepaymentRecordVos
                        .iterator(); iterator.hasNext();) {
                    BRepaymentRecordVo bRepaymentRecordVo = iterator.next();
                    Date repaymentTimeDate = new Date( Long.parseLong(bRepaymentRecordVo.getRepaymentTime()) * 1000);
                    repaymentTimeDate = DateUtils.parseForYMD_DASH(DateUtils.format(repaymentTimeDate, DateUtils.YMD_DASH));
                    Date now = DateUtils.parseForYMD_DASH(DateUtils.format(new Date(), DateUtils.YMD_DASH));
                    // 提前天数
                    int earlyDays = DateUtils.dayDiff(repaymentTimeDate, now);
                    bRepaymentRecordVo.setEaryDay(earlyDays);

                    // 逾期天数
                    int lateDays = DateUtils.dayDiff(now, repaymentTimeDate);
                    // 罚息金额
                    BigDecimal lateDayInterest = BigDecimal.ZERO;
                    if (lateDays > 0) {
                        lateDayInterest = bRepaymentRecordVo
                                .getRepaymentAccount()
                                .multiply(
                                        new BigDecimal(
                                                BusinessConstants.OUT_OF_DAYE_RATE))
                                .multiply(new BigDecimal(lateDays))
                                .setScale(2, RoundingMode.UP);
                    }
                    // 应还总金额 = 还款金额 + 罚息金额
                    BigDecimal totalPayMoney = bRepaymentRecordVo
                            .getRepaymentAccount().setScale(2, RoundingMode.UP)
                            .add(lateDayInterest);
                    // 借款者帐号
                    AccountVo accoutVo = accountService.queryAccountByUserId(bRepaymentRecordVo.getUserId());

                    if(bRepaymentRecordVo.getIsCustody() !=null && bRepaymentRecordVo.getIsCustody().equals("1")){
                        if (totalPayMoney.compareTo(accoutVo.geteUseMoney()) == 1) {
                            bRepaymentRecordVo.setNeedRechargeAccount(totalPayMoney.subtract(accoutVo.geteUseMoney())
                                    .setScale(2,RoundingMode.UP));
                        } else {
                            bRepaymentRecordVo.setNeedRechargeAccount(BigDecimal.ZERO);
                        }

                    }else{
                        if (totalPayMoney.compareTo(accoutVo.getUseMoney()) == 1) {
                            bRepaymentRecordVo.setNeedRechargeAccount(totalPayMoney.subtract(accoutVo.getUseMoney())
                                    .setScale(2,RoundingMode.UP));
                        } else {
                            bRepaymentRecordVo.setNeedRechargeAccount(BigDecimal.ZERO);
                        }
                    }

                }

            }
            sumRepayAccount = bRepaymentRecordService.sumWaitRepayMoney(borrowCnd);
        } catch (Exception e) {
            logger.error(e);
        }
        return new ModelAndView("/borrow/manage/repaySettlement/list")
                .addObject("page", page).addObject("portal_path", path)
                .addObject("sumRepayAccount", sumRepayAccount)
                .addObject("useMoney", useMoney);
    }


    /**
     *
     * <p>
     * Description:借款者还款<br />
     * </p>
     *
     * @author yubin
     * @version 0.1 2015年6月29日
     * @return MessageBox
     */
    @RequestMapping("/doReplay")
    @ResponseBody
    public MessageBox doReplay(@RequestParam(value = "name", required = false) String borrowUserName,
                               @RequestParam(value = "id", required = false) Integer repaymentid) {
        String message = null;
        try {

            BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordService.queryRepaymentByIdForUpdate(repaymentid);
            BorrowVo borrow = borrowService.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());

            // 如果是存管标，走银行存管本息还款流程
            if (borrow.getIsCustody() != null && borrow.getIsCustody().intValue() == 1
                    && bRepaymentRecordVo.getIsCustody() !=null && bRepaymentRecordVo.getIsCustody().intValue() == 1) {
                //return MessageBox.build("0","存管标还款功能处于关闭状态");
                return doCustodyReplay(borrowUserName, repaymentid);
            } else {
                return MessageBox.build("0","非存管标不能进行存管还款操作！");
            }
        }catch (Exception e) {
            message = "还款失败，请刷新页面后重试！";
            return MessageBox.build("0", message);
        }
    }

    // 平台提前结清还款
    public MessageBox doCustodyReplay(String borrowUserName, Integer repaymentid) {
        String message = null;
        try {

            Integer memberId = memberService
                    .getMemberIdByUserName(borrowUserName);
            if (memberId == null) {
                message = "请输入用户名不存在";
                return MessageBox.build("0", message);
            }
            ShiroUser shiroUser = ShiroUtils.currentUser();
            String ip = HttpTookit.getRealIpAddr(currentRequest());
            String repayKey = "RPKEY" + String.valueOf(repaymentid);
            String repay = String.valueOf(repaymentid);
            boolean repaybool = CacheService.getInstance().existKey(repayKey);

            if (!repaybool) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("uname", shiroUser.getUserName());
                map.put("repay", repay);
                CacheService.getInstance().put(repayKey, map);
                CacheService.getInstance().expire(repayKey, 1800);
                try {
                    message = cGRepaySettlementService.saveCustodyRepayBorrow(repaymentid, memberId);
                } catch (Exception e) {
                    CacheService.getInstance().remove(
                            "RPKEY" + String.valueOf(repaymentid));
                    message = "执行还款流程失败，请刷新页面后重试！";
                    return MessageBox.build("0", message);
                }
            } else {
                Map<String, String> repayMap = CacheService.getInstance()
                        .getMap(repayKey);
                message = "还款操作已经在进行中，操作人：" + repayMap.get("uname");
                return MessageBox.build("0", message);
            }

            CacheService.getInstance().remove(repayKey);

            if (message.equals(BusinessConstants.SUCCESS)) {
                return MessageBox.build("1", "还款成功");
            }
            return MessageBox.build("0", message);
        } catch (Exception e) {
            message = "还款失败，请刷新页面后重试！";
            return MessageBox.build("0", message);
        }
    }


    // 银行本息还款
    public MessageBox doCustodyReplayBackUp(String borrowUserName, Integer repaymentid) {
        String message = null;
        int bizType=0;
        MessageBox messageBox=MessageBox.build("0","");
        Integer memberId = memberService.getMemberIdByUserName(borrowUserName);
        if (memberId == null) {message = "请输入用户名不存在";
            return MessageBox.build("0", message);
        }
        ShiroUser shiroUser = ShiroUtils.currentUser();
        String ip = HttpTookit.getRealIpAddr(currentRequest());

        String repayKey = "RPKEY" + String.valueOf(repaymentid);
        String repay = String.valueOf(repaymentid);
        boolean repaybool = CacheService.getInstance().existKey(repayKey);

        //~~~~~~~~~~~~~~~生成本息还款数据~~~~~~~~~~~~~~~
        if (!repaybool) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("uname", shiroUser.getUserName());
            map.put("repay", repay);
            CacheService.getInstance().put(repayKey, map);
            CacheService.getInstance().expire(repayKey, 1800);
            try {
                message = cGRepaySettlementService.saveCustodyRepayBorrow(repaymentid, memberId);
            } catch (Exception e) {
                CacheService.getInstance().remove(repayKey);
                logger.error(e.getMessage() , e);
                return MessageBox.build("0", "生成本息还款数据失败，请刷新页面后重试！");
            }
        } else {
            Map<String, String> repayMap = CacheService.getInstance().getMap(repayKey);
            message = "生成本息还款数据已经在进行中，操作人：" + repayMap.get("uname");
            return MessageBox.build("0", message);
        }

        CacheService.getInstance().remove(repayKey);
        if(!messageBox.getCode().equals("1")){
            return messageBox;
        }



        //~~~~~~~~~~~~~~~上报本息还款数据~~~~~~~~~~~~~~~
        try {
            if ( messageBox.getCode().equals("1")) {
                bizType=Integer.parseInt(messageBox.getMessage());
                BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordService.queryRepaymentByIdForUpdate(repaymentid);
                List<EFundRepayMent> efundList = eFundRepayMentService.selectByRepayId(repaymentid, bRepaymentRecordVo.getBorrowId(),bizType);
                if(efundList == null || efundList.size() == 0 ){
                    return MessageBox.build("1", "暂无本息还款上报数据");
                }

                PageListModel plist = new PageListModel(efundList, 200);
                int totalPage = plist.getTotalPages();
                logger.info("本次总共:"+totalPage+"批，本息还款上报记录");
                for (int i = 1; i <= totalPage; i++) {
                    List<EFundRepayMent> efundPageList =plist.getObjects(i);
                    MessageBox mb = doReport(efundPageList,bRepaymentRecordVo,repaymentid, shiroUser.getUserId(),bizType);
                    if(! mb.getCode().equals("1")){
                        return mb;
                    }
                }
                return MessageBox.build("1", "上报本息还款数据成功");
            }
            return MessageBox.build("0", message);
        } catch (Exception e) {
            message="上报本息还款数据异常，请刷新页面后重试！";
            logger.error(message , e);
            return MessageBox.build("0", message);
        }
    }


    public MessageBox doReport(List<EFundRepayMent> efundList,BRepaymentRecordVo bRepaymentRecordVo,
                               Integer repaymentid,Integer userId,Integer bizType) throws Exception {
        BorrowVo borrowVo = borrowMapper.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());
        //生成还款XML
        String reqMessage = eFundRepayMentService.createRepayXML(efundList, borrowVo);
        //调用银行本息还款服务
        String relateNo = UUIDGenerator.generate(CGBusinessConstants.RELATENO);
        MessageBox mbox = eFundRepayMentService.sendXML(reqMessage, relateNo, userId, repaymentid);
        //银行本息请求处理
        if (!mbox.getCode().equals("1")) {return mbox;}
        MessageBox resbox = eFundRepayMentService.doRepayResponse(mbox.getMessage(),relateNo, userId, repaymentid,bizType);
        //银行本息响应处理
        if (!resbox.getCode().equals( "1")) {return resbox;}
        return MessageBox.build("1", "上报本息还款数据成功");
    }


}
