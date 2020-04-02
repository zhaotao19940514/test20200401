/*
 * Date: 2014年9月29日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager.quartz;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.model.quartz.CronTaskQueryModel;
import cn.com.taiji.common.model.quartz.CronTaskView;
import cn.com.taiji.css.model.quartz.MyCronTaskView;

/**
 * 
 * @author Peream <br>
 *         Create Time：2014年9月29日 下午4:49:21<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public interface CronTaskManager
{
	public Pagination queryPage(CronTaskQueryModel qm);

	public MyCronTaskView findOne(String taskName);

	public void updateTaskCron(CronTaskView view) throws ManagerException;

	/**
	 * 立即执行任务
	 * 
	 * @param taskName
	 * @throws ManagerException
	 */
	public void runTaskNow(String taskName) throws ManagerException;

	/**
	 * 启动指定任务的调度器
	 * 
	 * @param taskName
	 */
	public void start(String taskName) throws ManagerException;

	/**
	 * 停止指定任务的调度器
	 * 
	 * @param taskName
	 */
	public void stop(String taskName);

	/**
	 * 启动所选择的调度任务
	 * 
	 * @param taskNames
	 * @throws ManagerException
	 */
	public void startAll(String[] taskNames) throws ManagerException;

	/**
	 * 停止所选择的调度任务
	 * 
	 * @param taskNames
	 * @throws ManagerException
	 */
	public void stopAll(String[] taskNames) throws ManagerException;

}
