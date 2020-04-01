/*
 * Date: 2011-9-22
 * author: Peream  (peream@gmail.com)
 *
 */
package tests.cn.com.taiji.css.manager.system;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.model.echart.ChartData;
import cn.com.taiji.css.entity.SystemLog;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.LogType;
import cn.com.taiji.css.manager.acl.UserManager;
import cn.com.taiji.css.manager.system.SystemLogManager;
import cn.com.taiji.css.repo.request.system.SystemLogPageRequest;
import tests.MyBaseTransationalTest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-9-22 下午1:45:55<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class TestSystemLogManager extends MyBaseTransationalTest
{
	@Autowired
	private SystemLogManager manager;
	@Autowired
	private UserManager userManager;

	@Test
	public void addAndQueryPage()
	{
		SystemLog log = new SystemLog();
		log.setInfo("system_test_info");
		log.setIp("127.0.0.1");
		log.setLogType(LogType.SYSTEM);
		log.setOptime(LocalDateTime.now());
		User user = userManager.findByLoginName("admin");
		log.setUser(user);
		manager.add(log);

		SystemLogPageRequest qm = new SystemLogPageRequest();
		qm.setStartTime(LocalDateTime.parse("1981-02-16T08:30:00", DateTimeFormatter.ISO_DATE_TIME));
		qm.setEndTime(LocalDateTime.now());
		qm.setInfo("system");
		qm.setIp("127.0.0.1");
		qm.setUserName("admin");
		qm.setLogType(LogType.SYSTEM);
		Pagination pg = manager.queryPage(qm);
		Assert.assertTrue(pg.getResult().size() > 0);

		qm.setUserName(null);
		qm.setInfo(null);
		pg = manager.queryPage(qm);
		Assert.assertTrue(pg.getResult().size() > 0);
	}

	@Test
	public void statGroupBy()
	{
		ChartData chart = manager.multiGroup();
		System.out.println(chart.toJson());
		System.out.println(manager.singleGroup().toJson());
	}
}
