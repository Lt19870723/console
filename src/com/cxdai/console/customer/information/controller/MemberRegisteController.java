package com.cxdai.console.customer.information.controller;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.base.entity.Borrow;
import com.cxdai.console.base.entity.Borrower;
import com.cxdai.console.base.entity.Mortgage;
import com.cxdai.console.borrow.approve.service.BorrowService;
import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.approve.vo.BorrowerVo;
import com.cxdai.console.borrow.emerg.vo.AccountUploadDocVo;
import com.cxdai.console.borrow.emerg.vo.MortgageVo;
import com.cxdai.console.borrow.emerg.vo.SalariatBorrowVo;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.Dictionary;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.customer.information.entity.BorrowerUser;
import com.cxdai.console.customer.information.service.BusinessService;
import com.cxdai.console.customer.information.service.MemberService;
import com.cxdai.console.customer.information.vo.BorrowBusinessVo;
import com.cxdai.console.customer.information.vo.BusinessCnd;
import com.cxdai.console.customer.information.vo.BusinessVo;
import com.cxdai.console.customer.information.vo.MemberCnd;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.system.entity.Configuration;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.PropertiesUtil;
/**
 * 
 * <p>
 * Description:借款用户注册和发标<br />
 * </p>
 * @title MemberRegisteController.java
 * @package com.cxdai.console.customer.information.controller 
 * @author 于斌
 * @version 0.1 2015年7月17日
 */
@Controller
@RequestMapping(value = "/information/memberRegiste")
public class MemberRegisteController extends BaseController{
    
	@Autowired
	private MemberService memberService;
	@Autowired
	private BorrowService borrowService;
	@Autowired
	private BusinessService businessService;
	
	private String path=PropertiesUtil.getValue("portal_path");
	
