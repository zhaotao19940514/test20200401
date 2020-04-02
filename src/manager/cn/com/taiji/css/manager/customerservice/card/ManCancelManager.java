package cn.com.taiji.css.manager.customerservice.card;

import java.util.List;

import javax.validation.Valid;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.customerservice.card.ManCancelRequest;
import cn.com.taiji.qtk.entity.AccountCardBalanceOperate;
import cn.com.taiji.qtk.entity.AccountRefundDetail;
import cn.com.taiji.qtk.entity.AccountRefundLog;
import cn.com.taiji.qtk.entity.CancelledCardDetail;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.RefundImpFailMessage;

/**
 * @ClassName ManCancelManager.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
public interface ManCancelManager {

	/**
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<CancelledCardDetail> queryPage(ManCancelRequest queryModel,User user) throws ManagerException;

	CancelledCardDetail findById(String cardId);

	Long findAccountCardBalanceBycardId(String cardId,User user);

	CustomerInfo findByCustomerInfo(@Valid String cardId);

	AccountCardBalanceOperate findByCardId(String cardId);

	AppAjaxResponse confirmRefund(ManCancelRequest queryModel, User loginUser);

	boolean cancel15ArgueTime(String cardId) throws ManagerException;

	AccountCardBalanceOperate queryBankCard(String cardId);

	AppAjaxResponse updateBankCard(ManCancelRequest queryModel);

	AccountRefundDetail findRefundBalance(String cardId);

	List<AccountRefundLog> listRefundMsg(String cardId);
}

