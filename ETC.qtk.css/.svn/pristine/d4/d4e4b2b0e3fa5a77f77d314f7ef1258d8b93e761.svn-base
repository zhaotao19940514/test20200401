package cn.com.taiji.css.web.system;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.manager.SystemInfoManager;
import cn.com.taiji.css.model.SystemPara;
import cn.com.taiji.css.web.BaseLogController;

@Controller
@RequestMapping("/system/para")
public class SystemParaController extends BaseLogController
{
	private final String prefix = "system/para/";
	@Autowired
	private SystemInfoManager  infoManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(Model model)
	{
		SystemPara para = infoManager.getSystemPara();
		model.addAttribute("para", para);
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public void managePost(@Valid SystemPara para, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ManagerException
	{
		infoManager.updateSystemPara(para);
		this.addSysLog(request, "页眉:{}",para.getPrintHead());
		addSuccess(response, "修改成功！");
		response.getWriter().print("ok");
	}

	
}
