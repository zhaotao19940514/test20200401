package cn.com.taiji.css.model.administration.pkg;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaSortDataRequest;
import cn.com.taiji.qtk.entity.ServiceHall;

public class PackageColRequest extends JpaSortDataRequest<ServiceHall>{
	
	private String  agencyCode;
	
	
	
	public PackageColRequest(String agencyCode) {
		super();
		this.agencyCode = agencyCode;
	}

	public PackageColRequest() {
		super();
	}

	public String getAgencyCode() {
		return agencyCode;
	}


	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}


	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from ServiceHall where 1=1");
		hql.append(" and agencyCode=:agencyCode", agencyCode);
		return hql;
	}

}
