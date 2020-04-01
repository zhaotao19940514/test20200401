/*
 * Date: 2013-5-21
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.NoteModel;
import cn.com.taiji.common.pub.StringTools;
import cn.com.taiji.common.web.util.WebTools;
import cn.com.taiji.css.entity.AppResource;
import cn.com.taiji.css.entity.AppResource.MenuType;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.acl.AclManager;
import cn.com.taiji.css.manager.acl.ResourceManager;
import cn.com.taiji.css.model.MySessionNames;

/**
 * 
 * @author Peream <br>
 *         Create Time：2013-5-21 上午10:35:37<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class AclHandlerInterceptor extends HandlerInterceptorAdapter
{
	private String welcomeUri;
	private String noRightUri;
//	private String indexUri;
	@Autowired
	private AclManager manager;
	@Autowired
	private ResourceManager resourceManager;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		// WebDebugUtil.echoRequestParams(request);
		String uri = WebTools.getUri(request);
		if (uri.startsWith("/")) uri = uri.substring(1);
		// 忽略app/common开头的请求
		if (uri.startsWith("app/common/") || uri.startsWith("app/sample/"))
		{
			response.reset();
			return true;
		}

		RequestMethod method = RequestMethod.valueOf(request.getMethod().toUpperCase());
		// 还未登录,跳转到本地登录页面
		if (!LoginHelper.hasLogin(request))
		{
			request.getSession().invalidate();
			WebUtils.setSessionAttribute(request, MySessionNames.ERROR_MSG, "会话已超时失效,请重新登录");
			if (method == RequestMethod.GET)
				WebUtils.setSessionAttribute(request, MySessionNames.LAST_URI, WebTools.getUri(request, true));
			String tjAjax = request.getHeader("taiji_ajax");
			if (StringTools.hasText(tjAjax)) // 如果是ajax请求，响应头增加taiji_jump，由页面进行跳转
			{	
				String msg = "您的登录已经超时，请退出重新登录。";
				response.setHeader("taiji_jump", getWelcomeUri(request));
				HttpMimeResponseHelper.responseJson(new NoteModel(false, msg).toJson(), request, response);
			}
			else
			{
				response.sendRedirect(getWelcomeUri(request));
			}
			return false;
		}
		setMyMenuType(request);
		// acl
		AppResource res = manager.getResource(uri, method);
		setColumnMenuResource(request, res, method);
		User user = LoginHelper.getLoginUser(request);
		String result = manager.checkPower(user.getRole(), res);
		if (!StringTools.hasText(result)) return true;
		// 权限校验失败
		WebUtils.setSessionAttribute(request, MySessionNames.NORIGHT_ERROR, result);
		if (method == RequestMethod.GET)
			WebUtils.setSessionAttribute(request, MySessionNames.LAST_URI, WebTools.getUri(request, true));
		response.sendRedirect(getNorightUri(request) + "?method=" + method);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception
	{
		// session中存放的是字符串，此处转换成model的形式返回
		if (modelAndView == null) return;
		User loginUser = LoginHelper.getLoginUser(request);
		if (loginUser != null) modelAndView.addObject(MySessionNames.LOGIN_USER, loginUser);
		modelAndView.addObject(MySessionNames.ROLE_MENU, LoginHelper.getCurrentRoleMenu(request));
		AppResource res = LoginHelper.getCurrentResource(request);
		if (res != null) modelAndView.addObject(MySessionNames.CURRENT_COLUMN_RESOURCE, res);
	}

	private void setColumnMenuResource(HttpServletRequest request, AppResource res, RequestMethod method)
	{
		if (res == null)// 找不到资源时，当前资源设置成空
		{
			WebUtils.setSessionAttribute(request, MySessionNames.CURRENT_COLUMN_RESOURCE, null);
			return;
		}
		MenuType mt = res.getMenuType();
		// 当资源不是GET形式的栏目菜单时,当前资源保持上一个的
		if (mt != MenuType.COLUMN || method != RequestMethod.GET) return;
		WebUtils.setSessionAttribute(request, MySessionNames.CURRENT_COLUMN_RESOURCE, res.toJson());
		String menuId = request.getParameter("myMenuId");
		if (StringTools.hasText(menuId)) return;
		menuId = res.getId();
		WebUtils.setSessionAttribute(request, MySessionNames.SESSION_MENU_ID, menuId);
	}

	/**
	 * 通过myMenuId参数设置页面当前MenuType，和请求url对应的AppResource没关系
	 * @param request
	 */
	private void setMyMenuType(HttpServletRequest request){
		String menuId = request.getParameter("myMenuId");
		if(!StringTools.hasText(menuId))return;
		AppResource resource = resourceManager.findById(menuId);
		if(resource!=null){
			request.setAttribute("myResourceType", resource.getType());
		}
	}
	private String getWelcomeUri(HttpServletRequest request)
	{
		if (welcomeUri == null) welcomeUri = request.getContextPath() + "/app/common/welcome";
		int ran = new Random().nextInt(10);
		return welcomeUri + "?ran=" + ran;
	}

	private String getNorightUri(HttpServletRequest request)
	{
		if (noRightUri == null) noRightUri = request.getContextPath() + "/app/common/acl/noright";
		return noRightUri;
	}

//	private String getIndexUri(HttpServletRequest request)
//	{
//		if (indexUri == null) indexUri = request.getContextPath() + "/app/index?myMenuId=index";
//		return indexUri;
//	}
}
