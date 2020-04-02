package cn.com.taiji.css.manager.acl;

import java.util.List;

import cn.com.taiji.css.entity.dict.ResourceType;
import cn.com.taiji.css.model.acl.HasButtonModel;
import cn.com.taiji.css.model.acl.RoleMenu;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-5-17 下午02:09:22<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public interface RoleResourceManager
{
	/**
	 * 取得当前登录角色的菜单
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RoleMenu> getRoleMenu(String roleId);

	/**
	 * 取得指定角色的菜单栏目（系统管理、。。。）
	 * 
	 * @param roleId
	 * @return
	 */
	public List<ResourceType> listResourceType(String roleId);

	/**
	 * 判断指定的角色是否具有resourceId指定的资源
	 * 
	 * @param roleId
	 * @param resourceId
	 * @return
	 */
	public boolean hasResource(String roleId, String resourceId);

	public HasButtonModel hasButton(String roleId, String uri);
}
