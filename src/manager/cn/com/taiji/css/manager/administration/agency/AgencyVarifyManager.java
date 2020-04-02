/**
 * @Title AgencyVarifyManager.java
 * @Package cn.com.taiji.css.manager.administration.agency
 * @Description TODO
 * @author yaonanlin
 * @date 2019年1月28日 下午2:19:40
 * @version V1.0
 */
package cn.com.taiji.css.manager.administration.agency;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.qtk.entity.CardInfo;

/**
 * @ClassName AgencyVarifyManager
 * @Description TODO
 * @author yaonl
 * @date 2019年01月28日 14:19:40
 * @E_mail yaonanlin@163.com
 */
public interface AgencyVarifyManager {
	boolean varifyAgency(User user, CardInfo cardInfo) throws ManagerException;
}

