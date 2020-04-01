package tests;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import tests.cn.com.taiji.common.DataSourceConfiguration;

/**
 * 
 * @author Peream <br>
 *         邮箱：peream@gmail.com<br>
 *         创建日期：2008-8-18 下午02:35:13
 * @since 1.0
 * @version 1.0
 */
@ContextConfiguration(locations = { "file:war/WEB-INF/beans/main.xml" })
@Rollback
@DataSourceConfiguration(dataSource = "dataSource")
public abstract class MyBaseTransationalTest extends BaseTransationalTest
{
	static
	{
		System.setProperty("webapp.css", "war");
	}

}
