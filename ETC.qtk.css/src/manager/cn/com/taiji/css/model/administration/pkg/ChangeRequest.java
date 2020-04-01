package cn.com.taiji.css.model.administration.pkg;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.qtk.entity.CardPackageView;

public class ChangeRequest  extends JpaDateTimePageableDataRequest<CardPackageView>{
	//卡号
	private String cardId;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("from " + "CardPackageView " + "  where 1=1 ");
		hql.append(" and cardId=:cardId", cardId);
		return hql;
	}
	
}
