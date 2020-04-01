/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.repo.request.acl;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaCountDataRequest;
import cn.com.taiji.css.entity.RoleResource;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年11月7日 上午10:39:54<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class RoleResourceCountRequest extends JpaCountDataRequest
{
	private String resourceId;

	public RoleResourceCountRequest()
	{

	}

	public RoleResourceCountRequest(String resourceId)
	{
		this.resourceId = resourceId;
	}

	public String getResourceId()
	{
		return resourceId;
	}

	public void setResourceId(String resourceId)
	{
		this.resourceId = resourceId;
	}

	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("select count(id) from " + RoleResource.class.getName() + " where 1=1");
		hql.append(" and resource.id=:resourceId", resourceId);
		return hql;
	}

}
