/**
 * @Title CssOperateLogRequest.java
 * @Package cn.com.taiji.css.model.system
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月11日 下午2:12:12
 * @version V1.0
 */
package cn.com.taiji.css.model.system.request;

import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.entity.OperateLog;
import cn.com.taiji.css.entity.dict.CssOperateLogType;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;

/**
 * @ClassName CssOperateLogRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年08月11日 14:12:12
 * @E_mail yaonanlin@163.com
 */
public class CssOperateLogRequest extends JpaPageableDataRequest<OperateLog> {
	private String startTime;// yyyy-MM-dd
	private String endTime;
	private CssServiceLogType serviceType;
	private CssOperateLogType operateType;
	private String username;
	private String relatedCardId;
	private String relatedObuId;
	private String relatedCustomerId;
	private VehiclePlateColorType plateColor;
	private String plateNum;
	private String discription;
	private String remoteIp;
	private String relatedRechargeId;
	private String data;
	
	public CssOperateLogRequest() {
		super();
		super.setOrderBy("operateTime");
		super.setDesc(true);
	}

	@Override
	public HqlBuilder toSelectHql() {
		String relatedVehicleId = null;
		if (StringTools.hasText(plateNum) && plateColor != null) {
			relatedVehicleId = plateNum.concat("_").concat(String.valueOf(plateColor.getTypeCode()));
		}
		HqlBuilder hql = new HqlBuilder("from OperateLog where 1=1 ");
		//下面的时间写法用来绕开框架的一个BUG 框架正则会捕获冒号及单词的组合 并存为key-value 而时间格式的:mi并非参数但多次使用 所以会报错
		hql.append(" and to_date(operateTime,'yyyy-MM-dd HH24").append(":").append("mi").append(":").append("ss') ");
		hql.append(" >= to_date(:startTime,'yyyy-MM-dd HH24",startTime).append(":").append("mi").append(":").append("ss') ");
		hql.append(" and to_date(operateTime,'yyyy-MM-dd HH24").append(":").append("mi").append(":").append("ss') ");
		hql.append(" <= to_date(:endTime,'yyyy-MM-dd HH24",endTime).append(":").append("mi").append(":").append("ss') ");
		if (serviceType != CssServiceLogType.ALL) {
			hql.append(" and serviceType = :serviceType", serviceType);
		}
		if (operateType != CssOperateLogType.ALL) {
			hql.append(" and operateType = :operateType", operateType);
		}
		hql.append(" and username = :username", username);
		hql.append(" and relatedRechargeId = :relatedRechargeId", relatedRechargeId);
		hql.append(" and relatedCardId = :relatedCardId", relatedCardId);
		hql.append(" and relatedVehicleId = :relatedVehicleId", relatedVehicleId);
		hql.append(" and relatedObuId = :relatedObuId", relatedObuId);
		hql.append(" and relatedCustomerId = :relatedCustomerId", relatedCustomerId);
		hql.append(" and remoteIp = :remoteIp", remoteIp);
		hql.append(" and discription like :discription", like(discription));
		hql.append(" and data like :data", like(data));
		return hql;
	}

	public String getRelatedRechargeId() {
		return relatedRechargeId;
	}

	public void setRelatedRechargeId(String relatedRechargeId) {
		this.relatedRechargeId = relatedRechargeId;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public CssServiceLogType getServiceType() {
		return serviceType;
	}

	public CssOperateLogType getOperateType() {
		return operateType;
	}

	public String getUsername() {
		return username;
	}

	public String getRelatedCardId() {
		return relatedCardId;
	}

	public String getRelatedObuId() {
		return relatedObuId;
	}

	public String getRelatedCustomerId() {
		return relatedCustomerId;
	}

	public VehiclePlateColorType getPlateColor() {
		return plateColor;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public String getDiscription() {
		return discription;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setServiceType(CssServiceLogType serviceType) {
		this.serviceType = serviceType;
	}

	public void setOperateType(CssOperateLogType operateType) {
		this.operateType = operateType;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRelatedCardId(String relatedCardId) {
		this.relatedCardId = relatedCardId;
	}

	public void setRelatedObuId(String relatedObuId) {
		this.relatedObuId = relatedObuId;
	}

	public void setRelatedCustomerId(String relatedCustomerId) {
		this.relatedCustomerId = relatedCustomerId;
	}

	public void setPlateColor(VehiclePlateColorType plateColor) {
		this.plateColor = plateColor;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void valid() {
		MyViolationException mve = new MyViolationException();
		if (!StringTools.hasText(startTime)) {
			mve.addViolation("startTime", "日志起始时间不能为空");
		}
		if (!StringTools.hasText(endTime)) {
			mve.addViolation("endTime", "日志结束时间不能为空");
		}
		if (plateColor != null || StringTools.hasText(plateNum)) {
			if (!StringTools.hasText(plateNum)) {
				mve.addViolation("plateNum", "车牌颜色不能为空");
			}
			if (plateColor == null) {
				mve.addViolation("plateColor", "车牌颜色不能为空");
			}
		}
		if (mve.hasViolation()) {
			throw mve;
		}
	}
}
