package cn.com.taiji.css.model.customerservice.finance;

import cn.com.taiji.common.model.BaseModel;

public class BalanceReckonModel extends BaseModel{
    private Long accountBalance;
    private Long trafficBalance;
    private Long chargeBalance;
    private Long reckonBalnace;
    
	public Long getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Long accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Long getTrafficBalance() {
		return trafficBalance;
	}
	public void setTrafficBalance(Long trafficBalance) {
		this.trafficBalance = trafficBalance;
	}
	public Long getChargeBalance() {
		return chargeBalance;
	}
	public void setChargeBalance(Long chargeBalance) {
		this.chargeBalance = chargeBalance;
	}
	public Long getReckonBalnace() {
		return reckonBalnace;
	}
	public void setReckonBalnace(Long reckonBalnace) {
		this.reckonBalnace = reckonBalnace;
	}
    

}
