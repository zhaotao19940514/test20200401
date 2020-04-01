package cn.com.taiji.css.manager.administration.inventory;

import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.model.administration.inventory.CardNoCalculateRequest;
import cn.com.taiji.css.model.administration.inventory.CardNoCalculateResponse;
import cn.com.taiji.css.model.administration.inventory.StorageObuInfoBatchRequest;
import cn.com.taiji.css.model.administration.inventory.ObuInBoundAppResponse;
import cn.com.taiji.qtk.entity.StorageObuInfoBatch;

public interface DevicewarehousingObuManager {
	Pagination queryPage(StorageObuInfoBatchRequest queryModel);

	public ObuInBoundAppResponse add(StorageObuInfoBatch obuInfoBatchModel, User user) throws ManagerException;

	CardNoCalculateResponse generateEndId(CardNoCalculateRequest cardNoCalculateRequest);

	public StorageObuInfoBatch findById1(String id);
	
	void updateStorageObuInfoBatch(StorageObuInfoBatch obuInfoBatchModel) throws ManagerException;

	List<ZTreeItem> getCurrentConf();
	
}
