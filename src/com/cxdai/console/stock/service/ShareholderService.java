package com.cxdai.console.stock.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.stock.entity.ShareholderRoster;
import com.cxdai.console.stock.entity.ShareholderRosterlog;
import com.cxdai.console.stock.mapper.ApplyInfoMapper;
import com.cxdai.console.stock.mapper.ShareholderRosterMapper;
import com.cxdai.console.stock.mapper.ShareholderRosterlogMapper;
import com.cxdai.console.stock.mapper.StockAccountMapper;
import com.cxdai.console.stock.vo.ShareholderCnd;
import com.cxdai.console.util.StringUtils;

/****
 * 
 * <p>
 * Description:股东花名册接口实现类<br />
 * </p>
 * @title ShareholderService.java
 * @package com.cxdai.console.stock.service 
 * @author xinwugang
 * @version 0.1 2016-5-9
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class ShareholderService {

	@Autowired
	private ShareholderRosterMapper shareholderRosterMapper;
	@Autowired
	private StockAccountMapper stockAccountMapper;
	@Autowired
	private ShareholderRosterlogMapper shareholderlogMapper;
	
	@Autowired
	private ApplyInfoMapper applyInfoMapper;
	
	
	/****
	 * 股东花名册列表查询
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
	public Page queryApplyForPage(ShareholderCnd shareholderCnd,int pageSize, int curPage)throws Exception{
		Page page = new Page(curPage, pageSize);
		int totalCount = shareholderRosterMapper.queryShareholderForCounts(shareholderCnd);
		page.setTotalCount(totalCount);
		List<ShareholderRoster> shareholderList = shareholderRosterMapper.queryShareholderForPage(shareholderCnd, page);
		page.setResult(shareholderList);
		return page;
	}
	
	/***
	 * 查询可用股权数量大于等于50000股的用户信息
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-17
	 * @return
	 * List<ShareholderRoster>
	 */
	public List<ShareholderRoster> selectShareholderList(){
		List<ShareholderRoster> shareList = stockAccountMapper.selectShareholderList();
		return shareList; 
	}
	
	/****
	 * 需要退出股东名册用户
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-31
	 * @return
	 * List<ShareholderRoster>
	 */
	public List<ShareholderRoster> outShareholdList(){
		List<ShareholderRoster> shareList = stockAccountMapper.outShareholdList();
		return shareList; 
	}
	
	/***
	 * 需加入股东名册
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-31
	 * @return
	 * List<ShareholderRoster>
	 */
	public List<ShareholderRoster> addShareholdList(){
		List<ShareholderRoster> shareList = stockAccountMapper.addShareholdList();
		return shareList; 
	}
	
	/****
	 * 查询股东名册变更记录
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author xinwugang
	 * @version 0.1 2016-5-20
	 * @return
	 * List<ShareholderRosterlog>
	 */
	public List<ShareholderRosterlog> shareholderLogList(@Param("status") int status, @Param("userId") int userId){
		List<ShareholderRosterlog> shareLogList = shareholderlogMapper.shareholderLogList(status, userId);
		return shareLogList;
	}
	public ShareholderRoster findShoreInfo(Integer id){
		ShareholderRoster share = shareholderRosterMapper.findShoreInfo(id);
		return share;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:股东花名册读取<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-6-1
	 * @param file
	 * @return
	 * @throws Exception
	 * List<ShareholderRoster>
	 */
	public List<ShareholderRoster> readExcel(File file) throws Exception{
		List<ShareholderRoster> list = new ArrayList<ShareholderRoster>();
		 Workbook workbook = Workbook.getWorkbook(file);
	        Sheet sheet[] = workbook.getSheets();
	        String lab = null;
	        for(int a=0;a<sheet.length;a++){
	        	//去掉标题，从第2行开始取值
	            for(int i=1;i<sheet[a].getRows();i++){
	            	ShareholderRoster record= new ShareholderRoster();
	                for(int j=0;j<sheet[a].getColumns();j++){
	                    lab = sheet[a].getCell(j,i).getContents();
	                    if(j == 0 && StringUtils.isNotEmpty(lab)){
	                    	 record.setUserId(Integer.parseInt(lab));//用户ID
	                    }
	                    if(j == 1 && StringUtils.isNotEmpty(lab)){
	                    	 record.setUserName(lab);
	                    }
	                    if(j == 2 && StringUtils.isNotEmpty(lab)){
	                    	record.setUserRealName(lab);
	                    }
	                    if(j == 3 && StringUtils.isNotEmpty(lab)){
	                    	record.setIdCard(lab);
	                    }
	                    if(j == 4 && StringUtils.isNotEmpty(lab)){
	                    	record.setStockCode(lab);
	                    }
	                    if(j == 5 && StringUtils.isNotEmpty(lab)){
	                    	record.setStockName(lab);
	                    }
	                    if(j == 6 && StringUtils.isNotEmpty(lab)){
	                    	 record.setStockTotal(Integer.parseInt(lab));
	                    }
	                    if(j == 7 && StringUtils.isNotEmpty(lab)){
	                    	record.setShareholdingRatio(new BigDecimal(lab));
	                    }
	                }
	                list.add(record);
	            }
	        }
		return list;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:股东花名册更新<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-6-1
	 * @param list
	 * void
	 */
	@Transactional
	public void updateShareholder(List<ShareholderRoster> list,int userId,String realName, String ip){
		//1获取股东花名册版本
		int version = shareholderRosterMapper.queryVersion();
		//2将此版本股东花名册作废
		List<ShareholderRoster> oldShareholderRoster =  shareholderRosterMapper.queryList(version);
		for(ShareholderRoster old : oldShareholderRoster){
			shareholderRosterMapper.toVoid(old.getId());
			old.setStatus(-1);//作废
			old.setRemark("作废"+old.getStockName()+"第"+version+"版内转花名册");
			old.setAdduser(userId);
			old.setOptUserRealName(realName);
			old.setAddip(ip);
			insertShareholderLog(old,old.getId());
		}
		//3作废股东退出申请
			applyInfoMapper.toVoidApply();
		//4新增股东花名册
			version++;
			for(ShareholderRoster record : list){
				record.setVersion(version);
				record.setStatus(1);
				record.setRemark(record.getStockName()+"第"+version+"版内转花名册");
			  	record.setAdduser(userId);
				record.setOptUserRealName(realName);
				record.setAddip(ip);
				shareholderRosterMapper.insertSelective(record);
				//5插入股东花名册变更日志
				insertShareholderLog(record,record.getId());
			}
	}
	
	/**
	 * 
	 * <p>
	 * Description:产生股东花名册变更日志<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-6-1
	 * @param roster
	 * @param id
	 * void
	 */
	public void insertShareholderLog(ShareholderRoster roster,int id){
		ShareholderRosterlog log = new ShareholderRosterlog();
		log.setRosterId(id);
		log.setUserId(roster.getUserId());
		log.setUserName(roster.getUserName());
		log.setUserRealName(roster.getUserRealName());
		log.setIdCard(roster.getIdCard());
		log.setStockCode(roster.getStockCode());
		log.setStockName(roster.getStockName());
		log.setStockTotal(roster.getStockTotal());
		log.setShareholdingRatio(roster.getShareholdingRatio());
		log.setStatus(roster.getStatus());
		log.setVersion(roster.getVersion());
		log.setRemark(roster.getRemark());
		log.setAdduser(roster.getAdduser());
		log.setOptUserRealName(roster.getOptUserRealName());
		log.setAddip(roster.getAddip());
		shareholderlogMapper.insertSelective(log);
	}
}
