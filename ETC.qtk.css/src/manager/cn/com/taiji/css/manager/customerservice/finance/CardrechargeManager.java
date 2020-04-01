
package cn.com.taiji.css.manager.customerservice.finance;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeRequest;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeResponse;
import cn.com.taiji.qtk.entity.ChargeDetail;
import cn.com.taiji.qtk.entity.dict.ChargeType;


public interface CardrechargeManager {

	/**
	 * @param queryModelO
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<ChargeDetail> queryPage(CardrechargeRequest queryModel,HttpServletRequest request) throws ManagerException;
	CardrechargeResponse CardChargeCheck(CardrechargeRequest request,User user) throws ManagerException;
	CardrechargeResponse CardChargeByCOS(CardrechargeRequest request,User user) throws ManagerException;
	CardrechargeResponse CardChargeConfirmByCOS(CardrechargeRequest request,User user) throws ManagerException;
	CardrechargeResponse FindAccountBalanceByCardId(CardrechargeRequest request,User user)throws ManagerException;
	boolean agencyCheck(User user, String cardId) throws ManagerException ;
	ChargeType[] setChargeType(CardrechargeRequest queryModel,User user);
}

