package cn.com.taiji.css.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.NoteModel;
import cn.com.taiji.common.model.acl.UserModel;
import cn.com.taiji.common.model.finals.SessionNames;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.web.util.WebTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.User.UserStatus;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.acl.RoleResourceManager;
import cn.com.taiji.css.manager.acl.UserManager;
import cn.com.taiji.css.manager.oauth.OAuthLoginManager;
import cn.com.taiji.css.model.MyFinals;
import cn.com.taiji.css.model.MySessionNames;

/**
 * 
 * @author Peream <br>
 *         Create Time：2009-6-5 下午05:39:04<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Controller
public class WelcomeController extends BaseLogController
{
	@Autowired
	private UserManager userManager;
	@Autowired
	private RoleResourceManager rrManager;
	@Autowired
	private OAuthLoginManager oauthLoginManager;

	@RequestMapping(value = "/common/login",method=RequestMethod.POST)
	public void login(HttpServletRequest request,HttpServletResponse response,
			UserModel userModel,Model model) throws Exception
	{
		User loginUser=LoginValidator.validate(userModel, request, userManager);
		LoginHelper.setSession(request, loginUser, rrManager.getRoleMenu(loginUser.getRole().getId()));
		super.addLoginLog(request, "用户({})登录成功.", userModel.getUsername());
		logger.info("用户通过本地登录界面登录成功:{}", loginUser);
		String jumpUri = (String) WebTools.getSessionAttribute(request, SessionNames.LAST_URI);
		if (!StringTools.hasText(jumpUri) || jumpUri.startsWith("/app/common/welcome")){
			jumpUri=request.getContextPath() + "/app/index?myMenuId=index";
		}
		response.setHeader("taiji_jump", jumpUri);
		WebTools.setSessionAttribute(request, SessionNames.LAST_URI, null);
		response.getWriter().print(jumpUri);
	}
	
	
	@RequestMapping(value = "/common/welcome",method=RequestMethod.GET)
	public String setupForm(HttpServletRequest request, UserModel user, BindingResult result, SessionStatus status,
			Model model)
	{
//		logger.info("-----{}---", user.getUsername());
		model.addAttribute("oauthUrl", oauthLoginManager.getAuthLoginUrl());
		// 使用cookie自动登录
		boolean loginSucess = LoginHelper.hasLogin(request) || autoLogin(request);
		if (!loginSucess)return "welcome";
		super.addLoginLog(request, "用户({})自动登录成功.", user.getUsername());
		String uri = (String) WebTools.getSessionAttribute(request, SessionNames.LAST_URI);
		if (!StringTools.hasText(uri) || uri.startsWith("/app/common/welcome"))
			return "redirect:/app/index?myMenuId=index";
		WebTools.setSessionAttribute(request, SessionNames.LAST_URI, null);
		return "redirect:" + uri;
	}

	private boolean autoLogin(HttpServletRequest request)
	{
		User sessionUser = LoginHelper.getLoginUser(request);
		if (sessionUser != null && StringTools.hasText(sessionUser.getId())) return true;
		Cookie[] cookies = request.getCookies();
		if (isEmpty(cookies)) return false;
		String user = null;
		String pass = null;
		for (Cookie cookie : cookies)
		{
			if (MyFinals.COOKIE_USER.equals(cookie.getName()))
			{
				user = cookie.getValue();
			}
			else if (MyFinals.COOKIE_PASS.equals(cookie.getName()))
			{
				pass = cookie.getValue();
			}
		}
		if (!StringTools.hasText(user) || !StringTools.hasText(pass)) return false;
		try
		{
			user = URLDecoder.decode(user, UTF8);
		}
		catch (UnsupportedEncodingException e)
		{
			logger.error("", e);
			return false;
		}
		user = user.replace('#', '@');
		pass = pass.replace('#', '@');
		logger.debug("cookie user,pass:{}\t{}", user, pass);
		User loginUser = userManager.findByLoginName(user);
		if (loginUser == null || loginUser.getStatus() != UserStatus.NORMAL) return false;
		if (!LoginHelper.isPassValid(pass, loginUser)) return false;
		WebUtils.setSessionAttribute(request, MySessionNames.ERROR_MSG, null);
		LoginHelper.setSession(request, loginUser, rrManager.getRoleMenu(loginUser.getRole().getId()));
		
		return true;
	}

	@RequestMapping(value = "/modPasswd", method = { RequestMethod.GET })
	public String modPasswdView(@ModelAttribute("passwdModel") User passwdModel, HttpServletResponse response,
			Model model) throws IOException
	{
		return "modPasswd";
	}

	@RequestMapping(value = "/modPasswd", method = { RequestMethod.POST })
	public void modPasswdSubmit(@ModelAttribute("passwdModel") User passwdModel, HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
		User user = LoginHelper.getLoginUser(request);
		// 先验证字段的有效性
		if (!StringTools.hasText(passwdModel.getOldPasswd()))
		{
			responseJson(new NoteModel(false, "原始密码不能为空").toJson(), response);
			return;
		}
		if (!StringTools.hasText(passwdModel.getPasswd()))
		{
			responseJson(new NoteModel(false, "新密码不能为空").toJson(), response);
			return;
		}
		if (!StringTools.hasText(passwdModel.getConfirm_pw()))
		{
			responseJson(new NoteModel(false, "确认密码不能为空").toJson(), response);
			return;
		}
		else if (!passwdModel.getConfirm_pw().equals(passwdModel.getPasswd()))
		{
			responseJson(new NoteModel(false, "两次密码不一致").toJson(), response);
			return;
		}
		String oldPasswd = passwdModel.getOldPasswd();
		if (!LoginHelper.isPassValid(oldPasswd, user))
		{
			responseJson(new NoteModel(false, "原始密码错误").toJson(), response);
			return;
		}
		try
		{
			user = userManager.modPasswd(passwdModel.getPasswd(), user.getId());
			WebUtils.setSessionAttribute(request, SessionNames.LOGIN_USER, user.toJson());
			responseJson(new NoteModel(true, "密码修改成功").toJson(), response);
		}
		catch (ManagerException e)
		{
			responseJson(new NoteModel(false, e.getMessage()).toJson(), response);
			logger.error("", e);
		}
	}

	@RequestMapping("/validatePasswd")
	public void validatePasswd(@RequestParam("oldPasswd") String oldPasswd, HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
		User user = LoginHelper.getLoginUser(request);
		response.getWriter().print(LoginHelper.isPassValid(oldPasswd, user));
	}

	@RequestMapping(value = "/modMyself", method = { RequestMethod.GET })
	public String modMyselfForm(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		User user = LoginHelper.getLoginUser(request);
		model.addAttribute("user", userManager.findById(user.getId()));
		return "modMyself";
	}

	@RequestMapping(value = "/modMyself", method = { RequestMethod.POST })
	public void modMyselfSubmit(HttpServletRequest request, @ModelAttribute("user") User user,
			HttpServletResponse response) throws IOException
	{
		try
		{

			user.setRoleId(userManager.findById(user.getId()).getRoleId());
			user = userManager.update(user, user);
			WebUtils.setSessionAttribute(request, SessionNames.LOGIN_USER, user.toJson());
			responseJson(new NoteModel(true, "修改个人信息成功").toJson(), response);
		}
		catch (JsonManagerException e)
		{
			responseJson(new NoteModel(false, e.getMessage()).toJson(), response);
			logger.error("", e);
		}
		catch (ConstraintViolationException e)
		{
			responseJson(new NoteModel(false, "手机号为空或格式不正确").toJson(), response);
			// logger.error("", e);
		}
	}

}
