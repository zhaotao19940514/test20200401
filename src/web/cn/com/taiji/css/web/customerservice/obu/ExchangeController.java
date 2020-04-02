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
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.css.entity.dict.AccountStatus;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.obu.ExchangeManager;
import cn.com.taiji.css.model.customerservice.obu.ExchangeRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUStatusChangeResponse;
import cn.com.taiji.qtk.entity.OBUInfo;
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
@RequestMapping("/customerservice/obu/exchange")
public class ExchangeController extends MyLogController {
	private final String prefix = "customerservice/obu/exchange/";
	
	@Autowired
	private ExchangeManager exchangeManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") ExchangeRequest queryModel, Model model)
	{
		model.addAttribute("status", AccountStatus.values());
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") ExchangeRequest queryModel, Model model,HttpServletRequest request) throws ManagerException
	{
		
		LargePagination<OBUInfo> pagn = exchangeManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("CustomerIDType",CustomerIDType.values());
		model.addAttribute("ObuUploadStatus",ObuUploadStatus.values());
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String oldObuId,@ModelAttribute("pageModel") ExchangeRequest queryModel, HttpServletRequest request, Model model) throws ManagerException
	{
		OBUInfo obuInfo = exchangeManager.findById(oldObuId);
		if(null==obuInfo.getVehicle()) {
			throw new ManagerException("数据验证失败:该OBU未绑定车辆信息");
		}
		//车辆信息校验
		exchangeManager.VehicleInfoCheck(obuInfo.getVehicle());
		
		model.addAttribute("vehiclePlate", obuInfo.getVehicle().getVehiclePlate());
		model.addAttribute("vehColor", obuInfo.getVehicle().getVehiclePlateColor());
		model.addAttribute("oldObuId", oldObuId);
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String porcessEdit(@Valid @ModelAttribute("pageModel") ExchangeRequest queryModel, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		
		return prefix + "result";
	}
	@RequestMapping(value = "/OBUExchange", method = RequestMethod.POST)
	public void doOBUExchange( @RequestBody ExchangeRequest queryModel, Model model,HttpServletRequest request,HttpServletResponse response) throws ManagerException {
		OBUStatusChangeResponse obuRes= exchangeManager.doOBUExchange(queryModel,LoginHelper.getLoginUser(request));
		if(obuRes.getStatus()==1) {
			OBUInfo obuInfo = exchangeManager.findById(queryModel.getOldObuId());
			super.doAddLog(request, CssServiceLogType.CUSTOMERSERVICE_OBU_EXCHANGE, obuInfo);
		}
		try {
			HttpMimeResponseHelper.responseJson(obuRes.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/obuRefund", method = RequestMethod.POST)
	public void obuRefund(@RequestParam("newObuId") String newObuId, HttpServletRequest request,HttpServletResponse response) {
		long refund = exchangeManager.obuRefund(LoginHelper.getLoginUser(request),newObuId);
		String json;
		json = JsonTools.toJsonStr(refund);
		try {
			HttpMimeResponseHelper.responseJson(json, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

