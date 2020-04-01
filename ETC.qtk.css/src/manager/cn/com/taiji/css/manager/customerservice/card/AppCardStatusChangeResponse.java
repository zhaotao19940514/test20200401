package cn.com.taiji.css.manager.customerservice.card;

import cn.com.taiji.css.model.appajax.AppAjaxResponse;

public class AppCardStatusChangeResponse extends AppAjaxResponse {
	private String command;
	private String cosRecordId;
	private Integer orderStatus;
	private String cardId;
	private Long cardBalance;
	private String oldCardId;

	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	
	public String getOldCardId() {
		return oldCardId;
	}
	public void setOldCardId(String oldCardId) {
		this.oldCardId = oldCardId;
	}
	public String getCosRecordId() {
		return cosRecordId;
	}
	public void setCosRecordId(String cosRecordId) {
		this.cosRecordId = cosRecordId;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public Long getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(Long cardBalance) {
		this.cardBalance = cardBalance;
	}
	
	
}
