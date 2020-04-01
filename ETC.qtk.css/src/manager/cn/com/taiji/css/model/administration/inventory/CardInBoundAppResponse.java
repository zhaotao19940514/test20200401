package cn.com.taiji.css.model.administration.inventory;

import cn.com.taiji.dsi.model.common.storage.CardInBoundResponse;
import cn.com.taiji.qtk.entity.StorageCardInfoBatch;

public class CardInBoundAppResponse extends CardInBoundResponse{
	private StorageCardInfoBatch storageCardInfoBatch;

	public StorageCardInfoBatch getStorageCardInfoBatch() {
		return storageCardInfoBatch;
	}

	public void setStorageCardInfoBatch(StorageCardInfoBatch storageCardInfoBatch) {
		this.storageCardInfoBatch = storageCardInfoBatch;
	}
	
}
