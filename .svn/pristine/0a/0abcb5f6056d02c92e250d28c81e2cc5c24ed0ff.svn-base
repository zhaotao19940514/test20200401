/*
 * Date: 2011-9-21
 * author: Peream  (peream@gmail.com)
 *
 */
package tests.cn.com.taiji.css.manager.acl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.entity.AppResource.MenuType;
import cn.com.taiji.css.entity.dict.ResourceType;
import cn.com.taiji.css.manager.acl.ResourceManager;
import cn.com.taiji.css.repo.request.acl.AppResourcePageRequest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-9-21 下午2:03:56<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class TestResourceManager extends InitAclData
{
	@Autowired
	private ResourceManager manager;

	@Test
	public void getResourceByUrl()
	{
		AppResource resource = manager.getResourceByUrl("acl/column1.do", RequestMethod.GET);
		Assert.assertNotNull(resource);
	}

	@Test(expected = ManagerException.class)
	public void addExist() throws ManagerException
	{
		AppResource resource = new AppResource();
		resource.setUrl("acl/column1.do");
		manager.add(resource);
	}

	@Test(expected = ManagerException.class)
	public void addNoMenu() throws ManagerException
	{
		AppResource resource = new AppResource();
		resource.setUrl("acl/newcolumnfortest.do");
		resource.setMenuId("no this menu exist");
		manager.add(resource);
	}

	@Test
	public void addAndFind() throws ManagerException
	{
		AppResource resource = new AppResource();
		resource.setUrl("acl/newcolumnfortest.do");
		resource.setList(1);
		resource.setMenuType(MenuType.COLUMN);
		resource.setType(ResourceType.Z_XTGL);
		resource.setName("new column for test");
		String id = manager.add(resource);
		resource = manager.findById(id);
		Assert.assertNotNull(resource);
		Assert.assertEquals(resource.getUrl(), "acl/newcolumnfortest.do");
	}

	@Test(expected = ManagerException.class)
	public void updateNotExist() throws ManagerException
	{
		AppResource resource = new AppResource();
		resource.setId("no this resource");
		manager.update(resource);
	}

	@Test
	public void update() throws ManagerException
	{
		AppResource resource = manager.findById("acl_column1");
		Assert.assertNotNull(resource);
		resource.setUrl("acl/column1_update.do");
		resource.setName("acl_column1_update");
		resource.setType(ResourceType.Z_XTGL);
		resource.setList(99);
		manager.update(resource);
		resource = manager.findById(resource.getId());
		Assert.assertEquals(resource.getName(), "acl_column1_update");
		// 修改时不能修改URL
		// Assert.assertFalse(resource.getUrl().equals("acl/column1_update.do"));
	}

	@Test
	public void delete() throws ManagerException
	{
		manager.delete("no this resource");
		manager.delete("acl_column3");
	}

	@Test(expected = ManagerException.class)
	public void deleteHasTabMenu() throws ManagerException
	{
		manager.delete("acl_column2");
	}

	@Test(expected = ManagerException.class)
	public void deleteHasRole() throws ManagerException
	{
		manager.delete("acl_column1");
	}

	@Test
	public void queryPage()
	{
		AppResourcePageRequest qm = new AppResourcePageRequest();
		qm.setResourceType(ResourceType.Z_XTGL);
		qm.setMenuType(MenuType.COLUMN);
		qm.setName("栏目");
		Pagination pg = manager.queryPage(qm);
		Assert.assertNotNull(pg);
		Assert.assertTrue(pg.getResult().size() > 0);

		qm.setId("acl_column1");
		pg = manager.queryPage(qm);
		Assert.assertNotNull(pg);
		Assert.assertTrue(pg.getResult().size() == 0);

		qm.setName("no this resource name");
		pg = manager.queryPage(qm);
		Assert.assertNotNull(pg);
		Assert.assertTrue(pg.getResult().size() == 0);
	}

	@Test
	public void listByParent()
	{
		AppResourcePageRequest qm = new AppResourcePageRequest();
		qm.setResourceType(ResourceType.Z_XTGL);
		List<AppResource> list = manager.listByParent(qm);
		Assert.assertTrue(list.size() > 0);

		qm.setId("acl_column2");
		list = manager.listByParent(qm);
		Assert.assertTrue(list.size() > 0);

		qm.setId("acl_column_xxxx");
		list = manager.listByParent(qm);
		Assert.assertTrue(list.size() == 0);
	}
}
