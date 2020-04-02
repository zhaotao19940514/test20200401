package cn.com.taiji.css.repo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.com.taiji.common.repo.jpa.AbstractJpaRepo;
import cn.com.taiji.css.entity.User;

public interface UserRepo extends AbstractJpaRepo<User, String>
{
	@Query("select count(id) from User where role.id=?1")
	public long count(String roleId);

	@Query("from User where unit.id=?1")
	public List<User> listByUnit(String unitId);
	
	public User findByLoginName(String loginName);
	
	public User findByMobile(String mobile);
	@Query("from User where staffId=?1")
	public List<User> findByStaffId(String staffId);
}
