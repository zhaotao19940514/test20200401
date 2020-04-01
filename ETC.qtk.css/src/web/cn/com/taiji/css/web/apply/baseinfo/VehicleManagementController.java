package cn.com.taiji.css.web.apply.baseinfo;

import java.io.IOException;
import java.util.ArrayList;
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

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.apply.baseinfo.VehicleManagementManager;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.apply.customermanager.VehicleCheckRequset;
import cn.com.taiji.css.model.apply.customermanager.VehicleInfoRequest;
import cn.com.taiji.css.model.apply.customermanager.VehicleManagementRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.VehicleInfo;
import cn.com.taiji.qtk.entity.dict.CarType;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;
import cn.com.taiji.qtk.entity.dict.VehicleType;
import cn.com.taiji.qtk.entity.dict.VehicleTypeSimple;
import cn.com.taiji.qtk.entity.dict.VehicleUseCharacter;

@Controller
@RequestMapping("/apply/baseinfo/vehiclemanagement")
public class VehicleManagementController extends MyLogController {
      
	@Autowired
	private VehicleManagementManager vehicleManagementManager;
	
	private final String prefix = "/apply/baseinfo/vehiclemanagement/";
	
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
	   
		@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
		public String setupEdit(@PathVariable("id") String id, HttpServletRequest request,Model model) throws ManagerException
		{
			model.addAttribute("pageModel", vehicleManagementManager.findByIdEdit(id));
			model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
			model.addAttribute("vehicleUseCharacters", VehicleUseCharacter.getWithoutEmergency());
			model.addAttribute("vehicleTypeSimples", VehicleTypeSimple.getWithoutEmergency());
			model.addAttribute("vehicleTypes", VehicleType.values());
			model.addAttribute("customerIDTypes", CustomerIDType.values());
			model.addAttribute("carType", CarType.values());
			return prefix + "edit";
		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public String processEdit(@Valid @ModelAttribute("pageModel") VehicleInfoRequest vehicleInfo, HttpServletRequest request, Model model,
				HttpServletResponse response) throws ManagerException
		{
			String vehicleId = vehicleManagementManager.update(vehicleInfo,LoginHelper.getLoginUser(request));
			VehicleInfo info = vehicleManagementManager.findByVehicleId(vehicleId);
			model.addAttribute("vehicleTypes", VehicleType.values());
			model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
			model.addAttribute("customerIDTypes", CustomerIDType.values());
			model.addAttribute("vo", info);
			model.addAttribute("carType", CarType.values());
			addSuccess(response, "修改车辆成功");
			super.doUpdateLog(request, CssServiceLogType.APPLY_BASEINFO_VEHICLE, info);
			return prefix + "result";
		}
		
		@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
		public String setupDel(@PathVariable("id") String id, HttpServletRequest request, Model model,
				HttpServletResponse response) throws ManagerException 
		{
			model.addAttribute("pageModel", vehicleManagementManager.findByIdEdit(id));
			model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
			model.addAttribute("vehicleUseCharacters", VehicleUseCharacter.values());
			model.addAttribute("vehicleTypeSimples", VehicleTypeSimple.values());
			model.addAttribute("vehicleTypes", VehicleType.values());
			model.addAttribute("customerIDTypes", CustomerIDType.values());
			model.addAttribute("carType", CarType.values());
			return prefix + "delete";
		}
		
		@RequestMapping(value = "/del", method = RequestMethod.POST)
		public String processDel(@Valid @ModelAttribute("pageModel") VehicleInfoRequest vehicleInfo, HttpServletRequest request, Model model,
				HttpServletResponse response) throws ManagerException
		{
			vehicleManagementManager.delete(vehicleInfo,LoginHelper.getLoginUser(request));
			addSuccess(response, "删除车辆成功");
			model.addAttribute("vo",vehicleInfo);
			super.doDeleteLog(request, CssServiceLogType.APPLY_BASEINFO_VEHICLE, vehicleInfo);
			return prefix + "result";
		}
		
		@RequestMapping(value = "/vehicleCheck", method = RequestMethod.POST)
		public void vehicleCheck(@Valid @ModelAttribute("pageModel") VehicleCheckRequset vcr, HttpServletRequest request,Model model, HttpServletResponse response) throws ManagerException {
			
			AppAjaxResponse appAjaxResponse = vehicleManagementManager.vehicleCheck(vcr);
			try {
				HttpMimeResponseHelper.responseJson(appAjaxResponse.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ManagerException("校验结果返回失败",e);
			}
		}
		
		@RequestMapping(value = "/viewInfo/{id}", method = RequestMethod.GET)
		public String viewInfo(@PathVariable("id") String id, HttpServletRequest request, Model model,
				HttpServletResponse response) throws ManagerException 
		{
			model.addAttribute("pageModel", vehicleManagementManager.findByIdEdit(id));
			model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
			model.addAttribute("vehicleUseCharacters", VehicleUseCharacter.values());
			model.addAttribute("vehicleTypeSimples", VehicleTypeSimple.values());
			model.addAttribute("vehicleTypes", VehicleType.values());
			model.addAttribute("customerIDTypes", CustomerIDType.values());
			model.addAttribute("carType", CarType.values());
			return prefix + "viewInfo";
		}
		
		@RequestMapping(value="/viewOne/{id}",method=RequestMethod.GET)
		public String viewOne(@PathVariable("id") String id,HttpServletRequest request,Model model) throws ManagerException{
			VehicleInfo vehicleInfo = vehicleManagementManager.findById(id);
			List<String> picBase64 = vehicleManagementManager.getVehicleInfoPicBase64(vehicleInfo.getVehicleId(), 1);
			if(picBase64 == null || picBase64.size() == 0) throw new ManagerException("未查到证件照！");
			if(picBase64.size() <= 3) {
				model.addAttribute("imgBase64", picBase64);
			}else {
				List<String> listPic = new ArrayList<String>();
				for (int i = 0; i < 3; i++) {
					listPic.add(picBase64.get(i));
				}
				model.addAttribute("imgBase64", listPic);
			}
			
			return prefix+"view";
		}
		@RequestMapping(value="/viewTwo/{id}",method=RequestMethod.GET)
		public String viewTwo(@PathVariable("id") String id,HttpServletRequest request,Model model) throws ManagerException{
			VehicleInfo vehicleInfo = vehicleManagementManager.findById(id);
			List<String> picBase64 = vehicleManagementManager.getVehicleInfoPicBase64(vehicleInfo.getVehicleId(), 2);
			if(picBase64 == null || picBase64.size() == 0) throw new ManagerException("未查到车辆照！");
			if(picBase64.size() <= 3) {
				model.addAttribute("imgBase64", picBase64);
			}else {
				List<String> listPic = new ArrayList<String>();
				for (int i = 0; i < 3; i++) {
					listPic.add(picBase64.get(i));
				}
				model.addAttribute("imgBase64", listPic);
			}
			
			return prefix+"view";
		}
		
}
