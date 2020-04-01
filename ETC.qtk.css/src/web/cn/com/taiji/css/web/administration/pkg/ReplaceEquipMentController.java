package cn.com.taiji.css.web.administration.pkg;

import java.io.IOException;

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
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.administration.pkg.ReplaceEquipmentManager;
import cn.com.taiji.css.model.administration.pkg.ReplaceEquipmentRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.ReplaceEquipmentCostDetail;

@Controller
@RequestMapping("/administration/package/replace")
public class ReplaceEquipMentController extends MyLogController {
	private final String prefix = "administration/package/replace/";
	
	@Autowired
	private ReplaceEquipmentManager replaceEquipmentManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") ReplaceEquipmentRequest queryModel, Model model)
	{   
		return prefix+"manage";
	}
	
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") ReplaceEquipmentRequest queryModel, Model model)
	{
		Pagination pagn = replaceEquipmentManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupAdd(@ModelAttribute("pageModel") ReplaceEquipmentCostDetail replaceEquipmentCostDetail,HttpServletRequest request, Model model) throws ServiceHandleException, IOException
	{      
		return prefix + "add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAdd(@RequestBody @Valid @ModelAttribute("pageModel") ReplaceEquipmentCostDetail replaceEquipmentCostDetail, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException, ManagerException
	{   User user = LoginHelper.getLoginUser(request);
		ReplaceEquipmentCostDetail resd =  replaceEquipmentManager.add(replaceEquipmentCostDetail,user);
		addSuccess(response, "添加补换设备费用信息成功");
		model.addAttribute("vo", resd);
		super.doAddLog(request, CssServiceLogType.ADMINISTRATION_PKG_REPLACE, resd);
		return prefix + "result";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id ,HttpServletRequest request, Model model) throws IOException, ManagerException
	{  
		model.addAttribute("pageModel", replaceEquipmentManager.findId(id));
		return prefix + "edit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(@Valid @ModelAttribute("pageModel") ReplaceEquipmentCostDetail replaceEquipmentCostDetail, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException, ManagerException
	{   User user = LoginHelper.getLoginUser(request);
		ReplaceEquipmentCostDetail po = replaceEquipmentManager.update(replaceEquipmentCostDetail,user);
		addSuccess(response, "修改补换设备费用信息成功");
		model.addAttribute("vo", po);
		super.doUpdateLog(request, CssServiceLogType.ADMINISTRATION_PKG_REPLACE, po);

		return prefix + "result";	
	}
	
}
