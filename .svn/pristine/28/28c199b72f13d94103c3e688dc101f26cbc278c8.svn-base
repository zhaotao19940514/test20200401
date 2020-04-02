/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
package cn.com.taiji.css.web.customerservice.card;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.net.http.HttpMimeResponseHelper;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.card.CancelManager;
import cn.com.taiji.css.manager.customerservice.card.CancelRefundManager;
import cn.com.taiji.css.manager.customerservice.finance.CardAccountRefundManager;
import cn.com.taiji.css.manager.customerservice.finance.InportRefundManager;
import cn.com.taiji.css.model.UserRequset;
import cn.com.taiji.css.model.appajax.AppAjaxResponse;
import cn.com.taiji.css.model.apply.inportInfomation.InportInfomationResponse;
import cn.com.taiji.css.model.customerservice.card.CancelRefundRequest;
import cn.com.taiji.css.model.customerservice.card.PreCancelRequest;
import cn.com.taiji.css.model.customerservice.finance.CardRefundExcelModel;
import cn.com.taiji.css.model.customerservice.finance.RefundInpExceptionResponse;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CardCancelRefund;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.dict.AccountCardBalanceOperateType;
import cn.com.taiji.qtk.entity.dict.BankType;
import cn.com.taiji.qtk.entity.dict.RefundDetailType;
import cn.com.taiji.qtk.entity.dict.VehiclePlateColorType;

/**
 * @ClassName CancelRefundController.java
 * @author zhaotao
 * @Description 
 * @date2018年12月24日
 */
