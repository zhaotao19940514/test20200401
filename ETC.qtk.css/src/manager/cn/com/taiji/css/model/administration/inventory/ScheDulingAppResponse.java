package cn.com.taiji.css.model.administration.inventory;

import cn.com.taiji.dsi.model.common.storage.ScheDulingResponse;
import cn.com.taiji.qtk.entity.StorageCardInfoBatch;
import cn.com.taiji.qtk.entity.StorageObuInfoBatch;

public class ScheDulingAppResponse extends ScheDulingResponse {
	private StorageObuInfoBatch storageObuInfoBatch;
	private StorageCardInfoBatch storageCardInfoBatch;
	public StorageObuInfoBatch getStorageObuInfoBatch() {
		return storageObuInfoBatch;
	}
	public void setStorageObuInfoBatch(StorageObuInfoBatch storageObuInfoBatch) {
		this.storageObuInfoBatch = storageObuInfoBatch;
	}
	public StorageCardInfoBatch getStorageCardInfoBatch() {
		return storageCardInfoBatch;
	}
	public void setStorageCardInfoBatch(StorageCardInfoBatch storageCardInfoBatch) {
		this.storageCardInfoBatch = storageCardInfoBatch;
	}
	
}
