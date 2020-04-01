/*
 * Date: 2013-5-6
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.css.config.manager.TaskInfo;
import cn.com.taiji.css.manager.SystemInfoManager;

/**
 * 
 * @author Peream <br>
 *         Create Time：2013-5-6 下午2:51:53<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
public class InitSystemInfoTask extends AbstractCronTask
{
	@Autowired
	private SystemInfoManager infoManager;

	public InitSystemInfoTask()
	{
		super(TaskInfo.SYS_INFO);
	}

	@Override
	public void run()
	{
		infoManager.init();
	}

}
