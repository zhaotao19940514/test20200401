package cn.com.taiji.css.web.customerservice.card;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.card.CardPinUnlockingManager;
import cn.com.taiji.css.model.customerservice.card.CardPinUnlockingRequset;
import cn.com.taiji.css.model.customerservice.card.CardPinUnlockingResponse;
import cn.com.taiji.css.web.MyLogController;

@Controller
@RequestMapping("/customerservice/card/cardPinUnlocking")
public class CardPinUnlockingController extends MyLogController {
	
	@Autowired
	private CardPinUnlockingManager cardPinUnlockingManager;
	
	private final String prefix = "/customerservice/card/cardPinUnlocking/";

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(Model model,HttpServletRequest req)
	{
    	model.addAttribute("reqModel", new CardPinUnlockingRequset());
		return prefix + "manage";
	}
   
   @RequestMapping(value = "/unlocking", method = RequestMethod.POST)
   public void cardPinUnlocking(@Valid @RequestBody CardPinUnlockingRequset appReq,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException {
		
		logger.info("------"+appReq.toJson());
		CardPinUnlockingResponse cardPinUnlocking = cardPinUnlockingManager.CardPinUnlocking(appReq,LoginHelper.getLoginUser(request));
		if(cardPinUnlocking != null && "1".equals(cardPinUnlocking.getCardUnblockStatus())) {
			doAddLog(request, CssServiceLogType.CUSTOMERSERVICE_CARD_PINUNLOCKING, cardPinUnlocking);
		}
		logger.info("------"+cardPinUnlocking.toJson());
		try {
			HttpMimeResponseHelper.responseJson(cardPinUnlocking.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("返回数据失败");
		}
		
	}
}
