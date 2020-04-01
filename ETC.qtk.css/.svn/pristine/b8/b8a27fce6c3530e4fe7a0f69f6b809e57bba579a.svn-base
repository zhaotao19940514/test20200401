/**
 * @Title CardOcxController.java
 * @Package cn.com.taiji.css.web.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月16日 下午2:42:18
 * @version V1.0
 */
package cn.com.taiji.css.web.acl;

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
import cn.com.taiji.css.manager.acl.UserExcelDownloadManager;

/**
 * @ClassName UserExcelDownloadController
 * @Description   批量导入用户 Excel模板下载
 * @author fxd
 * @date 2018年11月14日 14:42:18
 */
@Controller
@RequestMapping("user")
public class UserExcelDownloadController extends BaseDownloadController {
	@Autowired
	private UserExcelDownloadManager userExcelDownloadManager;

	private void downloadFile(HttpServletRequest request, HttpServletResponse response, File rarFile, String fileName)
			throws ManagerException {
		try {
			doDownLoad(request, response, rarFile, fileName);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("下载文件失败",e);
		}
	}
	
	@RequestMapping(value="/dlocx/userExcel",method =RequestMethod.POST)
	public void downConfigDoc(HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File file = userExcelDownloadManager.getUserExcel();
		downloadFile(request, response, file, "批量上传用户模板.xlsx");
	}
	
	@RequestMapping(value="/dlocx/serviceExcel",method =RequestMethod.POST)
	public void downServiceExcel(HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File file = userExcelDownloadManager.getServiceExcel();
		downloadFile(request, response, file, "批量上传网点模板.xlsx");
	}
	
	@RequestMapping(value="/dlocx/infomationExcel",method =RequestMethod.GET)
	public void downInfomationExcel(HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File file = userExcelDownloadManager.getInfomationExcel();
		downloadFile(request, response, file, "批量开卡导入模板.xlsx");
	}
	
	@RequestMapping(value="/dlocx/cardRefundExcel",method =RequestMethod.GET)
	public void downCardRefundExcel(HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File file = userExcelDownloadManager.getCardRefundExcel();
		downloadFile(request, response, file, "退费回执导入模板.xlsx");
	}
	
}

