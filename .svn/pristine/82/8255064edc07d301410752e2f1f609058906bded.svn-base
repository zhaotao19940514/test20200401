package cn.com.taiji.css.model.administration.pkg;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaSortDataRequest;
import cn.com.taiji.qtk.entity.PackageServiceHall;
import cn.com.taiji.qtk.entity.ServiceHall;

public class PackageListRequest extends JpaSortDataRequest<ServiceHall> {
	private String serviceHallId;
	private String packageId;
	
	public PackageListRequest() {
		super();
	}
	
	public PackageListRequest(String serviceHallId, String packageId) {
		super();
		this.serviceHallId = serviceHallId;
		this.packageId = packageId;
	}

	public String getServiceHallId() {
		return serviceHallId;
	}

	public void setServiceHallId(String serviceHallId) {
		this.serviceHallId = serviceHallId;
	}


	public String getPackageId() {
		return packageId;
	}



	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}


	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + ServiceHall.class.getName() + " r where 1=1 ");
		hql.append(" and r.id=:id", serviceHallId);
		hql.append(" and exists(select b from " + PackageServiceHall.class.getName()
				+ " b where b.serviceHallId=r and b.packageId.id=:packageId )", packageId);
	    return hql;
	}     
	
}
