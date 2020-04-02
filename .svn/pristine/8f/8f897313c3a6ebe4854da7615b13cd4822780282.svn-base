package cn.com.taiji.css.web;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.common.web.BaseController;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.model.MySessionNames;
import cn.com.taiji.css.model.acl.ColumnMenu;
import cn.com.taiji.css.model.acl.RoleMenu;

/**
 * 
 * @author Peream <br>
 *         邮箱：peream@gmail.com<br>
 *         创建日期：2009-1-20 上午10:02:39
 * @since 1.0
 * @version 1.0
 */
@Controller
public class IndexController extends BaseController
{
	@RequestMapping("/index")
	public String showIndex()
	{
		return "/administration/desktop/manage";
//		return "index";
	}

	@RequestMapping("/baseTime")
	public void baseTime(HttpServletResponse response) throws Exception
	{
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
		LocalDateTime ldt=LocalDateTime.now();
		String weeks[]={"星期天","星期一","星期二","星期三","星期四","星期五","星期六","星期天"};
		String humanStr=ldt.format(f)+" "+weeks[ldt.get(ChronoField.DAY_OF_WEEK)];
		long epochMilli=ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("humanStr", humanStr);
		m.put("time", epochMilli);
		response.getWriter().print(JsonTools.toJsonStr(m));
	}
	
	@RequestMapping(value = "/common/boxtab/{myMenuId}")
	public String sendBoxtab(HttpServletRequest request, @PathVariable("myMenuId") String columnMenuId, Model model)
	{
		ColumnMenu menu = getColumnMenu(request, columnMenuId);
		model.addAttribute("columnMenu", menu);
		model.addAttribute(MySessionNames.CURRENT_COLUMN_RESOURCE, menu.getColumnResource());//title需要currentResource
		return "boxtab";
	}

	private ColumnMenu getColumnMenu(HttpServletRequest request, String columnMenuId)
	{
		// @SuppressWarnings("unchecked") List<RoleMenu> menus = (List<RoleMenu>)
		// WebUtils.getSessionAttribute(request,
		// MySessionNames.ROLE_MENU);
		List<RoleMenu> menus = LoginHelper.getCurrentRoleMenu(request);
		if (menus == null) return new ColumnMenu();
		for (RoleMenu roleMenu : menus)
		{
			for (ColumnMenu columnMenu : roleMenu.getColumnMenus())
			{
				if (columnMenu.getColumnResource().getId().equals(columnMenuId.trim()) && columnMenu.isHasTabMenu())
					return columnMenu;
			}
		}
		return new ColumnMenu();
	}
}
