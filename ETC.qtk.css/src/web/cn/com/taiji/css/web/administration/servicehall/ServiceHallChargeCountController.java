package cn.com.taiji.css.web.administration.servicehall;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.serviceHall.ServiceHallChargeCountManager;
import cn.com.taiji.css.model.request.serviceHall.ServiceHallChargeCountRequest;
import cn.com.taiji.css.web.MyLogController;
@Controller
@RequestMapping("/administration/channelchargecount/")
public class ServiceHallChargeCountController extends MyLogController{
	private final String prefix = "administration/channelchargecount/";
	@Autowired
	private ServiceHallChargeCountManager serviceHallChargeCountManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") ServiceHallChargeCountRequest queryModel, Model model)
	{
		return prefix+"manage";
	}
  
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") ServiceHallChargeCountRequest queryModel,HttpServletRequest request, Model model) throws ManagerException
	{   User user = LoginHelper.getLoginUser(request);
	    queryModel.setServiceHallId(user.getStaff().getServiceHallId());
		logger.info("serviceHallId:"+user.getStaff().getServiceHallId());
	    Pagination pagn = serviceHallChargeCountManager.page(queryModel);
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
}
