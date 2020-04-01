package cn.com.taiji.css.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.com.taiji.common.entity.StringUUIDEntity;
import cn.com.taiji.qtk.entity.CustomerInfo;

@Entity
@Table(name = "TEMP_XNK")
public class YangCheng extends StringUUIDEntity {

	
	//认证监管平台车辆编号
	private String daspVehicleId;
	//是否认证监管平台  0-不是  1-是"
	private String daspSign;
	//认证监管平台接收时间
	private Date daspAccept;
	
	
	//应急车辆标识 0-非应急车辆  1-应急车辆
	private Integer emergencyFlag;
	//是否为冷藏运输车
	private Integer refrigeratedTrucks;
	
	// 车辆编号
	private String vehicleId;

	// 车牌号
	private String vehiclePlate;

	// 车牌颜色
	private Integer vehiclePlateColor;

	// 用户id
	private String customerId;

	// 机动车所有人名称
	private String ownerName;

	// 所有人证件类型
	private Integer ownerIdType;

	// 所有人证件号码
	private String ownerIdNum;

	// 所有人联系方式
	private String ownerTel;

	// 所有人联系地址
	private String ownerAddress;

	// 指定联系人列表
	private String contacts;

	// 录入方式
	private Integer registeredType;

	// 渠道编号
	private String channelId;

	// 录入时间
	private String registeredTime;

	// 档案编号
	private String fileNum;

	// 行驶证车辆类型
	private String vehicleType;

	// 行驶证品牌型号
	private String vehicleModel;

	// 车辆使用性质
	private Integer useCharacter;

	// 车辆识别代号
	private String VIN;

	// 车辆发动机号
	private String engineNum;

	// 注册日期
	private String registerDate;

	// 发证日期
	private String issueDate;

	// 核定载人数
	private Integer approvedCount;

	// 总质量
	private Integer totalMass;

	// 整备质量
	private Integer maintenanceMass;

	// 核定载质量
	private Integer permittedWeight;

	// 外廓尺寸     统一格式为:长×宽×高     单位为:mm
	private String outsideDimensions;

	// 准牵引总质量
	private Integer permittedTowWeight;

	// 检验记录
	private String testRecord;

	// 车轮数
	private Integer wheelCount;

	// 车轴数
	private Integer axleCount;

	// 轴距
	private Integer axleDistance;

	// 轴型
	private String axisType;

	// 用户表外键
	private CustomerInfo customerInfo;

	// 提交时间
	private Calendar createTime;
	 //录入时间
    private Calendar updateTime;
    //收费车型
    private Integer type;
    
	
	/**
	 * @return refrigeratedTrucks
	 */
	@Column(name = "REFRIGERATED_TRUCKS")
	public Integer getRefrigeratedTrucks() {
		return refrigeratedTrucks;
	}

	/**
	 * @param refrigeratedTrucks 要设置的 refrigeratedTrucks
	 */
	public void setRefrigeratedTrucks(Integer refrigeratedTrucks) {
		this.refrigeratedTrucks = refrigeratedTrucks;
	}
	
