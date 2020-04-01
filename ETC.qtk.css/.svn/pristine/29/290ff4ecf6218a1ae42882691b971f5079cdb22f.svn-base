package cn.com.taiji.css.manager.acl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.AssertUtil;
import cn.com.taiji.common.pub.CollectionTools;
import cn.com.taiji.common.validation.MyViolationException;
import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.entity.Role;
import cn.com.taiji.css.entity.RoleResource;
import cn.com.taiji.css.entity.Unit;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.ResourceType;
import cn.com.taiji.css.model.acl.ColumnMenu;
import cn.com.taiji.css.model.acl.ConfRoleModel;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.repo.jpa.AppResourceRepo;
import cn.com.taiji.css.repo.jpa.RoleRepo;
import cn.com.taiji.css.repo.jpa.RoleResourceRepo;
import cn.com.taiji.css.repo.jpa.UnitRepo;
import cn.com.taiji.css.repo.jpa.UserRepo;
import cn.com.taiji.css.repo.request.acl.AppResourceColRequest;
import cn.com.taiji.css.repo.request.acl.AppResourceListRequest;
import cn.com.taiji.css.repo.request.acl.RoleCountRequest;
import cn.com.taiji.css.repo.request.acl.RoleListRequest;
import cn.com.taiji.css.repo.request.acl.RolePageRequest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2009-6-11 下午04:01:30<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service("roleManager")
public class RoleManagerImpl extends AbstractManager implements RoleManager
{
	@Autowired
	private UnitRepo unitRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleResourceRepo rrRepo;
	@Autowired
	private AppResourceRepo resourceRepo;
	@Autowired
	private RoleResourceHolder resourceHolder;

	@Override
	@Transactional
	public String add(Role role, User loginUser) throws ManagerException
	{
		AssertUtil.notNull(role);
		if (this.isNameExist(null, role.getName())) throw new MyViolationException("name", "不能添加重复的名称");
		Unit unit = unitRepo.findById(role.getUnit().getId()).orElse(null);
		if (unit == null) throw new MyViolationException("unit\\.name", "所选的单位不存在");
		if (!unit.belongTo(loginUser.getUnit())) throw new ManagerException("单位超出范围");
		roleRepo.save(role);
		role.setSystem(false);
		return roleRepo.save(role).getId();
	}

	@Override
	@Transactional
	public Role getDefaultRole()
	{
		Role role = roleRepo.findById(Role.DEFAULT_ID).orElse(null);
		if (role != null) return role;
		role = new Role(Role.DEFAULT_ID);
		role.setName("system_default_role");
		role.setSystem(true);
		role.setList(999);
		return roleRepo.save(role);
	}

