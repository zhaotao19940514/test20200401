
package cn.com.taiji.css.web.apply.baseinfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.LargePagination;
import cn.com.taiji.css.manager.apply.baseinfo.AccountTradeManager;
import cn.com.taiji.css.model.apply.customermanager.AccountTradeRequest;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.AccountTradeDetail;
import cn.com.taiji.qtk.entity.dict.TradeType;

/**
 * @ClassName LossController.java
 * @author zhaotao
 * @Description 
 * @date2018年11月28日
 */
@Controller
@RequestMapping("/apply/baseinfo/accounttrade")
public class AccountTradeController extends MyLogController {
	private final String prefix = "apply/baseinfo/accounttrade/";
	
	@Autowired
	private AccountTradeManager accountTradeManager;
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") AccountTradeRequest queryModel, Model model)
	{
		return prefix+"manage";
	}
	
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") AccountTradeRequest queryModel, HttpServletRequest request, Model model,HttpServletResponse response) throws ManagerException
	{
		LargePagination<AccountTradeDetail> pagn = accountTradeManager.queryPage(queryModel);
		model.addAttribute("TradeType", TradeType.values());
		model.addAttribute("pagn", pagn);
		return prefix+"queryResult";
	}
	
}

