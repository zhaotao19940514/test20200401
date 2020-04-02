package cn.com.taiji.css.manager.administration.inventory;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.administration.inventory.StorageCardInfoRequest;

public interface InventoryQueryManager {
	Pagination queryPage(StorageCardInfoRequest queryModel);
}
