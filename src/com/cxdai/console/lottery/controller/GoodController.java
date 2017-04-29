package com.cxdai.console.lottery.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cxdai.console.common.BaseController;
import com.cxdai.console.common.Constants;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.lottery.constants.LotteryConstant;
import com.cxdai.console.lottery.service.ChanceRuleInfoService;
import com.cxdai.console.lottery.service.GoodService;
import com.cxdai.console.lottery.service.SourceTypeChanceService;
import com.cxdai.console.lottery.vo.ChanceRuleInfo;
import com.cxdai.console.lottery.vo.Good;
import com.cxdai.console.lottery.vo.SourceTypeChance;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.ShiroUtils;
/**
 * 
 * <p>
 * Description:抽奖奖品控制类<br />
 * </p>
 * @title GoodController.java
 * @package com.cxdai.console.lottery.controller 
 * @author yubin
 * @version 0.1 2015年7月6日
 */
@Controller
@RequestMapping(value = "/lottery/good")
public class GoodController extends BaseController {
	
  private final static Logger logger=Logger.getLogger(GoodController.class);
  @Autowired
  private GoodService goodService; 
  @Autowired
  private SourceTypeChanceService sourceTypeChanceService;
  @Autowired
  private ChanceRuleInfoService chanceRuleInfoService;
	
  /**
    * 
    * <p>
    * Description:抽奖奖品管理主界面<br />
    * </p>
    * @author yubin
    * @version 0.1 2015年6月23日
    * @return
    * ModelAndView
    */
    @RequestMapping("/main")
	public ModelAndView main() {
	 
		return new ModelAndView("/lottery/good/main");
	}
    /**
     * 
     * <p>
     * Description:添加奖品<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年7月6日
     * @return
     * ModelAndView
     */
    @RequestMapping("/add")
	public ModelAndView add(@RequestParam(value = "id", required = false) Integer goodId) {
    	BigDecimal sumChance=null;
    	Good good=null;
    	List<SourceTypeChance>sourceTypeChances=null; 
     
    	if (goodId != null && goodId != 0) {
			sumChance = goodService.getSumChance(goodId);
			good = goodService.getGoodById(goodId);

			if (good.getAwardMoney() != null) {
				good.setAwardMoney(good.getAwardMoney().setScale(2));
			}

			if (good.getAwardType() == 4) {
				good.setName(good.getChirldNameStr());
			}

			sourceTypeChances = sourceTypeChanceService.querySourceTypeChanceByGoodId(goodId);
        
		} else {
			good = new Good();
			good.setId(goodId);
			sumChance = goodService.getSumChance(null);
			 
		}
		return new ModelAndView("/lottery/good/layer").addObject("sumChance", sumChance).addObject("good",good)
				                                      .addObject("sourceTypeChances",sourceTypeChances);
	}
    /**
     * 
     * <p>
     * Description:保存奖品<br />
     * </p>
     * @author yubin
     * @version 0.1 2015年7月6日
     * @param goodId
     * @return
     * MessageBox
     */
    @RequestMapping("/saveGood")
    @ResponseBody
    public  MessageBox saveGood(@ModelAttribute Good good) {
    	String message=null;
    	try {

			if (good.getId() == 0) {
				good.setId(null);
			}

			if (good.getAwardType() != 3) {
				if (StringUtils.isEmpty(good.getName())) {
					message = "奖品名不能为空";
					return  MessageBox.build("0", message);
				}
			}

			// 添加 奖品名称
			if (good.getAwardType() == 1 && good.getId() == null) {
				good.setName(good.getName().concat("元现金"));
			}
			// 添加 红包奖品名称  红包系统  liutao 20151103
			if (good.getAwardType() == 5 && good.getId() == null) {
				good.setName(good.getName().concat("元红包"));
			}
			if (good.getAwardType() == 4) {
				validataChance(good.getChance().toString());
				// 抽奖机会
				if (StringUtils.isEmpty(good.getChirldChanceStr())) {
					message = "奖品子概率不能为空";
					return  MessageBox.build("0", message);
				}
				String chirldChanceStr = good.getChirldChanceStr();
				String name = good.getName();
				String[] chirldChance = chirldChanceStr.split(LotteryConstant.FENHAO);
				String[] names = name.split(LotteryConstant.FENHAO);
				if (chirldChance.length != names.length) {
					message = "奖品子概率和奖品名称不一致";
				    return  MessageBox.build("0", message);
				}

				Double result = new Double(0);
				for (int i = 0; i < chirldChance.length; i++) {
					String chance = chirldChance[i];
					validataChance(chance);
					result += Double.parseDouble(chance);
				}

				if (result != 100) {
					message = "子概率总和确保为100%";
					return  MessageBox.build("0", message);
				}

				setCommonInfo(good);
				goodService.save(good);
			} else {
				validataChance(good.getChance().toString());
				setCommonInfo(good);
				goodService.save(good);
			}
			message = "操作成功";
			return  MessageBox.build("1", message);
		} catch (NumberFormatException e) {
			stackTraceError(logger, e);
			message = "操作出错";
			return  MessageBox.build("0", message);
		} catch (RuntimeException e) {
			message = e.getMessage();
			e.printStackTrace();
			return  MessageBox.build("0", message);
		} catch (Exception e) {
			stackTraceError(logger, e);
			e.printStackTrace();
			message = "操作出错";
			return  MessageBox.build("0", message);
		}
    	
    }
    
