package cn.com.taiji.css.web.administration.notify;

import java.io.IOException;

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

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.ServiceHandleException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.administration.notify.NotifyManager;
import cn.com.taiji.css.model.administration.notify.NotifyRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.Notify;

@Controller
@RequestMapping("/administration/notify")
public class NotifyController extends MyLogController {
	private final String prefix = "administration/notify/";

	@Autowired
	private NotifyManager notifyManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") NotifyRequest queryModel, Model model) {
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") NotifyRequest queryModel, Model model) {
		Pagination pagn = notifyManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupAdd(@ModelAttribute("pageModel") Notify notifyModel, HttpServletRequest request, Model model) {
		notifyModel.setName(LoginHelper.getLoginUser(request).getLoginName());
		return prefix + "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAdd(@Valid @ModelAttribute("pageModel") Notify notifyModel, HttpServletRequest request,
			Model model, HttpServletResponse response) throws Exception {
		Notify po = notifyManager.add(notifyModel);
		addSuccess(response, "添加通知信息成功");
		model.addAttribute("vo", po);
//		super.addSysLog(request, "添加通知信息({})成功", po.getId());
		super.doAddLog(request, CssServiceLogType.ADMINISTRATION_NOTIFY_NOTIFY, null);
		return prefix + "result";
	}

	/**
	 * 修改
	 */

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id, HttpServletRequest request, Model model) {
		model.addAttribute("pageModel", notifyManager.findById1(id));
//		notifyModel.setName(LoginHelper.getLoginUser(request).getLoginName());
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(@Valid @ModelAttribute("pageModel") Notify notifyModel, HttpServletRequest request,
			Model model, HttpServletResponse response) throws ManagerException {
		Notify po = notifyManager.updateNotify(notifyModel,LoginHelper.getLoginUser(request));
		addSuccess(response, "修改通知公告信息成功");
		model.addAttribute("vo", po);
		super.doUpdateLog(request, CssServiceLogType.ADMINISTRATION_NOTIFY_NOTIFY, null);
		return prefix + "result";

	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param model
	 * @param response
	 * @return
	 * @throws ManagerException 
	 * @throws JsonManagerException
	 * @throws ServiceHandleException
	 */
	@RequestMapping(value = "/delete/{id}")
	public void processEdit(@PathVariable("id") String id, Model model, HttpServletResponse response,
			HttpServletRequest request) throws ManagerException {
		Notify Notify = notifyManager.findById1(id);
		model.addAttribute("model", Notify);
		notifyManager.delete(id);
		addSuccess(response, "删除通知公告信息成功");
		super.doDeleteLog(request, CssServiceLogType.ADMINISTRATION_NOTIFY_NOTIFY, null);
	}

	/**
	 * 页面折叠信息
	 * @throws IOException 
	 * @throws ManagerException 
	 */
	@RequestMapping(value = "/view/{id}")
	public String info(@PathVariable("id") String id, Model model,HttpServletResponse response) throws IOException, ManagerException {
		model.addAttribute("pageModel", notifyManager.findById1(id));
		return prefix + "view";
	}
	/**
	 * 置顶
	 * @param id
	 * @param model
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/zd/{id}")
	public void processTop(@PathVariable("id") String id, Model model, HttpServletResponse response,
			HttpServletRequest request) {
		Notify update = notifyManager.update(id);
		model.addAttribute("model", update);
		addSuccess(response, "置顶成功");
//		super.addSysLog(request, "置顶({})成功", update.getId());
	}
	/**
	 * 取消置顶
	 * @param id
	 * @param model
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/qxzd/{id}")
	public void processQxTop(@PathVariable("id") String id, Model model, HttpServletResponse response,
			HttpServletRequest request) {
		Notify update = notifyManager.qxZd(id);
		model.addAttribute("model", update);
		addSuccess(response, "取消置顶成功");
//		super.addSysLog(request, "取消置顶({})成功", update.getId());
	}
}
