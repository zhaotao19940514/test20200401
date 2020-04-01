package cn.com.taiji.css.repo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.css.entity.Role;

public interface RoleRepo extends AbstractJpaRepo<Role, String>
{
	@Query("from Role where unit.id=?1")
	public List<Role> listByUnit(String unitId);
	
	public Role findFirstByOrderByNameAsc();
	
	@Query("from Role where name=?1")
	public Role findByName(String name);
}
