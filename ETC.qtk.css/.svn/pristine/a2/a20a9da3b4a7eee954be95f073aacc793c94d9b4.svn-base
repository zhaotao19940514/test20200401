/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.repo.request.system;

import java.time.LocalDateTime;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.css.entity.ScheduleLog;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年11月7日 下午3:47:24<br>
 *         三 <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class ScheduleLogPageRequest extends JpaDateTimePageableDataRequest<ScheduleLog>
{
	private Boolean bySystem;
	@Digits(integer = 10, fraction = 0)
	@Min(value = 1)
	private Long execTime;

	public ScheduleLogPageRequest()
	{
		this.orderBy = "startTime";
		this.desc = true;
	}

	public Long getExecTime()
	{
		return execTime;
	}

	public void setExecTime(Long execTime)
	{
		this.execTime = execTime;
	}

	public Boolean getBySystem()
	{
		return bySystem;
	}

	public void setBySystem(Boolean bySystem)
	{
		this.bySystem = bySystem;
	}

	@Override
	public HqlBuilder toSelectHql()
	{
		LocalDateTime[] times = toDateTimes();
		HqlBuilder hql = new HqlBuilder("from " + ScheduleLog.class.getName() + " where 1=1");
		hql.append(" and startTime>=:startTime", times[0]).append(" and startTime<=:endTime", times[1]);
		hql.append(" and bySystem=:bySystem", bySystem);
		hql.append(" and execTime>=:execTime", execTime);
		return hql;
	}

}
