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
import cn.com.taiji.css.entity.dict.CssOperateLogType;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.finance.CardrechargeManager;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeRequest;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeResponse;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.ChargeDetail;
import cn.com.taiji.qtk.entity.ServiceHall;
import cn.com.taiji.qtk.repo.jpa.ServiceHallRepo;

@Controller
@RequestMapping("/customerservice/finance/cardrecharge")
public class CardRechargeController extends MyLogController{
	private final String prefix = "customerservice/finance/cardrecharge/";
	
	@Autowired
	private CardrechargeManager cardrechargeManager;
	@Autowired
	private ServiceHallRepo serviceHallRepo;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CardrechargeRequest queryModel,HttpServletRequest request, Model model)
	{
		model.addAttribute("chargeType", cardrechargeManager.setChargeType(queryModel,LoginHelper.getLoginUser(request)));
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CardrechargeRequest queryModel,HttpServletRequest request,Model model) throws ManagerException
	{
		queryModel.validate();
		LargePagination<ChargeDetail> pagn = cardrechargeManager.queryPage(queryModel,request);
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
	
	@RequestMapping(value = "/view/{channelId}",method = RequestMethod.GET)
	public String viewPost(@PathVariable("channelId") String channelId,HttpServletRequest request,Model model) throws ManagerException
	{
		ServiceHall pageModel = serviceHallRepo.findByServiceHallId(channelId);
		model.addAttribute("pageModel", pageModel);
		return prefix+"view";
	}
	
	@RequestMapping(value = "/findAccountBalanceByCardId", method = RequestMethod.POST)
	public void findAccountBalanceByCardId(@Valid @RequestBody CardrechargeRequest queryModel,HttpServletRequest request,HttpServletResponse response, Model model) throws ManagerException
	{
		CardrechargeResponse cardrechargeResponse=cardrechargeManager.FindAccountBalanceByCardId(queryModel, LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(cardrechargeResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	//圈存检测-------检测此卡有无半条流水 等情况.满足圈存条件则可以调用圈存申请
	@RequestMapping(value = "/cardChargeCheck",method = RequestMethod.POST)
	public void cardChargeCheck( @RequestBody CardrechargeRequest queryModel,HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException 
	{
		CardrechargeResponse  cardrechargeResponse=cardrechargeManager.CardChargeCheck(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(cardrechargeResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//圈存申请 -------将此条流水写入库中,将其status改为0( 确认状态),然后进行写卡操作
	@RequestMapping(value = "/cardChargeByCOS",method = RequestMethod.POST)
	public void CardChargeByCOS(@Valid @RequestBody CardrechargeRequest queryModel,HttpServletResponse response,HttpServletRequest request, Model model) throws ManagerException 
	{
		CardrechargeResponse CardrechargeResponse=cardrechargeManager.CardChargeByCOS(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(CardrechargeResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//圈存确认-------确认成功后将数据库中的status改为1  圈存结束  未确认成功则为0  成为半条流水,提示用户进行圈存修复
	@RequestMapping(value = "/cardChargeConfirmByCOS",method = RequestMethod.POST)
	public void CardChargeConfirmByCOS(@RequestBody CardrechargeRequest queryModel, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException 
	{
		CardrechargeResponse cardrechargeResponse=cardrechargeManager.CardChargeConfirmByCOS(queryModel,LoginHelper.getLoginUser(request));
		try {
			if(cardrechargeResponse==null) {
				cardrechargeResponse=new CardrechargeResponse();
				cardrechargeResponse.setMessage("圈存确认接口调用失败");
			}
			if(cardrechargeResponse.getPostBalance()!=null && cardrechargeResponse.getPostBalance()>0) {
				super.doSysLog(request,  CssServiceLogType.CUSTOMERSERVICE_FINANCE_CARDRECHARGE, CssOperateLogType.REQUEST, null, "圈存业务", queryModel);
			}
			HttpMimeResponseHelper.responseJson(cardrechargeResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("圈存确认失败");
		}
	}
	
	
	
}

