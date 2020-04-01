/**
 * @Title TestApplyCardResponse.java
 * @Package cn.com.taiji.css.model.ocxtest
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月12日 下午4:52:49
 * @version V1.0
 */
package cn.com.taiji.css.model.customerservice.card;

import cn.com.taiji.css.model.appajax.AppAjaxResponse;

/**
 * @ClassName TestApplyCardResponse
 * @Description TODO
 * @author yaonl
 * @date 2018年07月12日 16:52:49
 * @E_mail yaonanlin@163.com
 */
public class ApplyCardResponse extends AppAjaxResponse {
	private String message;
	private String command;
	private String cosRecordId;
	private Integer orderStatus;
	private String cardId;
	private String oldCardId;
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	public String getOldCardId() {
		return oldCardId;
	}
	public void setOldCardId(String oldCardId) {
		this.oldCardId = oldCardId;
	}
	public String getMessage() {
		return message;
	}
	public String getCommand() {
		return command;
	}
	public String getCosRecordId() {
		return cosRecordId;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public void setCosRecordId(String cosRecordId) {
		this.cosRecordId = cosRecordId;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
}

