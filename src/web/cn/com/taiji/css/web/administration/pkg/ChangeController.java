package cn.com.taiji.css.web.administration.pkg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.ServiceHandleException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.administration.pkg.ChangeManager;
import cn.com.taiji.css.model.administration.pkg.ChangeRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardPackageChangeResponse;
import cn.com.taiji.qtk.entity.CardPackageView;
import cn.com.taiji.qtk.repo.jpa.CardPackageViewRepo;

@Controller
@RequestMapping("/administration/package/change")
public class ChangeController extends MyLogController {
	private final String prefix = "/administration/package/change/";
	
	@Autowired
	private ChangeManager changeManager;
	@Autowired
	private CardPackageViewRepo cardPackageViewRepo;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") ChangeRequest queryModel, Model model)
	{
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") ChangeRequest queryModel, Model model)
	{
		Pagination pagn = changeManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id ,HttpServletRequest request, Model model) throws ServiceHandleException
	{   model.addAttribute("packageName",changeManager.findAllPackage());
		model.addAttribute("pageModel", changeManager.findById(id));
		return prefix + "edit";
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(@Valid @ModelAttribute("pageModel") CardPackageView cardPackageView, HttpServletRequest request, Model model,
			HttpServletResponse response) throws JsonManagerException, ManagerException
	{   
		User user =LoginHelper.getLoginUser(request);
		CardPackageView cpv = new CardPackageView();
		CardPackageChangeResponse res = changeManager.updatePackage(cardPackageView,user);;
		addSuccess(response, res.getMessage());
		CardPackageView cp = cardPackageViewRepo.findByCardId(cardPackageView.getCardId());
        BeanUtils.copyProperties(cp, cpv);
        model.addAttribute("vo", cpv);
		super.doUpdateLog(request, CssServiceLogType.ADMINISTRATION_PKG_CHANGE, cardPackageView);; 
		return prefix + "result";
		
	}
}