   /**
    *  
    * <p>
    * Description:抽奖奖品管理列表<br />
    * </p>
    * @author Administrator
    * @version 0.1 2015年6月24日
    * @param rechargeRecordCnd
    * @param pageNo
    * @return
    * ModelAndVie
    */
   @RequestMapping("/list/{pageNo}")
   public ModelAndView searchGoodList( @PathVariable Integer pageNo) {
   	Page page = null;
    
   	try {
   		page = goodService.queryPageGoodList(pageNo, Constants.CONSOLE_PAGE_SIZE);
   	} catch (Exception e) {
   	 
   		logger.error(e);
   	}
   	return new ModelAndView("/lottery/good/list").addObject("page", page);
   }
   /**
    * 
    * <p>
    * Description:新增或修改白名单<br />
    * </p>
    * @author yubin
    * @version 0.1 2015年6月29日
    * @param borrowId
    * @return
    * MessageBox
    */
   @RequestMapping("/delGood")
   @ResponseBody
   public  MessageBox delGood(@RequestParam(value = "id", required = false) Integer goodId) {
   	String message=null;
   	try {
   		goodService.deleteGood(goodId);
        message="删除成功";
        return MessageBox.build("0", message);
  
   	} catch (Exception e) {
   		stackTraceError(logger, e);
   		message = "还款失败，请刷新页面后重试！";
   		return  MessageBox.build("1", message);
   	}
   }
   /**
    * 
    * <p>
    * Description:进入指定活动中奖概率<br />
    * </p>
    * @author yubin
    * @version 0.1 2015年7月7日
    * @param goodId
    * @return
    * ModelAndView
    */
   @RequestMapping("/addActiveChance")
	public ModelAndView addActiveChance(@RequestParam(value = "id", required = false) Integer goodId) {
   	
   	BigDecimal sourceChanceLimit=null;
   	List<SelectItem> chanceRuleInfosInsert = new ArrayList<SelectItem>();
    
   	List<ChanceRuleInfo> list = chanceRuleInfoService.queryAllChanceRuleInfos();
		for (ChanceRuleInfo bankVo : list) {
			chanceRuleInfosInsert.add(new SelectItem(bankVo.getId(), bankVo.getName()));
		}

		sourceChanceLimit = goodService.getGoodChangeById(goodId);
		
   return new ModelAndView("/lottery/good/addActive").addObject("sourceChanceLimit", sourceChanceLimit)
				                                     .addObject("chanceRuleInfosInsert",chanceRuleInfosInsert)
				                                     .addObject("goodId", goodId);
				                                    
	}
   /**
    * 
    * <p>
    * Description:删除指定活动中奖概率<br />
    * </p>
    * @author yubin
    * @version 0.1 2015年6月29日
    * @param borrowId
    * @return
    * MessageBox
    */
   @RequestMapping("/deleteGoodChance")
   @ResponseBody
   public  MessageBox deleteGoodChance(@RequestParam(value = "id", required = false) Integer goodId) {
   	String message=null;
   	try {
   		sourceTypeChanceService.deleteSourceTypeChance(goodId);
        message="删除成功";
        return MessageBox.build("0", message);
  
   	} catch (Exception e) {
   		stackTraceError(logger, e);
   		message = "删除失败！";
   		return  MessageBox.build("1", message);
   	}
   }
   /**
    * 
    * <p>
    * Description:新增指定活动中奖概率<br />
    * </p>
    * @author yubin
    * @version 0.1 2015年7月7日
    * @param sourceTypeChance
    * @return
    * MessageBox
    */
   @RequestMapping("/saveGoodChance")
   @ResponseBody
   public  MessageBox saveGoodChance(@ModelAttribute SourceTypeChance sourceTypeChance) {
   	String message=null;
   	try {
			validataChance(sourceTypeChance.getChance().toString());

			String ip = HttpTookit.getRealIpAddr(currentRequest());
			ShiroUser shiroUser=ShiroUtils.currentUser();
			Integer userId = shiroUser.getUserId();

			if (sourceTypeChance.getId() == null) {
				sourceTypeChance.setAddIp(ip);
				sourceTypeChance.setAddUserId(userId);
				sourceTypeChance.setAddTime(new Date());
			} else {
				sourceTypeChance.setLastUpdateIp(ip);
				sourceTypeChance.setLastUpdateUserId(userId);
				sourceTypeChance.setLastUpdateTime(new Date());
			}

			sourceTypeChanceService.saveSourceTypeChance(sourceTypeChance);
			message= "操作成功";
			return  MessageBox.build("1", message);
		} catch(RuntimeException e){
			return  MessageBox.build("0", e.getMessage());
		 }catch (Exception e) {
			e.printStackTrace();
			message= "操作出错";
			return  MessageBox.build("0", message);
		} 
   }
   private void setCommonInfo(Good good) {
		String ip = HttpTookit.getRealIpAddr(currentRequest());
		ShiroUser shiroUtils=ShiroUtils.currentUser();
		 
		Integer userId = shiroUtils.getUserId();

		if (good.getId() == null) {
			good.setAddIp(ip);
			good.setAddUserId(userId);
			good.setAddTime(new Date());
		} else {
			good.setLastUpdateIp(ip);
			good.setLastUpdateUserId(userId);
			good.setLastUpdateTime(new Date());
		}
	}

	public void validataChance(String chance) {

		String chanceRep = "^[0-9]{1,3}[.]?[0-9]?[0-9]?$";

		if (StringUtils.isEmpty(chance)) {
			throw new RuntimeException("概率不能为空");
		}

		if (!chance.matches(chanceRep)) {
			throw new RuntimeException(chance + "--概率不能大于100且保留两位位小数");
		}

	}
}
