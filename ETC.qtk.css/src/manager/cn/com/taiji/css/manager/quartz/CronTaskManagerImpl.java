/*
 * Date: 2014年9月29日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.quartz.CronQuartzManager;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.model.dao.ResultConverter;
import cn.com.taiji.common.model.quartz.CronTaskQueryModel;
import cn.com.taiji.common.model.quartz.CronTaskView;
import cn.com.taiji.common.pub.AssertUtil;
import cn.com.taiji.css.config.manager.TaskInfo;
import cn.com.taiji.css.manager.SystemInfoManager;
import cn.com.taiji.css.model.CronPara;
import cn.com.taiji.css.model.quartz.MyCronTaskView;

/**
 * 
 * @author Peream <br>
 *         Create Time：2014年9月29日 下午4:49:39<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class CronTaskManagerImpl extends AbstractManager
		implements CronTaskManager, ResultConverter<CronTaskView, MyCronTaskView>
{
	@Autowired
	private CronQuartzManager manager;
	@Autowired
	private SystemInfoManager infoManager;

	@Override
	public void startAll(String[] taskNames) throws ManagerException
	{
		AssertUtil.notEmpty(taskNames, "no task exists.");
		for (String taskName : taskNames)
		{
			start(taskName);
		}
	}

	@Override
	public void stopAll(String[] taskNames) throws ManagerException
	{
		AssertUtil.notEmpty(taskNames, "no task exists.");
		for (String taskName : taskNames)
		{
			stop(taskName);
		}
	}

	@Override
	public Pagination queryPage(CronTaskQueryModel qm)
	{
		return manager.queryPage(qm).convertResult(this);
	}

	@Override
	public MyCronTaskView convert(CronTaskView from)
	{
		return MyCronTaskView.newInstance(from);
	}

	@Override
	public MyCronTaskView findOne(String taskName)
	{
		return convert(manager.findOne(taskName));
	}

	@Override
	public void updateTaskCron(CronTaskView view) throws ManagerException
	{
		view.valid();
		TaskInfo taskInfo = TaskInfo.valueOf(view.getTaskName());
		CronPara para = infoManager.getCronPara();
		taskInfo.setCron(para, view.getCron());// 只改变其中一项
		infoManager.updateCronPara(para);// 更新配置到参数配置中
		manager.updateTaskCron(view.getTaskName(), view.getCron());// 修改当前内存中的配置
	}

	@Override
	public void runTaskNow(String taskName) throws ManagerException
	{
		manager.runTaskNow(taskName);
	}

	@Override
	public void start(String taskName) throws ManagerException
	{
		try
		{
			manager.start(taskName);
		}
		catch (Exception e)
		{
			throw new ManagerException(toLogString("启动调度器{}失败:{}", taskName, e.getMessage()));
		}
	}

	@Override
	public void stop(String taskName)
	{
		manager.stop(taskName);
	}

}
