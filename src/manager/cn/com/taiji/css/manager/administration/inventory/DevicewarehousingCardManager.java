package cn.com.taiji.css.manager.administration.inventory;

import java.util.List;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.model.administration.inventory.CardEndCalculateRequest;
import cn.com.taiji.css.model.administration.inventory.CardEndCalculateResponse;
import cn.com.taiji.css.model.administration.inventory.CardInBoundAppResponse;
import cn.com.taiji.css.model.administration.inventory.StorageCardInfoBatchRequest;
import cn.com.taiji.qtk.entity.StorageCardInfoBatch;

public interface DevicewarehousingCardManager {
	Pagination queryPage(StorageCardInfoBatchRequest queryModel);

	public CardInBoundAppResponse add(StorageCardInfoBatch cardInfoBatchModel, User user) throws ManagerException;

	public StorageCardInfoBatch findById1(String id);
	
//	ScheDulingResponse updateCssCardInfo(StorageCardInfoBatch storageCardInfoBatchModel,User user) throws ManagerException;
	
//	String generateBatchId();
	
	CardEndCalculateResponse generateEndId(CardEndCalculateRequest cardEndCalculateRequest)throws ManagerException;
	
	void updateStorageCardInfoBatch(StorageCardInfoBatch storageCardInfoBatch) throws ManagerException;

	List<ZTreeItem> getCurrentConf(String agencyId);
	
}
