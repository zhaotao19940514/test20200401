/*
 * Date: 2013-5-6
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.config.manager;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.quartz.CronQuartzManager;
import cn.com.taiji.common.manager.quartz.CronQuartzManagerImpl;
import cn.com.taiji.common.manager.quartz.ExclusiveTask;
import cn.com.taiji.common.manager.quartz.FileExclusiveTask;
import cn.com.taiji.common.manager.quartz.RunnableProxy;
import cn.com.taiji.common.manager.quartz.TaskListener;
import cn.com.taiji.common.model.quartz.CronTaskConfig;
import cn.com.taiji.css.manager.MyFileHelper;
import cn.com.taiji.css.manager.SystemInfoManager;
import cn.com.taiji.css.manager.quartz.TaskRunnable;
import cn.com.taiji.css.model.CronPara;

/**
 * 
 * @author Peream <br>
 *         Create Time：2013-5-6 下午2:43:31<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Configuration
public class QuartzConfig extends AbstractManager
{
	@Value("#{taskMap}")
	private Map<TaskInfo, TaskRunnable> tasks;
	@Autowired
	private SystemInfoManager infoManager;
	@Autowired
	private TaskListener taskListener;

	// @Autowired
	// private DataSource ds;

	@Bean(name = "cronQuartzManager", initMethod = "init", destroyMethod = "destory")
	public CronQuartzManager cronQuartzManager()
	{
		CronQuartzManager manager = new CronQuartzManagerImpl();
		CronPara para = infoManager.getCronPara();
		for (Entry<TaskInfo, TaskRunnable> en : tasks.entrySet())
		{
			if (en.getKey() != en.getValue().getTaskInfo())
				throw new RuntimeException(toLogString("任务的key({})与Task声明的类型({})不一致", en.getKey(), en.getValue()));
			manager.addTaskConfig(newTaskConfig(en.getValue(), en.getKey().getCron(para)));
		}
		return manager;
	}

	private CronTaskConfig newTaskConfig(TaskRunnable rawTask, String cron)
	{
		CronTaskConfig config = new CronTaskConfig();
		TaskInfo info = rawTask.getTaskInfo();
		// TODO 修改此处的task封装可以实现虚拟机间互斥 MemcachedExclusiveTask DBExclusiveTask FileExclusiveTask
		RunnableProxy task = info.getMaxExecSencods() < 0 ? new ExclusiveTask(rawTask, info.name(), taskListener)
				: new FileExclusiveTask(rawTask, MyFileHelper.getQuartzPath(), taskListener, info.name());
		// ExclusiveTask task = new ExclusiveTask(rawTask, info.name(), taskListener);
		config.setCron(cron);
		config.setTask(task);
		config.setInfo(info.getInfo());
		config.setAutoStart(info.isAutoStart());
		config.setType(info.getGroup().name());
		TaskHolder.addTask(info, task);
		return config;
	}

}
