package cn.com.taiji.css.web.apply.quickapply;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.css.entity.dict.CustomerStatus;
import cn.com.taiji.css.manager.apply.quickapply.AccountRechargeManager;
import cn.com.taiji.css.model.apply.recharge.AccountRechargeRequest;
import cn.com.taiji.css.web.BaseLogController;

@Controller
@RequestMapping("/apply/quickapply/recharge")
public class AccountRechargeController extends BaseLogController {
	        @Autowired
	        private AccountRechargeManager accountRechargeManager;
	 
	        private final String prefix = "/apply/quickapply/recharge/";
			/**
			 * 查询
			 * @param queryModel
			 * @param model
			 * @param req
			 * @return
			 */
	        @RequestMapping(value = "/manage", method = RequestMethod.GET)
			public String manageGet(@ModelAttribute("queryModel") AccountRechargeRequest queryModel, Model model,HttpServletRequest req)
			{
	        	model.addAttribute("customerStatus", CustomerStatus.values());
				return prefix + "manage";
			}
		   
		   
		   @RequestMapping(value = "/manage", method = RequestMethod.POST)
			public String managePost(@Valid @ModelAttribute("queryModel")AccountRechargeRequest queryModel, Model model ,HttpServletRequest req)
			{
			   model.addAttribute("pagn", this.accountRechargeManager.queryPage(queryModel));
			   return prefix + "queryResult";
			}
}
