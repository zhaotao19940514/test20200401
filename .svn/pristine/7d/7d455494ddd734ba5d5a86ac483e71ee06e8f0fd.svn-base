/**
 * @Title RechargeRequest.java
 * @Package cn.com.taiji.css.model.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:18:48
 * @version V1.0
 */
package cn.com.taiji.css.model.customerservice.cardobuquery;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.qtk.entity.CardInfo;

/**
 * @ClassName RechargeRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:18:48
 * @E_mail yaonanlin@163.com
 */
public class CardRequest extends JpaPageableDataRequest<CardInfo> {
	//客户编号
	private String customerId;
	//车辆编号
	private String vehicleId;
	// 车牌号
	private String vehiclePlate;
	// 车牌颜色
	private Integer vehiclePlateColor;
	//合作机构编号/网点编号
	private String agencyId;
	//卡号
	private String cardId;
	//卡类型
	private Integer cardType;
	//卡状态
	private Integer status;
	
	public CardRequest(){
		this.orderBy="enableTime desc, id";
		this.desc=true;
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public HqlBuilder toSelectHql() {
		if(vehiclePlate != null && vehiclePlateColor != null) {
			vehicleId = vehiclePlate + "_" + vehiclePlateColor;
		}
		HqlBuilder hql = new HqlBuilder("from " + CardInfo.class.getName() + " where 1=1 ");
		hql.append(" and customerId=:customerId", customerId);
		hql.append(" and vehicleId=:vehicleId", vehicleId);
		if(vehicleId == null) {
			hql.append(" and substr(vehicleId,1,instr(vehicleId,'_',-1,1)-1)=:vehiclePlate", vehiclePlate);
			if(vehiclePlateColor != null)
			hql.append(" and substr(vehicleId,instr(vehicleId,'_',-1,1)+1)=:vehiclePlateColor", vehiclePlateColor.toString());
		}
		hql.append(" and agencyId=:agencyId", agencyId);
		hql.append(" and cardId=:cardId", cardId);
		if(cardType != null)
		hql.append(" and substr(cardType,0,1)=:cardType", cardType.toString());
		hql.append(" and status=:status", status);
		return hql;
	}
}

