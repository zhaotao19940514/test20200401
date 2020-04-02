package cn.com.taiji.css.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.taiji.common.entity.StringUUIDEntity;

@Entity
@Table(name = "ACCOUNT_TEMPORARY")
public class AccountTemporary extends StringUUIDEntity
{
	private String cardId;
	private Long balance;
	private String time;
	/**
	 * @return cardId
	 */
	@Column(name = "CARD_ID")
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
	 * @return balance
	 */
	@Column(name = "BALANCE")
	public Long getBalance() {
		return balance;
	}
	/**
	 * @param balance 要设置的 balance
	 */
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	/**
	 * @return time
	 */
	@Column(name = "TIME")
	public String getTime() {
		return time;
	}
	/**
	 * @param time 要设置的 time
	 */
	public void setTime(String time) {
		this.time = time;
	}
	
	
	
}
