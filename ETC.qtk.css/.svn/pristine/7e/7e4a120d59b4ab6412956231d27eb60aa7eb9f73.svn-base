/* Copyright  2016 TaiJi Computer Corporation Limited
 * All rights reserved.
 * 
 * Date: 2016年5月25日
 * author: luhj  (luhj@mail.taiji.com.cn)
 *
 */
package cn.com.taiji.css.web.oauth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.taiji.common.model.finals.SessionNames;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.web.util.WebTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.acl.RoleResourceManager;
import cn.com.taiji.css.manager.oauth.OAuthLoginManager;
import cn.com.taiji.css.web.BaseLogController;
import cn.com.taiji.sso.model.comm.protocol.client.SsoCodeResponse;

@Controller
public class OAuthLoginController extends BaseLogController
{
	@Autowired
	private OAuthLoginManager oauthLoginManager;
	@Autowired
	private RoleResourceManager rrManager;
	@RequestMapping(value = "/common/oauth/login")
	public String oauthLogin(HttpServletRequest request, SsoCodeResponse codeResponse, Model model)
	{
		User user;
		try
		{
			user = oauthLoginManager.oauthLogin(request, codeResponse);
		}
		catch (IOException e)
		{
			model.addAttribute("errorMsg", "第三方登录请求失败");
			return "welcome";
		}
		if (user == null)
		{
			model.addAttribute("errorMsg", "第三方登录请求失败");
			return "welcome";
		}
		LoginHelper.setSession(request, user, rrManager.getRoleMenu(user.getRole().getId()));
		logger.info("用户通过第三方登录界面登录成功:{}", user);
		super.addLoginLog(request, "用户({})登录成功.", user.getLoginName());
		String uri = (String) WebTools.getSessionAttribute(request, SessionNames.LAST_URI);
		if (!StringTools.hasText(uri) || uri.startsWith("/app/common/welcome"))
			return "redirect:/app/index?myMenuId=index";
		WebTools.setSessionAttribute(request, SessionNames.LAST_URI, null);
		
		return "redirect:" + uri;
	}
}
