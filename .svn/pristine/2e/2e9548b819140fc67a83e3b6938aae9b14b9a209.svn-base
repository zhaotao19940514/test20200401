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
import cn.com.taiji.qtk.entity.CardInfo;

/**
 * @ClassName RechargeRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:18:48
 * @E_mail yaonanlin@163.com
 */
public class PreCancelRequest extends JpaDateTimePageableDataRequest<CardInfo> {
	private String vehicleId;
	private String cardId;
	private Integer provider;
	private String command;
	private Integer applyStep;
	private String cosRecordId;
	private String cosResponse;
	private String vehicleColor;
	private String agencyId;
	private int orderStatus;
	private String balanceType;
	private Integer cardType;
	private String bankCardId;
	private long cardBalance;
	private Integer bankType;
	private Integer cusType;
	private String cusName;
	private String cusTel;
	private String province;
	private String sell;
	//注销或补换标识  false表示补换卡 true注销
	private boolean supplyOrCancel;
	private Integer refundType;
	
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
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
	
	public Integer getApplyStep() {
		return applyStep;
	}
	public void setApplyStep(Integer applyStep) {
		this.applyStep = applyStep;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	
	public String getCosRecordId() {
		return cosRecordId;
	}
	public void setCosRecordId(String cosRecordId) {
		this.cosRecordId = cosRecordId;
	}
	
	
	public long getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(long cardBalance) {
		this.cardBalance = cardBalance;
	}
	public String getBalanceType() {
		return balanceType;
	}
	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getCosResponse() {
		return cosResponse;
	}
	public void setCosResponse(String cosResponse) {
		this.cosResponse = cosResponse;
	}
	
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	
	
	public String getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}
	public Integer getBankType() {
		return bankType;
	}
	public void setBankType(Integer bankType) {
		this.bankType = bankType;
	}
	public Integer getCusType() {
		return cusType;
	}
	public void setCusType(Integer cusType) {
		this.cusType = cusType;
	}
	public String getCusName() {
		return cusName;
	}
	
	public boolean isSupplyOrCancel() {
		return supplyOrCancel;
	}
	public void setSupplyOrCancel(boolean supplyOrCancel) {
		this.supplyOrCancel = supplyOrCancel;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusTel() {
		return cusTel;
	}
	public void setCusTel(String cusTel) {
		this.cusTel = cusTel;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getSell() {
		return sell;
	}
	public void setSell(String sell) {
		this.sell = sell;
	}
	public Integer getRefundType() {
		return refundType;
	}
	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
	}
	public void validate() {
		MyViolationException mve = new MyViolationException();		
		if(cardId == null&&null==vehicleId&&null==vehicleColor) mve.addViolation("cardId", "请填写卡号");
		if(cardId!= null&&null!=vehicleId&&null==vehicleColor) mve.addViolation("vehicleColor", "请选择车牌颜色");
		if(cardId== null&&null!=vehicleId&&null==vehicleColor) mve.addViolation("vehicleColor", "请选择车牌颜色");
		if(cardId!= null&&null==vehicleId&&null!=vehicleColor) mve.addViolation("vehicleId", "请填写车牌号");
		if(cardId == null&&null==vehicleId&&null!=vehicleColor) mve.addViolation("vehicleId", "请填写车牌号");
		if (mve.hasViolation()) throw mve;
		if(null!=vehicleId&&null!=vehicleColor) {
			vehicleId=vehicleId+"_"+vehicleColor;
		}
	}
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "CardInfo " +
				"  where 1=1 ");
		hql.append(" and vehicleId=:vehicleId",vehicleId);
		hql.append(" and cardId=:cardId", cardId);
		if(null!=agencyId&&!"".equals(agencyId)) {
			hql.append(" and agencyId=:agencyId", agencyId);
		}
				return hql;
	}
}

