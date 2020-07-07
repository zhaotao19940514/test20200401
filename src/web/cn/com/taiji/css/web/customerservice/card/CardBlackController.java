/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
package cn.com.taiji.css.web.customerservice.card;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.dict.CardBlackQueryType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.card.CardBlackManager;
import cn.com.taiji.css.model.customerservice.card.BlackCardModel;
import cn.com.taiji.css.model.customerservice.card.CardBlackRequest;
import cn.com.taiji.css.model.customerservice.card.PreCancelRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.BlackCard;
import cn.com.taiji.qtk.entity.BlackCardHis;
import cn.com.taiji.qtk.entity.dict.CardBlackType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;

@Controller
@RequestMapping("/customerservice/card/cardBlack")
public class CardBlackController extends MyLogController {
	private final String prefix = "customerservice/card/cardBlack/";
	
	@Autowired
	private CardBlackManager cardBlackManager;
	

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CardBlackRequest queryModel, Model model)
	{
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("cardBlackQueryType", CardBlackQueryType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CardBlackRequest queryModel, Model model, HttpServletRequest request) throws ManagerException
	{
		List<BlackCard> blackCards =new ArrayList<BlackCard>();
		List<BlackCardHis> blackCardHiss =new ArrayList<BlackCardHis>();
		if(queryModel.getCardBlackQueryType()==0)  blackCards = cardBlackManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		if(queryModel.getCardBlackQueryType()==0)  {
			List<BlackCardModel> pagn = BlackCardModel.toBlackCardModel(blackCards);
			model.addAttribute("pagn", pagn);
		}
		if(queryModel.getCardBlackQueryType()==1)  blackCardHiss = cardBlackManager.queryPageHis(queryModel,LoginHelper.getLoginUser(request));
		if(queryModel.getCardBlackQueryType()==1)  {
			List<BlackCardModel> pagnHis = BlackCardModel.toBlackCardHisModel(blackCardHiss);
			model.addAttribute("pagn", pagnHis);
		}
		model.addAttribute("cardBlackType", CardBlackType.values());
		
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/view/{cardIdAndTime}", method = RequestMethod.GET)
	public String view(@PathVariable("cardIdAndTime") String cardIdAndTime,@ModelAttribute("queryModel") CardBlackRequest queryModel, HttpServletRequest request, Model model) throws ManagerException
	{
		String[] str=cardIdAndTime.split("_");
		String cardId=str[0];
		String createTime=str[1];
		String status=str[2];
		List<List<String>> queryYgzLog = cardBlackManager.queryYgzLog(createTime, createTime, cardId,status,LoginHelper.getLoginUser(request));
		model.addAttribute("queryYgzLog", queryYgzLog);
		return prefix + "view";
	}
}

