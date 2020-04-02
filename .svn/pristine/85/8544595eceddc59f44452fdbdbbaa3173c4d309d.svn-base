/**
 * @Title TestApplyCardModel.java
 * @Package cn.com.taiji.css.model.ocxtest
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月12日 下午4:33:47
 * @version V1.0
 */
package cn.com.taiji.css.model.ocxtest;

import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.common.validation.MyViolationException;

/**
 * @ClassName TestApplyCardModel
 * @Description TODO
 * @author yaonl
 * @date 2018年07月12日 16:33:47
 * @E_mail yaonanlin@163.com
 */
public class TestApplyCardRequest extends BaseModel {
	private Integer applyStep;//请求到第几次
	private String cosResponse;
	private String command;
	private String cosRecordId;
	private String cardId;
	private String userId;
	private Integer cardType;
	private String enableTime;
	private String expireTime;
	private String vehicleId;
	private String pkgId;
	private String netId;
	public String getNetId() {
		return netId;
	}
	public void setNetId(String netId) {
		this.netId = netId;
	}
	public String getPkgId() {
		return pkgId;
	}
	public void setPkgId(String pkgId) {
		this.pkgId = pkgId;
	}
	public Integer getApplyStep() {
		return applyStep;
	}
	public String getCosResponse() {
		return cosResponse;
	}
	public String getCommand() {
		return command;
	}
	public String getCosRecordId() {
		return cosRecordId;
	}
	public String getCardId() {
		return cardId;
	}
	public String getUserId() {
		return userId;
	}
	public String getEnableTime() {
		return enableTime;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setApplyStep(Integer applyStep) {
		this.applyStep = applyStep;
	}
	public void setCosResponse(String cosResponse) {
		this.cosResponse = cosResponse;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public void setCosRecordId(String cosRecordId) {
		this.cosRecordId = cosRecordId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setEnableTime(String enableTime) {
		this.enableTime = enableTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public void validate() {
		MyViolationException mve = new MyViolationException();
		if(applyStep==null){
			mve.addViolation("applyStep", "applyStep不能为空");
		}
	}
	
}

