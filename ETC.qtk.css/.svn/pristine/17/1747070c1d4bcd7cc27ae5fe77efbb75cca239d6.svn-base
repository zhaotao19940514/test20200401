/*
 * Date: 2011-9-22
 * author: Peream  (peream@gmail.com)
 *
 */
package tests.cn.com.taiji.css.manager.acl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.entity.Role;
import cn.com.taiji.css.manager.acl.AclManager;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-9-22 上午10:53:40<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class TestAclManager extends InitAclData
{
	@Autowired
	private AclManager manager;

	@Test
	public void getResource()
	{
		AppResource resource = manager.getResource("no this resource", RequestMethod.GET);
		Assert.assertNull(resource);
		resource = manager.getResource("acl/column1.do", RequestMethod.GET);
		Assert.assertNotNull(resource);
	}

	@Test
	public void checkPower()
	{
		Assert.assertTrue(StringTools.hasText(manager.checkPower(null, null)));

		String rs = manager.checkPower(new Role("111"), null);
		Assert.assertTrue(!StringTools.hasText(rs));

		AppResource res = new AppResource();
		res.setRequestMethod(RequestMethod.GET);
		res.setRealUrl("acl/column1.do");
		rs = manager.checkPower(new Role("111"), res);
		Assert.assertTrue(StringTools.hasText(rs));

		rs = manager.checkPower(new Role("acl_role1"), res);
		echo(rs);
		Assert.assertTrue(!StringTools.hasText(rs));

		res.setRealUrl("acl/column3.do");
		rs = manager.checkPower(new Role("acl_role1"), res);
		Assert.assertTrue(StringTools.hasText(rs));
	}
}
