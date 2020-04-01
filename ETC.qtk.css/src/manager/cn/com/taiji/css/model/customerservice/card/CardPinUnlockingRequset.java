package cn.com.taiji.css.model.customerservice.card;

import cn.com.taiji.common.model.BaseModel;

public class CardPinUnlockingRequset extends BaseModel {
	//用于记录卡号，与解锁无关
	private String cardId;
	//执行指令
	private String command;
	//卡执行指令结果
	private String cosResponse;
	/*卡pin码类型  只能是 
	1-  pin为123456,
	2-  pin为313233343536
	不清楚具体值和解锁指令具体含义的请使用该接口上次返回的pinType.*/
	private String pinType;
	/*可以都为空,用于开始执行解锁流程.当command不为空时,其余也不能为空.*/
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getCosResponse() {
		return cosResponse;
	}
	public void setCosResponse(String cosResponse) {
		this.cosResponse = cosResponse;
	}
	public String getPinType() {
		return pinType;
	}
	public void setPinType(String pinType) {
		this.pinType = pinType;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
}
