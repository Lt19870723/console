package com.cxdai.console.stock.vo;

import com.cxdai.console.common.page.BaseCnd;

@SuppressWarnings("serial")
public class StockDealRequest extends BaseCnd{
	
		private Integer entrustId; //委托ID 
		private Integer dealType;//委托类型
		private Integer status; //委托状态
		private String startDate; //开始时间
		private String endDate;	//结束时间
		
		
		public Integer getEntrustId() {
			return entrustId;
		}
		public void setEntrustId(Integer entrustId) {
			this.entrustId = entrustId;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		public Integer getDealType() {
			return dealType;
		}
		public void setDealType(Integer dealType) {
			this.dealType = dealType;
		}
		
		

}
