package cn.com.taiji.css.web.apply.quickapply;

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
import cn.com.taiji.css.manager.apply.baseinfo.CardManager;
import cn.com.taiji.css.manager.apply.quickapply.ObuBindingManager;
import cn.com.taiji.css.manager.customerservice.card.AppCardStatusChangeResponse;
import cn.com.taiji.css.model.apply.quickapply.CardObuBindingRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CardInfo;

@Controller
@RequestMapping("/apply/quickapply/obuBinding")
public class ObuBindingController extends MyLogController{

    @Autowired
    private ObuBindingManager obuBindingManager;
    @Autowired
    private CardManager cardManager;

    private final String prefix = "/apply/quickapply/obuBinding/";

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(Model model,HttpServletRequest req)
	{
		return prefix + "manage";
	}
   
   @RequestMapping(value = "/cardObuBinding", method = RequestMethod.POST)
	public void cardObuBinding(@Valid @RequestBody CardObuBindingRequest appReq,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException {
		
		logger.info("------"+appReq.toJson());
		
		AppCardStatusChangeResponse cardObuBinding = obuBindingManager.cardObuBinding(appReq,LoginHelper.getLoginUser(request));
		if(cardObuBinding.getOrderStatus() != null && cardObuBinding.getOrderStatus() == 2 && cardObuBinding.getSuccess()) {
			CardInfo cardInfo = cardManager.findByCardId(appReq.getCardId());
			super.doUpdateLog(request, CssServiceLogType.APPLY_QUICKAPPLY_OBUBINDING, cardInfo);
		}
		try {
			HttpMimeResponseHelper.responseJson(cardObuBinding.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("返回数据失败");
		}
		
	}

}
