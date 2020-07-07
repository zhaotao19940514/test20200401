package cn.com.taiji.css.model.customerservice.card.balance;

import cn.com.taiji.common.entity.BaseEntity;
import cn.com.taiji.css.entity.User;

public class CardBalancePaymentBackLogEntity extends BaseEntity {

	private String cardId;
	private Long fee;
	private User user;

	public CardBalancePaymentBackLogEntity() {
		// TODO Auto-generated constructor stub
	}

	public CardBalancePaymentBackLogEntity(String cardId, Long fee, User user) {
		super();
		this.cardId = cardId;
		this.fee = fee;
		this.user = user;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public Long getFee() {
		return fee;
	}

	public void setFee(Long fee) {
		this.fee = fee;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
