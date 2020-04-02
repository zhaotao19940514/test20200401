/**
 * @Title ObuCommandController.java
 * @Package cn.com.taiji.css.web.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月4日 下午6:28:21
 * @version V1.0
 */
package cn.com.taiji.css.web.ocx;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.ocx.ObuCommandManager;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.ocx.ObuOfflineSysInfoCommandRequest;
import cn.com.taiji.css.model.ocx.ObuOfflineVehicleInfoCommandRequest;
import cn.com.taiji.css.web.MyLogController;

/**
 * @ClassName ObuCommandController
 * @Description TODO
 * @author yaonl
 * @date 2018年08月04日 18:28:21
 * @E_mail yaonanlin@163.com
 */
@Controller
@RequestMapping("/ocx/obu")
public class ObuCommandController extends MyLogController {
	@Autowired
	private ObuCommandManager obuCommandManager;
	
	@RequestMapping(value="/sysinfo",method=RequestMethod.POST)
	public void getObuSystemInfoCommand(@RequestBody ObuOfflineSysInfoCommandRequest req,HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		AppAjaxResponse res = obuCommandManager.generateCommand(req, LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(res.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("返回数据失败",e);
		}
	}
	@RequestMapping(value="/vehicleinfo",method=RequestMethod.POST)
	public void getObuVehicleInfoCommand(@RequestBody ObuOfflineVehicleInfoCommandRequest req,HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		AppAjaxResponse res = obuCommandManager.generateCommand(req, LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(res.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("返回数据失败",e);
		}
	}
}

