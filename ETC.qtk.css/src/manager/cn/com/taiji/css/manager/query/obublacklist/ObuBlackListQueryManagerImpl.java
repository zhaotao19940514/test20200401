package cn.com.taiji.css.manager.query.obublacklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.repo.request.obublacklist.ObuBLackListQueryRequest;
import cn.com.taiji.qtk.entity.ObuBlackList;
import cn.com.taiji.qtk.repo.jpa.ObuBlackListRepo;

@Service
public class ObuBlackListQueryManagerImpl implements ObuBlackListQueryManager {

	@Autowired
	private ObuBlackListRepo repo;

	@Override
	public LargePagination<ObuBlackList> findById(ObuBLackListQueryRequest req) {
		return repo.largePage(req);
	}
	
}
