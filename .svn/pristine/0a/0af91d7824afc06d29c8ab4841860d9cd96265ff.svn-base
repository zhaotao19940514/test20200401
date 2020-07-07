package cn.com.taiji.css.web.administration.refund;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.JsonManagerException;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.entity.Unit;
import cn.com.taiji.css.entity.User;
import cn.com.taiji.css.entity.dict.CssOperateLogType;
import cn.com.taiji.css.entity.dict.CssServiceLogType;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.administration.refund.DuplicateRefundManager;
import cn.com.taiji.css.model.administration.refund.DuplicateRefundPageRequest;
import cn.com.taiji.css.model.customerservice.card.balance.CardBalancePaymentBackLogEntity;
import cn.com.taiji.css.model.customerservice.report.DuplicateRefundLogEntity;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.DuplicateRefund;
import cn.com.taiji.qtk.entity.dict.DuplicateRefundType;

@Controller
@RequestMapping("/administration/refund")
public class DuplicateRefundController extends MyLogController {
	@Autowired
	private DuplicateRefundManager duplicateRefundManager;
	private final String prefix = "administration/refund/";

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") DuplicateRefundPageRequest duplicateRefundPageRequest,
			Model model) {
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@Valid @ModelAttribute("queryModel") DuplicateRefundPageRequest duplicateRefundPageRequest,
			Model model) {
		model.addAttribute("type", DuplicateRefundType.values());
		model.addAttribute("pagn", duplicateRefundManager.queryLargePage(duplicateRefundPageRequest));
		return prefix + "queryResult";
	}
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, HttpServletRequest request,Model model)
	{
		model.addAttribute("pageModel", duplicateRefundManager.findById(id));
		return prefix + "view";
	}
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String radio(@PathVariable("id") String id,HttpServletRequest request,Model model)
	{
		model.addAttribute("pageModel", duplicateRefundManager.findById(id));
		return prefix + "edit";
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("pageModel") DuplicateRefund duplicateRefund, HttpServletRequest request, Model model,
			HttpServletResponse response) throws ManagerException
	{
		User loginUser=LoginHelper.getLoginUser(request);
		if(loginUser==null) {
			addSuccess(response, "无登入用户信息");
		}else {
		duplicateRefundManager.paymentBack(duplicateRefund, loginUser);
		addSuccess(response, "操作成功");
		doSysLog(request, CssServiceLogType.CUSTOMERSERVICE_REFUND, CssOperateLogType.UPDATE, null, "银行回款",new DuplicateRefundLogEntity(duplicateRefund,LoginHelper.getLoginUser(request)));
		}
		return prefix + "queryResult";
	}
	
}