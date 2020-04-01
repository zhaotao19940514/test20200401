package cn.com.taiji.css.web;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.taiji.css.entity.SystemLog;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.LogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.system.SystemLogManager;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-5-20 下午03:06:58<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public abstract class BaseLogController extends MyBaseController
{
	@Autowired
	protected SystemLogManager logManager;

	protected void addLoginLog(HttpServletRequest request, String info, Object... args)
	{
		addLog(request, LogType.LOGIN, info, args);
	}

	protected void addSysLog(HttpServletRequest request, String info, Object... args)
	{
		addLog(request, LogType.SYSTEM, info, args);
	}

	protected void addLog(HttpServletRequest request, LogType type, String info, Object... args)
	{
		User user = LoginHelper.getLoginUser(request);
		String ip = LoginHelper.getLoginIP(request);
		SystemLog log = new SystemLog();
		log.setUser(user);
		log.setIp(ip);
		log.setOptime(LocalDateTime.now());
		log.setLogType(type);
		log.setInfo(toLogString(info, args));
		logManager.add(log);
	}

}
