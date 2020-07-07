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

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.card.CardBlackRequest;
import cn.com.taiji.qtk.entity.BlackCard;
import cn.com.taiji.qtk.entity.BlackCardHis;

public interface CardBlackManager {

	List<BlackCard> queryPage(CardBlackRequest queryModel,User user) throws ManagerException;

	List<BlackCardHis> queryPageHis(CardBlackRequest queryModel, User loginUser)throws ManagerException;
	
	List<List<String>> queryYgzLog(String sTime,String eTime,String cardId, String status, User loginUser)throws ManagerException;

}

