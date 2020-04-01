/**
 * @Title RechargeManager.java
 * @Package cn.com.taiji.css.manager.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:16:25
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.obu;

import javax.validation.Valid;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.obu.OBUTransferRequest;
import cn.com.taiji.css.model.customerservice.obu.OBUTransferResponse;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.entity.VehicleInfo;

/**
 * @ClassName RechargeManager
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:16:25
 * @E_mail yaonanlin@163.com
 */
public interface OBUTransferManager {

	/**
	 * @param queryModel
	 * @return
	 */
	OBUInfo findById(String obuId);

	LargePagination<VehicleInfo> queryPage(OBUTransferRequest queryModel, User loginUser) throws ManagerException;

	VehicleInfo findByVehicleInfo(String vehicleId);

	Object findByCustomerInfo(String customerId);

	Integer findBrandById(String vehicleId);

	OBUTransferResponse infoChange(@Valid OBUTransferRequest queryModel, User loginUser);

}

