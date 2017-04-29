package com.cxdai.console.borrow.manage.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.manage.service.BorrowManageService;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.common.statics.CGBusinessConstants;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.PropertiesUtil;
import com.cxdai.console.util.ShiroUtils;
import com.cxdai.console.util.serialnumberutil.UUIDGenerator;

/**
 * <p>
 * Description:投标中的借款标业务<br />
 * </p>
 * @title TenderBorrowController.java
 * @package com.cxdai.console.borrow.manage.controller
 * @author yubin
 * @version 0.1 2015年6月28日
 */
@Controller
@RequestMapping(value = "/manage/forTenderBorrowMain")
public class TenderBorrowController extends BaseController {

	private final static Logger logger = Logger.getLogger(TenderBorrowController.class);
	@Autowired
	private BorrowManageService borrowManageService;
	private String path = PropertiesUtil.getValue("portal_path");

	/**
	 * <p>
	 * Description:进入投标中的借款标列表<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @return ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main() {

		return new ModelAndView("/borrow/manage/tenderBorrow/main");
	}

	/**
	 * <p>
	 * Description:查询投标中的借款标<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return ModelAndView
	 */
	@RequestMapping("/list/{pageNo}")
	public ModelAndView searchTenderBorrowList(@ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
		Page page = null;
		try {
			page = borrowManageService.queryTenderBorrowList(borrowCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);
		} catch (Exception e) {

			logger.error(e);
		}
		return new ModelAndView("/borrow/manage/tenderBorrow/list").addObject("page", page).addObject("portal_path", path);
	}

	/**
	 * <p>
	 * Description:撤标<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月29日
	 * @param borrowId
	 * @return MessageBox
	 */
	@RequestMapping("/cancelBorrow")
	@ResponseBody
	public MessageBox cancelBorrow(@RequestParam(value = "id", required = false) Integer borrowId) {
		try {
			ShiroUser shiroUser=ShiroUtils.currentUser();
			String msg = null;
			BorrowVo borrowVo =borrowManageService.findBorrow(borrowId);
			if (borrowVo == null) {
				return MessageBox.build("0", "该借款标不存在，无法撤标！");
			}
			//存管标
			if(borrowVo.getIsCustody()==1){
				String relateNo=UUIDGenerator.generate(CGBusinessConstants.RELATENO);
				//调用流标接口
				MessageBox mb = borrowManageService.cancelCGBorrow(borrowVo.getId(), shiroUser, relateNo);
				if(mb.getCode().equals("0")){
					//记录接口响应日志
					String ms= borrowManageService.savePTRRes(mb.getMessage(), shiroUser, relateNo, borrowVo.getId());
					//接口响应成功
					if(BusinessConstants.SUCCESS.equals(ms)){
						//更新借款标状态
						Borrow borrow=new Borrow();
						borrow.setId(borrowVo.getId());
						borrow.setStatus(7);//流标银行处理中
						borrowManageService.updateBorrowStatusById(borrow);
						
					}else{
						msg=ms;
					}
					
				}else{
					msg=mb.getMessage();
				}
			//非存管标	
			}else if(borrowVo.getIsCustody()==0){
				msg = borrowManageService.cancelBorrow(borrowId);
			}
			
			return MessageBox.build("0", msg);
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("1", "撤标异常！");
		}
	}
}
