package cn.com.taiji.css.web.apply.quickapply;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.administration.pkg.AccountManager;
import cn.com.taiji.css.manager.administration.pkg.IssueManager;
import cn.com.taiji.css.manager.apply.baseinfo.CardManager;
import cn.com.taiji.css.manager.apply.baseinfo.CustomerManager;
import cn.com.taiji.css.manager.apply.baseinfo.ObuManager;
import cn.com.taiji.css.manager.apply.baseinfo.VehicleManagementManager;
import cn.com.taiji.css.manager.apply.quickapply.EquipmentIssueManager;
import cn.com.taiji.css.manager.apply.quickapply.QuickApplyManager;
import cn.com.taiji.css.manager.customerservice.card.AppCardStatusChangeResponse;
import cn.com.taiji.css.manager.customerservice.card.SupplyManager;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.apply.customermanager.CssInventoryCheckResponse;
import cn.com.taiji.css.model.apply.customermanager.InventoryVerifyRequset;
import cn.com.taiji.css.model.apply.customermanager.PackageTotalMoneyResponse;
import cn.com.taiji.css.model.apply.customermanager.VehicleManagementRequest;
import cn.com.taiji.css.model.apply.quickapply.CarIssuePackageCheckRequest;
import cn.com.taiji.css.model.apply.quickapply.CarIssuePackageCheckResponse;
import cn.com.taiji.css.model.apply.quickapply.ObuApplyAndConfirmRequest;
import cn.com.taiji.css.model.apply.quickapply.ObuApplyAndConfirmResponse;
import cn.com.taiji.css.model.apply.quickapply.PayIssuePackageRequest;
import cn.com.taiji.css.model.apply.quickapply.SaveAndPayIssuePackageResponse;
import cn.com.taiji.css.model.apply.quickapply.SaveIssuePackageRequest;
import cn.com.taiji.css.model.apply.quickapply.VehiclePlateOnlyCheckRequest;
import cn.com.taiji.css.model.apply.quickapply.VehiclePlateOnlyCheckResponse;
import cn.com.taiji.css.model.customerservice.card.ApplyCardRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.Agency;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.IssuePackageInfo;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.entity.VehicleInfo;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;
import cn.com.taiji.qtk.entity.dict.VehicleType;
import cn.com.taiji.qtk.entity.dict.VehicleTypeSimple;
import cn.com.taiji.qtk.entity.dict.VehicleUseCharacter;

@Controller
@RequestMapping("/apply/quickapply/equipmentissue")
public class EquipmentIssueController extends MyLogController{
	   	
		@Autowired
		private EquipmentIssueManager equipmentIssueManager;
		@Autowired
		private CustomerManager customerManager;
		@Autowired
		private QuickApplyManager quickApplyManager;
		@Autowired
		private SupplyManager supplyManager;
		@Autowired
		private VehicleManagementManager vehicleManagementManager;
		@Autowired
		private AccountManager accountManager;
		@Autowired
		private IssueManager issueManager;
		@Autowired
		private CardManager cardManager;
		@Autowired
		private ObuManager obuManager;
		
		private final String prefix = "/apply/quickapply/equipmentissue/";
		
		@RequestMapping(value = "/manage", method = RequestMethod.GET)
		public String manageGet(@ModelAttribute("queryModel") VehicleManagementRequest queryModel, Model model,HttpServletRequest req)
		{
		    model.addAttribute("customerIDTypes", CustomerIDType.values());
		    model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
			return prefix + "manage";
		}
	   
	     
	   @RequestMapping(value = "/manage", method = RequestMethod.POST)
		public String managePost(@Valid @ModelAttribute("queryModel")VehicleManagementRequest queryModel, Model model ,HttpServletRequest req)
		{
//		   queryModel.setAgencyId(LoginHelper.getLoginUser(req).getStaff().getServiceHall().getAgencyId());
		   model.addAttribute("vehicleTypes", VehicleType.values());
		   model.addAttribute("customerIDTypes", CustomerIDType.values());
		   model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		   model.addAttribute("pagn", this.vehicleManagementManager.queryPage(queryModel));
		   return prefix + "queryResult";
		}
	   
