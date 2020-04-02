package cn.com.taiji.css.web.customerservice.card;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.ServiceHandleException;
import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.issuetranscation.PerCancelManager;
import cn.com.taiji.css.model.acl.ZTreeItem;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.PerCancel;

@Controller
@RequestMapping("/customerservice/card/perCancel")
public class PerCancelController extends MyLogController {
	private final String prefix = "perCancel/";
	
	@Autowired
	private PerCancelManager perCancelManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(Model model)
	{
		return prefix+"manage";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String setupEdit(HttpServletRequest request, Model model) throws ServiceHandleException, IOException
	{   
		List<ZTreeItem> list = perCancelManager.getCurrentConf();
		
		String json = JsonTools.toJsonStr(list);
		System.out.println(json);
		model.addAttribute("json", json);
		model.addAttribute("pageModel", perCancelManager.findAll());
		return prefix + "edit";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(@Valid @ModelAttribute("pageModel") PerCancel perCancel, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException, ManagerException
	{   
		User user = LoginHelper.getLoginUser(request);
		PerCancel po = perCancelManager.update(perCancel,user.getName());;
		addSuccess(response, "配置成功");
		model.addAttribute("vo", po);
		super.doUpdateLog(request, CssServiceLogType.ADMINISTRATION_PERCANCEL, po);
		return prefix + "manage";
		
	}
	
}
