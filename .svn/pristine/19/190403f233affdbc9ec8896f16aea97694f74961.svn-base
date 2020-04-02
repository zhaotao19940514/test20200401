
package cn.com.taiji.css.manager.customerservice.finance;

import java.io.IOException;
import java.util.List;

import org.springframework.ui.Model;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.finance.ForcefixCardBalanceResponse;
import cn.com.taiji.css.model.customerservice.finance.ForcefixRequest;
import cn.com.taiji.qtk.entity.ChargeDetail;


public interface ForcefixManager {

	LargePagination<ChargeDetail> queryPage(ForcefixRequest queryModel);
	
	
	ForcefixCardBalanceResponse updateStatus(String chargeId,Long cardBalance,User user) throws ManagerException, ApiRequestException, IOException ;


	ForcefixCardBalanceResponse deleteStatus(String chargeId,Long cardBalance) throws ManagerException, ApiRequestException, IOException ;
	
	 List<String> getScreenShotBase64(String  chargeId) throws ManagerException;
	 
	 Model modelAdd(String chargeId,Model model) throws ManagerException;
	 
	 ChargeDetail findBychargeId(String chargeId,Model model) throws ManagerException;
	 
}

