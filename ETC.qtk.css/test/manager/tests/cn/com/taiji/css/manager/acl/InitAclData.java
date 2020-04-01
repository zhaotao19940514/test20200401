/*
 * Date: 2011-9-22
 * author: Peream  (peream@gmail.com)
 *
 */
package tests.cn.com.taiji.css.manager.acl;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import tests.MyBaseTransationalTest;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.acl.ResourceManager;
import cn.com.taiji.css.repo.jpa.UserRepo;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-9-22 上午11:16:22<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public abstract class InitAclData extends MyBaseTransationalTest
{
	@Autowired
	protected ResourceManager resourceManager;
	@Autowired
	private UserRepo userRepo;

	protected User loginUser;

	@Before
	public void init()
	{
		setSqlScriptEncoding("UTF-8");
		executeSqlScript("classpath:acl_init.sql", false);
		resourceManager.init();// resourceManager使用了缓存，初始化数据后需要重新执行init
		logger.info("Init ACL data success.");

		loginUser = userRepo.findById("acl_user1").get();
	}
}
