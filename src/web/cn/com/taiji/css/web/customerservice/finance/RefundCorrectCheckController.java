/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
package cn.com.taiji.css.web.customerservice.finance;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.manager.customerservice.finance.RefundCorrectCheckManager;
import cn.com.taiji.css.model.customerservice.finance.RefundCorrectCheckRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.ReckonAccountRefundDetail;
import cn.com.taiji.qtk.entity.dict.RefundDetailType;

@Controller
@RequestMapping("/customerservice/finance/refundcorrectcheck")
public class RefundCorrectCheckController extends MyLogController{
	private final String prefix = "customerservice/finance/refundcorrectcheck/";
	
	@Autowired
	private RefundCorrectCheckManager refundCorrectCheckManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") RefundCorrectCheckRequest queryModel, Model model,HttpServletRequest request) throws ManagerException
	{
		
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") RefundCorrectCheckRequest queryModel, Model model,HttpServletRequest request) throws ManagerException
	{
		
		LargePagination<ReckonAccountRefundDetail> pagn = refundCorrectCheckManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
		model.addAttribute("RefundDetailType", RefundDetailType.values());
		return prefix+"queryResult";
	}
}

