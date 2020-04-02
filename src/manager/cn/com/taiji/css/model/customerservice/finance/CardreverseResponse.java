/**
 * @Title RechargeRequest.java
 * @Package cn.com.taiji.css.model.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:18:48
 * @version V1.0
 */
package cn.com.taiji.css.model.customerservice.finance;

import cn.com.taiji.common.model.BaseModel;

/**
 * @ClassName RechargeRequest
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:18:48
 * @E_mail yaonanlin@163.com
 */
public class CardreverseResponse  extends BaseModel {
	
	private String cosResponse;
	//接口调用
	private Integer status;
	//接口返回结果
	private String message;
	
	//冲正初始化指令
	private String cos;
	
	//写卡指令
	private String command;
	
	//返回信息
	private String info;
	
	
	//交易流水号
	private String chargeId;
	
	//冲正初始化指令结果
	private String initializeResponse;
	
	//支付方式
	private String tradeType;
	
	private Integer ReverseConfirmStatus;
	 

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCos() {
		return cos;
	}

	public void setCos(String cos) {
		this.cos = cos;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

	public String getInitializeResponse() {
		return initializeResponse;
	}

	public void setInitializeResponse(String initializeResponse) {
		this.initializeResponse = initializeResponse;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCosResponse() {
		return cosResponse;
	}

	public void setCosResponse(String cosResponse) {
		this.cosResponse = cosResponse;
	}

	public Integer getReverseConfirmStatus() {
		return ReverseConfirmStatus;
	}

	public void setReverseConfirmStatus(Integer reverseConfirmStatus) {
		ReverseConfirmStatus = reverseConfirmStatus;
	}


	
	
	
	
	
	
	
	
	
	
	

}

