package cn.com.taiji.css.web.apply.quickapply;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.apply.baseinfo.CustomerManager;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.apply.quickapply.InfoCheckRequset;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.CustomerType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;
import cn.com.taiji.qtk.entity.dict.VehicleType;

@Controller
@RequestMapping("/apply/quickapply/infoCheck")
public class InfoCheckController extends MyLogController {
	
	@Autowired
	private CustomerManager customerManager;
	
    private final String prefix = "/apply/quickapply/infoCheck/";

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("pageModel") InfoCheckRequset pageModel, Model model,HttpServletRequest req)
	{
    	model.addAttribute("customerType", CustomerType.values());
		model.addAttribute("idType", CustomerIDType.getPersonIDType());
		model.addAttribute("unitIdType", CustomerIDType.getUnitIDType());
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		model.addAttribute("vehicleTypes", VehicleType.values());
		return prefix + "manage";
	}
    
    @RequestMapping(value = "/quickApplyCheck", method = RequestMethod.POST)
	public void quickApplyCheck(@Valid @RequestBody InfoCheckRequset req,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException {
		
		logger.info("------"+req.toJson());
		AppAjaxResponse checkResponse = customerManager.quickApplyCheck(req,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(checkResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("返回数据失败");
		}
		
	}
}
