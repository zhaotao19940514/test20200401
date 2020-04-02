package cn.com.taiji.css.model.acl;

import cn.com.taiji.common.model.BaseModel;

/**
 * 
 * 
 * @author Peream <br>
 *         Create Time：2011-5-18 下午04:52:20<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class ConfRoleModel extends BaseModel
{
	private String roleId;
	private String[] resourceIds;
	
	public ConfRoleModel() {
		super();
	}
	public ConfRoleModel(String roleId) {
		super();
		this.roleId = roleId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String[] getResourceIds() {
		return resourceIds;
	}
	public void setResourceIds(String[] resourceIds) {
		this.resourceIds = resourceIds;
	}
	

}
