package cn.com.taiji.css.manager.issuetranscation;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.model.issuetranscation.CardAnnounceRecordRequest;

public interface CardAnnounceRecordManager {
	Pagination page(CardAnnounceRecordRequest request);
}
