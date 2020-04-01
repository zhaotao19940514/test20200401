
package cn.com.taiji.css.manager.customerservice.card;

import javax.validation.Valid;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.card.CancelRequest;
import cn.com.taiji.css.model.customerservice.card.CardInformationChangeRequest;
import cn.com.taiji.css.model.customerservice.card.CardInformationChangeResponse;
import cn.com.taiji.qtk.entity.CardInfo;

public interface CardInformationChangeManager {

	/**
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<CardInfo> queryPage(CancelRequest queryModel,User user) throws ManagerException;


	CardInfo findById(@Valid String id);


	CardInformationChangeRequest modelAdd(String cardId) throws ManagerException;
	
	
	CardInformationChangeResponse applyCardOrderConfirm(CardInformationChangeRequest request,User user) throws ManagerException;
	CardInformationChangeResponse cardInfoChange(CardInformationChangeRequest request,User user)throws  ManagerException;
}

