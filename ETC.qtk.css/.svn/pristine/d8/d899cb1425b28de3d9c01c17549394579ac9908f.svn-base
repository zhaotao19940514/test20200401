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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.dict.CssOperateLogType;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.finance.SupplyCardBalanceManager;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeRequest;
import cn.com.taiji.css.model.customerservice.finance.CardrechargeResponse;
import cn.com.taiji.css.model.customerservice.finance.HalfauditingResponse;
import cn.com.taiji.css.model.customerservice.finance.SupplyCardBalanceRequest;
import cn.com.taiji.css.model.customerservice.finance.SupplyCardBalanceResponse;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.SupplyCardBalanceDetail;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;

@Controller
@RequestMapping("/customerservice/finance/supplyCardBalance")
public class SupplyCardBalanceController extends MyLogController{
	private final String prefix = "customerservice/finance/supplyCardBalance/";
	
	@Autowired
	private SupplyCardBalanceManager supplyCardBalanceManager;
	
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") SupplyCardBalanceRequest queryModel, Model model)
	{
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") SupplyCardBalanceRequest queryModel,HttpServletRequest request,Model model) throws ManagerException
	{
		queryModel.validate();
		LargePagination<SupplyCardBalanceDetail> pagn = supplyCardBalanceManager.queryPage(queryModel,request);
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/info/{cardId}&{enableTime}", method = RequestMethod.GET)
	public String info(@PathVariable("cardId") String cardId,@PathVariable("enableTime") String enableTime,Model model) throws ManagerException
	{
		SupplyCardBalanceRequest request=new SupplyCardBalanceRequest();
		request.setCardId(cardId);
		request.setEnableTime(enableTime);
		model.addAttribute("pageModel", request);
		return prefix+"info";
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public String info(@RequestBody SupplyCardBalanceRequest queryModel,HttpServletRequest request,HttpServletResponse response, Model model) throws ManagerException
	{
		model.addAttribute("pageModel", queryModel);
		return prefix+"info";
    }
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(@ModelAttribute("pageModel") SupplyCardBalanceRequest queryModel,HttpServletRequest request,Model model) throws ManagerException
	{
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		return prefix+"login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void loginPost( @RequestBody SupplyCardBalanceRequest queryModel ,HttpServletRequest request,HttpServletResponse response,Model model) throws ManagerException
	{
		SupplyCardBalanceResponse SupplyCardBalanceResponse=supplyCardBalanceManager.save(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(SupplyCardBalanceResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping( value ="/savePng" , method = RequestMethod.POST )
	   public void fileUpload(@RequestParam("cardId") String cardId,@RequestParam("enableTime")String enableTime,HttpServletRequest request,@RequestParam("file") MultipartFile file,HttpServletResponse response) throws ManagerException{
		HalfauditingResponse halfauditingResponse =  supplyCardBalanceManager.savePng(file,cardId,enableTime);
		try {
			HttpMimeResponseHelper.responseJson(halfauditingResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	   }
	
	@RequestMapping(value = "/findCardInfoByCardId", method = RequestMethod.POST)
	public void findAccountBalanceByCardId(@Valid @RequestBody SupplyCardBalanceRequest queryModel,HttpServletRequest request,HttpServletResponse response, Model model) throws ManagerException
	{
		SupplyCardBalanceResponse supplyCardBalanceResponse=supplyCardBalanceManager.FindCardInfoByCardId(queryModel, LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(supplyCardBalanceResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@RequestMapping(value = "/showPhoto/{cardId}&{enableTime}",method=RequestMethod.GET)
	public String view(@PathVariable("cardId") String cardId,@PathVariable("enableTime") String enableTime,HttpServletRequest request,Model model) throws ManagerException{
		model = supplyCardBalanceManager.modelAdd(cardId,enableTime,model);
		return prefix+"view";
	}

	
	//圈存检测-------检测此卡有无半条流水 等情况.满足圈存条件则可以调用圈存申请
		@RequestMapping(value = "/cardChargeCheck",method = RequestMethod.POST)
		public void cardChargeCheck( @RequestBody CardrechargeRequest queryModel,HttpServletRequest request, Model model,
				HttpServletResponse response) throws ManagerException 
		{
			CardrechargeResponse  cardrechargeResponse=supplyCardBalanceManager.CardChargeCheck(queryModel,LoginHelper.getLoginUser(request));
			try {
				HttpMimeResponseHelper.responseJson(cardrechargeResponse.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//圈存申请 -------将此条流水写入库中,将其status改为0( 确认状态),然后进行写卡操作
		@RequestMapping(value = "/cardChargeByCOS",method = RequestMethod.POST)
		public void CardChargeByCOS(@RequestBody CardrechargeRequest queryModel,HttpServletResponse response,HttpServletRequest request, Model model) throws ManagerException 
		{
			CardrechargeResponse CardrechargeResponse=supplyCardBalanceManager.CardChargeByCOS(queryModel,LoginHelper.getLoginUser(request));
			try {
				HttpMimeResponseHelper.responseJson(CardrechargeResponse.toJson(), response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//圈存确认-------确认成功后将数据库中的status改为1  圈存结束  未确认成功则为0  成为半条流水,提示用户进行圈存修复
		@RequestMapping(value = "/cardChargeConfirmByCOS",method = RequestMethod.POST)
		public void CardChargeConfirmByCOS(@RequestBody CardrechargeRequest queryModel, HttpServletRequest request, Model model,
				HttpServletResponse response) throws ManagerException 
		{
			CardrechargeResponse cardrechargeResponse=supplyCardBalanceManager.CardChargeConfirmByCOS(queryModel,LoginHelper.getLoginUser(request));
			try {
				HttpMimeResponseHelper.responseJson(cardrechargeResponse.toJson(), response);
				if(cardrechargeResponse!=null &&cardrechargeResponse.getPostBalance()>0) {
					super.doSysLog(request,  CssServiceLogType.CUSTOMERSERVICE_FINANCE_SUPPLYCARDBALANCE, CssOperateLogType.REQUEST, null, "补卡额", queryModel);
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new ManagerException("圈存确认失败");
			}
		}
		
	
	
	
	
	
	
}

