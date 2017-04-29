package com.cxdai.console.stock.vo;

import com.cxdai.console.common.page.BaseCnd;

@SuppressWarnings("serial")
public class StockEntrustRequest extends BaseCnd{
	
		private Integer entrustType; //委托类型
		private Integer status; //委托状态
		private String startDate; //开始时间
		private String endDate;	//结束时间
		
		public Integer getEntrustType() {
			return entrustType;
		}
		public void setEntrustType(Integer entrustType) {
			this.entrustType = entrustType;
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
		
		

}
