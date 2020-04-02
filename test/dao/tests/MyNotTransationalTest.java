package tests;

import org.springframework.test.context.ContextConfiguration;

/**
 * 
 * @author Peream <br>
 *         Create Time：2009-12-26 下午09:33:24<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@ContextConfiguration(locations = { "file:war/WEB-INF/beans/main.xml" })
public abstract class MyNotTransationalTest extends MyBaseTest
{
	
}
