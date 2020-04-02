package cn.com.taiji.css.model.apply.quickapply;

import cn.com.taiji.common.model.BaseModel;

public class CardObuBindingRequest extends BaseModel {
	private String cardId;
	private String obuId;
	private Integer applyStep;//请求到第几次
	private String cosResponse;
	private String command;
	private String cosRecordId;
	
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getObuId() {
		return obuId;
	}
	public void setObuId(String obuId) {
		this.obuId = obuId;
	}
	public Integer getApplyStep() {
		return applyStep;
	}
	public void setApplyStep(Integer applyStep) {
		this.applyStep = applyStep;
	}
	public String getCosResponse() {
		return cosResponse;
	}
	public void setCosResponse(String cosResponse) {
		this.cosResponse = cosResponse;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getCosRecordId() {
		return cosRecordId;
	}
	public void setCosRecordId(String cosRecordId) {
		this.cosRecordId = cosRecordId;
	}
	
}
