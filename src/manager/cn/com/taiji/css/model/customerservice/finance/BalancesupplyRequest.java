/**
 * @Title RechargeRequest.java
 * @Package cn.com.taiji.css.model.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:18:48
 * @version V1.0
 */
package cn.com.taiji.css.model.customerservice.finance;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.manager.util.MyPatterns;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;


public class BalancesupplyRequest extends JpaDateTimePageableDataRequest<CardInfo> {
	private String com;
	private String vehicleId;
	private String oldCardId;
	private String initId;
	private Long preBalance;
	private String newCardId;
	private Long cardBalance;
	private Integer cosProvider;
	private Long oldPreBalance;
	private Long newPreBalance;
	//判断有卡还是无卡
	private Integer cardStatus;
	// 车牌号
	private String vehiclePlate;

	// 车牌颜色
	private Integer vehiclePlateColor;
	//客服合作机构编号
	private String agencyId;

	//网点编号
	private  String serviceHallId;
	
	private String customerId;
	
	
	
	
	
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	

	public String getOldCardId() {
		return oldCardId;
	}
	public void setOldCardId(String oldCardId) {
		this.oldCardId = oldCardId;
	}
	public String getInitId() {
		return initId;
	}
	public void setInitId(String initId) {
		this.initId = initId;
	}
	
	
	public Long getPreBalance() {
		return preBalance;
	}
	public void setPreBalance(Long preBalance) {
		this.preBalance = preBalance;
	}
	
	public String getNewCardId() {
		return newCardId;
	}
	public void setNewCardId(String newCardId) {
		this.newCardId = newCardId;
	}
	
	
	public Long getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(Long cardBalance) {
		this.cardBalance = cardBalance;
	}
	public Integer getCosProvider() {
		return cosProvider;
	}
	public void setCosProvider(Integer cosProvider) {
		this.cosProvider = cosProvider;
	}
	
	
	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public String getServiceHallId() {
		return serviceHallId;
	}
	public void setServiceHallId(String serviceHallId) {
		this.serviceHallId = serviceHallId;
	}
	
	
	
	public Long getOldPreBalance() {
		return oldPreBalance;
	}
	public void setOldPreBalance(Long oldPreBalance) {
		this.oldPreBalance = oldPreBalance;
	}
	public Long getNewPreBalance() {
		return newPreBalance;
	}
	public void setNewPreBalance(Long newPreBalance) {
		this.newPreBalance = newPreBalance;
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
	
	
	public Integer getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(Integer cardStatus) {
		this.cardStatus = cardStatus;
	}
	public void validate() {
		MyViolationException mve = new MyViolationException();
		if(oldCardId==null) {
		if (vehiclePlate == null) {
			mve.addViolation("oldCardId", "不能全部为空！");
			mve.addViolation("vehiclePlate", "不能全部为空！");
		}else {
			if(!MyPatterns.checkPlateNumFormat(vehiclePlate)) mve.addViolation("vehiclePlate", "格式不正确！");
			if (vehiclePlateColor == null) {
				mve.addViolation("vehiclePlateColor", "不能为空！");
			}else {
				if(VehiclePlateColorType.valueOfCode(vehiclePlateColor) == null)
					mve.addViolation("vehiclePlateColor", "没有此车牌颜色类型！"+vehiclePlateColor);
		}
		}
		}
		if (mve.hasViolation()) throw mve;
	}
	
	public void validateForSupply() {
		MyViolationException mve = new MyViolationException();
		if(oldCardId==null) {
		if (vehiclePlate == null) {
			mve.addViolation("oldCardId", "不能全部为空！");
			mve.addViolation("vehiclePlate", "不能全部为空！");
		}else {
			if(!MyPatterns.checkPlateNumFormat(vehiclePlate)) mve.addViolation("vehiclePlate", "格式不正确！");
			if (vehiclePlateColor == null) {
				mve.addViolation("vehiclePlateColor", "不能为空！");
			}else {
				if(VehiclePlateColorType.valueOfCode(vehiclePlateColor) == null)
					mve.addViolation("vehiclePlateColor", "没有此车牌颜色类型！"+vehiclePlateColor);
		}
		}
		}
		if(newCardId==null) {
			mve.addViolation("newCardId", "不能为空！");
		}
		if (mve.hasViolation()) throw mve;
	}
	
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "CardInfo " +
				"  where 1=1 ");
		hql.append(" and agencyId =:agencyId",agencyId);
		hql.append(" and vehicleId =:vehicleId",vehicleId);
		hql.append(" and cardId=:cardId", oldCardId);
		return hql;
	}
	
	
	
}

