package cn.com.taiji.css.model.administration.pkg;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.qtk.entity.CarIssuePackageInfo;

public class IssuerecordsQueryRequest extends JpaPageableDataRequest<CarIssuePackageInfo> {
	//车辆编号
	private String vehicleId;
	// 车牌号
	private String vehiclePlate;
	// 车牌颜色
	private String vehiclePlateColor;
	// 0 未付款   1 已付款  2作废
	private Integer  status;
	//渠道id
	private String agencyId;
	//网点id
	private String serviceHallId;
	//工号
	private String staffId;
	
	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehiclePlate() {
		return vehiclePlate;
	}

	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}

	public String getVehiclePlateColor() {
		return vehiclePlateColor;
	}

	public void setVehiclePlateColor(String vehiclePlateColor) {
		this.vehiclePlateColor = vehiclePlateColor;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getServiceHallId() {
		return serviceHallId;
	}

	public void setServiceHallId(String serviceHallId) {
		this.serviceHallId = serviceHallId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	@Override
	public HqlBuilder toSelectHql() {
		if(vehiclePlate != null && vehiclePlateColor != null) {
			vehicleId = vehiclePlate + "_" + vehiclePlateColor;
		}
		HqlBuilder hql = new HqlBuilder("from " + CarIssuePackageInfo.class.getName() + " where 1=1 ");
		hql.append(" and vehicleId=:vehicleId", vehicleId);
		if(vehicleId == null) {
			hql.append(" and substr(vehicleId,1,instr(vehicleId,'_',-1,1)-1)=:vehiclePlate", vehiclePlate);
			hql.append(" and substr(vehicleId,instr(vehicleId,'_',-1,1)+1)=:vehiclePlateColor", vehiclePlateColor);
		}
		hql.append(" and serviceHallId=:serviceHallId", serviceHallId);
		hql.append(" and substr(serviceHallId,0,11)=:agencyId", agencyId);
		hql.append(" and status=:status", status);
		hql.append(" order by handleTime desc ,id desc");
		return hql;
	}

}
