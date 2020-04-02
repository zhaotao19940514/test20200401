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
import cn.com.taiji.css.model.customerservice.obu.OBULossRequest;
import cn.com.taiji.css.model.customerservice.obu.OBUUnLossRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeResponse;
import cn.com.taiji.qtk.entity.OBUInfo;

/**
 * @ClassName RechargeManager
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:16:25
 * @E_mail yaonanlin@163.com
 */
public interface OBUUnLossManager {

	/**
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */

	LargePagination<OBUInfo> queryPage(OBUUnLossRequest queryModel, User loginUser) throws ManagerException;
	OBUInfo findById(String obuId);
	OBUStatusChangeResponse unLossOBU(OBUUnLossRequest queryModel, User loginUser) throws ManagerException;
}

