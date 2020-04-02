/*
 * Date: 2014年9月28日
 * author: Peream  (peream@gmail.com)
 *
 */
package cn.com.taiji.css.manager;

import cn.com.taiji.common.manager.pub.FileHelper;

/**
 * 
 * @author Peream <br>
 *         Create Time：2014年9月28日 下午5:27:51<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
public class MyFileHelper extends FileHelper
{
	/**
	 * 存放quartz任务的路径
	 * @return
	 */
	public static String getQuartzPath()
	{
		String path = getDataPath() + "/quartz";
		mkdirs(path);
		return path;
	}
}
