package cn.com.taiji.css.model.request.report.gybftpdetail;

import cn.com.taiji.common.validation.MyViolationException;

public class GybFtpDetailRequest {

	private String startTime;
	private String endTime;

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

	public void paramCheck() {
		MyViolationException mve = new MyViolationException();
		if (null == startTime)
			mve.addViolation("startTime", "请选择起始时间");
		if (null == endTime)
			mve.addViolation("endTime", "请选择终止时间");
		if (mve.hasViolation()) {
			throw mve;
		}
	}

}
