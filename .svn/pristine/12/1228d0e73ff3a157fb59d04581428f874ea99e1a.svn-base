package cn.com.taiji.css.web.customerservice.finance;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.finance.HalfauditingManager;
import cn.com.taiji.css.model.customerservice.finance.HalfauditingRequest;
import cn.com.taiji.css.model.customerservice.finance.HalfauditingResponse;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.ChargeDetail;
import cn.com.taiji.qtk.repo.jpa.ChargeDetailRepo;

@Controller
@RequestMapping("/customerservice/finance/halfauditing")
public class HalfauditingController extends MyLogController {
	private final String prefix = "customerservice/finance/halfauditing/";
	
	@Autowired
	private HalfauditingManager halfauditingManager;

	
	@Autowired
	private ChargeDetailRepo chargeDetailRepo;
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") HalfauditingRequest queryModel, Model model)
	{
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") HalfauditingRequest queryModel,HttpServletRequest request ,Model model) throws ManagerException
	{
		LargePagination<ChargeDetail> pagn = halfauditingManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
	
	
	@RequestMapping(value = "/update/{chargeId}", method = RequestMethod.POST)
	public String update(@PathVariable("chargeId") String chargeId, Model model,HttpServletResponse response)
	{
		String  message=halfauditingManager.updateStatus(chargeId);
		addSuccess(response, message);
		return prefix+"manage";
	}
	
	@RequestMapping(value = "/info/{chargeId}", method = RequestMethod.GET)
	public String info(@PathVariable("chargeId") String chargeId,Model model) throws ManagerException
	{
		model.addAttribute("pageModel", chargeDetailRepo.findByChargeId(chargeId));
		return prefix+"info";
	}
	
	
	@RequestMapping( value ="/savePng" , method = RequestMethod.POST )
	   public void fileUpload(@RequestParam("chargeId") String chargeId,HttpServletRequest request,@RequestParam("file") MultipartFile file,HttpServletResponse response) throws ManagerException{
		HalfauditingResponse halfauditingResponse =  halfauditingManager.savePng(file,chargeId);
		try {
			HttpMimeResponseHelper.responseJson(halfauditingResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("冲正初始化失败");
		}
	   }
	
}

