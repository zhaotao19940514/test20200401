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
import cn.com.taiji.css.manager.customerservice.finance.CardreverseManager;
import cn.com.taiji.css.manager.customerservice.finance.ForcefixManager;
import cn.com.taiji.css.model.customerservice.finance.CardreverseRequest;
import cn.com.taiji.css.model.customerservice.finance.CardreverseResponse;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.ChargeDetail;

@Controller
@RequestMapping("/customerservice/finance/cardreverse")
public class CardReverseController extends MyLogController {
	private final String prefix = "customerservice/finance/cardreverse/";
	
	@Autowired
	private CardreverseManager cardreverseManager;
	
	@Autowired
	private ForcefixManager forcefixManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CardreverseRequest queryModel, Model model)
	{
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CardreverseRequest queryModel,HttpServletRequest request, Model model) throws ManagerException
	{
		LargePagination<ChargeDetail> 	pagn = cardreverseManager.queryPage(queryModel, LoginHelper.getLoginUser(request));
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/info/{chargeId}",method = RequestMethod.POST)
	public String info(@PathVariable("chargeId") String chargeId, Model model) throws Exception
	{
		ChargeDetail chargeDetail=forcefixManager.findBychargeId(chargeId,model);
		model.addAttribute("pageModel",chargeDetail);
		
		return prefix + "info";
	}
	
	@RequestMapping(value = "/cardReverseInitWithCOS",method = RequestMethod.POST)
	public void cardReverseInitWithCOS(@Valid @RequestBody CardreverseRequest queryModel,HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException 
	{
		CardreverseResponse cardreverseResponse=cardreverseManager.cardReverseInitWithCOS(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(cardreverseResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("冲正初始化失败");
		}
	}
	
	
	@RequestMapping(value = "/cardReverseDebitWithCOS",method = RequestMethod.POST)
	public void CardChargeByCOS(@Valid @RequestBody CardreverseRequest queryModel,HttpServletResponse response,HttpServletRequest request, Model model) throws ManagerException 
	{
		CardreverseResponse cardreverseResponse=cardreverseManager.cardReverseDebitWithCOS(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(cardreverseResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("冲正消费失败");
		}
	}
	
	@RequestMapping(value = "/cardReverseConfirmWithCOS",method = RequestMethod.POST)
	public void CardChargeConfirmByCOS(@RequestBody CardreverseRequest queryModel, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException 
	{		
		CardreverseResponse cardreverseResponse=cardreverseManager.cardReverseConfirmWithCOS(queryModel,LoginHelper.getLoginUser(request));
		if(cardreverseResponse!=null &&cardreverseResponse.getInfo()!=null) {
			super.doSysLog(request,  CssServiceLogType.CUSTOMERSERVICE_FINANCE_CARDREVERSE, CssOperateLogType.REQUEST, null, "冲正业务", queryModel);
		}
		try {
			HttpMimeResponseHelper.responseJson(cardreverseResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}

