package cn.com.taiji.css.manager.query.cardblacklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.repo.request.cardblacklist.CardBLackListQueryRequest;
import cn.com.taiji.qtk.entity.CardBlackList;
import cn.com.taiji.qtk.repo.jpa.CardBlackListRepo;

@Service
public class CardBlackListQueryManagerImpl implements CardBlackListQueryManager {

	@Override
	public LargePagination<CardBlackList> findById(CardBLackListQueryRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Autowired
//	private CardBlackListRepo repo;
//
//	@Override
//	public LargePagination<CardBlackList> findById(CardBLackListQueryRequest req) {
//		return repo.largePage(req);
//	}
	
}
