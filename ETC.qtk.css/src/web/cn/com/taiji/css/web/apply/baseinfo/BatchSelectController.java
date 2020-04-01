/**
 * @Title RechargeController.java
 * @Package cn.com.taiji.css.web.customerservice.finance
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月25日 下午5:14:54
 * @version V1.0
 */
package cn.com.taiji.css.web.apply.baseinfo;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.taiji.css.entity.dict.BatchIssueStatus;
import cn.com.taiji.css.manager.apply.baseinfo.InportInfomationManager;
import cn.com.taiji.css.model.apply.inportInfomation.InportInfomationRequset;
import cn.com.taiji.css.web.MyLogController;

/**
 * lz
 */
@Controller
@RequestMapping("/apply/baseinfo/select")
public class BatchSelectController extends MyLogController {
	
	private final String prefix = "apply/baseinfo/select/";
	
	@Autowired
	private InportInfomationManager inportInfomationManager;

	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("queryModel") InportInfomationRequset queryModel, Model model)
	{
		model.addAttribute("types", BatchIssueStatus.values());
		return prefix+"manage";
	}
	
	@RequestMapping(value = "/manage", method = RequestMethod.POST)
	public String managePost(@ModelAttribute("queryModel") InportInfomationRequset queryModel, Model model,HttpServletResponse response) throws IOException
	{
		model.addAttribute("pagn",inportInfomationManager.queryBatch());
		return prefix+"queryResult";
	}

	
}

