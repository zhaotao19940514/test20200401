package cn.com.taiji.css.web.customerservice.card;

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
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.card.ManCancelManager;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.customerservice.card.ManCancelRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.AccountCardBalanceOperate;
import cn.com.taiji.qtk.entity.AccountRefundDetail;
import cn.com.taiji.qtk.entity.AccountRefundLog;
import cn.com.taiji.qtk.entity.CancelledCardDetail;
import cn.com.taiji.qtk.entity.dict.AccountCardBalanceOperateType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;
import cn.com.taiji.qtk.repo.jpa.AccountCardBalanceOperateRepo;

/**
 * @ClassName ManCancelController.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
@Controller
@RequestMapping("/customerservice/card/mancancel")
public class ManCancelController extends MyLogController {
	private final String prefix = "customerservice/card/mancancel/";
	@Autowired
	private ManCancelManager manCancelManager;
	@Autowired
	private AccountCardBalanceOperateRepo accountCardBalanceOperateRepo;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") ManCancelRequest queryModel, Model model)
	{
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") ManCancelRequest queryModel, Model model, HttpServletRequest request) throws ManagerException
	{
		LargePagination<CancelledCardDetail> pagn = manCancelManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		if(null!=pagn&&null!=pagn.getResult()&&pagn.getResult().size()!=0) {
			AccountCardBalanceOperate accountCardBalanceOperate = accountCardBalanceOperateRepo.findByCardId(pagn.getResult().get(0).getCardId());
			model.addAttribute("accountCardBalanceOperate", accountCardBalanceOperate);
		}
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}	
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String cardId, @ModelAttribute("pageModel1") ManCancelRequest queryModel,HttpServletRequest request, Model model) throws ManagerException
	{
		boolean flag = manCancelManager.cancel15ArgueTime(cardId);
		//if(!flag) throw new ManagerException("当前卡号未过30天争议期,无法查看详情");
		model.addAttribute("cardId", cardId);
		model.addAttribute("customerInfo", manCancelManager.findByCustomerInfo(cardId));
		AccountRefundDetail accountRefundDetail = null;
		AccountCardBalanceOperate accountCardBalanceOperate = null;
		try {
			accountCardBalanceOperate = manCancelManager.findByCardId(cardId);
			accountRefundDetail = manCancelManager.findRefundBalance(cardId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("未查询到退款信息，请联系管理员");
		}
		model.addAttribute("accountCardBalanceOperate",accountCardBalanceOperate);
		model.addAttribute("accountRefundDetail",accountRefundDetail);
		if(null!=accountRefundDetail) {
			model.addAttribute("refundDetailType",accountRefundDetail.getRefundType().getValue());
		}
		/*LastTransBalanceRequest lastTransBalanceRequest = new LastTransBalanceRequest();
		lastTransBalanceRequest.setCardId(cardId);
		model.addAttribute("accountCardBalance", accountCardBalanceManager.getOne(lastTransBalanceRequest));*/
		model.addAttribute("accountCardBalance",manCancelManager.findAccountCardBalanceBycardId(cardId,LoginHelper.getLoginUser(request)));
		model.addAttribute("AccountCardBalanceOperateType", AccountCardBalanceOperateType.values());
		model.addAttribute("flag", flag);
		return prefix + "edit";
	}
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String setupUpdate(@PathVariable("id") String cardId, @ModelAttribute("pageModel2") ManCancelRequest queryModel,HttpServletRequest request, Model model) throws ManagerException
	{
		AccountCardBalanceOperate accountCardBalanceOperate  = manCancelManager.queryBankCard(cardId);
		model.addAttribute("accountCardBalanceOperate", accountCardBalanceOperate);
		return prefix + "update";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void porcessUpate(@RequestBody ManCancelRequest queryModel,HttpServletRequest request, Model model,HttpServletResponse response) throws ManagerException
	{
		AppAjaxResponse appAjaxRes = manCancelManager.updateBankCard(queryModel);
		 try {
			HttpMimeResponseHelper.responseJson(appAjaxRes.toJson(), response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String porcessEdit(@Valid @ModelAttribute("pageModel") ManCancelRequest queryModel, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		//AccountCardBalance accountCardBalance = accountCardBalanceRepo.findAccountCardBalanceBycardId(cardInfo.getCardId());
		return prefix + "result";
	}
	@RequestMapping(value = "/confirmRefund", method = RequestMethod.POST)
	public void confirmRefund(@RequestBody ManCancelRequest queryModel, HttpServletRequest request, Model model,
			HttpServletResponse response) {
		AppAjaxResponse appAjaxRes = manCancelManager.confirmRefund(queryModel,LoginHelper.getLoginUser(request));
		CancelledCardDetail cancelledCardDetail = new CancelledCardDetail();
		cancelledCardDetail = manCancelManager.findById(queryModel.getCardId());
		try {
			super.doUpdateLog(request, CssServiceLogType.CUSTOMERSERVICE_CARD_MANCANCEL, cancelledCardDetail);
		} catch (ManagerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			HttpMimeResponseHelper.responseJson(appAjaxRes.toJson(), response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/view/{cardId}", method = RequestMethod.GET)
	public String setupView(@PathVariable("cardId") String cardId, HttpServletRequest request, Model model) throws ManagerException
	{
		List<AccountRefundLog> list = manCancelManager.listRefundMsg(cardId);
		
		model.addAttribute("list", list);
		return prefix + "view";
	}
}

