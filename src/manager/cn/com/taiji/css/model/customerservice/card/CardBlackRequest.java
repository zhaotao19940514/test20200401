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
import cn.com.taiji.qtk.entity.BlackCard;

/**
 * @ClassName RechargeRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:18:48
 * @E_mail yaonanlin@163.com
 */
public class CardBlackRequest extends JpaDateTimePageableDataRequest<BlackCard> {
	private String vehiclePlate;
	private String vehiclePlateColor;
	private String cardId;
	private String vehicleId;
	private Integer type;
	private String creationTime;
	private Integer cardBlackQueryType;
	
	
	

	public Integer getCardBlackQueryType() {
		return cardBlackQueryType;
	}
	public void setCardBlackQueryType(Integer cardBlackQueryType) {
		this.cardBlackQueryType = cardBlackQueryType;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public String getVehiclePlateColor() {
		return vehiclePlateColor;
	}
	public void setVehiclePlateColor(String vehiclePlateColor) {
		this.vehiclePlateColor = vehiclePlateColor;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public void validate() {
		MyViolationException mve = new MyViolationException();		
		if(cardId == null&&null==vehiclePlate&&null==vehiclePlateColor) mve.addViolation("cardId", "请填写卡号");
		if(cardId != null&&null!=vehiclePlate&&null==vehiclePlateColor) mve.addViolation("vehicleColor", "请选择车牌颜色");
		if(cardId == null&&null!=vehiclePlate&&null==vehiclePlateColor) mve.addViolation("vehicleColor", "请选择车牌颜色");
		if(cardId != null&&null==vehiclePlate&&null!=vehiclePlateColor) mve.addViolation("vehicleId", "请填写车牌号");
		if(cardId == null&&null==vehiclePlate&&null!=vehiclePlateColor) mve.addViolation("vehicleId", "请填写车牌号");
		if (mve.hasViolation()) throw mve;
		if(null!=vehiclePlate&&null!=vehiclePlateColor) {
			vehicleId=vehiclePlate+"_"+vehiclePlateColor;
		}
	}
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "BlackCard " +
				"  where 1=1 ");
		hql.append(" and cardId=:cardId", cardId);
		return hql;
	}
}

