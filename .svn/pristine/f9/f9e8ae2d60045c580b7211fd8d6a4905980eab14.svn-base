package cn.com.taiji.css.config.web;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;
import org.springframework.web.util.WebAppRootListener;

/**
 * 
 * 
 * @author Peream <br>
 *         Create Time：2014年8月12日 下午3:16:45<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class MyWebAppInitializer extends AbstractDispatcherServletInitializer
{

	@Override
	protected void registerContextLoaderListener(ServletContext servletContext)
	{
		// Web 应用根目录以该属性名(webapp.css)添加到系统参数(System.property)中
		WebAppRootListener appRootListener = new WebAppRootListener();
		servletContext.setInitParameter("webAppRootKey", "webapp.css");
		servletContext.addListener(appRootListener);
		super.registerContextLoaderListener(servletContext);
	}

	@Override
	protected WebApplicationContext createServletApplicationContext()
	{
		XmlWebApplicationContext cxt = new XmlWebApplicationContext();
		cxt.setConfigLocation("/WEB-INF/spring-servlet.xml");
		return cxt;
	}

	@Override
	protected WebApplicationContext createRootApplicationContext()
	{
		XmlWebApplicationContext cxt = new XmlWebApplicationContext();
		cxt.setConfigLocation("/WEB-INF/beans/main.xml");
		return cxt;
	}

	@Override
	protected void customizeRegistration(Dynamic registration)
	{
		super.customizeRegistration(registration);
		// 使用servlet 3.0的multipart config
		MultipartConfigElement multipartConfig = new MultipartConfigElement("", 1000000000L, -1, 0);
		registration.setMultipartConfig(multipartConfig);
	}

	@Override
	protected String[] getServletMappings()
	{
		return new String[] { "/app/*" };
	}

	protected Filter[] getServletFilters()
	{
		CharacterEncodingFilter encFilter = new CharacterEncodingFilter();
		encFilter.setEncoding("UTF-8");
		encFilter.setForceEncoding(true);
		encFilter.setBeanName("encodingFilter");
		// sso auth filter
		return new Filter[] { encFilter };
	}

}