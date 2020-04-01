
package cn.com.taiji.css.model.customerservice.finance;

import java.util.Calendar;

import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.manager.util.MyPatterns;
import cn.com.taiji.dsi.model.comm.protocol.CommQtkRequset;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;


public class RechargeRequest extends CommQtkRequset {
	
		private String testId;
		private String passWord;
		/**
		 * @return testId
		 */
		public String getTestId() {
			return testId;
		}
		/**
		 * @param testId 要设置的 testId
		 */
		public void setTestId(String testId) {
			this.testId = testId;
		}


		//账户余额
		private Long accountBalance;
		// 用户编号
		private String customerId;
		private Calendar createTime;
		// 交易时间
		private String tradeDate;
		// 交易时间
		private String tradeTime;
		private Long tradeFee;
		private Integer pdd;
		private Integer tradeType;
		private Long preBalance;
		// 交易类型
		private String channelId;
		private String message;
		private Integer status;
		// 开户人证件号
		private String customerIdNum;
		private Integer customerIdType;
		
		private Integer chargeType;
		
		// 车牌号
		private String vehiclePlate;

		// 车牌颜色
		private Integer vehiclePlateColor;
		
		private String agencyId;
		private String userId;
		private String startTime;
		private String endTime;
		private Integer pageNo;
		private Integer pageSize;
		private String cardId;
		//标识是否为圈存冲正后的账户充值
		private Integer identification;
		private String chargeId;
		private Integer chargeStatus;
		
		
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
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		public Calendar getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Calendar createTime) {
			this.createTime = createTime;
		}
		public String getTradeDate() {
			return tradeDate;
		}
		public void setTradeDate(String tradeDate) {
			this.tradeDate = tradeDate;
		}
		public String getTradeTime() {
			return tradeTime;
		}
		public void setTradeTime(String tradeTime) {
			this.tradeTime = tradeTime;
		}
		public Long getTradeFee() {
			return tradeFee;
		}
		public void setTradeFee(Long tradeFee) {
			this.tradeFee = tradeFee;
		}
		public Integer getPdd() {
			return pdd;
		}
		public void setPdd(Integer pdd) {
			this.pdd = pdd;
		}
		public Integer getTradeType() {
			return tradeType;
		}
		public void setTradeType(Integer tradeType) {
			this.tradeType = tradeType;
		}
		public Long getPreBalance() {
			return preBalance;
		}
		public void setPreBalance(Long preBalance) {
			this.preBalance = preBalance;
		}
		public String getChannelId() {
			return channelId;
		}
		public void setChannelId(String channelId) {
			this.channelId = channelId;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public String getCustomerIdNum() {
			return customerIdNum;
		}
		public void setCustomerIdNum(String customerIdNum) {
			this.customerIdNum = customerIdNum;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public String getEndTime() {
			return endTime;
		}
		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
		public Integer getPageNo() {
			return pageNo;
		}
		public void setPageNo(Integer pageNo) {
			this.pageNo = pageNo;
		}
		public Integer getPageSize() {
			return pageSize;
		}
		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}
		public String getCardId() {
			return cardId;
		}
		public void setCardId(String cardId) {
			this.cardId = cardId;
		}
		public Integer getCustomerIdType() {
			return customerIdType;
		}
		public void setCustomerIdType(Integer customerIdType) {
			this.customerIdType = customerIdType;
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
		public Integer getChargeType() {
			return chargeType;
		}
		public void setChargeType(Integer chargeType) {
			this.chargeType = chargeType;
		}
		public Long getAccountBalance() {
			return accountBalance;
		}
		public void setAccountBalance(Long accountBalance) {
			this.accountBalance = accountBalance;
		}
		public String getAgencyId() {
			return agencyId;
		}
		public void setAgencyId(String agencyId) {
			this.agencyId = agencyId;
		}
		
		
		/**
		 * @return identification
		 */
		public Integer getIdentification() {
			return identification;
		}
		/**
		 * @param identification 要设置的 identification
		 */
		public void setIdentification(Integer identification) {
			this.identification = identification;
		}
		
		
		public String getChargeId() {
			return chargeId;
		}
		public void setChargeId(String chargeId) {
			this.chargeId = chargeId;
		}
		public Integer getChargeStatus() {
			return chargeStatus;
		}
		public void setChargeStatus(Integer chargeStatus) {
			this.chargeStatus = chargeStatus;
		}
		public void validate() {
			MyViolationException mve = new MyViolationException();
			if(cardId==null) {
				if (vehiclePlate == null) {
					mve.addViolation("cardId", "卡号和车牌号不能同时为空！");
					mve.addViolation("vehiclePlate", "卡号和车牌号不能同时为空！");
				}else {
					if(!MyPatterns.checkPlateNumFormat(vehiclePlate)) mve.addViolation("vehiclePlate", "格式不正确！");
					if (vehiclePlateColor == null) {
						mve.addViolation("vehiclePlateColor", "不能为空！");
					}else {
						if(VehiclePlateColorType.valueOfCode(vehiclePlateColor) == null)
							mve.addViolation("vehiclePlateColor", "没有此车牌颜色类型！"+vehiclePlateColor);
					}
				}
			}
			if (mve.hasViolation()) throw mve;
		}
		
		
		
		
}

