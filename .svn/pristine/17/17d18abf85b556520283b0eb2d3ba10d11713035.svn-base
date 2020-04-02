package cn.com.taiji.css.model.apply.customermanager;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.qtk.entity.VehicleInfo;

public class VehicleManagementRequest extends JpaPageableDataRequest<VehicleInfo> {
	//所有人证件类型
	private Integer ownerIdType;
	//所有人证件号码
	private String ownerIdNum;
	//所有人名称
	private String ownerName;
	//用户编号
	private String customerId;
	//车牌号
	private String vehiclePlate;
	//车牌颜色
	private Integer vehiclePlateColor;
	//渠道id
	private String agencyId;
	//应急车辆标识 0-非应急车辆  1-应急车辆
	private Integer emergencyFlag = 0;
	
	public VehicleManagementRequest(){
		this.orderBy="updateTime desc, id";
		this.desc=true;
	}
	
	public Integer getOwnerIdType() {
		return ownerIdType;
	}

	public void setOwnerIdType(Integer ownerIdType) {
		this.ownerIdType = ownerIdType;
	}

	public String getOwnerIdNum() {
		return ownerIdNum;
	}

	public void setOwnerIdNum(String ownerIdNum) {
		this.ownerIdNum = ownerIdNum;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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

	public Integer getVehiclePlateColor() {
		return vehiclePlateColor;
	}

	public void setVehiclePlateColor(Integer vehiclePlateColor) {
		this.vehiclePlateColor = vehiclePlateColor;
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
		hql.append(" and ownerIdType=:ownerIdType", ownerIdType);
		hql.append(" and ownerIdNum=:ownerIdNum", ownerIdNum);
		hql.append(" and ownerName=:ownerName", ownerName);
		hql.append(" and customerId=:customerId", customerId);
		hql.append(" and vehiclePlate=:vehiclePlate", vehiclePlate);
		hql.append(" and vehiclePlateColor=TO_CHAR(:vehiclePlateColor)", vehiclePlateColor);
		hql.append(" and substr(channelId,0,11)=:agencyId", agencyId);
		return hql;
	}


	
}
