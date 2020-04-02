package cn.com.taiji.css.web.customerservice.card;

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
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.card.CardInformationChangeManager;
import cn.com.taiji.css.model.customerservice.card.CancelRequest;
import cn.com.taiji.css.model.customerservice.card.CardInformationChangeRequest;
import cn.com.taiji.css.model.customerservice.card.CardInformationChangeResponse;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;


@Controller
@RequestMapping("/customerservice/card/cardInformationChange")
public class CardInformationChangeController extends MyLogController {
	private final String prefix = "customerservice/card/cardInformationChange/";
	
	@Autowired
	private CardInformationChangeManager cardInformationChangeManager;
	

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CancelRequest queryModel, Model model)
	{
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CancelRequest queryModel, Model model, HttpServletRequest request) throws ManagerException
	{
		LargePagination<CardInfo> pagn = cardInformationChangeManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String cardId,@ModelAttribute("queryModel") CardInformationChangeRequest queryModel, HttpServletRequest request, Model model) throws ManagerException
	{
		queryModel = cardInformationChangeManager.modelAdd(cardId);
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		model.addAttribute("pageModel", queryModel);
		return prefix + "edit";
	}

	/*@RequestMapping(value = "/cardInfoChanfe", method = RequestMethod.POST)
	public void cardInfoChanfe( @RequestBody CardInfoChangeApplyByCosRequest queryModel, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		CardInfoChangeApplyByCosResponse  cardInfoChangeApplyByCosResponse=new CardInfoChangeApplyByCosResponse();
		cardInfoChangeApplyByCosResponse = cardInformationChangeManager.cardInfoChange(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(cardInfoChangeApplyByCosResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	@RequestMapping(value = "/saveInformation", method = RequestMethod.POST)
	public void saveInformation(@RequestBody CardInformationChangeRequest queryModel, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		CardInformationChangeResponse  cardInformationChangeResponse=new CardInformationChangeResponse();
		cardInformationChangeResponse =  cardInformationChangeManager.cardInfoChange(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(cardInformationChangeResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/applyCardOrderConfirm", method = RequestMethod.POST)
	public void applyCardOrderConfirm(@RequestBody CardInformationChangeRequest queryModel, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		CardInformationChangeResponse cardInformationChangeResponse = cardInformationChangeManager.applyCardOrderConfirm(queryModel,LoginHelper.getLoginUser(request));
		try {
			if(cardInformationChangeResponse.getStatus()==1) {
				super.doSysLog(request,  CssServiceLogType.CUSTOMERSERVICE_CARD_REWRITE, CssOperateLogType.REQUEST, null, "卡重写", queryModel);
			}
			HttpMimeResponseHelper.responseJson(cardInformationChangeResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}

