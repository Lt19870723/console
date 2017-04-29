package com.cxdai.console.borrow.manage.controller;

import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.emerg.mapper.TenderRecordMapper;
import com.cxdai.console.borrow.manage.mapper.RepaymentBatchRecordMapper;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/7/4.
 * 批量还款
 */
@Controller
@RequestMapping(value = "/borrow/manage/repayBatch")
public class RepayBatchController extends BaseController {

    private final static Logger logger=Logger.getLogger(RepayBatchController.class);

    @Autowired
    private RepaymentBatchService repaymentBatchService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BRepaymentRecordService bRepaymentRecordService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private BorrowManageService borrowManageService;
    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private EFundRepayMentService eFundRepayMentService;
    @Autowired
    private CGBorrowManageService gGBorrowManageService;
    private String path= PropertiesUtil.getValue("portal_path");


    /**
     *
     * <p>
     * Description:进入批量还款页面<br />
     * </p>
     * @author luobinbin
     * @version 0.1 2015年6月28日
     * @return
     * ModelAndView
     */
    @RequestMapping("/main")
    public ModelAndView main(){
        return  new ModelAndView("/borrow/manage/repayBatch/main");
    }


    /**
     *
     * <p>
     * Description:分页查询还款中的借款标集合<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年6月28日
     * @param pageNo
     * @return
     * ModelAndView
     */
    @RequestMapping("/list/{pageNo}")
    public ModelAndView searchRepayingBorrowList(@ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
        Page page = null;
        BigDecimal sumRepayAccount = null;
        try {
             borrowCnd.setIsOfficial("1");
             borrowCnd.setStatus("0");

            page =repaymentBatchService.selectRepayingBorrow(borrowCnd, pageNo,  Constants.CONSOLE_PAGE_SIZE);

            List<BRepaymentRecordVo> bRepaymentRecordVos = (List<BRepaymentRecordVo>) page
                    .getResult();

            if (!bRepaymentRecordVos.isEmpty()) {
                // 获取借款者当期的还款总和
                // repaymentAccount =
                // bRepaymentRecordVos.get(0).getRepaymentAccount();
                for (Iterator<BRepaymentRecordVo> iterator = bRepaymentRecordVos
                        .iterator(); iterator.hasNext();) {
                    BRepaymentRecordVo bRepaymentRecordVo = iterator.next();
                    Date repaymentTimeDate = new Date(
                            Long.parseLong(bRepaymentRecordVo
                                    .getRepaymentTime()) * 1000);
                    repaymentTimeDate = DateUtils.parse(DateUtils.format(
                            repaymentTimeDate, DateUtils.YMD_DASH),
                            DateUtils.YMD_DASH);
                    Date now = DateUtils.parse(
                            DateUtils.format(new Date(), DateUtils.YMD_DASH),
                            DateUtils.YMD_DASH);

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

            sumRepayAccount=repaymentBatchService.sumWaitRepayMoney(borrowCnd);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return new ModelAndView("/borrow/manage/repayBatch/list")
                .addObject("page", page)
                .addObject("sumRepayAccount", sumRepayAccount)
                .addObject("portal_path", path);
    }



    @RequestMapping("/doBatchReplay")
    @ResponseBody
    public MessageBox doBatchReplay(@ModelAttribute BorrowCnd borrowCnd) throws Exception{
        System.out.println("=======================");
        String repaytime = borrowCnd.getRepaymentTimeBeginStr();
        if(repaytime == null || repaytime.trim().equals("")){
            return MessageBox.build("1","应还日期不能为空");
        }

        String eL = "\\d{4}([-]\\d{2}){2}";
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(repaytime);
        if(!m.matches()){
            return MessageBox.build("0","应还日期格式错误");
        }

        String isCustody = borrowCnd.getIsCustody();
        if(isCustody == null || isCustody.trim().equals("")){
            return MessageBox.build("0","是否存管不能为空");
        }

        borrowCnd.setIsOfficial("1");
        borrowCnd.setStatus("0");
        Page page = null;

        try {
            page =repaymentBatchService.selectRepayingBorrow(borrowCnd, 1,  2);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return MessageBox.build("0","查询待还记录异常");
        }

        List<BRepaymentRecordVo> bRepaymentRecordVos = (List<BRepaymentRecordVo>) page.getResult();
        int total= bRepaymentRecordVos.size();
        int sucNum =0;
        int failNum = 0;

        for(BRepaymentRecordVo bRepaymentRecordVo : bRepaymentRecordVos){
            MessageBox mbx = doSelfReplay(bRepaymentRecordVo.getUsername(),bRepaymentRecordVo.getId());

            if (mbx.getCode()=="1"){
                sucNum+=1;
            }
            if (mbx.getCode()=="0"){
                failNum+=1;
            }
        }
       return MessageBox.build("0","批量处理完成;总数："+total+";成功："+sucNum+"笔;"+";失败："+failNum+"笔;");

    }


    // 平台本息还款
    public MessageBox doSelfReplay(String borrowUserName, Integer repaymentid) {
        String message = null;
        try {

            Integer memberId = memberService.getMemberIdByUserName(borrowUserName);
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
                    message = borrowManageService.saveRepayBorrow(repaymentid,
                            ip, memberId, shiroUser, borrowUserName);
                } catch (Exception e) {
                    CacheService.getInstance().remove(
                            "RPKEY" + String.valueOf(repaymentid));
                    message = "执行还款流程失败，请刷新页面后重试！";
                    return MessageBox.build("0", message);
                }
            } else {
                Map<String, String> repayMap = CacheService.getInstance().getMap(repayKey);
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
    public MessageBox doCustodyReplay(String borrowUserName, Integer repaymentid) {
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
                messageBox = gGBorrowManageService.saveCustodyRepayBorrow(repaymentid, ip, memberId, shiroUser, borrowUserName);
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
