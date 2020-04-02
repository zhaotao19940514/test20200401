/*
 * Date: 2012-3-7
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.web.system;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.css.manager.system.ScheduleLogManager;
import cn.com.taiji.css.repo.request.system.ScheduleLogPageRequest;
import cn.com.taiji.css.web.MyBaseController;

/**
 * 
 * @author Peream <br>
 *         Create Time：2012-3-7 下午4:25:57<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Controller
@RequestMapping("/system/schedulelog")
public class ScheduleLogController extends MyBaseController
{
	@Autowired
	private ScheduleLogManager logManager;
	private final String prefix = "system/schedulelog/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manage(@ModelAttribute("queryModel") ScheduleLogPageRequest queryModel, Model model)
	{
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String manageList(@Valid @ModelAttribute("queryModel") ScheduleLogPageRequest queryModel, Model model)
	{
		model.addAttribute("pagn", this.logManager.queryPage(queryModel));
		return prefix + "queryResult";
	}
}
