package cn.com.taiji.css.manager.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.entity.Role;

/**
 * 
 * @author Peream <br>
 *         Create Time：2010-3-1 下午02:02:06<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service("aclManager")
public class AclManagerImpl extends AbstractManager implements AclManager
{
	@Autowired
	private ResourceManager resourceManager;
	@Autowired
	private RoleResourceManager roleResourceManager;

	@Override
	public AppResource getResource(String url, RequestMethod method)
	{
		return resourceManager.getResourceByUrl(url, method);
	}

	@Override
	@Transactional
	public String checkPower(Role role, AppResource resource)
	{
		if (role == null || !hasText(role.getId())) return "角色不存在.";
		// 未配置的请求任何人都可以访问
		if (resource == null) return null;
		boolean rs = roleResourceManager.hasResource(role.getId(), resource.getId());
		return rs ? null : toLogString("角色({})无权访问此URL({}_{})", role.getName(), resource.getUrl(),
				resource.getRequestMethod());
	}

}
