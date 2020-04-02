package cn.com.taiji.css.config.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.format.CalendarFormatter;
import cn.com.taiji.common.manager.format.LocalDateFormatter;
import cn.com.taiji.common.manager.format.LocalDateTimeFormatter;
import cn.com.taiji.common.manager.format.LocalTimeFormatter;
import cn.com.taiji.common.manager.format.TrimStringFormatter;
import cn.com.taiji.css.config.manager.AppConfig;
import cn.com.taiji.css.manager.AclHandlerInterceptor;

/**
 * 
 * 
 * @author Peream <br>
 *         Create Time：2011-12-8 下午2:37:42<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "cn.com.taiji." + AppConfig.APP_NAME + ".web")
public class WebConfig extends AbstractManager implements WebMvcConfigurer
{
	/**
	 * 常规视图
	 * 
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver()
	{
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/**
	 * 覆盖父类的方法以改变mvc的对象绑定
	 */
	@Override
	public void addFormatters(FormatterRegistry registry)
	{
		registry.addFormatterForFieldType(String.class, new TrimStringFormatter());
		registry.addFormatterForFieldType(Calendar.class, new CalendarFormatter());
		registry.addFormatterForFieldType(LocalDateTime.class, new LocalDateTimeFormatter());
		registry.addFormatterForFieldType(LocalDate.class, new LocalDateFormatter());
		registry.addFormatterForFieldType(LocalTime.class, new LocalTimeFormatter());
	}

	@Autowired
	private AclHandlerInterceptor aclInterceptor;

	/**
	 * 覆盖父类方法注册拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(aclInterceptor);// 访问控制
	}

	/**
	 * 报表视图
	 * 
	 * @return
	 */
	// @Bean
	// public ResourceBundleViewResolver ResourceBundleViewResolver()
	// {
	// ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
	// resolver.setOrder(1);
	// resolver.setBasename("views");
	// return resolver;
	// }

	/**
	 * 国际化
	 * 
	 * @return
	 */
	// @Bean
	// public SessionLocaleResolver sessionLocaleResolver()
	// {
	// return new SessionLocaleResolver();
	// }

	/**
	 * 上传组件
	 * 
	 * @return
	 */
	@Bean
	public MultipartResolver multipartResolver()
	{
		return new StandardServletMultipartResolver();
	}
}