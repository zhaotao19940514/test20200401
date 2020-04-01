package cn.com.taiji.css.manager.system;

import java.io.File;

import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.common.model.echart.ChartData;
import cn.com.taiji.css.entity.SystemLog;
import cn.com.taiji.css.repo.request.system.SystemLogPageRequest;

/**
 * 
 * @author Peream <br>
 *         Create Time：2011-5-20 下午03:04:48<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public interface SystemLogManager
{
	public String add(SystemLog log);

	public Pagination queryPage(SystemLogPageRequest req);

	ChartData multiGroup();

	ChartData singleGroup();
	
	public File export() throws Exception;
}
