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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.card.UnlossManager;
import cn.com.taiji.css.model.customerservice.card.UnlossRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.dsi.model.comm.protocol.maintenance.CardStatusChangeResponse;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.dict.CardUploadStatus;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;

/**
 * @ClassName UnlossController.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
@Controller
@RequestMapping("/customerservice/card/unloss")
public class UnlossController extends MyLogController {
	private final String prefix = "customerservice/card/unloss/";
	
	@Autowired
	private UnlossManager unlossManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") UnlossRequest queryModel, Model model)
	{
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") UnlossRequest queryModel,HttpServletRequest request, Model model) throws ManagerException
	{	
		LargePagination<CardInfo> pagn = unlossManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("pagn", pagn);
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("CustomerIDType",CustomerIDType.values());
		model.addAttribute("CardUploadStatus",CardUploadStatus.values());
		return prefix+"queryResult";
	}					
	@RequestMapping(value = "/unlossCard", method = RequestMethod.POST)
	public void unlossCard(@Valid @RequestBody UnlossRequest queryModel,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException {
		CardStatusChangeResponse unLossResponse;
		try {
			unLossResponse = unlossManager.doUnLossCard(queryModel.getCardId(),LoginHelper.getLoginUser(request));
			CardInfo cardInfo = unlossManager.findById(queryModel.getCardId());
			super.doAddLog(request, CssServiceLogType.CUSTOMERSERVICE_CARD_UNLOSS, cardInfo);
			HttpMimeResponseHelper.responseJson(unLossResponse.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ManagerException("解挂失败，请联系管理员。");
		}
		
	}
	
	@RequestMapping(value = "/readCCBCancelContent", method = RequestMethod.POST)
	public void readCCBCancelContent(HttpServletRequest request) throws IOException{
		
		unlossManager.readCCBCancelContent(request);
	}
	@RequestMapping(value = "/readLkfContent", method = RequestMethod.POST)
	public void readLkfContent(HttpServletRequest request) throws IOException{
		
		unlossManager.readLkfContent(request);
	}
	@RequestMapping(value = "/readFileContent", method = RequestMethod.POST)
	public void readFileContent(HttpServletRequest request) throws IOException{
		
		unlossManager.readFileContent(request);
	}
	@RequestMapping(value = "/readReChargeFileContent", method = RequestMethod.POST)
	public void readReChargeFileContent(HttpServletRequest request) throws IOException{
		
		unlossManager.readReChargeFileContent(request);
	}
	
	@RequestMapping(value = "/deleteCancelDetail", method = RequestMethod.POST)
	public void deleteCancelDetail(@RequestParam("cardId") String cardId,HttpServletResponse response,HttpServletRequest request) throws IOException, ManagerException{
		
		unlossManager.deleteCancelDetail(cardId);
	}
//	
//	@RequestMapping(value = "/exportOutProvince", method = RequestMethod.POST)
//	public void exportOutProvince(HttpServletRequest request) throws IOException{
//		
//		unlossManager.exportOutProvince(request);
//	}
}

