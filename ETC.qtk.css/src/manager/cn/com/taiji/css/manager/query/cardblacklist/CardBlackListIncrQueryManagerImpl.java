package cn.com.taiji.css.manager.query.cardblacklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.repo.request.cardblacklist.CardBLackListIncrQueryRequest;
import cn.com.taiji.qtk.entity.CardBlackListIncr;
import cn.com.taiji.qtk.repo.jpa.CardBlackListIncrRepo;

@Service
public class CardBlackListIncrQueryManagerImpl implements CardBlackListIncrQueryManager {

	@Autowired
	private CardBlackListIncrRepo repo;

	@Override
	public LargePagination<CardBlackListIncr> findById(CardBLackListIncrQueryRequest req) {
		return repo.largePage(req);
	}
	
}
