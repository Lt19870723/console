package com.cxdai.console.borrow.manage.controller;

import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.borrow.approve.mapper.BorrowMapper;
import com.cxdai.console.borrow.manage.service.BRepaymentRecordService;
import com.cxdai.console.borrow.manage.service.CGRepayMentService;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.fix.mapper.BCollectionrecordMapper;
import com.cxdai.console.fix.vo.BCollectionEfendRecord;
import com.cxdai.console.fix.vo.BCollectionRecordVo;
import com.cxdai.console.statistics.account.service.AccountService;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.util.PropertiesUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 失败的待收记录
 */
@Controller
@RequestMapping(value = "/borrow/manage/repayCollection")
public class RepayFailCollectionController extends BaseController {

    private final static Logger logger = Logger .getLogger(RepayFailCollectionController.class);
    @Autowired
    private BRepaymentRecordService bRepaymentRecordService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BCollectionrecordMapper bCollectionrecordMapper;
    @Autowired
    private BorrowMapper borrowMapper;

    private String path = PropertiesUtil.getValue("portal_path");

    @Autowired
    private CGRepayMentService cGRepayMentService;


    @RequestMapping("/main")
    public ModelAndView main() {
        return new ModelAndView("/borrow/manage/repayCollection/main");
    }


    @RequestMapping("/list/{pageNo}")
    public ModelAndView selectRepayingBorrow(
            @ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
        BigDecimal useMoney = null, sumRepayAccount = null;
        Page page = null;
        try {
            Integer memberId = memberService.getMemberIdByUserName(borrowCnd
                    .getUserName().trim());

            borrowCnd.setIsOfficial("1");
            page = bRepaymentRecordService.selectRepayFail(borrowCnd,
                    pageNo, Constants.CONSOLE_PAGE_SIZE);

        } catch (Exception e) {
            logger.error(e);
        }
        return new ModelAndView("/borrow/manage/repayCollection/list")
                .addObject("page", page);
    }

    @RequestMapping("/fail/{repaymentId}/{pageNo}")
    public ModelAndView queryfailCollection(@PathVariable Integer repaymentId,
                                            @PathVariable Integer pageNo)throws Exception {
        Page page = new Page(pageNo, Constants.CONSOLE_PAGE_SIZE2);

        BRepaymentRecordVo bRepaymentRecordVo = bRepaymentRecordService.queryRepaymentById(repaymentId);
        Borrow borrow = borrowMapper.selectByPrimaryKey(bRepaymentRecordVo.getBorrowId());
        List<BCollectionEfendRecord>  collectionList = bCollectionrecordMapper.queryCollectionByRepayMentId(repaymentId,page);
        for (BCollectionEfendRecord bCollectionEfendRecord : collectionList ){

            bCollectionEfendRecord.setName(borrow.getName());
            bCollectionEfendRecord.setStyle(borrow.getStyle());
            bCollectionEfendRecord.setBorrowtype(borrow.getBorrowtype());
            bCollectionEfendRecord.setTimeLimit(borrow.getTimeLimit());
            bCollectionEfendRecord.setBorrowOrder(borrow.getOrder());


        }

        page.setTotalCount(bCollectionrecordMapper.queryCollectionCountByRepayMentId(repaymentId));
        page.setResult(collectionList);
        return new ModelAndView("/borrow/manage/repayCollection/failcollection")
                .addObject("repaymentId", repaymentId).addObject("page", page);

    }


    @RequestMapping("/recon")
    @ResponseBody
    public void reconTest(@RequestParam(value = "projectName", required = false) String projectName,
                          @RequestParam(value = "fileName", required = false) String fileName) throws Exception {

          logger.debug("============对账后处理======="+"项目名："+projectName+";文件名："+fileName);
            cGRepayMentService.RepayRecon(projectName,fileName);


    }
}
