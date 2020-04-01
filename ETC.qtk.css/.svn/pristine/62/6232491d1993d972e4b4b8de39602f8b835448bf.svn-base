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

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.Unit;
import cn.com.taiji.css.manager.acl.UnitManager;
import cn.com.taiji.css.model.acl.UnitModel;
import cn.com.taiji.css.repo.request.acl.UnitPageRequest;

/**
 * @author wanglijun
 * 		   Create Time 2016年11月24日 上午11:43:10
 * @since 1.0
 * @version 1.0
 */
public class TestUnitManager extends InitAclData
{
	@Autowired
	private UnitManager unitManager;

	@Test
	public void add() throws JsonManagerException{
		Unit unit=new Unit();
		String id = "acl_unit3";
		String parentId = "acl_unit2";
		unit.setId(id);
		unit.setParentId(parentId);
		unit.setName("二级单位");
		unit.setList(1);
		unitManager.add(unit);
		UnitModel parent=(UnitModel) unitManager.findById(parentId);
		Unit po=unitManager.listByParentId(parentId).get(0);
		Assert.assertTrue(po.getUnitLevel()==2);
		Assert.assertEquals("1-1-1",po.getCode());
		Assert.assertTrue(po.belongTo(parent));
	}

	@Test(expected=JsonManagerException.class)
	public void addNoParent() throws JsonManagerException{
		Unit unit=new Unit();
		String id = "acl_unit3";
		String parentId = "errorId";
		unit.setId(id);
		unit.setParentId(parentId);
		unit.setName("二级单位");
		unit.setList(1);
		unitManager.add(unit);
	}
	
	@Test
	public void update() throws JsonManagerException{
		Unit po=unitManager.findById("acl_unit2");
		po.setName("新单位");
		po.setParentId("acl_unit2");
		po=unitManager.update(po);
		Assert.assertEquals("acl_unit1", po.getParentId());
	}

	@Test(expected=JsonManagerException.class)
	public void deleteHasChild() throws JsonManagerException{
		unitManager.delete("acl_unit1");
	}
	
	@Test(expected=JsonManagerException.class)
	public void deleteHasUser() throws JsonManagerException{
		unitManager.delete("acl_unit2");
	}
	
	@Test
	public void findById(){
		UnitModel unit1=(UnitModel) unitManager.findById("acl_unit1");
		UnitModel unit2=(UnitModel) unitManager.findById("acl_unit2");
		Assert.assertTrue(unit1.isHasChild());
		Assert.assertFalse(unit2.isHasChild());
	}
	
	@Test
	public void queryPage(){
		UnitPageRequest req=new UnitPageRequest();
		Pagination page = unitManager.queryPage(req, loginUser);
		echo(page);
		Assert.assertTrue(page.getTotalCount()==1);
		req.setName("errorname");
		page = unitManager.queryPage(req, loginUser);
		Assert.assertTrue(page.getTotalCount()==0);
	}
	
	@Test
	public void listByParent(){
		List<UnitModel> list = unitManager.listByParentId("acl_unit1");
		Assert.assertEquals("acl_unit2", list.get(0).getId());
		
	}
}
