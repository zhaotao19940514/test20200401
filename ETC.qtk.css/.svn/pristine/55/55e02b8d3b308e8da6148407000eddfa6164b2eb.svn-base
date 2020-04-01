/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
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
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.card.CardInformationChangeManager;
import cn.com.taiji.css.manager.customerservice.card.UnhangManager;
import cn.com.taiji.css.model.customerservice.card.CardInformationChangeRequest;
import cn.com.taiji.css.model.customerservice.card.CardInformationChangeResponse;
import cn.com.taiji.css.model.customerservice.card.UnhangRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardStatusChangeResponse;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.dict.CardUploadStatus;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;

/**
 * @ClassName UnhangController.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
@Controller
@RequestMapping("/customerservice/card/unhang")
public class UnhangController extends MyLogController {
	private final String prefix = "customerservice/card/unhang/";
	
	@Autowired
	private UnhangManager unhangManager;
	
	@Autowired
	private CardInformationChangeManager cardInformationChangeManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") UnhangRequest queryModel, Model model)
	{
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") UnhangRequest queryModel, Model model, HttpServletRequest request) throws ManagerException
	{
		LargePagination<CardInfo> pagn = unhangManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("pagn", pagn);
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("CustomerIDType",CustomerIDType.values());
		model.addAttribute("CardUploadStatus",CardUploadStatus.values());
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
	
	@RequestMapping(value = "/saveInformation", method = RequestMethod.POST)
	public void saveInformation(@RequestBody CardInformationChangeRequest queryModel, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		CardInformationChangeResponse  cardInformationChangeResponse=new CardInformationChangeResponse();
		cardInformationChangeResponse =  cardInformationChangeManager.cardInfoChange(queryModel,LoginHelper.getLoginUser(request));
		CardInfo card=new CardInfo();
		card.setCardId(queryModel.getCardId());
		try {
			CardStatusChangeResponse cardStatusChangeResponse=unhangManager.doUnHangCard(queryModel.getCardId(),LoginHelper.getLoginUser(request));
			cardInformationChangeResponse.setMessage(cardStatusChangeResponse.getMessage());
			cardInformationChangeResponse.setStatus(cardStatusChangeResponse.getStatus());
			super.doUpdateLog(request, CssServiceLogType.CUSTOMERSERVICE_CARD_UNHANG,card);
		} catch (Exception e1) {
			e1.printStackTrace();
			cardInformationChangeResponse.setMessage(e1+"");
		}
		try {
			HttpMimeResponseHelper.responseJson(cardInformationChangeResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

