package cn.com.taiji.css.manager.administration.notice;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.administration.notice.NoticeRequest;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.qtk.entity.BankSignDetail;

/**
 * @ClassName SupplyPaymentManager.java
 * @author zhaotao
 * @Description 
 * @date2019年1月7日
 */
public interface NoticeManager {

	/**
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<BankSignDetail> queryPage(NoticeRequest queryModel,User user) throws ManagerException;

	AppAjaxResponse update(String rowId);


	//AppAjaxResponse doSupply(SupplyPaymentRequest queryModel);

}

