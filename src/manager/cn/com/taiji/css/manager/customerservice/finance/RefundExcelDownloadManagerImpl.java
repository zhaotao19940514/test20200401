/**
 * @Title OcxManagerImpl.java
 * @Package cn.com.taiji.css.manager.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月16日 下午2:44:50
 * @version V1.0
 */
package cn.com.taiji.css.manager.customerservice.finance;

import java.io.File;

import org.springframework.stereotype.Service;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.manager.util.CssConstant;

/**
 * @ClassName OcxManagerImpl
 * @Description TODO
 * @author yaonl
 * @date 2018年07月16日 14:44:50
 * @E_mail yaonanlin@163.com
 */
@Service
public class RefundExcelDownloadManagerImpl extends AbstractManager implements RefundExcelDownloadManager {
	
	
	private File getFile(String filePath) throws ManagerException {
		File file = new File(filePath);
		System.out.println(filePath);
		if(!file.exists()){
			throw new ManagerException("文件不存在");
		}
		return file;
	}




	@Override
	public File getServiceExcel() throws ManagerException {
		return getFile(CssConstant.REFUND_EXCEL);
	}
}

