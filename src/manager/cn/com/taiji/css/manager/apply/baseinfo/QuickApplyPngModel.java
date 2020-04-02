package cn.com.taiji.css.manager.apply.baseinfo;

import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.model.BaseModel;

public class QuickApplyPngModel extends BaseModel {
	private Integer customerType;
	private Integer customerIdType;
	private String customerIdNum;
	private String customerName;
	private String tel;
	private String address;
	private String department;
	private String agentName;
	private Integer agentIdType;
	private String agentIdNum;
	//应急车辆用户标识 0-非应急车辆用户  1-应急车辆用户
	private Integer emergencyFlag = 0;
	private MultipartFile[] file;
	public Integer getCustomerType() {
		return customerType;
	}
	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}
	public Integer getCustomerIdType() {
		return customerIdType;
	}
	public void setCustomerIdType(Integer customerIdType) {
		this.customerIdType = customerIdType;
	}
	public String getCustomerIdNum() {
		return customerIdNum;
	}
	public void setCustomerIdNum(String customerIdNum) {
		this.customerIdNum = customerIdNum;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public Integer getAgentIdType() {
		return agentIdType;
	}
	public void setAgentIdType(Integer agentIdType) {
		this.agentIdType = agentIdType;
	}
	public String getAgentIdNum() {
		return agentIdNum;
	}
	public Integer getEmergencyFlag() {
		return emergencyFlag;
	}
	public void setEmergencyFlag(Integer emergencyFlag) {
		this.emergencyFlag = emergencyFlag;
	}
	public void setAgentIdNum(String agentIdNum) {
		this.agentIdNum = agentIdNum;
	}
	public MultipartFile[] getFile() {
		return file;
	}
	public void setFile(MultipartFile[] file) {
		this.file = file;
	}
}
