package cn.com.taiji.css.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


import cn.com.taiji.common.entity.StringUUIDEntity;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.dict.BatchIssueStatus;
import cn.com.taiji.css.model.apply.inportInfomation.InportInfomationResponse;

/**
 * @author T440
 * 批量开卡表
 */
@Entity
@Table(name = "QTK_BATCH_ISSUE_BASEINFO")
public class BatchIssueBaseInfo extends StringUUIDEntity {
	private String createTime;
	private String updateTime;
	private BatchIssueStatus status;
	private String batchId;
	private Integer packageNum;
	private Integer userType;
	private String userName;
	private String identNo;
	private Integer identType;
	private String address;
	private String userMobile;
	private String department;
	private String agentName;
	private Integer agentIdType;
	private String agentIdNum;
	private String vehiclePlate;
	private Integer vehiclePlateColor;
	private Integer vehicleType;
	private String vehicleOwner;
	private String ownerName;
	private Integer ownerIdType;
	private String ownerIdNum;
	private String ownerTel;
	private Integer type;
	private Integer useCharacter;
	private String registerDate;
	private String issueDate;
	private String vin;
	private String engineNum;
	private String fileNum;
	private Integer approvedCount;
	private Integer totalMass;
	private Integer maintenanceMass;
	private Integer permittedWeight;
	private String outsideDimensions;
	private Integer permittedTowWeight;
	private String testRecord;
	private Integer wheelCount;
	private Integer axleCount;
	private Integer axleDistance;
	private String axisType;
	private String orderNo;
	private Integer businessType;
	private Integer orderType;
	private Integer isOnline;
	private Integer postal;
	private String receiverMobile;
	private String receiverName;
	private String province;
	private String city;
	private String county;
	private String district;
	private String detailAddress;
	private String remarks;
	private String respCode;
	private String respMessage;
	private String finishId;
	private String agencyId;
	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}
	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return updateTime;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	public BatchIssueStatus getStatus() {
		return status;
	}
	@Column(name = "BATCH_ID")
	public String getBatchId() {
		return batchId;
	}
	@Column(name = "PACKAGE_NUM")
	public Integer getPackageNum() {
		return packageNum;
	}
	@Column(name = "USER_TYPE")
	public Integer getUserType() {
		return userType;
	}
	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}
	@Column(name = "IDENT_NO")
	public String getIdentNo() {
		return identNo;
	}
	@Column(name = "IDENT_TYPE")
	public Integer getIdentType() {
		return identType;
	}
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}
	@Column(name = "USER_MOBILE")
	public String getUserMobile() {
		return userMobile;
	}
	@Column(name = "DEPARTMENT")
	public String getDepartment() {
		return department;
	}
	@Column(name = "AGENT_NAME")
	public String getAgentName() {
		return agentName;
	}
	@Column(name = "AGENT_ID_TYPE")
	public Integer getAgentIdType() {
		return agentIdType;
	}
	@Column(name = "AGENT_ID_NUM")
	public String getAgentIdNum() {
		return agentIdNum;
	}
	@Column(name = "VEHICLE_PLATE")
	public String getVehiclePlate() {
		return vehiclePlate;
	}
	@Column(name = "VEHICLE_PLATE_COLOR")
	public Integer getVehiclePlateColor() {
		return vehiclePlateColor;
	}
	@Column(name = "VEHICLE_TYPE")
	public Integer getVehicleType() {
		return vehicleType;
	}
	@Column(name = "VEHICLE_OWNER")
	public String getVehicleOwner() {
		return vehicleOwner;
	}
	@Column(name = "OWNER_NAME")
	public String getOwnerName() {
		return ownerName;
	}
	@Column(name = "OWNER_ID_TYPE")
	public Integer getOwnerIdType() {
		return ownerIdType;
	}
	@Column(name = "OWNER_ID_NUM")
	public String getOwnerIdNum() {
		return ownerIdNum;
	}
	@Column(name = "OWNER_TEL")
	public String getOwnerTel() {
		return ownerTel;
	}
	@Column(name = "TYPE")
	public Integer getType() {
		return type;
	}
	@Column(name = "USE_CHARACTER")
	public Integer getUseCharacter() {
		return useCharacter;
	}
	@Column(name = "REGISTER_DATE")
	public String getRegisterDate() {
		return registerDate;
	}
	@Column(name = "ISSUE_DATE")
	public String getIssueDate() {
		return issueDate;
	}
	@Column(name = "VIN")
	public String getVin() {
		return vin;
	}
	@Column(name = "ENGINE_NUM")
	public String getEngineNum() {
		return engineNum;
	}
	@Column(name = "FILE_NUM")
	public String getFileNum() {
		return fileNum;
	}
	@Column(name = "APPROVED_COUNT")
	public Integer getApprovedCount() {
		return approvedCount;
	}
	@Column(name = "TOTAL_MASS")
	public Integer getTotalMass() {
		return totalMass;
	}
	@Column(name = "MAINTENANCE_MASS")
	public Integer getMaintenanceMass() {
		return maintenanceMass;
	}
	@Column(name = "PERMITTED_WEIGHT")
	public Integer getPermittedWeight() {
		return permittedWeight;
	}
	@Column(name = "OUTSIDE_DIMENSIONS")
	public String getOutsideDimensions() {
		return outsideDimensions;
	}
	@Column(name = "PERMITTED_TOW_WEIGHT")
	public Integer getPermittedTowWeight() {
		return permittedTowWeight;
	}
	@Column(name = "TEST_RECORD")
	public String getTestRecord() {
		return testRecord;
	}
	@Column(name = "WHEEL_COUNT")
	public Integer getWheelCount() {
		return wheelCount;
	}
	@Column(name = "AXLE_COUNT")
	public Integer getAxleCount() {
		return axleCount;
	}
	@Column(name = "AXLE_DISTANCE")
	public Integer getAxleDistance() {
		return axleDistance;
	}
	@Column(name = "AXIS_TYPE")
	public String getAxisType() {
		return axisType;
	}
	@Column(name = "ORDER_NO")
	public String getOrderNo() {
		return orderNo;
	}
	@Column(name = "BUSINESS_TYPE")
	public Integer getBusinessType() {
		return businessType;
	}
	@Column(name = "ORDER_TYPE")
	public Integer getOrderType() {
		return orderType;
	}
	@Column(name = "IS_ONLINE")
	public Integer getIsOnline() {
		return isOnline;
	}
	@Column(name = "POSTAL")
	public Integer getPostal() {
		return postal;
	}
	@Column(name = "RECEIVER_MOBILE")
	public String getReceiverMobile() {
		return receiverMobile;
	}
	@Column(name = "RECEIVER_NAME")
	public String getReceiverName() {
		return receiverName;
	}
	@Column(name = "PROVINCE")
	public String getProvince() {
		return province;
	}
	@Column(name = "CITY")
	public String getCity() {
		return city;
	}
	@Column(name = "COUNTY")
	public String getCounty() {
		return county;
	}
	@Column(name = "DISTRICT")
	public String getDistrict() {
		return district;
	}
	@Column(name = "DETAIL_ADDRESS")
	public String getDetailAddress() {
		return detailAddress;
	}
	@Column(name = "REMARKS")
	public String getRemarks() {
		return remarks;
	}
	@Column(name = "RESP_CODE")
	public String getRespCode() {
		return respCode;
	}
	@Column(name = "RESP_MESSAGE")
	public String getRespMessage() {
		return respMessage;
	}
	@Column(name = "FINISH_ID")
	public String getFinishId() {
		return finishId;
	}
	@Column(name = "AGENCY_ID")
	public String getAgencyId() {
		return agencyId;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public void setStatus(BatchIssueStatus status) {
		this.status = status;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public void setPackageNum(Integer packageNum) {
		this.packageNum = packageNum;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setIdentNo(String identNo) {
		this.identNo = identNo;
	}
	public void setIdentType(Integer identType) {
		this.identType = identType;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public void setAgentIdType(Integer agentIdType) {
		this.agentIdType = agentIdType;
	}
	public void setAgentIdNum(String agentIdNum) {
		this.agentIdNum = agentIdNum;
	}
	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}
	public void setVehiclePlateColor(Integer vehiclePlateColor) {
		this.vehiclePlateColor = vehiclePlateColor;
	}
	public void setVehicleType(Integer vehicleType) {
		this.vehicleType = vehicleType;
	}
	public void setVehicleOwner(String vehicleOwner) {
		this.vehicleOwner = vehicleOwner;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public void setOwnerIdType(Integer ownerIdType) {
		this.ownerIdType = ownerIdType;
	}
	public void setOwnerIdNum(String ownerIdNum) {
		this.ownerIdNum = ownerIdNum;
	}
	public void setOwnerTel(String ownerTel) {
		this.ownerTel = ownerTel;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setUseCharacter(Integer useCharacter) {
		this.useCharacter = useCharacter;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}
	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}
	public void setApprovedCount(Integer approvedCount) {
		this.approvedCount = approvedCount;
	}
	public void setTotalMass(Integer totalMass) {
		this.totalMass = totalMass;
	}
	public void setMaintenanceMass(Integer maintenanceMass) {
		this.maintenanceMass = maintenanceMass;
	}
	public void setPermittedWeight(Integer permittedWeight) {
		this.permittedWeight = permittedWeight;
	}
	public void setOutsideDimensions(String outsideDimensions) {
		this.outsideDimensions = outsideDimensions;
	}
	public void setPermittedTowWeight(Integer permittedTowWeight) {
		this.permittedTowWeight = permittedTowWeight;
	}
	public void setTestRecord(String testRecord) {
		this.testRecord = testRecord;
	}
	public void setWheelCount(Integer wheelCount) {
		this.wheelCount = wheelCount;
	}
	public void setAxleCount(Integer axleCount) {
		this.axleCount = axleCount;
	}
	public void setAxleDistance(Integer axleDistance) {
		this.axleDistance = axleDistance;
	}
	public void setAxisType(String axisType) {
		this.axisType = axisType;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}
	public void setPostal(Integer postal) {
		this.postal = postal;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
	}
	public void setFinishId(String finishId) {
		this.finishId = finishId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public InportInfomationResponse validate() {
		InportInfomationResponse response = new InportInfomationResponse();
		response.setStatus(-1);
		if(userType==null) {
			response.setMessage("用户类型不能为空！");
		}else if(!(StringTools.hasText(userName))) {
			response.setMessage("用户姓名不能为空！");
		}else if(!(StringTools.hasText(identNo))) {
			response.setMessage("身份证号不能为空！");
		}else if(!(StringTools.hasText(address))) {
			response.setMessage("地址不能为空！");
		}else if(!(StringTools.hasText(userMobile))) {
			response.setMessage("手机号不能为空！");
		}else if(!(StringTools.hasText(department))) {
			response.setMessage("部门分支机构不能为空！");
		}else if(!(StringTools.hasText(agentName))) {
			response.setMessage("指定经办人姓名不能为空！");
		}else if(!(StringTools.hasText(agentIdNum))) {
			response.setMessage("指定经办人证件号不能为空！");
		}else if(!(StringTools.hasText(vehiclePlate))) {
			response.setMessage("车牌号不能为空！");
		}else if(!(StringTools.hasText(vehicleOwner))) {
			response.setMessage("车辆所有人不能为空！");
		}else if(!(StringTools.hasText(ownerName))) {
			response.setMessage("机动车所有人名称不能为空！");
		}else if(!(StringTools.hasText(ownerIdNum))) {
			response.setMessage("所有人证件号码不能为空！");
		}else if(!(StringTools.hasText(ownerTel))) {
			response.setMessage("所有人联系方式不能为空！");
		}else if(!(StringTools.hasText(registerDate))) {
			response.setMessage("注册日期不能为空！");
		}else if(!(StringTools.hasText(issueDate))) {
			response.setMessage("发证日期不能为空！");
		}else if(!(StringTools.hasText(vin))) {
			response.setMessage("车辆识别代号不能为空！");
		}else if(!(StringTools.hasText(engineNum))) {
			response.setMessage("车辆发动机号不能为空！");
		}else {
			response.setStatus(1);
		}
		
		return response;
	}
	
}
