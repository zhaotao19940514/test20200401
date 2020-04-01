package tests.cn.com.taiji.css.web;

import org.springframework.test.context.ContextConfiguration;

import tests.MyBaseTransationalTest;

/**
 * 
 * @author Peream <br>
 *         邮箱：peream@gmail.com<br>
 *         创建日期：2008-8-18 下午03:42:09
 * @since 1.0
 * @version 1.0
 */
@ContextConfiguration(locations = { "file:war/WEB-INF/spring-servlet.xml" })
public abstract class BaseWebTest extends MyBaseTransationalTest
{

}
