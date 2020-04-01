package cn.com.taiji.css.manager.acl;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.repo.request.acl.AppResourcePageRequest;

public interface ResourceManager
{
	public void init();

	/**
	 * 按uri获取资源，如果存在多个，返回其中一个method的
	 * 
	 * @param uri
	 * @return
	 */
	public AppResource getResource(String uri);

	/**
	 * 根据URL查找对应的资源
	 * 
	 * @param url
	 * @return
	 */
	public AppResource getResourceByUrl(String url, RequestMethod method);

	/**
	 * 添加资源，需要进行必要的判断
	 * 
	 * @param resource
	 * @return 添加后的ID
	 * @throws ManagerException
	 */
	public String add(AppResource resource) throws ManagerException;

	/**
	 * 修改资源信息，只允许修改名字，排序等简单信息
	 * 
	 * @param resource
	 * @throws ManagerException
	 */
	public void update(AppResource resource) throws ManagerException;

	/**
	 * 删除指定ID的资源，需要判断资源是否有子资源及是否被角色引用
	 * 
	 * @param id
	 * @throws ManagerException
	 */
	public void delete(String id) throws ManagerException;

	/**
	 * 根据父节点id分页查询资源列表
	 * 
	 * @param req
	 * @return
	 */
	public Pagination queryPage(AppResourcePageRequest req);

	/**
	 * 根据父节点id查询子节点
	 * 
	 * @param req
	 * @return
	 */
	public List<AppResource> listByParent(AppResourcePageRequest req);

	public AppResource findById(String id);
}
