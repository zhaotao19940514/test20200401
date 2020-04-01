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
import cn.com.taiji.css.manager.administration.inventory.ObuInventoryQueryManager;
import cn.com.taiji.css.model.administration.inventory.StorageObuInfoRequest;
import cn.com.taiji.css.web.MyLogController;
@Controller
@RequestMapping("/administration/inventory/inventoryQuery/obu")
public class ObuInventoryQueryController extends MyLogController{
	private final String prefix = "administration/inventory/inventoryQuery/obu/";
	@Autowired
	private ObuInventoryQueryManager obuInventoryQueryManager;
	
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") StorageObuInfoRequest queryModel, Model model) {
		return prefix + "manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") StorageObuInfoRequest queryModel,
			HttpServletRequest request, Model model) throws ManagerException {
		Pagination pagn = obuInventoryQueryManager.queryPage(queryModel);
		model.addAttribute("pagn", pagn);
		return prefix + "queryResult";
	}
	
}
