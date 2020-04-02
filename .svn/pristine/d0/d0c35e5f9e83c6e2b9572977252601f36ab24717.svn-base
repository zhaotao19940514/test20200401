/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
package cn.com.taiji.css.web.customerservice.finance;

import java.io.File;

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
import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.finance.CardAccountRefundManager;
import cn.com.taiji.css.model.UserRequset;
import cn.com.taiji.css.model.UserResponse;
import cn.com.taiji.css.model.customerservice.finance.CardAccountRefundRequest;
import cn.com.taiji.css.model.customerservice.finance.ExpenseRefundApplicationResponse;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.AccountRefundDetail;
import cn.com.taiji.qtk.entity.dict.CardRefundDetailType;
import cn.com.taiji.qtk.entity.dict.CustomerIDType;
import cn.com.taiji.qtk.entity.dict.RefundDetailType;

@Controller
@RequestMapping("/customerservice/finance/cardaccountrefund")
public class CardAccountRefundController extends MyLogController{
	private final String prefix = "customerservice/finance/cardaccountrefund/";
	
	@Autowired
	private CardAccountRefundManager cardAccountRefundManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CardAccountRefundRequest queryModel, Model model,HttpServletRequest request)
	{
		model.addAttribute("RefundDetailType", RefundDetailType.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CardAccountRefundRequest queryModel,HttpServletRequest request,Model model) throws ManagerException
	{
		queryModel.setIsConfirm(0);
		LargePagination<AccountRefundDetail> pagn = cardAccountRefundManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("pagn", pagn);
		model.addAttribute("RefundDetailType", RefundDetailType.values());
		model.addAttribute("CardRefundDetailType", CardRefundDetailType.values());
		model.addAttribute("customerIDTypes", CustomerIDType.values());
		return prefix+"queryResult";
	}
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	public void exportExcel(@Valid @ModelAttribute("pageModel") CardAccountRefundRequest queryModel,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException {
		cardAccountRefundManager.exportExcel(queryModel,request,response,LoginHelper.getLoginUser(request));
	}
	
	@RequestMapping( value ="/importFile" , method = RequestMethod.POST )
	   public void importFile(HttpServletRequest request,@RequestParam("file") MultipartFile file,HttpServletResponse response){
		ExpenseRefundApplicationResponse expenseRefundApplicationResponse = new ExpenseRefundApplicationResponse();
		try {
			expenseRefundApplicationResponse=cardAccountRefundManager.saveFile(file);
			HttpMimeResponseHelper.responseJson(expenseRefundApplicationResponse.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   }
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String setupFile(@ModelAttribute("pageModel") User user,HttpServletRequest request, Model model)
	{
		return prefix + "file";
	}
	
	@RequestMapping( value ="/handleFile" , method = RequestMethod.POST )
	   public void fileUpload(HttpServletRequest request,@RequestParam("file") MultipartFile file,HttpServletResponse response){
		ExpenseRefundApplicationResponse expenseRefundApplicationResponse = new ExpenseRefundApplicationResponse();
		try {
			expenseRefundApplicationResponse=cardAccountRefundManager.saveFile(file);
			HttpMimeResponseHelper.responseJson(expenseRefundApplicationResponse.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   }
	
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public void loginPost( @RequestBody UserRequset queryModel ,HttpServletRequest request,HttpServletResponse response,Model model) throws ManagerException
	{	
		UserResponse userResponse=new UserResponse();
		try {
			File f =new File(queryModel.getFilePath()+queryModel.getFileName());
			userResponse=cardAccountRefundManager.importExcel(cardAccountRefundManager.getLines(f),LoginHelper.getLoginUser(request),queryModel);
			if(userResponse.getStatus()==1) {
				userResponse.setMessage("此Excel内的数据已导入！");
			}
			HttpMimeResponseHelper.responseJson(userResponse.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

