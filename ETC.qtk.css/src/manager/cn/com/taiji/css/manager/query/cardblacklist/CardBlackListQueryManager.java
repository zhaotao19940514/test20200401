package cn.com.taiji.css.manager.query.cardblacklist;

import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.repo.request.cardblacklist.CardBLackListQueryRequest;
import cn.com.taiji.qtk.entity.CardBlackList;

public interface CardBlackListQueryManager{
	
	LargePagination<CardBlackList> findById(CardBLackListQueryRequest req);
//	List<CardBlackList> findById(CardBLackListQueryRequest req);
}
