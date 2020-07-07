package cn.com.taiji.css.model.customerservice.card.balance;

import cn.com.taiji.common.entity.BaseEntity;

public class CardBalancePaymentBackShowModel extends BaseEntity{
	// 卡账生成时间
	private String handleDate;
	// 卡号
	private String cardId;
	// 需要补交的金额
	private Long paymentFee;
	// 卡片所属渠道
	private String agencyName;

	public CardBalancePaymentBackShowModel(String handleDate, String cardId, Long paymentFee, String agencyName) {
		super();
		this.handleDate = handleDate;
		this.cardId = cardId;
		this.paymentFee = paymentFee;
		this.agencyName = agencyName;
	}

	public CardBalancePaymentBackShowModel() {

	}

	public String getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public Long getPaymentFee() {
		return paymentFee;
	}

	public void setPaymentFee(Long paymentFee) {
		this.paymentFee = paymentFee;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

}
