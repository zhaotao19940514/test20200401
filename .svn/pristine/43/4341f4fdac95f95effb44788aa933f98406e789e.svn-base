package cn.com.taiji.css.web.acl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.acl.UserManager;
import cn.com.taiji.css.repo.request.acl.UserPageRequest;
import cn.com.taiji.css.web.BaseLogController;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-5-30 上午10:08:19<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Controller
@RequestMapping("/acl/userSelect")
public class UserSelectController extends BaseLogController
{
	@Autowired
	private UserManager userManager;
	private final String prefix = "acl/userSelect/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") UserPageRequest req)
	{
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") UserPageRequest req,HttpServletRequest request, Model model)
	{
		model.addAttribute("pagn", this.userManager.queryPage(req, LoginHelper.getLoginUser(request)));
		return prefix + "queryResult";
	}

}
