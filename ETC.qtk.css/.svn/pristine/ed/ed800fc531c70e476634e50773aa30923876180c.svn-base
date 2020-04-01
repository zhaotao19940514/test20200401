package cn.com.taiji.css.manager.acl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.AssertUtil;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.entity.AppResource.MenuType;
import cn.com.taiji.css.repo.jpa.AppResourceRepo;
import cn.com.taiji.css.repo.jpa.RoleResourceRepo;
import cn.com.taiji.css.repo.request.acl.AppResourceColRequest;
import cn.com.taiji.css.repo.request.acl.AppResourcePageRequest;
import cn.com.taiji.css.repo.request.acl.RoleResourceCountRequest;
import spring.cn.com.taiji.common.annotation.PostInitialize;

@Service("resourceManager")
public class ResourceManagerImpl extends AbstractManager implements ResourceManager
{
	private List<AppResource> resources = new ArrayList<AppResource>();
	@Autowired
	private AppResourceRepo repo;
	@Autowired
	private RoleResourceRepo rrRepo;

	@Override
	public AppResource getResource(String uri)
	{
		RequestMethod[] methods = { RequestMethod.GET, RequestMethod.POST };
		for (RequestMethod method : methods)
		{
			AppResource rs = getResourceByUrl(uri, method);
			if (rs != null) return rs;
		}
		return null;
	}

	@Override
	public AppResource getResourceByUrl(String url, RequestMethod method)
	{
		String request = AppResource.getRequest(url, method);
		for (AppResource resource : resources)
		{
			if (resource.getResPattern().matcher(request).matches())
			{
				resource.setRealUrl(url);
				// logger.debug("request:{}对应的resource:{}", request, resource);
				return resource;
			}
		}
		return null;
	}

	@Override
	@Transactional
	public String add(AppResource resource) throws ManagerException
	{
		AssertUtil.notNull(resource);
		if (getResourceByUrl(resource.getUrl(), resource.getRequestMethod()) != null) throw new ManagerException(
				toLogString("该URI的资源已经存在:{}_{}", resource.getUrl(), resource.getRequestMethod()));
		String menuId = resource.getMenuId();
		if (StringTools.hasText(menuId) && !repo.findById(menuId).isPresent()) throw new ManagerException("指定的父资源不存在.");
		repo.save(resource);
		String id = resource.getId();
		reInit();
		return id;
	}

	@Override
	@Transactional
	public void update(AppResource resource) throws ManagerException
	{
		AssertUtil.notNull(resource);
		Optional<AppResource> opo = repo.findById(resource.getId());
		if (!opo.isPresent()) throw new ManagerException("指定的资源不存在:" + resource.getUrl());
		AppResource po = opo.get();
		po.setName(resource.getName());
		po.setList(resource.getList());
		po.setLogoPic(resource.getLogoPic());
		repo.save(po);
	}

	@Override
	@Transactional(rollbackFor = { ManagerException.class })
	public void delete(String id) throws ManagerException
	{
		AppResource resource = repo.findById(id).orElse(null);
		if (resource == null) return;
		// List<AppResource> list = repo.listResource(id);
		long countTab = repo.count(id, MenuType.BOX_TAB);
		if (countTab > 0) throw new ManagerException("该资源下存在标签菜单，请先删除标签菜单资源");
		if (rrRepo.count(new RoleResourceCountRequest(id)) > 0) throw new ManagerException("该资源已经被角色引用，请先修改角色的权限配置.");
		int count = repo.deleteByMenu(id, MenuType.NOT_MENU);
		logger.debug("删除 {} 条该资源下的非菜单资源.", count);
		repo.delete(resource);
		File file = new File(FileHelper.getWebappPath() + "/images/menu/" + id + ".png");
		if (file.exists() && !file.delete()) logger.error("delete file error:{}", file.getAbsolutePath());
		reInit();
	}

	@PostInitialize
	public void init()
	{
		resources = repo.findAll();
		logger.info("资源URL成功加载至内存:{}", resources.size());
	}

	private void reInit()
	{
		resources.clear();
		init();
	}

	@Override
	public Pagination queryPage(AppResourcePageRequest rpr)
	{
		return repo.page(rpr);
	}

	@Override
	public List<AppResource> listByParent(AppResourcePageRequest req)
	{
		if (StringTools.hasText(req.getId()))
		{
			List<AppResource> tabResourceList = repo.listResource(req.getId());
			// for (AppResource box : tabResourceList) {
			// if(dao.querySize(box.getId(), MenuType.BOX_TAB)>0){
			// box.setHasChild(true);
			// }
			// }
			return tabResourceList;
		}

		List<AppResource> colMenus = repo.list(new AppResourceColRequest(req.getResourceType(), null));
		for (AppResource col : colMenus)
		{
			if (repo.count(col.getId(), MenuType.BOX_TAB) > 0) col.setHasChild(true);
		}
		return colMenus;
	}

	@Override
	public AppResource findById(String id)
	{
		return repo.findById(id).orElse(null);
	}

}
