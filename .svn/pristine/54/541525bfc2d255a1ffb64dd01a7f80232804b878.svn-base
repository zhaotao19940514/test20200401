
package cn.com.taiji.css.web.customerservice.finance;

import java.io.File;
import java.io.IOException;

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
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.finance.ExpenseRefundApplicationManager;
import cn.com.taiji.css.manager.customerservice.finance.ExpenseRefundAuditManager;
import cn.com.taiji.css.model.UserRequset;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationRequest;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundAuditRequest;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundAuditResponse;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CardRefundDetail;
import cn.com.taiji.qtk.entity.dict.AuditStatusType;
import cn.com.taiji.qtk.entity.dict.ChargeType;
import cn.com.taiji.qtk.entity.dict.RefundType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;

@Controller
@RequestMapping("/customerservice/finance/expenserefundaudit")
public class ExpenseRefundAuditController extends MyLogController {
	private final String prefix = "customerservice/finance/expenserefundaudit/";
	
	@Autowired
	private ExpenseRefundAuditManager expenseRefundAuditManager;
	
	@Autowired
	private ExpenseRefundApplicationManager expenseRefundApplicationManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") ExpenseRefundAuditRequest queryModel, Model model)
	{
		model.addAttribute("auditStatusType", AuditStatusType.getAccountChargeEnums());
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		model.addAttribute("chargeType", ChargeType.getAccountChargeEnums());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") ExpenseRefundAuditRequest queryModel,HttpServletRequest request, Model model) throws ManagerException
	{
		queryModel.validate();
		LargePagination<CardRefundDetail> cardRefundResponse =expenseRefundAuditManager.page(queryModel);
		model.addAttribute("pagn", cardRefundResponse);
		return prefix+"queryResult";
    }
	
	
	@RequestMapping(value = "/audit/{id}", method = RequestMethod.GET)
	public String loginGet(@ModelAttribute("pageModel") ExpenseRefundApplicationRequest queryModel,HttpServletRequest request,Model model) throws ManagerException
	{
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		model.addAttribute("refundType", RefundType.values());
		model.addAttribute("pageModel", expenseRefundAuditManager.findById(queryModel.getId()));
		return prefix+"audit";
	}
	
	@RequestMapping(value = "/deleteById", method = RequestMethod.POST)
	public void loginPost( @RequestBody ExpenseRefundAuditRequest queryModel ,HttpServletRequest request,HttpServletResponse response,Model model) throws ManagerException
	{
		ExpenseRefundAuditResponse expenseRefundAuditResponse=expenseRefundAuditManager.deleteById(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(expenseRefundAuditResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/finance/{id}", method = RequestMethod.GET)
	public String financeGet(@ModelAttribute("pageModel") ExpenseRefundApplicationRequest queryModel,HttpServletRequest request,Model model) throws ManagerException
	{
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		model.addAttribute("refundType", RefundType.values());
		model.addAttribute("pageModel", expenseRefundAuditManager.findById(queryModel.getId()));
		return prefix+"audit";
	}
	
	@RequestMapping(value = "/finance", method = RequestMethod.POST)
	public void financePost( @RequestBody ExpenseRefundAuditRequest queryModel ,HttpServletRequest request,HttpServletResponse response,Model model) throws ManagerException
	{
		ExpenseRefundAuditResponse expenseRefundAuditResponse=expenseRefundAuditManager.save(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(expenseRefundAuditResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
	public String showGet(@ModelAttribute("pageModel") ExpenseRefundAuditRequest queryModel,HttpServletRequest request,Model model) throws ManagerException
	{
		model.addAttribute("vehiclePlateColorTypes", VehiclePlateColorType.values());
		model.addAttribute("refundType", RefundType.values());
		model.addAttribute("pageModel", expenseRefundAuditManager.select(queryModel));
		return prefix+"select";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void savePost( @RequestBody ExpenseRefundAuditRequest queryModel ,HttpServletRequest request,HttpServletResponse response,Model model) throws ManagerException
	{
		ExpenseRefundAuditResponse expenseRefundAuditResponse=expenseRefundAuditManager.update(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(expenseRefundAuditResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public void exportExcel(@ModelAttribute("pageModel") ExpenseRefundAuditRequest queryModel,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException {
		expenseRefundAuditManager.exportExcel(queryModel,request,response);
		
	}
	
	@RequestMapping(value = "/showFile/{id}/{status}", method = RequestMethod.GET)
  	public void showFile(@ModelAttribute("pageModel") ExpenseRefundApplicationRequest queryModel,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException, IOException {
      	expenseRefundApplicationManager.download(queryModel,response,request);
  	}
	
	
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String setupFile(@ModelAttribute("pageModel") User user,HttpServletRequest request, Model model)
	{
		return prefix + "file";
	}
	
	@RequestMapping( value ="/handleFile" , method = RequestMethod.POST )
	   public void fileUpload(HttpServletRequest request,@RequestParam("file") MultipartFile file,HttpServletResponse response){
		ExpenseRefundAuditResponse expenseRefundAuditResponse = new ExpenseRefundAuditResponse();
		try {
			expenseRefundAuditResponse=expenseRefundAuditManager.saveFile(file);
			HttpMimeResponseHelper.responseJson(expenseRefundAuditResponse.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   }
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public void loginPost( @RequestBody UserRequset queryModel ,HttpServletRequest request,HttpServletResponse response,Model model) throws ManagerException
	{	
		ExpenseRefundAuditResponse expenseRefundAuditResponse=new ExpenseRefundAuditResponse();
		try {
			File f =new File(queryModel.getFilePath()+queryModel.getFileName());
			expenseRefundAuditResponse=expenseRefundAuditManager.importExcel(expenseRefundAuditManager.getLines(f));
			HttpMimeResponseHelper.responseJson(expenseRefundAuditResponse.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

