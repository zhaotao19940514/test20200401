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
import cn.com.taiji.qtk.entity.OBUInfo;

/**
 * @ClassName RechargeRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:18:48
 * @E_mail yaonanlin@163.com
 */
public class ExchangeRequest extends JpaDateTimePageableDataRequest<OBUInfo> {
	private String vehicleId;
	private String obuId;
	private String oldObuId;
	private Integer type;
	private String customerId;
	private boolean applyOrChange;
	private Integer vehicleColor;
	private String newObuId;
	private Integer obuStatus;
	private Integer supReason;
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
	
	
	
	public Integer getObuStatus() {
		return obuStatus;
	}
	public void setObuStatus(Integer obuStatus) {
		this.obuStatus = obuStatus;
	}
	public String getNewObuId() {
		return newObuId;
	}
	public void setNewObuId(String newObuId) {
		this.newObuId = newObuId;
	}
	public Integer getVehicleColor() {
		return vehicleColor;
	}
	public void setVehicleColor(Integer vehicleColor) {
		this.vehicleColor = vehicleColor;
	} 
	public boolean isApplyOrChange() {
		return applyOrChange;
	}
	public void setApplyOrChange(boolean applyOrChange) {
		this.applyOrChange = applyOrChange;
	}
	public String getOldObuId() {
		return oldObuId;
	}
	public void setOldObuId(String oldObuId) {
		this.oldObuId = oldObuId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public Integer getSupReason() {
		return supReason;
	}
	public void setSupReason(Integer supReason) {
		this.supReason = supReason;
	}
	public void validate() {
		MyViolationException mve = new MyViolationException();		
		if(obuId == null&&null==vehicleId&&null==vehicleColor) mve.addViolation("obuId", "请填写OBU序列号");
		if(obuId!= null&&null!=vehicleId&&null==vehicleColor) mve.addViolation("vehicleColor", "请选择车牌颜色");
		if(obuId== null&&null!=vehicleId&&null==vehicleColor) mve.addViolation("vehicleColor", "请选择车牌颜色");
		if(obuId!= null&&null==vehicleId&&null!=vehicleColor) mve.addViolation("vehicleId", "请填写车牌号");
		if(obuId == null&&null==vehicleId&&null!=vehicleColor) mve.addViolation("vehicleId", "请填写车牌号");
		if (mve.hasViolation()) throw mve;
		if(null!=vehicleId&&null!=vehicleColor) {
			vehicleId=vehicleId+"_"+vehicleColor;
		}
	}
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "OBUInfo " +
				"  where 1=1 ");
		hql.append(" and vehicleId=:vehicleId", vehicleId);
		hql.append(" and obuId=:obuId", obuId);
				return hql;
	}
	
	
	
	
}

