/**
 * @Title CardOcxController.java
 * @Package cn.com.taiji.css.web.ocx
 * @Description TODO
 * @author yaonanlin
 * @date 2018年7月16日 下午2:42:18
 * @version V1.0
 */
package cn.com.taiji.css.web.customerservice.finance;

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
import cn.com.taiji.css.manager.customerservice.finance.ExpenseRefundExcelDownloadManager;

/**
 * @ClassName UserExcelDownloadController
 * @Description   批量导入用户 Excel模板下载
 * @author fxd
 * @date 2018年11月14日 14:42:18
 */
@Controller
@RequestMapping("expense")
public class ExpenseRefundExcelDownloadController extends BaseDownloadController {
	@Autowired
	private ExpenseRefundExcelDownloadManager expenseRefundExcelDownloadManager;

	private void downloadFile(HttpServletRequest request, HttpServletResponse response, File rarFile, String fileName)
			throws ManagerException {
		try {
			doDownLoad(request, response, rarFile, fileName);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("下载文件失败",e);
		}
	}
	
	@RequestMapping(value="/dlocx/valueExcel",method =RequestMethod.POST)
	public void downConfigDoc(HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File file = expenseRefundExcelDownloadManager.getValueExcel();
		downloadFile(request, response, file, "批量导入储值卡消费退费模板.xlsx");
	}
	
	@RequestMapping(value="/dlocx/accountExcel",method =RequestMethod.POST)
	public void downServiceExcel(HttpServletRequest request,HttpServletResponse response) throws ManagerException{
		File file = expenseRefundExcelDownloadManager.getAccountExcel();
		downloadFile(request, response, file, "批量导入记账卡消费退费模板.xlsx");
	}
}

