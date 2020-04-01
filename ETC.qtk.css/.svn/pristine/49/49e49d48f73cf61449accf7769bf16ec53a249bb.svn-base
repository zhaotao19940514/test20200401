/**
 * @Title RechargeManager.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:25
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.card;

import java.util.List;

import javax.validation.Valid;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.card.ApplyCardRequest;
import cn.com.taiji.css.model.customerservice.card.SupplyRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardCancelResponse;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.Package;

/**
 * @ClassName SupplyManager.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
public interface SupplyManager {

	/**
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<CardInfo> queryPage(SupplyRequest queryModel,User user) throws ManagerException;


	CardInfo findById(@Valid String id);
	AppCardStatusChangeResponse cardApplyAndConfirm(ApplyCardRequest appReq,User user) throws ManagerException;

	/*AppCardStatusChangeResponse updateCardStatusToHang(@Valid ApplyCardRequest appReq,User user) throws ManagerException;


	AppCardStatusChangeResponse cardPrecancel(@Valid String oldCardId,User user) throws ManagerException;
*/

	List<Package> queryPackage(ApplyCardRequest appReq,User user);


	CardCancelResponse oldCardCancel(@Valid ApplyCardRequest appReq, User loginUser);


	Long cardRefund(User user);



}

