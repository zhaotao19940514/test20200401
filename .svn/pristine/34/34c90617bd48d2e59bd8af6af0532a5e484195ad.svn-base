/*
 * Date: 2015年11月7日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.repo.request.system;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;

import cn.com.taiji.common.pub.dao.HqlBuilder;
import cn.com.taiji.common.repo.request.jpa.JpaDateTimePageableDataRequest;
import cn.com.taiji.css.entity.SystemLog;
import cn.com.taiji.css.entity.dict.LogType;

/**
 * 
 * @author Peream <br>
 *         Create Time：2015年11月7日 下午3:52:49<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class SystemLogPageRequest extends JpaDateTimePageableDataRequest<SystemLog>
{
	private LogType logType;
	@Size(min = 2, max = 30)
	private String userName;
	@Size(min = 3, max = 20)
	private String ip;
	@Size(max = 30)
	private String info;

	public SystemLogPageRequest()
	{
		this.orderBy = "optime";
		this.desc = true;
	}

	@Override
	public HqlBuilder toSelectHql()
	{
		LocalDateTime[] times = toDateTimes();
		HqlBuilder hql = new HqlBuilder("from " + clazz.getName() + " where 1=1");
		hql.append(" and optime>=:optimeMin", times[0]).append(" and optime<=:optimeMax", times[1]);
		hql.append(" and logType=:logType", logType);
		hql.append(" and ip=:ip", ip);
		hql.append(" and info like :info", like(info));
		hql.append(" and (user.loginName like :loginName or user.name like :name or user.namePy like :namePy)",
				like(userName));
		return hql;
	}

	public LogType getLogType()
	{
		return logType;
	}

	public void setLogType(LogType logType)
	{
		this.logType = logType;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public String getInfo()
	{
		return info;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}
}
