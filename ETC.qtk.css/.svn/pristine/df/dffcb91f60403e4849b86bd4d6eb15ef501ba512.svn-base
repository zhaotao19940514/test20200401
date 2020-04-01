package cn.com.taiji.css.manager.administration.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.administration.inventory.StorageCardInfoRequest;
import cn.com.taiji.qtk.repo.jpa.StorageCardInfoRepo;

@Service("inventoryQueryManager")
public class InventoryQueryManagerImpl extends AbstractDsiCommManager implements InventoryQueryManager{
	@Autowired
	private StorageCardInfoRepo storageCardInfoRepo;

	@Override
	public Pagination queryPage(StorageCardInfoRequest queryModel) {
		return storageCardInfoRepo.page(queryModel);
	}
}
