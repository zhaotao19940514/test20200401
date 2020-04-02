package cn.com.taiji.css.manager.timing;

import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.LifecycleService;
import cn.com.taiji.common.pub.AssertUtil;

/**
 * @author wanglijun
 * 		   Create Time 2017年6月8日 下午3:22:31
 * @since 1.0
 * @version 1.0
 */
public class SimpleTimeTaskRunner extends AbstractManager implements LifecycleService {
	private AtomicBoolean running = new AtomicBoolean(false);
	private ThreadPoolTaskScheduler scheduler;
	private final Runnable job;
	private final String name;
	private final Date startTime;
	private final boolean stopImmediate;//是否马上停止，还是等任务完成

	public SimpleTimeTaskRunner(String name, Runnable job,boolean stopImmediate, Date startTime)
	{
		AssertUtil.notNull(job);
		this.name=name;
		this.job=job;
		this.stopImmediate = stopImmediate;
		this.startTime=startTime;
	}
	
	@Override
	public boolean isRunning() {
		return running.get();
	}

	private void initScheduler()
	{
		scheduler = new ThreadPoolTaskScheduler();
		scheduler.setWaitForTasksToCompleteOnShutdown(!stopImmediate);
		scheduler.setPoolSize(1);
		scheduler.setBeanName(name);
		scheduler.initialize();
	}
	
	@Override
	public void start() throws Exception {
		if (running.get())
		{
			logger.info("调度线程({})已经启动.", name);
			return;
		}
		try
		{
			initScheduler();
			scheduler.schedule(job, startTime);
			logger.info("开启调度线程({})成功", name);
			running.set(true);
		}
		finally
		{
			if (running.get() || scheduler == null) return;
			try
			{
				scheduler.shutdown();
			}
			finally
			{
				scheduler = null;
			}
		}
	}

	@Override
	public void stop() {
		if (scheduler == null) return;
		try
		{
			scheduler.shutdown();
			logger.info("停止调度线程({})成功", name);
		}
		finally
		{
			scheduler = null;
			running.set(false);
		}
	}

	

	
}
