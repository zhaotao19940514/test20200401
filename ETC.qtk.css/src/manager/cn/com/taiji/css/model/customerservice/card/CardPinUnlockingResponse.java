package cn.com.taiji.css.model.customerservice.card;

import cn.com.taiji.css.model.appajax.AppAjaxResponse;

public class CardPinUnlockingResponse extends AppAjaxResponse {
	//用于记录解锁卡号，与解锁无关
	private String cardId;
	//写卡指令
	private String command;
	//解锁状态
	//1- 解锁成功  2- 解锁失败  3- 继续执行,调用该接口
	private String cardUnblockStatus;
	/*卡pin码类型
	只能是1 或者2 . 
	1-  pin为123456,
	2-  pin为313233343536
	用于下次调用该接口使用.*/
	private String pinType;
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getCardUnblockStatus() {
		return cardUnblockStatus;
	}
	public void setCardUnblockStatus(String cardUnblockStatus) {
		this.cardUnblockStatus = cardUnblockStatus;
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
