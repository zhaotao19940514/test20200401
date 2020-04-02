/**
 * @Title RechargeManager.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:25
 * @version V1.0
 */
package cn.com.taiji.css.manager.apply.baseinfo;

import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.cardobuquery.CardRequest;
import cn.com.taiji.dsi.model.comm.protocol.inquire.CardInfoQueryResponse;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.CardInfo;

/**
 * @ClassName RechargeManager
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:16:25
 * @E_mail yaonanlin@163.com
 */
public interface CardManager {

	/**
	 * @param queryModel
	 * @return
	 */
	LargePagination<CardInfo> queryPage(CardRequest req);

	public List<Agency> queryAllAgency();

	public CardInfo findByCardId(String cardId);

	/**
	 * 通过卡编号查询卡信息
	 * @param cardId
	 * @param user
	 * @return
	 * @throws ManagerException
	 */
	public CardInfoQueryResponse findByCardIdPort(String cardId, User user) throws ManagerException; 

}

