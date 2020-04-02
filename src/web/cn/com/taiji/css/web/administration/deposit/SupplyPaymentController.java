package cn.com.taiji.css.web.administration.deposit;

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
import cn.com.taiji.css.manager.administration.deposit.SupplyPaymentManager;
import cn.com.taiji.css.manager.customerservice.card.CancelManager;
import cn.com.taiji.css.model.administration.deposit.SupplyPaymentRequest;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CollateCardBalance;
import cn.com.taiji.qtk.entity.dict.CardUploadStatus;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;

/**
 * @ClassName SupplyPaymentController.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
@Controller
@RequestMapping("/administration/deposit/supplypayment")
public class SupplyPaymentController extends MyLogController {
	private final String prefix = "administration/deposit/supplypayment/";
	@Autowired
	private SupplyPaymentManager supplyPaymentManager;
	@Autowired
	private CancelManager cancelManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") SupplyPaymentRequest queryModel, Model model)
	{
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") SupplyPaymentRequest queryModel, Model model, HttpServletRequest request) throws ManagerException
	{
		LargePagination<CardInfo> pagn = supplyPaymentManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("CardUploadStatus", CardUploadStatus.values());
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}	
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String cardId, @ModelAttribute("pageModel1") SupplyPaymentRequest queryModel,HttpServletRequest request, Model model) throws ManagerException
	{
		CollateCardBalance collateCardBalance = supplyPaymentManager.findbyCollateCardBalance(cardId);
		model.addAttribute("collateCardBalance", collateCardBalance);
		model.addAttribute("cardId", cardId);
		return prefix + "edit";
	}
	@RequestMapping(value = "/doSubmit", method = RequestMethod.POST)
	public void doSubmit(@RequestBody SupplyPaymentRequest queryModel,HttpServletRequest request, Model model,HttpServletResponse response) throws ManagerException
	{
		
		AppAjaxResponse appAjaxRes = supplyPaymentManager.doSubmit(queryModel,LoginHelper.getLoginUser(request));
		CardInfo cardInfo = cancelManager.findById(queryModel.getCardId());
		super.doAddLog(request, CssServiceLogType.ADMINISTRATION_INVENTORY_SUPPLYPAYMENT, cardInfo);
		
		try {
			HttpMimeResponseHelper.responseJson(appAjaxRes.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

