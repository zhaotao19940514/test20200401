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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.Role;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.card.HangManager;
import cn.com.taiji.css.model.customerservice.card.HangRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardStatusChangeResponse;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.dict.CardUploadStatus;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;

/**
 * @ClassName HangController.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
@Controller
@RequestMapping("/customerservice/card/hang")
public class HangController extends MyLogController {
	private final String prefix = "customerservice/card/hang/";
	
	@Autowired
	private HangManager hangManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") HangRequest queryModel, Model model)
	{
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") HangRequest queryModel, Model model, HttpServletRequest request) throws ManagerException
	{
		LargePagination<CardInfo> pagn = hangManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("CustomerIDType",CustomerIDType.values());
		model.addAttribute("CardUploadStatus",CardUploadStatus.values());
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}	
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id, HttpServletRequest request, Model model)
	{
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String porcessEdit(@Valid @ModelAttribute("pageModel") Role role, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		
		return prefix + "result";
	}
	@RequestMapping(value = "/hangCard", method = RequestMethod.POST)
	public void hangCard(@Valid @RequestBody HangRequest queryModel,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException {
		
		CardStatusChangeResponse cardHangRes;
		try {
			cardHangRes = hangManager.doHangCard(queryModel,LoginHelper.getLoginUser(request));
			CardInfo cardInfo = hangManager.findById(queryModel.getCardId());
			super.doAddLog(request, CssServiceLogType.CUSTOMERSERVICE_CARD_HANG, cardInfo);
			HttpMimeResponseHelper.responseJson(cardHangRes.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("卡挂起失败，请联系管理员。");
		}
	}
}

