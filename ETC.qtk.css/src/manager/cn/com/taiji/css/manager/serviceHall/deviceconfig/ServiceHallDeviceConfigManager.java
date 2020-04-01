/**
 * @Title ServiceHallDeviceConfigManager.java
 * @Package cn.com.taiji.css.manager.serviceHall.deviceconfig
 * @Description TODO
 * @author yaonanlin
 * @date 2018年9月6日 下午7:58:59
 * @version V1.0
 */
package cn.com.taiji.css.manager.serviceHall.deviceconfig;

import javax.validation.Valid;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.model.request.serviceHall.deviceconfig.ServiceHallDeviceConfigEditByAgencyModel;
import cn.com.taiji.css.model.request.serviceHall.deviceconfig.ServiceHallDeviceConfigEditModel;
import cn.com.taiji.css.model.request.serviceHall.deviceconfig.ServiceHallDeviceConfigRequest;
import cn.com.taiji.qtk.entity.ServiceHallDeviceConfig;

/**
 * @ClassName ServiceHallDeviceConfigManager
 * @Description TODO
 * @author yaonl
 * @date 2018年09月06日 19:58:59
 * @E_mail yaonanlin@163.com
 */
public interface ServiceHallDeviceConfigManager {

	Object page(@Valid ServiceHallDeviceConfigRequest req);

	ServiceHallDeviceConfig findById(String id);

	void editByAgencyId(ServiceHallDeviceConfigEditByAgencyModel configToEdit);

	ServiceHallDeviceConfig delete(String id) throws ManagerException;

	ServiceHallDeviceConfig edit(ServiceHallDeviceConfigEditModel configToEdit) throws ManagerException;

	ServiceHallDeviceConfig findByServiceHallId(String serviceHallId);

	ServiceHallDeviceConfig add(ServiceHallDeviceConfig configToAdd) throws ManagerException;

}

