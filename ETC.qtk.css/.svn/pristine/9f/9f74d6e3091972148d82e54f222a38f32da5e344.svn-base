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
import cn.com.taiji.css.model.customerservice.obu.OBUHangRequest;
import cn.com.taiji.css.model.customerservice.obu.OBUUnhangRequest;
import cn.com.taiji.css.model.customerservice.obu.RewriteRequest;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUInfoChangeResponse;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeResponse;
import cn.com.taiji.qtk.entity.OBUInfo;

/**
 * @ClassName RechargeManager
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:16:25
 * @E_mail yaonanlin@163.com
 */
public interface OBUUnhangManager {

	/**Os
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<OBUInfo> queryPage(OBUUnhangRequest queryModel,User user) throws ManagerException;

	void doUnHangObu(@Valid OBUHangRequest queryModel, User loginUser);

	OBUInfo findById(String obuId);

	OBUStatusChangeResponse updateObuStatus(@Valid RewriteRequest queryModel,User user);
	OBUInfoChangeResponse reWriteVehicleInfo(RewriteRequest queryModel, User loginUser) throws ManagerException;

}

