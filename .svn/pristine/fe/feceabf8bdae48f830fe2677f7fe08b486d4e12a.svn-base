package cn.com.taiji.css.model.apply.customermanager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.manager.util.CssUtil;
import cn.com.taiji.css.manager.util.MyPatterns;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.VehicleCustomerType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;
import cn.com.taiji.qtk.entity.dict.VehicleType;
import cn.com.taiji.qtk.entity.dict.VehicleTypeSimple;

public class VehicleInfoRequest extends BaseModel {
	//车辆信息表主键   UUID
	private String id;
	// 车辆编号
	private String vehicleId;

	//是否为冷藏运输车
	private Integer refrigeratedTrucks;

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

	// 外廓尺寸
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
	// 录入时间
	private Calendar updateTime;
	// 收费车型
	private Integer type;
	// 发现套餐编号
	private String packageNum;
	//车长
	private Integer vehicleLength;
	//车宽
	private Integer vehicleWidth;
	//车高
	private Integer vehicleHeight;
	//应急车辆标识 0-非应急车辆  1-应急车辆
	private Integer emergencyFlag = 0;
	//行驶证照片
	private MultipartFile[] drivingLicensePic;
	//车辆照片
	private MultipartFile[] vehiclePic;
	//车辆使用类型  
	private Integer vehicleCustomerType;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Integer getVehiclePlateColor() {
		return vehiclePlateColor;
	}

	public void setVehiclePlateColor(Integer vehiclePlateColor) {
		this.vehiclePlateColor = vehiclePlateColor;
	}

	/**
	 * @return refrigeratedTrucks
	 */
	public Integer getRefrigeratedTrucks() {
		return refrigeratedTrucks;
	}

