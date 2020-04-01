/**
 * @Title OcxManager.java
 * @Package cn.com.taiji.css.manager.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月16日 下午2:44:39
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.finance;

import java.io.File;

import cn.com.taiji.common.manager.ManagerException;

/**
 * @ClassName OcxManager
 * @Description TODO
 * @author yaonl
 * @date 2018年07月16日 14:44:39
 * @E_mail yaonanlin@163.com
 */
public interface ExpenseRefundExcelDownloadManager {
	File getValueExcel() throws ManagerException;
	File getAccountExcel() throws ManagerException;
}

