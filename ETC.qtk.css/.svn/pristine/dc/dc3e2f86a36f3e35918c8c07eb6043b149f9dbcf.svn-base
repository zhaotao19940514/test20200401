/*
 * Date: 2012-11-8
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import cn.com.taiji.common.manager.quartz.RunnableProxy;
import cn.com.taiji.common.web.BaseAsyncController;

/**
 * 
 * @author Peream <br>
 *         Create Time：2012-11-8 上午11:07:22<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public abstract class MyAsyncController<E> extends BaseAsyncController<E>
{
	protected static final String TASK_PREFIX = "_ASYNC_";
	@Autowired
	@Qualifier("myExecutor")
	private ThreadPoolTaskExecutor executor;

	protected MyAsyncController(RunnableProxy task)
	{
		super(task);
	}

	@Override
	protected ThreadPoolTaskExecutor getExecutor()
	{
		return executor;
	}
}
