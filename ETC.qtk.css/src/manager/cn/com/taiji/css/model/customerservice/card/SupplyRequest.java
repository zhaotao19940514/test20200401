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
 * @ClassName SupplyRequest.java
 * @author zhaotao
 * @Description 
 * @date2019年2月26日
 */
public class SupplyRequest extends JpaDateTimePageableDataRequest<CardInfo> {
	private String vehicleId;
	private String cardId;
	private String initId;
	private String newCardId;
	private String packageType;
	private String cardType;
	private String supReason;
	private String agencyId;
	private String vehicleColor;
	private String applyStep;
	
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
	
	public String getInitId() {
		return initId;
	}
	public void setInitId(String initId) {
		this.initId = initId;
	}
	
	public String getVehicleColor() {
		return vehicleColor;
	}
	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	public String getNewCardId() {
		return newCardId;
	}
	public void setNewCardId(String newCardId) {
	}
	
	public String getApplyStep() {
		return applyStep;
	}
	public void setApplyStep(String applyStep) {
		this.applyStep = applyStep;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	
	public String getSupReason() {
		return supReason;
	}
	public void setSupReason(String supReason) {
		this.supReason = supReason;
	}
	
	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
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

