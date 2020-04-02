package cn.com.taiji.css.model.apply.quickapply;

import cn.com.taiji.common.model.BaseModel;

public class SaveIssuePackageRequest extends BaseModel {
	//车辆编号
	private String vehicleId;
	//发行套餐编号
	private String packageNum;
	//备注
	private String remarks;
	
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getPackageNum() {
		return packageNum;
	}
	public void setPackageNum(String packageNum) {
		this.packageNum = packageNum;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
