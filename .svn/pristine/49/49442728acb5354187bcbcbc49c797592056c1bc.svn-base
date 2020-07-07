package cn.com.taiji.css.manager.customerservice.card.balance;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.card.balance.CardBalancePaymentBackRequest;
import cn.com.taiji.css.model.customerservice.card.balance.CardBalancePaymentBackShowModel;
import cn.com.taiji.dsi.model.cardbalance.CardBalancePaymentDetailResponse;
import cn.com.taiji.dsi.model.cardbalance.CardBalanceTransactionResponse;

public interface CardBalancePaymentBackManager {
	
	public CardBalancePaymentBackShowModel query(CardBalancePaymentBackRequest queryModel,User user) throws ManagerException;
	
	public void paymentBack(String cardId,User user,Long fee) throws ManagerException;

	public CardBalancePaymentDetailResponse paymentDetail(String cardId, User user) throws ManagerException;
	
	public CardBalanceTransactionResponse balanceTransaction(String cardId, User user) throws ManagerException;
}
