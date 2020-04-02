/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
package cn.com.taiji.css.web.customerservice.report;

import java.util.ArrayList;
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
import cn.com.taiji.css.manager.customerservice.report.DailySheetManager;
import cn.com.taiji.css.model.customerservice.report.CountModel;
import cn.com.taiji.css.web.MyLogController;
import cn.com.taiji.qtk.entity.dict.AgencyName;

@Controller
@RequestMapping("/customerservice/report/dailysheet")
public class DailySheetController extends MyLogController{
	private final String prefix = "customerservice/report/dailysheet/";
	
	@Autowired
	private DailySheetManager dailySheetManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") CountModel queryModel,Model model)
	{
		model.addAttribute("AgencyName", AgencyName.values());
		return prefix+"manage";
	}

	
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") CountModel queryModel,HttpServletRequest request, Model model) throws ManagerException
	{  
		model.addAttribute("pagn", dailySheetManager.queryPage(queryModel,request));
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/view/{regionName}/{startDate}/{endDate}/{amount}",method = RequestMethod.GET)
	public String viewPost(@PathVariable("regionName")String regionName,@PathVariable("startDate")String startDate,@PathVariable("endDate")String endDate,@PathVariable("amount")String amount,HttpServletRequest request,Model model) throws ManagerException
	{
		CountModel countModel=new CountModel();
		countModel.setRegionName(regionName);
		countModel.setStartDate(startDate);
		countModel.setEndDate(endDate);
		countModel.setFee(amount);
		String fee = ""+dailySheetManager.sumCash(countModel);
		countModel.setCash(fee);
		List<CountModel> pageModel = new ArrayList<CountModel>();
		pageModel.add(countModel);
		model.addAttribute("pageModel", pageModel);
		return prefix+"viewResult";
	}
	
}

