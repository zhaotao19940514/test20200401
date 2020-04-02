package cn.com.taiji.css.web.administration.pkg;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.ServiceHandleException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.administration.pkg.AccountManager;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.model.administration.pkg.AccountRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.Package;


@Controller
@RequestMapping("/administration/package/account")
public class AccountController extends MyLogController {
	private final String prefix = "administration/package/account/";
	
	@Autowired
	private AccountManager accountManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") AccountRequest queryModel, Model model)
	{
		return prefix+"manage";
	}
  
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") AccountRequest queryModel, Model model)
	{	
		Pagination pagn = accountManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupAdd(@ModelAttribute("pageModel") Package packageInfo,HttpServletRequest request, Model model) throws ServiceHandleException, IOException
	{      List<ZTreeItem> list = accountManager.getCurrentConf(packageInfo.getId());
		   String json = JsonTools.toJsonStr(list);
		   model.addAttribute("json", json);
		return prefix + "add";
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAdd(@RequestBody @Valid @ModelAttribute("pageModel") Package packageInfo, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException, ManagerException
	{   
		packageInfo.setCreateTime(Calendar.getInstance());
		Package po = accountManager.add(packageInfo);
		addSuccess(response, "添加记账卡套餐成功");
		model.addAttribute("vo", po);
		po.setAgencyIds(null);
		po.setServiceHallList(null);
		super.doAddLog(request, CssServiceLogType.ADMINISTRATION_PKG_ACCOUNT, po);
		return prefix + "result";
	}
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id ,HttpServletRequest request, Model model) throws ServiceHandleException, IOException
	{   List<ZTreeItem> list = accountManager.getCurrentConf(id);
		String json = JsonTools.toJsonStr(list);
		model.addAttribute("json", json);
		model.addAttribute("pageModel", accountManager.findId(id));
		return prefix + "edit";
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(@Valid @ModelAttribute("pageModel") Package packageInfo, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException, ManagerException
	{
		Package po = accountManager.update(packageInfo);
		addSuccess(response, "修改记账卡套餐成功");
		model.addAttribute("vo", po);
		po.setAgencyIds(null);
		po.setServiceHallList(null);
		super.doUpdateLog(request, CssServiceLogType.ADMINISTRATION_PKG_ACCOUNT, po);
		return prefix + "result";	
	}
	
	
}
