package cn.com.taiji.css.manager.administration.inventory;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.administration.inventory.DevicemodelRequest;
import cn.com.taiji.qtk.entity.CssObuInfo;

public interface DeviceObumodelManager {

	/**
	 * @param queryModel
	 * @return
	 */
	Pagination queryPage(DevicemodelRequest queryModel);

	public CssObuInfo add(CssObuInfo obuModel);

	public CssObuInfo findById1(String id);

	CssObuInfo updateCssObuInfo(CssObuInfo obuModel) throws ManagerException;

	void delete(String id)throws ManagerException;
}
