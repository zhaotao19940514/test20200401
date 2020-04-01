
package cn.com.taiji.css.model.customerservice.finance;

import cn.com.taiji.common.model.BaseModel;
import cn.com.taiji.qtk.entity.CardRefundDetail;
import cn.com.taiji.qtk.entity.dict.AuditStatusType;
import cn.com.taiji.qtk.entity.dict.RefundType;
import cn.com.taiji.qtk.entity.dict.TradeType;


public class ExpenseRefundAuditResponse extends BaseModel  {
    
private String id;
	
	private String cardId;
	// 车牌号
	private String vehiclePlate;
	// 车牌颜色
	private Integer vehiclePlateColor;
    //交易类型
	private TradeType tradeType;
	//卡类型
	private Integer cardType;
	// 银行卡号
	private String bankCard;
	// 开户行
	private String bank;
	
	private String enableTime;
	
	private CardRefundDetail cardRefundDetail;
	//操作人员工号
	private String userId;
	// 开户行户名
	private String bankUserName;
	// 户主联系方式
	private String phone;
	//退费类型
	private RefundType refundType;
	//详细描述
	private String detailedDescription;
	// 录入时间
	private String createTime;
	// 交易时间
	private String tradeTime;
	// 交易金额
	private Long tradeFee;
	//出口站名
	private String  exName;
	//审核状态
	private Integer status;
	
	private String  message;
	// 审核状态
	private AuditStatusType auditStatusType;
	
	//审核金额
	private Long refundFee;

	//文件路径
	private String filePath;
	//文件名
	private String fileName;
	//退费审核描述
	private String refundDescription;
	//资金审核描述
	private String financeDescription;
	
	
	
	
	
	public AuditStatusType getAuditStatusType() {
		return auditStatusType;
	}




	public void setAuditStatusType(AuditStatusType auditStatusType) {
		this.auditStatusType = auditStatusType;
	}




	public Long getRefundFee() {
		return refundFee;
	}




	public void setRefundFee(Long refundFee) {
		this.refundFee = refundFee;
	}




	public String getFilePath() {
		return filePath;
	}




	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}




	public String getFileName() {
		return fileName;
	}




	public void setFileName(String fileName) {
		this.fileName = fileName;
	}




	public String getRefundDescription() {
		return refundDescription;
	}




	public void setRefundDescription(String refundDescription) {
		this.refundDescription = refundDescription;
	}




	public String getFinanceDescription() {
		return financeDescription;
	}




	public void setFinanceDescription(String financeDescription) {
		this.financeDescription = financeDescription;
	}




	public RefundType getRefundType() {
		return refundType;
	}




	public void setRefundType(RefundType refundType) {
		this.refundType = refundType;
	}




	public Integer getCardType() {
		return cardType;
	}




	public void setCardType(Integer cardType) {
		this.cardType = cardType;
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
	
	/*public AuditStatusType getAuditStatusType() {
		return auditStatusType;
	}

	public void setAuditStatusType(AuditStatusType auditStatusType) {
		this.auditStatusType = auditStatusType;
	}*/
	
	

	
	public String getEnableTime() {
		return enableTime;
	}


	public void setEnableTime(String enableTime) {
		this.enableTime = enableTime;
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




	public String getExName() {
		return exName;
	}


	public void setExName(String exName) {
		this.exName = exName;
	}




	public String getBankCard() {
		return bankCard;
	}




	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}




	public Integer getStatus() {
		return status;
	}




	public void setStatus(Integer status) {
		this.status = status;
	}


	


	public String getBank() {
		return bank;
	}




	public void setBank(String bank) {
		this.bank = bank;
	}




	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}




	public String getBankUserName() {
		return bankUserName;
	}




	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}




	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public String getDetailedDescription() {
		return detailedDescription;
	}




	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}




	public String getCreateTime() {
		return createTime;
	}




	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}




	public CardRefundDetail getCardRefundDetail() {
		return cardRefundDetail;
	}




	public void setCardRefundDetail(CardRefundDetail cardRefundDetail) {
		this.cardRefundDetail = cardRefundDetail;
	}



	

	public String getMessage() {
		return message;
	}




	public void setMessage(String message) {
		this.message = message;
	}


	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

//	@Override
//	public HqlBuilder toSelectHql() {
//		HqlBuilder hql = new HqlBuilder("from " + "AccountCardDetail " +
//				"  where 1=1 ");
//		hql.append(" and cardId =:cardId", cardId);
//		hql.append(" and tradeType =:tradeType", tradeType.CARD_TRANSACTION);
//		LocalDateTime[] times = toDateTimes();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//		if(times[0]!=null)
//		{
//			hql.append(" and tradeDate>=:createtimeMin",
//					(times[0].format(formatter)));
//		}
//		if(times[1]!=null)
//		{
//			hql.append(" and tradeDate<=:createtimeMax",
//					(times[1].format(formatter)));
//		}
//		return hql;
//	}

}
