
package cn.com.taiji.css.web.customerservice.finance;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.finance.ExpenseRefundApplicationManager;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationModel;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationRequest;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationResponse;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.dict.AuditStatusType;
import cn.com.taiji.qtk.entity.dict.RefundType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;


@Controller
@RequestMapping("/customerservice/finance/expenserefundapplication")
public class ExpenseRefundApplicationController extends MyLogController {
	private final String prefix = "customerservice/finance/expenserefundapplication/";
	
	@Autowired
	private ExpenseRefundApplicationManager expenseRefundApplicationManager;
	
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") ExpenseRefundApplicationRequest queryModel, Model model)
	{
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		model.addAttribute("auditStatusType", AuditStatusType.values());
		model.addAttribute("refundType", RefundType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") ExpenseRefundApplicationRequest queryModel,HttpServletRequest request, Model model) throws ManagerException
	{
		queryModel.validate();
		List<ExpenseRefundApplicationModel> resposne=expenseRefundApplicationManager.page(queryModel);
		model.addAttribute("pagn", resposne);
		return prefix+"queryResult";
    }
	
	
	@RequestMapping(value = "/info/{id}/{status}", method = RequestMethod.GET)
	public String info(@ModelAttribute("pageModel") ExpenseRefundApplicationRequest queryModel,Model model) throws ManagerException
	{
		ExpenseRefundApplicationRequest request =new ExpenseRefundApplicationRequest();
		request.setId(queryModel.getId());
		request.setStatus(queryModel.getStatus());
		model.addAttribute("pageModel", request);
		return prefix+"info";
	}
	
	
	@RequestMapping(value = "/login/{id}/{cardId}", method = RequestMethod.GET)
	public String loginGet(@ModelAttribute("pageModel") ExpenseRefundApplicationRequest queryModel,HttpServletRequest request,Model model) throws ManagerException
	{
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		model.addAttribute("refundType", RefundType.values());
		model.addAttribute("loginModel", expenseRefundApplicationManager.findById(queryModel.getCardId(),queryModel.getId()));
		return prefix+"login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void loginPost( @RequestBody ExpenseRefundApplicationRequest queryModel ,HttpServletRequest request,HttpServletResponse response,Model model) throws ManagerException
	{
		ExpenseRefundApplicationResponse expenseRefundApplicationResponse=expenseRefundApplicationManager.save(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(expenseRefundApplicationResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public void selectPost( @RequestBody ExpenseRefundApplicationRequest queryModel ,HttpServletRequest request,HttpServletResponse response,Model model) throws ManagerException
	{
		ExpenseRefundApplicationResponse expenseRefundApplicationResponse=expenseRefundApplicationManager.select(queryModel);
		try {
			HttpMimeResponseHelper.responseJson(expenseRefundApplicationResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping( value ="/saveFile" , method = RequestMethod.POST )
	   public void fileUpload(@RequestParam("pathid") String id,@RequestParam("pathStatus") String status,HttpServletRequest request,@RequestParam("file") MultipartFile file,HttpServletResponse response) throws ManagerException{
		ExpenseRefundApplicationResponse expenseRefundApplicationResponse =  expenseRefundApplicationManager.saveFile(file,id,status);
		try {
			HttpMimeResponseHelper.responseJson(expenseRefundApplicationResponse.toJson(), response);
		} catch (IOException e) {	
			e.printStackTrace();
		}
	   }
	
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String showGet(@ModelAttribute("pageModel") ExpenseRefundApplicationRequest queryModel,HttpServletRequest request,Model model) throws ManagerException
	{
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		model.addAttribute("refundType", RefundType.values());
		model.addAttribute("pageModel", expenseRefundApplicationManager.select(queryModel));
		return prefix+"select";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void savePost( @RequestBody ExpenseRefundApplicationRequest queryModel ,HttpServletRequest request,HttpServletResponse response,Model model) throws ManagerException
	{
		ExpenseRefundApplicationResponse expenseRefundApplicationResponse=expenseRefundApplicationManager.save(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(expenseRefundApplicationResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@RequestMapping(value = "/trueFinance",method=RequestMethod.POST)
	public void trueFinance(@RequestBody ExpenseRefundApplicationRequest queryModel ,HttpServletRequest request,Model model,HttpServletResponse response) throws ManagerException{
		ExpenseRefundApplicationResponse expenseRefundApplicationResponse =expenseRefundApplicationManager.trueFinance(queryModel, LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(expenseRefundApplicationResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
			
			            
    @RequestMapping(value = "/showFile/{id}/{status}", method = RequestMethod.GET)
	public void showFile(@ModelAttribute("pageModel") ExpenseRefundApplicationRequest queryModel,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException, IOException {
    	expenseRefundApplicationManager.download(queryModel,response,request);
	}

	
	
	
	
	
}

