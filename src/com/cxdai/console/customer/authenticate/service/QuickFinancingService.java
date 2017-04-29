package com.cxdai.console.customer.authenticate.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.customer.authenticate.entity.QuickFinancing;
import com.cxdai.console.customer.authenticate.mapper.QuickFinancingMapper;
import com.cxdai.console.customer.authenticate.vo.QuickFinancingCnd;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.ShiroUtils;
/**
 * 
 * <p>
 * Description:快速融资业务<br />
 * </p>
 * @title QuickFinancingService.java
 * @package com.cxdai.console.customer.authenticate.service 
 * @author yubin
 * @version 0.1 2015年9月9日
 */
@Service
public class QuickFinancingService {
	
  @Autowired
  private QuickFinancingMapper quickFinancingMapper;
  
  private static final Logger logger=Logger.getLogger(QuickFinancingService.class);
  /**
   * 
   * <p>
   * Description:遍历集合<br />
   * </p>
   * @author yubin
   * @version 0.1 2015年9月9日
   * @param cnd
   * @param curPage
   * @param pageSize
   * @return
   * Page
   */
  public Page getFinancing(QuickFinancingCnd cnd, int curPage, int pageSize) {
		Page page = new Page(curPage, pageSize);

		int totalCount;

		List<QuickFinancing> list;
		try {
			totalCount=quickFinancingMapper.countQuickFianFinancing(cnd);
			page.setTotalCount(totalCount);
			list=quickFinancingMapper.queryQuickFianFinancing(cnd, page);
			
			page.setResult(list);
			
		} catch (Exception e) {
			 logger.error("快速融资业务", e);
		}

		return page;
	}
  /**
   * 
   * <p>
   * Description:审核通过<br />
   * </p>
   * @author yubin
   * @version 0.1 2015年9月9日
   * @param quickFinancing
   * @return
   * String
   */
  @Transactional(rollbackFor = Throwable.class)
  public String saveFinancingCheck(Integer id,Integer stauts,String remark){
	  
      QuickFinancing quickFinancing=null;
	  
	  quickFinancing=quickFinancingMapper.selectByPrimaryKey(id);
	  
	  if(quickFinancing==null){ 
		  return "fail";
	  }	  
	  ShiroUser user=ShiroUtils.currentUser();

	  quickFinancing.setApproveStatus(stauts);
	  
	  quickFinancing.setApproveRemark(remark);
	  
	  quickFinancing.setApproveTime(DateUtils.getTime());
	  
	  quickFinancing.setApproveUser(user.getUserId());
	 
	  if(quickFinancingMapper.updateByPrimaryKeySelective(quickFinancing)>0){
		  
		  return BusinessConstants.SUCCESS;
	  };
	  
	  return "fail";
  }
  public QuickFinancing findById(Integer id){
	  
	  QuickFinancing quickFinancing=null;
	  
	  quickFinancing=quickFinancingMapper.selectFinance(id);
	  
	  return quickFinancing;
  }
}
