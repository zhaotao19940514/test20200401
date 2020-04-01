package cn.com.taiji.css.manager.customerservice.card;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.card.CardPinUnlockingRequset;
import cn.com.taiji.css.model.customerservice.card.CardPinUnlockingResponse;

public interface CardPinUnlockingManager {
	public CardPinUnlockingResponse CardPinUnlocking(CardPinUnlockingRequset req,User user) throws ManagerException;
}
