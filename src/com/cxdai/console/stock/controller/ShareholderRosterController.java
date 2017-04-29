package com.cxdai.console.stock.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.red.util.WDWUtil;
import com.cxdai.console.stock.entity.ShareholderRoster;
import com.cxdai.console.stock.entity.ShareholderRosterlog;
import com.cxdai.console.stock.service.ShareholderService;
import com.cxdai.console.stock.vo.ShareholderCnd;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.ViewExcel;


/****
 * 股东花名册
 * <p>
 * Description:这里写描述<br />
 * </p>
 * @title ShareholderController.java
 * @package com.cxdai.console.stock.controller 
 * @author xinwugang
 * @version 0.1 2016-5-9
 */
@Controller
@RequestMapping(value="/shareholder")
public class ShareholderRosterController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(ShareholderRosterController.class);
	@Autowired
	private ShareholderService shareholderService;
	
	/***
	 * 申请审核信息主页面
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-9
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView shareholderMain(){
		return new ModelAndView("/stock/shareholder/main");
	}
	/****
	 * 申请审核信息列表页面
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-9
	 * @param pageNo
	 * @param applyCnd
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value = "/shareholderList/{pageNo}")
	public ModelAndView list(@PathVariable Integer pageNo, ShareholderCnd shareholderCnd) {
		
		Page page = null;
		try {
			page =  shareholderService.queryApplyForPage(shareholderCnd,Constants.CONSOLE_PAGE_SIZE, pageNo);
		} catch (Exception e) {
			logger.error(e);
			return new ModelAndView("/stock/shareholder/list").addObject("page", null);
		}
		return new ModelAndView("/stock/shareholder/list").addObject("page", page);
		
	}
	
	
	@RequestMapping(value = "/toExport")
	public ModelAndView toExport() {
		
		return new ModelAndView("/stock/shareholder/download");
		
	}
	
	
	
	/****
	 * 导出股东花名册
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-17
	 * @return
	 * ModelAndView
	 */
	@RequestMapping(value="/exportShareholder/{shareDownType}")
	public ModelAndView exportShareholder(HttpServletRequest requestm,String date, ModelMap model,@PathVariable Integer shareDownType)
			throws Exception {
		List<ShareholderRoster> shareList = null;
		String fileNmae="";
		//最新股东名册
		if(shareDownType==1){
			shareList = shareholderService.selectShareholderList();	
			fileNmae="最新内转名册";
		}
		//需退出股东名册
		if(shareDownType==2){
			shareList = shareholderService.outShareholdList();	
			fileNmae="需退出内转名册";
		}
		//需加入股东名册
		if(shareDownType==3){
			shareList = shareholderService.addShareholdList();
			fileNmae="需加入内转名册";
		}

		String[] headData = new String[] {"用户编号", "用户名称", "真实姓名", "身份证号", "内转交易编号", "有限合伙名称","可操作内转总数量","持有比例" };
		List<String[]> dataList = new ArrayList<String[]>();
		String[] endData = null;
		if (shareList != null) {
			for(ShareholderRoster res : shareList){
				String[] data = new String[headData.length];
				data[0] = res.getUserId() == null ? "" : res.getUserId()+"";
				data[1] = res.getUserName() == null ? "" : res.getUserName();
				data[2] = res.getUserRealName() == null ? "" : res.getUserRealName();
				data[3] = res.getIdCard()==null ? "" :res.getIdCard();
				data[4] = res.getStockCode() != null ? res.getStockCode()+"" : "";
				data[5] = res.getStockName() != null ? res.getStockName()+"" : "";
				data[6] = res.getStockTotal() != null ? res.getStockTotal()+"" : "";
				data[7] = res.getShareholdingRatio() != null ? res.getShareholdingRatio()+"" : "";
				dataList.add(data);
			}
		} else {
			String[] data = new String[1];
			data[0] = "暂无数据";
			dataList.add(data);
		}
		return new ModelAndView(new ViewExcel(fileNmae + DateUtils.formatDateYmd(new Date()), headData, dataList, endData), model);
	
	}
	
	@RequestMapping(value = "/shareholderLogList/{id}/{userId}")
	public ModelAndView shareholderLogList(@PathVariable Integer id,@PathVariable Integer userId) {
		List<ShareholderRosterlog> shareLogList = null;
		ShareholderRoster shareholderRoster = null;
		try {
			shareLogList = shareholderService.shareholderLogList(1,userId);
			shareholderRoster = shareholderService.findShoreInfo(id);
					
		} catch (Exception e) {
			logger.error(e);
			return new ModelAndView("/common/404");
		}
		return new ModelAndView("/stock/shareholder/info").addObject("shareLogList", shareLogList).addObject("shareholderRoster", shareholderRoster);
		
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:导入股东花名册<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-5-24
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * MessageBox
	 */
	  @RequestMapping(value = "/importExport", method = RequestMethod.POST)
	  @ResponseBody
	  public MessageBox importBrandSort(@RequestParam("fileName") MultipartFile file,
	      HttpServletRequest request,HttpServletResponse response){
		  File newFile = null;
		  
			if (file == null){
				return new MessageBox("50000", "文件为空");
			}
			String fileName = file.getOriginalFilename();
			// 判断文件大小、即名称
			long size = file.getSize();
			if (fileName == null || ("").equals(fileName) && size == 0){
			    return new MessageBox("50000", "文件名或大小为空");
			}
			if (size>(5 * 1024 * 1024)){
				 return new MessageBox("50000", "文件大小不能大于5M");
			}
			if (!validateExcel(fileName)) {
				return new MessageBox("50000", "文件名不是excel2003版本格式");
		    }
	    try{
		  String temp = request.getSession().getServletContext().getRealPath(File.separator)+"temp"; // 临时目录
		    File tempFile = new File(temp);
		    if (!tempFile.exists()) {
		      tempFile.mkdirs();
		    }
		    String filePath= temp+File.separator+file.getOriginalFilename();
		    newFile = new File(filePath); 
		    file.transferTo(newFile);
		    //读取excel 返回操作集合
		    List<ShareholderRoster> list = shareholderService.readExcel(newFile);
		    //变更股东花名册，作废退出股东申请
		    shareholderService.updateShareholder(list,currentUser().getUserId(),currentUser().getUserName(),HttpTookit.getRealIpAddr(currentRequest()));
	    }catch(Exception e){
	    	logger.error(e);
			return new MessageBox("50000", "导入数据失败！请联系管理员");
	    }finally{
	    	if(newFile != null)
	    	newFile.delete();
	    }
	    return new MessageBox("00000", "");
	  }
	  
	  /**
	   * 
	   * <p>
	   * Description:进入导入页面<br />
	   * </p>
	   * @author xiaofei.li
	   * @version 0.1 2016-5-31
	   * @return
	   * ModelAndView
	   */
	  @RequestMapping("/inImport")
		public ModelAndView inImport(){
			return new ModelAndView("/stock/shareholder/importExport");
		}
	  
	  
	  
	  
	  private boolean validateExcel(String filePath) {
			if (filePath == null
					|| !(WDWUtil.isExcel2003(filePath))) {
				return false;
			}
			return true;
		}
}
