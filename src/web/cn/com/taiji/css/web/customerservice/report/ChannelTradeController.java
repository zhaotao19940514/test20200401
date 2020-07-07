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
import cn.com.taiji.css.manager.customerservice.report.ChannelTradeManager;
import cn.com.taiji.css.manager.customerservice.report.RegionStatisticsManager;
import cn.com.taiji.css.model.customerservice.report.RegionStatisticsModel;
import cn.com.taiji.css.web.MyLogController;

@Controller
@RequestMapping("/customerservice/report/channeltrade")
public class ChannelTradeController extends MyLogController{
	private final String prefix = "customerservice/report/channeltrade/";
	
	@Autowired
	private  ChannelTradeManager channelTradeManager;
	
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") RegionStatisticsModel queryModel,Model model)
	{
		return prefix+"manage";
	}

	
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") RegionStatisticsModel queryModel,HttpServletRequest request, Model model) throws ManagerException
	{  
		model.addAttribute("pagn", channelTradeManager.queryPage(queryModel,request));
		return prefix+"queryResult";
	}

}
