package cn.com.taiji.css.web.reportquerry;

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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.pub.json.JsonTools;
import cn.com.taiji.css.manager.customerservice.report.LssuancePerBankManager;
import cn.com.taiji.css.manager.report.truckopencard.TruckOpenCardManager;
import cn.com.taiji.css.model.customerservice.report.QueryTimes;

@Controller
@RequestMapping("/report/truckOpenCard")
public class TruckOpenCardController {
	private final String prefix = "report/truckopencard/";

	@Autowired
	private TruckOpenCardManager truckOpenCardManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") QueryTimes queryModel,Model model)
	{
		return prefix+"manage";
	}

	
	@RequestMapping(value = "/manage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String managePost(@RequestBody QueryTimes queryModel,HttpServletRequest request,HttpServletResponse response, Model model) throws ManagerException, IOException
	{  
		return JsonTools.toJsonStr(truckOpenCardManager.page(queryModel));
	}
	
}
