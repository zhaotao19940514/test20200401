/*
 * Date: 2014年8月15日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.config.web;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * 
 * @author Peream <br>
 *         Create Time：2014年8月15日 上午8:56:27<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@WebServlet(initParams = { @WebInitParam(name = StatViewServlet.PARAM_NAME_ALLOW, value = "192.168.55.1/24,127.0.0.1") }, urlPatterns = { "/druid/*" })
public class DruidStatViewServlet extends StatViewServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4140527419399728566L;

}
