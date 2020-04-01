/*
 * Date: 2013年10月12日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.quartz.TaskListener;
import cn.com.taiji.common.model.quartz.TaskEvent;
import cn.com.taiji.common.pub.TimeTools;
import cn.com.taiji.css.entity.ScheduleLog;
import cn.com.taiji.css.repo.jpa.ScheduleLogRepo;

/**
 * 
 * @author Peream <br>
 *         Create Time：2013年10月12日 下午7:11:00<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class TaskListenerImpl extends AbstractManager implements TaskListener
{
	@Autowired
	private ScheduleLogRepo logRepo;

	@Override
	public void taskBegin(TaskEvent event)
	{
		logger.debug("task ({}) begin", event.toJson());
	}

	@Override
	public void taskFinish(TaskEvent event)
	{
		ScheduleLog log = new ScheduleLog();
		log.setStartTime(TimeTools.calendar2LocalDateTime(event.getBeginTime()));
		log.setEndTime(TimeTools.calendar2LocalDateTime(event.getFinishTime()));
		log.setBySystem(event.isBySystem());
		log.setCurrentCron(event.getCurrentCron());
		log.setTaskName(event.getTaskName());
		log.setExecTime(event.getFinishTime().getTimeInMillis() - event.getBeginTime().getTimeInMillis());
		logRepo.save(log);
		logger.debug("task ({}) finished", event.toJson());
	}

}
