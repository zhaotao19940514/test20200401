package cn.com.taiji.css.manager.acl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.pub.AssertUtil;
import cn.com.taiji.common.pub.CollectionTools;
import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.entity.AppResource.MenuType;
import cn.com.taiji.css.entity.dict.ResourceType;
import cn.com.taiji.css.model.acl.ColumnMenu;
import cn.com.taiji.css.model.acl.HasButtonModel;
import cn.com.taiji.css.model.acl.RoleMenu;
import cn.com.taiji.css.repo.jpa.RoleResourceRepo;
import cn.com.taiji.css.repo.request.acl.RoleResourceListRequest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-5-17 下午02:09:42<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service("roleResourceManager")
public class RoleResourceManagerImpl extends AbstractManager implements RoleResourceManager
{
	@Autowired
	private RoleResourceRepo rrRepo;
	@Autowired
	private RoleResourceHolder resourceHolder;
	@Autowired
	private ResourceManager resourceManager;

	@Override
	public List<RoleMenu> getRoleMenu(String roleId)
	{
		List<RoleMenu> rs = new ArrayList<RoleMenu>();
		List<ResourceType> types = rrRepo.listResourceType(roleId, MenuType.NOT_MENU);
		for (ResourceType type : types)
		{
			RoleMenu menu = new RoleMenu();
			menu.setType(type);
			List<ColumnMenu> colMenus = new ArrayList<ColumnMenu>();
			RoleResourceListRequest req = new RoleResourceListRequest().setRoleId(roleId).setResourceType(type)
					.setMenuType(MenuType.COLUMN);
			List<AppResource> cols = rrRepo.list(req);// 取得所有栏目菜单
			for (AppResource colRes : cols)
			{
				ColumnMenu colMenu = new ColumnMenu();
				req = new RoleResourceListRequest().setRoleId(roleId).setMenuType(MenuType.BOX_TAB)
						.setColumnResourceId(colRes.getId());
				List<AppResource> tabRes = rrRepo.list(req);
				colMenu.setColumnResource(colRes);
				colMenu.setTabResources(tabRes);
				colMenu.setHasTabMenu(!CollectionTools.isEmpty(tabRes));
				colMenus.add(colMenu);
			}
			menu.setColumnMenus(colMenus);
			rs.add(menu);
		}
		return rs;
	}

	@Override
	public HasButtonModel hasButton(String roleId, String uri)
	{
		AssertUtil.hasText(uri);
		AppResource res = resourceManager.getResource(uri);
		if (res == null) return new HasButtonModel(uri, true, "资源未配置，不进行校验:" + uri);
		boolean rs = hasResource(roleId, res.getId());
		return rs ? new HasButtonModel(uri, true, "") : new HasButtonModel(uri, false, "无权访问:" + uri);
	}

	@Override
	public List<ResourceType> listResourceType(String roleId)
	{
		return rrRepo.listResourceType(roleId, MenuType.NOT_MENU);
	}

	@Override
	public boolean hasResource(String roleId, String resourceId)
	{
		return resourceHolder.hasResource(roleId, resourceId);
	}
}