	   @RequestMapping(value = "/issuePackage/{id}", method = RequestMethod.GET)
		public String setupIssuePackage(@PathVariable("id") String id,HttpServletRequest request, Model model) throws ManagerException
		{
		   	VehicleInfo vehicleInfo = vehicleManagementManager.findById(id);
		   	equipmentIssueManager.VehicleInfoCheck(vehicleInfo);//车辆信息校验
		   	CustomerInfo customerInfo = customerManager.findByCustomerId(vehicleInfo.getCustomerId());
		   	equipmentIssueManager.CustomerInfoCheck(customerInfo);//用户信息校验
		   	equipmentIssueManager.ExistingIssuepackageCheck(LoginHelper.getLoginUser(request), vehicleInfo.getVehicleId());//刷pos机记录，半条校验
			model.addAttribute("vehicleInfo", vehicleInfo);
			model.addAttribute("customerInfo", customerInfo);
			model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
			model.addAttribute("vehicleUseCharacters", VehicleUseCharacter.values());
			model.addAttribute("vehicleTypeSimples",VehicleTypeSimple.values());
			model.addAttribute("vehicleTypes", VehicleType.values());
			model.addAttribute("customerIDTypes", CustomerIDType.values());
			//获取发行套餐数据
			List<IssuePackageInfo> list = issueManager.findByServiceHallId(LoginHelper.getLoginUser(request).getStaff().getServiceHall().getServiceHallId(),vehicleInfo.getType());
			model.addAttribute("issueManagers", list);
			
			model.addAttribute("serviceHallId", LoginHelper.getLoginUser(request).getStaff().getServiceHall().getServiceHallId());
			return prefix + "issuePackage";
		}
		
	   @RequestMapping(value = "/openCard/{id}", method = RequestMethod.GET)
		public String setupOpenCard(@PathVariable("id") String id,HttpServletRequest request, Model model) throws ManagerException
		{
		    
		   	User user = LoginHelper.getLoginUser(request);
		    String agencyId = user.getStaff().getServiceHall().getAgencyId();//获取登录user的机构编号id
		   	VehicleInfo vehicleInfo = vehicleManagementManager.findById(id);
		   	VehiclePlateOnlyCheckResponse vehiclePlateOnlyCheck = equipmentIssueManager.vehiclePlateOnlyCheck(1,vehicleInfo,user); //车牌卡唯一性校验
		   	if(!vehiclePlateOnlyCheck.getSuccess()) throw new ManagerException(vehiclePlateOnlyCheck.getMessage());
		   	CarIssuePackageCheckResponse carIssuePackageCheck = equipmentIssueManager.carIssuePackageCheck(LoginHelper.getLoginUser(request), new CarIssuePackageCheckRequest(vehicleInfo.getVehicleId(), 1));
		   	if(!carIssuePackageCheck.getSuccess()) throw new ManagerException(carIssuePackageCheck.getMessage());//发行套餐校验
		   	LocalDateTime now = LocalDateTime.now();
			String enableTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String expireTime = now.plusYears(10).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			ApplyCardRequest applyCardRequest = new ApplyCardRequest();
			Agency agency = quickApplyManager.findByAgencyId(agencyId);//根据机构编号id查找发行方机构信息
			applyCardRequest.setPackageNo(agency.getAccountNo()==null? "0":agency.getAccountNo());//记账机构编号为空  将请求实体记账机构编号设置为"0" 否则取原值
			model.addAttribute("vehicleInfo", vehicleInfo);
			model.addAttribute("customerInfo", customerManager.findByCustomerId(vehicleInfo.getCustomerId()));
			model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
			model.addAttribute("vehicleUseCharacters", VehicleUseCharacter.values());
			model.addAttribute("vehicleTypeSimples",VehicleTypeSimple.values());
			model.addAttribute("vehicleTypes", VehicleType.values());
			model.addAttribute("pageModel", applyCardRequest);
			model.addAttribute("customerIDTypes", CustomerIDType.values());
			model.addAttribute("startT", enableTime);
			model.addAttribute("endT", expireTime);
			//获取记账卡套餐数据
			model.addAttribute("accountPackages", accountManager.findByServiceHallId(user.getStaff().getServiceHall().getServiceHallId()));
			
			return prefix + "openCard";
		}
		
