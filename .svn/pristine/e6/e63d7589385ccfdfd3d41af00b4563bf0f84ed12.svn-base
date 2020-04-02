
package cn.com.taiji.css.manager.customerservice.finance;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.finance.RechargeRequest;
import cn.com.taiji.css.model.customerservice.finance.RechargeResponse;
import cn.com.taiji.dsi.model.comm.protocol.finance.AccountChargeResponse;

public interface RechargeManager {
	
	
	/*LargePagination<AccountTradeDetail> queryPage(RechargeRequest queryModel);*/
	RechargeResponse accountTradesQuery(RechargeRequest request,User user)throws ManagerException;

	String  findUserId(RechargeRequest request,User user) throws ManagerException;
	
	AccountChargeResponse accountCharge(RechargeRequest request,User user) throws ManagerException ;
	AccountChargeResponse accountReverse(RechargeRequest request,User user) throws ManagerException ;
	AccountChargeResponse accountreversal(RechargeRequest request,User user) throws ManagerException ;
	

	
	
}

