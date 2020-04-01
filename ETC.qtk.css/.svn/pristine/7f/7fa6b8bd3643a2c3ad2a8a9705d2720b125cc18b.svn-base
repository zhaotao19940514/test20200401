/*
 * Date: 2011-9-22
 * author: Peream  (peream@gmail.com)
 *
 */
package tests.cn.com.taiji.css.manager.acl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.SecurityUtil;
import cn.com.taiji.common.pub.SecurityUtil.HashType;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.entity.Unit;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.User.UserStatus;
import cn.com.taiji.css.manager.acl.UserManager;
import cn.com.taiji.css.repo.request.acl.UserPageRequest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-9-22 上午11:40:29<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class TestUserManager extends InitAclData
{
	@Autowired
	private UserManager userManager;

	@Test(expected = MyViolationException.class)
	public void addUserExist() throws JsonManagerException
	{
		User user = new User();
		user.setLoginName("acl_user1");
		user.setPasswd("123456");
		userManager.add(user, loginUser);
	}

	@Test(expected = MyViolationException.class)
	public void addUserNoRole() throws JsonManagerException
	{
		User user = new User();
		user.setLoginName("acl_user_test");
		user.setPasswd("123456");
		user.setRoleId("no this role");
		userManager.add(user, loginUser);
	}

	@Test
	public void addAndFind() throws JsonManagerException
	{
		User user = new User();
		user.setLoginName("acl_user_test");
		user.setPasswd("123456");
		user.setRoleId("acl_role1");
		user.setName("ccc");
		user.setMobile("13691517931");
		user.setConfirm_pw("123456");
		user.setMale(true);
		Unit unit=new Unit();
		unit.setId("acl_unit2");
		user.setUnit(unit);
		String id = userManager.add(user, loginUser);
		user = userManager.findById(id);
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getLoginName(), "acl_user_test");

		user = userManager.findByLoginName("acl_user_test");
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getName(), "ccc");

		user = userManager.findById("no this user");
		Assert.assertNull(user);
	}

	@Test(expected = JsonManagerException.class)
	public void addWithUnitOutBounds() throws JsonManagerException
	{
		User user = new User();
		user.setLoginName("acl_user_test");
		user.setPasswd("123456");
		user.setRoleId("acl_role1");
		user.setName("ccc");
		user.setMobile("13691517931");
		user.setConfirm_pw("123456");
		user.setMale(true);
		Unit unit=new Unit();
		unit.setId("acl_unit1");
		user.setUnit(unit);
		userManager.add(user, loginUser);
	}
	@Test
	public void queryPage()
	{
		UserPageRequest qm = new UserPageRequest();
		qm.setStatus(UserStatus.NORMAL);
		User loginUser=new User();
		loginUser.setLoginName("admin");
		Pagination pg = userManager.queryPage(qm, loginUser);
		Assert.assertTrue(pg.getResult().size() > 0);

		qm.setUserName("acl");
		pg = userManager.queryPage(qm, loginUser);
		Assert.assertTrue(pg.getResult().size() > 0);
	}

	@Test(expected = JsonManagerException.class)
	public void updateNotExist() throws JsonManagerException
	{
		User user = new User();
		user.setId("no this user");
		userManager.update(user, loginUser);
	}
	
	@Test(expected = JsonManagerException.class)
	public void updateWithUnitOutBounds() throws JsonManagerException
	{
		User user = userManager.findById("admin");
		Unit unit=new Unit();
		unit.setId("acl_unit1");
		user.setUnit(unit);
		userManager.update(user, loginUser);
	}

	@Test(expected = MyViolationException.class)
	public void updateSystem() throws JsonManagerException
	{
		User user = userManager.findByLoginName("admin");
		user.setRoleId("acl_role1");
		userManager.update(user, loginUser);
	}

	@Test
	public void update() throws JsonManagerException
	{
		User user = userManager.findByLoginName("acl_user1");
		user.setRoleId("no this role");
		userManager.update(user, loginUser);

		user.setRoleId("acl_role2");
		user.setName("ccc");
		user = userManager.update(user, loginUser);
		Assert.assertEquals("ccc", user.getName());
	}

	@Test(expected = ManagerException.class)
	public void updateStatusNotExist() throws ManagerException
	{
		userManager.updateStatus("no this user", UserStatus.NORMAL);
	}

	@Test(expected = ManagerException.class)
	public void updateStatusSystem() throws ManagerException
	{
		User user = userManager.findByLoginName("admin");
		userManager.updateStatus(user.getId(), UserStatus.INVALID);
	}

	@Test
	public void updateStatus() throws ManagerException
	{
		userManager.updateStatus("acl_user1", UserStatus.INVALID);
		User user = userManager.findByLoginName("acl_user1");
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getStatus(), UserStatus.INVALID);
	}

	@Test(expected = ManagerException.class)
	public void modPasswdNotExist() throws ManagerException
	{
		userManager.modPasswd("654321", "no this user");
	}

	@Test
	public void modPasswd() throws ManagerException
	{
		userManager.modPasswd("654321", "acl_user1");
		User user = userManager.findByLoginName("acl_user1");
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getPasswd(), SecurityUtil.encryptStr("654321", HashType.SHA_512, true));
	}

}
