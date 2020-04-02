package cn.com.taiji.css.model.administration.pkg;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.IssuePackageInfo;


public class IssueRequest extends JpaDateTimePageableDataRequest<IssuePackageInfo> {
	// 名称
	private String packageName;
	// 车种
	private String vehicleType;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "IssuePackageInfo " + "  where 1=1 ");
		hql.append(" and packageName like :packageName", like(packageName));
		hql.append(" and vehicleType=:vehicleType", vehicleType);
		return hql;
	}

}
