package cn.com.taiji.css.manager.quartz;

import java.util.Calendar;

import cn.com.taiji.common.manager.quartz.AbstractRunnableProxy;
import cn.com.taiji.common.manager.quartz.TaskListener;
import cn.com.taiji.common.model.quartz.TaskEvent;

public class SimpleExclusiveTask extends AbstractRunnableProxy {

	public SimpleExclusiveTask(Runnable task, String taskName, TaskListener listener) {
		super(task, taskName, listener);
	}
	
	@Override
	public void run()
	{
		if (taskRunning.get())
		{
			logger.info("{} 任务正在运行,本次任务调用忽略.", taskName);
			return;
		}
		try
		{
			taskRunning.set(true);
			TaskEvent event =null;
			if(listener!=null){
				event=new TaskEvent(Calendar.getInstance(), taskName, false, null);
				listener.taskBegin(event);
			}
			doTask();
			if(listener!=null){
				event.setFinishTime(Calendar.getInstance());
				listener.taskFinish(event);
			}
		}
		finally
		{
			taskRunning.set(false);
		}
	}

	

	public boolean isRunning()
	{
		return taskRunning.get();
	}

}
