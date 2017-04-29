package com.cxdai.console.cg.controller;

import com.cxdai.console.cgnotify.service.CZBankReconNotifyService;
import com.cxdai.console.cgnotify.vo.ReconResultCnd;
import com.cxdai.console.common.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 出入账对账
 *
 * @author zhaowei
 */
@Controller
@RequestMapping(value = "/cg/biotfrecon")
public class BiotfReconController extends BaseController {

    private static final Logger logger = Logger.getLogger(BiotfReconController.class);
    @Autowired
    private CZBankReconNotifyService czBankReconNotifyService;

    @RequestMapping("/main")
    public ModelAndView main() {
        return new ModelAndView("/cg/biotfrecon/main");
    }

    /**
     * 出入账对账统计结果
     *
     * @param reconResultCnd
     * @param request
     * @return
     */
    @RequestMapping("/reconresult")
    public ModelAndView reconresult(@ModelAttribute ReconResultCnd reconResultCnd, HttpServletRequest request) {
        Map<String, Object> checkMap = new HashMap<>();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if (!StringUtils.isEmpty(request.getParameter("beginTotalTime"))) {
                reconResultCnd.setBeginTime(format.parse(request.getParameter("beginTotalTime")));
                reconResultCnd.setEndTime(format.parse(request.getParameter("beginTotalTime")));
            }
            checkMap = czBankReconNotifyService.queryBiotfReconByCnd(reconResultCnd);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("出入对账统计错误信息：" + e);
        }
        return new ModelAndView("/cg/biotfrecon/list").addObject("checkMap", checkMap);
    }
}
