/**
 * @Title RechargeManager.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:25
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.card;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.card.UnhangRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardStatusChangeResponse;
import cn.com.taiji.qtk.entity.CardInfo;

/**
 * @ClassName UnhangManager.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
public interface UnhangManager {

	/**
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<CardInfo> queryPage(UnhangRequest queryModel,User user) throws ManagerException;

	CardStatusChangeResponse doUnHangCard( String cardId, User loginUser) throws  Exception;

	CardInfo findById(String cardId);


}

