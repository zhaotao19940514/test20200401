package cn.com.taiji.css.model.request.report.cancelreport;

import cn.com.taiji.common.validation.MyViolationException;

public class CancelReportRequest {

	private String startTime;
	private String endTime;
	private String agencyId;
	private String vehicleIsGui;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getVehicleIsGui() {
		return vehicleIsGui;
	}

	public void setVehicleIsGui(String vehicleIsGui) {
		this.vehicleIsGui = vehicleIsGui;
	}

	public void paramCheck() {
		MyViolationException mve = new MyViolationException();
		if (null == startTime)
			mve.addViolation("startTime", "请选择起始时间");
		if (null == endTime)
			mve.addViolation("endTime", "请选择终止时间");
		if (null == agencyId)
			mve.addViolation("agencyId", "请选择渠道");
		if (null == vehicleIsGui)
			mve.addViolation("vehicleIsGui", "请选择是否限制贵籍车牌");
		if (mve.hasViolation()) {
			throw mve;
		}
	}

}
