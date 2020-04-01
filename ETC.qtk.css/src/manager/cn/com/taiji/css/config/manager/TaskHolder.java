/*
 * Date: 2015年2月14日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.config.manager;

import java.util.Map;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.quartz.RunnableProxy;

import com.google.common.collect.Maps;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年2月14日 下午4:49:17<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class TaskHolder extends AbstractManager
{
	private static Map<TaskInfo, RunnableProxy> tasks = Maps.newConcurrentMap();

	public static void addTask(TaskInfo info, RunnableProxy task)
	{
		tasks.put(info, task);
	}

	public static RunnableProxy getTask(TaskInfo info)
	{
		return tasks.get(info);
	}

}
