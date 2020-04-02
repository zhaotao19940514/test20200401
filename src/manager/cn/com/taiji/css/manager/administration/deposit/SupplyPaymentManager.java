package cn.com.taiji.css.manager.administration.deposit;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.administration.deposit.SupplyPaymentRequest;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CollateCardBalance;

/**
 * @ClassName SupplyPaymentManager.java
 * @author zhaotao
 * @Description 
 * @date2019年1月7日
 */
public interface SupplyPaymentManager {

	/**
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<CardInfo> queryPage(SupplyPaymentRequest queryModel,User user) throws ManagerException;

	CollateCardBalance findbyCollateCardBalance(String cardId);

	AppAjaxResponse doSubmit(SupplyPaymentRequest queryModel,User user);

	//AppAjaxResponse doSupply(SupplyPaymentRequest queryModel);

}

