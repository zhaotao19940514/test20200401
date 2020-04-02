/**
 * @Title ObuCommandManager.java
 * @Package cn.com.taiji.css.manager.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月3日 下午4:21:16
 * @version V1.0
 */
package cn.com.taiji.css.manager.ocx;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.ocx.ObuOfflineSysInfoCommandRequest;
import cn.com.taiji.css.model.ocx.ObuOfflineVehicleInfoCommandRequest;

/**
 * @ClassName ObuCommandManager
 * @Description TODO
 * @author yaonl
 * @date 2018年08月03日 16:21:16
 * @E_mail yaonanlin@163.com
 */
public interface ObuCommandManager {
	AppAjaxResponse generateCommand(ObuOfflineSysInfoCommandRequest request,User user) throws ManagerException;
	AppAjaxResponse generateCommand(ObuOfflineVehicleInfoCommandRequest request,User user);
}

