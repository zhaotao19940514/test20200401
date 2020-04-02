package cn.com.taiji.css.manager;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.SystemInfo;
import cn.com.taiji.css.model.CronPara;
import cn.com.taiji.css.model.SystemPara;

/**
 * 
 * @author Peream <br>
 *         Create Time：2009-11-16 下午01:13:57<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public interface SystemInfoManager
{
	public void init();

	public SystemPara getSystemPara();

	public void updateSystemPara(SystemPara para) throws ManagerException;

	public CronPara getCronPara();

	public void updateCronPara(CronPara para) throws ManagerException;

	/**
	 * 用于测试事务配置是否生效
	 * 
	 * @param rollback
	 *            被嵌套的事务是否回滚
	 * @param rollbackAlways
	 *            事务总是回滚，忽略被嵌套事务是否回滚
	 * @throws ManagerException
	 */
	public void txSample(boolean rollback, boolean rollbackAlways) throws ManagerException;

	public void deleteById(String id);

	public void delete(SystemInfo info);

}