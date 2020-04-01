/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.repo.request.acl;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaSortDataRequest;
import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.entity.AppResource.MenuType;
import cn.com.taiji.css.entity.RoleResource;
import cn.com.taiji.css.entity.dict.ResourceType;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年11月7日 下午12:49:46<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class AppResourceColRequest extends JpaSortDataRequest<AppResource>
{
	private ResourceType resourceType;
	private String ownerRoleId;

	public AppResourceColRequest()
	{
		this(null,null);
	}

	public AppResourceColRequest(ResourceType resourceType, String ownerRoleId)
	{
		super();
		this.resourceType = resourceType;
		this.ownerRoleId = ownerRoleId;
		this.orderBy="list";
	}

	public String getOwnerRoleId()
	{
		return ownerRoleId;
	}

	public ResourceType getResourceType()
	{
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType)
	{
		this.resourceType = resourceType;
	}

	public void setOwnerRoleId(String ownerRoleId)
	{
		this.ownerRoleId = ownerRoleId;
	}

	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + AppResource.class.getName());
		hql.append(" r where r.menuType=:menuType", MenuType.COLUMN);
		hql.append(" and type=:type", resourceType);
		hql.append(" and exists(select b from " + RoleResource.class.getName()
				+ " b where b.resource=r and b.role.id=:ownerRoleId )", ownerRoleId);
		return hql;
	}

}
