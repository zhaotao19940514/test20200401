package cn.com.taiji.css.web.administration.notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.manager.administration.notify.NotifyManager;
import cn.com.taiji.css.model.administration.notify.NotifyDeskRequest;
import cn.com.taiji.css.web.MyLogController;

@Controller
@RequestMapping("/administration/desktop")
public class NotificationDesktopController extends MyLogController {
	/**
	 * 我的桌面 -----通知桌面
	 */
	private final String prefix = "administration/desktop/";
	@Autowired
	private NotifyManager notifyManager;

	/**
	 * 查询
	 * 
	 * @param queryModel
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") NotifyDeskRequest queryModel, Model model) {
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") NotifyDeskRequest queryModel, Model model) {
		Pagination pagn = notifyManager.queryNotifyDeskPage(queryModel);
		model.addAttribute("pagn", pagn);
		return prefix + "queryResult";
	}

	/**
	 * 页面折叠信息
	 */
	@RequestMapping(value = "/view/{id}")
	public String info(@PathVariable("id") String id, Model model) {
		model.addAttribute("pageModel", notifyManager.findById1(id));
		return prefix + "view";
	}
}