@Controller
@RequestMapping("/customerservice/card/cancelrefund")
public class CancelRefundController extends MyLogController {
	private final String prefix = "customerservice/card/cancelrefund/";
	@Autowired
	private CancelRefundManager cancelrefundManager;
	@Autowired
	private InportRefundManager inportRefundManager;
	@Autowired
	private CancelManager cancelManager;
	@Autowired
	private CardAccountRefundManager cardAccountRefundManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CancelRefundRequest queryModel,HttpServletRequest request, Model model)
	{
		boolean reverFlag = cardAccountRefundManager.queryRevereRefund(LoginHelper.getLoginUser(request));
		model.addAttribute("vehiclePlateColorType", VehiclePlateColorType.values());
		model.addAttribute("refundDetailType", RefundDetailType.values());
		model.addAttribute("reverFlag", reverFlag);
		//判断是否有查看日志 冲正 二次核定的权限
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CancelRefundRequest queryModel, Model model, HttpServletRequest request) throws ManagerException
	{
		model.addAttribute("BankType", BankType.values());
		model.addAttribute("AccountCardBalanceOperateType", AccountCardBalanceOperateType.values());
		model.addAttribute("refundDetailType", RefundDetailType.values());
		Pagination pagn = cancelrefundManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String cardId,@ModelAttribute("pageModelEdit") PreCancelRequest queryModel, HttpServletRequest request, Model model) throws ManagerException
	{
		CardCancelRefund  refund =  cancelrefundManager.findOne(cardId);
		CardInfo cardInfo = cancelManager.findById(cardId);
		User loginUser = LoginHelper.getLoginUser(request);
		CustomerInfo cusInfo  = cancelrefundManager.findbyCustomerId(cardInfo.getCustomerId());
		if(refund==null) {
			throw new ManagerException("未查到退款信息");
		}
		if(loginUser.getRole().getName().equals("黔通智联管理员")||loginUser.getRole().getName().equals("黔通智联中心点")) {
			model.addAttribute("administrative", true);
		}else {
			model.addAttribute("administrative", false);
		}
		model.addAttribute("accountCardBalanceOperateType", AccountCardBalanceOperateType.values());
		model.addAttribute("customerName", cusInfo.getCustomerName());
		model.addAttribute("bankType",BankType.getBankEnums());
		model.addAttribute("refund", refund);
		return prefix + "edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public void porcessEdit(@Valid @RequestBody PreCancelRequest queryModel, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		AppAjaxResponse AppAjaxResponse = cancelrefundManager.updateCancelData(queryModel,LoginHelper.getLoginUser(request));
		try {
			HttpMimeResponseHelper.responseJson(AppAjaxResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
//	public void exportExcel(@Valid @ModelAttribute("pageModel") CancelRefundRequest queryModel,HttpServletResponse response,HttpServletRequest request,Model model) throws ManagerException {
//		cancelrefundManager.exportExcel(queryModel,request,response);
//	}
	@RequestMapping(value = "/view/{cardId}", method = RequestMethod.GET)
	public String view(@PathVariable("cardId") String cardId, Model model,HttpServletRequest request) throws ManagerException
	{
		CardCancelRefund  refund =  cancelrefundManager.findOne(cardId);
		if(null==refund) {
			throw new ManagerException("未查到退款信息");
		}
		List<String> picBase64 = cancelrefundManager.getScreenShotBase64(refund,request);
		model.addAttribute("picBase64", picBase64);
		return prefix + "view";
	}
	
	@RequestMapping(value = "/info/{cardId}", method = RequestMethod.GET)
	public String info(@PathVariable("cardId") String cardId,@ModelAttribute("pageModelEdit") PreCancelRequest queryModel, Model model,HttpServletRequest request) throws ManagerException
	{
		model.addAttribute("bankType",BankType.getBankEnums());
		model.addAttribute("refund", cancelrefundManager.findOne(cardId));
		return prefix + "info";
	}
	
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String setupFile(@ModelAttribute("pageModel") User user,HttpServletRequest request, Model model)
	{
		return prefix + "file";
	}
	
	@RequestMapping( value ="/handleFile" , method = RequestMethod.POST )
	public void fileUpload(HttpServletRequest request,@RequestParam("file") MultipartFile file,HttpServletResponse response){
		InportInfomationResponse inportInfomationResponse = new InportInfomationResponse();
		try {
			inportInfomationResponse=inportRefundManager.saveFile(file);
			HttpMimeResponseHelper.responseJson(inportInfomationResponse.toJson(), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public void loginPost( @RequestBody UserRequset queryModel ,HttpServletRequest request,HttpServletResponse response,Model model) 
	{	
		List<RefundInpExceptionResponse> res=Lists.newArrayList();
			File f =new File(queryModel.getFilePath()+queryModel.getFileName());
			try {
				res = inportRefundManager.importExcel(inportRefundManager.getLines(f),response);
				model.addAttribute("res", res);
				HttpMimeResponseHelper.responseJson(res.toString(), response);
				//HttpMimeResponseHelper.doDownLoad(request, response, file, file.getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//				try {
//				} catch (Exception e) {
//					e.printStackTrace();
//					inportInfomationResponse.setMessage("解析Excel文件失败(请用导入模板Excel存放数据)，请重新上传！");
//					inportInfomationResponse.setStatus(-1);
//				}
//				try {
//					HttpMimeResponseHelper.responseJson(inportInfomationResponse.toJson(), response);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			
	}
	   
	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	public void getExcelFile(@RequestBody CardRefundExcelModel queryModel ,HttpServletRequest request, Model model,HttpServletResponse response) throws ManagerException
	{
		User user = LoginHelper.getLoginUser(request);
		File export = inportRefundManager.getExcelFilePath(queryModel,user);
		try {
			HttpMimeResponseHelper.doDownLoad(request, response, export, export.getName());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("下载文件失败",e);
		}
	}
	@RequestMapping(value = "/attachConfirm", method = RequestMethod.POST)
	public void attachConfirm(@RequestParam("cardId") String cardId,@RequestParam("attachStatus") Integer attachStatus,HttpServletResponse response) {
		
		AppAjaxResponse AppAjaxResponse = cancelrefundManager.attachConfirm(cardId,attachStatus);
		try {
			HttpMimeResponseHelper.responseJson(AppAjaxResponse.toJson(), response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}


