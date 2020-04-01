/**
 * @Title CardInfoResolveController.java
 * @Package cn.com.taiji.css.web.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年9月8日 下午5:06:13
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

import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.ocx.CardInfoResolveManager;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.ocx.request.CardInfoResolveAppRequest;
import cn.com.taiji.css.web.BaseLogController;

/**
 * @ClassName CardInfoResolveController
 * @Description TODO
 * @author yaonl
 * @date 2018年09月08日 17:06:13
 * @E_mail yaonanlin@163.com
 */
@Controller
@RequestMapping("/ocx/card/")
public class CardInfoResolveController extends BaseLogController {
	
	@Autowired
	private CardInfoResolveManager cardInfoResolveManager;
	
	@RequestMapping(value="cardinforesolve",method=RequestMethod.POST)
	public void resolve(@RequestBody CardInfoResolveAppRequest req,HttpServletRequest request,HttpServletResponse response){
		AppAjaxResponse ajaxResponse = cardInfoResolveManager.resolve(req,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(ajaxResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

