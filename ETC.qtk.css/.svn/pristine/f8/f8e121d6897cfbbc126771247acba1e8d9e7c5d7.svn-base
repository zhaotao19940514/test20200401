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
import cn.com.taiji.css.model.customerservice.obu.RewriteRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUInfoChangeResponse;
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
public interface RewriteManager {

	/**Os
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<OBUInfo> queryPage(RewriteRequest queryModel,User user) throws ManagerException;
	VehicleInfo queryVeInfo(RewriteRequest queryModel);

	void doOBURewrite(RewriteRequest offReq, User loginUser) throws ManagerException;
	/**
	 * @param queryModel
	 * @param loginUser
	 * @return 
	 */
	VehicleInfo vehInfoByObuId(RewriteRequest queryModel, User loginUser);
	OBUInfo findById(String obuId);
	OBUInfoChangeResponse reWriteVehicleInfo(RewriteRequest queryModel, User loginUser) throws ManagerException;
	OBUStatusChangeResponse vehicleInfoCheck(RewriteRequest queryModel) throws ManagerException;

}

