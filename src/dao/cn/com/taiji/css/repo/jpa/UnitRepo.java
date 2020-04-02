package cn.com.taiji.css.repo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.css.entity.Unit;

public interface UnitRepo extends AbstractJpaRepo<Unit, String>{

	@Query("from Unit where parentId=?1 order by code desc")
	public List<Unit> listByParentId(String id);
	
	
	@Query("from Unit where parentId=?1 and list=?2 and id!=?3")
	public List<Unit> listBy(String parentId,int list,String excludeId);
}