	/**
	 * @param refrigeratedTrucks 要设置的 refrigeratedTrucks
	 */
	public void setRefrigeratedTrucks(Integer refrigeratedTrucks) {
		this.refrigeratedTrucks = refrigeratedTrucks;
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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

	public String getOwnerTel() {
		return ownerTel;
	}

	public void setOwnerTel(String ownerTel) {
		this.ownerTel = ownerTel;
	}

	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public Integer getRegisteredType() {
		return registeredType;
	}

	public void setRegisteredType(Integer registeredType) {
		this.registeredType = registeredType;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getRegisteredTime() {
		return registeredTime;
	}

	public void setRegisteredTime(String registeredTime) {
		this.registeredTime = registeredTime;
	}

	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public Integer getUseCharacter() {
		return useCharacter;
	}

	public void setUseCharacter(Integer useCharacter) {
		this.useCharacter = useCharacter;
	}

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String vIN) {
		VIN = vIN;
	}

	public String getEngineNum() {
		return engineNum;
	}

	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public Integer getApprovedCount() {
		return approvedCount;
	}

	public void setApprovedCount(Integer approvedCount) {
		this.approvedCount = approvedCount;
	}

	public Integer getTotalMass() {
		return totalMass;
	}

	public void setTotalMass(Integer totalMass) {
		this.totalMass = totalMass;
	}

	public Integer getMaintenanceMass() {
		return maintenanceMass;
	}

	public void setMaintenanceMass(Integer maintenanceMass) {
		this.maintenanceMass = maintenanceMass;
	}

	public Integer getPermittedWeight() {
		return permittedWeight;
	}

	public void setPermittedWeight(Integer permittedWeight) {
		this.permittedWeight = permittedWeight;
	}

	public String getOutsideDimensions() {
		return outsideDimensions;
	}

	public void setOutsideDimensions(String outsideDimensions) {
		this.outsideDimensions = outsideDimensions;
	}

	public Integer getPermittedTowWeight() {
		return permittedTowWeight;
	}

	public void setPermittedTowWeight(Integer permittedTowWeight) {
		this.permittedTowWeight = permittedTowWeight;
	}

	public String getTestRecord() {
		return testRecord;
	}

	public void setTestRecord(String testRecord) {
		this.testRecord = testRecord;
	}

	public Integer getWheelCount() {
		return wheelCount;
	}

	public void setWheelCount(Integer wheelCount) {
		this.wheelCount = wheelCount;
	}

	public Integer getAxleCount() {
		return axleCount;
	}

	public void setAxleCount(Integer axleCount) {
		this.axleCount = axleCount;
	}

	public Integer getAxleDistance() {
		return axleDistance;
	}

	public void setAxleDistance(Integer axleDistance) {
		this.axleDistance = axleDistance;
	}

	public String getAxisType() {
		return axisType;
	}

	public void setAxisType(String axisType) {
		this.axisType = axisType;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public Calendar getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Calendar updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(String packageNum) {
		this.packageNum = packageNum;
	}
	
	public Integer getVehicleLength() {
		return vehicleLength;
	}

	public void setVehicleLength(Integer vehicleLength) {
		this.vehicleLength = vehicleLength;
	}

	public Integer getVehicleWidth() {
		return vehicleWidth;
	}

	public void setVehicleWidth(Integer vehicleWidth) {
		this.vehicleWidth = vehicleWidth;
	}

	public Integer getVehicleHeight() {
		return vehicleHeight;
	}

	public void setVehicleHeight(Integer vehicleHeight) {
		this.vehicleHeight = vehicleHeight;
	}
	
	public Integer getEmergencyFlag() {
		return emergencyFlag;
	}

	public void setEmergencyFlag(Integer emergencyFlag) {
		this.emergencyFlag = emergencyFlag;
	}

	public MultipartFile[] getDrivingLicensePic() {
		return drivingLicensePic;
	}

	public void setDrivingLicensePic(MultipartFile[] drivingLicensePic) {
		this.drivingLicensePic = drivingLicensePic;
	}

	public MultipartFile[] getVehiclePic() {
		return vehiclePic;
	}

	public void setVehiclePic(MultipartFile[] vehiclePic) {
		this.vehiclePic = vehiclePic;
	}

	/**
	 * 车辆信息格式校验
	 * @throws ManagerException
	 */
	public void validateVehicleInfo() throws ManagerException {
		MyViolationException mve = new MyViolationException();
		if(customerId == null)
			throw new ManagerException("客户编号错误！请联系管理员！");
		if (vehiclePlate == null) {
			mve.addViolation("vehiclePlate", "不能为空！");
		}else {
			if(emergencyFlag.intValue() == 1 && !vehiclePlate.contains("应急")) mve.addViolation("vehiclePlate", "应急车辆车牌格式应为：车牌号+应急，如：贵AER66应急");
			if(!MyPatterns.checkPlateNumFormat(vehiclePlate)) mve.addViolation("vehiclePlate", "格式不正确！");
		}
		if (vehiclePlateColor == null) {
			mve.addViolation("vehiclePlateColor", "不能为空！");
		}else {
			if(VehiclePlateColorType.valueOfCode(vehiclePlateColor) == null)
				mve.addViolation("vehiclePlateColor", "没有此车牌颜色类型！"+vehiclePlateColor);
		}
		/*if (useCharacter == null)
			mve.addViolation("useCharacter", "不能为空！");*/
		if (vehicleType == null) {
			mve.addViolation("vehicleType", "不能为空！");
		}else {
			if (vehicleType.equals(String.valueOf(VehicleTypeSimple.CAR.getTypeCode()))) {
				if (approvedCount == null) {
					mve.addViolation("approvedCount", "不能为空！");
				}else {
					if (approvedCount <= 0) 
						mve.addViolation("approvedCount", "客车不能小于等于0！");
				}
			}else if (vehicleType.equals(String.valueOf(VehicleTypeSimple.TRUCK.getTypeCode()))) {
				if (permittedWeight == null) {
					mve.addViolation("permittedWeight", "不能为空！");
				}else {
					if (permittedWeight <= 0)
						mve.addViolation("permittedWeight", "货车不能小于等于0！");
				}
				if(refrigeratedTrucks==null)
					mve.addViolation("refrigeratedTrucks", "不能为空！");
			}else if (vehicleType.equals(String.valueOf(VehicleTypeSimple.ZXZZY.getTypeCode()))) {
				if (approvedCount == null) 
					mve.addViolation("approvedCount", "不能为空！");
				if (permittedWeight == null) 
					mve.addViolation("permittedWeight", "不能为空！");
			}else {
				mve.addViolation("vehicleType", "不合法的值！");
			}
		}
		if (type == null) {
			mve.addViolation("type", "不能为空！");
		}else {
			if(VehicleType.valueOfCode(type) == null) {
				mve.addViolation("type", "没有此车辆收费类型！"+type);
			}
		}
		if (vehicleModel != null) {
			if(vehicleModel.length() > 50)
				mve.addViolation("vehicleModel", "不能大于50个字符！");
		}
		if (VIN == null) {
			mve.addViolation("VIN", "不能为空！");
		}else {
			if(VIN.length() > 50) {
				mve.addViolation("VIN", "不能大于50个字符！");
			}else if(!VIN.matches("^[A-Za-z0-9]+$")) {
				mve.addViolation("VIN", "只能由字母和数字组成！");
			}
		}
		if (engineNum != null) {
			if(engineNum.length() > 50)
				mve.addViolation("engineNum", "不能大于50个字符！");
		}
		/*if (registerDate == null)
			mve.addViolation("registerDate", "不能为空！");
		if (issueDate == null)
			mve.addViolation("issueDate", "不能为空！");*/
		if (ownerName != null) {
			if(ownerName.length() > 50) {
				mve.addViolation("ownerName", "不能大于50个字符！");
			}else if(MyPatterns.checkIntRege(ownerName)) {
				mve.addViolation("ownerName", "不能为数字！");
			}
		}
		if (ownerIdType != null) {
			if(CustomerIDType.valueOfCode(ownerIdType) == null)
				mve.addViolation("ownerIdType", "没有此证件类型！"+ownerIdType);
			
			if(ownerIdNum != null) {
				switch (ownerIdType) {
				case 101:
					if(!MyPatterns.checkIdCode(ownerIdNum)) {
						mve.addViolation("ownerIdNum", "身份证格式不正确！");
					}
					break;
				case 103:
					if(!MyPatterns.checkGaCnIdCodeFormat(ownerIdNum)) {
						mve.addViolation("ownerIdNum", "港澳通行证证格式不正确！");
					}
					break;
				case 104:
					if(!MyPatterns.checkTwCnIdCodeFormat(ownerIdNum)) {
						mve.addViolation("ownerIdNum", "台湾通行证格式不正确！");
					}
					break;
				case 105:
					if(!MyPatterns.checkSgzCnIdCodeFormat(ownerIdNum)) {
						mve.addViolation("ownerIdNum", "军官证格式不正确！");
					}
					break;
				case 201:
					if(!MyPatterns.checkCreditCodeFormat(ownerIdNum)) {
						mve.addViolation("ownerIdNum", "社会信用代码格式不正确！");
					}
					break;
				
				default:
					break;
				}
			}
		}
		/*if (ownerIdNum == null)
			mve.addViolation("ownerIdNum", "不能为空！");*/
		if (ownerTel != null) {
			if(!MyPatterns.checkMobileFormat(ownerTel))
				mve.addViolation("ownerTel", "手机号格式不正确！");
		}
		if (ownerAddress != null) {
			if(ownerAddress.length() > 100)
				mve.addViolation("ownerAddress", "不能大于100字符！");
		}
		if (!StringTools.hasText(contacts)) {
			mve.addViolation("contacts", "不能为空！");
		}else {
			if(contacts.length() > 50) {
				mve.addViolation("contacts", "不能大于50字符！");
			}else if(!contacts.matches("^[a-zA-Z\\u4e00-\\u9fa5·\\*]+$")) {
				mve.addViolation("contacts", "不能有数字和特殊字符！");
			}else if(!contacts.matches("^[\\u4e00-\\u9fa5·\\*]+$") && !contacts.matches("^[a-zA-Z·\\*]+$")) {
				mve.addViolation("contacts", "中英文不能同时存在！");
			}
		}
		
		if(emergencyFlag != null && emergencyFlag.intValue() == 1) {
			if (totalMass == null) {
				mve.addViolation("totalMass", "不能为空！");
			}else {
				if (totalMass <= 0)
					mve.addViolation("totalMass", "不能小于等于0！");
			}
			if (axleCount == null) {
				mve.addViolation("axleCount", "不能为空！");
			}else {
				if (axleCount <= 1)
					mve.addViolation("axleCount", "不能小于等于1！");
			}
		}
		/*if (totalMass == null)
			mve.addViolation("totalMass", "不能为空！");
		if (maintenanceMass == null)
			mve.addViolation("maintenanceMass", "不能为空！");
		if (wheelCount == null)
			mve.addViolation("wheelCount", "不能为空！");
		if (axleCount == null)
			mve.addViolation("axleCount", "不能为空！");*/
		
		if(drivingLicensePic != null && drivingLicensePic.length > 0) {
			List<String> list = new ArrayList<>();
			list.add("image/png");
			list.add("image/jpg");
			list.add("image/jpeg");
			Boolean flag = CssUtil.validateFileType(drivingLicensePic, list);
			if(!flag) {
				 mve.addViolation("drivingLicensePic", "只能上传png、jpg、jpeg格式的照片！");
			}
		}
		if(vehiclePic != null && vehiclePic.length > 0) {
			List<String> list = new ArrayList<>();
			list.add("image/png");
			list.add("image/jpg");
			list.add("image/jpeg");
			Boolean flag = CssUtil.validateFileType(vehiclePic, list);
			if(!flag) {
				 mve.addViolation("vehiclePic", "只能上传png、jpg、jpeg格式的照片！");
			}
		}
		
		 if(VehicleType.isSpecialCar(type) || VehicleType.isWeightCar(type)) {
		    if(axleCount==null || axleCount==0) {
		     mve.addViolation("axleCount", "货车与专项作业车车轴数必填");
		    }
		    if(totalMass==null || totalMass==0) {
		     mve.addViolation("totalMass", "货车与专项作业车总质量必填");
		    }
		    if(vehicleCustomerType==null || vehicleCustomerType==0) {
		     mve.addViolation("vehicleCustomerType", "货车与专项作业车车辆用户类型必填");
		    }
		   }
		 

		if (mve.hasViolation())
			throw mve;
	}
}
