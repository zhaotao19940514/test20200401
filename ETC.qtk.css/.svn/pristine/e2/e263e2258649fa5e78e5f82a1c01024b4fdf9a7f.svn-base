package tests.cn.com.taiji.css.manager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tests.MyNotTransationalTest;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.entity.AppResource.MenuType;
import cn.com.taiji.css.manager.acl.ResourceManager;
import cn.com.taiji.css.repo.jpa.AppResourceRepo;

public class TestDeleteHql extends MyNotTransationalTest
{

	@Autowired
	private ResourceManager manager;
	@Autowired
	private AppResourceRepo repo;

	@Test
	public void testRepo()
	{
		repo.deleteByMenu("1", MenuType.COLUMN);
	}

	@Test
	public void testRepo2()
	{
		AppResource r = repo.findById("5de7569dc7a64560b8d0dd9a1e8e2798").orElse(null);
		repo.delete(r);
		repo.save(r);
	}

	@Test
	public void testManager() throws ManagerException
	{
		AppResource r = repo.findById("5de7569dc7a64560b8d0dd9a1e8e2798").orElse(null);
		manager.delete("5de7569dc7a64560b8d0dd9a1e8e2798");
		repo.save(r);
	}

	@Test
	public void testManager2() throws ManagerException
	{
		AppResource r = repo.findById("5de7569dc7a64560b8d0dd9a1e8e2798").orElse(null);
		r.setName("remove2");
		manager.update(r);
	}
}
