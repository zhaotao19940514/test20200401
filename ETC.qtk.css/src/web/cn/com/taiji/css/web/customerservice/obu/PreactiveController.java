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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.dict.AccountStatus;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.obu.PreactiveManager;
import cn.com.taiji.css.model.customerservice.obu.PreactiveRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.dsi.model.comm.protocol.releases.InstallApplyResponse;
import cn.com.taiji.qtk.entity.OBUInfo;

/**
 * @ClassName RechargeController
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:14:54
 * @E_mail yaonanlin@163.com
 */
@Controller
@RequestMapping("/customerservice/obu/preactive")
public class PreactiveController extends MyLogController {
	private final String prefix = "customerservice/obu/preactive/";
	
	@Autowired
	private PreactiveManager preactiveManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") PreactiveRequest queryModel, Model model)
	{
		model.addAttribute("status", AccountStatus.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") PreactiveRequest queryModel, Model model)
	{
		Pagination pagn = preactiveManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/doPreactive", method = RequestMethod.POST)
	public void doPreactive(@RequestBody PreactiveRequest queryModel,HttpServletRequest request,HttpServletResponse response) throws ManagerException {
		
		try {
			InstallApplyResponse obuRes = preactiveManager.doPreactive(queryModel.getObuId(),LoginHelper.getLoginUser(request));
			OBUInfo obuInfo =  preactiveManager.queryObuInfo(queryModel.getObuId());
			if(null!=obuInfo) {
				super.doAddLog(request, CssServiceLogType.CUSTOMERSERVICE_OBU_PREACTIVE, obuInfo);
			}
			HttpMimeResponseHelper.responseJson(obuRes.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("电子标签预激活失败,请联系管理员。");
		}
		
	}
}

