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
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.card.ManCancelManager;
import cn.com.taiji.css.manager.customerservice.finance.CardAccountRefundManager;
import cn.com.taiji.css.manager.customerservice.finance.CardRefundConfirmManager;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.customerservice.finance.CardAccountRefundRequest;
import cn.com.taiji.css.model.customerservice.finance.CardRefundConfirmRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.AccountRefundDetail;
import cn.com.taiji.qtk.entity.AccountRefundLog;
import cn.com.taiji.qtk.entity.CardAccountBalance;
import cn.com.taiji.qtk.entity.dict.CardRefundDetailType;
import cn.com.taiji.qtk.entity.dict.RefundDetailType;

@Controller
@RequestMapping("/customerservice/finance/cardrefundcost")
public class CardRefundCostController extends MyLogController{
	private final String prefix = "customerservice/finance/cardrefundcost/";
	
	@Autowired
	private CardRefundConfirmManager cardRefundConfirmManager;
	@Autowired
	private ManCancelManager manCancelManager;
	@Autowired
	private CardAccountRefundManager cardAccountRefundManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CardRefundConfirmRequest queryModel, Model model,HttpServletRequest request)
	{
		model.addAttribute("RefundDetailType", RefundDetailType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CardAccountRefundRequest queryModel,HttpServletRequest request,Model model) throws ManagerException
	{
		queryModel.setIsConfirm(0);
		if(null!=queryModel.getCardId()) {
			boolean flag = manCancelManager.cancel15ArgueTime(queryModel.getCardId());
			if(!flag) throw new ManagerException("当前卡号未过15天争议期,无法查看详情");
		}
		//判断是否是银行卡转账模式退款 true是  false否
		boolean cancelTimeFlag = cardAccountRefundManager.deCcancelTimeByBankType(queryModel.getCardId());
		LargePagination<AccountRefundDetail> pagn = cardAccountRefundManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		//判断是否有查看日志 冲正 二次核定的权限
		boolean reverFlag = cardAccountRefundManager.queryRevereRefund(LoginHelper.getLoginUser(request));
		
		model.addAttribute("pagn", pagn);
		model.addAttribute("CardRefundDetailType", CardRefundDetailType.values());
		model.addAttribute("RefundDetailType", RefundDetailType.values());
		model.addAttribute("reverFlag", reverFlag);
		model.addAttribute("cancelTimeFlag", cancelTimeFlag);
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String cardId, HttpServletRequest request, Model model)
	{
		List<CardAccountBalance> list = cardRefundConfirmManager.queryList(cardId);
		model.addAttribute("list", list);
		return prefix + "edit";
	}
	@RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
	public String setupInfo(@PathVariable("id") String cardId, HttpServletRequest request, Model model) throws ManagerException
	{
		AccountRefundDetail accountRefundDetail = cardRefundConfirmManager.findByCardId(cardId);
		
		if(!accountRefundDetail.getRefundType().equals(RefundDetailType.WTJTF)&&!accountRefundDetail.getRefundType().equals(RefundDetailType.YTJTF)) {
			throw new ManagerException("无法提交");
		}
		model.addAttribute("cardId", cardId);
		
		model.addAttribute("accountRefundDetail", accountRefundDetail);
		//model.addAttribute("balance", balance);
		return prefix + "info";
	}
	
	@RequestMapping(value = "/saveRefundInfo", method = RequestMethod.POST)
	public void saveRefundInfo(@RequestBody CardAccountRefundRequest queryModel, HttpServletRequest request, Model model,HttpServletResponse response)
	{
		queryModel.setRefundType(1);
		AppAjaxResponse res = cardRefundConfirmManager.saveRefundInfo(queryModel,LoginHelper.getLoginUser(request),request);
		try {
			HttpMimeResponseHelper.responseJson(res.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/byLittleRefund", method = RequestMethod.POST)
	public void byLittleRefund(@RequestBody CardRefundConfirmRequest queryModel, HttpServletRequest request,HttpServletResponse response) {
		AppAjaxResponse res = cardRefundConfirmManager.byLittleRefund(queryModel);
	}
	
	public void confirmRefund(@RequestBody CardRefundConfirmRequest queryModel, HttpServletRequest request,HttpServletResponse response) {
		AppAjaxResponse res = cardRefundConfirmManager.confirmRefund(queryModel);
	}
	
	
	
	
	@RequestMapping(value = "/updateRefundBalance", method = RequestMethod.POST)
	public void updateRefundBalance(@RequestBody CardAccountRefundRequest queryModel, HttpServletRequest request, Model model,HttpServletResponse response)
	{
		AppAjaxResponse res = cardRefundConfirmManager.updateRefundBalance(queryModel,LoginHelper.getLoginUser(request),request);
		try {
			HttpMimeResponseHelper.responseJson(res.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/confirmBalance", method = RequestMethod.POST)
	public void confirmBalance(@RequestBody CardAccountRefundRequest queryModel, HttpServletRequest request, Model model,HttpServletResponse response)
	{
		AppAjaxResponse res = cardRefundConfirmManager.confirmBalance(queryModel,LoginHelper.getLoginUser(request),request);
		try {
			HttpMimeResponseHelper.responseJson(res.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/discript/{id}", method = RequestMethod.GET)
	public String discript(@PathVariable("id") String cardId, HttpServletRequest request, Model model) {
		model.addAttribute("cardId", cardId);
		return prefix+"discript";
	}
	
	@RequestMapping(value = "/saveDiscript", method = RequestMethod.POST)
	public void saveDiscript(@RequestBody CardAccountRefundRequest queryModel, HttpServletRequest request, Model model,HttpServletResponse response)
	{
		AppAjaxResponse res = cardRefundConfirmManager.saveDiscript(queryModel,LoginHelper.getLoginUser(request),request);
		try {
			HttpMimeResponseHelper.responseJson(res.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/operateRecode/{id}", method = RequestMethod.GET)
	public String operateRecode(@PathVariable("id") String cardId, HttpServletRequest request, Model model) throws ManagerException {
		
		List<AccountRefundLog> list = cardRefundConfirmManager.operateRecode(cardId);
		model.addAttribute("list", list);
		return prefix+"record";
	}
	
	@RequestMapping(value = "/revereRefund", method = RequestMethod.POST)
	public void revereRefund(@RequestBody CardAccountRefundRequest queryModel, HttpServletRequest request, Model model,HttpServletResponse response) throws ManagerException {
		AppAjaxResponse res = cardAccountRefundManager.revereRefund(queryModel,LoginHelper.getLoginUser(request),request);
		
		try {
			HttpMimeResponseHelper.responseJson(res.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

