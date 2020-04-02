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
import cn.com.taiji.css.model.customerservice.card.HangRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardStatusChangeResponse;
import cn.com.taiji.qtk.entity.CardInfo;

/**
 * @ClassName HangManager.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
public interface HangManager {

	/**
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<CardInfo> queryPage(HangRequest queryModel,User user) throws ManagerException;


	CardInfo findById(String cardId);

	CardStatusChangeResponse doHangCard(HangRequest queryModel, User loginUser) throws Exception;


}

