/**
 * @Title RechargeManager.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:25
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.finance;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.finance.BalanceReckonRequest;
import cn.com.taiji.qtk.entity.CardInfo;

/**
 * 
 *@ClassName BalanceReckonManager.java
 *@Description: 
 *@author:zhaot
 *@date: 2020年7月7日
 */
public interface BalanceReckonManager {

	/**
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<CardInfo> queryPage(BalanceReckonRequest queryModel,User user) throws ManagerException;

}

