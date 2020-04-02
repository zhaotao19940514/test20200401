/*
 * Date: 2011-6-2
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.web.system;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.taiji.css.manager.system.WorkdayManager;
import cn.com.taiji.css.model.system.Workday;
import cn.com.taiji.css.web.MyBaseController;

@Controller
@RequestMapping("/system/workday")
public class WorkdayController extends MyBaseController
{
	@Autowired
	private WorkdayManager manager;
	private final String prefix = "system/workday/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(Model model)
	{
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		model.addAttribute("years", new int[]{year-1,year,year+1,year+2});
		model.addAttribute("months", Month.values());
		model.addAttribute("year", year);
		model.addAttribute("month", now.getMonth());
		model.addAttribute("today", now);
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@RequestParam("year")int year,
			@RequestParam("month")Month month, Model model)
	{
		List<Workday> list = this.manager.listByMonth(year, month);
		Workday[][] days=new Workday[list.size()/7][7];
		for (int i = 0; i < days.length; i++) {
			days[i][0]=list.get(i*7+0);
			days[i][1]=list.get(i*7+1);
			days[i][2]=list.get(i*7+2);
			days[i][3]=list.get(i*7+3);
			days[i][4]=list.get(i*7+4);
			days[i][5]=list.get(i*7+5);
			days[i][6]=list.get(i*7+6);
		}
		model.addAttribute("paramMonth", month);
		model.addAttribute("days", days);
		return prefix + "queryResult";
	}

}
