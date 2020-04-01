package cn.com.taiji.css.repo.jpa;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.css.entity.AppResource.MenuType;
import cn.com.taiji.css.entity.RoleResource;
import cn.com.taiji.css.entity.dict.ResourceType;

public interface RoleResourceRepo extends AbstractJpaRepo<RoleResource, String>
{
	@Transactional
	@Modifying
	@Query("delete from RoleResource where role.id=?1")
	public int deleteByRole(String roleId);

	@Query("from RoleResource where role.id=?1 and resource.id=?2")
	public RoleResource findByRoleResource(String roleId, String resourceId);

	@Query("select resource.type from RoleResource where role.id=?1 and resource.menuType<>?2 group by resource.type order by resource.type ")
	public List<ResourceType> listResourceType(String roleId, MenuType menuType);
}
