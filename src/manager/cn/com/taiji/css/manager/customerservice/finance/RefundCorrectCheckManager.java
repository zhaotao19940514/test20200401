
package cn.com.taiji.css.manager.customerservice.finance;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.model.customerservice.finance.RefundCorrectCheckRequest;
import cn.com.taiji.qtk.entity.ReckonAccountRefundDetail;


public interface RefundCorrectCheckManager {

	/**
	 * @param queryModelO
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<ReckonAccountRefundDetail> queryPage(RefundCorrectCheckRequest queryModel) throws ManagerException;

}

