/**
 * @Title AgencyController.java
 * @Package cn.com.taiji.css.web.administration.agency
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月28日 下午2:49:20
 * @version V1.0
 */
package cn.com.taiji.css.web.administration.agency;

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
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.administration.agency.AgencyManager;
import cn.com.taiji.css.model.administration.agency.AgencyModel;
import cn.com.taiji.css.model.request.agency.AgencyRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.Agency;

/**
 * @ClassName AgencyController
 * @Description TODO
 * @author yaonl
 * @date 2018年08月28日 14:49:20
 * @E_mail yaonanlin@163.com
 */
@Controller
@RequestMapping("/administration/agency")
public class AgencyController extends MyLogController {
	private final String prefix = "administration/agency/";
	@Autowired
	private AgencyManager agencyManager;

	@RequestMapping(value = "manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") AgencyRequest req) {
		return prefix + "manage";
	}

	@RequestMapping(value = "manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") AgencyRequest req, Model model) {
		model.addAttribute("pagn", agencyManager.page(req));
		return prefix + "queryResult";
	}

	@RequestMapping("/queryByNameForModal")
	public void queryByNameForModal(@RequestParam("accountId") String name, HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		List<LabelIdPair> pairs = LabelIdPair.fromList(agencyManager.queryByName(name), "name", "agencyId");
		super.responseJson(JsonTools.toJsonStr(pairs), response);
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String toAdd(@ModelAttribute("addModel") Agency agency) {
		return prefix + "add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String doAdd(@ModelAttribute("addModel") Agency agencyToAdd, Model model, HttpServletRequest request,
			HttpServletResponse response) throws ManagerException {
		Agency agency = agencyManager.add(agencyToAdd);
		addSuccess(response, "添加机构成功！");
		model.addAttribute("vo",agency);
		doAddLog(request, CssServiceLogType.ADMINISTRATION_AGENCY_MANAGEMENT, agency);
		return prefix + "result";
	}

	@RequestMapping(value = "edit/{id}&{accountId}", method = RequestMethod.GET)
	public String toEdit(@ModelAttribute("editModel") AgencyModel agencyToEdit, @PathVariable("id") String id,@PathVariable("accountId") String accountId, Model model) {
		Agency agency = agencyManager.findById(id);
		Agency account= agencyManager.findAccount(accountId);
		agencyToEdit=agencyToEdit.toSet(agency,account);
		model.addAttribute("editModel", agencyToEdit);
		return prefix + "edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String doEdit(@ModelAttribute("editModel") Agency agencyToEdit, HttpServletRequest request,
			HttpServletResponse response, Model model) throws ManagerException {
		Agency agency = agencyManager.edit(agencyToEdit);
		addSuccess(response, "修改机构信息成功");
		model.addAttribute("vo", agency);
		doUpdateLog(request, CssServiceLogType.ADMINISTRATION_AGENCY_MANAGEMENT, agency);
		return prefix + "result";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws ManagerException {
		Agency agency = agencyManager.findById(id);
		agencyManager.delete(agency);
		addSuccess(response, "删除机构成功");
		doDeleteLog(request, CssServiceLogType.ADMINISTRATION_AGENCY_MANAGEMENT, agency);
		return prefix + "result";
	}

	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) {
		Agency agency = agencyManager.findById(id);
		model.addAttribute("vo", agency);
		return prefix + "view";
	}
}
