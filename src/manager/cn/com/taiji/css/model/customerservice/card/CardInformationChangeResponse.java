
package cn.com.taiji.css.model.customerservice.card;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.CardInfo;


public class CardInformationChangeResponse extends JpaDateTimePageableDataRequest<CardInfo> {
	private Integer vehiclePlateColor;
	private String cardId;
	private String vehiclePlate;
	private String customerName;
	private String customerIdNum;
	private String expireTime;
	private String message;
	private String command;
	private String cosRecordId;
	private String cosResponse;
	private int status;
	private Integer orderStatus;
	private Integer cardInfoChangeStatus;
	
	public Integer getVehiclePlateColor() {
		return vehiclePlateColor;
	}



	public void setVehiclePlateColor(Integer vehiclePlateColor) {
		this.vehiclePlateColor = vehiclePlateColor;
	}



	public String getCardId() {
		return cardId;
	}



	public void setCardId(String cardId) {
		this.cardId = cardId;
	}



	public String getVehiclePlate() {
		return vehiclePlate;
	}



	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public String getCustomerIdNum() {
		return customerIdNum;
	}



	public void setCustomerIdNum(String customerIdNum) {
		this.customerIdNum = customerIdNum;
	}


	

	public String getExpireTime() {
		return expireTime;
	}



	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	


	public String getCommand() {
		return command;
	}



	public void setCommand(String command) {
		this.command = command;
	}


	public String getCosRecordId() {
		return cosRecordId;
	}



	public void setCosRecordId(String cosRecordId) {
		this.cosRecordId = cosRecordId;
	}



	public String getCosResponse() {
		return cosResponse;
	}



	public void setCosResponse(String cosResponse) {
		this.cosResponse = cosResponse;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}


	

	public Integer getOrderStatus() {
		return orderStatus;
	}



	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}


	
	
	/**
	 * @return cardInfoChangeStatus
	 */
	public Integer getCardInfoChangeStatus() {
		return cardInfoChangeStatus;
	}



	/**
	 * @param cardInfoChangeStatus 要设置的 cardInfoChangeStatus
	 */
	public void setCardInfoChangeStatus(Integer cardInfoChangeStatus) {
		this.cardInfoChangeStatus = cardInfoChangeStatus;
	}



	@Override
	public HqlBuilder toSelectHql() {
		
		return null;
	}
}

