/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
package cn.com.taiji.css.web.customerservice.card;

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
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.card.AppCardStatusChangeResponse;
import cn.com.taiji.css.manager.customerservice.card.PreCancelManager;
import cn.com.taiji.css.model.customerservice.card.PreCancelRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.dict.CardUploadStatus;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;

/**
 * @ClassName PreCancelController.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
@Controller
@RequestMapping("/customerservice/card/precancel")
public class PreCancelController extends MyLogController {
	private final String prefix = "customerservice/card/precancel/";
	
	@Autowired
	private PreCancelManager preCancelManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") PreCancelRequest queryModel, Model model)
	{	
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") PreCancelRequest queryModel, Model model,HttpServletRequest request) throws ManagerException
	{
		LargePagination<CardInfo> pagn = preCancelManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("pagn", pagn);
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("CardUploadStatus",CardUploadStatus.values());
		model.addAttribute("CustomerIDType",CustomerIDType.values());
		return prefix+"queryResult";
	}	
	/**
	 * 销卡
	 * @param queryModel
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public String Edit(@ModelAttribute("queryModel") PreCancelRequest queryModel, Model model)
	{
		return prefix+"edit";
	}
	@RequestMapping(value = "/doPreCancel", method = RequestMethod.POST)
	public void doPreCancel(@Valid @RequestBody PreCancelRequest queryModel,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException {
		
		AppCardStatusChangeResponse preCancelRes;
		try {
			preCancelRes = preCancelManager.doPreCancel(queryModel,LoginHelper.getLoginUser(request));
			if(null!=preCancelRes.getOrderStatus()&&preCancelRes.getOrderStatus()==2) {
				CardInfo cardInfo = preCancelManager.findById(queryModel.getCardId());
				super.doUpdateLog(request, CssServiceLogType.CUSTOMERSERVICE_CARD_PRECANCEL, cardInfo);
			}else if(queryModel.getProvider()==0) {
				CardInfo cardInfo = preCancelManager.findById(queryModel.getCardId());
				super.doUpdateLog(request, CssServiceLogType.CUSTOMERSERVICE_CARD_PRECANCEL, cardInfo);
			}
			HttpMimeResponseHelper.responseJson(preCancelRes.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("卡预注销失败，请联系管理员。");
		}
	}
	
	/*@RequestMapping(value = "/doCardOrder", method = RequestMethod.POST)
	public void doCardOrder(@RequestBody PreCancelRequest queryModel,HttpServletResponse response,HttpServletRequest request,Model model) {
		CardObuBindingConfirmResponse CardObuConfirmRes = preCancelManager.doCardOrder(queryModel, LoginHelper.getLoginUser(request));
		CardInfo cardInfo = preCancelManager.findById(queryModel.getCardId());
		try {
			super.doUpdateLog(request, CssServiceLogType.CUSTOMERSERVICE_CARD_PRECANCEL, cardInfo);
			HttpMimeResponseHelper.responseJson(CardObuConfirmRes.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
}

