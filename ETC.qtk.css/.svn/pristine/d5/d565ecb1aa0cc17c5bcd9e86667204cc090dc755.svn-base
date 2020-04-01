package cn.com.taiji.css.repo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.entity.AppResource.MenuType;

public interface AppResourceRepo extends AbstractJpaRepo<AppResource, String>
{
	@Transactional
	@Modifying
	@Query("delete from AppResource where menuId=?1 and menuType=?2")
	public int deleteByMenu(String menuId, MenuType menuType);

	@Query("from AppResource where menuId=?1 order by list asc")
	public List<AppResource> listResource(String menuId);

	@Query("select count(id) from AppResource where menuId=?1 and menuType=?2")
	public long count(String menuId, MenuType menuType);
}
