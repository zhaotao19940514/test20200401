package cn.com.taiji.css.web.reportquerry;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.manager.pub.FileHelper;
import cn.com.taiji.css.manager.report.cancelreport.CancelReportManage;
import cn.com.taiji.css.model.request.report.cancelreport.CancelReportRequest;
import cn.com.taiji.css.web.BaseLogController;

@Controller
@RequestMapping("/report/cancelreport")
public class CancelReportController extends BaseLogController {
	private final String prefix = "report/cancelreport/";

	@Autowired
	private CancelReportManage cancelReportManage;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CancelReportRequest queryModel, Model model)
			throws ManagerException {
		model.addAttribute("agency", cancelReportManage.getAllAgency());
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CancelReportRequest queryModel, Model model)
			throws ManagerException {
		model.addAttribute("pagn", cancelReportManage.getCancelDetail(queryModel));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/export", method = RequestMethod.POST)
	private void export(@ModelAttribute("queryModel") CancelReportRequest queryModel, HttpServletResponse response)
			throws Exception {
		cancelReportManage.export(queryModel);
		addSuccess(response, "下载成功");
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	private void download(@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
			@RequestParam("agencyName") String agencyName, HttpServletRequest request, HttpServletResponse response)
			throws ManagerException, IOException {
		File file = new File(FileHelper.getTmpPath() + "/cancelreport.xls");
		if (!file.exists())
			throw new ManagerException("文件不存在无法导出");
		HttpMimeResponseHelper.doDownLoad(request, response, new FileInputStream(file),
				startTime.replaceAll(":", "_") + "至" + endTime.replaceAll(":", "_") + agencyName + "注销量.xls");
	}
}
