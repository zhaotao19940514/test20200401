
package cn.com.taiji.css.model.customerservice.finance;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.ChargeDetail;


public class CardrechargeRequest extends JpaDateTimePageableDataRequest<ChargeDetail> {
	
			private String oldCardId;
			private String id;
			private String cancelledCardDetailId;
			private String passWord;
			private Long accountBalance;
			private Integer commandType;
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
			
			private Long rechargeAmount;
			// 充值金额
			private Long fee;
			
			// 支付方式
			private Integer tradeType;
			
			private String cosResponse;
			
			private String command;
			
			//充值状态
			private String chargeStatus;
			
			//充值交易流水编号
			private String rechargeId;
			
			private Integer channelType;
			
			private Integer chargeType;

			
			//客服合作机构编号
			private String agencyId;

			//网点编号
			private  String channelId;
			
			private String enableTime;

			
			
			
			
			public String getOldCardId() {
				return oldCardId;
			}

			public void setOldCardId(String oldCardId) {
				this.oldCardId = oldCardId;
			}

			/**
			 * @return commandType
			 */
			public Integer getCommandType() {
				return commandType;
			}

			/**
			 * @param commandType 要设置的 commandType
			 */
			public void setCommandType(Integer commandType) {
				this.commandType = commandType;
			}

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

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
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

			public Long getFee() {
				return fee;
			}

			public void setFee(Long fee) {
				this.fee = fee;
			}

			public Integer getTradeType() {
				return tradeType;
			}

			public void setTradeType(Integer tradeType) {
				this.tradeType = tradeType;
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

			public String getChargeStatus() {
				return chargeStatus;
			}

			public void setChargeStatus(String chargeStatus) {
				this.chargeStatus = chargeStatus;
			}

			public String getRechargeId() {
				return rechargeId;
			}

			public void setRechargeId(String rechargeId) {
				this.rechargeId = rechargeId;
			}

			public Integer getChannelType() {
				return channelType;
			}

			public void setChannelType(Integer channelType) {
				this.channelType = channelType;
			}
			
			
			public Long getRechargeAmount() {
				return rechargeAmount;
			}

			public void setRechargeAmount(Long rechargeAmount) {
				this.rechargeAmount = rechargeAmount;
			}

			
			public String getCancelledCardDetailId() {
				return cancelledCardDetailId;
			}

			public void setCancelledCardDetailId(String cancelledCardDetailId) {
				this.cancelledCardDetailId = cancelledCardDetailId;
			}

			
			public Integer getChargeType() {
				return chargeType;
			}

			public void setChargeType(Integer chargeType) {
				this.chargeType = chargeType;
			}
			

			public String getAgencyId() {
				return agencyId;
			}

			public void setAgencyId(String agencyId) {
				this.agencyId = agencyId;
			}

			
			
			

			/**
			 * @return channelId
			 */
			public String getChannelId() {
				return channelId;
			}

			/**
			 * @param channelId 要设置的 channelId
			 */
			public void setChannelId(String channelId) {
				this.channelId = channelId;
			}

			public Long getAccountBalance() {
				return accountBalance;
			}

			public void setAccountBalance(Long accountBalance) {
				this.accountBalance = accountBalance;
			}

			
			public String getEnableTime() {
				return enableTime;
			}

			public void setEnableTime(String enableTime) {
				this.enableTime = enableTime;
			}

			public void validate() {
				MyViolationException mve = new MyViolationException();		
				if(cardId == null) mve.addViolation("cardId", "未获取到卡号,请检查是否已读取卡信息！");
				if(paidAmount!=null) {
					if(chargeType == null) mve.addViolation("chargeType", "未获取到收费类型！");
				}
				
				if (mve.hasViolation()) throw mve;
			}
			
			@Override
			public HqlBuilder toSelectHql() {
				HqlBuilder hql = new HqlBuilder("from " + "ChargeDetail" +
						"  where 1=1 ");
				hql.append(" and cardId=:cardId", cardId);
				hql.append(" order by tradeTime desc,chargeId ASC");
				return hql;
			}
			
			
			


}

