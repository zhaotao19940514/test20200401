/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
package cn.com.taiji.css.web.customerservice.report;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.css.manager.customerservice.report.DailySheetManager;
import cn.com.taiji.css.model.customerservice.report.CountModel;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.dict.AgencyName;
import cn.com.taiji.qtk.entity.dict.Region;

@Controller
@RequestMapping("/customerservice/report/serverhallcount")
public class ServerHallCountController extends MyLogController{
	private final String prefix = "customerservice/report/serverhallcount/";
	
	@Autowired
	private DailySheetManager dailySheetManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CountModel queryModel,Model model)
	{
		model.addAttribute("AgencyName", AgencyName.values());
		model.addAttribute("Region", Region.values());
		return prefix+"manage";
	}

	
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CountModel queryModel,HttpServletRequest request, Model model) throws ManagerException
	{  
		model.addAttribute("pagn", dailySheetManager.page(queryModel,request));
		model.addAttribute("Region", Region.values());
		return prefix+"queryResult";
	}
	
}

