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

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.dict.AccountStatus;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.obu.OBUUnhangManager;
import cn.com.taiji.css.model.customerservice.obu.OBUHangRequest;
import cn.com.taiji.css.model.customerservice.obu.OBUUnhangRequest;
import cn.com.taiji.css.model.customerservice.obu.RewriteRequest;
import cn.com.taiji.css.web.BaseLogController;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.OBUInfoChangeResponse;
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
@RequestMapping("/customerservice/obu/unhang")
public class OBUUnhangController extends BaseLogController {
	private final String prefix = "customerservice/obu/unhang/";
	
	@Autowired
	private OBUUnhangManager unhangManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") OBUUnhangRequest queryModel, Model model)
	{
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("status", AccountStatus.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") OBUUnhangRequest queryModel, Model model,HttpServletRequest request) throws ManagerException
	{
		LargePagination<OBUInfo> pagn = unhangManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("pagn", pagn);
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("CustomerIDType",CustomerIDType.values());
		model.addAttribute("ObuUploadStatus",ObuUploadStatus.values());
		return prefix+"queryResult";
	}
	@RequestMapping(value = "/edit/{obuId}", method = RequestMethod.GET)
	public String editGet(@PathVariable("obuId") String obuId,@ModelAttribute("veQueryModel") RewriteRequest queryModel, Model model) {
		
		OBUInfo obuInfo = unhangManager.findById(obuId);
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("vehicleId",obuInfo.getVehicleId() );
		model.addAttribute("oldObuId",obuId);
		return prefix+"edit";
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public void porcessEdit(@RequestBody RewriteRequest queryModel, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		OBUInfoChangeResponse veRes = unhangManager.reWriteVehicleInfo(queryModel,LoginHelper.getLoginUser(request));
		OBUStatusChangeResponse res = null;
		if(veRes.getStatus()==1) {
			res = unhangManager.updateObuStatus(queryModel,LoginHelper.getLoginUser(request));
		}
		try {
			HttpMimeResponseHelper.responseJson(res.toJson(), response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/doUnHangObu", method = RequestMethod.POST)
	public void doUnHangObu(@Valid @RequestBody OBUHangRequest queryModel,HttpServletResponse response,HttpServletRequest request,Model model) {
		unhangManager.doUnHangObu(queryModel,LoginHelper.getLoginUser(request));
	}
	
}