	private final static Logger logger=Logger.getLogger(MemberRegisteController.class);
	/**
	 * 
	 * <p>
	 * Description:进入页面<br />
	 * </p>
	 * @author 于斌
	 * @version 0.1 2015年7月16日
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/main")
	public ModelAndView main(@RequestParam(value = "username", required = false)  String username){
		
		return new ModelAndView("/customer/information/memberRegiste/main").addObject("username", username);
	}
	
	/**
	 * 
	 * <p>
	 * Description:客户信息查询 <br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年6月28日
	 * @param borrowAuditHistoryCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
    @RequestMapping("/list/{pageNo}")
	public ModelAndView searchRepayingBorrowList(@ModelAttribute MemberCnd memberCnd, @PathVariable Integer pageNo) {
	Page page = null; 
	 
	try {
		    memberCnd.setUsersType(0);
	   		page =memberService.queryMemberVoListForPages(memberCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);	 
	} catch (Exception e) {
		    logger.error(e);
	}
	  return new ModelAndView("/customer/information/memberRegiste/list").addObject("page", page);
   }
    /**
     * 
     * <p>
     * Description:进入注册页面<br />
     * </p>
     * @author 于斌
     * @version 0.1 2015年7月16日
     * @return
     * ModelAndView
     */
    @RequestMapping("/register")
	public ModelAndView registe(){
    	
		return new ModelAndView("/customer/information/memberRegiste/register");
	}
    /**
     * 
     * <p>
     * Description:保存注册信息<br />
     * </p>
     * @author 于斌
     * @version 0.1 2015年7月16日
     * @param borrowApprovedVo
     * @return
     * MessageBox
     */
	@RequestMapping("/save")
	@ResponseBody
	public MessageBox save(@ModelAttribute MemberVo memberVo){
	    String msg=""; 
		try {
			msg=memberService.saveMember(memberVo);
			if(msg.equals(BusinessConstants.SUCCESS)) {
				return MessageBox.build("1", "注册成功");
			}
			return MessageBox.build("0", msg);
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("0", "操作失败");
		}
	}
	/**
	 * 
	 * <p>
	 * Description:去发标页面<br />
	 * </p>
	 * @author 于斌
	 * @version 0.1 2015年7月17日
	 * @param userId
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/applyBorrow")
	public ModelAndView applyBorrow(@RequestParam(value = "userId", required = false)  Integer userId){
		ModelAndView modelAndView=new ModelAndView("/customer/information/memberRegiste/applyBorrow");
		try {
			MemberVo memberVo = memberService.queryMemberById(userId);
			if (memberVo != null) {
				 modelAndView.addObject("username", memberVo.getUsername());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.addObject("userId",userId).addObject("portal_path", path);
		return modelAndView;
	}
	/**
	 * 
	 * <p>
	 * Description:集合数据<br />
	 * </p>
	 * @author 于斌
	 * @version 0.1 2015年7月17日
	 * @param borrowCnd
	 * @param pageNo
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/sublist/{pageNo}")
	public ModelAndView searchEffectiveBorrowList(@ModelAttribute BorrowCnd borrowCnd, @PathVariable Integer pageNo) {
			Page page = null; 
			 
			try {
				page = borrowService.searchEffectiveBorrowListByBorrowCnd(borrowCnd, pageNo, Constants.CONSOLE_PAGE_SIZE);	 
			} catch (Exception e) {
				    logger.error(e);
			}
			  return new ModelAndView("/customer/information/memberRegiste/applyBorrowlist").
					  addObject("page", page).addObject("portal_path", path);
   }
	
	/**
     * 
     * <p>
     * Description:初始化发布借款标（官方标）<br />
     * </p>
     * @author 于斌
     * @version 0.1 2015年7月17日
     * @param userId
     * @return
     * ModelAndView
     */
	@RequestMapping("/initApplyBorrow")
	public ModelAndView initApplyBorrow(@RequestParam(value = "userId", required = false)  Integer userId,
			                          @RequestParam(value = "borrowId", required = false)  Integer borrowId){
		
		ModelAndView modelAndView=new ModelAndView("/customer/information/memberRegiste/add");
		
	    Map<String, Object> map = new HashMap<String, Object>();
	    Mortgage mortgage = new Mortgage();
	    Borrower borrower = new Borrower();
	    SalariatBorrowVo salariatBorrowVo =new SalariatBorrowVo();;
	    
		try {
			MemberVo memberVo = memberService.queryMemberById(userId);
			if (memberVo != null) {
				 
					if (borrowId != null && borrowId > 0) {
						BorrowVo borrowVo = borrowService.selectByPrimaryKey(borrowId); 
						BeanUtils.copyProperties(borrowVo, salariatBorrowVo);
						 
						MortgageVo mortgageVo = borrowService.queryMortgageByBorrow(borrowId);
						if(mortgageVo!=null){
							BeanUtils.copyProperties(mortgageVo, mortgage);
						}						
						
						BorrowerVo borrowerVo = borrowService.queryBorrowerByBorrowId(borrowId);
						BeanUtils.copyProperties(borrowerVo, borrower);
						
						BorrowBusinessVo borrowBusinessVo=businessService.selectBorBusByBorrowId(borrowId);
						if(borrowBusinessVo!=null){
							//设置业务员ID
							salariatBorrowVo.setBusinessUserId(borrowBusinessVo.getUserId());
						}
						
						salariatBorrowVo.setOldBidPassword(salariatBorrowVo.getBidPassword());
					}
					modelAndView.addObject("salariatBorrowVo", salariatBorrowVo);
					modelAndView.addObject("mortgage", mortgage);
					modelAndView.addObject("borrower", borrower);
					map = borrowService.initBorrowInfo(memberVo);
					
					Collection<Configuration> educationOptions = Dictionary.getValues(810);// 学历
					Collection<Configuration> maritalStatusOptions = Dictionary.getValues(811);// 婚姻状况
					Collection<Configuration> scaleOptions = Dictionary.getValues(812);// 公司规模
					Collection<Configuration> workYearsOptions = Dictionary.getValues(813);// 工作时间
					Collection<Configuration> repaymentStyleOptions = Dictionary.getValues(400);// 官方标-还款方式
					
					//查询状态正常的业务员信息
					BusinessCnd businessCnd=new BusinessCnd();					
					businessCnd.setStatus(0);
					List<BusinessVo> businessList=businessService.searchBusinessListByCnd(businessCnd);
					 
					//查询权证人员账户号
					BorrowerUser borrowerUser=new BorrowerUser();
					borrowerUser.setStatus(1);//1：已启用
					List<BorrowerUser> borrowerUserList= borrowService.selectBorrowUser(borrowerUser);
					
					
					modelAndView.addObject("educationOptions", educationOptions)
					            .addObject("maritalStatusOptions", maritalStatusOptions)
					            .addObject("scaleOptions", scaleOptions)
					            .addObject("workYearsOptions", workYearsOptions)
					            .addObject("repaymentStyleOptions", repaymentStyleOptions)
					            .addObject("map", map).addObject("userId",userId)
					            .addObject("businessList", businessList)
					            .addObject("borrowerUserList", borrowerUserList);
								
			}	 
			 
		} catch (Exception e) {
			logger.error("发布借款标初始化失败！", e);
		}
		return modelAndView; 
	}
	
