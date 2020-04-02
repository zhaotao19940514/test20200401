/**
 * @Title RechargeRequest.java
 * @Package cn.com.taiji.css.model.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:18:48
 * @version V1.0
 */
package cn.com.taiji.css.model.customerservice.obu;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.VehicleInfo;

/**
 * @ClassName RechargeRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:18:48
 * @E_mail yaonanlin@163.com
 */
public class OBUTransferRequest extends JpaDateTimePageableDataRequest<VehicleInfo> {
	private String vehicleId;
	private String obuId;
	private Integer vehicleColor;
	private Integer obuStatus;
	private Integer ownerIdType;
	private String ownerIdNum;
	private String ownerName;
	private String enableTime;
	private String expireTime;
	
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getObuId() {
		return obuId;
	}
	public void setObuId(String obuId) {
		this.obuId = obuId;
	}
	
	public Integer getVehicleColor() {
		return vehicleColor;
	}
	public void setVehicleColor(Integer vehicleColor) {
		this.vehicleColor = vehicleColor;
	}
	
	public Integer getObuStatus() {
		return obuStatus;
	}
	public void setObuStatus(Integer obuStatus) {
		this.obuStatus = obuStatus;
	}
	
	public Integer getOwnerIdType() {
		return ownerIdType;
	}
	public void setOwnerIdType(Integer ownerIdType) {
		this.ownerIdType = ownerIdType;
	}
	public String getOwnerIdNum() {
		return ownerIdNum;
	}
	public void setOwnerIdNum(String ownerIdNum) {
		this.ownerIdNum = ownerIdNum;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	public String getEnableTime() {
		return enableTime;
	}
	public void setEnableTime(String enableTime) {
		this.enableTime = enableTime;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public void validate() {
		MyViolationException mve = new MyViolationException();
		/*if(null==vehicleId&&null==vehicleColor&&null==ownerIdType&&null==ownerIdNum) mve.addViolation("vehicleId", "请填写用户名称");*/
		
		if(null!=vehicleId) {
			if(null==vehicleColor) mve.addViolation("vehicleColor", "请填写车牌颜色");
			else if(null!=ownerIdType&&null==ownerIdNum) mve.addViolation("ownerIdNum", "请填写证件号码");
			else if(null==ownerIdType&&null!=ownerIdNum) mve.addViolation("ownerIdType", "请填写证件类型");
		}
		if(null!=vehicleColor) {
			if(null==vehicleId) mve.addViolation("vehicleColor", "请填写车牌号");
			else if(null!=ownerIdType&&null==ownerIdNum) mve.addViolation("ownerIdNum", "请填写证件号码");
			else if(null==ownerIdType&&null!=ownerIdNum)  mve.addViolation("ownerIdType", "请填写证件类型");
		}
		
		if(null!=ownerIdType) {
			if(null==ownerIdNum) mve.addViolation("ownerIdNum", "请填写证件号码");
			else if(null!=vehicleColor&&null==vehicleId) mve.addViolation("vehicleId", "请填写车牌号");
			else if(null==vehicleColor&&null!=vehicleId)  mve.addViolation("vehicleColor", "请填写车牌颜色");
			 
		}
		
		if(null!=ownerIdNum) {
			if(null==ownerIdType) mve.addViolation("ownerIdType", "请填写证件类型");
			else if(null!=vehicleColor&&null==vehicleId) mve.addViolation("vehicleId", "请填写车牌号");
			else if(null==vehicleColor&&null!=vehicleId)  mve.addViolation("vehicleColor", "请填写车牌颜色");
			
		}
		if (mve.hasViolation()) throw mve;
		if(null!=vehicleId&&null!=vehicleColor) {
			vehicleId=vehicleId+"_"+vehicleColor;
		}
	}
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "VehicleInfo " +
				"  where 1=1 ");
		hql.append(" and vehicleId=:vehicleId", vehicleId);
				return hql;
	}
	
	
	
	
	
}

