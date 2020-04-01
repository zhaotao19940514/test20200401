package cn.com.taiji.css.web.apply.baseinfo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.web.BaseController;
import cn.com.taiji.css.manager.apply.baseinfo.CommonQuerymanager;
import cn.com.taiji.qtk.entity.ServiceHall;

@Controller
@RequestMapping("/apply/baseinfo/commonQuery")
public class CommonQueryController extends BaseController {
	@Autowired
	private CommonQuerymanager commonQuerymanager;
	
	private final String prefix = "/apply/baseinfo/commonQuery/";
	
	@RequestMapping(value = "/manage")
	public String manageGet(Model model,HttpServletRequest req)
	{
		return prefix + "manage";
	}
	@RequestMapping(value = "/agencyIdView/{agencyId}")
	public String agencyView(@PathVariable("agencyId") String agencyId, HttpServletRequest request, Model model) throws ManagerException
	{
		model.addAttribute("viewModel", commonQuerymanager.findAgencyByAgencyId(agencyId));
		return prefix + "agencyView";
	}
	@RequestMapping(value = "/servicehallIdView/{servicehallId}")
	public String servicehallView(@PathVariable("servicehallId") String servicehallId, HttpServletRequest request, Model model) throws ManagerException
	{
		ServiceHall serviceHall = commonQuerymanager.findServiceHallByServicehallId(servicehallId);
		model.addAttribute("serviceHall", serviceHall);
		if(serviceHall != null && serviceHall.getAgencyId() != null) {
			model.addAttribute("agency", commonQuerymanager.findAgencyByAgencyId(serviceHall.getAgencyId()));
		}
		return prefix + "servicehallView";
	}
	@RequestMapping(value = "/staffIdView/{staffId}")
	public String staffView(@PathVariable("staffId") String staffId, HttpServletRequest request, Model model) throws ManagerException
	{
		model.addAttribute("userModel", commonQuerymanager.findUserByStaffId(staffId));
		model.addAttribute("staffModel", commonQuerymanager.findStaffByStaffId(staffId));
		return prefix + "staffView";
	}
	@RequestMapping(value = "/userByStaffIdView/{staffId}")
	public String userByStaffIdView(@PathVariable("staffId") String staffId, HttpServletRequest request, Model model) throws ManagerException
	{
		model.addAttribute("viewModel", commonQuerymanager.findUserByStaffId(staffId));
		return prefix + "userView";
	}
	@RequestMapping(value = "/userByLoginNameView/{loginName}")
	public String userByLoginNameView(@PathVariable("loginName") String loginName, HttpServletRequest request, Model model) throws ManagerException
	{
		model.addAttribute("viewModel", commonQuerymanager.findUserByLoginName(loginName));
		return prefix + "userView";
	}
	@RequestMapping(value = "/issuePackageNumView/{packageNum}")
	public String issuePackView(@PathVariable("packageNum") String packageNum, HttpServletRequest request, Model model) throws ManagerException
	{
		model.addAttribute("viewModel", commonQuerymanager.findIssuePackageByPackageNum(packageNum));
		return prefix + "issuePackView";
	}
}
