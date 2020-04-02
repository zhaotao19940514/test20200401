package cn.com.taiji.css.web.administration.agency.permission;

import java.io.IOException;
import java.util.List;

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
import cn.com.taiji.common.model.LabelIdPair;
import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.css.entity.dict.CssOperateLogType;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.administration.agency.permission.AgencyPermissionManager;
import cn.com.taiji.css.model.administration.agency.permission.AgencyPermissionModel;
import cn.com.taiji.css.model.administration.agency.permission.AgencyPermissionRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.dict.CardTypeSimple;

/**
 * 
 * @author lz
 *
 */
@Controller
@RequestMapping("/administration/agencypermission")
public class AgencyPermissionController extends MyLogController {

	private final String prefix = "administration/agency/permission/";

	@Autowired
	private AgencyPermissionManager agencyPermissionManager;

	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") AgencyPermissionRequest queryModel, Model model) {
		model.addAttribute("cardTypeCode", CardTypeSimple.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") AgencyPermissionRequest queryModel, Model model,
			HttpServletRequest request) throws ManagerException {
		model.addAttribute("pagn", agencyPermissionManager.query(queryModel));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addGet(@ModelAttribute("addModel") AgencyPermissionRequest addModel, Model model) {
		model.addAttribute("cardTypeCode", CardTypeSimple.values());
		return prefix + "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addPost(@ModelAttribute("addModel") AgencyPermissionRequest addModel, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ManagerException {
		agencyPermissionManager.add(addModel);
		super.doSysLog(request, CssServiceLogType.ADMINISTRATION_AGENCY_VARIFYMANAGE, CssOperateLogType.ADD, null, "渠道权限添加", addModel);
		addSuccess(response, "添加权限成功！");
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editGet(@PathVariable("id") String id, @ModelAttribute("editModel") AgencyPermissionRequest editModel,
			Model model) throws ManagerException {
		AgencyPermissionModel agencyVarifyInfo = agencyPermissionManager.getAgencyVarifyInfo(id);
		model.addAttribute("agencyId", agencyPermissionManager.getAgencyIdById(id));
		model.addAttribute("oldInfo", agencyVarifyInfo);
		model.addAttribute("cardTypeCode", CardTypeSimple.values());
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@ModelAttribute("editModel") AgencyPermissionRequest editModel, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ManagerException {
		agencyPermissionManager.edit(editModel);
		super.doSysLog(request, CssServiceLogType.ADMINISTRATION_AGENCY_VARIFYMANAGE, CssOperateLogType.UPDATE, null, "渠道权限修改", editModel);
		addSuccess(response, "修改权限成功！");
		return prefix + "result";
	}

	@RequestMapping(value = "/batchAdd", method = RequestMethod.GET)
	public String batchAddGet(@ModelAttribute("batchAddModel") AgencyPermissionRequest batchAddModel, Model model)
			throws ManagerException {
		model.addAttribute("cardTypeCode", CardTypeSimple.values());
		return prefix + "batchAdd";
	}

	@RequestMapping(value = "/batchAdd", method = RequestMethod.POST)
	public void batchAddPost(@ModelAttribute("batchAddModel") AgencyPermissionRequest batchAddModel, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ManagerException {
		agencyPermissionManager.batchAdd(batchAddModel);
		super.doSysLog(request, CssServiceLogType.ADMINISTRATION_AGENCY_VARIFYMANAGE, CssOperateLogType.ADD,"批量添加" , "渠道权限添加", batchAddModel);
		addSuccess(response, "批量增加权限成功！");
	}

	@RequestMapping(value = "/batchDelete", method = RequestMethod.GET)
	public String batchDeleteGet(@ModelAttribute("batchDeleteModel") AgencyPermissionRequest batchDeleteModel,
			Model model) throws ManagerException {
		model.addAttribute("cardTypeCode", CardTypeSimple.values());
		return prefix + "batchDelete";
	}

	@RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
	public void batchDeletePost(@ModelAttribute("batchDeleteModel") AgencyPermissionRequest batchDeleteModel,
			Model model, HttpServletRequest request, HttpServletResponse response) throws ManagerException {
		agencyPermissionManager.batchDelete(batchDeleteModel);
		super.doSysLog(request, CssServiceLogType.ADMINISTRATION_AGENCY_VARIFYMANAGE, CssOperateLogType.DELETE,"批量删除" , "渠道权限删除", batchDeleteModel);
		addSuccess(response, "批量删除权限成功！");
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public void delete(@PathVariable("id") String id, HttpServletResponse response, HttpServletRequest request) throws ManagerException {
		AgencyPermissionRequest deleteModel = new AgencyPermissionRequest();
		deleteModel.setId(id);
		agencyPermissionManager.delete(id);
		super.doSysLog(request, CssServiceLogType.ADMINISTRATION_AGENCY_VARIFYMANAGE, CssOperateLogType.DELETE,null , "渠道权限删除", deleteModel);
		addSuccess(response, "删除渠道权限成功");
	}

	@RequestMapping("/queryByAgencyName")
	public void queryByName(@RequestParam("agencyId") String name, Model model, HttpServletRequest request,
			HttpServletResponse response) throws ManagerException, IOException {
		List<LabelIdPair> pairs = LabelIdPair.fromList(agencyPermissionManager.findLikeName(name), "name", "agencyId");
		super.responseJson(JsonTools.toJsonStr(pairs), response);
	}

	@RequestMapping("/queryByPermissionAgencyName")
	public void queryByPermissionName(@RequestParam("permittedAgencyId") String name, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ManagerException, IOException {
		List<LabelIdPair> pairs = LabelIdPair.fromList(agencyPermissionManager.findLikeName(name), "name", "agencyId");
		super.responseJson(JsonTools.toJsonStr(pairs), response);
	}

	@RequestMapping("/agencyJsonInfo")
	public void getAgencyJsonInfo(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ManagerException, IOException {
		super.responseJson(JsonTools.toJsonStr(agencyPermissionManager.getAgencyInfo()),response);
	}
}
