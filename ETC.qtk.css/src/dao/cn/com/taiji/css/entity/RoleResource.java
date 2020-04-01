package cn.com.taiji.css.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.com.taiji.common.entity.StringUUIDEntity;

/**
 * 
 * @author Peream <br>
 *         Create Time：2009-6-11 下午03:34:06<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Entity
@Table(name = "QTK_CSS_ROLE_RESOURCE")
public class RoleResource extends StringUUIDEntity
{
	private Role role;
	private AppResource resource;

	public RoleResource()
	{

	}

	public RoleResource(Role role, AppResource resource)
	{
		this.role = role;
		this.resource = resource;
	}

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	@ManyToOne
	@JoinColumn(name = "resource_id", nullable = false)
	public AppResource getResource()
	{
		return resource;
	}

	public void setResource(AppResource resource)
	{
		this.resource = resource;
	}

}
