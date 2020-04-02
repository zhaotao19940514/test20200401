/**
 * @Title RechargeRequest.java
 * @Package cn.com.taiji.css.model.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:18:48
 * @version V1.0
 */
package cn.com.taiji.css.model.customerservice.card;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.CancelledCardDetail;
import cn.com.taiji.qtk.entity.dict.AccountCardBalanceOperateType;

/**
 * @ClassName RechargeRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:18:48
 * @E_mail yaonanlin@163.com
 */
public class ManCancelRequest extends JpaDateTimePageableDataRequest<CancelledCardDetail> {
	private String vehicleId;
	private String cardId;
	private String cardChannel;
	private String agencyId;
	private String vehicleColor;
	private Integer provider;
	private String customerName;
	private String customerIdNum;
	private String bankcard;
	private AccountCardBalanceOperateType balanceType;
	private String customerId;
	private long cardBalance;
	
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	public String getCardChannel() {
		return cardChannel;
	}
	public void setCardChannel(String cardChannel) {
		this.cardChannel = cardChannel;
	}
	
	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	
	public String getVehicleColor() {
		return vehicleColor;
	}
	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	
	public Integer getProvider() {
		return provider;
	}
	public void setProvider(Integer provider) {
		this.provider = provider;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerIdNum() {
		return customerIdNum;
	}
	public void setCustomerIdNum(String customerIdNum) {
		this.customerIdNum = customerIdNum;
	}
	
	public AccountCardBalanceOperateType getBalanceType() {
		return balanceType;
	}
	public void setBalanceType(AccountCardBalanceOperateType balanceType) {
		this.balanceType = balanceType;
	}
	
	public long getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(long cardBalance) {
		this.cardBalance = cardBalance;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
	public String getBankcard() {
		return bankcard;
	}
	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}
	public void validate() {
		MyViolationException mve = new MyViolationException();
		if(null==cardId&&null==customerIdNum&&null==vehicleId&&null==vehicleColor)mve.addViolation("cardId", "请填写卡号");
		//if(null==customerIdNum) mve.addViolation("customerIdNum", "请填写证件号");
		if(null==vehicleId&&null!=vehicleColor) mve.addViolation("vehicleId", "请填写车牌号");
		if(null!=vehicleId&&null==vehicleColor) mve.addViolation("vehicleColor", "请填写车牌颜色");
		if (mve.hasViolation()) throw mve;
		if(null!=vehicleId&&null!=vehicleColor) {
			vehicleId=vehicleId+"_"+vehicleColor;
		}
	}
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "CancelledCardDetail " +
				"  where 1=1 ");
		hql.append(" and cardId=:cardId", cardId);
		hql.append(" and vehicleId=:vehicleId", vehicleId);
		/*hql.append(" and user_idnum=:customerIdNum", customerIdNum);*/
		return hql;
	}
}

