package cn.com.taiji.css.web.apply.emergency;

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

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.apply.baseinfo.CustomerManager;
import cn.com.taiji.css.manager.apply.baseinfo.QuickApplyPngModel;
import cn.com.taiji.css.manager.apply.baseinfo.VehicleManagementManager;
import cn.com.taiji.css.manager.apply.quickapply.QuickApplyManagerImpl;
import cn.com.taiji.css.model.apply.customermanager.EgCustomerManagerRequest;
import cn.com.taiji.css.model.apply.customermanager.VehicleInfoRequest;
import cn.com.taiji.css.model.apply.quickapply.VehicleInfoQuickQueryRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.VehicleInfo;
import cn.com.taiji.qtk.entity.dict.CarType;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.CustomerType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;
import cn.com.taiji.qtk.entity.dict.VehicleType;
import cn.com.taiji.qtk.entity.dict.VehicleTypeSimple;
import cn.com.taiji.qtk.entity.dict.VehicleUseCharacter;


@Controller
@RequestMapping("/apply/emergency/usermanager")
public class EgCustomerManagerController extends MyLogController {
	
	@Autowired
	private CustomerManager customerManager;
	@Autowired
	private QuickApplyManagerImpl quickApplyManager; 
	@Autowired
	private VehicleManagementManager vehicleManagementManager;
	
	private final String prefix = "/apply/emergency/usermanager/";
	
	 @RequestMapping(value = "/manage", method = RequestMethod.GET)
		public String manageGet(@ModelAttribute("queryModel") EgCustomerManagerRequest queryModel, Model model,HttpServletRequest req)
		{
 			model.addAttribute("types", CustomerIDType.values());
			return prefix + "manage";
		}
	   
	     
	   @RequestMapping(value = "/manage", method = RequestMethod.POST)
		public String managePost(@ModelAttribute("queryModel") EgCustomerManagerRequest queryModel, Model model ,HttpServletRequest req)
		{
//		   queryModel.setAgencyId(LoginHelper.getLoginUser(req).getStaff().getServiceHall().getAgencyId());
		   model.addAttribute("types", CustomerIDType.values());
		   model.addAttribute("pagn", this.customerManager.queryPage(queryModel));
		   return prefix + "queryResult";
		}
	   
	   
	   @RequestMapping(value = "/manageEdit/{customerId}", method = RequestMethod.GET)
		public String manageEditGet(@ModelAttribute("queryModel") VehicleInfoQuickQueryRequest req,@PathVariable("customerId") String customerId, HttpServletRequest request,
				Model model) throws ManagerException {
			model.addAttribute("pageModel", this.customerManager.findByCustomerId(customerId));
			model.addAttribute("types", CustomerIDType.values());
			model.addAttribute("personTypes", CustomerIDType.getPersonIDType());
			model.addAttribute("customerTypes", CustomerType.values());
			return prefix + "manageEdit";
		}
		
		@RequestMapping(value = "/manageEdit", method = RequestMethod.POST)
		public String manageEditPost(@Valid @ModelAttribute("queryModel") VehicleInfoQuickQueryRequest req,HttpServletRequest request, Model model)
		{
//			req.setAgencyId(LoginHelper.getLoginUser(request).getStaff().getServiceHall().getAgencyId());
			req.setEmergencyFlag(1);
			model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
			model.addAttribute("vehicleTypes", VehicleType.values());
			model.addAttribute("pagn", this.quickApplyManager.queryPage(req));
			return prefix + "queryResultEdit";
		}
	   
		@RequestMapping(value = "/addCar/{customerId}", method = RequestMethod.GET)
		public String setupAdd(@PathVariable("customerId") String customerId,HttpServletRequest request, Model model)
		{
			model.addAttribute("customerId", customerId);
			model.addAttribute("vehiclePlateColorTypes", Lists.newArrayList(VehiclePlateColorType.WHITE));
			model.addAttribute("vehicleUseCharacters", Lists.newArrayList(VehicleUseCharacter.YJJYC));
			model.addAttribute("vehicleTypeSimples", Lists.newArrayList(VehicleTypeSimple.ZXZZY));
			model.addAttribute("vehicleTypes", VehicleType.values());
			model.addAttribute("pageModel", new VehicleInfoRequest());
			model.addAttribute("carType", CarType.values());
			model.addAttribute("customerIDTypes", CustomerIDType.values());
			return prefix + "addCar";
		}
		@RequestMapping(value = "/addCar", method = RequestMethod.POST)
		public String processAdd(@Valid @ModelAttribute("pageModel") VehicleInfoRequest vehicleInfoRequest, HttpServletRequest request, Model model,
				HttpServletResponse response) throws ManagerException
		{
			vehicleInfoRequest.setEmergencyFlag(1);
			String vehicleId = vehicleManagementManager.addCar(vehicleInfoRequest,LoginHelper.getLoginUser(request));
			VehicleInfo vhcl = vehicleManagementManager.findByVehicleId(vehicleId);
			model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
			model.addAttribute("vehicleTypes", VehicleType.values());
			model.addAttribute("vo", vhcl);
			model.addAttribute("carType", CarType.values());
			super.doAddLog(request, CssServiceLogType.APPLY_EMERGENCY_VEHICLE, vhcl);
			addSuccess(response, "添加车辆信息成功！");
			return prefix + "resultEdit";
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public void processEdit(@Valid @ModelAttribute("pageModel") QuickApplyPngModel customerInfo, HttpServletRequest request,
				Model model, HttpServletResponse response) throws Exception {
			String customerId = customerManager.update(customerInfo,LoginHelper.getLoginUser(request));
			CustomerInfo cstm = customerManager.findByCustomerId(customerId);
			addSuccess(response, "修改成功");
			super.doUpdateLog(request, CssServiceLogType.APPLY_EMERGENCY_CUSTOMER, cstm);
//			HttpMimeResponseHelper.responseJson(cstm.toJson(), response);
		}
	   
}
