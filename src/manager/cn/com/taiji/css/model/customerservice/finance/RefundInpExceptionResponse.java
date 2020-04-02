package cn.com.taiji.css.model.customerservice.finance;

import cn.com.taiji.common.model.BaseModel;

public class RefundInpExceptionResponse extends BaseModel {
	
	//卡号
	private String cardId;
	//错误信息
	private String errorMsg;
	//状态 1成功  0失败
	private Integer status;
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
