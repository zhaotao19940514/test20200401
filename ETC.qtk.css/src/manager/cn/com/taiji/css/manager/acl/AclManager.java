/*
 * Date: 2011-7-21
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager.acl;

import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.entity.Role;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-7-21 下午5:40:03<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public interface AclManager
{
	public AppResource getResource(String url, RequestMethod requestMethod);

	/**
	 * 校验权限
	 * 
	 * @param role
	 *            当前角色
	 * @param resource
	 *            所请求的资源
	 * @return 失败信息，无信息则表示成功通过验证
	 */
	public String checkPower(Role role, AppResource resource);
}
