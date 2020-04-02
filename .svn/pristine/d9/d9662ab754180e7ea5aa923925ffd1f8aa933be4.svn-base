/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.repo.request.acl;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaSortDataRequest;
import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.entity.RoleResource;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年11月7日 下午12:49:46<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class AppResourceListRequest extends JpaSortDataRequest<AppResource>
{
	private String menuId;
	private String ownerRoleId;

	public AppResourceListRequest()
	{
		this(null,null);
	}

	public AppResourceListRequest(String menuId, String ownerRoleId)
	{
		super();
		this.menuId = menuId;
		this.ownerRoleId = ownerRoleId;
		this.orderBy="list";
	}

	public String getOwnerRoleId()
	{
		return ownerRoleId;
	}

	public String getMenuId()
	{
		return menuId;
	}

	public void setMenuId(String menuId)
	{
		this.menuId = menuId;
	}

	public void setOwnerRoleId(String ownerRoleId)
	{
		this.ownerRoleId = ownerRoleId;
	}

	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + AppResource.class.getName() + " r where 1=1 ");
		hql.append(" and r.menuId=:menuId", menuId);
		hql.append(" and exists(select b from " + RoleResource.class.getName()
				+ " b where b.resource=r and b.role.id=:ownerRoleId )", ownerRoleId);
		return hql;
	}

}
