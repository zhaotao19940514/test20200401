
package cn.com.taiji.css.manager.customerservice.finance;

import org.springframework.ui.Model;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.finance.BalancesupplyRequest;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeRequest;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeResponse;
import cn.com.taiji.qtk.entity.CardInfo;


public interface BalancesupplyManager {

	/**
	 * @param queryModelO
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<CardInfo> queryPage(BalancesupplyRequest queryModel) throws ManagerException;
	
	CardrechargeResponse updateOperateStatus (String  cardId ,Integer  operateStatus) throws ManagerException;
	//补换卡
	CardrechargeResponse cardReplace(BalancesupplyRequest queryModel,User user) throws ManagerException; 
	//圈存申请
	CardrechargeResponse cardChargeByCOS(CardrechargeRequest request,User user) throws ManagerException;
	//圈存确认
	CardrechargeResponse cardChargeConfirmByCOS(CardrechargeRequest request,User user) throws ManagerException;
	
	 CardInfo findByCardId(String cardId,Model model) throws ManagerException;

}

