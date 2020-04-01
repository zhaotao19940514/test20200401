/*
 * Date: 2013年8月9日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager.quartz;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.quartz.RunnableProxy;
import cn.com.taiji.css.config.manager.TaskHolder;
import cn.com.taiji.css.config.manager.TaskInfo;

/**
 * 
 * @author Peream <br>
 *         Create Time：2013年8月9日 上午10:42:56<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public abstract class AbstractCronTask extends AbstractManager implements TaskRunnable
{
	private final TaskInfo taskInfo;

	protected AbstractCronTask(TaskInfo taskInfo)
	{
		this.taskInfo = taskInfo;
	}

	public TaskInfo getTaskInfo()
	{
		return taskInfo;
	}

	protected final void runDepenedTasks(TaskInfo... infos)
	{
		if (isEmpty(infos)) return;
		for (TaskInfo info : infos)
		{
			RunnableProxy task = TaskHolder.getTask(info);
			if (task == null)
			{
				logger.error("任务{}未配置.", info);
				continue;
			}
			logger.info("\t开始执行依赖任务:{}", info);
			task.run();
			logger.info("\t完成依赖任务执行:{}", info);
		}
	}
}
