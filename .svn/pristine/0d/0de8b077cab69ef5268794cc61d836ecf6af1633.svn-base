package cn.com.taiji.css.repo.request.cardblacklist;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.qtk.entity.CardBlackListIncr;

public class CardBLackListIncrQueryRequest extends JpaPageableDataRequest<CardBlackListIncr>{

	private String cardId;
	
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + CardBlackListIncr.class.getName() + " a where 1=1 ");
		hql.append(" and cardId =:cardId", cardId);
		return hql;
	}

}
