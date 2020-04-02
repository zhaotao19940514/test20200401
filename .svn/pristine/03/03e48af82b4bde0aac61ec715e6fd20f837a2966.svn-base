package cn.com.taiji.css.manager.administration.inventory;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.administration.inventory.CssCardRequest;
import cn.com.taiji.css.model.administration.inventory.DeviceCardModelRequest;
import cn.com.taiji.qtk.entity.CssCardInfo;

public interface DeviceCardModelManager {
	Pagination queryPage(DeviceCardModelRequest queryModel);

	public CssCardInfo add(CssCardInfo cardModel);

	public CssCardInfo findById1(String id);

	CssCardInfo updateCssCardInfo(CssCardRequest cardModel) throws ManagerException;

	void delete(String id)throws ManagerException;
}
