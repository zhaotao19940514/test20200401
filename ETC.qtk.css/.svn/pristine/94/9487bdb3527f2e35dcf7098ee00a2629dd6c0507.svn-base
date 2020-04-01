
package cn.com.taiji.css.web.customerservice.finance;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.CssOperateLogType;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.finance.BalancesupplyManager;
import cn.com.taiji.css.model.customerservice.finance.BalancesupplyRequest;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeRequest;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeResponse;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;


@Controller
@RequestMapping("/customerservice/finance/balancesupply")
public class BalanceSupplyController extends MyLogController {
	private final String prefix = "customerservice/finance/balancesupply/";
	

	@Autowired
	private BalancesupplyManager balancesupplyManager;
	

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") BalancesupplyRequest queryModel, Model model)
	{
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		return prefix+"cardOperations";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") BalancesupplyRequest queryModel,HttpServletRequest request,Model model)throws ManagerException
	{ 
		queryModel.validate();
		User user=LoginHelper.getLoginUser(request);
		queryModel.setAgencyId(user.getStaff().getServiceHall().getAgencyId());
		 LargePagination<CardInfo>pagn = balancesupplyManager.queryPage(queryModel);
		 model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
	
	@RequestMapping(value = "/edit/{cardId}",method = RequestMethod.POST)
	public String info1(@PathVariable("cardId") String cardId, Model model) throws Exception
	{
		return prefix + "edit";
	}
	
	@RequestMapping(value = "/info/{cardId}",method = RequestMethod.GET)
	public String info(@PathVariable("cardId") String cardId, Model model) throws Exception
	{
		if(cardId==null || cardId=="") {
			throw new ManagerException("旧卡卡号不能为空！");
		}
		CardInfo cardinfo =balancesupplyManager.findByCardId(cardId, model);
		BalancesupplyRequest req=new BalancesupplyRequest();
		req.setOldCardId(cardinfo.getCardId());
		req.setAgencyId(cardinfo.getAgencyId());
		req.setCustomerId(cardinfo.getCustomerId());
		model.addAttribute("pageModel", req);
		return prefix + "info";
	}
	
	@RequestMapping(value = "/cardReplace",method = RequestMethod.POST)
	public void cardReplace(@Valid @RequestBody BalancesupplyRequest queryModel,HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException 
	{
		queryModel.validateForSupply();
		CardrechargeResponse cardrechargeResponse=balancesupplyManager.cardReplace(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(cardrechargeResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("余额补领圈存检测接口调用失败");
		}
	}
	
	
	@RequestMapping(value = "/cardChargeByCOS",method = RequestMethod.POST)
	public void CardChargeByCOS(@Valid @RequestBody CardrechargeRequest queryModel,HttpServletResponse response,HttpServletRequest request, Model model) throws ManagerException 
	{
		CardrechargeResponse CardrechargeResponse=balancesupplyManager.cardChargeByCOS(queryModel,LoginHelper.getLoginUser(request));
		try {
			//圈存申请产生流水后把状态改为1   避免重复余额补领 
			if(CardrechargeResponse.getStatus()==1 && CardrechargeResponse.getCommand()!=null ) {
				balancesupplyManager.updateOperateStatus(queryModel.getOldCardId(), 1);
				super.doSysLog(request,  CssServiceLogType.CUSTOMERSERVICE_FINANCE_BALANCESUPPLY, CssOperateLogType.REQUEST, null, "余额补领业务", queryModel);
			}
			HttpMimeResponseHelper.responseJson(CardrechargeResponse.toJson(), response);
			addSuccess(response, "圈存申请成功");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("圈存申请失败");
		}
	}
	
	
	
	@RequestMapping(value = "/cardChargeConfirmByCOS",method = RequestMethod.POST)
	public void CardChargeConfirmByCOS(@RequestBody CardrechargeRequest queryModel, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException 
	{
		CardrechargeResponse cardrechargeResponse=balancesupplyManager.cardChargeConfirmByCOS(queryModel,LoginHelper.getLoginUser(request));
		if(cardrechargeResponse.getStatus()==1) {
			try {
				HttpMimeResponseHelper.responseJson(cardrechargeResponse.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ManagerException("余额补领确认返回失败");
			}
		}
	}
	
	
	
	
	
	
}

