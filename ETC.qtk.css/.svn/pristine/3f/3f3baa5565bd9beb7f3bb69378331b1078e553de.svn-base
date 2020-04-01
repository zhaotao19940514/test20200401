
package cn.com.taiji.css.web.customerservice.finance;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.css.entity.dict.CssOperateLogType;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.finance.CardrechargeManager;
import cn.com.taiji.css.manager.customerservice.finance.RechargeManager;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeRequest;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeResponse;
import cn.com.taiji.css.model.customerservice.finance.RechargeRequest;
import cn.com.taiji.css.model.customerservice.finance.RechargeResponse;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.dsi.model.comm.protocol.finance.AccountChargeResponse;
import cn.com.taiji.qtk.entity.dict.ChargeType;
import cn.com.taiji.qtk.entity.dict.TradeType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;


@Controller
@RequestMapping("/customerservice/finance/recharge")
public class RechargeController extends MyLogController {
	private final String prefix = "customerservice/finance/recharge/";
	
	@Autowired
	private CardrechargeManager cardrechargeManager;
	@Autowired
	private RechargeManager rechargeManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") RechargeRequest queryModel, Model model)
	{
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		model.addAttribute("chargeType", ChargeType.getCardChargeEnums());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") RechargeRequest queryModel,HttpServletRequest request, Model model) throws ManagerException
	{
		queryModel.validate();
		RechargeResponse rechargeResponse =rechargeManager.accountTradesQuery(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("TradeType", TradeType.values());
		model.addAttribute("pagn", rechargeResponse);
		
		return prefix+"queryResult";
    }
	
	
	@RequestMapping(value = "/findAccountBalanceByCardId", method = RequestMethod.POST)
	public void findAccountBalanceByCardId(@Valid @RequestBody CardrechargeRequest queryModel,HttpServletRequest request,HttpServletResponse response, Model model) throws ManagerException
	{
		CardrechargeResponse cardrechargeResponse=cardrechargeManager.FindAccountBalanceByCardId(queryModel, LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(cardrechargeResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			cardrechargeResponse.setMessage("该卡没有对应的用户账户");
		}
    }
	
	@RequestMapping(value = "/accountCharge", method = RequestMethod.POST)
	public void accountCharge(@Valid @RequestBody RechargeRequest queryModel,HttpServletRequest request,HttpServletResponse response, Model model) throws ManagerException
	{
			AccountChargeResponse accountChargeResponse =rechargeManager.accountCharge(queryModel,LoginHelper.getLoginUser(request));
			if(accountChargeResponse!=null&&accountChargeResponse.getStatus()==1) {
				super.doSysLog(request,  CssServiceLogType.CUSTOMERSERVICE_FINANCE_RECHARGE, CssOperateLogType.REQUEST, null, "账户充值业务", queryModel);
			}
			try {
				HttpMimeResponseHelper.responseJson(accountChargeResponse.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
			}
    }
	
	
	
	
}

