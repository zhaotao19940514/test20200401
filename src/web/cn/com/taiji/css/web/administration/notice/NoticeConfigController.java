package cn.com.taiji.css.web.administration.notice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.administration.notice.NoticeConfigManager;
import cn.com.taiji.css.model.administration.notice.NoticeConfigRequest;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.customerservice.card.CancelRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.BankSignVersionDetail;

/**
 * @ClassName SupplyPaymentController.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
@Controller
@RequestMapping("/administration/notice/noticeconfig")
public class NoticeConfigController extends MyLogController {
	private final String prefix = "administration/notice/noticeconfig/";
	@Autowired
	private NoticeConfigManager noticeConfigManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") NoticeConfigRequest queryModel, Model model)
	{
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") NoticeConfigRequest queryModel, Model model, HttpServletRequest request) throws ManagerException
	{
		LargePagination<BankSignVersionDetail> pagn = noticeConfigManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addGet(@ModelAttribute("queryModel") NoticeConfigRequest queryModel, HttpServletRequest request, Model model)
	{
		return prefix+"add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addPost(@RequestBody NoticeConfigRequest queryModel, Model model, HttpServletRequest request,HttpServletResponse response) throws ManagerException
	{
		AppAjaxResponse res =noticeConfigManager.add(queryModel);
		try {
			HttpMimeResponseHelper.responseJson(res.toJson(), response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editGet(@PathVariable("id") String id,@ModelAttribute("queryModel1") NoticeConfigRequest queryModel, HttpServletRequest request, Model model)
	{
		BankSignVersionDetail bankVersion = noticeConfigManager.findbyId(id);
		model.addAttribute("bankVersion", bankVersion);
		return prefix+"edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public void editPost(@RequestBody NoticeConfigRequest queryModel, Model model, HttpServletRequest request,HttpServletResponse response) throws ManagerException
	{
		AppAjaxResponse res =noticeConfigManager.edit(queryModel);
		try {
			HttpMimeResponseHelper.responseJson(res.toJson(), response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