		@RequestMapping(value = "/cardApplyAndConfirm", method = RequestMethod.POST)
		public void cardApplyAndConfirm(@Valid @RequestBody ApplyCardRequest appReq,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException {
			
			logger.info("------"+appReq.toJson());
			AppCardStatusChangeResponse cardApplyAndConfirm = supplyManager.cardApplyAndConfirm(appReq,LoginHelper.getLoginUser(request));
			if(cardApplyAndConfirm.getCardId() != null && cardApplyAndConfirm.getSuccess()) {
				CardInfo cardInfo = cardManager.findByCardId(cardApplyAndConfirm.getCardId());
				super.doAddLog(request, CssServiceLogType.APPLY_QUICKAPPLY_EQUIPMENTISSUE_CARD, cardInfo);
			}
			try {
				HttpMimeResponseHelper.responseJson(cardApplyAndConfirm.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ManagerException("返回数据失败");
			}
			
		}
		
		@RequestMapping(value = "/inventoryVerify", method = RequestMethod.POST)
		public void inventoryVerify(@Valid @RequestBody InventoryVerifyRequset req, HttpServletRequest request,Model model, HttpServletResponse response) throws ManagerException {
			
			CssInventoryCheckResponse inventoryVerify = equipmentIssueManager.inventoryVerify(req,LoginHelper.getLoginUser(request));
			try {
				HttpMimeResponseHelper.responseJson(inventoryVerify.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ManagerException("校验结果返回失败",e);
			}
		}
		
		@RequestMapping(value = "/openOBU/{id}", method = RequestMethod.GET)
		public String setupOpenOBU(@PathVariable("id") String id,HttpServletRequest request, Model model) throws ManagerException
		{
		   	User user = LoginHelper.getLoginUser(request);
		   	VehicleInfo vehicleInfo = vehicleManagementManager.findById(id);
		   	VehiclePlateOnlyCheckResponse vehiclePlateOnlyCheck = equipmentIssueManager.vehiclePlateOnlyCheck(2,vehicleInfo,user);//校验OBU唯一性
		   	if(!vehiclePlateOnlyCheck.getSuccess()) throw new ManagerException(vehiclePlateOnlyCheck.getMessage());
		   	CarIssuePackageCheckResponse carIssuePackageCheck = equipmentIssueManager.carIssuePackageCheck(LoginHelper.getLoginUser(request), new CarIssuePackageCheckRequest(vehicleInfo.getVehicleId(), 2));
		   	if(!carIssuePackageCheck.getSuccess()) throw new ManagerException(carIssuePackageCheck.getMessage());//发行套餐校验
		   	LocalDateTime now = LocalDateTime.now();
			String enableTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String expireTime = now.plusYears(10).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			ObuApplyAndConfirmRequest obuRequest = new ObuApplyAndConfirmRequest();
			obuRequest.setEnableTime(enableTime);
			obuRequest.setExpireTime(expireTime);
			model.addAttribute("vehicleInfo", vehicleInfo);
			model.addAttribute("customerInfo", customerManager.findByCustomerId(vehicleInfo.getCustomerId()));
			model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
			model.addAttribute("vehicleUseCharacters", VehicleUseCharacter.values());
			model.addAttribute("vehicleTypeSimples",VehicleTypeSimple.values());
			model.addAttribute("vehicleTypes", VehicleType.values());
			model.addAttribute("customerIDTypes", CustomerIDType.values());
			model.addAttribute("pageModel", obuRequest);		
			return prefix + "openOBU";
		}
		
		@RequestMapping(value = "/obuApplyAndConfirm", method = RequestMethod.POST)
		public void obuApplyAndConfirm(@Valid @RequestBody ObuApplyAndConfirmRequest appReq,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException {
			
			logger.info("------"+appReq.toJson());
			ObuApplyAndConfirmResponse obuApplyAndConfirm = equipmentIssueManager.obuApplyAndConfirm(appReq,LoginHelper.getLoginUser(request));
			if(appReq.getType() == 2 && obuApplyAndConfirm.getSuccess()) {
				OBUInfo obuInfo = obuManager.findByObuId(obuApplyAndConfirm.getObuId());
				super.doAddLog(request, CssServiceLogType.APPLY_QUICKAPPLY_EQUIPMENTISSUE_OBU, obuInfo);
			}
			try {
				HttpMimeResponseHelper.responseJson(obuApplyAndConfirm.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ManagerException("返回数据失败");
			}
			
		}
		
		@RequestMapping(value = "/saveCarIssuePackage", method = RequestMethod.POST)
		public void saveCarIssuePackage(@Valid @RequestBody SaveIssuePackageRequest appReq,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException {
			
			logger.info("------"+appReq.toJson());
			SaveAndPayIssuePackageResponse saveCarIssuePackage = equipmentIssueManager.saveCarIssuePackage(appReq, LoginHelper.getLoginUser(request));
			if(saveCarIssuePackage.getSuccess()) {
				super.doAddLog(request, CssServiceLogType.APPLY_QUICKAPPLY_EQUIPMENTISSUE_ISSUEPACKAGESELECT, saveCarIssuePackage.getCarIssuePackageInfo());
			}
			try {
				if(saveCarIssuePackage.getCarIssuePackageInfo() != null)
					saveCarIssuePackage.getCarIssuePackageInfo().setCreateTime(null);
				HttpMimeResponseHelper.responseJson(saveCarIssuePackage.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ManagerException("返回数据失败");
			}
			
		}
		
		@RequestMapping(value = "/payCarIssuePackage", method = RequestMethod.POST)
		public void payCarIssuePackage(@Valid @RequestBody PayIssuePackageRequest appReq,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException {
			
			logger.info("------"+appReq.toJson());
			SaveAndPayIssuePackageResponse payCarIssuePackage = equipmentIssueManager.payCarIssuePackage(LoginHelper.getLoginUser(request), appReq);
			if(payCarIssuePackage.getSuccess()) {
				super.doUpdateLog(request, CssServiceLogType.APPLY_QUICKAPPLY_EQUIPMENTISSUE_ISSUEPACKAGEPAY, payCarIssuePackage.getCarIssuePackageInfo());
//				super.doAddLog(request, CssServiceLogType.APPLY_QUICKAPPLY_EQUIPMENTISSUE_ISSUEPACKAGEPAY, payCarIssuePackage.getIssuePackage());
			}
			try {
				HttpMimeResponseHelper.responseJson(payCarIssuePackage.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ManagerException("返回数据失败");
			}
			
		}
		
		@RequestMapping(value = "/moneyPlayType/{packageNum}", method = RequestMethod.POST)
		public void totalMoney(@PathVariable("packageNum") String packageNum, HttpServletRequest request,Model model, HttpServletResponse response) throws ManagerException {
			
			PackageTotalMoneyResponse totalMoney = equipmentIssueManager.moneyPlayType(equipmentIssueManager.findByPackageNum(packageNum));
			try {
				HttpMimeResponseHelper.responseJson(totalMoney.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ManagerException("套餐金额及支付方式返回失败",e);
			}
		}
		
		@RequestMapping(value = "/carIssuePackageCheck", method = RequestMethod.POST)
		public void carIssuePackageCheck(@Valid @RequestBody CarIssuePackageCheckRequest req, HttpServletRequest request,Model model, HttpServletResponse response) throws ManagerException {
			
			AppAjaxResponse checkResponse = equipmentIssueManager.carIssuePackageCheck(LoginHelper.getLoginUser(request), req);
			try {
				HttpMimeResponseHelper.responseJson(checkResponse.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ManagerException("校验结果返回失败",e);
			}
		}
		
		/**
		 * 车牌唯一性校验
		 * @param req
		 * @param request
		 * @param model
		 * @param response
		 * @throws ManagerException
		 */
		@RequestMapping(value = "/vehiclePlateOnlyCheck", method = RequestMethod.POST)
		public void vehiclePlateOnlyCheck(@Valid @RequestBody VehiclePlateOnlyCheckRequest req, HttpServletRequest request,Model model, HttpServletResponse response) throws ManagerException {
			VehiclePlateOnlyCheckResponse vehiclePlateOnlyCheck = equipmentIssueManager.vehiclePlateOnlyCheck(req.getType(), vehicleManagementManager.findByVehicleId(req.getVehicleId()), LoginHelper.getLoginUser(request));
			try {
				HttpMimeResponseHelper.responseJson(vehiclePlateOnlyCheck.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ManagerException("校验结果返回失败",e);
			}
		}
		
		@RequestMapping(value = "/returnReceipt/{id}", method = RequestMethod.GET)
		public String returnReceipt(@PathVariable("id") String id, HttpServletRequest request,Model model, HttpServletResponse response) throws ManagerException {
			try {
				model.addAttribute("title", "发行业务回执单");
				List<List<String>> list = equipmentIssueManager.returnReceipt(id, LoginHelper.getLoginUser(request));
				model.addAttribute("customerInfo", list.get(0));
				model.addAttribute("vehicleInfo", list.get(1));
				model.addAttribute("issuePackage", list.get(2));
				model.addAttribute("issue", list.get(3));
				model.addAttribute("footer", list.get(4));
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMessage", e.getMessage());
			}
			return prefix + "printPage";
		}
}
