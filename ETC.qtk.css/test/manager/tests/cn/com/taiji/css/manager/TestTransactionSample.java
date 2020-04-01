/*
 * Date: 2011-10-24
 * author: Peream  (peream@gmail.com)
 *
 */
package tests.cn.com.taiji.css.manager;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.css.entity.SystemInfo;
import cn.com.taiji.css.manager.SystemInfoManager;
import cn.com.taiji.css.repo.jpa.SystemInfoRepo;
import tests.MyNotTransationalTest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-10-24 下午2:26:26<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class TestTransactionSample extends MyNotTransationalTest
{
	@Autowired
	private SystemInfoManager infoManager;
	@Autowired
	private SystemInfoRepo infoRepo;

	@Test
	public void txSample()
	{
		echo(infoRepo);
		infoManager.deleteById("-1");
		executeTx(true, false);
		Optional<SystemInfo> info = infoRepo.findById("-1");
		Assert.assertFalse(info.isPresent());

		executeTx(false, false);
		info = infoRepo.findById("-1");
		Assert.assertTrue(info.isPresent());

		infoManager.delete(info.orElse(null));
	}

	@Test
	public void txSampleAlways()
	{
		infoManager.deleteById("-1");
		// 测试嵌套事务
		executeTx(false, true);
		SystemInfo info = infoRepo.findById("-1").orElse(null);
		Assert.assertNull(info);

		executeTx(true, true);
		info = infoRepo.findById("-1").orElse(null);
		Assert.assertNull(info);
	}

	private void executeTx(boolean rollback, boolean rollbackAlways)
	{
		try
		{
			infoManager.txSample(rollback, rollbackAlways);
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
	}
}
