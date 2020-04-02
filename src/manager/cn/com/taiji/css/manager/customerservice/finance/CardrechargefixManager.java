
package cn.com.taiji.css.manager.customerservice.finance;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeResponse;
import cn.com.taiji.css.model.customerservice.finance.CardrechargefixRequest;
import cn.com.taiji.qtk.entity.ChargeDetail;


public interface CardrechargefixManager {

	List<ChargeDetail>  queryPage(CardrechargefixRequest queryModel,HttpServletRequest request) throws ManagerException;
	CardrechargeResponse CardChargeCheck(CardrechargefixRequest request,User user) throws ManagerException;
	CardrechargeResponse CardChargeFix(CardrechargefixRequest request,User user) throws ManagerException;
	CardrechargeResponse CardChargeByCOS(CardrechargefixRequest request,User user) throws ManagerException;
	CardrechargeResponse CardChargeConfirmByCOS(CardrechargefixRequest request,User user) throws ManagerException;
}

