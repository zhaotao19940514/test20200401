/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.repo.request.acl;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaSortDataRequest;
import cn.com.taiji.css.entity.Role;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年11月7日 下午1:46:03<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class RoleListRequest extends JpaSortDataRequest<Role>
{
	private String name;
	private String unitLikeCode;

	public RoleListRequest(String name, String unitLikeCode) {
		super();
		this.name = name;
		this.unitLikeCode = unitLikeCode;
	}

	public RoleListRequest()
	{

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getUnitLikeCode() {
		return unitLikeCode;
	}


	public void setUnitLikeCode(String unitLikeCode) {
		this.unitLikeCode = unitLikeCode;
	}


	@Override
	public HqlBuilder toSelectHql()
	{
		HqlBuilder hql = new HqlBuilder("from " + Role.class.getName() + " a where 1=1 ");
		hql.append(" and name like :name", like(name));
		hql.append(" and exists(select b from Unit b where b.id=a.unit.id and b.code like :code)",rightLike(unitLikeCode));
		return hql;
	}

}
