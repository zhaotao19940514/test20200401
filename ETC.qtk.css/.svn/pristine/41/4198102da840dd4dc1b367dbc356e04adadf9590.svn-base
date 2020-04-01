package cn.com.taiji.css.manager.administration.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.manager.abstractmanager.AbstractDsiCommManager;
import cn.com.taiji.css.model.administration.inventory.StorageObuInfoRequest;
import cn.com.taiji.qtk.repo.jpa.StorageObuInfoRepo;
@Service("obuInventoryQueryManager")
public class ObuInventoryQueryManagerImpl extends AbstractDsiCommManager implements ObuInventoryQueryManager{
	@Autowired
	private StorageObuInfoRepo storageObuInfoRepo;
	@Override
	public Pagination queryPage(StorageObuInfoRequest queryModel) {
		return storageObuInfoRepo.page(queryModel);
	}

}
