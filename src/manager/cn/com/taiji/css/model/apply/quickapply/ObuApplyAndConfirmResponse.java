package cn.com.taiji.css.model.apply.quickapply;

import cn.com.taiji.css.model.appajax.AppAjaxResponse;

public class ObuApplyAndConfirmResponse extends AppAjaxResponse {
	private String plateNum;
	private Integer plateColor;
	private Integer vehicleType;
	private Integer userType;
	private Integer vehicleLength;
	private Integer vehicleWidth;
	private Integer vehicleHeight; 
	private Integer wheelsCount;
	private Integer axleCount;
	private Integer wheelBase;
	private Integer loadOrSeat;
	private String vehicleFeature; 
	private String engineNum;
	private String obuId;
	private long refund;
	
	public String getPlateNum() {
		return plateNum;
	}
	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}
	public Integer getPlateColor() {
		return plateColor;
	}
	public void setPlateColor(Integer plateColor) {
		this.plateColor = plateColor;
	}
	public Integer getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(Integer vehicleType) {
		this.vehicleType = vehicleType;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getVehicleLength() {
		return vehicleLength;
	}
	public void setVehicleLength(Integer vehicleLength) {
		this.vehicleLength = vehicleLength;
	}
	public Integer getVehicleWidth() {
		return vehicleWidth;
	}
	public void setVehicleWidth(Integer vehicleWidth) {
		this.vehicleWidth = vehicleWidth;
	}
	public Integer getVehicleHeight() {
		return vehicleHeight;
	}
	public void setVehicleHeight(Integer vehicleHeight) {
		this.vehicleHeight = vehicleHeight;
	}
	public Integer getWheelsCount() {
		return wheelsCount;
	}
	public void setWheelsCount(Integer wheelsCount) {
		this.wheelsCount = wheelsCount;
	}
	public Integer getAxleCount() {
		return axleCount;
	}
	public void setAxleCount(Integer axleCount) {
		this.axleCount = axleCount;
	}
	public Integer getWheelBase() {
		return wheelBase;
	}
	public void setWheelBase(Integer wheelBase) {
		this.wheelBase = wheelBase;
	}
	public Integer getLoadOrSeat() {
		return loadOrSeat;
	}
	public void setLoadOrSeat(Integer loadOrSeat) {
		this.loadOrSeat = loadOrSeat;
	}
	public String getVehicleFeature() {
		return vehicleFeature;
	}
	public void setVehicleFeature(String vehicleFeature) {
		this.vehicleFeature = vehicleFeature;
	}
	public String getEngineNum() {
		return engineNum;
	}
	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}
	public String getObuId() {
		return obuId;
	}
	public void setObuId(String obuId) {
		this.obuId = obuId;
	}
	public long getRefund() {
		return refund;
	}
	public void setRefund(long refund) {
		this.refund = refund;
	}
	
	
}
