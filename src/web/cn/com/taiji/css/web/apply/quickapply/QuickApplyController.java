package cn.com.taiji.css.web.apply.quickapply;

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
import cn.com.taiji.common.manager.net.http.ServiceHandleException;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.apply.baseinfo.CustomerManager;
import cn.com.taiji.css.manager.apply.baseinfo.QuickApplyPngModel;
import cn.com.taiji.css.manager.apply.quickapply.QuickApplyManager;
import cn.com.taiji.css.model.apply.quickapply.VehicleInfoQuickQueryRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.dict.CarType;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.CustomerType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;
import cn.com.taiji.qtk.entity.dict.VehicleType;

@Controller
@RequestMapping("/apply/quickapply/quickopen")
public class QuickApplyController extends MyLogController {
	
	@Autowired
	private CustomerManager customerManager;
	@Autowired
	private QuickApplyManager quickApplyManager;
	
	private final String prefix = "/apply/quickapply/quickopen/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String setupAdd(@ModelAttribute("queryModel") VehicleInfoQuickQueryRequest req,@ModelAttribute("pageModel") CustomerInfo customerInfo, HttpServletRequest request,
			Model model) throws ServiceHandleException {
		model.addAttribute("customerType", CustomerType.values());
		model.addAttribute("idType", CustomerIDType.getPersonIDType());
		model.addAttribute("unitIdType", CustomerIDType.getUnitIDType());
		return prefix + "manage";
	}
	
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") VehicleInfoQuickQueryRequest req,HttpServletRequest request, Model model)
	{
		req.setAgencyId(LoginHelper.getLoginUser(request).getStaff().getServiceHall().getAgencyId());
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		model.addAttribute("vehicleTypes", VehicleType.values());
		model.addAttribute("carType", CarType.values());
		model.addAttribute("pagn", this.quickApplyManager.queryPage(req));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void processAdd(@Valid @ModelAttribute("pageModel") QuickApplyPngModel customerInfo, HttpServletRequest request,
			Model model, HttpServletResponse response) throws ManagerException {
		String customerId = customerManager.add(customerInfo,LoginHelper.getLoginUser(request));
		CustomerInfo cstm = customerManager.findByCustomerId(customerId);
		addSuccess(response, "开户成功");
		super.doAddLog(request, CssServiceLogType.APPLY_QUICKAPPLY_QUICKAPPLY, cstm);
		try {
			HttpMimeResponseHelper.responseJson(cstm.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("用户信息返回失败",e);
		}
	}

	@RequestMapping(value="/view/{customerId}",method=RequestMethod.GET)
	public String view(@PathVariable("customerId") String customerId,HttpServletRequest request,Model model) throws ManagerException{
//		model.addAttribute("customerIdType", CustomerIDType.values());
		CustomerInfo customer = customerManager.findByCustomerId(customerId);
		List<String> picBase64 = customerManager.getScreenShotBase64(customer);
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
}
