/**
 * @Title MyLogController.java
 * @Package cn.com.taiji.css.web
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月28日 上午11:32:35
 * @version V1.0
 */
package cn.com.taiji.css.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.common.entity.BaseEntity;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.OperateLog;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.CssOperateLogType;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.system.OperateLogManager;
import cn.com.taiji.css.manager.util.CssUtil;

/**
 * @ClassName MyLogController
 * @Description TODO
 * @author yaonl
 * @date 2018年07月28日 11:32:35
 * @E_mail yaonanlin@163.com
 */
public abstract class MyLogController extends MyBaseController {
	@Autowired
	private OperateLogManager logManager;

	protected void doAddLog(HttpServletRequest request, CssServiceLogType serviceType, BaseEntity baseEntity)
			throws ManagerException {
		doSysLog(request, serviceType, CssOperateLogType.ADD, baseEntity);
	}

	protected void doUpdateLog(HttpServletRequest request, CssServiceLogType serviceType, BaseEntity baseEntity)
			throws ManagerException {
		doSysLog(request, serviceType, CssOperateLogType.UPDATE, baseEntity);
	}

	protected void doDeleteLog(HttpServletRequest request, CssServiceLogType serviceType, BaseEntity baseEntity)
			throws ManagerException {
		doSysLog(request, serviceType, CssOperateLogType.DELETE, baseEntity);
	}

	protected void doQueryLog(HttpServletRequest request, CssServiceLogType serviceType, BaseEntity baseEntity)
			throws ManagerException {
		doSysLog(request, serviceType, CssOperateLogType.QUERY, baseEntity);
	}

	protected void doCommLog(HttpServletRequest request, CssServiceLogType serviceType, String message,
			BaseEntity... entities) throws ManagerException {
		saveLog(request, serviceType, CssOperateLogType.REQUEST, message, serviceType.getServiceName(), entities);
	}

	protected void doSysLog(HttpServletRequest request, CssServiceLogType serviceType, CssOperateLogType operateType,
			String message, String operateItem, BaseEntity... entities) throws ManagerException {
		saveLog(request, serviceType, operateType, message, operateItem, entities);
	}

	private void doSysLog(HttpServletRequest request, CssServiceLogType serviceType, CssOperateLogType operateType,
			BaseEntity... entities) throws ManagerException {
		saveLog(request, serviceType, operateType, null, null, entities);
	}

	private void saveLog(HttpServletRequest request, CssServiceLogType serviceType, CssOperateLogType operateType,
			String message, String operateItem, BaseEntity... entities) throws ManagerException {
		String remoteIp = LoginHelper.getLoginIP(request);
		User user = LoginHelper.getLoginUser(request);
		String serverIp = CssUtil.getLocalHostLANAddress().getHostAddress();
		String requestURL = request.getRequestURL().toString();
		String operateTime = CssUtil.getNowDateTimeStrWithoutT();
		String data = entities[0].toJson();
		Class<?> clazz = entities[0].getClass();
		if (!StringTools.hasText(operateItem)) {
			operateItem = clazz.getSimpleName();
		}
		String cardId = CssUtil.getPropertyValueFromJsonData("cardId", data);
		String obuId = CssUtil.getPropertyValueFromJsonData("obuId", data);
		String vehicleId = CssUtil.getPropertyValueFromJsonData("vehicleId", data);
		String customerId = CssUtil.getPropertyValueFromJsonData("customerId", data);
		String rechargeId = CssUtil.getPropertyValueFromJsonData("chargeId", data);
		if(!hasText(rechargeId)){
			rechargeId = CssUtil.getPropertyValueFromJsonData("rechargeId", data);
		}
		StringBuilder discription = new StringBuilder();
		if (StringTools.hasText(message)) {
			discription.append(message);
		} else {
			discription.append("用户").append(user.getName()).append("(").append(user.getId()).append(")")
					.append(operateType.getValue()).append(operateItem).append("成功");
		}
		OperateLog log = new OperateLog();
		log.setCreateTime(CssUtil.getNowDateTimeStrWithoutT());
		log.setDiscription(discription.toString());
		log.setOperateItem(operateItem);
		log.setOperateTime(operateTime);
		log.setOperateType(operateType);
		log.setOperatorId(user.getId());
		log.setData(data);
		log.setRemoteIp(remoteIp);
		log.setServerIp(serverIp);
		log.setServiceType(serviceType);
		log.setUrl(requestURL);
		log.setServiceName(serviceType.getServiceName());
		log.setUsername(user.getLoginName());
		log.setRelatedCardId(cardId);
		log.setRelatedCustomerId(customerId);
		log.setRelatedObuId(obuId);
		log.setRelatedVehicleId(vehicleId);
		log.setRelatedRechargeId(rechargeId);
		logManager.saveLog(log);
	}

}
