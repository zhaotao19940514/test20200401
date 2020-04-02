/*
 * Date: 2011-6-24
 * author: Peream  (peream@gmail.com)
 *
 */
package tests.cn.com.taiji.css.manager;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tests.MyNotTransationalTest;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.manager.SystemInfoManager;
import cn.com.taiji.css.model.SystemPara;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-6-24 下午01:39:49<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class TestSystemInfoManager extends MyNotTransationalTest
{
	@Autowired
	private SystemInfoManager infoManager;

	@Test
	public void updateSystemPara() throws ManagerException
	{
		SystemPara para = new SystemPara();
		para.setPrintHead("ccc");
		infoManager.updateSystemPara(para);
		para = infoManager.getSystemPara();
		Assert.assertEquals("ccc", para.getPrintHead());
	}
}
