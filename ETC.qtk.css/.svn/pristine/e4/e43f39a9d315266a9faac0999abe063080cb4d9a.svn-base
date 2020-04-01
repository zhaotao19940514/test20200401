package cn.com.taiji.css.web.timing;

import java.time.LocalDateTime;

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
import cn.com.taiji.common.model.NoteModel;
import cn.com.taiji.css.entity.SimpleTimeTask;
import cn.com.taiji.css.entity.SimpleTimeTask.SimpleTaskType;
import cn.com.taiji.css.entity.SimpleTimeTask.TaskStatus;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.timing.SimpleTimeTaskManager;
import cn.com.taiji.css.repo.request.timing.SimpleTimeTaskPageRequest;
import cn.com.taiji.css.web.BaseLogController;

@Controller
@RequestMapping("/timing/task")
public class SimpleTimeTaskController extends BaseLogController
{
	@Autowired
	private SimpleTimeTaskManager manager;
	private final String prefix = "timing/task/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") SimpleTimeTaskPageRequest req, Model model,
			HttpServletRequest request)
	{
		model.addAttribute("status", TaskStatus.values());
		model.addAttribute("taskType",SimpleTaskType.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") SimpleTimeTaskPageRequest req, Model model)
	{
		model.addAttribute("pagn", this.manager.queryPage(req));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupAdd(@ModelAttribute("pageModel") SimpleTimeTask simpleTimeTask, Model model)
	{
		model.addAttribute("status", TaskStatus.values());		
		model.addAttribute("taskTypes", SimpleTaskType.values());	
		return prefix + "add";
	}

	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAdd(@Valid @ModelAttribute("pageModel") SimpleTimeTask simpleTimeTask, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException
	{
		simpleTimeTask.setUser(LoginHelper.getLoginUser(request));
		simpleTimeTask.setCreatTime(LocalDateTime.now());
		SimpleTimeTask po = manager.add(simpleTimeTask);
		addSuccess(response, "添加成功");
		model.addAttribute("vo", po);
		return prefix + "result";
	}

	
	@RequestMapping(value = "/editGrid", method = RequestMethod.POST)
	public void processEditGrid(@RequestParam("pk")String id,@RequestParam("name")String name,@RequestParam("value")LocalDateTime value,
			HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		SimpleTimeTask model=new SimpleTimeTask();
		model.setId(id);
		model.setStartTime(value);
		manager.updateTime(model);
		responseJson(new NoteModel(true, "修改成功").toJson(), response);
	}
	

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") String id, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException
	{
		manager.delete(id);
		addSuccess(response, "删除成功");
		return prefix + "result";
	}

	@RequestMapping(value = "/view/{id}")
	public String view(@PathVariable("id") String id, Model model)
	{
		model.addAttribute("pageModel", manager.findById(id));
		return prefix + "view";
	}
	


}
