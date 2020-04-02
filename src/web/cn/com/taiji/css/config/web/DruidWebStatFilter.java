/*
 * Date: 2014年8月13日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.config.web;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 
 * @author Peream <br>
 *         Create Time：2014年8月13日 下午12:40:16<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@WebFilter(urlPatterns = { "/app/*" }, initParams = {
		@WebInitParam(name = WebStatFilter.PARAM_NAME_EXCLUSIONS, value = "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"),
		@WebInitParam(name = WebStatFilter.PARAM_NAME_PRINCIPAL_SESSION_NAME, value = "currentLoginName"),
		@WebInitParam(name = WebStatFilter.PARAM_NAME_SESSION_STAT_ENABLE, value = "false")})
public class DruidWebStatFilter extends WebStatFilter
{
	private static Logger logger = LoggerFactory.getLogger(DruidWebStatFilter.class);

	@Override
	public void init(FilterConfig config) throws ServletException
	{
		super.init(config);
		logger.info("exclusions:{}", config.getInitParameter(PARAM_NAME_EXCLUSIONS));
		logger.info("principalSessionName:{}", principalSessionName);
	}

}
