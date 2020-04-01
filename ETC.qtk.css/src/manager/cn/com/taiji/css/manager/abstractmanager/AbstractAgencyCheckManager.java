/**
 * @Title AbstractAgencyCheckManager.java
 * @Package cn.com.taiji.css.manager.abstractcomm
 * @Description TODO
 * @author yaonanlin
 * @date 2018年9月1日 下午12:17:20
 * @version V1.0
 */
package cn.com.taiji.css.manager.abstractmanager;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.pub.AssertUtil;
import cn.com.taiji.css.entity.User;

/**
 * @ClassName AbstractAgencyCheckManager
 * @Description TODO
 * @author yaonl
 * @date 2018年09月01日 12:17:20
 * @E_mail yaonanlin@163.com
 */
public abstract class AbstractAgencyCheckManager extends AbstractManager {
	
	protected void checkAgencyAuthority(User user, String targetAgencyId, String exceptionMsg) throws ManagerException{
		AssertUtil.hasText(targetAgencyId);
		// 系统管理员跳过
		if(user.getRole().isSystem()) return;
		
		String userAgencyId = user.getStaff().getServiceHall().getAgencyId();
		if(!targetAgencyId.equals(userAgencyId)){
			throw new ManagerException(exceptionMsg);
		}
	}
}

