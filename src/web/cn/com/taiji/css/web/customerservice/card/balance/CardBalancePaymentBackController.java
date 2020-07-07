package cn.com.taiji.css.web.customerservice.card.balance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.dict.CssOperateLogType;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.card.balance.CardBalancePaymentBackManager;
import cn.com.taiji.css.model.customerservice.card.balance.CardBalancePaymentBackLogEntity;
import cn.com.taiji.css.model.customerservice.card.balance.CardBalancePaymentBackRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.dsi.model.cardbalance.CardBalancePaymentDetailResponse;
import cn.com.taiji.dsi.model.cardbalance.CardBalanceTransactionResponse;

@Controller
@RequestMapping("/customerservice/card/balance")
public class CardBalancePaymentBackController extends MyLogController {

	private final String prefix = "customerservice/card/balance/";

	@Autowired
	private CardBalancePaymentBackManager cardBalancePaymentBackManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CardBalancePaymentBackRequest queryModel, Model model) {
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CardBalancePaymentBackRequest queryModel, Model model,
			HttpServletRequest request) throws ManagerException {
		model.addAttribute("vo", cardBalancePaymentBackManager.query(queryModel, LoginHelper.getLoginUser(request)));
		return prefix + "queryResult";
	}

	@RequestMapping(value = "/paymentBack/{cardId}/{fee}", method = RequestMethod.POST)
	public void paymentBackPost(@PathVariable("cardId") String cardId, @PathVariable("fee") Long fee, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ManagerException {
		cardBalancePaymentBackManager.paymentBack(cardId, LoginHelper.getLoginUser(request), fee);
		addSuccess(response, "补交成功");
		doSysLog(request, CssServiceLogType.CUSTOMERSERVICE_CARDBALANCE_PAYMENT, CssOperateLogType.ADD, null, "卡账补交",new CardBalancePaymentBackLogEntity(cardId, fee,LoginHelper.getLoginUser(request)));
	}

	@RequestMapping(value = "/paymentDetail/{cardId}")
	public String paymentDetailPost(@PathVariable("cardId") String cardId, Model model, HttpServletRequest request)
			throws ManagerException {
		CardBalancePaymentDetailResponse response = cardBalancePaymentBackManager.paymentDetail(cardId,
				LoginHelper.getLoginUser(request));
		model.addAttribute("response", response);
		return prefix + "paymentDetail";
	}
	
	@RequestMapping(value = "/balanceTransaction/{cardId}")
	public String balanceTransactionPost(@PathVariable("cardId") String cardId, Model model, HttpServletRequest request)
			throws ManagerException {
		CardBalanceTransactionResponse response = cardBalancePaymentBackManager.balanceTransaction(cardId,
				LoginHelper.getLoginUser(request));
		model.addAttribute("response", response);
		return prefix + "balanceTransaction";
	}
}
