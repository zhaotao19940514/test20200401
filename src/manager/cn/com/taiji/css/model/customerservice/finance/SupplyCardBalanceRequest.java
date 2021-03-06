
package cn.com.taiji.css.model.customerservice.finance;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.SupplyCardBalanceDetail;


public class SupplyCardBalanceRequest  extends JpaDateTimePageableDataRequest<SupplyCardBalanceDetail> {
	private String uuId;
	// 卡号
	private String cardId;
	
	//状态
	private Integer status;
	
	// 充前金额
	private Long preBalance;

	// 实收金额
	private Long paidAmount;
	
	// 赠送金额
	private Long giftAmount;
	
	// 充值金额
	private Long rechargeAmount;
	
	// 支付方式
	private Integer tradeType;
	
	private String cosResponse;
	private String command;
	private String chargeStatus;
	private String cosRecordId;
	private Integer cardType;
	private String enableTime;
	private String expireTime;
	private String rechargeId;
	private Integer channelType;
	private String agencyId;
	// 开户人名称
	private String customerName;
	
	private String customerIdNum;
	
	// 车牌号
	private String vehiclePlate;

	// 车牌颜色
	private Integer vehiclePlateColor;
	
	
	


	public String getUuId() {
		return uuId;
	}


	public void setUuId(String uuId) {
		this.uuId = uuId;
	}


	public String getChargeStatus() {
		return chargeStatus;
	}


	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}


	/**
	 * @return rechargeId
	 */
	public String getRechargeId() {
		return rechargeId;
	}


	/**
	 * @param rechargeId 要设置的 rechargeId
	 */
	public void setRechargeId(String rechargeId) {
		this.rechargeId = rechargeId;
	}




	/**
	 * @return channelType
	 */
	public Integer getChannelType() {
		return channelType;
	}


	/**
	 * @param channelType 要设置的 channelType
	 */
	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}


	/**
	 * @return cardId
	 */
	/**
	 * @return cardId
	 */
	public String getCardId() {
		return cardId;
	}


	/**
	 * @param cardId 要设置的 cardId
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	

	
	

	/**
	 * @return preBalance
	 */
	public Long getPreBalance() {
		return preBalance;
	}


	/**
	 * @param preBalance 要设置的 preBalance
	 */
	public void setPreBalance(Long preBalance) {
		this.preBalance = preBalance;
	}


	

	/**
	 * @return paidAmount
	 */
	public Long getPaidAmount() {
		return paidAmount;
	}


	/**
	 * @param paidAmount 要设置的 paidAmount
	 */
	public void setPaidAmount(Long paidAmount) {
		this.paidAmount = paidAmount;
	}

	
	/**
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}


	/**
	 * @param status 要设置的 status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}


	
	


	/**
	 * @return cosResponse
	 */
	public String getCosResponse() {
		return cosResponse;
	}


	/**
	 * @param cosResponse 要设置的 cosResponse
	 */
	public void setCosResponse(String cosResponse) {
		this.cosResponse = cosResponse;
	}




	public String getCommand() {
		return command;
	}


	public void setCommand(String command) {
		this.command = command;
	}


	/**
	 * @return cosRecordId
	 */
	public String getCosRecordId() {
		return cosRecordId;
	}


	/**
	 * @param cosRecordId 要设置的 cosRecordId
	 */
	public void setCosRecordId(String cosRecordId) {
		this.cosRecordId = cosRecordId;
	}





	/**
	 * @return cardType
	 */
	public Integer getCardType() {
		return cardType;
	}


	/**
	 * @param cardType 要设置的 cardType
	 */
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}


	/**
	 * @return enableTime
	 */
	public String getEnableTime() {
		return enableTime;
	}


	/**
	 * @param enableTime 要设置的 enableTime
	 */
	public void setEnableTime(String enableTime) {
		this.enableTime = enableTime;
	}


	/**
	 * @return expireTime
	 */
	public String getExpireTime() {
		return expireTime;
	}


	/**
	 * @param expireTime 要设置的 expireTime
	 */
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}


	

	/**
	 * @return tradeType
	 */
	public Integer getTradeType() {
		return tradeType;
	}


	/**
	 * @param tradeType 要设置的 tradeType
	 */
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	

	/**
	 * @return giftAmount
	 */
	public Long getGiftAmount() {
		return giftAmount;
	}


	/**
	 * @param giftAmount 要设置的 giftAmount
	 */
	public void setGiftAmount(Long giftAmount) {
		this.giftAmount = giftAmount;
	}
	
	


	/**
	 * @return rechargeAmount
	 */
	public Long getRechargeAmount() {
		return rechargeAmount;
	}


	
	/**
	 * @param rechargeAmount 要设置的 rechargeAmount
	 */
	public void setRechargeAmount(Long rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	
	
	public String getAgencyId() {
		return agencyId;
	}


	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}


	
	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getVehiclePlate() {
		return vehiclePlate;
	}


	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}


	public Integer getVehiclePlateColor() {
		return vehiclePlateColor;
	}


	public void setVehiclePlateColor(Integer vehiclePlateColor) {
		this.vehiclePlateColor = vehiclePlateColor;
	}


	
	public String getCustomerIdNum() {
		return customerIdNum;
	}


	public void setCustomerIdNum(String customerIdNum) {
		this.customerIdNum = customerIdNum;
	}


	public void validate() {
		MyViolationException mve = new MyViolationException();		
		if(cardId == null) {
			mve.addViolation("cardId", "卡号不能为空！");
		} else if(cardId.length()<20) mve.addViolation("cardId", "卡号长度不正确！");
		if (mve.hasViolation()) throw mve;
	}

	
	
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "SupplyCardBalanceDetail " +
				"  where 1=1 ");
		hql.append(" and cardId =:cardId",cardId);
		return hql;
	}

}

