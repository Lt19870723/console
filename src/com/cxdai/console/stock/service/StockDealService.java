package com.cxdai.console.stock.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.stock.entity.StockDeal;
import com.cxdai.console.stock.mapper.StockDealMapper;
import com.cxdai.console.stock.vo.StockDealRequest;

@Service
public class StockDealService {

	@Autowired
	private StockDealMapper stockDealMapper;
	
	/**
	 * 
	 * <p>
	 * Description:查询XX委托单成交记录<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-5-9
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * Page
	 */
	public List<StockDeal> queryDealList(String id){
		StockDealRequest request = new StockDealRequest();
		request.setEntrustId(Integer.parseInt(id));
		int  totalCount = stockDealMapper.queryListCount(request);
		Page page = new Page();
		page.setTotalCount(totalCount);
		page.setPageSize(totalCount);
		return stockDealMapper.queryList(request, page);
	}
	
	/**
	 * 
	 * <p>
	 * Description:根据主键获取成交记录实体<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-5-10
	 * @param id
	 * @return
	 * StockDeal
	 */
	public StockDeal queryDealById(String id){
		return stockDealMapper.selectByPrimaryKey(Integer.parseInt(id));
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:分页查询成交记录列表<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-5-10
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * Page
	 */
	public Page queryList(StockDealRequest request,int pageNo, int pageSize){
		int  totalCount = stockDealMapper.queryListCount(request);
		Page page = new Page(pageNo, pageSize);
		page.setTotalCount(totalCount);
		List<StockDeal> list =stockDealMapper.queryList(request, page);
		page.setResult(list);
		return page;
	}
	
	/**
	 * 
	 * <p>
	 * Description:股权交易数据汇总<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-5-11
	 * @param request
	 * @return
	 * Map<String,Object>
	 */
	public  Map<String,Object> querySummaryByDate(StockDealRequest request){
		return this.createSummary(request);
	}
	
	
	private Map<String,Object> createSummary(StockDealRequest request){
		List<Map<String,Object>> map1 = stockDealMapper.querySummary1(request);
		List<Map<String,Object>> map2 = stockDealMapper.querySummary2(request);
		List<Map<String,Object>> map3 = stockDealMapper.querySummary3(request);
		Map<String,Object> map = new HashMap<String,Object>();
		for(int i=0; i< map1.size();i++){
				  if("1".equals(map1.get(i).get("type")+"")){
					  map.put("buyerEntrustTotal", map1.get(i).get("entrustTotal"));
					  map.put("buyerPrice", map1.get(i).get("price"));
					  map.put("buyerDealPercentage", map1.get(i).get("dealPercentage"));
				  }
				  if("2".equals(map1.get(i).get("type")+"")){
					  map.put("sellerEntrustTotal", map1.get(i).get("entrustTotal"));
					  map.put("sellerPrice", map1.get(i).get("price"));
					  map.put("sellerDealPercentage", map1.get(i).get("dealPercentage"));
				  }
		}
		
			for(int i=0; i< map2.size();i++){
			  if("1".equals(map2.get(i).get("type")+"")){
				  map.put("sellerDealAmount", map2.get(i).get("dealAmount"));
			  }
			  if("2".equals(map2.get(i).get("type")+"")){
				  map.put("buyerDealAmount", map2.get(i).get("dealAmount"));
			  }
			}
 			if(map3.size()>0){
				map.putAll(map3.get(0));
			}
			return map;
	}
}
