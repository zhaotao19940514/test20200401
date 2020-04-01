package cn.com.taiji.css.web.reportquerry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.manager.report.gybftpdetail.GybFtpDetailManage;
import cn.com.taiji.css.model.request.report.gybftpdetail.GybFtpDetailRequest;
import cn.com.taiji.css.web.BaseLogController;

@Controller
@RequestMapping("/report/gybftpdetail")
public class GybFtpBlackController extends BaseLogController {
	private final String prefix = "report/gybftpdetail/";

	@Autowired
	private GybFtpDetailManage gybFtpDetailManage;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") GybFtpDetailRequest queryModel, Model model)
			throws ManagerException {
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") GybFtpDetailRequest queryModel, Model model)
			throws ManagerException {
		model.addAttribute("pagn", gybFtpDetailManage.getDetail(queryModel));
		return prefix + "queryResult";
	}
}
