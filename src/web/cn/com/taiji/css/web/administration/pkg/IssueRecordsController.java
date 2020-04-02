package cn.com.taiji.css.web.administration.pkg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.entity.dict.IssuePackagePayStatus;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.administration.pkg.IssueRecordsManager;
import cn.com.taiji.css.model.administration.pkg.IssuerecordsQueryRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CarIssuePackageInfo;
import cn.com.taiji.qtk.entity.dict.IssueStatusType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;

@Controller
@RequestMapping("/administration/package/issuerecords")
public class IssueRecordsController extends MyLogController {
	private final String prefix = "administration/package/issuerecords/";
	
	@Autowired
	private IssueRecordsManager issueRecordsManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") IssuerecordsQueryRequest queryModel, Model model)
	{
		model.addAttribute("payStatus", IssuePackagePayStatus.values());
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") IssuerecordsQueryRequest queryModel, Model model, HttpServletRequest req)
	{
		String serviceHallId = LoginHelper.getLoginUser(req).getStaff().getServiceHall().getServiceHallId();
		//如果不是黔通智联中心点，加网点查询限制
		if(!serviceHallId.equals("5201010600401140003"))
			queryModel.setServiceHallId(serviceHallId);
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		model.addAttribute("payStatus", IssuePackagePayStatus.values());
		model.addAttribute("pagn", issueRecordsManager.queryPage(queryModel));
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/verifyPay/{id}", method = RequestMethod.GET)
	public String verifyPayGet(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response, Model model) throws ManagerException
	{
		model.addAttribute("pageModel", issueRecordsManager.findById(id));
		model.addAttribute("issueStatusTypes", IssueStatusType.values());
		model.addAttribute("issuePackagePayStatus", IssuePackagePayStatus.values());
		return prefix+"edit";
	}
	@RequestMapping(value = "/verifyPay", method = RequestMethod.POST)
	public String verifyPayPost(@Valid @ModelAttribute("pageModel") CarIssuePackageInfo packInfo, HttpServletRequest request, HttpServletResponse response, Model model) throws ManagerException
	{
		CarIssuePackageInfo verifyPay = issueRecordsManager.verifyPay(LoginHelper.getLoginUser(request), packInfo);
		doUpdateLog(request, CssServiceLogType.ADMINISTRATION_PKG_ISSUERECORDS_PAY, verifyPay);
		addSuccess(response, "确认支付成功！");
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		model.addAttribute("payStatus", IssuePackagePayStatus.values());
		model.addAttribute("vo", verifyPay);
		return prefix+"result";
	}
	@RequestMapping(value = "/verifyRepeal/{id}", method = RequestMethod.POST)
	public void verifyRepealPost(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response, Model model) throws ManagerException
	{
		CarIssuePackageInfo verifyRepeal = issueRecordsManager.verifyRepeal(LoginHelper.getLoginUser(request), id);
		doUpdateLog(request, CssServiceLogType.ADMINISTRATION_PKG_ISSUERECORDS_REPEAL, verifyRepeal);
		addSuccess(response, "作废成功！");
	}
	@RequestMapping(value = "/view/{id}")
	public String viewDisputeMessage(@PathVariable("id") String id, Model model) throws ManagerException
	{
		model.addAttribute("pageModel", issueRecordsManager.findById(id));
		model.addAttribute("issueStatusTypes", IssueStatusType.values());
		model.addAttribute("issuePackagePayStatus", IssuePackagePayStatus.values());
		return prefix + "view";
	}
}
