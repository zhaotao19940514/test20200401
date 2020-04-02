package cn.com.taiji.css.model.request.serviceHall;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.qtk.entity.ChargeDetail;

public class ServiceHallChargeCountRequest extends JpaPageableDataRequest<ChargeDetail>{
    
	private String handleDate; 
	private String serviceHallId;
	private String startDate;
	private String endDate;
	
	
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from ServiceHall where 1=1 ");	
		return hql;
	}


	public String getHandleDate() {
		return handleDate;
	}


	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}


	public String getServiceHallId() {
		return serviceHallId;
	}


	public void setServiceHallId(String serviceHallId) {
		this.serviceHallId = serviceHallId;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
