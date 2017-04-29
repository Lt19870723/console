package com.cxdai.console.stock.vo;

public class StockAccountRequest {
		
		private Integer id;//主键
		private Integer userId;//用户主键
		private Integer isForUpdate;//不为空时查询锁定账户
		
		
		public Integer getIsForUpdate() {
			return isForUpdate;
		}
		public void setIsForUpdate(Integer isForUpdate) {
			this.isForUpdate = isForUpdate;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		
		
	
}
