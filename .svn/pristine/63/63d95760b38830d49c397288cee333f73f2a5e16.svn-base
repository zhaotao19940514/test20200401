package cn.com.taiji.css.manager.query.obublacklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.repo.request.obublacklist.ObuBLackListIncrQueryRequest;
import cn.com.taiji.qtk.entity.ObuBlackListIncr;
import cn.com.taiji.qtk.repo.jpa.ObuBlackListRepo;

@Service
public class ObuBlackListIncrQueryManagerImpl implements ObuBlackListIncrQueryManager {

	@Autowired
	private ObuBlackListRepo repo;

	@Override
	public LargePagination<ObuBlackListIncr> findById(ObuBLackListIncrQueryRequest req) {
		return repo.largePage(req);
	}
	
}
