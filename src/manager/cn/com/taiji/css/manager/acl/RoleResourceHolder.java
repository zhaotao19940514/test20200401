package cn.com.taiji.css.manager.acl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.pub.CollectionTools;
import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.repo.jpa.RoleResourceRepo;
import cn.com.taiji.css.repo.request.acl.RoleResourceListRequest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-5-19 下午01:35:57<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class RoleResourceHolder extends AbstractManager
{
	private Map<String, Map<String, AppResource>> roleMenus = Maps.newConcurrentMap();
	@Autowired
	private RoleResourceRepo rrRepo;

	/**
	 * 验证指定角色是否拥有指定的(URI和method确定)资源
	 * 
	 * @param roleId
	 *            指定的角色
	 * @param uri
	 *            请求的URI
	 * @param method
	 *            请求URI的方式
	 * @return 是否拥有权限
	 */
	// @Deprecated
	// public boolean hasResource(String roleId, String uri, RequestMethod method)
	// {
	// List<AppResource> myMenus = getMyMenu(roleId);
	// String request = AppResource.getRequest(uri, method);
	// // logger.debug("request={}", request);
	// for (AppResource resource : myMenus)
	// {
	// if (resource.getResPattern().matcher(request).matches()) return true;
	// }
	// logger.debug("no resouce match");
	// return false;
	// }

	public Map<String, AppResource> getMyMenu(String roleId)
	{
		Map<String, AppResource> myMenus = roleMenus.get(roleId);
		if (CollectionTools.isEmpty(myMenus))
		{
			List<AppResource> list = rrRepo.list(new RoleResourceListRequest().setRoleId(roleId));
			myMenus = Maps.newConcurrentMap();
			for (AppResource res : list)
			{
				myMenus.put(res.getId(), res);
			}
			roleMenus.put(roleId, myMenus);
		}
		return myMenus;
	}

	public boolean hasResource(String roleId, String resourceId)
	{
		Map<String, AppResource> myMenu = getMyMenu(roleId);
		return myMenu.get(resourceId) != null;
	}

	/**
	 * 配置完角色权限后调用本方法清空角色拥有权限的缓存
	 * 
	 * @param roleId
	 *            角色ID
	 * @see {@link RoleManager#confRole(ccc.sample.model.acl.ConfRoleModel)}
	 */
	public void removeRoleMenus(String roleId)
	{
		roleMenus.remove(roleId);
	}
}
