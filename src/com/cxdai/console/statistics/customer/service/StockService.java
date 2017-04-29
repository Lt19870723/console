package com.cxdai.console.statistics.customer.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.recharge.entity.Account;
import com.cxdai.console.account.recharge.mapper.BaseAccountMapper;
import com.cxdai.console.base.entity.Stock;
import com.cxdai.console.base.mapper.BaseAccountLogMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.statistics.account.entity.AccountLog;
import com.cxdai.console.statistics.account.mapper.AccountNetValueMapper;
import com.cxdai.console.statistics.account.vo.DrawMoneyToNoDrawCnd;
import com.cxdai.console.statistics.customer.mapper.StockMapper;
import com.cxdai.console.statistics.customer.vo.StockCnd;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.DateUtils;

@Service
@Transactional(rollbackFor = Throwable.class)
public class StockService{

	@Autowired
	private StockMapper stockMapper;

	@Autowired
	private BaseAccountMapper accountMapper;

	@Autowired
	private BaseAccountLogMapper accountLogMapper;

	@Autowired
	private AccountNetValueMapper accountNetValueMapper;

	/**
	 * 分页查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2017年9月23日
	 * @param stockCnd
	 * @param page
	 * @return
	 * @throws Exception Page
	 */
	public Page pageQuery(StockCnd stockCnd, Page page) throws Exception {
		int totalCount = stockMapper.pageQueryCount(stockCnd);
		// System.out.println(stockCnd.getIsReduce() +
		// "-----------------------------");
		page.setTotalCount(totalCount);
		List<Stock> list = stockMapper.pageQuery(stockCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 后台现金行权
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2017年9月23日
	 * @param stockId
	 * @param exerciseIp
	 * @return
	 * @throws Exception String
	 */
	public String updateStock(int stockId, String exerciseIp, UserVo userVo) throws Exception {
		Stock stock = stockMapper.getByIdForUpdate(stockId);
		if (stock.getStatus() == 2) {
			return "现金行权失败：您已进行过现金行权。";
		}

		BigDecimal stockMoney = getMoney(stock.getStockNum());
		if (stockMoney.compareTo(new BigDecimal(0)) == 0) {
			return "现金行权失败：未在行权时间内操作。";
		}
		stock.setStockMoney(stockMoney);

		stock.setAdminId(userVo.getId());
		stock.setExerciseTime(new Date().getTime());
		stock.setExerciseIp(exerciseIp);

		stockMapper.updateStockMoney(stock);

		// 更改账户可提，可用，总额
		Account account = accountMapper.queryByUserIdForUpdate(stock.getUserId());
		stockMapper.updateAccount(stockMoney, stock.getUserId());

		// 记录账户金额变动日志
		account = accountMapper.queryById(account.getId());
		AccountLog accountLog = new AccountLog();
		accountLog.setUserId(stock.getUserId());
		accountLog.setAddip(exerciseIp);
		accountLog.setAddtime(new Date().getTime() / 1000 + "");
		accountLog.setMoney(stockMoney);
		accountLog.setTotal(account.getTotal());
		accountLog.setDrawMoney(account.getDrawMoney());
		accountLog.setNoDrawMoney(account.getNoDrawMoney());
		accountLog.setUseMoney(account.getUseMoney());
		accountLog.setNoUseMoney(account.getNoUseMoney());
		accountLog.setCollection(account.getCollection());
		accountLog.setFirstBorrowUseMoney(account.getFirstBorrowUseMoney());
		accountLog.setToUser(-1);
		accountLog.setType("stock_to_money");
		accountLog.setRemark("现金行权操作，原有期权： " + stock.getStockNum() + " 份，现金行权获得金额：" + stockMoney + " 元，现有期权 0 份。");
		accountLogMapper.insertEntity(accountLog);

		// 判断用户的可提金额是否大于净值额度，如果大于，转入受限金额
		saveDrawMoneyToNoDraw(exerciseIp, stock.getUserId());

		return "";
	}

	/**
	 * 获取行权金额
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2014年9月11日
	 * @param money
	 * @return BigDecimal
	 */
	public BigDecimal getMoney(long stockNum) {
		long now = new Date().getTime();
		// now = DateUtils.parse("2017-09-26 23:59:59",
		// "yyyy-MM-dd HH:mm:ss").getTime();

		long begin = DateUtils.parse("2014-09-27", "yyyy-MM-dd").getTime();
		long end = DateUtils.parse("2015-09-27", "yyyy-MM-dd").getTime();

		if (now >= begin && now < end) {
			return new BigDecimal(stockNum);
		}

		begin = DateUtils.parse("2015-09-27", "yyyy-MM-dd").getTime();
		end = DateUtils.parse("2016-09-27", "yyyy-MM-dd").getTime();
		if (now >= begin && now < end) {
			return new BigDecimal(stockNum * 2);
		}

		begin = DateUtils.parse("2016-09-27", "yyyy-MM-dd").getTime();
		end = DateUtils.parse("2017-09-27", "yyyy-MM-dd").getTime();
		if (now >= begin && now < end) {
			return new BigDecimal(stockNum * 3);
		}
		return new BigDecimal(0);
	}

	/**
	 * <p>
	 * Description:判断用户的可提金额是否大于净值额度，如果大于，转入受限金额<br />
	 * </p>
	 * @author justin.xu
	 * @version 0.1 2014年10月25日
	 * @param exerciseIp
	 * @param userId
	 * @throws Exception void
	 */
	private void saveDrawMoneyToNoDraw(String exerciseIp, int userId) throws Exception {
		DrawMoneyToNoDrawCnd drawMoneyToNoDrawCnd = new DrawMoneyToNoDrawCnd();
		drawMoneyToNoDrawCnd.setUserid(userId);
		drawMoneyToNoDrawCnd.setNetmoneytype(BusinessConstants.NET_TYPE_STOCK_TO_MONEY);
		drawMoneyToNoDrawCnd.setAddip(exerciseIp);
		drawMoneyToNoDrawCnd.setAccountlogType("net_draw_to_nodraw_first_stock_money");
		drawMoneyToNoDrawCnd.setAccountlogRemark("现金行权之后,可提金额大于净值额度，可提金额转入受限金额。");
		accountNetValueMapper.dealDrawmoneyToNodraw(drawMoneyToNoDrawCnd);
	}
}
