/**
 * @Title RechargeManager.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:25
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.obu;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.obu.ExchangeRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeResponse;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.entity.VehicleInfo;

/**
 * @ClassName RechargeManager
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:16:25
 * @E_mail yaonanlin@163.com
 */
public interface ExchangeManager {

	/**
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<OBUInfo> queryPage(ExchangeRequest queryModel,User user) throws ManagerException;

	OBUStatusChangeResponse doOBUExchange(ExchangeRequest queryModel, User loginUser) throws ManagerException;


	OBUInfo findById(String obuId);

	long obuRefund(User user,String newObuId);

	void VehicleInfoCheck(VehicleInfo vehicle) throws ManagerException;

	void doFullObuInfo(String obuId);

	boolean VehicleInfoCheck(VehicleInfo vehicle, OBUStatusChangeResponse oBUChangeRes) throws ManagerException;


}

