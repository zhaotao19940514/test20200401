package cn.com.taiji.css.manager.query.obublacklist;

import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.repo.request.obublacklist.ObuBLackListIncrQueryRequest;
import cn.com.taiji.qtk.entity.ObuBlackListIncr;

public interface ObuBlackListIncrQueryManager{
		
	LargePagination<ObuBlackListIncr> findById(ObuBLackListIncrQueryRequest req);
//	List<ObuBlackList> findById(ObuBLackListQueryRequest req);
}
