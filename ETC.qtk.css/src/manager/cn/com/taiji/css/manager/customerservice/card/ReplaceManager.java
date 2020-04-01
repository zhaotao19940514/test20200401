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
import cn.com.taiji.css.model.customerservice.card.ReplaceRequest;
import cn.com.taiji.qtk.entity.CardInfo;

/**
 * @ClassName ReplaceManager.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
public interface ReplaceManager {

	/**
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<CardInfo> queryPage(ReplaceRequest queryModel,User user) throws ManagerException;
	CardInfo findById(@Valid String id);

}

