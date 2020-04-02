/*
 * Date: 2013-5-6
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.web.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.quartz.CronTaskQueryModel;
import cn.com.taiji.common.model.quartz.CronTaskView;
import cn.com.taiji.css.config.manager.TaskGroup;
import cn.com.taiji.css.manager.quartz.CronTaskManager;
import cn.com.taiji.css.web.BaseLogController;

/**
 * 
 * @author Peream <br>
 *         Create Time：2013-5-6 下午5:06:44<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Controller
@RequestMapping("/system/cron")
public class CronTaskController extends BaseLogController
{
	@Autowired
	private CronTaskManager manager;
	private final String prefix = "system/cron/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CronTaskQueryModel queryModel, Model model)
	{
		model.addAttribute("types", TaskGroup.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CronTaskQueryModel queryModel, Model model)
	{
		model.addAttribute("pagn", this.manager.queryPage(queryModel));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/edit/{taskName}", method = RequestMethod.GET)
	public String edit(@PathVariable("taskName") String taskName, Model model)
	{
		model.addAttribute("pageModel", manager.findOne(taskName));
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String porcessEdit(@ModelAttribute("pageModel") CronTaskView view, HttpServletRequest request,
			HttpServletResponse response, Model model) throws ManagerException
	{
		manager.updateTaskCron(view);
		addSuccess(response, "修改({})任务的cron为:{}", view.getTaskName(), view.getCron());
		super.addSysLog(request, "修改({})任务的cron为:{}", view.getTaskName(), view.getCron());
		model.addAttribute("vo", manager.findOne(view.getTaskName()));
		return prefix + "result";
	}

	@RequestMapping(value = "/view/{taskName}", method = RequestMethod.GET)
	public String view(@PathVariable("taskName") String taskName, Model model)
	{
		model.addAttribute("pageModel", manager.findOne(taskName));
		return prefix + "view";
	}

	@RequestMapping(value = "/runner/{taskName}/{type}", method = RequestMethod.POST)
	public String runnerManage(@PathVariable("taskName") String taskName, @PathVariable("type") String type,
			HttpServletRequest request, Model model, HttpServletResponse response) throws ManagerException
	{
		if ("stop".equals(type))
		{
			manager.stop(taskName);
			addSuccess(response, "停止调度器{}成功", taskName);
			addSysLog(request, "停止调度器{}", taskName);
		}
		else
		{
			manager.start(taskName);
			addSuccess(response, "启动调度器{}成功", taskName);
			addSysLog(request, "启动调度器{}", taskName);
		}
		model.addAttribute("vo", manager.findOne(taskName));
		return prefix + "result";
	}

	@RequestMapping(value = "/runnow/{taskName}", method = RequestMethod.POST)
	public String runTaskNow(@PathVariable("taskName") String taskName, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		manager.runTaskNow(taskName);
		addSuccess(response, "执行任务{}成功", taskName);
		addSysLog(request, "执行任务{}", taskName);
		model.addAttribute("vo", manager.findOne(taskName));
		return prefix + "result";
	}

	@RequestMapping(value = "/startall", method = RequestMethod.POST)
	public void startAll(@RequestParam("taskNames") String[] taskNames, HttpServletResponse response)
			throws ManagerException
	{
		manager.startAll(taskNames);
		addSuccess(response, "批量启动调度器成功，请刷新后查看状态");
	}

	@RequestMapping(value = "/stopall", method = RequestMethod.POST)
	public void stopAll(@RequestParam("taskNames") String[] taskNames, HttpServletResponse response)
			throws ManagerException
	{
		manager.stopAll(taskNames);
		addSuccess(response, "批量停止调度器成功，请刷新后查看状态");
	}
}
