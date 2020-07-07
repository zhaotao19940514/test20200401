package cn.com.taiji.css.model.customerservice.card.balance;

public class CardBalancePaymentBackRequest {

	private String cardId;

	public CardBalancePaymentBackRequest(String cardId) {
		super();
		this.cardId = cardId;
	}

	public CardBalancePaymentBackRequest() {
		
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

}
