/**
 * @Title OcxManagerImpl.java
 * @Package cn.com.taiji.css.manager.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月16日 下午2:44:50
 * @version V1.0
 */
package cn.com.taiji.css.manager.ocx;

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
public class OcxDownloadManagerImpl extends AbstractManager implements OcxDownloadManager {
	@Override
	public File getCardOcxDriver() throws ManagerException {
		return getFile(CssConstant.CARD_OCX_DRIVER_PATH);
	}
	
	@Override
	public File getJlCardOcxDriver() throws ManagerException {
		return getFile(CssConstant.JL_CARD_OCX_DRIVER_PATH);
	}

	@Override
	public File getObuOcxDriver() throws ManagerException {
		return getFile(CssConstant.OBU_OCX_DRIVER_PATH);
	}

	@Override
	public File getPosOcxDriver() throws ManagerException {
		return getFile(CssConstant.POS_OCX_DRIVER_PATH);
	}
	
	private File getFile(String filePath) throws ManagerException {
		File file = new File(filePath);
		if(!file.exists()){
			throw new ManagerException("文件不存在");
		}
		return file;
	}

	@Override
	public File getCcbCardOcxDriver() throws ManagerException {
		return getFile(CssConstant.CCB_CARD_OCX_DRIVER_PATH);
	}

	@Override
	public File getWatchObuOcxDriver() throws ManagerException {
		return getFile(CssConstant.WATCH_OBU_OCX_DRIVER_PATH);
	}

	@Override
	public File getFireFoxRar() throws ManagerException {
		return getFile(CssConstant.FIRE_FOX_GREEN_PATH);
	}

	@Override
	public File getChrome() throws ManagerException {
		return getFile(CssConstant.CHROME_GREEN_PATH);
	}

	@Override
	public File getConfigDoc() throws ManagerException {
		return getFile(CssConstant.CONFIG_DOC);
	}
}

