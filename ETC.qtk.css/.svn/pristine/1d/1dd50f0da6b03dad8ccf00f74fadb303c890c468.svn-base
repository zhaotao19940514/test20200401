package cn.com.taiji.css.model.apply.customermanager;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.qtk.entity.CustomerInfo;

public class EgCustomerManagerRequest extends JpaPageableDataRequest<CustomerInfo> {
	//应急车辆用户标识 0-非应急车辆用户  1-应急车辆用户
	private Integer emergencyFlag;
	// 客户编号
	private String customerId;
	// 客户证件类型
	private Integer customerIdType;
	// 客户证件号码
	private String customerIdNum;
	// 指定经办人姓名
	private String agentName;
	// 客户名称
	private String customerName;
	// 渠道id
	private String agencyId;
	// 新密码
	private String newPassWord;
	// 用户输入的旧密码
	private String password;
	// 库内的旧密码
	private String oldPassWord;
	// 开户人/指定经办人电号码
	private String tel;
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public EgCustomerManagerRequest(){
		this.orderBy="customerIdNum desc, id";
		this.desc=true;
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

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	
	

	/**
	 * @return newPassWord
	 */
	public String getNewPassWord() {
		return newPassWord;
	}

	/**
	 * @param newPassWord 要设置的 newPassWord
	 */
	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password 要设置的 password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	

	/**
	 * @return oldPassWord
	 */
	public String getOldPassWord() {
		return oldPassWord;
	}

	/**
	 * @param oldPassWord 要设置的 oldPassWord
	 */
	public void setOldPassWord(String oldPassWord) {
		this.oldPassWord = oldPassWord;
	}

	public Integer getEmergencyFlag() {
		return emergencyFlag;
	}

	public void setEmergencyFlag(Integer emergencyFlag) {
		this.emergencyFlag = emergencyFlag;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + CustomerInfo.class.getName() + " where 1=1 ");
		hql.append(" and emergencyFlag=:emergencyFlag", emergencyFlag);
		hql.append(" and customerId=:customerId", customerId);
		hql.append(" and customerIdType=:customerIdType", customerIdType);
		hql.append(" and customerIdNum=:customerIdNum", customerIdNum);
		hql.append(" and agentName=:agentName", agentName);
		hql.append(" and customerName like:customerName", like(customerName));
		hql.append(" and tel=:tel", tel);
		hql.append(" and substr(channelId,0,11)=:agencyId", agencyId);
		return hql;
	}
}
