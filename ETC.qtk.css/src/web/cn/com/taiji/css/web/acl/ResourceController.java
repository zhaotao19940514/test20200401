package cn.com.taiji.css.web.acl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.ProjectEnv;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.pub.file.ImageTools;
import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.entity.AppResource.MenuType;
import cn.com.taiji.css.entity.dict.ResourceType;
import cn.com.taiji.css.manager.acl.ResourceManager;
import cn.com.taiji.css.repo.request.acl.AppResourcePageRequest;
import cn.com.taiji.css.web.MyBaseController;

@Controller
@RequestMapping("/acl/resource")
public class ResourceController extends MyBaseController
{
	@Autowired
	private ResourceManager resourceManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") AppResourcePageRequest queryModel, Model model)
	{
		model.addAttribute("resourceType", ResourceType.values());
		return "acl/resource/manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") AppResourcePageRequest queryModel, Model model)
	{
		Pagination pagn = resourceManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
		return "acl/resource/queryResult";
	}

	@RequestMapping(value = "/getByParent")
	public String getByParent(@ModelAttribute("queryModel") AppResourcePageRequest queryModel, Model model)
	{
		List<AppResource> list = resourceManager.listByParent(queryModel);
		model.addAttribute("list", list);
		return "acl/resource/treeItem";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupAdd(@ModelAttribute("pageModel") AppResource appResource, Model model)
	{
		return "acl/resource/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAdd(@Valid @ModelAttribute("pageModel") AppResource resource, HttpServletRequest request,
			Model model, HttpServletResponse response) throws IOException, ManagerException
	{
		handleMultipartFile(resource.getLogoFile(), resource);
		resourceManager.add(resource);
		model.addAttribute("model", resource);
		addMenuId(resource, model);
		addSuccess(response, "添加成功!");
		return "acl/resource/result";
	}

	private void addMenuId(AppResource appResource, Model model)
	{
		if (StringTools.hasText(appResource.getMenuId()))
		{
			AppResource parent = resourceManager.findById(appResource.getMenuId());
			model.addAttribute("menuId", parent.getMenuType().name() + "_" + appResource.getMenuId());
		}
		else
		{
			model.addAttribute("menuId", "type_" + appResource.getType().name());
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id, Model model)
	{
		AppResource appResource = resourceManager.findById(id);
		model.addAttribute("pageModel", appResource);
		return "acl/resource/edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(@Valid @ModelAttribute("pageModel") AppResource resource, Model model,
			HttpServletResponse response) throws IOException, ManagerException
	{
		handleMultipartFile(resource.getLogoFile(), resource);
		resourceManager.update(resource);
		model.addAttribute("model", resource);
		addMenuId(resource, model);
		addSuccess(response, "修改成功!");
		return "acl/resource/result";
	}

	private void handleMultipartFile(MultipartFile mfile, AppResource resource) throws ManagerException
	{
		if (resource.getMenuType() == MenuType.NOT_MENU || mfile == null || mfile.isEmpty()) return;
		logger.debug("mfile:{},is empty:{}", mfile, mfile.isEmpty());
		try
		{
			String path = FileHelper.getWebappPath() + "/images/menu/";
			ProjectEnv.mkdirs(path);
			File file = new File(path + resource.getId() + ".tmp");
			mfile.transferTo(file);
			BufferedImage image = ImageTools.reduce(file, 20, 20);
			File out = new File(path + resource.getId() + ".png");
			ImageIO.write(image, "PNG", out);
			if (!file.delete()) logger.error("delete file error:{}", file.getAbsolutePath());
			resource.setLogoPic(resource.getId() + ".png");
		}
		catch (Exception e)
		{
			logger.error("", e);
			throw new ManagerException("保存图片失败，请检查图片格式:" + e.getMessage());
		}
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") String id, Model model, HttpServletResponse response)
			throws ManagerException
	{
		AppResource appResource = resourceManager.findById(id);
		model.addAttribute("model", appResource);
		addMenuId(appResource, model);
		resourceManager.delete(id);
		addSuccess(response, "删除成功!");
		return "acl/resource/result";
	}

	@RequestMapping(value = "/check")
	public void check(@RequestParam(value = "parentId") String parentId,
			@RequestParam(value = "id", required = false) String id, @RequestParam("name") String name,
			HttpServletResponse response, HttpServletRequest request) throws IOException
	{
		boolean exist = false;
		response.getWriter().print(!exist);
	}

}
