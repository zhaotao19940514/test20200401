package cn.com.taiji.css.web.customerservice.finance;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.apply.baseinfo.CustomerManager;
import cn.com.taiji.css.model.apply.customermanager.CustomerManagerRequest;
import cn.com.taiji.css.model.apply.customermanager.CustomerManagerResponse;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;


@Controller
@RequestMapping("/customerservice/finance/useraccountmanage")
public class AccountCustomerManagerController extends MyLogController {
	
	@Autowired
	private CustomerManager customerManager;
	
	private final String prefix = "/customerservice/finance/useraccountmanage/";
	
	 @RequestMapping(value = "/manage", method = RequestMethod.GET)
		public String manageGet(@ModelAttribute("queryModel") CustomerManagerRequest queryModel, Model model,HttpServletRequest req)
		{
 			model.addAttribute("types", CustomerIDType.values());
			return prefix + "manage";
		}
	   
	     
	   @RequestMapping(value = "/manage", method = RequestMethod.POST)
		public String managePost(@ModelAttribute("queryModel") CustomerManagerRequest queryModel, Model model ,HttpServletRequest req)
		{
		   model.addAttribute("types", CustomerIDType.values());
		   model.addAttribute("pagn", this.customerManager.queryPage(queryModel));
		   return prefix + "queryResult";
		}
	   
	   
	   @RequestMapping(value = "/info/{customerId}", method = RequestMethod.GET)
		public String infoPost(@PathVariable("customerId") String customerId,@ModelAttribute("pageModel1") CustomerManagerRequest queryModel, Model model ,HttpServletRequest req) throws ManagerException
		{
		   model.addAttribute("pageModel1", this.customerManager.findByCustomerId(customerId));
		   return prefix + "info";
		}
	   
	   
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public void update(@RequestBody CustomerManagerRequest customerInfo, HttpServletRequest request,HttpServletResponse response,Model model) throws ManagerException, ApiRequestException, IOException
		{
			
			CustomerManagerResponse	customerManagerResponse=customerManager.updatePassWord(customerInfo,LoginHelper.getLoginUser(request));
			if(customerManagerResponse!=null && customerManagerResponse.getStatus()==1) {
				super.doUpdateLog(request, CssServiceLogType.CUSTOMERSERVICE_FINANCE_USERACCOUNTMANAGE, customerInfo);
			}
			try {
				HttpMimeResponseHelper.responseJson(customerManagerResponse.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@RequestMapping(value = "/initialization", method = RequestMethod.POST)
		public void initialization(@RequestBody CustomerManagerRequest customerInfo, HttpServletRequest request,HttpServletResponse response,Model model) throws ManagerException, ApiRequestException, IOException
		{
			
			CustomerManagerResponse	customerManagerResponse=customerManager.initializationPassWord(customerInfo,LoginHelper.getLoginUser(request));
			if(customerManagerResponse!=null && customerManagerResponse.getStatus()==1) {
				super.doUpdateLog(request, CssServiceLogType.CUSTOMERSERVICE_FINANCE_INITIALIZATIONUSERACCOUNT, customerInfo);
			}
			try {
				HttpMimeResponseHelper.responseJson(customerManagerResponse.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	   
}