	@Column(name = "TYPE")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "VEHICLE_ID")
	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Column(name = "VEHICLE_PLATE")
	public String getVehiclePlate() {
		return vehiclePlate;
	}

	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}

	@Column(name = "VEHICLE_PLATECOLOR")
	public Integer getVehiclePlateColor() {
		return vehiclePlateColor;
	}

	public void setVehiclePlateColor(Integer vehiclePlateColor) {
		this.vehiclePlateColor = vehiclePlateColor;
	}

	@Column(name = "CUSTOMER_ID")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Column(name = "OWNER_NAME")
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Column(name = "OWNER_IDTYPE")
	public Integer getOwnerIdType() {
		return ownerIdType;
	}

	public void setOwnerIdType(Integer ownerIdType) {
		this.ownerIdType = ownerIdType;
	}

	@Column(name = "OWNER_IDNUM")
	public String getOwnerIdNum() {
		return ownerIdNum;
	}

	public void setOwnerIdNum(String ownerIdNum) {
		this.ownerIdNum = ownerIdNum;
	}

	@Column(name = "OWNER_TEL")
	public String getOwnerTel() {
		return ownerTel;
	}

	public void setOwnerTel(String ownerTel) {
		this.ownerTel = ownerTel;
	}

	@Column(name = "OWNER_ADDRESS")
	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	@Column(name = "VEHICLE_CONTACTS")
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	@Column(name = "REGISTERED_TYPE")
	public Integer getRegisteredType() {
		return registeredType;
	}

	public void setRegisteredType(Integer registeredType) {
		this.registeredType = registeredType;
	}

	@Column(name = "CHANNEL_ID")
	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	@Column(name = "REGISTERED_TIME")
	public String getRegisteredTime() {
		return registeredTime;
	}

	public void setRegisteredTime(String registeredTime) {
		this.registeredTime = registeredTime;
	}

	@Column(name = "VEHICLE_FILENUM")
	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	@Column(name = "VEHICLE_TYPE")
	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Column(name = "VEHICLE_MODEL")
	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	@Column(name = "VEHICLE_USECHARACTER")
	public Integer getUseCharacter() {
		return useCharacter;
	}

	public void setUseCharacter(Integer useCharacter) {
		this.useCharacter = useCharacter;
	}

	@Column(name = "VEHICLE_VIN")
	public String getVIN() {
		return VIN;
	}

	public void setVIN(String VIN) {
		this.VIN = VIN;
	}

	@Column(name = "VEHICLE_ENGINENUM")
	public String getEngineNum() {
		return engineNum;
	}

	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}

	@Column(name = "REGISTER_DATE")
	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	@Column(name = "ISSUE_DATE")
	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	@Column(name = "VEHICLE_APPROVEDCOUNT")
	public Integer getApprovedCount() {
		return approvedCount;
	}

	public void setApprovedCount(Integer approvedCount) {
		this.approvedCount = approvedCount;
	}

	@Column(name = "VEHICLE_TOTALMASS")
	public Integer getTotalMass() {
		return totalMass;
	}

	public void setTotalMass(Integer totalMass) {
		this.totalMass = totalMass;
	}

	@Column(name = "VEHICLE_MAINTENANCEMASS")
	public Integer getMaintenanceMass() {
		return maintenanceMass;
	}

	public void setMaintenanceMass(Integer maintenanceMass) {
		this.maintenanceMass = maintenanceMass;
	}

	@Column(name = "VEHICLE_PERMITTEDWEIGHT")
	public Integer getPermittedWeight() {
		return permittedWeight;
	}

	public void setPermittedWeight(Integer permittedWeight) {
		this.permittedWeight = permittedWeight;
	}

	@Column(name = "VEHICLE_OUTSIDEDIMENSIONS")
	public String getOutsideDimensions() {
		return outsideDimensions;
	}

	public void setOutsideDimensions(String outsideDimensions) {
		this.outsideDimensions = outsideDimensions;
	}

	@Column(name = "VEHICLE_PERMITTEDTOWWEIGHT")
	public Integer getPermittedTowWeight() {
		return permittedTowWeight;
	}

	public void setPermittedTowWeight(Integer permittedTowWeight) {
		this.permittedTowWeight = permittedTowWeight;
	}

	@Column(name = "VEHICLE_TESTRECORD")
	public String getTestRecord() {
		return testRecord;
	}

	public void setTestRecord(String testRecord) {
		this.testRecord = testRecord;
	}

	@Column(name = "VEHICLE_WHEELCOUNT")
	public Integer getWheelCount() {
		return wheelCount;
	}

	public void setWheelCount(Integer wheelCount) {
		this.wheelCount = wheelCount;
	}

	@Column(name = "VEHICLE_AXLECOUNT")
	public Integer getAxleCount() {
		return axleCount;
	}

	public void setAxleCount(Integer axleCount) {
		this.axleCount = axleCount;
	}

	@Column(name = "VEHICLE_AXLEDISTANCE")
	public Integer getAxleDistance() {
		return axleDistance;
	}

	public void setAxleDistance(Integer axleDistance) {
		this.axleDistance = axleDistance;
	}

	@Column(name = "VEHICLE_AXISTYPE")
	public String getAxisType() {
		return axisType;
	}

	public void setAxisType(String axisType) {
		this.axisType = axisType;
	}

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_FOREIGNKEY")
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	@Column(name = "CREATE_TIME")
	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}
	@Column(name = "UPDATE_TIME")
	public Calendar getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Calendar updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name = "EMERGENCY_FLAG")
	public Integer getEmergencyFlag() {
		return emergencyFlag;
	}
	public void setEmergencyFlag(Integer emergencyFlag) {
		this.emergencyFlag = emergencyFlag;
	}
	@Column(name = "DASP_VEHICLE_ID")
	public String getDaspVehicleId() {
		return daspVehicleId;
	}

	public void setDaspVehicleId(String daspVehicleId) {
		this.daspVehicleId = daspVehicleId;
	}
	@Column(name = "DASP_SIGN")
	public String getDaspSign() {
		return daspSign;
	}

	public void setDaspSign(String daspSign) {
		this.daspSign = daspSign;
	}
	@Column(name = "DASP_ACCEPT_TIME")
	public Date getDaspAccept() {
		return daspAccept;
	}

	public void setDaspAccept(Date daspAccept) {
		this.daspAccept = daspAccept;
	}
	

	
}
