package cn.com.taiji.css.repo.jpa;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.css.entity.SystemLog;

public interface SystemLogRepo extends AbstractJpaRepo<SystemLog, String>
{
	@Query("select to_char(optime,'yyyymmdd'),user.id,count(*) from SystemLog"
			+ " where optime>?1 and optime<?2 and user.id in(?3) " + " group by to_char(optime,'yyyymmdd'),user.id"
			+ " order by to_char(optime,'yyyymmdd')")
	public List<Object[]> groupStat(LocalDateTime beginTime, LocalDateTime endTime, Collection<String> userIds);

//	@Query("select date_format(optime,'%Y%m%d'),user.id,count(*) from SystemLog"
//			+ " where optime>?1 and optime<?2 and user.id in(?3) " + " group by date_format(optime,'%Y%m%d'),user.id"
//			+ " order by date_format(optime,'%Y%m%d')")
//	public List<Object[]> groupStat(LocalDateTime beginTime, LocalDateTime endTime, Collection<String> userIds);

	
	@Query("select user.loginName,count(*) from SystemLog where optime>?1"
			+ " group by user.loginName  order by count(*) desc")
	public List<Object[]> groupStat(LocalDateTime beginTime);
}
