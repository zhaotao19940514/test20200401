package cn.com.taiji.css.model;

import java.io.File;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.entity.ServiceHallDeviceConfig;

public class ServiceHallRequset extends BaseModel{
	
		private File file;
	
		private String fileName;
	
		private String filePath;
	
		//客服合作机构编号
		private String agencyId;
		//服务网点名称
		private String  name;
		//联系人姓名
		private String contact;
		//联系电话
		private String tel;
		//服务项目
		private String serviceItems;
		//营业时间
		private String businessHours;
		//地址
		private String address;
		//起始日期
		private String startTime;
		//终止日期
		private String endTime;
		//开户行
		private String bank;
		//开户行地址
		private String bankAddr;
		//开户行账号
		private String bankAccount;
		//录入时间
		private Calendar createTime;
		
		private String serviceHallId;
		
		private String agencyName;
		
		private String agencyCode;
		
		private ServiceHallDeviceConfig config;

		/**
		 * @return file
		 */
		public File getFile() {
			return file;
		}

		/**
		 * @param file 要设置的 file
		 */
		public void setFile(File file) {
			this.file = file;
		}

		/**
		 * @return fileName
		 */
		public String getFileName() {
			return fileName;
		}

		/**
		 * @param fileName 要设置的 fileName
		 */
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		/**
		 * @return filePath
		 */
		public String getFilePath() {
			return filePath;
		}

		/**
		 * @param filePath 要设置的 filePath
		 */
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}

		/**
		 * @return agencyId
		 */
		public String getAgencyId() {
			return agencyId;
		}

		/**
		 * @param agencyId 要设置的 agencyId
		 */
		public void setAgencyId(String agencyId) {
			this.agencyId = agencyId;
		}

		/**
		 * @return name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name 要设置的 name
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return contact
		 */
		public String getContact() {
			return contact;
		}

		/**
		 * @param contact 要设置的 contact
		 */
		public void setContact(String contact) {
			this.contact = contact;
		}

		/**
		 * @return tel
		 */
		public String getTel() {
			return tel;
		}

		/**
		 * @param tel 要设置的 tel
		 */
		public void setTel(String tel) {
			this.tel = tel;
		}

		/**
		 * @return serviceItems
		 */
		public String getServiceItems() {
			return serviceItems;
		}

		/**
		 * @param serviceItems 要设置的 serviceItems
		 */
		public void setServiceItems(String serviceItems) {
			this.serviceItems = serviceItems;
		}

		/**
		 * @return businessHours
		 */
		public String getBusinessHours() {
			return businessHours;
		}

		/**
		 * @param businessHours 要设置的 businessHours
		 */
		public void setBusinessHours(String businessHours) {
			this.businessHours = businessHours;
		}

		/**
		 * @return address
		 */
		public String getAddress() {
			return address;
		}

		/**
		 * @param address 要设置的 address
		 */
		public void setAddress(String address) {
			this.address = address;
		}

		/**
		 * @return startTime
		 */
		public String getStartTime() {
			return startTime;
		}

		/**
		 * @param startTime 要设置的 startTime
		 */
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		/**
		 * @return endTime
		 */
		public String getEndTime() {
			return endTime;
		}

		/**
		 * @param endTime 要设置的 endTime
		 */
		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		/**
		 * @return bank
		 */
		public String getBank() {
			return bank;
		}

		/**
		 * @param bank 要设置的 bank
		 */
		public void setBank(String bank) {
			this.bank = bank;
		}

		/**
		 * @return bankAddr
		 */
		public String getBankAddr() {
			return bankAddr;
		}

		/**
		 * @param bankAddr 要设置的 bankAddr
		 */
		public void setBankAddr(String bankAddr) {
			this.bankAddr = bankAddr;
		}

		/**
		 * @return bankAccount
		 */
		public String getBankAccount() {
			return bankAccount;
		}

		/**
		 * @param bankAccount 要设置的 bankAccount
		 */
		public void setBankAccount(String bankAccount) {
			this.bankAccount = bankAccount;
		}

		/**
		 * @return createTime
		 */
		public Calendar getCreateTime() {
			return createTime;
		}

		/**
		 * @param createTime 要设置的 createTime
		 */
		public void setCreateTime(Calendar createTime) {
			this.createTime = createTime;
		}

		/**
		 * @return serviceHallId
		 */
		public String getServiceHallId() {
			return serviceHallId;
		}

		/**
		 * @param serviceHallId 要设置的 serviceHallId
		 */
		public void setServiceHallId(String serviceHallId) {
			this.serviceHallId = serviceHallId;
		}

		/**
		 * @return agencyName
		 */
		public String getAgencyName() {
			return agencyName;
		}

		/**
		 * @param agencyName 要设置的 agencyName
		 */
		public void setAgencyName(String agencyName) {
			this.agencyName = agencyName;
		}

		/**
		 * @return agencyCode
		 */
		public String getAgencyCode() {
			return agencyCode;
		}

		/**
		 * @param agencyCode 要设置的 agencyCode
		 */
		public void setAgencyCode(String agencyCode) {
			this.agencyCode = agencyCode;
		}

		/**
		 * @return config
		 */
		public ServiceHallDeviceConfig getConfig() {
			return config;
		}

		/**
		 * @param config 要设置的 config
		 */
		public void setConfig(ServiceHallDeviceConfig config) {
			this.config = config;
		}
	
		
	public UserResponse validate() {
		UserResponse response = new UserResponse();
		response.setStatus(-1);
		if(name==null || name=="") {
			response.setMessage("网点名称不能为空！");
		}else if(serviceHallId ==null || serviceHallId=="") {
			response.setMessage("网点编号不能为空！");
		}else if(contact==null || contact=="") {
			response.setMessage("联系人姓名不能为空！");
		}else if(tel==null || tel=="") {
			response.setMessage("联系电话不能为空！");
		}else if(businessHours==null || businessHours=="") {
			response.setMessage("营业时间不能为空！");
		}else if(address==null || address =="") {
			response.setMessage("地址不能为空！");
		}else if(serviceHallId.length()!= 19) {
			response.setMessage("网点编号长度必须为19位！");
		}else if(!isNumeric(serviceHallId)) {
			response.setMessage("网点编号不能包含符号！");
		}
		else {
			response.setStatus(1);
		}
		
		return response;
	}
	

	public boolean isNumeric(String str){ 
	   Pattern pattern = Pattern.compile("[0-9]*"); 
	   Matcher isNum = pattern.matcher(str);
	   if( !isNum.matches() ){
	       return false; 
	   } 
	   return true; 
	}

}
