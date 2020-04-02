/*
 * Date: 2011-8-18
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager.quartz;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import cn.com.taiji.common.model.annotation.StartScheduler;
import cn.com.taiji.common.model.annotation.StopScheduler;
import cn.com.taiji.css.config.manager.TaskInfo;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-8-18 下午2:35:43<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class SampleCronTask extends AbstractCronTask
{
	public SampleCronTask()
	{
		super(TaskInfo.SAMPLE);
	}

	@StartScheduler(beforeStart = true)
	public void beforeStart()
	{
		logger.info("Scheduler启动之前，本方法只在调度器启动之前执行一次");
	}

	@StartScheduler(beforeStart = false)
	public void afterStart()
	{
		logger.info("Scheduler启动之后，本方法只在调度器启动之后执行一次");
	}

	@Override
	public void run()
	{
		runDepenedTasks(TaskInfo.SYS_INFO);
		logger.info("正在执行任务....");
		try
		{
			TimeUnit.SECONDS.sleep(50);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		logger.info("执行任务完成");
	}

	@StopScheduler(beforeStop = true)
	public void beforeStop()
	{
		logger.info("Scheduler停止之前，本方法只在调度器停止之前执行一次");
	}

	@StopScheduler(beforeStop = false)
	public void afterStop()
	{
		logger.info("Scheduler停止之后，本方法只在调度器停止之后执行一次");
	}
}
