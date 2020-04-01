package cn.com.taiji.css.model.administration.deposit;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.CardInfo;

/**
 * @ClassName SupplyPaymentRequest.java
 * @author zhaotao
 * @Description 
 * @date2019年1月7日
 */
public class SupplyPaymentRequest extends JpaDateTimePageableDataRequest<CardInfo> {
	private String cardId;
	private Long cardBalance;
	private String vehicleId;
	private Integer vehicleColor;
	private Long payBalance;
	
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	public Long getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(Long cardBalance) {
		this.cardBalance = cardBalance;
	}
	

	public Long getPayBalance() {
		return payBalance;
	}
	public void setPayBalance(Long payBalance) {
		this.payBalance = payBalance;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Integer getVehicleColor() {
		return vehicleColor;
	}
	public void setVehicleColor(Integer vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	public void validate() {
		MyViolationException mve = new MyViolationException();
		if(null==cardId&&null==vehicleId&&null==vehicleColor)mve.addViolation("cardId", "请填写卡号");
		if(null!=vehicleId&&null==vehicleColor) mve.addViolation("vehicleColor", "请选择车牌颜色");
		if(null==vehicleId&&null!=vehicleColor) mve.addViolation("vehicleColor", "请填写车牌号");
		if (mve.hasViolation()) throw mve;
		if(null!=vehicleId&&null!=vehicleColor) {
			vehicleId=vehicleId+"_"+vehicleColor;
		}
	}
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "CardInfo " +
				"  where 1=1 ");
		hql.append(" and cardId=:cardId", cardId);
		return hql;
	}
}

