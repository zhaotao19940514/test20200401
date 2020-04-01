package cn.com.taiji.css.model.customerservice.report;

import cn.com.taiji.common.validation.MyViolationException;

public class QueryTimes {
	
	String startTime;
	String endTime;
	
	public void Violation() {
		MyViolationException mve = new MyViolationException();
		if(startTime==null) 
			mve.addViolation("startTime", "日期不能为空");
		if(endTime==null) 
			mve.addViolation("endTime", "日期不能为空");
		if(mve.hasViolation()) {
			throw mve;
		}
	}
	
	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	/**
	 * 自动替换时间中的空格 为 "T"
	 * @param startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime.replace(" ", "T");
	}

	/**
	 * 自动替换时间中的空格 为 "T"
	 * @param endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime.replace(" ", "T");
	}
	
}

