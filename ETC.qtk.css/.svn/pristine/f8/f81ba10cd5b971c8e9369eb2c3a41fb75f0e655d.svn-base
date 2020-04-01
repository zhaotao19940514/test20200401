package cn.com.taiji.css.repo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.css.entity.LmkStorageCard;

public interface LmkStorageCardRepo extends AbstractJpaRepo<LmkStorageCard, String> {
	@Query("from LmkStorageCard where startId<=?1 and endId>=?1")
	public List<LmkStorageCard> listByCardIdCheck(String cardId);
}
