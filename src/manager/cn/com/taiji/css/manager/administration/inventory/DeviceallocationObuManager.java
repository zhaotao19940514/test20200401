package cn.com.taiji.css.manager.administration.inventory;

import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.model.administration.inventory.CardNoCalculateRequest;
import cn.com.taiji.css.model.administration.inventory.CardNoCalculateResponse;
import cn.com.taiji.css.model.administration.inventory.ScheDulingAppResponse;
import cn.com.taiji.css.model.administration.inventory.StorageOperationRequest;
import cn.com.taiji.qtk.entity.StorageObuInfoBatch;

public interface DeviceallocationObuManager {

	public ScheDulingAppResponse add(StorageOperationRequest req, User user) throws ManagerException;

	CardNoCalculateResponse generateEndId(CardNoCalculateRequest cardNoCalculateRequest);

	public StorageObuInfoBatch findById1(String id);
	
	void updateStorageCardInfoBatch(StorageObuInfoBatch storageObuInfoBatch) throws ManagerException;
	
//	void reversalStorageObuInfoBatch(StorageObuInfoBatch storageObuInfoBatch) throws ManagerException;
	
	List<ZTreeItem> getCurrentConf();
}
