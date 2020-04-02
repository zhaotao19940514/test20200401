package cn.com.taiji.css.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.com.taiji.common.entity.StringUUIDEntity;
import cn.com.taiji.css.entity.dict.LogType;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-5-20 下午02:43:14<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Entity
@Table(name = "QTK_CSS_SYSTEM_LOG")
public class SystemLog extends StringUUIDEntity
{
	private User user;
	private LocalDateTime optime;
	private String ip;
	private LogType logType;
	private String info;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Column(nullable = false)
	public LocalDateTime getOptime()
	{
		return optime;
	}

	public void setOptime(LocalDateTime optime)
	{
		this.optime = optime;
	}

	@Column(nullable = false, length = 50)
	public String getIp()
	{
		return ip;
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "log_type", nullable = false, length = 10)
	public LogType getLogType()
	{
		return logType;
	}

	public void setLogType(LogType logType)
	{
		this.logType = logType;
	}

	@Column(length = 300, nullable = false)
	public String getInfo()
	{
		return info;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}

}
