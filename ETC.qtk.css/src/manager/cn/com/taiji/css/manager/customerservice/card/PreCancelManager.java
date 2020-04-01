/**
 * @Title RechargeManager.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:25
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.card;

import javax.validation.Valid;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.card.PreCancelRequest;
import cn.com.taiji.qtk.entity.CardInfo;

/**
 * @ClassName RechargeManager
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:16:25
 * @E_mail yaonanlin@163.com
 */
public interface PreCancelManager {

	/**
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<CardInfo> queryPage(PreCancelRequest queryModel,User user) throws ManagerException;

	AppCardStatusChangeResponse doPreCancel(@Valid PreCancelRequest queryModel,User user) throws Exception;

	CardInfo findById(@Valid String id);
	
	/*CardObuBindingConfirmResponse doCardOrder(PreCancelRequest preReq, User user);*/

}

