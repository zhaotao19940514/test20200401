/*
 * Date: 2014年10月15日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.web.acl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;

import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.acl.RoleResourceManager;
import cn.com.taiji.css.model.acl.HasButtonModel;
import cn.com.taiji.css.web.MyBaseController;

/**
 * 
 * @author Peream <br>
 *         Create Time：2014年10月15日 下午6:19:44<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Controller
public class RoleResourceController extends MyBaseController
{
	@Autowired
	private RoleResourceManager rrManager;

	@RequestMapping("/acl/hasbutton")
	public void hasButton(@RequestParam("uri") String uri, HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		User user = LoginHelper.getLoginUser(request);
		HasButtonModel rs = rrManager.hasButton(user.getRole().getId(), uri);
		responseJson(rs.toJson(), request, response);
	}

	@RequestMapping("/acl/hasbuttons")
	public void hasButtons(@RequestParam("uris") String[] uris, HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		User user = LoginHelper.getLoginUser(request);
		List<HasButtonModel> rs = Lists.newArrayList();
		for (String uri : uris)
		{
			HasButtonModel hbm = rrManager.hasButton(user.getRole().getId(), uri);
			rs.add(hbm);
		}
		responseJson(JsonTools.toJsonStr(rs), request, response);
	}
}
