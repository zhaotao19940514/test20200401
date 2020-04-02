package cn.com.taiji.css.web.administration.inventory;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.model.dao.Pagination;
import cn.com.taiji.css.manager.LoginHelper;
import cn.com.taiji.css.manager.administration.inventory.InventoryQueryManager;
import cn.com.taiji.css.model.administration.inventory.StorageCardInfoRequest;
import cn.com.taiji.css.web.MyLogController;
@Controller
@RequestMapping("/administration/inventory/inventoryQuery/card")
public class CardInventoryQueryController extends MyLogController{
	private final String prefix = "administration/inventory/inventoryQuery/card/";
	@Autowired
	private InventoryQueryManager inventoryQueryManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") StorageCardInfoRequest queryModel, Model model) {
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") StorageCardInfoRequest queryModel,
			HttpServletRequest request, Model model) throws ManagerException {
		String agencyId =  LoginHelper.getLoginUser(request).getStaff().getServiceHall().getAgencyId();
		queryModel.setAgencyId(agencyId);
		Pagination pagn = inventoryQueryManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
		return prefix + "queryResult";
	}

}
