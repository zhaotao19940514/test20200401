/**
 * @Title ObuCommandManagerImpl.java
 * @Package cn.com.taiji.css.manager.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月4日 下午3:21:30
 * @version V1.0
 */
package cn.com.taiji.css.manager.ocx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.ocx.ObuOfflineSysInfoCommandRequest;
import cn.com.taiji.css.model.ocx.ObuOfflineVehicleInfoCommandRequest;
import cn.com.taiji.qtk.entity.ServiceHallDeviceConfig;
import cn.com.taiji.qtk.repo.jpa.ServiceHallDeviceConfigRepo;

/**
 * @ClassName ObuCommandManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2018年08月04日 15:21:30
 * @E_mail yaonanlin@163.com
 */
@Service
public class ObuCommandManagerImpl extends AbstractManager implements ObuCommandManager {
	@Autowired
	private ServiceHallDeviceConfigRepo serviceHallDeviceConfigRepo;
	@Override
	public AppAjaxResponse generateCommand(ObuOfflineSysInfoCommandRequest request,User user) {
		String sysInfoCommand = "";
		try {
			ObuDeviceServerConfig obuDeviceServerConfig = getConfigByUser(user);
			request.valid();
			sysInfoCommand = ObuCommandGenerator.generateSysInfo(request.getOldInfo(),request.getNewObuId(),request.getPlateNum(),request.getPlateColor(),obuDeviceServerConfig);
		} catch (ManagerException e) {
			e.printStackTrace();
			return new AppAjaxResponse(e);
		}
		return new AppAjaxResponse(sysInfoCommand);
	}

	@Override
	public AppAjaxResponse generateCommand(ObuOfflineVehicleInfoCommandRequest request,User user){
		String vehicleInfoCommand = "";
		try {
			ObuDeviceServerConfig obuDeviceServerConfig = getConfigByUser(user);
			request.valid();
			vehicleInfoCommand = ObuCommandGenerator.generateVehicleInfo(request.getOldInfo(), request.toParamArray(),obuDeviceServerConfig);
		} catch (ManagerException e) {
			e.printStackTrace();
			return new AppAjaxResponse(e);
		}
		return new AppAjaxResponse(vehicleInfoCommand);
	}

	private ObuDeviceServerConfig getConfigByUser(User user) throws ManagerException {
		ServiceHallDeviceConfig deviceConfig = serviceHallDeviceConfigRepo.findByServiceHallId(user.getStaff().getServiceHallId());
		if(deviceConfig == null) throw new ManagerException("当前工号所属网点："+user.getStaff().getServiceHallId()+" 未配置obu设备类型，请联系管理员配置。");
		ObuDeviceServerConfig obuDeviceServerConfig = ObuDeviceServerConfig.getConfig(deviceConfig);
		return obuDeviceServerConfig;
	}

}

