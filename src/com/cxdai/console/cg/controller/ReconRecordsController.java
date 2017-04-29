package com.cxdai.console.cg.controller;

import com.cxdai.console.cgnotify.controller.CZBankNotifyController;
import com.cxdai.console.cgnotify.entity.EReconFile;
import com.cxdai.console.cgnotify.vo.ReconCnd;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.finance.virement.entity.Journal;
import com.cxdai.console.finance.virement.service.JournalService;
import com.cxdai.console.finance.virement.vo.JournalCnd;
import com.cxdai.console.util.custody.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 * Description:浙商银行对账记录Controller<br />
 * </p>
 *
 * @author zhaowei
 * @version 0.1 2016年5月18日
 * @title CZBankNotifyController.java
 * @package com.cxdai.portal.account.controller
 */
@Controller
@RequestMapping(value = "/cg/reconrecords")
public class ReconRecordsController extends CZBankNotifyController {

    public Logger logger = Logger.getLogger(ReconRecordsController.class);

    @RequestMapping("/main")
    public ModelAndView reconRecordMain() {
        return new ModelAndView("/cg/recon/main");
    }

    @RequestMapping("/list/{pageNo}")
    public ModelAndView reconRecordList(@ModelAttribute ReconCnd reconCnd, @PathVariable Integer pageNo) {
        Page page = null;
        try {
            if (reconCnd.getStatus() != null && reconCnd.getStatus() == -1) {
                reconCnd.setStatus(null);
            }
            if (reconCnd.getTargetType() != null && "-1".equals(reconCnd.getTargetType())) {
                reconCnd.setTargetType(null);
            }
            if (reconCnd.getTargetName() != null && "-1".equals(reconCnd.getTargetName())) {
                reconCnd.setTargetName(null);
            }
            page = czBankReconNotifyService.queryListForPage(reconCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return new ModelAndView("/cg/recon/list").addObject("page", page);
    }

    @RequestMapping("/retry/{filename}")
    @ResponseBody
    public MessageBox retry(@PathVariable("filename") String filename) {
        String msg = messageRecordMapper.queryLastMessage(filename);
        if (!StringUtils.hasLength(msg)) {
            return MessageBox.build("0", "查询报文错误");
        }
        String result = super.onReceive(msg);
        if (!BusinessConstants.SUCCESS.equals(result)) {
            return MessageBox.build("0", result);
        }
        return MessageBox.build("1", "操作成功");
    }

    /****
     * 详情
     *
     * @param filename 文件名称
     * @return
     */
    @RequestMapping(value = "/detail/{filename}")
    public ModelAndView detail(@PathVariable String filename) {
        ModelAndView mv = new ModelAndView("/cg/recon/detail");
        if (StringUtils.hasLength(filename)) {
            List<EReconFile> reconFiles = czBankReconNotifyService.selectByFilename(filename);
            mv.addObject("reconFiles", reconFiles);
        }
        return mv;
    }

}
