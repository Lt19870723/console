package com.cxdai.console.account.cash.controller;

import com.cxdai.console.account.cash.service.CashRecordService;
import com.cxdai.console.account.cash.vo.CashRecordCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.page.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * Description:资金管理 - 提现管理 - 提现记录查看<br />
 * </p>
 *
 * @author hujianpan
 * @version 0.1 2015年4月7日
 * @title CashRecordController.java
 * @package com.cxdai.console.account.cash.controller
 */
@Controller
@RequestMapping(value = "/account/custodycashrecords")
public class CustodyCashRecordsController extends BaseController {
    private final static Logger logger = Logger.getLogger(CustodyCashRecordsController.class);

    @Autowired
    private CashRecordService cashRecordService;

    @RequestMapping("/main")
    public ModelAndView CashRecordMain() {
        return new ModelAndView("/account/cash/query/custody/main");
    }

    /**
     * <p>
     * Description:查询提现记录<br />
     * </p>
     *
     * @author hujianpan
     * @version 0.1 2015年4月7日 void
     */
    @RequestMapping("/list/{pageNo}")
    public ModelAndView searchPageCashRecordList(@ModelAttribute CashRecordCnd cashRecordCnd, @PathVariable Integer pageNo, HttpServletRequest request) {
        Map<String, BigDecimal> resultMap = null;
        Page page = null;
        try {
            cashRecordCnd.setCashColumn(3);
            cashRecordCnd.setIsCustody(1);
            cashRecordCnd.setStatusIn("0,10,40");
            cashRecordCnd.setBeginTime(request.getParameter("beginTime"));
            cashRecordCnd.setEndTime(request.getParameter("endTime"));
            cashRecordCnd.setBeginTime2(request.getParameter("beginTime2"));
            cashRecordCnd.setEndTime2(request.getParameter("endTime2"));
            cashRecordCnd.setVerifyTimeBeginDate(request.getParameter("verifyTimeBeginDate"));
            cashRecordCnd.setVerifyTimeEndDate(request.getParameter("verifyTimeEndDate"));
            page = cashRecordService.queryPageListByCnd(cashRecordCnd, pageNo, Constants.CONSOLE_PAGE_SIZE4);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("/account/cash/query/custody/list").addObject("page", page);
    }

}
