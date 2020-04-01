package cn.com.taiji.css.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.finals.SessionNames;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.css.model.MyFinals;

/**
 * @author Peream<br>
 *         邮箱：peream@gmail.com<br>
 *         创建日期：2008-3-28 下午04:56:33
 * @since 1.0
 * @version 1.0
 */
@Controller
public class AclController extends MyBaseController
{
	@RequestMapping("/common/acl/noright")
	public String noRight(@RequestParam("method") RequestMethod method, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		String reason = (String) WebUtils.getSessionAttribute(request, SessionNames.NORIGHT_ERROR);
		if (!StringTools.hasText(reason)) reason = "原因未知";
		if (method == RequestMethod.POST) throw new ManagerException(reason);
		model.addAttribute("reason", reason);
		logger.debug("没有权限,{}", reason);
		return "acl/noright";
	}

	@RequestMapping("/common/ssoerror")
	public String ssoError(HttpServletRequest request, Model model)
	{
		return "acl/ssoerror";
	}

	@RequestMapping("/common/localerror")
	public String localError(@RequestParam(value = "reason", required = false) String reason, Model model)
	{
		if (hasText(reason)) model.addAttribute("reason", reason);
		return "acl/localerror";
	}

	@RequestMapping("/common/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response)
	{
		request.getSession().invalidate();
		WebUtils.setSessionAttribute(request, SessionNames.LOGIN_USER, null);
		Cookie cookie = new Cookie(MyFinals.COOKIE_PASS, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:/app/index?myMenuId=index";
	}

}
