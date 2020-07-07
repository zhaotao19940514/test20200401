
package cn.com.taiji.css.model.customerservice.finance;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.qtk.entity.CardInfo;


public class BalanceReckonRequest extends JpaDateTimePageableDataRequest<CardInfo> {
		private String cardId;
			
		public String getCardId() {
			return cardId;
		}

		public void setCardId(String cardId) {
			this.cardId = cardId;
		}

		public void validate() {
			MyViolationException mve = new MyViolationException();		
			if(null==cardId) {
				mve.addViolation("cardId", "请输入卡号");
			}
			
			if (mve.hasViolation()) throw mve;
		}
		
		@Override
		public HqlBuilder toSelectHql() {
			HqlBuilder hql = new HqlBuilder("from " + "CardInfo" +
					"  where 1=1 ");
			hql.append(" and cardId=:cardId", cardId);
			hql.append(" and agencyId not in('52010102007','52010102005')");
			return hql;
		}
		
}

