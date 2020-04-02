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
import java.util.List;

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
import cn.com.taiji.css.entity.dict.CssOperateLogType;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.finance.CardrechargefixManager;
import cn.com.taiji.css.manager.customerservice.finance.ForcefixManager;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeResponse;
import cn.com.taiji.css.model.customerservice.finance.CardrechargefixRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.ChargeDetail;


@Controller
@RequestMapping("/customerservice/finance/cardrechargefix")
public class CardRechargeFixController extends MyLogController{
	private final String prefix = "customerservice/finance/cardrechargefix/";
	
	@Autowired
	private CardrechargefixManager cardrechargefixManager;
	
	@Autowired
	private ForcefixManager forcefixManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CardrechargefixRequest queryModel, Model model)
	{
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CardrechargefixRequest queryModel,HttpServletRequest request, Model model) throws ManagerException
	{
		queryModel.validate();
		List<ChargeDetail>  pagn = cardrechargefixManager.queryPage(queryModel,request);
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/info/{chargeId}",method = RequestMethod.POST)
	public String info(@PathVariable("chargeId") String chargeId, Model model) throws Exception
	{
		model.addAttribute("pageModel", forcefixManager.findBychargeId(chargeId, model));
		return prefix + "info";
	}
	
	
	@RequestMapping(value = "/cardChargeCheck",method = RequestMethod.POST)
	public void cardChargeCheck(@Valid @RequestBody CardrechargefixRequest queryModel,HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException 
	{
		CardrechargeResponse CardrechargeResponse=cardrechargefixManager.CardChargeCheck(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(CardrechargeResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("圈存检测失败");
		}
	}
	
	
	@RequestMapping(value = "/cardChargeFix",method = RequestMethod.POST)
	public void CardChargeFix(@Valid @RequestBody CardrechargefixRequest queryModel,HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException 
	{
		CardrechargeResponse CardrechargeResponse=cardrechargefixManager.CardChargeFix(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(CardrechargeResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("圈存修复失败");
		}
	}
	
	
	@RequestMapping(value = "/cardChargeByCOS",method = RequestMethod.POST)
	public void CardChargeByCOS(@Valid @RequestBody CardrechargefixRequest queryModel,HttpServletResponse response,HttpServletRequest request, Model model) throws ManagerException 
	{
		CardrechargeResponse CardrechargeResponse=cardrechargefixManager.CardChargeByCOS(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(CardrechargeResponse.toJson(), response);
			addSuccess(response, "圈存申请成功");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("圈存申请失败");
		}
	}
	
	@RequestMapping(value = "/cardChargeConfirmByCOS",method = RequestMethod.POST)
	public void CardChargeConfirmByCOS(@RequestBody CardrechargefixRequest queryModel, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException 
	{
		CardrechargeResponse CardrechargeResponse=cardrechargefixManager.CardChargeConfirmByCOS(queryModel,LoginHelper.getLoginUser(request));
		if(CardrechargeResponse.getPostBalance()!=null&&CardrechargeResponse.getPostBalance()>0) {
			super.doSysLog(request,  CssServiceLogType.CUSTOMERSERVICE_FINANCE_CARDRECHARGEFIX, CssOperateLogType.REQUEST, null, "圈存修复业务", queryModel);
		}
		try {
			HttpMimeResponseHelper.responseJson(CardrechargeResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("圈存修复确认失败");
		}
	}
	
	
}

