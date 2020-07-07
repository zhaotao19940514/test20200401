package cn.com.taiji.css.model.customerservice.report;

import cn.com.taiji.common.model.BaseModel;

public class ChannelTradeRequest extends BaseModel {
	private String startDate;			//统计开始日期
	private String endDate;				//统计结束日期
	private String regionName;
	private Long balance;
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
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	
	
	
	
	
	
	
	
}
