package cn.com.taiji.css.manager.administration.notice;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.administration.notice.NoticeConfigRequest;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.qtk.entity.BankSignVersionDetail;

/**
 * @ClassName SupplyPaymentManager.java
 * @author zhaotao
 * @Description 
 * @date2019年1月7日
 */
public interface NoticeConfigManager {

	/**
	 * @param queryModel
	 * @return
	 * @throws ManagerException 
	 */
	LargePagination<BankSignVersionDetail> queryPage(NoticeConfigRequest queryModel,User user) throws ManagerException;

	BankSignVersionDetail findbyId(String rowId);

	AppAjaxResponse edit(NoticeConfigRequest queryModel);

	AppAjaxResponse add(NoticeConfigRequest queryModel);


	//AppAjaxResponse doSupply(SupplyPaymentRequest queryModel);

}

