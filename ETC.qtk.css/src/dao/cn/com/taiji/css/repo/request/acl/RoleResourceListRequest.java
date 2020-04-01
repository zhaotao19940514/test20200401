/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.repo.request.acl;

import cn.com.taiji.common.pub.AssertUtil;
import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaSortDataRequest;
import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.entity.AppResource.MenuType;
import cn.com.taiji.css.entity.RoleResource;
import cn.com.taiji.css.entity.dict.ResourceType;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年11月7日 下午2:29:52<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class RoleResourceListRequest extends JpaSortDataRequest<AppResource>
{
	private String roleId;
	private String columnResourceId;
	private MenuType menuType;
	private ResourceType resourceType;

	public RoleResourceListRequest()
	{
		this.orderBy = "a.resource.list";
	}

	public String getRoleId()
	{
		return roleId;
	}

	public String getColumnResourceId()
	{
		return columnResourceId;
	}

	public MenuType getMenuType()
	{
		return menuType;
	}

	public ResourceType getResourceType()
	{
		return resourceType;
	}

	public RoleResourceListRequest setRoleId(String roleId)
	{
		this.roleId = roleId;
		return this;
	}

	public RoleResourceListRequest setColumnResourceId(String columnResourceId)
	{
		this.columnResourceId = columnResourceId;
		return this;
	}

	public RoleResourceListRequest setMenuType(MenuType menuType)
	{
		this.menuType = menuType;
		return this;
	}

	public RoleResourceListRequest setResourceType(ResourceType resourceType)
	{
		this.resourceType = resourceType;
		return this;
	}

	@Override
	public HqlBuilder toSelectHql()
	{
		AssertUtil.hasText(roleId);
		HqlBuilder hql = new HqlBuilder("select a.resource from " + RoleResource.class.getName())
				.append(" a where a.role.id=:roleId", roleId);
		hql.append(" and a.resource.type=:type", resourceType);
		hql.append(" and a.resource.menuType=:menuType", menuType);
		hql.append(" and a.resource.menuId=:menuId", columnResourceId);
		return hql;
	}

}
