/**
 * @Title StaffController.java
 * @Package cn.com.taiji.css.web.administration.staff
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月30日 上午11:11:59
 * @version V1.0
 */
package cn.com.taiji.css.web.administration.staff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.staff.StaffManager;
import cn.com.taiji.css.model.administration.staff.StaffModel;
import cn.com.taiji.css.model.request.staff.StaffRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.Staff;

/**
 * @ClassName StaffController
 * @Description TODO
 * @author yaonl
 * @date 2018年08月30日 11:11:59
 * @E_mail yaonanlin@163.com
 */
@Controller
@RequestMapping("/administration/staff/")
public class StaffController extends MyLogController {
	private final String prefix = "administration/staff/";
	@Autowired
	private StaffManager staffManager;
	@RequestMapping(value = "manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") StaffRequest req) {
		return prefix + "manage";
	}

	@RequestMapping(value = "manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") StaffRequest req, Model model) {
		model.addAttribute("pagn", staffManager.page(req));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String toAdd(@ModelAttribute("addModel") StaffModel staff,Model model) {
		return prefix + "add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String doAdd(@ModelAttribute("addModel") StaffModel staffToAdd, Model model, HttpServletRequest request,
			HttpServletResponse response) throws ManagerException {
		Staff staff = staffManager.add(staffToAdd);
		addSuccess(response, "添加工号成功！");
		model.addAttribute("vo",staff);
		doAddLog(request, CssServiceLogType.ADMINISTRATION_STAFF_MANAGEMENT, staff);
		return prefix + "result";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String toEdit(@ModelAttribute("editModel") StaffModel staffToEdit, @PathVariable("id") String id, Model model) {
		StaffModel staffModel = staffManager.findByIdForEdit(id);
		staffModel = staffManager.addModel(staffModel);
		model.addAttribute("editModel", staffModel);
		return prefix + "edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String doEdit(@ModelAttribute("editModel") StaffModel staffToEdit, HttpServletRequest request,
			HttpServletResponse response, Model model) throws ManagerException {
		Staff staff = staffManager.edit(staffToEdit);
		addSuccess(response, "修改工号信息成功");
		model.addAttribute("vo", staff);
		doUpdateLog(request, CssServiceLogType.ADMINISTRATION_STAFF_MANAGEMENT, staff);
		return prefix + "result";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws ManagerException {
		Staff staff = staffManager.findById(id);
		staffManager.delete(staff);
		addSuccess(response, "删除工号成功");
		doDeleteLog(request, CssServiceLogType.ADMINISTRATION_STAFF_MANAGEMENT, staff);
		return prefix + "result";
	}

	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) {
		StaffModel staffModel=new StaffModel();
		Staff staff = staffManager.findById(id);
		Agency agency=staffManager.findbyAgencyId(staff.getAgencyId());
		Agency account=staffManager.findbyAgencyId(staff.getAccountId());
		staffModel.setAgency(agency);
		staffModel.setAccount(account);
		model.addAttribute("vo", staff);
		model.addAttribute("staffModel", staffModel);
		return prefix + "view";
	}
	
}

