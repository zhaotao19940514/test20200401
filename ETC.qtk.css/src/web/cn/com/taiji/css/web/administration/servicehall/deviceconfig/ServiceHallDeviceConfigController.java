/**
 * @Title ServiceHallDeviceConfig.java
 * @Package cn.com.taiji.css.web.administration.servicehall.deviceconfig
 * @Description TODO
 * @author yaonanlin
 * @date 2018年9月6日 下午3:41:50
 * @version V1.0
 */
package cn.com.taiji.css.web.administration.servicehall.deviceconfig;

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
import cn.com.taiji.css.manager.administration.agency.AgencyManager;
import cn.com.taiji.css.manager.serviceHall.deviceconfig.ServiceHallDeviceConfigManager;
import cn.com.taiji.css.model.request.serviceHall.deviceconfig.ServiceHallDeviceConfigEditByAgencyModel;
import cn.com.taiji.css.model.request.serviceHall.deviceconfig.ServiceHallDeviceConfigEditModel;
import cn.com.taiji.css.model.request.serviceHall.deviceconfig.ServiceHallDeviceConfigRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.ServiceHallDeviceConfig;

/**
 * @ClassName ServiceHallDeviceConfig
 * @Description TODO
 * @author yaonl
 * @date 2018年09月06日 15:41:50
 * @E_mail yaonanlin@163.com
 */
@Controller
@RequestMapping("/administration/servicehall/deviceconfig/")
public class ServiceHallDeviceConfigController extends MyLogController {
	private final String prefix = "administration/servicehall/deviceconfig/";
	@Autowired
	private ServiceHallDeviceConfigManager configManager;
	@Autowired
	private AgencyManager agencyManager;
	@RequestMapping(value = "manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") ServiceHallDeviceConfigRequest req,Model model) {
		model.addAttribute("agencies", agencyManager.findAll());
		return prefix + "manage";
	}

	@RequestMapping(value = "manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") ServiceHallDeviceConfigRequest req, Model model) {
		model.addAttribute("pagn", configManager.page(req));
		return prefix + "queryResult";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String toAdd(@ModelAttribute("addModel") ServiceHallDeviceConfig configToAdd, Model model) {
		return prefix + "add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String doAdd(@ModelAttribute("addModel") ServiceHallDeviceConfig configToAdd,
			HttpServletRequest request, HttpServletResponse response, Model model) throws ManagerException {
		ServiceHallDeviceConfig config = configManager.add(configToAdd);
		addSuccess(response, "添加网点设备配置信息成功");
		model.addAttribute("vo", config);
		doAddLog(request,CssServiceLogType.ADMINISTRATION_SERVICEHALL_DEVICECONFIG,config);
		return prefix + "result";
	}
	
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String toEdit(@ModelAttribute("editModel") ServiceHallDeviceConfigEditModel configToEdit,
			@PathVariable("id") String id, Model model) {
		ServiceHallDeviceConfig config = configManager.findById(id);
		model.addAttribute("editModel", config);
		return prefix + "edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String doEdit(@ModelAttribute("editModel") ServiceHallDeviceConfigEditModel configToEdit,
			HttpServletRequest request, HttpServletResponse response, Model model) throws ManagerException {
		ServiceHallDeviceConfig config = configManager.edit(configToEdit);
		addSuccess(response, "修改网点设备配置信息成功");
		model.addAttribute("vo", config);
		doUpdateLog(request,CssServiceLogType.ADMINISTRATION_SERVICEHALL_DEVICECONFIG,config);
		return prefix + "result";
	}

	@RequestMapping(value = "editByAgency", method = RequestMethod.GET)
	public String toEditByAgency(@ModelAttribute("editModel") ServiceHallDeviceConfigEditByAgencyModel configToEdit,
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws ManagerException {
		model.addAttribute("agencies", agencyManager.findAll());
		return prefix + "editByAgency";
	}

	@RequestMapping(value = "editByAgency", method = RequestMethod.POST)
	public String doEditByAgency(@ModelAttribute("editModel") ServiceHallDeviceConfigEditByAgencyModel configToEdit,
			HttpServletRequest request,  HttpServletResponse response,
			Model model) throws ManagerException {
		configManager.editByAgencyId(configToEdit);
		addSuccess(response, "按机构批量修改网点设备配置信息成功");
		// 刷新由页面事件触发
		return prefix + "result";
	}

	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String toEditByAgency(HttpServletRequest request, @PathVariable("id") String id,
			HttpServletResponse response, Model model) throws ManagerException {
		ServiceHallDeviceConfig config = configManager.findById(id);
		model.addAttribute("vo", config);
		return prefix + "view";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public String doDelete(HttpServletRequest request, @PathVariable("id") String id, HttpServletResponse response,
			Model model) throws ManagerException {
		ServiceHallDeviceConfig config = configManager.delete(id);
		model.addAttribute("vo", config);
		addSuccess(response, "删除网点{}设备配置成功！",config.getServiceHall().getName());
		doDeleteLog(request, CssServiceLogType.ADMINISTRATION_SERVICEHALL_DEVICECONFIG, config);
		return prefix + "result";
	}
}
