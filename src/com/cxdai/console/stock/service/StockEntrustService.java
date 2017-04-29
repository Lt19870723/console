package com.cxdai.console.stock.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.base.entity.Member;
import com.cxdai.console.base.mapper.BaseMemberMapper;
import com.cxdai.console.common.MessageBox;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.stock.entity.StockAccount;
import com.cxdai.console.stock.entity.StockAccountlog;
import com.cxdai.console.stock.entity.StockEntrust;
import com.cxdai.console.stock.mapper.CapitalAccountMapper;
import com.cxdai.console.stock.mapper.StockAccountMapper;
import com.cxdai.console.stock.mapper.StockAccountlogMapper;
import com.cxdai.console.stock.mapper.StockEntrustMapper;
import com.cxdai.console.stock.vo.AccountLogRequest;
import com.cxdai.console.stock.vo.CapitalAccountCnd;
import com.cxdai.console.stock.vo.StockEntrustCnd;
import com.cxdai.console.stock.vo.StockEntrustRequest;


@Service
public class StockEntrustService {

	@Autowired
	private StockEntrustMapper stockEntrustMapper;
	
	@Autowired
	private  StockEntrustLogService stockEntrustLogService;
	
	@Autowired
	private  CapitalAccountMapper capitalAccountMapper;
	@Autowired
	private  CapitalAccountService capitalAccountService;
	
	@Autowired
	private  StockAccountService stockAccountService;
	
	@Autowired
	private  StockAccountMapper stockAccountMapper;
	
	
	@Autowired
	private StockAccountlogMapper stockAccountLogMapper;
	
	@Autowired
	private BaseMemberMapper baseMemberMapper;
	/**
	 * 
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-5-9
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * Page
	 */
	public Page queryList(StockEntrustRequest request,int pageNo, int pageSize){
		int  totalCount = stockEntrustMapper.queryListCount(request);
		Page page = new Page(pageNo, pageSize);
		page.setTotalCount(totalCount);
		List<StockEntrust> list =stockEntrustMapper.queryList(request, page);
		page.setResult(list);
		return page;
	}
	
	
	public StockEntrust queryById(String id){
		return stockEntrustMapper.selectByPrimaryKey(Integer.parseInt(id));
	}
	
	@Transactional
	public MessageBox saveRevokeEntrust(StockEntrustCnd entrustCnd,int optId, String realName) throws Exception{
		
		Member member = baseMemberMapper.queryById(optId);
		//1:获取锁定委托单
		entrustCnd.setIsLocked(1);//是否锁定
		StockEntrust entrust = stockEntrustMapper.selectByPrimaryCnd(entrustCnd);
		//2:验证委托单信息
		if(entrust.getStatus() != 1 && entrust.getStatus() != 2){
			return new MessageBox("30000", "委托单状态错误！");
		}
		if(entrust.getResidueAmount() <= 0){
			return new MessageBox("30000", "剩余认购量错误！");
		}
		if(entrust.getEntrustType() == 1){
			//认购撤单
			return this.updateBuyerEntrust(entrust,entrustCnd.getUpdateIp(),entrustCnd.getUserId(),optId,realName,member.getUsername());
		}else{
			//转让撤单
			return this.updateSellerEntrust(entrust,entrustCnd.getUpdateIp(),entrustCnd.getUserId(),optId,realName,member.getUsername());
		}
	}
	
	private MessageBox updateSellerEntrust(StockEntrust entrust, String ip,int userId,int optId, String realName,String userName ){
		//3:修改委托单
		StockEntrust updateEntrust = new StockEntrust();
			updateEntrust.setStatus(-1);//撤销
			updateEntrust.setUpdateip(ip);
			updateEntrust.setUserId(userId);
			updateEntrust.setId(entrust.getId());
			updateEntrust.setRemark("已撤单");
			stockEntrustMapper.updateByPrimaryKeySelective(updateEntrust);
		//4：产生委托单变更日志
			entrust.setStatus(-1);//撤单
			entrust.setUpdateip(ip);
			entrust.setRemark("已撤单");
			stockEntrustLogService.saveStockEntrustLog(entrust,optId,realName,userName);
		//5:修改股权账户信息
			StockAccount stockAccount = stockAccountService.selectAccountForUpdate(userId,"forUpdate");
			//未成交量
			int residueAmount = entrust.getResidueAmount();
			stockAccount.setUseStock(stockAccount.getUseStock()+residueAmount);
			stockAccount.setNoUseStock(stockAccount.getNoUseStock()-residueAmount);
			stockAccountMapper.updateByPrimaryKeySelective(stockAccount);
		//6：产生股权账户日志
			StockAccountlog stockAccountLog = new StockAccountlog();
			stockAccountLog.setOptStock(residueAmount);//操作股权数量
			stockAccountLog.setOptType(-1);//1、认购；2、转让；3、开户；-1、撤销
			stockAccountLog.setTargetId(stockAccount.getId());
			stockAccountLog.setTargetName("gq_stock_account");
			stockAccountLog.setTargetType(9);//1、主动认购；2、被动认购；3、开户；4、主动转让；5、被动转让；6、委托转让；7、委托认购；8、撤销认购；9、撤销转让
			stockAccountLog.setRemark("后台代操作股权委托撤销");
			stockAccountLog.setAddip(ip);
			stockAccountLog.setToUser(userId);
			this.saveStockAccountlog(stockAccount, stockAccountLog);
			
			return new MessageBox("00000","");
	}
	
	
	
