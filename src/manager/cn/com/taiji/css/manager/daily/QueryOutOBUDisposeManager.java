package cn.com.taiji.css.manager.daily;

import java.io.File;

import cn.com.taiji.common.manager.ManagerException;

/**
 * 临时过期obu处理查询
 * @author HHW
 */
public interface QueryOutOBUDisposeManager {

	File run(String date, String filePath) throws ManagerException;
	 
}
