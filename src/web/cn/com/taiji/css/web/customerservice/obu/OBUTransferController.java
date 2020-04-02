/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
package cn.com.taiji.css.web.customerservice.obu;

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
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.obu.OBUTransferManager;
import cn.com.taiji.css.manager.customerservice.obu.OfflineinstallManager;
import cn.com.taiji.css.model.customerservice.obu.OBUTransferRequest;
import cn.com.taiji.css.model.customerservice.obu.OBUTransferResponse;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.entity.VehicleInfo;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;
import cn.com.taiji.qtk.entity.dict.VehicleType;
import cn.com.taiji.qtk.entity.dict.VehicleTypeSimple;

/**
 * @ClassName RechargeController
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:14:54
 * @E_mail yaonanlin@163.com
 */
@Controller
@RequestMapping("/customerservice/obu/transfer")
public class OBUTransferController extends MyLogController {
	private final String prefix = "customerservice/obu/transfer/";
	
	@Autowired
	private OBUTransferManager obutransferManager;
	@Autowired
	private  OfflineinstallManager offlineinstallManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") OBUTransferRequest queryModel, Model model)
	{
		model.addAttribute("customerIDTypes", CustomerIDType.values());
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") OBUTransferRequest queryModel, Model model,HttpServletRequest request) throws ManagerException
	{
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		model.addAttribute("customerIDTypes", CustomerIDType.values());
		model.addAttribute("vehicleTypes", VehicleType.values());
		LargePagination<VehicleInfo> pagn = obutransferManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		/*if(null!=pagn&&pagn.getResult().size()!=0) {
			model.addAttribute("obuBrand",obutransferManager.findBrandById(pagn.getResult().get(0).getVehicleId()));
		}*/
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String vehicleId,@ModelAttribute("pageModel") OBUTransferRequest queryModel, HttpServletRequest request, Model model)
	{
		model.addAttribute("vehicleTypeSimples",VehicleTypeSimple.values());
		model.addAttribute("customerIDTypes", CustomerIDType.values());
		VehicleInfo vehicleInfo = obutransferManager.findByVehicleInfo(vehicleId);
		model.addAttribute("vehicleInfo",vehicleInfo);
		model.addAttribute("vehicleTypes", VehicleType.values());
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		model.addAttribute("customerInfo", obutransferManager.findByCustomerInfo(vehicleInfo.getCustomerId()));
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String porcessEdit(@Valid @ModelAttribute("pageModel") OBUTransferRequest queryModel, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		
		return prefix + "result";
	}
	@RequestMapping(value = "/infochange", method = RequestMethod.POST)
	public void infoChange(@Valid @RequestBody OBUTransferRequest queryModel, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		try {
			OBUTransferResponse obuInfoRes=obutransferManager.infoChange(queryModel,LoginHelper.getLoginUser(request));
			OBUInfo obuInfo = offlineinstallManager.queryObuInfo(queryModel.getObuId());
			super.doAddLog(request, CssServiceLogType.CUSTOMERSERVICE_OBU_TRANSFER, obuInfo);
			HttpMimeResponseHelper.responseJson(obuInfoRes.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("OBU过户失败，请联系管理员。");
		}
	}
	
	/*@RequestMapping(value = "/lossOBU", method = RequestMethod.POST)
	public void lossOBU(@Valid @RequestBody OBUTransferRequest queryModel,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException {
		
		OBUStatusChangeResponse obuLossRes;
		try {
			obuLossRes = obulossManager.lossOBU(queryModel,LoginHelper.getLoginUser(request));
			OBUInfo obuInfo = obulossManager.findById(queryModel.getObuId());
			super.doUpdateLog(request, CssServiceLogType.CUSTOMERSERVICE_CARD_LOSS, obuInfo);
			HttpMimeResponseHelper.responseJson(obuLossRes.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("OBU挂失失败，请联系管理员。");
		}
	}*/
	
}

