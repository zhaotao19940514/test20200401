package cn.com.taiji.css.web.acl;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.LabelIdPair;
import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.css.entity.Role;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.acl.RoleManager;
import cn.com.taiji.css.model.acl.ConfRoleModel;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.repo.request.acl.RoleListRequest;
import cn.com.taiji.css.web.BaseLogController;

@Controller
@RequestMapping("/acl/role")
public class RoleController extends BaseLogController
{
	@Autowired
	private RoleManager roleManager;
	private final String prefix = "acl/role/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(Model model)
	{
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(HttpServletRequest request,Model model,RoleListRequest req)
	{
		model.addAttribute("roles", this.roleManager.listAll(LoginHelper.getLoginUser(request), req));
		model.addAttribute("queryModel", req);
		model.addAttribute("randomBoolean", new Random().nextBoolean());
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupAdd(@ModelAttribute("pageModel") Role role)
	{
		return prefix + "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAdd(@Valid @ModelAttribute("pageModel") Role role, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		roleManager.add(role, LoginHelper.getLoginUser(request));
		addSuccess(response, "添加角色成功");
		model.addAttribute("vo", role);
		super.addSysLog(request, "添加角色({})成功", role.getName());
		return prefix + "result";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id, HttpServletRequest request, Model model)
	{
		model.addAttribute("pageModel", roleManager.findById(id));
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String porcessEdit(@Valid @ModelAttribute("pageModel") Role role, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		roleManager.update(role, LoginHelper.getLoginUser(request));
		addSuccess(response, "修改角色成功");
		model.addAttribute("vo", role);
		super.addSysLog(request, "修改角色({})成功", role.getName());
		return prefix + "result";
	}

	@RequestMapping(value = "/conf/{id}", method = RequestMethod.GET)
	public String setupConf(@PathVariable("id") String id, Model model, HttpServletRequest request) throws IOException
	{
		String ownerRoleId=LoginHelper.getLoginUser(request).getRole().getId();
		List<ZTreeItem> list = roleManager.getCurrentConf(ownerRoleId,id);
		String json = JsonTools.toJsonStr(list);
		model.addAttribute("json", json);
		model.addAttribute("role", roleManager.findById(id));
		return prefix + "conf";
	}

	@RequestMapping(value = "/conf", method = RequestMethod.POST)
	public String processConf(@ModelAttribute("pageModel") ConfRoleModel confRoleModel, HttpServletRequest request,
			Model model, HttpServletResponse response) throws ManagerException
	{
		this.roleManager.confRole(confRoleModel);
		addSuccess(response, "配置角色成功");
		Role role = this.roleManager.findById(confRoleModel.getRoleId());
		model.addAttribute("vo", role);
		super.addSysLog(request, "配置角色权限({})成功", role.getName());
		return prefix + "result";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") String id, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		String roleName = roleManager.delete(id);
		addSuccess(response, "删除角色成功");
		super.addSysLog(request, "删除角色({})成功", roleName);
		return prefix + "result";
	}

	@RequestMapping("/isNameValid")
	public void isNameValid(@RequestParam("name") String name, @RequestParam(value = "id", required = false) String id,
			HttpServletResponse response) throws IOException
	{
		response.getWriter().print(!roleManager.isNameExist(id, name));
	}

	@RequestMapping("/listByName")
	public void listByName(@RequestParam("roleId") String name, HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		List<LabelIdPair> pairs = LabelIdPair.fromList(roleManager.listByName(name, LoginHelper.getLoginUser(request)), "name", "id");
		super.responseJson(JsonTools.toJsonStr(pairs), response);
	}
	
	@RequestMapping("/getAll")
	public void listByName(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		List<Role> all = roleManager.getAll(LoginHelper.getLoginUser(request));
//		{value: 1, text: 'Active'},
		String json=all.stream().map(r->toLogString("{\"value\": \"{}\", \"text\": \"{}\"}", r.getId(),r.getName())).collect(Collectors.joining(","));
		super.responseJson("["+json+"]",response);
	}
}
