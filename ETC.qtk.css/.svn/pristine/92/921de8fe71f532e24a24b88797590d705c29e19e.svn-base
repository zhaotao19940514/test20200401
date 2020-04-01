
package cn.com.taiji.css.manager.customerservice.finance;

import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.customerservice.finance.RefundImpLogRequest;
import cn.com.taiji.qtk.entity.RefundImpFailMessage;
import cn.com.taiji.qtk.entity.RefundImpLog;

public interface RefundImpLogManager {

	LargePagination<RefundImpLog> queryPage(RefundImpLogRequest queryModel, User loginUser) throws ManagerException;
	
	List<RefundImpFailMessage> queryDetails(RefundImpLogRequest queryModel) throws ManagerException;

	List<RefundImpFailMessage> listFailMessage(String id);

}

