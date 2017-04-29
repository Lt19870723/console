package com.cxdai.console.account.recharge.controller;

import com.cxdai.console.account.recharge.service.RechargeRecordService;
import com.cxdai.console.account.recharge.vo.RechargeRecordCnd;
import com.cxdai.console.cgnotify.controller.CZBankNotifyController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * <p>
 * Description:资金管理 - 充值管理 - 充值记录查询<br />
 * </p>
 *
 * @title AdditionalOrderController.java
 */
@Controller
@RequestMapping(value = "/account/custodyrechargerecords")
public class CustodyRechargeRecordsController extends CZBankNotifyController {

    private final static Logger logger = Logger.getLogger(CustodyRechargeRecordsController.class);

    @Autowired
    private RechargeRecordService rechargeRecordService;

    /**
     * <p>
     * Description:充值记录主界面<br />
     * </p>
     *
     * @return ModelAndView
     * @author hujianpan
     * @version 0.1 2015年3月17日
     */
    @RequestMapping("/main")
    public ModelAndView rechargeRecordsMain() {
        return new ModelAndView("/account/recharge/custody/main");
    }

    /**
     * <p>
     * Description:查询充值记录列表<br />
     * </p>
     *
     * @author justin.xu
     * @version 0.1 2014年5月4日 void
     */
    @RequestMapping("/list/{pageNo}")
    public ModelAndView searchPageRechargeList(@ModelAttribute RechargeRecordCnd rechargeRecordCnd, @PathVariable Integer pageNo) {
        Page page = null;
        rechargeRecordCnd.setIsCustody(1);
        rechargeRecordCnd.setStatusIn("2,10,40");
        try {
            page = rechargeRecordService.queryPageListByRechargeRecordCnd(rechargeRecordCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return new ModelAndView("/account/recharge/custody/list").addObject("page", page);
    }

}
