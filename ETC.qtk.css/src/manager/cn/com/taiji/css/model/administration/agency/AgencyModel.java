/**
 * @Title StaffModel.java
 * @Package cn.com.taiji.css.model.administration.staff
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月30日 下午2:47:26
 * @version V1.0
 */
package cn.com.taiji.css.model.administration.agency;

import java.util.Calendar;

import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.dict.AgencyType;

/**
 * @ClassName StaffModel
 * @Description TODO
 * @author yaonl
 * @date 2018年08月30日 14:47:26
 * @E_mail yaonanlin@163.com
 */
public class AgencyModel extends BaseModel {
	
		private String id;
		// 发行方编号
		private String issuerId;
		// 资金方编号
		private String accountId;
		// 客服合作机构编号
		private String agencyId;
		// 合作机构类别
		private AgencyType type;
		// 机构名称
		private String name;
		// 联系人姓名
		private String contact;
		// 联系电话
		private String tel;
		// 纳税人识别号
		private String taxPayerCode;
		// 统一社会信用代码/组织机构代码证号
		private String creditCode;
		// 地址
		private String address;
		// 备注
		private String info;
		// 录入时间
		private Calendar createTime;
		//起始时间
		private String startTime;
		//终止日期
		private String endTime;
		//老系统-接口用户名
		private String HGUserName;
		//老系统-接口密码
		private String HGPassWord;
		//老系统-流水编号
		private String serialNo;
		//老系统-发行方编号
		private String issueNo;
		//老系统-保证金编号
		private String packageNo;
		//老系统-记账编号
		private String accountNo;
		//流水文件目录名
		private String fileDir;
		private Agency account;
		private Agency agency;
		
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public Agency getAccount() {
			return account;
		}
		public void setAccount(Agency account) {
			this.account = account;
		}
		public Agency getAgency() {
			return agency;
		}
		public void setAgency(Agency agency) {
			this.agency = agency;
		}
		public String getIssuerId() {
			return issuerId;
		}
		public void setIssuerId(String issuerId) {
			this.issuerId = issuerId;
		}
		public String getAccountId() {
			return accountId;
		}
		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}
		public String getAgencyId() {
			return agencyId;
		}
		public void setAgencyId(String agencyId) {
			this.agencyId = agencyId;
		}
		public AgencyType getType() {
			return type;
		}
		public void setType(AgencyType type) {
			this.type = type;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getContact() {
			return contact;
		}
		public void setContact(String contact) {
			this.contact = contact;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getTaxPayerCode() {
			return taxPayerCode;
		}
		public void setTaxPayerCode(String taxPayerCode) {
			this.taxPayerCode = taxPayerCode;
		}
		public String getCreditCode() {
			return creditCode;
		}
		public void setCreditCode(String creditCode) {
			this.creditCode = creditCode;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		public Calendar getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Calendar createTime) {
			this.createTime = createTime;
		}
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public String getEndTime() {
			return endTime;
		}
		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
		public String getHGUserName() {
			return HGUserName;
		}
		public void setHGUserName(String hGUserName) {
			HGUserName = hGUserName;
		}
		public String getHGPassWord() {
			return HGPassWord;
		}
		public void setHGPassWord(String hGPassWord) {
			HGPassWord = hGPassWord;
		}
		public String getSerialNo() {
			return serialNo;
		}
		public void setSerialNo(String serialNo) {
			this.serialNo = serialNo;
		}
		public String getIssueNo() {
			return issueNo;
		}
		public void setIssueNo(String issueNo) {
			this.issueNo = issueNo;
		}
		public String getPackageNo() {
			return packageNo;
		}
		public void setPackageNo(String packageNo) {
			this.packageNo = packageNo;
		}
		public String getAccountNo() {
			return accountNo;
		}
		public void setAccountNo(String accountNo) {
			this.accountNo = accountNo;
		}
		public String getFileDir() {
			return fileDir;
		}
		public void setFileDir(String fileDir) {
			this.fileDir = fileDir;
		}
		public AgencyModel toSet(Agency agency2, Agency account2) {
			AgencyModel agencyModel=new AgencyModel();
			agencyModel.setAccount(account2);
			agencyModel.setAgency(agency2);
			agencyModel.setId(agency2.getId());
			agencyModel.setAgencyId(agency2.getAgencyId());
			agencyModel.setAccountId(agency2.getAccountId());
			agencyModel.setName(agency2.getName());
			agencyModel.setContact(agency2.getContact());
			agencyModel.setTel(agency2.getTel());
			agencyModel.setStartTime(agency2.getStartTime());
			agencyModel.setEndTime(agency2.getEndTime());
			agencyModel.setAddress(agency2.getAddress());
			agencyModel.setHGUserName(agency2.getHGUserName());
			agencyModel.setHGPassWord(agency2.getHGPassWord());
			agencyModel.setSerialNo(agency2.getSerialNo());
			agencyModel.setIssueNo(agency2.getIssueNo());
			agencyModel.setPackageNo(agency2.getPackageNo());
			agencyModel.setAccountNo(agency2.getAccountNo());
			agencyModel.setFileDir(agency2.getFileDir());
			
			return agencyModel;
		}
		
		
}

