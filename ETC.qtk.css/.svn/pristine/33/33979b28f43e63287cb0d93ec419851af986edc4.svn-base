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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.obu.OBUUnLossManager;
import cn.com.taiji.css.model.customerservice.obu.OBUUnLossRequest;
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
@RequestMapping("/customerservice/obu/unloss")
public class OBUUnLossController extends MyLogController {
	private final String prefix = "customerservice/obu/unloss/";
	
	@Autowired
	private OBUUnLossManager obuunlossManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") OBUUnLossRequest queryModel, Model model)
	{
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") OBUUnLossRequest queryModel, Model model,HttpServletRequest request) throws ManagerException
	{
		LargePagination<OBUInfo> pagn = obuunlossManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("CustomerIDType",CustomerIDType.values());
		model.addAttribute("ObuUploadStatus",ObuUploadStatus.values());
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	@RequestMapping(value = "/unlossOBU", method = RequestMethod.POST)
	public void unlossOBU(@Valid @RequestBody OBUUnLossRequest queryModel,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException {
		
		OBUStatusChangeResponse obuLossRes;
		try {
			obuLossRes = obuunlossManager.unLossOBU(queryModel,LoginHelper.getLoginUser(request));
			OBUInfo obuInfo = obuunlossManager.findById(queryModel.getObuId());
			super.doAddLog(request, CssServiceLogType.CUSTOMERSERVICE_OBU_UNLOSS, obuInfo);
			HttpMimeResponseHelper.responseJson(obuLossRes.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("OBU解挂失败，请联系管理员。");
		}
	}
	
	
}

