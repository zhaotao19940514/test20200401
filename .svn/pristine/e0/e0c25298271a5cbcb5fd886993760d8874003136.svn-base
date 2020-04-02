package cn.com.taiji.css.model.administration.pkg;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.Package;

public class AccountRequest extends JpaDateTimePageableDataRequest<Package> {
	// 机构名称
	private String serviceHallList;
	// 优惠套餐名称
	private String packageName;

	public AccountRequest() {
		this.orderBy = " packageNum";
	}

	

	public String getServiceHallList() {
		return serviceHallList;
	}



	public void setServiceHallList(String serviceHallList) {
		this.serviceHallList = serviceHallList;
	}



	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + " Package " + "  where 1=1 ");
		hql.append(" and serviceHallList like :serviceHallList", like(serviceHallList));
		hql.append(" and packageName like :packageName", like(packageName));
		return hql;
	}

}
