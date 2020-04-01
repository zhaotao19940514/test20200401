
package cn.com.taiji.css.manager.customerservice.finance;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeRequest;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeResponse;
import cn.com.taiji.css.model.customerservice.finance.HalfauditingResponse;
import cn.com.taiji.css.model.customerservice.finance.SupplyCardBalanceRequest;
import cn.com.taiji.css.model.customerservice.finance.SupplyCardBalanceResponse;
import cn.com.taiji.qtk.entity.SupplyCardBalanceDetail;


public interface SupplyCardBalanceManager {

	/**
	 * @param queryModelO
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<SupplyCardBalanceDetail> queryPage(SupplyCardBalanceRequest queryModel,HttpServletRequest request) throws ManagerException;
	SupplyCardBalanceResponse FindCardInfoByCardId(SupplyCardBalanceRequest request,User user)throws ManagerException;
	boolean agencyCheck(User user, String cardId) throws ManagerException ;
	SupplyCardBalanceResponse  save(SupplyCardBalanceRequest request,User user) throws ManagerException ;
	HalfauditingResponse savePng(MultipartFile file,String cardId,String enableTime) throws ManagerException ;
	List<String> getScreenShotBase64(String  cardId,String enableTime) throws ManagerException;
    Model modelAdd(String cardId,String enableTime,Model model) throws ManagerException;
    
    CardrechargeResponse CardChargeCheck(CardrechargeRequest request,User user) throws ManagerException;
	CardrechargeResponse CardChargeByCOS(CardrechargeRequest request,User user) throws ManagerException;
	CardrechargeResponse CardChargeConfirmByCOS(CardrechargeRequest request,User user) throws ManagerException;
}

