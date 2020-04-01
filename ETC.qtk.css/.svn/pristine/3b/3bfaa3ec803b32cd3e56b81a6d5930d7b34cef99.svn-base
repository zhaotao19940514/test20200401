package cn.com.taiji.css.web.acl;

import java.util.List;
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

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.css.entity.Unit;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.acl.UnitManager;
import cn.com.taiji.css.model.acl.UnitModel;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.repo.request.acl.UnitPageRequest;
import cn.com.taiji.css.web.BaseLogController;

@Controller
@RequestMapping("/acl/unit")
public class UnitController extends BaseLogController
{
	@Autowired
	private UnitManager manager;
	private final String prefix = "acl/unit/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") UnitPageRequest req, Model model,
			HttpServletRequest request)
	{
				
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(HttpServletRequest request,@Valid @ModelAttribute("queryModel") UnitPageRequest req, Model model)
	{
		User user=LoginHelper.getLoginUser(request);
		model.addAttribute("pagn", this.manager.queryPage(req, user));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/getByParent")
	public void getByParent(@RequestParam("parentId")String parentId, Model model,HttpServletResponse response) throws Exception
	{
		List<UnitModel> list=manager.listByParentId(parentId);
		List<ZTreeItem> treeList=list.stream().map(u->{
			return new ZTreeItem().setId(u.getId()).setName(u.getName()).setIsParent(u.isHasChild());
		}).collect(Collectors.toList());
		response.getWriter().print(JsonTools.toJsonStr(treeList));
	}
	
	@RequestMapping(value = "/add/{parentId}", method = RequestMethod.GET)
	public String setupAdd(@ModelAttribute("pageModel") Unit sampleUnit, Model model)
	{
				
		return prefix + "add";
	}

	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAdd(@Valid @ModelAttribute("pageModel") Unit sampleUnit, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException
	{
		Unit po = manager.add(sampleUnit);
		addSuccess(response, "添加成功");
		model.addAttribute("vo", po);
		return prefix + "result";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id, Model model)
	{
				
		model.addAttribute("pageModel", manager.findById(id));
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(@Valid @ModelAttribute("pageModel") Unit sampleUnit, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException
	{
		Unit po = manager.update(sampleUnit);
		addSuccess(response, "修改成功");
		model.addAttribute("vo", po);
		return prefix + "result";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") String id, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException
	{
		manager.delete(id);
		addSuccess(response, "删除成功");
		return prefix + "result";
	}

}
