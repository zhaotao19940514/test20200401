/*
 * Date: 2014年9月29日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.model.quartz;

import cn.com.taiji.common.model.quartz.CronTaskView;
import cn.com.taiji.common.pub.BeanTools;
import cn.com.taiji.css.config.manager.TaskGroup;

/**
 * 
 * @author Peream <br>
 *         Create Time：2014年9月29日 下午4:51:52<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class MyCronTaskView extends CronTaskView
{
	private TaskGroup taskGroup;

	public TaskGroup getTaskGroup()
	{
		return taskGroup;
	}

	public void setTaskGroup(TaskGroup taskGroup)
	{
		this.taskGroup = taskGroup;
	}

	public static MyCronTaskView newInstance(CronTaskView view)
	{
		MyCronTaskView rs = new MyCronTaskView();
		BeanTools.copyProperties(view, rs);
		rs.setTaskGroup(TaskGroup.valueOf(view.getType()));
		return rs;
	}
}
