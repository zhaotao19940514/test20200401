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
import cn.com.taiji.css.manager.customerservice.report.RegionStatisticsManager;
import cn.com.taiji.css.model.customerservice.report.RegionStatisticsModel;
import cn.com.taiji.css.web.MyLogController;

@Controller
@RequestMapping("/customerservice/report/regionstatistics")
public class RegionStatisticsController extends MyLogController{
	private final String prefix = "customerservice/report/regionstatistics/";
	
	@Autowired
	private RegionStatisticsManager regionStatisticsManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") RegionStatisticsModel queryModel,Model model)
	{
		return prefix+"manage";
	}

	
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") RegionStatisticsModel queryModel,HttpServletRequest request, Model model) throws ManagerException
	{  
		model.addAttribute("pagn", regionStatisticsManager.queryPage(queryModel,request));
//		model.addAttribute("Region", Region.values());
		return prefix+"queryResult";
	}
	
	@RequestMapping(value = "/edit/{reginNameAndDate}", method = RequestMethod.GET)
	public String setupEdit(@PathVariable("reginNameAndDate") String reginNameAndDate,HttpServletRequest request, Model model) throws ManagerException
	{
		List<RegionStatisticsModel> channelModel = regionStatisticsManager.queryChannelData(reginNameAndDate);
		model.addAttribute("channelModel", channelModel);
		return prefix + "edit";
	}
	
	/**
	 * 地区退款明细
	 */
	@RequestMapping(value = "/refund/{reginNameAndDate}", method = RequestMethod.GET)
	public String refund(@PathVariable("reginNameAndDate") String reginNameAndDate,HttpServletRequest request, Model model) throws ManagerException
	{
		List<RegionStatisticsModel> refundModel = regionStatisticsManager.queryRegionRefundData(reginNameAndDate);
		model.addAttribute("refundModel", refundModel);
		return prefix + "refund";
	}
}

