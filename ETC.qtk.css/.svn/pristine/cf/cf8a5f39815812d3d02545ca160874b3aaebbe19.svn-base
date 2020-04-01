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
import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.card.AppCardStatusChangeResponse;
import cn.com.taiji.css.manager.customerservice.card.SupplyManager;
import cn.com.taiji.css.manager.customerservice.obu.ExchangeManager;
import cn.com.taiji.css.model.customerservice.card.ApplyCardRequest;
import cn.com.taiji.css.model.customerservice.card.SupplyRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.dsi.model.comm.protocol.finance.CardCancelResponse;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.Package;
import cn.com.taiji.qtk.entity.dict.CardUploadStatus;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;

/**
 * @ClassName RechargeController
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:14:54
 * @E_mail yaonanlin@163.com
 */
@Controller
@RequestMapping("/customerservice/card/supply")
public class SupplyController extends MyLogController {
	private final String prefix = "customerservice/card/supply/";
	
	@Autowired
	private SupplyManager supplyManager;
	@Autowired
	private CardInfoRepo cardInfoRepo;
	@Autowired
	private ExchangeManager exchangeManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") SupplyRequest queryModel, Model model) throws ManagerException
	{
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") SupplyRequest queryModel,HttpServletRequest request, Model model,HttpServletResponse response) throws ManagerException
	{
		LargePagination<CardInfo> pagn = supplyManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("pagn", pagn);
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("CustomerIDType",CustomerIDType.values());
		model.addAttribute("CardUploadStatus",CardUploadStatus.values());
		return prefix+"queryResult";
	}	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addGet(@ModelAttribute("queryModel") SupplyRequest queryModel, Model model)
	{
		return prefix+"manage";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPost(@ModelAttribute("queryModel") SupplyRequest queryModel, Model model)
	{
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/oldCardCancel", method = RequestMethod.POST)
	public void oldCardCancel(@Valid @RequestBody ApplyCardRequest appReq, HttpServletResponse response,HttpServletRequest request) throws ManagerException {
		try {
			CardCancelResponse cardSCResponse = supplyManager.oldCardCancel(appReq,LoginHelper.getLoginUser(request));
			CardInfo cardInfo = supplyManager.findById(appReq.getCardId());
			if(appReq.getSupplyOrReplace()) {
				super.doAddLog(request, CssServiceLogType.CUSTOMERSERVICE_CARD_REPLACE, cardInfo);
			}else {
				super.doAddLog(request, CssServiceLogType.CUSTOMERSERVICE_CARD_SUPPLY, cardInfo);
			}
			HttpMimeResponseHelper.responseJson(cardSCResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("补换卡时执行注销业务失败，请联系管理员。");
		}
		
	}
	@RequestMapping(value = "/cardApplyAndConfirm", method = RequestMethod.POST)
	public void cardApplyAndConfirm(@Valid @RequestBody ApplyCardRequest appReq,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException {
			try {
				CardInfo cardInfo = cardInfoRepo.findByCardId(appReq.getOldCardId());
				if(null==appReq.getVehicleId()) {
					appReq.setVehicleId(cardInfo.getVehicleId());
				}
				AppCardStatusChangeResponse applyCard = supplyManager.cardApplyAndConfirm(appReq,LoginHelper.getLoginUser(request));
				HttpMimeResponseHelper.responseJson(applyCard.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ManagerException("补换卡时执行开卡业务失败，请联系管理员。");
			}
	}
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id,@ModelAttribute("pageModel") SupplyRequest appReq, HttpServletRequest request, Model model) throws ManagerException
	{
		
		CardInfo cardInfo = cardInfoRepo.findByCardId(id);
		//车辆信息校验
		if(null==cardInfo.getVehicle()) {
			throw new ManagerException("数据验证失败:该ETC卡未绑定车辆信息");
		}
		exchangeManager.VehicleInfoCheck(cardInfo.getVehicle());
		model.addAttribute("cardType", Integer.valueOf(cardInfo.getCardType()/100)==2?22:23);
		model.addAttribute("cardId",id);
		return prefix + "edit";
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String porcessEdit(@Valid @ModelAttribute("pageModel") SupplyRequest appReq, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		return prefix + "result";
	}
	
	@RequestMapping(value = "/queryPackage", method = RequestMethod.POST)
	public void queryPackage(@Valid @RequestBody ApplyCardRequest appReq,HttpServletResponse response,HttpServletRequest request,Model model) {
		
		List<Package> list = supplyManager.queryPackage(appReq, LoginHelper.getLoginUser(request));
		String json;
		try {
			json = JsonTools.toJsonStr(list);
			
			HttpMimeResponseHelper.responseJson(json, response);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/cardRefund", method = RequestMethod.POST)
	public void cardRefund(HttpServletResponse response,HttpServletRequest request) {
		Long refund = supplyManager.cardRefund(LoginHelper.getLoginUser(request));
		String json;
		json = JsonTools.toJsonStr(refund);
		try {
			HttpMimeResponseHelper.responseJson(json, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

