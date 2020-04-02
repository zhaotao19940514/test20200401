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

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.entity.dict.EquipmentType;
import cn.com.taiji.css.manager.administration.inventory.DeviceCardModelManager;
import cn.com.taiji.css.model.administration.inventory.CssCardRequest;
import cn.com.taiji.css.model.administration.inventory.DeviceCardModelRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CssCardInfo;
import cn.com.taiji.qtk.entity.dict.CssCardType;
import cn.com.taiji.qtk.entity.dict.CssObuType;

@Controller
@RequestMapping("/administration/inventory/devicemodel/card")
public class DeviceCardmodelController extends MyLogController {
	private final String prefix = "administration/inventory/devicemodel/card/";

	@Autowired
	private DeviceCardModelManager deviceCardModelManager;

	/**
	 * 查询
	 */
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") DeviceCardModelRequest queryModel, Model model) {
		model.addAttribute("equipmentType", EquipmentType.values());
		model.addAttribute("cardType", CssCardType.values());
		model.addAttribute("obuType", CssObuType.values());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") DeviceCardModelRequest queryModel,
			HttpServletRequest request, Model model) throws ManagerException {
		Pagination pagn = deviceCardModelManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
//		super.doQueryLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_DEVICECARDMODEL, queryModel);
		return prefix + "queryResult";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupAdd(@ModelAttribute("pageModel") CssCardInfo cardModel, HttpServletRequest request,
			Model model) {
//		cardModel.setType("黔通卡");
		model.addAttribute("equipmentType", EquipmentType.values());
		model.addAttribute("cardType", CssCardType.values());
		model.addAttribute("obuType", CssObuType.values());
		return prefix + "add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAdd(@Valid @ModelAttribute("pageModel") CssCardInfo cardModel, HttpServletRequest request,
			Model model, HttpServletResponse response) throws ManagerException {
		CssCardInfo po = deviceCardModelManager.add(cardModel);
		addSuccess(response, "添加黔通卡设备类型信息成功");
		model.addAttribute("vo", po);
		super.doAddLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_DEVICECARDMODEL, po);
		return prefix + "result";
	}

	/**
	 * 修改
	 */

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id, HttpServletRequest request, Model model) {
		model.addAttribute("pageModel", deviceCardModelManager.findById1(id));
		model.addAttribute("equipmentType", EquipmentType.values());
		model.addAttribute("cardType", CssCardType.values());
		model.addAttribute("obuType", CssObuType.values());
		for(CssObuType type:CssObuType.values()) {
			System.out.println(type.getValue());
			System.out.println(type.getCode());
		}
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(@Valid @ModelAttribute("pageModel") CssCardRequest cardModel, HttpServletRequest request,
			Model model, HttpServletResponse response) throws ManagerException {
		CssCardInfo po = deviceCardModelManager.updateCssCardInfo(cardModel);
		addSuccess(response, "修改黔通卡设备信息成功");
		model.addAttribute("vo", po);
		super.doUpdateLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_DEVICECARDMODEL, po);
		return prefix + "result";

	}

	/**
	 * 删除 Lusb
	 * 
	 * @throws ManagerException
	 */
	@RequestMapping(value = "/delete/{id}")
	public void processEdit(@PathVariable("id") String id, Model model, HttpServletResponse response,
			HttpServletRequest request) throws ManagerException {
		CssCardInfo cssCardInfo = deviceCardModelManager.findById1(id);
		model.addAttribute("model", cssCardInfo);
		deviceCardModelManager.delete(id);
		addSuccess(response, "删除obu设备信息成功");
		super.doDeleteLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_DEVICECARDMODEL, cssCardInfo);
	}
}
