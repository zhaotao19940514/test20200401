package cn.com.taiji.css.web.apply.baseinfo;

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
import cn.com.taiji.css.manager.apply.baseinfo.VehicleManagementManager;
import cn.com.taiji.css.model.apply.customermanager.VehicleManagementRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.VehicleInfo;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;
import cn.com.taiji.qtk.entity.dict.VehicleType;

@Controller
@RequestMapping("/apply/baseinfo/emergencyvehicle")
public class EmergencyvehicleController extends MyLogController {
      
	@Autowired
	private VehicleManagementManager vehicleManagementManager;
	
	private final String prefix = "/apply/baseinfo/emergencyvehicle/";
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") VehicleManagementRequest queryModel, Model model,HttpServletRequest req)
	{
	    model.addAttribute("customerIDTypes", CustomerIDType.values());
	    model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		return prefix + "manage";
	}
	     
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") VehicleManagementRequest queryModel, Model model ,HttpServletRequest req)
	{
//		   queryModel.setAgencyId(LoginHelper.getLoginUser(req).getStaff().getServiceHall().getAgencyId());
	   model.addAttribute("vehicleTypes", VehicleType.values());
	   model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
	   model.addAttribute("customerIDTypes", CustomerIDType.values());
	   model.addAttribute("pagn", this.vehicleManagementManager.baseQueryPage(queryModel));
	   return prefix + "queryResult";
	}
	
	@RequestMapping(value = "/emergencytrue/{id}", method = RequestMethod.POST)
	public void emergencyTrue(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response, Model model) throws ManagerException
	{
		VehicleInfo vehicleInfo = this.vehicleManagementManager.emergencyAlter(id, 1);
//		doUpdateLog(request, CssServiceLogType.APPLY_BASEINFO_EMERGENCYTRUE, vehicleInfo);
		addSuccess(response, vehicleInfo.getVehicleId() + "标记为应急车辆成功！");
	}
	@RequestMapping(value = "/emergencyfalse/{id}", method = RequestMethod.POST)
	public void emergencyFalse(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response, Model model) throws ManagerException
	{
		VehicleInfo vehicleInfo = this.vehicleManagementManager.emergencyAlter(id, 0);
//		doUpdateLog(request, CssServiceLogType.APPLY_BASEINFO_EMERGENCYFALSE, vehicleInfo);
		addSuccess(response, vehicleInfo.getVehicleId() + "取消应急车辆标记成功！");
	}
}