	/**
	 * 
	 * <p>
	 * Description:保存借款标申请信息<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年6月3日 void
	 */
	@RequestMapping("/saveApplyBorrow")
	@ResponseBody
	public MessageBox saveApplyBorrow(@ModelAttribute SalariatBorrowVo salariatBorrowVo,
			                          @ModelAttribute Borrower borrower,
			                          @ModelAttribute Mortgage mortgage,  
			                          @RequestParam(value = "userId", required = false)  Integer userId) {
		String msg = "success";
		try {
			 
			MemberVo memberVo = memberService.queryMemberById(userId);
			if (memberVo != null) {
				if (memberVo.getIsFinancialUser() != 0) {
					msg = "抱歉，您是理财用户，不能申请借款标！";
				} else {
					String addip = HttpTookit.getRealIpAddr(currentRequest());
					salariatBorrowVo.setIsCheck(0);//默认为未登记
					msg = borrowService.saveApplyBorrow(memberVo, salariatBorrowVo, borrower, mortgage, addip);
					if(msg.equals("success")){						
						return MessageBox.build("1", "借款标申请成功");
					}
				}
			} else {
				msg = "抱歉，无选择借款用户，不能申请借款标！";
			}
		} catch (Exception e) {
			logger.error("借款标申请失败！", e);
			msg = "借款标申请失败！";
		}
		return MessageBox.build("0", msg);
	}
	/**
	 * 
	 * <p>
	 * Description:资料上传初始化<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年6月16日 void
	 */
	@RequestMapping("/initUpload")
	public ModelAndView initUpload( @RequestParam(value = "borrowId", required = false)  Integer borrowId,
			                        @RequestParam(value = "userId", required = false)  Integer userId) {
		
		ModelAndView modelAndView=new ModelAndView("/customer/information/memberRegiste/borrowInfoUpload"); 
		Integer style, oldBorrowId;
		Map<String, Object> map = new HashMap<String, Object>();
		Collection<Configuration> guarantyOptions = Dictionary.getValues(817);// 上传资料类型
		List<SelectItem> styleOptionsList,oldBorrowOptionsList;
		try {
			map = borrowService.initUpload(borrowId);
			styleOptionsList = new ArrayList<SelectItem>();
			oldBorrowOptionsList = new ArrayList<SelectItem>();
			if (guarantyOptions != null) {
				for (Configuration configuration : guarantyOptions) {
					styleOptionsList.add(new SelectItem(configuration.getName(), configuration.getValue()));
				}
				modelAndView.addObject("styleOptionsList",styleOptionsList);
			}
			Object object=map.get("beforeList");
			if (object != null) {
				List<Borrow> beforeList = (List<Borrow>)object;
				if (beforeList != null && beforeList.size() > 0) {
					for (Borrow borrow : beforeList) {
						oldBorrowOptionsList.add(new SelectItem(String.valueOf(borrow.getId()), borrow.getName() + "【借款标编号：" + borrow.getContractNo()
								+ "】"));
					}
					modelAndView.addObject("oldBorrowOptionsList", oldBorrowOptionsList);
				}
			}
			Object objDocList=map.get("accountUploadDocVoList");
			if(objDocList!=null){
				List<AccountUploadDocVo> list = (List<AccountUploadDocVo>)objDocList;
				if(list!=null && list.size()>0){
					for(AccountUploadDocVo uploadVo:list){
						//判断图片路径是否为空
						if(uploadVo.getDocPath()!=null && !"".equals(uploadVo.getDocPath())){							
					        try {
					            URL url = new URL(path+"/"+uploadVo.getDocPath());					                    
					            // 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
					            URLConnection uc = url.openConnection();
					            // 打开的连接读取的输入流。
					            InputStream in = uc.getInputStream();
					            // 获取图片对象
					            BufferedImage img =ImageIO.read(in);
					            if(img!=null){
					            	//存在此图片
					            	uploadVo.setFlagExist("1");
					            }else{
					            	//不存在此图片
						        	uploadVo.setFlagExist("0");
					            }
					        } catch (Exception e) {
					        	//不存在此图片
					        	uploadVo.setFlagExist("0");
					        }
						}else{
							//不存在此图片
							uploadVo.setFlagExist("0");							
						}
					}					
					modelAndView.addObject("accountUploadDocVoList", list);
				}
			}
			oldBorrowId = null;
			style = null;
			modelAndView.addObject("style",style).addObject("portal_path", path);
			modelAndView.addObject("oldBorrowId", oldBorrowId).addObject("map", map);
			modelAndView.addObject("borrowId", borrowId).addObject("userId",userId);
		} catch (Exception e) {
			 
			logger.error("借款标资料上传初始化失败！", e);
		}
		return modelAndView;
	}
	/**
	 * 图片上传
	 * @param slideshow
	 * @param event
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public MessageBox upload(@RequestParam("files") MultipartFile[] files,@RequestParam("style") Integer style,
			                 @RequestParam("borrowId") Integer borrowId,@RequestParam("userId") Integer userId) throws Exception {
	   String msg="";
	   if (style == null || style <= 0) {
			msg = "请先选择类型！";
		}else{
		    try {
				
				msg = memberService.saveUploadPic(files[0], currentRequest(),style, borrowId, userId, HttpTookit.getRealIpAddr(currentRequest()));
				if(msg.equals("success")){
					return MessageBox.build("1", "上传成功");
				}
			} catch (Exception e) {
				 
				logger.error(e);
				msg = "图片上传错误!!";
			}
	    }
	   return new MessageBox("0", msg);
	}
	/**
	 * 
	 * <p>
	 * Description:删除上传资料<br />
	 * </p>
	 * @author 于斌
	 * @version 0.1 2015年7月28日
	 * @param Id
	 * @return
	 * MessageBox
	 */
	@RequestMapping("/deleteDoc")
	@ResponseBody
	public MessageBox deleteDoc(@RequestParam(value = "id", required = false) Integer Id ) {
		String msg="";
		 
		try {
		 msg = memberService.deleteDoc(Id);	 
		 if(msg.equals("success")){
			 return MessageBox.build("1", "删除成功");
		 }
		 return MessageBox.build("0", msg);
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("0", "删除上传资料错误！"); 
		}
	}
	/**
	 * 
	 * <p>
	 * Description:删除借款标全部资料<br />
	 * </p>
	 * @author 于斌
	 * @version 0.1 2015年7月28日
	 * @param Id
	 * @return
	 * MessageBox
	 */
	@RequestMapping("/deleteAllDoc")
	@ResponseBody
	public MessageBox deleteAllDoc(@RequestParam(value = "borrowId", required = false) Integer borrowId ) {
		String msg="";
		 
		try {
		 msg = memberService.deleteAllDoc(borrowId); 
		 if(msg.equals("success")){
			 return MessageBox.build("1", "删除成功");
		 }
		 return MessageBox.build("0", msg);
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("0", "删除借款标全部资料错误！"); 
		}
	}
	@RequestMapping("/uploadOldBorrowInfos")
	@ResponseBody
	public MessageBox uploadOldBorrowInfos(@RequestParam(value = "borrowId", required = false) Integer borrowId,
			                               @RequestParam(value = "oldBorrowId", required = false) Integer oldBorrowId) {
		String msg="";
		 
		try {
		 msg = memberService.saveBorrowInfosFromOldBorrow(HttpTookit.getRealIpAddr(currentRequest()), borrowId, oldBorrowId); 
		 if(msg.equals("success")){
			 return MessageBox.build("1", "成功上传历史标");
		 }
		 return MessageBox.build("0", msg);
		} catch (Exception e) {
			stackTraceError(logger, e);
			return MessageBox.build("0", "上传历史标图片错误！"); 
		}
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:根据权证人员账户名查询账户号<br />
	 * </p>
	 * @author tanghaitao
	 * @version 0.1 2016年5月16日
	 * @param repayname
	 * @return
	 * BorrowerUser
	 */
	@RequestMapping(value="/findBorrowerUser")
	@ResponseBody
	public BorrowerUser findBorrowerUser(@RequestParam("repayName") String repayName){
		BorrowerUser borrowerUserList=null;
		try {
			//查询权证人员账户号
			BorrowerUser borrowerUser=new BorrowerUser();
			borrowerUser.setStatus(1);//1：已启用
			borrowerUser.setRepayName(repayName);
			borrowerUserList= borrowService.selectBorrowUser(borrowerUser).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return borrowerUserList;
		
	}
	
	
}
