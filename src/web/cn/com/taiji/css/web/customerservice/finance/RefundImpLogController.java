/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
package cn.com.taiji.css.web.customerservice.finance;

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
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.customerservice.finance.RefundImpLogManager;
import cn.com.taiji.css.model.customerservice.finance.RefundImpLogRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.RefundImpFailMessage;
import cn.com.taiji.qtk.entity.RefundImpLog;

@Controller
@RequestMapping("/customerservice/finance/refundimplog")
public class RefundImpLogController extends MyLogController{
	private final String prefix = "customerservice/finance/refundimplog/";
	
	@Autowired
	private RefundImpLogManager refundImpLogManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") RefundImpLogRequest queryModel, Model model,HttpServletRequest request)
	{
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") RefundImpLogRequest queryModel,HttpServletRequest request,Model model) throws ManagerException
	{
		if(queryModel.getCardId() == null) {
			LargePagination<RefundImpLog> pagn = refundImpLogManager.queryPage(queryModel,LoginHelper.getLoginUser(request));
			model.addAttribute("pagn", pagn);
			return prefix+"queryResult";
		}else {
			List<RefundImpFailMessage> details = refundImpLogManager.queryDetails(queryModel);
			model.addAttribute("result", details);
			return prefix+"queryResultDetail";
		}
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("id") String id, HttpServletRequest request, Model model)
	{
		List<RefundImpFailMessage> list = refundImpLogManager.listFailMessage(id);
		model.addAttribute("list", list);
		return prefix + "edit";
	}
}

