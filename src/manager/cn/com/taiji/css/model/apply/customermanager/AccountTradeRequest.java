package cn.com.taiji.css.model.apply.customermanager;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.AccountTradeDetail;

public class AccountTradeRequest extends JpaDateTimePageableDataRequest<AccountTradeDetail> {
	private String cardId;
	private String customerId;
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public void validate() {
	}
	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "AccountTradeDetail " +
				"  where 1=1 ");
		hql.append(" and cardId=:cardId", cardId);
		hql.append(" and customerId=:customerId",customerId);
		
		return hql;
	}

}
