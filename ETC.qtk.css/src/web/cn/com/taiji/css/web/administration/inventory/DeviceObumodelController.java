package cn.com.taiji.css.web.administration.inventory;

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
import cn.com.taiji.css.manager.administration.inventory.DeviceObumodelManager;
import cn.com.taiji.css.model.administration.inventory.DevicemodelRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CssObuInfo;

@Controller
@RequestMapping("/administration/inventory/devicemodel/obu")
public class DeviceObumodelController extends MyLogController {
	private final String prefix = "administration/inventory/devicemodel/obu/";

	@Autowired
	private DeviceObumodelManager devicemodelManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") DevicemodelRequest queryModel, Model model) {
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") DevicemodelRequest queryModel, HttpServletRequest request,
			Model model) throws ManagerException {
		Pagination pagn = devicemodelManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
//		super.doQueryLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_DEVICEOBUMODEL, queryModel);
		return prefix + "queryResult";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupAdd(@ModelAttribute("pageModel") CssObuInfo obuModel, HttpServletRequest request, Model model) {
		obuModel.setType("电子标签");
		return prefix + "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAdd(@Valid @ModelAttribute("pageModel") CssObuInfo obuModel, HttpServletRequest request,
			Model model, HttpServletResponse response) throws ManagerException {
		CssObuInfo po = devicemodelManager.add(obuModel);
		addSuccess(response, "添加obu类型信息成功");
		model.addAttribute("vo", po);
		super.doAddLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_DEVICEOBUMODEL, po);
		return prefix + "result";
	}

	/**
	 * 修改
	 */

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id, HttpServletRequest request, Model model) {
		model.addAttribute("pageModel", devicemodelManager.findById1(id));
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(@Valid @ModelAttribute("pageModel") CssObuInfo obuModel, HttpServletRequest request,
			Model model, HttpServletResponse response) throws ManagerException {
		CssObuInfo po = devicemodelManager.updateCssObuInfo(obuModel);
		addSuccess(response, "修改obu设备信息成功");
		model.addAttribute("vo", po);
		super.doUpdateLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_DEVICEOBUMODEL, po);
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
		CssObuInfo cssObuInfo = devicemodelManager.findById1(id);
		model.addAttribute("model", cssObuInfo);
		devicemodelManager.delete(id);
		addSuccess(response, "删除obu设备信息成功");
		super.doDeleteLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_DEVICEOBUMODEL, cssObuInfo);
	}
}
