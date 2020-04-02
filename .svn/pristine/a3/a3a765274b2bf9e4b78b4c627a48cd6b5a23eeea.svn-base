package cn.com.taiji.css.web.apply.baseinfo;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.css.entity.dict.AccountStatus;
import cn.com.taiji.css.manager.apply.baseinfo.AccountManagementManager;
import cn.com.taiji.css.model.apply.customermanager.AccountManagementRequest;

@Controller
@RequestMapping("/apply/baseinfo/accountmanagement")
public class AccountManagementController {
     
	@Autowired
	private AccountManagementManager accountManagementManager;
	private final String prefix = "/apply/baseinfo/accountmanagement/";
	
	 @RequestMapping(value = "/manage", method = RequestMethod.GET)
		public String manageGet(@ModelAttribute("queryModel") AccountManagementRequest queryModel, Model model,HttpServletRequest req)
		{
		model.addAttribute("accountStatus", AccountStatus.values());
			return prefix + "manage";
		}
	   
	     
	   @RequestMapping(value = "/manage", method = RequestMethod.POST)
		public String managePost(@Valid @ModelAttribute("queryModel")AccountManagementRequest queryModel, Model model ,HttpServletRequest req)
		{
		   model.addAttribute("pagn", this.accountManagementManager.queryPage(queryModel));
		   return prefix + "queryResult";
		}
	   
}
