package cn.com.taiji.css.manager.administration.inventory;

import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.model.administration.inventory.CardEndCalculateRequest;
import cn.com.taiji.css.model.administration.inventory.CardEndCalculateResponse;
import cn.com.taiji.css.model.administration.inventory.ScheDulingAppResponse;
import cn.com.taiji.css.model.administration.inventory.StorageOperationRequest;
import cn.com.taiji.qtk.entity.StorageCardInfoBatch;

public interface DeviceallocationManager {

	public ScheDulingAppResponse add(StorageOperationRequest req, User user) throws ManagerException;
	
	CardEndCalculateResponse generateEndId(CardEndCalculateRequest cardEndCalculateRequest);

	public StorageCardInfoBatch findById1(String id);
	
	void updateStorageCardInfoBatch(StorageCardInfoBatch storageCardInfoBatch) throws ManagerException;
	
//	void reversalStorageCardInfoBatch(StorageCardInfoBatch storageCardInfoBatch) throws ManagerException;

	List<ZTreeItem> getCurrentConf();
}
