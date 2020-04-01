/**
 * @Title CardOcxController.java
 * @Package cn.com.taiji.css.web.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月16日 下午2:42:18
 * @version V1.0
 */
package cn.com.taiji.css.web.ocx;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.web.BaseDownloadController;
import cn.com.taiji.css.manager.ocx.OcxDownloadManager;

/**
 * @ClassName CardOcxController
 * @Description 卡、签、pos机 驱动下载
 * @author yaonl
 * @date 2018年07月16日 14:42:18
 * @E_mail yaonanlin@163.com
 */
@Controller
@RequestMapping("ocx")
public class OcxDownloadController extends BaseDownloadController {
	@Autowired
	private OcxDownloadManager ocxManager;
	@RequestMapping(value="/dlocx/card",method =RequestMethod.POST)
	public void downloadCardOcx(HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File cardOcxRar = ocxManager.getCardOcxDriver();
		downloadFile(request, response, cardOcxRar, cardOcxRar.getName());
	}
	@RequestMapping(value="/dlocx/jlCard",method =RequestMethod.POST)
	public void downloadJlCardOcx(HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File cardOcxRar = ocxManager.getJlCardOcxDriver();
		downloadFile(request, response, cardOcxRar, cardOcxRar.getName());
	}
	@RequestMapping(value="/dlocx/ccbCard",method =RequestMethod.POST)
	public void downloadCcbCardOcx(HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File ccbCardOcxRar = ocxManager.getCcbCardOcxDriver();
		downloadFile(request, response, ccbCardOcxRar, ccbCardOcxRar.getName());
	}
	@RequestMapping(value="/dlocx/obu",method =RequestMethod.POST)
	public void downloadObuOcx(HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File cardOcxRar = ocxManager.getObuOcxDriver();	
		downloadFile(request, response, cardOcxRar, cardOcxRar.getName());
	}
	@RequestMapping(value="/dlocx/pos",method =RequestMethod.POST)
	public void downloadPosOcx(HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File cardOcxRar = ocxManager.getPosOcxDriver();
		downloadFile(request, response, cardOcxRar, cardOcxRar.getName());
	}
	@RequestMapping(value="/dlocx/watchObu",method =RequestMethod.POST)
	public void downloadWatchObuOcx(HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File cardOcxRar = ocxManager.getWatchObuOcxDriver();
		downloadFile(request, response, cardOcxRar, cardOcxRar.getName());
	}
	private void downloadFile(HttpServletRequest request, HttpServletResponse response, File rarFile, String fileName)
			throws ManagerException {
		try {
			doDownLoad(request, response, rarFile, fileName);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("下载文件失败",e);
		}
	}
	
	@RequestMapping(value="/dlocx/firefox",method =RequestMethod.POST)
	public void downFireFox(HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File rar = ocxManager.getFireFoxRar();
		downloadFile(request, response, rar, rar.getName());
	}
	
	@RequestMapping(value="/dlocx/chrome",method =RequestMethod.POST)
	public void downChrome(HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File rar = ocxManager.getChrome();
		downloadFile(request, response, rar, rar.getName());
	}
	
	@RequestMapping(value="/dlocx/configDoc",method =RequestMethod.POST)
	public void downConfigDoc(HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File file = ocxManager.getConfigDoc();
		downloadFile(request, response, file, "黔通卡新客服系统操作手册.doc");
	}
}

