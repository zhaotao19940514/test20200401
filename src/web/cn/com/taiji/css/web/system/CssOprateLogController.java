/**
 * @Title CssOprateLogController.java
 * @Package cn.com.taiji.css.web.system
 * @Description TODO
 * @author yaonanlin
 * @date 2018年8月11日 下午2:07:42
 * @version V1.0
 */
package cn.com.taiji.css.web.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.entity.OperateLog;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.manager.acl.UserManager;
import cn.com.taiji.css.manager.apply.baseinfo.CardManager;
import cn.com.taiji.css.manager.apply.baseinfo.ChargeDetailManager;
import cn.com.taiji.css.manager.apply.baseinfo.CustomerManager;
import cn.com.taiji.css.manager.apply.baseinfo.ObuManager;
import cn.com.taiji.css.manager.apply.baseinfo.VehicleManagementManager;
import cn.com.taiji.css.manager.system.OperateLogManager;
import cn.com.taiji.css.model.receipt.ReceiptBaseModel;
import cn.com.taiji.css.model.system.request.CssOperateLogRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.CardInfo;
import cn.com.taiji.qtk.entity.ChargeDetail;
import cn.com.taiji.qtk.entity.CustomerInfo;
import cn.com.taiji.qtk.entity.OBUInfo;
import cn.com.taiji.qtk.entity.VehicleInfo;

/**
 * @ClassName CssOprateLogController
 * @author yaonl
 * @date 2018年08月11日 14:07:42
 * @E_mail yaonanlin@163.com
 */
@Controller
@RequestMapping("/system/operateLog")
public class CssOprateLogController extends MyLogController {
	private final String prefix = "system/operateLog/";
	@Autowired
	private UserManager userManager;
	@Autowired
	private CustomerManager customerManager;
	@Autowired
	private ObuManager obuInfoManager;
	@Autowired
	private CardManager cardManager;
	@Autowired
	private VehicleManagementManager vehicleManager;
	@Autowired
	private OperateLogManager operateLogManager;
	@Autowired
	private ChargeDetailManager chargeDetailManager;
	@RequestMapping(value="manage",method=RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CssOperateLogRequest req){
		return prefix+"manage";
	}
	@RequestMapping(value="manage",method=RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CssOperateLogRequest req,Model model){
		LargePagination<OperateLog> largePagination = operateLogManager.queryLargePage(req);
		model.addAttribute("pagn", largePagination);
		return prefix+"queryResult";
	}
	@RequestMapping(value="view/{id}",method=RequestMethod.GET)
	public String view(@PathVariable("id") String id,Model model){
		OperateLog log = operateLogManager.findById(id);
		model.addAttribute("vo", log);
		return prefix + "view";
	}
	@RequestMapping(value="viewUser/{userId}",method=RequestMethod.GET)
	public String viewUser(@PathVariable("userId") String userId,Model model){
		User user = userManager.findById(userId);
		model.addAttribute("vo", user);
		return prefix + "viewUser";
	}
	@RequestMapping(value="viewCustomer/{customerId}",method=RequestMethod.GET)
	public String viewCustomer(@PathVariable("customerId") String customerId,Model model) throws ManagerException{
		CustomerInfo customerInfo = customerManager.findByCustomerId(customerId);
		model.addAttribute("vo", customerInfo);
		return prefix + "viewCustomer";
	}
	@RequestMapping(value="viewObuInfo/{obuId}",method=RequestMethod.GET)
	public String viewObuInfo(@PathVariable("obuId") String obuId,Model model){
		OBUInfo obuInfo = obuInfoManager.findByObuId(obuId);
		model.addAttribute("vo", obuInfo);
		return prefix + "viewObuInfo";
	}
	@RequestMapping(value="viewCardInfo/{cardId}",method=RequestMethod.GET)
	public String viewCardInfo(@PathVariable("cardId") String cardId,Model model){
		CardInfo cardInfo = cardManager.findByCardId(cardId);
		model.addAttribute("vo", cardInfo);
		return prefix + "viewCardInfo";
	}
	@RequestMapping(value="viewChargeDetail/{chargeDetailId}",method=RequestMethod.GET)
	public String viewChargeDetail(@PathVariable("chargeDetailId") String chargeDetailId,Model model){
		ChargeDetail chargeDetail = chargeDetailManager.findByChargeDetailId(chargeDetailId);
		model.addAttribute("vo", chargeDetail);
		return prefix + "viewChargeDetail";
	}
	@RequestMapping(value="viewVehicleInfo/{vehicleId}",method=RequestMethod.GET)
	public String viewVehicleInfo(@PathVariable("vehicleId") String vehicleId,Model model){
		VehicleInfo vehicleInfo = vehicleManager.findByVehicleId(vehicleId);
		model.addAttribute("vo", vehicleInfo);
		return prefix + "viewVehicleInfo";
	}
	
	@RequestMapping(value="printReceipt/{id}",method=RequestMethod.GET)
	public String printReceipt(@PathVariable("id") String logId,Model model){
		ReceiptBaseModel receiptBaseModel = operateLogManager.toReceiptModel(logId);
		model.addAttribute("vo", receiptBaseModel);
		return prefix + "receipt";
	}
}

