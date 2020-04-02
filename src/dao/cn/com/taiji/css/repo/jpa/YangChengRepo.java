package cn.com.taiji.css.repo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.css.entity.YangCheng;

public interface YangChengRepo extends AbstractJpaRepo<YangCheng, String>
{
	@Query(" from YangCheng ")
	public List<YangCheng> listAll();
	
	
}
