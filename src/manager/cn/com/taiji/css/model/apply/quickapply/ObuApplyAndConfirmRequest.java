package cn.com.taiji.css.model.apply.quickapply;

import cn.com.taiji.common.model.BaseModel;

public class ObuApplyAndConfirmRequest extends BaseModel {
	//操作请求类型type  1-申请  2-确认
	private Integer type;
	//OBU编号
	private String obuId;
	//客户编号
	private String customerId;
	//车辆编号
	private String vehicleId;
	//网络编号
	private String netId;
	//OBU品牌
	private Integer brand;
	//OBU型号
	private String model;
	//生效日期
	private String enableTime;
	//失效日期
	private String expireTime;
	//true:obu更换   false:obu发行
	private boolean applyOrChange;
	//true:obu过户 false:obu发行
	private boolean applyOrTransfer;
	//
	private String oldObuId;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getObuId() {
		return obuId;
	}
	public void setObuId(String obuId) {
		this.obuId = obuId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getNetId() {
		return netId;
	}
	public void setNetId(String netId) {
		this.netId = netId;
	}
	
	public Integer getBrand() {
		return brand;
	}
	public void setBrand(Integer brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getEnableTime() {
		return enableTime;
	}
	public void setEnableTime(String enableTime) {
		this.enableTime = enableTime;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public boolean isApplyOrChange() {
		return applyOrChange;
	}
	public void setApplyOrChange(boolean applyOrChange) {
		this.applyOrChange = applyOrChange;
	}
	public String getOldObuId() {
		return oldObuId;
	}
	public void setOldObuId(String oldObuId) {
		this.oldObuId = oldObuId;
	}
	public boolean isApplyOrTransfer() {
		return applyOrTransfer;
	}
	public void setApplyOrTransfer(boolean applyOrTransfer) {
		this.applyOrTransfer = applyOrTransfer;
	}
	
	
}
