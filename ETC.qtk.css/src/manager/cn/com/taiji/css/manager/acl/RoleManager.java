package cn.com.taiji.css.manager.acl;

import java.util.List;
import java.util.Map;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.Role;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.ResourceType;
import cn.com.taiji.css.model.acl.ColumnMenu;
import cn.com.taiji.css.model.acl.ConfRoleModel;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.repo.request.acl.RoleListRequest;
import cn.com.taiji.css.repo.request.acl.RolePageRequest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2009-6-11 下午04:01:13<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public interface RoleManager
{
	/**
	 * 添加一个角色
	 * 
	 * @param role
	 * @param loginUser TODO
	 * @return
	 */
	public String add(Role role, User loginUser) throws ManagerException;

	/**
	 * 更新角色信息
	 * 
	 * @param role
	 * @param loginUser TODO
	 */
	public void update(Role role, User loginUser) throws ManagerException;

	public Role getDefaultRole();

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Role findById(String id);

	public List<Role> getAll(User loginUser);

	List<Role> listAll(User loginUser, RoleListRequest req);
	
	/**
	 * 删除角色，需要符合一定约束，否则异常
	 * 
	 * @param roleId
	 * @throws ManagerException
	 */
	public String delete(String roleId) throws ManagerException;

	/**
	 * 取得当前角色的权限配置(只返回是菜单的资源)
	 * 
	 * @param roleId
	 * @return
	 */
	public Map<ResourceType, List<ColumnMenu>> getCurrentConf(String roleId);

	/**
	 * 配置角色权限
	 * 
	 * @param conf
	 * @throws ManagerException
	 * @see {@link RoleResourceHolder#removeRoleMenus(String)}
	 */
	public void confRole(ConfRoleModel conf) throws ManagerException;

	public Pagination queryPage(RolePageRequest req, User loginUser);

	public boolean isNameExist(String id, String name);

	/**
	 * 
	 * 根据名称查询角色，此为模糊查询
	 * 
	 * @param name
	 * @param loginUser TODO
	 * @return
	 */
	public List<Role> listByName(String name, User loginUser);

	List<ZTreeItem> getCurrentConf(String ownerRoleId, String confRoleId);

	

}
