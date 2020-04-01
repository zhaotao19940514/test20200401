package tests.cn.com.taiji.css.manager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.cn.com.taiji.css.manager.acl.AclSuite;
import tests.cn.com.taiji.css.manager.system.TestSystemLogManager;

/**
 * 
 * @author Peream <br>
 *         邮箱：peream@gmail.com<br>
 *         创建日期：2009-4-28 下午04:33:33
 * @since 1.0
 * @version 1.0
 */
@RunWith(Suite.class)
@SuiteClasses({ AclSuite.class, TestSystemLogManager.class, TestSystemInfoManager.class, TestTransactionSample.class })
public class SampleManagerSuite
{

}
