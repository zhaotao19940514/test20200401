/**
 * @Title OperateLog.java
 * @Package cn.com.taiji.css.entity
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月28日 下午1:21:51
 * @version V1.0
 */
package cn.com.taiji.css.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import cn.com.taiji.common.entity.StringUUIDEntity;
import cn.com.taiji.css.entity.dict.CssOperateLogType;
import cn.com.taiji.css.entity.dict.CssServiceLogType;

/**
 * @ClassName OperateLog
 * @Description TODO
 * @author yaonl
 * @date 2018年07月28日 13:21:51
 * @E_mail yaonanlin@163.com
 */
@Entity
@Table(name="QTK_CSS_OPERATE_LOG")
public class OperateLog extends StringUUIDEntity {
	private String remoteIp;
	private String serverIp;
	private String url;
	private String operatorId;
	private String operateTime;
	private CssServiceLogType serviceType;
	private CssOperateLogType operateType;
	private String operateItem;
	private String data;
	private String createTime;
	private String discription;
	private String username;
	private String serviceName;
	private String relatedCardId;
	private String relatedVehicleId;
	private String relatedObuId;
	private String relatedCustomerId;
	private String relatedRechargeId;
	@Column(name="RELATED_RECHARGE_ID")
	public String getRelatedRechargeId() {
		return relatedRechargeId;
	}
	public void setRelatedRechargeId(String relatedRechargeId) {
		this.relatedRechargeId = relatedRechargeId;
	}
	@Column(name="SERVICE_NAME")
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	@Column(name="USER_NAME")
	public String getUsername() {
		return username;
	}
	@Column(name="REMOTE_IP")
	public String getRemoteIp() {
		return remoteIp;
	}
	@Column(name="SERVER_IP")
	public String getServerIp() {
		return serverIp;
	}
	@Column(name="URL")
	public String getUrl() {
		return url;
	}
	@Column(name="OPERATOR_ID")
	public String getOperatorId() {
		return operatorId;
	}
	@Column(name="OPERATE_TIME")
	public String getOperateTime() {
		return operateTime;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="SERVICE_TYPE")
	public CssServiceLogType getServiceType() {
		return serviceType;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="OPERATE_TYPE")
	public CssOperateLogType getOperateType() {
		return operateType;
	}
	@Column(name="OPERATE_ITEM")
	public String getOperateItem() {
		return operateItem;
	}
	@Column(name="CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}
	@Column(name="DISCRIPTION")
	public String getDiscription() {
		return discription;
	}
	@Column(name="DATA")
	public String getData() {
		return data;
	}
	@Column(name="RELATED_CARD_ID")
	public String getRelatedCardId() {
		return relatedCardId;
	}
	@Column(name="RELATED_VEHICLE_ID")
	public String getRelatedVehicleId() {
		return relatedVehicleId;
	}
	@Column(name="RELATED_OBU_ID")
	public String getRelatedObuId() {
		return relatedObuId;
	}
	@Column(name="RELATED_CUSTOMER_ID")
	public String getRelatedCustomerId() {
		return relatedCustomerId;
	}
	public void setRelatedCardId(String relatedCardId) {
		this.relatedCardId = relatedCardId;
	}
	public void setRelatedVehicleId(String relatedVehicleId) {
		this.relatedVehicleId = relatedVehicleId;
	}
	public void setRelatedObuId(String relatedObuId) {
		this.relatedObuId = relatedObuId;
	}
	public void setRelatedCustomerId(String relatedCustomerId) {
		this.relatedCustomerId = relatedCustomerId;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public void setServiceType(CssServiceLogType serviceType) {
		this.serviceType = serviceType;
	}
	public void setOperateType(CssOperateLogType operateType) {
		this.operateType = operateType;
	}
	public void setOperateItem(String operateItem) {
		this.operateItem = operateItem;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}

