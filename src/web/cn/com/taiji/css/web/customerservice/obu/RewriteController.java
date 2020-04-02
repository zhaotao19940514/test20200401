/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
package cn.com.taiji.css.web.customerservice.obu;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.dict.AccountStatus;
import cn.com.taiji.css.entity.dict.AppAjaxResponseCode;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.apply.quickapply.EquipmentIssueManager;
import cn.com.taiji.css.manager.customerservice.obu.ExchangeManager;
import cn.com.taiji.css.manager.customerservice.obu.RewriteManager;
import cn.com.taiji.css.model.apply.quickapply.VehiclePlateOnlyCheckResponse;
import cn.com.taiji.css.model.customerservice.obu.RewriteRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUInfoChangeResponse;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeResponse;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.entity.VehicleInfo;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.ObuUploadStatus;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;

/**
 * @ClassName RechargeController
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:14:54
 * @E_mail yaonanlin@163.com
 */
@Controller
@RequestMapping("/customerservice/obu/rewrite")
public class RewriteController extends MyLogController {
	private final String prefix = "customerservice/obu/rewrite/";
	
	@Autowired
	private RewriteManager rewriteManager;
	@Autowired
	private EquipmentIssueManager equipmentIssueManager;
	@Autowired
	private ExchangeManager exchangeManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") RewriteRequest queryModel, Model model)
	{
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("status", AccountStatus.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") RewriteRequest queryModel, Model model,HttpServletRequest request) throws ManagerException
	{
		LargePagination<OBUInfo> pagn = rewriteManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("CustomerIDType",CustomerIDType.values());
		model.addAttribute("ObuUploadStatus",ObuUploadStatus.values());
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	@RequestMapping(value = "/edit/{obuId}", method = RequestMethod.GET)
	public String editGet(@PathVariable("obuId") String obuId,@ModelAttribute("veQueryModel") RewriteRequest queryModel, Model model) throws ManagerException {
		
		OBUInfo obuInfo = rewriteManager.findById(obuId);
		if(null!=obuInfo&&null!=obuInfo.getVehicle()) {
			exchangeManager.VehicleInfoCheck(obuInfo.getVehicle());
		}
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("vehicleId",obuInfo.getVehicleId() );
		model.addAttribute("oldObuId",obuId);
		return prefix+"edit";
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public void editPost(@RequestBody  RewriteRequest queryModel,HttpServletResponse response,HttpServletRequest request, Model model) throws IOException, ManagerException 
	{
		OBUInfo obuInfo = exchangeManager.findById(queryModel.getObuId());
		if(null==obuInfo.getVehicle()) {
			throw new ManagerException("数据验证失败:该OBU未绑定车辆信息");
		}
		//车辆信息校验
		exchangeManager.VehicleInfoCheck(obuInfo.getVehicle());
		OBUInfoChangeResponse veRes = rewriteManager.reWriteVehicleInfo(queryModel,LoginHelper.getLoginUser(request));
		HttpMimeResponseHelper.responseJson(veRes.toJson(), response);
	}
	
	@RequestMapping(value = "/VehiclePlateOnlyCheck", method = RequestMethod.POST)
	public void VehiclePlateOnlyCheck(@RequestBody  RewriteRequest queryModel,HttpServletResponse response,HttpServletRequest request, Model model) throws IOException, ManagerException 
	{
		VehiclePlateOnlyCheckResponse vehiclePlateOnlyCheck = new VehiclePlateOnlyCheckResponse();
		VehicleInfo pagnVe = rewriteManager.queryVeInfo(queryModel);
		if(null==pagnVe) {
			vehiclePlateOnlyCheck.setCode(AppAjaxResponseCode.FAILED);
			vehiclePlateOnlyCheck.setMessage("未查到该车辆");
		}else {
			vehiclePlateOnlyCheck = equipmentIssueManager.vehiclePlateOnlyCheck(2,pagnVe,LoginHelper.getLoginUser(request));//校验OBU唯一性	
		}
		try {
			HttpMimeResponseHelper.responseJson(vehiclePlateOnlyCheck.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/doOUBRewrite", method = RequestMethod.POST)
	public void doOUBRewrite(@RequestBody RewriteRequest queryModel, Model model,HttpServletRequest request,HttpServletResponse response) throws ManagerException {
		rewriteManager.doOBURewrite(queryModel,LoginHelper.getLoginUser(request));
		
	}
	
	@RequestMapping(value = "/vehicleInfoCheck", method = RequestMethod.POST)
	public void vehicleInfoCheck(@RequestBody RewriteRequest queryModel, Model model,HttpServletRequest request,HttpServletResponse response) throws ManagerException {
		OBUStatusChangeResponse obuRes = rewriteManager.vehicleInfoCheck(queryModel);
		try {
			HttpMimeResponseHelper.responseJson(obuRes.toJson(), response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	@RequestMapping(value = "/vehInfoByObuId", method = RequestMethod.POST)
	public void vehInfoByObuId(@RequestBody RewriteRequest queryModel, Model model,HttpServletRequest request,HttpServletResponse response) throws ManagerException {
		OBUInfo obuInfo = rewriteManager.findById(queryModel.getObuId());
		queryModel.setVehicleId(obuInfo.getVehicleId());
		VehicleInfo vehicleInfo = rewriteManager.vehInfoByObuId(queryModel,LoginHelper.getLoginUser(request));
		super.doAddLog(request, CssServiceLogType.CUSTOMERSERVICE_OBU_REWRITE, obuInfo);
		try {
			HttpMimeResponseHelper.responseJson(vehicleInfo.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

