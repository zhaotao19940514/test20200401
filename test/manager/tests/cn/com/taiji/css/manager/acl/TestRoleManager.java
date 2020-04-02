package tests.cn.com.taiji.css.manager.acl;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.entity.Role;
import cn.com.taiji.css.entity.Unit;
import cn.com.taiji.css.entity.dict.ResourceType;
import cn.com.taiji.css.manager.acl.RoleManager;
import cn.com.taiji.css.model.acl.ColumnMenu;
import cn.com.taiji.css.model.acl.ConfRoleModel;
import cn.com.taiji.css.repo.request.acl.RolePageRequest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-5-26 下午03:48:34<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class TestRoleManager extends InitAclData
{
	@Autowired
	private RoleManager roleManager;

	@Test
	public void addAndFind() throws ManagerException
	{
		Role role = new Role();
		role.setInfo("备注");
		role.setName("acl_fortest");
		role.setList(99);
		Unit unit=new Unit();
		unit.setId("acl_unit2");
		role.setUnit(unit);
		String id = roleManager.add(role, loginUser);
		role = roleManager.findById(id);
		Assert.assertNotNull(role);
		Assert.assertEquals("acl_fortest", role.getName());
	}

	@Test(expected = MyViolationException.class)
	public void addExist() throws ManagerException
	{
		Role role = roleManager.findById("acl_role1");
		Assert.assertNotNull(role);
		role = new Role();
		role.setInfo("备注");
		role.setName("acl_role1");
		role.setList(99);
		Unit unit=new Unit();
		unit.setId("acl_unit1");
		role.setUnit(unit);
		roleManager.add(role, loginUser);
	}

	@Test(expected = MyViolationException.class)
	public void addWithUnitOutBounds() throws ManagerException
	{
		Role role = new Role();
		role.setInfo("备注");
		role.setName("acl_role1");
		role.setList(99);
		Unit unit=new Unit();
		unit.setId("acl_unit1");
		role.setUnit(unit);
		roleManager.add(role, loginUser);
	}
	
	@Test(expected = ManagerException.class)
	public void updateSystem() throws ManagerException
	{
		Role role = roleManager.findById("admin");
		Assert.assertNotNull(role);
		Assert.assertTrue(role.isSystem());
		role.setName("ccc");
		roleManager.update(role, loginUser);
	}

	@Test(expected = ManagerException.class)
	public void updateNameExist() throws ManagerException
	{
		Role role = new Role();
		role.setId("acl_role1");
		role.setName("acl_role2");
		roleManager.update(role, loginUser);
	}

	@Test
	public void update() throws ManagerException
	{
		Role role = roleManager.findById("acl_role1");
		role.setName("acl_role1_changed");
		roleManager.update(role, loginUser);
		role = roleManager.findById("acl_role1");
		Assert.assertEquals(role.getName(), "acl_role1_changed");
	}

	@Test(expected = ManagerException.class)
	public void deleteNotExist() throws ManagerException
	{
		roleManager.delete("not exist id");
	}

	@Test(expected = ManagerException.class)
	public void deleteSystem() throws ManagerException
	{
		Role role = roleManager.findById("admin");
		Assert.assertTrue(role.isSystem());
		roleManager.delete(role.getId());
	}

	@Test(expected = ManagerException.class)
	public void deleteHasUser() throws ManagerException
	{
		roleManager.delete("acl_role1");
	}

	@Test
	public void delete() throws ManagerException
	{
		roleManager.delete("acl_role2");
	}

	@Test
	public void getAll()
	{
		List<Role> list = roleManager.getAll(loginUser);
		Assert.assertTrue(list.size() ==2);
	}
	
	@Test
	public void listByName(){
		
		List<Role> list = roleManager.listByName("role", loginUser);
		Assert.assertTrue(list.size() ==2);
	}

	@Test(expected = ManagerException.class)
	public void confRoleNotExist() throws ManagerException
	{
		roleManager.confRole(getConf("role not exist"));
	}

	@Test(expected = ManagerException.class)
	public void confRoleSystem() throws ManagerException
	{
		Role role = roleManager.findById("admin");
		Assert.assertTrue(role.isSystem());
		roleManager.confRole(getConf(role.getId()));
	}

	@Test
	public void confRole() throws ManagerException
	{
		roleManager.confRole(getConf("acl_role1"));
	}

	private ConfRoleModel getConf(String roleId)
	{
		ConfRoleModel model = new ConfRoleModel();
		model.setRoleId(roleId);
		model.setResourceIds(new String[] { "acl_column1", "acl_column2", "acl_column2_tab1",  "acl_column2_tab2", "acl_column2_tab1_add" });
		return model;
	}

	@Test
	public void confRoleNoColumn() throws ManagerException
	{
		ConfRoleModel model = new ConfRoleModel("acl_role1");
		model.setResourceIds(new String[] { "acl_column1","acl_column2_tab1" });
		roleManager.confRole(model);
	}

	@Test(expected = ManagerException.class)
	public void confRoleNoResource() throws ManagerException
	{
		ConfRoleModel model = new ConfRoleModel("acl_role1");
		model.setResourceIds(new String[] { "acl_column2", "not exist column" ,"acl_column2_tab1", "not exist tab"});
		roleManager.confRole(model);
	}

	@Test(expected = ManagerException.class)
	public void confRoleNoResource2() throws ManagerException
	{
		ConfRoleModel model = new ConfRoleModel("acl_role1");
		model.setResourceIds(new String[] { "acl_column2","acl_column2_tab1", "not exist tab" });
		roleManager.confRole(model);
	}

	@Test
	public void getCurrentConf() throws ManagerException
	{
		Map<ResourceType, List<ColumnMenu>> map = roleManager.getCurrentConf("admin");
		Assert.assertTrue(map.size() > 0);
	}

	@Test
	public void queryPage()
	{
		RolePageRequest qm = new RolePageRequest();
		qm.setName("acl");
		Pagination pg = roleManager.queryPage(qm, loginUser);
		Assert.assertNotNull(pg);
		Assert.assertTrue(pg.getResult().size() > 0);
		qm.setName("no this record exist");
		pg = roleManager.queryPage(qm, loginUser);
		Assert.assertNotNull(pg);
		Assert.assertTrue(pg.getResult().size() == 0);
	}
	
	
}
