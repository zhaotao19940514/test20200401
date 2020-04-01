/*
 * Date: 2011-9-22
 * author: Peream  (peream@gmail.com)
 *
 */
package tests.cn.com.taiji.css.manager.acl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.css.entity.dict.ResourceType;
import cn.com.taiji.css.manager.acl.RoleResourceManager;
import cn.com.taiji.css.model.acl.RoleMenu;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-9-22 上午11:23:05<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class TestRoleResourceManager extends InitAclData
{
	@Autowired
	private RoleResourceManager rrManager;

	@Test
	public void getRoleMenu()
	{
		List<RoleMenu> menus = rrManager.getRoleMenu("acl_role1");
		Assert.assertTrue(menus.size() > 0);
	}

	@Test
	public void listResourceType()
	{
		List<ResourceType> list = rrManager.listResourceType("acl_role1");
		Assert.assertTrue(list.size() > 0);
	}

	@Test
	public void hasResource()
	{
		boolean rs = rrManager.hasResource("acl_role1", "acl_column1");
		Assert.assertTrue(rs);
		rs = rrManager.hasResource("acl_role1", "acl_column3");
		Assert.assertFalse(rs);
	}
}
