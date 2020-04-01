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
import cn.com.taiji.css.model.customerservice.obu.OBUCancelRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeResponse;
import cn.com.taiji.qtk.entity.OBUInfo;

/**
 * @ClassName RechargeManager
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:16:25
 * @E_mail yaonanlin@163.com
 */
public interface OBUCancelManager {

	/**Os
	 * @param queryModel
	 * @return
	 */

	OBUStatusChangeResponse doOBUCancel(OBUCancelRequest queryModel, User loginUser) throws ManagerException;

	LargePagination<OBUInfo> queryPage(OBUCancelRequest queryModel, User user) throws ManagerException;

	OBUInfo findById(String obuId);


}

