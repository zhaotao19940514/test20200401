/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.repo.request.acl;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaPageableDataRequest;
import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.entity.AppResource.MenuType;
import cn.com.taiji.css.entity.dict.ResourceType;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年11月7日 下午12:36:28<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class AppResourcePageRequest extends JpaPageableDataRequest<AppResource>
{
	private String id; // 当前选中节点的id
	private ResourceType resourceType;
	private MenuType menuType;
	private String name;

	public String getId()
	{
		return id;
	}

	public ResourceType getResourceType()
	{
		return resourceType;
	}

	public MenuType getMenuType()
	{
		return menuType;
	}

	public String getName()
	{
		return name;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public void setResourceType(ResourceType resourceType)
	{
		this.resourceType = resourceType;
	}

	public void setMenuType(MenuType menuType)
	{
		this.menuType = menuType;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public AppResourcePageRequest()
	{
		this.orderBy = "list";// 默认需要list排序
	}

	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + AppResource.class.getName() + " where 1=1 ");
		hql.append(" and menuId = :menuId", id);
		hql.append(" and type = :type", resourceType);
		hql.append(" and menuType = :menuType", menuType);
		hql.append(" and name like :name", like(name));
		return hql;
	}

}
