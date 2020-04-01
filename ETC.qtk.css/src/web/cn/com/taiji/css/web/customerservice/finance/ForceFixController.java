/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
package cn.com.taiji.css.web.customerservice.finance;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.finance.ForcefixManager;
import cn.com.taiji.css.model.customerservice.finance.ForcefixCardBalanceRequest;
import cn.com.taiji.css.model.customerservice.finance.ForcefixCardBalanceResponse;
import cn.com.taiji.css.model.customerservice.finance.ForcefixRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.ChargeDetail;


@Controller
@RequestMapping("/customerservice/finance/forcefix")
@ComponentScan
public class ForceFixController  extends MyLogController{
	private final String prefix = "customerservice/finance/forcefix/";
	 
	
	@Autowired
	private ForcefixManager forcefixManager;

	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") ForcefixRequest queryModel, Model model)
	{
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") ForcefixRequest queryModel,HttpServletRequest request,User user ,Model model) throws ManagerException
	{
		LargePagination<ChargeDetail> pagn = forcefixManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
	
	@RequestMapping(value = "/info/{chargeId}", method = RequestMethod.GET)
	public String info(@PathVariable("chargeId") String chargeId,@ModelAttribute("pageModel1") ForcefixCardBalanceRequest queryModel,HttpServletRequest request, Model model) throws ManagerException 
	{   
		ChargeDetail chargeDetail=forcefixManager.findBychargeId(chargeId,model);
		model.addAttribute("pageModel",chargeDetail);
		return prefix + "info";
	}
	
	@RequestMapping(value = "/showPhoto/{chargeId}",method=RequestMethod.GET)
	public String view(@PathVariable("chargeId") String chargeId,HttpServletRequest request,Model model) throws ManagerException{
		model = forcefixManager.modelAdd(chargeId,model);
		return prefix+"view";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody ForcefixCardBalanceRequest queryModel, HttpServletRequest request,HttpServletResponse response,Model model) throws ManagerException, ApiRequestException, IOException
	{
		
		ForcefixCardBalanceResponse	forcefixCardBalanceResponse=forcefixManager.updateStatus(queryModel.getChargeId(),queryModel.getCardBalance(),LoginHelper.getLoginUser(request));
		super.doUpdateLog(request, CssServiceLogType.CUSTOMERSERVICE_FINANCE_FORCEFIX,queryModel );
		try {
			HttpMimeResponseHelper.responseJson(forcefixCardBalanceResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/deleteDetil", method = RequestMethod.POST)
	public void delete(@RequestBody ForcefixCardBalanceRequest queryModel, HttpServletRequest request,HttpServletResponse response,Model model) throws ManagerException, ApiRequestException, IOException
	{
		    ForcefixCardBalanceResponse	forcefixCardBalanceResponse=forcefixManager.deleteStatus(queryModel.getChargeId(),queryModel.getCardBalance());
			super.doDeleteLog(request, CssServiceLogType.CUSTOMERSERVICE_FINANCE_FORCEFIX,queryModel );
		try {
			HttpMimeResponseHelper.responseJson(forcefixCardBalanceResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}

