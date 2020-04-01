package cn.com.taiji.css.model.administration.notice;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.BankSignDetail;
import cn.com.taiji.qtk.entity.CardInfo;

/**
 * 
 *@ClassName NoticeRequest.java
 *@Description: 
 *@author:zhaot
 *@date: 2020年2月28日
 */
public class NoticeRequest extends JpaDateTimePageableDataRequest<BankSignDetail> {
	private String vehiclePlate;
	public String getVehiclePlate() {
		return vehiclePlate;
	}
	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}
	public void validate() {
		MyViolationException mve = new MyViolationException();
//		if(null==cardId&&null==vehicleId&&null==vehicleColor)mve.addViolation("cardId", "请填写卡号");
//		if(null!=vehicleId&&null==vehicleColor) mve.addViolation("vehicleColor", "请选择车牌颜色");
//		if(null==vehicleId&&null!=vehicleColor) mve.addViolation("vehicleColor", "请填写车牌号");
//		if (mve.hasViolation()) throw mve;
//		if(null!=vehicleId&&null!=vehicleColor) {
//			vehicleId=vehicleId+"_"+vehicleColor;
//		}
	}
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "BankSignDetail " +
				"  where 1=1 ");
		hql.append(" and vehiclePlate=:vehiclePlate", vehiclePlate);
		return hql;
	}
}

