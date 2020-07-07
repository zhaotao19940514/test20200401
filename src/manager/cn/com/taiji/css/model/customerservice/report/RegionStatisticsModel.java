package cn.com.taiji.css.model.customerservice.report;

import cn.com.taiji.common.model.BaseModel;

public class RegionStatisticsModel extends BaseModel {
	private String startDate;			//统计开始日期
	private String endDate;				//统计结束日期
	private String serviceHallName;		//网点名称
	private String regionId;			
	private String regionName;			//地区名称
	private Double refund;				//退款金额
	private Long amount;				//圈存金额
	private Long btAount;			//半条金额
	private Long accountAmount;		    //账户充值金额
	private Long correctAmount;//账户冲正
	private Double payAmount;//补缴金额
	private Double sum;
	private String cardId;
	private String handleDate;
	private String staffId;
	private Long handleMoney;
	private Integer type;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String afterDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getServiceHallName() {
		return serviceHallName;
	}
	public void setServiceHallName(String serviceHallName) {
		this.serviceHallName = serviceHallName;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public Double getRefund() {
		return refund;
	}
	public void setRefund(Double refund) {
		this.refund = refund;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getAccountAmount() {
		return accountAmount;
	}
	public void setAccountAmount(Long accountAmount) {
		this.accountAmount = accountAmount;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
	public Double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}
	public Long getCorrectAmount() {
		return correctAmount;
	}
	public void setCorrectAmount(Long correctAmount) {
		this.correctAmount = correctAmount;
	}
	public Long getBtAount() {
		return btAount;
	}
	public void setBtAount(Long btAount) {
		this.btAount = btAount;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getHandleDate() {
		return handleDate;
	}
	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public Long getHandleMoney() {
		return handleMoney;
	}
	public void setHandleMoney(Long handleMoney) {
		this.handleMoney = handleMoney;
	}
	public String getEndDate() {
		return endDate;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
	
	
	
	
	
}
