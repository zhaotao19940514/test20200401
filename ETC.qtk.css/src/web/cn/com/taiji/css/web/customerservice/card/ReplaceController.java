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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.card.ReplaceManager;
import cn.com.taiji.css.manager.customerservice.obu.ExchangeManager;
import cn.com.taiji.css.model.customerservice.card.ReplaceRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.dict.CardUploadStatus;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;
import cn.com.taiji.qtk.repo.jpa.CardInfoRepo;

/**
 * @ClassName ReplaceController.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
@Controller
@RequestMapping("/customerservice/card/replace")
public class ReplaceController extends MyLogController {
	private final String prefix = "customerservice/card/replace/";
	
	@Autowired
	private ReplaceManager replaceManager;
	@Autowired
	private ExchangeManager exchangeManager;
	@Autowired
	private CardInfoRepo cardInfoRepo;
	
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") ReplaceRequest queryModel, Model model) throws ManagerException
	{
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") ReplaceRequest queryModel, Model model, HttpServletRequest request) throws ManagerException
	{
		LargePagination<CardInfo> pagn = replaceManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("CustomerIDType",CustomerIDType.values());
		model.addAttribute("CardUploadStatus",CardUploadStatus.values());
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}	
	/**
	 * 换卡
	 * @param queryModel
	 * @param model
	 * @return
	 * @throws ManagerException 
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id,@ModelAttribute("pageModel") ReplaceRequest queryModel, HttpServletRequest request, Model model) throws ManagerException
	{
		CardInfo cardInfo = cardInfoRepo.findByCardId(id);
		//车辆信息校验
		if(null==cardInfo.getVehicle()) {
			throw new ManagerException("数据验证失败:该ETC卡未绑定车辆信息");
		}
		exchangeManager.VehicleInfoCheck(cardInfo.getVehicle());
		model.addAttribute("cardType", Integer.valueOf(cardInfo.getCardType()/100));
		model.addAttribute("cardId",id);
		return prefix + "edit";
	}
	
}

