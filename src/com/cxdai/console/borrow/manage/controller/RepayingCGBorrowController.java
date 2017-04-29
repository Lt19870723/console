package com.cxdai.console.borrow.manage.controller;

import com.cxdai.console.borrow.manage.service.BRepaymentRecordService;
import com.cxdai.console.borrow.manage.service.BorrowManageService;
import com.cxdai.console.borrow.manage.service.CGWebPayService;
import com.cxdai.console.borrow.manage.service.EFundRepayMentService;
import com.cxdai.console.borrow.manage.vo.BRepaymentRecordVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.borrow.manage.vo.EFundRepayMent;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.stock.entity.ShareholderRoster;
import com.cxdai.console.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * <p>
 * Description:存管还款中的借款标业务<br />
 * </p>
 * @title TenderBorrowController.java
 * @package com.cxdai.console.borrow.manage.controller 
 * @author yubin
 * @version 0.1 2015年6月28日
 */
@Controller
@RequestMapping(value = "/manage/forRepayingCGBorrow")
public class RepayingCGBorrowController extends BaseController {
	
	private final static Logger logger=Logger.getLogger(RepayingCGBorrowController.class);
	@Autowired
	private BorrowManageService borrowManageService;
	@Autowired
	private BRepaymentRecordService bRepaymentRecordService;
	private String path=PropertiesUtil.getValue("portal_path");
	@Autowired
	private CGWebPayService cgWebPayService;
    @Autowired
    private EFundRepayMentService eFundRepayMentService;
	/**
	 * 
	 * <p>
	 * Description:进入还款中的借款标列表<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(){
		return  new ModelAndView("/borrow/manage/repayingCGBorrow/main");
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
		    borrowCnd.setIsCustody("1");
	   		page =cgWebPayService.selectRepayingBorrow(borrowCnd, pageNo,  Constants.CONSOLE_PAGE_SIZE);
	   		sumRepayAccount=cgWebPayService.sumWaitRepayMoney(borrowCnd);
	} catch (Exception e) {
	   		e.printStackTrace();
	   		logger.error(e);
	}
	  return new ModelAndView("/borrow/manage/repayingCGBorrow/list").addObject("page", page).addObject("sumRepayAccount", sumRepayAccount).addObject("portal_path", path);
   }


	/**
	 *
	 * <p>
	 * Description:网站垫付<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月29日
	 * @return
	 * MessageBox
	 */
	@RequestMapping("/webpay")
	@ResponseBody
	public  MessageBox webpay(@RequestParam(value = "id", required = false) Integer repaymentId) {
		String addip = HttpTookit.getRealIpAddr(currentRequest());
		ShiroUser shiroUser= ShiroUtils.currentUser();
		String msg="";
		try {
			msg = cgWebPayService.saveWebpayBorrow(shiroUser, repaymentId, addip);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return MessageBox.build("2", "系统繁忙,请稍后重试！");
		}
		if(msg.equals(BusinessConstants.SUCCESS)){
			return MessageBox.build("0", "垫付成功");
		}

		return MessageBox.build("1",msg);
	}

	@RequestMapping(value="/exportWebpayRecord/{repaymentId}")
	public ModelAndView exportWebpayRecord(HttpServletRequest request,ModelMap model, @PathVariable Integer repaymentId)
			throws Exception {

		if(repaymentId ==null){
			throw new Exception("待还记录ID不能为空");
		}

        BRepaymentRecordVo bRepaymentRecordVo =  bRepaymentRecordService.queryRepaymentById(repaymentId);
        if((bRepaymentRecordVo.getStatus() == null || bRepaymentRecordVo.getStatus() != 0)&&
                (bRepaymentRecordVo.getWebstatus() == null || bRepaymentRecordVo.getWebstatus() != 5)){
            throw new Exception("待还记录状态不在银行垫付中，无法导出");
        }
        List<EFundRepayMent> efundList = eFundRepayMentService.selectByRepayId(repaymentId, bRepaymentRecordVo.getBorrowId(),3);
        String[] headData = new String[] {"项目基本信息登记ID","借款标ID","待还记录ID","待收记录ID","期数" ,"融资人姓名","回款账户类型",
				"划款账户类型","P2P平台流水号","原投资流水号","回款金额","手续费" };

        List<String[]> dataList = new ArrayList<String[]>();
        String[] endData = null;
        String fileName ="银行垫付记录";
        if (efundList != null && efundList.size()>0) {
            for(EFundRepayMent efend : efundList){
                String[] data = new String[headData.length];
                data[0] = efend.geteProjectId() == null ? "" :  efend.geteProjectId()+"";
                data[1] = efend.getBorrowId()== null ? "" :efend.getBorrowId().intValue()+"";
                data[2] = efend.getReapaymentId() == null ? "" : efend.getReapaymentId().intValue()+"";
                data[3] = efend.getCollectionId()==null ? "" :efend.getCollectionId()+"";
                data[4] = efend.getOrder() == null ?  "": efend.getOrder()+"" ;
                data[5] = efend.getRepayName() == null ? "":  efend.getRepayName();
				String flag=efend.getReturnType()+"";

				if(flag.equals("0")){
					flag="存管账户";
				}else if (flag.equals("1")){
					flag="他行主账户";
				}else if (flag.equals("2")){
					flag="平台自身投资账户";
				}
				data[6] = flag == null ?  "" : flag ;
				String innerflag=efend.getInnerFlag()+""; //划款账户类型

				if(innerflag.equals("0")){
					innerflag="原还款账户";
				}else if (flag.equals("1")){
					innerflag="内部账户";
				}
				data[7] = innerflag == null ?  "" : innerflag+"" ;
				data[8] = efend.getBizNo() == null ?  "" : efend.getBizNo() ;
				data[9] = efend.geteInvestNo() == null ?  "" : efend.geteInvestNo() ;
//                data[6] = efend.getAccountBankNumber() == null ?  "" : efend.getAccountBankNumber() ;
//                data[7] = efend.getAccountName() == null ?  "": efend.getAccountName();
				Integer repaymentAmount = efend.getRepaymentAmount();
				BigDecimal money = new BigDecimal(repaymentAmount).divide(new BigDecimal(100));
                data[10] = money == null ?  "": money+"";
                data[11] = efend.getFee() == null ?  "": efend.getFee()+"";
                dataList.add(data);
            }
        } else {
            String[] data = new String[1];
            data[0] = "暂无数据";
            dataList.add(data);
        }
        return new ModelAndView(new ViewExcel(fileName + DateUtils.formatDateYmd(new Date()), headData, dataList, endData), model);

	}

    /**
     * 银行垫付后确认
     * @param repaymentId
     * @return
     */
    @RequestMapping("/webpayConfirm/{repaymentId}")
    @ResponseBody
    public  MessageBox webpayConfirm(@PathVariable Integer repaymentId) {
        String addip = HttpTookit.getRealIpAddr(currentRequest());
        ShiroUser shiroUser= ShiroUtils.currentUser();
        String msg="";
        try {
            msg = cgWebPayService.saveWebpaywebpayConfirm(shiroUser, repaymentId, addip);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return MessageBox.build("0", "系统繁忙,请稍后重试！");
        }
        if(msg.equals(BusinessConstants.SUCCESS)){
            return MessageBox.build("1", "银行垫付确认完成");
        }

        return MessageBox.build("0",msg);
    }



}
