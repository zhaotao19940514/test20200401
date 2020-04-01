package cn.com.taiji.css.web.administration.pkg;
import java.io.IOException;
import java.util.List;

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
import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.administration.pkg.IssueManager;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.model.administration.pkg.IssueRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.IssuePackageInfo;

@Controller
@RequestMapping("/administration/package/issue")
public class IssueController extends MyLogController {
	private final String prefix = "administration/package/issue/";
	
	@Autowired
	private IssueManager issueManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") IssueRequest queryModel, Model model)
	{
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") IssueRequest queryModel, Model model)
	{
		Pagination pagn = issueManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupAdd(@ModelAttribute("pageModel") IssuePackageInfo issuePackageInfo,HttpServletRequest request, Model model) throws ServiceHandleException, Exception
	{
		List<ZTreeItem> list = issueManager.getCurrentConf(issuePackageInfo.getId());
		String json = JsonTools.toJsonStr(list);
		model.addAttribute("json", json);
		return prefix + "add";
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAdd(@Valid @ModelAttribute("pageModel") IssuePackageInfo issuePackageInfo, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException, ManagerException
	{   
		User user = LoginHelper.getLoginUser(request);
		issuePackageInfo.setCreatePerson(user.getName());
		IssuePackageInfo po = issueManager.add(issuePackageInfo);
		addSuccess(response, "添加发行套餐成功");
		model.addAttribute("vo", po);
		super.doAddLog(request, CssServiceLogType.ADMINISTRATION_PKG_ISSUE, po);
		return prefix + "result";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id ,HttpServletRequest request, Model model) throws ServiceHandleException, IOException
	{   
		List<ZTreeItem> list = issueManager.getCurrentConf(id);
		String json = JsonTools.toJsonStr(list);
		model.addAttribute("json", json);
		model.addAttribute("pageModel", issueManager.findId(id));
		return prefix + "edit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(@Valid @ModelAttribute("pageModel") IssuePackageInfo issuePackageInfo, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException, ManagerException
	{   
		User user = LoginHelper.getLoginUser(request);
		IssuePackageInfo po = issueManager.update(issuePackageInfo,user.getName());;
		addSuccess(response, "修改套餐成功");
		model.addAttribute("vo", po);
		super.doUpdateLog(request, CssServiceLogType.ADMINISTRATION_PKG_ISSUE, po);
		return prefix + "result";
		
	}
	
}
