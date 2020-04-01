/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.repo.request.acl;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaCountDataRequest;
import cn.com.taiji.css.entity.Role;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年11月7日 上午10:39:54<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class RoleCountRequest extends JpaCountDataRequest {
	private String id;
	private String name;

	public RoleCountRequest() {

	}

	public RoleCountRequest(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public HqlBuilder toSelectHql() {
		HqlBuilder hql = new HqlBuilder("select count(id) from "+ Role.class.getName() + " where 1=1");
		hql.append(" and id!=:id", id);
		hql.append(" and name=:name", name);
		return hql;
	}

}