	@Override
	public Role findById(String id)
	{
		return roleRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public String delete(String roleId) throws ManagerException
	{
		Role role = roleRepo.findById(roleId).orElse(null);
		if (role == null) throw new ManagerException("角色不存在");
		if (role.isSystem()) throw new ManagerException("系统角色不能删除:" + role.getName());
		long count = userRepo.count(roleId);
		if (count > 0) throw new ManagerException("该角色下还有用户，不能删除");
		String roleName = role.getName();
		rrRepo.deleteByRole(roleId);
		roleRepo.delete(role);
		return roleName;
	}

	@Override
	@Transactional
	public void update(Role role, User loginUser) throws ManagerException
	{
		AssertUtil.notNull(role);
		if (role.isSystem()) throw new ManagerException("系统角色不能修改");
		if (this.isNameExist(role.getId(), role.getName())) throw new ManagerException("指定名称的角色已经存在:" + role.getName());
		Unit unit = unitRepo.findById(role.getUnit().getId()).orElse(null);
		if (unit == null) throw new MyViolationException("unit\\.name", "所选的单位不存在");
		if (!unit.belongTo(loginUser.getUnit())) throw new ManagerException("单位超出范围");
		roleRepo.save(role);
	}

	@Override
	public List<ZTreeItem> getCurrentConf(String ownerRoleId, String confRoleId)
	{
		if (ownerRoleId.equals("admin"))
		{
			ownerRoleId = null;
		}
		List<ZTreeItem> rs = new ArrayList<ZTreeItem>();
		for (ResourceType type : ResourceType.values())
		{
			List<AppResource> colResources = resourceRepo.list(new AppResourceColRequest(type, ownerRoleId));
			if (colResources.size() > 0)
			{
				ZTreeItem item = new ZTreeItem();
				item.setId(type.name());
				item.setName(type.getValue());
				List<ZTreeItem> children = handleChildren(colResources, ownerRoleId, confRoleId);
				boolean checked = true;
				for (ZTreeItem r : children)
				{
					if (r.isChecked() == false)
					{
						checked = false;
					}
				}
				item.setChecked(checked);
				item.setChildren(children);
				rs.add(item);
			}
		}
		return rs;
	}

	private List<ZTreeItem> handleChildren(List<AppResource> res, String ownerRoleId, String confRoleId)
	{
		List<ZTreeItem> list = new ArrayList<ZTreeItem>();
		for (AppResource resource : res)
		{
			ZTreeItem item = new ZTreeItem();
			item.setId(resource.getId());
			item.setName(toLogString("{}({}_{})", resource.getName(), resource.getUrl(),
					resource.getRequestMethod().name()));
			RoleResource rr = rrRepo.findByRoleResource(confRoleId, resource.getId());
			if (rr != null) item.setChecked(true);
			item.setChildren(handleChildren(resourceRepo.list(new AppResourceListRequest(item.getId(), ownerRoleId)),
					ownerRoleId, confRoleId));
			list.add(item);
		}
		return list;
	}

	@Override
	public Map<ResourceType, List<ColumnMenu>> getCurrentConf(String roleId)
	{
		Map<ResourceType, List<ColumnMenu>> rs = new LinkedHashMap<ResourceType, List<ColumnMenu>>();
		for (ResourceType type : ResourceType.values())
		{
			List<ColumnMenu> colMenus = new ArrayList<ColumnMenu>();
			List<AppResource> colResources = resourceRepo.list(new AppResourceColRequest(type, null));
			for (AppResource colResource : colResources)
			{
				String colResourceId = colResource.getId();
				ColumnMenu menu = new ColumnMenu();
				menu.setColumnResource(colResource);
				menu.setHasColumnResource(rrRepo.findByRoleResource(roleId, colResourceId) != null);
				menu.setHasTabMenu(false);
				List<AppResource> tabResources = resourceRepo.listResource(colResourceId);
				if (!CollectionTools.isEmpty(tabResources))// 标签菜单不为空
				{
					menu.setHasTabMenu(true);
					handleResources(tabResources, roleId);
				}
				menu.setTabResources(tabResources);
				colMenus.add(menu);
			}
			rs.put(type, colMenus);
		}
		return rs;
	}

	private void handleResources(List<AppResource> resources, String roleId)
	{
		for (AppResource resource : resources)
		{
			RoleResource rr = rrRepo.findByRoleResource(roleId, resource.getId());
			if (rr != null) resource.setHasResource(true);
		}
	}

	@Override
	@Transactional(rollbackFor = { ManagerException.class })
	public void confRole(ConfRoleModel conf) throws ManagerException
	{
		AssertUtil.allElementsHasValue(conf, conf.getResourceIds());
		Role role = roleRepo.findById(conf.getRoleId()).orElse(null);
		if (role == null) throw new ManagerException("角色不存在,可能已经被其他用户删除");
		if (role.isSystem()) throw new ManagerException("不能配置系统内置角色的权限");
		rrRepo.deleteByRole(conf.getRoleId());
		for (String resourceId : conf.getResourceIds())
		{
			AppResource resource = resourceRepo.findById(resourceId).orElse(null);
			if (resource == null) throw new ManagerException("该资源不存在,请检查页面与数据库的配置是否一致");
			// 传进来的resourceIds可能重复
			if (rrRepo.findByRoleResource(role.getId(), resourceId) != null) continue;
			rrRepo.save(new RoleResource(role, resource));
		}
		// 清空角色拥有权限的缓存
		resourceHolder.removeRoleMenus(conf.getRoleId());
	}

	@Override
	public List<Role> getAll(User loginUser)
	{
		return roleRepo.list(new RoleListRequest(null, loginUser.getUnitLikeCode()));
	}

	@Override
	public List<Role> listAll(User loginUser, RoleListRequest req)
	{
		req.setUnitLikeCode(loginUser.getUnitLikeCode());
		return roleRepo.list(req);
	}

	@Override
	public Pagination queryPage(RolePageRequest req, User loginUser)
	{
		req.setUnitLikeCode(loginUser.getUnitLikeCode());
		return roleRepo.page(req);
	}

	@Override
	public boolean isNameExist(String id, String name)
	{
		AssertUtil.notNull(name);
		return roleRepo.count(new RoleCountRequest(id, name)) > 0;
	}

	@Override
	public List<Role> listByName(String name, User loginUser)
	{
		AssertUtil.notNull(name);
		RoleListRequest req = new RoleListRequest(name, loginUser.getUnitLikeCode());
		return this.roleRepo.list(req);
	}

}
