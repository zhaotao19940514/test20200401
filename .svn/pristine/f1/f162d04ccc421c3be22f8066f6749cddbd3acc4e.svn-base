package cn.com.taiji.css.model.issuetranscation;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDataRequest;
import cn.com.taiji.qtk.entity.FileInprovinceDetail;

public class InprovinceStatisticalRequest extends JpaDataRequest<FileInprovinceDetail>  {
    
	private Integer agencyType;   
	private String startTime;
	private String endTime;
	
	
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "FileInprovinceDetail " +
				"  where 1=1 ");
		return hql;
	}

	public Integer getAgencyType() {
		return agencyType;
	}

	public void setAgencyType(Integer agencyType) {
		this.agencyType = agencyType;
	}

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

	
}
