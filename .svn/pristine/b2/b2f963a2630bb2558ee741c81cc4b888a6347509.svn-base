package cn.com.taiji.css.model.apply.quickapply;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.qtk.entity.VehicleInfo;

public class VehicleInfoQuickQueryRequest extends JpaPageableDataRequest<VehicleInfo> {
	//客户编号
	private String customerId;
	// 车牌号
	private String vehiclePlate;
	//渠道id
	private String agencyId;
	//应急车辆标识 0-非应急车辆  1-应急车辆
	private Integer emergencyFlag = 0;
	
	public VehicleInfoQuickQueryRequest(){
		this.orderBy="updateTime desc, id";
		this.desc=true;
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getVehiclePlate() {
		return vehiclePlate;
	}

	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public Integer getEmergencyFlag() {
		return emergencyFlag;
	}

	public void setEmergencyFlag(Integer emergencyFlag) {
		this.emergencyFlag = emergencyFlag;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + VehicleInfo.class.getName() + " where 1=1 ");
		hql.append(" and emergencyFlag=:emergencyFlag", emergencyFlag);
		hql.append(" and customerId=:customerId", customerId);
		hql.append(" and vehiclePlate=:vehiclePlate", vehiclePlate);
		hql.append(" and substr(channelId,0,11)=:agencyId", agencyId);
		return hql;
	}
	
}
