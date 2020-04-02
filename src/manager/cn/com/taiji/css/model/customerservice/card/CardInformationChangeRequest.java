
package cn.com.taiji.css.model.customerservice.card;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.VehicleInfo;


public class CardInformationChangeRequest extends JpaDateTimePageableDataRequest<CardInfo> {
	private Integer vehiclePlateColor;
	private String cardId;
	private String vehiclePlate;
	private String customerName;
	private String customerIdNum;
	protected int orderStatus;
	private String command;
	private Integer applyStep;
	private String cosRecordId;
	private String cosResponse;
	private String enableTime;
	private String expireTime;
	private CustomerInfo customer;
	private VehicleInfo vehicle;
	private Integer status;


	/**
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}



	/**
	 * @param status 要设置的 status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}



	public Integer getVehiclePlateColor() {
		return vehiclePlateColor;
	}



	public void setVehiclePlateColor(Integer vehiclePlateColor) {
		this.vehiclePlateColor = vehiclePlateColor;
	}



	public int getOrderStatus() {
		return orderStatus;
	}



	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
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



	public Integer getApplyStep() {
		return applyStep;
	}



	public void setApplyStep(Integer applyStep) {
		this.applyStep = applyStep;
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



	/**
	 * @return enableTime
	 */
	public String getEnableTime() {
		return enableTime;
	}



	/**
	 * @param enableTime 要设置的 enableTime
	 */
	public void setEnableTime(String enableTime) {
		this.enableTime = enableTime;
	}



	/**
	 * @return customer
	 */
	public CustomerInfo getCustomer() {
		return customer;
	}



	/**
	 * @param customer 要设置的 customer
	 */
	public void setCustomer(CustomerInfo customer) {
		this.customer = customer;
	}



	/**
	 * @return vehicle
	 */
	public VehicleInfo getVehicle() {
		return vehicle;
	}



	/**
	 * @param vehicle 要设置的 vehicle
	 */
	public void setVehicle(VehicleInfo vehicle) {
		this.vehicle = vehicle;
	}



	@Override
	public HqlBuilder toSelectHql() {
		
		return null;
	}
}

