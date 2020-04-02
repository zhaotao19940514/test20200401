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


public class CardrechargeResponse extends BaseModel  {
	
	private String passWord;
	private String cancelledCardDetailId;

	private Long balance;
	//账户余额
	private Long accountBalance;
	//接口返回信息
	private String message;
	//端口号
	private String com;
	// 卡号
	private String cardId;
	//车牌
	private String vehicleId;
	//状态
	private Integer status;
	// 充前金额
	private Long preBalance;
	// 实收金额
	private Long paidAmount;
	// 赠送金额
	private Long giftAmount;
	//充值后金额
	private Long postBalance;
	// 支付方式
	private Integer tradeType;
	
	private Integer applyStep;//请求到第几次
	private String cosResponse;
	private String command;
	private String cosRecordId;
	private Integer cardType;
	
	//充值交易流水编号
	private String rechargeId;
	//充值状态
	private Integer chargeStatus;
	//指令类型
	private Integer commandType;
	//修复状态码
	private Integer fixStatus;
	//充值金额
	private String fee;
	//渠道类型
	private String channelType;
	//渠道编号
	private String channelId;
	
	private String customerId;
	
	
	/**
	 * @return passWord
	 */
	public String getPassWord() {
		return passWord;
	}
	/**
	 * @param passWord 要设置的 passWord
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getPreBalance() {
		return preBalance;
	}
	public void setPreBalance(Long preBalance) {
		this.preBalance = preBalance;
	}
	public Long getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Long paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Long getGiftAmount() {
		return giftAmount;
	}
	public void setGiftAmount(Long giftAmount) {
		this.giftAmount = giftAmount;
	}
	public Long getPostBalance() {
		return postBalance;
	}
	public void setPostBalance(Long postBalance) {
		this.postBalance = postBalance;
	}
	public Integer getTradeType() {
		return tradeType;
	}
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
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
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public String getRechargeId() {
		return rechargeId;
	}
	public void setRechargeId(String rechargeId) {
		this.rechargeId = rechargeId;
	}
	public Integer getChargeStatus() {
		return chargeStatus;
	}
	public void setChargeStatus(Integer chargeStatus) {
		this.chargeStatus = chargeStatus;
	}
	public Integer getCommandType() {
		return commandType;
	}
	public void setCommandType(Integer commandType) {
		this.commandType = commandType;
	}
	public Integer getFixStatus() {
		return fixStatus;
	}
	public void setFixStatus(Integer fixStatus) {
		this.fixStatus = fixStatus;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getCancelledCardDetailId() {
		return cancelledCardDetailId;
	}
	public void setCancelledCardDetailId(String cancelledCardDetailId) {
		this.cancelledCardDetailId = cancelledCardDetailId;
	}
	public Long getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Long accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	/**
	 * @return customerId
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId 要设置的 customerId
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
	
	

		
}

