
package cn.com.taiji.css.manager.customerservice.finance;

import javax.servlet.http.HttpServletRequest;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.model.customerservice.finance.CloseAcountRequest;
import cn.com.taiji.dsi.model.comm.protocol.finance.AccountCancelResponse;
import cn.com.taiji.qtk.entity.CustomerInfo;

public interface CloseAcountManager {
	
	LargePagination<CustomerInfo> queryPage(CloseAcountRequest queryModel,HttpServletRequest request) throws ManagerException;

	AccountCancelResponse accountCancel(CloseAcountRequest queryModel,HttpServletRequest request);
	
}

