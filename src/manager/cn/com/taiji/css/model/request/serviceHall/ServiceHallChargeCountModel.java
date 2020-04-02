package cn.com.taiji.css.model.request.serviceHall;

import cn.com.taiji.common.model.BaseModel;

public class ServiceHallChargeCountModel extends BaseModel {

	private String handleDate;
	private String serviceHallName;
	private Long count;
	private Long amount;

	public String getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}

	public String getServiceHallName() {
		return serviceHallName;
	}

	public void setServiceHallName(String serviceHallName) {
		this.serviceHallName = serviceHallName;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

}
