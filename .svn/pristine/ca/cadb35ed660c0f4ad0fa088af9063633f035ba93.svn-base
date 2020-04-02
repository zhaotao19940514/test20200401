/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
package cn.com.taiji.css.web.customerservice.report;

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
import cn.com.taiji.css.manager.customerservice.report.FinancialstatementManager;
import cn.com.taiji.css.model.customerservice.report.CountModel;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.dict.AgencyName;
import cn.com.taiji.qtk.entity.dict.Region;

@Controller
@RequestMapping("/customerservice/report/financialstatement")
public class FinancialstatementController extends MyLogController{
	private final String prefix = "customerservice/report/financialstatement/";
	
	@Autowired
	private FinancialstatementManager financialstatementManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CountModel queryModel,Model model)
	{
		model.addAttribute("AgencyName", AgencyName.values());
		model.addAttribute("RegionName", Region.values());
		return prefix+"manage";
	}

	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CountModel queryModel,HttpServletRequest request, Model model) throws ManagerException
	{  
		model.addAttribute("pagn", financialstatementManager.page(queryModel,request));
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/view/{channelId}/{startDate}/{endDate}",method = RequestMethod.GET)
	public String viewPost(@PathVariable("channelId")String channelId,@PathVariable("startDate")String startDate,@PathVariable("endDate")String endDate,HttpServletRequest request,Model model) throws ManagerException
	{
		CountModel countModel=new CountModel();
		countModel.setChannelId(channelId);
		countModel.setStartDate(startDate);
		countModel.setEndDate(endDate);
		List<CountModel> pageModel = financialstatementManager.findByChannelId(countModel);
		model.addAttribute("pageModel", pageModel);
		return prefix+"viewResult";
	}
}

