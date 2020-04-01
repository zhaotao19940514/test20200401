/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
package cn.com.taiji.css.web.apply.baseinfo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.apply.baseinfo.CardManager;
import cn.com.taiji.css.model.customerservice.cardobuquery.CardRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.dict.CardType;
import cn.com.taiji.qtk.entity.dict.CardUploadStatus;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;
import cn.com.taiji.qtk.entity.dict.VehicleType;

/**
 * @ClassName RechargeController
 * @Description TODO
 * @author yaonl
 * @date 2018年06月25日 17:14:54
 * @E_mail yaonanlin@163.com
 */
@Controller
@RequestMapping("/apply/baseinfo/card")
public class CardController extends MyLogController {
	@Autowired
	private CardManager cardManager;
	
	private final String prefix = "apply/baseinfo/card/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CardRequest queryModel, Model model)
	{
//		model.addAttribute("agencys", cardManager.queryAllAgency());
		model.addAttribute("cardUploadStatus", CardUploadStatus.values());
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CardRequest queryModel, Model model, HttpServletRequest req)
	{	
//		queryModel.setAgencyId(LoginHelper.getLoginUser(req).getStaff().getServiceHall().getAgencyId());
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		model.addAttribute("vehicleTypes", VehicleType.values());
		model.addAttribute("cardUploadStatus", CardUploadStatus.values());
		model.addAttribute("pagn", cardManager.queryPage(queryModel));
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/view/{cardId}")
	public String view(@PathVariable("cardId") String cardId, HttpServletRequest request, Model model) throws ManagerException
	{
		model.addAttribute("cardTypes", CardType.values());
		model.addAttribute("agencys", cardManager.queryAllAgency());
		model.addAttribute("cardUploadStatus", CardUploadStatus.values());
		model.addAttribute("pageModel", cardManager.findByCardIdPort(cardId,LoginHelper.getLoginUser(request)));
		return prefix + "view";
	}
	   
}

