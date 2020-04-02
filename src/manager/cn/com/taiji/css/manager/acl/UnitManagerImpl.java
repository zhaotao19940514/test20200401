package cn.com.taiji.css.manager.acl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.BeanTools;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.entity.Unit;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.model.acl.UnitModel;
import cn.com.taiji.css.repo.jpa.RoleRepo;
import cn.com.taiji.css.repo.jpa.UnitRepo;
import cn.com.taiji.css.repo.jpa.UserRepo;
import cn.com.taiji.css.repo.request.acl.UnitPageRequest;

/**
 * @author wanglijun Create Time 2016年11月22日 下午3:40:18
 * @since 1.0
 * @version 1.0 测试： 1、添加用户、角色所选单位不超范围
 */
@Service("unitManager")
public class UnitManagerImpl extends AbstractManager implements UnitManager
{

	@Autowired
	private UnitRepo repo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public Pagination queryPage(UnitPageRequest req, User user)
	{
		req.setLikeCode(user.getUnitLikeCode());
		return repo.page(req).convertResult(this::setHasChild);
	}

	@Override
	@Transactional(rollbackFor = { JsonManagerException.class })
	public Unit add(Unit model) throws JsonManagerException
	{
		Unit parent = repo.findById(model.getParentId()).orElse(null);
		if (parent == null)
		{
			throw new JsonManagerException("父单位未找到！");
		}
		if (repo.listBy(parent.getId(), model.getList(), "-1").size() > 0)
		{
			throw new MyViolationException("list", "序号重复！");
		}
		List<Unit> list = repo.listByParentId(parent.getId());
		String code = parent.getCode() + "-1";
		if (list.size() > 0)
		{
			String maxCode = list.get(0).getCode();
			String maxNum = maxCode.substring(maxCode.lastIndexOf("-") + 1);
			code = parent.getCode() + "-" + (1 + Integer.parseInt(maxNum));
		}
		model.setCode(code);
		model.setUnitLevel(parent.getUnitLevel() + 1);
		// 设置全局排序号
		model.setGlobalList(calculateGlobalList(parent, model.getList()));
		repo.save(model);
		return setHasChild(model);
	}

	@Override
	@Transactional(rollbackFor = { JsonManagerException.class })
	public Unit update(Unit model) throws JsonManagerException
	{
		Unit po = repo.findById(model.getId()).orElse(null);
		if (repo.listBy(po.getParentId(), model.getList(), model.getId()).size() > 0)
		{
			throw new MyViolationException("list", "序号重复！");
		}
		Integer oldList = po.getList();
		BeanTools.copyProperties(model, po, new String[] { "id", "code", "unitLevel", "parentId", "globalList" });
		if (po.getList() != oldList && po.getId().equals("root") == false)
		{
			Unit parent = repo.findById(po.getParentId()).orElse(null);
			po.setGlobalList(calculateGlobalList(parent, po.getList()));
			updateChildrenGlobalList(po);
		}
		repo.save(po);
		return setHasChild(po);
	}

	private void updateChildrenGlobalList(Unit po)
	{
		List<Unit> children = repo.listByParentId(po.getId());
		children.stream().forEach(u -> {
			u.setGlobalList(calculateGlobalList(po, u.getList()));
			repo.save(u);
			updateChildrenGlobalList(u);
		});
	}

	public String calculateGlobalList(Unit parent, Integer curList)
	{
		return parent.getGlobalList() + "-" + String.format("%04d", curList);
	}

	@Override
	@Transactional(rollbackFor = { JsonManagerException.class })
	public void delete(String id) throws JsonManagerException
	{
		Unit po = repo.findById(id).orElse(null);
		if (po == null) throw new JsonManagerException("找不到记录！");
		if (repo.listByParentId(id).size() > 0) throw new JsonManagerException("有下级单位，不能删除！");
		if (userRepo.listByUnit(id).size() > 0) throw new JsonManagerException("已被引用，不能删除！");
		if (roleRepo.listByUnit(id).size() > 0) throw new JsonManagerException("已被引用，不能删除！");
		// TODO被引用后不能删除
		repo.delete(po);
	}

	@Override
	public Unit findById(String id)
	{
		return setHasChild(repo.findById(id).orElse(null));
	}

	private UnitModel setHasChild(Unit unit)
	{
		UnitModel model = new UnitModel();
		BeanTools.copyProperties(unit, model);
		model.setHasChild(repo.listByParentId(unit.getId()).size() > 0);
		return model;
	}

	@Override
	public List<UnitModel> listByParentId(String parentId)
	{
		return repo.listByParentId(parentId).stream().map(this::setHasChild).collect(Collectors.toList());
	}
}
