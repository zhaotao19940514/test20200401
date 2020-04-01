package cn.com.taiji.css.model.apply.quickapply;

import cn.com.taiji.common.model.BaseModel;

public class CardRechargeRequest extends BaseModel{
	private String cardId;
	private Integer preBalance;
	private Integer balance;
	private Integer postBalance;
	private String chargeTime;
//    private String pageSize;
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public Integer getPreBalance() {
		return preBalance;
	}
	public void setPreBalance(Integer preBalance) {
		this.preBalance = preBalance;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Integer getPostBalance() {
		return postBalance;
	}
	public void setPostBalance(Integer postBalance) {
		this.postBalance = postBalance;
	}
	public String getChargeTime() {
		return chargeTime;
	}
	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}

}
