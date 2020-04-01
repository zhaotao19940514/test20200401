package cn.com.taiji.css.web.apply.quickapply;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.css.model.apply.quickapply.DeviceCheckRequest;
import cn.com.taiji.css.web.MyLogController;

@Controller
@RequestMapping("/apply/quickapply/device")
public class DeviceCheckController extends MyLogController{

    private final String prefix = "/apply/quickapply/device/";

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") DeviceCheckRequest queryModel, Model model,HttpServletRequest req)
	{
		return prefix + "manage";
	}

}