	private MessageBox updateBuyerEntrust(StockEntrust entrust, String ip,int userId ,int optId, String realName,String userName ) throws Exception{
		//3:修改委托单
		StockEntrust updateEntrust = new StockEntrust();
			updateEntrust.setStatus(-1);//撤销
			updateEntrust.setUpdateip(ip);
			updateEntrust.setUserId(userId);
			updateEntrust.setId(entrust.getId());
			updateEntrust.setRemark("已撤单");
			updateEntrust.setUpdateuser(optId);
			stockEntrustMapper.updateByPrimaryKeySelective(updateEntrust);
		//4：产生委托单变更日志
			entrust.setStatus(-1);//撤单
			entrust.setUpdateip(ip);
			entrust.setRemark("已撤单");
			stockEntrustLogService.saveStockEntrustLog(entrust,optId,realName,userName);
		//5:修改资金账户
		//未成交金额 =委托总价（包含服务费） - 已成交总价 - 实际成交服务费 
			BigDecimal noDealMoney  = entrust.getEntrustTotalPrice().subtract(entrust.getDealTotalPrice()).subtract(entrust.getDealFee()); 
			//当前委托单申请时冻结可提金额
			BigDecimal freezeDreawMoney = entrust.getDrawMoney();
			//当前委托单申请时冻结不可提金额
			BigDecimal freezeNoDreawMoney = entrust.getNoDrawMoney();
			if(noDealMoney.compareTo(freezeDreawMoney) <= 0){
				//未成交金额 <= 可提冻结金额  未成交金额全部解冻到可提
				freezeDreawMoney= noDealMoney;
				freezeNoDreawMoney = BigDecimal.ZERO;
			}else{
				//未成交金额 > 可提冻结金额  冻结可提全部解冻
				freezeNoDreawMoney = noDealMoney.subtract(freezeDreawMoney);
			}
			CapitalAccountCnd accountCnd = new CapitalAccountCnd();
			accountCnd.setUserId(userId);
			accountCnd.setYear("yes");
			AccountVo account = capitalAccountMapper.queryCapitalAccount(accountCnd);
			account.setDrawMoney(account.getDrawMoney().add(freezeDreawMoney));
			account.setNoDrawMoney(account.getNoDrawMoney().add(freezeNoDreawMoney));
			account.setUseMoney(account.getUseMoney().add(noDealMoney));
			account.setNoUseMoney(account.getNoUseMoney().subtract(noDealMoney));
			capitalAccountMapper.updateAccount(account);
			
		//6：资金变更日志
			AccountLogRequest accountLogCnd = new AccountLogRequest();
			accountLogCnd.setType("gq_deal_revoke"); //新增类型    股权交易冻结
			accountLogCnd.setMoney(noDealMoney);
			accountLogCnd.setToUser(-1);
			accountLogCnd.setBorrowId(entrust.getId());
			accountLogCnd.setBorrowName("gq_stock_entrust");
			accountLogCnd.setIdType(11);//新增类型   股权认购委托撤单
			accountLogCnd.setRemark("后台代操作股权认购委托撤单");
			accountLogCnd.setAddip(ip);
			accountLogCnd.setToUser(userId);
			capitalAccountService.saveAccountlog(account, accountLogCnd);
			
			return new MessageBox("00000","");
	}
	
	

	public void saveStockAccountlog(StockAccount account,
			StockAccountlog accountLog) {
		accountLog.setUserId(account.getUserId());//股东ID 
		accountLog.setAccountId(account.getId());//股权信息表id'
		accountLog.setTotal(account.getTotal());//股权总数量
		accountLog.setUseStock(account.getUseStock());//可用股权数量
		accountLog.setNoUseStock(account.getNoUseStock());//冻结股权数量
		accountLog.setAddtime(new Date());
		stockAccountLogMapper.insertSelective(accountLog);
	}
}
