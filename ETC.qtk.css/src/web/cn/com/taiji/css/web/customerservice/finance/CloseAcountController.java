
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
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.dict.CssOperateLogType;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.customerservice.finance.CloseAcountManager;
import cn.com.taiji.css.model.customerservice.finance.CloseAcountRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.dsi.model.comm.protocol.finance.AccountCancelResponse;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;


@Controller		
@RequestMapping("/customerservice/finance/closeaccount")
public class CloseAcountController extends MyLogController {
	private final String prefix = "customerservice/finance/closeaccount/";
	
	@Autowired
	private CloseAcountManager closeAcountManager;
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CloseAcountRequest queryModel, Model model)
	{
		model.addAttribute("customerIdType", CustomerIDType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CloseAcountRequest queryModel,HttpServletRequest request, Model model) throws ManagerException
	{
		queryModel.validate();
		LargePagination<CustomerInfo> customerInfo =closeAcountManager.queryPage(queryModel,request);
		model.addAttribute("pagn", customerInfo);
		return prefix+"queryResult";
    }
	
	
	@RequestMapping(value = "/edit/{customerId}", method = RequestMethod.GET)
	public String edit(@PathVariable("customerId") String customerId,@ModelAttribute("pageModel") CloseAcountRequest queryModel,HttpServletRequest request,HttpServletResponse response, Model model) throws ManagerException
	{
		return prefix + "edit";
    }
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public void deleteUser( @RequestBody CloseAcountRequest queryModel,HttpServletRequest request,HttpServletResponse response, Model model) throws ManagerException
	{
			AccountCancelResponse	accountCancelResponse =closeAcountManager.accountCancel(queryModel,request);
			if(accountCancelResponse!=null&&accountCancelResponse.getStatus()==1) {
				super.doSysLog(request,  CssServiceLogType.CUSTOMERSERVICE_FINANCE_CANCELACCOUNT, CssOperateLogType.UPDATE, null, "销户业务", queryModel);
			}
			try {
				HttpMimeResponseHelper.responseJson(accountCancelResponse.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
	
	
	
	
}

