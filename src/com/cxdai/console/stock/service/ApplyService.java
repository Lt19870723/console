package com.cxdai.console.stock.service;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.stock.entity.ApplyInfo;
import com.cxdai.console.stock.entity.StockAccount;
import com.cxdai.console.stock.entity.StockApprove;
import com.cxdai.console.stock.mapper.ApplyInfoMapper;
import com.cxdai.console.stock.mapper.StockAccountMapper;
import com.cxdai.console.stock.mapper.StockApproveMapper;
import com.cxdai.console.stock.vo.ApplyInfoCnd;
import com.cxdai.console.util.HttpTookit;
import com.cxdai.console.util.ShiroUtils;

/****
 * 
 * <p>
 * Description:申请审核接口实现类<br />
 * </p>
 * @title ApplyService.java
 * @package com.cxdai.console.stock.service 
 * @author xinwugang
 * @version 0.1 2016-5-9
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class ApplyService {

	@Autowired
	private ApplyInfoMapper applyInfoMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private StockApproveMapper stockApproveMapper;
	@Autowired
	private StockAccountMapper stockAccountMapper;
	
	
	/****
	 * 申请数据列表查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-9
	 * @param applyCnd
	 * @param pageSize
	 * @param curPage
	 * @return
	 * @throws Exception
	 * Page
	 */
	public Page queryApplyForPage(ApplyInfoCnd applyCnd,int pageSize, int curPage)throws Exception{
		Page page = new Page(curPage, pageSize);
		int totalCount = applyInfoMapper.queryApplyForCounts(applyCnd);
		page.setTotalCount(totalCount);
		List<ApplyInfo> applyList = applyInfoMapper.queryApplyForPage(applyCnd, page);
		page.setResult(applyList);
		return page;
	}
	
	/****
	 * 更加客户id查询客户信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-10
	 * @param username
	 * @return
	 * @throws Exception
	 * MemberVo
	 */
	public MemberVo queryMemberVoById(int id) throws Exception{
		MemberVo memberVo = memberMapper.queryMemberById(id);
		return memberVo;
	}
	
	/***
	 * 查询散标待收
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-10
	 * @param userId
	 * @return
	 * BigDecimal
	 */
	public BigDecimal queryCollectionrecord(int userId){
		BigDecimal count = applyInfoMapper.queryCollectionrecord(userId);
		return count;
	}
	/****
	 * 查询定期宝待收
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-10
	 * @param userId
	 * @return
	 * BigDecimal
	 */
	public BigDecimal queryFixCollectionrecord(int userId){
		BigDecimal fixcount = applyInfoMapper.queryFixCollectionrecord(userId);
		return fixcount;
	}
	
	/***
	 * 存管待收
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-6-20
	 * @param userId
	 * @return
	 * BigDecimal
	 */
	public BigDecimal queryCunGuanCollectionrecord(int userId){
		BigDecimal cunguan = applyInfoMapper.queryCunGuanCollectionrecord(userId);
		return cunguan;
	}
	/****
	 * 根据主键id查询审核信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-10
	 * @param id
	 * @return
	 * ApplyInfo
	 */
	public ApplyInfo selectByPrimaryKey(Integer id){
		ApplyInfo applyInfo = applyInfoMapper.selectByPrimaryKey(id);
		return applyInfo;
	}
	
	/****
	 * 修改审核信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-10
	 * @param record
	 * void
	 */
	public void updateByPrimaryKeySelective(ApplyInfo applyInfo){
		applyInfoMapper.updateByPrimaryKeySelective(applyInfo);
	}
	
	/****
	 * 提交审核信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-10
	 * @param applyInfo
	 * @param request
	 * @return
	 * @throws Exception
	 * MessageBox
	 */
	public MessageBox approveApply(ApplyInfo applyInfo,HttpServletRequest request) throws Exception {
		String addip = HttpTookit.getRealIpAddr(request);
		ShiroUser user = ShiroUtils.currentUser();
		StockApprove approve = new StockApprove();
		// 审批信息
		approve.setTargetId(applyInfo.getId());
		approve.setTargetName("gq_apply_info");
		approve.setTargetType(applyInfo.getType());
		approve.setStatus(applyInfo.getStatus());
		approve.setAdduser(user.getUserId());
		approve.setUserName(user.getUserName());
		approve.setUserRealName(user.getUserName());
		approve.setAddip(addip);
		approve.setRemark(applyInfo.getRemark());
		
		applyInfoMapper.updateStatus(applyInfo.getStatus(), applyInfo.getId(), user.getUserId(), addip);
		// 保存审批记录
		stockApproveMapper.insert(approve);
		return MessageBox.build("0", "审核成功");
	}
	
	/***
	 * 查询审核记录信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-10
	 * @param id
	 * @param targetType
	 * @return
	 * List<StockApprove>
	 */
	 public List<StockApprove> selectApproveList(StockApprove record){
		 List<StockApprove> StockApproveList = stockApproveMapper.selectApproveList(record);
		 return StockApproveList;
	 }
	 
	 /***
	  * 查询股东信息
	  * <p>
	  * Description:这里写描述<br />
	  * </p>
	  * @author xinwugang
	  * @version 0.1 2016-5-10
	  * @param userid
	  * @return
	  * StockAccount
	  */
	 public StockAccount selectStockCountByUserId(Integer userid){
		 StockAccount stockAccount = stockAccountMapper.selectByUserId(userid);
		 return stockAccount;
	 }
	 
}
