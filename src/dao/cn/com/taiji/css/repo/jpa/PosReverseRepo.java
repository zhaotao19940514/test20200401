package cn.com.taiji.css.repo.jpa;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.css.entity.PosReverse;

public interface PosReverseRepo extends AbstractJpaRepo<PosReverse, String>
{
	@Query("from PosReverse where referno=?1 and createTime = ?2")
	PosReverse findByRefernoAndTime(String referno,LocalDate createTime);
	
}
